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
<html:html>
<head>
	<base target="_self" />
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function checkStuInfo(){
			var xh = document.getElementById('xh').value;
			if(xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return ;
			}
			refreshForm("whlg_xszz.do?method=gjzxdksq&doType=add");
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "";
			document.forms[0].submit();
		}
		
		function noMarryElemReadonly(flag){
			var str = 'po_xm-po_lxdh-po_gzdw-po_dwdz';
			var strSplit = str.split("-");
			if(flag){
				for(var i=0;i<strSplit.length;i++){
					document.getElementById(strSplit[i]).readOnly = true;
				}
			}else{
				for(var i=0;i<strSplit.length;i++){
					document.getElementById(strSplit[i]).readOnly = false;
				}
			}	
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
			��ǰ����λ�ã�ѧ������ - ������ѧ�������� - ������ѧ�������� 
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal><%--
	<logic:equal name="sfksq" value="1">
		--%><html:form action="/whlg_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/whlg_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc-sfzh-zzmmmc-nj" />	
			<table class="tbstyle" width="100%">
			<tbody>
				<tr>
					<td rowspan="13">
						��<br>��<br>��<br>��<br>��<br>��<br>��<br>
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" >
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"				
							value="<bean:write name='rs' property="zzmmmc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" 
							value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" 
							value="<bean:write name='rs' property="sfzh" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ҵ<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							��ͥ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh"
							value="<bean:write name='rs' property="jtdh" />">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td  scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							��ҵȥ��
						</div>
					</td>
					<td>
						<input type="text" id="byqx" name="byqx"	
							value="<bean:write name='rs' property="byqx" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							value="<bean:write name='rs' property="bjmc" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							�Ƿ񱨿��о���
						</div>
					</td>
					<td>
						<logic:notEmpty name="rs" property="sfdkyjs">
							<logic:match value="��" name="rs" property="sfdkyjs">
								<input type="radio" name="sfdkyjs" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdkyjs" value="��">&nbsp;&nbsp;��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="sfdkyjs">
								<input type="radio" name="sfdkyjs" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdkyjs" value="��" checked>&nbsp;&nbsp;��
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfdkyjs">
								<input type="radio" name="sfdkyjs" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdkyjs" value="��" >&nbsp;&nbsp;��
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="center">
						��ͥסַ
					</td>
					<td colspan="3">
							<input type="text" id="jtzz" name="jtzz"	style="width:80%"
							value="<bean:write name='rs' property="jtzz" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						������λ
					</td>
					<td>
							<input type="text" id="gzdw" name="gzdw"	style="width:80%"
							value="<bean:write name='rs' property="gzdw" />">
					</td>
					<td align="center">
						��λ�绰
					</td>
					<td>
							<input type="text" id="dwdh" name="dwdh"
							value="<bean:write name='rs' property="dwdh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						��λ��ַ
					</td>
					<td>
							<input type="text" id="dwdz" name="dwdz"	style="width:80%"
							value="<bean:write name='rs' property="dwdz" />">
					</td>
					<td align="center">
						��λ�ʱ�
					</td>
					<td>
							<input type="text" id="dwyb" name="dwyb"
							value="<bean:write name='rs' property="dwyb" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						�ƶ��绰
					</td>
					<td>
							<input type="text" id="yddh" name="yddh" 
							value="<bean:write name='rs' property="yddh" />">
					</td>
					<td align="center">
						E-mail
					</td>
					<td>
						<input type="text" id="email" name="email"
							value="<bean:write name='rs' property="email" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						ѧ�� 
					</td>
					<td>
						<html:select property="xl" styleId="xl" name="rs">
							<logic:equal value="�о���" property="xl" name="rs">
								<option value="����">����</option>
								<option value="�о���" selected="selected">�о���</option>
							</logic:equal>
							<logic:notEqual value="�о���" property="xl" name="rs">
								<option value="����" selected="selected">����</option>
								<option value="�о���">�о���</option>
							</logic:notEqual>		
						</html:select>
						
					</td>
					<td align="center">
						����״��
					</td>
					<td>
						<logic:notEmpty name="rs" property="hyzk">
							<logic:match value="��" name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="��" checked>&nbsp;&nbsp;�ѻ�
							    <input type="radio" name="hyzk" value="��" onclick="noMarryElemReadonly(true)">&nbsp;&nbsp;δ��
							</logic:match>
							<logic:notMatch value="��" name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="��">&nbsp;&nbsp;�ѻ�
							    <input type="radio" name="hyzk" value="��" checked>&nbsp;&nbsp;δ��
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="��" onclick="noMarryElemReadonly(false)">&nbsp;&nbsp;�ѻ�
							    <input type="radio" name="hyzk" value="��" onclick="noMarryElemReadonly(true)">&nbsp;&nbsp;δ��
						</logic:empty>
						<input type="hidden" id="hyzk" value="<bean:write name="rs" property="hyzk"/>"/>
						<script type="text/javascript">
							var is_marry = document.getElementById("hyzk").value;
							if(is_marry == '��'){
								noMarryElemReadonly(true);
							}
						</script>
					</td>
				</tr>
				<tr>		
						<td align="center">
							��ż����
						</td>
						<td>
							<input type="text" id="po_xm" name="po_xm"
								value="<bean:write name='rs' property="po_xm" />">
						</td>
						<td align="center">
							��ϵ�绰
						</td>
						<td >
							<input type="text" id="po_lxdh" name="po_lxdh"
								value="<bean:write name='rs' property="po_lxdh" />">
						</td>
					</tr>
					<tr>
						<td align="center">
							��ż������λ
						</td>
						<td >
							<input type="text" id="po_gzdw" name="po_gzdw" style="width:80%"
								value="<bean:write name='rs' property="po_gzdw" />">
						</td>
						<td align="center">
							��ż��λ��ַ
						</td>
						<td>
							<input type="text" id="po_dwdz" name="po_dwdz" style="width:80%"
								value="<bean:write name='rs' property="po_dwdz" />">
						</td>
					</tr>
				</tbody>	

				
				<tbody>
					<tr>
					<td rowspan="5">
						<div align="center">��<br>ϵ<br>��<br>��<br>��<br>��<br>��</div>
					</td>	
					<td align="center">
							��ϵ������
					</td>
					<td >
						<input type="text" id="lxr_xm" name="lxr_xm" value="<bean:write name='rs' property="lxr_xm" />">
					</td>
					<td align="center">
						�Ա�
					</td>
					<td>
						<logic:notEmpty name="rs" property="lxr_xb">
							<logic:match value="��" name="rs" property="lxr_xb">
								<input type="radio" name="lxr_xb" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="lxr_xb" value="Ů">&nbsp;&nbsp;Ů
							</logic:match>
							<logic:notMatch value="��" name="rs" property="lxr_xb">
								<input type="radio" name="lxr_xb" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="lxr_xb" value="Ů" checked>&nbsp;&nbsp;Ů
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="lxr_xb">
								<input type="radio" name="lxr_xb" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="lxr_xb" value="Ů">&nbsp;&nbsp;Ů
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="center">
						�����˹�ϵ
					</td>
					<td >
						<input type="text" id="lxr_yjkrgx" name="lxr_yjkrgx" 
							value="<bean:write name='rs' property="lxr_yjkrgx" />">
						</td>
						<td align="center">
							��������
						</td>
						<td>
							<html:text name='rs' property="lxr_csrq" styleId="lxr_csrq" 
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('lxr_csrq','y-mm-dd');" />	
						</td>
				</tr>
				<tr>
						<td align="center">
							ְ��
						</td>
						<td >
							<input type="text" id="lxr_zw" name="lxr_zw" 
								value="<bean:write name='rs' property="lxr_zw" />">
						</td>
						<td align="center">
							��ͥ��ϵ�绰
						</td>
						<td>
							<input type="text" id="lxr_jtlxdh" name="lxr_jtlxdh" 
								value="<bean:write name='rs' property="lxr_jtlxdh" />">
						</td>
					</tr>
				<tr>
						<td align="center">
							���֤����
						</td>
						<td >
							<input type="text" id="lxr_sfzh" name="lxr_sfzh" 
								value="<bean:write name='rs' property="lxr_sfzh" />">
						</td>
						<td align="center">
							��λ��ϵ�绰
						</td>
						<td>
							<input type="text" id="lxr_dwlxdh" name="lxr_dwlxdh" 
								value="<bean:write name='rs' property="lxr_dwlxdh" />">
						</td>
					</tr>	
					<tr>
						<td align="center">
							������λ/��ַ
						</td>
						<td >
							<input type="text" id="lxr_gzdw_dz" name="lxr_gzdw_dz" style="width:80%"
								value="<bean:write name='rs' property="lxr_gzdw_dz" />">
						</td>
						<td align="center">
							��λ�ʱ�
						</td>
						<td>
							<input type="text" id="lxr_dwyb" name="lxr_dwyb" 
								value="<bean:write name='rs' property="lxr_dwyb" />">
						</td>
				</tr>	
				</tbody>
				<tbody>
					<tr>
						<td rowspan="7">
							<div align="center">��<br>��<br>��<br>��<br>ͥ<br>��<br>��<br>��<br>��</div>
						</td>
						<td align="center">
							��ͥ��ϸסַ
						</td>
						<td colspan="3">
							<input type="text" id="jkr_jtxxzz" name="jkr_jtxxzz" style="width:80%"
								value="<bean:write name='rs' property="jkr_jtxxzz" />">
						</td>
					</tr>
					<tr>
						<td align="center">
							�ʱ�
						</td>
						<td >
							<input type="text" id="jkr_yb" name="jkr_yb"
								value="<bean:write name='rs' property="jkr_yb" />">
						</td>
						<td align="center">
							�绰
						</td>
						<td>
							<input type="text" id="jkr_dh" name="jkr_dh"
								value="<bean:write name='rs' property="jkr_dh" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							��������
						</td>
						<td >
							<input type="text" id="jkr_fq_xm" name="jkr_fq_xm"
								value="<bean:write name='rs' property="jkr_fq_xm" />">
						</td>
						<td align="center">
							����ְҵ
						</td>
						<td>
							<input type="text" id="jkr_fq_zy" name="jkr_fq_zy"
								value="<bean:write name='rs' property="jkr_fq_zy" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							�������֤����
						</td>
						<td >
							<input type="text" id="jkr_fq_sfzh" name="jkr_fq_sfzh"
								value="<bean:write name='rs' property="jkr_fq_sfzh" />">
						</td>
						<td align="center">
							ĸ������
						</td>
						<td>
							<input type="text" id="jkr_mq_xm" name="jkr_mq_xm"
								value="<bean:write name='rs' property="jkr_mq_xm" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							ĸ��ְҵ
						</td>
						<td >
							<input type="text" id="jkr_mq_zy" name="jkr_mq_zy"
								value="<bean:write name='rs' property="jkr_mq_zy" />">
						</td>
						<td align="center">
							ĸ������
						</td>
						<td>
							<input type="text" id="jkr_mq_xm" name="jkr_mq_xm"
								value="<bean:write name='rs' property="jkr_mq_xm" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							ĸ�����֤����
						</td>
						<td >
							<input type="text" id="jkr_mq_sfzh" name="jkr_mq_sfzh"
								value="<bean:write name='rs' property="jkr_mq_sfzh" />">
						</td>
						<td></td>
						<td></td>
				</tr>
				<tr>
					<td style="height:50px" colspan="4">
						<div style="width:90%">
							�����ڴ˳�ŵ�ṩ�������ļ������Ϻ�ƾ֤��������Ͼ�Ϊ׼ȷ����ʵ����������Ч�ģ�������Ը�е�һ��������Ρ�
							���б䶯�����˳�ŵ�ڱ䶯��һ���ڽ��䶯�����ʼ����й�����
							<input type="text" id="yhmc" name="yhmc" size="20"
								value="<bean:write name='rs' property="yhmc" />">
							��/֧�С�
							���ṩ������ϻ�δ�ܼ�ʱ���ͱ�����ϣ�������Ȩ�϶�����ΥԼ�����ɰ��ա��й����й�����ѧ�������ͬ��
							�е����Լ��׷�����˵�ΥԼ���Ρ�
						</div>
					</td>
				</tr>
				</tbody>
				<tbody>
					<tr>
						<td align="center">
							<div align="center">��<br>ע</div>
						</td>
						<td colspan="4"><span><font color="blue">��500���ַ�</font></span>
							<html:textarea name='rs' property='bz' style="width:99%" styleId="bz" rows='6' />
						</td>
					</tr>
				</tbody>	
			</table>	
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="rs" property="doType">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					�ύ����
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut();" style="width:80px">
					�� ӡ
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<logic:notEqual value="student" scope="session" name="userOnLine">
					<button type="button" class="button2" onClick="Close();" style="width:80px">
						�ر�
					</button>
				</logic:notEqual>
			</div>	
		</html:form><%--
	</logic:equal>
--%></body>
	<logic:present name="ok">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("����ɹ���");
	   			if(window.dialogArguments){
	   				dialogArgumentsQueryChick();
	   				Close();
	   			}
	   		
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
</html:html>
