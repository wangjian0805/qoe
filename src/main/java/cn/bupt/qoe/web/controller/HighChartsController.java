package cn.bupt.qoe.web.controller;

import cn.bupt.qoe.mapper.DetectDataMapper;
import cn.bupt.qoe.mapper.HighChartsMapper;
import cn.bupt.qoe.model.*;
import cn.bupt.qoe.rest.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ambitous on 2017/7/16.
 */
@Controller
@RequestMapping("/api/high")
public class HighChartsController {


    private static final Logger logger = LoggerFactory.getLogger(HighChartsController.class);

    @Autowired
    HighChartsMapper highChartsMapper;

    @Autowired
    DetectDataMapper detectDataMapper;


    @RequestMapping("/osCompare")
    @ResponseBody//对象变json
    public WebResult getMapData(){
        WebResult result = new WebResult();
        List<OsCompareModel> dataList = highChartsMapper.getOsCompareData();
        List<List> list = new ArrayList<>();
        List<Integer>  xList = new ArrayList<>();
        List<Double> objList = new ArrayList<>();
        List<Double> subList = new ArrayList<>();
        int i = 1;
        for(OsCompareModel os : dataList){
            xList.add(i++);
            subList.add(os.getSub());
            objList.add(os.getObj());
        }
        list.add(xList);
        list.add(subList);
        list.add(objList);
        result.setData(list);
        return result;
    }
    @RequestMapping("/osCompareByBuilding")
    @ResponseBody//对象变json
    public WebResult getBuildingData(@RequestParam(value = "building", required = true) String building){
        WebResult result = new WebResult();
        List<BuildingModel> buildingMes = highChartsMapper.getBuildingMes();
        List<DetectData> allData = detectDataMapper.getAllDetectData();
        for(DetectData data : allData){
            addToCorrectLocation(data,buildingMes);
        }

        List<DetectData> detectDataList = new ArrayList<>();
        for(BuildingModel model:buildingMes){
            if(building.equals(model.getBuilding())){
                detectDataList = model.getDataList();
            }
        }
        List<OsCompareModel> osDataList = new ArrayList<>();
        for(DetectData data:detectDataList){
            for(TestData test:data.getTest()){
                OsCompareModel os = new OsCompareModel();
                os.setObj(test.getMos_obj());
                os.setSub(test.getMos_sub());
                osDataList.add(os);
            }
        }
        List<List> list = new ArrayList<>();
        List<Integer>  xList = new ArrayList<>();
        List<Double> objList = new ArrayList<>();
        List<Double> subList = new ArrayList<>();
        int i = 1;
        for(OsCompareModel os : osDataList){
            xList.add(i++);
            subList.add(os.getSub());
            objList.add(os.getObj());
        }
        list.add(xList);
        list.add(subList);
        list.add(objList);
        result.setData(list);
        return result;
    }

