<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务-保险、理赔-理赔信息维护</a>
			</p>
		</div>


		<html:form action="/rcsw_bxlp" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" id="url" name="url"
				value="/rcsw_bxlp.do?method=lpwhSave" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>保险、理赔-理赔信息维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button type="button" id="buttonSave"
												onclick="saveUpdate('/xgxt/rcsw_bxlp.do?method=lpwhSave&doType=save','xh-spje-lpje-slrq-zfrq-cxlx');">
												确定
											</button>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="view" name="doType">
										<button type="button" id="buttonSave" onclick="window.close();return false;">
											关闭
										</button>
									</logic:equal>
									<logic:equal value="update" name="doType">
										<button type="button" id="buttonSave"
											onclick="saveUpdate('/xgxt/rcsw_bxlp.do?method=lpwhSave&doType=modi','xh-je');">
											修改
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />

								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="update" name="doType">
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<html:text property="xm" name="rs" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<html:text property="xb" name="rs" readonly="true"></html:text>
							</td>
							<th>
								身份证号
							</th>
							<td>
								<html:text property="sfzh" readonly="true" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" name="rs" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td>
								<html:select property="xydm" name="rs" disabled="true" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								<html:select property="zydm" name="rs" disabled="true" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级名称
							</th>
							<td>
								<html:select property="bjdm" name="rs" disabled="true" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>索赔金额
							</th>
							<td>
								<html:text property="spje" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>
								<font color="red">*</font>理赔金额
							</th>
							<td>
								<html:text property="lpje" name="rs"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>受理日期
							</th>
							<td>
								<html:text property="slrq" name="rs" styleId="slrq"
									readonly="true"
									onclick="return showCalendar('slrq','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
							<th>
								<font color="red">*</font>支付日期
							</th>
							<td>
								<html:text property="zfrq" name="rs" styleId="zfrq"
									readonly="true"
									onclick="return showCalendar('zfrq','y-mm-dd');"
									onblur="dateFormatChg(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>出险类型
							</th>
							<td>
								<html:select property="cxlx" name="rs">
									<html:options collection="cxlxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								联系电话
							</th>
							<td>
								<html:text property="lxdh" name="rs" onblur="checkPhone(this)"></html:text>
							</td>
						</tr>
						<customTag:customTable rsname="rs" nodeslist="bxlpForm"
							doType="updata" scope="request" />
							</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("保存成功！");
	         	if(window.dialogArguments){
					 window.close();
					 window.dialogArguments.document.getElementById('search_go').click();
				}
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
	</html>