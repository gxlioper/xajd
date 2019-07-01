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
	
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/systemFunction.js'></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script>
		function sumbitForm(){
			if(val('disabled') == 'disabled'){
				alert('�ϼ��Ѿ���ˣ���ʱ�����޸���˽����');
				return false;
			}
			//�ύ
			refreshForm("XsgyglDispatch.do?method=audiXqwmqssq&doType=save");			
		}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="/XsgyglDispatch" method="post">	
			<input id="pk" name="pk" value="${rs.pk}" type="hidden"/>		
			<input id="disabled" name="disabled" value="${rs.disabled}" type="hidden"/>
			<div class="title">
				<div class="title_img" id="title_m">
						��ǰ����λ�ã���Ԣ���� - ��� - ѧ�����������������
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								ѧ����������������Ϣ
							</td>
						</tr>
					</thead>					
					<tr>
					    <td align="right">
							<font color="red">*</font>¥�����ƣ�
						</td>
						<td align="left">
							${rs.ldmc}
							<html:hidden property="lddm" name="rs"/>
						</td>		
						<td align="right" >
							<font color="red">*</font>ѧ�꣺
						  </td>
						<td align="left">
							${rs.xn}
							<html:hidden property="xn" name="rs"/>
						</td>	
					</tr>					
					<tr>
					<td align="right">
							<font color="red">*</font>���Һţ�
						</td>					
						<td align="left">
							${rs.qsh}
							<html:hidden property="qsh" name="rs"/>
							<html:hidden property="ssbh" name="rs"/>
						</td>	
						<td align="right">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							${rs.xqmc}
							<html:hidden property="xq" name="rs"/>
						</td>					
					</tr>
					<tr>	
						<td align="right">
						    �����ˣ�
						</td>
						<td align="left">
							${rs.sqr }		
						</td>
						<td align="right">
							
						</td>
						<td align="left">
							
							
						</td>	
					</tr>
					<tr>
						<td align="right" >
							��ע��
						</td>
						<td align="left" colspan="3">
							${rs.bz}
						</td>
					</tr>
					<!--��Ԣ����Ա	-->
					<logic:equal value="fdy" name="yhType">
					<tr>	
						<td align="right">
							����Ա��ˣ�
						</td>
						<td align="left">
							<html:select property="fdysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>							
						<td align="right">
						   ���ʱ�䣺
						</td>
						<td align="left">
							${rs.fdyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							����Ա�����
						</td>
						<td align="left" colspan="3">
							<html:textarea property="fdyshyj" name="rs" cols="80" rows="4" onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					<tr>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��ˣ�
						</td>
						<td align="left">
							${rs.xysh}
						</td>							
						<td align="right">
						   <bean:message key="lable.xsgzyxpzxy" />���ʱ�䣺
						</td>
						<td align="left">
							${rs.xyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td align="left" colspan="3">
							${rs.xyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							${rs.xxsh}
						</td>							
						<td align="right">
						   ѧУ���ʱ�䣺
						</td>
						<td align="left">
							${rs.xxshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							ѧУ�����
						</td>
						<td align="left" colspan="3">
							${rs.xxshyj}
						</td>
					</tr>
					</logic:equal>
					<!--end��Ԣ����Ա	-->
					<!--<bean:message key="lable.xsgzyxpzxy" />	-->
					<logic:equal value="xy" name="yhType">
					<tr>	
						<td align="right">
							����Ա��ˣ�
						</td>
						<td align="left">
							${rs.fdysh}
						</td>							
						<td align="right">
						   ���ʱ�䣺
						</td>
						<td align="left">
							${rs.fdyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							����Ա�����
						</td>
						<td align="left" colspan="3">
							${rs.fdyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��ˣ�
						</td>
						<td align="left">
							<html:select property="xysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>							
						<td align="right">
						   <bean:message key="lable.xsgzyxpzxy" />���ʱ�䣺
						</td>
						<td align="left">
							${rs.xyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td align="left" colspan="3">
							<html:textarea property="xyshyj" name="rs" cols="80" rows="4" onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					<tr>	
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							${rs.xxsh}
						</td>							
						<td align="right">
						   ѧУ���ʱ�䣺
						</td>
						<td align="left">
							${rs.xxshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							ѧУ�����
						</td>
						<td align="left" colspan="3">
							${rs.xxshyj}
						</td>
					</tr>
					</logic:equal>
					<!--end<bean:message key="lable.xsgzyxpzxy" />	-->
					<!--ѧУ	-->
					<logic:equal value="xx" name="yhType">
					<tr>	
						<td align="right">
							����Ա��ˣ�
						</td>
						<td align="left">
							${rs.fdysh}
						</td>							
						<td align="right">
						   	���ʱ�䣺
						</td>
						<td align="left">
							${rs.fdyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							����Ա�����
						</td>
						<td align="left" colspan="3">
							${rs.fdyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��ˣ�
						</td>
						<td align="left">
							${rs.xysh}
						</td>							
						<td align="right">
						   <bean:message key="lable.xsgzyxpzxy" />���ʱ�䣺
						</td>
						<td align="left">
							${rs.xyshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td align="left" colspan="3">
							${rs.xyshyj}
						</td>
					</tr>
					<tr>	
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							<html:select property="xxsh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>							
						<td align="right">
						   ѧУ���ʱ�䣺
						</td>
						<td align="left">
							${rs.xxshsj}
						</td>
					</tr>
					<tr>
						<td align="right" >
							ѧУ�����
						</td>
						<td align="left" colspan="3">
							<html:textarea property="xxshyj" name="rs" cols="80" rows="4" onblur="chLeng(this,100)"></html:textarea>
						</td>
					</tr>
					</logic:equal>
					<!--endѧУ	-->
				</table>
				<div class="buttontool" align="center">											
					<button class="button2" onclick="sumbitForm();" style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonSave">
						�� ��
					</button>
				</div>		
		</html:form>
		<logic:equal value="true" name="result">
			<script type="text/javascript">
			    alert('�����ɹ���');
			    if(window.dialogArguments){
				    Close();
					dialogArgumentsQueryChick();
				}
			 </script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script type="text/javascript">
			    alert('����ʧ�ܣ�');
			  </script>
		</logic:equal>
  </body>
</html>
