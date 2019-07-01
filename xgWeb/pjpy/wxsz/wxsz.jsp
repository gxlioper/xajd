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
<h3> <bean:write name="map" property="xxmc"/>������ҵְҵ����<bean:message key="lable.xsgzyxpzxy" />����ѧ����ѧ�������</h3>
			<table width="95%" class="printstyle">
				<tr>
					<th width="100" align="center">
						&nbsp;&nbsp;&nbsp;���뽱ѧ��<br/>&nbsp;&nbsp;&nbsp;�ĵȼ�
					</th>
					<td colspan="7" align="center">
						<bean:write name="map" property="jxjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<bean:write name="map" property="xymc"/>&nbsp;&nbsp;&nbsp;Ժ,ϵ(��) 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="map" property="zymc"/>&nbsp;&nbsp;&nbsp;רҵ 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="map" property="bjmc"/>&nbsp;&nbsp;&nbsp;�༶
					</td>
				</tr>
				<tr>
					<th align="center">
						����
					</th>
					<td width="90" align="center">
						<bean:write name="map" property="xm"/>
					</td>
					<th width="70" align="center">
						�Ա�
					</th>
					<td width="90" align="center">
						<bean:write name="map" property="xb"/>
					</td>
					<th width="84" align="center">
						��������
					</th>
					<td width="71" align="center">
						<bean:write name="map" property="csny"/>
					</td>
					<th width="84" align="center">
						����
					</th>
					<td width="71" align="center">
						<bean:write name="map" property="jg"/>
					</td>
				</tr>
				<tr>
					<th align="center">
						������ò
					</th>
					<td width="71" align="center">
						<bean:write name="map" property="zzmmmc"/>
					</td>
					<th width="70" align="center">
						����
					</th>
					<td width="100" align="center">
						<bean:write name="map" property="mzmc"/>
					</td>
					<th width="84" align="center">
						ְ��
					</th>
					<td align="center">
						&nbsp;
					</td>
					<th width="84" align="center">
						����
					</th>
					<td  align="center">
						${map.ssbh }
					</td>
				</tr>
				<tr>
					<th rowspan="11" align="center">
						<p>
							��ѧ��
						</p>
						<p>
							���ſ�
						</p>
						<p>
							�̳ɼ�
						</p>
					</th>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc1 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc2 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc3 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj1 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj2 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj3 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc4 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc5 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc6 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj4 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj5 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj6 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc7 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc8 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc9 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj7 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj8 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj9 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc10 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc11 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc12 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj10 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj11 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj12 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc13 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc14 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc15 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj13 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj14 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj15 }
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<br/>
						ƽ���ɼ���${rs.pjcj } 
						<p align="right">
							����ǩ����&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;��
						</p>
					</td>
				</tr>
				<tr>
					<th align="center">
						���еȼ�
					</th>
					<td height="30" align="center">
						&nbsp;
					</td>
					<th align="center">
						�ۺϲ���
					</th>
					<td colspan="2" align="center">
						&nbsp;
					</td>
					<th align="center" colspan="2">
						��ѧ�������������
					</th>
					<td align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th align="center">
							<br/>
							��<p/>
							��<p/>
							��<p/>
							��<p/>
							��<p/>
					</th>
					<td colspan="7">
						${map.fdyyj }
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<th align="center">
						<br/>
							ϵ
						<p/>
							(��)
						<p/>
							��
						<p/>
							��
					</th>
					<td colspan="7">
						${map.xyshyj }
						<p align="right">
							ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<th align="center">
						<br/>
							ѧ
						<p/>
							У
						<p/>
							��
						<p/>
							��
					</th>
					<td colspan="7">
						${map.xxshyj }
						<p align="right">
							����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;
							�� &nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<table border="0" width="95%">
				<tr>
					<td algin="left">
						ע��1��������ֻ������������ǩ��������<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						2�������еȵڡ������ۺϲ�����������ѧ����������������ɰ�������д��
					</td>
				</tr>
			</table>
		</center>
</html:form>
</body>
</html:html>
