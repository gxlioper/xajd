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
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/sjwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//初始化查询
				var gridSetting = {
						caption:"酬金发放息列表",
						pager:"pager",
						url:"qgzx_jfcjgl_cjff.do?method=gjcxCjff&type=query",
						params:getSuperSearch(),
						colList:[
						   {label:'唯一编号',name:'wbh', index: 'wbh',width:'20%',key:true,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
						   {label:'用人单位',name:'yrdw', index: 'yrdw',width:'15%'},
						   {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'20%'},
						   {label:'在岗状态',name:'zgzt', index: 'zgzt',width:'10%',hidden:true},
						   {label:'发放年月',name:'ffsj', index: 'ffsj',width:'12%'},
						   {label:'工时',name:'gs', index: 'gs',width:'6%'},
						   {label:'工资（元）',name:'je', index: 'je',width:'12%'},
                           {label:'单位工资上限',name:'dwgzsx', index: 'dwgzsx',width:'14%'},
						   {label:'sjbs',name:'sjbs', index: 'sjbs',width:'13%',hidden:true}
						  
						],
						sortname: "ffsj,yrdw,xh",
					 	sortorder: "desc"
					}
				jQuery("#dataTable").initGrid(gridSetting);
				//为button注册事件
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				//jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
			
			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>
							<li><a href="#" onclick="toImportDataNew('IMPORT_N030101_QGZXCJFF');return false;" class="btn_dr">导入</a></li>
						</logic:equal>
							<li><a href="#" class="btn_dc" onclick="qgjgwhExportConfig();return false;">导出</a></li>
						<logic:equal name="xxdm" value="10022">
							<li><a href="#" onclick="cjffcxExportData_10022();return false;" class="btn_down">下载申报表</a></li>
						</logic:equal>
							<!-- <li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>	 -->			
					</ul>
				</div>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 酬金发放列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
