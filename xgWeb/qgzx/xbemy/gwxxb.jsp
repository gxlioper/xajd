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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script language="javascript">
	function getValue(){
		var value="";
		var syrs=document.getElementById("xyrs").value;
		var knsbl=document.getElementById("knsbl").value;
		//alert(syrs);
		value=Math.round(syrs*(knsbl*0.01));
		document.getElementById("xyknsrs").value=value;
	}
	
</script>
		<base target="_self" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<html:form action="/comm_pub" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="dyxmdm" name="dyxmdm" value="001" />
			<input type="hidden" id="tabName" name="tabName" value="view_gwxx" />
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
								&nbsp;
							</td>
						</tr>
					</thead>
					<tr>
						<td width="26%" height="22" align="right">
							У����
						</td>
						<td width="40%" height="22" align="left">
							<html:select name="rs" property="xqdm" style="width:120px"
								styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xqList1" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td width="31%" height="22" align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td width="1%" height="22" align="left">
							<html:text name="rs" property="gwdm" styleId="gwdm" onkeyup="value=value.replace('-','')"/>
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
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
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
							<html:text name="rs" property="xyrs" styleId="xyrs" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>ʹ������������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xyknsrs" styleId="xyknsrs" />
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
							<html:text name="rs" property="jybcbz" styleId="jybcbz" />
							<span id="jybcbzDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>����ʱ�䣺
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="gzsj" styleId="gzsj" />
							<span id="gzsjDw"></span>
						</td>
						<td height="22" align="right">
							��ϵ�绰��
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>�������ݣ�
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								style="width:100%" rows="5"></html:textarea>
						</td>
					</tr>
					<logic:notPresent name="showshgc">
						<tr>
							<td height="22" align="right">
								���뱨�棺
							</td>
							<td height="22" colspan="3" align="left">
								<html:textarea name="rs" property="sqbg" style="width:100%"
									rows="5"></html:textarea>
							</td>
						</tr>
					</logic:notPresent>
					<tr>
						<td height="22" align="right">
							���뵥λ�����
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="sqdwyj" style="width:100%"
								rows="5"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							��ע��
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:100%"
								rows="5"></html:textarea>
						</td>
					</tr>
					<logic:notEmpty name="filedList">
					<thead>
					<tr>
						<td style="cursor:hand" align="center" colspan="4">
							<center>���븽����Ϣ</center>
						</td>
					</tr>
					</thead>
					<input type="hidden" id="rsNum" name="rsNum" value="<bean:write name="rsNum"/>"/>
					<logic:iterate id="filed" name="filedList" indexId="index">
						<tr>
							<td align="right">								
								<bean:write name="filed" property="zdmc"/>��
								<input type="hidden" id="zdlx${index}" name="zdlx${index}" value="<bean:write name="filed" property="zdlx"/>"/>
								<input type="hidden" id="bxwsz${index}" name="bxwsz${index}" value="<bean:write name="filed" property="bxwsz"/>"/>															
							</td>
							<td colspan="3">
							<div id="div1${index}" style="display:none">				
								<input id="zddm${index}" name="zddm${index}" style="width:100%" value="<bean:write name="rs" property="${filed.zddm}"/>"
								onkeyup="javascript:document.getElementById('${filed.zddm}').value=this.value">
							</div>
							<div id="div2${index}" style="display:none">
								<textarea id="zddm${index}" name="zddm${index}"  style="width:100%;height:45px" value=""
								onkeyup="javascript:document.getElementById('${filed.zddm}').value=this.value.replace(/\s+/g,'');">
								<bean:write name="rs" property="${filed.zddm}"/>
								</textarea>
							</div>
							<div id="div3${index}" style="display:none">				
								<input type="text" id="zddm${index}" name="zddm${index}" style="width:100%" value="<bean:write name="rs" property="${filed.zddm}"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') ;javascript:document.getElementById('${filed.zddm}').value=this.value"
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
							</div>
							<input id="${filed.zddm}" name="${filed.zddm}" value="" type="hidden"/>
							</td>							
						</tr>
					</logic:iterate>
					<script>
						var num = document.getElementById("rsNum").value;
						var zdlx = "";
						var bxwsz = "";	
						for(var i=0; i<parseInt(num); i++){
							zdlx = document.getElementById("zdlx" + i).value;
							bxwsz = document.getElementById("bxwsz" + i).value;
							if(zdlx!=null && zdlx=="�ı���" && bxwsz!=null && bxwsz=="��"){
								document.getElementById("div2" + i).style.display='';
							}
							if(zdlx!=null && zdlx=="һ���ı�" && bxwsz!=null && bxwsz=="��"){
								document.getElementById("div1" + i).style.display='';
							}
							if(bxwsz!=null && bxwsz=="��"){
								document.getElementById("div3" + i).style.display='';
							}
							
						}
					</script>
					</logic:notEmpty>
				</table>
				<font color="red">��ʾ:У�ڹ̶���λʹ���������������õ���<bean:write name="rs"
						property="knsbl" />%</font>
				<br />
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center" class="buttontool">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gznr-gzjssj-gzsj')) BatAlert.showTips('���ڲ����У����Ե�...'); "
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="refreshForm('xbemyQgzx.do?method=printGwxx')">
							��ӡ����
							</button>
							
						</div>
					</logic:match>
				</logic:present>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
