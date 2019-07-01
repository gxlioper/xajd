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
<link id="csss1" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
<script type="text/javascript" src="js/function.js"></script>		
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script language="javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/calendar-zh.js"></script>
<script language="javascript" src="js/calendar-setup.js"></script>
  <body>
    <html:form action="/pjpy_apply_bg">
       <input type="hidden" name="redirect" id="redirect" value="true">
	   <input type="hidden" name="variable" id="variable" value="none">
	   <!--input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" /-->
       <input type="hidden" name="url"  id="url"          value="/pjpy/pjpy_bgprise_apply.jsp">
       <input type="hidden" name="jxjlx" id="jxjlx"       value="baosteel">
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
       <table width="99%"  class="tbstyle" align="center">
          <thead>
            <tr><td colspan="10" align="center" style="font-size:14px">宝 钢 教 育 基 金 优 秀 学 生 奖 评 审 表</td></tr>
          </thead>
          <tr> 
          <logic:equal value="teacher" name="userOnLine" scope="session">    
             <td  width="10%">学号</td><td colspan="2"><html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
									<button onclick="showTopWin('stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button></td>
          </logic:equal>
          <logic:notEqual value="teacher" name="userOnLine" scope="session">
          	<td>学号</td><td colspan="2"><html:text name="rs" property="xh" readonly="true"/>
									</td>
          </logic:notEqual>
		  <td colspan="7">&nbsp;</td>
          </tr>
		  <tr>
		    <td height="25">姓名</td>
		    <td colspan="2"><html:text name="rs" property="xm" style="width:98%" readonly="true"/></td>
		    <td width="14%"><div align="center">性别</div></td>
		    <td width="12%"><html:text name="rs" property="xb" style="width:98%" readonly="true"/></td>
		    <td width="13%">出生年月</td>
		    <td colspan="2" ><html:text name="rs" property="csny" style="width:98%" readonly="true"/></td>
		    <td rowspan="4" colspan="2">&nbsp;</td>
		  </tr>
		  <tr>
		    <td  height="25">参加党团</td>
		    <td colspan="2"><html:text name="rs" property="zzmm" style="width:98%"/></td>
		    <td ><div align="center">民族</div></td>
		    <td><html:text name="rs" property="mzmc" style="width:98%" /></td>
		    <td>入学年月</td>
		    <td colspan="2"><html:text name="rs" property="rxny" style="width:98%" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td  height="25">院、系</td>
		    <td colspan="2" ><html:text name="rs" property="xymc" style="width:98%" readonly="true"/></td>
		    <td ><div align="center">专业</div></td>
		    <td colspan="4"><html:text name="rs" property="zymc" style="width:98%" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td  height="25">E-mail</td>
		    <td colspan="2" ><html:text name="rs" property="email" style="width:98%"/></td>
		    <td ><div align="center">联系电话</div></td>
		    <td colspan="4"><html:text name="rs" property="lxdh" style="width:98%"/></td>
		  </tr>
		  <tr>
		    <td rowspan="6">参加社会实践和承担社会工作情况</td>
		    <td height="25" colspan="2">起止日期</td>
		    <td colspan="7"> 社会实践名称 /社会工作任职名称</td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2"><html:text name="rs" property="qzrq1"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq1','y-mm-dd');"/></td>
		    <td colspan="7"><html:text name="rs" property="hdmc1" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2"><html:text name="rs" property="qzrq2"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq2','y-mm-dd');"/></td>
		    <td colspan="7"><html:text name="rs" property="hdmc2" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="qzrq3"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq3','y-mm-dd');"/></td>
		    <td colspan="7"><html:text name="rs" property="hdmc3" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="qzrq4" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq4','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="hdmc4" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="qzrq5" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq5','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="hdmc5" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td rowspan="6" >
		      <p align="center">在读</p>
		      <p align="center">学历</p>
		      <p align="center">以来</p>
		      <p align="center">获奖</p>
			  <p align="center">情况</p>
			</td>
		    <td height="25" colspan="2">日期</td>
		    <td colspan="7"> 奖项名称 /颁奖单位</td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq1" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq1','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc1" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq2" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq2','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc2" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq3" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq3','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc3" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq4" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq4','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc4" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq5" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq5','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc5" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="3" >本（专）科生本学年学习成绩</td>
		    <td colspan="7">年级人数<html:text name="rs" property="njrs" />人 　　　 年级排名<html:text name="rs" property="njpm" />名 </td>
		  </tr>
		  <tr>
		    <td rowspan="2" >研究生已经发表论文统计情况</td>
		    <td width="10%" height="25"><div align="center">国内刊物</div></td>
		    <td width="10%"><div align="center">国际刊物</div></td>
		    <td width="14%" align="center">国内学术会议</td>
		    <td width="12%">国际学术会议</td>
		    <td width="13%">其中被SCI收录</td>
		    <td width="8%">其中被EI收录 </td>
		    <td width="9%">获得专利</td>
		    <td width="8%">科技奖励</td>
		    <td>其 他</td>
	      </tr>
		  <tr>
		    <td height="46"><html:text name="rs" property="gnkw"  style="width:45px"/>篇</td>
		    <td height="46"><html:text name="rs" property="gjkw" style="width:45px"/>篇</td>
		    <td height="46"><html:text name="rs" property="gnxshy"  style="width:55px"/>篇</td>
			<td><html:text name="rs" property="gjxshy"  style="width:30px"/>篇</td>
			<td><html:text name="rs" property="sci"  style="width:30px"/>篇</td>
			<td><html:text name="rs" property="ei"  style="width:30px"/>篇</td>
			<td><html:text name="rs" property="zl"  style="width:30px"/>项</td>
			<td><html:text name="rs" property="kjjl"  style="width:30px"/>项</td>
			<td><html:text name="rs" property="qt"  style="width:20px"/>篇（项）</td>
		  </tr>
		  <tr>
		    <td height="120" colspan="10" valign="top"><p>申请人作为主要作者 (列前三名)的学术成果 <strong>（取最重要的3篇或项） </strong></p>
		    填写顺序：论文题目、刊物名、卷号、日期、页码；科技成果；所获专利及科技奖励情况<br>
		    <html:textarea name="rs" property="xscg" rows="6" style="width:98%"></html:textarea></td>
		    
		  </tr>
		  <tr>
		    <td  height="50">申请人主要事迹</td>
		    <td colspan="9"><html:textarea name="rs" property="zysj" rows='6' style="width:98%"/></td>
		  </tr>
		  
		  <logic:equal value="xy" name="userType" scope="session">
		  <tr>
		    <td  height="50"><bean:message key="lable.xsgzyxpzxy" />或系评审小组意见</td>
		    <td colspan="9"><html:textarea name="rs" property="xyshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
		  <logic:equal value="admin" name="userType" scope="session">
		  <tr>
		    <td  height="50">学校或单位评审意见</td>
		    <td colspan="9"><html:textarea name="rs" property="xxshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  <tr>
		    <td  height="50">宝钢教育基金评审工作委员会意见</td>
		    <td colspan="9"><html:textarea name="rs" property="baosteelshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
		</table>
    </html:form>
    <div class="buttontool">
       <button class="button2" onclick="shgcPriseApplication()">提交申请</button>
       <button class="button2" onclick="shgcPriseAppicationPrint()">报表打印</button>
    </div>
  </body>
</html:html>
