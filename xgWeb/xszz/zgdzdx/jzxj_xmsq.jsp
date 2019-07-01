<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">

<head>
	<base target="_self" />
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link id="csss" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xmdm = document.getElementById('xmdm').value;
			var xh = document.getElementById('xh').value;
			var fblw = document.getElementById('fblw').value;
			//var yhzs = document.getElementById('yhzs').value;
			var sqly = document.getElementById('sqly').value;
			var zdyzdXxxx = document.getElementById('zdyzdXxxx').value;
			var str = new Array();
			var strT = new Array();
			var i = 1;
			var j = 1;
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ����Ľ���ѧ��!");
				return false;
			}
			if(xh == null || xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(fblw != null){
	         	if(fblw.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("�񽱼�����������ܴ���1000���ַ���");
	          		 return false;
	       		 }
	       	}
			if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 2000){	         
	          		 alert("�������ɲ��ܴ���2000���ַ���");
	          		 return false;
	       		 }
	       	}
			str = zdyzdXxxx.split("!!TwoSpile!!");
	       	for(i = 0; i < str.length; i++) {
	       		strT = str[i].split("!!OneSpile!!");
	       		if(strT[4] == "�ı���"){
	       			var temp = document.getElementById('zdy'+strT[2]).value;
	       			if(temp != null){
	         			if(temp.replace(/[^\u0000-\u00ff]/g, "**").length > Math.round(strT[5])){	         
	          				 alert(strT[3]+"���ܴ���"+strT[5]+"���ַ���");
	          				 return false;
	       		 		}
	       			}
	       		}
	       	}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq&act=save";
			document.getElementById("tjsqbt").disabled = true;
			document.forms[0].submit();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function chang(){
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ���صĽ���ѧ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmmbxz&xmdm="+xmdm;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function printOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ���صĽ���ѧ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxjbbOut";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
</head>

<body>	
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>����ѧ�� - ���� - ����</a>
		</p>
	</div>
	
	<logic:equal name="sfksq" value="-1">
		<div class="tab_cur">
			<p class="location">
				���ڲ�������ʱ���ڻ򲻷�����������������
			</p>
		</div>
	</logic:equal>
	<logic:equal name="sfksq" value="-2">
		<div class="tab_cur">
			<p class="location">
				����ۺ��������ֵ��ڽ���Ҫ�󣡣���
			</p>
		</div>
	</logic:equal>
	<logic:equal name="sfksq" value="-3">
		<div class="tab_cur">
			<p class="location">
				����ۺ������������ڽ���Ҫ�󣡣���
			</p>
		</div>
	</logic:equal>
		<html:form action="zgdzdx_xszz.do?method=jzxj_xmsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=jzxj_xmsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="xq" name="xq"
				value="<bean:write name="rs" property="xq" />">
			<input type="hidden" id="xmmc" name="xmmc"
				value="<bean:write name="rs" property="xmmc" />">
			<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xyshsj" name="xyshsj"
				value="<bean:write name="rs" property="xyshsj" />">
			<input type="hidden" id="xypzje" name="xypzje"
				value="<bean:write name="rs" property="xypzje" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			<input type="hidden" id="xxshsj" name="xxshsj"
				value="<bean:write name="rs" property="xxshsj" />">
			<input type="hidden" id="xxpzje" name="xxpzje"
				value="<bean:write name="rs" property="xxpzje" />">
			<input type="hidden" id="zdyzdXxxx" name="zdyzdXxxx"
				value="<bean:write name="zdyzdXxxx" scope="request"/>">


			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="10"><span>��������</span></th>
			        </tr>
			    </thead>
				 <tfoot>
				 	<logic:equal name="sfksq" value="1">
				      <tr>
				        <td colspan="9"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				          	<button type="button" name="�ύ"  id="tjsqbt" onClick="yz();">�ύ����</button>
				            <button type="button" name="�ر�"  onClick="printOut();">��ӡ����</button>
				          </div></td>
				      </tr>
				      </logic:equal>
				    </tfoot>
				<tbody>
				<tr>
					<th align="" colspan="2">
						<font color="red">*</font>����ѧ����Ŀ
					</th>
					<td colspan="7">
						<html:select name="rs" property="xmdm" style="width:180px"
							onchange="chang();" styleId="xmdm">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="xmdm"
								labelProperty="xmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="" colspan="2">
						����ѧ��ο����
					</th>
					<td colspan="3">
						<html:text name ="rs" property="jlckje"  readonly="true" />
					</td>
						<th align="">
						����ѧ������
					</th>
					<td colspan="3">
						<html:text name ="rs" property="lbmc"  readonly="true" style="width:100%;heigh:100%"/>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="center" colspan="2">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" colspan="3">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								 id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th align="" colspan="2">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" colspan="3">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th >
							����
					</th>
					<td colspan="3" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�Ա�
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="xb" name="xb" style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<th>
						<div align="">
							�꼶
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							���֤��
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="sfzh" name="sfzh"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
					<th>
						<div align="">
							��ϵ�绰
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="lxdh" />" 
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="xymc" name="xymc"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
					<th>
						<div align="">
							רҵ
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�༶
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
					<th>
						<div align="">
							��Դ��
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="syd" name="syd"
							style="width:100%;heigh:100%" maxlength="50"
							value="<bean:write name='rs' property="syd" />">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="ssbh" name="ssbh"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="ssbh" />" readonly="true">
					</td>
					<th>
						<div align="">
							���ҵ绰
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="qsdh" name="qsdh"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="qsdh" />">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							������ò
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="zzmmmc" name="zzmmmc"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="zzmmmc" />"
							readonly="true">
					</td>
					<th>
						<div align="">
							����
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="mzmc" name="mzmc"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="mzmc" />">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ѧ����
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="rxny" name="rxny"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="rxny" />" readonly="true">
					</td>
					<th>
						<div align="">
							��������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="csrq" name="csrq"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name='rs' property="csrq" />">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�Ƿ�������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="sfkns" name="sfkns"
							style="width:95%;heigh:100%"
							value="<bean:write name='rs' property="sfkns" />" readonly="true">
					</td>
					<th>
						<div align="">
							�༶����
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="bjpm" name="bjpm"
							style="width:100%;heigh:100%" maxlength="3"
							value="<bean:write name='rs' property="bjpm" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�ϰ�ѧ��ѧ�ּ���
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="sbxqxfjd" name="sbxqxfjd" style="width:95%;heigh:100%" >
					</td>
					<th>
						<div align="">
							�°�ѧ��ѧ�ּ���
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="xbxqxfjd" name="xbxqxfjd" style="width:100%;heigh:100%" >
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							ѧ��ѧ�ּ���
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="xnxfjd" name="xnxfjd"
							style="width:95%;heigh:100%"
							value="<bean:write name="xnxfjd" />" readonly="true">
					</td>
					<th>
						<div align="">
							��ѧ����޿γ�����
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="bxnbxkms" name="bxnbxkms" style="width:100%;heigh:100%" >
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ѧ����޿γ���������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="bxnbxkyxms" name="bxnbxkyxms" style="width:95%;heigh:100%" >
					</td>
					<th>
						<div align="">
							��ѧ����޿γ���������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="bxnbxlhxms" name="bxnbxlhxms" style="width:100%;heigh:100%" >
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							רҵ�ɼ�����������/��������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="cjpm" name="cjpm" style="width:95%;heigh:100%" >
					</td>
					<th>
						<div align="">
							�ۺϲ����ɼ�
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="zhcpcj" name="zhcpcj" style="width:100%;heigh:100%" >
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�ۺϲ����ɼ��༶����������/��������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="zhcpcjpm" name="zhcpcjpm" style="width:95%;heigh:100%" >
					</td>
					<th>
						<div align="">
							����ˮƽ��ע�����������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="wysp" name="wysp" style="width:100%;heigh:100%" >
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�����ˮƽ
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="jsjsp" name="jsjsp" style="width:95%;heigh:100%" >
					</td>
					<th>
						<div align="">
							Ժ������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="yxjl" name="yxjl" style="width:90%;heigh:100%" value="${rs.yxjl }">��
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							У������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="xjjl" name="xjjl" style="width:90%;heigh:100%" value="${rs.xjjl }">��
					</td>
					<th>
						<div align="">
							ʡ�����Ͻ���
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="sjjl" name="sjjl" style="width:90%;heigh:100%" value="${rs.sjjl }">��
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<div align="">
							�񽱼��������
						</div>
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="fblw" rows='5' style="width:100%" onblur="yzdx(this,'fblw')"/>
						<input type="hidden" id="fblw" name="fblw">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							����
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="kh" name="kh" style="width:93%;heigh:100%"
							value="<bean:write name='rs' property="kh" />" readonly="true">
					</td>
					<th>
						<div align="">
							��ѧǰ����
						</div>
					</th>
					<td colspan="3" align="">
						<logic:present name="rs" property="rxqhk">
							<logic:match value="����" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="����" checked>&nbsp;&nbsp;����
							    <input type="radio" name="rxqhk" value="ũ��">&nbsp;&nbsp;ũ��
							</logic:match>
							<logic:match value="ũ��" name="rs" property="rxqhk">
								<input type="radio" name="rxqhk" value="����">&nbsp;&nbsp;����
							    <input type="radio" name="rxqhk" value="ũ��" checked>&nbsp;&nbsp;ũ��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="rxqhk">
							<input type="radio" name="rxqhk" value="����" checked>&nbsp;&nbsp;����
							<input type="radio" name="rxqhk" value="ũ��">&nbsp;&nbsp;ũ��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="4" scope="row">
						<div align="right">
							�Ƿ�Ը��μӴ��ƻ�־Ը�
						</div>
					</th>
					<td width="12%" align="">
						<logic:present name="rs" property="sfyycjcshzyhd">
							<logic:match value="��" name="rs" property="sfyycjcshzyhd">
								<input type="radio" name="sfyycjcshzyhd" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfyycjcshzyhd" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfyycjcshzyhd">
								<input type="radio" name="sfyycjcshzyhd" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfyycjcshzyhd" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfyycjcshzyhd">
							<input type="radio" name="sfyycjcshzyhd" value="��" checked>&nbsp;&nbsp;��
							<input type="radio" name="sfyycjcshzyhd" value="��">&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
					<th colspan="3">
						<div align="right">
							�Ƿ�Ը�����������ѧ������ڹ���ѧ
						</div>
					</th>
					<td width="12%" align="">
						<logic:present name="rs" property="sfyysqgjzxdkhqgzx">
							<logic:match value="��" name="rs" property="sfyysqgjzxdkhqgzx">
								<input type="radio" name="sfyysqgjzxdkhqgzx" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfyysqgjzxdkhqgzx" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:match value="��" name="rs" property="sfyysqgjzxdkhqgzx">
								<input type="radio" name="sfyysqgjzxdkhqgzx" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfyysqgjzxdkhqgzx" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfyysqgjzxdkhqgzx">
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="��" checked>&nbsp;&nbsp;��
							<input type="radio" name="sfyysqgjzxdkhqgzx" value="��">&nbsp;&nbsp;��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="9" scope="row">
						<div align="">
							��ͥ��Ϣ
						</div>
					</th>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ͥ����
						</div>
					</th>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;�Ƿ�ȫ��
						<logic:present name="rs" property="sfjq">
							<logic:match value="��" name="rs" property="sfjq">
								<input type="radio" name="sfjq" value="��" checked>��
							    <input type="radio" name="sfjq" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfjq">
								<input type="radio" name="sfjq" value="��">&nbsp;��
							    <input type="radio" name="sfjq" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfjq">
							<input type="radio" name="sfjq" value="��" checked>��
							<input type="radio" name="sfjq" value="��">��
						</logic:notPresent>
						&nbsp;&nbsp; �Ƿ�¶���
						<logic:present name="rs" property="sfge">
							<logic:match value="��" name="rs" property="sfge">
								<input type="radio" name="sfge" value="��" checked>��
							    <input type="radio" name="sfge" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfge">
								<input type="radio" name="sfge" value="��">��
							    <input type="radio" name="sfge" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfge">
							<input type="radio" name="sfge" value="��">��
							<input type="radio" name="sfge" value="��" checked>��
						</logic:notPresent>
						&nbsp;&nbsp; �Ƿ��ף�
						<logic:present name="rs" property="sfdq">
							<logic:match value="��" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��" checked>��
							    <input type="radio" name="sfdq" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfdq">
								<input type="radio" name="sfdq" value="��">��
							    <input type="radio" name="sfdq" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfdq">
							<input type="radio" name="sfdq" value="��">��
							<input type="radio" name="sfdq" value="��" checked>��
						</logic:notPresent>
						&nbsp;&nbsp; �Ƿ�м���
						<logic:present name="rs" property="sfcj">
							<logic:match value="��" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="��" checked>��
							    <input type="radio" name="sfcj" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfcj">
								<input type="radio" name="sfcj" value="��">��
							    <input type="radio" name="sfcj" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfcj">
							<input type="radio" name="sfcj" value="��">��
							<input type="radio" name="sfcj" value="��" checked>��
						</logic:notPresent>
						<br />�Ƿ��������
						<logic:present name="rs" property="sfjls">
							<logic:match value="��" name="rs" property="sfjls">
								<input type="radio" name="sfjls" value="��" checked>��
							    <input type="radio" name="sfjls" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfjls">
								<input type="radio" name="sfjls" value="��">��
							    <input type="radio" name="sfjls" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfjls">
							<input type="radio" name="sfjls" value="��">��
							<input type="radio" name="sfjls" value="��" checked>��
						</logic:notPresent>
						&nbsp;&nbsp; �Ƿ����죺
						<logic:present name="rs" property="sfly">
							<logic:match value="��" name="rs" property="sfly">
								<input type="radio" name="sfly" value="��" checked>��
							    <input type="radio" name="sfly" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfly">
								<input type="radio" name="sfly" value="��">��
							    <input type="radio" name="sfly" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfly">
							<input type="radio" name="sfly" value="��">��
							<input type="radio" name="sfly" value="��" checked>��
						</logic:notPresent>
						&nbsp;&nbsp; �Ƿ��ز���
						<logic:present name="rs" property="sfzb">
							<logic:match value="��" name="rs" property="sfzb">
								<input type="radio" name="sfzb" value="��" checked>��
							    <input type="radio" name="sfzb" value="��">��
							</logic:match>
							<logic:match value="��" name="rs" property="sfzb">
								<input type="radio" name="sfzb" value="��">��
							    <input type="radio" name="sfzb" value="��" checked>��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="sfzb">
							<input type="radio" name="sfzb" value="��">��
							<input type="radio" name="sfzb" value="��" checked>��
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ͥסַ
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="jtzz" maxlength="100" name="jtzz"
							style="width:93%;heigh:100%"
							value="<bean:write name="rs" property="jtzz"/>">
					</td>
					<th>
						<div align="">
							��������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ͥ��ϵ�绰
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="jtlxdh" maxlength="15" name="jtlxdh"
							style="width:93%;heigh:100%"
							value="<bean:write name="rs" property="jtlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="">
							��ͥ�˾�<br />������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="jtrjnsr" maxlength="6" name="jtrjnsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtrjnsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th width="4%" rowspan="6" scope="row">
						<div align="">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							Ϣ
						</div>
					</th>
					<th width="12%">
						<div align="">
							����
						</div>
					</th>
					<th width="12%">
						<div align="">
							����
						</div>
					</th>
					<th width="10%">
						<div align="">
							�뱾��
							<br />
							��ϵ
						</div>
					</th>
					<th colspan="2">
						<div align="">
							����(ѧϰ)��λ
						</div>
					</th>
					<th width="12%">
						<div align="">
							ְҵ
						</div>
					</th>
					<th width="10%">
						<div align="">
							������
							<br />
							(Ԫ)
						</div>
					</th>
					<th width="12%">
						<div align="">
							����״��
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="">
							<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_xm"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy1_nl" maxlength="3" name="jtcy1_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy1_gx" maxlength="20" name="jtcy1_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="">
							<input type="text" id="jtcy1_gzdw" maxlength="200" name="jtcy1_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy1_zy" maxlength="20" name="jtcy1_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_zy"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy1_nsr" maxlength="8" name="jtcy1_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy1_jkzk" maxlength="40" name="jtcy1_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy1_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="">
							<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_xm"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy2_nl" maxlength="3" name="jtcy2_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy2_gx" maxlength="20" name="jtcy2_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="">
							<input type="text" id="jtcy2_gzdw" maxlength="200" name="jtcy2_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy2_zy" maxlength="20" name="jtcy2_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_zy"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy2_nsr" maxlength="8" name="jtcy2_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy2_jkzk" maxlength="40" name="jtcy2_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy2_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="">
							<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_xm"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy3_nl" maxlength="3" name="jtcy3_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy3_gx" maxlength="20" name="jtcy3_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="">
							<input type="text" id="jtcy3_gzdw" maxlength="200" name="jtcy3_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy3_zy" maxlength="20" name="jtcy3_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_zy"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy3_nsr" maxlength="8" name="jtcy3_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy3_jkzk" maxlength="40" name="jtcy3_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy3_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="">
							<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_xm"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy4_nl" maxlength="3" name="jtcy4_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy4_gx" maxlength="20" name="jtcy4_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="">
							<input type="text" id="jtcy4_gzdw" maxlength="200" name="jtcy4_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy4_zy" maxlength="20" name="jtcy4_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_zy"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy4_nsr" maxlength="8" name="jtcy4_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy4_jkzk" maxlength="40" name="jtcy4_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy4_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="">
							<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_xm"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy5_nl" maxlength="3" name="jtcy5_nl"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nl"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy5_gx" maxlength="20" name="jtcy5_gx"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gx"/>">
						</div>
					</td>
					<td colspan="2">
						<div align="">
							<input type="text" id="jtcy5_gzdw" maxlength="200" name="jtcy5_gzdw"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_gzdw"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy5_zy" maxlength="20" name="jtcy5_zy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_zy"/>">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy5_nsr" maxlength="8" name="jtcy5_nsr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_nsr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="">
							<input type="text" id="jtcy5_jkzk" maxlength="40" name="jtcy5_jkzk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtcy5_jkzk"/>">
						</div>
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							ѧ���ڱ���<br />�������
						</div>
					</th>
					<td colspan="7">
						<input type="text" id="xszbdszqk" maxlength="250" name="xszbdszqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xszbdszqk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ͥ����<br />��Ȼ�ֺ����
						</div>
					</th>
					<td colspan="7">
						<input type="text" id="jtzszrzhqk" maxlength=250" name="jtzszrzhqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzszrzhqk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��ͥ����<br />ͻ�������¼�
						</div>
					</th>
					<td colspan="7">
						<input type="text" id="jtzstfywsj" maxlength="250" name="jtzstfywsj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtzstfywsj"/>">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							�������
						</div>
					</th>
					<td colspan="7">
						<input type="text" id="qtqk" maxlength="250" name="qtqk"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qtqk"/>">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							��������ͨѶ��ַ
						</div>
					</th>
					<td colspan="7">
						<input type="text" id="mzbm_txdz" maxlength="150" name="mzbm_txdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_txdz"/>">
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						<div align="">
							����������������
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="mzbm_yzbm" maxlength="6" name="mzbm_yzbm"
							style="width:93%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="">
							��������<br />��ϵ�绰
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="mzbm_lxdh" maxlength="15" name="mzbm_lxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzbm_lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th colspan="9">
						<div align="center">
							������Ϣ
						</div>
					</th>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
								String zdyzdXxxx = String.valueOf(request
								.getAttribute("zdyzdXxxx"));
								String zddm = "";
								String zdmc = "";
								String zddx = "";
								String zdlx = "";
								String bxwsz = "";

								String[] temp = zdyzdXxxx.split("!!TwoSpile!!");
								for (int i = 0; i < temp.length; i++) {
									String[] str = temp[i].split("!!OneSpile!!");
									zddm = "zdy" + str[2];
									zdmc = str[3];
									zdlx = str[4];
									zddx = str[5];
									bxwsz = str[6];
					%>
					<tr>
						<td colspan="2">
							<div align="">
								<%=zdmc%>
							</div>
						</td>
						<td colspan="7">
							<%
							if ("�ı���".equalsIgnoreCase(zdlx)) {
							%>
							<html:textarea name="rs" property="<%=zddm%>" rows='5' style="width:100%" onblur="yzdx(this,'<%=zddm%>')"/>
							<input type="hidden" id="<%=zddm%>" name="<%=zddm%>" value="">
							<%
							} else {
							%>
							<%if("��".equalsIgnoreCase(bxwsz)){ %>
							<input type="text" id="<%=zddm%>" name="<%=zddm%>"
								style="width:100%;heigh:100%" maxlength="<%=zddx%>"
								value="<bean:write name='rs' property="<%=zddm%>" />"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							<%} else {%>
							<input type="text" id="<%=zddm%>" name="<%=zddm%>"
								style="width:100%;heigh:100%" maxlength="<%=zddx%>"
								value="<bean:write name='rs' property="<%=zddm%>" />">
							<% }%>
							<%
							}
							%>
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<th colspan="2">
						<div align="">
							��������
						</div>
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="sqly" rows='5' style="width:100%" onblur="yzdx(this,'sqly')"/>
						<input type="hidden" id="sqly" name="sqly" value="">
					</td>
				</tr>
			</table>
		</html:form>
</body>
</html>
