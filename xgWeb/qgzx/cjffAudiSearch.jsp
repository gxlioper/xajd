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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	
	<body>		
		<script type="text/javascript">			
			//批量审核
			function batchAudi(shjg){
				var RowsStr="!!";	
				for (i=0; i<document.getElementsByName("pkV").length; i++){
			    	if(document.getElementsByName("pkV")[i].checked){
			    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
			    	}
				}				
				if (RowsStr=="!!"){
					alert("请选择要批量操作的记录！");
					return false;
				}
				
				refreshForm("qgzxcjff.do?method=xscjffAudiBatch&shjg=" + shjg);
			}
			
			//数据导出
			function exportData(){
				var url = "qgzxcjff.do?method=expXscjffxx";				
				var elementArr = ["nd","yf","gwdm","xh","yrdwdm","ffsj","fflx"];
				url += "&xydm=" + val("xy");
				url += "&zydm=" + val("zy");
				url += "&bjdm=" + val("bj");
				for(var i=0; i<elementArr.length; i++){
					if(ele(elementArr[i])){
						url += "&" + elementArr[i] + "=" + val(elementArr[i]);
					}
				}
				window.open(url);
			}
		</script>
		<html:form action="/qgzxcjff" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 酬金发放 - 酬金发放审核					
				</div>
			</div>
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle" >
					<thead>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" styleId="bj">
								<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/qgzxcjff.do?method=cjffAudiSearch')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								年度：								
								<html:select property="nd" style="width:80px" styleId="nd">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="nd" labelProperty="nd"/>
								</html:select>					
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月份：
								<html:select property="yf" style="width:90px"
									styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf"
										labelProperty="yf" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;岗位名称：
								<html:select property="gwdm" style="width:130px" styleId="gwdm">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdm"
										labelProperty="gwdm" />
								</html:select>
							</td>
							<tr>
							<td align="left" colspan="3">
								用人单位：
								<html:select property="yrdwdm" style="width:90px"
									styleId="yrdwdm">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm"
										labelProperty="yrdwmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号：								
								<html:text property="xh" style="width:85px" styleId="xh"></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发放时间：	
								<html:text property="ffsj" style="width:85px" styleId="ffsj"/>
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
						&nbsp;&nbsp;
						<font color="blue">单击表头可以排序</font>
					</legend>
					<table width="100%" class="tbstyle" id="rsTable">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="all" name="all" onclick="chec()" />
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
							<tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="showTopWin('qgzxcjff.do?method=showXscjffsh&pkV='+this.cells[0].getElementsByTagName('input')[0].value)">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=qgzxForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px;">
				<button type="button" class="button2"
					onclick="batchAudi('通过')"
					style="width:80px">
					通 过
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" 
					onclick="batchAudi('不通过')" 
					style="width:80px">
					不通过
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" 
					onclick="exportData()" 
					style="width:80px">
					数据导出
				</button>
			</div>
		</html:form>
		<logic:present name="flag">
			<logic:equal value="true" name="flag">
				<script>
					alert('操作成功！');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="flag">
				<script>
					alert('操作失败！');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present> 
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
		