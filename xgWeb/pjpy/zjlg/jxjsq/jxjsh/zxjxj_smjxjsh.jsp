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
			��ǰ����λ�ã��������� - ��ѧ����� - ɣ�齱ѧ��
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
						<html:select name="rs" property="jxjdm" style="width:180px;display:none"
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
							��Դʡ��
						</div>
					</td>
					<td colspan="2"><bean:write name="rs" property="jgs"/>
<%--						<html:text name="rs" property="jgs" readonly="true"></html:text>--%>
						<input id="jgs" name="jgs" type="text"" value="<bean:write name="rs" property="jg"/>"/>
					</td>
					<td>
						<div align="center">
							�س�
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="tc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							ũ�����
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="nccz" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�ۺϲ�������
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						רҵ����
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zhuanypm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�� �� �� ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="sbdj" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						�ڼ��λ��ɣ�齱ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="djchdjxj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							���<br>ɣ��<br>����<br>���<br>��
						</div>
					</td>
					<td colspan="8">
						���ɣ������������ʵҵ�ҡ�������ʿ������ر����������Ͼ�ѫ�»��
						<br>�߲����������������������������ҹ���֯�����ޣ�����ʮ���������
						<br>Ϊ��չ��֯��ҵ��ע�˴����������ɼ�׿��������һλ�ɹ�ʵҵ�ҵľ����
						<br>�ǻۣ������Ƽ������������˲��Ǵٽ��ҹ���֯��ҵ���غͷ�չ�ĸ�����
						<br>�⣬��ˣ�����һ�žŶ������ȳ��ʳ�����ɣ�����ᣬ�����Ե��λ����
						<br>��ϯ��ּ���廳�ҹ���֯��ҵ��Դ��ɣ�飬���Լ������ڷ�֯���ҵ��Ա��
						<br>����ѧ�ӷ��ﰮ�����徫�����ӿƼ���Ŭ�����У������л���֯��ҵ����
						<br>����Ⱥ�������֯��<bean:message key="lable.xsgzyxpzxy" />��������ѧ��ԭ�й���֯��ѧ�����㽭����ѧ
						<br>��ԭ�㽭˿��<bean:message key="lable.xsgzyxpzxy" />���ͱ�����װ<bean:message key="lable.xsgzyxpzxy" />������ɣ�齱ѧ�𣬶Խ����Ƚ�����
						<br>���˲��ṩ�������������ٽ��˷�֯������ҵ�ķ�չ��
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="8">
					����������Ӧ���������˵��������ı��֡�������Ṥ�����μ������������뽱ѧ��Ķ�����Ŭ��Ŀ�꣬����Ҫ˵����ͥ�������
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>Ա<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="fdyyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br><bean:message key="lable.xsgzyxpzxy" /><br>���<br>���<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ɣ��<br>��ѧ<br>����<br>����<br>����<br>��ί<br>Ա��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="lshshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ע<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%;display: none"  readonly="true"/>
					1.����һʽ���ݣ�������д����Ҫ����Դ�ӡ��<br>
					2.ѧУ��Ժ��ϵ�༶������д��
					
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
