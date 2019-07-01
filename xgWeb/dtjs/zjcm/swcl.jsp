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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
	var obj_bgc = "#FFFFFF";
	var cur_bgc = "#ffdead";//选中行背景（字符串）
	var curr_row = null;
	var sort_col = null;
	var temppk = "";
	var num = "";
	//多选功能
	function rowMore(objTr,tag) {
		curr_row = objTr;
		var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
		iRow=window.event.srcElement;
		do{
			iRow=iRow.parentElement;
		}while(iRow.tagName!='TR')
	
		//Ctrl多选
		if(event.ctrlKey){
			var j=-1;
			for(i=0;i<Rows.length;i++){
				if(iRow==Rows[i]){
					j=i;
					break;
				}
			}
			if(j!=-1){
				for(i=j;i<Rows.length-1;i++){
					Rows[i]=Rows[i+1];
				}
				Rows.length=Rows.length-1;
				iRow.style.backgroundColor = "#FFFFFF";
				temppk = temppk.replace(pk,"");
				pk="";

			}else{
				Rows[Rows.length]=iRow;
			}
		}
		else{	
			if (Rows.length!=0){
				for (i=0; i<Rows.length; i++){	
					if (Rows[i]!=null && Rows[i].tagName.toLowerCase() == "tr") {
						Rows[i].style.backgroundColor = obj_bgc;
	    			}
				}
			}
			if(document.all("ycxh")){
				document.getElementById("ycxh").value=curr_row.cells[1].innerText.trim();
			}
			obj_bgc = curr_row.style.backgroundColor;
			curr_row.style.backgroundColor = cur_bgc;
			Rows.length=1;
			Rows[0]=iRow;
		}
		changeC(iRow);
		temppk = temppk + pk + splitSignOne;
	}
	//选中行变色
	function changeC(E){
		for(i=0;i<Rows.length;i++){
			Rows[i].style.backgroundColor=cur_bgc;
			num = i;
		}
	}
	function rowSelect(objTr) {
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
	}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
	}
	function searchCheck(url){
		var xh = document.getElementById("xhV").value;
		if(xh != ""){
			if(xh.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("学号必需为数字！");
				return false;
			}
		}
		allNotEmpThenGo(url);
	}
	function swclDel(){
		if(curr_row == null){
			alert('请选择要删除的信息!');
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm('/xgxt/dtjs_zjcm.do?method=delSwcl&go=go&pk='+temppk)
	}
	function sh(zht){
		if(curr_row == null){
			alert('请选择要审核的行!');
			return false;
		}
		refreshForm('/xgxt/dtjs_zjcm.do?method=swclShhAll&go=go&pk='+temppk +'&zht='+ zht)
	}
	/*
	function setNull(url){
		document.getElementById("xhV").value="";
		document.getElementById("sssj").value="";
		document.getElementById("xy").value="";
		document.getElementById("nj").value="";
		document.getElementById("zy").value="";
		document.getElementById("bj").value="";
		document.getElementById("xm").value="";
		document.getElementById("shlx").value="";
		refreshForm(url);
	}*/
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<center>
			<html:form action="/dtjs_zjcm" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="method" name="method"
				value="swcl" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble"/>" />
			<input type="hidden" id="delSwcl" name="delSwcl" value="${delSwcl}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
								</html:select>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="searchCheck('/xgxt/dtjs_zjcm.do?method=swcl')">
									查询
								</button>
								<!--  <button type="button" class="button2" style="height:30px" id="reset_go"
									onclick="setNull('/xgxt/dtjs_zjcm.do?method=swcl')">
									清空
								</button>	-->
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<input type="hidden" name="zyV" value=""/>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:120px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
								&nbsp;&nbsp;学号：
								<html:text property="xh" styleId="xhV" maxlength="10"/>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" />
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
							&nbsp;&nbsp;送审时间:
							<html:text  property="sssj" styleId="sssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sssj','y-mm-dd');"/>
							&nbsp;&nbsp;审核状态：
								<html:select property="shlx" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="wsh">未审核</html:option>
									<html:option value="tg">通过</html:option>
									<html:option value="wtg">未通过</html:option>
								</html:select>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以选定；单击表头可以排序;按住Ctrl可以多选</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="top" name="topTr" offset="1" length="1">
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />		
									</td>
								</logic:iterate>
								<logic:iterate id="top" name="topTr" offset="2">
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />	
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<logic:equal value="yes" name="writeAble">
								<tr onclick="rowMore(this)" style="cursor:hand"
									ondblclick="showTopWin('/xgxt/dtjs_zjcm.do?method=swclShh&db=db&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,600,480)">	
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>
										<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
						</logic:iterate>
					</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=dtjszjcmForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
				</fieldset>						
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2"
							onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=swclShq',600,480)"
							style="width:80px">
							申 请
						</button>
						&nbsp;
					<logic:equal value="admin" name="userType">
						<button type="button" class="button2"
							onclick="sh('tg');"
							style="width:80px">
							审核通过
						</button>
						&nbsp;
						<button type="button" class="button2" style="width:80px"
								onclick="sh('wtg');">
							审核不通过
						</button>
						&nbsp;
						<button type="button" class="button2" style="width:80px"
								onclick="swclDel();">
							删 除
						</button>
						&nbsp;
						<button type="button" class="button2" onclick="impAndChkData()"
							style="width:80px">
							导入数据
						</button>
						&nbsp;
						<button type="button" class="button2" onclick="dataExport()"
							style="width:80px">
							导出数据
						</button>
					</logic:equal>		
					</div>
				<logic:present name="delSwcl">
				<script>
					alert(''+document.getElementById('delSwcl').value);
				</script>
				</logic:present>
				</logic:equal>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>