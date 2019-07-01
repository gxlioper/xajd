<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />
        <base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		    function savehf(){
		       
		        var hf = document.getElementById("hf").value;
		        if(hf==""){
		         alert("回复内容不能为空！");
		         return false;
		        }
		       
		       
		        document.forms[0].action = "dwhf.do?method=dwhf&doType=save&act=change&jytype=jyweb";
	            document.forms[0].submit();
		    
		    }
		
		
		   function mrhfchange(){
		       document.forms[0].action = "dwhf.do?method=dwhf&act=change&jytype=jyweb";
	           document.forms[0].submit();  
		   }
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
	<html:form action="/dwhf" method="post">
		<html:hidden name="rs" property="yhm" />
		<div class="kuang" align="center">
			<fieldset>
				<legend>
					单位回复
				</legend>
				<table width="97%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="15%" align="right">
							姓名：
						</td>
						<td width="35%">
							<html:text name="rs" property="xm" readonly="true"/>
						</td>
											<td width="15%" align="right">
							学号：
						</td>
						<td width="35%">
							<html:text name="rs" property="xh" readonly="true"/>
						</td>
					</tr>
					<tr height="25">
						<td colspan="4">
							回复:
							<html:select name="rs" property="bt" styleId="bt"
										style="width:150px" onchange="mrhfchange();" >
										<html:option value="请选择默认回复"></html:option>
										<html:options collection="mrhfList" property="bt"
											labelProperty="bt" />
							</html:select>
							<font color="red">注意(回复内容不得超过500字)</font>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<html:textarea name="rs" property="hf" rows="10"
								style="width:100%" />
						</td>
					</tr>
				</table>
				<div>
					<button onclick="savehf();return false;">
						提交
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="reset">
						重置
					</button>
					&nbsp;&nbsp;&nbsp;
					<button onclick="Close();return false;">
						关闭
					</button>
				</div>
			</fieldset>
		</div>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("提交成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("提交失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
