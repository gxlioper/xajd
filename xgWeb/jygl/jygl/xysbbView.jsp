<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			function saveData(){
				var userType = $('userType').value;
				
				if ("admin"==userType || "xx"==userType){
				
					var xxsh = $('save_xxsh').value;
					var xxysbh = $('save_xxysbh').value;
				
					if ("ͨ��"==xxsh && xxysbh == "" ){
						alert("����д�¾�ҵЭ������!");
						return false;
						
					} else if ("ͨ��"!=xxsh ){
						$('save_xxysbh').value = "";
					}
				}
				
				
				saveUpdate('/xgxt/jygl.do?method=xysbbsq&doType=modify','save_xh-save_sqsj');
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
									<logic:equal value="sh" name="doType">
										<button id="buttonSave" onclick="saveData();">
											����
										</button>
									</logic:equal>
									<button id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xh }
								<html:hidden property="save_xh" value="${rs.xh }" />
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
								��ҵЭ������
							</th>
							<td>
								${rs.xysbh }
							</td>
							<th>
								<logic:notEqual value="xy" name="userType" scope="session">
									<font color="red">*</font>
								</logic:notEqual>
								�¾�ҵЭ������
							</th>
							<td>
								<html:text property="save_xxysbh" maxlength="20"
									value="${rs.xxysbh }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td>
								${rs.bblbmc }
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								${rs.sqsj }
								<html:hidden value="${rs.sqsj }" property="save_sqsj" />
							</td>
						</tr>
						<tr>
							<th>
								���뱨��
							</th>
							<td colspan="3">
								${rs.sqbg }
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								${rs.bz }
							</td>
						</tr>
						<logic:equal value="xy" name="userType" scope="session">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									���
								</th>
								<td>
									<html:select property="save_xysh" value="${rs.xysh }">
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
									<html:hidden property="save_xyshsj" value="${now }" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									������
								</th>
								<td colspan="3">
									<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
										style="width:85%" rows="5"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
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
									<bean:message key="lable.xsgzyxpzxy" />
									������
								</th>
								<td colspan="3">
									${rs.xyshyj }
								</td>
							</tr>
							<tr>
								<th>
									ѧУ���
								</th>
								<td>
									<html:select property="save_xxsh" value="${rs.xxsh }">
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
									<html:hidden property="save_xxshsj" value="${now }" />
								</td>
							</tr>
							<tr>
								<th>
									ѧУ������
								</th>
								<td colspan="3">
									<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
										style="width:85%" rows="5"></html:textarea>
								</td>
							</tr>
						</logic:notEqual>
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