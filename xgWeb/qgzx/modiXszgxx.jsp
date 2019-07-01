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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function stringFormat(){
			var element = ['kcjqgzxsj','sqly','bz'];
			for(var i=0; i<element.length; i++){
				if(ele(element[i])){
					ele(element[i]).innerHTML = formatContentWidth(ele(element[i]).innerText,30);
				}
			}
		}
		
		function save(){
			refreshForm('qgzxGwgl.do?method=modiZgxsgwxx');
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>	
	<body onload="stringFormat()">
		<html:form action="/qgzxGwgl" method="post" enctype="multipart/form-data">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">��ǰ����λ��: �ڹ���ѧ - �ϸ�ѧ������  - �޸�</span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pkV" name="pkV"
					value="${pkV}" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								��λ��Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td align="left">
							${rs.xh}
						</td>
						<td align="right">
							��λ���ƣ�
						</td>
						<td align="left">
							${rs.gwdm}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left">
							${rs.xm}
						</td>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							${rs.nd}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							${rs.xn}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							${rs.nj}
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							${rs.xqmc}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right"><bean:message key="lable.xsgzyxpzxy" />: 
						</td>
						<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							${rs.lxdh}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							${rs.zymc}
						</td>
						<td align="right">
							�ɲμ��ڹ���ѧʱ�䣺
						</td>
						<td align="left" id="kcjqgzxsj">
							${rs.kcjqgzxsj}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
						    �Ƿ���������
						</td>
						<td align="left">
						    ${rs.sfpks}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							${rs.xxyj}
						</td>
						<td align="right">
						    ���˵�λ��ˣ�
						</td>
						<td align="left">
						    ${rs.xyyj}
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right" nowrap="nowrap">
							�������ɣ�
						</td>
						<td colspan="3" id="sqly">
							${rs.xssq}
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��ע��
						</td>
						<td colspan="3" id="bz">
							${rs.bz}
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ʱ�䣺
						</td>
						<td align="left">
							<html:text property="bdsj" name="rs" readonly="true" onclick="return showCalendar('bdsj','y-mm-dd');" styleId="bdsj"></html:text>
						</td>
						<td align="right">
						           ��ְʱ�䣺
						</td>
						<td align="left">
						   <html:text property="lzsj" name="rs" readonly="true" onclick="return showCalendar('lzsj','y-mm-dd');" styleId="lzsj"></html:text>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��λЭ�飺
						</td>
						<td colspan="3" id="bz">
							<input type="file" name="uploadFile" style="width:60%" id="fjwjmc" onkeyup="">
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			<center>
				<div class="buttontool" id="btn">
					<button type="button" class="button2" id="btn_gb"
						onclick="save();return false;"
						style="width:80px">
						����
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_gb"
						onclick="Close();return false;"
						style="width:80px">
						�� ��
					</button>								
				</div>					
			</center>
		</html:form>
		<logic:notEmpty name="result">
			<input type="hidden" id="result" value="${result}"/>
			<script>
				alert(val('result'));
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:notEmpty>
	</body>
</html>