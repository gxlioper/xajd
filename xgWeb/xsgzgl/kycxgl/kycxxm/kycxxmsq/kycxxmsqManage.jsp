<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"科研项目列表",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmsqgl.do?method=kycxxmsqManage&type=query",
						colList:[      
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'科研项目名称',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'科研类别',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'申请时间',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'项目申请人',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'指导老师',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
						   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
						   {label:'科研类别代码',name:'lbdm', index: 'lbdm',hidden:true}
						],
						sortname: "xmsqsj",
					 	sortorder: "desc"
					};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function viewKycxxmsq(sqid) {
				showDialog("查看科研项目", 727,480, "kycxgl_kycxxm_kycxxmsqgl.do?method=viewKycxxmsq&sqid=" + sqid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewKycxxmsq(\"" + rowObject["sqid"] + "\");'>" + cellValue + "</a>";
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function add(){
				var url = "kycxgl_kycxxm_kycxxmsqgl.do?method=addKycxxmsq";
				var title = "申请科研项目";
				showDialog(title,727,460,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var shzt = rows[0]["shzt"];
					if ("0" != shzt&&"3" != shzt) {
						showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
						return false;
					}
					var url = 'kycxgl_kycxxm_kycxxmsqgl.do?method=updateKycxxmsq&sqid=' + rows[0]["sqid"];
					var title = "修改科研项目";
					showDialog(title,727,460,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					var userName = jQuery("#userName").val();
					for (var i = 0; i < ids.length; i++) {
						if (rows[i]["shzt"] != "0") {
							showAlertDivLayer(jQuery("#lable_wjt_sc").val());
							return false;
						}
					}
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
							jQuery.post("kycxgl_kycxxm_kycxxmsqgl.do?method=delKycxxmsq", { values : ids.toString() },
									function(data) {
										var mes = "成功删除了<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>条数据";
										showAlertDivLayer(mes);
										jQuery("#dataTable").reloadGrid();
									}, 'json');
						}
					});
				}
			}
			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1){
					showAlertDivLayer(jQuery("#lable_one_tj").val());
				}else{
					var rows = jQuery("#dataTable").getSeletRow();
					var url = "kycxgl_kycxxm_kycxxmsqgl.do?method=submitKycxxmsq";
					if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
						showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
						return false;
					}
					showConfirmDivLayer(jQuery("#lable_confirm_tj").val(),{"okFun":function(){
						jQuery.post(url,
							{values:ids.toString(),
							 lbdm: rows[0]["lbdm"],
							 splc : rows[0]["splc"],
							 shzt : rows[0]["shzt"]
							},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1 ) {
					showAlertDivLayer(jQuery("#lable_one_cx").val());
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if (rows[i]['shzt'] != '5') {
							showAlertDivLayer(jQuery("#lable_shz_cx").val());
							return false;
						}
					}
					showConfirmDivLayer(jQuery("#lable_confirm_cx").val(),{"okFun":function(){
						jQuery.post("kycxgl_kycxxm_kycxxmsqgl.do?method=cancelKycxxmsq",
							{
							 values:ids.toString(),
							 splcid : rows[0]['splc'] 
							},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function kycxxmLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer(jQuery("#lable_one_lcgz").val());
				} else {
					var shzt = rows[0]["shzt"];
					if ("0" == shzt) {
						showAlertDivLayer(jQuery("#lable_wxglcxx").val());
						return false;
					}
					showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
			var DCCLBH = "kycxgl_kycxxm_kycxxmsq.do";//dcclbh,导出功能编号
			//自定义导出 功能
			function exportConfig() {
				customExport(DCCLBH, kycxxmsqExportData);
			}
			// 导出方法
			function kycxxmsqExportData() {
				setSearchTj();//设置高级查询条件
				var url = "kycxgl_kycxxm_kycxxmsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
		<html:form action="/kycxgl_kycxxm_kycxxmsqgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="kycxxmLcinfo();return false;" class="btn_cs">流程跟踪</a>
						</li>	
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
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
				<span>科研项目列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
