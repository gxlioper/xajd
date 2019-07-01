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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript">
			function chDate(){
				if($("gzkssj").value!=""&&$("gzjssj").value!=""){
					if($("gzkssj").value > $("gzjssj").value){
						alert("开始时间大于结束时间，请确认！！");
						return false;
					}
				}
				return true;
			}
			function modiData(act){
			   if (curr_row == null) {
		         alert("请选择要操作的行！\n(单击每行记录)");
		         return false;
	           } else {		
		         var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
		         var url = "/xgxt/zjcmxy_Gygl.do?method=dispose&pkValue=";
		         url+=val;
		         url+="&act="+act;
		         showTopWin(url,700,500)
		       }	
			}
			function extData(){
			   document.forms[0].action = "/xgxt/zjcmxy_Gygl.do?method=expStatData";
			   document.forms[0].target = "_blank";
			   document.forms[0].submit();
			   document.forms[0].target = "_self";
			}
		</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="bjLimit('bj');">

		<html:form action="zjcmxy_Gygl.do" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 住宿纪律管理 - 统计
				</div>
			</div>

			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								年级：
								<html:select property="nj" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年：
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								
								&nbsp;&nbsp;学号：
								<html:text property="xh" style="width: 90px" styleId="xh" />
								&nbsp;&nbsp;姓名：
								<html:text property="xm" styleId="xm" style="width:80px"></html:text>
								&nbsp;&nbsp;违纪时间：
								<html:text property="kssj" readonly="true"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kssj','y-mm-dd','ag');"
									style="cursor:hand;width:80px " />
								-
								<html:text property="jssj" readonly="true"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('jssj','y-mm-dd','ag');"
									style="cursor:hand;width:80px " />
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('/xgxt/zsjlStat.do?go=go')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp; 班级：
								<html:select property="bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								楼栋名：
								<html:select property="lddm" styleId="lddm"
									onchange="getLcList()">

									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
								&nbsp;&nbsp;楼层：
								<html:select property="lc" styleId="lc" onchange="getQshList2()">

									<html:options collection="lcList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;寝室号：
								<html:select property="qsh" styleId="qsh">

									<html:options collection="qshList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;处理结果
								<html:select property="xycljg">
									<html:option value=""></html:option>
									<html:options collection="cljgList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;是否处理
								<html:select property="sfcf">
									<html:option value=""></html:option>
									<html:option value="处分">处分</html:option>
									<html:option value="不处分">不处分</html:option>
								</html:select>
								&nbsp;&nbsp;纪律类别：
								<html:select property="wjlbdm" styleId="wjlbdm">
									<html:option value=""></html:option>
									<html:options collection="wjlbList" property="wjlbdm"
										labelProperty="wjlbmc" />
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
						<font color="blue">提示：单击表头可以排序</font>
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<br>
			<br>
			<br>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onclick="extData()"
							style="width:80px">
							导出Excel
						</button>
				</div>
			</center>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);			
		</script>
	</body>
</html>
