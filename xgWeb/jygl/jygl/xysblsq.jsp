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
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript">
			function szNewJyxybh(){
				var sybh = window.dialogArguments.document.getElementById('sybh').value;
				
				if ($('newjyxybh')){
					if (sybh==0){
						alert("��ҵЭ�����ѷ����꣬����ά��Э������!");
						return false;
					} else {
						dwr.engine.setAsync(false);
							jyglDAO.getWsybh(function(data){
								var wsybh = data.split("@");
								$('newjyxybh').value = wsybh[0];
							});
						dwr.engine.setAsync(true);
						return true;
					} 
				}
			}
			
		</script>

	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="url" id="url"
				value="/jygl.do?method=xysblsq&flg=${flg }" />

			<logic:equal value="stu" name="userType">
				<logic:empty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>��ʾ��</span>
						</h3>
						<p>
							�Բ������ľ�ҵЭ���黹δ�Ǽǹ� ��
						</p>
						<a class="close" title="����"
							onclick="this.parentNode.style.display='none'"></a>
					</div>

					<script defer="defer">
						$('saveButton').disabled = true;
					</script>
				</logic:empty>
			</logic:equal>
			<logic:present name="rs">
				<logic:equal value="1" property="xysbbshjb" name="cssz">
					<logic:equal value="�˻�" name="rs" property="xxsh">
						<html:hidden property="save_xxsh" value="������" />
					</logic:equal>
				</logic:equal>
				<logic:equal value="2" property="xysbbshjb" name="cssz">
					<logic:equal value="�˻�" name="rs" property="xysh">
						<html:hidden property="save_xysh" value="������" />
					</logic:equal>
				</logic:equal>
			</logic:present>
			
			

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notPresent name="doType">
										<button id="saveButton"
											onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&doType=save&flg=${flg }','save_xh-save_bblbdm');">
											����
										</button>
									</logic:notPresent>
									<logic:present name="doType">
										<logic:equal value="update" name="doType">
											<button id="saveButton"
												onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&doType=modify','save_xh-save_bblbdm');">
												����
											</button>
										</logic:equal>
										<logic:equal value="sh" name="doType">
											<logic:equal value="1" name="cssz" property="xysbbshjb">
												<button id="saveButton"
													onclick="if(szNewJyxybh()){saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=ͨ��&doType=modify','');}">
													ͨ��
												</button>
												<button id="saveButton"
													onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=��ͨ��&doType=modify','');">
													��ͨ��
												</button>
												<button id="saveButton"
													onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=�˻�&doType=modify','');">
													�˻�
												</button>
											</logic:equal>
											<logic:equal value="2" name="cssz" property="xysbbshjb">
												<logic:equal value="xy" name="userType" scope="session">

													<logic:equal value="�˻�" name="rs" property="xxsh">
														<button id="saveButton"
															onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=ͨ��&save_xxsh=������&doType=modify','');">
															ͨ��
														</button>
													</logic:equal>
													<logic:notEqual value="�˻�" name="rs" property="xxsh">
														<button id="saveButton"
															onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=ͨ��&doType=modify','');">
															ͨ��
														</button>
													</logic:notEqual>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=��ͨ��&doType=modify','');">
														��ͨ��
													</button>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=�˻�&doType=modify','');">
														�˻�
													</button>
												</logic:equal>
												<logic:notEqual value="xy" name="userType" scope="session">

													<button id="saveButton"
														onclick="if(szNewJyxybh()){saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=ͨ��&doType=modify','');}">
														ͨ��
													</button>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=��ͨ��&doType=modify','');">
														��ͨ��
													</button>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=�˻�&doType=modify','');">
														�˻�
													</button>
												</logic:notEqual>
											</logic:equal>
										</logic:equal>
									</logic:present>
									<logic:equal value="sq" name="flg">
										<button type="reset">
											����
										</button>
									</logic:equal>
									<logic:notEqual value="sq" name="flg">
										<button onclick="window.close();return false;">
											�ر�
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:16%">
								<span class="red">*</span>ѧ��
							</th>
							<td style="width:34%">
								<html:text property="save_xh" value="${rs.xh }" readonly="true"></html:text>
								<logic:notPresent name="doType">
									<logic:notEqual value="stu" name="userType">
										<button
											onclick="showTopWin('/xgxt/jygl.do?method=xysData',800,500);"
											id="buttonFindStu" class="btn_01">
											ѡ��
										</button>
									</logic:notEqual>
								</logic:notPresent>
							</td>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb}
							</td>
							<th>
								���֤��
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								ԭ��ҵЭ������
							</th>
							<td>
								<html:text property="save_yjyxybh" readonly="true"
									value="${rs.yjyxybh }${rs.jyxybh }"></html:text>
							</td>
							<th>
								<span class="red">*</span>�������
							</th>
							<td>
								<html:select property="save_bblbdm" value="${rs.bblbdm }">
									<html:options collection="bblbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��������
								<br />
								<font color="red"><��500��>
								</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_sqly" value="${rs.sqly }"
									style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>

						<logic:equal value="sh" name="doType">
							<logic:equal value="1" name="cssz" property="xysbbshjb">
								<tr>
									<th>
										������
										<br />
										<font color="red"><��500��>
										</font>
									</th>
									<td style="word-break:break-all;" colspan="3">
										<!-- �¾�ҵЭ���� -->
										<html:hidden property="save_newjyxybh" styleId="newjyxybh" />

										<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
											style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										�����
									</th>
									<td>
										${userNameReal }
										<html:hidden property="save_xxshr" value="${userName }" />
									</td>
									<th>
										���ʱ��
									</th>
									<td>
										${now }
										<html:hidden property="save_xxshsj" value="${now }" />
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
								<logic:equal value="xy" name="userType" scope="session">
									<tr>
										<th>
											������
											<br />
											<font color="red"><��500��>
											</font>
										</th>
										<td style="word-break:break-all;" colspan="3">
											<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
												style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
									<tr>
										<th>
											�����
										</th>
										<td>
											${userNameReal }
											<html:hidden property="save_xyshr" value="${userName }" />
										</td>
										<th>
											���ʱ��
										</th>
										<td>
											${now }
											<html:hidden property="save_xyshsj" value="${now }" />
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="session">
									<tr>
										<th>
											������
											<br />
											<font color="red"><��500��>
											</font>
										</th>
										<td style="word-break:break-all;" colspan="3">
											<!-- �¾�ҵЭ���� -->
											<html:hidden property="save_newjyxybh" styleId="newjyxybh" />

											<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
												style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
									<tr>
										<th>
											�����
										</th>
										<td>
											${userNameReal }
											<html:hidden property="save_xxshr" value="${userName }" />
										</td>
										<th>
											���ʱ��
										</th>
										<td>
											${now }
											<html:hidden property="save_xxshsj" value="${now }" />
										</td>
									</tr>
								</logic:notEqual>
							</logic:equal>
						</logic:equal>
						<logic:equal value="view" name="doType">
							<logic:equal value="1" name="cssz" property="xysbbshjb">
								<tr>
									<th>
										ѧУ���
									</th>
									<td>
										${rs.xxsh }
									</td>
									<th>�¾�ҵЭ����</th>
									<td>${rs.newjyxybh }</td>
								</tr>
								<tr>
									<th>
										������
									</th>
									<td style="word-break:break-all;" colspan="3">
										${rs.xxshyj }
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
								<tr>
									<th>
										<bean:message key="lable.xb" />���
									</th>
									<td>
										${rs.xysh }
									</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>
										������
									</th>
									<td style="word-break:break-all;" colspan="3">
										${rs.xyshyj }
									</td>
								</tr>
								<tr>
									<th>
										ѧУ���
									</th>
									<td>
										${rs.xxsh }
									</td>
									<th>�¾�ҵЭ����</th>
									<td>${rs.newjyxybh }</td>
								</tr>
								<tr>
									<th>
										������
									</th>
									<td style="word-break:break-all;" colspan="3">
										${rs.xxshyj }
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
				if (window.dialogArguments) {
					window.close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>
</html>
