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
		<meta name="Copyright" content="正方软件 zfsoft" />
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
			if($("rsNum").value == "0"||$("rsNum").value=="")){
				alert("请确定欲设置（取消）的学生！");
				return false;
			}else{
				if (confirm("确定进行该操作吗?")) {
					saveUpdate(url,'');
				}
			}
		}
		}
	</script>
	<body>
		<html:form action="ghxyXxxf" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="view_sjxy_dekthdqh" />
			<input type="hidden" name="rsNum" ud="rsNum" value="${rsNum}" styleId="rsNum"/>
			<input type="hidden" name="lx" id="lx" value="${lx}"styleId="lx"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:${title}
				</div>
			</div>	
				<div class="xxk">
			    			
			    		<logic:equal name="lx" value="wh">
							<ul>
								<li id="001l"
									class="xxk_on_l"></li>
								<li id="001m"
									onclick="refreshForm('ghxyXxxf.do?method=xxxfWh&lx=wh')" class="xxk_on_m">
									&nbsp;
									选修分维护
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_on_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_off_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyXxxf.do?method=xxxfWh&lx=cx')" class="xxk_off_m">
									&nbsp;
									选修分查询
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
									onclick="refreshForm('ghxyXxxf.do?method=xxxfWh&lx=wh')" class="xxk_off_m">
									&nbsp;
									选修分维护
									&nbsp;
								</li>
								<li id="001r"
									class="xxk_off_r"></li>
							</ul>
							<ul>
								<li id="002l"
									class="xxk_on_l"></li>
								<li id="002m"
									onclick="refreshForm('ghxyXxxf.do?method=xxxfWh&lx=cx')" class="xxk_on_m">
									&nbsp;
									选修分查询
								</li> 
								<li id="002r"
									class="xxk_on_r"></li>
							</ul>
							</logic:equal>
			  			</div>	
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								<logic:equal name="lx" value="wh">
								&nbsp;学年:
								<html:select property="xn" disabled="true" style="width:100px"
										styleId="xn" >
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn" value="${dqxn}"/>
								&nbsp;学期:
								<html:select property="xq" disabled="true" style="width:100px"
										styleId="xq" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
								<html:hidden property="queryequals_xq" value="${dqxq}"/>
								</logic:equal>
								<logic:equal name="lx" value="cx">
								&nbsp;学年:
								<html:select property="queryequals_xn" style="width:100px"value=""
										 >
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								&nbsp;学期:
								<html:select property="queryequals_xq" style="width:100px" value=""
										 >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
								</logic:equal>
								&nbsp;年级:
								<html:select property="queryequals_nj" style="width:100px"
										styleId="nj" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;学号：
								<logic:equal name="userType" value="stu">
									<input type="text" name="xsxh" value="${userName }" readonly="true" style="width:100px"/>
									<html:hidden property="querylike_xh" value="${userName}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
									<html:text property="querylike_xh" styleId="xh" style="width:100px"/>
								</logic:notEqual>
								&nbsp;姓名：
								<html:text property="querylike_xm" styleId="xm" style="width:100px"/>
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<button type="button" class="button2" id="search_go"
											onclick="allNotEmpThenGo('ghxyXxxf.do?method=xxxfWh&doType=qry')" style="height: 25px">
											查 询
										</button>
										<br>
										<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('xm-qnj-bmdm-id');">
											重 置
								</button>
							</td>
						</tr>
						<tr>
							<td>
							&nbsp;院系:
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
								&nbsp;专业:
								<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
								</html:select>
								&nbsp;班级:
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
					未找到任何记录！
				</p>
				</logic:empty>
			</logic:empty>
			<div id="tmpdiv1">
				
				</div>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
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
									分数
								</td>
								</logic:equal>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								 style="cursor:hand">
								
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" checked id="pkV"  style="display : none"
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
											   <input type="hidden" name="checkVal" id="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									</logic:iterate>
								<!-- 查询 -->
								<logic:equal name="lx" value="cx">
								<logic:iterate id="v" name="s" offset="1" length="8">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="9" length="1">
									<td nowrap>
										${v}分
									</td>
								</logic:iterate>
								</logic:equal>
								
								<!-- 维护 -->
								<logic:equal name="lx" value="wh">
								<logic:iterate id="v" name="s" offset="1" length="8">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="8" length="1">
								<td nowrap>
									<input type="text" name="zf"  style="width:50px" onkeypress="return onlyNum(this,20)"/>分
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
								page="/sjcz/turnpage.jsp?form=ghxyXxxfForm"></jsp:include>
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
					<button type="button" class="button2" onclick="save('ghxyXxxf.do?method=xxxfWh&doType=save')" style="width:80px">
						保   存
					</button>
					</logic:equal>
					<logic:equal name="lx" value="cx">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="impAndChkData();"
							style="width:80px">
							导入数据
						</button>
					</logic:equal>
				</logic:equal>
					<logic:equal name="lx" value="cx">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="wjcfDataExport('zjcmKkcs.do?method=expDate')" style="width:80px">
						导出数据
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
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
