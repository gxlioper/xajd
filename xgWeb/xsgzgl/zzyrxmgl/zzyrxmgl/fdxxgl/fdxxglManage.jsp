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
						caption:"��Ϣ�б�",
						pager:"pager",
						url:"zzyrxmglfdxxgl.do?method=fdxxglManage&type=query",
						colList:[
							{label:'key',name:'fdxxid', index: 'fdxxid',key:true ,hidden:true},
						 	{label:'����ѧ��',name:'fdrxh', index: 'fdrxh',width:'10%'},
							{label:'��������',name:'fdrxm', index: 'fdrxm',width:'10%'},
							{label:'ѧԱѧ��',name:'bfdrxh', index: 'bfdrxh',width:'10%'},
							{label:'ѧԱ����',name:'bfdrxm', index: 'bfdrxm',width:'10%'},
						 	{label:'������Ŀ',name:'fdkm', index: 'fdkm',width:'14%'},
						 	{label:'�μ�ʱ��',name:'tysj', index: 'tysj',width:'19%'},
						 	{label:'ѧ������',name:'xspjjg', index: 'xspjjg',width:'10%'},
						 	{label:'��ʦ��������',name:'lspjjg', index: 'lspjjg',width:'10%'}
						],
						sortname: "tysj",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function addPj(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_pj").val());
				} else {
					var xspjjg = rows[0]["xspjjg"];
					if(xspjjg == "" || xspjjg == null){
						showAlertDivLayer("��ʱ�������ۣ�");
						return false;
					}
					var url = 'zzyrxmglfdxxgl.do?method=addFdxxglpj&fdxxid=' + rows[0]["fdxxid"];
					var title = "����";
					showDialog(title,450,155,url);
				}
			}
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_ck").val());
				} else {
					var url = 'zzyrxmglfdxxgl.do?method=viewFdxxgl&fdxxid=' + rows[0]["fdxxid"];
					var title = "�鿴";
					showDialog(title,750,465,url);
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
		<html:form action="/zzyrxmglfdxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="addPj();return false;" class="btn_zj">����</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">�鿴��ϸ</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
