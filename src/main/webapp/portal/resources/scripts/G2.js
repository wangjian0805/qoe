/**
 * Created by huangxin on 16/9/18.
 */
//数据
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



function aaa(){
    $.ajax({
        type:"POST",
        url:"http://www.ping4g.cn:28080/wilysis/1.0/webLatencies",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"01",
            "standard":"13",
            "startTime":"2016-12-01 00:00:00",
            "endTime":getEndTime(),
            "province":"北京市",
            "city":"北京市",
            "district":"海淀区",
            "longitudeWest":116.337004,
            "longitudeEast":116.389321,
            "latitudeNorth":39.982196,
            "latitudeSouth":39.948353,
            "location":"",
            "target":"operator"
        }),
        success:function (data) {
            var dataaa=new Array();
            var dict=new Array();
            var a=new Array();

            for(var i=0;i<data.operatorLatencies.length;i++){
                dict[data.submitAts[i]]=data.operatorLatencies[i];
            
            }

            for(var i in dict){
                a.push([i,dict[i]]);        
            }

            for(var i=0;i<a.length;i++){
                dataaa[i]={Time:a[i][0],delay:a[i][1]};
            }

            var frame1 = new G2.Frame(dataaa);
            // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
            frame1 = G2.Frame.combinColumns(frame1, ['delay'], 'value', 'type', 'Time');
            chart.changeData(frame1)
        }
    });
}


function bbb(){
    $.ajax({
        type:"POST",
        url:"http://www.ping4g.cn:28080/wilysis/1.0/webLatencies",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"01",
            "standard":"13",
            "startTime":"2016-12-01 00:00:00",
            "endTime":getEndTime(),
            "province":"北京市",
            "city":"北京市",
            "district":"海淀区",
            "longitudeWest":116.337004,
            "longitudeEast":116.389321,
            "latitudeNorth":39.982196,
            "latitudeSouth":39.948353,
            "location":"",
            "target":"operator"
        }),
        success:function (data) {
            var dataa=new Array();
            var dict=new Array();
            var a=new Array();
            
            for(var i=0;i<data.websiteLatencies["www.tencent.com"].length;i++){
                dict[data.submitAts[i]]=[data.websiteLatencies["www.tencent.com"][i],data.websiteLatencies["www.baidu.com"][i]];
            }
            for(var i in dict){
                a.push([i,dict[i]]);       
            }

            for(var i=0;i<a.length;i++){
                dataa[i]={Time:a[i][0],tencent:a[i][1][0],baidu:a[i][1][1]};
            }

            var frame2 = new G2.Frame(dataa);
            // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
            frame2 = G2.Frame.combinColumns(frame2, ['tencent', 'baidu'], 'value', 'type', 'Time');
            chart.changeData(frame2);
        }
    });
}

function ccc(){
    $.ajax({
        type:"POST",
        url:"http://www.ping4g.cn:28080/wilysis/1.0/videoExperiences",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify({
            "operatorId":"01",
            "standard":"13",
            "startTime":"2016-12-01 00:00:00",
            "endTime":getEndTime(),
            "province":"北京市",
            "city":"北京市",
            "district":"海淀区",
            "longitudeWest":116.337004,
            "longitudeEast":116.389321,
            "latitudeNorth":39.982196,
            "latitudeSouth":39.948353,
            "location":"",
            "target":"video"
        }),
        success:function (data) {
            var dataaaa=new Array();
            var dict=new Array();
            var a=new Array();


            for(var i=0;i<data.length;i++){
                var tpTotal=0;
                var tp=0;
                for(var j=0;j<data[i].throughputs.length;j++){
                    tpTotal=tpTotal+data[i].throughputs[j];
                }
                tp=tpTotal/data[i].throughputs.length;
                dict[data[i].submitAt]=[data[i].bufferTime,data[i].mos,tp];
            }
            for(var i in dict){
                a.push([i,dict[i]]);       
            }

            for(var i=0;i<a.length;i++){

                dataaaa[i]={Time:a[i][0],bufferTime:a[i][1][0],mos:a[i][1][1],throughput:a[i][1][2]};
            }


            var frame3 = new G2.Frame(dataaaa);
            // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
            frame3 = G2.Frame.combinColumns(frame3, ['bufferTime', 'mos','throughput'], 'value', 'type', 'Time');
            chart.changeData(frame3);

        }
    });
}


