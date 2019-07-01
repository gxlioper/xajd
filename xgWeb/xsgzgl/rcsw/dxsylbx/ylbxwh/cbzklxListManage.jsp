<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/dxsylbx/ylbxwh/js/cbzklxListManage.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function add(){
			var url = "rcsw_dxsylbx_ylbxwhgl.do?method=addCbzklx";
			var title = "���Ӳα�״������";
			showDialog(title,470,180,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				var url = "rcsw_dxsylbx_ylbxwhgl.do?method=updateCbzklx&cbzkdm="+rows[0]["cbzkdm"];
				var title = "�޸Ĳα�״������";
				showDialog(title,470,180,url);
			}
		}   
		
		</script>
	</head>
	<body>
	<html:form action="/rcsw_dxsylbx_ylbxwhgl" method="post">
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="delCbzklx();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title"><ul>
					<li ><a href="#" onclick="newChgCode(this);return false;" id="rcsw_dxsylbx_ylbxwhgl.do?method=czqebzlxListManage"><span>��������</span> </a>
					<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="rcsw_dxsylbx_ylbxwhgl.do?method=cbzklxListManage"><span>�α�״������</span> </a>
				</div>
			</div>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">�α�״������</th>
						<td>
							<input type="text" id="cbzkmc" name="cbzkmc" maxleng="20" style="width:220px;"
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span> �α�״��ά���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
