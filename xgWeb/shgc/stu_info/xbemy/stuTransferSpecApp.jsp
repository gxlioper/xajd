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
	<base target="_self">
	<head>
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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script>
			function commitInfo(url,param){
				var nullStr = param.split("-");
				for(var i=0; i<nullStr.length; i++){
					if($(nullStr)){
						var Str = document.getElementById(nullStr[i]).value; 
						if(Str==null || Str == ""){
							alert('�뽫��\*�ŵ���Ŀ��д����!');
							return false;
						}
					}
				}
				refreshForm(url);
			}
			
			function print(url){
			var pk = "!!";
			var xh = document.getElementById("xh").value;
			var zkzh = document.getElementById("zkzh").value;
			var rxsj = document.getElementById("rxsj").value;
			
			var zczydm = document.getElementById("zczydm").value;
			var zcnj = document.getElementById("zcnj").value;
			var zcxz = document.getElementById("zcxz").value;			
			var zcxlcc = document.getElementById("zcxlcc").value;
			
			var zrzydm = document.getElementById("zrzydm").value;
			var zrnj = document.getElementById("zrnj").value;
			var zrxz = document.getElementById("zrxz").value;
			var zrxlcc = document.getElementById("zrxlcc").value;
			
			var sqly = document.getElementById("sqly").value;
			
			url += "&xh=" + xh;
			url += "&zkzh=" + zkzh;
			url += "&rxsj=" + rxsj;
			url += "&zczydm=" + zczydm;
			url += "&zcnj=" + zcnj;
			url += "&zcxz=" + zcxz;
			url += "&zcxlcc=" + zcxlcc;
			url += "&zrzydm=" + zrzydm;
			url += "&zrnj=" + zrnj;
			url += "&zrxz=" + zrxz;
			url += "&zrxlcc=" + zrxlcc;
			url += "&sqly=" + sqly;			
			window.open(url);
			}
		</script>
	<body>
		<html:form action="/xbemyStuStatus.do" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-mzdm" />
				<input type="hidden" id="url" name="url" value="/shgc/stu_info/xbemy/stuTransferSpecApp.jsp" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ѧ���䶯���� - תרҵ����
				</div>
			</div>
			<fieldset>
				<legend>
					��д�����
				</legend>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>תרҵ����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) return false;" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<input type="text" id="xh" name="xh"
									value="<bean:write name="userName"/>"
									readonly="readonly" />
							</td>
						</logic:equal>
						<td align="right">
							<font color="red">*</font>׼��֤�ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="zkzh" styleId="zkzh" />
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
							���壺
						</td>
						<td align="left">
							<html:select name="rs" property="mzdm" style="width:150px" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="mzList" labelProperty="mzmc"
									property="mzdm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							<html:radio name="rs" property="xb" value="��" disabled="true">��</html:radio>
							<html:radio name= "rs" property="xb" value="Ů" disabled="true">Ů</html:radio>
						</td>
						<td align="right">
							��ѧʱ�䣺
						</td>
						<td align="left" colspan="3">
							<html:text name="rs" property="rxsj" styleId="rxsj"
								onclick="return showCalendar('rxsj','y-mm-dd');" />
						</td>
					</tr>
					<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									ת��ת����Ϣ
								</td>
							</tr>
						</thead>
					<tr>
						<td rowspan="4" align="center">
							ת
							<br />
							��
							<br />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td colspan="2">
							<html:select name="rs" property="lqzydm"
									styleId="zczydm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td colspan="2">
							<html:select name="rs" property="zcnj"
								styleId="zcnj">
								<html:option value=""></html:option>
								<html:options  collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td colspan="2">
							<html:text name="rs" property="zcxz" styleId="zcxz" />
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧ����Σ�
						</td>
						<td colspan="2">
							<html:text name="rs" property="zcxlcc" styleId="zcxlcc" />
						</td>
					</tr>
					<tr>
						<td rowspan="4" align="center" width="150px">
							ת
							<br />
							��
							<br />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td colspan="2">
							<html:select name="rs" property="zrzydm"
									styleId="zrzydm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td colspan="2">
							<html:select name="rs" property="zrnj"
								styleId="zrnj">
								<html:option value=""></html:option>
								<html:options  collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td colspan="2">
							<html:text name="rs" property="zrxz" styleId="zrxz" />
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧ����Σ�
						</td>
						<td colspan="2">
							<html:text name="rs" property="zrxlcc" styleId="zrxlcc" />
						</td>
					</tr>
					<tr>
						<td align="right">
							תרҵ���루���ɣ���
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="sqly" styleId="sqly"
								style="width:100%;height:45px" />
						</td>
					</tr>
				</table>
				<logic:equal value="yes" name="writeAble">
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="commitInfo('xbemyStuStatus.do?method=showTransferSpecialty&doType=save','xh-zkzh')">
							�� �� �� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="print('xbemyStuStatus.do?method=printTransferSpecApp')">
							�� ӡ �� ��
						</button>
					</div>
				</logic:equal>
			</fieldset>

			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("����ɹ���");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
