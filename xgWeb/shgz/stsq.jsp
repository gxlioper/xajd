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
	<base target="_self">
	<script language="javascript">

	</script>
	<body onload="getStsqInfo()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getShgzTyDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPlan.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
		<script type="text/javascript" src="js/shgz/shgzFunction.js"></script>	
		<html:form action="/stgl" method="post">
		<input type="hidden" id="msg" name="msg" value="${msg}" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���� - ���Ź��� - ���������
				</div>
			</div>
			<logic:notPresent name="inserted">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/stgl.do?method=zhsq" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name='pkValue' />" />
				<input type="hidden" id="doType" name="doType" value="<bean:write name='doType' />" />
				<input type="hidden" id="stdm" name="stdm" value="${rs.stdm }" />
				<input type="hidden" id="msg" name="msg" value="${msg}" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>�������ƣ�
						</td>
						<td align="left" colspan="2">
							<html:text property="stmc" onblur="getStdm();refreshForm('/xgxt/stgl.do?method=zhsq&doType=ch');"/>
						</td>
						<td align="right" >
							����ʱ�䣺
						</td>
						<td align="left" colspan="4">
							<html:text  property="clsj" styleId="clsj"  style="cursor:hand;"
								onclick="return showCalendar('clsj','y��mm��dd��');" />
						</td>
					</tr>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>������ѧ�ţ�
							</td>
							<td align="left" colspan="2">
								<html:text  property="xh" styleId="xh" name="rs"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
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
							<td align="left" colspan="2">
								<bean:write name="rs" property="xh" /><html:hidden  property="xh" styleId="xh" />
							</td>
						</logic:equal>
						<td align="right" >
							�༶���ƣ�
						</td>
						<td align="left" colspan="4">
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������ò��
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="zzmmmc" />
						</td>
						<td align="right" >
							��ϵ��ʽ��
						</td>
						<td align="left" colspan="4">
							<html:text  property="lxfs" onkeypress="return onlyNum(this,20)" style="width:50%;ime-mode:disabled"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								���˼�����
							</td>
							<td colspan="7">
								<html:textarea  property='grjl' style="width:99%"
									rows='4' onblur="chLeng(this,200)"/>
							</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								<font color="red">*</font>���Ż���ݣ�
							</td>
							<td colspan="7">
								<html:textarea  property='hdnr' style="width:99%"
									rows='4' onblur="chLeng(this,200)" />
							</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>ָ����ʦ��
						</td>
						<td align="left" colspan="2">
							<html:select  property="zgh" style="width:90px" name="rs"
											styleId="zgh" onchange="refreshForm('/xgxt/stgl.do?method=zhsq');">
											<html:option value=""></html:option>
											<html:options collection="szdwList" property="zgh"
												labelProperty="xm" />
							</html:select>
						</td>
						<td align="right" >
							��ʦ�Ա�
						</td>
						<td align="left" colspan="4" id ="xb" name="rs">
							<bean:write name="rs" property="xb"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����ְ��
						</td>
						<td align="left" colspan="2" id = "zwmc" name="rs">
							<bean:write name="rs" property="zwmc" />
						</td>
						<td align="right" >
							�������
						</td>
						<td align="left" colspan="4">
							<html:select property="lbdm" style="width:90px"
											styleId="lbdm">
											<html:option value=""></html:option>
											<html:options collection="stlbList" property="lbdm"
												labelProperty="lbmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����һ�꼶������
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsn1st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							����һ�꼶Ů����
						</td>
						<td align="left" colspan="2">
							<html:text  property="zsm1st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							���ն��꼶������
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsn2st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							���ն��꼶Ů����
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsm2st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�������꼶������
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsn3st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							�������꼶Ů����
						</td>
						<td align="left" colspan="2">
							<html:text   property="zsm3st" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
				<logic:notEmpty name = "doType">
					<tr style="height:22px">
						<td align="right">
							�����ʻ��û�����Ҫ���û���Ӧ����
						</td>
						<td align="left" colspan="2">
							<html:text   property="yhm"/>
						</td>
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left" colspan="2">
							<html:select  property="shzt" style="width:90px"
											styleId="shzt">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								ѧ�������Ų������
							</td>
							<td colspan="7">
							<html:textarea  property='xshyj' style="width:99%"
								rows='3' />
							</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
								Ժ��ί�����
						</td>
						<td colspan="7">
							<html:textarea  property='ytwyj' style="width:99%"
								rows='3' />
						</td>
					</tr>
				</logic:notEmpty>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="szsxDataDoSave('/xgxt/stgl.do?method=saveStSq','stmc-xh-zgh-hdnr')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="showhzy">
					<button class="button2" onclick="window.open('sxjy_report.do?type=stsh&pk='+document.getElementById('pkValue').value)">
						�� ӡ �� ��
					</button>
					</logic:notPresent>
				</div>
			</logic:notEmpty>
			
			</logic:notPresent>
			<logic:present name="inserted">
				<logic:present name="act">
				<logic:equal name="inserted" value="ok">
	    			<script>
	    				alert("�ύ�ɹ���");
	    				if(opener){
	    					opener.document.getElementById("search_go").click();
							Close();
						}
	    			</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
		    			alert("�ύʧ�ܣ�");
		    			if(opener){
		    				alert(opener);
		    				opener.document.getElementById("search_go").click();
							Close();
						}
    				</script>
				</logic:equal>
				</logic:present>
				<logic:notPresent name="act">
				<logic:equal name="inserted" value="ok">
	    			<script>
	    				alert("�ύ�ɹ���");
	    				refreshForm('stgl.do?method=zhsq');
	    			</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
		    			alert("�ύʧ�ܣ�");
		    			alert(''+document.getElementById('msg').value);
		    			refreshForm('stgl.do?method=zhsq');
    				</script>
				</logic:equal>
				</logic:notPresent>
			</logic:present>
		</html:form>
	</body>
</html>
