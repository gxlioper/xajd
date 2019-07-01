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
					<h2>
						附件2&nbsp;&nbsp;&nbsp;&nbsp;高等学校家庭经济困难学生认定申请表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					<h3>
						学校：宁波城市职业技术学院（宁波大学职教学院）
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="6%"></td>
							<td width="8%"></td>
							<td width="11%"></td>
							<td width="11%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td width="8%"></td>
							<td width="8%"></td>
							<td></td>
							<td width="9%"></td>
						</tr>
						<tr height="40px">
							<td rowspan="4" align="center"><strong>学<br/>生<br/>本<br/>人<br/>基<br/>本<br/>情<br/>况</strong></td>
							<td colspan="2" align="center">姓&nbsp;&nbsp;名</td>
							<td align="center">${rs.xm }&nbsp;</td>
							<td colspan="2" align="center">性别</td>
							<td align="center" colspan="2">${rs.xb }&nbsp;</td>
							<td align="center" colspan="2">学号</td>
							<td align="center" colspan="2">${rs.xh }&nbsp;</td>
							<td align="center">民族</td>
							<td align="center">${rs.mzmc }&nbsp;</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">身份证号码</td>
							<td align="center" colspan="3">${rs.sfzh }&nbsp;</td>
							<td align="center" colspan="2">政治面貌</td>
							<td align="center" colspan="2">${rs.zzmmmc }&nbsp;</td>
							<td align="center" colspan="2">家庭人均年收入</td>
							<td align="center" colspan="2">${rs.jtrjsr }&nbsp;&nbsp;元</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">学  院</td>
							<td align="center" colspan="2">${rs.xymc }&nbsp;</td>
							<td align="center">系</td>
							<td align="center" colspan="4"></td>
							<td align="center">专业</td>
							<td align="center" colspan="3">${rs.zymc }&nbsp;</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">家庭联系电话</td>
							<td align="center" colspan="3">${rs.jtdh }&nbsp;</td>
							<td align="center" colspan="3">在校联系电话</td>
							<td align="center" colspan="5">${rs.lxdh }&nbsp;</td>
						</tr>
						<tr>
							<td align="center"><strong>学<br/>生<br/>陈<br/>述<br/>申<br/>请<br/>认<br/>定<br/>理<br/>由</strong></td>
							<td colspan="13">
								<p style="height:160px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.rdly }
								</p>
								<div align="right">
									学生签字：
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 ____________年_______月_____日
								</div>
								<div align="left">
									<strong>注：可另附详细情况说明。</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="3"><strong>民<br/>主<br/>评<br/>议</strong> </td>
							<td align="center" rowspan="3">推<br/>荐<br/>档<br/>次</td>
							<td colspan="2">A.家庭经济困难 □</td>
							<td colspan="2" rowspan="3" align="center">陈<br/>述<br/>理<br/>由</td>
							<td rowspan="3" colspan="8">
								<p style="height:120px">
								
								</p>
								评议小组组长（班主任）
								<div align="right">
									签字：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									_______年_____月____日&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">B.家庭经济特殊困难  □</td>
						</tr>
						<tr>
							<td colspan="2">C.家庭经济不困难   □</td>
						</tr>
						<tr>
							<td align="center"><strong>认<br/>定<br/>决<br/>定</strong> </td>
							<td align="center"><bean:message key="lable.xb" /><br/>意见</td>
							<td colspan="4">
								经评议小组推荐、本<bean:message key="lable.xb" />认真审<br/>核后，<br/>
								□  同意评议小组意见。<br/>
								□  不同意评议小组意见。调整<br/>
								为_______________。
								<br/><br/><br/>
								工作组组长（学生工作负责人）<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								签字：<br/><br/><br/>
								<div align="right">
								       	_______年___月__日&nbsp;<br/>
								       	（加盖<bean:message key="lable.xb" />公章）&nbsp;&nbsp;&nbsp;&nbsp;
								       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td align="center">学校<br/>学生<br/>资助<br/>管理<br/>机构<br/>意见</td>
							<td colspan="7">
								经学生所在<bean:message key="lable.xb" />提请，本机构认真核实，<br/>
								□  同意工作组和评议小组意见。<br/>
								□  不同意工作组和评议小组意见。调整为：<br/>
								_______________________________。<br/>
								负责人签字：<br/><br/><br/><br/><br/><br/>
								       <div align="right">
								       	_________年______月____日&nbsp;<br/>
								       	（加盖部门公章）&nbsp;&nbsp;&nbsp;&nbsp;
								       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
