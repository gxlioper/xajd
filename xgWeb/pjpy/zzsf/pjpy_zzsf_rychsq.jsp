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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpy_zzsf_rychsq" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �����ƺ����� - ��д�����
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
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/credit_apply.do" />
				<table width="98%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="8" align="center">
								<b>��д�����</b>							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td width="8%" colspan="2" align="right">
								<font color="red">*</font>ѧ�ţ�</td>
							<td width="15%" align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" style="width:70%"/>
								<button type="button" onClick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
ѡ��								</button></td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td width="8%" colspan="2" align="right">
								<font color="red">*</font>ѧ�ţ�</td>
							<td width="15%" align="left"><html:hidden name='rs' property="xh" styleId="xh" />
								<bean:write name='rs' property="xh" /></td>
						</logic:equal>
						<td width="16%" align="right" colspan='2'>ѧ�꣺</td>
						<td width="2%" align="left" colspan='3'>
							<bean:write name='rs' property="xn" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							������</td>
						<td align="left">
							<bean:write name='rs' property="xm" /></td>
						<td colspan="2" align="right">
							ѧ�ڣ�</td>
						<td align="left" colspan='3'>
							<bean:write name='rs' property="xq" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							�Ա�</td>
						<td align="left">
							<bean:write name='rs' property="xb" /></td>
						<td colspan="2" align="right">
							<font color="red">*</font>���������ƺţ�</td>
						<td align="left" colspan='3'>
							<html:select property="xmdm" styleId="rychdm">
								<option value=""></option>
								<html:options collection="rychList" property="rychdm"
									labelProperty="rychmc" />
							</html:select></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							�꼶��</td>
						<td align="left">
							<bean:write name='rs' property="nj" /></td>
						<td colspan="2" align="right">����ˮƽ��</td>
						<td align="left" colspan='3'><html:text name='rs' property="wysp" styleId="a"  style="width:70%"/></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />						</td>
						<td colspan="2" align="right">�ֻ����룺</td>
						<td align="left" colspan='3'><html:text name='rs' property="sjhm" styleId="a" style="width:70%" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							רҵ��</td>
						<td align="left">
							<bean:write name='rs' property="zymc" /></td>
						<td colspan="2" align="right">
							����ְ��</td>
						<td align="left" colspan='3'>
							<html:text name='rs' property="drzw" styleId="a" style="width:70%" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							�༶��</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" /></td>
						<td colspan="2" align="right">������������</td>
						<td align="left" colspan='3'><html:text name='rs' property="tydbqk" style="width:70%" styleId="a" /></td>
					</tr>

					<tr style="height:22px">
						<td colspan="2" align="right">
							��Դ�أ�</td>
						<td align="left">
							<html:text name='rs' property="syd" style="width:80%" styleId="a" /></td>
						<td colspan="2" align="right">����ְ��</td>
						<td align="left" colspan='3'><html:text name='rs' property="byzx" style="width:70%" styleId="a" /></td>
					</tr>
					<logic:equal value="10402" name="xxdm">
						<tr style="height:22px">
					  <td width="5%" height="75" rowspan="6" align="center"><p>��<p>��<p>��<p>��</td>
					  <td width="10%" rowspan="2" align="right">&nbsp;</td>
					  <td colspan="3" align="left"><div align="center">ѧϰ���</div></td>
				      <td colspan="4" align="left"><div align="center">�ۺϿ������</div></td>
			      </tr>
					<tr style="height:22px">
					  <td align="center">ѧϰ�ɼ�</td>
					  <td align="left" width="11%"><div align="center">�༶����</div></td>
				      <td width="11%" align="left"><div align="center">רҵ����</div></td>
				      <td width="8%" align="left"><div align="center">�����ܷ�</div></td>
				      <td width="12%" align="left"><div align="center">�༶����</div></td>
				      <td width="12%" colspan="2" align="left"><div align="center">רҵ����</div></td>
			      </tr>
				<tr style="height:22px">
					  <td align="right">��һѧ�꣺</td>
					  <td align="left"><html:text name="rs" property="pjcj1" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text name='rs' property="bjcjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">�ڶ�ѧ�꣺</td>
					  <td align="left"><html:text name="rs" property="pjcj2" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text  name='rs' property="bjcjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx2"  maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td colspan="2" align="left"><html:text name='rs' property="zhkpzypx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">����ѧ�꣺</td>
					  <td align="left"><html:text name="rs" property="pjcj3" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text  name='rs' property="bjcjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">����ѧ�꣺  </td>
					  <td align="left"><html:text name="rs" property="pjcj4" maxlength="6" style="width:100px"></html:text></td>
				      <td align="left"><html:text  name='rs' property="bjcjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							��ͥ��ַ��
						</td>
						<td colspan="6" align="left"><html:text name='rs' style="width:98%" property="jtdz" styleId="a" maxlength="50"/></td>
					</tr>
					</logic:equal>
					<logic:notEqual value="10402" name="xxdm">
						<tr style="height:22px">
					  <td width="5%" height="75" rowspan="6" align="center"><p>��<p>��<p>��<p>��</td>
					  <td width="10%" rowspan="2" align="right">&nbsp;</td>
					  <td colspan="2" align="left"><div align="center">ѧϰ���</div></td>
				      <td colspan="4" align="left"><div align="center">�ۺϿ������</div></td>
			      </tr>
					<tr style="height:22px">
					  <td align="left"><div align="center">�༶����</div></td>
				      <td width="13%" align="left"><div align="center">רҵ����</div></td>
				      <td width="8%" align="left"><div align="center">�����ܷ�</div></td>
				      <td width="12%" align="left"><div align="center">�༶����</div></td>
				      <td width="12%" colspan="2" align="left"><div align="center">רҵ����</div></td>
			      </tr>
				<tr style="height:22px">
					  <td align="right">��һѧ�꣺</td>
				      <td align="left"><html:text name='rs' property="bjcjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx1" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">�ڶ�ѧ�꣺</td>
				      <td align="left"><html:text  name='rs' property="bjcjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx2"  maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%" /></td>
			          <td colspan="2" align="left"><html:text name='rs' property="zhkpzypx2" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">����ѧ�꣺</td>
				      <td align="left"><html:text  name='rs' property="bjcjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx3" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%"/></td>
		          </tr>
					<tr style="height:22px">
					  <td align="right">����ѧ�꣺  </td>
				      <td align="left"><html:text  name='rs' property="bjcjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zycjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
				      <td align="left"><html:text  name='rs' property="zhkpzf4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td align="left"><html:text  name='rs' property="zhkpbjpx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:80%"/></td>
			          <td colspan="2" align="left"><html:text  name='rs' property="zhkpzypx4" maxlength="3"  onclick="alertMessage(this)" onmousemove="document.getElementById('message').style.display='none'" style="width:70%" /></td>
		          </tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							��ͥ��ַ��
						</td>
						<td colspan="6" align="left"><html:text name='rs' style="width:98%" property="jtdz" styleId="a" maxlength="50"/></td>
					</tr>
					</logic:notEqual>
					<tr style="height:22px">
						<td colspan="2" align="right">
							��Ҫ�¼���</td>
							
							
						<td colspan="6" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="zysj" styleId="a" /></td>
					</tr>
					<tr style="height:22px">
						<td colspan="2" align="right">
							�������</td>
							
							
						<td colspan="6" align="left"><html:textarea rows="5" name='rs' style="width:98%" property="hjqk" styleId="a" /></td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="dataDoSaveApply1('/xgxt/pjpy_zzsf_rych_save.do','rychdm','rych')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"  onclick="window.open('/xgxt/pjpy_zzsf_rychpsdjb.do?xh='+document.getElementById('xh').value+'&rychdm='+document.getElementById('rychdm').value)">
						�� ӡ �� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nocondi">
					<script>
    alert("����������������,����ʧ�ܣ�");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	window.dialogArguments.document.all("search_go").click();
    	Close();
    }
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	<div style="display:none;color: red;font-size: 14px;background-color: yellow" id="message" name="message"></div>	
	</body>
</html>
