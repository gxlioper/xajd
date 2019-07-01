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
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xszz/xszzFunction.js"></script>
	<script language="javascript">
	//保存专业分配
	function saveFp(){
		var rsNum = $("rsNum").value;
		var xmNum = $("xmNum").value;
		for(var i=0;i<rsNum;i++){
			var id = "checkVal"+i;
			var zyxm = "";
				
			for(var j=0;j<xmNum;j++){
				var xm = id+j;	
				if($(xm)){
					if($(xm).checked == true){
						zyxm+=$(xm).value+"!!@@!!";
					}
				}
			}
			
			var tmp = document.createElement("input");
				tmp.type = "hidden";
				tmp.name = "zyxmList";
				tmp.value = zyxm;
				
				document.forms[0].appendChild(tmp);
		}	
		if (confirm("确定所绑定的补助类别?")) {
			saveUpdate('/xgxt/guizdxXszz.do?method=fsbzfpManage&doType=save','');
		}
	}
	
	//设置专业项目
	function setZyxm(){
	
		var rsNum = $("rsNum").value;
		var xmNum = $("xmNum").value;
		
		for(var i=0;i<rsNum;i++){
		
			var id="bzlx"+i;
			
			if($("bzlx"+i) && $("bzlx"+i).value!=""){
			
				var bzlx = $("bzlx"+i).value.split("!!@@!!");
				
				for(var j=0;j<bzlx.length;j++){
				
					var id = "checkVal"+i;
						
					for(var k=0;k<xmNum;k++){
						var xm = id+k;	
						if($(xm)){
							if($(xm).value == bzlx[j]){
								$(xm).checked = true
							}
						}
					}
				}
			}
		}
	}
	
	</script>
	<body onload="xyDisabled('xy');setZyxm();">
		<html:form action="/guizdxXszz" method="post">
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" name="xmNum" id="xmNum" value="${xmNum }"/>
			<input type="hidden" name="rsNum" id="rsNum" value="${rsNum }"/>
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
										年度：
										<html:select property="nd" style="" onchange="">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="" styleId="xy" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/guizdxXszz.do?method=fsbzfpManage');">
											查询
										</button>
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
								<font color="blue">提示：单击表头可以排序.</font>
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
										<td>
											补助项目
										</td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="">
										
										<logic:iterate id="v" name="s" offset="0" length="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td align="left">
												<bean:write name="v" />
												<input type="hidden" id="bzzy" name="bzzy" 
												value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>">
											</td>
										</logic:iterate>
										<!-- 副食补助项目 -->
										<td>
											<logic:iterate id="v" name="s" offset="4" length="1">
												<input type="hidden" id="bzlx${index}" value="<bean:write name="v"/>"/>
											</logic:iterate>
											<logic:notEmpty name="xmList">
												<logic:iterate name="xmList" id="xm" indexId="num">
												<input type="checkbox" id="checkVal${index}${num}" name="checkVal" 
													value="${xm.xmdm }">${xm.xmmc }&nbsp;&nbsp;&nbsp;&nbsp;
												</logic:iterate>
											</logic:notEmpty>
										</td>
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
											page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
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
							<button class="button2" 
								style="width:80px"
								onclick="saveFp()">
								保	存
							</button>
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
						$("doType").value = "";
					}
				</script>
				</logic:equal>
			</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
