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
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<script type="text/javascript">
		function cxcfprint(url){
		if (curr_row==null || curr_row==''){	
			window.open(url+='');
		}else{
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += '&xh=';
		url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		window.open(url);
		}
}
		</script>
	<body onload="xyDisabled('xy');">
		 <script language="javascript" src="js/function.js"></script>
		 <script language="javascript" src="js/AjaxFunction.js"></script>
	    <script type="text/javascript" src="js/wjcfFuction.js"></script>
	    <script type="text/javascript" src="js/calendar.js"></script>
	    <script type="text/javascript" src="js/calendar-zh.js"></script>
	    <script type="text/javascript" src="js/calendar-setup.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	    <script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	    <script type="text/javascript" src="wjcf/csmz/csmzJs/csmzJs.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		
		<html:form action="/wjcfjgsdxwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message bundle="wjcfjgsdx" key="wjcf_jgsdx_cxcfqry"/>
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
				<input type="hidden" id="isFdy" name="isFdy"
				value="${fdyQx }" />
					<input type="hidden" id="isBzr" name="isBzr"
				value="${bzrQx }" />
				<input type="hidden" name="xyV" id="xyV" value=""/>
				<input type="hidden" name="bjV" id="bjV" value=""/>
				<input type="hidden" name="zyV" id="zyV" value=""/>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:110px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" style="width:90px" 
									styleId="xq" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="xh" styleId="xh" style="width:90px;"
								 styleClass="inputtext" maxlength="20"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" styleId="xm" style="width:90px;"
								 styleClass="inputtext" maxlength="10"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('cxcfcx.do')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy"
								 onchange="initZyList();initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
								 onchange="initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
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
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
							<td nowrap>
								<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:"
   					 			ondblclick="chkPriseOne_shgc('cxcfview.do?pkValue=',650,500)">							
									<td align=center><input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									    		<input type="hidden" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
									<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button type="button" class="button2" 
								onclick="chkPriseOne6('cxcfdel.do')" id="btn_del">
								批量删除
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" 
								onclick="cxcfprint('cxcfprint.do?pkValue=')" id="btn_print">
								打印/预览
							</button>
						</div>
					</logic:equal>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script> 
		<logic:present name="result">
			<logic:equal value="view" name="result">
			<script>
				
				window.document.getElementById('search_go').click();
			</script>
			<logic:notEqual value="view" name="result">
				<script>
				
				window.document.getElementById('search_go').click();
			</script>
			</logic:notEqual>
			</logic:equal>
		</logic:present>
</body>
</html>