    @RequestMapping("/map")
    @ResponseBody//对象变json
    public WebResult getOsCompareData(@RequestParam("mosMin")int mosMin,
                                      @RequestParam("mosMax")int mosMax,@RequestParam("timeStart")String timeStart,
                                      @RequestParam("timeStop")String timeStop,@RequestParam("againstHeaviness") boolean againstHeaviness,
                                      @RequestParam("radius")double radius){
        System.out.println("进入/map");
        System.out.println("解析出参数 -- mosMin:"+mosMin+",mosMax:"+mosMax+",timeStart:"+timeStart+
                ",timeStop:"+timeStop+",againstHeaviness:"+againstHeaviness+",radius: "+radius);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<HotMapModel> hotMapModelList = new ArrayList<HotMapModel>();
        WebResult result = new WebResult();
        //建筑物数据库信息
        List<BuildingModel> buildingMes = highChartsMapper.getBuildingMes();
        //将起始时间转换为时间戳
        Date dateStart = null;
        Date dateStop = null;
        Long tStart = null;
        Long tStop = null;
        try {
            if(!timeStart.equals("")){
                dateStart = simpleDateFormat.parse(timeStart);
                tStart = dateStart.getTime();
            }
            if(!timeStop.equals("")){
                dateStop = simpleDateFormat.parse(timeStop);
                tStop = dateStop.getTime()+24*60*60*1000;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(
                "查询的参数-- mosMin: "+mosMin+",mosMax: "+mosMax+",tStart: "+tStart+
                        ",tStop: "+tStop+",againstHeaviness: "+againstHeaviness);
        HeatMapOperator hmo = new HeatMapOperator();
        hmo.setMosMin(mosMin);
        hmo.setMosMax(mosMax);
        hmo.setTimeStart(tStart);
        hmo.setTimeStop(tStop);
        hmo.setAgainstHeaviness(againstHeaviness);
        hotMapModelList = highChartsMapper.getHotMapDataByOptions(hmo);
        System.out.println("得到原始的 hotMapModelList，数组长度： " + hotMapModelList.size());
        // 过滤后的hotMapModelList
        List<HotMapModel> filteredHotMapModelList = new ArrayList<HotMapModel>();
        if(againstHeaviness){
            System.out.println("需要反重叠");
            double distance = 0.00013*(radius/25);
            System.out.println("distance: "+distance);
            //若需要 反重叠
            if (hotMapModelList.size() > 0) {
                // 将第一个元素保存到filteredHotMapModelList
                //System.out.println("将第一个元素保存到filteredHotMapModelList");
                HotMapModel h = new HotMapModel();
                Building b = new Building();
                //得到该点的建筑信息
                b = getBuildingMesforPostion(hotMapModelList.get(0).getLat(),hotMapModelList.get(0).getLng(), buildingMes);
                h.setLat(hotMapModelList.get(0).getLat());
                h.setLng(hotMapModelList.get(0).getLng());
                h.setCount(hotMapModelList.get(0).getCount());
                h.setMark(hotMapModelList.get(0).getMark());
                if(b !=null){
                    //System.out.println("获取该点地理信息："+b.getBuildingName());
                    h.setBuildingNo(b.getBuildingNo());
                    h.setBuildingName(b.getBuildingName());
                }else{
                    //System.out.println("该点未获取到地理信息");地理信息设置为默认
                    h.setBuildingNo(1);
                    h.setBuildingName("北邮校园");
                }
                //System.out.println("第一个元素的参数："+hotMapModelList.get(0).getLat()+","+hotMapModelList.get(0).getCount());
                filteredHotMapModelList.add(h);
                // 遍历hotMapModelList，将每个元素和filteredHotMapModelList中的元素对比，当经纬度之差都大于某个值时，才将该hotMapModelList存入filteredHotMapModelList
                for (int i = 0; i < hotMapModelList.size(); i++) {
                    Double longitude = hotMapModelList.get(i).getLng();
                    Double latitude = hotMapModelList.get(i).getLat();
                    for (int j = 0; j < filteredHotMapModelList.size(); j++) {
                        Double Baselongitude = filteredHotMapModelList.get(j).getLng();
                        Double Baselatitude = filteredHotMapModelList.get(j).getLat();
                        //System.out.print("Baselongitude:"+Baselongitude+",longitude:"+longitude);
                        //System.out.println("-------- Baselatitude:"+Baselatitude+",latitude:"+latitude);
                        if (Math.abs(longitude - Baselongitude) < distance && Math.abs(latitude - Baselatitude) < distance) {
                            break;
                        } else if (j == filteredHotMapModelList.size() - 1) {
                            HotMapModel nh = new HotMapModel();
                            Building nb = new Building();
                            //得到该点的建筑信息
                            nb = getBuildingMesforPostion(hotMapModelList.get(i).getLat(), hotMapModelList.get(i).getLng(), buildingMes);
                            nh.setLat(hotMapModelList.get(i).getLat());
                            nh.setLng(hotMapModelList.get(i).getLng());
                            nh.setCount(hotMapModelList.get(i).getCount());
                            nh.setMark(hotMapModelList.get(i).getMark());
                            if(nb != null){
                                //System.out.println("获取该点地理信息："+nb.getBuildingName());
                                nh.setBuildingNo(nb.getBuildingNo());
                                nh.setBuildingName(nb.getBuildingName());
                            }else{
                                //System.out.println("该点未获取到地理信息");地理信息设置为默认
                                nh.setBuildingNo(1);
                                nh.setBuildingName("北邮校园");
                            }
                            filteredHotMapModelList.add(nh);
                        }
                    }
                }
            }

        }else{
            filteredHotMapModelList = hotMapModelList;
            //遍历测量记录，加上位置信息

        }
        System.out.println("(过滤)后,数组长度： "+filteredHotMapModelList.size());
        result.setData(filteredHotMapModelList);
        return result;
    }

    @RequestMapping("/detail")
    @ResponseBody//对象变json
    public WebResult getDetailData(@RequestParam(value = "mark",required = false) String mark){
        WebResult result = new WebResult();
        DetectData oneDetectDataByMark = detectDataMapper.getOneDetectDataByMark(mark);
        DetailModel detailModel = data2Detail(oneDetectDataByMark);
        result.setData(detailModel);
        return result;
    }

    @RequestMapping("/stastic")
    @ResponseBody//对象变json
    public WebResult getStasticData(){
        WebResult result = new WebResult();
        Integer test_num = highChartsMapper.getTestNum();
        Integer mac_num = highChartsMapper.getMacNum();
        Integer play_num = highChartsMapper.getPlayNum();
        Integer play_time = highChartsMapper.getMonitorNum()/play_num;

        List<Integer> list = new ArrayList<>();
        list.add(test_num);
        list.add(mac_num);
        list.add(play_num);
        list.add(play_time);
        result.setData(list);
        return result;
    }

    @RequestMapping("/main")
    @ResponseBody//对象变json
    public WebResult getMosData(){
        WebResult result = new WebResult();
        MosData mosData = new MosData();
        List<Double> doubleList = highChartsMapper.getMosData();
        double sum = 0.0;
        for(Double num:doubleList){
            sum+=num;
        }
        mosData.setMos1(remain3dot(doubleList.get(0)/sum)*100);
        mosData.setMos2(remain3dot(doubleList.get(1)/sum)*100);
        mosData.setMos3(remain3dot(doubleList.get(2)/sum)*100);
        mosData.setMos4(remain3dot(doubleList.get(3)/sum)*100);
        mosData.setMos5(remain3dot(doubleList.get(4)/sum)*100);
        result.setData(mosData);
        return result;
    }
    @RequestMapping("/mainobj")
    @ResponseBody//对象变json
    public WebResult getObjMosData(){
        WebResult result = new WebResult();
        MosData mosData = new MosData();
        List<Double> doubleList = highChartsMapper.getObjMosData();
        double sum = 0.0;
        for(Double num:doubleList){
            sum+=num;
        }
        mosData.setMos1(remain3dot(doubleList.get(0)/sum)*100);
        mosData.setMos2(remain3dot(doubleList.get(1)/sum)*100);
        mosData.setMos3(remain3dot(doubleList.get(2)/sum)*100);
        mosData.setMos4(remain3dot(doubleList.get(3)/sum)*100);
        mosData.setMos5(remain3dot(doubleList.get(4)/sum)*100);
        result.setData(mosData);
        return result;
    }



    @RequestMapping("/area")
    @ResponseBody//对象变json
    public WebResult getTimeData(){
        WebResult result = new WebResult();

        List<String> categories = new ArrayList<>();
        List<Double> avgList = new ArrayList<>();
        List<Double> varianceList = new ArrayList<>();

        List<BuildingModel> buildingMes = highChartsMapper.getBuildingMes();
        List<DetectData> allData = detectDataMapper.getAllDetectData();
        System.out.println("测得的数据："+allData.size());
        for(DetectData data : allData){
            addToCorrectLocation(data,buildingMes);
        }
        System.out.println(buildingMes.size());
        for(BuildingModel model:buildingMes){
            System.out.println(model.getBuilding());
            System.out.println(model.getDataList().size());
            categories.add(model.getBuilding());
            Double sum = 0.0;
            Integer count = 0;
            Double tmp = 0.0;
            if(model.getDataList()==null){
                model.setDataList(new ArrayList<DetectData>());
                avgList.add(null);
                varianceList.add(null);
                continue;
            }
            for(DetectData data : model.getDataList()){
                count+=data.getTest().size();
                for(int i=0;i<data.getTest().size();i++){
                    sum+=data.getTest().get(i).getMos_sub();
                }
            }
            if(count == 0){
                avgList.add(null);
                varianceList.add(null);
                continue;
            }
            Double avg = sum/count;
            for(DetectData data : model.getDataList()){
                for(int i=0;i<data.getTest().size();i++){
                    tmp+=(data.getTest().get(i).getMos_sub()-avg)*(data.getTest().get(i).getMos_sub()-avg);
                }
            }
            Double variance = tmp/count;
            model.setAvg(avg);
            model.setVariance(variance);

            avgList.add(avg);
            varianceList.add(variance);
        }

        AreaModel areaModel = new AreaModel();
        areaModel.setCategories(categories);
        areaModel.setAvgList(avgList);
        areaModel.setVarianceList(varianceList);
        result.setData(areaModel);
        return result;
    }

    @RequestMapping("/itemlist")
    @ResponseBody//对象变json
    public WebResult getItemListData(@RequestParam(value = "pick_start_time", required = false) String pick_start_time,
                                     @RequestParam(value = "pick_end_time", required = false) String pick_end_time,
                                     @RequestParam(value = "pick_mac", required = false) String pick_mac){
        WebResult result = new WebResult();
        List<String> list = new ArrayList<>();
        list.add("CC088D0C80A8-201709181000");
        list.add("CC088D0C80A8-201709181100");
        list.add("CC088D0C80A8-201709181200");
        list.add("CC088D0C80A8-201709181300");
        list.add("CC088D0C80A8-201709181400");
        list.add("CC088D0C80A8-201709181500");
        list.add("CC088D0C80A8-201709181600");
        list.add("CC088D0C80A8-201709181700");
        list.add("CC088D0C80A8-201709181800");
        list.add("CC088D0C80A8-201709181900");
        list.add("CC088D0C80A8-201709181000");
        list.add("CC088D0C80A8-201709181100");
        list.add("CC088D0C80A8-201709181200");
        list.add("CC088D0C80A8-201709181300");
        list.add("CC088D0C80A8-201709181400");
        list.add("CC088D0C80A8-201709181500");
        list.add("CC088D0C80A8-201709181600");
        list.add("CC088D0C80A8-201709181700");
        list.add("CC088D0C80A8-201709181800");
        list.add("CC088D0C80A8-201709181900");
        result.setData(list);
        return result;
    }

    @RequestMapping("/spider")
    @ResponseBody//对象变json
    public WebResult getSpiderData(@RequestParam(value = "pick_start_time", required = false) String pick_start_time,
                                     @RequestParam(value = "pick_end_time", required = false) String pick_end_time,
                                     @RequestParam(value = "pick_mac", required = false) String pick_mac){
        WebResult result = new WebResult();
        List<List<Integer>> returnlist = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2233);
        list1.add(4433);
        list1.add(3344);
        list1.add(9988);
        list1.add(11111);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3344);
        list2.add(7755);
        list2.add(2233);
        list2.add(8899);
        list2.add(12000);
        returnlist.add(list1);
        returnlist.add(list2);
        result.setData(returnlist);
        return result;
    }

