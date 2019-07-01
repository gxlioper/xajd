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
		<script type="text/javascript">
		 function sqPrint(){
        window.open('sjxyDektqh.do?method=printFkDektqh&pkValue=${pkValue}');
        }	
		</script>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
        <meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>	
	<script language="javascript">
	function dosubmit()
	{
		document.forms[0].submit();
	}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>		
		  <html:form action="/sjxyDektqh.do?method=shOneDektqh&doType=modi" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/sjxyDektqh.do?method=shOneDektqh&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<fieldset>
			<div class="title">
			<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ���� - ���Ź��� - �ڶ����û�󻮷���
			</div>
			</div>

			<logic:present name="result">
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>�ڶ����û�󻮷���</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 15%">
						<font color="red">*</font>������ˣ�
					</td>
					<td align="left" width="35%">
							<bean:write name="rs" property="hdfzr" />
					</td>
					<td align="right" width="18%">
						����ţ�
					</td>
					<td align="left" width="35%">
						<bean:write  name="rs" property="save_ssh"/>
					</td>
				</tr>
				<tr style="height:22px">
					
					<td align="right" style="width: 15%">
						�ٰ첿�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="jbbm"/>
						<html:hidden property="save_jbbm" value="${rs.jbbm}"/>
					</td>
					<td align="right">
						����ƣ�
					</td>
					<td align="left">
						<bean:write  name="rs" property="hdmc" />
						<html:hidden property="save_hdmc" value="${rs.hdmc}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						ָ����ʦ��
					</td>
					
					<td align="left">
						<bean:write name="rs" property="zdls"/>
					</td>
					<td align="right">
						����α���
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_yqjb"/>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ٰ�ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="jbsj"/>
						<html:hidden property="save_jbsj" value="${rs.jbsj}"/>
					</td>
					<td align="right">
						<font color="red">*</font>��ϵ��ʽ��
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_lxfs"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ص㣺
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_cjdxrs" />
					</td>
					<td align="right">
						<font color="red">*</font>�μӶ���������
					</td>
					<td align="left">
						<bean:write  name="rs" property="save_cjdxrs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�ֹ���ʦ��ˣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="save_xxsh"/>
					</td>
					<td align="right">
							ʵ�ʳ�ϯ������
					</td>
					<td align="left">
							<bean:write name="rs" property="sjcxrs" />
					</td>
				</tr>
				<tr>
					<td align="right">
							�Ч����
					</td>
					<td align="left">
							<bean:write name="rs" property="hdxg" />
					</td>
					<td align="right">
							�Ƿ���ͼƬ��
					</td>
					<td>
						<bean:write  name="rs" property="save_sfytp" />
					</td>
				</tr>
				<tr>	
					<td align="right">
						�Ƿ���ͨѶ�壺
					</td>
					<td>
						<bean:write name="rs" property="save_sfytxg"/>
					</td>	
					<td align="right">
						�Ƿ�����
					</td>
					<td>
						<bean:write name="rs" property="save_sfbx"/>
					</td>	
				</tr>
				<tr style="height:22px">
					<td align="right">
						���Ŀ�ĺ����壺
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdmdyy" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������Է�����
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdkxfx" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�ʵʩʱ���
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdsssjb" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���Ҫ���ѣ�
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs"  property="hdxyjf" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�����Ԥ���嵥��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="hdjfys" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<logic:equal name="userType"value="xx">
					<tr style="height:22px">
						<td align="right">
							ѧ������ϯ�º����ۡ������
							<br />
							<span class="style1">(��400��)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs" property="save_shpjyj" onblur="chLeng(this,400)"/>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType"value="admin">
					<tr style="height:22px">
						<td align="right">
							ѧ������ϯ�º����ۡ������
							<br />
							<span class="style1">(��400��)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" name="rs" property="save_shpjyj" onblur="chLeng(this,400)"/>
						</td>
					</tr>
				</logic:equal>
			</table>
					<div class="buttontool" align="center">
						<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="sqPrint()" style="width:80px">
						��  ӡ
					</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>