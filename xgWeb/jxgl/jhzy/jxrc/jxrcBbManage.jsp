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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
<script type="text/javascript">
 function xxrcb() {
     var xn = $("xn").value;
     var xq = $("xq").value; 
     var nd = $("nd").value;    
     if(xn==""){ 
        alert("��ѡ��ѧ�꣡");
        return false;
     }
     if(xq==""){ 
        alert("��ѡ��ѧ�ڣ�");
        return false;
     }
     if(nd==""){ 
        alert("��ѡ����ȣ�");
        return false;
     } 
    document.forms[0].action = "/xgxt/jxgl_jhzy.do?method=xxrcb";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";              
 }
 function yxrcb(){
     var xydm = $("xydm").value;
     var xn = $("xn").value;
     var xq = $("xq").value; 
     var nd = $("nd").value;    
     if(xydm==""){ 
        alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" />��");
        return false;
     }
     if(xn==""){ 
        alert("��ѡ��ѧ�꣡");
        return false;
     }
     if(xq==""){ 
        alert("��ѡ��ѧ�ڣ�");
        return false;
     }
     if(nd==""){ 
        alert("��ѡ����ȣ�");
        return false;
     } 
    document.forms[0].action = "/xgxt/jxgl_jhzy.do?method=yxrcb";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
 }
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jxgl_jhzy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��ѵ�ճ� - �ճ̱�����
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
								    <bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;��ȣ�
									<html:select property="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;ѧ�꣺
									<html:select property="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;ѧ�ڣ�
									<html:select property="xq">
									    <html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>									
								</td>								
							</tr>
						</thead>
					</table>
				</fieldset>				
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>					
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">							
							<button type="button" class="button2" onclick="xxrcb() "
								style="width:80px">
								У�ճ̱�
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="yxrcb() "
								style="width:80px">
								<bean:message key="lable.xsgzyxpzxy" />�ճ̱�
							</button>																																								
						</div>						
					</center>
				<div id="tmpdiv"></div>
				<script type="text/javascript" src="js/bottomButton.js"></script>
			</div>
		</html:form>	
	</body>
</html>
