/* var detailInfo = new Array(); */
var longtitudeArray = new Array();
var latitudeArray = new Array();

$(function drawTable(){
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
		
        success:function (data) {
/* 			sessionStorage.longtitude1 = data[0].longitude;
			sessionStorage.latitude1 = data[0].latitude; */
				
			for(var i=1;i<data.length+1;i++){				
				$(("#"+i+"_cor")).html("("+(data[i-1].longitude.toFixed(4)+","+data[i-1].latitude.toFixed(4))+")");
				$(("#"+i+"_mos")).html(data[i-1].mos);
				$(("#"+i+"_up")).html((data[i-1].uploadSpeed/1000).toFixed(2)+" kb/s");
				$(("#"+i+"_down")).html((data[i-1].downloadSpeed/1000).toFixed(2)+" kb/s");
				$(("#"+i+"_signalPower")).html(data[i-1].rsrp+" dbm");
				$(("#"+i+"_signalQuality")).html(data[i-1].rsrq+" db");
				longtitudeArray.push(data[i-1].longitude);
				latitudeArray.push(data[i-1].latitude);
/* 				if(i==1){
					sessionStorage.longtitude1 = data[i-1].longitude;
					sessionStorage.latitude1 = data[i-1].latitude;
				}*/
			}
			
			
           	
        }

    });
});

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

