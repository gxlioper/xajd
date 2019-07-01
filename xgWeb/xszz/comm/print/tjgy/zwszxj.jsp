<%@ page language="java"  pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
			<span style="font-size:22px;font-family:黑体">“真维斯基金”申请表</span>
			<br />
			<br />
		</center>
		<table class="printtab">
			<tr>
				<td width="91" colspan="2">
					<p align="center">
						姓 名
					</p>
				</td>
				<td width="203" colspan="3">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="70" colspan="3">
					<p align="center">
						性 别
					</p>
				</td>
				<td width="119" colspan="3">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td width="117" colspan="2" rowspan="3">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr>
				<td width="91" colspan="2">
					<p align="center">
						姓名汉语拼音
					</p>
				</td>
				<td width="392" colspan="9">
					<p align="center">
						${rs.xmpy }
					</p>
				</td>
			</tr>
			<tr>
				<td width="91" colspan="2">
					<p align="center">
						出生日期
					</p>
				</td>
				<td width="203" colspan="3">
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td width="70" colspan="3">
					<p align="center">
						民 族
					</p>
				</td>
				<td width="119" colspan="3">
					<p align="center">
						${rs.mzmc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="91" colspan="2">
					<p align="center">
						家庭地址
					</p>
				</td>
				<td width="273" colspan="6">
					<p align="center">
						${rs.jtdz }
					</p>
				</td>
				<td width="119" colspan="3">
					<p align="center">
						邮政编码
					</p>
				</td>
				<td width="117" colspan="2">
					<p align="center">
						${rs.jtyb }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="36" rowspan="4">
					<p align="center">
						就
					</p>
					<p align="center">
						读
					</p>
					<p align="center">
						学
					</p>
					<p align="center">
						校
					</p>
				</td>
				<td width="120" colspan="2">
					<p align="center">
						院校名称
					</p>
				</td>
				<td width="208" colspan="5">
					<p align="center">
						${xxmc }
					</p>
				</td>
				<td width="63" colspan="2">
					<p align="center">
						系
					</p>
				</td>
				<td width="173" colspan="3">
					<p align="center">
						${rs.xymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="120" colspan="2">
					<p align="center">
						专 业
					</p>
				</td>
				<td width="208" colspan="5">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
				<td width="63" colspan="2">
					<p align="center">
						班 级
					</p>
				</td>
				<td width="173" colspan="3">
					<p align="center">
						${rs.bjmc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="120" colspan="2">
					<p align="center">
						学校通讯地址
					</p>
				</td>
				<td width="250" colspan="6">
					<p align="center">
						${rs.xxdz }
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						邮政编码
					</p>
				</td>
				<td width="110">
					<p align="center">
						${rs.xxyb }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="160" colspan="3">
					<p align="center">
						联系电话（区号）
					</p>
				</td>
				<td width="126" colspan="3">
					<p align="center">
						${rs.xxlxdh }
					</p>
				</td>
				<td width="105" colspan="3">
					<p align="center">
						学生宿舍电话
					</p>
				</td>
				<td width="173" colspan="3">
					<p>
						${rs.qsdh }
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						申请理由
					</p>
				</td>
				<td width="564" colspan="12" valign="top">
					<p>
						<strong>（请详细说明父母职业、家庭人口、收入来源情况；另请注明学费来源，包括学费减免及获得奖学金、助学金状况。——可另附页填写）
						</strong>
					</p>
					<p style="height:100px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					</p>
					<p align="center">
						申请人签名：<u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
						&nbsp;&nbsp;时间：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						申请人承诺
					</p>
				</td>
				<td width="564" colspan="12" valign="top">
					<p>
						1 、参加真维斯店铺“定点”实习工作，每次不少于 8 个小时；大一学生每学年不少于 40 个小时，大二、大三学生每学年不少于 80
						个小时； 原则上安排在“五一”、“十一”、寒假和暑假等节假日集中完成，特殊情况可由受助生与真维斯店协商确定；
						勤工俭学期间遵守真维斯店各项规章制度，同时享受普通计时工各项待遇。
					</p>
					<p>
						2 、 学习期间积极参加各种社会公益活动；毕业工作后，自愿向基金办公室提供联络方式 ,
						在有能力时自愿向希望工程捐资，帮助其他需要帮助的学生。
					</p>
					<p>
						&nbsp;
					</p>
					<p align="center">
						承诺人签名： <u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
						&nbsp;&nbsp;时间：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						初评意见
					</p>
				</td>
				<td width="564" colspan="12" valign="top">
					<p align="center" style="height:80px">
						&nbsp;
						&nbsp;
					</p>
					<p align="center">
						院系评估人： <u>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
						&nbsp;&nbsp;时间：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 日
					</p>
				</td>
			</tr>
			<tr>
				<td width="36" valign="center">
					<p align="center">
						复审意见
					</p>
				</td>
				<td width="265" colspan="5" valign="top">
					<p align="center" style="height:80px">
						&nbsp;
					</p>
					<p>
						复审人：<u>
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;
							</u> （大学团委和学工部盖章）
					</p>
					<p>
						时间：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 日
					</p>
				</td>
				<td width="299" colspan="7" valign="top">
					<p align="center" style="height:80px">
						&nbsp;
					</p>
					<p>
						终审人：<u>
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;
							</u> （省级青基会盖章）
					</p>
					<p align="left">
						时间：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 年
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 月
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 日
					</p>
				</td>
			</tr>
		</table>
		<table width="685px" border="0" align="center">
			<tr>
				<td>
					<p>注：此表可以复制，一式三份，大学、 真维斯店铺和 中国青基会各存一份原件，省级青基会留复</p>
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
