<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"${path}&type=query&isAll=${isAll}",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'17%'},
                    {label:'书院',name:'symc', index: 'symc',width:'17%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'19%'},
				   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'18%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'18%'},
				   {label:'操作',name:'xh', index: '',width:'12%',noSort:true,formatter:dcmcLink}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function dcmcLink(cellValue, rowObject) {
				return "<button type=\"button\" onclick=\"show('"+cellValue+"');\" class=\"btn_01\">选择</button>";
			}
			function show(xh){
				var api = frameElement.api;
				var W = api.get('parentDialog');	
				W.showXsxxAjax(xh);		//该方法在对应selectStudent.jsp页面中定义，一般通用
				api.close();	
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
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
				<span> 学生信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
