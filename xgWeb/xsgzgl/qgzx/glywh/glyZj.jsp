<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/qgzx/glywh/glyZj.js"></script>
		<script>
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});
		</script>
	</head>
	<body>

		<!-- ���� 
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>-->
		<html:form action="/qgzx_glygl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="zjbcGly();return false;" class="btn_zj">���</a>
						</li>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box" >
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				
				<!-- ��ʾ��Ϣ -->
				<%@ include file="/comm/other/tsxx.jsp"%>
				
				<div id="div_rs"
					style="width:100%;height:400px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				
				<!--��ҳ��ʾ-->
				<div style="margin-top:-55px">
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxGlyglForm"></jsp:include>
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			
		</html:form>
	</body>
</html>