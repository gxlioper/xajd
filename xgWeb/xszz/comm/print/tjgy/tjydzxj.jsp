<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
			<span style="font-size:22px;font-family:黑体">“天津移动大学生助学基金”受助学生登记表</span>
			<br />
			<br />
		</center>
		<table width="685px" border="0" align="center">
			<tr>
				<td>
					学校名称：天津工业大学
				</td>
				<td>
					填表日期：
				</td>
			</tr>
		</table>
		<table class="printtab">
			<tr height="40px">
				<td width="85" colspan="2">
					<p align="center">
						姓 名
					</p>
				</td>
				<td width="73">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td width="87" colspan="2">
					<p>
						性 别
					</p>
				</td>
				<td width="73" colspan="3">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td width="87" colspan="2">
					<p align="center">
						籍 贯
					</p>
				</td>
				<td width="60">
					<p align="center">
						${rs.jg }
					</p>
				</td>
				<td width="113" rowspan="4">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="40px">
				<td width="85" colspan="2">
					<p align="center">
						学 号
					</p>
				</td>
				<td width="73">
					<p>
						${rs.xh }
					</p>
				</td>
				<td width="87" colspan="2">
					<p>
						政治面貌
					</p>
				</td>
				<td width="113" colspan="4">
					<p>
						${rs.zzmmmc }
					</p>
				</td>
				<td width="87">
					<p align="center">
						民 族
					</p>
				</td>
				<td width="60">
					<p>
						${rs.mzmc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="85" colspan="2">
					<p>
						联系电话
					</p>
				</td>
				<td width="73">
					<p>
						${rs.lxdh }
					</p>
				</td>
				<td width="87" colspan="2">
					<p>
						家庭地址
					</p>
				</td>
				<td width="220" colspan="6">
					<p align="center">
						${rs.jtdz }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td width="185" colspan="4">
					<p>
						所在院系、专业、年级
					</p>
				</td>
				<td width="280" colspan="7">
					<p>
						${rs.xymc }、${rs.zymc }、${rs.nj }级
					</p>
				</td>
			</tr>
			<tr>
				<td width="38" align="center">
					<br/>
					申
					<br/><br/>
					请
					<br/><br/>
					理
					<br/><br/>
					由
					<br/>
				</td>
				<td width="540" colspan="11" valign="top">
					<p>
						（请详细说明父母职业、家庭人口、收入来源情况，另请注明学费来源）
					</p>
					<p style="height:100px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					</p>
					<p align="center">
						申请人签名：
						<u>
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
				<td width="38" align="center">
					<br/>
					申<br/><br/>
					请<br/><br/>
					人<br/><br/>
					承<br/><br/>
					诺<br/>
				</td>
				<td width="540" colspan="11">
					<p>
						1 、遵守国家法律，遵守高等院校学生行为准则和学校的各项规章制度；
					</p>
					<p>	<br/>
						2 、生活俭朴，勤奋学习；
					</p>
					<p><br/>
						3 、承诺参加天津移动社会实践活动；
					</p>
					<p><br/>
						4 、承诺在校期间加入学校的公益性学生社团，参与公益互助和社会实践活动。
					</p>
					<p align="center"><br/>
						承诺人签名：
						<u>
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
				<td width="38" align="center">
					<br/>
					学<br/><br/>
					院<br/><br/>
					意<br/><br/>
					见<br/>
				</td>
				<td width="540" colspan="11" valign="top">
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="center">
						<bean:message key="lable.xb" />评估人：
						<u>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</u>
						（<bean:message key="lable.xb" />学生办签章）
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
				<td width="38" align="center">
					<br/>
					学<br/><br/>
					校<br/><br/>
					意<br/><br/>
					见<br/>
				</td>
				<td width="226" colspan="5">
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p>
						盖章（学生处盖章）
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
				<td width="36">
					<p align="center">
						捐赠方意见
					</p>
				</td>
				<td width="278" colspan="5">
					<p align="center" style="height:100px">
						&nbsp;&nbsp;
					</p>
					<p>
						盖章
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
