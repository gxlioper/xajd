<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/zzxmhzView.js"></script>
	</head>

	<body>
		<html:form action="/xszz_zzxmjg" method="post" >
			<input type="hidden" id="lbdm" value="${model.lbdm}">
			<input type="hidden" id="xn" value="${model.xn}">
			<input type="hidden" id="xq" value="${model.xq}">
			<input type="hidden" id="xmmc" value="${model.xmmc}">
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
						</li>
						<li>
							<a href="#" onclick="reBack();return false;" class="btn_fh">返回</a>
						</li>
					</ul>
				</div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 资助结果列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
