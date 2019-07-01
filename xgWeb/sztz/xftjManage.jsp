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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	function toSearch(){
	   if($("nj")){
	      if($("nj").value==""){
	         alert("请选择年级！");
	         return false;
	      }
	   }
	   if($("xydm")){
	      if($("xydm").value==""){
	         alert("请选择<bean:message key="lable.xsgzyxpzxy" />！");
	         return false;
	      }
	   }
	   document.forms[0].go.value='go';
	   refreshForm('/xgxt/sztzXfTjManage.do');
	   $("search_go").disabled=true;
	}
	function toPrint(){
	   var url = "/xgxt/sztzXfTjManage.do?doPrint=print&go=go";
	   var nj="";
	   var xydm="";
	   var zydm="";
	   var bjdm="";	   
	   if($("nj")){
	      if($("nj").value==""){
	         alert("请选择年级！");
	         return false;
	      }
	   }
	   if($("xydm")){
	      if($("xydm").value==""){
	         alert("请选择<bean:message key="lable.xsgzyxpzxy" />！");
	         return false;
	      }
	   }
      if($("nj"))nj=$("nj").value;
      if($("zydm"))zydm=$("zydm").value;
      if($("xydm"))xydm=$("xydm").value;
      if($("bjdm"))bjdm=$("bjdm").value;
	  url +="&nj="+nj;
	  url +="&xydm="+xydm;
	  url +="&zydm="+zydm;
	  url +="&bjdm="+bjdm;
      document.forms[0].go.value='go';
      showOpenWindow(url,900,850);	   	
	}
	</script>
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
		<center>
			
			<html:form action="/sztzXfTjManage" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：素质拓展 - 统计分析 - 拓展学分统计 - 查询
					</div>
				</div>
				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" name="zyV" id="zyV" />
			        <input type="hidden" name="bjV" id="bjV" />
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
										onchange="initZyList();initBjList()">
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
									&nbsp;&nbsp;班级：
									<html:select property="bjdm"
										style="width:120px;background-color:#FFFFFF" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<td width="10"  align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="toSearch()">
										查询
									</button>
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
							<font color="blue">提示：单击表头可以排序;每科目达标分数可在路径 <font color="red" >"系统维护 - 代码维护 - 素质拓展"</font>下，科目设立栏目中进行合格分数设置</font>
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
								<tr onclick="rowOnClick(this)" style="cursor:hand" >
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="4" length="1">
											 <img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="v" />" height="10px"/>
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<%--									<logic:iterate id="v" name="s">--%>
									<%--										<td align="left">--%>
									<%--											<bean:write name="v" />--%>
									<%--										</td>--%>
									<%--									</logic:iterate>--%>

								</tr>
							</logic:iterate>
						</table>
					</fieldset>					
				</logic:notEmpty>
				<br>
				<br>
				<br>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="toPrint()" style="width:80px">
						打印
					</button>				
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
