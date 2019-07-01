<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var jsonStr = jQuery("#jsonStr").val();
				var map = JSON.parse(jsonStr);
				map["sqid"] = jQuery("#id").val();
				var gridSetting = {
					caption:"�������",
					pager:"pager",
					rowNum:10,
					url:"xpj_sqsh.do?method=viweShmx",
					colList:[
				       {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				       {label:'key',name:'key', index: 'key',key:true ,hidden:true},
				       {label:'ѧԺ����',name:'xydm', index: 'xydm',hidden:true},
				       {label:'��Ŀ����',name:'xmdm', index: 'xmdm',hidden:true},
				       {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'10%'},
				       {label:'��������',name:'zzme', index: 'zzme',width:'10%'},
				       {label:'��ͨ������',name:'ytgrs', index: 'ytgrs',width:'10%'},
				       {label:'ʣ���ͨ������',name:'syktgrs', index: 'syktgrs',width:'10%'},
				       {label:'�����������',name:'bcshrs', index: 'bcshrs',width:'10%'}
					],
					params:map
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["bjdm"] = jQuery("#bjdm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		
			function saveZdPlsh(shzt){
				
				var shyj = jQuery("#shyj").val();
				var xyXmdm = jQuery("#dataTable").getSeletIds();
				var jsonStr = jQuery("#jsonStr").val();
				var id = jQuery("#id").val();
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				
				if (shyj == ""){
					showAlert("����д��������");
					return false;
				}

				if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���ͨ���ļ�¼��");
					return false;
				}

				var api = frameElement.api,W = api.opener;
				W.saveZdPlsh(shzt,shyj,xyXmdm.toString(),jsonStr,id);
				closeDialog();
			}

			/**
			 * ������˲�ѯ
			 */
			function doQuery(){
				
				var jsonStr = jQuery("#jsonStr").val();
				var map = {};
				if(jsonStr){
					map = JSON.parse(jsonStr);
				}
				map["sqid"] = jQuery("#id").val();
				map["xyXmdm"] = jQuery("#xyXm").val();
				jQuery("#dataTable").reloadGrid(map);
				
			}

			
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" onsubmit="return false;">
		<input type="hidden" value='${jsonStr }' id="jsonStr" />
		<input type="hidden" value='${id }' id="id" />
			<div class="tab">
				<table class="formlist">
				<!-- �������� -->	
					<div class="searchtab">
						<table width="100%" border="0">
							<tr>
								<th width="12%">ѧԺ/��Ŀ����</th>
								<td>
									<input type="text" id="xyXm" name="xyXm" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
								</td>
								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>					
						</table>
					</div>
				</table>
				<!-- �������� end-->
				<table id="dataTable" style="width:100%;"></table>
				<div id="pager"></div>
				<table class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=pjpy&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>
						</tr>
						<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveZdPlsh('1');">
											ͨ��
										</button>
										<button type="button" onclick="saveZdPlsh('2');">
											��ͨ��
										</button>
										<button type="button" onclick="saveZdPlsh('3');">
											�˻�
										</button>
										<button type="button" name="�� ��" onclick="closeDialog();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</div>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
