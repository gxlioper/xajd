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
				value="/jygl.do?method=xyslqUpdate" />




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
										<logic:notEqual value="update" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=xyslqUpdate&doType=save','save_xh-save_xysbh-save_sflq');">
												保存
											</button>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="update" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=xyslqUpdate&doType=modify','save_xh-save_xysbh-save_sflq');">
											保存
										</button>
									</logic:equal>
									<button id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
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
							<th>
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
								<font color="red">*</font>就业协议书编号
							</th>
							<td>
								<html:text property="save_xysbh" maxlength="20"
									value="${rs.xysbh }"></html:text>
							</td>
							<th>
								<font color="red">*</font>是否领取
							</th>
							<td>
								<html:select property="save_sflq" value="${rs.sflq }">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								领取情况
							</th>
							<td>
								<html:text property="save_lqqk" maxlength="50"
									value="${rs.lqqk }"></html:text>
							</td>
							<th>
								领取时间
							</th>
							<td>
								<html:text property="save_lqsj" value="${rs.lqsj }"
									styleId="lqsj" maxlength="20" onblur='dateFormatChg(this)'
									onclick="showCalendar(this.id,'y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_bz" style="width:85%" rows="5"
									onblur="checkLen(this,500)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
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