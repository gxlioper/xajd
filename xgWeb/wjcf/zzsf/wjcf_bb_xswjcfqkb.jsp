<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>wjcf_bb_xswjcfqkb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="Copyright" content="正方软件 zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
  <body>
  		<script language="javascript" src="js/function.js"></script>
  		<table width="80%" align="center" class="tbstyle">
  		<tr>
  		<td>
  		<p align="center">&nbsp;</p>
  		<p align="center">&nbsp;</p>
    	<p align="center"><font size="6"><bean:write name="rs" property="xxmc"/> </font></p>
    	<p align="center">&nbsp;</p>
		<p align="center"><font size="6">学生违纪处分情况表 </font></p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<table width="40%" align="center">
		  <tr>
			<td align="center"><br><font size="2">学生姓名：</font><br/></td>
			<td align="left"><br><font size="2" style="border-bottom: 1px solid;"><bean:write name="rs" property="xm"/></font><br/></td>
		  </tr>
		  <tr>	
			<td align="center"><br><font size="2">所在单位：</font><br/></td>
			<td align="left"><br><font size="2" style="border-bottom: 1px solid;"><bean:write name="rs" property="xymc"/></font><br/></td>
		  </tr>
		  <tr>
		  	<td align="center"><br><font size="2">学生类别：</font><br/></td>
		  	<td align="left"><br><font size="2" style="border-bottom: 1px solid;"><bean:write name="rs" property="xslb"/></font><br/></td>
		  </tr>
		  <tr>
		    <td align="center"><br><font size="2">年级专业：</font><br/></td>
		    <td align="left"><br><font size="2" style="border-bottom: 1px solid;"><bean:write name="rs" property="zymc"/></font><br/></td>
		  </tr>
		  <tr>
		    <td align="center"><br><font size="2">学　　号：</font><br/></td>
		    <td align="left"><br><font size="2" style="border-bottom: 1px solid;"><bean:write name="rs" property="xh"/></font><br/></td>
		  </tr>
		</table>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center"><font size="2">填表时间： 年 月 日 </font></p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center"><strong><font size="2">填表须知 </font></strong></p>
		<p align="center">&nbsp;</p>
		<p><font size="1">一、 本调查表由学生所在系或有关主管部门填写，应实事求是，认真负责；</font></p>
		<p><font size="1">二、 封面“学生类别”指硕士生、本科生或专科生；</font></p>
		<p><font size="1">三、 各项签名栏请用钢笔或水笔手写，打字、签字章、私章均无效。</font></p>
		</td></tr>
		</table>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<p align="center">&nbsp;</p>
		<table width="95%" align="center" class="tbstyle">
  			<tr>
    			<td width="59" align="center">姓名</td>
    			<td width="69" align="center"><bean:write name="rs" property="xm"/></td>
    			<td width="51" align="center">性别</td>
    			<td width="64" align="center"><bean:write name="rs" property="xb"/></td>
    			<td width="64" align="center">籍贯</td>
    			<td width="102" colspan="2" align="center"><bean:write name="rs" property="jg"/></td>
    			<td width="51" align="center">出生<br>年月<br/></td>
    			<td width="115" align="center"><bean:write name="rs" property="csrq"/></td>
  			</tr>
  			<tr>
    			<td width="59" align="center">政治<br>面貌<br/></td>
    			<td width="140" colspan="2"><bean:write name="rs" property="zzmm"/></td>
    			<td width="64"><p align="center">系别 </p></td>
   				<td width="117" colspan="2"><bean:write name="rs" property="xymc"/></td>
    			<td width="59"><p align="center">学号 </p></td>
    			<td width="166" colspan="2"><bean:write name="rs" property="xh"/></td>
  			</tr>
  			<tr>
    			<td width="59"><p align="center">年级 </p></td>
    			<td width="89"><bean:write name="rs" property="nj"/></td>
    			<td width="51"><p align="center">专业 </p></td>
    			<td width="396" colspan="6"><bean:write name="rs" property="zymc"/></td>
  			</tr>
  			<tr>
    			<td width="110" colspan="2" align="center">身份证号码</td>
    			<td width="268" colspan="4"><bean:write name="rs" property="sfzh"/></td>
    			<td width="51" align="center">联系<br>电话<br/></td>
    			<td width="166" colspan="2"><bean:write name="rs" property="lxdh"/></td>
  			</tr>
  			<tr>
    			<td width="59">
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">违 </p>
        			<p align="center">纪 </p>
        			<p align="center">事 </p>
        			<p align="center">实 </p>
        			<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
        			<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
    				<p align="center">&nbsp; </p>
        		</td>
    			<td width="537" colspan="8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bz }</td>
  			</tr>
  			</table>
  			<br/><br/><br/><br/><br/><br/><br/><br/><br/>
  			<table width="95%" align="center" class="tbstyle">
  			<tr>
    			<td width="59px" align="center"><br/><p align=""><br/>所
        			<br/>在
        			<br/>系
        			<br/>拟
        			<br/>处
        			<br/>分
        			<br/>意
        			<br/>见
        			</p>
        			<br/>
        		</td>
    			<td width="537" colspan="8" valign="bottom"><p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p></td>
  			</tr>
  			<tr>
    			<td width="59" align="center"><br/><br/>学 
        			<br/>生 
        			<br/>本 
        			<br/>人 
        			<br/>意 
        			<br/>见<br/> <p/><p>&nbsp;</p>
        		</td>
    			<td width="537" colspan="8" valign="bottom"><p align="right">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日  </p></td>
  			</tr>
  			<tr>
    			<td width="59" align="center"><p align="center"><br/><br/>保卫
        			<br/>处或
        			<br/>相关
        			<br/>部门
        			<br/>意
        			<br/>见 <p/><br/></td>
    			<td width="537" colspan="8" valign="bottom"><p align="right" >签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p></td>
  			</tr>
  			<tr>
    			<td width="59" align="center"><br/><br/><br/>学 
        			<br/>生 
        			<br/>处 
        			<br/>意 
        			<br/>见<br/> <p>&nbsp;</p></td>
    			<td width="537" colspan="8" valign="bottom"><p align="right">签章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p></td>
  			</tr>
  			<tr>
    			<td width="59" align="center"><br/><br/>学 
        			<br/>院 
        			<br/>处 
        			<br/>分 
       	 			<br/>决 
        			<br/>定<br/> <p/><p>&nbsp;</p></td>
    			<td width="537" colspan="8" valign="bottom"><p align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日 </p></td>
  			</tr>
		</table>
  </body>
</html:html>
