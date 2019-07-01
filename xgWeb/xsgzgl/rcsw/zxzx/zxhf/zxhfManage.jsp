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
						caption:"��ѯ�б�",
						pager:"pager",
						url:"rcsw_zxzx_zxhfgl.do?method=zxhfManage&type=query",
						colList:[
							{label:'key',name:'zxid', index: 'zxid',key:true ,hidden:true},
						   {label:'��ѯ���',name:'bkmc', index: 'bkmc',width:'15%'},
						   {label:'��ѯ����',name:'zxzt', index: 'zxzt',width:'34%',formatter:zxztLink},
						   {label:'��ѯ��',name:'zxrxm', index: 'zxrxm',width:'12%'},
						   {label:'��ѯʱ��',name:'zxsj', index: 'zxsj',width:'17%'},
						   {label:'�ظ�״̬',name:'sfhfmc', index: 'sfhfmc',width:'10%'},
						   {label:'�ظ���',name:'hfrxm', index: 'hfrxm',width:'12%'},
						   {label:'�ظ�id',name:'hfid', index: 'hfid',hidden:true}
						],
						sortname: "sfhf asc,zxsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function zxhfView(zxid) {
				showDialog("�鿴��ѯ", 750,300, "rcsw_zxzx_zxhfgl.do?method=viewZxhf&zxid=" + zxid);
			}
			function zxztLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='zxhfView(\"" + rowObject["zxid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�ظ��ļ�¼��");
				} else {
					var hfid = rows[0]["hfid"];
					if(hfid){
						showAlertDivLayer("��ѡ��δ�ظ��ļ�¼��");
						return false;
					}
					var url = 'rcsw_zxzx_zxhfgl.do?method=addZxhf&zxid=' + rows[0]["zxid"];
					var title = "��ѯ�ظ�";
					showDialog(title,750,340,url);
				}
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var hfid = rows[0]["hfid"];
					if(!hfid){
						showAlertDivLayer("��ѡ���ѻظ��ļ�¼��");
						return false;
					}
					var url = 'rcsw_zxzx_zxhfgl.do?method=updateZxhf&hfid=' + hfid +'&zxid=' + rows[0]["zxid"];
					var title = "�޸���ѯ�ظ�";
					showDialog(title,750,340,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("rcsw_zxzx_zxhfgl.do?method=delZxhf", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			//����
			function exportConfig(){
				var DCCLBH='rcsw_zxzx_zxhf.do';
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH='rcsw_zxzx_zxhf.do';
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_zxzx_zxhfgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		<html:form action="/rcsw_zxzx_zxhfgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">�ظ�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
							</li>
						</logic:equal>
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
				<span>��ѯ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
