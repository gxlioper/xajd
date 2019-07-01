<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/dkbc/jcjy/js/jcjy.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"基层就业结果列表",
				pager:"pager",
				url:"jcjy_bcdc.do?method=bcdcList&type=query",
				colList:[
					{ label : 'key', name : 'juid', index : 'juid',key : true, hidden : true },
					{ label : '学号', name : 'xh', index : 'xh', width : '8%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:jcjyView('"+rowObject["juid"]+"');\" class='name'>"+cell+"</a>";
					   }},
					{ label : '姓名', name : 'xm', index : 'xm', width : '8%'},
					{ label : '性别', name : 'xb', index : 'xb', width : '4%'},
					{ label : '学院', name : 'xymc', index : 'xydm', width : '10%'},
					{ label : '专业', name : 'zymc', index : 'zydm', width : '10%'},
					{ label : '联系电话', name : 'sjhm', index : 'sjhm', width : '8%'},
					{ label : '毕业时间', name : 'bysj', index : 'bysj', width : '8%'}
				],
				sortname : "xh",
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
		<html:form action="/jcjy_bcdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="update();return false;" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_sc" onclick="del();return false;" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dr" onclick="importJcjy();return false;" >导入</a>
						</li>
						</logic:equal>
						<logic:equal value="10335" name="xxdm">
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a>
								</li>	
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a>
							</li>
						</logic:notEqual>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>基层就业信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>