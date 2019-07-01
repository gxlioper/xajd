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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">	

	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/gzdxJxgl" method="post">
			<%@ include file="/jxgl/hiddenValue.jsp"%>
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
										ѧ�꣺
										<!-- ��� -->
										<logic:equal name="mklx" value="sh">
											<html:hidden property="queryequals_xn"/>
											<html:select property="queryequals_xn" style="" onchange="" disabled="true">
												<html:options collection="xnList" property="xn" labelProperty="xn" />
											</html:select>
										</logic:equal>
										<!-- ��� -->
										<logic:equal name="mklx" value="jg">
											<html:select property="queryequals_xn" style="" onchange="">
												<html:options collection="xnList" property="xn" labelProperty="xn" />
											</html:select>
										</logic:equal>
										&nbsp;&nbsp;�꼶��
										<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;�������ͣ�
										<html:select property="queryequals_sqlx" style=""
											onchange="">
											<html:options collection="mhlxList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="" />
										<!-- ��� -->
										<logic:equal name="mklx" value="sh">
											<button type="button" class="button2" style="height:40px" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/gzdxJxgl.do?method=mhxsh');">
												��ѯ
											</button>
										</logic:equal>
										<!-- ��� -->
										<logic:equal name="mklx" value="jg">
											<button type="button" class="button2" style="height:40px" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/gzdxJxgl.do?method=mhxjg');">
												��ѯ
											</button>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="queryequals_xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;רҵ��
										<html:select property="queryequals_zydm" style="" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="queryequals_bjdm" style="" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										ѧ�ţ�
										<!-- ѧ�� -->
										<logic:equal name="userType" value="stu">
											<html:text property="querylike_xh" styleId="xh" style="" maxlength="20" readonly="true"/>
										</logic:equal>
										<!-- ��ѧ�� -->
										<logic:notEqual name="userType" value="stu">
											<html:text property="querylike_xh" styleId="xh" style="" maxlength="20"/>
										</logic:notEqual>
										&nbsp;&nbsp;������
										<html:text property="querylike_xm" styleId="xm" style="" maxlength="20"/>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��ˣ�
										<!-- ��� -->
										<logic:equal name="mklx" value="jg">
											<html:select property="queryequals_xysh" style="" styleId="xysh" onchange="">
												<html:option value=""></html:option>
												<html:options collection="shList" property="en" labelProperty="cn" />
											</html:select>
										</logic:equal>
										<!-- ���end -->
										<!-- ��� -->
										<logic:equal name="mklx" value="sh">
											<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
											<logic:equal name="userType" value="xy">
												<html:select property="queryequals_xysh" style="" styleId="xysh">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en" labelProperty="cn" />
												</html:select>
											</logic:equal>
											<!-- <bean:message key="lable.xsgzyxpzxy" />end -->
											<!-- ��<bean:message key="lable.xsgzyxpzxy" /> -->
											<logic:notEqual name="userType" value="xy">
												<html:hidden property="queryequals_xysh"/>
												<html:select property="queryequals_xysh" style="" styleId="xysh" disabled="true">
													<html:option value=""></html:option>
													<html:options collection="shList" property="en" labelProperty="cn" />
												</html:select>
											</logic:notEqual>
											<!-- <bean:message key="lable.xsgzyxpzxy" />end -->
										</logic:equal>
										<!-- ���end -->
										&nbsp;&nbsp;ѧУ��ˣ�
										<html:select property="queryequals_xxsh" style="" styleId="xxsh" onchange="">
											<html:option value=""></html:option>
											<html:options collection="shList" property="en" labelProperty="cn" />
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
								<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
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
										ondblclick="showOpenInfo('/xgxt/gzdxJxgl.do?method=mhxUpdate','view',$('mklx').value,'800','600')">
										<td align="center">
											<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
										<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="center">
											<bean:write name="v" />	
										</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
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
											page="/sjcz/turnpage.jsp?form=jxglTyForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<!-- ��� -->
							<logic:equal name="mklx" value="sh">
								<button type="button" class="button2" 
										onclick="showOpenInfo('/xgxt/gzdxJxgl.do?method=mhxUpdate','update',$('mklx').value,'800','600')" 
										style="width:80px">
									���
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="sumitInfo('/xgxt/gzdxJxgl.do?method=mhxsh&shjg=ͨ��','sh')" style="width:80px">
									����ͨ��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="sumitInfo('/xgxt/gzdxJxgl.do?method=mhxsh&shjg=��ͨ��','sh')" style="width:80px">
									������ͨ��
								</button>
								&nbsp;&nbsp;
							</logic:equal>
							<!-- ��� -->
							<logic:equal name="mklx" value="jg">
								<button type="button" class="button2" onclick="showOpenInfo('/xgxt/gzdxJxgl.do?method=mhxUpdate','add',$('mklx').value,'800','600')" style="width:80px">
									��	��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="showOpenInfo('/xgxt/gzdxJxgl.do?method=mhxUpdate','update',$('mklx').value,'800','600')" style="width:80px">
									��	��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="sumitInfo('/xgxt/gzdxJxgl.do?method=mhxjg','del')" style="width:80px">
									ɾ	��
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="impAndChkData()"
									style="width:80px">
									��������
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" onclick="wjcfDataExport('gzdxJxgl.do?method=mhxjg&doType=exp')"
									style="width:80px">
									��������
								</button>
							</logic:equal>
						</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
					}
				</script>
				</logic:equal>
			</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
