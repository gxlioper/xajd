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
				url:"xsxx_xsgl.do?method=showStudentsForGygl&type=query&lddm=${lddm}",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',key:true},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'רҵ',name:'zymc', index: 'zydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'��λ��Ϣ',name:'cwxx', index: 'cwxx'},	
				   {label:'¥������',name:'ldmc', index: 'ldmc',hidden:true},
				   {label:'���Һ�',name:'qsh', index: 'qsh',hidden:true},
				   {label:'����',name:'xh', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   var rowData = JSON.stringify(rowObject);
						return '<button type=\'button\' onclick=\'selectStudent('+rowData+');\' class=\'btn_01\' >ѡ��</button>';
				   }}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectStudent(rowData){
				var api = frameElement.api;
				
				if (api){
					if (api.get('childDialog')){
						var w = api.get('parentDialog');
						updateElementContent(rowData,w);
					} else {
						var w = api.opener;
						updateElementContent(rowData,w);
					}
				} else if (parent.window){
					var w = parent.window;
					updateElementContent(rowData,w);
					
				}
				
				iFClose();
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function updateElementContent(rowData,w){
				var elements = '${elementIds}';
				var elementIds = elements.split(",");
				
				for(var i=0;i<elementIds.length;i++){
					var id = elementIds[i];
					var ele = w.document.getElementById(id);
					if(id=="xh"){
						ele.value = rowData[id];
					}else{
						ele.innerText = rowData[id];
					}
				}
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/xsxx_xsgl">
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
