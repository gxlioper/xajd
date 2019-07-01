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
	function xspd_save()
	{
			var xh=document.all["xh"].value;
			if ( xh==""){
				alert ("�뵥��>>����ѡ��ѧ����Ϣ��");
				document.all["xh"].focus();
				return false;
			}
			var rq=document.all["rq"].value;
			if ( rq==""){
				alert ("��ѡ���¼���ڣ�");
				document.all["rq"].focus();
				return false;
			}
			var xn=document.all["xn"].value;
			if(xn==""){
			    alert("��ѡ��ѧ�꣡");
			    document.all["xn"].focus();
			    return false;
			}
			var xq=document.all["xq"].value;
			if(xq==""){
			   alert("��ѡ��ѧ�ڣ�");
			   document.all["xq"].focus();
			   return false;
			}
			var xwmkdm=document.all["xwmkdm"].value;
			if ( xwmkdm==""){
				alert ("��ѡ����Ϊģ�飡");
				document.all["xwmkdm"].focus();
				return false;
			}
			var pdmkdm=document.all["pdmkdm"].value;
			if ( pdmkdm==""){
				alert ("��ѡ��Ʒ��ģ�飡");
				document.all["pdmkdm"].focus();
				return false;
			}
			var jlr=document.all["jlr"].value;
			if ( jlr==""){
				alert ("����д��¼�ˣ�");
				document.all["jlr"].focus();
				return false;
			}
			var jtnr=document.all["jtnr"].value;
			if ( jtnr==""){
				alert ("����д�����¼���ݣ�");
				document.all["jtnr"].focus();
				return false;
			}
			document.all["add_flag"].value="yes";
			refreshForm('/xgxt/rcgl_xspd.do?act=xspd&doType=xspd_add');
	}
	</script>
	<body >
	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/rcgl_xspd" method="post">
		<input type="hidden" id="add_flag" name="add_flag" value="no" />		
				<table  class="tbstyle" align="center">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>Ʒ�±�����Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
							<td align="right" colspan="2">
								<font color="red">*</font>ѧ �ţ�
							</td>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
									<button onclick="showTopWin('/xgxt/rcgl_xspd.do?act=xspd&doType=stu_info',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
								</button>
							</td>
							
						<td align="right">
							�� ����
						</td>
						<td align="left">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2" readonly="true" >
							�� ��
						</td>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							�� ����
						</td>
						<td align="left">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							ѧ Ժ��
						</td>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true"/>
						</td>
						<td align="right" >
							�� �ڣ�
						</td>
						<td align="left">
								<html:text style="cursor:hand; width:75px;" styleId="dateF"
									property="rq"
									onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							ѧ�꣺
						</td>
						<td align="left">							
								<html:select property="xn" style="width:100px;background-color:#FFFFFF" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
						</td>
						<td align="right" >
							ѧ�ڣ�
						</td>
						<td align="left">							
								<html:select property="xq" style="width:50px;background-color:#FFFFFF" styleId="xq" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							��Ϊģ�飺
						</td>
						<td align="left">
							<html:select property="xwmkdm" style="width:100px" styleId="xwmkdm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="rcxwList" property="XN_ID"
										labelProperty="MKMC" />
							</html:select>
						</td>
						<td align="right">
							Ʒ��ģ�飺
						</td>
 						<td align="left">
							<html:select property="pdmkdm" style="width:100px" styleId="pdmkdm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="pdList" property="XN_ID"
										labelProperty="MKMC" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
					    <td align="right" colspan="2" >
							��¼�ˣ�
						</td>
						<td align="left"  colspan="3" >
							<html:text  property="jlr" maxlength="15"/>
						</td>						
					</tr>					
				   <tr>
				  		<td align="right" colspan="2"> �������ݣ�</td>
                    	<td colspan="4" align="left"><html:textarea rows="5"  style="width:98%" property="jtnr" styleId="a" /></td>
				  </tr>
				</table>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"  onclick="xspd_save()">
						�� ��
					</button>
				</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
				<logic:notEmpty name="message">
					<logic:equal name="message" value="inser_success">
						<script>
						alert("���ӳɹ�!");
						close();
						dialogArgumentsQueryChick();
						</script>
					</logic:equal>
					<logic:equal name="message" value="insert_fail">
						<script>alert("����ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
</html>
