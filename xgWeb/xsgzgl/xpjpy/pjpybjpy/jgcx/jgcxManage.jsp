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
					caption:"�����б�",
					pager:"pager",
					url:"xpjpy_pjpybjpy_jgcxgl.do?method=jgcxManage&type=query&pyyxbl=${jcszModel.pyyxbl }",
					colList:[      
				         {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
				         {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'7%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'4%'},
						   //{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
						   //{label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'3%'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'11%'},
						   {label:'���뽱��',name:'xmmc', index: 'xmmc',width:'8%'},
						   {label:'��������<br/>/������',name:'yprsmc', index: 'yprsmc',width:'8%'},
						   {label:'����ͳ��',name:'pyjgtjmc', index: 'pyjgtjmc',width:'11%'},
						   {label:'������',name:'pyjgtempmc', index: 'pyjgtempmc',width:'10%'},
						   {label:'���״̬',name:'shztbjpymc', index: 'shztbjpymc',width:'5%'},
						   {label:'����������',name:'pyjgtempdm', index: 'pyjgtempdm',hidden:true},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',hidden:true},
						   {label:'ѧ��',name:'xq', index: 'xq',hidden:true},
						   {label:'�Ƿ�ȫ������',name:'yprsflag', index: 'yprsflag',hidden:true},
						   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',hidden:true},
						   {label:'�༶����״̬',name:'shztbjpy', index: 'shztbjpy',hidden:true}
						],
						callBack:setBjmc,
						sortname: "sqsj",
					 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);

			var userStatus = jQuery("#userStatus").val();
			if(userStatus != "stu"){
				jQuery("#btn_submit").hide();
				jQuery("#btn_cancel").hide();
			}
		});

		function jgcxView(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ��ѧ����");
			} else {
				var url = "xpjpy_pjpybjpy_jgcxgl.do?method=jgcxView&xn=" + rows[0]["xn"] + "&xq=" + rows[0]["xq"] + "&sqr=" + rows[0]["xh"] + "&shztbjpy=" + rows[0]["shztbjpy"] + "&xmdm=" + rows[0]["xmdm"];
				var title = "�鿴��������";
				showDialog(title,800,500,url);
			}
		}
		
		function setBjmc(){
			var html = "<span>�����б�&nbsp;&nbsp; </span>";
			var userStatus = jQuery("#userStatus").val();
			if(userStatus == "stu"){
				html += "<font color='#0000ff'>${xsxxMap.xymc} ${xsxxMap.bjmc}</font>";
			}
			jQuery("#jgcx_manage_title").html(html);
		}
		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='pjpybjpyView(\""+rowObject["sqid"]+"\");'>"+cellValue+"</a>";
		}
		function pjpybjpyView(sqid){
			showDialog('�鿴�����',780,520,'xpj_sqsh.do?method=viewSqb&sqid='+sqid);
		}
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ���Ű༶���飬����ϵ����Ա��");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ��ѧ����");
			} else {
				// ������������������ҳ��ȡֵ
				jQuery("#pyjgtempmc_hid").val(rows[0]["pyjgtempmc"]);
				jQuery("#pyjgtempdm_hid").val(rows[0]["pyjgtempdm"]);

				var ids = jQuery("#dataTable").getSeletIds();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='6'){
						showAlertDivLayer("��ѡ��δ�ύ�ļ�¼��");
						return false;
					}
				}
				
				if(rows[0]["yprsflag"] == 'false'){
					showAlertDivLayer("������Աδ���飡");
					return false;
				}
				var url = "xpjpy_pjpybjpy_jgcxgl.do?method=jgcxTj&sqid=" + rows[0]["sqid"];
				var title = "�ύ�༶������";
				showDialog(title,790,500,url);
			}
		}
		function cancleRst(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1 ) {
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']=='6'){
						showAlertDivLayer("�ü�¼��δ�ύ�����賷����");
						return false;
					}else{
						if(rows[i]['shzt']!='5' && rows[i]['shzt']!='7'){
							showAlertDivLayer("�ü�¼�ѱ���ˣ��޷�������");
							return false;
						}
					}
				}
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("xpjpy_pjpybjpy_jgcxgl.do?method=jgcxCx", {
							values : rows[0]["xh"],
							sqid : rows[0]["sqid"]
						}, function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}
		//����
		function exportConfig(){
			var DCCLBH='xpjpy_pjpybjpy_jgcx.do';
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			var DCCLBH='xpjpy_pjpybjpy_jgcx.do';
			setSearchTj();//���ø߼���ѯ����
			var url = "xpjpy_pjpybjpy_jgcxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		</script>
	</head>

	<body>
		<html:form action="/xpjpy_pjpybjpy_jgcxgl">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.bjpyisopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" id="pyjgtempmc_hid" value=""/>
			<input type="hidden" id="pyjgtempdm_hid" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="btn_submit">
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc" >�ύ</a>
						</li>
						<li id="btn_cancel">
							<a href="javascript:void(0);" onclick="cancleRst();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="jgcxView();return false;" class="btn_ck">�鿴��������</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01 id="jgcx_manage_title">
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
