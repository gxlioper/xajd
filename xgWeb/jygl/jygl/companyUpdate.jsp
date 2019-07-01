<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
<body>
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������˵�λ��Ϣ</a>
				</p>
		</div>

	<html:form action="/jygl" method="post">
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="userName" name="userName"value="${userName }" />
		<input type="hidden" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="saveButton" onclick="saveUpdate('/xgxt/jygl.do?method=companyUpdate&doType=save','save_dm-save_mc-save_dwxzdm-save_hyfldm');">
										����
									</button>
									<button onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
					<tr>
						<th style="width:30%">
							<font color="red">*</font>���˵�λ����
						</th>
						<td>
							<html:text property="save_dm" 
									   maxlength="20" style="width:90%"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>���˵�λ����
						</th>
						<td>
							<html:text property="save_mc" 
									   maxlength="25" 
									   style="width:90%"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							���˵�λ���ڵ�
						</th>
						<td>
							<html:select property="save_dwszd" style="width:90%">
								<html:options collection="dwdzList" property="dm" labelProperty="mc"/>
							</html:select>
							
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��λ����
						</th>
						<td>
							<html:select property="save_dwxzdm" style="width:90%">
								<html:options collection="dwxzList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��ҵ����
						</th>
						<td>
							<html:select property="save_hyfldm" style="width:90%">
								<html:options collection="hyList" property="dm"
												labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							��λ�绰
						</th>
						<td>
							<div class="pos" style="z-index:2">
								<html:text property="save_dwdh" 
										   onblur="checkPhoneV4(this)"
										   style="width:90%"></html:text>
								<div id="phoneErrow" class="hide">
									<p>
										�绰��ʽ����ȷ
									</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>
							��λ��ϵ��
						</th>
						<td>
							<html:text property="save_dwlxr" 
									   maxlength="20"
									   style="width:90%"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							�ʱ�
						</th>
						<td>
							<html:text property="save_dwyb" 
									   onkeyup="value=value.replace(/[^\d]/g,'')"
									   maxlength="6"
									   style="width:90%"></html:text>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
