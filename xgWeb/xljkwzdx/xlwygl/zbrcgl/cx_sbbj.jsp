<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSettingBj = {
					caption:"�༶��Ϣ�б�",
					pager:"pager",
					multiselect:false,
					rowNum:10,
					url:"xljk_xlwygl_zbrcglwh.do?method=cxSbbjQuery&zbid=${zbid}&sbbjlx=${sbbjlx}",
					colList:[
					   {label:'<bean:message key="lable.xb" />����',name:'xymc', index: 'xymc',width:'13%'},
					   {label:'רҵ����',name:'zymc', index: 'zymc',width:'13%'},
					   {label:'�༶����',name:'bjdm', index: 'bjdm',width:'10%',key:true},
					   {label:'�༶����',name:'bjmc', index: 'bjmc',width:'13%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
					   {label:'ѧ����',name:'xss', index: 'xss',width:'6%'}
					],
					sortname: "bjdm",
				 	sortorder: "asc"
				}
			jQuery(function(){
				var map = getSuperSearch();
				gridSettingBj["params"] = map;
				jQuery("#dataTable").initGrid(gridSettingBj);
			});
			function searchRs(){
				var map = getSuperSearch();	
				gridSettingBj["params"] = map;
				jQuery("#dataTable").initGrid(gridSettingBj);
			}

		</script>
	</head>

	<body>
		<html:form action="/jcsj">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
					<!--����start-->
			<h3 class="datetitle_01">
				<span> �༶��Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
