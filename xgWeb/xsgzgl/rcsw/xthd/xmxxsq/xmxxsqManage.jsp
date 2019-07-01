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
					caption:"��Ŀ�����б�",
					pager:"pager",
					url:"rcsw_txhd_xmxxsqgl.do?method=xmxxsqManage&type=query",
					colList:[      
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					   {label:'��������',name:'splc', index: 'splc',hidden:true},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'5%'},
					   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'23%',formatter:show},
					   {label:'�ʱ��',name:'hdsj', index: 'hdsj',width:'25%'},
					   {label:'��ص�',name:'hddd', index: 'hddd',width:'19%'},
					   {label:'����',name:'lbmc', index: 'lbmc',width:'10%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'17%'},
					   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
					   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function show(cellValue, rowObject){
			return "<a href='javascript:void(0);' onclick=\"viewXmxxsq('" + rowObject["sqid"]+ "')\" class='name'>" + cellValue + "</a>";
		}
		function viewXmxxsq(sqid) {
			showDialog("��Ŀ����鿴", 700, 495, "rcsw_txhd_xmxxsqgl.do?method=viewXmxxsq&sqid=" + sqid);
		}
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
				return false;
			}
			var url = "rcsw_txhd_xmxxsqgl.do?method=addXmxxsq";
			var title = "������Ŀ����";
			showDialog(title,790,476,url);
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
//			if ("false" == isopen){
//				showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
//				return false;
//			}
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" != shzt&&"3" != shzt) {
					showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
					return false;
				}
				var url = 'rcsw_txhd_xmxxsqgl.do?method=updateXmxxsq&sqid=' + rows[0]["sqid"];
				var title = "�޸���Ŀ����";
				showDialog(title,790,470,url);
			}
		}

		//ɾ��
		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				var userName = jQuery("#userName").val();
				for (var i = 0; i < ids.length; i++) {
					if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
						showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
						return false;
					}
				}
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("rcsw_txhd_xmxxsqgl.do?method=delXmxxsq", { values : ids.toString() },
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
			if(ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
			}else if (ids.length >1 ){
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
					return false;
				}
				if('3'!=rows[0]["shzt"]&&"true"!=isopen){
					showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
					return false;
				}
				var url = "rcsw_txhd_xmxxsqgl.do?method=submitXmxxsq";
				if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
					showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
					return false;
				}
				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
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
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
			} else if (ids.length >1 ) {
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var userName = jQuery("#userName").val();
				for(var i=0;i<ids.length;i++){
					if (rows[i]['shzt'] != '5') {
						showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
						return false;
					}
				}
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_txhd_xmxxsqgl.do?method=cancelXmxxsq",
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

		function xthdLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" == shzt) {
					showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
					return false;
				}
				showDialog("��Ŀ�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}

		var DCCLBH = "rcsw_xthd_xmxxsq.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, xmxxsqExportData);
		}

		// ��������
		function xmxxsqExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_txhd_xmxxsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>

	<body>
		<html:form action="/rcsw_txhd_xmxxsqgl">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
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
							<a href="javascript:void(0);" onclick="xthdLcinfo();return false;" title="ѡ��һ����¼������ð�ť���Բ鿴������̡�" class="btn_cs">���̸���</a>
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
				<span>��Ŀ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
