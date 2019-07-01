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
		<center>
			<br />
			<br />
			<span style="font-size:28px;font-family:黑体">${rs.nd
				}年天津市红十字会博爱助学金登记表</span>
			<br />
			<br />
		</center>
		<table width="100%"  class="printtab" style="font-family:宋体;font-weight:bold;font-size:17px">
			<tr height="40px">
				<td width="19%" align="center">
					<bean:message key="lable.xb" />名称：
				</td>
				<td colspan="4" align="center"  style="font-size:14px">
					${rs.xymc }
				</td>
				<td align="center">
						填表<br/>日期
				</td>
				<td>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" width="15%">
						姓 名
				</td>
				<td align="center"  width="10%" style="font-size:14px">
						${rs.xm }
				</td>
				<td align="center" width="100px" width="12%" >
						性 别
				</td>
				<td align="center"   style="font-size:14px">
						${rs.xb }
				</td>
				<td align="center" width="15%">
						出生日期
				</td>
				<td align="center"  style="font-size:14px">
						${rs.csrq }
				</td>
				<td rowspan="4" width="11%">
					<img
						src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
						border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
						民 族
				</td>
				<td align="center" style="font-size:14px">
						${rs.mzmc }
				</td>
				<td align="center">
						政治<br/>面貌
				</td>
				<td align="center" style="font-size:14px">
						${rs.zzmmmc }
				</td>
				<td align="center">
						生源地
				</td>
				<td align="center" style="font-size:14px">
						${rs.syd }
				</td>

			</tr>
			<tr height="30px">
				<td align="center">
						身份证号
				</td>
				<td colspan="2" align="center" style="font-size:14px">
						${rs.sfzh }
				</td>
				<td align="center">
						专业
				</td>
				<td colspan="2" align="center" style="font-size:14px">
						${rs.zymc }
				</td>
			</tr>
			<tr height="40px">
				<td align="center">
						年级
				</td>
				<td align="center" style="font-size:14px">
						${rs.nj }
				</td>
				<td align="center">
						层次
				</td>
				<td align="center" style="font-size:14px">
						${rs.pycc }
				</td>
				<td align="center">
						家庭电话
				</td>
				<td align="center" style="font-size:14px">
						${rs.jtdh }
				</td>
			</tr>
			<tr>
				<td align="center">
						邮编
				</td>
				<td align="center" style="font-size:14px">
						${rs.jtyb }
				</td>
				<td align="center">
						家庭<br/>地址
				</td>
				<td colspan="4" align="center" style="font-size:14px">
						${rs.jtdz }
				</td>
			</tr>
			<tr height="120px">
				<td align="center">
					家
					<br />
					庭
					<br />
					困
					<br />
					难
					<br />
					情
					<br />
					况
					<br />
				</td>
				<td colspan="7" valign="top"
					style="word-break:break-all;font-size:14px" >
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.jtjbjjqk }
				</td>
			</tr>
			<tr height="140px">
				<td align="center">
					学
					<br />
					业
					<br />
					及
					<br />
					表
					<br />
					现
					<br />
				</td>
				<td colspan="7" valign="top"
					style="word-break:break-all;font-size:14px">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyjbx }
				</td>
			</tr>
			<tr height="120px">
				<td align="center">
					获
					<br />
					过
					<br />
					何
					<br />
					种
					<br />
					奖
					<br />
					励
					<br />
				</td>
				<td colspan="7" valign="top"
					style="word-break:break-all;font-size:14px">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.hghzjl }
				</td>
			</tr>
			<tr height="120px">
				<td align="center">
					学
					<br />
					院
					<br />
					推
					<br />
					荐
					<br />
					意
					<br />
					见
					<br />
				</td>
				<td colspan="3">
					<p style="height:90px;font-size:14px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="center">
						学生办盖章
					</p>
					<p align="center">

						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
					</p>
				</td>
				<td>
					<p align="center">
						学
						<br />
						工
						<br />
						部
						<br />
						意
						<br />
						见
						<br />
					</p>
				</td>
				<td colspan="4">
					<p align="center" style="height:90px;font-size:14px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="right">
						盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>



		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
