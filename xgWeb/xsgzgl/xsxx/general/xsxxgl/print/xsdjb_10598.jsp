<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<title>湖北经济学院学生登记表</title>
		<style>
.font_style td {
	font-size: 14px;
	font-family: 宋体;
}
</style>

	</head>

	<body lang=ZH-CN style='text-justify-trim: punctuation'>

		<div align=center>
			<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
				<table width="652px" border="0" id="theTable" align="center">
					<tr>
						<td align="center">
							<br />
							<b> <span
								style='font-size: 18.0pt; font-family: 方正小标宋简体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>
									<u>${xxmc }毕业生清单</u> </span> <br /> </b>
							<br />
						</td>
					</tr>
					<tr>
						<td align="center">
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id="tab_jbxx">
								<!-- 学生基本信息 begin-->

								<thead>
									<tr onclick="" style="cursor: hand;">
										<th colspan="5">
											<span>基本信息</span>
										</th>
									</tr>
								</thead>

								<tbody id="hi_jbxx">
									<tr>
										<th width="13%">
											学号
										</th>
										<td width="30%">
											${rs.xh }
											<input type="hidden" name="xh" id="xh" value="${rs.xh }" />
										</td>
										<th width="13%">
											姓名
										</th>
										<td>
											${rs.xm }
										</td>
										<td rowspan="5" class="nohover"
											style="vertical-align: middle; text-align: left; width: 115px;">
											<div id="stuImg" class="open_img"
												style="margin-left: 0px; margin-top: 0px; height: 130px">
												<img style="width: 100px; height: 130px;"
													src="xsxx_xsgl.do?method=showPhoto&xh=${rs.xh}" border="0">
											</div>
										</td>
									</tr>
									<tr>
										<th width="13%">
											性别
										</th>
										<td width="30%">
											${rs.xb }
										</td>
										<th width="13%">
											出生日期
										</th>
										<td>
											${rs.csrq }
										</td>
									</tr>

									<tr>
										<th width="13%">
											年级
										</th>
										<td width="30%">
											${rs.nj }
										</td>
										<th>
											学制(年)
										</th>
										<td colspan="">
											${rs.xz }
										</td>
									</tr>
									<tr>
										<th>

											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											${rs.xymc }
										</td>
										<th>
											政治面貌
										</th>
										<td>
											${rs.zzmmmc }
										</td>
									</tr>
									<tr>
										<th>
											专业
										</th>
										<td>
											${rs.zymc }
										</td>

										<th>
											民族
										</th>
										<td>
											${rs.mzmc }
										</td>

									</tr>
									<tr>
										<th>
											班级
										</th>
										<td colspan="">

											${rs.bjmc }
										</td>
										<th>
											学籍
										</th>
										<td align="left" colspan="2">
											${rs.xjztm }
										</td>
									</tr>

									<tr>
										<th>
											入学时间
										</th>
										<td colspan="">
											${rs.rxrq }

										</td>
										<th>
											身份证号
										</th>
										<td align="left" colspan="2">
											${rs.sfzh}
										</td>
									</tr>

									<tr>
										<th>
											籍贯
										</th>
										<td colspan="4">
											${rs.jgmc }
										</td>
									</tr>

									<tr>
										<th>
											户口所在地
										</th>
										<td colspan="4">
											${rs.hkszdmc }
										</td>
									</tr>
									<tr>
										<th>
											生源地(高考时户籍所在地)
										</th>
										<td colspan="4">
											${rs.sydqmc }
										</td>
									</tr>
								</tbody>

							</table>
						</td>
					</tr>
					<%--<logic:iterate id="ejmk" name="wjlist">
						<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_奖学金'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='4'>
											<span>${ejmk.mkmc} </span>
										</th>
									</tr>
								</thead>
					</logic:iterate>--%>
					<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_奖学金'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='4'>
											<span>奖学金</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="jxj">
										<tr>
										<td colspan='4'>
											<div align='center'>
												暂无数据！
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="jxj">
										
									<logic:iterate id="temp" name="jxj">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_荣誉称号'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='4'>
											<span>荣誉称号</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="rych">
										<tr>
										<td colspan='4'>
											<div align='center'>
												暂无数据！
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rych">
										
									<logic:iterate id="temp" name="rych">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
					<%--<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" id='tab_综合测评'>
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='7'>
											<span>综合测评</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="zcf">
										<tr>
										<td colspan='4'>
											<div align='center'>
												暂无数据！
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="zcf">
										
									<logic:iterate id="temp" name="zcf">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>--%>
					<tr>
						<td>
							<table width="100%" border="0" style="margin-bottom: 5px"
								class="formlist" >
								<thead>
									<tr onclick='' style='cursor: hand;'>
										<th colspan='7'>
											<span>违纪处分</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="wjlist">
										<tr>
										<td colspan='4'>
											<div align='center'>
												暂无数据！
											</div>
										</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="wjlist">
										
									<logic:iterate id="temp" name="wjlist">
										<tr>
											<logic:iterate id="cell" name="temp">
												<td align='center'>
													${cell }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td align="right">
							<br />
							<span
								style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>
								<br/>学工处（盖章）<br/><br/><br/></span>
						</td>
					</tr>
				</table>
			</html:form>
		</div>
	</body>
</html>