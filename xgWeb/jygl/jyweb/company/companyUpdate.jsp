<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function checkMm(obj){
			if(obj.value != $('mm').value){
				$('errow').className = 'msg_error';
			} else{
				$('errow').className = 'hide';
			}
		}
	</script>
	</head>
	<body>

		<logic:notPresent name="doType">
			<div class="prompt" id="div_help">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					新增加单位初始密码为“0”
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none'"></a>
			</div>
		</logic:notPresent>


		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单位信息维护</span>
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
										<button id="saveButton"
											onclick="saveUpdate('jywebUseCheckSession.do?method=companyUpdate&doType=save',
          		'save_yhm-mm-save_mm-save_qyfr-save_jgdmzh-save_dwxz-save_hyfl-save_lxr-save_lxdh-save_email-save_wz-save_yb-save_dwjj');">
											保存
										</button>
									</logic:notPresent>
									<logic:present name="doType">
										<logic:equal value="update" name="doType">
											<html:hidden property="save_shzt" value="需重审" />

											<button id="saveButton"
												onclick="saveUpdate('jywebUseCheckSession.do?method=companyUpdate&doType=modify',
	          		'save_yhm-save_qyfr-save_jgdmzh-save_dwxz-save_hyfl-save_lxr-save_lxdh-save_email-save_wz-save_yb-save_dwjj');">
												保存
											</button>
										</logic:equal>
										<logic:equal value="sh" name="doType">
											<button id="saveButton"
												onclick="saveUpdate('jywebUseCheckSession.do?method=companyUpdate&doType=modify&save_shzt=通过','');">
												通过
											</button>

											<button id="saveButton"
												onclick="saveUpdate('jywebUseCheckSession.do?method=companyUpdate&doType=modify&save_shzt=不通过','');">
												不通过
											</button>

											<button id="saveButton"
												onclick="saveUpdate('jywebUseCheckSession.do?method=companyUpdate&doType=modify&save_shzt=退回','');">
												退回
											</button>
										</logic:equal>
									</logic:present>
									<logic:notEqual value="dw" name="jyweb_userType">
										<button onclick="window.close();return false;">
											关闭
										</button>
									</logic:notEqual>
									<logic:equal value="dw" name="jyweb_userType">
										<button type="reset">
											重置
										</button>
									</logic:equal>

								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span> 用户名
								<logic:notPresent name="doType">
									<logic:equal value="dw" name="jyweb_userType">
										<input type="hidden" name="save_dwlx" value="单位" />
									</logic:equal>
									<logic:notEqual value="dw" name="jyweb_userType">
										<input type="hidden" name="save_dwlx" value="学校" />
									</logic:notEqual>
								</logic:notPresent>
							</th>
							<td width="36%">
								<logic:present name="doType">
									<html:text property="save_yhm" value="${rs.yhm}"
										readonly="true"></html:text>
								</logic:present>
								<logic:notPresent name="doType">
									<div class="pos" style="z-index:3">
										<html:text property="save_yhm" maxlength="20"
											onkeyup="value=value.replace(/[^\w]/g,'')"
											onfocus="$('msg_div').className = 'msg_prompt'"
											onblur="$('msg_div').className = 'hide'" value="${rs.yhm}"></html:text>
										<div id="msg_div" class="hide">
											<p>
												6-20个字符（字母、数字、下划线）
											</p>
										</div>
									</div>
								</logic:notPresent>
							</td>
							<th width="14%">
								<span class="red">*</span>单位名称
							</th>
							<td width="36%">
								<logic:present name="doType">
									<html:text property="save_dwmc" maxlength="25"
										value="${rs.dwmc}" readonly="true"></html:text>
								</logic:present>
								<logic:notPresent name="doType">
									<html:text property="save_dwmc" maxlength="25"
										value="${rs.dwmc}"></html:text>
								</logic:notPresent>

							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span> 企业法人
							</th>
							<td>
								<html:text property="save_qyfr" maxlength="10"
									value="${rs.qyfr}"></html:text>
							</td>
							<th>
								<span class="red">*</span>机构代码证号
							</th>
							<td>
								<html:text property="save_jgdmzh"
									onkeyup="value=value.replace(/[^\w]/g,'')" maxlength="20"
									value="${rs.jgdmzh}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span> 单位性质
							</th>
							<td>
								<html:select property="save_dwxz" value="${rs.dwxz}">
									<html:options collection="dwxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<span class="red">*</span>行业分类
							</th>
							<td>
								<html:select property="save_hyfl" value="${rs.hyfl}">
									<html:options collection="hyflList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span> 联系人
							</th>
							<td>
								<html:text property="save_lxr" maxlength="10" value="${rs.lxr}"></html:text>
							</td>
							<th>
								<span class="red">*</span>联系电话
							</th>
							<td>
								<div class="pos" style="z-index:2">
									<html:text property="save_lxdh" maxlength="20"
										onblur="checkPhoneV4(this)" value="${rs.lxdh}"></html:text>
									<div id="phoneErrow" class="hide">
										<p>
											电话格式不正确
										</p>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								传真
							</th>
							<td>
								<html:text property="save_cz" maxlength="20" value="${rs.cz}"></html:text>
							</td>
							<th>
								<span class="red">*</span>电子邮箱
							</th>
							<td>
								<div class="pos" style="z-index:1">
									<html:text property="save_email" onblur="checkEmaile(this)"
										maxlength="30" value="${rs.email}"></html:text>
									<div id="emaliErrow" class="hide">
										<p>
											邮箱格式不正确
										</p>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span> 网址
							</th>
							<td>
								<html:text property="save_wz" maxlength="50" value="${rs.wz}"></html:text>
							</td>
							<th>
								<span class="red">*</span>邮编
							</th>
							<td>
								<html:text property="save_yb" maxlength="6"
									onkeyup="value=value.replace(/[^\d]/g,'')" value="${rs.yb}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								联系地址
							</th>
							<td colspan="3">
								<html:text property="save_dz" maxlength="25" style="width:90%"
									value="${rs.dz}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span> 单位简介
								<br />
								<font color="red"><限1000字>
								</font>
							</th>
							<td colspan="3" style="word-break:break-all">
								<html:textarea property="save_dwjj" onblur="checkLen(this,1000)"
									rows="12" style="width:90%" value="${rs.dwjj}"></html:textarea>
							</td>
						</tr>
					</tbody>
					<logic:equal value="sh" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									审核意见
									<br />
									<font color="red"><限500字>
									</font>
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }"
										onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${jyweb_realName }
									<html:hidden property="save_shr" value="${jyweb_userName }" />
								</td>
								<th>
									审核时间
								</th>
								<td>
									${nowTime }
									<html:hidden property="save_shsj" value="${nowTime }" />
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:equal value="view" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									审核时间
								</th>
								<td>
									${rs.shsj }
								</td>
							</tr>
						</tbody>
					</logic:equal>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
 			alert("${message}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 			
 		</script>
		</logic:present>

	</body>
</html>
