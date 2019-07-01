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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body >
		
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" name="qshV" id="qshV" />												
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
                     公寓管理 - 审核 - 文明寝室审核
				</div>
			</div>
			
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select  property="xn" style="width:90px" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
								<html:select  property="xq" style="width:90px" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
								
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 楼栋名:
								<html:select property="lddm" style="width:120px"
										onchange="GetQshList()" styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;寝室号：
								<html:select property="qsh" style="width:110px" styleId="qsh">
										<html:option value=""></html:option>
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="refreshForm('/xgxt/nblgxy_gygl.do?method=nblgxy_WmqsSbShQer');this.disabled=true;">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								评优时间：
								<html:text property="pysj" onblur="dateFormatChg(this)"
									onclick="return showCalendar('pysj','y-mm-dd');" style="cursor:hand " />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核状态：
								<html:select property="yesNo" style="width:80px" styleId="yesNo">
										<html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
								</html:select>								
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
						<font color="blue">提示：双击一行可以查看详细信息，并可修改其审核状态；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand;background-color:
	    					<logic:iterate id="v" name="s" offset="1" length="1">
						    	<bean:write name="v"/>
						    </logic:iterate>" ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
  </body>   
<script type="text/javascript">
  function CheckDo(){
    var	w = 550;
	var	h = 350;	
	url = "/xgxt/nblgxy_gygl.do?method=nblgxy_wmqsSbSh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
  } 
</script>  
</html>
