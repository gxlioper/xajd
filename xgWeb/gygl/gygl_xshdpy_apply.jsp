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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/Ry_manager" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ������Ū - ѧ������������
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/Ry_manager.do" />
					<table width="85%" id="rsT" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>��д�����</b>
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left">
										<html:text name='rs' property="xh" styleId="xh"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left">
										<html:text property="xh" name="rs" styleId="xh"
											readonly="true"></html:text>
									</td>
								</logic:equal>
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
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
									�꼶��
								</td>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
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
									�༶��
								</td>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<td align="right">
									�ֻ����룺
								</td>
								<td align="left">
									<html:text name='rs' property="sjh" styleId="sjh" onkeyup="chkInput(this,event)"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>���ҵ绰��
								</td>
								<td align="left">
									<html:text name='rs' property="qsdh" styleId="qsdh" onkeyup="chkInput(this,event)"/>
								</td>
								<td align="right">
									<font color="red">*</font>¥�����ƣ�
								</td>
								<td align="left">
									<html:select name="rs" property="lddm" styleId="lddm">
										<html:options collection="ldList" property="lddm" labelProperty="ldmc"/>
									</html:select>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>���Һţ�
								</td>
								<td align="left">
									<html:text name='rs' property="qsh" styleId="qsh" onkeyup="chkInput(this,event)"/>
								</td>
								<td align="right">
									�������ࣺ
								</td>
								<td align="left">
									<html:select name="rs" property="sqzl" styleId="sqzl" >
										<html:option value="A">����¥��</html:option>
										<html:option value="B">����㳤</html:option>
									</html:select>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									����ְ��
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="sqrzw" styleId="sqrzw" rows="2" cols="80"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									��Ҫ�¼���
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="zysj" rows="4" cols="80"/>
								</td>
							</tr>
							<logic:equal value="lz" name="lz">
							<tr style="height:22px;display: none;" id="lztj">
								<td align="right">
									¥����&nbsp;&nbsp;&nbsp;<br>�������
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="lztjyj" rows="4" cols="80"/>
								</td>
							</tr>
							</logic:equal>
							<logic:equal value="teacher" name="userOnLine" scope="session">
							<tr style="height:22px">
								<td align="right">
									ѧУ�����
								</td>
								<td colspan="3" align="left">
									<html:textarea name="rs" property="xxyj" rows="4" cols="80"/>
								</td>
							</tr>
							</logic:equal>
						</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="SaveApply('xh-qsdh-qsh','/xgxt/dataSave.do')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expAppTab('rsT','','')">
						�� ӡ �� ��
					</button>
				</div>
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
