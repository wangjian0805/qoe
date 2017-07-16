var map = new BMap.Map("u532");          // 创建地图实例

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

map.enableScrollWheelZoom();
map.enableInertialDragging();
map.enableScrollWheelZoom();
map.enableAutoResize();
map.addControl(top_right_navigation);

var pt = new BMap.Point(sessionStorage.longtitude1,sessionStorage.latitude1);
var marker = new BMap.Marker(pt);
map.clearOverlays();
map.addOverlay(marker); 
map.centerAndZoom(new BMap.Point(sessionStorage.longtitude1, sessionStorage.latitude1), 15);

/* var loc = sessionStorage.loc;
var rectangles = new Array();
var this_district = sessionStorage.district; */

/* map.addEventListener("zoomend", function () {
	if(loc === "district"){
        for(var i = 0; i < rectangles.length; i++){
			map.removeOverlay(rectangles[i]);
		}
		drawRect(this_district);
	}	
});

function getBoundaryInfo() {
	drawBoundaries(this_district);
	drawRect(this_district);
    $.ajax({  
        type:"POST",    
        url:"http://www.ping4g.cn:28080/wilysis/1.0/summaries",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"03",
            "standard":"13",
            "startTime":"2016-09-09 00:00:00",
            "endTime":"2017-10-20 23:59:59",
            "province":"北京市",
            "city":"北京市"
        }),
        success:function (data) {
            
        }
    });
}

setTimeout(function(){
    getBoundaryInfo();
}, 1000);


function getMapScale() {
    var scales = [20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 25000, 50000, 100000, 200000, 500000, 1000000, 2000000];
    var zoom = map.getZoom();
    var scale = scales[19 - zoom];
    return scale;
}

function drawRect(district){
	$.ajax({
        type:"POST",
        url:"http://www.ping4g.cn:28080/wilysis/1.0/metaSummaries",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"03",
            "standard":"13",
            "startTime":"2016-10-09 00:00:00",
            "endTime":"2017-10-27 23:59:59",
            "province":"北京市",
            "city":"北京市",
            "district":sessionStorage.district,
			"longitudeWest":sessionStorage.longitudeWest,
			"longitudeEast":sessionStorage.longitudeEast,
			"latitudeNorth":sessionStorage.latitudeNorth,
			"latitudeSouth":sessionStorage.latitudeSouth,
			"location":"",
        }),
		
        success:function (data) {
			var LENGTH = data.length;
			var mosTotal = 0;
			for(i=0;i<LENGTH;i++){
				mosTotal = mosTotal+data[i].mos;
			}
			
			var pStart = new BMap.Point(sessionStorage.longitudeWest, sessionStorage.latitudeNorth);
			var pEnd = new BMap.Point(sessionStorage.longitudeEast, sessionStorage.latitudeSouth);
			rectangles[i] = new BMap.Polygon([
				new BMap.Point(pStart.lng,pStart.lat),
				new BMap.Point(pEnd.lng,pStart.lat),
				new BMap.Point(pEnd.lng,pEnd.lat),
				new BMap.Point(pStart.lng,pEnd.lat)
			], {strokeColor:"black", strokeWeight:1, strokeOpacity:1}); 
				
			var color = "#ff0000";
			var mos = mosTotal/LENGTH;
			    if(mos < 1 ){
			    color = "#00ffff"
			    }else if( mos < 2){
			    color = "#0000ff";
				}else if(mos < 3){
					color = "#ccff33";
				}else if(mos < 4){
					color = "#9900ff";
				}else{
					color = "#ff0000";
				}
			
			rectangles[i].setFillColor(color);
			//map.addOverlay(rectangles[i]);	
           	
        }

    });
}

//画每个区的边界
function drawBoundaries(district) {
    var bdary = new BMap.Boundary();
    bdary.get(district, function(rs){       //获取行政区域
        map.clearOverlays();        //清除地图覆盖物
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
            map.addOverlay(ply);  //添加覆盖物
            map.setViewport(ply.getPath());    //调整视野
        }
    });
} */

function reLocate(longtitude,latitude){
	if(longtitude!=undefined){
		var pt = new BMap.Point(longtitude,latitude);
		var marker = new BMap.Marker(pt);
		map.clearOverlays();
		/* drawRect(this_district); */
		map.addOverlay(marker); 
		map.centerAndZoom(new BMap.Point(longtitude, latitude), 15);
	}
}














