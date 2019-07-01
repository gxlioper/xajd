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
				url:"xsxx_xsgl.do?method=showStudentsForTsxs&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',key:true},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'רҵ',name:'zymc', index: 'zydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'��������',name:'knlxmc', index: 'knlxmc'},				   
				   {label:'����',name:'xh', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectStudent('"+cell+"');\">ѡ��</label>";
				   }}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectStudent(xh){
				var gotoPath = jQuery("#gotoPath").val();

				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&xh="+xh;
				} else {
					gotoPath = gotoPath+"?xh="+xh;
				}
				var api = frameElement.api;
				
				if (api){
					if (api.get('childDialog')){
						api.reload(api.get('parentDialog') ,gotoPath);
					} else {
						var W = api.opener;
						W.location=gotoPath;			
					}
				} else if (parent.window){
					parent.window.document.location=gotoPath;						
				}
				
				iFClose();
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
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
