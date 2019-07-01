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
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">
		<html:form action="/bxgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ�����-У��ס��ά��-У��ס���޸�
				</div>
			</div>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xh-xm-nj-zymc-sjhm-jtdh" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-nj-zymc-sjhm-jtdh" />
			<input type="hidden" id="url" name="url"
				value="/rcsw_xwzswh.do?method=xwzsAdd" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="${rs.pkValue}" />
			<fieldset>
				<legend>
					У��ס��ά��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								У��ס��ά��
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="save_xh" readonly="true"
								styleId="xh" />
						</td>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" disabled="true" />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�ֻ����룺
						</td>
						<td align="left">
							<html:text name='rs' property="sjhm" styleId="sjhm"
								disabled="true" />
						</td>
						<td align="right">
							��ͥ�绰��
						</td>
						<td align="left">
							<html:text name='rs' property="jtdh" styleId="jtdh"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>ʵϰҽԺ��
						</td>
						<td align="left">
							<html:text name="rs" property="save_sxyy" styleId="sxyy"
								maxlength="20">
							</html:text>
						</td>
						<td align="right">
							<font color="red">*</font>����ס��ϸ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="save_xzzxxdz" styleId="xzzxxdz"
								maxlength="20">
							</html:text>
						</td>

					</tr>

					<tr>

						<td align="right">
							ʵϰҽԺ�绰��
						</td>

						<td align="left">
							<html:text name="rs" property="save_sxyydh" styleId="sxyydh"
								maxlength="20" onkeyup="value=value.replace(/[^\d|.]/g,'')">
							</html:text>
						</td>
						<td align="right">
							��ס������ϵ�绰��
						</td>
						<td align="left">
							<html:text name="rs" property="save_zzsqlxdh" styleId="zzsqlxdh"
								maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " />
						</td>
					</tr>

					<tr align="left">
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='save_bz' style="width:99%"
								rows='5' onblur="chLeng(this,250)" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<logic:equal name="readOnly" value="false">
						<button type="button" class="button2"
							onclick="saveData('rcsw_xwzswh.do?method=xwzsModi&type=save','xh-sxyy-xzzxxdz')"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}" />
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
