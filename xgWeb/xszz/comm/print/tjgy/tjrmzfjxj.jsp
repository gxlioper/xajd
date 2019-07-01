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
		<br/>
		<br/>
		<br/>
			<span style="font-size:22px;font-family:黑体">天津市人民政府奖学金申请表</span>
			<br />
			<br />
		</center>
		<table style="font-family:仿宋_GB2312;font-size:14px;" width="100%"
			border="0" align="center">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;学校：天津工业大学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填表日期：
				</td>
			</tr>
		</table>
		<table class="printtab" style="font-family:仿宋_GB2312;font-size:14px;">
			<tr height="30px">
				<td rowspan="5" align="center" width="7%">
					本
					<br />
					人
					<br />
					情
					<br />
					况
				</td>
				<td align="center" width="12.5%">
					姓名
				</td>
				<td align="center" width="13.5%">
					${rs.xm }
				</td>
				<td align="center" width="10%">
					性别
				</td>
				<td align="center" width="7%">
					${rs.xb }
				</td>
				<td align="center" width="11%">
					出生
					<br />
					年月
				</td>
				<td align="center" width="18%">
					${rs.csrq }
				</td>
				<td align="center" width="10%">
					民族
				</td>
				<td align="center" width="14%">
					${rs.mzmc }
				</td>
				<td rowspan="3" align="center">
					<img
						src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
						border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="center" colspan="2">
					公民身份号码
				</td>
				<td align="center" colspan="3">
					${rs.sfzh }
				</td>
				<td align="center">
					入学时间
				</td>
				<td align="center" colspan="2">
					${rs.rxrq }
				</td>
			</tr>
			<tr height="30px">
				<td align="center" colspan="5">
					${xxmc }&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;
				</td>
				<td align="center">
					学号
				</td>
				<td align="center" colspan="2">
					${rs.xh }
				</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">
					曾获何种奖励
				</td>
				<td colspan="7">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.hghzjl }
				</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">
					工商银行卡号
				</td>
				<td colspan="7">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.yhkh }
				</td>
			</tr>
			<tr align="center" height="30px">
				<td colspan="3">
					本学年综合测评班级或专业排名
				</td>
				<td colspan="3">
					${rs.zhcpbjhzypm }
				</td>
				<td colspan="2">
					本学年成绩班级或专业排名
				</td>
				<td colspan="2">
					${rs.cjbjhzypm }
				</td>
			</tr>
			<tr height="150px">
				<td align="center" colspan="2">
					本学年成绩
					<br />
					（课程名称
					<br />
					及分数）
				</td>
				<td colspan="8" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxcj }
				</td>
			</tr>
			<tr>
				<td colspan="11" valign="top">
				<br/>
					学校资助管理机构评审意见：
					<p style="height:30px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="left" style="height:30px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
						<span style="font-size:17px">经评审，该同学符合天津市人民政府奖学金申请条件，同意推荐!</span>
					</p>
					<p style="height:30px">
					<p align="right">
						公章： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="11" valign="top">
				<br/>
					学校审核意见：
					<p style="height:60px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="left" style="height:70px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
						<span style="font-size:17px">经评审，并在
						<u>全校</u>范围内公示
						<U>&nbsp;&nbsp;五&nbsp;&nbsp;</U> 天，无异议，现报请同意该同学获得
						<u>&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp;</u> 学年度天津市人民政府奖学金</span>
					</p>
					<p align="right">
						公章： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>



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
