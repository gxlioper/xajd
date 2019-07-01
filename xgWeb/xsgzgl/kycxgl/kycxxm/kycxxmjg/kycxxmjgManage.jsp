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
						caption:"科研项目列表",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmjggl.do?method=kycxxmjgManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'科研项目名称',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'科研类别',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'申请时间',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'项目申请人',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'指导老师',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'科研类别代码',name:'lbdm', index: 'lbdm',hidden:true}
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
			function kycxxmjgView(jgid) {
				showDialog("查看科研项目", 750,415, "kycxgl_kycxxm_kycxxmjggl.do?method=viewKycxxmjg&jgid=" + jgid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='kycxxmjgView(\"" + rowObject["jgid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=addKycxxmjg";
				var title = "增加科研项目";
				showDialog(title,750,445,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var sjly = rows[0]["sjly"];
					/*if (sjly=='1'){
						showAlertDivLayer("审批流数据不允许修改！");
						return false;
					}else{*/
						var url = 'kycxgl_kycxxm_kycxxmjggl.do?method=updateKycxxmjg&jgid=' + rows[0]["jgid"];
						var title = "修改科研项目";
						if (sjly=='1'){
							showDialog(title,750,478,url);
						}else{
							showDialog(title,750,445,url);
						}
					//}
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					for (var i = 0; i < ids.length; i++) {
						if (rows[i]["sjly"] == "1") {
							showAlertDivLayer("审核流数据不能删除！");
							return false;
						}
					}
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("kycxgl_kycxxm_kycxxmjggl.do?method=delKycxxmjg", { values : ids.toString() },
								function(data) {
									var mes = "成功删除了<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>条数据";
									showAlertDivLayer(mes);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			// 新版导入
			function drxxNew(){
				toImportDataNew("IMPORT_N970104_NEW");
				return false;
			}
			var DCCLBH = "kycxgl_kycxxm_kycxxmjg.do";//dcclbh,导出功能编号
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// 导出方法
			function kycxxmjgExportData() {
				setSearchTj();//设置高级查询条件
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
				<span>科研项目列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
