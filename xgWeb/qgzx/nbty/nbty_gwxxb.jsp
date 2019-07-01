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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>

	<script language="javascript">
	function getValue(){
		var value="";
		var syrs=document.getElementById("xyrs").value;
		var knsbl=document.getElementById("knsbl").value;
		value=Math.round(syrs*(knsbl*0.01));
		document.getElementById("xyknsrs").value=value;
	}
	
	function print1(){
		window.open('/xgxt/qgzx/nbty/nbtygwxxb.jsp');
	}
	
	function save(){
		var gznr = document.getElementById('gznr').value;
		var ryyq = document.getElementById('ryyq').value;
		if($('sqbg')){
		var sqbg = document.getElementById('sqbg').value;
		//���뱨��
		if(sqbg != null && sqbg != ''){
			if(sqbg.replace(/[^\u0000-\u00ff]/g, "**").length > 125){	         
	          	alert("���뱨�治�ܴ���125���ַ�");
	          	return false;
	       }
		}
		}
		var bz = document.getElementById('bz').value;
		//��������
		if(gznr != null && gznr != ''){
			if(gznr.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          	alert("�������ݲ��ܴ���150���ַ�");
	          	return false;
	       }
		}
		// ��ԱҪ��
		if(ryyq != null && ryyq != ''){
			if(ryyq.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          	alert("��ԱҪ���ܴ���100���ַ�");
	          	return false;
	       }
		}
		
		//��ע
		if(bz != null && bz != ''){
			if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          	alert("��ע���ܴ���60���ַ�");
	          	return false;
	       }
		}
		if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gznr-gzjssj-gzsj')) 
			BatAlert.showTips('���ڲ����У����Ե�...'); 
	}
</script>
	<body>
		
		<html:form action="/comm_pub" method="post">
			<logic:present name="gwsbsj">
				<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="gwsbsj"/>" />
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��λ���� - ��λ��Ϣ����
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
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="knsbl" name="knsbl"
					value="<bean:write name="rs" property="knsbl"/>" />
				<input type="hidden" id="xueqi" name="xueqi"
					value="<bean:write name="rs" property="xueqi"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/gwxxb.jsp" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								��λ��Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							У����
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="xqdm" style="width:120px"
								styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xqList1" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td height="22" align="left">							
							<logic:notEqual value="modi" name="doType">
								<html:text name="rs" property="gwdm" styleId="gwdm"
									onkeyup="value=value.replace('-','')" maxlength="20" />
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:text name="rs" property="gwdm" styleId="gwdm"
									readonly="true"  maxlength="20"/>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��λ���ʣ�
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="gwxz" style="width:120px"
								styleId="gwxz">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc" />
							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>���뵥λ��
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="sqdw" styleId="sqdw"
								style="width:120px" onchange="getYrdwInfo()">
								<html:option value=""></html:option>
								<html:options collection="sqdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							ѧ�꣺
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xn" style="width: 90px"
								readonly="true" />
						</td>
						<td height="22" align="right">
							��ȣ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="nd" style="width: 90px"
								readonly="true" />

						</td>

					</tr>
					<tr>
						<td height="22" align="right">
							������ʼ���ڣ�
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzkssj" styleId="gzkssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzkssj','y-mm-dd');" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>�����������ڣ�
						</td>
						<td height="22" align="left">
							<html:text name='rs' property="gzjssj" styleId="gzjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('gzjssj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>����������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyrs" styleId="xyrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>ʹ������������

						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyknsrs" styleId="xyknsrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>�Ƴ귽ʽ��
						</td>
						<td height="22" align="left">
							<html:select name="rs" property="jcfs" onchange="subloadPost();loadJcbz(this.value)">
									<html:options collection="jcfsList" property="dm" labelProperty="mc"/>
<!--								<html:option value="">------��ѡ��------</html:option>-->
<!--								<html:option value="h">��Сʱ</html:option>-->
<!--								<html:option value="d">����</html:option>-->
<!--								<html:option value="w">����</html:option>-->
<!--								<html:option value="m">����</html:option>-->
							</html:select>
						</td>
						<td height="22" align="right">
							<font color="red">*</font>���鱨���׼��
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="jybcbz" styleId="jybcbz" maxlength="20"/>
							<span id="jybcbzDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>����ʱ�䣺
						</td>
						<td height="22" align="left" colspan="3">
							<html:text name="rs" property="gzsj" styleId="gzsj" maxlength="20" style="width:70%"/>
							(������һ�ϣ��ܶ���...)
							<span id="gzsjDw"></span>
						</td>						
					</tr>
					<tr>
						<td height="22" align="right">
							��λ�����ˣ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="fzr" styleId="fzr" maxlength="20"/>							
						</td>
						<td height="22" align="right">
							��ϵ�绰��
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "
								maxlength="15" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							�����ص㣺
						</td>
						<td height="22" colspan="3" align="left">							
							<html:textarea name="rs" property="gzdd" styleId="gzdd"
								style="width:100%" rows="3" onblur="chLeng(this,'50')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>�������ݣ�
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								style="width:100%" rows="5" onblur="chLeng(this,'150')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��ԱҪ��
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="ryyq" style="width:100%" rows="5" styleId="ryyq" onblur="chLeng(this,'100')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							���뱨�棺
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="sqbg" style="width:100%" rows="5" styleId="sqbg" onblur="chLeng(this,'125')"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							���뵥λ�����
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="sqdwyj" style="width:100%" onblur="chLeng(this,'100')"
								rows="5"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��ע��
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:100%" rows="5" styleId="bz" onblur="chLeng(this,'60')"/>
						</td>
					</tr>
				</table>
				<font color="red">��ʾ:��λʹ���������������õ���<bean:write name="rs" property="knsbl" />%</font>
				<br />
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center" class="buttontool">
							<button type="button" class="button2"
								onclick="save();return false;"
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="print1();">
								�� ӡ �� ��
							</button>
						</div>
					</logic:match>
				</logic:present>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
