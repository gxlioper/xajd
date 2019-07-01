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
				url:"rcswSbglSbxx.do?method=getList",
				colList:[
				   {label:'����',name:'dm', index: 'dm',hidden:true,key:true},
				   {label:'�豸����',name:'bh', index: 'bh'},
				   {label:'�豸����',name:'flmc', index: 'fldm'},
				   {label:'����',name:'mc', index: 'mc'}
				],
				sortname: "dm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["bh"] = jQuery("#bh").val();
				map["mc"] = jQuery("#mc").val();
				map["fldm"] = jQuery("#fldm").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�',400,220,'rcswSbglSbxx.do?method=edit&dm='+rows[0]["dm"]);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("rcswSbglSbxx.do?method=delete",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('����',400,220,'rcswSbglSbxx.do?method=add');;
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcswSbglSbxx" method="post" styleId="form">
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="12%">�豸����</th>
							<td width="15%">
								<input type="text" id="bh"/>
							</td>
							<th width="12%">�豸����</th>
							<td width="15%">
								<input type="text" id="mc"/>
							</td>
							<th width="12%">�豸����</th>
							<td>
								<html:select property="fldm" styleId="fldm">
									<html:option value=""></html:option>
									<html:options collection="sbflList" property="dm" labelProperty="mc"/>
								</html:select>
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
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li><a href="rcsw_sbgl_sbfl.do"><span>�豸����</span></a></li>
	        <li class="ha"><a href="javascript:void(0);"><span>�豸��Ϣ</span></a></li>
	      </ul>
	    </div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �����б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
