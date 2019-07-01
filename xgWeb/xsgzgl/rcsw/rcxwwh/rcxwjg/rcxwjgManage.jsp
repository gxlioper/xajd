<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxwjg/js/rcxwjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		
		<script type="text/javascript">
	
		
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwjg"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
							<logic:equal value="12867" name="xxdm">
								<li><a href="#" onclick="importConfig();return false;" class="btn_dr">导入</a></li>	
							</logic:equal>
							<logic:notEqual value="12867"  name="xxdm">
								<li><a href="#" onclick="importData();return false;" class="btn_dr">导入</a></li>	
							</logic:notEqual>
						</logic:equal>
						<!-- 读写权 -->		
						<logic:notEqual value="10355" name="xxdm">		
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:notEqual>
						<logic:equal value="10355" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwjgExportData();return false;">导出</a></li>	
						</logic:equal>
						<logic:equal value="10351" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="xsPxsjDc();return false;">学生品行实践评定导出</a></li>
						</logic:equal>	
						<logic:equal value="13023" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwsjDc();return false;">素质测评分导出</a></li>
						</logic:equal>
						<logic:equal value="10071" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwtjbDc();return false;">日常行为统计表导出</a></li>
						</logic:equal>
						<logic:equal value="10868" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="sxpdcjhzDc();return false;">成绩汇总导出</a></li>
						</logic:equal>
						<logic:equal value="13431" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rcxwtjbDc();return false;">加分统计表导出</a></li>
						</logic:equal>
						
						<!-- 浙江警官职业学院，个性化打印德育考评通知单 -->
						<logic:equal name="xxdm" value="12869">
							<li><a href="javascript:void(0);" onclick="printDykptzd();return false;" class="btn_down">德育考评通知单</a></li>
							<li><a href="javascript:void(0);" onclick="printWjcld();return false;" class="btn_down">违纪处理单</a></li>
							<li><a href="javascript:void(0);" onclick="printJlspb();return false;" class="btn_down">奖励审批表</a></li>
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
				<logic:equal name="xxdm" value="13815">	
					<span>素质学分信息结果列表&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="13815">
					<logic:notEqual name="xxdm" value="13815">
						<span>日常行为信息结果列表&nbsp;&nbsp; </span>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="xxdm" value="13431">
					<span>加分信息结果列表&nbsp;&nbsp; </span>
				</logic:equal>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
