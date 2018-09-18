var ip = "localhost:8080";
//初始化index
function initpage(){
	//阻止表单提交事件
	document.querySelector('#operatorform').addEventListener('submit',function(e){
		 e.preventDefault();
	},false);
	console.log("阻止表单提交事件");
	//设置 时间选择器
	$(".form_datetime").datetimepicker({
    	format: 'yyyy-mm-dd',
    	autoclose:true,
		minView:2});
		
	var mydate = new Date();
		var y = mydate.getFullYear();  
        var m = mydate.getMonth() + 1;  
        m = m < 10 ? ('0' + m) : m;  
        var d = mydate.getDate();  
        d = d < 10 ? ('0' + d) : d;  
    var today = y + '-' + m + '-' + d;
	console.log("today: "+today);
	$("#timeStop").val(today);
	$("#radius").val(25);
	
	var timeSart = $("#timeStart").val();
	var timeStop = $("#timeStop").val();
	var radius = $("#radius").val();
	var againstHeaviness = $('#againstHeaviness').is(':checked')?true:false;
	//绘制热图 	
	showMapData(1,5,timeSart,timeStop,againstHeaviness,radius);
	
	//半径设置的监听
	console.log("半径设置的监听");
	$("#radius").mouseup(function() {
		console.log("radiusbar mouseup!")
		$("#radiusvalue").html($("#radius").val());
	});
	}

function showMapData( mosMin, mosMax,timeStart ,timeStop ,againstHeaviness,radius){
	console.log("进入showMapData("+mosMin+","+mosMax+","+timeStart+","+timeStop+","+againstHeaviness+","+radius+")");
    if(!isSupportCanvas){
        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
        return;
    }
    var map = new BMap.Map("baidumap",{enableMapClick:false});// 创建地图实例,构造底图时，关闭底图可点功能 
    var point = new BMap.Point(116.364895,39.968276);
    map.centerAndZoom(point, 18); // 初始化地图，设置中心点坐标和地图级别
    //map.enableScrollWheelZoom(); // 允许滚轮缩放
	map.disableDragging();     //禁止拖拽
	map.disableDoubleClickZoom(); //禁止双击放大
    var myIcon1 =new BMap.Icon("assets/img/transparent.png", new BMap.Size(20, 20), {imageOffset: new BMap.Size(0, 0)});
    var myShadowIcon = new BMap.Icon("assets/img/transparent.png", new BMap.Size(10, 10), {imageOffset: new BMap.Size(0, 0)});
    heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":radius,blur:"1","gradient":{0.2:'#0000FF',0.4:'#99FF00',0.6:'#FFFF00',1:'#FF0000'}});
    //0.2:'#3399FF',0.4:'#99FF00',0.6:'#FFFF00',1:'#FF0000'
    map.addOverlay(heatmapOverlay);
    var getMapDataUrl = "http://"+ip+"/qoe/api/high/map";
    getMapDataUrl = getMapDataUrl+
    	"?mosMin="+mosMin+"&mosMax="+mosMax+"&timeStart="+timeStart+"&timeStop="+timeStop+
    	"&againstHeaviness="+againstHeaviness+"&radius="+radius;
    console.log("最终getMapDataUrl: "+getMapDataUrl);
    
    $.ajax({
        url:getMapDataUrl,
        dataType:"json",
        type:"GET",
        success:function(response){
        	console.log("请求成功");
            var data = response.data;
            //热图统计信息
    		var mosMax = 0,mosMin = 5,mosAvg =0,mosNum = 0,userNum = 0;
            mosNum = data.length;
            var point;
            var marker;
            var buildingName="";var buildingNo = 1;
            for(var i=0;i<data.length;i++){
                point=data[i]
                // 为每个点添加标注marker
                marker = new BMap.Marker(new BMap.Point(point.lng,point.lat));
                marker.setIcon(myIcon1);
                marker.setShadow(myShadowIcon);
                map.addOverlay(marker);
                buildingName = point.buildingName;
                buildingNo = point.buildingNo;
                //console.log("该点位置信息："+buildingNo+": "+buildingName);
                var mac_time = data[i].mark;
                
                mosAvg+=point.count;
                if(point.count > mosMax){
                	mosMax = point.count;
                }
                if(point.count < mosMin){
                	mosMin = point.count;
                }
                showInfo(map,point.count,marker,mac_time,buildingName,buildingNo);
            }
            if(mosNum>0){
            	mosAvg  = mosAvg/mosNum;
           		mosAvg = mosAvg.toFixed(1);
            }else{
            	mosMin =0;
            	mosMax =0;
            	mosAvg = 0;
            }
            console.log("统计信息： +mosMax = "+mosMax+" mosMin = "+mosMin+" mosAvg ="+mosAvg
            +" mosNum = "+mosNum+" userNum = "+userNum);
            
            console.log("更新统计信息");
	            $("#info-mosNum").text(mosNum);
	            $("#info-mosAvg").text(mosAvg);
	            $("#info-mosMin").text(mosMin);
	            $("#info-mosMax").text(mosMax);
	            
            map.addOverlay(heatmapOverlay);
            heatmapOverlay.setDataSet({data,max:5});
        }, error: function() {}
    });
    heatmapOverlay.show();
    //添加模糊覆盖
    var polygon1 = new BMap.Polygon([
        new BMap.Point(116.349103,39.973099),
        new BMap.Point(116.361212,39.973181),
        new BMap.Point(116.361787,39.961154),
        new BMap.Point(116.352516,39.961265),
    ], {strokeColor:"#fff", strokeWeight:0.1, strokeOpacity:0,fillOpacity:0.7});
    polygon1.disableMassClear();
    map.addOverlay(polygon1);

    var polygon2 = new BMap.Polygon([
        new BMap.Point(116.367392,39.973596),
        new BMap.Point(116.376806,39.973431),
        new BMap.Point(116.377813,39.961375),
        new BMap.Point(116.367895,39.962151),
    ], {strokeColor:"#fff", strokeWeight:0.1, strokeOpacity:0,fillOpacity:0.7});
    polygon2.disableMassClear();
    map.addOverlay(polygon2);
}

