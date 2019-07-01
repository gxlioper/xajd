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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function sumTime(){
			var sum=0;
			var tem=0;
			var mxsbc=document.forms[0].mxsbc.value;
			if(mxsbc==''|| parseInt(mxsbc)==0){
				mxsbc = document.getElementById('spbcbz').value;
			}
			for(var i=1;i<32;i++){
				tem=document.getElementById("sj"+i).value;
				sum=sum+tem*1;
			}
			document.getElementById("ljxs").innerText=sum;
			document.forms[0].ysj.value = sum;
			document.getElementById("zq").innerText=sum*mxsbc
			document.forms[0].yje.value = sum*mxsbc;
		}
		
   	function sumpd(){
			var sum=document.forms[0].ysj.value;
			var myzgxs=document.forms[0].myzgxs.value;
			var xxdm = document.getElementById("xxdm").value;
			if(sum == null || sum==''){
				sum = 0;
			}
			if(myzgxs == null || myzgxs==''){
				myzgxs = 0;
			}
			if(parseFloat(sum)>parseFloat(myzgxs)&& xxdm != '13022'){
				alert("�ܹ���ʱ�䳬��ÿ�������ʱ��!");
				return false;
			}
			//�������ݻ�����������ж�
			
			if(xxdm=="10100"){
				//�ȷ�ʦ��
				var kshour =parseInt(document.getElementById("kshour").value);
				var jshour =parseInt(document.getElementById("jshour").value);
				var jsminute =parseInt(document.getElementById("jsminute").value);
				var ksminute =parseInt(document.getElementById("ksminute").value);
				if(kshour=="" || ksminute==""){
					alert("������ʼʱ��Ϊ��!");
					return false;
				}
				if(jshour=="" || jsminute==""){
					alert("��������ʱ��Ϊ��!");
					return false;
				}
				if(kshour<0 ||kshour>23){
					alert("������ʼСʱ����ȷ��");
					return false;
				}
				if(jshour<0 ||jshour>23){
					alert("��������Сʱ����ȷ��");
					return false;
				}
				if(ksminute<0 || ksminute>60){
					alert("������ʼ���Ӳ���ȷ!");
					return false;
				}
				if(jsminute<0 || jsminute>60){
					alert("�����������Ӳ���ȷ!");
					return false;
				}				
			}
			return true;
		}
		
		function initValue(){
			 var kssj = document.getElementById("kssj").value;
			 var jssj = document.getElementById("jssj").value;
			 if(kssj!=null && kssj!=""){
			 	 document.getElementById("kshour").value = kssj.substring(0,kssj.indexOf('��'));
			 	 document.getElementById("ksminute").value = kssj.substring(kssj.indexOf('��')+1,jssj.indexOf('��'));
			 	 document.getElementById("jshour").value = jssj.substring(0,jssj.indexOf('��'));
			 	 document.getElementById("jsminute").value = jssj.substring(jssj.indexOf('��')+1,jssj.indexOf('��'));
			 }
		}
		function printYkqb(){
			document.forms[0].action = "/xgxt/qgzxNblg.do?method=printYkqb";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body
		onload="checkWinType();document.all('buttonClose').focus();sumTime();">
		<html:form action="/data_search" method="post">
		<logic:present name="yf">
		<input type="hidden"  id="yf" name="yf" value="<bean:write name="yf"/>"/>
		</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
				��ǰ����λ�ã�ѧ���幤 - ���� - ѧ��������¼ - ����ѧ�����������¼
			</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
				��ǰ����λ�ã��ڹ���ѧ - ���� - ѧ��������¼ - ����ѧ�����������¼
			</logic:notEqual>
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="doType"
				value="<bean:write name="doType"/>" />
			<input type="hidden" name="xh"
				value="<bean:write name="rs" property="xh" />" />
			<input type="hidden" id="mxsbc" name="mxsbc"
				value="<bean:write name="mxsbc" scope="request"/>" />
			<input type="hidden" id="myzgxs" name="myzgxs"
				value="<bean:write name="myzgxs" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="ysj" name="ysj" value="" />
			<input type="hidden" id="yje" name="yje" value="" />
			<input type="hidden" id="yrdwmc" name="yrdwmc" value="${rs.yrdwmc}" />
			<input type="hidden" id="spbcbz" name="spbcbz" value="${rs.spbcbz}" />
			<table width="100%" align="center" class="tbstyle" id="tb">
				<tr>
					<td>
						<table width="100%" align="center" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										����ѧ�����������¼
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<td align="right">
									ѧ�ţ�
								</td>
								<td align="left">
									<input type="hidden" id="xh" name="xh"
										value="<bean:write name="rs" property="xh" />" />
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right">
									��ȣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="nd" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
									<input type="hidden" name="xm"
										value="<bean:write name="rs" property="xm" />" />
								</td>
								<td align="right">
									ѧ�꣺
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									��λ���ƣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="gwdm" />
									<input type="hidden" id="gwmc" name="gwmc" value="${rs.gwdm}" />
									<input type="hidden" id="gwdm" name="gwdm" value="${rs.gwdm}" />
									<input type="hidden" id="gwmc" name="gwsbsj"
										value="${rs.gwsbsj}" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�꼶��
								</td>
								<td align="left">
									<bean:write name="rs" property="nj" />
								</td>
								<td align="right">
									����ʱ�䣺
								</td>
								<td align="left">
									<bean:write name="rs" property="sqsj" />
									<input type="hidden" id="sbsj" name="sbsj" value="<bean:write name="rs" property="sqsj" />"/>
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
									<input type="hidden" name="xymc"
										value="<bean:write name="rs" property="xymc" />" />
								</td>
								<td align="right">
									�Ƿ���������
								</td>
								<td align="left">
									<bean:write name="rs" property="sfpks" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									רҵ��
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
								<td align="right">
									��ϵ�绰��
								</td>
								<td align="left">
									<bean:write name="rs" property="lxdh" />
								</td>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�༶��
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
									<input type="hidden" name="bjmc"
										value="<bean:write name="rs" property="bjmc" />" />
								</td>
								<td align="right">
									��ˣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="yesNo" />
								</td>
							</tr>
							<%--�������ϴ�ѧ--%>
							<logic:equal value="11417" name="xxdm">
								<tr>
									<td align="right">
										ʱ�䣺
									</td>
									<td colspan="3">
										<html:select property="nd" name="rs"
											onchange="refreshForm('qgzxBjlhdx.do?method=changeWorkLog')"
											styleId="nd">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;��&nbsp;&nbsp;
										<html:select property="yf" name="rs"
											onchange="refreshForm('qgzxBjlhdx.do?method=changeWorkLog')"
											styleId="yf">
											<html:option value=""></html:option>
											<html:options collection="yfList" property="yf"
												labelProperty="yf" />
										</html:select>
										&nbsp;&nbsp;��
									</td>
								</tr>
							</logic:equal>
							<!----��������----->
							<logic:equal value="10690" name="xxdm">
								<tr>
									<td align="right">
										����̬�ȣ�
									</td>
									<td align="left">
										<html:select name="rs" property="gztd" style="width:120px"
											styleId="gztd">
											<html:option value="">
											</html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
										</html:select>
									</td>
									<td align="right">
										����������
									</td>
									<td align="left">
										<html:select name="rs" property="gzzl" style="width:120px"
											styleId="gzzl">
											<html:option value="">
											</html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
										</html:select>
									</td>
								</tr>
							</logic:equal>
							<%--�ȷ�ʦ��<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="10100" name="xxdm">
								<tr>
									<td align="right">
										������ʼʱ�䣺
									</td>
									<td>
										<input type="hidden" id="kssj" name="kssj"
											value="${rs.gzqssj}" />
										<input type="text" id="kshour" name="kshour"
											style="width:50px">
										��
										<input type="text" id="ksminute" name="ksminute"
											style="width:50px">
										��
									</td>
									<td align="right">
										��������ʱ�䣺
									</td>
									<td>
										<input type="hidden" id="jssj" name="jssj"
											value="${rs.gzjssj}" />
										<input type="text" id="jshour" name="jshour"
											style="width:50px">
										��
										<input type="text" id="jsminute" name="jsminute"
											style="width:50px">
										��
									</td>
								</tr>
								<tr>
									<td align="right">
										�������ݣ�
									</td>
									<td colspan="3">
										<html:textarea name="rs" property="gznr" style="width:100%"
											styleId="gznr"></html:textarea>
									</td>
								</tr>
								<script>
								initValue();
							</script>
							</logic:equal>
							<%--end�ȷ�ʦ��<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:present name="xskh">
								<tr>
									<td align="right">
										�������֣�
									</td>
									<td align="left">
										<html:select name="rs" property="gzbx" style="width:120px"
											styleId="gzbx">
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
											<html:option value="��"> �� </html:option>
										</html:select>
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>
							</logic:present>
							<logic:present name="gzjl">
								<!--������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:equal value="13742" name="xxdm">								
								<td align="right">
									�������ݣ�
								</td>
								</logic:equal>
								<!--end������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<!--��������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:notEqual value="13742" name="xxdm">
								<td align="right">
									���������
								</td>
								</logic:notEqual>
								<!--end��������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<td colspan="3">
									<html:textarea name='rs' property='xsgzqk' rows="4"
										styleId="xsgzqk" style="width:99%" onblur="chLeng(this,120)"/>
								</td>
							</logic:present>
							<logic:present name="xskh">
								<!--������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:equal value="13742" name="xxdm">								
								<td align="right">
									�������ݣ�
								</td>
								</logic:equal>
								<!--end������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<!--��������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<logic:notEqual value="13742" name="xxdm">
								<td align="right">
									���������
								</td>
								</logic:notEqual>
								<!--end��������һְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
								<td colspan="3">
									<html:textarea name='rs' property='xsgzqk' rows="4"
										styleId="xsgzqk" readonly="true" style="width:99%" />
								</td>
							</logic:present>
							<%--�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="13022" name="xxdm">
								<tr>
									<td align="right">
										��ע��
									</td>
									<td colspan="3">
										<html:textarea name='rs' property='bz' rows="4" styleId="bz"
											style="width:99%" />
									</td>
								</tr>
							</logic:equal>
							<%--end�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" class="tbstyle" id="rsTable">
							<tr height="25">
								<td align="center">
									27
								</td>
								<td align="center">
									28
								</td>
								<td align="center">
									29
								</td>
								<td align="center">
									30
								</td>
								<td align="center">
									31
								</td>
								<td align="center">
									1
								</td>
								<td align="center">
									2
								</td>
							</tr>
							<tr height="45">
								<logic:present name="day27">
									<td align="center">
										<html:select name="rs" property="day27" styleId="sj1"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day27">
									<td align="center">
										<html:select name="rs" property="day27" styleId="sj1"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day27O" name="day27O"
									value="${rs.day27}" />
								<logic:present name="day28">
									<td align="center">
										<html:select name="rs" property="day28" styleId="sj2"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day28">
									<td align="center">
										<html:select name="rs" property="day28" styleId="sj2"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day28O" name="day28O"
									value="${rs.day28}" />
								<logic:present name="day29">
									<td align="center">
										<html:select name="rs" property="day29" styleId="sj3"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day29">
									<td align="center">
										<html:select name="rs" property="day29" styleId="sj3"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day29O" name="day29O"
									value="${rs.day29}" />
								<logic:present name="day30">
									<td align="center">
										<html:select name="rs" property="day30" styleId="sj4"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day30">
									<td align="center">
										<html:select name="rs" property="day30" styleId="sj4"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day30O" name="day30O"
									value="${rs.day30}" />
								<logic:present name="day31">
									<td align="center">
										<html:select name="rs" property="day31" styleId="sj5"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day31">
									<td align="center">
										<html:select name="rs" property="day31" styleId="sj5"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day31" name="day31O"
									value="${rs.day31}" />
								<logic:present name="day1">
									<td align="center">
										<html:select name="rs" property="day1" styleId="sj6"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day1">
									<td align="center">
										<html:select name="rs" property="day1" styleId="sj6"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day1O" name="day1O" value="${rs.day1}" />
								<logic:present name="day2">
									<td align="center">
										<html:select name="rs" property="day2" styleId="sj7"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day2">
									<td align="center">
										<html:select name="rs" property="day2" styleId="sj7"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day2O" name="day2O" value="${rs.day2}" />
							</tr>
							<tr height="25">
								<td align="center">
									3
								</td>
								<td align="center">
									4
								</td>
								<td align="center">
									5
								</td>
								<td align="center">
									6
								</td>
								<td align="center">
									7
								</td>
								<td align="center">
									8
								</td>
								<td align="center">
									9
								</td>
							</tr>
							<tr height="45">
								<logic:present name="day3">
									<td align="center">
										<html:select name="rs" property="day3" styleId="sj8"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day3">
									<td align="center">
										<html:select name="rs" property="day3" styleId="sj8"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day3O" name="day3O" value="${rs.day3}" />
								<logic:present name="day4">
									<td align="center">
										<html:select name="rs" property="day4" styleId="sj9"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day4">
									<td align="center">
										<html:select name="rs" property="day4" styleId="sj9"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day4O" name="day4O" value="${rs.day4}" />
								<logic:present name="day5">
									<td align="center">
										<html:select name="rs" property="day5" styleId="sj10"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day5">
									<td align="center">
										<html:select name="rs" property="day5" styleId="sj10"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day5O" name="day5O" value="${rs.day5}" />
								<logic:present name="day6">
									<td align="center">
										<html:select name="rs" property="day6" styleId="sj11"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day6">
									<td align="center">
										<html:select name="rs" property="day6" styleId="sj11"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day6O" name="day6O" value="${rs.day6}" />
								<logic:present name="day7">
									<td align="center">
										<html:select name="rs" property="day7" styleId="sj12"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day7">
									<td align="center">
										<html:select name="rs" property="day7" styleId="sj12"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day7O" name="day7O" value="${rs.day7}" />
								<logic:present name="day8">
									<td align="center">
										<html:select name="rs" property="day8" styleId="sj13"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day8">
									<td align="center">
										<html:select name="rs" property="day8" styleId="sj13"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day8O" name="day8O" value="${rs.day8}" />
								<logic:present name="day9">
									<td align="center">
										<html:select name="rs" property="day9" styleId="sj14"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day9">
									<td align="center">
										<html:select name="rs" property="day9" styleId="sj14"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day9O" name="day9O" value="${rs.day9}" />
							</tr>
							<tr height="25">
								<td align="center">
									10
								</td>
								<td align="center">
									11
								</td>
								<td align="center">
									12
								</td>
								<td align="center">
									13
								</td>
								<td align="center">
									14
								</td>
								<td align="center">
									15
								</td>
								<td align="center">
									16
								</td>
							</tr>
							<tr height="45">
								<logic:present name="day10">
									<td align="center">
										<html:select name="rs" property="day10" styleId="sj15"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day10">
									<td align="center">
										<html:select name="rs" property="day10" styleId="sj15"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day10O" name="day10O"
									value="${rs.day10}" />
								<logic:present name="day11">
									<td align="center">
										<html:select name="rs" property="day11" styleId="sj16"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day11">
									<td align="center">
										<html:select name="rs" property="day11" styleId="sj16"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day11O" name="day11O"
									value="${rs.day11}" />
								<logic:present name="day12">
									<td align="center">
										<html:select name="rs" property="day12" styleId="sj17"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day12">
									<td align="center">
										<html:select name="rs" property="day12" styleId="sj17"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day12O" name="day12O"
									value="${rs.day12}" />
								<logic:present name="day13">
									<td align="center">
										<html:select name="rs" property="day13" styleId="sj18"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day13">
									<td align="center">
										<html:select name="rs" property="day13" styleId="sj18"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day13O" name="day13O"
									value="${rs.day13}" />
								<logic:present name="day14">
									<td align="center">
										<html:select name="rs" property="day14" styleId="sj19"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day14">
									<td align="center">
										<html:select name="rs" property="day14" styleId="sj19"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day14O" name="day14O"
									value="${rs.day14}" />
								<logic:present name="day15">
									<td align="center">
										<html:select name="rs" property="day15" styleId="sj20"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day15">
									<td align="center">
										<html:select name="rs" property="day15" styleId="sj20"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day15O" name="day15O"
									value="${rs.day15}" />
								<logic:present name="day16">
									<td align="center">
										<html:select name="rs" property="day16" styleId="sj21"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day16">
									<td align="center">
										<html:select name="rs" property="day16" styleId="sj21"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day16O" name="day16O"
									value="${rs.day16}" />
							</tr>
							<tr height="25">
								<td align="center">
									17
								</td>
								<td align="center">
									18
								</td>
								<td align="center">
									19
								</td>
								<td align="center">
									20
								</td>
								<td align="center">
									21
								</td>
								<td align="center">
									22
								</td>
								<td align="center">
									23
								</td>
							</tr>
							<tr height="45">
								<logic:present name="day17">
									<td align="center">
										<html:select name="rs" property="day17" styleId="sj22"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day17">
									<td align="center">
										<html:select name="rs" property="day17" styleId="sj22"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day17O" name="day17O"
									value="${rs.day17}" />
								<logic:present name="day18">
									<td align="center">
										<html:select name="rs" property="day18" styleId="sj23"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day18">
									<td align="center">
										<html:select name="rs" property="day18" styleId="sj23"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day18O" name="day18O"
									value="${rs.day18}" />
								<logic:present name="day19">
									<td align="center">
										<html:select name="rs" property="day19" styleId="sj24"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day19">
									<td align="center">
										<html:select name="rs" property="day19" styleId="sj24"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day19O" name="day19O"
									value="${rs.day19}" />
								<logic:present name="day20">
									<td align="center">
										<html:select name="rs" property="day20" styleId="sj25"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day20">
									<td align="center">
										<html:select name="rs" property="day20" styleId="sj25"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day20O" name="day20O"
									value="${rs.day20}" />
								<logic:present name="day21">
									<td align="center">
										<html:select name="rs" property="day21" styleId="sj26"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>

								<logic:notPresent name="day21">
									<td align="center">
										<html:select name="rs" property="day21" styleId="sj26"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day21O" name="day21O"
									value="${rs.day21}" />
								<logic:present name="day22">
									<td align="center">
										<html:select name="rs" property="day22" styleId="sj27"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day22">
									<td align="center">
										<html:select name="rs" property="day22" styleId="sj27"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day22O" name="day22O"
									value="${rs.day22}" />
								<logic:present name="day23">
									<td align="center">
										<html:select name="rs" property="day23" styleId="sj28"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day23">
									<td align="center">
										<html:select name="rs" property="day23" styleId="sj28"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day23O" name="day23O"
									value="${rs.day23}" />
							</tr>
							<tr height="25">
								<td align="center">
									24
								</td>
								<td align="center">
									25
								</td>
								<td align="center">
									26
								</td>
								<td align="center" rowspan="2" colspan="12">
									���ϸ�ʱ���ۼ�
									<span id="ljxs">0</span>Сʱ
									<br />
									��Ǯ
									<span id="zq">0</span>Ԫ
								</td>
							</tr>

							<tr height="45">
								<logic:present name="day24">
									<td align="center">
										<html:select name="rs" property="day24" styleId="sj29"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day24">
									<td align="center">
										<html:select name="rs" property="day24" styleId="sj29"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day24O" name="day24O"
									value="${rs.day24}" />
								<logic:present name="day25">
									<td align="center">
										<html:select name="rs" property="day25" styleId="sj30"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day25">
									<td align="center">
										<html:select name="rs" property="day25" styleId="sj30"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day25O" name="day25O"
									value="${rs.day25}" />
								<logic:present name="day26">
									<td align="center">
										<html:select name="rs" property="day26" styleId="sj31"
											onchange="sumTime()">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:present>
								<logic:notPresent name="day26">
									<td align="center">
										<html:select name="rs" property="day26" styleId="sj31"
											disabled="true">
											<html:option value="0"> 0 </html:option>
											<html:options collection="sjList" labelName="sj"
												property="sj" />
										</html:select>
									</td>
								</logic:notPresent>
								<input type="hidden" id="day26O" name="day26O"
									value="${rs.day26}" />
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="if(sumpd()) {refreshForm('/xgxt/stu_work_info.do?act=save&doType=' + document.getElementById('doType').value);Close();window.dialogArguments.document.getElementById('search_go').click();}"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="if(sumpd()) {refreshForm('/xgxt/stu_work_info.do?act=tj&doType=' + document.getElementById('doType').value);Close();window.dialogArguments.document.getElementById('search_go').click();}"
					style="width:80px" id="buttonSave">
					�ύ
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="expTab('tb','','')"
					style="width:80px" id="buttonPrint">
					��ӡ�б�
				</button>
				<%--�ȷ�ʦ��<bean:message key="lable.xsgzyxpzxy" />--%>
				<logic:equal value="10100" name="xxdm">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
						onclick="window.open('/xgxt/qgzxLogic.do?method=printReport&xh='+document.getElementById('xh').value)"
						style="width:80px" id="buttonPrint">
						�¿��˱�
					</button>
				</logic:equal>
				<%--end�ȷ�ʦ��<bean:message key="lable.xsgzyxpzxy" />--%>
				<%--�㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
				<logic:equal value="12866" name="xxdm">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
						onclick="printKhb('qgzxZjjjzy.do?method=printReport')"
						style="width:80px" id="buttonPrint">
						�¿��˱�
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:equal value="13022" name="xxdm">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="printYkqb()" style="width:80px"
						id="buttonPrint">
						�¿��ڱ�
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<%--end�㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
