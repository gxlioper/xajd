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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��� - ��λ��� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="gwdm||gwsbsj"/>" />
			<input type="hidden" name="xhStr"
				value="<bean:write name="xhStr" />" />
		    <input type="hidden" name="xxyjStr" value="" />	
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������λ���
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						У����
					</td>
					<td align="left">
						<bean:write name="rs" property="xqmc" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��λ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="gwdm" />
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���뵥λ��
					</td>
					<td align="left">
						<bean:write name="rs" property="yrdwmc" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xueqimc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�걨ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="gwsbsj" />
					</td>
					<td align="right">
						��λ���ʣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="gwxzmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������ʼʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="gzksrq" />
					</td>
					<td align="right">
						�Ƴ��׼��
					</td>
					<td align="left">
						<bean:write name="rs" property="jybcbz" />
						<bean:write name="rs" property="jcfs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��������ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="gzjsrq" />
					</td>
					<td align="right">
						��Ҫ����������
					</td>
					<td align="left">
						<bean:write name="rs" property="xyboy" />
					</td>
				</tr>
				<tr style="height:22px">
					<td height="20" align="right">
						�����ˣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="fzr" />
					</td>
					<td align="right">��ҪŮ��������
					</td>
					<td align="left">
						<bean:write name="rs" property="xygirl" />
					</td>
				</tr>
				<tr style="height:22px">
				  <td align="right">��λ������</td>
				  <td align="left"><bean:write name="rs" property="gwsl" /></td>
				  <td align="right">��λҪ�� </td>
				  <td align="left"><bean:write name="rs" property="ryyq" /></td>
			  </tr>
				<tr style="height:22px">
					<td align="right">
						����ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="gzsj" />
					</td>
					<td align="right">
						�ܾ��ѣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zjf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">��λ�ص㣺 </td>
					<td align="left">
						<bean:write name="rs" property="dwdz" />
					</td>
					<td align="right">
						<font color="red">*</font>����ʹ��������
					</td>
					<td align="left">
						<html:text name="rs" property="sqsyrs" style="width:130px"></html:text>��
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right"><font color="red">*</font>���������׼�� </td>
					<td align="left">
						<html:text name="rs" property="spbcbz" style="width:130px"/><bean:write name="rs" property="jcfs" /></td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�������ݣ�
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="gznr" />
					</td>
				</tr>
				<tr style="height:22px">
				  <td align="right">��λ����Ҫ�� </td>
				  <td align="left" colspan="3"><bean:write name="rs" property="gwtsyq" /></td>
			  </tr>
				<tr style="height:22px">
					<td align="right">
						���˵�λ�����
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sqdwyj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�ڹ��������
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="qgbyj" style="width:90%"
							rows="3"></html:textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						ѧ���������
					</td>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="xgbyj" style="width:90%"
							rows="3"></html:textarea>
					</td>
				</tr>
				<thead>
				����<tr style="height:22px">
						<td colspan="4" align="center">
							<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
										����ø�λ��ѧ���б�
										</div>
							</div>
						</td>
					</tr>
			    </thead>
					<tr>
					    <td colspan="4" align="center">
					    <div id="child2" style="display:none">
					    <logic:empty name="xssqList">
											������ø�λ��ѧ����¼!
								</logic:empty>
									<logic:notEmpty name="xssqList">
										<fieldset>
											<legend>
												��¼����
												<bean:write name="count" />
											</legend>
											<table width="100%" id="rsTable1" class="tbstyle">
												<thead>
													<tr align="center" style="cursor:hand">
														<td width="7%">���</td>
														<td width="17%">ѧ��</td>
														<td width="13%">����</td>
														<td width="22%">�༶</td>
														<td width="13%">�Ƿ�ƶ����</td>
														<td width="18%">��ϵ�绰</td>
														<td width="10%">ѧУ���</td>
													</tr>
												</thead>				
									<logic:iterate name="xssqList" id="xssqList">
										<tr align="center">
											<td>
												<bean:write name="xssqList" property="rownum" />
											</td>
											<td>
												<bean:write name="xssqList" property="xh" />
											</td>
											<td>
												<bean:write name="xssqList" property="xm" />
											</td>
											<td>
												<bean:write name="xssqList" property="bjmc" />
											</td>
											<td>
												<bean:write name="xssqList" property="sfpks" />
											</td>
											<td>
												<bean:write name="xssqList" property="lxdh" />
											</td>
											<td>
											    <input name="<bean:write name="xssqList" property="xh" />" type="checkbox" value="1" <bean:write name="xssqList" property="xxyj" />>
											</td>
										</tr>
									</logic:iterate>
											</table>
										</fieldset>
										</logic:notEmpty>
									</div>
					    </td>
					</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/postChkOne.do?act=save');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
