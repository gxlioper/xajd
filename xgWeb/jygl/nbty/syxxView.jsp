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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
	
	</script>
	<body>
		<html:form action="/nbtybysy" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name='xxdm'/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��Դ��Ϣ- ��Դ��Ϣ�鿴
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
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="tableName" name="tableName" value="nbtysysbb" />
				<input type="hidden" id="url" name="url" value="/jycxzmgl.do?method=addJycczm" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��Դ��Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<bean:write name='rs' property="xh" />
							<input type="hidden" name="pkVal" id="pkVal"
								value="<bean:write name='rs' property="xh" />">
						</td>
						<td align="right">
							<font color="red">*</font>����:					
						</td>
						<td align="left">
							<html:text name="rs" property="xm" readonly="true"></html:text>						
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name="rs" property="xb" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							���֤�ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="sfzh" readonly="true" styleId="sfzh"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�������£�
						</td>
						<td align="left">
							<html:text name="rs" property="csny" readonly="true" onclick="return showCalendar('csny','y-mm-dd');" styleId="csny"></html:text>
						</td>
						<td align="right">
							���壺
						</td>
						<td align="left">
<%--							<html:text name="rs" property="xn" readonly="true" styleId="xn"></html:text>--%>
							<html:select name="rs" property="mz" styleId="mz">
								<html:options collection="mzList" property="mzdm"
									labelProperty="mzmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������ò��
						</td>
						<td align="left">
							<html:select name="rs" property="zzmm" styleId="mz">
								<html:options collection="zzmmList" property="zzmmdm"
									labelProperty="zzmmmc" />
							</html:select>
						</td>
						<td align="right">
							��ϵ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" maxlength="150" styleId="lxdz" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�������룺
						</td>
						<td align="left">
							<html:text name="rs" property="yzbm" maxlength="8" readonly="true"></html:text>
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ֻ����룺
						</td>
						<td align="left">
						<html:text name="rs" property="sjhm" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							email���䣺
						</td>
						<td>
							<html:text name="rs" property="email" maxlength="40" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ҵ���ޣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xynx" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							����ѧУ��
						</td>
						<td>
							<html:text name="rs" property="xxdm" maxlength="30" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�����༶��
						</td>
						<td align="left">
							<html:text name="rs" property="bjmc" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							��ѧ��ݣ�
						</td>
						<td>
							<html:text name="rs" property="rxnf" maxlength="30" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ҵ��ݣ�
						</td>
						<td align="left">
							<html:text name="rs" property="bynf" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							ѧ����Σ�
						</td>
						<td>
							<html:text name="rs" property="xlcc" maxlength="30" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������ʽ��
						</td>
						<td align="left">
							<html:text name="rs" property="pyfs" maxlength="15"></html:text>
						</td>
						<td align="right">
							�������
						</td>
						<td>
							<html:text name="rs" property="zslb" maxlength="30"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�����ί�൥λ��
						</td>
						<td align="left">
							<html:text name="rs" property="dxhwpdw" maxlength="15"></html:text>
						</td>
						<td align="right">
							�Ƿ���ְ��
						</td>
						<td>
							<html:select name="rs" property="sfzz" styleId="sfzz">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ƿ�ʦ����
						</td>
						<td align="left">
							<html:select name="rs" property="sfsf" styleId="sfsf">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<td align="right">
							�Ƿ����<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							<html:select name="rs" property="sfdlxy" styleId="sfdlxy">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��Դ�أ�
						</td>
						<td colspan="2">
							<html:select name="rs" property="syds" styleId="syds">
								<html:options collection="sydList" property="syddm"
									labelProperty="sydmc" />
							</html:select>
						</td>
						<td>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button id="buttonSave" class="button2" onclick="Close();return false;">
<%--						onclick="if(checkFiledSuccess()){chkisDataExist('xh-xmdm-lxdh-kcjqgzxsj-sqly');}">--%>
						�ر�
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
