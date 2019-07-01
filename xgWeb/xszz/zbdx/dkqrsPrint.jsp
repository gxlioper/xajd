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
    
    <title><bean:message key="lable.title" /> ����ȷ����</title>

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
		/**
		 * @describe ���������ӡ
		 */
		function printAction() {
			document.forms[0].action = "zbdx_xszz.do?method=dkqrsPrint";
			document.forms[0].submit();
		}
		function validate(){
			return true;
			var xh = document.getElementById('xh').value;
			var qsdh = document.getElementById('qsdh').value;
			var yzbm = document.getElementById('yzbm').value;
			var sfzh = document.getElementById('sfzh').value;
			var jtcy1_ysr = document.getElementById('jtcy1_ysr').value;
			var jtcy2_ysr = document.getElementById('jtcy2_ysr').value;
			var jtcy3_ysr = document.getElementById('jtcy3_ysr').value;
			var jtcy4_ysr = document.getElementById('jtcy4_ysr').value;
			var jtcy5_ysr = document.getElementById('jtcy5_ysr').value;
			var jttgje = document.getElementById('jttgje').value;
			var zxje = document.getElementById('zxje').value;
			var jxje = document.getElementById('jxje').value;
			var qgzxje = document.getElementById('qgzxje').value;
			var xnwxdkje = document.getElementById('xnwxdkje').value;
			var qtsrje = document.getElementById('qtsrje').value;
			var zxdkje = document.getElementById('zxdkje').value;
			var yffzxdkje = document.getElementById('yffzxdkje').value;
			var zzff1qsje = document.getElementById('zzff1qsje').value;
			var zzff1jsje = document.getElementById('zzff1jsje').value;
			var sqzzly = document.getElementById('sqzzly').value;
			
			if(!isNumber(xh)){
				alert("ѧ�ű���Ϊ����!");
				return false;
			}else if((qsdh != null) && (qsdh != "") && (!isNumber(qsdh))){
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
			}
			return true;
		}
		
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		
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
	    <html:form action="/knbz.do" method="post">
	    <input type="hidden" id="url" name="url" value="/xszz/knbz.jsp" />
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
					����ȷ����
				</div>
		  </div>
			<table width="100%" id="rsTable" class="tbstyle">  
				  <tr>
							<td height="31">
								<div align="left">
									<font color="red">*</font>ѧ�ţ�
								</div>
							</td>
							<td colspan="2">
								<div align="left">
									&nbsp;<bean:write name='map' property="xh" />
								</div>
							</td>
				    
				    <td height="31" colspan="2">����</td>
				    <td colspan="2">&nbsp;<bean:write name="map" property="xm"/></td>
				  </tr>
				  <tr>
				    <td height="31">�Ա�</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="xb"/></td>
				    <td height="31" colspan="2">�꼶</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="nj"/></td>
				  </tr>
				  <tr>
				    <td height="31"><bean:message key="lable.xsgzyxpzxy" /></td>
				    <td height="31" colspan="2">&nbsp;<bean:write name='map' property="xy" />
				    </td>
				    <td height="31" colspan="2"><font color="red">*</font>��� </td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="nd"/></td>
				  </tr>
				  <tr>
				    <td height="31">רҵ </td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="zymc"/></td>
				    <td height="31" colspan="2"><font color="red">*</font>ѧ��</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="xn"/></td>
				  </tr>
				  <tr>
				    <td height="31">�༶</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="bj"/></td>
				    <td height="31" colspan="2"><font color="red">*</font>ѧ�� </td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="xq"/></td>
				  </tr>
				  <tr>
				    <td height="31"><font color="red">���ع����ʺŻ��߿���</font></td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="ghzh"/></td>
				    <td height="31" colspan="2"><font color="red">��������</font></td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="dkqx"/></td>
				  </tr>
				  <tr>
				    <td height="31">��������ܶ�</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="dkze"/>Ԫ</td>
				    <td height="31" colspan="2">�����뱾�˹�ϵ</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qygx"/></td>
				  </tr>
				  <tr>
				    <td height="31">�������ѧ��</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="sqdkxf"/>Ԫ</td>
				    <td height="31" colspan="2">������ϵ�绰</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qylxdh"/></td>
				  </tr>
				  <tr>
				    <td height="31">������������</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qsdkshf"/>Ԫ</td>
				    <td height="31" colspan="2">�������֤����</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qysfzh"/></td>
				  </tr>
				  <tr>
				    <td height="31">�������ס�޷�</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="sqdkzsf"/>Ԫ</td>
				    <td height="31" colspan="2">���ѹ�����λ</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qygzdw"/></td>
				  </tr>
				  <tr>
				    <td height="31">��Ч��ϵ��ʽ</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="yxlxfs"/></td>
				    <td height="31" colspan="2">����סַ</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="qyzz"/></td>
				  </tr>
				  <tr>
				    <td height="48">��������</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqxm"/></td>
				    <td height="48" colspan="2">ĸ������</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqxm"/></td>
				  </tr>
				  <tr>
				    <td height="48">�������֤����</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqsfzh"/></td>
				    <td height="48" colspan="2">ĸ�����֤����</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqsfzh"/></td>
				  </tr>
				  <tr>
				    <td height="48">���׹�����λ</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqgzdw"/></td>
				    <td height="48" colspan="2">ĸ�׹�����λ</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqgzdw"/></td>
				  </tr>
				  <tr>
				    <td height="48">������ϵ��ʽ</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="fqlxfs"/></td>
				    <td height="48" colspan="2">ĸ����ϵ��ʽ</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="mqlxfs"/></td>
				  </tr>
				  <tr>
				    <td height="48">�״α�ҵȥ��</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="scbyqx"/></td>
				    <td height="48" colspan="2">����ʱ��</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="ffsj"/></td>
				  </tr>
				  <tr>
				    <td height="48">��ͬ��1</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="hth1"/></td>
				    <td height="48" colspan="2">��ͬ��2</td>
				    <td height="48" colspan="2">&nbsp;<bean:write name="map" property="hth2"/></td>
				  </tr>
				  <tr>
				    <td height="31">��ͬ������ڻ���1</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="htjbjg1"/></td>
				    <td height="31" colspan="2">��ͬ������ڻ���2</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="htjbjg2"/></td>
				  </tr>
				  <tr>
				    <td height="31">��֧��������1</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="fzjgmc1"/></td>
				    <td height="31" colspan="2">��֧��������2</td>
				    <td height="31" colspan="2">&nbsp;<bean:write name="map" property="fzjgmc2"/></td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">������1</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="dkje1"/>
				      Ԫ</td>	
				    <td width="15%" height="31" colspan="2">������2</td><td colspan="2">&nbsp;<bean:write name="map" property="dkje2"/>
				      Ԫ </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">�������1</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="hknf1"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">�������2</td><td colspan="2">&nbsp;<bean:write name="map" property="hknf2"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">��ͬ��3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="hth3"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">��ǰ������λ</td><td colspan="2">&nbsp;<bean:write name="map" property="dqgzdw"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">��ͬ������ڻ���3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="htjbjg3"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">��ǰ��λ��ַ</td><td colspan="2">&nbsp;<bean:write name="map" property="dqdwdz"/>
				       </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">��֧��������3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="fzjgmc3"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">��ǰ��λ�ʱ�</td><td colspan="2">&nbsp;<bean:write name="map" property="dqdwyb"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">������3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="dkjg3"/>Ԫ
				      </td>	
				    <td width="15%" height="31" colspan="2">��ǰ��λ��ϵ��ʽ</td><td colspan="2">&nbsp;<bean:write name="map" property="dqdwlxfs"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">�������3</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="hknf"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">�ʼ����ͱ��</td><td colspan="2">&nbsp;<bean:write name="map" property="yjfsbj"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31"><bean:message key="lable.xsgzyxpzxy" />���</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="xysh"/>
				      </td>	
				    <td width="15%" height="31" colspan="2">ѧУ���</td><td colspan="2">&nbsp;<bean:write name="map" property="xxsh"/>
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">���Ŵ����ܶ�</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="fsdkze"/>Ԫ
				      </td>	
				    <td width="15%" height="31" colspan="2">���Ŵ���ѧ��</td><td colspan="2">&nbsp;<bean:write name="map" property="fsdkzsf"/>
				    Ԫ</td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">���Ŵ��������</td>
				    <td width="8%" colspan="2">&nbsp;<bean:write name="map" property="fsdkshf"/>Ԫ</td>	
				    <td width="15%" height="31" colspan="2">���Ŵ���ס�޷�</td><td colspan="2">&nbsp;<bean:write name="map" property="yffzxdkje"/>Ԫ
				      </td>
				  </tr>
				  <tr>
				    <td width="11%" height="31">��������</td>
				    <td width="8%" colspan="6">&nbsp;<bean:write name="map" property="sqly"/>
				      </td>	
				  </tr>
				  <tr>
				    <td width="11%" height="31">��ע</td>
				    <td width="8%" colspan="6">&nbsp;<bean:write name="map" property="beizhu"/>
				      </td>	
				  </tr>				 
			����</table>
			
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;width:100%">
							<button type="button" class="button2" onclick="expTabWebPrint('rsTable','�б���ѧ����ȷ����',null,null,null,null);"
								style="width:80px">
								��ӡ
							</button>
						</div>
					</center>
				
				
	    </html:form>
	    <script src="js/bottomButton.js" ></script>
	    <script language="javascript">
if(document.getElementById("btn") && !window.dialogArguments){
	document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
	document.getElementById("btn").style.width = "96%";
	window.setInterval("initBTNTool('btn')",1);
}
</script>
  </body>
</html:html>
