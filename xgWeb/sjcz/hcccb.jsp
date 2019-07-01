<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function checkForm(url) {
				var pk = 'save_cc-save_qdz-save_zdz-save_kcsj-save_ddsj-save_pj';
				var key = pk.split("-");
				
				if(key.length > 0){
					for(var i=0;i<key.length;i++){
						if($(key[i])){
							if($(key[i]).value == ""){
								alert("��\"*\"��Ŀ����Ϊ�գ���ȷ��");
								return false;
							}
						}
					}
				}
			
				var qdz = $('qdz').value.trim();
				var zdz = $('zdz').value.trim();
				
				if (qdz == zdz) {
					alert('���վ���յ�վ����Ϊͬһ��վ��');
					return false;
				}
				refreshForm(url);
			}		
			
		</script>
	</head>
	<body>
		<html:form action="/rcsw_pwgl" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button type="button" class="button2"
												onclick="checkForm('/xgxt/rcsw_pwgl.do?method=ccUpdate&doType=save');"
												id="btn_bc">
												�� ��
											</button>
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal value="update" name="doType">
										<button type="button" class="button2"
											onclick="checkForm('/xgxt/rcsw_pwgl.do?method=ccUpdate&doType=modify');"
											id="btn_bc">
											�� ��
										</button>
									</logic:equal>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="button2" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<html:text property="save_cc" style="width: 120px" styleId="cc"
									value="${rs.cc }" maxlength="6" onblur="checkCc(this)" />
							</td>
							<th>
								��ǰ״̬
							</th>
							<td>
								<html:select property="save_dqzt" style="width:90px"
									styleId="dqzt" value="${rs.dqzt }">
									<html:option value=""></html:option>
									<html:option value="����">����</html:option>
									<html:option value="����">����</html:option>
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>���վ
							</th>
							<td>
								<html:select property="save_qdz" style="width:120px"
									styleId="qdz" value="${rs.qdz}">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czdm"
										labelProperty="czmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>�յ�վ
							</th>
							<td>
								<html:select property="save_zdz" style="width:120px"
									styleId="zdz" value="${rs.zdz}">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czdm"
										labelProperty="czmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ʱ��
							</th>
							<td>
								<html:text property="save_kcsj" style="width: 120px" readonly="true"
									styleId="kcsj" value="${rs.kcsj}" onclick="return showCalendar(this.id,'HH:mm')" />
							</td>
							<th>
								<font color="red">*</font>����ʱ��
							</th>
							<td>
								<html:text property="save_ddsj" style="width: 120px" readonly="true"
									styleId="ddsj" value="${rs.ddsj}" onclick="return showCalendar(this.id,'HH:mm')"/>
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<html:text property="save_yxsj" style="width: 90px"
									styleId="yxsj" value="${rs.yxsj}"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" />
								(Сʱ)
							</td>
							<th>
								<font color="red">*</font>Ʊ��
							</th>
							<td>
								<html:text property="save_pj" style="width: 90px" styleId="pj"
									value="${rs.pj}" onblur="checkMoney(this)" />
								(Ԫ)
							</td>
						</tr>
						<tr>
							<th>
								ͣ��վ
							</th>
							<td colspan="3">
								<html:textarea property='save_tkz'
									style="word-break:break-all;width:99%" rows='4'
									value="${rs.tkz}" onblur="checkLen(this,150)" />
								<br />
								��ʾ�������е�ͣ��վ�Է��ţ������ָ
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