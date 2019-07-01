<%@  page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
  <head>
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<meta http-equiv="Content-Language" content="GBK" />
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
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
  
  <body>
    <center>
     <html:form action="/stu_archives_info_details">
          <div class="title" align="left">当前位置：学生档案基本信息</div>        
			  <table  width="100%" align="center" class="tbstyle"> 
			  <thead align="center">
						<tr><td align="center" colspan="4">
							历届学生档案
						</td></tr>
					</thead>
	     			<tr>
	         			  <td align="right" width="15%">学号 ：</td>
	         			  <td align="left" width="20%">	         			  
	         			  <bean:write name="rs" property="xh" /> </td>
	         			  <td align="right" width="15%">年级 ：</td>
	         			  <td align="left" width="20%"><bean:write name="rs" property="nj" /></td>
		     			  
	     			</tr>
		 			<tr>
		       			  <td align="right">姓名 ：</td>
		       			  <td align="left"><bean:write name="rs" property="xm" /></td>
		       			  <td align="right"><bean:message key="lable.xsgzyxpzxy" />名称 ：</td>
			   	          <td align="left"><bean:write name="rs" property="xymc" /></td>
				    </tr>
					<tr>
		     			  <td align="right">性别 ：</td>
		     			  <td align="left"><bean:write name="rs" property="xb" /></td>
		                  <td align="right">专业名称 ：</td>
		                  <td align="left"><bean:write name="rs" property="zymc" /></td>
		            </tr>
		            <tr>
		                  <td align="right">担任职务 ：</td>
		                  <td align="left"><bean:write name="rs" property="drzw" /></td>
		                  <td align="right">班级名称 ：</td>
		                  <td align="left"><bean:write name="rs" property="bjmc" /></td>
		            </tr>
		            <tr>
		                  <td align="right">籍贯 ：</td>
		                  <td align="left"><bean:write name="rs" property="jg" /></td>
		                  <td align="right">&nbsp;</td>
		                  <td align="left">&nbsp;</td>
		            </tr>
	          </table>
         
         
         
         <fieldset>
             <legend>
                        学生活动信息
             </legend>
                 <table  class="tbstyle"  width="100%">
                       <thead>
                          <tr align="center" style="curser:hand">
                                  <logic:iterate id="title" name="topTr">
                                           <td width="30%" id="<bean:write name="title" property="en"/>"
                                  			        onclick="tableSort(this)" nowrap >
                                  					<bean:write name="title" property="cn"/>
                               			   </td>
                        		  </logic:iterate>
                    	  </tr>
                   		</thead>
                   		<logic:iterate  name="rs" id="v">
                   		       <td>
                   		           
                   		       </td>
                   		</logic:iterate>
                 </table>
         </fieldset>
      </html:form>
    </center>
  </body>
</html>
