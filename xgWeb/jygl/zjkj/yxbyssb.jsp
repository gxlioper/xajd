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
				<em>您的当前位置:</em><a>毕业生详细信息</a>
			</p>
		</div>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="url" id="url"
				value="/jygl.do?method=yxbyssb" />
			<input type="hidden" name="getStuInfo" value="xh" />



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
									<logic:notPresent name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=yxbyssb&doType=save','save_xh');">
											保存
										</button>
									</logic:notPresent>
									<logic:present name="doType">
										<logic:notEqual value="view" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=yxbyssb&doType=modify','save_xh');">
												保存
											</button>
										</logic:notEqual>
										<logic:equal value="view" name="doType">
											<button id="buttonSave" onclick="window.close();return false;">
												关闭
											</button>
										</logic:equal>
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
								<logic:equal value="stu" name="userType" scope="session">
									<html:text property="save_xh" maxlength="20" value="${rs.xh }"
										readonly="true" />
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
									<logic:present name="doType">
										<html:text property="save_xh" maxlength="20" value="${rs.xh }"
											readonly="true" />
									</logic:present>
									<logic:notPresent name="doType">
										<html:text property="save_xh" maxlength="20" value="${rs.xh }"
											onkeypress="autoFillStuInfo(event.keyCode,this)"
											onblur="chkIsStu(this,'view_jy_bysxxb','xh')"></html:text>
										<button
											onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notPresent>
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
								生源地
							</th>
							<td>
								${rs.sydq }${rs.syds }${rs.sydx }
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
								优秀类别
							</th>
							<td>
								<html:select property="save_sqlb" value="${rs.sqlb }">
									<html:option value=""></html:option>
									<html:options collection="sqlbList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>

						<tr>
							<th>
								英语等级
							</th>
							<td>
								<html:text property="save_wysp" maxlength="20"
									value="${rs.wysp }"></html:text>
							</td>
							<th>
								计算机等级
							</th>
							<td>
								<html:text property="save_jsjsp" maxlength="20"
									value="${rs.jsjsp }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								综合测评总分
							</th>
							<td>
								<html:text property="save_zcf" maxlength="20"
									value="${rs.zcf }${zcfMap.fs } " readonly="true"></html:text>
							</td>
							<th>
								综合测评名次
							</th>
							<td>
								<html:text property="save_zcpm" maxlength="20"
									value="${rs.zcpm }${zcfMap.pm }" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								实习环节平均成绩一
							</th>
							<td>
								<html:text property="save_pjcj1" maxlength="10"
									value="${rs.pjcj1 }"></html:text>
							</td>
							<th>
								实习环节平均成绩二
							</th>
							<td>
								<html:text property="save_pjcj2" maxlength="10"
									value="${rs.pjcj2 }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								毕业设计
							</th>
							<td>
								<html:text property="save_bysj" maxlength="50"
									value="${rs.bysj }"></html:text>
							</td>
							<th>
								拟评奖项
							</th>
							<td>
								<html:text property="save_npjx" maxlength="50"
									value="${rs.npjx }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								获奖情况
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_hjqk" style="width:80%" rows="5"
									value="${rs.hjqk }" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_bz" style="width:80%" rows="5"
									value="${rs.bz }" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>

						<logic:equal value="view" name="doType">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核
								</th>
								<td colspan="3">
									${rs.xysh }
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
									学校审核
								</th>
								<td colspan="3">
									${rs.xxsh }
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
						<logic:equal value="sh" name="doType">
							<logic:equal value="xy" name="userType" scope="session">
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										审核
									</th>
									<td colspan="3">
										<html:select property="save_xysh" value="${rs.xysh }">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										审核意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" style="width:80%"
											value="${rs.xyshyj }" rows="5" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType" scope="session">
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										审核
									</th>
									<td colspan="3">
										<html:select property="save_xysh" value="${rs.xysh }"
											disabled="true">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										审核意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xyshyj" style="width:80%"
											disabled="true" value="${rs.xyshyj }" rows="5"
											onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										学校审核
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:select property="save_xxsh" value="${rs.xxsh }">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										学校审核意见
									</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="save_xxshyj" style="width:80%"
											value="${rs.xxshyj }" rows="5" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
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
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>