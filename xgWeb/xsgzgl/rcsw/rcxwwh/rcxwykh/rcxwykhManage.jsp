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
						caption:"月考核列表",
						pager:"pager",
						params:getSuperSearch(),
						url:"rcsw_rcxwwh_rcxwykhgl.do?method=rcxwykhManage&type=query",
						colList:[
						   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
						   {label:'年度',name:'nd', index: 'nd',width:'6%'},
						   {label:'月份',name:'yfmc', index: 'yf',width:'5%'},
						   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'18%'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'19%'},
						   {label:'总分',name:'fzsum', index: 'fzsum',width:'7%',formatter:fzsumLink},
						   {label:'月等级',name:'ydjmc', index: 'ydjmc',width:'7%'},
						   {label:'月处分',name:'ycfmc', index: 'ycfmc',width:'12%'}
						],
						sortname: "nd,yf,xh",
					 	sortorder: "asc"
					}
				jQuery("#dataTable").initGrid(gridSetting);
	
			});
			function searchRs() {
				var ndLength=jQuery("a[name=a_name_nd]").length;
				if(ndLength == 0){
					showAlertDivLayer("请选择至少一个年度！");
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

			function fzsumLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewRcxwykh(\""+rowObject["pk"]+"\",\""+rowObject["xm"]+"\",\""+rowObject["nd"]+"\",\""+rowObject["yfmc"]+"\");'>" + cellValue + "</a>";
			}
			function viewRcxwykh(pk,xm,nd,yfmc) {
				jQuery("#xm_parent_hid").val(xm);
				jQuery("#nd_parent_hid").val(nd);
				jQuery("#yfmc_parent_hid").val(yfmc);
				showDialog("月考核信息", 800, 520, "rcsw_rcxwwh_rcxwykhgl.do?method=viewRcxwykh&id=" + pk);
			}
	
			var DCCLBH = "rcsw_rcxwwh_rcxwykh.do";// dcclbh,导出功能编号
	
			// 自定义导出 功能
			function exportConfig() {
				// DCCLBH导出功能编号,执行导出函数
				customExport(DCCLBH, rcxwykhExportData);
			}
	
			// 导出方法
			function rcxwykhExportData() {
				setSearchTj();// 设置高级查询条件
				var url = "rcsw_rcxwwh_rcxwykhgl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,导出功能编号
				url = addSuperSearchParams(url);// 设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
	
			// 太原旅游职业学院导出
			function rcxwykhDc_13696() {
				var url ="rcsw_rcxwwh_rcxwykhgl.do?method=rcxwykhDc_13696";
				var ndLength=jQuery("a[name=a_name_nd]").length;
				if(ndLength != "1"){
					showAlertDivLayer("请选择一个年度！");
					return false;
				}
				var yfLength=jQuery("a[name=a_name_yf]").length;
				if(yfLength != "1"){
					showAlertDivLayer("请选择一个月份！");
					return false;
				}
				var bjLength=jQuery("a[name=a_name_bj]").length;
				if(bjLength != "1"){
					showAlertDivLayer("请选择一个班级！");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwykhgl">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="xm_parent_hid" value=""/>
			<input type="hidden" id="nd_parent_hid" value=""/>
			<input type="hidden" id="yfmc_parent_hid" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							<logic:equal value="13696" name="xxdm">
								<li><a href="#" class="btn_dc" onclick="rcxwykhDc_13696();return false;">月考核表导出</a></li>
							</logic:equal>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>月考核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
