<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxplsq.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				
				var dclGrid = getDclGird();
				var map = getSuperSearch();
					map["shzt"] = "0";
					dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
			});

		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="shzt" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
<%--							<logic:equal value="true" name="cssz" property="kgzt">--%>
								<li id="li_sh">
									<a href="javascript:void(0);" onclick="viewJxsq();return false;" 
									   title="选中您要上报的记录，点击该按钮可以打开上报页面。"
									   class="btn_sh">上报</a>
								</li>
<%--							</logic:equal>--%>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		
		</html:form>
		
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>奖项上报列表</span>
			</h3>
			
			<table id="dataTable" style="width:100%;"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
