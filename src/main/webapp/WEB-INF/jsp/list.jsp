<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="common/head.jsp"%>
<title>秒杀列表页</title>
</head>
<body>
	<!-- 页面显示部分 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>手机端基于wifi获取数据列表</h2>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>测试ID</th>
							<th>cpu主频</th>
							<th>内存消耗量</th>
							<th>屏幕像素密度</th>
							<th>视频流平均比特率</th>
							<th>吞吐量</th>
							<th>初始化缓冲区</th>
							<th>预留缓冲区</th>
							<th>视频长度</th>
							<th>消息时延</th>
							<th>纬度</th>
							<th>经度</th>
							<th>测试时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dr" items="${list}">
							<tr>
								<td>${dr.resultId}</td>
								<td>${dr.cpu}</td>
								<td>${dr.memoryConsumption}</td>
								<td>${dr.screenPixels}</td>
								<td>${dr.videoStreamBitrate}</td>
								<td>${dr.throughput}</td>
								<td>${dr.initBuffer}</td>
								<td>${dr.reservedBuffer}</td>
								<td>${dr.videoLength}</td>
								<td>${dr.messageDelay}</td>
								<td>${dr.latitude}</td>
								<td>${dr.longitude}</td>
								<td>
									<fmt:formatDate value="${dr.detectTime}" pattern="yyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>