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

	<script language="javascript" src="js/commanFunction.js"></script>
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
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url,800,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		 function doQuery(){
	    	if($("qjjssj") && $("qjqssj")){
	    		if($("qjjssj").value<$("qjqssj").value){
	    			alert("请假开始时间早于结束时间！");
	    			return false;
	    		}
	    	}
	    	allNotEmpThenGo('rcswKqglXskq.do?method=rcswKqglXskqTj&doType=qry')
	    }
	</script>
	<body>
		<html:form action="rcswKqglXskq" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 -- 考勤管理 -- 学生请假统计 		
				</div>
			</div>			
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								&nbsp;学年：
								<html:select property="xn" styleId="xn">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;学期：
								<html:select property="xq"  styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;学号：
								<logic:equal name="userOnLine" value="student">
									<html:text property="xh" disabled="true"  styleId="xh"/>
									<html:hidden property="xh" value="${userName}"/>
								</logic:equal>
								<logic:notEqual name="userOnLine" value="student">
									<html:text property="xh"></html:text>
								</logic:notEqual>
								姓名：
								<html:text property="xm"  styleId="xm"></html:text>
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
									<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" id="search_go"
											onclick="doQuery()" style="height: 25px">
											查 询
										</button>
										<br>
										<button type="button" class="button2" style="height:25px" id="cz"
											onclick="czSearchCond('nj-xn-xq-xh-xm-xy-zy-bj-qjqssj-qjjssj-id');">
											重 置
										</button>
							</td>
						</tr>
						<tr>
						<td>
							&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
							<logic:notEqual name="userType" value="xy">
							<html:select property="xydm" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:notEqual>
							<logic:equal name="userType" value="xy">
							 <logic:equal name="isFdy" value="true">
							  <html:select  property="xydm" style="width:180px"
							   styleId="xy" onchange="initZyList();initBjList();">
							     <html:option value=""></html:option>
							     <html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
									
							   </html:select>
							 </logic:equal>
							 <logic:notEqual name="isFdy" value="true">
							 <html:select disabled="true" property="queryequals_xydm" style="width:180px"
							   styleId="xy" onchange="initZyList();initBjList();">
							     <html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
									
							   </html:select>
							   	<html:hidden property="xydm" value="${userDep}"/>
							   </logic:notEqual>
							</logic:equal>
							&nbsp;专业：
							<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;班级：
							<html:select property="bjdm" style="width:180px" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						</tr>
						<tr>
						<td>
						&nbsp;请假时间：
						 <html:text property="qjqssj" styleId="qjqssj" 
							onclick="return showCalendar('qjqssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />至
						 <html:text property="qjjssj" styleId="qjjssj" 
							onclick="return showCalendar('qjjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
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
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序;统计的数据为请假审核通过的数据;双击可查询详细信息</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<logic:notEqual name="index" value="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"" ondblclick="modi('rcswKqglXskq.do?method=allTjxx&xh=<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>')"
								 style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0" length="7">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="7" length="1">
									<td nowrap>
										<bean:write name="v" />天
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
					<button type="button" class="button2" onclick="wjcfDataExport('rcswKqglXskq.do?method=printQjsjbb')" style="width:80px">
						导出数据
					</button>
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
