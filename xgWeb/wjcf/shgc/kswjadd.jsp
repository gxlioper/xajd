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
					document.getElementById(tid).value='�����̽�[][]��';
				}
			}
		</script>
		<html:form action="/shgcwjcfwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ����ά�� - ����Υ������ά��
				</div>
			</div>
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/wjcf_shgc_kswjadd.do" />
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							��������
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 20%">
					<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" style="width: 30%">
						<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<html:select property="nd" styleId="nd" style="width:100px">
							<html:options collection="xnList" property="nd" labelProperty="nd"/>
						</html:select>
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
						<html:select property="xn" styleId="xn" style="width:100px">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
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
						<html:select property="xq" styleId="xq" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
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
						<html:select property="cflb" styleId="cflb" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="kscflbList" property="cflbdm" labelProperty="cflbmc"/>
						</html:select>
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
						<html:select property="cfyy" styleId="cfyy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="kscfyyList" property="cfyydm" labelProperty="cfyymc"/>
						</html:select>
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
							<html:text property="cfwh" styleId="cfwh" onclick="getwh('cfwh');"></html:text>
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
							<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');"></html:text>
						</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����Υ������:
					</td>
					<td align="left" colspan="3">
						<html:textarea property="jtwjsy" styleId="jtwjsy" style="width:95%" rows="3">
						</html:textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�ΰ��������:
					</td>
					<td align="left" colspan="3">
						<html:textarea property="zacfqk" styleId="zacfqk" style="width:95%" rows="3">
						</html:textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�����������:
					</td>
					<td align="left" colspan="3">
						<html:textarea property="qtcfqk" styleId="qtcfqk" style="width:95%" rows="3">
						</html:textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���:
					</td>
					<td align="left" colspan="3">
						<logic:notEqual value="xy" name="userType">
							<html:textarea property="xyclyj" styleId="xyclyj" readonly="true" style="width:95%"  rows="3" >
						</html:textarea>
						</logic:notEqual>
						<logic:equal value="xy" name="userType">
							<html:textarea property="xyclyj" styleId="xyclyj" style="width:95%"  rows="3" >
						</html:textarea>
						</logic:equal>
						
					</td>
				</tr>
				<logic:notEqual value="xy" name="userType">
					<tr style="height:22px">
					<td align="right">
						ѧ�������:
					</td>
					<td align="left" colspan="3">
						<html:textarea property="xxclyj" styleId="xxclyj" style="width:95%" rows="3">
						</html:textarea>
					</td>
				</tr>
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('wjcf_shgc_kswjsave.do','xh-xn-xq');"
					style="width:80px" >
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>	
			</div>
		</html:form>
		<logic:equal value="yes" name="inserted">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
			<logic:equal value="no" name="inserted">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
	</body>
</html>
