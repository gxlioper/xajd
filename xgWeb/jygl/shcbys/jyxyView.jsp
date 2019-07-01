<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业协议详细信息</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="url" value="/jygl.do?method=jyxywh" />
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
								<span>第一部分</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td align="left" width="34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
							</td>
							<th width="16%" align="right">
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
								<bean:message key="lable.xsgzyxpzxy" />
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
								${rs.sydq }
							</td>
						</tr>
						<tr>
							<th>
								户籍地址
							</th>
							<td colspan="3">
								${rs.syds }${rs.sydx }
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
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>第二部分</span>
							</td>
						</tr>
					</thead>
					<tbody>

						<tr>
							<th>
								就业类型
							</th>
							<td width="34%">
								${rs.jybzmc }
							</td>
							<th>
								政治面貌
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								考取学校
							</th>
							<td>
								${rs.kqxx }
							</td>
							<th>
								主管部门
							</th>
							<td>
								${rs.zgdwdm }
							</td>
						</tr>
						<tr>
							<th>
								实际就业单位
							</th>
							<td>
								${rs.dwmc }
							</td>
							<th>
								报到证单位名称
							</th>
							<td>
								${rs.bdzdw }
							</td>
						</tr>
						<tr>
							<th>
								推荐类型
							</th>
							<td>
								${rs.tjlxmc }
							</td>
							<th>
								解约情况
							</th>
							<td>
								${rs.jyqk }
							</td>
						</tr>
						<tr>
							<th>
								未就业标志
							</th>
							<td>
								${rs.wjybz }
							</td>
							<th>
								未就业原因
							</th>
							<td>
								${rs.wjyyy }
							</td>
						</tr>
						<tr>
							<th>
								是否解约
							</th>
							<td>
								${rs.sfjy }
							</td>
							<th>
								是否违约
							</th>
							<td>
								${rs.sfwy }
							</td>
						</tr>
						<tr>
							<th>报到证当<br/>天审核情况</th>
							<td>
								${rs.dtshqk }
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td>
								${rs.lxdz }
							</td>
							<th>
								邮编
							</th>
							<td>
								${rs.yzbh }
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
								Email
							</th>
							<td>
								${rs.dzyx }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>第三部分</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<logic:equal value="不通过" name="rs" property="xxsh">
									<html:hidden property="save_xxsh" value="未审核"/>
								</logic:equal>
							
								组织机构代码
							</th>
							<td width="34%">
								${rs.zzjgdm }
							</td>
							<th>
								单位地址
							</th>
							<td width="34%" style="word-break:break-all;">
								${rs.dwszd }
							</td>
						</tr>
						<tr>
							<th>
								单位电话
							</th>
							<td>
								${rs.dwdh }
							</td>
							<th>
								单位邮编
							</th>
							<td>
								${rs.dwyb }
							</td>
						</tr>
						<tr>
							<th>
								单位隶属
							</th>
							<td>
								${rs.lsbmmc }
							</td>
							<th>
								单位类别
							</th>
							<td>
								${rs.dwxzmc }
							</td>
						</tr>
						<tr>
							<th>
								单位行业
							</th>
							<td>
								${rs.jyhymc }
							</td>
							<th>
								实际工作所在地
							</th>
							<td>
								${rs.sjdwszdmc }
							</td>
						</tr>
						<tr>
							<th>
								档案接收地
							</th>
							<td style="word-break:break-all;">
								${rs.dajsd }
							</td>
							<th>
								档案接收地地址
							</th>
							<td style="word-break:break-all;"> 
								${rs.dajsddz }
							</td>
						</tr>
						<tr>
							<th>
								档案接收地邮编
							</th>
							<td>
								${rs.dajsdyb }
							</td>
							<th>
								户口迁移地址
							</th>
							<td style="word-break:break-all;">
								${rs.hkqydz }
							</td>
						</tr>
						<tr>
							<th>
								试用期工资
							</th>
							<td>
								${rs.syqgz }
							</td>
							<th>
								转正后工资
							</th>
							<td>
								${rs.zzhgz }
							</td>
						</tr>
						<tr>
							<th>
								专业对口
							</th>
							<td>
								${rs.sfdk }
							</td>
							<th>
								登记日期
							</th>
							<td>
								${rs.djrq }
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									辅导员审核
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
									辅导员审核意见
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
										<font color="red">*</font>辅导员审核
									</th>
									<td>
										<html:select property="save_xysh" name="rs">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<td>
										<html:hidden property="save_xyshr" value="${userName }" />
										<html:hidden property="save_xyshsj" value="${now }" />
									</td>
									<td></td>
								</tr>
								<tr>
									<th>
										辅导员意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<tr>
									<th>
										<font color="red">*</font>学校审核
									</th>
									<td>
										<html:select property="save_xxsh" name="rs">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
									<td>
										<html:hidden property="save_xxshr" value="${userName }" />
										<html:hidden property="save_xxshsj" value="${now }" />
									</td>
									<td></td>
								</tr>
								<tr>
									<th>
										学校意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xxshyj" name="rs"
											style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal value="sh" name="doType">
										<logic:equal value="xy" name="userType">
											<button
												onclick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save','save_xysh');">
												保存
											</button>
										</logic:equal>
										<logic:notEqual value="xy" name="userType">
											<button 
												onclick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&doType=save','save_xxsh ');">
												保存
											</button>
										</logic:notEqual>
									</logic:equal>
									<button onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>