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
		<base target="_self">
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
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	   function chec(){
             for(i=0;i<document.getElementsByName("pkV").length;i++){
      	         document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
             }
       }
       function stuOnLoadCon(){
	     var userType =$("userType").value;
	     if(userType == 'stu'){
	     $("nj").disabled = true;
	     $("xydm").disabled = true; 
	     $("zydm").disabled = true;
	     $("bjdm").disabled = true;
	     $("xh").disabled = true;
	     $("xm").disabled = true;
	    }  
	}     
       
	</script>
	<body onload="stuOnLoadCon();xyDisabled('xy')">
		<html:form action="/gzdxPjpy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="delPk" id="delPk" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 综测信息维护 - 综合表现申报评分
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									学年：
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;年级：
									<html:select property="nj"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:100px" styleId="xh"></html:text>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" styleId="xm" style="width:80px"></html:text>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/pjpy_gzdx_xsZhbxpf.do?go=go');">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left">
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
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" styleId="bj">
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
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
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand;"
									ondblclick="dataView()">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<TABLE width="99%" id="rsTable" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=pjpyGzdxActionForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
				</logic:notEmpty>
			</div>
		</html:form>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		<center>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" onclick="dataModi()" style="width:80px">
						评 分
					</button>
				</div>
				<script language="javascript">
                     document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                     document.getElementById("btn").style.width = "96%";
                     window.setInterval("initBTNTool('btn')",1);
                </script>
			</logic:equal>
		</center>
	</body>

	<script type="text/javascript">
   function dataAdd(){
       showTopWin("/xgxt/pjpy_gzdx_xsZhbxfsb.do",800,600);
   }
   function dataModi(){
        if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	    } 
	    var url = "/xgxt/gzdxPjpy.do?method=zhBxPf";
	    url +="&pkValue=";
	    url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;	      
        showTopWin(url,800,600);
   }
   function dataView(){
        var url = "/xgxt/gzdxPjpy.do?method=zhBxPf&act=view";
	    url +="&pkValue=";
	    url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
        showTopWin(url,800,600);
   }   

</script>
</html>
