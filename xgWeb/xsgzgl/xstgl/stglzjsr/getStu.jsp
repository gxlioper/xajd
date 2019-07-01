<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"查询结果",
					pager:"pager",
					rowNum:10,
					url:"stgl_zjsr.do?method=getStuList",
					params:getSuperSearch(),
					colList:[
					   {label:'学号',name:'xh', index: 'xh',width:'12%',key:true},
					   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					   {label:'性别',name:'xb', index: 'xb',width:'5%'},
					   {label:'年级',name:'nj', index: 'nj',width:'8%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
					   {label:'学制',name:'xz', index: 'xz',width:'7%'}
					],
					sortname: "xh",
				 	sortorder: "desc",
				 	radioselect:true
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function sqxsZj() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("请选择一个学生！");
					return false;
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				<logic:equal value="selsz" name="type">
				//选择社长
				parentsW.jQuery("#szxm").val(rows[0]['xm']);
				parentsW.jQuery("#sz").val(rows[0]['xh']);
				</logic:equal>
				<logic:equal value="selcwfzr" name="type">
				//选择社团负责人
				parentsW.jQuery("#cwfzrxm").val(rows[0]['xm']);
				parentsW.jQuery("#cwfzr").val(rows[0]['xh']);
				</logic:equal>
				closeDialog();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/stglStsq">
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="sqxsZj();return false;" class="btn_zj">添加</a>
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
