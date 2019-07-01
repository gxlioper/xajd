<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
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
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>	
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="/xgxt/js/xgutil.js"></script>
	<script>
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
	</script>
	<body onload="xyDisabled('xy')">
		<html:form action="/n05_xszz.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ѧ�ѻ���
				</div>
			</div>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">								
								�꼶��
								<html:select property="queryequals_nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="querylike_xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
								&nbsp;&nbsp;������
								<html:text property="querylike_xm" styleId="xm" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>								
								&nbsp;&nbsp;���֤�ţ�
								<html:text property="querylike_sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=xfhj3Sh&go=go')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">							
								<html:hidden property="queryequals_xydm" value="${userDep}"/>
							</logic:equal>
							</logic:notEqual>
							<html:select property="queryequals_xydm" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="queryequals_zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
						<td colspan="3">
							<logic:equal value="3" name="shjb">
							<logic:equal value="true" name="fdyQx">
								����Ա��ˣ�
								<html:select property="queryequals_fdysh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</logic:equal>
							&nbsp;&nbsp;
							</logic:equal>	
							<logic:equal value="xy" name="userType">						
							<bean:message key="lable.xsgzyxpzxy" />��ˣ�
							<html:select property="queryequals_xysh">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
							&nbsp;&nbsp;
							</logic:equal>
							ѧУ��ˣ�
							<html:select property="queryequals_xxsh">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
							&nbsp;&nbsp;����ʱ�䣺
							<html:text property="querygreaterequal_sqsj" 
							           styleId="sqsjks" 
							           style="width:80px;inputtext"
								       styleClass="inputtext"
								       onblur="dateFormatChg(this)" 
									   onclick="return showCalendar('sqsjks','y-mm-dd');"></html:text>
							-
							<html:text property="querylessequal_sqsj" 
							           styleId="sqsjjs" 
							           style="width:80px;inputtext"
								       styleClass="inputtext"
								       onblur="dateFormatChg(this)" 
									   onclick="return showCalendar('sqsjjs','y-mm-dd');"></html:text>
							&nbsp;&nbsp;��ĸ�Ƿ�֪��ѧ�ѻ�����
							<html:select property="queryequals_fmsfzxxfhj">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
					<logic:empty name="topTr">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					</logic:empty>
					<logic:notEmpty name="rs">
					<logic:notEmpty name="topTr">
						<fieldset>
							<legend>
								��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" name="all" value="all" onclick="chec()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="3">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="modi('/xgxt/n05_xszz.do?method=xfhj3ShOne&type=view')"
										style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     ">
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="checkbox" name="primarykey_cbv" id="pkV" value="<bean:write name="v"/>" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> >
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="3" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="4">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xszzCommonN05ActionForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
	                		<button class="button2" onclick="dataBatch('n05_xszz.do?method=xfhj3Sh&shjg=ͨ��&type=save')"
								style="width:80px">
								ͨ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataBatch('n05_xszz.do?method=xfhj3Sh&shjg=��ͨ��&type=save')"
								style="width:80px">
								��ͨ��
							</button>							
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
					</div>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
			<input type="hidden" id="message" value="${message}"/>
		 	<script>
		 		alert(val('message'));
	 			document.getElementById('search_go').click();
	 		</script>
		 </logic:notEmpty>
	</body>
</html>
