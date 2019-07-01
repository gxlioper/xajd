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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��ڹ���ѧ - ���� - �˸�����
			</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
					<logic:notEqual name="gwExists" value="yes">
						<script>
    					alert("�������ڹ���ѧ�ڸ�ѧ����������Ǹ�!");
    					</script>
					</logic:notEqual>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="url" name="url" value="/qgzxCgsq.do" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��д�˸������</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="300">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="300">
								<input type="hidden" name="xh" id="xh"
									value="<bean:write name='rs' property="xh" />">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right">
							������
						</td>
						<td align="left" width="300">
							<bean:write name='rs' property="xm" />
						</td>
					</tr>

					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>��ȥ��λ��
						</td>
						<td align="left">
							<html:select property="gwdm" styleId="gwdmgwsbsj">
								<html:options collection="gwList" property="gwdmgwsbsj" labelProperty="gwdm"/>
							</html:select>
						</td>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true"></html:text>
						</td>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true"></html:text>
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
						</td>						
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������ϵ�绰��
						</td>
						<td align="left">
							<html:text property="lxdh" name="rs" styleId="lxdh" />
						</td>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							<font color="red">*</font>�˸�ԭ��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='sqly' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="zgdzdx_cg_chkisDataExist('xh-sqly');">
							�� �� �� ��
					</button>
					<logic:notPresent name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
							�� ӡ Ԥ ��
						</button>
					</logic:notPresent>
					<logic:present name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
							onclick="printReport('qgzx_bb_gwsqb.do?gwdm=')">
							�� ӡ Ԥ ��
						</button>
					</logic:present>
				</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
				    alert("����ɹ���");
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
						var html_reason= '<bean:write name="reason"/>';
					    alert("����ʧ�ܣ�"+html_reason);
				    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
