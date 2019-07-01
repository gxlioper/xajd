<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxjkhk/js/jkhk.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "还款结果列表",
				pager : "pager",
				url : "zxdk_jkhk.do?method=getXnwxjkhkList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '15%', formatter : xhLink},
							{ label : '姓名', name : 'xm', index : 'xm', width : '15%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '20%' },							
							{ label : '还款状态', name : 'hkzt', index : 'hkzt', width : '15%'},			
							{ label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
							{ label : '还款时间', name : 'hksj', index : 'hksj', width : '20%' }],
					sortname : "hksj",
				    sortorder : "desc" }
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
		<html:form action="/zxdk_jkhk">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
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
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
						</li>
						</logic:equal>
						
						<logic:equal value="zf01" name="userName">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
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
				<span>项目申报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
