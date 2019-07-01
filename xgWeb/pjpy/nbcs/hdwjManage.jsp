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
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function hdwj(){
		if(curr_row == null){
			alert('请选择要回答的问卷！');
			return false;
		}
		var stxx = curr_row.cells[5].innerText;
		if("未组卷 " == stxx){
			alert("该问卷还未进行组卷，请确认！");
			return false;
		}
		showInfo('/xgxt/nbcsPjpy.do?method=hdwjUpdate&mklx=hd','update','800','600');
	}
	</script>
	<body onload="">
		<html:form action="/nbcsPjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
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
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										&nbsp;&nbsp;学年：
										<!-- 非宁波城市 -->
										<logic:equal name="xxdm" value="12645">
										&nbsp;&nbsp;学年：
											<html:hidden property="queryequals_xn" styleId="xn"/>
											<html:select property="queryequals_xn" style="width:120px" styleId="xn" disabled="true">
												<html:options collection="xnList" property="xn" labelProperty="xn" />
											</html:select>		
											&nbsp;&nbsp;年度：
											<html:hidden property="queryequals_nd" styleId="nd"/>
											<html:select property="queryequals_nd" style="" styleId="nd" disabled="true">
												<html:options collection="ndList" property="nd" labelProperty="nd" />
											</html:select>
											&nbsp;&nbsp;学期：
											<html:hidden property="queryequals_xq" styleId="xq"/>
											<html:select property="queryequals_xq" style="" styleId="xq" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
											</html:select>
										</logic:equal>
										<!-- 非宁波城市 -->
										<logic:notEqual name="xxdm" value="12645">
											&nbsp;&nbsp;学年：
											<html:select property="queryequals_xn" style="width:120px" styleId="xn" onchange="getWjList()">
												<html:options collection="xnList" property="xn" labelProperty="xn" />
											</html:select>		
											&nbsp;&nbsp;年度：
											<html:select property="queryequals_nd" style="" styleId="nd" onchange="getWjList()">
												<html:options collection="ndList" property="nd" labelProperty="nd" />
											</html:select>
											&nbsp;&nbsp;学期：
											<html:select property="queryequals_xq" style="" styleId="xq" onchange="getWjList()">
												<html:option value=""></html:option>
												<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
											</html:select>
										</logic:notEqual>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:25px" id="search_go"
											onclick="if(checkSearchTj('querygreaterequal_jlsj','querylessequal_jlsj')){allNotEmpThenGo('/xgxt/nbcsPjpy.do?method=hdwjManage');}">
											查询
										</button>
										<br>
										<button class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xn-nd-xq-id-zjxx-querygreaterequal_jlsj-querylessequal_jlsj');return false;">
											重置
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										&nbsp;&nbsp;问卷：
										<html:select property="queryequals_id" style="" styleId="id">
											<html:options collection="wjList" property="dm" labelProperty="mc" />
										</html:select>
										&nbsp;&nbsp;组卷信息：
										<html:select property="queryequals_zjxx" style="" styleId="zjxx">
											<html:option value="">----请选择----</html:option>
											<html:options collection="zzList" property="en" labelProperty="cn" />
										</html:select>
										&nbsp;&nbsp;建立时间：
										<html:text property="querygreaterequal_jlsj" styleId="querygreaterequal_jlsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('querygreaterequal_jlsj','y-mm-dd');"/>	
										——
										<html:text property="querylessequal_jlsj" styleId="querylessequal_jlsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('querylessequal_jlsj','y-mm-dd');"/>	
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
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
								<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
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
										ondblclick="showInfo('/xgxt/nbcsPjpy.do?method=hdwjUpdate','view','800','600')">								
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td align="center">
												<bean:write name="v"/>
												<input type="checkbox" id="checkVal" style="display : none" 
												   name="primarykey_checkVal"  
												   value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
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
											page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
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
							<button class="button2"
								onclick="hdwj()"
								style="width:80px">
								回&nbsp;&nbsp;答
							</button>
						</logic:equal>
					</div>
					</center>
				</div>
			</logic:empty>
		</html:form>
		<logic:notEmpty name="result">
			<logic:present name="message">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
					}
				</script>
			</logic:present>
		</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
