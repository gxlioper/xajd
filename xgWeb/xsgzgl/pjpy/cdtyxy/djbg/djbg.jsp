<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
<!-- author：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<table width="80%" border="0" id="theTable" align="center">
			<tr>
				<td align="center" style="height: 50px;vertical-align: bottom">
					
					<span style="font-size: 24px;font-family: 黑体"><B>成都体育学院${rs.xmmc }登记表</B></span>	
				</td>
			</tr>
			<tr>
				<td align="right" style="height: 30px;vertical-align: center">
					<span style="font-size:15px;margin-right: 15px">（学年:&nbsp;&nbsp;<span lang=EN-US>${rs.xn}</span>&nbsp;&nbsp;）</span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" id="rsT" class="printstyle">
						<tr style="height: 70px" align=center valign="middle">
							<td  colspan=3>
								姓名
							</td>
							<td colspan=2>
								${rs.xm }
							</td>
							<td >
								性别
							</td>
							<td >
								${rs.xb }
							</td>
							<td >
								年龄
							</td>
							<td colspan=2 >
								${rs.nl }
							</td>
							<td colspan=2>
								政治<br/>面貌
							</td>
							<td >
								${rs.zzmmmc }
							</td>
						</tr>
						<tr style="height: 45px">
							<td colspan=4>
								系、级、班
							</td>
							<td colspan=5 >
								${rs.xymc}${rs.nj}${rs.bjmc}
							</td>
							<td colspan=2>
								学号
							</td>
							<td colspan=2 >
								${rs.xh}
							</td>
						</tr>
						<tr>
							<td style="height: 260px;width:7%" >
								主<br/><br/>要<br/><br/>事<br/><br/>迹
							</td>
							<td  colspan=12 >
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px">
								班&nbsp;级<br/>意&nbsp;见
							</td>
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:宋体;margin-right: 50px;'>签字（章）</p><br/>
								<span style="margin-right: 30px">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px" >
								年&nbsp;级<br/>意&nbsp;见
							</td>
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:宋体;margin-right: 50px;'>签字（章）</p><br/>
								<span style="margin-right: 30px">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px">
								系&nbsp;部<br/>意&nbsp;见
							</td>
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:宋体;margin-right: 50px;'>签字（章）</p><br/>
								<span style="margin-right: 30px">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px">
								学&nbsp;院<br/>意&nbsp;见
							</td>
							<td colspan=11 valign=bottom align="right" style="height: 80px">
								<p style='font-family:宋体;margin-right: 50px;'>签字（章）</p><br/>
								<span style="margin-right: 30px">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
							</td>
						</tr>
						<tr>
							<td colspan=2 style="height: 80px" >
								备&nbsp;注
							</td>
							
							<td  colspan=11 valign=bottom align="right">
								<p style='font-family:宋体;margin-right: 50px;'>签字（章）</p><br/>
								<span style="margin-right: 30px">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span>
							</td>
						</tr>
						<tr height=0>
							<td width=6%></td>
							<td width=2%></td>
							<td width=4%></td>
							<td width=5%></td>
							<td width=5%></td>
							<td width=11%></td>
							<td width=12%></td>
							<td width=11%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=18%></td>
						</tr>
					</table>
					<span style="float: left;margin-left: 10px">此表由组织填写，一式两份。</span><span style="float: right">学生处制</span>
				</td>
			</tr>
		</table>
	</body>
</html>


