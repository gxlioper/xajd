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
	function chMklx(mklx){
	
		$("mklx").value = mklx;
		
		hiddenJg(mklx);
	}
	
	function searchFsbz(){
		var mklx = $("mklx").value;
		var nd = $("queryequals_nd").value;
		var bzlx = $("queryequals_bzlx").value;
		var querygreaterequal_yf = parseInt($("querygreaterequal_yf").value);
		var querylessequal_yf = parseInt($("querylessequal_yf").value);
		//未发放
		if(mklx == "wff"){
			if(nd == ""){
				alert("请确定要查找未发放的年度！")
				return false;
			}else if(bzlx == ""){
				alert("请确定要查找未发放的补助类型！")
				return false;
			}
		}
		//结果
		else if(mklx == "jg"){
			if(querygreaterequal_yf != "" && querylessequal_yf != ""){
				if(querygreaterequal_yf > querylessequal_yf){
					alert("结束月份不能大于开始月份！");
					return false;
				}
			}
		}
		allNotEmpThenGo('/xgxt/guizdxXszz.do?method=fsbzjgManage');
	}
	
	function checkRadio(){
	
		var mklx = $("mklx").value;

		hiddenJg(mklx);
		
		var lx = document.getElementsByName('lx');
		for(var i=0;i<lx.length;i++){
			var lxmc = lx[i].value;
			if(mklx == lxmc){
				document.getElementsByName('lx')[i].checked = true;
				break;
			}
		}
	}
	
	function hiddenJg(mklx){
		if(mklx == "jg"){
			$("expBtn").style.display = "";
			$("jg").style.display = "";
			$("nojg").style.display = "none";
			$("queryequals_yf").value = "";
		}else if(mklx == "tj"){
			$("expBtn").style.display = "";
			$("jg").style.display = "none";
			$("nojg").style.display = "none";
			$("queryequals_yf").value = "";
			$("querygreaterequal_yf").value = "";
			$("querylessequal_yf").value = "";
		}else{
			$("expBtn").style.display = "none";
			$("jg").style.display = "none";
			$("nojg").style.display = "";
			$("querygreaterequal_yf").value = "";
			$("querylessequal_yf").value = "";
		}
	}
	
	</script>
	<body onload="xyDisabled('xy');checkRadio()">
		<html:form action="/guizdxXszz" method="post">
			<%@ include file="/xszz/hiddenValue.jsp"%>
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
										年级：
										<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;年度：
										<html:select property="queryequals_nd" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select>
										<!-- 非结果 -->
										<span id="nojg" style="display = none">
										&nbsp;&nbsp;月份：
										<html:select property="queryequals_yf" style="" onchange="" styleId="queryequals_yf">
											<html:option value=""></html:option>
											<html:options collection="yfList" property="yf" labelProperty="yf" />
										</html:select>
										</span>
										<!-- 非结果 end-->
										<!-- 结果 -->
										<span id="jg">
										&nbsp;&nbsp;月份：
										<html:select property="querygreaterequal_yf" style="" onchange="" styleId="querygreaterequal_yf">
											<html:option value=""></html:option>
											<html:options collection="yfList" property="yf" labelProperty="yf" />
										</html:select>
										------
										<html:select property="querylessequal_yf" style="" onchange="" styleId="querylessequal_yf">
											<html:option value=""></html:option>
											<html:options collection="yfList" property="yf" labelProperty="yf" />
										</html:select>
										</span>
										<!-- 结果 end-->
										&nbsp;&nbsp;补助类型：
										<html:select property="queryequals_bzlx" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="bzlxList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="searchFsbz()">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="queryequals_xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="queryequals_zydm" style="" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;班级：
										<html:select property="queryequals_bjdm" style="" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										学号：
										<html:text property="querylike_xh" style="" maxlength="20"/>
										&nbsp;&nbsp;姓名：
										<html:text property="querylike_xm" style="" maxlength="20"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="lx" value="jg" onclick="chMklx(this.value)">结果查询
										<input type="radio" name="lx" value="tj" onclick="chMklx(this.value)">结果统计
										<input type="radio" name="lx" value="wff"onclick="chMklx(this.value)">未发放
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
								<font color="blue">提示：单击表头可以排序。</font>
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
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="">	
										<logic:iterate id="v" name="s" offset="0">
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
							&nbsp;&nbsp;
							<button class="button2" onclick="impAndChkData()"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;
							<button class="button2" id="expBtn" 
								onclick="wjcfDataExport('guizdxXszz.do?method=fsbzjgManage&doType=exp')"
								style="width:80px">
								导出数据
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
