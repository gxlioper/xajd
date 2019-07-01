<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"学生列表",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmjggl.do?method=kycxxmjgManageCx&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						  {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
						  {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						  {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'科研项目名称',name:'xmmc', index: 'xmmc',width:'27%',formatter:xmmcLink},
						   {label:'科研类别',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'申请时间',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'指导老师',name:'zdlsxm', index: 'zdlsxm',width:'10%'}
						],
						sortname: "xmsqsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function zxsxxView(xh) {
				showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh + "&xs");
			}
			function kycxxmjgView(jgid) {
				showDialog("查看科研项目", 750,415, "kycxgl_kycxxm_kycxxmjggl.do?method=viewKycxxmjg&jgid=" + jgid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='kycxxmjgView(\"" + rowObject["jgid"] + "\");'>" + cellValue + "</a>";
			}
			var DCCLBH = "kycxgl_kycxxm_kycxxmjgglCx";//dcclbh,导出功能编号
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// 导出方法
			function kycxxmjgExportData() {
				setSearchTj();//设置高级查询条件
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=exportDataCx&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/kycxgl_kycxxm_kycxxmjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
