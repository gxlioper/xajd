<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xsdkqf/js/xsdkqf.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生贷款欠费列表",
				pager : "pager",
				url : "zxdk_xsdkqf.do?method=getXsdkqfList&type=query",
				colList : [
							{ label : '学号', name : 'xh', index : 'xh', width : '10%' },
							{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '学院', name : 'xymc', index : 'xydm', width : '15%' },
							{ label : '贷款名称', name : 'dkxm', index : 'dkxm', width : '25%' },
							{ label : '是否缴清', name : 'sfjq', index : 'sfjq', width : '10%' }],
					sortname : "xh",
				    sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		function importSydk(){
				toImportDataNew("ZXDK_SFJQDK");
				return false;
			}
		function fsznx(){
			var url = "xsxx_xsxxgl.do?method=ycsjTs";
			showConfirmDivLayer("您将同步异常数据", {
				"okFun" : function() {
					jQuery.post(url, {
					}, function(data) {
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
		}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
		<html:form action="/zxdk_xsdkqf">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:notEqual value="stu" name="userType">
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dr" onclick="importSydk();return false;">导入贷款信息</a></li>
							<logic:equal value="xx" name="usersf">
								<li><a href="#" class="btn_dr" onclick="fsznx();return false;">发送邮件</a></li>
							</logic:equal>
						</ul>
					</div>
				</logic:notEqual>
				
				<!--<logic:equal value="zf01" name="userName">
				 按钮 
				<div class="buttonbox">
					<ul>										
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a></li>						
					</ul>
				</div>
				</logic:equal>-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生欠费查询列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>
