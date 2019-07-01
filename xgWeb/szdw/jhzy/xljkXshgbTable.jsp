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
     function disabledBjzw(){
         var len = document.getElementById("pk").value.length;
         if(len>0){
              document.getElementById("bjgb").disabled="false";
         }
         
     }
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/show_classStudentCadre_list" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
						��ǰ����λ�ã�˼������-�༶���-������ѧ����ɲ�
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="pk"/>" />
				<input type="hidden" name="bjdm" property="bjdm" value="<bean:write name="rs" property="bjdm"/>" />
				<input type="hidden" name="url" property="url" value="/xljkXshgb_updata.do?act=xljkXshzw" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>��дѧ����ɲ���</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/sxjy_stu_info.do?act=xljkXshzw&pk=<bean:write name='rs' property="pk" />',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						<td align="right" >
							<font color="red">*</font>����ְ��
						</td>
						<td align="left">						
						<html:select property="bjgb" style="width:180px" styleId="bjgb">
							<html:option value=""></html:option>
							<html:options collection="xshgbList" property="xshgbdm"
								labelProperty="xshgbmc" />
						</html:select>				        
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" >
							�༶
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="bjmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧ��������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right" >
							�Ա�
						</td>
						<td align="left" >
							<bean:write name="rs"  property="xb"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ְ��ʼʱ�䣺
						</td>
						<td align="left">
								<html:text name = "rs" property="kssj" styleId="zhdrq"
								onblur="dateFormatChg(this)" style="cursor:hand;" readonly = "true"
								onclick="return showCalendar('zhdrq','y-mm-dd');" />
						<td align="right" >
							��ְ����ʱ�䣺
						</td>
						<td align="left" >
								<html:text name = "rs" property="jssj" styleId="zhdrq"
								onblur="dateFormatChg(this)" style="cursor:hand;"  readonly = "true"
								onclick="return showCalendar('jssj','y-mm-dd');" />
						</td>
					</tr>
					<tr style="height:22px">
					<td align="right" >
							��ϵ��ʽ
						</td>
						<td align="left" colspan="6">
							<html:text name="rs"  property="lxfs" style="width: 400px" maxlength="20"/>
					</td>
					</tr>
					<tr style="height:22px">
					<td align="right" >
							��ע
						</td>
						<td align="left" colspan="6">
							<html:text name="rs"  property="bz" style="width: 400px" maxlength="40"/>
					</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="sxjyCommonSave('/xgxt/xljkXshgb_save.do?type=xljkXshzw&','bjgb-xh','xljkXshzw')">
						��  ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>