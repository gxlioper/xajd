<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�¿���ϵ���б�",
					pager:"pager",
					url:"gygl_ntzd.do?method=nykhxsManage&type=query",
					colList:[
					   {label:'����',name:'ny', index: 'ny',width:'20%',key:true},
					   {label:'����ϵ��',name:'khxs', index: 'khxs',width:'40%'},
					   {label:'�������ῼ�˷�ֵ',name:'dysskhfz', index: 'dysskhfz',width:'40%'}
					],
					sortname: "ny",
				 	sortorder: "desc"
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				showDialog("�����¿���ϵ��",400,195,"gygl_ntzd.do?method=nykhxsAdd");
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog("�޸��¿���ϵ��",400,195,'gygl_ntzd.do?method=nykhxsUpdate&ny='+rows[0]["ny"]);
				}
			}
			function deletes(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
						if(ty=="ok"){
							jQuery.post("gygl_ntzd.do?method=nykhxsDelete",{values:ids.toString()},function(data){
								alertInfo(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});
				}
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//Ϊbuttonע���¼�
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				//jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_ntzd.do?method=nykhxsManage">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">ɾ��</a></li>	
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>	
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�¿���ϵ���б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
