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
		<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript">
	function setZhf(num,value,id){
		
		dwr.engine.setAsync(false);

		var dyfbl = "";if($("dyfbl")){dyfbl=$("dyfbl").value};
		var tyfbl = "";if($("tyfbl")){tyfbl=$("tyfbl").value};
		var nlfbl = "";if($("nlfbl")){nlfbl=$("nlfbl").value};
		var zhf = "";
		
		if(value != ""){
			if("dyf" == id && dyfbl !=""){
				zhf = parseFloat(value)*parseFloat(dyfbl)/100;
			}else if ("tyf" == id && tyfbl !=""){
				zhf = parseFloat(value)*parseFloat(tyfbl)/100;
			}else if ("nlf" == id && nlfbl !=""){
				zhf = parseFloat(value)*parseFloat(nlfbl)/100;
			}
			
			getPjpyInfo.sswrValue(zhf,function(data){
				if(data!=null){
					zhf = data;
				}
			});
			
			$(id+num).value = zhf;
		}
		
		dwr.engine.setAsync(true);
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjcmPjpy" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="csh" name="csh" value="${csh }" />
			<input type="hidden" id="message" name="message" value="${message}"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" name="dyfbl" id="dyfbl" value="${dyfbl }" />
			<input type="hidden" name="zyfbl" id="zyfbl" value="${zyfbl }" />
			<input type="hidden" name="tyfbl" id="tyfbl" value="${tyfbl }" />
			<input type="hidden" name="nlfbl" id="nlfbl" value="${nlfbl }" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										�꼶��
										<logic:notEqual name="userType" value="stu">
										<html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:hidden property="nj"/>
											<html:select property="nj" style="" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;ѧ�꣺
										<html:hidden property="xn"/>
										<html:select property="xn" style="" onchange="" disabled="true">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;ѧ�ڣ� 
										<html:hidden property="xq"/>
										<html:select property="xq" style="" onchange="" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="showTips('�����ѯ�У���ȴ�......');allNotEmpThenGo('/xgxt/zjcmPjpy.do?method=zhfManage');">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<logic:equal name="userType" value="xy">
											<html:hidden property="xydm"/>
											<html:select property="xydm" style="" styleId="xy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:equal name="userType" value="stu">
											<html:hidden property="xydm"/>
											<html:select property="xydm" style="" styleId="xy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										</logic:notEqual>
										&nbsp;&nbsp;רҵ��
										<logic:equal name="userType" value="stu">
											<html:hidden property="zydm"/>
											<html:select property="zydm" style="" styleId="zy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:notEqual>
										&nbsp;&nbsp;�༶��
										<logic:equal name="userType" value="stu">
											<html:hidden property="bjdm"/>
											<html:select property="bjdm" style="" styleId="bj"  disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<html:select property="bjdm" style="" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										ѧ�ţ�
										<html:text property="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;������
										<html:text property="xm" style="" maxlength="20"/>
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
								<font color="red">${ts }</font>
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
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="left">
												<bean:write name="v" />
												<input type="hidden" name="pjxh" value="<bean:write name="v" />"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="6">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
											<td align="center">
												<input type="text" name="dyf" id="dyf" 
													value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v" /></logic:iterate>" 
													onblur="chMax(this,100);setZhf(${index },this.value,this.id);"
													onkeypress="return sztzNumInputValue(this,5,event)" 
													style="width:40%;ime-mode:disabled"/>/
												<input type="text" id="dyf${index }" 
													value="<logic:iterate id="v" name="s" offset="8" length="1"><bean:write name="v" /></logic:iterate>"
													style="width:40%;" readonly="readonly"/>
											</td>	
											<td align="center">
												<input type="text" name="zyf" id="zyf" readonly="readonly"
													value="<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v" /></logic:iterate>" 
													onblur="chMax(this,100);"
													onkeypress="return sztzNumInputValue(this,5,event)" 
													style="width:40%;ime-mode:disabled"/>/
												<input type="text" id="zyf${index }" 
													value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v" /></logic:iterate>"
													style="width:40%;" readonly="readonly"/>
											</td>												
											<td align="center">
												<input type="text" name="tyf" id="tyf" 
													value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" 
													onblur="chMax(this,100);setZhf(${index },this.value,this.id);"
													onkeypress="return sztzNumInputValue(this,5,event)" 
													style="width:40%;ime-mode:disabled"/>/
												<input type="text" id="tyf${index }" 
													value="<logic:iterate id="v" name="s" offset="12" length="1"><bean:write name="v" /></logic:iterate>"
													style="width:40%;" readonly="readonly"/>
											</td>						
											<td align="center">
												<input type="text" name="nlf" id="nlf" 
													value="<logic:iterate id="v" name="s" offset="13" length="1"><bean:write name="v" /></logic:iterate>" 
													onblur="chMax(this,100);setZhf(${index },this.value,this.id);"
													onkeypress="return sztzNumInputValue(this,5,event)" 
													style="width:40%;ime-mode:disabled"/>/
												<input type="text" id="nlf${index }" 
													value="<logic:iterate id="v" name="s" offset="14" length="1"><bean:write name="v" /></logic:iterate>"
													style="width:40%;" readonly="readonly"/>
											</td>		
										<logic:iterate id="v" name="s" offset="15" length="1">
											<td>
												<input type="text" name="zhf" value="<bean:write name="v" />"
													 onkeypress="return sztzNumInputValue(this,5,event)"  readonly="readonly"
													 style="width:100%;ime-mode:disabled"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="16" length="1">
											<td>
												<input type="text" name="zhf" value="<bean:write name="v" />"
													 onkeypress="return sztzNumInputValue(this,5,event)"  readonly="readonly"
													 style="width:100%;ime-mode:disabled"/>
											</td>
										</logic:iterate>
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
											page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<button class="button2"
								onclick="saveUpdate('/xgxt/zjcmPjpy.do?method=zhfManage&doType=save','')"
								style="width:80px">
								�� ��
							</button>
							<logic:notEqual name="userType" value="stu">
								<logic:notPresent name="nobl">
									&nbsp;
									<button class="button2" 
										onclick="if (confirm('ȷ��Ҫ���㱾������ѧ��ѧ�ڵ��ۺϷ�?\nע������ǰ���ȱ�������ֵ��')){saveUpdate('/xgxt/zjcmPjpy.do?method=zhfManage&doType=js','')}"
										style="width:80px">
										�����ۺϷ�
									</button>
								</logic:notPresent>
								<logic:notEqual name="userType" value="xy">
									&nbsp;
									<button class="button2"
										onclick="showTopWin('/xgxt/zjcmPjpy.do?method=zhfUpdate','400','350')"
										style="width:80px">
										��������
									</button>
								</logic:notEqual>
								&nbsp;
								<button class="button2" onclick="impAndChkData()"
									style="width:80px">
									��������
								</button>
							</logic:notEqual>
						</logic:equal>
					</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert(''+document.getElementById('message').value);
			</script>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
