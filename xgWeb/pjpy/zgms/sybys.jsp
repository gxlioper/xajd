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
<!-- ��ӡ�ؼ�begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<html:form action="/dxjxjsp">
		<p align="center" style="font-size:18px;font:'����' ">
			<b>�㽭ʡ��ͨ�ߵ�ѧУ�����ҵ���ǼǱ�</b>
		</p>
      
		<table width="600px" align="center" class="printstyle">
			&nbsp;&nbsp;&nbsp;ѧУ��&nbsp;${xxmc }&nbsp;Ժ��ϵ����&nbsp;${rs.xymc }&nbsp;רҵ��&nbsp;${rs.zymc }&nbsp; <br />
     &nbsp;&nbsp;&nbsp;�༶��&nbsp;${rs.bjmc }&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;${rs.y }&nbsp;�� &nbsp;${rs.m }&nbsp; �� &nbsp;${rs.r }&nbsp; ��
			<tr>
				<td width="72px">
					<div align="center">
						��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>
				</td>
				<td width="108px">
					<div align="center">
						${rs.xm }
					</div>
				</td>
				<td width="66px">
					<div align="center">
						��&nbsp;&nbsp;&nbsp;&nbsp;��
					</div>
				</td>
				<td width="66px">
					<div align="center">
						${rs.xb }
					</div>
				</td>
				<td width="66px">
					<div align="center">
						��������
					</div>
				</td>
				<td width="66px">
					<div align="center">
						${rs.csrq }
					</div>
				</td>
				<td width="66px">
					<div align="center">
						����
					</div>
				</td>
				<td width="90px">
					<div align="center">
						${rs.mzmc }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��&nbsp;Դ&nbsp;��
					</div>
				</td>
				<td>
					<div align="center">
						${rs.syd }
					</div>
				</td>
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.zzmmmc }
					</div>
					<div align="center"></div>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						ְ��
					</div>
				</td>
				<td>
					<div align="center">
						${rs.drzw }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ͥ
						<br />
						��ַ
					</div>
				</td>
				<td colspan="4">
					<div align="center">
						${rs.jtdz }
					</div>
					<div align="center"></div>
					<div align="center"></div>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						${rs.lxdh }
					</div>
					<div align="center"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</div>
					<br />
				</td>
				<td colspan="7">
					
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.brjl }
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						��
						<br />
						Ҫ
						<br />
						��
						<br />
						��
					</div>
					<br />
				</td>
				<td colspan="7">
					
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.zysj }
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
		</table>
		<p align="center">
ע��1���˱�һʽ���ݣ�ѧ�����˵�����ѧУ��һ�ݡ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
2���������ݿɴ�ӡ���øֱ���д���ּ��������Ժ��ϵ����ѧУ���£��쵼ǩ�ַ���Ч��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
		  
<%--		  <p>&nbsp;</p><p>&nbsp;</p>--%>
		<%--		  <p>&nbsp;</p><p>&nbsp;</p>--%>
		<div style='page-break-before:always;'>
			&nbsp;
		</div>
		<table width="600px" align="center" class="printstyle">
			<tr>
				<td td width="72px">
					<div align="center">
						<br />
						��
						<br />
						У
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</div>
					<br />
				</td>
				<td colspan="3">
				
					<p>
						&nbsp;
					</p>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
					</p>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
			<tr>
				<td td width="72px">
					<div align="center">
						<br />
						Ժ
						<br />
						ϵ
						<br />
						��
						<br />
						��
					</div>
					<br />
				</td>
				<td width="200px">
					&nbsp;
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
					</div>
				</td>
				<td td width="72px">
					<div align="center">
						ѧ
						<br />
						У
						<br />
						��
						<br />
						��
					</div>
				</td>
				<td width="200px">
					
					
					<p>
						&nbsp;
					</p>
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
					</div>
					<div align="center"></div>
					<div align="center"></div>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					
					
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						ʡ
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</div>
					<br />
				</td>
				<td width="200px"></td>
				<td>
					<div align="center">
						��
						<br />
						ҵ
						<br />
						��
						<br />
						��
						<br />
						ҵ
						<br />
						ȥ
						<br />
						��
					</div>
				</td>
				<td width="200px">
					
					<p>
						&nbsp;
					</p>
					<div align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.byjyqx }
					</div>
					<p>
						&nbsp;
					</p>
					
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<br />
						��
						<br />
						ע
						<br />
					</div>
					<br />
				</td>
				<td colspan="3">
					<div align="center"></div>
					<br />
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
					<p>
						&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<p align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�㽭ʡ�������Ʊ�
		</p>
		<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
		<!-- ע���˱�һʽ���ݣ�ϵ��Ժ����Ժѧ������һ�� -->
	</html:form>
</body>
</html:html>
