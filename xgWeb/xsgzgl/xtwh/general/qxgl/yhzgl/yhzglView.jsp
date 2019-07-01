<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie,"checkbox":"no"}

			parameter["str_zdm"]=jQuery("#zdm").val();
					
			jQuery("#divWaiting").css("display","");
			jQuery("#divDisable").css("display","");
			
			searchGo(url,parameter);

			jQuery("#divWaiting").css("display","none");
			jQuery("#divDisable").css("display","none");
			
			jQuery.ajaxSetup({async:true});
			
		}
		</script>
	</head>
	<body onload="searchRs()">
		<html:form action="/xtwh_qxgl_yhgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zdm" name="zdm" value="${zdm}"/>
			<input type="hidden" id="search_go" onclick="searchRs();return false;"/>
			<!-- ������ -->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<div id="div_rs"
					>
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglNewForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
