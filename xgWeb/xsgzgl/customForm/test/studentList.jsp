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
				url:"demo.do?method=studentList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:xhLink,key:true},
				   {label:'����',name:'xm', index: 'xm',width:'15%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xydm',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["xh"] = jQuery("#xh").val();
				map["xm"] = jQuery("#xm").val();
				map["xymc"] = jQuery("#xymc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�ѧ��',400,300,'demo.do?method=updateStudent&xh='+rows[0]["xh"]);
				}
			}

			function delStudent(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					jQuery.post("demo.do?method=delStudent",{values:ids.toString()},function(data){
						alert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
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
	
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="showDialog('����ѧ��',400,300,'demo.do?method=addStudent');" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="delStudent();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<input type="text" id="xh"/>
							</td>
							<th>����</th>
							<td>
								<input type="text" id="xm"/>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<input type="text" id="xymc"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
