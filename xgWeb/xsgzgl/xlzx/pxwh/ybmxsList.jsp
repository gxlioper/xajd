<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/pxwh/js/xljkpxwh.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"xlzx_pxwh.do?method=ybmxsList&type=query&pxid="+jQuery("#pxid").val(),
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'}
						],
				sortname: "xh",
			 	sortorder: "asc",
			}
			var map = getSuperSearch();
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
			
		});
			
		</script>
	</head>

	<body>
		<html:form action="/xlzx_pxwh">
		<input type="hidden" id="pxid" value="${pxwhForm.pxid}"/>
			<div class="toolbox">
			<%@ include file="/comm/hiddenValue.jsp"%>
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
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
