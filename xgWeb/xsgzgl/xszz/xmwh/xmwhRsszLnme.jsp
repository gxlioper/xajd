<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhRsszLnme.js"></script>
	</head>
	<body>
		<html:form action="/xszz_xmwh_rssz" method="post" >
			<html:hidden property="xmdm" styleId="xmdm" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			
			<div class="searchtab">
				<div id="checkboxDiv"></div>
			</div>			
			<div class="formbox">
					<table id="dataTable" class="dateline" style="width:100%">
						<thead></thead>
						<tbody></tbody>
					</table>
				</div>
		</html:form>
	</body>
</html>

