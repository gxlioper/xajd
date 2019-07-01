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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jtjjknsPrint.jsp' starting page</title>
    	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
  </style>
<center>
<body  class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center'><b><span style='font-size:18.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>三江<bean:message key="lable.xsgzyxpzxy" />学生第二课堂活动企划书</span></b></p> 
  <table class="tbstyle" width=85% height=700 > 
    <tr> 
      <td width=15% align="center" valign=middel class="Normal"> 
      	    活动名称
      </td> 
      <td width=240 align="center" colspan=2 valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hdmc}
      		</logic:notEmpty>
      </td> 
      <td width=90  align="center" colspan=2 valign=middel class="Normal">
     		 举办部门
      </td> 
      <td width=198  align="center" colspan=2 valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.jbbm} 
      		</logic:notEmpty>	
      </td> 
    </tr> 
    <tr> 
      <td width=105  align="center" valign=middel class="Normal">
      		拟举办时间
      </td> 
      <td width=240  align="center" colspan=2 valign=middel class="Normal">
      		&nbsp; 
      		<logic:notEmpty name="rs">
      		${rs.jbsj }
      		</logic:notEmpty>
      		</td> 
      <td width=90  align="center" colspan=2 valign=middel class="Normal">
      		地点
      </td> 
      <td width=198  align="center" colspan=2 valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hddd }
      		</logic:notEmpty>
       </td> 
    </tr> 
    <tr> 
      <td width=105 align="center"  valign=middle class="Normal">
      		活动责任人
	  </td> 
      <td width=168 align="center"  valign=middle class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		 ${rs.hdfzr }
      		</logic:notEmpty> 
      		 </td> 
      <td width=72  align="center" valign=middle class="Normal">
      		宿舍号
      </td> 
      <td width=71  align="center" valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		 ${rs.ssh }
      		</logic:notEmpty>		 
      </td> 
      <td width=73  align="center" colspan=2 valign=middel> 
          联系方式
      </td> 
      <td width=144  align="center" valign=top class="Normal">
      	   &nbsp;
      	   <logic:notEmpty name="rs">
      	   ${rs.lxfs } 
      		</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=105  align="center" valign=middle class="Normal">
      	 指导老师
      </td> 
      <td width=168  align="center" valign=top class="Normal">
      	<logic:notEmpty name="rs">	
      		&nbsp; ${rs.zdls }
      	</logic:notEmpty>
      </td> 
      <td width=72  align="center" valign=middle class="Normal">参加对象<br>及人数</td> 
      <td width=288 align="center"  colspan=4 valign=top class="Normal">
      	<logic:notEmpty name="rs">
      		&nbsp; ${rs.cjdxrs }
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=105 align="center"  valign=middle class="Normal">邀请嘉宾</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
     	 <logic:notEmpty name="rs">
      		&nbsp;${rs.yqjb } 
      		</logic:notEmpty>		
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105 height="15%" align="center" valign=middle class="Normal">活动的目的和<br>意义</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hdmdyy } 
      		</logic:notEmpty>
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105  align="center" valign=middle class="Normal">活动可行性分<br>析</td> 
      <td width=528 align="center"  colspan=6 valign=top class="Normal">
      		&nbsp;
      	<logic:notEmpty name="rs">
      		${rs.hdkxfx }
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105  align="center" valign=middle class="Normal">活动实施时间<br>表</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hdsssjb }
      		</logic:notEmpty>	
      	</td> 
    </tr> 
    <tr> 
      <td width=105  align="center" valign=middle class="Normal">活动需要经费</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal"> 
      	<logic:empty name="rs">
      		＿＿＿＿＿＿＿＿元
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<U>${rs.hdxyjf }</U>元
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105  align="center" valign=middle class="Normal"> <p><span style='  font-family:宋体'>活动经费预算<br>清单</span></p></td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
      		&nbsp; 
      	<logic:notEmpty name="rs">
      		${rs.hdjfys }
      	</logic:notEmpty>	
      </td> 
    </tr> 
  </table> 
  <p><span style='font-family:宋体'>备注<span
lang=EN-US>:</span>所有第二课堂活动的举办<span lang=EN-US>,</span>必须递交书面策划书<span
lang=EN-US>,</span>不交策划书者不予批准</span></p> 
</div> 
</body>
</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</html>