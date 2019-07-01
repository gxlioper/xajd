<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"������Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"gyjc_ccjgcx.do?method=showQsxx&type=query",
				colList:[
				   {label:'¥��',name:'ldmc', index: 'ldmc',width:'10%'},
				   {label:'¥������',name:'lddm', index: 'lddm',width:'10%'},
				   {label:'���',name:'ch', index: 'ch',width:'5%'},
				   {label:'���Һ�',name:'qsh', index: 'qsh',width:'5%'},
				   {label:'ѧԺ����',name:'xydm', index: 'xydm',width:'10%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
				   {label:'����',name:'xh', index: '',width:'5%',noSort:true,formatter:dcmcLink}
				],
				sortname: "ldmc",
			 	sortorder: "desc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function dcmcLink(cellValue, rowObject) {
				var rowData = JSON.stringify(rowObject);
				return '<button type=\'button\' onclick=\'show('+rowData+');\' class=\'btn_01\' >ѡ��</button>';
			}
			function show(rowData){
				var api = frameElement.api;
				var W = api.get('parentDialog');
				W.showQsxxNotF5CallBack(rowData);
				api.close();
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/gyjc_ccjgcx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ������Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
