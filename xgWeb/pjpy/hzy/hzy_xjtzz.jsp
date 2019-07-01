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
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>

<script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>


<body style="font-size:14px" onload="xyDisabled('xy');">
	<html:form action="/pjpy_hzy_xjbjandwmbj" method="post"
		enctype="multipart/form-data">
		<input type="hidden" id="titName" name="titName" value="" />
		<input type="hidden" id="defaultTitle"
			value="<bean:write name="defaultTitle" scope="request" />" />
		<input type="hidden" id="xxmc" name="xxmc"
			value="<bean:write name="xxmc" scope="session"/>" />
		<div class="xxk">
			<logic:iterate id="title" name="titleList">
				<ul>
					<li id="<bean:write name="title" property="en" />l"
						class="xxk_off_l"></li>
					<li id="<bean:write name="title" property="en" />m"
						onclick="hzy_changePage(this)" class="xxk_off_m">
						&nbsp;
						<bean:write name="title" property="cn" />
						&nbsp;
					</li>
					<li id="<bean:write name="title" property="en" />r"
						class="xxk_off_r"></li>
				</ul>
			</logic:iterate>
		</div>

		<div align="center" style="font: bold;font-size: 18px">
			<div id="mainTitle">
				先进团总支推荐
			</div>
		</div>

		<table width="100%" class="tbstyle">
			<tr>
				<td align="right" nowrap>
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<html:select property="xn" style="width:120px"
						style="background-color:#FFFFFF;">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
				<td align="right" nowrap>
					<font color="red">*</font>学期：
				</td>
				<td align="left">
					<html:select property="xq" style="width:90px"
						style="background-color:#FFFFFF;">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td scope="col" width="15%">
					<div align="right">
						<font color="red">*</font>团组织名称：
					</div>
				</td>
				<td align="left" width="40%">
					<html:text property="tzzmc" styleId="tzzmc" maxlength="150" />

				</td>
				<td align="right" width="10%">
					<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />:
				</td>
				<td align="left">
					<html:select property="xydm" style="width:90px"
						style="background-color:#FFFFFF;">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td scope="col" width="15%">
					<div align="right">
						团支部数：
					</div>
				</td>
				<td align="left" width="40%">
					<html:text property="tzbs" styleId="tzbs"
						onkeypress="return onlyNum(this,5)" />
				</td>


				<td align="right" width="10%">
					书记姓名：
				</td>
				<td align="left">
					<html:text property="tzzsj" styleId="tzzsj" maxlength="25" />
				</td>
			</tr>
			<tr>

				<td align="right" width="10%">
					团员数：
				</td>
				<td align="left">
					<html:text property="tys" styleId="tys"
						onkeypress="return onlyNum(this,5)" />
				</td>
				<td align="right" width="10%">

				</td>
				<td align="left">

				</td>
			</tr>
			<tr>
				<td scope="col" width="15%" scope="row">
					<div align="center">
						主
						<br />
						要
						<br />
						事
						<br />
						迹
						<br />
						<font color="red">（限500字）</font>
					</div>
				</td>
				<td align="left" colspan='4'>
					<html:textarea property="tzzzysj" rows="7" cols="100"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					材料或附件：
				</td>
				<td align="left" colspan="4">
					<html:file property="uploadFile" style="width:60%"></html:file>
				</td>
			</tr>
		</table>
		<div class="buttontool">
			<button class="button2" id="buttonSave" onclick="xjtzzSave()">
				提交申请
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="printTable()">
				打印报表
			</button>
		</div>
		<logic:notEmpty name="rswj">
			<fieldset>
				<legend>
					&nbsp;&nbsp;材 料 或 附 件 &nbsp;&nbsp;
				</legend>
				<table border="0" id="rsTable" width="100%" class="tbstyle">
					<thead>
						<tr>
							<td></td>
							<td>
								附件名
							</td>
							<td>
								申请时间
							</td>
						</tr>
					</thead>
					<logic:iterate id="list" name="rswj">
						<tr onmouseover="rowOnClick(this)">
							<td>
								<a
									href="pjpy_hzy_xjbjandwmbj.do?method=DownLoadFile&downType=xjtzz&param=<bean:write name="list" property="pk" />"
									target=_black>下载 </a>
							</td>
							<td>
								<bean:write name="list" property="wjm" />
							</td>
							<td>
								<bean:write name="list" property="sqsj" />
							</td>
							<%--       							<td>--%>
							<%--        							<a href="#" onclick="if(confirm('确实要删除当前文件吗？'))location='fileDel.do?wjh=<bean:write name="list" property="wjh"/>';" >删除</a> </td>--%>
						</tr>
					</logic:iterate>
				</table>
			</fieldset>
		</logic:notEmpty>

	</html:form>
</body>
<script type="text/javascript">
    window.onload= function(){     
    	hzy_changePage(document.getElementById(document.getElementById('defaultTitle').value+'m'),"true");
    }
    function xjtzzSave() {
        var xn = "";
        var xq = "";
        var tzzmc = "";
        var zysj = "";
        var xydm = "";
        if($("xn")){
           xn = $("xn").value;
        }
        if($("xq")){
           xq = $("xq").value;
        }
        if($("tzzmc")){
           tzzmc = $("tzzmc").value;
        }
        if($("zysj")){
           zysj = $("zysj").value;
        }
        if($("xydm")){
          xydm = $("xydm").value;
        }        
        if(xn==""){
           alert("学年不能为空！");
           return false;
        }
        if(xq==""){
           alert("学期不能为空！");
           return false;
        }
        if(tzzmc==""){
           alert("团组织名称不能为空！");
           return false;
        }
        if(xydm==""){
           alert("<bean:message key="lable.xsgzyxpzxy" />不能为空！");
           return false;
        }       
        if(zysj.length>500){
           alert("主要事迹字数过大，限500字内！");
           return false;
        }       
         refreshForm('/xgxt/pjpy_hzy_xjbjandwmbj.do?method=xjbjAndWmbjSq&doType=save');
         $("buttonSave").disabled=true;
         BatAlert.showTips('正在操作，请等待...');
        
    }
function printTable(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	var titName = document.getElementById("titName");
	requestPath += "&titName="+titName.value;		
	document.forms[0].action = requestPath;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";

}    
  </script>
<logic:present name="result">
	<logic:equal value="true" name="result">
		<script type="text/javascript">
    alert("保存成功！");
  </script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
    alert("保存失败！");
  </script>
	</logic:equal>
</logic:present>
</html:html>
