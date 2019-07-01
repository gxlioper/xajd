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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/stu_schoolinginfo_one" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ѧ����Ϣ - ѧ����Ϣ
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType"/>" />
				<input type="hidden" id="url" name="url" value="/xsxx/hzzy/stu_schooling_one.jsp" />
				<table width="99%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>����ѧ��ѧ����Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="doType" value="add">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="doType" value="modi">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
							</td>
						</logic:equal>
						<td align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<logic:equal value="modi" name="doType">
						<td align="left">
							<html:text name="rs" property="xn" styleId="xn"  readonly="true"></html:text>
						</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
						<td align="left">
							<html:select name="rs" property="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
						</logic:notEqual>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							Ӧ���ѣ�
						</td>
						<td align="left">
							<html:text name="rs" property="yjf" styleId="yjf" maxlength="10" onblur="chkInput(this,event)"></html:text>
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
							ʵ���ѣ�
						</td>
						<td align="left">
							<html:text name="rs" property="sjf" styleId="sjf" maxlength="10" onblur="chkInput(this,event)"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right">
							Ƿ�ѣ�
						</td>
						<td align="left">
							<html:text name="rs" property="qf" styleId="qf" maxlength="10" onblur="chkInput(this,event)"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="dataDoSaveApply3('/xgxt/stu_schoolinginfo_save.do','xh-xn','xsxx_xsxfxxb')">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="Close();return false;">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				Close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
				dialogArgumentsQueryChick();
				Close();
			</script>
		</logic:equal>
	</body>
</html>
