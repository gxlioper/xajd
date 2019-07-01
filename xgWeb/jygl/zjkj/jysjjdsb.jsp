<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post" enctype="multipart/form-data">
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="getStuInfo" value="xh" />

			<logic:equal value="update" name="doType">
				<input type="hidden" name="save_shzt" value="未审核" />
			</logic:equal>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>就业实践基地</span>
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
									<logic:notPresent name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=jysjjdsb&doType=save','xydm-save_jtmc-save_qysj-save_qtr-save_sfba-save_qyrs');">
											保存
										</button>
										
										<script>
											if (window.dialogArguments){
												document.write("<button id=\"buttonSave\" onclick=\"window.close();\">关闭</button>");
											} else {
												document.write("<button onclick=\"reset();\">重置</button>");
											}
										</script>
										
									</logic:notPresent>

									<logic:equal value="view" name="doType">
										<button id="buttonSave" onclick="window.close();return false;">
											关闭
										</button>
									</logic:equal>

									<logic:present name="doType">
										<logic:notEqual value="view" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=jysjjdsb&doType=modify','xydm-save_jtmc-save_qysj-save_qtr-save_sfba-save_qyrs');">
												保存
											</button>
											<button id="buttonSave" onclick="window.close();return false;">
												关闭
											</button>
										</logic:notEqual>
									</logic:present>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>部门名称
							</th>
							<td>
								
								<logic:notPresent name="doType">
								<logic:equal value="xy" name="userType">
									<html:select property="save_bmdm" 
												 style="width:160px" 
												 disabled="true"
												 styleId="xy" 
												 value="${userDep}">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
									
									<html:hidden property="save_bmdm" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<html:select property="save_bmdm" style="width:160px" styleId="xy" value="${rs.bmdm }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
								</logic:notEqual>
								</logic:notPresent>
								
								<logic:present name="doType">
									<html:select property="save_bmdm" style="width:160px" styleId="xy" value="${rs.bmdm }" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
									
									<html:hidden property="save_bmdm" value="${rs.bmdm }"/>
								</logic:present>
							</td>
							<th>
								<font color="red">*</font>就业实践基地名称
							</th>
							<td>
								<logic:notPresent name="doType">
									<html:text property="save_jtmc" maxlength="50" style="width:160px" value="${rs.jtmc }" onkeyup="chgPkValue(this)"></html:text>
								</logic:notPresent>
								<logic:present name="doType">
									<html:text property="save_jtmc" maxlength="50" style="width:160px" value="${rs.jtmc }" onkeyup="chgPkValue(this)" readonly="true"></html:text>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>签约时间
							</th>
							<td>
								<html:text property="save_qysj" 
										   value="${rs.qysj }"
										   readonly="true" style="width:160px"
										   onblur="dateFormatChg(this)"
										   onclick="return showCalendar('save_qysj','y-mm-dd');"></html:text>
							</td>
							<th>
								<font color="red">*</font>牵头人
							</th>
							<td>
								<html:text property="save_qtr" maxlength="25" style="width:160px" value="${rs.qtr }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>是否备案
							</th>
							<td>
								<html:select property="save_sfba" style="width:160px" value="${rs.sfba }">
									<html:option value=""></html:option>
									<html:options collection="isNot" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>签约人数
							</th>
							<td>
								<html:text property="save_qyrs" 
										   value="${rs.qyrs }"
										   maxlength="5" 
										   style="width:160px" 
										   onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>学生签约情况记录<br/><font color="red"><限500字></font></th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_qyqkjl" value="${rs.qyqkjl }" style="width:90%" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>备注<br/><font color="red"><限500字></font></th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_bz" value="${rs.bz }" style="width:90%" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<logic:equal value="sh" name="doType">
							<tr>
								<th>
									<font color="red">*</font>审核状态
								</th>
								<td>
									<html:select property="save_shzt" style="width:160px" value="${rs.shzt }">
										<html:options collection="shztList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>审核意见<br/><font color="red"><限400字></font></th>
								<td style="word-break:break-all;" colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }" style="width:90%" rows="5" onblur="checkLen(this,400)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									审核状态
								</th>
								<td>
									${rs.shzt }
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>
									审核状态意见
								</th>
								<td style="word-break:break-all;" colspan="3">
									${rs.shyj }
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