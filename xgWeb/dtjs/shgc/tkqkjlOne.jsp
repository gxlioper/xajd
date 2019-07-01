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
	<body onload="checkWinType();">
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/dtjs" method="post">
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
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/dtjs.do?method=rtjjfzOne" />
				<fieldset>
					<legend>
						���������
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>��ȣ�
							</td>
							<td align="left">
								<html:select name = "rs" property="nd" style="width:90px"
									styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>ѧ�꣺
							</td>
							<td align="left">
								<html:select name = "rs" property="xn" style="width:90px"
									styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ڣ�
							</td>
							<td align="left">
								<html:select name = "rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>������
							</td>
							<td align="left">
								<html:text name='rs' property="tkr" styleId="tkr" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:select name = "rs" property="nj" style="width:90px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:select  name = "rs" property="xydm" style="width:180px" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:select name = "rs" property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									<input type="hidden" name="zyV" value=""/>
							</td>
							<td align="right">
								<font color="red">*</font>�༶��
							</td>
							<td align="left">
								<html:select name = "rs" property="bjdm" style="width:200px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="zs" styleId="zs" />
							</td>
							<td align="right">
								<font color="red">*</font>�γ����ƣ�
							</td>
							<td align="left">
								<html:text name='rs' property="kcmc" styleId="kcmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>�ο���ʦְ���ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="zgh" styleId="zgh" />
							</td>
							<td align="right">
								�ο���ʦ������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ͽ�������
							</td>
							<td align="left" colspan="3">
								Ӧ��<html:text name='rs' property="ydrs" style="width:40px" maxlength="4"/>��;
								ʵ��<html:text name='rs' property="sdrs" style="width:40px" maxlength="4"/>��;
								�ٵ�<html:text name='rs' property="cdrs" style="width:40px" maxlength="4"/>��;
								����<html:text name='rs' property="kkrs" style="width:40px" maxlength="4"/>��;
								���<html:text name='rs' property="qjrs" style="width:40px" maxlength="4"/>��;
							</td>
						</tr>
						<tr>
							<td align="right">
								���ң�
							</td>
							<td align="left">
								<html:text name='rs' property="js" styleId="js" />
							</td>
							<td align="right">
								<font color="red">*</font>�������ڣ�
							</td>
							<td align="left">
								<html:text name='rs' property="tkrq" styleId="tkrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tkrq','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>��ʼʱ�̣�
							</td>
							<td align="left">
								<html:text name='rs' property="kssk" styleId="kssk" />
							</td>
							<td align="right">
								<font color="red">*</font>����ʱ�̣�
							</td>
							<td align="left">
								<html:text name='rs' property="jssk" styleId="jssk" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ͽ���ڣ�
							</td>
							<td align="left">
								<html:select name = "rs" property="sklj" style="width:90px">
										<html:option value="��">��</html:option>
										<html:option value="һ��">һ��</html:option>
										<html:option value="��">��</html:option>
								</html:select>
							</td>
							<td align="right">
								ѧ�����ڰ壺
							</td>
							<td align="left">
								<html:select name = "rs" property="xschb" style="width:90px">
										<html:option value="����">����</html:option>
										<html:option value="��">��</html:option>
										<html:option value="����">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								 &nbsp;&nbsp;<html:text name='rs' property="jrs" styleId="jrs" style="width:40px" maxlength="4"/>&nbsp;&nbsp;��
							</td>
							<td align="right">
								��ʳ�
							</td>
							<td align="left">
								 &nbsp;&nbsp;<html:text name='rs' property="crs" styleId="crs" style="width:40px" maxlength="4"/>&nbsp;&nbsp;��
							</td>
						</tr>
						<tr>
							<td align="right">
								���ֻ���
							</td>
							<td align="left">
								 &nbsp;&nbsp;<html:text name='rs' property="wrs" styleId="wrs" style="width:40px" maxlength="4"/> &nbsp;&nbsp;��
							</td>
							<td align="right">
								��������޹����ݣ�
							</td>
							<td align="left">
								 &nbsp;&nbsp;<html:text name='rs' property="krs" styleId="krs" style="width:40px" maxlength="4"/> &nbsp;&nbsp;��
							</td>
						</tr>
						<tr>
							<td align="right">
								��ͷ�Ӷ���
							</td>
							<td align="left">
								 &nbsp;&nbsp;<html:text name='rs' property="wrs" styleId="trs" style="width:40px" maxlength="4"/> &nbsp;&nbsp;��
							</td>
							<td align="right">
								�������գ�
							</td>
							<td align="left">
								<html:radio name = "rs" property="ktqf1"  value="1"/>����ӻԾ&nbsp;&nbsp;
								<html:radio name = "rs" property="ktqf1"  value="2"/>����Ͻ�ʦ<br />
								<html:radio name = "rs" property="ktqf2"  value="1"/>��������&nbsp;&nbsp;
								<html:radio name = "rs" property="ktqf2"  value="2"/>������<br />
								<html:radio name = "rs" property="ktqf3"  value="1"/>ר������&nbsp;&nbsp;
								<html:radio name = "rs" property="ktqf3"  value="2"/>���ֲ�ֹ<br />
								<html:radio name = "rs" property="ktqf4"  value="1"/>���ջ�Ծ&nbsp;&nbsp;
								<html:radio name = "rs" property="ktqf4"  value="2"/>���ճ���<br />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ʦ�ڿμ������������
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jsskqk' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />ѧ���칫�����
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='xyxsbgsyj' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ע
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('dtjs.do?method=saveTkqkOne','bjdm-zgh-kcmc-tkrq-kssk-jssk-xn-nd-xq');"
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
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
