<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body>
		<p style="text-align: center;" align="center">
			<span style="font-size: 16.0pt; font-family: 仿宋_GB2312;">&nbsp;</span>
		</p>
		<p style="text-align: center;" align="center">
			<span style="font-size: 18.0pt; font-family: 方正大标宋简体;">${rs.pjnd }</span><span
				style="font-size: 18.0pt; font-family: 方正大标宋简体;">届贵州省普通高校优秀大学毕业生审批表<span></span>
			</span>
		</p>
		<p style="text-indent: 216.0pt;">
			<span style="font-size: 15.0pt; font-family: 仿宋;">填报时间：
				<span>${rs.nowTime }</span>
			</span>
		</p>
		<table border="1" cellpadding="0" cellspacing="0" class="printtab" 
			   style="text-align: center;font-size: 12.0pt; font-family: 仿宋_GB2312;" width="99%">
			<tbody>
				<tr class="nowrap">
					<td width="60">
						姓名
					</td>
					<td width="84">
						${rs.xm }
					</td>
					<td width="60">
						性别
					</td>
					<td width="48">
						${rs.xb }
					</td>
					<td width="60">
						出生<br/>日期
					</td>
					<td width="96">
						${rs.csrq }
					</td>
					<td width="60">
						民族
					</td>
					<td width="60">
						${rs.mzmc }
					</td>
					<td rowspan="3" width="120">
						<div id="stuImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
							<img style="width:100px;height:130px;" 
								src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
								border="0">
						</div>
					</td>
				</tr>
				<tr class="nowrap">
					<td width="60">
						政治<br/>面貌
					</td>
					<td width="84">
						${rs.zzmmmc }
					</td>
					<td colspan="2" width="108">
						现任社会<br/>工作职务
					</td>
					<td colspan="2" width="156">
						${rs.zw }
					</td>
					<td width="60">
						学历
					</td>
					<td width="60">
						${rs.xlmc }
					</td>
				</tr>
				<tr>
					<td width="60">
						毕业<br/>院校
					</td>
					<td colspan="3" width="192">
						贵州大学
					</td>
					<td width="60">
						专业<br/>名称
					</td>
					<td colspan="3" width="216">
						${rs.zymc }
					</td>
				</tr>
				<tr style="height:220px">
					<td colspan="9" valign="top" width="648" align="left">
						个人简历：
					</td>
				</tr>
				<tr style="height:220px">
					<td colspan="9" align="left" valign="top" width="648">
						获奖情况：
					</td>
				</tr>
				<tr style="height:280px">
					<td colspan="9" align="left" valign="top" width="648">
						主要事迹：
					</td>
				</tr>
			</tbody>
		</table>
		<p style="height:2px;">&nbsp;</p>
		<table border="1" class="printtab" 
			   style="text-align: center;font-size: 12.0pt;font-family: 仿宋_GB2312;" width="99%">
			<tbody>
				<tr>
					<td width="104" style="Writing-mode:tb-rl;">
						班级推荐意见
					</td>
					<td valign="top" width="544">
						<p style="height:160px;" align="left">
							${rs.xyyj }
						</p>
						<p style="text-indent: 30.0pt;">
							<span>辅导员或班主任签名：
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
							</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>
								<span>&nbsp;</span>2013
							</span>
							<span>年<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</span>月<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</span>日<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						院(系)评选意见
					</td>
					<td valign="top" width="544">
						<p style="height: 160px">
							${rs.xyyj }
						</p>
						<p style="text-indent: 30.0pt;">
							<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</span><span>（盖章）
							</span>
							</span>
						</p>
						<p>
							<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;&nbsp;&nbsp;</span>
							<span style="">2013</span>
							</span><span>年<span><span>&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
							</span>月<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</span>日<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						学校初审意见
					</td>
					<td valign="top" width="544">
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>经评审，并在校内公示<span>5</span>个工作日，无异议，现报请批准该同学为
							<span>${rs.pjnd }</span>届贵州省优秀大学毕业生。<span></span>
							</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p style="text-indent: 24.0pt;">
							<span>&nbsp;</span>
						</p>
						<p>
							<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							</span>
							<span>（盖章）</span>
						</p>
						<p>
							<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span>2013</span>
							</span>
							<span>年<span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp; </span>
								</span>月<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
								</span>日<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						省教育厅审批意见
					</td>
					<td valign="top" width="544">
						<p style="height: 160px;">
						</p>
						<p style="text-indent: 294.0pt;">
							<span>（盖章）<span></span>
							</span>
						</p>
						<p>
							<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span>&nbsp;&nbsp;</span><span>&nbsp;&nbsp;</span>
							</span>
							<span>年<span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</span>月<span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
							</span>日<span></span>
							</span>
						</p>
					</td>
				</tr>
				<tr>
					<td style="Writing-mode:tb-rl;" width="104">
						备&nbsp;&nbsp;注
					</td>
					<td style="height: 120px;" width="544">
					</td>
				</tr>
			</tbody>
		</table>
	</body>

</html>