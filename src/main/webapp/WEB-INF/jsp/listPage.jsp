<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="common/head.jsp"%>
    <title>众测</title>
    <script type="application/javascript">
        $(document).ready(function() {
            loadDetectResultPage(1, 5);
        });

        function loadDetectResultPage(currentPage, limit) {
            //先销毁表格
            $('#table').bootstrapTable('destroy');

            //var queryTableName=$('#keyword').val();

            $("#table").bootstrapTable({
                method: "post",  //使用get请求到服务器获取数据
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                url: '<%=request.getContextPath()%>/detectResult/listPage', //获取数据的Servlet地址
                striped: true,  //表格显示条纹
                pagination: true, //启动分页
                pageSize: limit,  //每页显示的记录数
                pageNumber:currentPage, //当前第几页
                pageList: [5, 10, 15, 20, 25],  //记录数可选列表
                search: false,  //是否启用查询
                sidePagination: "server", //表示服务端请求
                clickToSelect: true,
                undefinedText:'',
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType : "undefined",
                queryParams: function queryParams(params) {   //设置查询参数
                    var param = {
                        pageSize: params.pageNumber,
                        pageNumber: params.pageSize
                    };
                    return param;
                },
                onLoadSuccess: function(data){  //加载成功时执行
                },
                onLoadError: function(){  //加载失败时执行
                    bootbox.alert("加载数据失败");
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'resultId',
                    title: '测试ID',
                    valign: 'middle',
                    align: 'center',
                    order:'asc'
                }, {
                    field: 'cpu',
                    valign: 'middle',
                    align: 'center',
                    title: 'cpu主频'
                }, {
                    field: 'memoryConsumption',
                    class:"hidden",
                    title: '内存消耗量'
                },{
                    field: 'screenPixels',
                    valign: 'middle',
                    align: 'center',
                    title: '屏幕像素密度',
                },{
                    field: 'videoStreamBitrate',
                    valign: 'middle',
                    align: 'center',
                    title: '视频流平均比特率',
                },{
                    field: 'throughput',
                    valign: 'middle',
                    align: 'center',
                    title: '吞吐量',
                },{
                    field: 'initBuffer',
                    valign: 'middle',
                    align: 'center',
                    title: '初始化缓冲区',
                },{
                    field: 'reservedBuffer',
                    valign: 'middle',
                    align: 'center',
                    title: '预留缓冲区',
                },{
                    field: 'videoLength',
                    valign: 'middle',
                    align: 'center',
                    title: '视频长度',
                },{
                    field: 'messageDelay',
                    valign: 'middle',
                    align: 'center',
                    title: '消息时延',
                },{
                    field: 'latitude',
                    valign: 'middle',
                    align: 'center',
                    title: '纬度',
                },{
                    field: 'longitude',
                    valign: 'middle',
                    align: 'center',
                    title: '经度',
                },{
                    field: 'detectTime',
                    valign: 'middle',
                    align: 'center',
                    title: '测试时间',
                    formatter: function (value, row, index) {
                        if(value!=null){
                            return new Date(parseInt(value)).Format('yyyy-MM-dd HH:mm:ss');
                        }
                        return "无";
                    }
                }],
                responseHandler : function(res) {
                    $('#currentPage').val(currentPage);
                    $('#limit').val(limit);
                    if(res.total > 0) {
                        return {
                            "rows": res.rows,
                            "total": res.total
                        }
                    } else {
                        return {
                            "rows": [],
                            "total": 0
                        }
                    }
                },
            });
        }

    </script>
</head>
<body>
<!-- 页面显示部分 -->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>手机端基于wifi获取数据列表</h2>
        </div>
        <div class="panel-body">
            <div id="toolbar1" class="btn-group">
                    <button id="btn_add" type="button" class="btn btn-default"
                            onclick="">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                    </button>
                    <button id="btn_edit" type="button" class="btn btn-default"
                            onclick="">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                    </button>
                    <button id="btn_delete" type="button" class="btn btn-default"
                            onclick="">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                    </button>
                    <button id="btn_import" type="button" class="btn btn-default"
                            onClick=''>
                        <span class="glyphicon glyphicon-open" aria-hidden="true"></span>导入
                    </button>

                    <button id="btn_export" type="button" class="btn btn-default"
                            onclick=''>
                        <span class="glyphicon glyphicon-save" aria-hidden="true"></span>导出
                    </button>
            </div>
            <table 	data-show-refresh="false"
                      data-show-toggle="false"
                      data-show-columns="false"
                      data-switchable="true"
                      data-select-item-name="subBox"
                      data-pagination="true"
                      data-sort-order="desc"
                      id="table"
                      class="table table-hover">
            </table>
        </div>

    </div>
</div>


</body>
</html>