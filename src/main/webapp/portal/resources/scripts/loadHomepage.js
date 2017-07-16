var map = new BMap.Map("u2", {enableMapClick: false});          // 创建地图实例
var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
/*缩放控件type有四种类型:
 BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/

//添加控件和比例尺
function add_control(){
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);
}

map.centerAndZoom(new BMap.Point(116.403765, 39.914850), 8);
map.enableScrollWheelZoom();
map.enableInertialDragging();
map.enableScrollWheelZoom();
map.enableAutoResize();
map.addControl(top_right_navigation);

sessionStorage.loc = "city";
var rectangles = new Array();

map.addEventListener("zoomstart", function () {
	map.clearOverlays();
});


map.addEventListener("zoomend", function () {
    if(sessionStorage.loc === "district"){
		console.log(sessionStorage.loc);
        var point=map.getCenter();
        var zoom=map.getZoom();
        drawBoundaries(sessionStorage.district,point,zoom);
        setTimeout(function(){
            drawRect(sessionStorage.district);
			
        }, 500);

	}	
});




function getBoundaryInfo() {
	drawAll();
    $.ajax({  
        type:"POST",    
        url:"http://www.ping4g.cn:28080/wilysis/1.0/mosCounts",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"02",
            "standard":"13",
            "startTime":"2016-12-01 00:00:00",
            "endTime":"2017-01-01 23:59:59",
            "province":"北京市",
            "city":"北京市"
        }),
        success:function (data) {
            $("#u71").html(data.mosCount_4_5);
			$("#u75").html(data.mosCount_3_4);
			$("#u77").html(data.mosCount_2_3);
			$("#u79").html(data.mosCount_1_2);
			$("#u81").html(data.mosCount_0_1);
        }
    });
}

//画整体的边界
function drawAll(){
    var bdary = new BMap.Boundary();
    bdary.get("北京市", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.setViewport(ply.getPath());    //调整视野
        }
    });
    var bdary1 = new BMap.Boundary();
    bdary1.get("北京市东城", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary2 = new BMap.Boundary();
    bdary2.get("北京市门头沟", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary3 = new BMap.Boundary();
    bdary3.get("北京市怀柔", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary4 = new BMap.Boundary();
    bdary4.get("北京市西城", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary5 = new BMap.Boundary();
    bdary5.get("北京市房山", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary6 = new BMap.Boundary();
    bdary6.get("北京市平谷", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary7 = new BMap.Boundary();
    bdary7.get("北京市朝阳", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary8 = new BMap.Boundary();
    bdary8.get("北京市通州", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary9 = new BMap.Boundary();
    bdary9.get("北京市密云", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary10 = new BMap.Boundary();
    bdary10.get("北京市丰台", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary11 = new BMap.Boundary();
    bdary11.get("北京市顺义", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary12 = new BMap.Boundary();
    bdary12.get("北京市延庆", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary13 = new BMap.Boundary();
    bdary13.get("北京市石景山区", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary14 = new BMap.Boundary();
    bdary14.get("北京市昌平", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary15 = new BMap.Boundary();
    bdary15.get("北京市大兴", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
    var bdary16 = new BMap.Boundary();
    bdary16.get("北京市海淀", function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            //ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
        }
    });
}


setTimeout(function(){
    getBoundaryInfo();
}, 1000);

//获取地图比例尺
function getMapScale() {
    var scales = [20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000];
    var zoom = map.getZoom();
    var scale = scales[19 - zoom];
    return scale;
}



function drawRect(district){
	
    $.ajax({
        type:"POST",
        url:"http://www.ping4g.cn:28080/wilysis/1.0/summaries",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        // data:JSON.stringify({
        //     "operatorId":"02",
        //     "standard":"13",
        //     "startTime":"2016-12-01 00:00:00",
        //     "endTime":getEndTime(),
        //     "province":"北京市",
        //     "city":"北京市",
        //     "district":district,
        //     "distance":getMapScale()/10
        // }),
        data:JSON.stringify({
            "getData":"test"
        }),
        success:function (data) {
			/*
			$("#u107_div").html(data[0].avgMos);
			$("#u109_div").html(data[0].avgUploadSpeed + "/" + data[0].avgDownloadSpeed);
			$("#u111_div").html(data[0].avgRsrq);
			$("#u113_div").html(data[0].avgRsrp);
			$("#u115_div").html(data[0].count);
			*/
            sessionStorage.district = district;	
            rectangles.length = data.length;			
            for(var i = 0; i < data.length; i++){
				var pStart = new BMap.Point(data[i].longitudeWest, data[i].latitudeNorth);
				var pEnd = new BMap.Point(data[i].longitudeEast, data[i].latitudeSouth);
				rectangles[i] = new BMap.Polygon([
					new BMap.Point(pStart.lng,pStart.lat),
					new BMap.Point(pEnd.lng,pStart.lat),
					new BMap.Point(pEnd.lng,pEnd.lat),
					new BMap.Point(pStart.lng,pEnd.lat)
				], {strokeColor:"black", strokeWeight:1, strokeOpacity:1}); 
				
				var color = "#ff0000";
				var mos = data[i].avgMos;
			    if(mos <= 1 ){
				    color = "#00ffff"
				}else if( mos <= 2){
					color = "#0000ff";
				}else if(mos <= 3){
					color = "#ccff33";
				}else if(mos <= 4){
					color = "#9900ff";
				}else{
					color = "#ff0000";
				}
			
				rectangles[i].setFillColor(color);
				map.addOverlay(rectangles[i]);
				rectangles[i].mos = data[i].avgMos.toFixed(2);
				rectangles[i].uploadSpeed = ((data[i].avgUploadSpeed)/1000).toFixed(1);
				rectangles[i].downloadSpeed = (data[i].avgDownloadSpeed/1000).toFixed(1);				
				rectangles[i].rsrq = data[i].avgRsrq; 
				rectangles[i].rsrp = data[i].avgRsrp;
				rectangles[i].count = data[i].count;
				
				rectangles[i].longitudeWest = data[i].longitudeWest;
				rectangles[i].longitudeEast = data[i].longitudeEast;
				rectangles[i].latitudeNorth = data[i].latitudeNorth;
				rectangles[i].latitudeSouth = data[i].latitudeSouth;
				
				
				var signalQuality = "很好";
				var sq = data[i].avgRsrq;
			    if(sq < -97 ){
				    signalQuality = "差";
				}else if( -97< sq < -60){
					signalQuality = "一般";
				}else if(-60< sq < -40){
					signalQuality = "好";
				}else{
					signalQuality = "很好";
				}
				
				
			    (function() {  
					rectangles[i].addEventListener("click",function show(){
						$("#u107_div").html(this.mos);
						$("#u109_div").html(this.uploadSpeed + "/" + this.downloadSpeed);
						$("#u111_div").html(signalQuality);
						$("#u113_div").html((this.rsrp).toFixed(2));
						$("#u115_div").html(this.count);
						
						sessionStorage.longitudeWest = this.longitudeWest;
						sessionStorage.longitudeEast = this.longitudeEast;
						sessionStorage.latitudeNorth = this.latitudeNorth;
						sessionStorage.latitudeSouth = this.latitudeSouth;
						getAddress();
					});
			    })();
				
		        
			    
			}
          			
        }

    });
}


