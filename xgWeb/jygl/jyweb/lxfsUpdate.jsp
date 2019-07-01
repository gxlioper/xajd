<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>联系方式</a>
			</p>
		</div>
	
	
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="save_xxdm" value="${pkValue }" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>联系方式维护</span>
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
									<logic:notEmpty name="rs">
										<button name="提交" id="saveButton"
											onclick="saveUpdate('jywebUseCheckSession.do?method=lxfsUpdate&doType=modify','save_lxdh-save_czhm-save_dzyx-save_yzbh');">
											保存
										</button>
									</logic:notEmpty>
									<logic:empty name="rs">
										<button name="提交" id="saveButton"
											onclick="saveUpdate('jywebUseCheckSession.do?method=lxfsUpdate&doType=save','save_lxdh-save_czhm-save_dzyx-save_yzbh');">
											保存
										</button>
									</logic:empty>
									<button name="重置" type="reset">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>联系电话
							</th>
							<td width="80%">
								<div class="pos" style="z-index:2">
									<html:text property="save_lxdh" value="${rs.lxdh}"
										maxlength="20" onkeypress="checkPhoneV4(this)"
										onblur="checkPhoneV4(this)"></html:text>
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
								<span class="red">*</span>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真
							</th>
							<td>
								<html:text property="save_czhm" value="${rs.czhm}"
									maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>电子邮箱
							</th>
							<td>
								<div class="pos" style="z-index:1">
									<html:text property="save_dzyx" value="${rs.dzyx}"
										onkeypress="checkEmaile(this)"
										onblur="checkEmaile(this)"></html:text>
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
								<span class="red">*</span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编
							</th>
							<td>
								<html:text property="save_yzbh" value="${rs.yzbh}"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="6"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>联系地址
							</th>
							<td>
								<html:text property="save_lxdz" value="${rs.lxdz }"
									maxlength="50" style="width:90%"></html:text>
							</td>
						</tr>
					</tbody>
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
