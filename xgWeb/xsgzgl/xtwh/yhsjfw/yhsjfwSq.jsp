<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xtwh/yhsjfw/js/yhsjfwSq.js"></script>
		<style>
			.main_function{min-height : 200px;display: none;}
			.function_list02{height:470px;overflow:scroll;overflow-x:hidden;}
		</style>
	</head>
	<body>	
	
			<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="saveForm();" class="btn_ccg">保存</a></li>
				</ul>
			</div>
			</div>
	
	
		<html:form action="/xtwh_yhsjfw" method="post" styleId="form1">
			<input type="hidden" name="ids" id="ids" value="${ids}"/>
			<input type="hidden" name="sjfwdmSubmit" id="sjfwdmSubmit" value=""/>
			<input type="hidden" name="sjfwmcSubmit" id="sjfwmcSubmit" value=""/>

		<div class="main_function">
		  <div class="function_list01">
		    <h3><span>年级</span></h3>
		    <ul>
		    </ul>
		  </div>
		  <div class="function_list02">
		   </div>
		  <div class="function_list03"></div>
		</div>

		</html:form>
	</body>
</html>