function getDistrictInfo(district,point,zoom, flag){
	showCount(district);
	sessionStorage.district = district;
	
    map.clearOverlays();	
    var newzoom = zoom;
	var oldzoom = sessionStorage.zoom;
	var loc = sessionStorage.loc;
	
	if(loc == "city"){
		drawBoundaries(district,point,zoom);
		setTimeout(function(){			
			getHeatMapData();
		}, 2000);
		sessionStorage.zoom = zoom;
	}else if(newzoom == oldzoom){
		console.log('same');
		drawBoundaries(district,point,zoom);
        setTimeout(function(){
            getHeatMapData();
		}, 2000);
	}else{
		console.log('diff');
		map.centerAndZoom(point,zoom);
		sessionStorage.zoom = zoom;
	}
	
    sessionStorage.loc = "district";
}

//按钮触发画每个区的边界
function drawBoundaries(district,point,zoom) {
	sessionStorage.zoom = zoom;
    var bdary = new BMap.Boundary();
	map.centerAndZoom(point,zoom);
    bdary.get(district, function(rs){       //获取行政区域
	    //map.clearOverlays();        //清除地图覆盖物
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            ply.setFillOpacity(0.001);
            map.addOverlay(ply);  //添加覆盖物
            //map.setViewport(ply.getPath());    //调整视野
        }
    });
}	

// //缩放触发每个区的边界
// function drawBoundaries2(district) {
//     var bdary = new BMap.Boundary();
//     bdary.get(district, function(rs){       //获取行政区域
//         var count = rs.boundaries.length; //行政区域的点有多少个
//         for(var i = 0; i < count; i++){
//             var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
//             ply.setFillOpacity(0.001);
//             map.addOverlay(ply);  //添加覆盖物
//             map.centerAndZoom(map.getCenter(), map.getZoom());
//         }
//     });
// }


