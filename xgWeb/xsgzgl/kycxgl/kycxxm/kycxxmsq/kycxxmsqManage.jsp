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
						caption:"������Ŀ�б�",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmsqgl.do?method=kycxxmsqManage&type=query",
						colList:[      
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'������Ŀ����',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'�������',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'����ʱ��',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'��Ŀ������',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'ָ����ʦ',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
						   {label:'����������',name:'lbdm', index: 'lbdm',hidden:true}
						],
						sortname: "xmsqsj",
					 	sortorder: "desc"
					};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function viewKycxxmsq(sqid) {
				showDialog("�鿴������Ŀ", 727,480, "kycxgl_kycxxm_kycxxmsqgl.do?method=viewKycxxmsq&sqid=" + sqid);
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
				var title = "���������Ŀ";
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
					var title = "�޸Ŀ�����Ŀ";
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
										var mes = "�ɹ�ɾ����<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>������";
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
					showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
			var DCCLBH = "kycxgl_kycxxm_kycxxmsq.do";//dcclbh,�������ܱ��
			//�Զ��嵼�� ����
			function exportConfig() {
				customExport(DCCLBH, kycxxmsqExportData);
			}
			// ��������
			function kycxxmsqExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "kycxgl_kycxxm_kycxxmsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
		<html:form action="/kycxgl_kycxxm_kycxxmsqgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="kycxxmLcinfo();return false;" class="btn_cs">���̸���</a>
						</li>	
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ŀ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
