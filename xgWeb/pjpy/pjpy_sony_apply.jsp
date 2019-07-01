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
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="js/String.js"></script>
  <body>
    <html:form action="/pjpy_apply_sony">
	   <input type="hidden" name="redirect" id="redirect" value="true">
	   <input type="hidden" name="variable" id="variable" value="none">
       <input type="hidden" name="url"  id="url"          value="/pjpy/pjpy_sony_apply.jsp">
       <input type="hidden" name="jxjlx" id="jxjlx"       value="sony">
       <input type="hidden" name="jxjdm" id="jxjdm"       value="<bean:write name="jxjdm"/>">
       <logic:present name="dboperation">
         <logic:equal value="true" name="dboperation">
         <script type="text/javascript">
         	alert("保存成功！");
         </script>
         </logic:equal>
         <logic:equal value="false" name="dboperation">
         <script type="text/javascript">
         	alert("保存失败！");
         </script>
         </logic:equal>
       </logic:present>
    <div class="title">
       <div class="title_img" id="title_m">
       	当前所在位置：评奖评优 － 奖学金申请 － 填写申请表
       </div>
    </div>
      <p align="center">上海市高等学校优秀学生（索尼）奖学金评审表 </p>
			<table align="center" class="tbstyle" style="width:99%;">
			 <tr>
				<logic:equal value="teacher" name="userOnLine" scope="session">    
		             <td  width="10%" colspan="2">学号</td><td colspan="2"><html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button onclick="showTopWin('stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button></td>
	          	</logic:equal>
    	      	<logic:notEqual value="teacher" name="userOnLine" scope="session">
        	  		<td>学号</td><td colspan="2"><html:text name="rs" property="xh" readonly="true"/></td>
	          	</logic:notEqual>
		  		<td colspan="7">&nbsp;</td>
        	  </tr>
			  <tr>
			    <td width="91" height="25" colspan="2">学 校</td>
			    <td width="192"><html:text name="rs" property="xxmc" /></td>
			    <td width="96">姓 名</td>
			    <td width="84"  colspan="3" ><html:text name="rs" property="xm" /></td>
			    <td width="60" colspan="2">性别</td>
			    <td width="45" colspan="2"><html:text name="rs" property="xb" /></td>
			  </tr>
			  <tr>
			    <td width="91" height="26" colspan="2">出生年月</td>
			    <td width="120"><html:text name="rs" property="csny" readonly="true"/></td>
			    <td width="72">民族</td>
			    <td width="96" colspan="3"><html:text name="rs" property="mzmc" /></td>
			    <td width="84" colspan="2">入学时间</td>
			    <td width="105" colspan="2"><html:text name="rs" property="rxny" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td width="91" height="28" colspan="2">专 业</td>
			    <td width="288" colspan="5" ><html:text name="rs" property="zymc" /></td>
			    <td width="84" colspan="2">学 制</td>
			    <td width="105" colspan="2"><html:text name="rs" property="xz" /></td>
			  </tr>
			  <tr>
			    <td>获奖记录</td>
			    <td colspan="10" valign="top"><html:textarea name="rs" property="hjjl" rows="6" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td>品德评语/辅导员意见</td>
			    <td colspan="10" valign="top"><html:textarea name="rs" property="dsyj" rows="6" style="width:98%"/></td>
			  </tr>
		  <logic:equal value="xy" name="userType" scope="session">
		  <tr>
		    <td  height="50"><bean:message key="lable.xsgzyxpzxy" />或系评审小组意见</td>
		    <td colspan="10"><html:textarea name="rs" property="xyshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
		  <logic:equal value="admin" name="userType" scope="session">
		  <tr>
		    <td height="50">学校或单位评审意见</td>
		    <td colspan="10"><html:textarea name="rs" property="xxshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
			  <tr>
			    <td>评委意见</td>
			    <td colspan="10"><html:textarea property="pwyj" rows='6' style="width:98%"></html:textarea></td>
			  </tr>
			</table>
			<p>注： </p>
			<p>1 、此表一式三份，推荐学校、市教委、索尼公司各一份。 </p>
			<p>2 、评审表各栏统一用电脑打印，经系、校（院）盖章有效。 </p>
			<p>3 、被评审人主要事迹、成绩单及获奖证书一律用 A4 纸打印或复印。 </p>
    </html:form>
	<div class="buttontool">
       <button class="button2" onclick="shgcPriseApplication()">提交申请</button>
       <button class="button2" onclick="shgcPriseAppicationPrint()">报表打印</button>
    </div>
  </body>
</html:html>
