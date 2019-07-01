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
						caption:"ѧ���б�",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmjggl.do?method=kycxxmjgManageCx&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						  {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
						  {label:'����',name:'xm', index: 'xm',width:'10%'},
						  {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'������Ŀ����',name:'xmmc', index: 'xmmc',width:'27%',formatter:xmmcLink},
						   {label:'�������',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'����ʱ��',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'ָ����ʦ',name:'zdlsxm', index: 'zdlsxm',width:'10%'}
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
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function zxsxxView(xh) {
				showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh + "&xs");
			}
			function kycxxmjgView(jgid) {
				showDialog("�鿴������Ŀ", 750,415, "kycxgl_kycxxm_kycxxmjggl.do?method=viewKycxxmjg&jgid=" + jgid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='kycxxmjgView(\"" + rowObject["jgid"] + "\");'>" + cellValue + "</a>";
			}
			var DCCLBH = "kycxgl_kycxxm_kycxxmjgglCx";//dcclbh,�������ܱ��
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// ��������
			function kycxxmjgExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "kycxgl_kycxxm_kycxxmjggl.do?method=exportDataCx&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/kycxgl_kycxxm_kycxxmjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
