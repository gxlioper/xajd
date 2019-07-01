<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"wsjcWsflr.do?method=getFslrList",
				radioselect:true,
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'jcdx',name:'jcdx', index: 'jcdx',hidden:true},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xq'},
				   {label:'名称',name:'rcmc', index: 'rcmc'},
				   {label:'开始时间',name:'kssj', index: 'kssj'},
				   {label:'结束时间',name:'jssj', index: 'jssj'},
<%--				   {label:'已评数',name:'lrs', index: 'lrs'},--%>
<%--				   {label:'未评数',name:'wlrs', index: 'wlrs'},--%>
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

			function selectTab(obj,jcdx){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				//var map = getSuperSearch();
				var map = {};
				map["jcdx"] = jcdx;
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function wsflr(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要录入的检查日程！");
				} else {
					if (rows[0]["tjzt"] == "1"){
						showAlertDivLayer("该检查日程已提交，不能录入！");
						return false;
					}
					document.location.href = "wsjcWsflr.do?method=wsflr&rcid="+rows[0]["id"]+"&jcdx="+rows[0]["jcdx"];
				}
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
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
						<li><a href="javascript:void(0);" onclick="wsflr()" class="btn_xg">录入</a></li>
					</ul>
				</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>寝室</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>床位</span></a></li>
		      </ul>
			</div>
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>查询结果列表</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