//	为每个marker设置监听
function showInfo(map,count,marker,mac_time,buildingName,buildingNo){
    var contentt = "<div><h4 style='margin:0 0 0px 0;padding:0.2em 0'>详情</h4>" +
        "<img style='float:right;margin:1px' id='imgDemo' src='assets/img/building"+buildingNo+".jpg'" +
        "width='96' height='128' title='XXX'/>" +
        "<div><p style='margin:0;line-height:1.5;font-size:13px;text-indent:1em'>"+"体验指数: "+count+
        "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:1em'>"+"位置: "+buildingName+
        "</p ></div><br /><p style='text-indent:1em'>点击热点进入<br />“热点详情页”</p ></div>"
    /*
    var opts = {
        width : 0,     // 信息窗口宽度
        height: 0,		// 信息窗口高度
        enableMessage:false
    };
    */
    var t;
    var infowindow = new BMap.InfoWindow(contentt);
    marker.addEventListener("mouseover",function(){
        this.openInfoWindow(infowindow);
        window.clearTimeout(t);
    });
    marker.addEventListener("mouseout",function(e){
        this.closeInfoWindow(infowindow);
        //重新设置map中心
        t = window.setTimeout(function(){
       	 	map.panTo(new BMap.Point(116.364895,39.968276));
        },1500); 
    });
    marker.addEventListener("click",function(e){
        
        console.info("点击了热图,点击的这个点是"+mac_time);
     	console.info("跳转页面");
        window.location.href='parameter.html?mark='+mac_time;
        
    });
}

//是否显示热力图
function openHeatmap(){
    heatmapOverlay.show();
}

function closeHeatmap(){
    heatmapOverlay.hide();
}

//判断浏览区是否支持canvas
function isSupportCanvas(){
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}

function showChartData(){
    //清空显示区
    $('#baidumap').empty();
    $('#baidumap').css({"background-color":""});
}

//根据参数重新绘制热图
function reCreateHeatMap(){
	var mosMax,mosMin,timeStart,timeStop,againstHeaviness,radius;
	mosMax = $("#mosMax").val();
	mosMin = $("#mosMin").val();
	timeStart = $("#timeStart").val();
	timeStop = $("#timeStop").val();
	radius = $("#radius").val();
	var againstHeaviness = $('#againstHeaviness').is(':checked')?true:false;
	console.log("用户输入的热图参数： "+mosMin+", "+mosMax+", "+timeStart+", "+timeStop+", "+againstHeaviness+", "+radius);
	//开始重新绘制
	console.log("开始重新绘制");
	showMapData( mosMin, mosMax,timeStart ,timeStop ,againstHeaviness,radius);
}

//重置绘图参数
function resetOperations(){
	console.log("reset");
	var mydate = new Date();
	var y = mydate.getFullYear();  
    var m = mydate.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = mydate.getDate();  
    d = d < 10 ? ('0' + d) : d;  
	var today = y + '-' + m + '-' + d;
	$("#timeStop").val(today);
	$("#timeStart").val("2017-09-15");
	$("#mosMin").val(1);
	$("#mosMax").val(5);
	$("#radius").val(25);
	$("#radiusvalue").html(25);
}