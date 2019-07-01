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
				url:"xszz_knsrdbjpy.do?method=getStudentsByShqk&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'15%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
				   {label:'性别',name:'xb', index: 'xb',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
				   {label:'shsj',name:'shsj', index: 'shsj',hidden:true},
				   {label:'shyj',name:'shyj', index: 'shyj',hidden:true},
				   {label:'操作',name:'shr', index: 'shr',width:'10%',formatter:doShow,noSort:true}
				],
				params:{shzt:"${knsrdbjpyForm.shzt}",xtgwid:"${knsrdbjpyForm.xtgwid}"},
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function doShow(cellValue,rowObject){
				return "<a href='javascript:void(0);' title='审核人："+rowObject["shr"]+"\r\n审核时间："+rowObject["shsj"]+"\r\n审核意见："+rowObject["shyj"]+"' class='mouse-tsk'>详细</a>";
				
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				map["shzt"] = jQuery("#shzt").val();
				map["xtgwid"] = jQuery("#xtgwid").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
	
		<html:form action="/xszz_knsrdbjpy">
			<html:hidden property="xtgwid" styleId="xtgwid" />
			<html:hidden property="shzt" styleId="shzt" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
