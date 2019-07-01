<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/jtqkdc/js/jtqkdc.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"家庭情况信息列表",
				pager:"pager",
				url:"xszz_jtqkdc.do?method=dcxxManage&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink,key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
                    {label:'书院',name:'symc', index: 'symc',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'15%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
				   {label:'调查时间',name:'dcsj', index: 'dcsj',width:'12%'}
				],
				sortname: "dcsj",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				
<%--				jQuery.ajaxSetup({async:false,cache:true});--%>
<%--					var initSetting = getQuerySetting("XSZZ_JTQKDC");--%>
<%--					gridSetting = jQuery.extend(gridSetting,initSetting || {});--%>
<%--				jQuery.ajaxSetup({async:true});--%>
				
				jQuery("#dataTable").initGrid(gridSetting);
			});

			
			function getWord(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择一条记录！");
				 } else if (rows.length > 1){
					var url="xszz_jtqkdc.do?method=getJtqkdcZip";
					var ids = jQuery("#dataTable").getSeletIds();
					var url= url+"&value="+ids;
					window.open(url);
				 } else {
					var url="xszz_jtqkdc.do?method=getJtqkdcWord";
					
					var url= url+"&xh="+rows[0]["xh"];
					window.open(url);
			     }
			}
			// 打印发展规划与资助申请表（温州大学）
			function getFzghyzzsqbWord(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择一条记录！");
				 } else if (rows.length > 1){
					var url="xszz_jtqkdc.do?method=getFzghyzzsqbZip";
					var ids = jQuery("#dataTable").getSeletIds();
					var url= url+"&value="+ids;
					window.open(url);
				 } else {
					var url="xszz_jtqkdc.do?method=getFzghyzzsqbWord";
					
					var url= url+"&xh="+rows[0]["xh"];
					window.open(url);
			     }
			}
			
			
			function jtknmd(){
				showDialog("经济困难标准", 500, 700, "xszz_jtqkdc.do?method=jtknmd");
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_jtqkdcb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="showDialog('家庭情况调查',945,500,'xszz_jtqkdc.do?method=dcxxModify');return false;" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="jtqkUpdate();return false;" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="jtqkDelete();return false;" class="btn_sc">删除</a></li>	
						</logic:equal>						
<%--						<li><a href="javascript:void(0);" onclick="printJtqkdc();return false;" class="btn_dy">打印报表</a></li>						--%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载家庭情况调查表</a></li>	
						</logic:equal>
						<logic:notEqual name="xxdm" value="10264">
							<logic:notEqual value="13431" name="xxdm">
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>	
							</logic:notEqual>
						</logic:notEqual>					
						<logic:equal name="xxdm" value="10351">			
						<!-- 打印发展规划与资助申请表（温州大学） begin -->
							<li><a href="javascript:void(0);" onclick="getFzghyzzsqbWord();return false;" class="btn_down">下载调查表</a></li>						
						<!-- 打印发展规划与资助申请表（温州大学） end -->			
						</logic:equal>
						
						<logic:equal value="10335" name="xxdm">
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
								</li>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							</li>
						</logic:notEqual>
					</ul>
				</div>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生家庭情况调查信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