    @RequestMapping("/tdchart")
    @ResponseBody//对象变json
    public WebResult getTdChartData(@RequestParam(value = "pick_start_time", required = false) String pick_start_time,
                                   @RequestParam(value = "pick_end_time", required = false) String pick_end_time,
                                   @RequestParam(value = "pick_mac", required = false) String pick_mac){
        WebResult result = new WebResult();
        List<List<Integer>> returnlist = new ArrayList<>();
        List<Integer> sublist = new ArrayList<>();
        sublist.add(3);
        sublist.add(4);
        sublist.add(3);
        sublist.add(4);
        sublist.add(5);
        sublist.add(3);
        sublist.add(4);
        sublist.add(3);
        sublist.add(4);
        sublist.add(5);
        sublist.add(3);
        sublist.add(4);
        sublist.add(3);
        sublist.add(4);
        sublist.add(5);
        sublist.add(3);
        sublist.add(4);
        sublist.add(3);
        sublist.add(4);
        sublist.add(5);
        List<Integer> objlist = new ArrayList<>();
        objlist.add(2);
        objlist.add(3);
        objlist.add(1);
        objlist.add(4);
        objlist.add(2);
        objlist.add(2);
        objlist.add(3);
        objlist.add(1);
        objlist.add(4);
        objlist.add(2);
        objlist.add(2);
        objlist.add(3);
        objlist.add(1);
        objlist.add(4);
        objlist.add(2);
        objlist.add(2);
        objlist.add(3);
        objlist.add(1);
        objlist.add(4);
        objlist.add(2);
        returnlist.add(sublist);
        returnlist.add(objlist);
        result.setData(returnlist);
        return result;
    }

