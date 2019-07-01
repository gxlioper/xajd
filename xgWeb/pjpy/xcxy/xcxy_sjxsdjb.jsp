<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
 <html:form action="/prise_conf_rs" method="post" >
<center>
<h3> <bean:write name="map" property="xxmc"/>�ڽ�ѧ���ǼǱ�</h3>
			<table width="90%" class="tbstyle">
				<tr>
					<td width="93" align="center">
						�ڽ���Ŀ
					</td>
					<td colspan="3" align="center">
						<bean:write name="map" property="jxjmc"/>
					</td>
					<td colspan="2" align="center">
						���ڰ༶
					</td>
					<td colspan="2" align="center">
						<bean:write name="map" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						����
					</td>
					<td width="90" align="center">
						<bean:write name="map" property="xm"/>
					</td>
					<td width="70" align="center">
						�Ա�
					</td>
					<td width="90" align="center">
						<bean:write name="map" property="xb"/>
					</td>
					<td width="33" align="center">
						����
					</td>
					<td width="100" align="center">
						<bean:write name="map" property="mzmc"/>
					</td>
					<td width="84" align="center">
						��������
					</td>
					<td width="71" align="center">
						<bean:write name="map" property="csny"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						�뵳����ʱ��
					</td>
					<td align="center">
						${map.rtrq }
					</td>
					<td align="center">
						����ְ��
					</td>
					<td align="center">
						&nbsp;
					</td>
					<td colspan="2" align="center">
						����ְ��
					</td>
					<td colspan="2" align="center">
						${map.drzw }
					</td>
				</tr>
				<tr>
					<td align="center">
						<p>
							��
						</p>
						<p>
							Ҫ
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
					</td>
					<td colspan="7" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xxjl }
						<div align="right">
						������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="7" align="center">
						<p>
							ѧ
						</p>
						<p>
							ҵ
						</p>
						<p></p>
						��
						<p>
							��
						</p>
					</td>
					<td align="center">
						��&nbsp;&nbsp;Ŀ
					</td>
					<td align="center">
						��&nbsp;&nbsp;��
					</td>
					<td align="center">
						��&nbsp;&nbsp;Ŀ
					</td>
					<td colspan="2" align="center">
						��&nbsp;&nbsp;��
					</td>
					<td align="center">
						��&nbsp;&nbsp;Ŀ
					</td>
					<td align="center">
						��&nbsp;&nbsp;��
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc1 }
					</td>
					<td height="30" align="center">
						${rs.cj1 }
					</td>
					<td align="center">
						${rs.kcmc2 }
					</td >
					<td colspan="2" align="center">
						${rs.cj2 }
					</td>
					<td align="center">
						${rs.kcmc3 }
					</td >
					<td align="center">
						${rs.cj3 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc4 }
					</td>
					<td height="30" align="center">
						${rs.cj4 }
					</td>
					<td align="center">
						${rs.kcmc5 }
					</td>
					<td colspan="2" align="center">
						${rs.cj5 }
					</td>
					<td align="center">
						${rs.kcmc6 }
					</td>
					<td align="center">
						${rs.cj6 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc7 }
					</td>
					<td height="30" align="center">
						${rs.cj7 }
					</td>
					<td align="center">
						${rs.kcmc8 }
					</td>
					<td colspan="2" align="center">
						${rs.cj8 }
					</td>
					<td align="center">
						${rs.kcmc9 }
					</td>
					<td align="center">
						${rs.cj9 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc10 }
					</td>
					<td height="30" align="center">
						${rs.cj10 }
					</td>
					<td align="center">
						${rs.kcmc11 }
					</td>
					<td colspan="2" align="center">
						${rs.cj11 }
					</td>
					<td align="center">
						${rs.kcmc12 }
					</td>
					<td align="center">
						${rs.cj12 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc13 }
					</td>
					<td height="30" align="center">
						${rs.cj13 }
					</td>
					<td align="center">
						${rs.kcmc14 }
					</td>
					<td colspan="2" align="center">
						${rs.cj14 }
					</td>
					<td align="center">
						${rs.kcmc15 }
					</td>
					<td align="center">
						${rs.cj15 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc16 }
					</td>
					<td height="30" align="center">
						${rs.cj16 }
					</td>
					<td align="center">
						${rs.kcmc17 }
					</td>
					<td colspan="2" align="center">
						${rs.cj17 }
					</td>
					<td align="center">
						${rs.kcmc18 }
					</td>
					<td align="center">
						${rs.cj18 }
					</td>
				</tr>
				<tr>
					<td align="center">
						<p>
							ϵ
						</p>
						<p>
							(��)
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
					</td>
					<td colspan="7">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xyshyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<p>
							ѧ
						</p>
						<p>
							У
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
					</td>
					<td colspan="7">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xxshyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
							�� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr align="center">
					<td>
						<p>
							��
						</p>
						<p>
							ע
						</p>
					</td>
					<td colspan="7" height="100px">
						&nbsp;
					</td>
				</tr>
			</table>
		</center>
</html:form>
</body>
</html:html>
