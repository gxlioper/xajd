<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/pfzgl/js/pfzwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"查询结果",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"khglPfzgl.do?method=viewPfcy&type=query&pflx="+'${pflx}'+"&pfzid="+'${pfzid}'+"&khlx="+'${khlx}'+"&xh="+'${xh}'+"&zgh="+'${zgh}',
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'sfydf',name:'sfydf', index: 'sfydf',width:'5%',hidden:true},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'是否班干部',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'},
				   {label:'是否属于评分组',name:'sfsypfz', index: 'sfsypfz',width:'10%'}
						],
				sortname: "sfsypfz",
			 	sortorder: "desc",
			}
			var gridSetting2 = {
					caption:"查询结果",
					pager:"pager",
					multiselect:false,
					rowNum:10,
					url:"khglPfzgl.do?method=viewPfcy&type=query&pflx="+'${pflx}'+"&pfzid="+'${pfzid}'+"&khlx="+'${khlx}'+"&zgh="+'${zgh}'+"&xh="+'${xh}',
					colList:[
							   {label:'职工号',name:'zgh', index: 'zgh',width:'10%',key:true},
							   {label:'sfydf',name:'sfydf', index: 'sfydf',width:'5%',hidden:true},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'性别',name:'xb', index: 'xb',width:'5%'},
							   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'},
							   {label:'是否属于评分组',name:'sfsypfz', index: 'sfsypfz',width:'10%'}
							],
							sortname: "sfsypfz",
						 	sortorder: "desc",
				}
			
		jQuery(function(){
			var map = getSuperSearch();
			var gridSetting={};
			var pflx=${pflx};
			if("1"==pflx){
				gridSetting=gridSetting1;
				}
			else{
				gridSetting=gridSetting2;
			}
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id ="pflx" value="${pflx}"/>
			<input type="hidden" id ="khlx" value="${khlx}"/>
			<input type="hidden" id ="xh" value="${xh}"/>
			<input type="hidden" id ="zgh" value="${zgh}"/>	
			<div class="toolbox">
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
