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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType =""; 
			if($("userType")){
			  userType = document.getElementById('userType').value;
			}
			var xxsh =""; 
			if($("xxsh")){
			   xxsh =document.getElementById('xxsh').value;
			} 
			var xysh =""; 
			if($("xysh")){
			   xysh =document.getElementById('xysh').value;
			}
			var fdyshyj =""; 
			if($("fdyshyj")){
			   fdyshyj =document.getElementById('fdyshyj').value;
			}
			var xxshyj = "";
			if($("xxshyj")){
			    xxshyj =document.getElementById('xxshyj').value;
			}
			var xyshyj = "";
			if($("xyshyj")){
			    xyshyj = document.getElementById('xyshyj').value;
			}
			if(("δ���"  != xxsh ) && (userType == "xy")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			if(("δ���" != xysh ) && (userType == "fdy")){
				alert("<bean:message key="lable.xsgzyxpzxy" />����ˣ��������޸���˽��!");
	          	return false;
			}
			if($("fdyshyj")){
	         	if(fdyshyj.length > 100){	         
	          		 alert("����Ա���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if($("xyshyj")){
	         	if(xyshyj.length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if($("xxshyj")){
	         	if(xxshyj.length > 100){	         
	          		 alert("ѧУ���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/jhzyjsxy_xszz.do?method=gjlzjxjshOne&doType=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������־��ѧ��
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
							ѧ��
					</td>
					<td width="34%">
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxrq"/>
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
					   <bean:write name='rs' property="xz" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
					<td>
						<div align="center">
							���
						</div>
					</td>
					<td>
					   <bean:write name='rsJxj' property="nd" />
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rsJxj' property="nd"/>">						
					</td>
				</tr>
				<tr>
					<tr>
				    <td><div align="center">
				    ������ֽ���	
				    </div>					
					</td>
					<td	colspan="3">					
					<bean:write name='rsJxj' property="chhzjl" />						
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="jthk" />						  
					</td>
					<td>
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="jtzrks" />				
					</td>
				  </tr>
                  <tr>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>	
					<bean:write name='rsJxj' property="jtyzsr" />	
					</td>
					<td>
						<div align="center">
							�˾�������
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="rjysr" />
					</td>
				</tr>
                 <tr>
					<td>
						<div align="center">
							������Դ
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="srly" />
					</td>
					<td>
						<div align="center">
							��ͥ��ַ
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="jtzz" />
					</td>
				</tr>
                <tr>					
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="yzbm" />
					</td>
					<td>
						<div align="center">
							������ϵ�绰
						</div>
					</td>
					<td>
					<bean:write name='rsJxj' property="lxdh" />
					</td>
				</tr>
                  <tr>
					<td>
					<div align="center">
						ѧϰ�����
					</div>
					</td>
					<td colspan="3">
					<div>
					��ѧ����޿γ���<u>&nbsp;<bean:write name='rsJxj' property="gxnbxkcs" />&nbsp;</u>�ţ����У�
					����<u>&nbsp;<bean:write name='rsJxj' property="yxkcs" />&nbsp;</u>�ţ�����
					<u>&nbsp;<bean:write name='rsJxj' property="lhkcs" />&nbsp;</u>��
					</div>
					<div>
					�ɼ�����(���ڰ༶)<u>&nbsp;<bean:write name='rsJxj' property="cjpm" />&nbsp;</u>(����/������)
					</div>
					<div>
					�ۺϿ����ɼ�<u>&nbsp;<bean:write name='rsJxj' property="zhkpcj" />&nbsp;</u>�֣�����&nbsp;
					<u>&nbsp;<bean:write name='rsJxj' property="zhkppm" />&nbsp;</u>(����/������)
					</div>					
					</td>
				</tr>														
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rsJxj" property="sqly"/>
					</td>
				</tr>
				<logic:equal name="userType" value="fdy">
					<tr>
						<td>
							<div align="center">
								����Ա���
							</div>
						</td>
						<td>
							<html:select name="rsJxj" property="fdysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name="rsJxj" property="xysh"/>">
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name="rsJxj" property="xxsh"/>">
						</td>
						<td>
							<div align="center">
								���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="fdyshsj"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								����Ա������								
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rsJxj" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name="rsJxj"property="xyshyj"/>">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name="rsJxj" property="xxshyj"/>">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
					<tr>
						<td>
							<div align="center">
								����Ա���
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="fdysh"/>
						</td>
						<td>
							<div align="center">
								����Ա���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="fdyshsj"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								����Ա������
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rsJxj" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')" readonly="true"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<html:select name="rsJxj" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name="rsJxj" property="xxsh"/>">
						</td>
						<td>
							<div align="center">
								���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="xyshsj"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rsJxj" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name="rs" property="xxshyj"/>">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<tr>
						<td>
							<div align="center">
								����Ա���
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="fdysh"/>
						</td>
						<td>
							<div align="center">
								����Ա���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="fdyshsj"/>
						</td>
					</tr>
										<tr>
						<td>
							<div align="center">
								����Ա������
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rsJxj" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')" readonly="true"/>
							<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
						</td>
					</tr>
					
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="xysh"/>
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="xyshsj"/>
						</td>
					</tr>
										<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />������
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rsJxj" property="xyshyj" rows='5' style="width:100%" readonly="true"/>
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name="rs" property="xxshyj"/>">
						</td>
					</tr>
										
					<tr>
						<td>
							<div align="center">
								ѧУ���
							</div>
						</td>
						<td>
							<html:select name="rsJxj" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name="rsJxj" property="xysh"/>">
						</td>
						<td>
							<div align="center">
								���ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rsJxj" property="xxshsj"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								ѧУ������
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rsJxj" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name="rs" property="xyshyj"/>">
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
