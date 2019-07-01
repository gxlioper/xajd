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
				<br/><br/>
				<div align="left" style="font-size:15.75pt;font-family:'黑体';margin-left: 15px ">
					<b>附件3：</b>
				</div>
				<br/><br/>
				<div align="center" style="font-size:15.75pt;font-family:'华文中宋' ">
					<b>浙江理工大学优秀毕业生登记表</b>
				</div>
				<br/><br/>
				<div align="left" style="font-family: 仿宋_GB2312;font-size:12pt;margin-left: 12px  ">
					学院：${rs.xymc}
					&nbsp;
					专业：${rs.zymc}
					&nbsp;
					班级：${rs.bjmc}
					&nbsp;
					${rs.nowTime}
				</div>
				<table width="100%" id="rsT" class="printstyle" style="font-family: 仿宋_GB2312;font-size:13.75pt">
					<tr>
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
					<tr> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="10%" colspan=3 height="30px" > 姓 名</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="15%" colspan=2>${rs.xm }&nbsp;</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="10%" colspan=1 > 性 别</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="15%" colspan=2 >${rs.xb }&nbsp;</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="10%" colspan=3 > 出生年月</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13px" width="15%" colspan=2 ><%=Base.isNull(rs.get("csrq"))?"":rs.get("csrq").substring(0,7).replace("-","年")+"月" %></td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="10%" colspan=2> 民 族</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="15%" colspan=2 >${rs.mzmc }&nbsp;</td>
				    </tr>
				    <tr> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=3 height="30px" >生源地</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=2>${rs.sydshi }</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=3>政&nbsp;治&nbsp;面&nbsp;貌</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=5>${rs.zzmmmc }&nbsp; </td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=2> 职 务 </td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=2>${rs.zw } </td>
				    </tr>
				    <tr> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=4 height="30px">家&nbsp;庭&nbsp;地&nbsp;址&nbsp;</td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt"  colspan=6 >${rs.jtszd}&nbsp; </td>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=3 >联系电话&nbsp;</th>
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=4 >${rs.sjhm }&nbsp; </th>
				    </tr> 
				    <tr style="height: 100px"> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt"  width="7%"  style="text-align:center;line-height: 35px;">本<br>人<br>简<br>历</td>	      
				   	  <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=16  ><br>
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 130px;_height: 180px;font-size:14px;font-weight:bold;" type="_moz">${rs.grjl }</textarea>
				   	   </td>
				    </tr> 
				    <tr> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt"  width="7%" height="260px" style="text-align:center;line-height: 35px"> 获<br>奖<br>情<br>况</td>	      
				   	  <td style="font-family: 仿宋_GB2312;font-size:13.75pt"  colspan=16  ><br>
				   	  <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px;font-weight:bold;" type="_moz">${rs.zd2 }</textarea>
				   	  </td> 
				    </tr>
				    <tr> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="7%" height="70px" style="text-align:center;line-height: 35px">学<br>院<br>意<br>见</td>	      
				   	  <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=6 >&nbsp; </td>
				   	  <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="7%" height="70px" style="text-align:center;line-height: 35px">学<br>校<br>意<br>见</td>	      
				   	  <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=9 >&nbsp; </td>
				    </tr> 
				    <tr> 
				      <td style="font-family: 仿宋_GB2312;font-size:13.75pt" width="7%" height="40px" style="text-align:center;line-height: 35px">备<br>注</td>	      
				   	  <td style="font-family: 仿宋_GB2312;font-size:13.75pt" colspan=16 >&nbsp; </td>
				    </tr> 
				</table>
				<br/>
				<div align="left" style="font-family: 仿宋_GB2312;font-size:12pt;">
					<span style="margin-left:15px">注：1、此表一式两份：本人档案、学校各一份。<br></span>
					<span style="margin-left:44px">2、本表内容要求电脑打印，经学院盖章有效。</span>
				</div>
			</div>
		</html:form>
	</body>
</html>
