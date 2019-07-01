<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
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
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zdm" name="zdm" value="${zdm}"/>
			<input type="hidden" id="search_go" onclick="searchRs();return false;"/>
			<!-- 隐藏域 -->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<div id="div_rs"
					>
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglNewForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
