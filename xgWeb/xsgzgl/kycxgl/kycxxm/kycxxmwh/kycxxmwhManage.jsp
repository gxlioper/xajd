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
				caption:"��������б�",
				pager:"pager",
				url:"kycxgl_kycxxm_kycxxmwhgl.do?method=kycxxmwhManage&type=query",
				colList:[
				   {label:'����������',name:'lbdm', index: 'lbdm',key:true,width:'25%'},
				   {label:'�����������',name:'lbmc', index: 'lbmc',width:'25%'},
				   {label:'�������',name:'lcxx', index: 'lcxx',width:'50%'},
				   {label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
				   {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
				   {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true}
				],
				sortname: "lbdm",
			 	sortorder: "asc"
			}
			function query(){
				var map = {};
				map["lbmc"] = jQuery("#lbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			// ���뿪�� 
			function setSqkg(cellValue,rowObject){
				var lbdm = rowObject.lbdm;
				var value = "δ����";
				var color = "#ff0000";
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "�ѿ���";
						color = "#004400";
					}
				}
				return "<a  href='javascript:void(0);' onclick='return sjkg(\""+lbdm+"\");' ><font color='"+color+"'>"+value+"</font></a>";
			}
			//ʱ�俪��
			function sjkg(lbdm) {
				if(lbdm == null){ //�����ť
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
						return false;
					}
					lbdm = rows[0]["lbdm"];
				}
				var url = 'kycxgl_kycxxm_kycxxmwhgl.do?method=kycxxmwhSjkg&lbdm=' + lbdm;
				var title = "����ʱ�����";
				showDialog(title, 610, 210, url);
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function add(){
				var url = "kycxgl_kycxxm_kycxxmwhgl.do?method=addKycxxmwh";
				var title = "���ӿ������";
				showDialog(title,470,180,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'kycxgl_kycxxm_kycxxmwhgl.do?method=updateKycxxmwh&lbdm='+rows[0]["lbdm"];
					var title = "�޸Ŀ������";
					showDialog(title,470,180,url);
				}
			}
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(),{"okFun":function(){
							jQuery.post("kycxgl_kycxxm_kycxxmwhgl.do?method=delKycxxmwh",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
		</script>
	</head>
	<body>
	<html:form action="/kycxgl_kycxxm_kycxxmwhgl" method="post">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
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
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					</logic:equal>
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">�����������</th>
						<td width="5%">
							<input type="text" id="lbmc" name="lbmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}" />
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
				<h3 class="datetitle_01">
					<span>��������б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
