<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
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
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media="print">
	.tbstyle{display:none}
	</style>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'����' ">ѧ�꺼��ְҵ����<bean:message key="lable.xsgzyxpzxy" />�Ƚ�����֧�Ƽ���</div>
<br/>

		<table width="98%" class="printstyle">
			<tr>
				<th height="30" width="20%" colspan="2" scope="col">
					����֯����
				</th>
				<td height="30" width="30%" colspan="2" scope="col" align="center">
					&nbsp;${tzzmc }
				</td>
				<th height="30" width="20%" colspan="2" scope="col">
					�������
				</th>
				<td height="30" width="30%" colspan="2" scope="col" align="center">
					&nbsp;${tzzsj }
				</td>

			</tr>
			<tr>
				<th height="30" colspan="2" scope="col">
					��֧����
				</th>
				<td height="30" colspan="2" scope="col" align="center">
					&nbsp;${tzbs}
				</td>
				<th height="30" colspan="2" scope="col">
					��Ա��
				</th>
				<td height="30" colspan="2" scope="col" align="center">
					&nbsp;${tys }
				</td>
			</tr>
			<tr>
				<th width="10%" height="121" rowspan="" scope="row">
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
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
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
				</th>
				<th colspan="7">
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${zysj }
					</p>
				</th>
			</tr>
			<tr>
				<th scope="row" width="10%" colspan="">
					<p>
						&nbsp;
					</p>
					<p>
						��
					</p>
					<p>
						��
					</p>
					<p>
						֧
					</p>
					<p>
						��
					</p>
					<p>
						��
					</p>
					<p>
						&nbsp;
					</p>
				</th>
				<th colspan="3">

					<p align="left">
						<br />
						<br />
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${xyyj }
					</p>

					<div align="right">
						ǩ��(����)��${xyqm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						<br>
						<p align="right">
							&nbsp;${xyshyear } &nbsp;��&nbsp; ${xyshmon } &nbsp;��&nbsp;
							${xyshdate } &nbsp;��
						</p>
					</div>
				</th>
				<th scope="row" width="10%">
					<p>
						&nbsp;
					</p>
					<p>
						ѧ
					</p>
					<p>
						Ժ
					</p>
					<p>
						��
					</p>
					<p>
						ί
					</p>
					<p>
						��
					</p>
					<p>
						��
					</p>
					<p>
						&nbsp;
					</p>
				</th>
				<th colspan="3" style="">

					<p align="left">
						<br />
						<br />
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${xyyj }
					</p>

					<div align="right">
						ǩ��(����)��${xyqm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br />
						<br>
						<p align="right">
							&nbsp;${xyshyear } &nbsp;��&nbsp; ${xyshmon } &nbsp;��&nbsp;
							${xyshdate } &nbsp;��
						</p>
					</div>
				</th>

			</tr>			
			<tr>
				<th scope="row" width="10%" colspan="">
					<p>
						��
					</p>
					<p>
						ע
					</p>
				</th>
				<th colspan="7" scope="row" style="">
				</th>
			</tr>
		</table>
		<br>
		<br>
		<div align="center" class="noprint">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</html:form>
  </body>
</html:html>
