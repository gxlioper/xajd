<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<div class="yl_win">
			<h3></h3>
			<div class="yl_con">
				<div class="module">
					<h5>
						个人信息
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								身份证号：
								<logic:equal value="是" property="sfzhbm" name="rs">
									保密
								</logic:equal>
								<logic:notEqual value="是" property="sfzhbm" name="rs">
									${rs.sfzh }
								</logic:notEqual>
							</td>
							<td>
								姓&nbsp;&nbsp;&nbsp;&nbsp;名：${rs.xm }
							</td>
							<td rowspan="4">
								<div class="photo">
									<img
										src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
										style="width:95px;height:130px" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								性&nbsp;&nbsp;&nbsp;&nbsp;别：${rs.xb }
							</td>
							<td>
								民&nbsp;&nbsp;&nbsp;&nbsp;族：${rs.mzmc }
							</td>
						</tr>
						<tr>
							<td>
								出生年月：${rs.csrq }
							</td>
							<td>
								生源地区：${rs.sydq }
							</td>
						</tr>
						<tr>
							<td>
								政治面貌：${rs.zzmm }
							</td>
							<td>
								健康状况：${rs.jkzk }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						教育背景
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								毕业<bean:message key="lable.xb" />：${rs.xymc }
							</td>
							<td>
								入学年月：${rs.rxnd }
							</td>
						</tr>
						<tr>
							<td>
								主修专业：${rs.zymc }
							</td>
							<td>
								学&nbsp;&nbsp;&nbsp;&nbsp;制：${rs.xz }
							</td>
						</tr>
						<tr>
							<td>
								辅修专业：${rs.fxzy }
							</td>
							<td>
								学&nbsp;&nbsp;&nbsp;&nbsp;历：${rs.xl }
							</td>
						</tr>
						<tr>
							<td>
								计算机水平：${rs.jsjsp }
							</td>
							<td>
								外语水平：${rs.wysp }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						联系方式
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								联系地址：${rs.lxdz }
							</td>
							<td>
								邮政编码：${rs.yzbh }
							</td>
						</tr>
						<tr>
							<td>
								电子信箱：${rs.dzyx }
							</td>
							<td>
								联系电话：${rs.lxdh }
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						求职意向
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="70px">
								工作性质：
							</td>
							<td>
								${rs.gzxz }
							</td>
						</tr>
						<tr>
							<td>
								目标岗位：
							</td>
							<td>
								${rs.mbgw }
							</td>
						</tr>
						<tr>
							<td>
								目标地点：
							</td>
							<td>
								${rs.gzdd }
							</td>
						</tr>
						<tr>
							<td>
								期望薪水：
							</td>
							<td>
								${rs.qwxs }
							</td>
						</tr>
						<tr>
							<td valign="top">
								目标职能：
							</td>
							<td>
								${rs.mbzn }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						社会工作情况
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								起止时间
							</td>
							<td width="30%" align="center">
								工作单位（或社团组织）
							</td>
							<td width="30%" align="center">
								所任职务
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shgzkssj1 }
								<logic:present name="rs" property="shgzjssj1"> - </logic:present>
								${rs.shgzjssj1 }
							</td>
							<td align="center">
								${rs.gzdw1 }
							</td>
							<td align="center">
								${rs.srzw1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shgzkssj2 }
								<logic:present name="rs" property="shgzjssj2"> - </logic:present>
								${rs.shgzjssj2 }
							</td>
							<td align="center">
								${rs.gzdw2 }
							</td>
							<td align="center">
								${rs.srzw2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shgzkssj3 }
								<logic:present name="rs" property="shgzjssj3"> - </logic:present>
								${rs.shgzjssj3 }
							</td>
							<td align="center">
								${rs.gzdw3 }
							</td>
							<td align="center">
								${rs.srzw3 }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						社会实践情况
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								起止时间
							</td>
							<td width="30%" align="center">
								工作单位及实践内容
							</td>
							<td width="30%" align="center">
								实践活动成效
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shsjkssj1 }
								<logic:present name="rs" property="shsjjssj1"> - </logic:present>
								${rs.shsjjssj1 }
							</td>
							<td align="center">
								${rs.shsjdw1 }
							</td>
							<td align="center">
								${rs.shsjcx1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shsjkssj2 }
								<logic:present name="rs" property="shsjjssj2"> - </logic:present>
								${rs.shsjjssj2 }
							</td>
							<td align="center">
								${rs.shsjdw2 }
							</td>
							<td align="center">
								${rs.shsjcx2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.shsjkssj3 }
								<logic:present name="rs" property="shsjjssj3"> - </logic:present>
								${rs.shsjjssj3 }
							</td>
							<td align="center">
								${rs.shsjdw3 }
							</td>
							<td align="center">
								${rs.shsjcx3 }
							</td>
						</tr>
					</table>
				</div>

				<div class="module">
					<h5>
						获奖情况
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								时间
							</td>
							<td width="30%" align="center">
								荣誉称号
							</td>
							<td width="30%" align="center">
								奖励部门
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.hjsj1 }
							</td>
							<td align="center">
								${rs.rych1 }
							</td>
							<td align="center">
								${rs.bzjg1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.hjsj2 }
							</td>
							<td align="center">
								${rs.rych2 }
							</td>
							<td align="center">
								${rs.bzjg2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.hjsj3 }
							</td>
							<td align="center">
								${rs.rych3 }
							</td>
							<td align="center">
								${rs.bzjg3 }
							</td>
						</tr>
					</table>
				</div>

				<div class="module">
					<h5>
						技能证书
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="20%" align="center">
								时间
							</td>
							<td width="30%" align="center">
								证书名称
							</td>
							<td width="30%" align="center">
								颁证机构
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jnzssj1 }
							</td>
							<td align="center">
								${rs.jnzsmc1 }
							</td>
							<td align="center">
								${rs.jnzsmc1 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jnzssj2 }
							</td>
							<td align="center">
								${rs.jnzsmc2 }
							</td>
							<td align="center">
								${rs.jnzsmc2 }
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jnzssj3 }
							</td>
							<td align="center">
								${rs.jnzsmc3 }
							</td>
							<td align="center">
								${rs.jnzsmc3 }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						自我推荐
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td>
								${rs.zwtj }
							</td>
						</tr>
					</table>
				</div>

				<div class="btn">
					<button onclick="window.close();return false;">
						关 闭
					</button>
				</div>
			</div>
		</div>
	</body>
</html>
