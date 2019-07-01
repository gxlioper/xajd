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
<base target="_self">	
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>
<body style="font-size:14px" onload="xyDisabled('xy');">
	<html:form action="/pjpy_xjch_modi" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pkValue" id="pkValue" value="<bean:write name="pkValue" scope="request" />"/>
		<input type="hidden"  name="xjType" id="xjType" value="<bean:write name="xjType" scope="request" />" />	
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />			
		<div align="center" style="font: bold;font-size: 18px">
			<div id="mainTitle">先进团支部推荐</div>
		</div>
		<table width="100%" class="tbstyle">
			<tr>
				<td align="right" width="30%" >
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<html:select name="rs" property="xn" style="width:120px" disabled="true"
						style="background-color:#FFFFFF;">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
				<td align="right" width="15%">
					<font color="red">*</font>学期：
				</td>
				<td align="left">
					<html:select name="rs"  property="xq" style="width:90px" disabled="true"
						style="background-color:#FFFFFF;">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td  >
					<div align="right">
						<font color="red">*</font>团支部名称：
					</div>
				</td>
				<td align="left" >
					<html:text name="rs" property="tzbmc" styleId="tzbmc" maxlength="150"/>

				</td>
				<td align="right" width="10%">
                   <font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<html:select name="rs" property="xydm" style="width:90px"
						style="background-color:#FFFFFF;">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>				

			</tr>
			<tr>
				<td align="right">
					团支部书记：
				</td>
				<td align="left">
					<html:text name="rs" property="tzbsj" styleId="tzbsj"
						maxlength="25" />
				</td>
				<td>
					<div align="right">
						团员数：
					</div>
				</td>
				<td align="left">
					<html:text name="rs" property="tys" styleId="tys"
						onkeypress="return onlyNum(this,5)" />
				</td>
			</tr>
			<tr>
				<td align="right">
					非团员数：
				</td>
				<td align="left">
					<html:text name="rs" property="ftys" styleId="ftys"
						onkeypress="return onlyNum(this,5)" />
				</td>
			</tr>
			<tr>
				<td scope="row">
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
					<html:textarea name="rs" property="zysj" rows="7" cols="100"></html:textarea>
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
			<logic:notEmpty name="rswj"> 
                <fieldset>               			    
			      <legend> &nbsp;&nbsp;材 料 或 附 件 &nbsp;&nbsp; </legend> 				       			
  					<table border="0" id="rsTable" width="100%" class="tbstyle"> 
    					<thead>
    					<tr>
    					     <td ></td> 
      						<td >附件名</td>
      						<td >申请时间</td>  
    					</tr>
    					</thead>
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="pjpy_hzy_xjbjandwmbj.do?method=DownLoadFile&downType=xjtzb&param=<bean:write name="list" property="pk" />" target=_black>下载   							
      							</a> </td> 
      							<td > <bean:write name="list" property="wjm" /> </td> 
      							<td > <bean:write name="list" property="sqsj" /> </td> 
   						</tr> 
    					</logic:iterate> 
  					</table> 
			</fieldset>
			</logic:notEmpty> 
		<div class="buttontool">
		    <logic:notEqual value="view" name="act">
			<button class="button2" id="buttonSave"
				onclick="xjtzbModiSave()">
				保 存
			</button>
			</logic:notEqual>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="printTable()">
				打印报表
			</button>
		</div>		
	</html:form>
</body>
<script type="text/javascript">
    function xjtzbModiSave() {
        var xn = "";
        var xq = "";
        var tzbmc = "";
        var zysj = "";
        if($("xn")){
           xn = $("xn").value;
        }
        if($("xq")){
           xq = $("xq").value;
        }
        if($("tzbmc")){
           tzbmc = $("tzbmc").value;
        }
        if($("zysj")){
           zysj = $("zysj").value;
        }
        if(xn==""){
           alert("学年不能为空！");
           return false;
        }
        if(xq==""){
           alert("学期不能为空！");
           return false;
        }
        if(tzbmc==""){
           alert("团支部不能为空！");
           return false;
        }
        if(zysj.length>500){
           alert("主要事迹字数过大，限500字内！");
           return false;
        }       
         refreshForm('pjpy_xjch_modi.do?method=xjchModi&doType=save');
         $("buttonSave").disabled=true;
         //BatAlert.showTips('正在操作，请等待...');       
    }
function printTable(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	var pkValue = $("pkValue").value;
	requestPath += "&titName=xjtzb";	
	requestPath += "&pkValue="+pkValue;	
	document.forms[0].action = requestPath;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}     
  </script>
	<logic:equal value="true" name="done">
		<script type="text/javascript">
    alert("保存成功！");
    window.dialogArguments.document.all("search_go").click();
    Close();
  </script>
	</logic:equal>
	<logic:equal value="false" name="done">
		<script type="text/javascript">
    alert("保存失败！");
  </script>
	</logic:equal>
</html:html>
