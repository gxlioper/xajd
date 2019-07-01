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
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccrcsz.do?method=getCcrcList&type=query",
				colList : [ {
					label : 'key',
					name : 'ccid',
					index : 'ccid',
					key : true,
					hidden : true
				},  {
					label : '检查日期',
					name : 'jcrq',
					index : 'jcrq',
					width : '10%'
				}, {
					label : '检查学院（个）',
					name : 'jcxy',
					index : 'jcxy',
					width : '20%',
				}, {
					label : '检查寝室（个）',
					name : 'jcqs',
					index : 'jcqs',
					width : '20%',
				}, {
					label : '已提交检查数',
					name : 'ytjs',
					index : 'ytjs',
					width : '10%',
				},
				{
					label : '未提交检查数',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				},
				{
					label : 'xydm',
					name : 'xydm',
					index : 'xydm',
					hidden : true
				}],
				sortname : "jcrq",
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
		<html:form action="/gyjc_ccrcsz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addpage();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_sz" >分配抽查寝室</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
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
				<span>检查日程列表&nbsp;&nbsp;<font color="blue">用于卫生抽查</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
