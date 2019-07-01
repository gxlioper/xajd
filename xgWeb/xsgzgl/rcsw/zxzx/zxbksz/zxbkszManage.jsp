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
						caption:"��ѯ����б�",
						pager:"pager",
						url:"rcsw_zxzx_zxbkszgl.do?method=zxbkszManage&type=query",
						colList:[
							{label:'key',name:'bkid', index: 'bkid',key:true ,hidden:true},
						   {label:'�������',name:'bkmc', index: 'bkmc',width:'30%'},
						   {label:'����״̬',name:'sfqymc', index: 'sfqymc',width:'10%'},
						   {label:'��ʾ˳��',name:'xssx', index: 'xssx',width:'10%'}
						],
						sortname: "xssx",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function zxbkszView(bkid) {
				showDialog("�鿴��ѯ���", 470,180, "rcsw_zxzx_zxbkszgl.do?method=viewZxbksz&bkid=" + bkid);
			}
			function bkmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='zxbkszView(\"" + rowObject["bkid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "rcsw_zxzx_zxbkszgl.do?method=addZxbksz";
				var title = "������ѯ���";
				showDialog(title,470,180,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'rcsw_zxzx_zxbkszgl.do?method=updateZxbksz&bkid=' + rows[0]["bkid"];
					var title = "�޸���ѯ���";
					showDialog(title,470,180,url);
				}
			}
			function updateSfqy(sfqy){
				var msg = "����";
				if(sfqy == '0'){
					msg = "ͣ��";
				}
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫ"+msg+"�ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫ"+msg+"ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("rcsw_zxzx_zxbkszgl.do?method=sfqyZxbksz",{values:ids.toString(),sfqy:sfqy},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
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
						jQuery.post("rcsw_zxzx_zxbkszgl.do?method=delZxbksz", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
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
		<html:form action="/rcsw_zxzx_zxbkszgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li><a href="javascript:void(0);" onclick="updateSfqy('1');" class="btn_shtg">����</a></li>						
							<li><a href="javascript:void(0);" onclick="updateSfqy('0');" class="btn_shbtg">ͣ��</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
