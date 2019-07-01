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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
<body onload="chkPjPage()"> 
<html:form action="/setCpdx" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置:</em><a><bean:message key="tips.N170201" /> </a>
	</p>
</div>
 
<input type="hidden" id="sfcp" value="${sfcp }"/>
<input type="hidden" name="userType" value="<bean:write name="userType" scope="session"/>">
<input type="hidden" name="xxdm" id = "xxdm" value="<bean:write name="xxdm" scope="session"/>">
<logic:present name="sfcp" scope="request">
  <logic:equal value="no" name="sfcp">
    <br><br><br><br>
    	<p align="center">
			<img src="/xgxt/pictures/xszz/pic_prompt.gif"></img>
			</p>
    <div align="center"><font size="5"><b>您不需要评价！</b></font></div>
  </logic:equal>
  <logic:equal value="out" name="sfcp">
    <br><br><br><br>
    	<p align="center">
			<img src="/xgxt/pictures/xszz/pic_prompt.gif"></img>
			</p>
    <div align="center"><font size="5"><b>评价前的设置不完整！</b></font></div>
  </logic:equal>
  <logic:equal value="time" name="sfcp">
    <br><br><br><br>
    	<p align="center">
			<img src="/xgxt/pictures/xszz/pic_prompt.gif"></img>
			</p>
    <div align="center"><font size="5"><b>评价尚未开放！</b></font></div>
  </logic:equal>
  <logic:equal value="yes" name="sfcp">
	<div class="toolbox">
		<logic:equal value="no" name="sfpj" scope="request">
		<div class="buttonbox" id="btn">
		    <ul>
		      <logic:equal name="xxdm" value="10290" scope="session">
				<logic:present name = "zwpj">
				 <li> <a href="#"onclick="showTopWin('szdwfzjy.do?method=zwpjb&zgh='+document.getElementById('fdy').value,'630','640')" class="btn_xg"> 自我评价表填写 </a> </li>
		  		</logic:present>
		      </logic:equal> 
			  <li> <a href="#" onclick="showTips('保存评价中......');refreshForm('pj.do?act=save')" class="btn_ccg"> 保存 </a> </li>
		      <li> <a href="#" onclick="submitPj()" class="btn_xg"> 提交 </a> </li>
		    </ul>
	 	</div>
		</logic:equal>
		
	<div class="searchtab">
		<table width="100%" border="0">
		<thead>
				<bean:write name="fdyglForm" property="xn" />学年 (<bean:write name="fdyglForm" property="nd" />年度)， 第<bean:write name="fdyglForm" property="xq" />学期 思政考核参评名单设定 
		</thead>
	  <tbody> 
	    <tr> 
	      <td align="left" nowrap>
	      <logic:notEqual value="stu" name="userType" scope="session">
	      	<logic:notEqual value="10878" name="xxdm" scope="session">
				 <bean:message key="lable.xsgzyxpzxy" />	
			</logic:notEqual>
	      	<logic:equal value="10878" name="xxdm" scope="session">
				 部门
			</logic:equal> 
	        <html:select property="xydm" style="width:230px" styleId="xy" onchange="refreshForm('pj.do')"> 
	          <html:option value=""></html:option> 
	          <html:options collection="xyList" property="xydm" labelProperty="xymc" /> 
	        </html:select> &nbsp;&nbsp;&nbsp;&nbsp; 
	        <logic:equal name="xxdm" value="10290" scope="session">
	        	分管年级
	        	<logic:notPresent name = "sjnj">
	        	<html:select property="nj" style="width:80px" styleId="nj" onchange="refreshForm('pj.do')"> 
	          	<html:option value=""></html:option>
	          	<html:option value="no">未分配</html:option>  
	          	<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        	</html:select>
	        	</logic:notPresent>  
	        	<logic:present name = "sjnj">
	        		<input type = "hidden" name = "nj" value = "<bean:write name = "sjnj" />" />
	        		<html:select property="nj" style="width:80px" styleId="nj" onchange="refreshForm('pj.do')" disabled="true"> 
	          		<html:option value=""></html:option> 
	          		<html:option value="no">未分配</html:option>  
	          		<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        		</html:select>
	        	</logic:present> 
	        	&nbsp;&nbsp;&nbsp;&nbsp; 
	        </logic:equal>
	      </logic:notEqual>
	      辅导员
	        <html:select property="zgh" style="width:230px" styleId="fdy" onchange="refreshForm('pj.do')"> 
	          <html:option value=""></html:option> 
	          <html:options collection="fdyList" property="zgh" labelProperty="xm" /> 
	        </html:select> </td> 
	    </tr> 
	  </tbody> 
	</table> 
	</div>
	</div>
		<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	评分&nbsp;&nbsp;
			    	<logic:present name="sfpj" scope="request"> 
						<logic:equal value="yes" name="sfpj" scope="request"> 
							<font color="red">已经提交，不能修改</font>
						</logic:equal>
					</logic:present>
			    </span>
			    </h3>
	 <table summary="" class="dateline" align="" width="100%">
	  <thead> 
	    <tr align="center" style="cursor:hand">
	      <td width="100">评价指标 </td>
	      <logic:equal name="xxdm" value="10290" scope="session">
	      <td >二级指标 </td>
	      </logic:equal>  
	      <td title = "adsad">评分标准 </td> 
	      <td width="40">分值</td> 
	      <td width="110">评分</td> 
	    </tr> 
	  </thead> 
	  <tbody>
	  <logic:present name="pjzbList"> 
		<logic:iterate id="s" name="pjzbList"> 
		<tr onclick="rowOnClick(this)">
			<td align="center"><bean:write name="s" property="stlbmc" /></td>
			<logic:equal name="xxdm" value="10290" scope="session">
	      <td><bean:write name="s" property="scojzb" /></td>
	      </logic:equal> 
		<td title = "<bean:write name="s" property="jtbz" />"><input type ="hidden" name="pfbz" value="<bean:write name="s" property="pjzbxh" />" ><bean:write name="s" property="pjzb" /></td> 
		  <td align="center"><bean:write name="s" property="fz" /></td> 
		  <td> 
	        <html:select name="s" property="pjfs" style="width:100px" onchange="this.blur()"> 
	          <html:option value=""></html:option> 
	          <html:options collection="pfbzList" property="qz" labelProperty="pfbz" /> 
	        </html:select> 
		  </td> 
		 </tr> 
		 </logic:iterate> 
	  </logic:present> 
	  </tbody>
	</table> 
	</div>
	<logic:present name="sfpj" scope="request"> 
	<logic:equal value="yes" name="sfpj" scope="request"> 
	
	<div id="btn" > 
	<logic:equal name="xxdm" value="10290" scope="session">
			  <logic:present name = "zwpj">
			  <button class="button2" onclick="showTopWin('szdwfzjy.do?method=zwpjb&zgh='+document.getElementById('fdy').value,'630','600')">  自我评价表填写 </button>
	  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	  		  </logic:present>
		</logic:equal> 
	  <button class="btn" style="display:none">已经提交，不能修改</button>
	</logic:equal> 
	</logic:present>
  </logic:equal>
</logic:present>
</html:form> 
 <script type="text/javascript" src="js/bottomButton.js"></script> 
 
  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("操作成功！");</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("操作失败，请重试！");</script>
    </logic:equal>
  </logic:present>
  
</body>
</html>
