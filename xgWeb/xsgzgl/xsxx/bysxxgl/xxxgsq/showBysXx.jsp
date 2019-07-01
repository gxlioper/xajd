<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxgl/js/xxgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"xsxx_bysxx_xxxgsq.do?method=showBysXx&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'年级',name:'nj', index: 'nj',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'19%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'19%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'18%'},
				   {label:'学制',name:'xz', index: 'xz',width:'4%'},
				   {label:'操作',name:'xh', index: '',width:'9%',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectStudent('"+cell+"');\">选择</label>";
				   }}
				],
				sortname: "xh",
			 	sortorder: "asc",
			}
			jQuery("#dataTable").initGrid(gridSetting);
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function selectStudent(xh){

				var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSq";
				url += "&xh=" + xh;
				showDialog("毕业生信息修改申请", 850, 550, url);
				iFClose();
			}
		</script>
	</head>

	<body>
		
	
		<html:form method="post" styleId="form" action="/xsxx_bysxx_xxgl.do?method=showStudents&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
