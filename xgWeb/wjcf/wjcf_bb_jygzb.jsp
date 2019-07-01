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
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style type="text/css">
		.tbstyle {
					border-collapse: collapse;
				}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
}		
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
  <body>
    <p align="center">违纪学生教育跟踪表 </p>
	<p>__________ <bean:message key="lable.xsgzyxpzxy" /> </p>
	<table width="100%" class="tbstyle" border="1">
  		<tr>
    		<td width="705" colspan="15"></td>
    		<td width="36" rowspan="5"><p align="center"><strong>第 </strong></p>
        		<p align="center"><strong>三 </strong></p>
        		<p align="center"><strong>季 </strong></p>
        		<p align="center"><strong>度 </strong></p>
        		<p align="center"><strong>教 </strong></p>
        		<p align="center"><strong>育 </strong></p>
        		<p align="center"><strong>情 </strong></p>
        		<p align="center"><strong>况 </strong></p>
        		<p align="center"><strong>记 </strong></p>
        		<p align="center"><strong>录 </strong></p></td>
    		<td width="539" rowspan="5" valign="bottom" align="right"><p align="right"><strong>联系人签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p>
        		<p align="right"></p>
        		<p align="right"><strong>学生签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
  		</tr>
  		<tr>
   		 	<td width="67" colspan="2" valign="top"><p><strong>姓 </strong><strong>名 </strong></p></td>
    		<td width="84" colspan="2" valign="top">&nbsp;</td>
    		<td width="72" colspan="2" valign="top"><p><strong>专 </strong><strong>业 </strong></p></td>
    		<td width="96" colspan="2" valign="top">&nbsp;</td>
    		<td width="60" valign="top"><p><strong>班级 </strong></p></td>
    		<td width="96" valign="top">&nbsp;</td>
    		<td width="60" valign="top"><p><strong>性别 </strong></p></td>
    		<td width="48" colspan="2" valign="top">&nbsp;</td>
    		<td width="72"><p align="center"><strong>年 </strong><strong>龄 </strong></p></td>
    		<td width="50" valign="top">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="91" colspan="3" valign="top"><p><strong>处分类别 </strong></p></td>
    		<td width="108" colspan="2" valign="top">&nbsp;</td>
    		<td width="96" colspan="2" valign="top"><p><strong>处分时间 </strong></p></td>
    		<td width="180" colspan="3" valign="top">&nbsp;</td>
    		<td width="96" colspan="2" valign="top"><p><strong>考查年月 </strong></p></td>
    		<td width="134" colspan="3" valign="top">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="705" colspan="15" valign="top"><p align="center"><strong>教 </strong><strong>育 </strong><strong>跟 </strong><strong>踪 </strong><strong>记 </strong><strong>录 </strong></p></td>
  		</tr>
  		<tr>
    		<td width="43" rowspan="2"><p align="center"><strong>第 </strong></p>
        		<p align="center"><strong>一 </strong></p>
        		<p align="center"><strong>季 </strong></p>
        		<p align="center"><strong>度 </strong></p>
        		<p align="center"><strong>教 </strong></p>
        		<p align="center"><strong>育 </strong></p>
        		<p align="center"><strong>情 </strong></p>
        		<p align="center"><strong>况 </strong></p>
        		<p align="center"><strong>记 </strong></p>
        		<p align="center"><strong>录 </strong></p></td>
    		<td width="662" colspan="14" rowspan="2" valign="bottom" align="right"><p><strong>联系人签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p>
        		<p></p>
        		<p><strong>学生签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
  		</tr>
  		<tr>
    		<td width="36"><p align="center"><strong>第 </strong></p>
        		<p align="center"><strong>四季度 </strong></p>
        		<p align="center"><strong>教 </strong></p>
        		<p align="center"><strong>育 </strong></p>
        		<p align="center"><strong>记 </strong></p>
        		<p align="center"><strong>录 </strong></p></td>
    		<td width="539" valign="bottom" align="right"><p><strong>联系人签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p>
        		<p></p>
        		<p><strong>学生签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
  		</tr>
  		<tr>
    		<td width="43"><p align="center"><strong>第 </strong></p>
        		<p align="center"><strong>二 </strong></p>
        		<p align="center"><strong>季 </strong></p>
        		<p align="center"><strong>度 </strong></p>
        		<p align="center"><strong>教 </strong></p>
        		<p align="center"><strong>育 </strong></p>
        		<p align="center"><strong>情 </strong></p>
        		<p align="center"><strong>况 </strong></p>
        		<p align="center"><strong>记 </strong></p>
        		<p align="center"><strong>录 </strong></p></td>
    		<td width="662" colspan="14" valign="bottom" align="right"><p><strong>联系人签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p>
        		<p></p>
        		<p><strong>学生签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
    		<td width="36"><p align="center"><strong>学 </strong></p>
        		<p align="center"><strong>院 </strong></p>
        		<p align="center"><strong>意 </strong></p>
        		<p align="center"><strong>见 </strong></p>
        		<p align="center"></p></td>
    		<td width="539" valign="bottom"><p align="right"><strong>院领导签名： </strong><strong>年 </strong><strong>月 </strong><strong>日 </strong></p></td>
  		</tr>
	</table>
  </body>
</html:html>
