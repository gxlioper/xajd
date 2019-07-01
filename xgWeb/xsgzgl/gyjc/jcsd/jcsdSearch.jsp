<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcsd/js/jcsd.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_jcsd.do?method=jcsdList&type=query",
				colList : [ {
					label : 'key',
					name : 'xydm',
					index : 'xydm',
					key : true,
					hidden : true
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xymc',
					width : '10%'
					//formatter : xhLink
				}, {
					label : '楼栋情况',
					name : 'ldnum',
					index : 'ldnum',
					width : '20%',
					formatter : ldLink
				}, {
					label : '卫生检查人',
					name : 'wsjcr',
					index : 'wsjcr',
					width : '10%'
				}, {
					label : '安全检查人',
					name : 'aqjcr',
					index : 'aqjcr',
					width : '10%'
				}, {
					label : '纪律检查人',
					name : 'jljcr',
					index : 'jljcr',
					width : '10%'
				},
				<logic:equal name="userType" value="xx">
				{
					label : '抽查人',
					name : 'ccr',
					index : 'ccr',
					width : '15%'
				},
				</logic:equal>
				{
					label : '评分标准',
					name : 'pfnum',
					index : 'pfnum',
					width : '17%',
					formatter:pfLink
				},
				{
					label : 'chnum',
					name : 'chnum',
					index : 'chnum',
					hidden : true
				},
				{
					label : 'qshnum',
					name : 'qshnum',
					index : 'qshnum',
					hidden : true
				}],
				sortname : "xydm",
				sortorder : "asc",
				radioselect:true
			}
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
		<html:form action="/gyjc_jcsd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="fpjcr('1');return false;"  >分配卫生检查人</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="fpjcr('2');return false;" class="btn_sz" >分配安全检查人</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="fpjcr('3');return false" class="btn_sz" >分配纪律检查人</a>
						</li>
						<logic:equal name="userType" value="xx">
							<li><a href="#" class="btn_sz" onclick="fpjcr('0');return false;">分配抽查人</a></li>	
							
						</logic:equal>
						<li><a href="#" class="btn_sz" onclick="pfbzwh();return false;">评分标准维护</a></li>	
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
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
