<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				url:"qjsq.do?method=selectQjkc&type=query&xh="+jQuery("#xh").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'课程编号',name:'kcbh', index: 'kcbh',width:'25%',key:true},
				   {label:'课程名称',name:'kcmc', index: 'kcmc',width:'25%'},
				   {label:'任课老师姓名',name:'rklsxm', index: 'rklsxm',width:'25%'},
				   {label:'任课老师联系方式',name:'rklslxfs', index: 'rklslxfs',width:'25%'}
				],
				sortname: "kcbh",
			 	sortorder: "desc"
			}
			jQuery("#dataTable").initGrid(gridSetting);

		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="qjsqForm" action="/qjsq.do?method=selectQjkc&type=query">
		<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="addQjkc();return false;" class="btn_zj">选择</a>
						</li>
				</ul>
			</div>
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
