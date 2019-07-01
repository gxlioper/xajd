<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body>
		<html:form action="/rcsw_xszgl.do" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="${rs.pkValue}" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button type="button" class="button2"
											onclick="saveData('rcsw_xszgl.do?method=xszbbshOne&doType=save','')">
											保 存
										</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>补办申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
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
								专业
							</th>
							<td>
								${rs.zymc }
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
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								政治面貌
							</th>
							<td>
								${rs.zzmmmc }
							</td>
							<th>
								学制
							</th>
							<td>
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th>
								出生日期
							</th>
							<td>
								${rs.csrq }
							</td>
							<th>
								入学时间
							</th>
							<td>
								${rs.rxrq }
							</td>
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
							<th>
								手机号码
							</th>
							<td>
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th>
								申请时间
							</th>
							<td>
								${rs.sqsj}
							</td>
							<th>
								补办证件
							</th>
							<td>
								<html:select property="save_bblxdm" value="${rs.bblxdm }" disabled="true">
									<html:options collection="bblxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly}
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="shxx">
							<logic:iterate id="s" name="shxx">
								
								<logic:equal value="${rcswForm.shgw }" name="s" property="mc">
									<tr>
										<th>
											审核步骤
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
											<html:hidden property="shgw" value="${s.mc }"/>
										</th>
										<td>
											<html:select property="shjg" value="${s.shzt }">
												<html:options collection="shztList" property="en" labelProperty="cn"/>
											</html:select>
										</td>
										<tr>
											<th>审核时间</th>
											<td>
												<html:text property="shsj" value="${shsj }"></html:text>
											</td>
											<th>审核人</th>
											<td><html:text property="shr" value="${userName }" readonly="true"></html:text></td>
										</tr>
										<tr>
											<th>审核意见</th>
											<td colspan="3" style="word-break:break-all;">
												<html:textarea property="shyj" value="${s.shyj }" style="width:95%" rows="5"></html:textarea>
											</td>
										</tr>
									</tr>
								</logic:equal>
								<logic:notEqual value="${rcswForm.shgw }" name="s" property="mc">
									<tr>
										<th>
											审核步骤
										</th>
										<td>
											${s.xh }
										</td>
										<th>
											${s.mc }
										</th>
										<td>
											${s.shzt }
										</td>
									</tr>
									<tr>
										<th>审核时间</th>
										<td>${s.shsj }</td>
										<th>审核人</th>
										<td>${s.shr }</td>
									</tr>
									<tr>
										<th>审核意见</th>
										<td colspan="3" style="word-break:break-all;">
											${s.shyj }
										</td>
									</tr>
								</logic:notEqual>
							</logic:iterate>
						</logic:present>
					</tbody>
				</table>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		dialogArgumentsQueryChick();
			 		window.close();
			 	}
			</script>
		</logic:present>
	</body>
</html>
