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
				var gridSetting = {
					caption:"�������",
					pager:"pager",
					url:"xszz_knsjg.do?method=knstksPercent&type=query",
					colList:[
				       {label:'ѧԺ����',name:'xymc', index: 'xymc',width:'10%'},
				       {label:'������',name:'tk', index: 'tk',width:'5%'},
				       {label:'������',name:'kn', index: 'kn',width:'5%'},
				       {label:'������/������',name:'bl', index: 'bl',width:'5%'}
					],
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});

			/**
			 * ������˲�ѯ
			 */
			function doQuery(){
				var map = {};
				map["ylzd5"] = jQuery("#ylzd5").val();
				jQuery("#dataTable").reloadGrid(map);
				
			}
			
		</script>
	</head>
	<body>
		<html:form action="/xszz_knsjg" method="post" styleId="knsjgForm" onsubmit="return false;">
			<div class="tab">
				<table class="formlist">
				<!-- �������� -->	
					<div class="searchtab">
						<table width="100%" border="0">
							<tr>
								<th width="12%">ѧԺ����</th>
								<td>
									<input type="text" id="ylzd5" name="ylzd5" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
									
								</td>
								<td>
									<span style="color:red">ע�⣺��Ϊ�����������ѧԺ���ѡ���������</span>
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
			</div>
		</html:form>
	</body>
</html>
