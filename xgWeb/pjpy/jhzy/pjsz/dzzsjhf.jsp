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
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<base target="_self">
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
<body onload="initSetFdyZy();xyDisabled('xy');"> 
<script language="javascript">
			function dataExport() {
	document.forms[0].action = "/xgxt/expData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
<html:form action="/jhzy_pjpysz" method="post">
<div class="title"> 
  <div class="title_img" id="title_m"> 当前所在位置：思政队伍 - 信息维护 - 党总支部书记划分 </div> 
</div> 
<fieldset> 
<legend>班主任编班</legend>
<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>"> 
<input type="hidden" id="method" name="method" value="fdybb"> 
<input type="hidden" id="realTable" name="realTable" value="dzzbsjhfb"> 
<input type="hidden" id="tableName" name="tableName" value="view_dzzbsjhf"> 
<table width="100%" class="tbstyle">
  <thead> 
    <tr> 
      <td colspan="6">年级:
        <html:select property="nj" onchange="refreshForm('jhzy_szdw_dzzsjhf.do')"> 
          <option value=""></option>
          <html:options collection="njList" property="nj" labelProperty="nj"/> 
        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
        <html:select property="xydm" styleId="xy" onchange="refreshForm('jhzy_szdw_dzzsjhf.do')"> 
          <option value=""></option>
          <html:options collection="xyList" property="xydm" labelProperty="xymc"/> 
        </html:select>
         <br/>
       辅导员所属部门：
        <html:select property="bmdm" onchange="refreshForm('jhzy_szdw_dzzsjhf.do')"> 
          <option value=""></option>
          <html:options collection="bmList" property="bmdm" labelProperty="bmmc"/> 
        </html:select></td> 
    </tr> 
  </thead> 
    <tr>
      <td width="30" rowspan="4" align="center" valign="middle"><p>党</p>
      <p>总</p>
      <p>支</p>
      <p>部</p>
      <p>书</p>
      <p>记</p>
      </td>
      <td width="40%" rowspan="4">
      <html:select property="fdyxm" size="10" styleId="fdyxmList" style="width:100%" ondblclick="getDzbsjInfo()"> 
        <html:options collection="fdyList" property="zgh" labelProperty="xm"/> 
      </html:select></td>
      <td  height="30" style="text-align: center;" >职工号：</td>
    <td width="100"><bean:write  name="fdyInfo" property="zgh"/><html:hidden name="fdyInfo" property="zgh" styleId="zgh"/></td>
      <td  align="center" class="tbstyle">所属部门：</td>
      <td nowrap><bean:write name="fdyInfo" property="bmmc"/> </td> 
    </tr>
    <tr>
      <td align="center" height="30" >姓名：</td>
      <td><bean:write name="fdyInfo" property="xm"/> </td> 
      <td align="center" class="tbstyle">职务：</td>
      <td><bean:write name="fdyInfo" property="zwmc"/> </td> 
    </tr>
    <tr>
      <td align="center" height="30" >性别：</td>
      <td><bean:write name="fdyInfo" property="xb"/> </td> 
      <td align="center" class="tbstyle">联系电话：</td>
      <td><bean:write name="fdyInfo" property="lxdh"/> </td> 
    </tr>
    <tr>
      <td align="center" height="75" valign="top" nowrap >主要职责：</td>
      <td colspan="3" valign="top"><bean:write name="fdyInfo" property="zyzz"/></td> 
    </tr>
    <tr>
      <td align="center" valign="middle"><p>专</p>
      <p>业</p></td>
      <td >
      <html:select name="fdyInfo" property="zymc" styleId="zyFlist" ondblclick="addFdyZy()" size="13" style="width:100% "> 
        <html:options collection="zyList" property="zydm" labelProperty="zymc"/> 
      </html:select></td>
      <td align="middle" nowrap>
    <button type="button" class="button2" style="width:30%" id="addFdyZyB" onclick="addFdyZy()"> &gt;&nbsp;&gt; </button><br><br><br>
    <button type="button" class="button2" style="width:30%" id="delFdyZyB" onclick="delFdyZy()"> &lt;&nbsp;&lt; </button></td>
      <td colspan="3">负责专业：<br>
      <html:select name="fdyInfo" property="zylist" size="12" ondblclick="delFdyZy()" styleId="zyTlist" style="width:100% "> 
        <html:options collection="fdyZyList" property="zydm" labelProperty="zymc"/> 
      </html:select></td>
    </tr>
</table> 
</fieldset> 

<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%"> 
  <button type="button" class="button2" onclick="saveBzrZy()"> 保 存 </button> 
 &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" class="button2" onclick="refreshForm('jhzy_szdw_dzzsjhf.do')"> 撤 销 </button> 
  &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" class="button2" onclick="impAndChkData();" style="width:80px">导入数据</button>
  &nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="button2" onclick="dataExport()" style="width:80px">
										导出数据
									</button>
</div> 

  <logic:present name="ok" scope="request"> 
    <logic:equal value="ok" name="ok" scope="request"> 
      <script>alert("保存成功！");</script> 
  </logic:equal> 
  <logic:equal value="no" name="ok" scope="request"> 
    <script>alert("保存失败，请重试！");</script> 
  </logic:equal> 
</logic:present> 
<script language="javascript" src="js/bottomButton.js"></script>
</html:form> 
</body>
</html>
