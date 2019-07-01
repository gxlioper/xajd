<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����Σ�������б�",
				pager:"pager",
				url:"xlzx_xlwjga_xlwjgasbgl.do?method=xlwjgasbManage&type=query",
				colList:[
				   {label:'����',name:'id', index: 'id',key:true,hidden : true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				  {label:'����',name:'xm', index: 'xm',width:'10%'},
				  {label:'�Ա�',name:'xb', index: 'xb',width:'7%'},
				  {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
				  {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'�ϱ���',name:'sbrxm', index: 'sbrxm',width:'10%'},
				   {label:'�ϱ�����ϵ�绰',name:'sbrlxfs', index: 'sbrlxfs',width:'10%'},
				   {label : 'Σ���̶�',name : 'wjcdmc',index : 'wjcdmc',width : '8%'},
				   {label : '���ķ���',name : 'zzfkmc',index : 'zzfkmc',width : '8%'},
				   {label : 'Σ������״̬',name : 'wjgabzmc',index : 'wjgabzmc',width : '9%'},
				   {label : 'Σ���̶ȴ���',name : 'wjcd',index : 'wjcd',hidden : true},
				   {label : '���ķ�������',name : 'zzfk',index : 'zzfk',hidden : true},
				   {label : 'Σ������״̬����',name : 'wjgabz',index : 'wjgabz',hidden : true},
				   {label : '�ϱ�ʱ��',name : 'sbsj',index : 'sbsj',hidden : true}
				],
				sortname: "sbsj",
			 	sortorder: "desc"
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xlwjgasbView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function xlwjgasbView(xh) {
				showDialog("�鿴����Σ������", 750,500, "xlzx_xlwjga_xlwjgasbgl.do?method=viewXlwjgasb&xh=" + xh);
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function add(){
				var url = "xlzx_xlwjga_xlwjgasbgl.do?method=addXlwjgasb";
				var title = "��������Σ������";
				showDialog(title,750,500,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					if(rows[0]['zzfk']!='0'){
						showAlertDivLayer("ֻ���޸�δ�����ļ�¼��");
						return false;
					}
					var url = 'xlzx_xlwjga_xlwjgasbgl.do?method=updateXlwjgasb&xh='+rows[0]["xh"];
					var title = "�޸�����Σ������";
					showDialog(title,750,500,url);
				}
			}
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					for(var i=0;i<ids.length;i++){
						if(rows[i]['zzfk']!='0'){
							showAlertDivLayer("ֻ��ɾ��δ�����ļ�¼��");
							return false;
						}
					}
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(),{"okFun":function(){
							jQuery.post("xlzx_xlwjga_xlwjgasbgl.do?method=delXlwjgasb",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫ����ļ�¼��");
				} else {
					for(var i=0;i<ids.length;i++){
						if(rows[i]['zzfk']!='1'){
							showAlertDivLayer("ֻ�ܽ���ѷ����ļ�¼��");
							return false;
						}
						if(rows[i]['wjgabz']!='1'){
							showAlertDivLayer("ֻ�ܽ��Σ�������ļ�¼��");
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫ���ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("xlzx_xlwjga_xlwjgasbgl.do?method=cancelXlwjgasb",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			var DCCLBH = "xlzx_xlwjga_xlwjgasb.do";//dcclbh,�������ܱ��
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// ��������
			function kycxxmjgExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xlzx_xlwjga_xlwjgasbgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
	<!-- ���� -->
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
		</p>
		<p class="help">
			<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
		</p>
	</div>
	<!-- ���� end-->
	<!-- ��ʾ��Ϣ -->
	<div class="prompt" id="div_help" style="display: none">
		<h3>
			<span>��ʾ��</span>
		</h3>
		<p>
			<span>
				���ϱ�Σ������ѧ������������ӡ��ظ�����ʱ�����²���һ��Σ��������¼����ǰֻ̨��ʾ���¼�¼����ͨ�����ѧ�Ų鿴��ѧ����ʷΣ�����������
			</span>
		</p>
		<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
	</div>
	<!-- ��ʾ��Ϣ end-->
	<html:form action="/xlzx_xlwjga_xlwjgasbgl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
						<li><a href="javascript:void(0);" onclick="cancel();" class="btn_qxsh">Σ�����</a></li>						
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>����Σ�������б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
