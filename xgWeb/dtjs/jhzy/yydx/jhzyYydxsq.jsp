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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	 
	function isNumber(obj){
		var s = $(obj).value;
		if(s==""){
			alert('�ڽ첻��Ϊ��');
			 $(obj).focus();
			return false;
		}
		var p = /^\d+$/;
		if(p.test(s)){
		}else{
			alert('�ڽ��������������');
			 $(obj).focus();
			return false;
		}
		return true;
	}    
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/jhzyYydx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã����Ž��� - ҵ�൳У - ��д�����
				</div>
			</div>

			<logic:equal name="rs" property="stuExists" value="no">
				<script>
			    alert("�������ѧ����Ч!");
			    </script>
			</logic:equal>
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/jhzyYydxsq.do" />
			<%--					<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />--%>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��д�����</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
							<html:hidden name='rs' property="xh" styleId="xh" />
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>

					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<html:select property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right" style="width: 10%">
						ѧ�ڣ�
					</td>
					<td align="left" style="width: 40%">
						<html:select property="xq" disabled="true">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
				<tr style="height:22px">
					<td align="right">
						�༶���ƣ�
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td align="right">
						�ڽ�:
					</td>
					<td align="left">
						<html:text property="qj" maxlength="20" styleId="qj"></html:text>��
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����뵳����ʱ�䣺
					</td>
					<td align="left">
						<bean:write name='rs' property="djsqsj" />
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>
				<tr style="height:22px" id="sqly">
					<td align="right">
					    �������ɣ�
						<span class="style1">(��600��)&nbsp;</span>
					</td>
					<td colspan="3" align="left" >
						<html:textarea rows="8" style="width:98%" property="sqly" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="if(isNumber('qj')){dtjsCommonSave('jhzyYydx.do?method=yydxsqSave','sqly','600','xh-sqly');}">
					��  �� 
				</button>
				<button type="button" id="btn_cj" class="button2" onclick="showTopWin('ahjg_xscjb.do?xh='+document.getElementById('xh').value,'500','400')">
							ѧ �� �� ��
						</button>
						&nbsp;&nbsp;
			</div>
			
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ����Ѿ������˸���ҵ�൳У");
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>
