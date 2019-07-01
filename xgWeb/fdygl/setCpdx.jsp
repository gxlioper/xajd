<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
</head>
<body onload="xyDisabled('xy')"> 
<html:form action="/setCpdx" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 设置参评对象 </a>
	</p>
</div>
<div class="toolbox">
	 <div class="buttonbox">
	 	<logic:equal value="yes" name="writeAble" scope="request"> 
	    <ul>
		  <li> <a href="#" onclick="refreshForm('/xgxt/setCpdx.do?act=all')" class="btn_shtg"> 全部参评 </a> </li>
	      <li> <a href="#" onclick="refreshForm('/xgxt/setCpdx.do?act=none')" class="btn_shbtg"> 全部不参评 </a> </li>
	    </ul>
	   	</logic:equal>
	 </div>	
 
   <input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
   <div class="searchtab">
	<table width="100%" border="0">
	<thead>
		<bean:write name="fdyglForm" property="xn" />学年 (<bean:write name="fdyglForm" property="nd" />年度)， 第<bean:write name="fdyglForm" property="xq" />学期 思政考核参评名单设定 
	</thead>
 	<tbody>
    <tr> 
      <th> <bean:message key="lable.xsgzyxpzxy" /></th>
      <td colspan="5">
        <html:select property="xydm" style="width:230px" styleId="xy" onchange="refreshForm('setCpdx.do')"> 
          <html:option value=""></html:option> 
          <html:options collection="bmList" property="xydm" labelProperty="xymc" /> 
        </html:select> </td> 
    </tr> 
  </tbody>
</table> 
</div>
</div>
<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	 查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：<bean:write name="rsNum" /> ; 双击改变是否参评。
			 		 </logic:notEmpty>
			    </span>
			    </h3>
  <table summary="" class="dateline" align="" width="100%">  
  <thead> 
    <tr align="center" style="cursor:hand"> 
      <td onclick="tableSort(this)">部门</td> 
      <td onclick="tableSort(this)">职工号</td> 
      <td onclick="tableSort(this)">姓名</td> 
      <td onclick="tableSort(this)">性别</td> 
      <td onclick="tableSort(this)">职务</td> 
      <td onclick="tableSort(this)">联系电话</td> 
      <td onclick="tableSort(this)">是否参评</td> 
    </tr> 
  </thead> 
  <logic:present name="rs"> 
	<logic:iterate id="s" name="rs"> 
	<tr onclick="rowOnClick(this)" ondblclick="refreshForm('/xgxt/setCpdx.do?act='+curr_row.getElementsByTagName('input')[0].value)"
					  style="cursor:hand;color:
	<logic:equal name="s" property="color" value="参评"> 
	  blue
	</logic:equal> 
	<logic:equal name="s" property="color" value="不参评"> 
	   <bean:write name = "bcpColor" />
	</logic:equal>"> 
	  <td><bean:write name="s" property="bmmc" /></td> 
	  <td><input type ="hidden" value="<bean:write name="s" property="zgh" />" /><bean:write name="s" property="zgh" /></td> 
	  <td><bean:write name="s" property="xm" /></td> 
	  <td><bean:write name="s" property="xb" /></td> 
	  <td><bean:write name="s" property="zwmc" /></td> 
	  <td><bean:write name="s" property="lxdh" /></td> 
	  <td><bean:write name="s" property="color" /></td> 
	 </tr> 
	 </logic:iterate> 
  </logic:present> 
</table> 
</div>

   
</html:form> 
 <script type="text/javascript" src="js/bottomButton.js"></script> 
 
  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("设置成功！");</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("设置失败，请重试！");</script>
    </logic:equal>
  </logic:present>
  
</body>
</html>
