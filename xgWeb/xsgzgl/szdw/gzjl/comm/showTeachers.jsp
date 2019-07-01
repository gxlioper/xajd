<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD zghTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/zghtml1/DTD/zghtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/zghtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"教师信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"gzjljg.do?method=showTeachers&type=query",
				colList:[
				   {label:'职工号',name:'zgh', index: 'zgh',key:true},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
				   {label:'操作',name:'zgh', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectTeacher('"+cell+"');\">选择</label>";
				   }}
				],
				sortname: "zgh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectTeacher(zgh){
				var gotoPath = jQuery("#gotoPath").val();

				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&zgh="+zgh;
				} else {
					gotoPath = gotoPath+"?zgh="+zgh;
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
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 教师信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
