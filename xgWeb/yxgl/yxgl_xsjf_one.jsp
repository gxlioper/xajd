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
    <base target="_self" />
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/yxglFunction.js"></script>
  <body>
  <logic:present name="dors">
    	<logic:equal value="ok" name="dors">
    		<script>
    		   alert("保存成功！");
    		</script>
    	</logic:equal>
    	<logic:equal value="no" name="dors">
    		<script>
    		   alert("保存失败！");
    		</script>
    	</logic:equal>
    </logic:present>
    <html:form action="yxgl_xsjf_one" method="post">
    <div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：迎新管理 - 学生交费 - 个别修改
		</div>
	</div>
	<table width="100%" class="tbstyle">
		 <tr>
		    <td width="16%" height="20%"><div align="right">考生号:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ksh" styleId="ksh" readonly="true"/></td>
		    <td width="11%" height="20%"><div align="right">学号:</div></td>
		    <td width="20%" height="20%"><html:text name="rs" property="xh" styleId="xh" readonly="true"/></td>
		    <td height="60%" rowspan="3">
		    	<img alt="无法显示" height="100" width="75"  border="1" align="right"
		    		src="<bean:write name="rs" property="picture"/>"></img>
		    </td>
		  </tr>
		  <tr>
		  	<td width="16%" height="20%"><div align="right">姓名:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="xm" styleId="xm" readonly="true"/></td>
		  	<td width="11%" height="20%"><div align="right">省份:</div></td>
		    <td width="20%" height="20%" ><html:text name="rs" property="sfmc" styleId="sfmc" readonly="true"/></td>
		  </tr>
		  <tr>
		  	<td width="11%" height="20%"><div align="right">身份证号:</div></td>
		    <td width="36%" height="20%"><html:text name="rs" property="sfzh" styleId="sfzh" readonly="true"/></td>
		  	<td width="16%" height="20%"><div align="right"></div></td>
		    <td width="20%" height="20%"></td>
		  </tr>
		  <tr>		    
		    <td height="20%"><div align="right"><bean:message key="lable.xsgzyxpzxy" />名称:</div></td>
		    <td height="20%">
		       <html:select name="rs" property="xydm" onchange="refreshForm('yxgl_xybd_one.do');" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="xyList" property="xydm" labelProperty="xymc" />
		       </html:select>
			</td>
			 <td height="20%"><div align="right">专业名称:</div></td>
		    <td height="20%" colspan="2">
				<html:select name="rs" property="zydm" onchange="refreshForm('yxgl_xybd_one.do');" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="zyList" property="zydm" labelProperty="zymc" />
		       </html:select>			
			</td>
		  </tr>
		  <tr>
			<td height="20%"><div align="right">班级名称:</div></td>
		    <td height="20%" >
				<html:select name="rs" property="bjdm" onchange="refreshForm('yxgl_xybd_one.do');" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
		       </html:select>			
			</td>
			<td width="11%" height="20%"><div align="right">收费方式:</div></td>
		    <td height="20%" colspan="2">
				<html:select name="rs" property="sffs" style="width:98%" disabled="true">
		          <html:option value=""></html:option>
		          <html:options collection="sffsList" property="en" labelProperty="cn" />
		       </html:select>			
			</td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right">楼栋号:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ldh" styleId="ldh" readonly="true"/></td>
		    <td width="11%" height="20%"><div align="right">寝室号:</div></td>
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="qsh" styleId="qsh" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right">应收住宿费:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ysqsf" styleId="ysqsf" /></td>
		    <td width="11%" height="20%"><div align="right">实缴住宿费:</div></td>
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="sjqsf" styleId="sjqsf" /></td>
		  </tr>
		  <tr>
		    <td width="16%" height="20%"><div align="right">应收学费:</div></td>
		    <td width="37%" height="20%"><html:text name="rs" property="ysxf" styleId="ysxf" /></td>
		    <td width="11%" height="20%"><div align="right">实缴学费:</div></td>
		    <td width="36%" height="20%" colspan="2"><html:text name="rs" property="sjxf" styleId="sjxf" /></td>
		  </tr>
		</table>
	</html:form>
	<div class="buttontool">
       <logic:notPresent name="modify">
       <button class="button2" onclick="document.forms[0].action='yxgl_xsgl_one.do?doType=add';document.forms[0].submit();alert('添加成功');window.dialogArguments.document.getElementById('search_go').click();window.close();">
          提交信息
       </button>
       </logic:notPresent>     
       <logic:present name="modify" >
       <button class="button2" onclick="document.forms[0].action='yxgl_xsjf_one.do?doType=modify';document.forms[0].submit();alert('修改成功');window.dialogArguments.document.getElementById('search_go').click();window.close();">
          修改信息
       </button>
       </logic:present>  
       &nbsp;&nbsp;&nbsp;&nbsp;       
       <button class="button2" onclick="window.close();return false;">
           关 闭
       </button>
       
    </div>
    <script type="text/javascript">
      window.unload = yxglXybdOneUnLoad('search_go'); 
    </script>
  </body>
</html:html>
