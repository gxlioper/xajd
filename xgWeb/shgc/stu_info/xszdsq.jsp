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
	<base target="_self">
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
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function zdsqCommit(){
				var zdlb = $('zdlb').value;
				var yzzd= '';
				if(zdlb == 'sx'){
					yzzd = 'xh-xxmc-zddwmc-zddwdz-zddwyb';
				}else if(zdlb == 'zx'){
					yzzd = 'xh-xxmc-zddwmc-zddwdz-zddwyb-wjh';
				}else{
					yzzd = 'xh-wjh-zddwmc-zddwdz-zddwyb';
				}
				commSave('/xgxt/stu_archives_apply.do?doType=save',yzzd);
			}
		</script>
		<html:form action="/stu_archives_apply" method="post">
			<input type="hidden" name="url" id="url"
				value="/shgc/stu_info/xszdsq.jsp">
			<input type="hidden" name="zdlb" id="zdlb"
				value="${zdlb}">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ -
					<logic:equal value="sx" name="zdlb">
						��ѧת������
					</logic:equal>
					<logic:equal value="zx" name="zdlb">
						תѧת������
					</logic:equal>
					<logic:equal value="tx" name="zdlb">
						��ѧת������
					</logic:equal>
					- ��дת�������
				</div>
			</div>
			<fieldset>
				<legend>
					��д�����
				</legend>
				<table width="100%" id="rsT" class="tbstyle">
					<tr style="height:30px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name="rs" property="xh" styleId="xh" readonly="true" />
								<logic:notEqual value="view" name="doType">
								<button align="left" class="button2"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									style="width:20px" id="buttonFindStu">
									ѡ��
								</button>
								</logic:notEqual>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<input type="text" id="xh" name="xh"
									value="<bean:write name="userName"/>" readonly="readonly" />
							</td>
						</logic:equal>
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
					</tr>
					<tr style="height:30px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr style="height:30px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" />

						</td>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>
					<tr style="height:30px">
						<td align="right">
							<font color="red">*</font>
							<logic:equal value="sx" name="zdlb">
									¼ȡѧУ����
								</logic:equal>
							<logic:equal value="zx" name="zdlb">
									תѧѧУ����
								</logic:equal>
							<logic:equal value="tx" name="zdlb">
									��ѧ�ļ���
							</logic:equal>
						</td>
						<td align="left">
							<logic:notEqual value="tx" name="zdlb">
								<html:text name="rs" property="xxmc" styleId="xxmc" maxlength="50"/>
							</logic:notEqual>
							<logic:equal value="tx" name="zdlb">
								<html:text name="rs" property="wjh" styleId="wjh" maxlength="50"/>
							</logic:equal>
						</td>
						<td align="right">
							<font color="red">*</font>���������ĵ�ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="zddwdz" styleId="zddwdz" maxlength="50"/>
						</td>
					</tr>
					<tr style="height:30px">
						<td align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="zddwmc" styleId="zddwmc" maxlength="50"/>
						</td>
						<td align="right">
							<font color="red">*</font>�ʱࣺ
						</td>
						<td align="left">
							<html:text name="rs" property="zddwyb" styleId="zddwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>
					<logic:equal value="zx" name="zdlb">
						<tr style="height:30px">
						<td align="right">
							<font color="red">*</font>ʡ������תѧ�ļ��ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="wjh" styleId="wjh" maxlength="50"/>
						</td>
						<td align="right">
						</td>
						<td align="left">
						</td>
						</tr>
					</logic:equal>
				</table>
				<div class="buttontool" align="center">
					<logic:notEqual value="view" name="doType">
					<button class="button2"
						onclick="zdsqCommit();">
						�� �� �� ��
					</button>
					</logic:notEqual>
					<logic:equal value="view" name="doType">
					<button class="button2"
						onclick="Close();return false;">
						�� ��
					</button>
					</logic:equal>
				</div>
			</fieldset>
			<logic:equal value="true" name="result">
				<script>
					alert("����ɹ���");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
