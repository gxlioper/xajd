<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead.ini"%>
	</head>
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
						${xxmc }${nd }届毕业生就业推荐表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="10%"></td>
							<td align="center" width="16%"></td>
							<td align="center" width="12%"></td>
							<td align="center" width="12%"></td>
							<td align="center" width="12%"></td>
							<td align="center" width="10%"></td>
							<td align="center" width="14%"></td>
							<td align="center" width="14%"></td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="3">
								基本<br/>情况
							</td>
							<td align="center">
								姓名
							</td>
							<td align="center">
								${rs.xm }
							</td>
							<td align="center">
								性别
							</td>
							<td align="center">
								${rs.xb }
							</td>
							<td align="center">
								民族
							</td>
							<td align="center">
									${rs.mzmc }
							</td>
							<td align="center" rowspan="5">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
									border="0" align="absmiddle"  style="width:80px;height:100px" />
							</td>
						</tr>
						<tr  height="25px">
							<td align="center">
								出生年月
							</td>
							<td align="center">
								${rs.csrq }
							</td>
							<td align="center">
								生源地区
							</td>
							<td align="center">
								${rs.sydq }
							</td>
							<td align="center">
								政治面貌
							</td>
							<td align="center">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								家庭地址
							</td>
							<td align="center" colspan="3">
								${rs.jtdz }
							</td>
							<td align="center">
								健康状况
							</td>
							<td align="center">
								${rs.jkzk }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="2">
								联系<br/>方式
							</td>
							<td align="center">
								联系地址
							</td>
							<td align="center" colspan="3">
								${rs.lxdz }
							</td>
							<td align="center">
								邮政编码
							</td>
							<td align="center">
								${rs.yzbh }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								电子信箱
							</td>
							<td align="center" colspan="3">
								${rs.dzyx }
							</td>
							<td align="center">
								联系电话
							</td>
							<td align="center">
								${rs.lxdh }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								教育<br/><br/>背景
							</td>
							<td align="center">
								所在<bean:message key="lable.xb" />
							</td>
							<td align="center" colspan="3">
								${rs.xymc }
							</td>
							<td align="center">
								入学年月
							</td>
							<td align="center" colspan="2">
								${rs.rxnd }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								主修专业
							</td>
							<td align="center" colspan="3">
								${rs.zymc }
							</td>
							<td align="center">
								学 制
							</td>
							<td align="center" colspan="2">
								${rs.xz }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								辅修专业
							</td>
							<td align="center" colspan="3">
								${rs.fxzy }
							</td>
							<td align="center">
								学历
							</td>
							<td align="center" colspan="2">
								${rs.xl }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								计算机水平
							</td>
							<td align="center" colspan="3">
								${rs.jsjsp }
							</td>
							<td align="center">
								外语水平
							</td>
							<td align="center" colspan="2">
								${rs.wysp }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								社<br/>会<br/>工<br/>作
							</td>
							<td align="center">
								起止时间
							</td>
							<td align="center" colspan="4">
								工作单位（或社团组织）
							</td>
							<td align="center" colspan="2">
								所任职务
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
									${rs.shgzkssj1 }<br/>${rs.shgzjssj1 }
							</td>
							<td align="center" colspan="4">
									${rs.gzdw1 }
							</td>
							<td align="center" colspan="2">
									${rs.srzw1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
									${rs.shgzkssj2 }<br/>${rs.shgzjssj2 }
							</td>
							<td align="center" colspan="4">
									${rs.gzdw2 }
							</td>
							<td align="center" colspan="2">
								${rs.srzw2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shgzkssj3 }<br/>${rs.shgzjssj3 }
							</td>
							<td align="center" colspan="4">
								${rs.gzdw3 }
							</td>
							<td align="center" colspan="2">
								${rs.srzw3 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								获<br/>奖<br/>情<br/>况
							</td>
							<td align="center">
								时间
							</td>
							<td align="center" colspan="4">
								荣誉称号或证书名称
							</td>
							<td align="center" colspan="2">
								奖励部门（颁证机构）
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.hjsj1 }
							</td>
							<td align="center" colspan="4">
								${rs.rych1 }
							</td>
							<td align="center" colspan="2">
								${rs.bzjg1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.hjsj2 }
							</td>
							<td align="center" colspan="4">
								${rs.rych2 }
							</td>
							<td align="center" colspan="2">
								${rs.bzjg2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.hjsj3 }
							</td>
							<td align="center" colspan="4">
								${rs.rych3 }
							</td>
							<td align="center" colspan="2">
								${rs.bzjg3 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								技<br/>能<br/>证<br/>书
							</td>
							<td align="center">
								时间
							</td>
							<td align="center" colspan="4">
								荣誉称号或证书名称
							</td>
							<td align="center" colspan="2">
								奖励部门（颁证机构）
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.jnzssj1 }
							</td>
							<td align="center" colspan="4">
								${rs.jnzsmc1 }
							</td>
							<td align="center" colspan="2">
								${rs.jnzsbzjg1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.jnzssj2 }
							</td>
							<td align="center" colspan="4">
								${rs.jnzsmc2 }
							</td>
							<td align="center" colspan="2">
								${rs.jnzsbzjg2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.jnzssj3 }
							</td>
							<td align="center" colspan="4">
								${rs.jnzsmc3 }
							</td>
							<td align="center" colspan="2">
								${rs.jnzsbzjg3 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">
								社<br/>会<br/>实<br/>践
							</td>
							<td align="center">
								起止时间
							</td>
							<td align="center" colspan="4">
								工作单位及实践内容
							</td>
							<td align="center" colspan="2">
								实践活动成效
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shsjkssj1 }<br/>${rs.shsjjssj1 }
							</td>
							<td align="center" colspan="4">
								${rs.shsjdw1 }
							</td>
							<td align="center" colspan="2">
								${rs.shsjcx1 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shsjkssj2 }<br/>${rs.shsjjssj2 }
							</td>
							<td align="center" colspan="4">
								${rs.shsjdw2 }
							</td>
							<td align="center" colspan="2">
								${rs.shsjcx2 }
							</td>
						</tr>
						<tr height="25px">
							<td align="center">
								${rs.shsjkssj3 }<br/>${rs.shsjjssj3 }
							</td>
							<td align="center" colspan="4">
								${rs.shsjdw3 }
							</td>
							<td align="center" colspan="2">
								${rs.shsjcx3 }
							</td>
						</tr>
					</table>
					
					<br/><br/>
					<br/><br/>
					<br/><br/>
					<br/><br/>
					
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="10%">
								毕<br/><br/>业<br/><br/>生<br/><br/>自<br/><br/>我<br/><br/>推<br/><br/>荐
							</td>
							<td colspan="7">
								<p style="height:420px">
									<u>（性格、爱好、特长、敬业和创新精神、团队协作与组织管理能力等）</u><br/>
									${rs.zwtj }
								</p>
								<div align="right" >
									本人签名:
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/><br/>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
								<br/>毕<br/><br/>业<br/><br/>生<br/><br/>所<br/><br/>在<br/><br/>学<br/><br/>院<br/><br/>推<br/><br/>荐<br/><br/>意<br/><br/>见<br/>
							</td>
							<td colspan="5">
								<p style="height:220px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
								</p>
								<div align="right" >
									（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="2" valign="bottom" align="center" style="width:20%">
								<strong> ${xxmc }</strong><br/>
								就业指导服务中心<br/>
								（盖章）<br/>
								年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
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
</body>
</html>
