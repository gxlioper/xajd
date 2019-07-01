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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/addkqxx.do" method="post">
		<html:text  property="xn_id" styleId="xn_id" style="display:none;"></html:text>
			<div class="title">
				<div class="title_img" id="title_m">
					
					<logic:equal value="11049" name="xxdm">
						��ǰ����λ�ã��ճ����� - ���ڹ��� - ���ӿ�����Ϣ
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						��ǰ����λ�ã�Υ�ʹ��� - ѧ������ - ������Ϣά�� - ���ӿ�����Ϣ
					</logic:notEqual>
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<html:text  property="xn" styleId="xn" readonly="true"></html:text>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<html:text  property="xq" styleId="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶���ƣ�
					</td>
					<td align="left">
						<html:text  property="bjmc" readonly="true"></html:text>
					</td>
					<td align="right">
						�γ����ƣ�
					</td>
					<td align="left">
						<html:text  property="kcmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>ʵ��������
					</td>
					<td align="left">
						<html:text  property="sdrs" onblur="" readonly="true"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>���������
					</td>
					<td align="left">
						<html:text  property="qjrs" onblur="" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>Ӧ��������
					</td>
					<td align="left">
						<html:text  property="ydrs" onblur="" readonly="true"></html:text>
					</td>
					<td align="right">
						<font color="red">*</font>ȱ�ڣ�
					</td>
					<td align="left"> 
						<html:text  property="qq" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>�����ʣ�
					</td>
					<td align="left">
						<html:text  property="cql" readonly="true" />
					</td>
					<td align="right">
						������ڣ�
					</td>
					<td align="left"> 
						<html:text style="cursor:hand; width:85px;" property="rq" readonly="true" />
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						���ʱ�䣨��ʼʱ�䣩��
					</td>
					<td align="left">
						<html:text  property="kssj"  styleId="kssj" readonly="true"></html:text>
					</td>
					<td align="right">
						���ʱ�䣨����ʱ�䣩��
					</td>
					<td align="left">
						<html:text  property="jssj"  styleId="jssj" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						�ον�ʦ��
					</td>
					<td align="left">
						<html:text  property="rkjs"  styleId="rkjs" readonly="true"></html:text>
					</td>
					<td align="right">
						�Ͽεص㣺
					</td>
					<td align="left">
						<html:text  property="skdd"  styleId="skdd" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						��ע��
					</td>
					<td align="left" colspan="3">
						<html:textarea  property="bz" cols="50"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
			</script>
		</logic:equal>
	</body>
</html>
