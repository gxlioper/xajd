<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
<%@ page import="java.util.*" %>
<%@ page import="xgxt.action.Base" %>
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<html:form action="/stu_info_add" method="post">
			<%HashMap<String,String>rs=(HashMap<String,String>)request.getAttribute("rs"); %>
			<div style="width: 100%" align="center">
				<br><br><br><br><br>
				<div align="left">
					<b><font style="font-size:15.75pt;font-family:'黑体';margin-left:10px ">附件1：</font></b>
				</div>
				<div align="center" style="font-size:15.75pt;font-family:'华文中宋';" >
					<b>浙江省普通高等学校优秀毕业生登记表</b>
				</div>
				<br/>
				<div align="left" style="font-size:10.5pt;font-family:'仿宋_GB2312';margin-left:10px ">
					学 校:<font style="font-size:8.5pt">浙江理工大学</font>
					院(系):<font style="font-size:8.5pt">&nbsp;${rs.xymc }</font>
					专 业:<font style="font-size:8.5pt">&nbsp;${rs.zymc }</font>
					班 级:<font style="font-size:8.5pt">&nbsp;${rs.bjmc }</font>
					<font style="font-size:8.5pt">&nbsp;${rs.nowTime }</font>
				</div>
				<table width="100%" id="rsT" class="printstyle">
					<tr valign="bottom"> 
				      <td style="width:4%"></td> 
				      <td style="width:4%" ></td> 
				      <td style="width:4%" ></td> 
				      <td style="width:6%"></td> 
				      <td style="width:6%"></td> 
				      <td style="width:12%"></td> 
				      <td style="width:3%"></td>
				      <td style="width:3%"></td>
				      <td style="width:5%"></td> 
				      <td style="width:5%"></td> 
				      <td style="width:5%"></td>
				      
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td>
				      <td style="width:6%"></td> 
				    </tr>
					<tr valign="bottom"> 
				      <td colspan='3' height="30px"  style="font-family:'仿宋_GB2312';font-size:15.75pt;">姓 名</td> 
				      <td colspan=2  style="font-family:'仿宋_GB2312';font-size:15px;">${rs.xm }</td> 
				      <td colspan=1  style="font-family:'仿宋_GB2312';font-size:15.75pt;"> 性 别</td> 
				      <td colspan=2  style="font-family:'仿宋_GB2312';font-size:15px;">${rs.xb }</td> 
				      <td colspan=3  style="font-family:'仿宋_GB2312';font-size:15.75pt;"> 出生年月</td> 
				      <td colspan=2  style="font-family:'仿宋_GB2312';font-size:13px;"><%=Base.isNull(rs.get("csrq"))?"":rs.get("csrq").substring(0,7).replace("-","年")+"月" %></td> 
				      <td colspan=2  style="font-family:'仿宋_GB2312';font-size:15.75pt;"> 民 族</td> 
				      <td colspan=2  style="font-family:'仿宋_GB2312';font-size:15px;">${rs.mzmc }</td> 
				    </tr>
				    <tr valign="bottom"> 
				      <td colspan=3 height="30px" style="font-family:'仿宋_GB2312';font-size:15.75pt;"> 生源地</td> 
				      <td colspan=2 style="font-family:'仿宋_GB2312';font-size:14px;">${rs.sydshi } </td> 
				      <td colspan=3 style="font-family:'仿宋_GB2312';font-size:15.75pt;"> &nbsp;政&nbsp;治&nbsp;面&nbsp;貌&nbsp;</td> 
				      <td colspan=5 style="font-family:'仿宋_GB2312';font-size:15px;">${rs.zzmmmc } </td> 
				      <td colspan=2 style="font-family:'仿宋_GB2312';font-size:15.75pt;"> 职 务 </td> 
				      <td colspan=2 style="font-family:'仿宋_GB2312';font-size:15px;">${rs.zw } </td> 
				    </tr>
				    <tr valign="bottom"> 
				      <td colspan=3  style="font-family:'仿宋_GB2312';font-size:15.75pt;" height="40px" >家 庭<br>地 址 </td> 
				      <td colspan=7   style="font-family:'仿宋_GB2312';font-size:15px;" >${rs.jtszd }${rs.jtdz } </td> 
				      <td colspan=3   style="font-family:'仿宋_GB2312';font-size:15.75pt;" >联 系<br>电 话 </td>
				      <td colspan=4 style="font-family:'仿宋_GB2312';font-size:15px;"  >${rs.sjhm }&nbsp; </td>
				    </tr> 
				    <tr> 
				      <td width="7%" colspan="3" height="280px" style="font-family:'仿宋_GB2312';text-align:center;font-size:15.75pt;" > 本<br><br>人<br><br>简<br><br>历</td> 	      
				   	  <td colspan=14  style="font-family:'仿宋_GB2312';font-size:15.75pt;" >
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.grjl }</textarea>
				   	   </td> 
				    </tr> 
				    <tr> 
				      <td width="7%" colspan="3" height="280px" style="font-family:'仿宋_GB2312';text-align:center;font-size:15.75pt;"> 主<br><br>要<br><br>事<br><br>迹</td> 
				      <td colspan=14 style="font-family:'仿宋_GB2312';font-size:15.75pt;" >
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.zd1 }</textarea>
				   	  </td> 
				    </tr>
				</table>
				<br/>
				<div align="left" style="font-family:'仿宋_GB2312';font-size:'10.5pt';">
					<span style="margin-left: 10px">注：1、此表一式两份：学生本人档案、学校各一份。</span><br/>
					<span style="margin-left: 33px">2、本表内容可打印或用钢笔填写，字迹清楚，经院（系）、学校盖章，领导签字方有效。</span>
				</div>
			</div>
			
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			<div style="width: 100%" align="center">
				<table width="100%" id="rsT" class="printstyle">
				    <tr> 
				      <td width="12%"height="280px" style="text-align:center;font-size:15.75pt;font-family:'仿宋_GB2312';line-height: 35px">在<br/>校<br/>期<br/>间<br/>获<br/>奖<br/>情<br/>况</td>
				      <td colspan="3" >
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.zd2 }</textarea>
				   	  </td> 
				    </tr>     
				    <tr> 
				      <td height="180px" style="text-align:center;font-size:15.75pt;font-family:'仿宋_GB2312';line-height: 35px">院<br/>系<br/>意<br/>见</td> 	      
				   	  <td>&nbsp; </td> 
				   	  <td width="12%" height="160px" style="text-align:center;font-size:15.75pt;font-family:'仿宋_GB2312';line-height: 35px">学<br/>校<br/>意<br/>见</td> 	      
				   	  <td>&nbsp; </td> 
				    </tr> 
				     <tr> 
				      <td height="230px" style="text-align:center;font-size:15.75pt;font-family:'仿宋_GB2312';line-height: 35px">省<br/>教<br/>育<br/>厅<br/>意<br/>见</td> 	      
				   	  <td>&nbsp; </td> 
				   	  <td style="text-align:center;font-size:15.75pt;font-family:'仿宋_GB2312';line-height: 35px">毕<br/>业<br/>就<br/>业<br/>去<br/>向</td> 	      
				   	  <td>&nbsp; </td> 
				    </tr> 
				    <tr> 
				      <td height="85px" style="text-align:center;font-size:15.75pt;font-family:'仿宋_GB2312';line-height: 35px">备<br>注</td> 	      
				   	  <td colspan="3">&nbsp; </td> 
				    </tr> 
				</table>
				<br/>
				<div style="text-align:right;font-size:15.75pt;font:'仿宋_GB2312';">
					浙江省教育厅制表&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</html:form>
	</body>
</html>
