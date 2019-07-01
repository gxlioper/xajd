<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
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
			<div align="center" style="font-size:25px;font:'黑体' ">
				<b><br/>浙江理工大学${rs.xn }学年${rs.xmmc }申请表</b>
				<p style="height:20px"></p>
			</div>
			<table width="100%" id="rsT" class="printstyle">
				<tr valign="bottom"> 
			      <td style="width:7%"></td> 
			      <td style="width:12%"></td> 
			      <td style="width:7%"></td> 
			      <td style="width:5%"></td> 
			      <td style="width:7%"></td> 
			      <td style="width:8%"></td> 
			      <td style="width:8%"></td>
			      <td style="width:7%"></td>
			      <td style="width:1%"></td> 
			      <td style="width:5%"></td> 
			      
			      <td style="width:3%"></td>
			      <td style="width:3%"></td>
			      <td style="width:5%"></td>
				</tr>
				<tr> 
				  <td colspan='1' height="30px" align="center" >姓 名</td> 
				  <td colspan='1' >${rs.xm }</td>
				  <td colspan='1' align="center" >性 别</td>
				  <td colspan='1' >${rs.xb }</td>
				  <td colspan='2' align="center" >出生年月</td>
				  <td colspan='3' >${rs.csrq }</td>
				  <td colspan='2' align="center" >政治面貌</td>
				  <td colspan='2' >${rs.zzmmmc }</td>
				</tr>
				<tr>
				  <td colspan='1' align="center" >学 号</td>
				  <td colspan='1' >${rs.xh }</td>
				  <td colspan='1' align="center" >学 院</td>
				  <td colspan='2' >${rs.xymc }</td>
				  <td colspan='1' align="center" >班 级</td>
				  <td colspan='3' >${rs.bjmc }</td>
				  <td colspan='2' align="center" >担任职务</td>
				  <td colspan='2' ></td>
				</tr>
				<tr>
				  <td colspan='2' align="center" >综合测评成绩班级排名</td>
				  <td colspan='3' >${rs.cpzpm }</td>
				  <td colspan='1' align="center" >德育</td>
				  <td colspan='1' >${rs.dyf }</td>
				  <td colspan='1' align="center" >智育</td>
				  <td colspan='2' >${rs.zyf }</td>
				  <td colspan='2' align="center" >体育</td>
				  <td colspan='1' >${rs.tyf }</td>
				</tr>
				<tr>
				  <td colspan='1' style="text-align:center" ><br/><br/><br/><br/>申<br/><br/>请<br/><br/>理<br/><br/>由<br/><br/><br/><br/></td>
				  <td colspan='12' ><p align="lift">（本人德智体等诸方面的表现，包含参与科研活动、论文发表、社会实践等方面）<br/><br/><br/><br/><br/><br/><br/><br/></p>
				  		<p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">&nbsp;</p>
					    <p align="right">申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/></p>
				     	<div align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/></div></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" ><br/><br/><br/>班<br/>级<br/>推<br/>荐<br/>意<br/>见<br/><br/><br/></td>
				  <td colspan='12' ><p>&nbsp;</p>
				    <p align=center class="STYLE2"><br/><br/>班主任签字（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/></p>
		          <div align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" ><br/><br/><br/>学<br/>院<br/>意<br/>见<br/><br/><br/></td>
				  <td colspan='12' ><p>&nbsp;</p>
				  	<p align="center" class="STYLE2">学院领导签字（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/></p>
			      <div align="right" class="STYLE2">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" ><br/><br/><br/>学<br/>校<br/>意<br/>见<br/><br/><br/></td>
				  <td colspan='12' ><p align="center" class="STYLE1">同意获${rs.xmmc }单项奖学金。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/> </p>
				    <p align="right" class="STYLE2">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
				</tr>
				<tr>
				  <td colspan='' style="text-align:center" >备<br/>注<br/></td>
				  <td colspan='12' >1.本表一式两份，表中填写内容要求电脑输入并打印；<br>2.表中内容须填写完整，不得涂改</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
