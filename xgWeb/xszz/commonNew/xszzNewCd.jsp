<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
        function yz(){
       		var temp = document.getElementById("gnmk").value;
       		var cdlb = document.getElementById("cdlb").value;
       		if (cdlb == "sq"){
       			if((temp == null) || (temp=="")){
       				alert("����ѡ��Ҫ�������Ŀ!");
       				return false;
       			}
				document.forms[0].action = "/xgxt/new_common_xszz.do?method=xszzNewCd&cdlb=sq&doType=query";
			} else if (cdlb == "sh"){
				if((temp == null) || (temp=="")){
       				alert("����ѡ��Ҫ��˵���Ŀ!");
       				return false;
       			}
				document.forms[0].action = "/xgxt/new_common_xszz.do?method=xszzNewCd&cdlb=sh&doType=query";
			}
			
		 	document.forms[0].submit();
	}
    </script>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>


	<body>
		<form action="" method="post" name="from">
			<table width="100%" class="tbstyle">
				<thead>
					<tr valign="middle" >
						<td align="center">
							<logic:equal name="cdlb" value="sq">
							<b style="font-size:14">��ѡ��Ҫ�������Ŀ</b>
							</logic:equal>
							<logic:equal name="cdlb" value="sh">
							<b style="font-size:14">��ѡ��Ҫ��˵���Ŀ</b>
							</logic:equal>
							<input type="hidden" id="cdlb" name="cdlb"
								value="<bean:write name="cdlb"/>" />
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center">
						<html:select name="rs" property="gnmk" styleId="gnmk">
								<html:option value="">---------��ѡ��--------</html:option>
								<html:options collection="list" property="gnmklj"
									labelProperty="gnmkmc" />
							</html:select>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input  class="button2" onclick=yz()
							style="width:80px" value="ȷ  ��" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
