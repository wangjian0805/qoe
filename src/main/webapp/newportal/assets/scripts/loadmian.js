function showMapData(){
	if(!isSupportCanvas){
		alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
		return;
	}
	var map = new BMap.Map("baidumap");          // 创建地图实例
	var point = new BMap.Point(116.364549,39.968112);
	map.centerAndZoom(point, 18); // 初始化地图，设置中心点坐标和地图级别
	map.enableScrollWheelZoom(); // 允许滚轮缩放
	var myIcon1 =new BMap.Icon("http://10.103.93.3:8080/WiBUPTInfo/transparent.png", new BMap.Size(90, 90), {imageOffset: new BMap.Size(0, 0)});
	var myShadowIcon = new BMap.Icon("http://10.103.93.3:8080/WiBUPTInfo/transparent.png", new BMap.Size(90, 90), {imageOffset: new BMap.Size(0, 0)});
	var points  = [];
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":50,
		"gradient":{".3":'rgb(70,176,53)',".5":'rgb(155,199,2)',".7":'rgb(255,174,0)',".8":'rgb(255,114,0)',".9":'rgb(253,48,35)'}
		});
	console.log("1.发送ajax请求")
	$.ajax({
		url:"http://localhost:8080/qoe/api/detect/data",
		dataType:"json",
		type:"GET",
		success:function(response){
		console.log("2.成功接收")
		var data = response.data;
		var point;
		var marker;
		for(var i=0;i<data.length;i++){
			point={
			"lng":data[i].longitude,
			"lat":data[i].latitude,
			"count":3,
			};
			console.log("point"+i+": "+point.lng+", "+point.lat+", "+point.count);
			points[i]=point;
			// 为每个点添加标注marker
			marker = new BMap.Marker(new BMap.Point(point.lng,point.lat)); 
			marker.setIcon(myIcon1);
			marker.setShadow(myShadowIcon);
			map.addOverlay(marker);              
			showInfo(point.count,marker);
		}	
			console.log("points数组，共"+points.length+"个数据")
			console.log("3.heatmap声明 ");
			map.addOverlay(heatmapOverlay);
			console.log("4.为heatmap添加数据集 ");
			heatmapOverlay.setDataSet({data:points,max:5});
		}, error: function() {}
	});
	heatmapOverlay.show();
}
    //详细的参数,可以查看heatmap.js的文档 https://github.com/pa7/heatmap.js/blob/master/README.md
    //参数说明如下:
    /* visible 热力图是否显示,默认为true
     * opacity 热力的透明度,1-100
     * radius 势力图的每个点的半径大小
     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
     *	{
     .2:'rgb(0, 255, 255)',
     .5:'rgb(0, 110, 255)',
     .8:'rgb(100, 0, 255)'
     }
     其中 key 表示插值的位置, 0~1.
     value 为颜色值.
     */
	//	为每个marker设置监听
function showInfo(content,marker){
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
