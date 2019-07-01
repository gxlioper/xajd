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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript">	
	function saveWsf(){
		var num = $("num").value;
		var cpf = "";

		if(num!=0){
			if($("userType").value == "teacher"){
				for(var i=0;i<num;i++){
					if($("qsf"+i)){
						if($("qsf"+i).value != ""){
							if($("xyfjf"+i).value != ""){
								cpf += $("xh"+i).value + "!@!"+$("xn"+i).value+"!@!"+$("qsf"+i).value+"!@!"+$("isZds"+i).value+"!@!"+$("xyfjf"+i).value+"!!@@!!";
							}else{
								cpf += $("xh"+i).value + "!@!"+$("xn"+i).value+"!@!"+$("qsf"+i).value+"!@!"+$("isZds"+i).value+"!@!"+"noPoint"+"!!@@!!";
							}
						}else{
							alert("��"+(i+1)+"�У����ҷ�Ϊ�գ���ȷ�ϣ�����");
							return false��
						}
					}
				}
			}else if($("userType").value == "xy"){
				for(var i=0;i<num;i++){
					if($("xyfjf"+i)){
						if($("xyfjf"+i).value != ""){
							cpf += $("xh"+i).value + "!@!"+$("xn"+i).value+"!@!"+$("xyfjf"+i).value+"!@!"+ $("xyshV"+i).value+"!!@@!!";
						}else{
							cpf += $("xh"+i).value + "!@!"+$("xn"+i).value+"!@!"+"noPoint"+"!@!"+$("xyshV"+i).value+"!!@@!!";
						}
					}
				}
			}else{
				for(var i=0;i<num;i++){
					if($("qsf"+i)){
						if($("qsf"+i).value != ""){
							if($("xyfjf"+i).value != ""){
								cpf += $("xh"+i).value + "!@!"+$("xn"+i).value+"!@!"+$("qsf"+i).value+"!@!"+$("isZds"+i).value+"!@!"+$("xyfjf"+i).value+"!@!"+ $("xyshV"+i).value+"!!@@!!";
							}else{
								cpf += $("xh"+i).value + "!@!"+$("xn"+i).value+"!@!"+$("qsf"+i).value+"!@!"+$("isZds"+i).value+"!@!"+"noPoint"+"!@!"+ $("xyshV"+i).value+"!!@@!!";
							}
						}else{
							alert("��"+(i+1)+"�У����ҷ�Ϊ�գ���ȷ�ϣ�����");
							return false��
						}
					}
				}
			}
			$("cpf").value = cpf;
	
			refreshForm('/xgxt/zjlgPjpy.do?method=dycpfWsf&doType=save');
		}else{
			alert("����д�������ٱ���");
			return false;
		}
	}
	function chbox(num,flag){
		$("xyshV"+num).value = flag;
	}
	
	function setbox(id){
		var zdsId = id.replace("isZdsV","");
		if($(id).checked){
			$("isZds"+zdsId).value = "yes";
		}else{
			$("isZds"+zdsId).value = "no";
		}
	}
	</script>
	<body onload="bjLimit('bj');onShow()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zjlgPjpy" method="post">
			<input type="hidden" name="xyV" id="xyV" value="<bean:write name="xydm" scope="request"/>"/>
			<input type="hidden" name="zyV" id="zyV" value="<bean:write name="zyV" />"/>
			<input type="hidden" name="bjV" id="bjV" value="<bean:write name="bjV" />"/>
			<input type="hidden" name="njV" id="njV" value="<bean:write name="njV" />"/>
			<input type="hidden" name="xnV" id="xnV" value="<bean:write name="xnV" />"/>
			<input type="hidden" name="xhV" id="xhV" value="<bean:write name="xhV" />"/>
			<input type="hidden" name="xmV" id="xmV" value="<bean:write name="xmV" />"/>
			<input type="hidden" name="cpf" id="cpf" />
			<input type="hidden" name="num" id="num" value="<bean:write name="rsNum"/>"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name="title" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="2">
									<input type="radio" name="cjlx" value="0" id="cjlx" 
										onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfPsf');" >
										ƽʱ��
									&nbsp; 
									<input type="radio" name="cjlx" value="1" id="cjlx" 
										onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfWsf');" checked>
									&nbsp; 
										������
									<input type="radio" name="cjlx" value="2" id="cjlx" 
										onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfKqf');" >
										���ڷ�
									&nbsp; 		
									<input type="radio" name="cjlx" value="3" id="cjlx" 
										onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfZf');" >
										���������ܷ�		
								</td>
							</tr>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" style="width:80px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									ѧ�꣺
									<html:select property="xn" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:85px" maxlength="20"></html:text>
									&nbsp;&nbsp;&nbsp;������
									<html:text property="xm" style="width:85px" maxlength="20"></html:text>								
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/zjlgPjpy.do?method=dycpfWsf')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td align="center">
										<bean:write name="s" property="xh" />
										<input type="hidden" name="xh" id="xh${index}"
											value="<bean:write name="s" property="xh"/>" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<html:text name="s" property="xn" styleId="xn${index}" style="width:85px" readonly="true"></html:text>
									</td>
									<td align="center">
										<logic:notEqual name="userType" value="xy">
										<html:text name="s" property="qsf" styleId="qsf${index}" style="width:85px" 
											onkeypress="return numInputValue(this,4,event)" maxlength="4" onblur="chFs(this);"/>
										</logic:notEqual>
										<logic:equal name="userType" value="xy">
										<html:text name="s" property="qsf" styleId="qsf${index}" style="width:85px" 
											onkeypress="return numInputValue(this,4,event)" maxlength="4" readonly="true"/>
										</logic:equal>
									</td>								
									<td align="center">
										<html:text name="s" property="xyfjf" styleId="xyfjf${index}" style="width:85px" 
											onkeypress="return numInputValue(this,4,event)" maxlength="4" onblur="chFs(this);"/>
									</td>
									<logic:equal name="userType" value="teacher">
									<td align="center">
										<logic:equal name="s" property="isZds" value="yes">
										<input type="hidden" name="isZds${index}"id="isZds${index}" value="yes"/>
										<input type="checkbox" name="isZdsV${index}"id="isZdsV${index}" onclick="setbox(this.id)"  checked/>
										</logic:equal>
										<logic:notEqual name="s" property="isZds" value="yes">
										<input type="hidden" name="isZds${index}"id="isZds${index}" value="no"/>
										<input type="checkbox" name="isZdsV${index}"id="isZdsV${index}" onclick="setbox(this.id)"/>
										</logic:notEqual>
									</td>
									</logic:equal>
									<logic:notEqual name="userType" value="teacher">
									<logic:notEqual name="userType" value="xy">
									<td align="center">
										<logic:equal name="s" property="isZds" value="yes">
										<input type="hidden" name="isZds${index}"id="isZds${index}" value="yes"/>
										<input type="checkbox" name="isZdsV${index}"id="isZdsV${index}" onclick="setbox(this.id)"  checked/>
										</logic:equal>
										<logic:notEqual name="s" property="isZds" value="yes">
										<input type="hidden" name="isZds${index}"id="isZds${index}" value="no"/>
										<input type="checkbox" name="isZdsV${index}"id="isZdsV${index}" onclick="setbox(this.id)"/>
										</logic:notEqual>
									</td>
									</logic:notEqual>
									<logic:equal name="userType" value="xy">
									<td align="center">
										<logic:equal name="s" property="isZds" value="yes">
										<input type="hidden" name="isZds${index}"id="isZds${index}" value="yes"/>
										<input type="checkbox" name="isZdsV${index}"id="isZdsV${index}"checked onclick="setbox(this.id)" disabled=true;/>
										</logic:equal>
										<logic:notEqual name="s" property="isZds" value="yes">
										<input type="hidden" name="isZds${index}"id="isZds${index}" value="no"/>
										<input type="checkbox" name="isZdsV${index}"id="isZdsV${index}" onclick="setbox(this.id)" disabled=true;/>
										</logic:notEqual>
									</td>
									</logic:equal>
									<logic:equal name="s" property="xysh" value="��ͨ��">
										<td>
											<input type="hidden" name="xyshV${index}"id="xyshV${index}" value="1"/>
											<input type="radio" name="xysh${index}" value="1" id="open" checked
											onclick="chbox(${index},this.value);" 
												>&nbsp;ͨ��
											<input type="radio" name="xysh${index}" value="0" id="open" 
											onclick="chbox(${index},this.value);"
												>&nbsp;��ͨ��
										</td>
									</logic:equal>
									<logic:notEqual name="s" property="xysh" value="��ͨ��">
										<td>
											<input type="hidden" name="xyshV${index}"id="xyshV${index}" value="0"/>
											<input type="radio" name="xysh${index}" value="1" id="open" 
											onclick="chbox(${index},this.value);" 
												>&nbsp;ͨ��
											<input type="radio" name="xysh${index}" value="0" id="open" checked
											onclick="chbox(${index},this.value);"
												>&nbsp;��ͨ��
										</td>
									</logic:notEqual>
									</logic:notEqual>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal name="writeAble" value="yes">
							<logic:notEqual name="userType" value="xy">
								<button type="button" class="button2"
									onclick="saveWsf()"
									style="width:80px">
									�� ��
								</button>
							</logic:notEqual>
							<logic:equal name="userType" value="xy">
								<button type="button" class="button2"
									onclick="saveWsf()"
									style="width:80px">
									�� ��
								</button>
							</logic:equal>
							&nbsp;
							<button type="button" class="button2" onclick="impAndChkData()"
								style="width:80px">
								��������
							</button>
							&nbsp;
							<button type="button" class="button2" onclick="dataExport()"
								style="width:80px">
								��������
							</button>
							</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				//alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
