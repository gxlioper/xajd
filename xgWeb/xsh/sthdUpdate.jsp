<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xsh.js'></script>
		<script type="text/javascript">
			function setStxx(text){
				if (""!=text){
					xsh.getstInfo(text,function(data){
						$('stmc').value=data.stmc;
						$('stxz').value=data.stxz;
						$('bmdm').value=data.bmdm;
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xsh" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" name="message" value="${message }" id="message" />



			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>社团活动维护</span>
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
												onclick="saveUpdate('/xgxt/xsh.do?method=sthdUpdate&doType=save','stValue-hdzt-hdsj-hddd');">
												保存
											</button>
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal value="update" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/xsh.do?method=sthdUpdate&doType=modify','stValue-hdzt-hdsj-hddd');">
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
							<th width="16%">
								<font color="red">*</font>社团名称
							</th>
							<td width="30%">
							<logic:notEqual value="update" name="doType">
								<html:select property="stValue" styleId="stValue" onchange="setStxx(this.value)" value="${rs.stmc }${rs.stxz }${rs.bmdm }">
									<html:option value=""></html:option>
									<html:options collection="stList" property="pk" labelProperty="stmc"/>
								</html:select>
							</logic:notEqual>
							<logic:equal value="update" name="doType">
								<html:select property="stValue" styleId="stValue"  disabled="true" value="${rs.stmc }${rs.stxz }${rs.bmdm }">
									<html:option value=""></html:option>
									<html:options collection="stList" property="pk" labelProperty="stmc"/>
								</html:select>
							</logic:equal>
								
								<html:hidden property="stmc" styleId="stmc" value="${rs.stmc }"/>
								<html:hidden property="stxz" styleId="stxz" value="${rs.stxz }"/>
								<html:hidden property="bmdm" styleId="bmdm" value="${rs.bmdm }"/>
								
								
							</td>
							<th width="16%">
								<font color="red">*</font>活动主题
							</th>
							<td width="30%">
								<logic:equal value="update" name="doType">
									<html:text property="hdzt" maxlength="25" value="${rs.hdzt }"
										onkeyup="value=value.replace(/[+&%#]/g,'')" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="hdzt" maxlength="25" value="${rs.hdzt }"
										onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>活动时间
							</th>
							<td>
								<logic:equal value="update" name="doType">
									<html:text property="hdsj" readonly="true" styleId="hdsj"
										value="${rs.hdsj }"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="hdsj" readonly="true" styleId="hdsj"
										onclick="return showCalendar('hdsj','y-mm-dd');"
										onblur="dateFormatChg(this)" value="${rs.hdsj }"></html:text>
								</logic:notEqual>
							</td>
							<th>
								<font color="red">*</font>活动地点
							</th>
							<td>
								<logic:equal value="update" name="doType">
									<html:text property="hddd" maxlength="50" style="width:92%"
										value="${rs.hddd }"
										onkeyup="value=value.replace(/[+&%#]/g,'')" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="hddd" maxlength="50" style="width:92%"
										value="${rs.hddd }"
										onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
								</logic:notEqual>

							</td>
						</tr>
						<customTag:customTable rsname="rs" nodeslist="xshForm" doType="updata" scope="request" />
						<tr>
							<th>
								活动内容
							</th>
							<td colspan="3">
								<html:textarea property="hdnr" rows="8" style="width:99%"
									onblur="checkLen(this,500)" value="${rs.hdnr}"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("保存成功！"+$('message').value);
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