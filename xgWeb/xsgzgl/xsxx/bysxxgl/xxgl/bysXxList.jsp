<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxgl/js/xxgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
				    		 caption:"毕业生信息列表",
								pager:"pager",
								url:"xsxx_new_bysxx_xxgl.do?method=cxBysXxList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'学号',name:'xh', index: 'xh',width:'10%',key:true,formatter:dcmcLink},
								   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
								   {label:'性别',name:'xb', index: 'xb',width:'5%'},
								   {label:'年级',name:'nj', index: 'nj',width:'8%'},
								   {label:'毕业年度',name:'bynd', index: 'bynd',width:'8%'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
								   {label:'专业',name:'zymc', index: 'zymc',width:'12%'},
								   {label:'班级',name:'bjmc', index: 'bjmc',width:'12%'},
								   {label:'培养层次',name:'pyccmc', index: 'pyccmc',width:'5%'},
								   {label:'身份证号',name:'sfzh', index: 'sfzh',width:'15%'}
								],
								sortname: "xh",
							 	sortorder: "asc",
							}
						var map = getSuperSearch();
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
				});
					
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/xsxx_bysxx_xxgl.do?method=cxBysXxList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="bysXxAdd();return false;" class="btn_zj">添加准毕业生</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bysXxDel();return false;" class="btn_zj">取消准毕业生</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bysXxEdit();return false;" class="btn_xg">修改</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);"onclick="printByjdb('xsxx_bysxx_xxgl.do?method=printByjdb');return false;" class="btn_down">下载毕业生鉴定表</a></li>	
						<%-- 
						<li><a href="javascript:void(0);" onclick="printQjjgb('qjjg.do?method=printQjjgb');return false;" class="btn_down">下载结果表</a></li> 
						--%>  
						<%--
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>
				--%></ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 毕业生信息列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
