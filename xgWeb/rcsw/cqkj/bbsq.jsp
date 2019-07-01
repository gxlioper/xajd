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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ճ����� - ���� - ��������
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
				<logic:equal name="bbFlag" value="1">
					<script>
    alert("��������,��������Ѳ����!");
   // location.href="about:blank";
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-xn-nd-xq" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/putAgain_apply.do" />
				<input type="hidden" id="sfzh" name="sfzh" value="<bean:write name='rs' property="sfzh" />" />
				<input type="hidden" id="csrq" name="csrq" value="<bean:write name='rs' property="csrq" />" />
				<input type="hidden" id="xz" name="xz" value="<bean:write name='rs' property="xz" />" />
				<input type="hidden" id="syd" name="syd" value="<bean:write name='rs' property="syd" />" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" bgcolor="DOEOEE">
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
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" bgcolor="DOEOEE">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<input type="text" id="xh" name="xh"  value="<bean:write name='rs' property="xh"  />" readonly="true" />
							</td>
						</logic:equal>
						<td align="right" bgcolor="DOEOEE">
							������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							�Ա�
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							��ȣ�
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"
								style="width: 90px"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							�꼶��
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							ѧ�꣺
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true"
								style="width: 90px"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xy" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true"
								style="width: 90px"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							ѧ�ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xz" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							���֤�ţ�
						</td>
						<td align="left" id="tshzh">
							<bean:write name='rs' property="sfzh" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							�������ڣ�
						</td>
						<td align="left" id="tcsrq">
							<bean:write name='rs' property="csrq" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							��Դ�أ�
						</td>
						<td align="left" id="tsyd">
							<bean:write name='rs' property="syd" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							���壺
						</td>
						<td align="left" id="tmz">
							<bean:write name='rs' property="mz" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							רҵ��
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>������Ŀ��
						</td>
						<td align="left">
							<html:select name='rs' property="bbxm" styleId="bbxm">
								<option value=""></option>
								<option value="xszbbb">
									ѧ��֤
								</option>
								<option value="hcyhkbbb">
									���Żݿ�
								</option>
								<option value="yktbbb">
									һ��ͨ
								</option>
								<option value="xhbbb">
									У��
								</option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							��վ����
						</td>
						<td align="left">
							<html:text name='rs' property="hczm" styleId="hczm"
								style="width: 50%" />
						</td>
						<td align="right" bgcolor="DOEOEE">
							<font color="red">*</font>����ԭ��
						</td>
						<td align="left">
							<html:text name='rs' property="bbyy" styleId="bbyy"
								style="width: 100%" />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right" bgcolor="DOEOEE">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="dataDoSaveApply3('/xgxt/applySave.do','xh-bbxm-bbyy','bbsq')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','���������','')">
						�� ӡ �� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
