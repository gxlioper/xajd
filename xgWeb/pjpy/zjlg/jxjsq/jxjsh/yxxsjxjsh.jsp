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
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - ��ѧ����� - ����ѧ����ѧ��
		</div>
	</div>
		<html:form action="/zjlgPjpy" method="post">
					<TABLE width="99%" id="rsTable1" class="tbstyle">
			<TR>
				<TD>
					<jsp:include flush="true" page="/pjpy/zjlg/jxjsq/jxjsh/jxjShDh.jsp"></jsp:include>
				</TD>
			</TR>
		</TABLE>
			<table class="tbstyle" width="90%">
				<tr>
					<td align="center" colspan="1" style="width: 120px">
						<font color="red">*</font>��ѧ������
					</td>
					<td colspan="2">
						<html:select name="rs" property="jxjdm" styleId="jxjdm" style="width:180px;display:none"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						<html:text name="rs" property="jxjmc" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							��ѧ�����
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="jxjlbmc" readonly="true"></html:text>
						<html:text name="rs" property="jxjlb" readonly="true" style="display: none"></html:text>
					</td>
				</tr>
				<tr>
						<td align="center" colspan="1">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							���п���
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="yhkh" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="yhlx" readonly="true"></html:text>
						<!--<html:select property="yhlx" name="rs" styleId="yhlx"
								disabled="true"	>
								<html:option value=""></html:option>
									<html:options collection="yhklxList" property="yhklxmc" labelProperty="yhklxmc" name="yhklxdm"/>
								</html:select>-->
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							����ְ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="drzw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xymc" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="bjmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							����������
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
							<html:text name="rs" style="width:60px" property="dycj" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="zycj" />" readonly="true">
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
						<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="tycj" />" readonly="true">
					</td>
				</tr>
					<tr style="height:22px">
					<td scope="row">
					<div align="center">
						�۲�ɼ�
						</div>
					</td>
					<td>
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="zhcj" />" readonly="true">
					</td>
					<td >
					<div align="center">
						�۲�����
						</div>
					</td>
					<td align="left" >
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="zcbjpm" />" readonly="true">
					</td>
					<td>
					<div align="center">
						��������
						</div>
					</td>
					<td  >
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="dypm" />" readonly="true">
						
					</td>	
					<td >
				    <div align="center">
						��������
						</div>
					</td>
					<td >
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="zypm" />" readonly="true">
						
					</td>
				</tr>
					<tr>
				
				    
				 <td  >
				    <div align="center">
						Ӣ��ȼ��ɼ�
						</div>
					</td>
					<td  >
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="yydjcj" />" readonly="true">
						</td>
						<td >
					<div align="center">
						������ɼ�
						</div>
					</td>
					<td >
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="jsjdjcj" />" readonly="true">
						
					</td>
					 <td >
					  <div align="center">
					  ������γ���
					  </div>
					</td>
					<td  colspan="3">
					<input type="text" id="zymc" name="zymc"
							style="width:60px"
							value="<bean:write name='rs' property="bjg" />" readonly="true">
					
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="7">
						�����˵���������ı��֣�����������л�����ķ������ʵ���ȷ��棩
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<html:textarea name="rs" property="xxjl" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="fdyyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ѧ<br>Ժ<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ѧ<br>У<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xxshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ע<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea name="rs" property="bz" rows='6' style="width:100%;display: none" />
						1. ����һʽ���ݣ�������д����Ҫ����Դ�ӡ��<br>
						2. <bean:message key="lable.xsgzyxpzxy" />���༶��д�����

					</td>
				</tr>
			</table>
<%--	<div class="buttontool" align="center">--%>
<%--				<button type="button" class="button2" id="buttonSave" onclick="jxjSqSavett();">--%>
<%--					�� �� �� ��--%>
<%--				</button>--%>
<%--				&nbsp;&nbsp;--%>
<%--			</div>--%>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			        </script>
			</logic:equal>

		</html:form>
</body>
</html:html>
