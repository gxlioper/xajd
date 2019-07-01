<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsh/js/xmsh.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
	</head>
	<body onunload="setJxshCookie();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/xpjpy_xmsh" method="post" styleId="xmshForm">
		<input type="hidden" id="shzt" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal value="true" name="cssz" property="kgzt">
								<li id="li_sh">
									<a href="javascript:void(0);" onclick="xmshPlsh();return false;" class="btn_sh">批量审核</a>
								</li>
								<li id="li_qx" style="display: none;">
									<a href="javascript:void(0);" onclick="xmshRevoke();return false;" class="btn_qxsh">撤消</a>
								</li>	
							</logic:equal>
								<li>
									<a href="javascript:void(0);" onclick="xmshTrack();return false;" class="btn_cs">流程跟踪</a>
								</li>
<%--								<li>--%>
<%--									<a href="javascript:void(0);" onclick="xmshStatistics();return false;" class="btn_tj">奖项审核统计</a>--%>
<%--								</li>--%>
						</logic:equal>
						
							<li>
								<a href="#" class="btn_dc" onclick="xmshExport();return false;">导出</a>
							</li>
						<li>
							<a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">下载登记表</a>
						</li>
					</ul>
				</div>
			</div>
		
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%" id="tabUl">
			<li class="ha" clzt="dcl"><a href="javascript:void(0);"  onclick="chageShzt(this,'0');"><span>待处理</span></a></li>
			<li clzt="ycl"><a href="javascript:void(0);"  onclick="chageShzt(this,'1');"><span>已处理</span></a></li>
	      </ul>
	    </div>
	    
	    <!-- 过滤条件 -->	
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- 过滤条件 end--> 
		
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span color="blue">奖项、荣誉审核列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
			<table id="dataTable"></table>
			<div id="pager"></div>
			</div>
		</div>
	</body>
</html>