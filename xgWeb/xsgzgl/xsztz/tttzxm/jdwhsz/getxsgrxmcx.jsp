<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jdwhsz/js/jdwhsz.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "个人项目查询列表",
				pager : "pager",
				url : "grttxm_jdsz.do?method=getXsGrxmCx&type=query",
				colList : [ {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xssqLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '年级',
					name : 'nj',
					index : 'nj',
					width : '5%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '15%'
				},{
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%'
				},{
					label : '项目阶段',
					name : 'jdmc',
					index : 'jdmc',
					width : '15%'
				},{
					label : '阶段分',
					name : 'jbf',
					index : 'jbf',
					width : '5%'
				},{
					label : 'xsjgid',
					name : 'xsjgid',
					index : 'xsjgid',
					hidden : true
				}],
				sortname : "jdsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfigGr();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>个人项目查询列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
