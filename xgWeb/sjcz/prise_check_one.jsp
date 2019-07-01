<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
</head>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<html:form action="/data_search" method="post">
				
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 审核 - 奖学金审核 - 单个奖学金审核</a>
				</p>
			</div>
			
			<input type="hidden" name="pkVal" value="${pkVal }" />
			<input type="hidden" name="tg" id="tg" value="${tgres }" />
			<input type="hidden" name="res" id="res" value="${sResult}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" name="xsh" id="xsh" value="${xxsh }" />
			<input type="hidden" name="ysh" id="ysh" value="${xysh }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<!-- 浙江机电 -->
			<logic:present name="showzjjd">
				<table width="100%" align="center" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								单个奖学金审核
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<th align="right">
							学号
						</th>
						<td align="left" id="selxh">
							<bean:write name="XH" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name="XH" />" />
						</td>
						<th align="right">
							年度
						</th>
						<td align="left">
							<bean:write name="ND" />
							<input type="hidden" name="nd" id="nd"
								value="<bean:write name="ND" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							姓名
						</th>
						<td align="left">
							<bean:write name="XM" />
						</td>
						<th align="right">
							学年
						</th>
						<td align="left">
							<bean:write name="XN" />
							<input type="hidden" name="xn" id="xn"
								value="<bean:write name="XN" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							性别
						</th>
						<td align="left">
							<bean:write name="XB" />
						</td>
						<th align="right">
							奖学金
						</th>
						<td align="left">
							<bean:write name="JXJMC" />
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							年级
						</th>
						<td align="left">
							<bean:write name="NJ" />
						</td>
						<th align="right">
							德育总分
						</th>
						<td align="left">
							${dyzf }
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<bean:write name="XYMC" />
						</td>
						<th align="right">
							智育总分
						</th>
						<td align="left">
							${zyzf }
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							专业
						</th>
						<td align="left">
							<bean:write name="ZYMC" />
						</td>
						<th align="right">
							体育总分
						</th>
						<td align="left">
							${tyzf }
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							班级
						</th>
						<td align="left">
							<bean:write name="BJMC" />
						</td>
						<th align="right">
							审核
						</th>
						<td align="left">
							<html:select property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							综合测评总分
						</th>
						<td align="left">
							${zhszcpzf }
						</td>
						<th align="right">
							综合测评排名
						</th>
						<td align="left">
							${zhszpm }
						</td>
					</tr>
					<tr style="height: 22px">
						<th align="right">
							班级评定等级
						</th>
						<td align="left">
							${bjpddj }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
					<logic:equal value="xy" scope="request" name="userType">
						<tr>
							<th align="right">
								辅导员意见
							</th>
							<td align="left" colspan="3">
								<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><bean:write name="FDYYJ" /></textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />评审委员会意见
							</th>
							<td align="left" colspan="3">
								<textarea name="wyhyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYPSWYHYJ" /></textarea>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</th>
							<td align="left" colspan="3">
								<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYSHYJ" /></textarea>
							</td>
						</tr>

					</logic:equal>
					<logic:equal value="xx" scope="request" name="userType">
						<tr>
							<th align="right">
								辅导员意见
							</th>
							<td align="left" colspan="3">
								<bean:write name="FDYYJ" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />评审委员会意见
							</th>
							<td align="left" colspan="3">
								<bean:write name="XYPSWYHYJ" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</th>
							<td align="left" colspan="3">
								<bean:write name="XYSHYJ" />
							</td>
						</tr>
						<tr>
							<th align="right">
								学校审核意见
							</th>
							<td align="left" colspan="3">
								<textarea name="xxyj" style="width:100%" rows="3" type="_moz"><bean:write name="XXSHYJ" /></textarea>
							</td>
						</tr>

					</logic:equal>
				</table>
			</logic:present>
			<!-- 非浙江机电 -->
			<logic:notPresent name="showzjjd">
			<div class="tab">
				<table width="100%" align="center" class="formlist">
					<thead>
						<tr style="height:22px">
							<th colspan="4">
							<span>单个奖学金审核</span>
							</th>
						</tr>
					</thead>
					<tr style="height:22px">
						<th align="right" width="15%">
							学号
						</th>
						<td align="left" id="selxh">
							<bean:write name="XH" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name="XH" />" />
						</td>
						<th align="right">
							年度
						</th>
						<td align="left">
							<bean:write name="ND" />
							<input type="hidden" name="nd" id="nd"
								value="<bean:write name="ND" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							姓名
						</th>
						<td align="left">
							<bean:write name="XM" />
						</td>
						<th align="right">
							学年
						</th>
						<td align="left">
							<bean:write name="XN" />
							<input type="hidden" name="xn" id="xn"
								value="<bean:write name="XN" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							性别
						</th>
						<td align="left">
							<bean:write name="XB" />
						</td>
						<th align="right">
							奖学金
						</th>
						<td align="left">
							<bean:write name="JXJMC" />
						</td>
					</tr>
					<logic:present name="showhzy">
						<tr style="height:22px">
							<th align="right">
								年级
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<th align="right">
								学期
							</th>
							<td align="left">
								<bean:write name="xq" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="XYMC" />
							</td>
							<th align="right">
								专业
							</th>
							<td align="left">
								<bean:write name="ZYMC" />
							</td>
						</tr>
					</logic:present>
					<logic:notPresent name="isgdby">
						<logic:notPresent name="showhzy">
							<!-- 安徽建筑工业 -->
							<logic:equal value="10878" name="xxdm">
								<tr style="height:22px">
									<th align="right">
										年级
									</th>
									<td align="left">
										<bean:write name="NJ" />
									</td>
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td align="left">
										<bean:write name="XYMC" />
									</td>
								</tr>
							</logic:equal>
							<!-- 非安徽建筑工业 -->
							<logic:notEqual value="10878" name="xxdm">
								<tr style="height:22px">
									<th align="right">
										年级
									</th>
									<td align="left">
										<bean:write name="NJ" />
									</td>
									<logic:notEqual value="10355" name="xxdm">
										<logic:notEqual value="11647" name="xxdm">
											<logic:notEqual value="13742" name="xxdm">
												<th align="right">
													<logic:equal value="12682" name="xxdm">
									行为规范
									</logic:equal>
													<logic:notEqual value="12682" name="xxdm">
										德成绩
									</logic:notEqual>

												</th>
												<td align="left">
													<bean:write name='DCJ' />
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="10355" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="11647" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="13742" name="xxdm">
										<th align="right">
											德育考核等级:
										</th>
										<td align="left">
											${dykhdj }
										</td>
									</logic:equal>
								</tr>
								<tr style="height:22px">
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td align="left">
										<bean:write name="XYMC" />
									</td>
									<logic:notEqual value="13742" name="xxdm">
										<logic:notEqual value="10355" name="xxdm">
											<logic:notEqual value="11647" name="xxdm">
												<th align="right">
													<logic:equal value="12682" name="xxdm">
									文化成绩
									</logic:equal>
													<logic:notEqual value="12682" name="xxdm">
										智成绩
									</logic:notEqual>
												</th>
												<td align="left">
													<bean:write name="ZCJ" />
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="10355" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="11647" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="13742" name="xxdm">
										<th align="right">
											学生体质健康标准:
										</th>
										<td align="left">
											${xstzjkbz }
										</td>
									</logic:equal>
								</tr>
								<tr style="height:22px">
									<th align="right">
										专业
									</th>
									<td align="left">
										<bean:write name="ZYMC" />
									</td>
									<logic:notEqual value="13742" name="xxdm">
										<logic:notEqual value="10355" name="xxdm">
											<logic:notEqual value="11647" name="xxdm">
												<th align="right">
													体育成绩
												</th>
												<td align="left">
													<bean:write name="TCJ" />
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="11647" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="10355" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="13742" name="xxdm">
										<th align="right">
											班级
										</th>
										<td align="left">
											<bean:write name="BJMC" />
										</td>
									</logic:equal>
								</tr>
							</logic:notEqual>
						</logic:notPresent>
						<logic:present name="shownblg">
							<tr style="height:22px">
								<th align="right">
									英语四级
								</th>
								<td align="left">
									${cet4 }
								</td>
								<th align="right">
									英语六级
								</th>
								<td align="left">
									${cet6 }
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									加权分
								</th>
								<td align="left">
									<bean:write name="jqf" />
								</td>
								<th align="right">
									综合素质总分
								</th>
								<td align="left">
									<bean:write name="zhszcpzf" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									加权分专业排名
								</th>
								<td align="left">
									${jqfpm }
								</td>
								<th align="right">
									综合素质班级排名
								</th>
								<td align="left">
									${zhszcpzfpm }
								</td>
							</tr>
						</logic:present>
						<logic:notPresent name="shownblg">
							<logic:notEqual value="10878" name="xxdm">
								<logic:notEqual value="11417" name="xxdm">

									<logic:notEqual value="10355" name="xxdm">

										<logic:equal value="11647" name="xxdm">
											<tr style="height:22px">
												<td align="right">
													综合素质分
												</td>
										</logic:equal>
										<logic:notEqual value="11647" name="xxdm">
											<logic:equal value="12682" name="xxdm">
												<tr style="height:22px">
													<td align="right">
														总成绩
													</td>
											</logic:equal>
											<logic:notEqual value="12682" name="xxdm">
												<%--							成绩名次--%>
											</logic:notEqual>
										</logic:notEqual>


										<logic:equal value="12872" name="xxdm">
											<td align="left">
												<html:text property="cjmc" styleId="cjmc" value="${cjmc}"></html:text>
											</td>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<logic:equal value="11647" name="xxdm">
												<td align="left">
													${cj }
												</td>
											</logic:equal>
											<logic:notEqual value="11647" name="xxdm">

												<logic:equal value="12682" name="xxdm">
													<td align="left">
														${zpf }
													</td>
												</logic:equal>
												<logic:notEqual value="12682" name="xxdm">
										${cjmc }
										</logic:notEqual>
											</logic:notEqual>

										</logic:notEqual>


										<logic:notPresent name="showcsmzxy">
											<!-- quers -->

										</logic:notPresent>
										<logic:present name="showcsmzxy">
											<td align="right">
												素质拓展学分名次
											</td>
											</tr>
										</logic:present>


										<logic:notPresent name="showcsmzxy">
											<logic:present name="showhzy">
												<td align="left">
													<html:text property="zhpfmc" styleId="zhpfmc"
														value="${zhpfmc}"></html:text>
												</td>
												</tr>
											</logic:present>
											<logic:notPresent name="showhzy">
												<td align="left">
													${zhszcpcjpm}
												</td>
												</tr>
											</logic:notPresent>
										</logic:notPresent>
										<logic:present name="showcsmcxy">
											<td align="left">
												${zhpfmc}
											</td>
											</tr>
										</logic:present>

									</logic:notEqual>

								</logic:notEqual>
							</logic:notEqual>
						</logic:notPresent>
					</logic:notPresent>

					<logic:equal value="yes" name="isgdby">

						<tr style="height:22px">
							<th align="right">
								年级
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td align="left">
								&nbsp;
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="XYMC" />
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td align="left">
								&nbsp;
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								专业
							</th>
							<td align="left">
								<bean:write name="ZYMC" />
							</td>
							<th align="right">
								操行成绩
							</th>
							<td align="left">
								<bean:write name="CXCJ" />
							</td>
						</tr>

					</logic:equal>

					<tr style="height:22px">
						<logic:notEqual value="13742" name="xxdm">
							<th align="right">
								班级
							</th>
							<td align="left">
								<bean:write name="BJMC" />
							</td>
						</logic:notEqual>
						<logic:equal value="12872" name="xxdm">
							<logic:equal value="true" name="isFdy">
								<td align="right">
									<input type="hidden" name="yesNo" value="${yesNo}" />
								</td>
								<td align="left">

								</td>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy">
								<th align="right">
									审核
								</th>
								<td align="left">
									<html:select property="yesNo" styleId="yesNo">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
							<th align="right">
								审核
							</th>
							<td align="left">
								<html:select property="yesNo" styleId="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</logic:notEqual>
						<!-- 江苏信息职业技术学院 -->
						<logic:notEqual value="13108" name="xxdm">
							
						</logic:notEqual>
					</tr>
					<tr style="height:22px">
					<logic:notEqual value="13742" name="xxdm">
							<th align="right">
								担任职务
							</th>
							<td align="left" colspan="">
								<bean:write name="DRZW" />
							</td>
							<td></td>
							<td></td>
					</logic:notEqual>
					<logic:present name="isjgs">
						<th align="right">
							是否师范
						</th>
						<td align="left">
							<bean:write name="sfsf" />
						</td>
					</logic:present>
					<logic:notPresent name="isjgs">
						<logic:notPresent name="showcsmzxy">
							<logic:present name="showhzy">
								<th align="right">
									平均成绩:
								</th>
								<td align="left">
									${pjcj }
								</td>
							</logic:present>
						</logic:notPresent>
					</logic:notPresent>
					</tr>
					<logic:equal value="10878" name="xxdm">
						<logic:equal value="true" name="isFdy">
							<tr style="height:22px">
								<th align="right">
									项目名称
								</th>
								<td align="left" colspan="3">
									<html:text property="szmc1" styleId="szmc1" style="width:90px"
										value="${szmc1}"></html:text>

									<html:text property="szmc2" styleId="szmc2" style="width:90px"
										value="${szmc2}"></html:text>

									<html:text property="szmc3" styleId="szmc3" style="width:90px"
										value="${szmc3}"></html:text>
									<html:text property="szmc4" styleId="szmc4" style="width:90px"
										value="${szmc4}"></html:text>
									<html:text property="szmc5" styleId="szmc5" style="width:90px"
										value="${szmc5}"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									项目成绩
								</th>
								<td align="left" colspan="3">
									<html:text property="szcj1" styleId="szcj1" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj1}"></html:text>

									<html:text property="szcj2" styleId="szcj2" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj2}"></html:text>

									<html:text property="szcj3" styleId="szcj3" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj3}"></html:text>
									<html:text property="szcj4" styleId="szcj4" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj4}"></html:text>
									<html:text property="szcj5" styleId="szcj5" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj5}"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									综合成绩
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcj" style="width:90px"
										styleId="zhszcpcj" onblur="ckinpdata(this)"
										value="${zhszcpcj}"></html:text>
								</td>
								<th align="right">
									综合排名
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcjpm" style="width:90px"
										styleId="zhszcpcjpm" onblur="ckinpdata(this)"
										value="${zhszcpcjpm}"></html:text>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="true" name="isFdy">
							<tr style="height:22px">
								<th align="right">
									项目名称
								</th>
								<td align="left" colspan="3">
									<html:text property="szmc1" styleId="szmc1" style="width:90px"
										value="${szmc1}" readonly="true"></html:text>
									<html:text property="szmc2" styleId="szmc2" style="width:90px"
										value="${szmc2}" readonly="true"></html:text>
									<html:text property="szmc3" styleId="szmc3" style="width:90px"
										value="${szmc3}" readonly="true"></html:text>
									<html:text property="szmc4" styleId="szmc4" style="width:90px"
										value="${szmc4}" readonly="true"></html:text>
									<html:text property="szmc5" styleId="szmc5" style="width:90px"
										value="${szmc5}" readonly="true"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									项目成绩
								</th>
								<td align="left" colspan="3">
									<html:text property="szcj1" styleId="szcj1" style="width:90px"
										value="${szcj1}" readonly="true"></html:text>
									<html:text property="szcj2" styleId="szcj2" style="width:90px"
										value="${szcj2}" readonly="true"></html:text>
									<html:text property="szcj3" styleId="szcj3" style="width:90px"
										value="${szcj3}" readonly="true"></html:text>
									<html:text property="szcj4" styleId="szcj4" style="width:90px"
										value="${szcj4}" readonly="true"></html:text>
									<html:text property="szcj5" styleId="szcj5" style="width:90px"
										value="${szcj5}" readonly="true"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									综合成绩
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcj" style="width:90px"
										styleId="zhszcpcj" value="${zhszcpcj}" readonly="true"></html:text>
								</td>
								<th align="right">
									综合排名
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcjpm" style="width:90px"
										styleId="zhszcpcjpm" value="${zhszcpcjpm}" readonly="true"></html:text>
								</td>
							</tr>
						</logic:notEqual>
					</logic:equal>
					<tr style="height:22px">
						<logic:present name="showhzy">
							<td align="right">
								奖罚情况
							</td>
							<td align="left" colspan="7">
								<textarea name='jfqk' style="width:99%" rows='3' type="_moz"><bean:write name="jfqk" /></textarea>
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<logic:notEqual value="13742" name="xxdm">
								<th align="right">
									科研项目
								</th>
								<td colspan="3">
									<textarea name='kyxm' style="width:97%" rows='5' type="_moz"readonly="readonly"><bean:write name="KYCG" /></textarea>
								</td>
							</logic:notEqual>
						</logic:notPresent>
					</tr>
					<logic:present name="isjgs">
						<tr>
							<th align="right">
								获奖学金情况
							</th>
							<td align="left" colspan="3">
								<textarea rows="3" cols="4" style="width: 90%" name="hjxjqk" type="_moz"><bean:write name="hjxjqk" /></textarea>
							</td>
						</tr>
					</logic:present>
					<tr style="height:22px">
						<logic:present name="showhzy">
							<th align="right">
								申请理由
							</th>
							<td align="left" colspan="3">
								<textarea name="sqly" style="width:100%" rows="3" type="_moz"><bean:write name="SQLY" /></textarea>
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<th align="right">
								申请理由
							</th>
							<td align="left" colspan="3">
								<bean:write name="SQLY" />
							</td>
						</logic:notPresent>
					</tr>
					<logic:equal value="10355" name="xxdm">
						<tr style="height:22px">
							<th align="right">
								备注
								<font color="red">(受处分,旷课,<br />体育达标,<br />学习成绩情况)</font>
								<br />
							</th>
							<td colspan="7">
								<html:textarea property='bz' style="width:99%" rows='5'
									readonly="true"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="xy" scope="request" name="userType">
						<logic:present name="showhzy">
							<tr>
								<th align="right">
									班主任意见
								</th>
								<td align="left" colspan="3">
									<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><logic:equal value=" " name="FDYYJ">同意推荐</logic:equal><logic:notEqual value=" " name="FDYYJ"><bean:write name="FDYYJ" /></logic:notEqual></textarea>
								</td>
							</tr>


							<logic:equal value="no" name="writeAble">
								<tr>
									<th align="right">
										系(院)意见
									</th>
									<td align="left" colspan="3">
										<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><logic:equal value=" " name="XYSHYJ">同意推荐</logic:equal><logic:notEqual value=" " name="XYSHYJ"><bean:write name="XYSHYJ" /></logic:notEqual></textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="yes" name="writeAble">
								<tr>
									<td align="right">
										系(院)意见
									</td>
									<td align="left" colspan="3">
										<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><logic:equal value=" " name="XYSHYJ">同意推荐</logic:equal><logic:notEqual value=" " name="XYSHYJ"><bean:write name="XYSHYJ" /></logic:notEqual></textarea>
									</td>
								</tr>
							</logic:equal>
							<tr>
								<th align="right">
									班主任签名
								</th>
								<td align="left">
									<input type="text" name="fdyqm" id="fdyqm" value="${fdyqm}">
								</td>
								<logic:equal value="true" name="isFdy">
									<td align="right">

									</td>
									<td align="left">
										<input type="hidden" name="xyqm" id="xyqm" value="${xyqm}">
									</td>
								</logic:equal>
								<logic:notEqual value="true" name="isFdy">
									<th align="right">
										系(院)签名
									</th>
									<td align="left">
										<input type="text" name="xyqm" id="xyqm" value="${xyqm}">
									</td>
								</logic:notEqual>
								<logic:equal value="true" name="idFdy">
									<td align="right">
										&nbsp;
									</td>
									<td align="left">
										&nbsp;
									</td>
								</logic:equal>
							</tr>
						</logic:present>
						<logic:notPresent name="showhzy">
							<logic:present name="iscsmz">
								<logic:equal value="fdy" name="iscsmz">
									<tr>
										<th align="right">
											辅导员意见
										</th>
										<td align="left" colspan="3">
											<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><bean:write name="FDYYJ" /></textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="xy" name="iscsmz">
									<tr>
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />评审委员会意见
										</th>
										<td align="left" colspan="3">
											<textarea name="wyhyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYPSWYHYJ" /></textarea>
										</td>
									</tr>
									<tr style="height:22px">
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />审核意见
										</th>
										<td align="left" colspan="3">
											<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYSHYJ" /></textarea>
										</td>
									</tr>
								</logic:equal>
							</logic:present>
							<logic:notPresent name="iscsmz">
								<tr>
									<th align="right">
										<logic:equal value="13742" name="xxdm">
						班级评议
						</logic:equal>
										<logic:notEqual value="13742" name="xxdm">
						                                          辅导员意见
						              </logic:notEqual>
									</th>
									<td align="left" colspan="3">
										<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><bean:write name="FDYYJ" /></textarea>
									</td>
								</tr>
								<logic:equal value="false" name="isFdy">
									<tr>
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />评审委员会意见
										</th>
										<td align="left" colspan="3">
											<textarea name="wyhyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYPSWYHYJ" /></textarea>
										</td>
									</tr>
									<tr style="height:22px">
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />审核意见
										</th>
										<td align="left" colspan="3">
											<textarea name="xyyj" style="width:100%" rows="3" type="_moz" id="xyyj6"><bean:write name="XYSHYJ" /></textarea>
										</td>
									</tr>
								</logic:equal>
							</logic:notPresent>
						</logic:notPresent>
					</logic:equal>
					<logic:equal value="xx" scope="request" name="userType">

						<logic:present name="showhzy">
							<tr>
								<th align="right">
									班主任意见
								</th>
								<td align="left" colspan="3"><bean:write name="FDYYJ" />
								</td>
							</tr>
							<tr>
								<th align="right">
									系(院)意见
								</th>
								<td align="left" colspan="3"><bean:write name="XYSHYJ" /></td>
							</tr>
							<tr>
								<th align="right">
									<bean:message key="lable.xsgzyxpzxy" />意见
								</th>
								<td align="left" colspan="3">
									<textarea name="xxyj" style="width:100%" rows="3" type="_moz">
										<logic:equal value=" " name="XXSHYJ">同意</logic:equal>
										<logic:notEqual value=" " name="XXSHYJ">
											<bean:write name="XXSHYJ" />
										</logic:notEqual>
									</textarea>
								</td>
							</tr>
						</logic:present>
						<logic:notPresent name="showhzy">
							<tr>
								<th align="right">
									辅导员意见
								</th>
								<td align="left" colspan="3"><bean:write name="FDYYJ" /></td>
							</tr>
							<logic:notEqual value="13742" name="xxdm">
								<tr>
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />评审委员会意见
									</th>
									<td align="left" colspan="3"><bean:write name="XYPSWYHYJ" /></td>
								</tr>
							</logic:notEqual>
							<tr>
								<th align="right">
									<logic:equal value="13742" name="xxdm"><bean:message key="lable.xsgzyxpzxy" />评审意见</logic:equal>
									<logic:notEqual value="13742" name="xxdm"><bean:message key="lable.xsgzyxpzxy" />审核意见</logic:notEqual>
								</th>
								<td align="left" colspan="3">
									<bean:write name="XYSHYJ" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<logic:equal value="13742" name="xxdm"><bean:message key="lable.xsgzyxpzxy" />审核意见</logic:equal>
									<logic:notEqual value="13742" name="xxdm">学校审核意见</logic:notEqual>
								</th>
								<td align="left" colspan="3">
									<textarea name="xxyj" style="width:97%" rows="3" type="_moz"><bean:write name="XXSHYJ" /></textarea>
								</td>
							</tr>
						</logic:notPresent>
					</logic:equal>
					<tfoot>
						<tr>
							<td colspan="4">
								 <div class="btn">
				<button type="button" class="" onclick="ckflag()" 
					id="buttonSave">
					保 存
				</button>
				<logic:present name="showhzy">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class=""
						onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" id="buttonQuery">
						查看学生信息
					</button>
				</logic:present>
				<logic:equal value="13108" name="xxdm">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class=""
						onclick="showTopWin('/xgxt/stu_cj_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" id="buttonQuery">
						查看学生成绩
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="" onclick="Close();return false;" 
					id="buttonClose">
					关 闭
				</button>
				<%--<logic:present name="showhzy">
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.open('/xgxt/dxjxjsp.do?method=dxjxjsp&pk=<bean:write name="xn||nd||xh||jxjdm"/>')" style="width:90px"
					id="buttonQuery">
					打印报表
				</button>
				</logic:present>
			--%>
			</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
			</logic:notPresent>
			
		</html:form>
		<logic:present name="result">
			<logic:equal value="view" name="result">
				<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script>
					alert("操作失败！"+document.getElementById('res').value);
					Close();
			</script>
			</logic:equal>
		</logic:present>
		<script type="text/javascript">
		function ckflag() {
				var xxdm;
				var uType = document.getElementById('userType').value;
				var isFdy = document.getElementById('isFdy').value;
				var xsh = document.getElementById('xsh').value;
				var ysh = document.getElementById('ysh').value;
				if ($('xxdm')) {
					xxdm = document.getElementById('xxdm').value;
				}
				if (xxdm=='10827') {
					if (uType=='xy') {//fdy
						if (isFdy=='true') {
							if (xsh=='通过' || ysh=='通过') {
								alert('经相关部门审核且已通过,不能再修改!');
								return;
							} else {
								BatAlert.showTips('正在操作，请等待...');
								refreshForm('/xgxt/priseChkOne.do?act=save');
								return;
							}
						} else {//xy
							if (xsh=='通过') {
								alert('经相关部门审核且已通过,不能再修改!');
								return;
							} else {
								refreshForm('/xgxt/priseChkOne.do?act=save');
								BatAlert.showTips('正在操作，请等待...');
								return;
							}
						}
					} else {
						refreshForm('/xgxt/priseChkOne.do?act=save');
						BatAlert.showTips('正在操作，请等待...');
						return;
					}
				} else {
					refreshForm('/xgxt/priseChkOne.do?act=save');
					BatAlert.showTips('正在操作，请等待...');
					return;
				}
			}
	</script>
	</body>
</html>
