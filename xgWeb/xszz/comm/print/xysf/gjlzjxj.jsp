<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h3>
						信阳师范学院国家励志奖学金申请审批表
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center" style="font-size:15px;">
						（${rs.xn }学年）
				</td>
			</tr>
			<tr>
			<td align="center" style="font-size:15px;">
						<p align=center><bean:message key="lable.xb" />：${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：${rs.zymc }&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级：${rs.bjmc }</p> 
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td rowspan="4" align="center" width="7%">
								基
								<br />
								本
								<br />
								情
								<br />
								况
							</td>
							<td align="center" width="16%">
								姓名
							</td>
							<td align="center" width="16%">${rs.xm }</td>
							<td align="center" width="10%">
								性别
							</td>
							<td align="center" width="8%">${rs.xb }</td>
							<td align="center" width="14%">
								出生年月
							</td>
							<td align="center" width="14%">${rs.csrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								学号
							</td>
							<td align="center">${rs.xh }</td>
							<td align="center">
								民族
							</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">
								入学时间
							</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								身份证号
							</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">
								政治面貌
							</td>
							<td align="center">${rs.zzmmmc }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								经济困难认定档次
							</td>
							<td align="center" colspan="3">${rs.knjb }</td>
							<td align="center">
								联系电话
							</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr  height="40px">
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
							<td align="center">
								家庭户口
							</td>
							<td align="center" colspan="3">
									A、<logic:equal value="城镇" property="jthk" name="rs">
										√
									</logic:equal>城镇
									&nbsp;&nbsp;
									B、<logic:equal value="农村" property="jthk" name="rs">
										√
									</logic:equal>
									农村
							</td>
							<td align="center">
								邮政编码
							</td>
							<td align="center">
								${rs.jtyb }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭人口总数
							</td>
							<td align="center">
								${rs.jtrs }
							</td>
							<td align="center">
								家庭年总收入
							</td>
							<td align="center">
								${rs.jtnzsr }
							</td>
							<td align="center">
								收入来源
							</td>
							<td align="center">
								${rs.srly }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								家庭住址
							</td>
							<td align="center" colspan="5">
								${rs.jtdz }
							</td>
						
						</tr>
						<tr  height="40px">
							<td align="center">
								综
								<br />
								合
								<br />
								测
								<br />
								评
							</td>
							<td align="center" colspan="6" >
								本年级专业总人数：<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>         ；学习成绩排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>         ；综合考评排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>       。
							</td>
							
						</tr>
					<tr>
							<td align="center">
								获
								<br />
								奖
								<br />
								情
								<br />
								况
								<br />
							</td>
							<td colspan="6">
								<p style="height:220px">
									主要奖项：
									</br>
										${rs.sqly}
								</p>
							
								<div align="right" style="margin-bottom: px20">
									其中，院级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>      ； 校级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>      项； 省级以上奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>     项。
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
								申
								<br />
								请
								<br />
								理
								<br />
								由
								<br />
								︵
								<br />
								家
								<br />
								庭
								<br />
								情
								<br />
								况
								<br />
								、
								<br />
								在
								<br />
								校
								<br />
								表
								<br />
								现
								<br />
								、
								<br />
								５
								<br />
								０
								<br />
								０
								<br />
								字
								<br />
								以
								<br />
								内
								<br />
								︶
							</td>
							<td colspan="6">
								<p style="height:220px">
										${rs.sqly}
								</p>
							
								<div align="right" style="margin-bottom: px20">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />意见：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
								</p>
								<div align="right">
									（公章）
									签名
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="4">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;学校意见：
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
								</p>
								<div align="right">
									（公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
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
