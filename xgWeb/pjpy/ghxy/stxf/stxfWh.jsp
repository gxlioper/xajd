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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">
		
		function save(url){
		if($("rsNum")){
			if($("rsNum").value == "0" || $("rsNum").value==""){
				alert("��ȷ�������ã�ȡ������ѧ����");
				return false;
			}else{
				if (confirm("ȷ�����иò�����?")) {
					saveUpdate(url,'');
				}
			}
		}
		}
	</script>
	<body>
		<html:form action="ghxyStxf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="view_sjxy_dekthdqh" />
			<input type="hidden" name="rsNum" id="rsNum" value="${rsNum}" styleId="rsNum"/>
			<input type="hidden" name="lx" id="lx" value="${lx}"styleId="lx"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ��:${title}
				</div>
			</div>	
			<div class="xxk">
			    			
			    		<logic:equal name="lx" value="wh">
							<ul>
								<li id="001l"
									class="xxk_on_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyStxf.do?method=stxfWh&lx=wh')" class="xxk_on_m">
									&nbsp;
									����ѧ��ά��
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_on_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_off_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyStxf.do?method=stxfWh&lx=cx')" class="xxk_off_m">
									&nbsp;
									����ѧ�ֲ�ѯ
								</li> 
								<li id="002r"
									class="xxk_off_r"></li>
							</ul>
							</logic:equal>
							<logic:equal name="lx" value="cx">
							<ul>
								<li id="001l"
									class="xxk_off_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyStxf.do?method=stxfWh&lx=wh')" class="xxk_off_m">
									&nbsp;
									����ѧ��ά��
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_off_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_on_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyStxf.do?method=stxfWh&lx=cx')" class="xxk_on_m">
									&nbsp;
									����ѧ�ֲ�ѯ
								</li> 
								<li id="002r"
									class="xxk_on_r"></li>
							</ul>
							</logic:equal>
			  			</div>				
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								<logic:equal name="lx" value="cx">
								&nbsp;ѧ��:
								<html:select property="queryequals_xn"  style="width:100px"
										styleId="xn" value="">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								</logic:equal>
								<logic:equal name="lx" value="wh">
								&nbsp;ѧ��:
								<html:select property="xn"  disabled="true" style="width:100px"
										styleId="xn" >
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn" value="${xn}"/>
								</logic:equal>
								&nbsp;�꼶:
								<html:select property="queryequals_nj" style="width:100px"
										styleId="nj" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;ѧ�ţ�
								<logic:equal name="userType" value="stu">
									<input type="text" name="xsxh" id="xsxh" readonly="true" value="${userName }"/>
									<html:hidden property="querylike_xh" value="${userName}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
									<html:text property="querylike_xh" styleId="xh" style="width:100px"/>
								</logic:notEqual>
								&nbsp;������
								<html:text property="querylike_xm" styleId="xm" style="width:100px"/>
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<button type="button" class="button2" id="search_go"
											onclick="allNotEmpThenGo('ghxyStxf.do?method=stxfWh&doType=qry')" style="height: 25px">
											�� ѯ
										</button>
										<br>
										<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xm-qnj-bmdm-id');">
											�� ��
								</button>
							</td>
						</tr>
						<tr>
							<td>
							
							&nbsp;Ժϵ:
								<logic:equal name="userType" value="xy">
								<html:select property="xydm" disabled="true" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
								<html:hidden property="queryequals_xydm" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="queryequals_xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
								&nbsp;רҵ:
								<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
								</html:select>
								&nbsp;�༶:
								<html:select property="queryequals_bjdm" style="width:180px" styleId="bj"
									>
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
								</html:select>
								</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rsNum">
				<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
				</logic:empty>
			</logic:empty>
			<div id="tmpdiv1">
				
				</div>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1" >
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:equal name="lx" value="wh">
									<td>
										����
									</td>
								</logic:equal>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								 style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" style="display:none" checked id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
											   <input type="hidden" name="checkVal" id="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									</logic:iterate>
								<logic:equal name="lx" value="wh">
								<logic:iterate id="v" name="s" offset="1" length="7">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<td nowrap>
									<input type="text" name="zf" style="width:50px"  onkeyup="value=value.replace(/[^\d]/g,'')"/>
								</td>
								</logic:equal>
								<logic:equal name="lx" value="cx">
									<logic:iterate id="v" name="s" offset="1" length="8">
									<td nowrap>
										<bean:write name="v" />
									</td>
									</logic:iterate>
								</logic:equal>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=ghxyStxfForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="lx" value="wh">
					<button type="button" class="button2" onclick="save('ghxyStxf.do?method=stxfWh&doType=save')" style="width:80px">
						��   ��
					</button>
					</logic:equal>
					<logic:equal name="lx" value="cx">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
					</logic:equal>
				</logic:equal>
					<logic:equal name="lx" value="cx">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="wjcfDataExport('zjcmKkcs.do?method=expDate')" style="width:80px">
						��������
					</button>
					</logic:equal>
				</div>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
