<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpyshcbyswh">
		<div align="center" style="font-size:18px;font:'����' ">
			&nbsp;
		</div>
		<br />
		<h3 align="center">
			${xxmc}��ѧ�������
		</h3>
		<table width="98%" class="printstyle" border="1">
			<tr>
				<th height="38" colspan="2" width="13%" scope="col">
					��&nbsp;&nbsp;��
				</th>
				<td colspan="2" style="width:20%" scope="col" align="center">
					${rs.xm}
				</td>
				<th width="8%" scope="col">
					��&nbsp;&nbsp;��
				</th>
				<td colspan="2" scope="col" style="width:10%" align="center">
					${rs.xb}
				</td>
				<th width="12%" scope="col">
					��������
				</th>
				<td colspan="2" scope="col" align="center" width="13%">
					${rs.csrq}
				</td>
				<th width="10%" scope="col">
					��&nbsp;&nbsp;��
				</th>
				<td colspan="2" scope="col" align="center">
					${rs.mz}
				</td>
			</tr>
			<tr>
				<th height="38" colspan="2" scope="col">
					��Դ��
				</th>
				<td colspan="2" scope="col" align="center">
					${rs.syd}
				</td>
				<th colspan="3" scope="col">
					������ò
				</th>
				<td colspan="3" align="center">
					${rs.zzmm}
				</td>
				<th scope="col">
					ְ&nbsp;&nbsp;��
				</th>
				<td colspan="2" scope="col" align="center">
					${rs.drzw}
				</td>
			</tr>
			<tr>
				<th height="38" colspan="2" scope="col">
					ר&nbsp;&nbsp;ҵ
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.zymc}
				</td>
				<th scope="col">
					ѧ&nbsp;&nbsp;��
					<br>
					(10λ)
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.xh}
				</td>
			</tr>
			<tr>
				<th height="38" colspan="2" scope="col">
					ѧ&nbsp;&nbsp;��
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.xn}${rs.xq}
				</td>
				<th scope="col">
					��&nbsp;&nbsp;��
				</th>
				<td colspan="5" scope="col" align="center">
					${rs.jd}
				</td>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp; ���Ƴɼ���
					</p>
					<p align="left">
						<br />					
					<p>
						&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp; �������룺
					</p>
					<p align="left">
						<br />
						
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						ǩ�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp;����Ա�����(����񽱵ȼ�)
					</p>
					<p align="left">
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						ǩ�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp;<bean:message key="lable.xb" />�����
					</p>
					<p align="left">
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						ǩ�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" colspan="13">
					<p align="left">
						&nbsp;&nbsp;ѧ���������
					</p>
					<p align="left">
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="right">
						ǩ�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</th>
			</tr>
		</table>
ע��&nbsp;&nbsp;&nbsp;&nbsp;1.�˱�һʽ���ݣ����˵�����<bean:message key="lable.xb" />��һ��<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.�øֱ���д���ּ��������ϵ��У���·���Ч
    </html:form>
    
</body>
</html:html>
