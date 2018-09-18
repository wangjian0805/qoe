//引导
//文档加载完毕时执行
$(function() {
	var $demo = $("#demo");
	var tour = new Tour({
	  onStart: function() {
	  	console.log("onStart");
	  	return $demo.addClass("disabled", true);
	  },
	  onEnd: function() {
	  	console.log("onEnd");
		 return $demo.removeClass("disabled", true);
		},
	  steps: [
		  {
			element: "#indexpage",
			title: "首页",
			content: "这里是系统的首页，包括热图。热图信息和热图设置",
			path:"/index.html"
		  },
		  {
			element: "#baidumap",
			title: "热图",
			content: "热图的中心是北邮校园，展示了校园网MoS值的分布情况",
			path:"/index.html"
		  },
		  {
			element: "#tuli",
			title: "热图图例",
			content: "热图点的颜色代表不同的MoS值",
			path:"/index.html"
		  },
		  {
			element: "#mapinfo",
			title: "热图信息",
			content: "这里将显示所展示热点的总体信息",
			path:"/index.html",
			placement:"left"
		  },
		  {
			element: "#mapoperator",
			title: "热图设置",
			content: "这里可以设置热图的过滤条件和展示样式",
			path:"/index.html",
			placement:"left"
		  },
		  {
			element: "#dataoverview",
			title: "数据总览",
			content: "这里将展示平台所收集数据的详细信息",
			path:"/page-class1.html",
			reflex: true
		  },
		  {
			element: "#overview",
			title: "数据总览",
			content: "平台数据的总体情况",
			path:"/page-class1.html",
			placement:"bottom"
		  },
		  {
			element: "#mosSub",
			title: "主观MoS值分布",
			content: "这里是用户主观评分的分布情况",
			path:"/page-class1.html"
		  },
		  {
			element: "#mosObj",
			title: "客观MoS值分布",
			content: "这里是QoE预测算法评估的MoS值分布",
			path:"/page-class1.html",
			placement:"left"
		  },
		  {
			element: "#moscomparebybuilding",
			title: "不同区域的MoS对比",
			content: "这里是校园内不同区域内MoS值平均值和方差的对比",
			path:"/page-class1.html",
			placement:"bottom"
		  },
		  {
			element: "#moscompareinbuilding",
			title: "某区域主客观MoS值对比",
			content: "这里是一个特定区域内用户主观评测和算法预测的MoS对比图",
			path:"/page-class1.html",
			placement:"top"
		  },
		  {
			title: "引导教程结束",
			content: "祝您使用愉快",
			path:"/index.html",
			orphan:true
		  }
		],
	  debug: true,
	  backdrop: true,
	  backdropContainer: 'body',
	  backdropPadding: 0,
	  basePath: "/qoe/newportal",
	  template:  '<div class="popover" role="tooltip"> <div class="arrow"></div> '+
		 	'<h3 class="popover-title"></h3> '+
		 	'<div class="popover-content"></div>'+
		 	'<div class="popover-navigation"> '+
		 	'<div class="btn-group"> '+
		 	'<button class="btn btn-sm btn-default" data-role="prev">&laquo; Prev</button> '+
		 	'<button class="btn btn-sm btn-default" data-role="next">Next &raquo;</button> '+
		 	'</div> '+
		 	'<button class="btn btn-sm btn-default" data-role="end">End tour</button> '+
		 	'</div> </div>'
	  });
	// Initialize the tour
	console.log(" Initialize the tour");
	tour.init();
	// Start the tour
	
	$(document).on("click", "[data-demo]", function(e) {
      e.preventDefault();
      if ($(this).hasClass("disabled")) {
      	console.log("this :"+ this);
        return;
      }
      tour.restart();
    });
    
});