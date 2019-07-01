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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-mzmc-csrq-jg-sfzh-jrgqtsj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-mzmc-csrq-jg-sfzh" />
				<input type="hidden" id="url" name="url" value="/dtjs/zgkd/zgkd_rdsqb.jsp" />
				<fieldset>
					<legend>
						�뵳������Ϣά��
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									ѡ��
								</button>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								
							</td>
							<td align="left">
								
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								<font color="red">*</font>�ݽ�����ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="djsqsj" styleId="pxjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxjssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<html:text name='rs' property="mzmc" styleId="mzmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<td align="right">
								��������:
							</td>
							<td align="left">
								<html:text name='rs' property="csrq" styleId="csrq" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<td align="right">
								����:
							</td>
							<td align="left">
								<html:text name='rs' property="jg" styleId="jg" />
							</td>
						</tr>
						<tr>
							<td align="right">
								���֤���룺
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" />
							</td>
							<td align="right">
								����ʱ��:
							</td>
							<td align="left">
								<html:text name='rs' property="jrgqtsj" styleId="jrgqtsj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								ȷ��Ϊ�뵳��������ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="rdjjfzsj" styleId="rdjjfzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rdjjfzsj','y-mm-dd');" />
							</td>
							<td align="right">
								�뵳����������ѵ��ҵ֤����:
							</td>
							<td align="left">
								<html:text name='rs' property="rdjjfzpxbh" styleId="rdjjfzpxbh" 
								onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								ȷ��Ϊ��չ����ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="fzdxsj" styleId="fzdxsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('fzdxsj','y-mm-dd');" />
							</td>
							<td align="right">
								��չ������ѵ��ҵ֤����:
							</td>
							<td align="left">
								<html:text name='rs' property="fzdxpxbh" styleId="fzdxpxbh" 
								onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ע��
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						�� ��
					</button>
					<button type="button" class="button2"
						onclick="if(checkbz())dataDoSave('xh-pxjssj');"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
			   <jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
