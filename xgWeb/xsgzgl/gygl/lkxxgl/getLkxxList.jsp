<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/lkxxgl/js/lkxx.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "住宿登记列表",
				pager : "pager",
				url : "xgygl_lkxxdj.do?method=getLkxxList&type=query",
				colList : [
							{ label : 'id', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '住宿人姓名', name : 'xm', index : 'xm', width : '8%',formatter:xhLink },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '身份证号', name : 'sfzh', index : 'sfzh', width : '22%' },
							{ label : '入住楼栋', name : 'rzld', index : 'rzld', width : '15%' },
							{ label : '入住房间', name : 'rzfj', index : 'rzfj', width : '10%' },
							{ label : '押金（元）', name : 'rzyj', index : 'rzyj', width : '10%' },
							{ label : '入住时间', name : 'rzsj', index : 'rzsj', width : '15%' },
							{ label : '退宿时间', name : 'tssj', index : 'tssj', width : '15%' },
						  ],
					sortname : "rzsj",
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
		<html:form action="/xgygl_lkxxdj">
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >登记</a>
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
							<li>
								<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
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
				<span>住宿登记列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
