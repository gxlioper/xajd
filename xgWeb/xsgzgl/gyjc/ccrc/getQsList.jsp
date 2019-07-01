<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccrc/js/ccrc.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var ccid = jQuery("#ccid").val();
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccrcsz.do?method=fenpei&type=query&ccid="+ccid,
				colList : [ {
					label : 'key1',
					name : 'lddm',
					index : 'lddm',
					key : true,
					hidden : true
				}, {
					label : 'key2',
					name : 'qsh',
					index : 'qsh',
					key : true,
					hidden : true
				}, {
					label : '楼幢',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '层',
					name : 'ch',
					index : 'ch',
					width : '20%',
				}, {
					label : '寝室号',
					name : 'qsh',
					index : 'qsh',
					width : '10%',
				}, {
					label : '所属学院',
					name : 'bmmc',
					index : 'jcxy',
					width : '20%',
				}, {
					label : '本月已查次数',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				}, {
					label : '本学期已查次数',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				}
				],
				sortname : "lddm",
				sortorder : "desc"
				
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
		<html:form action="/gyjc_ccrcsz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			<input  type="hidden" id="ccid" value="${ccid }"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						
						
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="fpccr();return false;">分配抽查人</a>
						</li>
						
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>寝室列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
