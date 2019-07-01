<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function pubNews(){
				if(frames('eWebEditor1').getHTML()){
					$('content1').value = frames('eWebEditor1').getHTML();
					$('zphnr').value = $('content1').value; 
				}
			}
		</script>
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
	 			if(window.dialogArguments){
	 				window.close();
	 				dialogArgumentsQueryChick();
	 			}	
				</script>
		</logic:present>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƹά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:present name="doType">

										<logic:equal value="sh" name="doType">
											<button
												onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&save_shzt=ͨ��&doType=modify','');">
												ͨ��
											</button>
											<button
												onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&save_shzt=��ͨ��&doType=modify','');">
												��ͨ��
											</button>
											<button
												onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&save_shzt=�˻�&doType=modify','');">
												�˻�
											</button>
										</logic:equal>

										<logic:notEqual value="sh" name="doType">
											<logic:notEqual value="view" name="doType">
												<html:hidden property="save_shzt" value="������"/>
												<button
													onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&doType=modify','save_zplxdm-save_zphbt-save_zphnr');">
													����
												</button>
											</logic:notEqual>
										</logic:notEqual>

									</logic:present>
									<logic:notPresent name="doType">
										<button
											onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&doType=save','save_zplxdm-save_zphbt-save_zphnr');">
											����
										</button>
									</logic:notPresent>
									<button onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>��Ƹ����
							</th>
							<td colspan="3">
								<html:select property="save_zplxdm" value="${rs.zplxdm}">
									<html:options collection="zplxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��Ƹ����
							</th>
							<td colspan="3">
								<html:hidden property="save_fbr" value="${jyweb_userName }" />
								<input type="hidden" name="save_fbsj" value="${nowTime }" />

								<html:text property="save_zphbt" maxlength="50"
									value="${rs.zphbt }" style="width:90%"
									onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>���߱༭��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��Ƹ����
							</th>

							<td colspan="3">
								<input type="hidden" name="content1" value="${rs.zphnr }" />
								<input type="hidden" name="save_zphnr" id="zphnr"
									value="${rs.zphnr }" />
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="99%" height="350">
								</iframe>
							</td>
						</tr>
					</tbody>
					<logic:equal value="sh" name="doType" scope="request">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									������
									<br />
									<font color="red"><��500��>
									</font>
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }"
										onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
								</td>
							</tr>
							<tr>
								<th width="14%">
									�����
								</th>
								<td width="36%">
									${jyweb_realName }
									<html:hidden property="save_shr" value="${jyweb_userName }" />
								</td>
								<th width="14%">
									���ʱ��
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
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									������
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									�����
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									���ʱ��
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
	</body>
</html>
