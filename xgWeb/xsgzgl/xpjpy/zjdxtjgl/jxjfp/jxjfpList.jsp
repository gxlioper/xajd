<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxtjgl/jxjfp/js/jxjfp.js"></script>
	</head>

	<body style="min-height: 800px;">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		
		<logic:present name="pjxmList">
			<div id="pjxmDiv">
				<logic:iterate id="p" name="pjxmList">
					<font style="display:none;" xmdm="${p.xmdm}" xmmc="${p.xmmc}" name="pjxm"></font>
				</logic:iterate>
			</div>
		</logic:present>
		
		<html:form action="/xpjpy_jxjfp">
			<input type="hidden" id="notFirst" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<li><a href="#" class="btn_dc" onclick="jxjfpExport(); return false;">导出</a></li>
							<li><a href="#" class="btn_dc" onclick="ffhzExport(); return false;">发放汇总导出</a></li>
							<li><a href="#" class="btn_dc" onclick="gjjxjhzExport(); return false;">国家奖学金汇总导出</a></li>
						</li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>奖学金名额分配一览表 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