var data = [
    {Time: "2015-03-01 12:01:40", delay: 0}
];



$.ajax({
    type:"POST",
    url:"http://www.ping4g.cn:28080/wilysis/1.0/webLatencies",
    contentType:"application/json;charset=utf-8",
    dataType:"json",
    data:JSON.stringify({
        "operatorId":"01",
        "standard":"13",
        "startTime":"2016-12-01 00:00:00",
        "endTime":getEndTime(),
        "province":"北京市",
        "city":"北京市",
        "district":"海淀区",
        "longitudeWest":116.337004,
        "longitudeEast":116.389321,
        "latitudeNorth":39.982196,
        "latitudeSouth":39.948353,
        "location":"",
        "target":"operator"
    }),
    success:function (data) {
        var dataaa=new Array();
        var dict=new Array();
        var a=new Array();

        for(var i=0;i<data.operatorLatencies.length;i++){
            dict[data.submitAts[i]]=data.operatorLatencies[i];
            
        }

        for(var i in dict){
            a.push([i,dict[i]]);        
        }

        for(var i=0;i<a.length;i++){
            dataaa[i]={Time:a[i][0],delay:a[i][1]};
        }

        var frame1 = new G2.Frame(dataaa);
            // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
        frame1 = G2.Frame.combinColumns(frame1, ['delay'], 'value', 'type', 'Time');
        chart.changeData(frame1)
    }
});




// 图表内部做的转换
var frame = new G2.Frame(data);
// 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
frame = G2.Frame.combinColumns(frame, ['delay'], 'value', 'type', 'Time');
// Step 1: 创建 Chart 对象
var chart = new G2.Chart({
    id: 'u524',      // 指定图表容器 ID
    width: 713,   // 指定图表宽度
    height: 389,    // 指定图表高度
    plotCfg: {
        border: {
            stroke: 'white',
            lineWidth: 3, // 设置线的宽度
            radius: 10, // 设置圆角
            fill: 'white'
        }, // 设置边框
        background: {
            fill: 'white'
        } // 设置背景色
    }
});
var defs = {
    value: {
        //alias: '可以修改',
        //formatter: function(val) {return val;}
    },
    Time: {
        type: 'time',
        nice: true,
        mask: 'yy-mm-dd HH:mm:ss'
    }
};
// 去除 X 轴标题
chart.axis('Time', {
    title: null
});

// 去除 Y 轴标题
chart.axis('value', {
    title: null
});

chart.source(frame, defs);
//辅助线
chart.tooltip({
    crosshairs: true
});
// Step 3：创建图形语法，绘制图
chart.area().position('Time*value').color('type').shape('smooth');
chart.line().position('Time*value').color('type').size(2).shape('smooth');
// Step 4: 渲染图表
chart.render();


function a() {
//     var frame1 = new G2.Frame(data);
// // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
//     frame1 = G2.Frame.combinColumns(frame1, ['delay'], 'value', 'type', 'Time');
//     chart.changeData(frame1);
    aaa();
}
function b() {
//     var frame2 = new G2.Frame(data2);
// // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
//     frame2 = G2.Frame.combinColumns(frame2, ['baiduDelay', 'wangyiDelay','xinlangDelay'], 'value', 'type', 'Time');
//     chart.changeData(frame2);
    bbb();
}

function c() {
//     var frame3 = new G2.Frame(data3);
// // 合并的列，合并新列的名称，存储原先字段的名字作为分类，除了合并的列之外保留的列
//     frame3 = G2.Frame.combinColumns(frame3, ['bufferTime', 'throughtout','MOS'], 'value', 'type', 'Time');
//     chart.changeData(frame3);
    ccc();
}