function  showCount(district) {
    $.ajax({  
        type:"POST",    
        // url:"http://www.ping4g.cn:28080/wilysis/1.0/mosCounts",
        url:"http://localhost:8080/wilysis/detectResult/mosCounts",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        // data:JSON.stringify({
        //     "operatorId":"02",
        //     "standard":"13",
        //     "startTime":"2016-12-01 00:00:00",
        //     "endTime":getEndTime(),
        //     "province":"北京市",
        //     "city":"北京市",
			// "district":district
        // }),
        data:JSON.stringify({
            "getData":"test"
        }),
        success:function (result) {
            $("#u71").html(result['data']['mosCount_4_5']);
			$("#u75").html(result['data']['mosCount_3_4']);
			$("#u77").html(result['data']['mosCount_2_3']);
			$("#u79").html(result['data']['mosCount_1_2']);
			$("#u81").html(result['data']['mosCount_0_1']);
        }
    });
}

function getAddress(){
    $.ajax({
        type:"POST",
        url:"http://www.ping4g.cn:28080/wilysis/1.0/metaSummaries",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"02",
            "standard":"13",
            "startTime":"2016-12-01 00:00:00",
            "endTime":getEndTime(),
            "province":"北京市",
            "city":"北京市",
            "district":sessionStorage.district,
			"longitudeWest":sessionStorage.longitudeWest,
			"longitudeEast":sessionStorage.longitudeEast,
			"latitudeNorth":sessionStorage.latitudeNorth,
			"latitudeSouth":sessionStorage.latitudeSouth,
			"location":"",

        }),
		
        success:function (data){
			sessionStorage.longtitude1 = data[0].longitude;
			sessionStorage.latitude1 = data[0].latitude;
		}           	

    });
}

//格式化日期
Date.prototype.Format = function(formatStr){   
	var str = formatStr;   
	str=str.replace(/yyyy|YYYY/,this.getFullYear());     
	str=str.replace(/MM|mm/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));     
	str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());   

	str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());   
	str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());   
	str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());   

	return str;   
}

//预设起止时间函数
function getEndTime(){
	var now = new Date();
	var endTime = now.Format("yyyy-mm-dd hh:mm:ss");
	return endTime;
} 

