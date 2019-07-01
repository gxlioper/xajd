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
						caption:"就业创业教育与指导信息列表",
						pager:"pager",
						url:"jyglnew_jygl_cyjyyzdgl.do?method=cyjyyzdManage&type=query",
						colList:[
							{label:'key',name:'jyid', index: 'jyid',key:true ,hidden:true},
						 	{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xb', index: 'xb',width:'6%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'14%'},
						   {label:'公司名称',name:'gsmc', index: 'gsmc',width:'19%'},
						   {label:'公司类型',name:'gslxmc', index: 'gslxmc',width:'10%'},
						   {label:'注册资本',name:'zczb', index: 'zczb',width:'10%'},
						   {label:'所属行业',name:'sshymc', index: 'sshymc',width:'17%'},
						   {label:'公司类型dm',name:'gslx', index: 'gslx',hidden:true},
						   {label:'所属行业dm',name:'sshy', index: 'sshy',hidden:true}
						],
						sortname: "xh",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhView(jyid, xh) {
				showDialog("查看就业创业教育与指导信息", 750,365, "jyglnew_jygl_cyjyyzdgl.do?method=viewCyjyyzd&jyid=" + jyid+"&xh=" + xh);
			}
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\"" + rowObject["jyid"] + "\",\"" + rowObject["xh"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "jyglnew_jygl_cyjyyzdgl.do?method=addCyjyyzd";
				var title = "增加就业创业教育与指导信息";
				showDialog(title,750,365,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'jyglnew_jygl_cyjyyzdgl.do?method=updateCyjyyzd&jyid=' + rows[0]["jyid"] + '&xh=' + rows[0]["xh"];
					var title = "修改就业创业教育与指导信息";
					showDialog(title,750,365,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("jyglnew_jygl_cyjyyzdgl.do?method=delCyjyyzd", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			//自定义导出 功能
			function exportConfig() {
				var exportBh = "jyglnew_jygl_cyjyyzd.do";
				//DCCLBH导出功能编号,执行导出函数 
				customExport(exportBh, exportData);
			}
			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var exportBh = "jyglnew_jygl_cyjyyzd.do";
				var url = "jyglnew_jygl_cyjyyzdgl.do?method=exportData&dcclbh="+exportBh;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// 新版导入
			function drxxNew(){
				toImportDataNew("IMPORT_N790103_NEW");
				return false;
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jyglnew_jygl_cyjyyzdgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
							</li>
							<li><a href="javascript:void(0);" onclick="drxxNew();return false;" id="btn_dr" class="btn_dr">导入</a></li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc" id="btn_dc" onclick="exportConfig();return false;">导出</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>就业创业教育与指导信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
