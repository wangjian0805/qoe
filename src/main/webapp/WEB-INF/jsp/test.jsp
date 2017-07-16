<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="common/head.jsp"%>
    <title>众测</title>
    <script type="text/javascript">
        $(document).ready(function(){
            var mydata ='{"cpu":"' + 66666 +
                '","memoryConsumption":"' + 1111 +
                '","screenPixels":"' + 2222 +
                '","videoStreamBitrate":"' + 3333 +
                '","throughput":"' + 444 +
                '","initBuffer":"' + 555 +
                '","reservedBuffer":"' + 232 +
                '","videoLength":"' + 232 +
                '","messageDelay":"' + 232 +
                '","latitude":"' + 120.111111 +
                '","longitude":"' + 39.666666 +'"}';
            alert(mydata);
            $.ajaxSetup({
                contentType : 'application/json'
            });
            $.post('http://10.103.93.36:8080/wilysis/detectResult/postForInsert', mydata,
                function(data) {
                    alert("success: " + data['success'] + "\n"+
                        "data:" + data['data']['cpu']);
                }, 'json');
        });
    </script>
</head>
<body>
<!-- 页面显示部分 -->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>test页面</h2>
        </div>
    </div>
</div>

</body>
</html>
