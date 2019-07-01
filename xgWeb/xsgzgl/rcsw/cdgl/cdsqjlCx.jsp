<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
			<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdsq.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "场地申请情况列表",
		pager : "pager",
		url : "rcsw_cdgl_cdsq.do?method=cdsqjlCx&type=query",
		colList : [
				{ label : '申请场地', name : 'cdmc', index : 'cdmc', width : '12%' },
				{ label : '申请使用时间段', name : 'sqsjd', index : 'sqsjd', width : '28%' }]
		}

	jQuery(function() {
		var map = {};
		var sqsjdkssj = jQuery("#sqsjdkssj").val();
		map["sqsjdkssj"] = jQuery.trim(sqsjdkssj);
		gridSetting["params"]=map;
		jQuery("#dataTable").initGrid(gridSetting);

	});

	function query(){
			var map = {};
			var cdmc = jQuery("#cdmc").val();
			var sqsjdkssj = jQuery("#sqsjdkssj").val();
			var sqsjdjssj = jQuery("#sqsjdjssj").val();
			map["cdmc"] = jQuery.trim(cdmc);
			map["sqsjdkssj"] = jQuery.trim(sqsjdkssj);
			map["sqsjdjssj"] = jQuery.trim(sqsjdjssj);			
			jQuery("#dataTable").reloadGrid(map);
		}
	
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_cdgl_cdxxwh">

			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<div class="searchtab">
					<!-- 模糊查询 -->
					<div class="adv_filter">
					<table border="0" width="100%">
					<tr>
						<th style="text-align:right;">申请场地</th>
						<td style="padding-left:25px;">
							<input type="text" id="cdmc" name="cdmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}"/>
						</td>
						<th style="text-align:right;">申请使用时间段</th>
						<td>
								<input type="text" name="sqsjdkssj"
											id="sqsjdkssj" value="${rs.sqsjdkssj }" size="10" onclick="return showCalendar('sqsjdkssj','yyyy-MM-dd',true,'sqsjdkssj');" readonly="true" />
										-
								<input type="text" name="sqsjdjssj"
											id="sqsjdjssj" size="10" onclick="return showCalendar('sqsjdjssj','yyyy-MM-dd',false,'sqsjdjssj');" readonly="true"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
				</div>
			</div>
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>场地申请情况列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
