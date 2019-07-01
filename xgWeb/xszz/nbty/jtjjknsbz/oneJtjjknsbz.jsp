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
		function rychSqPrint(){
        window.open('nbtyJtjjkns.do?method=jtjjknsPrint&pkValue=${pkValue}');
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
		  <html:form action="/nbtyJtjjkns.do?method=oneJtjjknsbz&doType=modi" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/nbtyJtjjkns.do?method=oneJtjjknsbz&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="save_xh" name="save_xh" value="${rs.xh}" />
			<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn}" />
			<input type="hidden" id="save_xq" name="save_xq" value="${rs.xq}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<input type="hidden" id="save_nd" name="save_nd" value="${rs.nd}" />
			<fieldset>
			<div class="title">
			<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ������ - ���� - ��������ѯ
			</div>
			</div>

			<logic:present name="result">
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<logic:notEqual name="doType" value="save">
							<b>��ͥ��������ѧ��������Ϣ</b>
							</logic:notEqual>
							<logic:equal name="doType" value="save">
							<b>��ͥ��������ѧ�������޸�</b>
							</logic:equal>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
							<bean:write name='rs' property="save_xh" />
						</td>
					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="save_xn" />	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="save_xq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red"></font>��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="save_nd" />
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						<font color="red">*</font>���벹����
					</td>
					<td align="left">
					<logic:equal name="doType" value="save">
						<html:select property="save_bzlx" styleId="bzlx">
							<html:options collection="xszzList" property="dm"
								labelProperty="mc" />
						</html:select>
					</logic:equal>
					<logic:notEqual name="doType" value="save">
						<bean:write property="bzmc" name="rs"/>
					</logic:notEqual>
					</td>
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zymc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						������
					</td>
					<td align="left">
						<html:text name="rs" property="save_bzje"  onkeyup="value=value.replace(/[^\d]/g,'')" onblur="chLeng(this,10)"/>Ԫ
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						Ʒ�µȵڣ�
					</td>
					<td align="left">
						<html:text name="rs" property="save_pddd" onblur="chLeng(this,10)"  />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="nj" />
					</logic:notEmpty>	
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
				    <td align="right">
						��ѧ����������(д���������)
						<br />
						<span class="style1">(��30��)&nbsp;</span>
					</td>
					<td  align="left" colspan=3>
					<logic:equal name="doType" value="save">
						<html:textarea  styleId="xnzz" style="width:98%" name="rs" property="save_xnzz" onblur="chLeng(this,30)"/>
					</logic:equal>
					<logic:notEqual name="doType" value="save">
						<bean:write  name="rs" property="xnzz"/>
					</logic:notEqual>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						�Ƿ�μ��ڹ���ѧ��������ѧ����:
						<br />
						<span class="style1">(��30��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
					<logic:equal name="doType" value="save">
						<html:textarea  style="width:98%" name="rs" property="save_cjqgdg" onblur="chLeng(this,30)"/>
					</logic:equal>
					<logic:notEqual name="doType" value="save">
						<bean:write   name="rs" property="cjqgdg" />
					</logic:notEqual>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						�������ɣ�
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_sqly" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
					<div class="buttontool" align="center">
						<logic:equal name="doType" value="save">
							<button type="button" class="button2"	onclick="dosubmit()" style="width:80px" >
								�� ��
							</button>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							�� ��
						</button>
						<logic:notEqual name="doType" value="save">
							<button type="button" class="button2" onclick="rychSqPrint()" style="width:80px" >
								��  ӡ
							</button>
						</logic:notEqual>
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