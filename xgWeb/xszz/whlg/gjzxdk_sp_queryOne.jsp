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
	<title><bean:message key="lable.title" /></title>
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
			refreshForm("whlg_xszz.do?method=gjzxdksp&doType=add");
		}
	
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "";
			document.forms[0].submit();
		}
		
		//unchecked
		function cancelXz(){
			var xlns = document.getElementsByName("xlns");
			for(var i=0;i<xlns.length;i++){
				xlns[i].checked = false;
			}	
		}
		
		function cancelSs(){
			var xl = document.getElementsByName("xl");
			for(var i=0;i<xl.length;i++){
				if(xl[i].value == "�о���"){
					xl[i].checked = false;
				}else if(xl[i].value == "����"){
					xl[i].checked = true;
				}
			}
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ������������ - ������ѧ������������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
	<%--
	<logic:equal name="sfksq" value="1">
		--%>
	<html:form action="/whlg_xszz.do?method=gjzxdksp" method="post">
		<input type="hidden" id="url" name="url"
			value="/whlg_xszz.do?method=gjzxdksp" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc-zzmmmc-sfzh-csrq-xz-zymc-nj" />
		<table class="tbstyle" width="100%">
			<tbody>
				<tr>
					<td rowspan="13">
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
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
								value="<bean:write name='rs' property="xh" />"
								readonly="readonly" />
						</td>
					</logic:equal>
					<td>
						<div align="center">
							���������
						</div>
					</td>
					<td scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="csrq" name="csrq"
							value="<bean:write name='rs' property="csrq" />"
							readonly="readonly" />
					</td>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<logic:notEmpty name="rs" property="xb">
							<logic:match value="��" name="rs" property="xb">
								<input type="radio" name="xb" value="��" checked
									readonly="readonly">&nbsp;&nbsp;��
							    <input type="radio" name="xb" value="Ů" readonly="readonly">&nbsp;&nbsp;Ů
							</logic:match>
							<logic:notMatch value="��" name="rs" property="xb">
								<input type="radio" name="xb" value="��" readonly="readonly">&nbsp;&nbsp;��
							    <input type="radio" name="xb" value="Ů" checked
									readonly="readonly">&nbsp;&nbsp;Ů
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="xb">
							<input type="radio" name="xb" value="��" readonly="readonly">&nbsp;&nbsp;��
							    <input type="radio" name="xb" value="Ů" readonly="readonly">&nbsp;&nbsp;Ů
						</logic:empty>
						<script type="text/javascript">
							var xm = document.getElementById("xm").value;
							if(xm != null && xm != ""){
								var xbGro = document.getElementsByName("xb");
								for(var i=0;i<xbGro.length;i++){
									xbGro[i].disabled = true;
								}
							}
						</script>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							����
						</div>
					</td>
					<td>
						<input type="text" id="mzmc" name="mzmc"
							value="<bean:write name='rs' property="mzmc" />"
							readonly="readonly" />
					</td>
					<td scope="row">
						<div align="center">
							���֤����
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							value="<bean:write name='rs' property="sfzh" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="center">
						�꼶
					</td>
					<td>
						<input type="text" id="nj" name="nj" readonly="readonly"
							value="<bean:write name='rs' property="nj" />">
					</td>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							value="<bean:write name='rs' property="zymc" />"
							readonly="readonly" />
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							value="<bean:write name='rs' property="bjmc" />"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="center">
						ѧ��
					</td>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							value="<bean:write name='rs' property="xz" />">
					</td>
					<td>
						<div align="center">
							�Ͷ�ѧУ
						</div>
					</td>
					<td>
						<input type="text" id="xxmc" name="xxmc"
							value="<bean:write name='rs' property="xxmc" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ѧ���
						</div>
					</td>
					<td>
						<input type="text" id="rxnf" name="rxnf"
							value="<bean:write name='rs' property="rxnf" />" />
					</td>
					<td scope="row">
						<div align="center">
							��ҵʱ��
						</div>
					</td>
					<td>
						<input type="text" id="bynf" name="bynf"
							value="<bean:write name='rs' property="bynf"/>">
					</td>
				</tr>
				<tr>
					<td align="center">
						����绰
					</td>
					<td>
						<input type="text" id="ssdh" name="ssdh"
							value="<bean:write name='rs' property="ssdh" />">
					</td>
					<td align="center">
						������������������
					</td>
					<td>
						<input type="text" id="ghlmkh" name="ghlmkh"
							value="<bean:write name='rs' property="ghlmkh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						ѧ��
					</td>
					<td colspan="3">
						<input type="radio" name="xl" value="����" />
						&nbsp;&nbsp;�� (
						<input type="radio" name="xlns" value="4" onclick="cancelSs()" />
						&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="xlns" value="5" onclick="cancelSs()" />
						&nbsp;&nbsp;��
						)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="xl" value="�о���" onclick="cancelXz()" />
						&nbsp;&nbsp;��
						<input type="hidden" value="<bean:write name="rs" property="xl"/>"
							id="xlHidden" />
						<script type="text/javascript">
							var xlHidden = document.getElementById("xlHidden").value;
							var xlGro = document.getElementsByName("xl");
						/*	for(var k=0;k<xlGro.length;k++){
								if(xlGro[k].value == xlHidden){
									xlGro[k].checked = true;
								}
							}
						*/	
							var xz  = document.getElementById("xz").value;
							var var_xlns = document.getElementsByName("xlns");
							for(var i=0;i<var_xlns.length;i++){
								if(var_xlns[i].value == xz){
									var_xlns[i].checked = true;
									xlGro[0].checked = true;  //���������
								}
							}
							
							//�ж��� ѧ�� ����û������
							if (xz != null && xz != ""){
								for(var k=0;k<xlGro.length;k++){
									xlGro[k].disabled = true;
								}
								for(var j=0;j<var_xlns.length;j++){
									var_xlns[j].disabled = true;
								}
							}
							</script>
					</td>
				</tr>
			</tbody>
			<tr style="height:5px"></tr>
			<tbody>
				<tr>
					<td align="center" rowspan="3">
						<div align="center">
							��
							<br>
							��
							<br>
							��
							<br>
							Ϣ
							<br>
						</div>
					</td>
					<td>
						<div align="center">
							���������
						</div>
					</td>
					<td>
						<div>
							�ܽ�
							<input type="text" id="sqdkzje" name="sqdkzje"
								style="width:100px"
								value="<bean:write name='rs' property="sqdkzje" />">
							Ԫ
							<br>
							������У�ڼ䣺����������������
							<br>
							<div align="right">
								ѧ�ӷѴ����ܶ
								<input type="text" id="xzfdkzje" name="xzfdkzje"
									style="width:100px"
									value="<bean:write name='rs' property="xzfdkzje" />">
								Ԫ
								<br>
								ס�޷Ѵ����ܶ
								<input type="text" id="zsfdkzje" name="zsfdkzje"
									style="width:100px"
									value="<bean:write name='rs' property="zsfdkzje" />">
								Ԫ
							</div>
						</div>
					</td>
					<td rowspan="2">
						<div align="center">
							��ȷſ���
						</div>
					</td>
					<td rowspan="2">
						<div>
							<input type="text" id="nd_one" name="nd_one" style="width:60px"
								value="<bean:write name='rs' property="nd_one" />">
							&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_one_fkje" name="nd_one_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_one_fkje" />">
							&nbsp;&nbsp;Ԫ
							<br>
							<input type="text" id="nd_two" name="nd_two" style="width:60px"
								value="<bean:write name='rs' property="nd_two" />">
							&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_two_fkje" name="nd_two_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_two_fkje" />">
							&nbsp;&nbsp;Ԫ
							<br>
							<input type="text" id="nd_three" name="nd_three"
								style="width:60px"
								value="<bean:write name='rs' property="nd_three" />">
							&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_three_fkje" name="nd_three_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_three_fkje" />">
							&nbsp;&nbsp;Ԫ
							<br>
							<input type="text" id="nd_four" name="nd_four" style="width:60px"
								value="<bean:write name='rs' property="nd_four" />">
							&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_four_fkje" name="nd_four_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_four_fkje" />">
							&nbsp;&nbsp;Ԫ
							<br>
							<input type="text" id="nd_five" name="nd_five" style="width:60px"
								value="<bean:write name='rs' property="nd_five" />">
							&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" id="nd_five_fkje" name="nd_five_fkje"
								style="width:60px"
								value="<bean:write name='rs' property="nd_five_fkje" />">
							&nbsp;&nbsp;Ԫ
							<br>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center">
						��У����
					</td>
					<td>
						<input type="text" id="zxnx" name="zxnx"
							value="<bean:write name='rs' property="zxnx" />">
						&nbsp;&nbsp;��&nbsp;&nbsp;(Сд)
					</td>
				</tr>
				<tr>
					<td align="center">
						��������
					</td>
					<td colspan="3">
						<input type="text" id="dkqx_months" name="dkqx_months"
							value="<bean:write name='rs' property="dkqx_months" />">
						&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��
						<html:text name='rs' property="dkqx_kssj" styleId="dkqx_kssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('dkqx_kssj','y-mm-dd');" />
						&nbsp;&nbsp;&nbsp;&nbsp; ��
						<html:text name='rs' property="dkqx_jssj" styleId="dkqx_jssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('dkqx_jssj','y-mm-dd');" />
					</td>
				</tr>
			</tbody>
			<tr style="height:3px">
			</tr>
			<tbody>
				<tr>
					<td rowspan="7">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							��
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td align="center">
						��ͥ��ϸסַ
					</td>
					<td colspan="3">
						<input type="text" id="jkr_jtxxzz" name="jkr_jtxxzz"
							style="width:80%"
							value="<bean:write name='rs' property="jkr_jtxxzz" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						�ʱ�
					</td>
					<td>
						<input type="text" id="jkr_yb" name="jkr_yb"
							value="<bean:write name='rs' property="jkr_yb" />">
					</td>
					<td align="center">
						��ͥ��ϵ�绰
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh"
							value="<bean:write name='rs' property="jtdh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						��������
					</td>
					<td>
						<input type="text" id="jkr_fq_xm" name="jkr_fq_xm"
							value="<bean:write name='rs' property="jkr_fq_xm" />">
					</td>
					<td align="center">
						�������֤����
					</td>
					<td>
						<input type="text" id="jkr_fq_sfzh" name="jkr_fq_sfzh"
							value="<bean:write name='rs' property="jkr_fq_sfzh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						����ְҵ
					</td>
					<td colspan="3">
						<input type="text" id="jkr_fq_zy" name="jkr_fq_zy"
							style="width:80%"
							value="<bean:write name='rs' property="jkr_fq_zy" />">
					</td>

				</tr>
				<tr>
					<td align="center">
						ĸ������
					</td>
					<td>
						<input type="text" id="jkr_mq_xm" name="jkr_mq_xm"
							value="<bean:write name='rs' property="jkr_mq_xm" />">
					</td>
					<td align="center">
						ĸ�����֤����
					</td>
					<td>
						<input type="text" id="jkr_mq_sfzh" name="jkr_mq_sfzh"
							value="<bean:write name='rs' property="jkr_mq_sfzh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						ĸ��ְҵ
					</td>
					<td colspan="3">
						<input type="text" id="jkr_mq_zy" name="jkr_mq_zy"
							style="width:80%"
							value="<bean:write name='rs' property="jkr_mq_zy" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						��ͥ������
					</td>
					<td>
						<input type="text" id="jtysr" name="jtysr"
							value="<bean:write name='rs' property="jtysr" />">
						Ԫ
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
			<tr style="height:3px">
			</tr>
			<tbody>
				<tr>
					<td align="center">
						<div align="center">
							��
							<br>
							ע
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">��500���ַ�</font>
						</span>
						<html:textarea name='rs' property='bz' style="width:99%"
							styleId="bz" rows='6' />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center">
							��
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">��500���ַ�</font>
						</span>
						<html:textarea name='rs' property='xdyyj' style="width:99%"
							styleId="xdyyj" rows='4' />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center">
							��
							<br>
							��
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">��500���ַ�</font>
						</span>
						<html:textarea name='rs' property='kzyj' style="width:99%"
							styleId="kzyj" rows='4' />
					</td>
				</tr>
				<tr>
					<td align="center">
						<div align="center">
							��
							<br>
							Ȩ
							<br>
							��
							<br>
							׼
							<br>
							��
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td colspan="4">
						<span><font color="blue">��500���ַ�</font>
						</span>
						<html:textarea name='rs' property='yqspryj' style="width:99%"
							styleId="yqspryj" rows='6' />
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
	</html:form>
	<%--
	</logic:equal>
--%>
</body>
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
