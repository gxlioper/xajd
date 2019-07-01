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
		function chkValue(doType)
		{
			var xh=document.getElementById("xh").value;

			if (xh==''){
				alert("��ɫ*����ϢΪ������ٴ���д��ȷ�ϣ�");
			}else{
				refreshForm('wjcf_BgdOper.do?doType='+doType);
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<html:form action="/wjcf_BgdOper" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/wjcf_BgdLoad.do?doType=isSHGC" />
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">Υ�ʹ��� - ����ά�� - �������ֲ��鵵</span>
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							Υ�ʹ��ֲ��鵵����
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
						<input type="text" name="xh" style="width:85px"
							readonly="readonly" id="xh"
							value="<logic:present name="rs"><bean:write name="rs" property="xh"/></logic:present>"
							onkeypress="autoFillStuInfo(event.keyCode,this)">
					</td>
					<td align="right">
						������
					</td>
					<td align="left">
						<input type="text" readonly="readonly" name="xm" id="xm"
							value="<logic:present name="rs"><bean:write name="rs" property="xm"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<input type="text" name="xymc" readonly="readonly" id="xymc"
							value="<logic:present name="rs"><bean:write name="rs" property="xymc"/></logic:present>">
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<input type="text" name="zymc" readonly="readonly" id="zymc"
							value="<logic:present name="rs"><bean:write name="rs" property="zymc"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<input type="text" name="bjmc" readonly="readonly" id="bjmc"
							value="<logic:present name="rs"><bean:write name="rs" property="bjmc"/></logic:present>">
					</td>
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<input type="text" name="nj" readonly="readonly" id="nj"
							value="<logic:present name="rs"><bean:write name="rs" property="nj"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<input type="text" name="xb" readonly="readonly" id="xb"
							value="<logic:present name="rs"><bean:write name="rs" property="xb"/></logic:present>">
					</td>
					<td align="right">
						������ò��
					</td>
					<td align="left">
						<input type="text" name="zzmm" readonly="readonly" id="zzmm"
							value="<logic:present name="rs"><bean:write name="rs" property="zzmm"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						���壺
					</td>
					<td align="left">
						<input type="text" name="mz" readonly="readonly" id="mz"
							value="<logic:present name="rs"><bean:write name="rs" property="mz"/></logic:present>">
					</td>
					<td align="right">
						���᣺
					</td>
					<td align="left">
						<input type="text" name="jg" readonly="readonly" id="jg"
							value="<logic:present name="rs"><bean:write name="rs" property="jg"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						�������£�
					</td>
					<td align="left" colspan="3">
						<input type="text" name="csrq" readonly="readonly" id="csrq"
							value="<logic:present name="rs"><bean:write name="rs" property="csrq"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						���ֵȼ���
					</td>
					<td align="left">
						<input type="text" name="cflbmc" readonly="readonly" id="cflbmc"
							value="<logic:present name="rs"><bean:write name="rs" property="cflbmc"/></logic:present>">
					</td>
					<td align="right">
						����ԭ��
					</td>
					<td align="left">
						<input type="text" name="cfyymc" readonly="readonly" id="cfyymc"
							value="<logic:present name="rs"><bean:write name="rs" property="cfyymc"/></logic:present>">
					</td>
				</tr>
				<tr>
					<td align="right">
						���ֺ���ʵ���֣�
					</td>
					<td colspan="3">
						<textarea rows="3" cols="" style="width:97%" name="cfhbx" id="cfhbx" type="_moz"><logic:present name="rs"><bean:write name="rs" property="cfhbx"/></logic:present>
						</textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						�������ͼ�¼��
					</td>
					<td colspan="3">
						<textarea rows="3" cols="" style="width:97%" name="jcjl" id="jcjl"><logic:present name="rs"><bean:write name="rs" property="jcjl"/></logic:present></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
							<table style="width:99%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>���������</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none">
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													����Ա���������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<textarea rows="3" cols="" style="width:99%" name="bzryj" id="bzryj"><bean:write name="rs" property="bzryj"/></textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													<bean:message key="lable.xsgzyxpzxy" />������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<textarea rows="3" cols="" style="width:99%" name="xyyj" id="xyyj"><bean:write name="rs" property="xyyj"/></textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��첿��������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<textarea rows="3" cols="" style="width:99%" name="kbbmyj" id="kbbmyj"><bean:write name="rs" property="kbbmyj"/></textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													ѧ����������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<textarea rows="3" cols="" style="width:99%" name="xscyj" id="xscyj"><bean:write name="rs" property="xscyj"/></textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													У������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<textarea rows="3" cols="" style="width:99%" name="xxyj" id="xxyj"><bean:write name="rs" property="xxyj"/></textarea>
											</td>
										</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
					<td align="right">
						��ע��
					</td>
					<td colspan="3">
						<textarea rows="3" cols="" style="width:97%" name="bz" id="bz" type="_moz"><logic:present name="rs"><bean:write name="rs" property="bz"/></logic:present>
						</textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:notEqual value="xy" name="userType">
					<button type="button" class="button2" onclick="chkValue('modi')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>

		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
alert("�����ɹ���");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
  alert("����ʧ�ܣ�ԭ������Ǹ���Ϣ�����ݿ����Ѵ���! \n ������ϸ�˶�!��");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
		</logic:equal>
	</body>
</html>
