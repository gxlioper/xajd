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
				url:"xlzx_xlwjga_xlwjgafkgl.do?method=xlwjgafkManage&type=query",
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
				return "<a href='javascript:void(0);' class='name' onclick='xlwjgafkView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function xlwjgafkView(xh) {
				showDialog("�鿴����Σ������", 750,500, "xlzx_xlwjga_xlwjgasbgl.do?method=viewXlwjgasb&xh=" + xh);
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				} else {
					if(rows[0]["wjgabz"] != '1'){
						showAlertDivLayer("��ѡ��Σ�������ļ�¼��");
						return false;
					}
					var url = 'xlzx_xlwjga_xlwjgafkgl.do?method=updateXlwjgafk&xh='+rows[0]["xh"];
					var title = "����Σ����������";
					showDialog(title,520,250,url);
				}
			}
			var DCCLBH = "xlzx_xlwjga_xlwjgafk.do";//dcclbh,�������ܱ��
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// ��������
			function kycxxmjgExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xlzx_xlwjga_xlwjgafkgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
	<html:form action="/xlzx_xlwjga_xlwjgafkgl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">����</a></li>
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
