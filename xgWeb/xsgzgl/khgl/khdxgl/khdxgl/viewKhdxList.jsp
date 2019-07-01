<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/khdxgl/js/khdxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		function initGridSetting(){
			var gridSetting = {};
			if("1"==jQuery("#khlx").val()){
			 gridSetting = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"khglKhdxgl.do?method=viewKhdxList&type=query&khlx=1&khdxid="+'${khdxid}'+"&sfnz="+'${sfnz}'+"&fpzt="+'${fpzt}'+"&pfzid="+'${pfzid}'+"&pflx="+'${pflx}',
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'年级',name:'nj', index: 'nj',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'是否班干部',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'}
						],
				sortname: "xh",
			 	sortorder: "asc",
			}
			}else{
			var gridSetting = {
					caption:"查询结果",
					pager:"pager",
					rowNum:10,
					url:"khglKhdxgl.do?method=viewKhdxList&type=query&khlx=2&khdxid="+'${khdxid}'+"&sfnz="+'${sfnz}'+"&fpzt="+'${fpzt}'+"&pfzid="+'${pfzid}'+"&pflx="+'${pflx}',
					colList:[
							   {label:'用户名',name:'yhm', index: 'yhm',width:'10%',key:true},
							   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
							   {label:'性别',name:'xb', index: 'xb',width:'5%'},
							   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'},
							   {label:'用户身份',name:'yhsf', index: 'yhsf',width:'10%'}
							],
					sortname: "yhm",
				 	sortorder: "asc",
				}
			}
			return gridSetting;
		}
		jQuery(function(){
			var gridSetting=initGridSetting();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
			
			jQuery("#btn_fh").bind("click",function(){
				if (parent.window){
					refershParent();
				}
			});
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglKhdxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id="khlx" value="${khlx}"/>
			<input type="hidden" id="pflx" value="${pflx}"/>
			<input type="hidden" id="khdxmc" value="${khdxmc}"/>
			<input type="hidden" id="sfnz" value="${sfnz}"/>
			<input type="hidden" id="fpzt" value="${fpzt}"/>
			
			   
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
