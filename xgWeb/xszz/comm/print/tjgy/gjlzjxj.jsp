<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<html:html>
	<head>
		<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
		<style>
		.radic {
		position:relative;
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
		</style>
	</head>
<body>
	<p style="height:50px">&nbsp;</p>
		<center>
			<br />
			<br />
			<p align=center>
				<b><span
					style='font-size:21px;font-family:黑体;font-weight:normal;'>
						普通本科高校、高等职业学校国家励志奖学金申请表 </span>
				</b>
			</p>
			<br />
			<table class="printtab" style="font-family:仿宋_GB2312;font-size:14px;width:17.67cm;">
				<tr class="nowrap">
					<td rowspan="5" align="center" width="9%" valign="center">
						本人
						<br />
						情况
					</td>
					<td align="center" width="19%" valign="center">
						姓名
					</td>
					<td align="center" width="14.5%" valign="center">
						${rs.xm }
					</td>
					<td align="center" width="8.5%" valign="center">
						性别
					</td>
					<td align="center" width="9%" valign="center">
						${rs.kzzd1 }
					</td>
					<td align="center" width="12.5%" valign="center">
						出生年月
					</td>
					<td align="center" width="18.5%" valign="center">
						${rs.kzzd2 }
					</td>
					<td rowspan="4" align="center" width="110px" valign="center" style="padding:0px 0px 0px 0px">
						<img
							src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
							border="0" align="absmiddle" style="width:110px;height:140px" />
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						民族
					</td>
					<td align="center">
						${rs.kzzd3 }
					</td>
					<td align="center" style="padding:0px 0px 0px 0px">
						政治<br/>面貌
					</td>
					<td align="center">
						${rs.kzzd4 }
					</td>
					<td align="center">
						入学时间
					</td>
					<td align="center">
						${rs.kzzd5 }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						公民身份号码
					</td>
					<td align="center" colspan="3">
						${rs.kzzd6 }
					</td>
					<td align="center">
						联系电话
					</td>
					<td align="center">
						${rs.kzzd7 }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="left" colspan="4" style="position:relative;">
						&nbsp;
						<span style="position:absolute;right:180px;">${xxmc}</span>
						<span style="position:absolute;right:110px;">${rs.kzzd8}<bean:message key="lable.xb" /></span>
						<span style="position:absolute;right:60px;">${rs.kzzd9}系</span>
						<span style="position:absolute;right:5px;">${rs.kzzd10}班</span>
					</td>
					<td align="center">
						学号
					</td>
					<td align="center">
						${rs.xh }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						曾获何种奖励
					</td>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
					</td>
				</tr>
				<tr class="nowrap">
					<td rowspan="3" align="center">
						家
						<br />
						庭
						<br />
						经
						<br />
						济
						<br />
						情
						<br />
						况
					</td>
					<td align="center" class="nowrap">
						家庭户口
					</td>
					<td align="center" colspan="4" style="padding:0px 0px 0px 0px">
						<logic:notEqual name="rs" property="jthk" value="">
							<logic:equal value="城镇" property="jthk" name="rs">
								<span class="radic">A<em>&radic;</em></span>、城镇&nbsp;&nbsp;&nbsp;&nbsp;B、农村
							</logic:equal>
							<logic:equal value="农村" property="jthk" name="rs">
								A、城镇&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">B<em>&radic;</em></span>、农村
							</logic:equal>
						</logic:notEqual>
						<logic:equal name="rs" property="jthk" value="">
								A、城镇&nbsp;&nbsp;&nbsp;&nbsp;B、农村
						</logic:equal>
					</td>
					<td align="center">
						家庭人口总数
					</td>
					<td align="center">
						${rs.jtrs }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						家庭月总收入
					</td>
					<td align="center">
						${rs.jtyzsr }
					</td>
					<td align="center" colspan="2">
						人均月收入
					</td>
					<td align="center">
						${rs.jtrjysr }
					</td>
					<td align="center">
						收入来源
					</td>
					<td align="center">
						${rs.srly }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						家庭住址
					</td>
					<td align="center" colspan="4">
						${rs.jtdz }
					</td>
					<td align="center">
						邮政编码
					</td>
					<td align="center">
						${rs.jtyb }
					</td>
				</tr>
				<tr>
					<td colspan="8" valign="top">
						<p style="height:95px">
							<br/>
							学习成绩
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxcj }
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8" style="position:relative;">
						<p style="height:110px;">
							<br/>
							申请理由
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
						</p>
						&nbsp;
						<div align="right" style="position:absolute;right:98px;">
							申请人签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="width:150px;" >
								&nbsp;&nbsp;&nbsp;&nbsp;
								年&nbsp;&nbsp;月&nbsp;&nbsp;日
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8"  style="position:relative;">
						<p style="height:115px;">
							<br />
							学校资助管理机构评审意见：
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
						</p>
						&nbsp;
						<div align="right" style="position:absolute;right:95px;">
							（公章）
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="width:150px;" align="right">
								<logic:notEqual name="rs" property="shsj1" value="">
									${rs.shsj1 }
								</logic:notEqual>
								<logic:equal name="rs" property="shsj1" value="">
									&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;月&nbsp;&nbsp;日
								</logic:equal>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8" style="position:relative;">
						<p style="height:115px;">
							<br />
							学校审核意见：
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }
						</p>
						&nbsp;
						<div align="right" style="position:absolute;right:92px;">
							（公章） 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="width:150px;" align="right">
								<logic:notEqual name="rs" property="shsj2" value="">
									${rs.shsj2 }
								</logic:notEqual>
								<logic:equal name="rs" property="shsj2" value="">
									&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;月&nbsp;&nbsp;日
								</logic:equal>
							</span>
						</div>
					</td>

				</tr>
			</table>
	</center>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>

</body>
</html:html>
