<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	 	
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type='text/javascript'>
			jQuery(function() {
				var gridSetting = {
					caption : "学生列表",
					pager : "pager",
					url : "szdw_zdgzxsk.do?method=zdgzxskManage&doType=query",
					colList : [ {
						label : 'id',
						name : 'id',
						index : 'id',
						hidden : true,
						key : true
					}, {
						label : '学号',
						name : 'xh',
						index : 'xh',
						formatter : xhLink
					}, {
						label : '姓名',
						name : 'xsxm',
						index : 'xsxm'
					}, {
						label : '性别',
						name : 'xsxb',
						index : 'xsxb'
					}, {
						label : '年级',
						name : 'nj',
						index : 'nj'
					}, {
						label : jQuery("#xbmc").val(),
						name : 'xymc',
						index : 'xymc'
					}, {
						label : '班级',
						name : 'bjmc',
						index : 'bjmc'
					}, {
						label : '关注等级',
						name : 'gzdj',
						index : 'gzdj'
					}, {
						label : '谈话时间',
						name : 'thsj',
						index : 'thsj',
						formatter : getNewDate
					}, {
						label : '谈话教师',
						name : 'jsxm',
						index : 'jsxm'
					} ],
					sortname : "",
					sortorder : ""
				};
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs() {
				var map = getSuperSearch();
	
				jQuery("#dataTable").reloadGrid(map);
			}
	
			function getNewDate(cellValue) {
				var newDate = cellValue.substring(0, 10);
				return newDate;
			}

			function xhLink(cellValue, rowObject) {
				return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"xhLinkViewZdgzxsk('"+rowObject["id"]+"');\">" + cellValue + "</a>";
			}
	
			function xhLinkViewZdgzxsk(id) {
				showDialog("查看谈话记录", 700, 505,
						"szdw_thjl.do?method=thjlDetail&doType=view&id=" + id);
			}
			function viewZdgzxsk() {
				var id = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length != 1) {
					showAlertDivLayer("请选择一条您要查看的记录！");
					return false;
				} else {
					id = rowsValue[0]["id"];
				}
				showDialog("查看谈话记录", 700, 505,
						"szdw_thjl.do?method=thjlDetail&doType=view&id=" + id);
			}
	
			function exportZdgzxsk() {
				customExport("szdw_thjl_thjl.do", exportZdgzxskData, 700, 500);
			}
	
			// 导出方法
			function exportZdgzxskData() {
				setSearchTj();// 设置高级查询条件
				var url = "szdw_zdgzxsk.do?method=exportZdgzxskData&dcclbh="
						+ "szdw_thjl_thjl.do";// dcclbh,导出功能编号
				url = addSuperSearchParams(url);// 设置高级查询参数
				jQuery("#form").eq(0).attr("action", url);
				jQuery("#form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_zdgzxsk" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="viewZdgzxsk();return false;" class="btn_ck">查看</a>
						</li>
						<logic:notEqual name="userType" value="stu">
							<logic:equal value="yes" name="writeAble">
								<li>
									<a href="#" onclick="exportZdgzxsk();return false;" class="btn_dc">导出</a>
								</li>
							</logic:equal>
						</logic:notEqual>
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
				<span> 谈话记录信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
