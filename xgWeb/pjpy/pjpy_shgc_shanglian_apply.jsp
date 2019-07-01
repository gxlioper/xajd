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

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<body>
	<html:form action="/pjpy_apply_bg">
		<input type="hidden" name="redirect" id="redirect" value="true">
		<input type="hidden" name="variable" id="variable" value="none">
		<!--input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" /-->
		<input type="hidden" name="url" id="url"
			value="/pjpy/pjpy_shgc_shanglian_apply.jsp">
		<input type="hidden" name="jxjlx" id="jxjlx" value="jiaoyun">
		<input type="hidden" name="jxjdm" id="jxjdm"
			value="<bean:write name="jxjdm"/>">
		<logic:present name="dboperation">
			<logic:equal value="true" name="dboperation">
				<script type="text/javascript">
         	alert("����ɹ���");
         </script>
			</logic:equal>
			<logic:equal value="false" name="dboperation">
				<script type="text/javascript">
         	alert("����ʧ�ܣ�");
         </script>
			</logic:equal>
		</logic:present>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� �� ��ѧ������ �� ��д�����
			</div>
		</div>
		<table width="99%" class="tbstyle" align="center">
			<thead>
				<tr>
					<td colspan="10" align="center" style="font-size:14px">
						�Ϻ����̼�����ѧ������ѧ�������
					</td>
				</tr>
			</thead>
			<tr>
				<logic:equal value="teacher" name="userOnLine" scope="session">
					<td width="10%">
						ѧ��
					</td>
					<td colspan="2">
						<html:text name="rs" property="xh" styleId="xh"
							onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
						<button onclick="showTopWin('stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
				</logic:equal>
				<logic:notEqual value="teacher" name="userOnLine" scope="session">
					<td>
						ѧ��
					</td>
					<td colspan="2">
						<html:text name="rs" property="xh" readonly="true" />
					</td>
				</logic:notEqual>
				<td colspan="7">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="25">
					����
				</td>
				<td colspan="2">
					<html:text name="rs" property="xm" style="width:98%"
						readonly="true" />
				</td>
				<td width="14%">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td width="12%">
					<html:text name="rs" property="xb" style="width:98%"
						readonly="true" />
				</td>
				<td width="13%">
					��������
				</td>
				<td colspan="4">
					<html:text name="rs" property="csny" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25">
					������ò
				</td>
				<td colspan="2">
					<html:text name="rs" property="zzmm" style="width:98%" />
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<html:text name="rs" property="mzmc" style="width:98%" />
				</td>
				<td>
					��ѧ����
				</td>
				<td colspan="4">
					<html:text name="rs" property="rxny" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25">
					Ժ��ϵ
				</td>
				<td colspan="2">
					<html:text name="rs" property="xymc" style="width:98%"
						readonly="true" />
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td colspan="6">
					<html:text name="rs" property="zymc" style="width:98%"
						readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="25">
					��ͥ��ַ
				</td>
				<td colspan="2">
					<html:text name="rs" property="email" style="width:98%" />
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="6">
					<html:text name="rs" property="lxdh" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25">
					��ҵ��λ
				</td>
				<td colspan="9">
					<html:text name="rs" property="email" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25">
					����������λ
				</td>
				<td colspan="9">
					<html:text name="rs" property="hzjydw" style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="50">
					������ֽ���
				</td>
				<td colspan="9">
					<html:textarea name="rs" property="zysj" rows='6' style="width:98%" />
				</td>
			</tr>
			<tr>
				<td height="25" colspan="3">
					��ѧ������ѧϰ�ɼ�
				</td>
				<td colspan="7">
					����
					<html:text name="rs" property="njrs" size="3" />
					��&nbsp;&nbsp;&nbsp;&nbsp;����
					<html:text name="rs" property="njpm" size="3" />
					�� �ϸ�
					<html:text name="rs" property="njrs" size="3" />
					��&nbsp;&nbsp;&nbsp;&nbsp;������
					<html:text name="rs" property="njpm" size="3" />
					��
				</td>
			</tr>
			<logic:present name="cjList">
				<tr>
					<td rowspan="<bean:write name='cjRow'/>">
						��Ҫ�γ̳ɼ�
					</td>
					<td>
						�γ�
					</td>
					<td colspan="2">
						�ɼ�
					</td>
					<td>
						�γ�
					</td>
					<td colspan="2">
						�ɼ�
					</td>
					<td colspan="2">
						�γ�
					</td>
					<td colspan="2">
						�ɼ�
					</td>
				</tr>
				<logic:iterate id="e" name="cjIter" indexId="index">
					<tr>
						<td>
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3) + ""%>" length="1">
								<bean:write name="cjE" property="kcmc" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3) + ""%>" length="1">
								<bean:write name="cjE" property="cj" />
							</logic:iterate>
						</td>
						<td>
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 1) + ""%>" length="1">
								<bean:write name="cjE" property="kcmc" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 1) + ""%>" length="1">
								<bean:write name="cjE" property="cj" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 2) + ""%>" length="1">
								<bean:write name="cjE" property="kcmc" />
							</logic:iterate>
						</td>
						<td colspan="2">
							&nbsp;
							<logic:iterate id="cjE" name="cjList"
								offset="<%=(index.intValue() * 3 + 2) + ""%>" length="1">
								<bean:write name="cjE" property="cj" />
							</logic:iterate>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>

			<logic:notPresent name="cjList">
				<tr>
					<td rowspan="5">
						��Ҫ�γ̳ɼ�
					</td>
					<td>
						�γ�
					</td>
					<td colspan="2">
						�ɼ�
					</td>
					<td>
						�γ�
					</td>
					<td colspan="2">
						�ɼ�
					</td>
					<td colspan="2">
						�γ�
					</td>
					<td colspan="2">
						�ɼ�
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
			</logic:notPresent>
			<tr>
				<td height="50">
					���뽱ѧ������
				</td>
				<td colspan="9">
					<html:textarea name="rs" property="sqly" rows='6' style="width:98%" />
				</td>
			</tr>

			<logic:equal value="xy" name="userType" scope="session">
				<tr>
					<td height="50">
						<bean:message key="lable.xsgzyxpzxy" />��ϵ����С�����
					</td>
					<td colspan="9">
						<html:textarea name="rs" property="xyshyj" rows='6'
							style="width:98%" />
					</td>
				</tr>
			</logic:equal>
			<logic:equal value="admin" name="userType" scope="session">
				<tr>
					<td height="50">
						ѧУ��λ�������
					</td>
					<td colspan="9">
						<html:textarea name="rs" property="xxshyj" rows='6'
							style="width:98%" />
					</td>
				</tr>
				<tr>
					<td height="50">
						����ίԱ�Ḵ�����
					</td>
					<td colspan="9">
						<html:textarea name="rs" property="pwyj" rows='6'
							style="width:98%" />
					</td>
				</tr>
			</logic:equal>
			<tr>
				<td height="50">
					��ע
				</td>
				<td colspan="9">
					<html:textarea name="rs" property="beizhu" rows='6'
						style="width:98%" />
				</td>
			</tr>
		</table>
	</html:form>
	<div class="buttontool">
		<button class="button2" onclick="shgcPriseApplication()">
			�ύ����
		</button>
		<button class="button2" onclick="shgcPriseAppicationPrint()">
			�����ӡ
		</button>
	</div>
</body>
</html:html>
