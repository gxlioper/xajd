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
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">	
<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpyFunction.js"></script>
<script type="text/javascript" src="js/AjaxFunction.js"></script>
<body onload="pjpy_initCheck()" >
	<html:form action="/zjlgPjpy" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��� - �Ƚ��༶�������
			</div>
		</div>	
		<table width="100%" class="tbstyle">
					<thead>
					<div align="right">
						<logic:equal value="up" name="view">
							<input  id="up" value="��һ��" disabled="true">
						</logic:equal>
						<logic:notEqual value="up" name="view">
							<input  id="up" value="��һ��"
								onclick="showTips('ˢ�������У����Ժ�...');pjpy_ChangeRecord('up','/xgxt/zjlgPjpy.do?method=xjbjCkView');">
						</logic:notEqual>
						&nbsp; &nbsp;
						<logic:equal value="next" name="view">
							<input  id="next" value="��һ��" disabled="true">
						</logic:equal>
						<logic:notEqual value="next" name="view">
							<input  id="next" value="��һ��"
								onclick="showTips('ˢ�������У����Ժ�...');pjpy_ChangeRecord('next','/xgxt/zjlgPjpy.do?method=xjbjCkView');">
						</logic:notEqual>
						&nbsp; &nbsp;&nbsp; &nbsp;
						<logic:equal value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);"
								checked="true" />&nbsp;ѡ��
					    </logic:equal>
						 <logic:notEqual value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);" />&nbsp;ѡ��
					    </logic:notEqual>
					</div>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>�Ƚ��༶��Ϣ</b>
						</td>
					</tr>
				</thead>
			<thead>				
			</thead>
			<tr>
				<td scope="col" width="15%">
					<div align="right">
						<font color="red">*</font>�༶���룺
					</div>
				</td>
				<td align="left" width="30%">
				<html:text name="rs" property="bjdm" readonly="true"></html:text>
				</td>
				<td width="10%" align="right">
					ѧ�꣺
				</td>
				<td align="left">
					<html:select name="rs" property="xn" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					${rs.nj}
				</td>
				<td align="right">
					�༶���ƣ�
				</td>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					${rs.xymc}
				</td>
				<td align="right">
					����Ա��
				</td>
				<td align="left">
					${fdy}
				</td>
			</tr>
			<tr>
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					${rs.zymc}
				</td>
				<td align="right">
					�༶������
				</td>
				<td align="left">
					${xhnum}
				</td>
			</tr>
			<tr>
				<td scope="row" align="right">
					�������Ҹ�����
				</td>
				<td align="left">
					<html:text name="rs" property="wmqsgs" styleId="wmqsgs"
						onkeypress='return sztzNumInputValue(this,4,event)'
						onblur="pjpy_ckinpdata(this)" />
				</td>
				<td align="right">
					A�����Ҹ�����
				</td>
				<td align="left">
					<html:text name="rs" property="ajqsgs" styleId="ajqsgs"
						onkeypress='return sztzNumInputValue(this,4,event)'
						onblur="pjpy_ckinpdata(this)" />
				</td>
			</tr>
			<tr>
				<td width="13%" scope="row" align="right">
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>

					<span style="color: red">(��500��)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</td>
				<td colspan="3" scope="row" align="left">
					<html:textarea  name="rs" rows="12" style="width:98%" property="bjqk" />
				</td>
			</tr>
		</table>
		<div class="buttontool">
			<button type="button" class="button2" id="buttonClose" onclick="Close();return false;">
				�� ��
			</button>
		</div>
	</html:form>
</body>

</html:html>
