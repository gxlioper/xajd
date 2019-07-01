<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/js/jcrc.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"wsjcJcrc.do?method=getJcrcList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xq'},
				   {label:'名称',name:'rcmc', index: 'rcmc'},
				   {label:'类型',name:'fslx', index: 'fslx',formatter:function(v,r){
					   if (v == "0"){
						   return "分数";
					   } else if (v == "1"){
						   return "等级";
					   } else {
						   return "星级";
					   }
				   }},
				   {label:'开始时间',name:'kssj', index: 'kssj'},
				   {label:'结束时间',name:'jssj', index: 'jssj'},
				   {label:'提交状态',name:'tjzt', index: 'tjz',formatter:function(v,r){
					   return v == "1" ? "已提交" : "未提交";
				   }}
				],
				sortname: "kssj",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/wsjcJcxm" method="post" styleId="form">
	
	
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="jcrcSubmit();" class="btn_shtg">提交</a></li>						
					<li><a href="javascript:void(0);" onclick="jcrcCancel();" class="btn_shbtg">取消提交</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 检查日程列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
