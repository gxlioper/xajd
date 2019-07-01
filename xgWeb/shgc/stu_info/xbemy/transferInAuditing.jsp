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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xbemyStuStatus.do" method="post">		
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ����Ϣ - ����ѧ��������-��תѧ���
				</div>
			</div>			
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							ת�뵥�����
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" >
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
						<input type="hidden" value="<bean:write name="rs" property="sqrq" />" name="sqrq" id="sqrq"/>
					</td>
					<td align="right">
						ת��ѧУ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zcxxmc" />						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						ת��רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zczymc" />						
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">&nbsp;
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						ת���꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="zcnj" />						
					</td>	
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						׼��֤�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zkzh" />
					</td>
					<td align="right">
						ת��ѧ����Σ�</td>
					<td align="left">
						<bean:write name="rs" property="zcxlcc" />
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						���壺
					</td>
					<td align="left">
						<bean:write name="rs" property="mzmc"/>						
					</td>
					<td align="right">
						ת��ѧ�ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zcxz"/>
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdh"/>
					</td>
					<td align="right">
						ת��ѧУ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrxxmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ѧʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="rxsj"/>
					</td>
					<td align="right">
						ת��<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrzymc"/>
					</td>				
				</tr>
				<tr  style="height:22px">
					<td align="right">
						ת��רҵ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrzymc"/>
					</td>	
				    <td align="right">ת��ѧ�ƣ�</td>
				    <td align="left">
						<bean:write name="rs" property="zrxz"/>
					</td>
				</tr>	
				<tr>
					<td align="right">ת��༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="zrbjmc"/>
					</td>
					<td align="right">ת��ѧ����Σ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zrxlcc"/>
					</td>					
				</tr>
				<tr>
					<td align="right">ת���꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="zrnj"/>
					</td>
					<td align="right">ѧ��״̬��
					</td>
					<td align="left">
						<bean:write name="rs" property="xjztm"/>
					</td>					
				</tr>
				<tr>
					<td align="right">��������
					</td>
					<td align="left">
						<bean:write name="rs" property="cym"/>
					</td>
					<td align="right">����רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="fxzy"/>
					</td>					
				</tr>
				<tr>
					<td align="right">רҵ����
					</td>
					<td align="left">
						<bean:write name="rs" property="zyfx"/>
					</td>
					<td align="right">����רҵ����
					</td>
					<td align="left">
						<bean:write name="rs" property="fxzyfx"/>
					</td>					
				</tr>
				<tr>
					<td align="right">��������
					</td>
					<td align="left">
						<bean:write name="rs" property="pyfx"/>
					</td>
					<td align="right">ѧУ���ڵأ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xxszd"/>
					</td>					
				</tr>
				<tr>
					<td align="right">רҵ���
					</td>
					<td align="left">
						<bean:write name="rs" property="zylb"/>
					</td>	
					<td align="right">��ǰ���ڼ���
					</td>
					<td align="left">
						<bean:write name="rs" property="dqszj"/>
					</td>									
				</tr>
				<tr>
					<td align="right">��ѧ���ͣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="bxlx"/>
					</td>	
					<td align="right">��ѧ��ʽ��
					</td>
					<td align="left">
						<bean:write name="rs" property="bxxs"/>
					</td>									
				</tr>
				<tr>
					<td align="right">ѧϰ��ʽ��
					</td>
					<td align="left">
						<bean:write name="rs" property="xxxs"/>
					</td>	
					<td align="right">�������ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zsjj"/>
					</td>									
				</tr>
				<tr>
					<td align="right">�����ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="ksh"/>
					</td>	
					<td align="right">��Դ�أ�
					</td>
					<td align="left">
						<bean:write name="rs" property="syd"/>
					</td>									
				</tr>
				<tr>
					<td align="right">������ò��
					</td>
					<td align="left">
						<bean:write name="rs" property="zzmmmc"/>
					</td>	
					<td align="right">��ѧ��ʽ��
					</td>
					<td align="left">
						<bean:write name="rs" property="rxfs"/>
					</td>									
				</tr>
				<tr>
					<td align="right">���֤�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="sfzh"/>
					</td>	
					<td align="right">���᣺
					</td>
					<td align="left">
						<bean:write name="rs" property="jg"/>
					</td>									
				</tr>
				<tr>
					<td align="right">�����أ�
					</td>
					<td align="left">
						<bean:write name="rs" property="csd"/>
					</td>	
					<td align="right">�������ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="csrq"/>
					</td>									
				</tr>
				<tr>
					<td align="right">У��������
					</td>
					<td align="left">
						<bean:write name="rs" property="xzxm"/>
					</td>	
					<td align="right">��ҵ���ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="byrq"/>
					</td>									
				</tr>
				<tr>
					<td align="right">�Ͻ�ҵ���ۣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="bjyjl"/>
					</td>	
					<td align="right">֤���ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zsbh"/>
					</td>									
				</tr>
				<tr>
					<td align="right">֤���ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zsbh"/>
					</td>	
					<td align="right">֤�����кţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="zsxlh"/>
					</td>						
				</tr>
				<tr>
					<td align="right">ѧλ��
					</td>
					<td align="left">
						<bean:write name="rs" property="xw"/>
					</td>	
					<td align="right">ѧλ֤���ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xwzsbh"/>
					</td>						
				</tr>
				<tr>
					<td align="right">�滻��ʶ��
					</td>
					<td align="left">
						<bean:write name="rs" property="thbs"/>
					</td>	
					<td align="right">ѧλ֤�����кţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xwzsxlh"/>
					</td>						
				</tr>
				<tr>
					<td align="right">��˱�ǣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="shbj"/>
					</td>	
					<td align="right">��ӡ��ǣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dybj"/>
					</td>						
				</tr>
				
				<tr>
					<td align="right">��ע��
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="bz"/>
					</td>						
				</tr>
				<tr style="height:22px">
				  <td align="right">�������ɣ�</td>
				  <td colspan="3" align="left">
				  <bean:write name="rs" property="sqly"/>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right"> ��ˣ�</td>
				  <td align="left">
				  	<html:select property="xxsh" name="rs"> 
				  		<html:option value="δ���">δ���</html:option> 
				  		<html:option value="ͨ��">ͨ��</html:option> 
				  		<html:option value="��ͨ��">��ͨ��</html:option> 
				  </html:select> 
				  </td>				 
				  <td align="right">
				  	�Զ�ת��ѧ��������Ϣ�⣺
				  </td>
				  <td align="left">
				  <html:radio property="zdzrbj" value="1">��</html:radio>
				  <html:radio property="zdzrbj" value="0">��</html:radio>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">ת��ѧУ����� </td>
				  <td align="left" colspan="3">
				  	<html:textarea property="zcxxyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">ת��ѧУ�����</td>
				  <td align="left" colspan="3">
					<html:textarea property="zrxxyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">ת��ʡ�����������</td>
				  <td align="left" colspan="3">
					<html:textarea property="zcsjytyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">					
					<td align="right">ת��ʡ�����������</td>
					<td align="left" colspan="3">
					<html:textarea property="zrsjytyj" name="rs" style="width:100%;height:45px"/>
					</td>
				</tr>
				<html:hidden property="zxlx" name="rs"/>
			</table>
			<div class="buttontool" align="center">			
			<logic:equal value="yes" name="writeAble">
				<logic:notEqual value="xy" name="userType" scope="session">
				<button class="button2"
					onclick="refreshForm('xbemyStuStatus.do?method=stuTransferAuditing&doType=save')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				</logic:notEqual>				
				<logic:equal value="xy" name="userType" scope="session">
					<button class="button2"
					style="width:80px" id="buttonSave" disabled="disabled">
					�� ��
				</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			  </logic:equal>
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