    @RequestMapping("/score")
    @ResponseBody//对象变json
    public WebResult getScoreData(@RequestParam(value = "mac_time", required = false) String mac_time) {
        WebResult result = new WebResult();
        List<List> list = new ArrayList<>();
        String[] xs = {"1s","2s","3s","4s","5s","6s","7s","8s","9s","10s","11s","12s","13s","14s","15s","16s","17s","18s","19s","20s","21s","22s","23s","24s","25s","26s","27s","28s","29s","30s"};
        Integer[] is1 = {0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 0, 5, 0, 0, 0, 0, 2};
        Integer[] is2 = {0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 0, 5, 0, 0, 0, 0, 2};
        Double[] ds1 = {1.2,3.4,4.6,9.9,11.0,13.2,13.9,17.5, 20.8,26.4, 29.2,32.3,34.0, 35.3,36.0, 45.6, 48.5,49.9, 51.4, 52.3,68.1, 69.3,72.6,77.6 ,84.4,87.3,92.3,94.2,97.5,100.0};
        Double[] ds2 = {2.1,4.2,5.2,8.9,10.9,14.0,15.3,18.5,24.6, 29.4, 30.2,35.2, 44.0,45.2, 47.0, 48.6, 58.5,59.9, 63.4,63.4, 74.1, 76.9,80.6, 83.2,94.4,99.0,100.0,100.0,100.0,100.0};
        list.add(Arrays.asList(xs));
        list.add(Arrays.asList(is1));
        list.add(Arrays.asList(is2));
        list.add(Arrays.asList(ds1));
        list.add(Arrays.asList(ds2));
        result.setData(list);
        return result;
    }


