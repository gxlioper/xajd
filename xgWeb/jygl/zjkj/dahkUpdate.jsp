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
		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />



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
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=dahkUpdate&doType=modify','');">
											保存
										</button>
									</logic:notEqual>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
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
									档案投递单位
								</th>
								<td colspan="3">
									<html:text property="save_datddw" maxlength="50"
										style="width:80%" value="${rs.datddw }"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									户口迁移地址
								</th>
								<td colspan="3">
									<html:text property="save_hkqydz" maxlength="100"
										style="width:80%" value="${rs.hkqydz }"></html:text>
								</td>
							</tr>

							<logic:equal value="view" name="doType">
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
								<tr>
									<th>
										学校审核
									</th>
									<td colspan="3">
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