<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业协议</a>
			</p>
		</div>


		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=zgddJyxywh" />
			<input type="hidden" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />




			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:equal value="xy" name="userType">
											<logic:equal value="退回" name="rs" property="xxsh">
												<button id="buttonSave"
													onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=通过&save_xxsh=需重审&doType=save','');">
													通过
												</button>
											</logic:equal>
											<logic:notEqual value="退回" name="rs" property="xxsh">
												<button id="buttonSave"
													onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=通过&doType=save','');">
													通过
												</button>
											</logic:notEqual>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=不通过&doType=save','');">
												不通过
											</button>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xysh=退回&doType=save','');">
												退回
											</button>
										</logic:equal>
										<logic:notEqual value="xy" name="userType">
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xxsh=通过&doType=save','');">
												通过
											</button>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xxsh=不通过&doType=save','');">
												不通过
											</button>
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=zgddJyxyShow&save_xxsh=退回&doType=save','');">
												退回
											</button>
										</logic:notEqual>
									</logic:notEqual>
										<button id="buttonSave" onclick="window.close();return false;">
											关闭
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td align="left" width="34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%">
								性别
							</th>
							<td width="34%">
								${rs.xb}
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								学校
							</th>
							<td>
								${rs.xxmc }
							</td>
							<th>
								院系
							</th>
							<td>
								${rs.xymc }
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
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								学历
							</th>
							<td>
								${rs.xl }
							</td>
							<th>
								生源地
							</th>
							<td>
								${rs.sydq }${rs.syds }${rs.sydx }
							</td>
						</tr>
						<tr>
							<th>
								入学年份
							</th>
							<td>
								${rs.rxnf }
							</td>
							<th>
								毕业年份
							</th>
							<td>
								${rs.bynf }
							</td>
						</tr>
						<tr>
							<th>
								手机号码
							</th>
							<td>
								${rs.sjhm }
							</td>
							<th>
								固定电话
							</th>
							<td>
								${rs.lxdh }
							</td>

						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								${rs.mzmc }
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
								QQ
							</th>
							<td>
								${rs.qq }
							</td>
							<th>
								Email
							</th>
							<td>
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td>
								${map.jtdz }
							</td>
							<th>
								家庭邮编
							</th>
							<td>
								${map.jtyb }
							</td>
						</tr>
						<tr>
							<th>
								通迅地址
							</th>
							<td colspan="3">
								${rs.lxdz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>就业协议</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								就业情况
							</th>
							<td width="34%">
								${rs.jybzmc }
							</td>
							<th width="16%">
								毕业去向
							</th>
							<td width="34%">
								${rs.byqxmc }
							</td>

						</tr>
						<tr>
							<th>
								用人单位性质
							</th>
							<td width="34%">
								${rs.dwxzmc }
							</td>
							<th>
								用人单位
							</th>
							<td>
								${rs.dwmc }
							</td>

						</tr>
						<tr>
							<th>
								单位隶属部门
							</th>
							<td>
								${rs.lsbmmc }
							</td>
							<th>
								用人单位所在地
							</th>
							<td>
								${rs.dwszd }
							</td>
						</tr>
						<tr>
							<th>
								实际所在地
							</th>
							<td>
								${rs.sjdwszdmc }
							</td>
							<th>
								报到证号
							</th>
							<td>
								${rs.bdzh }
							</td>
						</tr>
						<tr>
							<th>
								派遣时间
							</th>
							<td>
								${rs.pqsj }
							</td>
							<th>
								档案处理情况
							</th>
							<td>
								${rs.daclqk }
							</td>
						</tr>
						<tr>
							<th>是否改派</th>
							<td>${rs.isgp }</td>
							<th>专业是否对口</th>
							<td>${rs.sfdk }</td>
						</tr>

						<logic:equal value="是" name="rs" property="isgp">
						<tr>
							<th>
								改派次数
							</th>
							<td>
								${rs.gpcs }
							</td>
							<th>
								改派原因
							</th>
							<td>
								${rs.gpyy }
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								原单位名称
							</th>
							<td>
								${rs.yrdwmc }
							</td>
							<th>
								原报到证号
							</th>
							<td>
								${rs.ybdzh }
							</td>
						</tr>
						
						<tr>
							<th>
								就业行业
							</th>
							<td>
								${rs.jyhymc }
							</td>
							<th>
								户口去向
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.hkqydz }
							</td>
						</tr>
						<tr>
							<th>扩展项1</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx1 }</td>
						</tr>
						<tr>
							<th>扩展项2</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx2 }</td>
						</tr>
						<tr>
							<th>扩展项3</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx3 }</td>
						</tr>
						<tr>
							<th>扩展项4</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx4 }</td>
						</tr>
						<tr>
							<th>扩展项5</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx5 }</td>
						</tr>
						<tr>
							<th>扩展项6</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx6 }</td>
						</tr>
						<tr>
							<th>扩展项7</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx7 }</td>
						</tr>
						<tr>
							<th>扩展项8</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx8 }</td>
						</tr>
						<tr>
							<th>扩展项9</th>
							<td colspan="3" style="word-break:break-all;">${rs.kzx9 }</td>
						</tr>
						
						
						<tr>
							<th>
								<font color="red"></font>备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.jybz1 }
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									<bean:message key="lable.xb" />审核
								</th>
								<td>
									${rs.xysh }
								</td>
								<th>
									学校审核
								</th>
								<td>
									${rs.xxsh }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.xxshyj }
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="sh" name="doType">
							<logic:equal value="xy" name="userType">
								<tr>
									<th>
										<bean:message key="lable.xb" />意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
							  		<th>审核人</th>
							  		<td>
							  			${userNameReal }
							  			<html:hidden property="save_xyshr" value="${userName }"/>
							  		</td>
							  		<th>审核时间</th>
							  		<td>
							  			${now }
							  			<html:hidden property="save_xyshsj" value="${now }"/>
							  		</td>
							  	</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<tr>
									<th>
										学校意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xxshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
							  		<th>审核人</th>
							  		<td>
							  			${userNameReal }
							  			<html:hidden property="save_xxshr" value="${userName }"/>
							  		</td>
							  		<th>审核时间</th>
							  		<td>
							  			${now }
							  			<html:hidden property="save_xxshsj" value="${now }"/>
							  		</td>
							  	</tr>
							</logic:notEqual>
						</logic:equal>
					</tbody>
				</table>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				close();
				dialogArgumentsQueryChick();
			</script>
		</logic:present>
	</body>