function getHeatMapData() {

    var points = [
        {"lng": 116.468192, "lat": 40.037236, "count": 50},
        {"lng": 116.478192, "lat": 39.916532, "count": 51},
        {"lng": 116.351192, "lat": 39.830658, "count": 15},
        {"lng": 116.371192, "lat": 39.820921, "count": 40},
        {"lng": 116.321192, "lat": 39.815516, "count": 100},
        {"lng": 116.491192, "lat": 39.818503, "count": 6},
        {"lng": 116.401192, "lat": 39.819989, "count": 18},
        {"lng": 116.411192, "lat": 39.815051, "count": 80},
        {"lng": 116.471192, "lat": 39.81782, "count": 11},
        {"lng": 116.411192, "lat": 39.717253, "count": 7},
        {"lng": 116.441192, "lat": 39.719426, "count": 42},
        {"lng": 116.501192, "lat": 39.716445, "count": 4},
        {"lng": 116.431192, "lat": 39.717943, "count": 27},
        {"lng": 116.491192, "lat": 39.720836, "count": 23},
        {"lng": 116.331192, "lat": 39.71463, "count": 60},
        {"lng": 116.341192, "lat": 39.724675, "count": 8},
        {"lng": 116.321192, "lat": 39.614509, "count": 15},
        {"lng": 116.481192, "lat": 39.621408, "count": 25},
        {"lng": 116.391192, "lat": 39.624396, "count": 21},
        {"lng": 116.321192, "lat": 39.62267, "count": 1},
        {"lng": 116.391192, "lat": 39.620034, "count": 51},
        {"lng": 116.481192, "lat": 39.62667, "count": 7},
        {"lng": 116.481192, "lat": 39.619114, "count": 11},
        {"lng": 116.391192, "lat": 39.621611, "count": 35},
        {"lng": 116.481192, "lat": 39.631037, "count": 22},
        {"lng": 116.381192, "lat": 39.631134, "count": 4},
        {"lng": 116.391192, "lat": 39.523254, "count": 5},
        {"lng": 116.381192, "lat": 39.52943, "count": 3},
        {"lng": 116.481192, "lat": 39.519621, "count": 100},
        {"lng": 116.391192, "lat": 39.519447, "count": 87},
        {"lng": 116.381192, "lat": 39.523091, "count": 32},
        {"lng": 116.397192, "lat": 39.521854, "count": 44},
        {"lng": 116.397192, "lat": 39.528227, "count": 21},
        {"lng": 116.397192, "lat": 39.522286, "count": 80},
        {"lng": 116.397192, "lat": 39.51948, "count": 32},
        {"lng": 116.397192, "lat": 39.520787, "count": 26},
        {"lng": 116.397192, "lat": 39.521197, "count": 17},
        {"lng": 116.397192, "lat": 39.522547, "count": 17},
        {"lng": 116.397192, "lat": 39.521938, "count": 25},
        {"lng": 116.397192, "lat": 39.515782, "count": 100},
        {"lng": 116.397192, "lat": 39.516759, "count": 39},
        {"lng": 116.397192, "lat": 39.529123, "count": 11},
        {"lng": 116.397192, "lat": 39.527518, "count": 9},
        {"lng": 116.481192, "lat": 39.515754, "count": 47},
        {"lng": 116.381192, "lat": 39.517061, "count": 52},
        {"lng": 116.481192, "lat": 39.515619, "count": 100},
        {"lng": 116.381192, "lat": 39.515958, "count": 46},
        {"lng": 116.481192, "lat": 39.531166, "count": 9},
        {"lng": 116.521192, "lat": 39.524055, "count": 8},
        {"lng": 116.438119, "lat": 39.521308, "count": 11},
        {"lng": 116.481192, "lat": 39.529376, "count": 3},
        {"lng": 116.481192, "lat": 39.820348, "count": 50},
        {"lng": 116.581192, "lat": 39.830511, "count": 15},
        {"lng": 116.381192, "lat": 39.818161, "count": 23},
        {"lng": 116.381192, "lat": 39.826306, "count": 3},
        {"lng": 116.481192, "lat": 39.72161, "count": 13},
        {"lng": 116.481192, "lat": 39.728616, "count": 6},
        {"lng": 116.481192, "lat": 39.715499, "count": 21},
        {"lng": 116.381192, "lat": 39.715738, "count": 29},
        {"lng": 116.481192, "lat": 39.716998, "count": 99},
        {"lng": 116.381192, "lat": 39.728001, "count": 10},
        {"lng": 116.481192, "lat": 39.730655, "count": 14},
        {"lng": 116.381192, "lat": 39.722995, "count": 16},
        {"lng": 116.481192, "lat": 39.731054, "count": 15},
        {"lng": 116.381192, "lat": 39.721895, "count": 13},
        {"lng": 116.431192, "lat": 39.713373, "count": 17},
        {"lng": 116.431192, "lat": 39.726572, "count": 1},
        {"lng": 116.331192, "lat": 39.717119, "count": 9},
        {"lng": 116.531192, "lat": 39.721337, "count": 54},
        {"lng": 116.331192, "lat": 39.721919, "count": 26},
        {"lng": 116.431192, "lat": 39.72536, "count": 17},
        {"lng": 116.431192, "lat": 39.614137, "count": 19},
        {"lng": 116.431192, "lat": 39.614394, "count": 43},
        {"lng": 116.431192, "lat": 39.622622, "count": 27},
        {"lng": 116.431192, "lat": 39.619467, "count": 8},
        {"lng": 116.431192, "lat": 39.617171, "count": 3},
        {"lng": 116.431192, "lat": 39.616659, "count": 34},
        {"lng": 116.431192, "lat": 39.615613, "count": 14},
        {"lng": 116.481192, "lat": 39.631416, "count": 12},
        {"lng": 116.481192, "lat": 39.625377, "count": 11},
        {"lng": 116.481192, "lat": 39.675017, "count": 38},
        {"lng": 116.481192, "lat": 39.670215, "count": 91},
        {"lng": 116.481192, "lat": 39.675908, "count": 54},
        {"lng": 116.481192, "lat": 39.679658, "count": 21},
        {"lng": 116.481192, "lat": 39.675015, "count": 15},
        {"lng": 116.481192, "lat": 39.673527, "count": 3},
        {"lng": 116.481192, "lat": 39.671854, "count": 24},
        {"lng": 116.482343, "lat": 39.679217, "count": 12},
        {"lng": 116.487957, "lat": 39.674987, "count": 57},
        {"lng": 116.482342, "lat": 39.875251, "count": 70},
        {"lng": 116.485464, "lat": 39.878989, "count": 8}];
    if(!isSupportCanvas()){
        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
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
    heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
    map.addOverlay(heatmapOverlay);
    heatmapOverlay.setDataSet({data:points,max:100});
    //是否显示热力图
    // function openHeatmap(){
        heatmapOverlay.show();
    // }
    // function closeHeatmap(){
    //     heatmapOverlay.hide();
    // }
    //closeHeatmap();
    function setGradient(){
        /*格式如下所示:
         {
         0:'rgb(102, 255, 0)',
         .5:'rgb(255, 170, 0)',
         1:'rgb(255, 0, 0)'
         }*/
        var gradient = {};
        var colors = document.querySelectorAll("input[type='color']");
        colors = [].slice.call(colors,0);
        colors.forEach(function(ele){
            gradient[ele.getAttribute("data-key")] = ele.value;
        });
        heatmapOverlay.setOptions({"gradient":gradient});
    }
    //判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
}
