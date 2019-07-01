<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <base target="_self"/>
    <title><bean:message key="lable.title" /> �б���ѧ������ѧ��������� </title>
	<meta http-equiv="pragma" content="No-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
	response.setHeader("Prama","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",0);
	 %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function printAction() {
			document.forms[0].action = "zbdx_xszz.do?method=gjzxdkPrint";
			document.forms[0].submit();
		}
		function validate(){
			var xh = $('xh').value.trim();
			var yzbm = $('yzbm').value.trim();
			var sfzh = $('sfzh').value.trim();
			var xzz = $('xzz').value.trim();
			var xsdh = $('xsdh').value.trim();
			var jtdz = $('jtdz').value.trim();
			var jtrk = $('jtrk').value.trim();
			var nsr = $('nsr').value.trim();
			var jtdh = $('jtdh').value.trim();
			
			if(!isNumber(xh)){
				alert("ѧ�ű���Ϊ����!");
				$('xh').focus();
				return false;
			}
			if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				return false;
			}
			if((xsdh != null) && (xsdh != "") && (!isNumber(xsdh))){
				alert("ѧ���绰����Ϊ����!");
				$('xsdh').focus();
				return false;
			}
			if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("�����������Ϊ����!");
				$('yzbm').focus();
				return false;
			}
			if((jtrk != null) && (jtrk != "") && (!isNumber(jtrk))){
				alert("��ͥ�˿ڱ���Ϊ����!");
				$('jtrk').focus();
				return false;
			}
			if((nsr != null) && (nsr != "") && (!isNumber(nsr))){
				alert("���������Ϊ����!");
				$('nsr').focus();
				return false;
			}
			if((jtdh != null) && (jtdh != "") && (!isNumber(jtdh))){
				alert("��ͥ�绰����Ϊ����!");
				$('jtdh').focus();
				return false;
			}
			return true;
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		//onclick="return showCalendar('bysj','y-mm-dd');"
		function checkSfzh(sfzh) {
   			sfzh=sfzh.toLowerCase()
			var OldID;
			if(sfzh.length == 15){
				OldID = sfzh;
				return true;
			}else if(sfzh.length == 18){
				OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
			}else{
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
			var A = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
			var i, j, S;
			var NewID, ID, strNF;
			NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
			S = 0;
			for( i = 0; i <= 16; i++){
				j = NewID.substring(i, i+1) * W[i];
				S = S + j;
			}
			S = S % 11;
			if(sfzh != NewID + A[S]){
				alert("��������ȷ�����֤���룡","",function(){
					obj.select();
					obj.focus();
				});
				return false;
			}
			return true;
		}
	</script>	
  </head>
  
  <body>
		<logic:equal value="false" name="sfksq">
	         	������ʱ��!! 
	    </logic:equal>
	<logic:present name="aa">
	<script>
		alert("ȷ���޸ģ���");
	</script>
	</logic:present>
	    <html:form action="/zbdx_xszz.do?method=getStuInfo" method="post">
	    <input type="hidden" id="url" name="url" value="/zbdx_xszz.do?method=getStuInfo" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />
	    		<logic:present name="doresult">
					<logic:match value="yes" name="doresult">
						<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
					</logic:match>
					<logic:match value="no" name="doresult">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
					</logic:match>
				</logic:present>
	    <div class="title">
			<div class="title_img" id="title_m">
				������ѧ���������
			</div>
		 </div>
			<table style="width:100%"   class="tbstyle">  
				  <tr>
				  	<logic:equal name="userOnLine" value="teacher" scope="session">
						<td height="31"><font color="red">*</font>ѧ  ��</td>
				    	<td height="31">
							<div align="left">
								<html:text name='map' property="xh" styleId="xh"
									onkeypress="" />
								<button onclick="showTopWin('zbdx_stu_info.do?method=stuInfoQuery',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</div>
						</td>
					</logic:equal>
					<logic:notEqual name="userOnLine" value="teacher" scope="session">
						<td height="31"><font color="red">*</font>ѧ  ��</td>
				    	<td height="31">
							<div align="left">
								<html:text name='map' property="xh" styleId="xh"
									onkeypress="" />
							</div>
						</td>
					</logic:notEqual>
				    <td height="31">�Ա�</td>
				    <td><input type="text" readonly style="width:100%" id="xb" name="xb" value="<bean:write name="map" property="xb"/>"></td>
					<td height="31">����</td>
				    <td><input type="text" readonly style="width:100%" id="xm" name="xm" value="<bean:write name="map" property="xm"/>"></td>
					<td height="31">��������</td>
				    <td><input type="text" size="20" readonly style="width:100%" id="csrq" name="csrq" value="<bean:write name="map" property="csrq"/>"></td>
					<td height="31">��ѧʱ��</td>
				    <td><input type="text" size="20" readonly style="width:100%" id="rxny" name="rxny" value="<bean:write name="map" property="rxny"/>"></td>	
				 
				  </tr>
				  <tr>
				  <td width="11%" height="31">
								<div align="left">
									����
								</div>
							</td>
							<td>
								<input type="text" readonly="true" style="width:100%" id="xm" name="xm" value="<bean:write name="map" property="xm"/>">&nbsp;
							</td>
				    <td height="31">ѧ��</td>
				    <td height="31" colspan="3"><input type="text" readonly style="width:100%" id="xz" name="xz" value="<bean:write name="map" property="xz"/>"></td>
					<td height="31">ѧ�����</td>
				    <td height="31"><input type="text" style="width:100%" readonly id="xslb" name="xslb" value="<bean:write name="map" property="xslb"/>"></td>
					<td height="31">�������</td>
				    <td height="31"><select name="hklb" id="hklb" readonly value="<bean:write name="map" property="hklb"/>"><option value="����">����</option><option value="ũ��">ũ��</option></select></td>
					
				  </tr>
				  <tr>
				    <td height="31">Ժ ϵ</td>
				    <td height="31">
				    <input type="text" readonly style="width:100%"
										value="<bean:write name='map' property="xy" />"
										name="xy" id="xy" />
				    </td>
				    <td height="31">רҵ </td>
				    <td height="31" colspan="3"><input type="text" readonly style="width:100%"  name="zymc" id="zymc" value="<bean:write name="map" property="zymc"/>"></td>
					<td height="31">�༶ </td>
				    <td height="31" colspan="3"><input type="text" readonly style="width:100%"  name="bjmc" id="bjmc" value="<bean:write name="map" property="bjmc"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">���֤���� </td>
				    <td height="31" colspan="9"><input type="text" style="width:100%"  name="sfzh" id="sfzh" value="<bean:write name="map" property="sfzh"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">��סַ</td>
				    <td height="31" colspan="5"><input type="text" style="width:100%" name="xzz" id="xzz" value="<bean:write name="map" property="xzz"/>"></td>
				    <td height="31">ѧ���绰 </td>
				    <td height="31" colspan="3"><input type="text" style="width:100%" name="xsdh" id="xsdh" value="<bean:write name="map" property="xsdh"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">��ͥ��ַ</td>
				    <td height="31" colspan="5"><input type="text" style="width:100%" name="jtdz" id="jtdz" value="<bean:write name="map" property="jtdz"/>"></td>
				    <td height="31">�������� </td>
				    <td height="31" colspan="3"><input type="text" style="width:100%" name="yzbm" id="yzbm" value="<bean:write name="map" property="yzbm"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">��ͥ�˿�</td>
				    <td height="31"><input type="text" style="width:100%" name="jtrk" id="jtrk" value="<bean:write name="map" property="jtrk"/>"></td>
				    <td height="31">������</td>
				    <td height="31" colspan="3"><input type="text" style="width:100%" name="nsr" id="nsr" value="<bean:write name="map" property="nsr"/>">Ԫ</td>
					<td height="31">��ͥ�绰</td>
				    <td height="31" colspan="3"><input type="text" style="width:100%" name="jtdh" id="jtdh" value="<bean:write name="map" property="jtdh"/>"></td>
				  </tr>
				  <tr>
				    <td height="31">������</td>
				    <td height="31" colspan="9"><input type="text" style="width:90%" name="dkje" id="dkje" value="<bean:write name="map" property="dkje"/>
				      ">Ԫ</td>
				  </tr>
				   
			����</table>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;width:100%">
							<button class="button2" onclick="if (validate())refreshForm('zbdx_xszz.do?method=gjzxdkSave');"
								style="width:80px">
								�ύ����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="printAction();"
								style="width:80px">
								�����ӡ
							</button>
						</div>
					</center>
	    </html:form>
	    <script src="js/bottomButton.js" ></script>
	    <script language="javascript">
		if($("btn") && !window.dialogArguments){
			$("btn").style.pixelTop = document.body.clientHeight - 35;
			$("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		}
		</script>
  </body>
</html:html>

