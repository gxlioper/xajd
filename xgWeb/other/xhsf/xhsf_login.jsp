<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>西华师范大学学生工作网</title>
		<meta http-equiv="Content-Language" content="UTF-8" />
		<meta content="all" name="robots" />
		<meta name="author" content="shenjian1984@yeah.net" />
		<meta name="Copyright" content="" />
		<meta name="description" content="" />
		<link href="/xgxt/other/xhsf/style/default.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/xgxt/js/function.js"></script>
  	</head>
  <body>
  <html:form action="/xhsfLogin">
   	<div class="body_main">
        <div class="mainframe">
        <div class="mainframe_01">
        <div class="mainframe_01_01">
        	<!--控件-->
			<div class="SystemLogin">
              <h3><span>Title</span></h3>
              <div class="login">
                <div class="user">
                  <label for=""> 用户名： </label>
                  <input class="text_nor" name="userName" id="userName" maxlength="20"/>
                </div>
                <div class="passw">
                  <label for=""> 密　码： </label>
                  <input type="password" class="text_nor" name="password" id="password" maxlength="20"/>
                </div>
<%--                <div class="yzm">--%>
<%--                  <label for=""> 验证码： </label>--%>
                  <input name="yzm" id="yzm_t" maxlength="4" onkeypress="if(event.keyCode==13)dl();" value="1234" type="hidden"/>
                  <input name="dllx" id="dllx" value="noyzm" type="hidden"/>
<%--                  <img src="/xgxt/yzm.jsp" border="0" align="absmiddle" width="50px" height="19px"/>  --%>
<%--                </div>--%>
                <div class="">
					<label>
						<input type="radio" name="userType" value="teacher" checked /> 
						教师
					</label>
					<label>
						<input type="radio" name="userType" value="student"/> 
						学生
					</label>
					<input type="hidden" id="userType_niub" value="${userType }"/>
				</div>
                <div class="btn">
                  <button class="btn_dl" id="btn_dl" onclick="dl()" ><span>登 录</span></button>
                  <button class="btn_cz" type="reset"><span>重 置</span></button>
                </div>
              </div>
            </div>
        </div>
	   </div>
	  </div>
	</div>
	 <logic:notEmpty name="commanForm" property="errMsg" scope="request"> <br /> 
    	<script> 
        	alert("<bean:write name="commanForm" property="errMsg" scope="request" />");
        </script> 
    </logic:notEmpty> 
    <logic:present name="message">
    	<logic:equal value="ok" name="message">
    		<script>
    			var userType = $("userType_niub").value;
    			if(userType == "stu"){
    				top.location="/xgxt/stuPage.jsp";
    			}else{
    				top.location="/xgxt/teaPage.jsp";
    			}	
    		</script>
    	</logic:equal>
    </logic:present>
   </html:form>
  </body>
</html>
