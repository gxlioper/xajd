<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"��ѯ���",
					pager:"pager",
					rowNum:10,
					url:"stgl_zjsr.do?method=getTeaList",
					params:getSuperSearch(),
					colList:[
					   {label:'����',name:'zgh', index: 'zgh',width:'12%',key:true},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'���ڲ���',name:'bmmc', index: 'bmdm',width:'8%'},
					   {label:'bmdm',name:'bmdm', index: 'bmdm',hidden:true},
					   {label:'zc',name:'zc', index: 'zc',hidden:true},
					   {label:'lxdh',name:'lxdh', index: 'lxdh',hidden:true}
					   ],
					sortname: "zgh",
				 	sortorder: "desc",
				 	radioselect:true
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function selZdls() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("��ѡ��һ����ʦ��");
					return false;
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				parentsW.jQuery("#zdlsxm").val(rows[0]['xm']);
				parentsW.jQuery("#zdls").val(rows[0]['zgh']);
				closeDialog();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/stglStsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="selZdls();return false;" class="btn_zj">���</a>
					</li>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ���
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
