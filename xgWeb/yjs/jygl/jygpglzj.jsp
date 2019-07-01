<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link rel="stylesheet" type="text/css" media="all" href="style/calendar.css" title="win2k-cold-1" />
    <script type="text/javascript" src="style/calendar.js"></script>
    <script type="text/javascript" src="style/calendar-zh.js"></script>
    <script type="text/javascript" src="style/calendar-setup.js"></script>
	<script language="JavaScript" src="style/dmwh.js"></script>
	<script language="JavaScript" src="style/xjgl.js"></script>
	<script language="JavaScript" src="style/jhgl.js"></script>
	<script language="javascript">
	function checkForm(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("������ѧ�ţ�");
				return false;
			}
			changTab('/yjsjwgl/jygpzg.do?dotype=add');
		}
	function checkModify(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("������ѧ�ţ�");
				return false;
			}
			changTab('/yjsjwgl/jygpzg.do?dotype=modify');
		}
	</script>
</head>
 <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
	<html:form method="POST" action="jytjgl"
		enctype="multipart/form-data">		
		<logic:equal name="page" value="add">
		<div id="title">
			<div class="titiel_img"></div>
			��ǰ����λ�ã���ҵ���ɹ���-->����ҳ��
		</div>
		<div>
			<table width="99%">
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>ѧ��
					</td>
					<td><input type="text" name="xh" /></td>
				</tr>
				<tr>
					<td align="center" width="45%">
						����ʱ��
					</td>
					<td><input type="text" name="sqsj" 
							onclick="return showCalendar('sqsj','y-mm-dd');"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						����ԭ��
					</td>
					<td><input type="text" name="gpyy" /></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						����ǰ��λ
					</td>
					<td><input type="text" name="gpqdw"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						���ɺ�λ
					</td>
					<td><input type="text" name="gphdw"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						������
					</td>
					<td><input type="text" name="spr"/></td>	
				</tr>
			</table>
			<div id="button">
				<button class="button2" onclick="checkForm()">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					�� ��
				</button>
			</div>
		</div>
		</logic:equal>
	
	<!-- ����Ϊ����ҳ�����ݣ�����Ϊ�޸�ҳ������ -->
		<logic:equal name="page" value="modify">
		<div>
			<div id="title">
				<div class="titiel_img"></div>
				��ǰ����λ�ã���ҵ���ɹ���-->�޸�ҳ��
			</div>
			<input type="hidden" name="dm" 
				value="<bean:write name="dm" scope="request"/>"/>
			<table width="99%">
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>ѧ��
					</td>
					<td><html:text property="xh" name="map"></html:text>  </td>
				</tr>
				<tr>
					<td align="center" width="45%">
						����ʱ��
					</td>
					<td>
						<html:text property="sqsj" name="map"
						     onclick="return showCalendar('sqsj','y-mm-dd');">
						</html:text>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						����ԭ��
					</td>
					<td><html:text property="gpyy" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						����ǰ��λ
					</td>
					<td><html:text property="gpqdw" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						���ɺ�λ
					</td>
					<td><html:text property="gphdw" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						������
					</td>
					<td><html:text property="spr" name="map"></html:text> </td>	
				</tr>
			</table>
			<div id="button">
				<logic:notPresent name="ck">
				<button class="button2" onclick="checkModify();">
					�� ��
				</button>
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					�� ��
				</button>
			</div>
		</div>
			</logic:equal>
			<logic:equal name="result" value="view">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			    window.dialogArguments.document.forms[0].action='/yjsjwgl/jygpgl.do?act=find';
			   	window.dialogArguments.document.forms[0].submit();
			   	window.close();
			  	</script>
			</logic:equal>
	</html:form>
</body>

</html:html>

