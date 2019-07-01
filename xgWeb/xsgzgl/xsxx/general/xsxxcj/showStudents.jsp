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
				url:"xsxx_gygl_xsxxcj.do?method=showStudents&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'15%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'}
				],
				dblclick:function(rowObject){
					var gotoPath = jQuery("#gotoPath").val();

					if (gotoPath.split("?").length > 1){
						gotoPath = gotoPath+"&xh="+rowObject["xh"];
					} else {
						gotoPath = gotoPath+"?xh="+rowObject["xh"];
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
				},
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

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
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б�
					<font color="red">��˫��һ����¼ѡ��ѧ��</font>
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
