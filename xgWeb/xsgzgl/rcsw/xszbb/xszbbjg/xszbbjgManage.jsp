<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/xszbbjg/js/xszbbjgManage.js"></script>
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript">
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_xszbb_bbjggl">
		
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
					<logic:equal name="writeAble" value="yes">
						<!-- 按钮 -->
						<div class="buttonbox">
							<ul>
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
								<li>
									<a href="javascript:void(0);" onclick="lingq();return false;" class="btn_down"
									>领取</a>
								</li>				
								<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
								<logic:equal name = "xxdm" value="11400">
									<li>
										<a href="javascript:void(0);" onclick="dyXszXaph('xsz_${xxdm }');return false;" class="btn_dy"
									>学生证打印</a>
									</li>
								</logic:equal>	
								<logic:equal name = "xxdm" value="13431">
									<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">在校证明导出</a></li>	
								</logic:equal>	
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>证件补办结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
