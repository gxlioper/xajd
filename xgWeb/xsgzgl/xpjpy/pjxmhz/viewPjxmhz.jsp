<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjxmhz/js/pjxmhz.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"评奖项目汇总列表",
				pager:"pager",
				url:"xpj_pjxmhz.do?method=viewPjxmhz&type=query",
				colList:[
				   {label:'学年',name:'xn', index: 'xn',width:'6%'},
				   {label:'项目类型',name:'xmlxmc', index: 'xmlxmc',width:'8%'},
				   {label:'项目性质',name:'xmxzmc', index: 'xmxzmc',width:'8%'},
				   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'16%'},
				   {label:'项目类型代码',name:'lxdm', index: 'lxdm',hidden:true},
				   {label:'项目性质代码',name:'xzdm', index: 'xzdm',hidden:true},
				   {label:'获奖人数',name:'hjrs', index: 'hjrs',width:'6%',formatter:rsLink}
				],
				sortname: "xn",
			 	sortorder: "desc"
			}			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span color="blue"> 评奖项目汇总列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>