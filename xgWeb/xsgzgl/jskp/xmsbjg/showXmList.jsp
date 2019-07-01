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
				url:"jskpXmjg.do?method=showXmList&type=query",
				colList:[
				   {label:'xmid',name:'xmid', index: 'xmid',width:'13%',key:true},
				   {label:'��Ŀ����',name:'xm', index: 'xm',width:'13%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'19%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'19%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'18%'},
				   {label:'����',name:'xh', index: '',width:'12%',noSort:true,formatter:dcmcLink}
				],
				sortname: "xh",
			 	sortorder: "asc"
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
				W.showXmxxCallBack(rowData);
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
	
		<html:form action="/jskpXmjg">
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
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
