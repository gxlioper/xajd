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
<html>
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="������� zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<base target="_self">
	<body onload="">
		<html:form action="/xbemyArchive">
			<input type="hidden" name="url" id="url" value="/xbemyArchive.do?method=showGraduateArchive">
			<input type="hidden" name="getStuInfo" id="getStuInfo" value="xh-xm-xb-nj-xymc-zymc-bjmc">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - �������� - ��ҵ��������Ϣ
				</div>
			</div>
			<table width="100%" class="tbstyle">
			<thead align="center">
				<tr><td align="center" colspan="4">��ҵ��������Ϣ</td></tr>
			</thead>	
					<tr>
						<td>
							<div align="right">
								<font color="red">*</font>ѧ�ţ�
							</div>
						</td>
						<td width="24%">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)"/>
								<button align="left" class="button2"
									onclick="showTopWin('/xgxt/stu_info.do?',750,550);"
									style="width:20px" id="buttonFindStu">
									ѡ��
								</button>
						</td>
						<td rowspan="5" align="center">
							<img border="1" style="height:133px;width:100px;" src="/<bean:write name="rs" property="xh"/>.jpg" id="pic">
						</td>
						</tr>
						<tr>
						<td width="30%">
							<div align="right">
								������
							</div>
						</td>
						<td width="30%"">
							<bean:write name="rs" property="xm" />
							<input type="hidden" name="xm" id="xm"
								value="<bean:write name="rs" property="xm"/>" />
						</td>					
					</tr>
					<tr>
						<td width="22%">
							<div align="right">
								�Ա�
							</div>
						</td>
						<td width="33%" >
							<bean:write name="rs" property="xb" />
						</td>						
					</tr>
					<tr>
						<td>
							<div align="right">
								�꼶��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<td width="22%">
							<div align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</div>
						</td>
						<td width="33%">
							<bean:write name="rs" property="xymc" />							
						</td>
					</tr>
					<tr height="25px">
						<td width="22%">
							<div align="right">רҵ��
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr height="22px">
						<td>
							<div align="right">�༶�� 
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">��ǲ��λ���ƣ� 
							</div>
						</td>
						<td colspan="2">
							<html:text property="pqdwmc" name="rs"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">����ʱ�䣺 
							</div>
						</td>
						<td colspan="2">
							<html:text property="ffsj" name="rs" styleId="ffsj" onclick="return showCalendar('ffsj','y-mm-dd');"/>
						</td>
					</tr>	
					<tr>
						<td>
							<div align="right">��ǲ֤��ţ� 
							</div>
						</td>
						<td colspan="2">
							<html:text property="pqzbh" name="rs"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">ȡ���������� 
							</div>
						</td>
						<td colspan="2">
							<html:text property="qdrxm" name="rs"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">��������� 
							</div>
						</td>
						<td colspan="2">
							<html:select property="ddqkdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="ddqkList" property="dddm" labelProperty="ddmc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">��ע�� 
							</div>
						</td>
						<td colspan="2">
							<html:textarea property="bz" name="rs" style="width:100%"/>
						</td>
					</tr>
			</table>

			<center>
				<div class="buttontool" id="btn" width="100%">		
						<button class="button2"
							onclick="refreshForm('xbemyArchive.do?method=showGraduateArchive&doType=save')"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;"
							style="width:80px">
							�� ��
						</button>		
				</div>
			</center>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
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
