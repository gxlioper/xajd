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
<body onunload="window.dialogArguments.refreshForm('setPjConf.do')"> 
<html:form action="/setPfbz" > 
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 评分标准设置</a>
	</p>
</div>

<div class="toolbox">
	 <!-- 按钮 -->
	 <logic:equal value="yes" name="writeAble" scope="request"> 
	 <div class="buttonbox">
	    <ul>
		<li> <a href="#" onclick="showTopWin('setPfbz.do?act=add',400,300)" class="btn_zj"> 增加 </a> </li>
	    <li> <a href="#" onclick="if(chkCurrRow()){showTopWin('setPfbz.do?act=modi&bzid='+curr_row.cells[0].innerText+'&pfxm='+curr_row.cells[2].innerText,400,220);}" class="btn_xg"> 修改 </a> </li>
		<li> <a href="#" onclick="if(chkCurrRow() && confirm('确定要删除选中的记录吗？')){refreshForm('setPfbz.do?act=del&bzid='+curr_row.cells[0].innerText+'&pfxm='+curr_row.cells[2].innerText);return true;}return false;" class="btn_sc"> 删除 </a> </li>
	    </ul>
	 </div>
	</logic:equal>
	<div class="searchtab">
		<table width="100%" border="0">
		  <thead> 
		    <tr> 
		      <td align="left" nowrap> 标准
		        <html:select property="bzbh" style="width:230px" styleId="bzbh" onchange="refreshForm('setPfbz.do')"> 
		          <html:option value=""></html:option> 
		          <html:options collection="bzList" property="bzbh" labelProperty="bzmc" /> 
		        </html:select> </td> 
		    </tr> 
		  </thead> 
	</table> 
	</div>
</div>
<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rslist">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rslist">
			 		 	记录数：<bean:write name="rsNum"/>;&nbsp;&nbsp;双击一行可以查看详细信息；单击表头可以排序
			 		 </logic:notEmpty>
			    </span>
			    </h3>
 <logic:present name="rslist" scope="request"> 
  <table summary="" class="dateline" align="" width="100%">
  <thead> 
    <tr style="cursor:hand"> 
      <td nowrap align="center" onclick="tableSort(this)">编号</td> 
      <td nowrap align="center" onclick="tableSort(this)">名称</td> 
      <td nowrap align="center" onclick="tableSort(this)">评分项目</td> 
      <td nowrap align="center" onclick="tableSort(this)">权重</td> 
      <td nowrap align="center" onclick="tableSort(this)">显示顺序</td> 
    </tr> 
  </thead> 
  <tbody>
    <logic:iterate id="rs" name="rslist" scope="request"> 
    <tr style="cursor:hand" onclick="rowOnClick(this)" ondblclick="showTopWin('setPfbz.do?act=modi&operation=view&bzid='+curr_row.cells[0].innerText+'&pfxm='+curr_row.cells[2].innerText,400,220);"> 
      <td nowrap align="center"><bean:write name="rs" property="bzbh" /></td> 
      <td nowrap align="left"><bean:write name="rs" property="bzmc"/></td> 
      <td nowrap align="left"><bean:write name="rs" property="pfbz" /></td> 
      <td nowrap align="center"><bean:write name="rs" property="qz" /></td> 
      <td nowrap align="center"><bean:write name="rs" property="xssx" /></td> 
    </tr> 
    </logic:iterate> 
   </tbody>
</table> 
</logic:present> 
<br>

</html:form> 
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