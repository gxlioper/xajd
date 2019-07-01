<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				var gridSetting = {
						caption:"学期考核列表",
						pager:"pager",
						params:getSuperSearch(),
						url:"rcsw_rcxwwh_rcxwxqkhgl.do?method=rcxwxqkhManage&type=query",
						colList:[
						   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
						   {label:'学年',name:'yfxn', index: 'yfxn',width:'10%'},
						   {label:'学期',name:'yfxqmc', index: 'yfxqmc',width:'5%'},
						   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'24%'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'25%'},
						   {label:'学期等级',name:'xqdjmc', index: 'xqdjmc',width:'9%'},
						   {label:'yfxq',name:'yfxq', index: 'yfxq' ,hidden:true}
						],
						sortname: "yfxn,yfxqmc,xh",
					 	sortorder: "asc"
					}
				jQuery("#dataTable").initGrid(gridSetting);
	
			});
			function searchRs() {
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xnLength == 0){
					showAlertDivLayer("请选择至少一个学年！");
					return false;
				}
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function zxsxxView(xh){
				showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
			}

			var DCCLBH = "rcsw_rcxwwh_rcxwxqkh.do";// dcclbh,导出功能编号
	
			// 自定义导出 功能
			function exportConfig() {
				// DCCLBH导出功能编号,执行导出函数
				customExport(DCCLBH, rcxwxqkhExportData);
			}
	
			// 导出方法
			function rcxwxqkhExportData() {
				setSearchTj();// 设置高级查询条件
				var url = "rcsw_rcxwwh_rcxwxqkhgl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
				url = addSuperSearchParams(url);// 设置高级查询参数
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
		<html:form action="/rcsw_rcxwwh_rcxwxqkhgl">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
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
				<span>学期考核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
