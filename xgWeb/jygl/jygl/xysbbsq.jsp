<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-zymc-xymc-bjmc-nj-sfzh" />
			<input type="hidden" id="url" name="url"
				value="/jygl.do?method=xysbbsq" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>就业协议书补办</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=xysbbsq&doType=save','save_xh-save_xysbh-save_xxysbh-save_bblb-save_sqsj');">
												保存
											</button>
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal value="update" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=xysbbsq&doType=modify','save_xh-save_xysbh-save_xxysbh-save_bblb-save_sqsj');">
											保存
										</button>
									</logic:equal>
									<logic:present name="doType">
										<button id="buttonSave" onclick="window.close();return false;">
											关闭
										</button>
									</logic:present>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<html:text property="save_xh" maxlength="20" styleId="xh"
									readonly="true" value="${rs.xh }"></html:text>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="stu" name="userType">
										<button
											onclick="showTopWin('/xgxt/jygl.do?method=jyxyData',800,600);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
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
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								班级名称
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
								毕业时间
							</th>
							<td>
								${rs.bynf }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>就业协议书编号
							</th>
							<td>
								<html:text property="save_xysbh" maxlength="20"
									value="${rs.xysbh }"></html:text>

								<html:hidden property="save_sqsj" value="${now }" styleId="sqsj" />

							</td>
							<th>
								<font color="red">*</font>补办类别
							</th>
							<td>
								<html:select property="save_bblb" value="${rs.bblb }">
									<html:options collection="bblbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>

						<logic:equal value="view" name="doType">
							<tr>
								<th>
									新就业协议书编号
								</th>
								<td>
									${rs.xxysbh }
								</td>
								<th>
									申请时间
								</th>
								<td>
									${rs.sqsj }
								</td>
							</tr>
						</logic:equal>

						<tr>
							<th>
								申请报告
							</th>
							<td colspan="3">
								<html:textarea property="save_sqbg" style="width:85%" rows="5"
									onblur="checkLen(this,500)" value="${rs.sqbg }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:85%" rows="5"
									onblur="checkLen(this,500)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核
								</th>
								<td>
									${rs.xysh }
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核时间
								</th>
								<td>
									${rs.xyshsj }
								</td>
							</tr>
							<tr>
								<th>
									学校审核
								</th>
								<td>
									${rs.xxsh }
								</td>
								<th>
									学校审核时间
								</th>
								<td>
									${rs.xxshsj }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核意见
								</th>
								<td colspan="3">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
								</th>
								<td colspan="3">
									${rs.xxshyj }
								</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert('${message}');
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>