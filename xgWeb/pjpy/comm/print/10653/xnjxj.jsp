<%@ page language="java" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<p align="center">
			<strong style="font-size:24px;font-family:黑体">成都体育学院学生奖学金登记表
			</strong>
		</p>
		<p align="center" style="font-size:14px;">
			${rs.zymc }&nbsp;系&nbsp;${rs.nj }&nbsp;级 （&nbsp;${rs.pjxn
			}&nbsp;学年第&nbsp;${rs.pjxqmc }&nbsp;学期 ）
		</p>
		<table class="printtab" width="100%" style="font-size:14px;">
			<tr>
				<td width="8%" colspan="2" align="center">
					姓名
				</td>
				<td width="13%" colspan="2" align="center">
					${rs.xm }
				</td>
				<td width="6%" rowspan="2" align="center">
					性<br/>
					别
				</td>
				<td width="7%" rowspan="2" align="center">
					${rs.xb }
				</td>
				<td width="6%" rowspan="2" align="center">
					班
				</td>
				<td width="12%" colspan="2" rowspan="2" align="center">
					${rs.bjmc }
				</td>
				<td width="10%" colspan="3" rowspan="2" align="center">
					是&nbsp;&nbsp;否<br/>党团员
				</td>
				<td width="13%" colspan="3" rowspan="2" align="center">
					${rs.是否党团员 }
				</td>
				<td width="13%" colspan="2" rowspan="2" align="center">
					担任何<br/>种干部
				</td>
				<td width="12%" colspan="2" rowspan="2" align="center">
					${rs.担任何种干部 }
				</td>
			</tr>
			<tr>
				<td colspan="2"  align="center">
					学号
				</td>
				<td colspan="2"  align="center">
					${rs.xh }
				</td>
			</tr>
			<tr height="50px">
				<td colspan="3"  align="center" valign="top">
					同级专业人数
				</td>
				<td colspan="5"  align="center">
					${rs.同级专业人数 }
				</td>
				<td colspan="5"  align="center">
					综合测评排名
				</td>
				<td colspan="6"  align="center">
					${rs.zcpm }
				</td>
			</tr>
			<tr height="50px">
				<td colspan="4"  align="center">
					奖学金类别
				</td>
				<td colspan="2"  align="center">
					${rs.xmxz }
				</td>
				<td colspan="5"  align="center">
					奖学金等级
				</td>
				<td colspan="3"  align="center">
				</td>
				<td colspan="2"  align="center">
					奖励金额
				</td>
				<td colspan="3"  align="center">
					${rs.xmje }
				</td>
			</tr>
			<tr height="220px">
				<td align="center">
					主<br/>要<br/>事<br/>迹
				</td>
				<td colspan="18">
					<p valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.主要事迹 }
					</p>
				</td>
			</tr>
			<tr  height="220px">
				<td align="center">
					年<br/>级<br/>审<br/>查<br/>意<br/>见
				</td>
				<td colspan="18">
					<p valign="top" style="height:220px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj1 }
					</p>
					<p align="right">
						年级主任签字（章） &nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr height="220px">
				<td align="center">
					系<br/>部<br/>意<br/>见
				</td>
				<td colspan="9">
					<p valign="top" style="height:200px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shyj2 }
					</p>
					<p align="center" valign="bottom">
						（公章） 年 月 日
					</p>
				</td>
				<td colspan="9">
					<p valign="top" style="height:10px" align="center">
						学生处审批意见
					</p>
					<p style="height:210px"  align="center">
					<br/><br/>
						${rs.shyj3 }
					</p>
					<p align="center" valign="bottom">
						（公章） 年 月 日
					</p>
				</td>
			</tr>
		</table>
		<p style="font-size:12px;">
			注：此表由组织填写，一式两份。 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			学生处制
		</p>



		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
