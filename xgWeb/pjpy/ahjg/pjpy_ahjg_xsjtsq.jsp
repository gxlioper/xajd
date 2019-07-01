<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  	<head>
		<title><bean:message key="lable.title" /></title>
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
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
  <script language="javascript" src="js/function.js"></script>
  <script language="javascript" src="js/pjpyFunction.js"></script>
  <script language="javascript" src="js/sharedFunction.js"></script>
  <script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>
  <script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
  <body style="font-size:14px">
    <html:form action="/pjpyahjgwh" method="post">
    <input type="hidden" id="titName" name="titName" value=""/>
    <input type="hidden" id="defaultTitle" value="${defaultTitle }"/>
    	<div class="xxk">
				<logic:iterate id="title" name="titleList">
				 <ul>
					<li id="<bean:write name="title" property="en" />l"
						class="xxk_off_l"></li>
					<li id="<bean:write name="title" property="en" />m"
						onclick="ahjg_changePage(this)" class="xxk_off_m">
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
  	   		<div id="mainTitle"></div>
  	   </div>
  	   <div id="items" name="items" style="display:none; position: absolute;background-color: #AFEEEE; " >
  	   <input type="hidden" name="njV" id="njV">
  	   <input type="hidden" name="xyV" id="xyV">
  	   <input type="hidden" name="zyV" id="zyV">
  	   <input type="hidden" name="bjV" id="bjV">
  	   		<table class="tbstyle">
  	   		  <tr>
  	   		  <td>年级</td>
  	   		  <td>
  	   		   <html:select property="nj" onchange="initZyList();initBjList();" styleId="nj">
  	   		   	 <html:option value=""></html:option>
  	   		   	 <html:options collection="njList" property="nj" labelProperty="nj"/>
  	   		   </html:select>
  	   		  </td>
  	   		  </tr>
  	   		  <tr>
  	   		  <td><bean:message key="lable.xsgzyxpzxy" /></td>
  	   		  <td>
  	   		  <html:select property="xydm" onchange="initZyList();initBjList();" styleId="xy">
  	   		   	 <html:option value=""></html:option>
  	   		   	 <html:options collection="xyList" property="xydm" labelProperty="xymc"/>
  	   		   </html:select>
  	   		  </td>
  	   		  </tr>
  	   		  <tr>
  	   		  <td>专业</td>
  	   		  <td>
  	   		   <html:select property="zydm" onchange="initBjList();" styleId="zy">
  	   		   	 <html:option value=""></html:option>
  	   		   	 <html:options collection="zyList" property="zydm" labelProperty="zymc"/>
  	   		   </html:select>
  	   		  </td>
  	   		  </tr>
  	   		  <tr>
  	   		  <td>班级</td>
  	   		  <td>
  	   		    <html:select property="bj"  styleId="bj">
  	   		   	 <html:option value=""></html:option>
  	   		   	 <html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
  	   		   </html:select>
  	   		  </td>
  	   		  </tr>
  	   		  <tr>
  	   		  <td colspan=2 align=center>
  	   		     <button class="button2" onclick="hideItems()">确 定</button>
  	   		  </td>
  	   		  </tr>
  	   		</table>
  	   </div>
  	   <div id="xjjtinfo" style="display: none;">
	     <table width="100%"  class="tbstyle">
	     	  <tr>
	     	  	<td colspan="2" scope="col" width="26%"><div align="center">学年</div></td>
			    <td width="26%" scope="col">
			    <html:text property="xn" readonly="true" value="${xn}" styleId="xn"/>
			    </td>
			    <td width="26%" scope="col"><div align="center">学期</div></td>
			    <td width="24%" scope="col">
			    <html:text property="xq" readonly="true" styleId="xq" value="${xq}"/>
			    </td>
	     	  </tr>
			  <tr>
			    <td colspan="2" scope="col" width="26%"><div align="center"><font color="red">*</font>班级</div></td>
			    <td width="26%" scope="col">
			    <html:text property="bjdm" readonly="true" onclick="showItems()" styleId="bjdm"/>
			    </td>
			    <td width="26%" scope="col"><div align="center">班长</div></td>
			    <td width="24%" scope="col">
			    <html:text property="bzxm" />
			    </td>
			  </tr>
			  <tr>
			    <td colspan="2" scope="row"><div align="center">学生人数</div></td>
			    <td>
			    <html:text property="xsrs" onblur="onlyNum(this)"/>
			    </td>
			    <td><div align="center">班主任</div></td>
			    <td>
			    <html:text property="bzr" />
			    </td>
			  </tr>
			  <tr>
			    <td width="13%" height="185" scope="row"><p align="center">主</p>
			    <p align="center">要</p>
			    <p align="center">事</p>
			    <p align="center">迹</p></td>
			    <td colspan="4" scope="row">
			    <html:textarea property="zysj" rows="10" cols="100"></html:textarea>
			    </td>
			  </tr>
		</table>
		</div>
		<!--  <div id="xjgrinfo" style="display: none;">这个功能公寓管理模块里面有啊</div> -->
		<div class="buttontool">
		   <button class="button2" onclick="ckdataempty('bjdm','pjpy_ahjg_xjbjsq.do')">
		      提交申请
		   </button>
		</div>
    </html:form>
  </body>
  <script type="text/javascript">
    window.onload= function(){
       //alert(document.getElementById('defaultTitle').value);
       document.getElementById('xjjtinfo').style.display = 'block';
       ahjg_changePage(document.getElementById(document.getElementById('defaultTitle').value+'m'));
    }
    function ckdataempty(val,url){
    	if (document.getElementById(val).value == ''){
    		alert('带*的内容为必填项！');
    	}else{
    		document.forms[0].action = url;
    		document.forms[0].submit();
    	}
    }
  </script>
  <logic:present name="result" >
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
    <logic:equal value="haved" name="result">
    <script type="text/javascript">
    alert("该班级申请数据已存在，请重新核对！");
  </script>
    </logic:equal>
  </logic:present>
</html:html>
