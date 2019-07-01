<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

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
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
 <html:form action="/prise_conf_rs" method="post" >
<center>
<h3> <bean:write name="map" property="xxmc"/>无锡商业职业技术<bean:message key="lable.xsgzyxpzxy" />优秀学生奖学金申请表</h3>
			<table width="95%" class="printstyle">
				<tr>
					<th width="100" align="center">
						&nbsp;&nbsp;&nbsp;申请奖学金<br/>&nbsp;&nbsp;&nbsp;的等级
					</th>
					<td colspan="7" align="center">
						<bean:write name="map" property="jxjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<bean:write name="map" property="xymc"/>&nbsp;&nbsp;&nbsp;院,系(部) 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="map" property="zymc"/>&nbsp;&nbsp;&nbsp;专业 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="map" property="bjmc"/>&nbsp;&nbsp;&nbsp;班级
					</td>
				</tr>
				<tr>
					<th align="center">
						姓名
					</th>
					<td width="90" align="center">
						<bean:write name="map" property="xm"/>
					</td>
					<th width="70" align="center">
						性别
					</th>
					<td width="90" align="center">
						<bean:write name="map" property="xb"/>
					</td>
					<th width="84" align="center">
						出生年月
					</th>
					<td width="71" align="center">
						<bean:write name="map" property="csny"/>
					</td>
					<th width="84" align="center">
						籍贯
					</th>
					<td width="71" align="center">
						<bean:write name="map" property="jg"/>
					</td>
				</tr>
				<tr>
					<th align="center">
						政治面貌
					</th>
					<td width="71" align="center">
						<bean:write name="map" property="zzmmmc"/>
					</td>
					<th width="70" align="center">
						民族
					</th>
					<td width="100" align="center">
						<bean:write name="map" property="mzmc"/>
					</td>
					<th width="84" align="center">
						职务
					</th>
					<td align="center">
						&nbsp;
					</td>
					<th width="84" align="center">
						宿舍
					</th>
					<td  align="center">
						${map.ssbh }
					</td>
				</tr>
				<tr>
					<th rowspan="11" align="center">
						<p>
							上学期
						</p>
						<p>
							各门课
						</p>
						<p>
							程成绩
						</p>
					</th>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc1 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc2 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc3 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj1 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj2 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj3 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc4 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc5 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc6 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj4 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj5 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj6 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc7 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc8 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc9 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj7 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj8 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj9 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc10 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc11 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc12 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj10 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj11 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj12 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br/>
						${rs.kcmc13 }
						<br/>
					</td>
					<td colspan="3" align="center">
						${rs.kcmc14 }
					</td>
					<td colspan="2" align="center">
						${rs.kcmc15 }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;${rs.cj13 }
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.cj14 }
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.cj15 }
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<br/>
						平均成绩：${rs.pjcj } 
						<p align="right">
							本人签名：&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;年
							&nbsp;&nbsp;&nbsp;月
							&nbsp;&nbsp;&nbsp;日
						</p>
					</td>
				</tr>
				<tr>
					<th align="center">
						操行等级
					</th>
					<td height="30" align="center">
						&nbsp;
					</td>
					<th align="center">
						综合测评
					</th>
					<td colspan="2" align="center">
						&nbsp;
					</td>
					<th align="center" colspan="2">
						上学期宿舍卫生情况
					</th>
					<td align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th align="center">
							<br/>
							班<p/>
							主<p/>
							任<p/>
							意<p/>
							见<p/>
					</th>
					<td colspan="7">
						${map.fdyyj }
						<p align="right">
							签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<th align="center">
						<br/>
							系
						<p/>
							(部)
						<p/>
							意
						<p/>
							见
					</th>
					<td colspan="7">
						${map.xyshyj }
						<p align="right">
							签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<th align="center">
						<br/>
							学
						<p/>
							校
						<p/>
							意
						<p/>
							见
					</th>
					<td colspan="7">
						${map.xxshyj }
						<p align="right">
							盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;
							月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<table border="0" width="95%">
				<tr>
					<td algin="left">
						注：1、申请人只需填至“本人签名”处。<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						2、“操行等第”、“综合测评”、“上学期宿舍卫生情况”由班主任填写。
					</td>
				</tr>
			</table>
		</center>
</html:form>
</body>
</html:html>