	/**
	 * ========================== Helper ==============================
	 */
	public double remain3dot(Double num) {
		DecimalFormat df = new DecimalFormat("0.000");
		return Double.parseDouble(df.format(num));
	}

	public DetailModel data2Detail(DetectData data) {
		DetailModel detailModel = new DetailModel();

		detailModel.setMac(data.getMac());
		detailModel.setCpu(data.getCpu());
		detailModel.setScreenPixels(data.getScreenPixels());
		detailModel.setMimeType(data.getMimeType());
		detailModel.setHeight(data.getHeight());
		detailModel.setWidth(data.getWidth());
		detailModel.setVideoStreamBitRate(data.getVideoStreamBitRate());
		detailModel.setVideoLength(data.getVideoLength());
		detailModel.setInitTime(data.getInitTime());
		detailModel.setVideoBufferStart(new Date(data.getVideoBufferStart()));
		detailModel.setRebuffNum(data.getStopNum());
		detailModel.setRebuffTime(data.getStopTimeAvg());
		Integer times = data.getTest().size();
		if (times == 0)
			return detailModel;
		detailModel.setScoreTime(times);
		Double max = 0.0, min = 5.0, avg = 0.0, var = 0.0;
		Double sum = 0.0;
		Double tmp = 0.0;
		for (TestData testData : data.getTest()) {
			if (testData.getMos_sub() > max)
				max = testData.getMos_sub();
			if (testData.getMos_sub() < min)
				min = testData.getMos_sub();
			sum += testData.getMos_sub();
		}
		avg = sum / times;
		for (TestData testData : data.getTest()) {
			tmp += ((testData.getMos_sub() - avg) * (testData.getMos_sub() - avg));
		}
		var = tmp / times;
		detailModel.setMaxScore(max);
		detailModel.setMinScore(min);
		detailModel.setAvgScore(avg);
		detailModel.setVariance(var);
		detailModel.setLongitude(data.getTest().get(0).getLongitude());
		detailModel.setLatitude(data.getTest().get(0).getLatitude());

		long overAllMos = Math.round(data.getMosOverall());
		String manyi = new String();
		if(overAllMos==1)   manyi="非常糟糕";
		else if(overAllMos==2)  manyi="比较差劲";
		else if(overAllMos==3)  manyi="一般";
		else if(overAllMos==4)  manyi="比较满意";
		else    manyi="非常满意";
		detailModel.setManyi(manyi);

		String bodong = new String();
		if(var<5)   bodong="较小";
		else if(var<10)     bodong="一般";
		else bodong="较大";
		detailModel.setBodong(bodong);


		Integer len = data.getMonitor().size();
		Double video_len = Double.valueOf(data.getVideoLength() * 1000);
		List<String> xAxis = new ArrayList<>();
		List<Double> objScore = new ArrayList<>();
		List<Double> subScore = new ArrayList<>();
		List<Double> messageDelay = new ArrayList<>();


		List<Double> playPercentage = new ArrayList<>();
		List<Double> bufferPercentage = new ArrayList<>();
		List<Double> sendSpeed = new ArrayList<>();
		List<Double> netSpeed = new ArrayList<>();
		List<Double> memoryConsumption = new ArrayList<>();
        List<Double> currentCPU = new ArrayList<>();
        int j = 0;
		Long startTime = data.getMonitor().get(0).getMonitorTimeStamp();
		for (Integer i = 0; i < len; i++) {
			xAxis.add(i + "s");
			playPercentage.add(remain3dot(Double.valueOf(data.getMonitor().get(i).getMonitorTime()) / video_len) * 100);
			bufferPercentage
					.add(remain3dot(Double.valueOf(data.getMonitor().get(i).getBufferPercentage()) / 100) * 100);
			sendSpeed.add(Double.valueOf(data.getMonitor().get(i).getSendSpeed()) / 1000);
			netSpeed.add(Double.valueOf(data.getMonitor().get(i).getNetSpeed()) / 1000);
			memoryConsumption.add(data.getMonitor().get(i).getMemoryConsumption());
            currentCPU.add(data.getMonitor().get(i).getCurrentCpu());
			if (j >= data.getTest().size()
					|| i != timeStamp2second(data.getTest().get(j).getTestTimeStamp(), startTime)) {
				objScore.add(0.0);
				subScore.add(0.0);
				messageDelay.add(0.0);
			} else {
				if (j >= data.getTest().size())
					continue;
				objScore.add(data.getTest().get(j).getMos_obj());
				subScore.add(data.getTest().get(j).getMos_sub());
				messageDelay.add(data.getTest().get(j).getMessageDelay());
				j++;
			}
		}
		detailModel.setxAxis(xAxis);
		detailModel.setObjScore(objScore);
		detailModel.setSubScore(subScore);
		detailModel.setMessageDelay(messageDelay);
		detailModel.setPlayPercentage(playPercentage);
		detailModel.setBufferPercentage(bufferPercentage);
		detailModel.setSendSpeed(sendSpeed);
		detailModel.setNetSpeed(netSpeed);
		detailModel.setMemoryConsumption(memoryConsumption);
		detailModel.setCurrentCPU(currentCPU);
		return detailModel;
	}

