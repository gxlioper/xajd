<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
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
			<span style="font-size:22px;font-family:黑体">老党员资助家庭经济困难学生具体情况登记表</span>
			<br />
			<br />
		</center>
		<table width="100%" class="printtab" style="font-weight:bold;font-size:18px">
			<tr height="40px">
				<td align="center" width="18%">
					学校名称：
				</td>
				<td colspan="4" align="center" style="font-size:14px">
				
				${xxmc }
				</td>
				<td align="center">
					填表<br/>日期
				</td>
				<td >
				</td>
			</tr>
			<tr height="40px">
				<td>
					<p align="center" width="15%">
						姓 名
					</p>
				</td>
				<td>
					<p align="center" width="10%" style="font-size:14px">
						${rs.xm }
					</p>
				</td>
				<td align="center" width="12%">
						性 别
				</td>
				<td>
					<p align="center" style="font-size:14px">
						${rs.xb }
					</p>
				</td>
				<td align="center" width="15%">
						出生日期
				</td>
				<td>
					<p align="center" style="font-size:14px">
						${rs.csrq }
					</p>
				</td>
				<td width="113" rowspan="4" width="11%">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
			</tr>
			<tr height="40px">
			   <td width="15%">
					<p align="center">
						民 族
					</p>
				</td>
				<td>
					<p align="center" width="15%" style="font-size:14px">
						${rs.mzmc }
					</p>
				</td>
				<td >
					<p align="center" width="15%">
						政治<br/>面貌
					</p>
				</td>
				<td>
					<p align="center" width="15%" style="font-size:14px">
						${rs.zzmmmc }
					</p>
				</td>
				<td>
					<p align="center" width="15%">
						籍贯
					</p>
				</td>
				<td >
					<p align="center" width="15%" style="font-size:14px">
						${rs.jg }
					</p>
				</td>
				
			</tr>
			<tr height="40px">
			<td align="center">
					<p align="center">
						院系
					</p>
				</td>
				<td  colspan="2" style="font-size:14px">
					<p align="center" >
						${rs.xymc }
					</p>
				</td>
				<td>
					<p align="center">
						专业
					</p>
				</td>
				<td colspan="2" style="font-size:14px">
					<p align="center" >
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr height="40px">
				<td>
					<p align="center">
						年级
					</p>
				</td>
				<td style="font-size:14px">
					<p align="center">
						${rs.nj }
					</p>
				</td>
				<td>
					<p align="center">
						层次
					</p>
				</td>
				<td style="font-size:14px">
					<p align="center">
						${rs.pycc }
					</p>
				</td>
				<td>
					<p align="center">
						家庭电话
					</p>
				</td>
				<td style="font-size:14px">
					<p align="center">
						${rs.jtdh }
					</p>
				</td>
			</tr>
			<tr height="40px">
			<td >
					<p align="center">
						邮编
					</p>
				</td>
				<td >
					<p align="center">
						${rs.jtyb }
					</p>
				</td>
				<td >
					<p align="center">
						家庭<br/>地址
					</p>
				</td>
				<td  colspan="4">
					<p align="center">
						${rs.jtdz }
					</p>
				</td>
			</tr>
			<tr height="160px">
				<td  align="center">
					家
					<br/>
					庭
					<br/>
					困
					<br/>
					难
					<br/>
					情
					<br/>
					况
					<br/>
				</td>
				<td colspan="7" valign="top" style="word-break:break-all">
				    &nbsp;&nbsp;&nbsp;&nbsp; ${rs.jtjbjjqk }
				</td>
			</tr>
			<tr  height="160px">
				<td  align="center">
					学<br/>
					业<br/>
					及<br/>
					表<br/>
					现<br/>
				</td>
				<td colspan="7" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyjbx }
				</td>
			</tr>
			<tr  height="160px">
				<td  align="center">
					获<br/>
					过<br/>
					何<br/>
					种<br/>
					奖<br/>
					励<br/>
				</td>
				<td colspan="7" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.hghzjl }
				</td>
			</tr>
			<tr  height="22px">
				<td  align="center">
					学<br/>
					校<br/>
					推<br/>
					荐<br/>
					意<br/>
					见<br/>
				</td>
				<td colspan="3">
					<p style="height:80px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="center">
						学校公章
					</p>
					<p align="center">
						
					</p>
				</td>
				<td>
					<p align="center">
						市<br/>
					          教<br/>
					          委<br/>
					          意<br/>
					          见<br/>
					</p>
				</td>
				<td colspan="4">
					<p align="center" style="height:80px">
						&nbsp;&nbsp;&nbsp;&nbsp;
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
