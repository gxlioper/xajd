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
<html:html >
<base target="_self" />
  <head>
    
    
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<% 
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","no-cache");	
	response.setDateHeader("Expires", 0);	
	%>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
 </head>
  
<body onload="pageLoadBbsq()">
	<html:form action="/bbsqb">
      <div class="title">
        <div class="title_img" id="title_m">
                ��ǰ����λ�ã��ճ����� - ���� - ��������
        </div>
      </div>
      <logic:present name="dors">
      		<logic:equal value="true" name="dors">
      			<script type="text/javascript">
      				alert("����ɹ���");
      			</script>
      		</logic:equal>
      		<logic:equal value="false" name="dors">
      			<script type="text/javascript">
      				alert("����ʧ�ܣ�");
      			</script>
      		</logic:equal>
      </logic:present>
      <input type="hidden" id="bblx" name="bblx" value="<bean:write name="bblx"/>"/>
      <input type="hidden" id="url" name="url" value="/sjcz/bbsqb.jsp" />
      <input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-zymc-bjmc-xymc-lxdh" />
      	<div class="xxk"> 
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request">
							<ul>
								<li id="<bean:write name='card' property='en'/>l"
									class="xxk_off_l"></li>
								<li id="<bean:write name='card' property='en'/>m"
									onclick="onclickPage(this)" class="xxk_off_m">
									&nbsp;
									<bean:write name='card' property='cn' />
									&nbsp;
								</li>
								<li id="<bean:write name='card' property='en'/>r"
									class="xxk_off_r"></li>
							</ul>							
						</logic:iterate>
				    </logic:notEmpty>					
		</div>
			    
		<%--ѧ��֤����--begin--%>
		
      	<div name="xsz" id="xsz" style="display:none;">
      		<div align="center" style="font-size:16px">ѧ��֤���������</div>
			<table width="98%" class="tbstyle" style="font-size:14px" name="xsz" id="xsz">
			  <tr>
			    <td width="7%" style="height:30"><div align="center"><font color="red">*</font>ѧ��</div></td>
			    <td width="27%" colspan="2">
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			    	<html:text name='rs' property="xh" styleId="xh" 
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
				</logic:equal>
				<logic:equal value="student" name="userOnLine" scope="session">
					<html:text name="rs" property="xh" style="width:95%"/>
				</logic:equal>				
			    </td>
			    <td width="7%"><div align="center">�Ա�</div></td>
			    <td width="25%"><html:text name="rs" property="xb" style="width:95%" readonly="true"/></td>
			    <td width="9%"><div align="center">����</div></td>
			    <td width="25%"><html:text name="rs" property="jg" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td style="height:30" ><div align="center">ѧ��</div></td>
			    <td style="height:30" colspan="2"><html:text name="rs" property="xz" style="width:95%" readonly="true"/></td>
			    <td style="height:30"><div align="center">����</div></td>
			    <td style="height:30"><html:text name="rs" property="xm" style="width:95%" readonly="true"/></td>
			    <td style="height:30"><div align="center">��ѧʱ��</div></td>
			    <td style="height:30"><html:text name="rs" property="rxsj" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="30" colspan="2"><div align="center">רҵ��༶</div></td>
			    <td height="30" colspan="5"><html:text name="rs" property="zybj" style="width:95%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td height="30" colspan="2"><div align="center">��ͥ��ַ</div></td>
			    <td height="30" colspan="5"><html:text name="rs" property="jtdz" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="138"><div align="center">��<br>
			      ��<br>
			      ��<br>
			    ��</div></td>
			    <td height="138" colspan="6"><html:textarea name="rs" property="sqly" cols="100" rows="5" style="height:100%"></html:textarea><br>
			    </td>
			  </tr>
			  
			</table>
     <p>&nbsp;&nbsp;&nbsp;&nbsp;ע����ȡʱ����Я���������Ͻ�</p>
      </div>
      <%--ѧ��֤����--end--%>
      
      
      
      <%--һ��ͨ����--begin--%>
      <div name="ykt" id="ykt" style="display:none;">
			<div align="center" style="font-size:16px">һ��ͨ���������</div>
			<table width="98%" height="475" class="tbstyle" style="font-size:14px">
			  <tr>
			    <td width="18%" height="15%"><div align="center"><font color="red">*</font>ѧ��</div></td>
			    <td width="30%" height="15%" colspan="2">
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			    	<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
				</logic:equal>
			    <logic:equal value="student" name="userOnLine" scope="session">
			    	<html:text name="rs" property="xh" style="width:95%"/>
			    </logic:equal>	
			    </td>
			    <td width="12%" height="15%"><div align="center">����</div></td>
			    <td width="38%" height="15%"><html:text name="rs" property="xm" style="width:95%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td height="15%"><div align="center">�༶</div></td>
			    <td height="15%" colspan="2"><html:text name="rs" property="bjmc" style="width:95%" readonly="true"/></td>
			    <td height="15%"><div align="center">��ϵ��ʽ</div></td>
			    <td height="15%"><html:text name="rs" property="lxfs" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="15%"><div align="center">֤����</div></td>
			    <td height="15%" colspan="2"><html:text name="rs" property="zjh" style="width:95%"/></td>
			    <td height="15%">&nbsp;</td>
			    <td height="15%">&nbsp;</td>
			  </tr>
			  <tr>
			    <td><div align="center">��ʧ���</div></td>
			    <td colspan="4"><div align="center">
			      <html:radio property="gsqk" value="yes">&nbsp;&nbsp;&nbsp;&nbsp;�ѹ�ʧ&nbsp;&nbsp;</html:radio>
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <html:radio property="gsqk" value="no">&nbsp;&nbsp;&nbsp;&nbsp;δ��ʧ&nbsp;&nbsp;</html:radio>
			      </div></td>
			  </tr>
			  <tr>
			    <td height="27" rowspan="2">��������</td>
			    <td height="27" colspan="2"><p align="center">&nbsp;&nbsp;
			      <html:radio property="blqk" value="bb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</html:radio>
			    </td>
			    <td align=center>����ԭ��</td>
			    <td><html:text name="rs" property="bbyy" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td height="27" colspan="2"><p align="center">&nbsp;&nbsp;
			        <html:radio property="blqk" value="xb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�°�</html:radio> 
			    </td>
			    <td align=center>�°�ԭ��</td>
			    <td><html:text name="rs" property="xbyy" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td height="23"><div align="center">������</div></td>
			    <td height="23" colspan="2"><html:text name="rs" property="slr" style="width:95%"/></td>
			    <td height="23"><div align="center">����</div></td>
			    <td height="23"><html:text name="rs" property="slrgh" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="15%"><div align="center">�������</div></td>
			    <td height="15%" colspan="2"><html:text name="rs" property="gzbc" style="width:95%"/></td>
			    <td height="15%"><div align="center">������</div></td>
			    <td height="15%"><html:text name="rs" property="slbh" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="39" rowspan="2">�������</td>
			    <td height="39" colspan="4">&nbsp;&nbsp;<html:checkbox property="bbfys">
			      &nbsp;&nbsp;&nbsp;��������� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
			      <html:checkbox property="xkbl">&nbsp;&nbsp;&nbsp;
			      �¿����� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
			     <html:checkbox property="xxyhd">&nbsp;&nbsp;&nbsp;
			      ��Ϣ�Ѻ˶� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
			     <html:checkbox property="yclhzjxgbm">&nbsp;&nbsp;&nbsp;
			      �Ѵ����ת����ز���</html:checkbox></td>
			  </tr>
			  <tr>
			    <td height="40" colspan="4" align="center" id="thtd">
			       <html:radio property="bjf" value="yes" onclick="var thistd=document.getElementById('thtd');thistd.colSpan='4';document.getElementById('yy1').style.display='none';document.getElementById('yy2').style.display='none';">&nbsp;&nbsp;�Ѱ��</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       <html:radio property="bjf" value="yes" onclick="var thistd=document.getElementById('thtd');thistd.colSpan='1';document.getElementById('yy1').style.display='block';document.getElementById('yy2').style.display='block';">&nbsp;&nbsp;δ��� </html:radio> 
			    </td>
			    <td height="40" name="yy1" id="yy1" style="display: none" colspan="2" align="center">ԭ��</td>
			    <td height="40" name="yy2" id="yy2" style="display: none"><html:text name="rs" property="yy" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td height="50" rowspan="3">�������</td>
			  </tr>
			  <tr>
			    <td height="23"><div align="center">������ǩ����</div></td>
			    <td height="23" colspan="2"><html:text name="rs" property="dlr" /></td>
			    <td height="23">&nbsp;</td>
			  </tr>
			  <tr>
			    <td height="23"><div align="center">������֤�����룺</div></td>
			    <td height="23" colspan="3"><html:text name="rs" property="dlrzjh" style="width:80%"/></td>
			  </tr>
			  <tr>
			    <td>��ע</td>
			    <td colspan="4"><html:textarea name="rs" property="yktbz" cols="80" rows="5" style="width:98%"/></td>
			  </tr>
			</table>
      </div>
    <%--һ��ͨ����--end--%>  
    
    
    <%--���Żݿ�����--begin--%>  
      <div name="hcpyh" id="hcpyh" style="display:none;">
      		<div align="center" style="font-size:16px">���Żݿ����������<br>
  
			</div>
			<table width="98%" class="tbstyle" height="405" style="font-size:14px" name="hcpyh" id="hcpyh">
			  <tr>
			    <td width="14%"><div align="center"><font color="red">*</font>ѧ��</div></td>
			    <td width="36%">
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			    	<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
				</logic:equal>
			    <logic:equal value="student" name="userOnLine" scope="session">
			    	<html:text name="rs" property="xh" style="width:95%"/>
			    </logic:equal>
			    </td>
			    <td width="14%"><div align="center">����</div></td>
			    <td width="36%"><html:text name="rs" property="xm" /></td>
			  </tr>
			  <tr>
			    <td><div align="center">�༶</div></td>
			    <td><html:text name="rs" property="bjmc" /></td>
			    <td><div align="center">���յ�վ</div></td>
			    <td><html:text name="rs" property="hczdz" /></td>
			  </tr>
			  <tr>
			    <td colspan="1"><div align="center">��ͥ��ַ</div></td>
			    <td colspan="3"><html:text name="rs" property="jtdz" style="width:100%"/></td>
			  </tr>
			  <tr>
			    <td><div align="center">��������</div></td>
			    <td colspan="3"><html:textarea name="rs" property="sqly" cols="100" rows="10"></html:textarea> </td>
			  </tr>
			  <tr>
			    <td height="84">�������</td>
			    <td colspan="3">
			    
			      <p>&nbsp;&nbsp;&nbsp;
			        <input name="hdzj" type="checkbox" value="yes"> &nbsp;&nbsp;
				�Ѻ˶����֤����ѧ��֤��</p>
			    <p>&nbsp;&nbsp;&nbsp;
			      <input name="yzxtzhd" type="checkbox" value="yes"> &nbsp;&nbsp;
			      ����ϵͳ�к˶ԣ��ɷ���
			</p>
			    <p>&nbsp;&nbsp;&nbsp;
			      <input name="ydj" type="checkbox" value="ydj"> &nbsp;&nbsp;
			�ѵǼ�</p>
			    </td>
			  </tr>
			  <tr>
			    <td height="121">�������</td>
			    <td colspan="3"><p>&nbsp;&nbsp;&nbsp;
			        <input name="ff" type="checkbox" value="yes"> &nbsp;&nbsp;
			    &nbsp;���ţ����տ�����Ԫ��</p>
			    <p>&nbsp;&nbsp;&nbsp;
			      <input name="qt" type="checkbox" value="yes"> &nbsp;&nbsp;
			    &nbsp;����</p>
				</td>
			  </tr>
			  <tr>
			    <td>��ע</td>
			    <td colspan="3"><html:textarea name="rs" property="hcpbz" cols="100" rows="5"/></td>
			  </tr>
			</table>
      </div>
      <%--���Żݿ�����--end--%>
      </html:form>
    <div class="buttontool">
    	<button type="button" class="button2" onclick="chkNull2();document.forms[0].action='bbsqb.do?doType=save&bblx='+document.getElementById('bblx').value;document.forms[0].submit();">�ύ����</button>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<button type="button" class="button2" onclick="document.forms[0].action='';document.forms[0].submit();">�����ӡ</button>
    </div>
</body>
</html:html>
