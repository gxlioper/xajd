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
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript">
			function getwh(tid){
				var cfwh = document.getElementById(tid).value;
				if (cfwh==null || cfwh=='') {
					document.getElementById(tid).value='������ѧ[][]��';
				}
			}
		</script>
		<html:form action="/shgcwjcfwh.do" method="post">
			<div class="title">
				<logic:equal value="11049" name="xxdm">
				
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ϵԺ�걨 - δͨ�������ѯ - ��ϸ��Ϣ
				</div>
				</logic:equal>
				<logic:notEqual value="11049" name="xxdm">
					<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ϵԺ�걨 - δͨ���걨��Ϣ
				</div>
				</logic:notEqual>
				
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							��ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 20%">
					<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" style="width: 30%">
						${rs.xh }
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						${rs.nd }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
					<font color="red">*</font>	ѧ�꣺
					</td>
					<td align="left">
						${rs.xn }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td align="right">
					<font color="red">*</font>	ѧ�ڣ�
					</td>
					<td align="left">
						${rs.xq }
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						�������
					</td>
					<td align="left">
						${rs.cflbmc }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						����ԭ��
					</td>
					<td align="left">
						${rs.cfyymc }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs"  property="zymc"/>
					</td>
						<td align="right">
							�����ĺ�:
						</td>
						<td align="left">
							${rs.cfwh }
						</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
						<td align="right">
							����ʱ��:
						</td>
						<td align="left">
							${rs.cfsj }
						</td>
				</tr>
				<logic:equal value="10856" name="xxdm">
					<tr style="height:22px">
					<td align="right">
						����Υ������:
					</td>
					<td align="left" colspan="3">
						${rs.jtwjsy }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�ΰ��������:
					</td>
					<td align="left" colspan="3">
						${rs.zacfqk }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�����������:
					</td>
					<td align="left" colspan="3">
						${rs.qtcfqk }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���:
					</td>
					<td align="left" colspan="3">
						${rs.xyclyj }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						ѧ�������:
					</td>
					<td align="left" colspan="3">
						${rs.xxclyj }
					</td>
				</tr>	
				</logic:equal>
				<logic:notEqual value="10856" name="xxdm">
					<tr style="height:22px">
					<td align="right">
						Υ����ʵ:
					</td>
					<td align="left" colspan="3">
						${rs.bz }
					</td>
				</tr>	
					<tr style="height:22px">
					<td align="right">
						ϵԺ���:
					</td>
					<td align="left" colspan="3">
						${rs.xyclyj }
					</td>
				</tr>
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
				
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>	
			</div>
		</html:form>
	</body>
</html>
