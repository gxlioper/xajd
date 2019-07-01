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
		<script language="javascript">
			function checkForm(){
				var blfs=document.forms[0].blfs.value;				
				if(blfs.match(/^\d+\.{0,1}\d{0,3}$/)==null){
					alert("����������������֣�");
					return false;
				}	
				return true;
			}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ��У�ڶ�֤�� - ��д�����
				</div>
			</div>			
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("����ɹ���");
    				</script>
				</logic:equal>
			</logic:notEmpty>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0" />
				<fieldset>
					<legend>
						��д�����
					</legend>
					<table width="100%" id="rsT" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b><bean:write name="xxmc"/>�����ڶ�֤������s</b>
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
									onblur="autoFillStuInfo2(this)" onkeypress="if(event.keyCode == 13) return false;" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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
									<input type="text" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" readonly="readonly" />
								</td>
							</logic:equal>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right" >
								������
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc"/>
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
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="left">
								��ϵ��ʽ(�ֻ�)��
							</td>
							<td align="left">
								<html:text name="rs" property="lxfs"/>
							</td>
							<td align="right">
								<font color="red">*</font>�������ڣ�
							</td>
							<td align="left">
								<html:text name="rs" property="sqrq" styleId="sqrq" onclick="return showCalendar('sqrq','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
						<td align="right">
							<font color="red">*</font>���������
						</td>
						<td align="left" colspan="3">
								<html:text name="rs" property="blfs" styleId="blfs"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<font color="red">*</font>�����ʽҪ��
							</td>
							<td align="left" colspan="3">
								<html:textarea name="rs" property="gsyq" style="height:100px;width:700px"/>
							</td>
						</tr>	
						<tr align="left">
							<td align="right" width="150px">
								<font color="red">*</font>����ԭ��
							</td>
							<td align="left" colspan="3"><html:textarea name="rs" property="sqly" style="height:100px;width:700px" styleId="sqly"/>
							</td>
						</tr>						
					</table>
					<logic:equal value="yes" name="writeAble">
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="if(checkForm()) commSave('/xgxt/attend_school_prove.do?doType=save&sfcg=0','xh-sqrq-sqly-blfs-gsyq')">
							�� �� �� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="window.open('shgc/stu_info/xszdzmsqb.jsp')">
							�� ӡ �� ��
						</button>
					</div>
					</logic:equal>
				</fieldset>
				<logic:notEmpty name="result">
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
				</logic:notEmpty>
		</html:form>
	</body>
</html>
