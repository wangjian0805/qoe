function showMapData(){

	if(!isSupportCanvas){
		alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
		return;
	}
	var map = new BMap.Map("baidumap");          // 创建地图实例
	var point = new BMap.Point(116.364549,39.968112);
	map.centerAndZoom(point, 18); // 初始化地图，设置中心点坐标和地图级别
	map.enableScrollWheelZoom(); // 允许滚轮缩放

    var myIcon1 =new BMap.Icon("assets/img/transparent.png", new BMap.Size(10, 10), {imageOffset: new BMap.Size(0, 0)});
    var myShadowIcon = new BMap.Icon("assets/img/transparent.png", new BMap.Size(10, 10), {imageOffset: new BMap.Size(0, 0)});

	var points  = [];
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
	map.addOverlay(heatmapOverlay);
	$.ajax({
		url:"http://localhost:8080/api/high/map",
		dataType:"json",
		type:"GET",
		success:function(response){
			var data = response.data;
			// heatmapOverlay.setDataSet({data:points,max:5});
			var point;
			var marker;
			for(var i=0;i<data.length;i++){
				point=data[i]
				points[i]=point;
				// 为每个点添加标注marker
				marker = new BMap.Marker(new BMap.Point(point.lng,point.lat));
				marker.setIcon(myIcon1);
				marker.setShadow(myShadowIcon);
				map.addOverlay(marker);
				var mac_time = data[i].mark;
				showInfo(point.count,marker,mac_time);
			}
			map.addOverlay(heatmapOverlay);
			heatmapOverlay.setDataSet({data:points,max:500});
		}, error: function() {}
	});
	heatmapOverlay.show();
}
	//	为每个marker设置监听
function showInfo(content,marker,mac_time){
	console.log("content:  "+content);
	var contentt = "体验指数："+content+"";  
	var opts = {
      	width : 0,     // 信息窗口宽度
		height: 0,		// 信息窗口高度
		enableMessage:false
	};
	var infowindow = new BMap.InfoWindow(contentt,opts);
	marker.addEventListener("mouseover",function(){
		this.openInfoWindow(infowindow);
	});
	marker.addEventListener("mouseout",function(e){
		this.closeInfoWindow(infowindow);
	});
    marker.addEventListener("click",function(e){
    	console.info("点击了热图")
		console.info("点击的这个点是"+mac_time)
    	$("#index-title").innerHTML = "本次测量获取参数的详细展示";
        $("#baidumap").hide();
        var remoteUrl = "http://localhost:8080/api/high/detail?mark="+mac_time;
        $.ajax({
            url:remoteUrl,
            dataType:"json",
            type:"GET",
            success:function(response){
                var data = response.data;
                score_chart.xAxis[0].categories = data.xAxis
                score_chart.series[0].setData(data.subScore)
                score_chart.series[1].setData(data.objScore)
                score_chart.series[2].setData(data.messageDelay)
                score_chart.series[3].setData(data.playPercentage)
                score_chart.series[4].setData(data.bufferPercentage)
                score_chart.series[5].setData(data.sendSpeed)
                score_chart.series[6].setData(data.netSpeed)
                score_chart.series[7].setData(data.memoryConsumption)
            }
        });
        $("#detail-container").show();
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
