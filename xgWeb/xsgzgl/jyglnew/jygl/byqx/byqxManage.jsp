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
						caption:"毕业去向信息列表",
						pager:"pager",
						url:"jyglnew_jygl_byqxgl.do?method=byqxManage&type=query",
						colList:[
							{label:'key',name:'xh', index: 'xh',key:true ,hidden:true},
						 	{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xb', index: 'xb',width:'6%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'14%'},
						   {label:'就业单位',name:'jydw', index: 'jydw',width:'17%'},
						   {label:'就业单位性质',name:'jydwxzmc', index: 'jydwxzmc',width:'10%'},
						   {label:'派遣单位',name:'pqdw', index: 'pqdw',width:'17%'},
						   {label:'就业形式',name:'jyxsmc', index: 'jyxsmc',width:'12%'},
						   {label:'就业单位性质dm',name:'jydwxz', index: 'jydwxz',hidden:true},
						   {label:'就业形式dm',name:'jyxs', index: 'jyxs',hidden:true}
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
			function xhView(xh) {
				showDialog("查看毕业去向信息", 750,350, "jyglnew_jygl_byqxgl.do?method=viewByqx&xh=" + xh);
			}
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\"" + rowObject["xh"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "jyglnew_jygl_byqxgl.do?method=addByqx";
				var title = "增加毕业去向信息";
				showDialog(title,750,500,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'jyglnew_jygl_byqxgl.do?method=updateByqx&xh=' + rows[0]["xh"];
					var title = "修改毕业去向信息";
					showDialog(title,750,500,url);
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
						jQuery.post("jyglnew_jygl_byqxgl.do?method=delByqx", { values : ids.toString() },
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
				var exportBh = "jyglnew_jygl_byqx.do";
				//DCCLBH导出功能编号,执行导出函数 
				customExport(exportBh, exportData);
			}
			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var exportBh = "jyglnew_jygl_byqx.do";
				var url = "jyglnew_jygl_byqxgl.do?method=exportData&dcclbh="+exportBh;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// 新版导入
			function drxxNew(){
				toImportDataNew("IMPORT_N790102_NEW");
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
		<html:form action="/jyglnew_jygl_byqxgl">
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
				<span>毕业去向信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
