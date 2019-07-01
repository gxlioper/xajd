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
	<body bgcolor="#FFFFFF" 
		style='text-justify-trim:punctuation' lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt'>
			<p align=center style='text-align:center'>
				<b><span style='font-size:18.0pt;font-family:黑体'>优秀家庭经济困难学生奖学金申请审批表</span>
				</b><span style='font-size:15.0pt;font-family:宋体'> （<u><span
						lang=EN-US>${rs.xn}</span>
				</u>学年）</span>
			</p>
			<p>
				<b><span style='font-size:12.0pt;font-family:宋体'>系：</span>
				</b><b><span lang=EN-US style='font-size:12.0pt'>${rs.xymc }</span>
				</b><b><span style='font-size:12.0pt;font-family:宋体'>专业：</span>
				</b><b><span style='font-size:12.0pt'> <span lang=EN-US>${rs.zymc }</span>
				</span>
				</b><b><span style='font-size:12.0pt;font-family:宋体'>班级：${rs.bjmc }</span>
				</b>
			</p>
			<table width="100%" id="rsT" class="printstyle" >
				<tr>
					<td width=67 rowspan=3  align="center">
						<p>
							<b><span style='font-size:12.0pt;font-family:宋体' >基<br/>本<br/>情<br/>况</span>
							</b>
						</p>
					</td>
					<td width=84  height="40px" align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>姓名</span>
						</p>
					</td>
					<td width=96  align="center">
						${rs.xm }
					</td>
					<td width=84 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>性别</span>
						</p>
					</td>
					<td width=84 colspan=2  align="center">
						${rs.xb }
					</td>
					<td width=84  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>出生年月</span>
						</p>
					</td>
					<td width=156  align="center">
						${rs.csrq }
					</td>
				</tr>
				<tr>
					<td width=84  height="40px" align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>学号</span>
						</p>
					</td>
					<td width=96  align="center">
						${rs.xh }
					</td>
					<td width=84 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>民族</span>
						</p>
					</td>
					<td width=84 colspan=2  align="center">
						${rs.mzmc }
					</td>
					<td width=84  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>入学时间</span>
						</p>
					</td>
					<td width=156  align="center">
						${rs.rxrq }
					</td>
				</tr>
				<tr>
					<td width=84  height="40px" align="center">
						<p>
							<span style='font-size:12.0pt;font-family:宋体'>申请等级</span>
						</p>
					</td>
					<td width=96 align="center">
						${rs.sqdj }
					</td>
					<td width=84 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size:12.0pt;font-family:宋体'>政治面貌</span>
						</p>
					</td>
					<td width=84 colspan=2  align="center">
						${rs.zzmmmc }
					</td>
					<td width=84  align="center">
						<p align=center style='text-align:center'>
							<span style='font-size: 12.0pt;font-family:宋体'>联系电话</span>
						</p>
					</td>
					<td width=156  align="center">
						${rs.lxdh }
					</td>
				</tr>
				<tr>
					<td width=67  align="center" height="140px">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:宋体'>学<br/>习<br/>等<br/>情<br/>况</span>
							</b>
						</p>
					</td>
					<td width=588 colspan=8  style="line-height: 20px" >
						<p style='line-height:150%;layout-grid-mode:char'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:宋体;margin-left: 10px'>贫困认定等级：</span>
								<span style='font-size:12.0pt;line-height:150%;margin-left: 10px'> <u><span lang=EN-US>${rs.xmzzjb }</span>
							</u>
							</span>
						</p>
						<p style='line-height:150%;layout-grid-mode:char'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:宋体;margin-left: 10px'>必修课、限选课最低分 ：</span>
								<span style='font-size:12.0pt;line-height:150%;margin-left: 10px'> <u><span lang=EN-US>${rs.mincj }</span>
							</u>
							</span>
						</p>
						<p style='line-height:150%;layout-grid-mode:char;margin-left:10px;'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:宋体'>综合测评成绩</span><u><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'>${rs.zcf }
							</span>
							</u><span style='font-size:12.0pt;line-height:150%;font-family:宋体'>分，排名</span><u><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'>${rs.zcfbjpm }/${rs.bjrs }
							</span>
							</u><span style='font-size:12.0pt;line-height:150%;font-family:宋体'>（名次</span><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'>/</span><span
								style='font-size:12.0pt;line-height:150%;font-family:宋体'>班级总人数）</span>
						</p>
						<p style='line-height:150%;layout-grid-mode:char;margin-left:10px;'>
							<span
								style='font-size:12.0pt;line-height:150%;font-family:宋体'>思想品德测评</span><span
								lang=EN-US style='font-size:12.0pt;line-height:150%'><u>${rs.dyf }</u></span><span
								style='font-size:12.0pt;line-height:150%;font-family:宋体'>分</span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=67  align="center" height="180px">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:宋体'>获<br/>奖<br/>情<br/>况</span>
							</b>
						</p>
					</td>
					<td width=588 colspan=8 align="left">
						<p style="margin-top: 10px;margin-left: 10px;height: 120px">
							<span style='font-size:12.0pt;font-family:宋体'>主要奖项：${rs.sqly }</span>
						</p>
						
						<p style="margin-bottom: 10px;margin-left: 10px">
							<span style='font-size:12.0pt;font-family:宋体'>其中，系级奖励</span><u><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;</span>
							</u><span style='font-size:12.0pt;font-family:宋体'>项；</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp; </span><span
								style='font-size:12.0pt; font-family:宋体'>院级奖励</span><u><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;</span>
							</u><span style='font-size:12.0pt;font-family:宋体'>项；</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp; </span><span
								style='font-size:12.0pt;font-family:宋体'>省级以上奖励</span><u><span
								lang=EN-US style='font-size:12.0pt'>&nbsp;
									&nbsp;&nbsp;&nbsp;</span>
							</u><span style='font-size:12.0pt;font-family:宋体'>项</span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=655 colspan=9  height="60px">
						<p>
							<span style='font-size:12.0pt;font-family:宋体'>本人保证以上所填情况真实有效。</span>
						</p>
						<br/>
						<p align="right" >
							<span style='font-size:12.0pt;font-family:宋体'>本人签名：<span
								lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>年<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp;
							</span>日</span>
						</p>
					</td>
				</tr>
				<tr  height="180px">
					<td width=67  align="center" >
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:宋体'>班<br/>委<br/>意<br/>见</span>
							</b>
						</p>
					</td>
					<td width=252 colspan=3 valign=bottom  >
						<p >
							<span style='font-family:宋体;margin-left: 100px'>班长签名：</span>
						</p>
						<br/>
						<p align=right style='text-align:right;'>
							<span style='font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;月<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>日</span>
						</p>
					</td>
					<td width=72 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:宋体'>班<br/>主<br/>任<br/>意<br/>见</span>
							</b>
						</p>
					</td>
					<td width=264 colspan=3  valign=bottom>
						<p>
							<span style='font-family:宋体;margin-left: 100px'>班主任签名：</span>
						</p>
						<br/>
						<p align=right style='text-align:right;'>
							<span style='font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;</span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>日</span>
						</p>
					</td>
				</tr>
				<tr  height="180px">
					<td width=67  align="center">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:宋体'>系<br/>初<br/>评<br/>等<br/>级</span>
							</b>
						</p>
					</td>
					<td width=252 colspan=3  valign=bottom>
						<p>
							<span style='font-family:宋体;margin-left: 100px'>系主任签名：</span>
						</p>
						<p>
							<span style='font-family:宋体;margin-left: 140px'>盖章</span>
						</p>
						<p align=right style='text-align:right;'>
							<span style='font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;</span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>日</span>
						</p>
					</td>
					<td width=72 colspan=2  align="center">
						<p align=center style='text-align:center'>
							<b><span style='font-size:12.0pt;font-family:宋体'>学<br/>院<br/>审<br/>批</span>
							</b>
						</p>
					</td>
					<td width=264 colspan=3  valign=bottom>
						<p>
							<span style='font-family:宋体;margin-left: 100px'>盖章</span>
						</p>
						<br/>
						<p align=right style='text-align:right;'>
							<span style='font-family:宋体'>年<span lang=EN-US>&nbsp;&nbsp;&nbsp;</span>月<span lang=EN-US>&nbsp;&nbsp;&nbsp; </span>日</span>
						</p>
					</td>
				</tr>
				<tr height=0>
					<td width=67 ></td>
					<td width=84 ></td>
					<td width=96 ></td>
					<td width=72 ></td>
					<td width=12 ></td>
					<td width=60 ></td>
					<td width=24 ></td>
					<td width=84 ></td>
					<td width=156 ></td>
				</tr>
			</table>
			
		</div>
	</body>
</html>


