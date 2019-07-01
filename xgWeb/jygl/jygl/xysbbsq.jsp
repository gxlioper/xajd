<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
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
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-zymc-xymc-bjmc-nj-sfzh" />
			<input type="hidden" id="url" name="url"
				value="/jygl.do?method=xysbbsq" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵЭ���鲹��</span>
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
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/jygl.do?method=xysbbsq&doType=save','save_xh-save_xysbh-save_xxysbh-save_bblb-save_sqsj');">
												����
											</button>
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal value="update" name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/jygl.do?method=xysbbsq&doType=modify','save_xh-save_xysbh-save_xxysbh-save_bblb-save_sqsj');">
											����
										</button>
									</logic:equal>
									<logic:present name="doType">
										<button id="buttonSave" onclick="window.close();return false;">
											�ر�
										</button>
									</logic:present>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>ѧ��
							</th>
							<td style="width:34%">
								<html:text property="save_xh" maxlength="20" styleId="xh"
									readonly="true" value="${rs.xh }"></html:text>
								<logic:notEqual value="view" name="doType">
									<logic:notEqual value="stu" name="userType">
										<button
											onclick="showTopWin('/xgxt/jygl.do?method=jyxyData',800,600);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>
								</logic:notEqual>
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
								${rs.xb }
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
								����
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ����
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								�༶����
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xl }
							</td>
							<th>
								��ҵʱ��
							</th>
							<td>
								${rs.bynf }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ҵЭ������
							</th>
							<td>
								<html:text property="save_xysbh" maxlength="20"
									value="${rs.xysbh }"></html:text>

								<html:hidden property="save_sqsj" value="${now }" styleId="sqsj" />

							</td>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<html:select property="save_bblb" value="${rs.bblb }">
									<html:options collection="bblbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>

						<logic:equal value="view" name="doType">
							<tr>
								<th>
									�¾�ҵЭ������
								</th>
								<td>
									${rs.xxysbh }
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									${rs.sqsj }
								</td>
							</tr>
						</logic:equal>

						<tr>
							<th>
								���뱨��
							</th>
							<td colspan="3">
								<html:textarea property="save_sqbg" style="width:85%" rows="5"
									onblur="checkLen(this,500)" value="${rs.sqbg }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:85%" rows="5"
									onblur="checkLen(this,500)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
						<logic:equal value="view" name="doType">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									���
								</th>
								<td>
									${rs.xysh }
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									���ʱ��
								</th>
								<td>
									${rs.xyshsj }
								</td>
							</tr>
							<tr>
								<th>
									ѧУ���
								</th>
								<td>
									${rs.xxsh }
								</td>
								<th>
									ѧУ���ʱ��
								</th>
								<td>
									${rs.xxshsj }
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									������
								</th>
								<td colspan="3">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									ѧУ������
								</th>
								<td colspan="3">
									${rs.xxshyj }
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
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>