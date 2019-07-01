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
		<script>   
var mb = 0;
function colorOn(){	
	for(i = 0;i<mbT.rows.length;i++){	
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";			
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}
function printZS(mod){
	if(mb >3 || mb <0){
		mb = 0;
		colorOn();
	}
<%--	var url = "/xgxt/print/printCotener_";--%>
<%--	url += mb;--%>
<%--	url += ".html";--%>
<%--	document.getElementById("nj").value = mod;--%>
<%--	chgRight(url,'_blank');--%>
<%--	document.forms[0].target = "_self";--%>
	  if (curr_row == null) {
		   alert("请选要打印的记录！\n单击一行记录即可");
		   return false;
	  } else {       
        var xh = "";
        if(curr_row != null){
            xh = replaceChar(curr_row.cells[0].innerText," ","");
        }       
	    var rqvar = new Date();
	    var mon = rqvar.getMonth()+'';
	    var day = rqvar.getDay()+'';
	    mon = (mon.length==1)?"0"+mon:mon;
	    day = (day.length==1)?"0"+day:day;
		var rq =  rqvar.getYear()+"-"+mon+"-"+day;
		var fzrq = "";
		//打印具体事项时不用打印日期
		fzrq = prompt(" 请输入颁证日期：(日期格式为2008-08-08)",rq);
		if(fzrq==null){
		    return false;
		}
		if(fzrq==""){
		    alert("请输入颁证日期！");
		    return false;
		}		
		if(fzrq.length!=10
		         ||fzrq.charAt(4)!="-"
		         ||fzrq.charAt(7)!="-"){
		   alert("颁证日期格式不对！");
		   return false;
		}	
		window.open("xcxy_sztzprintOne.do?mb="+mb+"&fzrq="+fzrq+"&xh="+xh);
     }
}

function printCS(){
	var url = "/xgxt/sztz/print/xcxy/xcxy_sztzCs_";
	url += mb;
	url += ".html";
	chgRight(url,'_blank');
	document.forms[0].target = "_self";
}
</script>
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body onload="colorOn();">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>
				<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
        <script language="javascript" src="js/AjaxFunction.js"></script>
			<html:form action="/xcxy_sztzprint" method="post">
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：素质拓展 - 信息维护 - 证书打印
					</div>
				</div>
				<fieldset>
					<legend>
						查 询
					</legend>					
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj"
										style="width:90px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm"
										style="width:180px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm"
										style="width:180px;background-color:#FFFFFF" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/xcxy_sztzprint.do?go=go');">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									班级：
									<html:select property="bjdm"
										style="width:120px;background-color:#FFFFFF" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;学号：
									<html:text property="xh" />
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<div class="noprint">
					<fieldset>
						<legend align="center">
							打印区----模板选择
						</legend>
						<table class="tbstyle" align="center" width="100%" height="150px"
							id="mbT">
							<tr>
								<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=0;colorOn();"> </td> -->
								<td bgcolor="#FFFFFF" width="33%" align="center"
									style="cursor:hand" onclick="mb=0;colorOn();">										
									<font color="red">学校提供模板</font>
									<img src="" height="150px" border="1" />
								</td>
								<td bgcolor="#FFFFFF" width="34%" align="center"
									style="cursor:hand" onclick="mb=1;colorOn();">
									<font color="red">学校提供模板</font>
									<img src="" height="150px" border="1" />
								</td>
								<td bgcolor="#FFFFFF" width="33%" align="center"
									style="cursor:hand" onclick="mb=2;colorOn();">
									<font color="red">学校提供模板</font>
									<img src="" height="150px" border="1" />
								</td>
								<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=2;colorOn();"> </td> -->
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" align="center" style="cursor:hand"
									onclick="mb=0;colorOn();">
									模板一(证书第一页)
								</td>
								<td bgcolor="#FFFFFF" align="center" style="cursor:hand"
									onclick="mb=1;colorOn();">
									模板二(证书第二页)
								</td>
								<td bgcolor="#FFFFFF" align="center" style="cursor:hand"
									onclick="mb=2;colorOn();">
									模板三(证书第三页)
								</td>
							</tr>
						</table>
					</fieldset>
				</div>

				<div class="noprint">
					<fieldset>
						<legend align="center">
							非打印区
						</legend>
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
								</legend>
								<table width="100%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" style="cursor:hand;">
											<logic:iterate id="v" name="s">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</fieldset>
						</logic:notEmpty>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<input type='button' class='button2' value='打印测试页'
								onclick="printCS()">
							<input type='button' class='button2' value='单个打印'
								onclick="printZS(1)">							
						</div>
					</fieldset> 
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
