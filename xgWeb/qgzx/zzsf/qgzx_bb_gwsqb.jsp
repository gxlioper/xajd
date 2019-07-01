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
<html:html locale="true">
  <head>
    
    <title>MyJsp.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/qgzx_bb_gwsbb">
    <div id="rsT">
    <table align="center" width="65%">
    	<tr>
    		<td align="center"><STRONG><bean:write name="xxmc" scope="request"/>院内勤工助学岗位个人申请表 </STRONG></td>
    	</tr>
    	<tr>
    		<td align="left"><strong>漳州师范<bean:message key="lable.xsgzyxpzxy" />学生处制表 </strong></td>
    	</tr>
    </table>
	<table class="tbstyle" width="70%" align="center">
  		<tr>
    		<td width="72"><p align="center">姓 名 </p></td>
    		<td width="158"><bean:write name="rs" property="xm" /></td>
    		<td width="61" colspan="2"><p align="center">学 号 </p></td>
    		<td width="107" colspan="2"><bean:write name="rs" property="xh" /></td>
    		<td width="72"><p>性 别 </p></td>
    		<td width="154"><bean:write name="rs" property="xb" /></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">所在系 </p></td>
    		<td width="158"><bean:write name="rs" property="xymc" /></td>
    		<td width="61" colspan="2"><p align="center">年 级 </p></td>
    		<td width="107" colspan="2"><bean:write name="rs" property="nj" /></td>
    		<td width="72"><p>专 业 </p></td>
    		<td width="154"><bean:write name="rs" property="zymc" /></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">特 长 </p></td>
   			<td width="168" colspan="2"></td>
    		<td width="110" colspan="2"><p align="center">是否家庭困难 </p></td>
    		<td width="48"><bean:write name="rs" property="sfpks" /></td>
    		<td width="72"><p>联系电话 </p></td>
    		<td width="154"><bean:write name="rs" property="lxdh" /></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">申请岗位 </p></td>
    		<td width="552" colspan="7"><bean:write name="rs" property="gwdm" /></td>
  		</tr>
  		<tr>
    		<td width="72" valign="top" align="center"><p>&nbsp;</p>所在系<br></br>辅导员<br></br>审核意<br></br>见<br></br>（请打钩） </td>
    		<td width="552" colspan="7">
    			<p>&nbsp;</p>
    			1．&piv; 该生上述情况属实，符合条件，同意参加校内勤工助学。<br/>
        		2．&piv; 该生不符合参加校内勤工助学的条件。理由：
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p align="right">辅导员（签章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;月&nbsp;&nbsp;日 </p>
        	</td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">用工 </p>
        		<p align="center">单位 </p>
        		<p align="center">聘任 </p>
        		<p align="center">意见 </p>
        		<p align="center">（请打钩） </p></td>
    		<td width="552" colspan="7" valign="top">
    			<p>&nbsp;</p>
    			1．&piv; 同意聘用。 <br/>
        		2．&piv; 不同意聘用。理由（负责向应聘学生解释）：
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p align="right">分管领导（签章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;月&nbsp;&nbsp;日 </p></td>
  		</tr>
  		<tr>
    		<td width="72"><p align="center">送学生<br></br>处审查、<br></br>公告和<br></br>备案情<br></br>况 </p></td>
    		<td width="552" colspan="7" valign="top"></td>
  		</tr>
	</table>
	<table width="68%" align="center">
		<tr>
			<td align="left"><strong>备注： </strong></td>
		</tr>
		<tr>
			<td align="left"><strong>１、院本部机关各类岗位：由用工单位负责受理学生个人申请并考核录用；用工单位将录用的学生申请表交学生处公告和备案。 </strong></td>
		</tr>
		<tr>
			<td align="left"><strong>２、各系配套支付劳酬的各类岗位：由用工单位自行受理学生个人申请，各系决定考核录用，并将录用的学生汇总交学生处备案。</strong></td>
		</tr>
	</table>
    </div>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','<bean:write name="xxmc" scope="request"/>院内勤工助学岗位个人申请表 ','')">
				打 印 报 表
		</button>
	</div>
	</html:form>
  </body>
</html:html>
