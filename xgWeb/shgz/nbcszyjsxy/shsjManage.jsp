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
	<script language="javascript">
</script>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/nbcsShgzgl" method="post">
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable" scope = "request"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope = "request"/>" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope = "session"/>" />
				<input type="hidden" name="bjV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="pkVStr"/>
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：社会工作 - 信息维护 - 社会实践
					</div>
				</div>
				<fieldset>
					<legend>
						条 件
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td>
									学年：
									<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:100px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									
									&nbsp;&nbsp;学号：
									<html:text property="xh" />
									&nbsp;&nbsp;姓名：
									<html:text property="xm" />
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/nbcsShgzgl.do?method=shsjManage&go=go')">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									年级：
									<html:select property="nj"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" styleId="xy"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" styleId="zy"
										onchange="initBjList();">
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
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="selall" id="selall" onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" >
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="dataView()">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="checkVal" id="checkVal"
												value="<bean:write name="v"/>">
										</logic:iterate>
										
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="result">
				</logic:notEmpty>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble">
						<button class="button2" onclick="dataAdd()" style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataModi()"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataDel()" style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;							
							<button class="button2" onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
					<button class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
				</div>

			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<script type="text/javascript">
	       function dataAdd(){
	            var url = "/xgxt/nbcsShgzgl.do?method=shsjAdd&act=add";
	            showTopWin(url,"600","550");
	       }
	       function dataModi(){
	           if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	           } else {
	            var url = "/xgxt/nbcsShgzgl.do?method=shsjAdd&act=modi";
	                url +="&pkValue=";
	                url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	                showTopWin(url,"600","550");
	           }
	       }
	       function dataView(){	          
	            var url = "/xgxt/nbcsShgzgl.do?method=shsjAdd&act=view";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            showTopWin(url,"600","550");	          
	       }
	       function dataDel(){
	             var url = "/xgxt/nbcsShgzgl.do?method=shsjDel&go=go";
	             var RowsStr="";		  
		         for (i=0; i<document.getElementsByName("checkVal").length; i++){
	    	        if(document.getElementsByName("checkVal")[i].checked){
	    		       RowsStr+=document.getElementsByName("checkVal")[i].value+"!!";
	    	        }	    	  
		         }
		         document.forms[0].pkVStr.value = RowsStr;
		         if (RowsStr==""){
			        alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			        return false;
		         }
		        refreshForm(url);
	       }	       
	</script>
	</body>
</html>
