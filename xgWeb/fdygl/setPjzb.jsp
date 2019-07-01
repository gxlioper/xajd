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
<body> 
<html:form action="/setPjzb" > 
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 考核参数设置</a>
	</p>
</div>
<div class="toolbox">
	 <div class="buttonbox">
	 	<logic:equal value="yes" name="writeAble" scope="request"> 
	    <ul>
		<li> <a href="#" onclick="showTopWin('setPjzb.do?act=add',620,520)" class="btn_zj"> 增加 </a> </li>
	    <li> <a href="#" onclick="if(chkCurrRow()){showTopWin('setPjzb.do?act=modi&id='+curr_row.cells[0].innerText,620,520);}" class="btn_xg"> 修改 </a> </li>
		<li> <a href="#" onclick="if(chkCurrRow() && confirm('确定要删除选中的记录吗？')){BatAlert.showTips('数据处理中，请稍候！');refreshForm('setPjzb.do?act=del&pjh='+curr_row.cells[0].innerText);return true;}return false;" class="btn_sc"> 删除 </a> </li>
	    </ul>
	    </logic:equal>
	 </div>
	 
	<div class="searchtab">
	  <table width="100%" border="0">
	  <tbody> 
	    <tr> 
	      <th align="left" width="10%">
	      面向对象
	     </th>
	     
	     <td colspan="5">
	      <html:select property="mxdx" name="fdyglForm" style="width:100px" onchange="refreshForm('setPjzb.do')">
	      	<option value=""> </option>
	        <html:options collection="mxdxList" property="qtdm" labelProperty="qtmc"/>
    	  </html:select>
        </td> 
	    </tr> 
	  </tbody> 
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
 		 	 <font color="blue">记录数：<bean:write name="rsNum"/>;双击一行可以查看详细信息；单击表头可以排序</font> 
 		 </logic:notEmpty>
    </span>
    </h3>
<logic:notEmpty name="rslist">
 <table summary="" class="dateline" align="" width="100%">
  <thead> 
    <tr style="cursor:hand"> 
      <td  width="40" align="center" onclick="tableSort(this)">评价号</td> 
      <td  width="60" onclick="tableSort(this)" align="center">评价指标</td>
      <logic:equal name="xxdm" value="10290" scope="session">
       <td  align="center"  onclick="tableSort(this)"width="150" >二级指标</td>
      </logic:equal> 
      <td  align="center" onclick="tableSort(this)" width="450">评价内容</td> 
      <td  width="30" align="center" onclick="tableSort(this)">对应分值</td> 
      <td  width="60" align="center" onclick="tableSort(this)">面向对象</td> 
    </tr> 
  </thead> 
  <tbody>
 
    <logic:iterate id="rs" name="rslist" scope="request"> 
    <tr style="cursor:hand" onclick="rowOnClick(this)" ondblclick="showTopWin('setPjzb.do?act=modi&operation=view&id='+curr_row.cells[0].innerText,620,520);"> 
      <td  align="center"><bean:write name="rs" property="xh" /></td> 
       <td  align="center"><bean:write name="rs" property="stlbmc"/></td> 
       <logic:equal name="xxdm" value="10290" scope="session">
        <td nowrap align="center"><bean:write name="rs" property="scojzb"/></td> 
      </logic:equal> 
      <td  align="left" width="450"><bean:write name="rs" property="pjzb"/></td> 
      <td  align="center"><bean:write name="rs" property="fz" /></td> 
      <td  align="center"><bean:write name="rs" property="mxdx" /></td> 
    </tr> 
    </logic:iterate> 
 	</tbody>
</table>
</logic:notEmpty> 

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