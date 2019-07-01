<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcrc/js/jcrc.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_jcrc.do?method=getJcrcList&type=query",
				colList : [ {
					label : 'key',
					name : 'guid',
					index : 'guid',
					key : true,
					hidden : true
				}, {
					label : '角色',
					name : 'js',
					index : 'js',
					width : '10%'
				}, {
					label : '检查日期',
					name : 'ccrq',
					index : 'ccrq',
					width : '10%'
				}, {
					label : '截止日期',
					name : 'jzrq',
					index : 'jzrq',
					width : '10%'
				}, {
					label : '检查项',
					name : 'wsjc',
					index : 'wsjc',
					width : '20%',
					formatter:jcxLink
				}, {
					label : '已提交检查数',
					name : 'ytjs',
					index : 'ytjs',
					width : '10%',
					formatter:tjLink
				},
				{
					label : '未提交检查数',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
					formatter:wtjLink
				},
				{
					label : 'ls',
					name : 'ls',
					index : 'ls',
					hidden : true
				},
				{
					label : 'xydm',
					name : 'xydm',
					index : 'xydm',
					hidden : true
				},
				{
					label : 'aqjc',
					name : 'aqjc',
					index : 'aqjc',
					hidden : true
				},
				{
					label : 'jljc',
					name : 'jljc',
					index : 'jljc',
					hidden : true
				}],
				sortname : "xn,xq,jzrq",
				sortorder : "desc",
				radioselect:true
			}
			var map = getSuperSearch();
			map["xydm"]=jQuery("#xydm").val();
			map["js"]=jQuery("#js").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gyjc_jcrc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			<html:hidden property="xydm" styleId="xydm" value="${xydm}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_sz" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sz" >删除</a>
						</li>
						</logic:equal>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>检查日程列表&nbsp;&nbsp;<font color="blue">用于卫生检查，安全检查，纪律检查</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
