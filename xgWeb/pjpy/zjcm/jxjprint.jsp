<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<html:form action="/pjpyzjcmwh" method="post">
		<div align="left" style="font-size:18px;">附件:</div>
		<div align="center" style="font-size:20px;font:'黑体' "><b>${xxmc }"飘萍"奖学金申请审批表</b></div>
		<div align="center">(${nd }年)</div>
		<bean:message key="lable.xsgzyxpzxy" />名称:${rs.xymc }
		<table width="100%" class="printstyle">
			<tr>
				<td width="5%" rowspan="6">
					<p align="center">
						<strong>基<br /> 本<br /> 情<br /> 况</strong>
					</p>
				</td>
				<td width="11%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="14%" align="center">
					${rs.xm }
				</td>
				<td width="12%" height="">
					<div align="center">
						性别
					</div>
				</td>
				<td width="15%" align="center">
					${rs.xb }
				</td>
				<td colspan="2">
					<div align="center">
						出生年月
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.csrq }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学号
					</div>
				</td>
				<td align="center">
					${rs.xh }
				</td>
				<td>
					<div align="center">
						民族
					</div>
				</td>
				<td align="center">
					${rs.mzmc }
				</td>
				<td colspan="2">
					<div align="center">
						入学时间
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.rxrq }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						专业
					</div>
				</td>
				<td align="center">
					${rs.zymc }
				</td>
				<td>
					<div align="center">
						学制
					</div>
				</td>
				<td align="center">
					${rs.xz }
				</td>
				<td colspan="2">
					<div align="center">
						毕业时间
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						${rs.bysj }
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td align="center">
					${rs.bjmc }
				</td>
				<td>
					<div align="center">
						政治面貌
					</div>
				</td>
				<td align="center">
					${rs.zzmmmc }
				</td>
				<td colspan="2">
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="3" align="center">
					${rs.lxdh }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						身份证号
					</div>
				</td>
				<td colspan="8" align="left">
					${rs.sfzh }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<p>
							家庭详细地址
						</p>
					</div>
				</td>
				<td colspan="8">
					${rs.jtdz }
				</td>
			</tr>
			<tr>
				<td rowspan="3">
					<div align="center">
						<strong>学<br /> 习<br /> 等<br /> 情<br /> 况</strong>
					</div>
				</td>
				<td height="60" colspan="9">
					<div align="left">
						本学年必修课程数
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门.其中,优秀
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门,良好
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门,
						<br/>
						英语成绩:&nbsp;
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>,第二次外语成绩:&nbsp;
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>.
						<br>
						(以上如无此项,填写&quot;无&quot;)
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第一学期
					</div>
				</td>
				<td>
					<p align="center">
						德育排名<br />名次/总人数
					</p>
				</td>
				<td >
					<div align="center"></div>
				</td>
				<td>
					<div align="center">智育排名<br />名次/总人数
					</div>
				</td>
				<td width="40px">
					&nbsp;
				</td>
				<td width="15%">
					<div align="center">
						综合排名<br />名次/总人数
					</div>
				</td>
				<td width="">
					&nbsp;
				</td>
				<td width="15%">
					<div align="center">
						奖学金<br />等级
					</div>
				</td>
				<td width="">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						第二学期
					</div>
				</td>
				<td>
					<div align="center">
						德育排名<br />名次/总人数
					</div>
				</td>
				<td>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						智育排名<br />名次/总人数
					</div>
				</td>
				<td>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						综合排名<br />名次/总人数
					</div>
				</td>
				<td>
					<div align="center"></div>
				</td>
				<td>
					<div align="center">
						奖学金<br />等级
					</div>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					
					<div align="center">
						<strong>家<br /> 庭<br /> 情<br /> 况<br /> 简<br /> 述</strong>
					</div>
				
				</td>
				<td colspan="9">
					<div align="center"></div>
				</td>
			</tr>
			<tr>
				<td>
					
					<div align="center">
						<strong>申<br /> 请<br /> 理<br /> 由<br /> </strong>
						<div align="center">
							<strong>︵</strong>
						</div>
						<strong>全<br /> 面<br /> 反<br /> 映<br /> 德<br />智<br />体<br />
							美<br /> 情<br /> 况 </strong>
						<div align="center">
							<strong>︶</strong>
							<br />
						</div>
					</div>
					
				</td>
				<td colspan="9">
					<div align="center">
					<br/><br/><br/><br/><br/>
						<p align="right">
							申请人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					
					<div align="center">
						<strong>学<br /> 院<br /> 意<br /> 见</strong>
					</div>
					
				</td>
				<td colspan="9">
					<div align="center">
					<br/>
						<p align="right">
							签名(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					<div align="center">
						<strong>学<br /> 校<br /> 意<br /> 见</strong>
					</div>
				</td>
				<td colspan="9">
					<div align="center">
						<br/>
						<p align="right">
							签名(公章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</div>
				</td>
			</tr>
		</table>
(提供教务处盖章成绩单,相关材料可另附页)
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>

	</html:form>
</body>
