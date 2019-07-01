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
    <title><bean:message key="lable.title" />  </title>
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
	<script language="javascript" src="js/webPrint.js"></script>
	<script language="javascript">
		function validate(){
			var xh = $('xh').value;
			var yzbm = $('yzbm').value;
			var sfzh = $('sfzh').value;
			var xzz = $('xzz').value;
			var xsdh = $('xsdh').value;
			var jtdz = $('jtdz').value;
			var jtrk = $('jtrk').value;
			var nsr = $('nsr').value;
			var jtdh = $('jtdh').value;
			
			if(!isNumber(xh)){
				alert("ѧ�ű���Ϊ����!");
				return false;
			}/*else if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
				alert("���ҵ绰����Ϊ����!");
				return false;
			}else if((yzbm != null) && (yzbm != "") && (!isNumber(yzbm))){
				alert("�����������Ϊ����!");
				return false;
			}else if((sfzh != null) && (sfzh != "") && (!checkSfzh(sfzh))){
				return false;
			}else if((jtcy1_ysr != null) && (jtcy1_ysr != "") && (!isNumber(jtcy1_ysr))){
				alert("��ͥ��Ա1���������Ϊ����!");
				return false;
			}else if((jtcy2_ysr != null) && (jtcy2_ysr != "") && (!isNumber(jtcy2_ysr))){
				alert("��ͥ��Ա2���������Ϊ����!");
				return false;
			}else if((jtcy3_ysr != null) && (jtcy3_ysr != "") && (!isNumber(jtcy3_ysr))){
				alert("��ͥ��Ա3���������Ϊ����!");
				return false;
			}else if((jtcy4_ysr != null) && (jtcy4_ysr != "") && (!isNumber(jtcy4_ysr))){
				alert("��ͥ��Ա4���������Ϊ����!");
				return false;
			}else if((jtcy5_ysr != null) && (jtcy5_ysr != "") && (!isNumber(jtcy5_ysr))){
				alert("��ͥ��Ա5���������Ϊ����!");
				return false;
			}else if((jttgje != null) && (jttgje != "") && (!isNumber(jttgje))){
				alert("��ѧ���ͥ�ṩ������Ϊ����!");
				return false;
			}else if((zxje != null) && (zxje != "") && (!isNumber(zxje))){
				alert("��ѧ����ѧ�����Ϊ����!");
				return false;
			}else if((jxje != null) && (jxje != "") && (!isNumber(jxje))){
				alert("��ѧ�꽱ѧ�����Ϊ����!");
				return false;
			}else if((qgzxje != null) && (qgzxje != "") && (!isNumber(qgzxje))){
				alert("��ѧ���ڹ���ѧ�������Ϊ����!");
				return false;
			}else if((xnwxdkje != null) && (xnwxdkje != "") && (!isNumber(xnwxdkje))){
				alert("��ѧ��У����Ϣ��ѧ�����Ϊ����!");
				return false;
			}else if((qtsrje != null) && (qtsrje != "") && (!isNumber(qtsrje))){
				alert("��ѧ�������������Ϊ����!");
				return false;
			}else if((zxdkje != null) && (zxdkje != "") && (!isNumber(zxdkje))){
				alert("��ѧ�������������Ϊ����!");
				return false;
			}else if((yffzxdkje != null) && (yffzxdkje != "") && (!isNumber(yffzxdkje))){
				alert("��ѧ�����ѷ��Ž�����Ϊ����!");
				return false;
			}else if((zzff1qsje != null) && (zzff1qsje != "") && (!isNumber(zzff1qsje))){
				alert("��һ־Ը��ʼ������Ϊ����!");
				return false;
			}else if((zzff1jsje != null) && (zzff1jsje != "") && (!isNumber(zzff1jsje))){
				alert("��һ־Ը����������Ϊ����!");
				return false;
			}else if(sqzzly != null){
	         	if(sqzzly.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�����������ɲ��ܴ���100���ַ�");
	          		 return false;
	       		 }
			}*/
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
			<table style="width:100%" id="rsTable" class="tbstyle">  
				  <tr>
				    <td width="11%" height="31">���� </td>
				    <td>
					&nbsp;<bean:write name="map" property="xm"/>&nbsp;
				    </td>
				    <td height="31">�Ա�</td>
				    <td>&nbsp;<bean:write name="map" property="xb"/>&nbsp;</td>
					<td height="31">����</td>
				    <td>&nbsp;<bean:write name="map" property="xm"/>&nbsp;</td>
					<td height="31">��������</td>
				    <td>&nbsp;<bean:write name="map" property="csrq"/>&nbsp;</td>
					<td height="31">��ѧʱ��</td>
				    <td>&nbsp;<bean:write name="map" property="rxny"/>&nbsp;</td>
					
				  </tr>
				  <tr>
				  <logic:equal name="userOnLine" value="teacher" scope="session">
						<td height="31">ѧ  ��</td>
				    	<td height="31">
							&nbsp;<bean:write name="map" property="xh"/>&nbsp;
						</td>
					</logic:equal>
				    <td height="31">ѧ��</td>
				    <td height="31" colspan="3">&nbsp;<bean:write name="map" property="xz"/>&nbsp;</td>
					<td height="31">ѧ�����</td>
				    <td height="31">&nbsp;<bean:write name="map" property="xslb"/>&nbsp;</td>
					<td height="31">�������</td>
				    <td height="31">&nbsp;<bean:write name="map" property="hklb"/>&nbsp;</td>
					
				  </tr>
				  <tr>
				    <td height="31">Ժ ϵ</td>
				    <td height="31">
				    &nbsp;<bean:write name='map' property="xy" />&nbsp;
				    </td>
				    <td height="31">רҵ </td>
				    <td height="31" colspan="3">&nbsp;<bean:write name="map" property="zymc"/>&nbsp;</td>
					<td height="31">�༶ </td>
				    <td height="31" colspan="3">&nbsp;<bean:write name="map" property="bjmc"/>&nbsp;</td>
				  </tr>
				  <tr>
				    <td height="31">���֤���� </td>
				    <td height="31" colspan="9">&nbsp;<bean:write name="map" property="sfzh"/>&nbsp;</td>
				  </tr>
				  <tr>
				    <td height="31">��סַ</td>
				    <td height="31" colspan="5">&nbsp;<bean:write name="map" property="xzz"/>&nbsp;</td>
				    <td height="31">ѧ���绰 </td>
				    <td height="31" colspan="3">&nbsp;<bean:write name="map" property="xsdh"/>&nbsp;</td>
				  </tr>
				  <tr>
				    <td height="31">��ͥ��ַ</td>
				    <td height="31" colspan="5">&nbsp;<bean:write name="map" property="jtdz"/>&nbsp;</td>
				    <td height="31">�������� </td>
				    <td height="31" colspan="3">&nbsp;<bean:write name="map" property="yzbm"/>&nbsp;</td>
				  </tr>
				  <tr>
				    <td height="31">��ͥ�˿�</td>
				    <td height="31">&nbsp;<bean:write name="map" property="jtrk"/>&nbsp;</td>
				    <td height="31">������</td>
				    <td height="31" colspan="3">&nbsp;<bean:write name="map" property="nsr"/>&nbsp;Ԫ</td>
					<td height="31">��ͥ�绰</td>
				    <td height="31" colspan="3">
			        &nbsp;<bean:write name="map" property="jtdh"/>&nbsp;</td>
				  </tr>
				  <tr>
				    <td height="31">������</td>
				    <td height="31" colspan="9">&nbsp;<bean:write name="map" property="dkje"/>
				      &nbsp;Ԫ</td>
				  </tr>
				   
			����</table>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;width:100%">
							<button type="button" class="button2" onclick="expTabWebPrint('rsTable','�б���ѧ������ѧ���������',null,null,null,null);"
								style="width:80px">
								��ӡ
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