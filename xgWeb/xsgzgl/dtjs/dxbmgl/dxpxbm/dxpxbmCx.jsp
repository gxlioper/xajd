<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
        <script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѵ��Ϣ����б�",
				pager:"pager",
				url:"dtjs_dxbmgl_dxpxbmCx.do?type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',key:true,hidden:true},
				   {label:'sfbm',name:'sfbm', index: 'sfbm',hidden:true},
				   {label:'��ѵ�ڴ�',name:'pxqc', index: 'pxqc',formatter:dcmcLink},
				   {label:'��ѵʱ��',name:'pxsj', index: 'xm'},
				   {label:'������ʼʱ��',name:'bmkssj', index: 'bmkssj'},
				   {label:'��������ʱ��',name:'bmjssj', index: 'bmjssj'},
				   {label:'������',name:'fbrxm', index: 'fbrxm'},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
				   {label:'�Ƿ��ѱ���',name:'sfbmmc', index: 'sfbmmc'},
				   {label:'���״̬',name:'shztmc', index: 'shztmc'}
				],
				sortname: "sfbm,pxsj",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function reload(){
				jQuery("#dataTable").reloadGrid();
			}
			function dcmcLink(cellValue, rowObject) {
				var id = rowObject["id"];
				var pxqc = rowObject["pxqc"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('"+id+"')\" class='name'>"+pxqc+"</a>";
			}
			function ckxx(id) {
				var url = "dtjs_dxbmgl_dxpxbmCk.do?id=" + id;
				var title = "�鿴��ѵ��Ϣ";
				showDialog(title, 700, 250, url);
			}
			function add(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				}else if (rows[0]["sfbm"] == '1') {
					showAlertDivLayer("�Ѿ���������Ŀ����Ҫ�ظ�������");
				} else {
					showConfirmDivLayer("��ȷ��Ҫ������", {
						"okFun" : function() {
						jQuery.post("dtjs_dxbmgl_dxpxbmBm.do", {pxid : rows[0]["id"]}, function(data) {
							if(data){
								showAlertDivLayer("�����ɹ�!");
							}else{
								showAlertDivLayer("����ʧ��!");
							}
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="dxbmgl_dxpxbm.do" >
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
					</li>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ѵ��Ϣ����б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
