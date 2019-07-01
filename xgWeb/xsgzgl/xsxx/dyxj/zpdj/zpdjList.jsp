<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"xsxxDyxjZpdj.do?method=getZpdjList",
				colList:[
				   {label:'����',name:'djdm', index: 'djdm',hidden:true,key:true},
				   {label:'�����ȼ�',name:'djmc', index: 'djdm'},
				   {label:'��Ŀ˵��',name:'xmsm', index: 'xmsm'}
				],
				sortname: "djdm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["djmc"] = jQuery("#djmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�',400,140,'xsxxDyxjZpdj.do?method=zpdjEdit&djdm='+rows[0]["djdm"]);
				}
			}

			function dmwhDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("xsxxDyxjZpdj.do?method=zpdjDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDmwh(){
				showDialog('����',400,140,'xsxxDyxjZpdj.do?method=zpdjAdd');;
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
					<li><a href="javascript:void(0);" onclick="addDmwh()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="dmwhDel();" class="btn_sc">ɾ��</a></li>
				</ul>
			</div>
			</logic:equal>						
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th>�����ȼ�</th>
							<td>
								<input type="text" id="djmc"/>
							</td>
							<td align="right">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�����ȼ��б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
