<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_hzy.js"></script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  
  <body>
    <html:form action="/jtxmtbjlsq">
    <div align=center style="font-size: 18px;font-family: '黑体'">杭州职业技术<bean:message key="lable.xsgzyxpzxy" /><br>集体项目特别奖励申请表</div>
         申请部门：<bean:write name="bmmc" />
    <html:hidden property="queryStr" styleId="queryStr"/>
	<table width="89%" class="tbstyle">
	  <tr>
	    <td height="36" colspan="2" scope="col" align=center><font color="red">*</font>活动名称</td>
	    <td width="26%" scope="col"><html:text property="hdmc" name="rs1" /></td>
	    <td colspan="2" scope="col" align=center>活动时间</td>
	    <td width="35%" scope="col"><html:text property="hdsj" name="rs1" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('hdsj','y-mm-dd');"  /></td>
	  </tr>
	  <tr>
	    <td colspan="2" scope="row" align=center>获奖名次</td>
	    <td><html:text property="hjmc" name="rs1" /></td>
	    <td colspan="2"><div align="center">申请奖励金额</div></td>
	    <td><html:text property="sqje" name="rs1"/></td>
	  </tr>
	  <tr>
	    <th colspan="6" scope="row">获集体奖参加人员</th>
	  </tr>
	  <tr>
	    <td colspan="2" scope="row"><div align="center">班级</div></td>
	    <td><div align="center">姓名</div></td>
	    <td colspan="2"><div align="center">班级</div></td>
	    <td><div align="center">姓名</div></td>
	  </tr>
	  <logic:iterate id="stu" name="rs" length="10">
<%--	  <tr>--%>
<%--	    <td colspan="2" scope="row"><select id="01" name="bj" onchange="getStus(this)"></select></td>--%>
<%--	    <td><html:text property="xh" name="stu" style="width:150px" /><span style="width:18px;border:0px solid red;" name="span"><select id="02" name="stuName" style="margin-left:-150px;width:168px"></select></td>--%>
<%--	    <td colspan="2"><select id="03" name="bj" onchange="getStus(this)"></select></td>--%>
<%--	    <td><html:text property="xh" style="width:150px" /><span style="width:18px;border:0px solid red;" name="span"><select id="04" name="stuName" style="margin-left:-150px;width:168px"></select></td>--%>
<%--	  </tr>--%>
	  <tr>
	    <td colspan="2" scope="row"><input type="text" id="bjmc" name="bjmc" value="<bean:write name="stu" property="bjmc1" />"/></td>
	    <td><input type="text" id="xm" name="xm" value="<bean:write name="stu" property="xm1" />"/></td>
	    <td colspan="2"><input type="text" id="bjmc" name="bjmc" value="<bean:write name="stu" property="bjmc2" />"/></td>
	    <td><input type="text" id="xm" name="xm" value="<bean:write name="stu" property="xm2" />"/></td>
	  </tr>
	  </logic:iterate>
	  <tr>
	    <td width="7%" height="122" scope="row" align=center>
	    <p>系</p>
	    <p>（院）</p>
	    <p>意</p>
	    <p>见</p></td>
	    <td colspan="5">
		    <html:textarea property="xyyj" rows="7" cols="85" ></html:textarea>
		    <div style="float:right" id="date" name="date">
	    <br>签名盖章
	    <br>year年 month月 day日
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </div>
	    </td>
	  </tr>
<%--	  <logic:equal value="admin" name="userType" scope="session">--%>
	  <tr>
	    <td scope="row" align=center>
	    <p>学</p>
	    <p>生</p>
	    <p>处</p>
	    <p>意</p>
	    <p>见</p></td>
	    <td colspan="3" scope="row"><html:textarea property="xscyj" rows="7" cols="50" disabled="true"></html:textarea>
	    <div style="float:right" id="date" name="date">
	    <br>签名盖章
	    <br>year年 month月 day日
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </div></td>
	    
	    <td width="11%" scope="row" align=center>
	      <p>学</p>
	      <p>院</p>
	      <p>意</p>
	    <p>见</p></td>
	    <td scope="row"><html:textarea property="xxyj" rows="7" cols="20" disabled="true"></html:textarea>
	    <div style="float:right"  id="date" name="date">
	    <br>签名盖章
	    <br>year年 month月 day日
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </div>
	    </td>
	  </tr>
<%--	  </logic:equal>--%>
	</table>
	<p>注：1.附获奖材料	</p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;2.用钢笔填写，字迹清楚，经院盖章，领导签字方为有效。 </p>
    </html:form>
    <div class="buttontool" align="center">
    <button class="button2" align="center" onclick="if(!checkAllInput('hdmc')) {return false;}submitFromAction('/xgxt/saveJtxmtbjlsq.do?method=saveJtxmtbjlsq')">
    	提&nbsp;&nbsp;&nbsp;&nbsp;交
    </button>
<%--    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--    <button class="button2" align="center" onclick="">--%>
<%--    	打&nbsp;&nbsp;&nbsp;&nbsp;印--%>
<%--    </button>--%>
    </div>
  </body>
  <logic:present name="saveFlag">
  <logic:equal value="true" name="saveFlag">
  <script>
  	alert("保存成功！");
  </script>
  </logic:equal>
  <logic:equal value="false" name="saveFlag">
  <script>
  	alert("保存失败！");
  </script>
  </logic:equal>
  </logic:present>
  <script type="text/javascript">
    initDate();
  </script>
</html:html>
