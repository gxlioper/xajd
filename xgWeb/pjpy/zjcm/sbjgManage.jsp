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
	<script language="javascript">
	function searchJg(){
		var url = "/xgxt/zjcmPjpy.do?method=";
		var lx = $("lx").value;
		if("jxj" == lx){
			url += "jxjjgManage";
		}else{
			url += "rychjgManage";
		}
		
		url += "&lx="+lx;
		allNotEmpThenGo(url);
	}
	
	function delJg(){
		var url = "/xgxt/zjcmPjpy.do?method=";
		var lx = $("lx").value;
		if("jxj" == lx){
			url += "jxjjgManage";
		}else{
			url += "rychjgManage";
		}
		
		url += "&lx="+lx;
		sumitInfo(url,'del');
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjcmPjpy" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="message" name="message" value="${message}"/>
			<input type="hidden" id="fdyQx" name="fdyQx" value="<bean:write name="fdyQx" scope="session"/>"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" name="lx" id="lx" value="${lx }" />
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
											<html:select property="nj" style="" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj" labelProperty="nj" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;ѧ�꣺
										<html:select property="xn" style="" onchange="" disabled="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;ѧ�ڣ� 
										<html:select property="xq" style="" onchange="" disabled="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="searchJg()">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<logic:notEqual name="userType" value="stu">
											<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;רҵ��
										<logic:notEqual name="userType" value="stu">
											<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="zydm" style="" styleId="zy" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;�༶��
										<logic:notEqual name="userType" value="stu">
											<html:select property="bjdm" style="" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
										<logic:equal name="userType" value="stu">
											<html:select property="bjdm" style="" styleId="bj" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										ѧ�ţ�
										<html:text property="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;������
										<html:text property="xm" style="" maxlength="20"/>
										<logic:equal name="lx" value="rych">
											&nbsp;&nbsp;<font color="red">*</font>�����ƺţ�
											<html:select property="rychdm" style="" styleId="rychdm">
												<html:options collection="rychList" property="dm" labelProperty="mc" />
											</html:select>
										</logic:equal>
										<logic:equal name="lx" value="jxj">
											&nbsp;&nbsp;<font color="red">*</font>��ѧ��
											<html:select property="jxjdm" style="" styleId="jxjdm">
												<html:options collection="jxjList" property="dm" labelProperty="mc" />
											</html:select>
										</logic:equal>
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
								<font color="blue">��ʾ��˫���ɲ鿴��ϸ��Ϣ���ϼ������Ѿ���ˣ������Ͳ�������ִ��ɾ������.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:notEqual name="userType" value="stu">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
										</logic:notEqual>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="showInfo('/xgxt/zjcmPjpy.do?method=sbjgUpdate',$('lx').value,'800','600')">			
											<!-- ��ѧ�� -->					
											<logic:equal name="lx"  value="jxj">
												<!-- ����Ա -->
												<logic:equal name="fdyQx" value="true">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<logic:iterate id="x" name="s" offset="11" length="1">
															<td align="center">
																<!-- <bean:message key="lable.xsgzyxpzxy" />ѧУ��δ��ˣ����Խ���ɾ������ -->
																<logic:equal name="v" value="δ���">
																	<logic:equal name="x" value="δ���">
																		<input type="checkbox" id="checkVal" name="checkVal" 
																			value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																	</logic:equal>
																</logic:equal>
																<!-- <bean:message key="lable.xsgzyxpzxy" />�Ѿ���ˣ������Խ���ɾ������ -->
																<logic:notEqual name="v" value="δ���">
																	<logic:equal name="x" value="δ���">
																		<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																		<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"/>
																	</logic:equal>
																</logic:notEqual>
																<!-- ѧУ�Ѿ���ˣ������Խ���ɾ������ -->
																<logic:notEqual name="x" value="δ���">
																	<logic:equal name="v" value="δ���">
																		<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																		<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"/>
																	</logic:equal>
																</logic:notEqual>
																<!-- ѧУ<bean:message key="lable.xsgzyxpzxy" />���Ѿ���ˣ������Խ���ɾ������ -->
																<logic:notEqual name="x" value="δ���">
																	<logic:notEqual name="v" value="δ���">
																		<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																		<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"/>
																	</logic:notEqual>
																</logic:notEqual>
															</td>
														</logic:iterate>
													</logic:iterate>
												</logic:equal>
												<logic:equal name="fdyQx" value="false">
													<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
													<logic:equal name="userType" value="xy">
														<logic:iterate id="v" name="s" offset="11" length="1">
															<td align="center">
																<!-- ѧУδ��ˣ����Խ���ɾ������ -->
																<logic:equal name="v" value="δ���">
																	<input type="checkbox" id="checkVal" name="checkVal" 
																		value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																</logic:equal>
																<!-- ѧУ����ˣ������Խ���ɾ������ -->
																<logic:notEqual name="v" value="δ���">
																	<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"
																		value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
																</logic:notEqual>
															</td>
														</logic:iterate>	
													</logic:equal>
													<!-- ѧУ -->
													<logic:notEqual name="userType" value="xy">
														<logic:notEqual name="userType" value="stu">
															<td>
																<input type="checkbox" id="checkVal" name="checkVal" 
																	value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
															</td>
														</logic:notEqual>
													</logic:notEqual>
												</logic:equal>
											</logic:equal>
											
											<!-- �����ƺ� -->
											<logic:equal name="lx"  value="rych">
												<logic:equal name="userType" value="xy">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<td align="center">
															<!-- ѧУδ��ˣ����Խ���ɾ������ -->
															<logic:equal name="v" value="δ���">
																<input type="checkbox" id="checkVal" name="checkVal" 
																	value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
															</logic:equal>
															<!-- ѧУ����ˣ������Խ���ɾ������ -->
															<logic:notEqual name="v" value="δ���">
																<input type="checkbox" id="checkVal" name="checkVal" disabled="disabled"
																value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
															</logic:notEqual>
														</td>
													</logic:iterate>
												</logic:equal>
												<logic:notEqual name="userType" value="xy">
													<logic:notEqual name="userType" value="stu">
														<td align="center">
															<input type="checkbox" id="checkVal" name="checkVal" 
															value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
														</td>
													</logic:notEqual>
												</logic:notEqual>
											</logic:equal>
										
										<logic:equal name="userType" value="stu">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="left">
													<input type="hidden" name="pjxh" value="<logic:iterate id="sh" name="s" offset="0" length="1"><bean:write name="sh"/></logic:iterate>"/>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</logic:equal>
										<logic:notEqual name="userType" value="stu">
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
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
							<logic:notEqual name="userType" value="stu">
							<button class="button2"
								onclick="delJg()"
								style="width:80px">
								ɾ&nbsp;&nbsp;��
							</button>
							</logic:notEqual>
						</logic:equal>
							&nbsp;&nbsp;
							<button class="button2" onclick="impAndChkData()"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()"
								style="width:80px">
								��������
							</button>
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
