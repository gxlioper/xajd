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
					$('zpnr').value = frames('eWebEditor1').getHTML();
				}
			}
		</script>
		<logic:present name="message">
			<script type="text/javascript">
				alert("${message}");
	 			if(window.dialogArguments){
	 				window.close();
	 				dialogArgumentsQueryChick();
	 			}	
			</script>
		</logic:present>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>У����Ƹά��</a>
			</p>
		</div>

		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="save_id" value="${rs.id }" />
			<input type="hidden" name="tempDwmc" id="tempDwmc"
				value="${tempDwmc }" />
			<input type="hidden" name="tempGwValue" id="tempGwValue"
				value="${tempGwValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>У����Ƹά��</span>
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
									<logic:equal value="sh" name="doType">
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&save_shzt=ͨ��&doType=save','');">
											ͨ��
										</button>
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&save_shzt=��ͨ��&doType=save','');">
											��ͨ��
										</button>
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&save_shzt=�˻�&doType=save','');">
											�˻�
										</button>
										<button name="�ر�" onclick="window.close();return false;">
											�ر�
										</button>
									</logic:equal>
									<logic:notEqual value="sh" name="doType">
									<logic:notEqual value="show" name="doType">
										<html:hidden property="save_shzt" value="������"/>
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&doType=save','save_zpzt-save_zpsj-save_zpdd-tempGwValue');">
											����
										</button>
										<button onclick="refreshForm('jyweb_ztzpManage.do');">
											����
										</button>
									</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="show" name="doType">
										<button name="�ر�" onclick="window.close();return false;">
											�ر�
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>��Ƹ����
							</th>
							<td width="86%" colspan="3">
								<html:text property="save_zpzt" maxlength="50" style="width:90%"
									value="${rs.zpzt}"></html:text>
							</td>
						</tr>
						<tr>
							<th width="14%">
								<span class="red">*</span>��Ƹʱ��
							</th>
							<td width="36%">
								<html:text property="save_zpsj" styleId="zpsj"
									value="${rs.zpsj}" onblur='dateFormatChg(this)'
									onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
							</td>
							<th width="14%">
								<span class="red">*</span>��Ƹ�ص�
							</th>
							<td width="36%">
								<html:text property="save_zpdd" maxlength="50"
									value="${rs.zpdd}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								${rs.fbr }
								<input type="hidden" value="${jyweb_realName }" name="save_fbr" />
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								${rs.fbsj }
							</td>
						</tr>
						<tr>
							<th>
								��Ƹ��λ
							</th>
							<td colspan="2">
								<div id="gwxx">
									<table style="width:100%">
										<thead>
											<tr>
												<td>
													��λ����
												</td>
												<td>
													רҵ/��λ����
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:iterate id="v" name="ztzpGwList">
												<tr>
													<td>
														${v.gsmc }
													</td>
													<td>
														${v.zpzwmc }
													</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</div>
							</td>
							<td>
							<logic:notEqual value="show" name="doType">
								<button
									onclick="showTopWin('/xgxt/jyweb.do?method=gwxxk',650,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
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
						<tbody >
						<tr>
							<th>
								<span class="red">*</span>��Ƹ����
							</th>

							<td colspan="3">
								<input type="hidden" name="save_zpnr" id="zpnr"
									value="<bean:write name="rs" property="zpnr" filter="true"/>" />
								<input type="hidden" name="content1" id="content1"
									value="<bean:write name="rs" property="zpnr" filter="true"/>" />
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
										scrolling="no" width="96%" height="350">
								</iframe>
							</td>
						</tr>
					</tbody>
					<logic:equal value="sh" name="doType">
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
								<th>
									�����
								</th>
								<td>
									${jyweb_realName }
									<html:hidden property="save_shr" value="${jyweb_userName }" />
								</td>
								<th>
									���ʱ��
								</th>
								<td>
									${nowTime }
									<html:hidden property="save_shsj" value="${nowTime }" />
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:equal value="show" name="doType">
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
