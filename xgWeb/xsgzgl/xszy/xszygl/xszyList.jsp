<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xszygl/js/xszygl.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "新生之友结果列表",
				pager : "pager",
				url : "xszygl.do?method=getXszyList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'dwdm', name : 'dwdm', index : 'dwdm', hidden : true },
							{ label : 'ppqsdm', name : 'ppqsdm', index : 'ppqsdm', hidden : true },
							{ label : 'nj', name : 'nj', index : 'nj', hidden : true },
							{ label : '职工号', name : 'zgh', index : 'zgh', width : '8%', formatter:zghLink},
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '所属部门', name : 'bmmc', index : 'bmmc', width : '12%' },
							{ label : '职务职称', name : 'zwzc', index : 'zwzc', width : '5%' },
							{ label : '大类要求', name : 'dlyq', index : 'dlyq', width : '10%' },
							{ label : '备注', name : 'bz', index : 'bz', width : '10%'},
							{ label : '跨院系标记', name : 'kyxbj', index : 'kyxbj', width : '10%' ,formatter:kyxbjFormatter},
							{ label : '标记院系', name : 'bjyxmc', index : 'bjyxmc', width : '12%'},
							{ label : '匹配寝室', name : 'ppqs', index : 'ppqs', width : '12%', formatter:ppqsLink}]
					}
			var map = getSuperSearch();
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
		<html:form action="/xszygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="nj" styleId="nj" value="${nj}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >导入</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="kyxbj();return false;" class="btn_sz" >跨院系标记</a>
						</li>
						<logic:equal name="userStatus" value="xx">
						<li>
							<a href="javascript:void(0);" onclick="fpyx();return false;" class="btn_sz" >分配到院系</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; <font color="blue">${nj }级新生之友信息</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