	public Integer timeStamp2second(Long timeStamp, Long startTime) {
		Long second = timeStamp - startTime;
		Long tmp = second / 1000;
		if (Math.abs(second - (second / 1000 * 1000)) < 500) {
			return Integer.valueOf(tmp.toString());
		} else if (second - (second / 1000 * 1000) > 500) {
			return Integer.valueOf(tmp.toString()) + 1;
		} else {
			return Integer.valueOf(tmp.toString()) - 1;
		}
	}

	public void addToCorrectLocation(DetectData data, List<BuildingModel> buildingModels) {
		if (data.getTest().size() == 0) {
			// System.out.println("没有test");
			return;
		}
		Double latitude = data.getTest().get(0).getLatitude();
		Double longitude = data.getTest().get(0).getLongitude();
		// System.out.println("开始定位一条数据："+latitude+" "+longitude);
		for (int i = 0; i < buildingModels.size(); i++) {
			if (longitude >= buildingModels.get(i).getLatitudeLeft()
					&& longitude <= buildingModels.get(i).getLatitudeRight()
					&& latitude >= buildingModels.get(i).getLongitudeDown()
					&& latitude <= buildingModels.get(i).getLongitudeUp()) {
				// System.out.println("成功定位一条数据："+data.getMark()+"...
				// "+buildingModels.get(i).getBuilding());
				buildingModels.get(i).getDataList().add(data);
				break;
			}
		}
	}

    //获取当前经纬度所处的建筑物信息

    public Building getBuildingMesforPostion(Double latitude,Double longitude,List<BuildingModel> buildingMes){
        Building result = new Building();
        for (int i = 0; i < buildingMes.size(); i++) {
            if (longitude >= buildingMes.get(i).getLatitudeLeft()
                    && longitude <= buildingMes.get(i).getLatitudeRight()
                    && latitude >= buildingMes.get(i).getLongitudeDown()
                    && latitude <= buildingMes.get(i).getLongitudeUp()) {
                result.setBuildingNo(buildingMes.get(i).getId());
                result.setBuildingName(buildingMes.get(i).getBuilding());
                //System.out.println("找到匹配的建筑物："+buildingMes.get(i).getBuilding());
                return result;
            }
        }
        //System.out.println("没有匹配的建筑物信息");
        result.setBuildingNo(0);
        result.setBuildingName("北邮校园");
        return result;
    }
}
