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
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
      </script>
	<body >
		<html:form action="/czxxPjpyTycj" method="post">
			
		<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - �۲���Ϣά�� - �ۺϲ����ɼ�ά��
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							�ۺϲ����ɼ���ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width:25%">
						ѧ�ţ�
					</td>
					<td height="22" align="left" style="width:25%">
						${rs.xh }
					</td>
					<td height="22" align="right" style="width:25%">
						ѧ�꣺
					</td>
					<td height="22" align="left" style="width:25%">
						${rs.xn }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						������
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						ѧ�ڣ�
					</td>
					<td height="22" align="left">
						${rs.xq }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�꼶��
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td height="22" align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						רҵ��
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					<td height="22" align="right">
						�༶��
					</td>
					<td height="22" align="left">
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ���
					</td>
					<td height="22" align="left">
						${rs.dcj }
					</td>
					<td height="22" align="right">
						�����ɼ���
					</td>
					<td height="22" align="left">
						${rs.zcj}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ���
					</td>
					<td height="22" align="left">
						${rs.tcj }
					</td>
					<td height="22" align="right">
						�۲��ܳɼ���
					</td>
					<td height="22" align="left">
						${rs.zxf}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.dpm }
					</td>
					<td height="22" align="right">
						�����ɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.zpm}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�����ɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.tpm }
					</td>
					<td height="22" align="right">
						�۲��ܳɼ��༶������
					</td>
					<td height="22" align="left">
						${rs.zfpm}
					</td>
				</tr>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
				<button class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
