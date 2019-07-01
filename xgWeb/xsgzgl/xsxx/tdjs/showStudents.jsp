<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"tdjs.do?method=showStudents&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'15%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'}
				],
				params:{
				 	bjdm:"<bean:write name='tdjsForm' property='bjdm'/>"
				},
				sortname: "xh",
			 	sortorder: "asc"
			 	
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = {};

				map["xh"] = jQuery("#xh").val();
				map["xm"] = jQuery("#xm").val();
				map["bjdm"]=jQuery("#bjdm").val();
				
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<html:form action="/tdjs">
			<html:hidden property="bjdm" styleId="bjdm"/>
			
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<input type="text" name="xh" id="xh"/>
								</td>
								<th>����</th>
								<td>
									<input type="text" name="xm" id="xm"/>
								</td>
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
