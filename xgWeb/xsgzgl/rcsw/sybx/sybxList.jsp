<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sybx/js/sybxList.js"></script>
			<script type="text/javascript">
			var gridSetting = {
					caption:"商业保险结果列表",
					pager:"pager",
					url:"rcsw_sybx.do?method=getSybxList&type=query",
					colList:[
				       {label:'guid',name:'guid', index: 'guid',width:'5%',key:true,hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
					   {label:'性别',name:'xb', index: 'xb',width:'8%'},
					   {label:'年级',name:'nj', index: 'nj',width:'7%',hidden:true},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'10%',hidden:true},
					   {label:'专业',name:'zymc', index: 'zydm',width:'10%',hidden:true},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
					   {label:'学年',name:'xn', index: 'xn',width:'10%'},
					   {label:'监护人',name:'jhrxm', index: 'jhrxm',width:'20%'},
					   {label:'保险金额',name:'bxje', index: 'bxje',width:'10%'}
					 
					],
					sortname: "xn,nj,xydm,zydm,bjdm,xh",
				 	sortorder: "desc"
				}
				jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);

					var btndr=jQuery("#btn_dr");
					//导入
					if(btndr!=null){
						btndr.click(function () {
							//调用通用的导入function，参数是导入功能模块代码。
							toImportData("IMPORT_N154801_SYBX");
							return false;
						});
					}
				});
			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_sybx">
			<input type="hidden" name="tableName" id="tableName" value="view_xg_rcsw_sybxxxb"/>
			<input type="hidden" name="realTable" id="realTable" value="xg_rcsw_sybxxxb"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="addSybx();return false;" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="updateSybx();return false;" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="delSybx();return false;" class="btn_sc">删除</a></li>
						<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>
						</logic:equal>					
						<li><a href="#" class="btn_dc" onclick="syylbxwhExportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					--%></ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 商业保险结果列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>