<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
		<script type="text/javascript">
		function setCl() {
			var bbyy = $('bbyy').value;
			
			if ('��ʧ' == bbyy) {
				DWRUtil.setValue('sxcl','������ʧ���µı�ֽ');
				DWRUtil.setValue('save_sxcl','������ʧ���µı�ֽ');
				$('sm').innerText = '��ʧ����ԭ�򣺸��ݽ���ʡ�������Ĺ涨,'+
				'����ʧ�ĵ��صǱ��������ǹ������еı�ֽ����������ʧ���¡���'+
				'��ҵ����ѯԭЭ�����ţ����м����Ϲ������еı�����'+
				'����ԭЭ�����������ϵ���ʧ���£��迯����������ҵԺУ����ҵЭ�����ţ�'+
				'������������ʧ��ҵ����ҵЭ���飬����ѧУ ������������������������ϡ�';
			} else if ('����' == bbyy) {
				DWRUtil.setValue('sxcl','ԭ�Ƽ���');
				DWRUtil.setValue('save_sxcl','ԭ�Ƽ���');
				$('sm').innerText ='����ҵ��˫��ѡ���ҵ�Ƽ����е�������11��'+
				'������ʱ��ҵ����ϢΪ׼��֮���ҵ����Ϣ�����䶯��ѧ��ֻ������ز���˵'+
				'�����ɣ����Ƽ������������ġ����Ƽ�������ʱ�����ƶ���ɵĸ�����Ϣ�䶯'+
				'�ı�ҵ�����ھ�ҵ����������վ��www.91job.gov.cn�����ĸ�����Ϣ��';
			} else {
				DWRUtil.setValue('sxcl','');
				DWRUtil.setValue('save_sxcl','');
				$('sm').innerText = 'ע�⣺���뻹�����ء��Ƽ����������������д��ƾ����'+
				'����������ϵ�Ժ��ϵ�����С��Ƽ������������ˡ�Ժ��ϵ�������ϲ�'+
				'ǩ�ֺ󣬴�ӡ�µ��Ƽ���ƾ����������ϡ�����������¡��Ƽ�������ҵ'+
				'����Ƹ��¡�����������Ƽ�����ԭ���Ƽ��������ɾ�ҵ������ջغ󣬷������¡��Ƽ����ϸ��¡�';
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="save_sqsj" value="${now }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="url" id="url"
				value="jygl.do?method=tjbBb" />
			<input type="hidden" name="xh" id="xh"
				value="${xh }" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:text value="${rs.xsxh }" property="save_xh" styleId="xh"
									readonly="true"></html:text>
								<logic:notEqual value="stu" name="userType">
									<button
										onclick="showTopWin('jygl.do?method=jyglData',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.name }
							</td>
						</tr>
						<tr>
							<th>
								ѧУ
							</th>
							<td>
								${rs.xxmc }
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
								���֤��
							</th>
							<td>
								${rs.id }
							</td>
						</tr>
						<tr>
							<th>
								ѧ�����
							</th>
							<td>
								${rs.xslb }
							</td>
							<th>
								����Ա
							</th>
							<td>
								${rs.fdy }
							</td>
						</tr>
						<tr>
							<th>
								��ѧʱ��
							</th>
							<td>
								${rs.nd }
							</td>
							<th>
								��ҵʱ��
							</th>
							<td>
								${rs.bynd }
							</td>
						</tr>
						<tr>
							<th>
								Э������
							</th>
							<td>
								<html:text property="save_xysbh" value="${rs.xysbh }"/>
							</td>
							<th>
								��λ����
							</th>
							<td>
								${rs.jydw }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ԭ��
							</th>
							<td>
								<logic:equal value="view" name="doType">
									<html:select property="save_bbyy" styleId="bbyy"
										onchange="setCl();" value="${rs.bbyy }" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="yyList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<html:select property="save_bbyy" styleId="bbyy"
										onchange="setCl();" value="${rs.bbyy }">
										<html:option value=""></html:option>
										<html:options collection="yyList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:notEqual>

							</td>
							<th>
								�������
							</th>
							<td>
								<html:hidden property="save_sxcl" styleId="save_sxcl"
									value="${rs.sxcl }" />
								<html:select styleId="sxcl" property="save_sxcl" disabled="true"
									value="${rs.sxcl }">
									<html:option value=""></html:option>
									<html:options collection="clList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								������ʾ
							</th>
							<td colspan="3">
								<div style="color:red;width:80%;" id="sm">
									ע�⣺���뻹�����ء�Э���鲹�������������д��ƾ��������������ϵ�Ժ��ϵ�����С�Э���鲹���������ˡ�Ժ��ϵ�������ϲ�ǩ�ֺ�ƾ����������Ϻ͡����������ҵ�����������ȡ�µľ�ҵЭ���顣
								</div>
							</td>
						</tr>
<%--						<logic:equal value="view" name="doType">--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									<bean:message key="lable.xsgzyxpzxy" />--%>
<%--									���--%>
<%--								</th>--%>
<%--								<td colspan="3">--%>
<%--									<html:textarea property="save_xyyj" style="width:80%" rows="8"--%>
<%--										onblur="checkLeng(this,500)" value="${ rs.xyyj}"></html:textarea>--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</logic:equal>--%>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button class="button2" id="buttonSave"
											onClick="saveUpdate('jygl.do?method=tjbBb&doType=save','save_xh-save_bbyy-save_sxcl');">
											���벹��
										</button>
									</logic:notEqual>
									<logic:equal value="view" name="doType">
										<logic:equal value="xy" name="userType">
											<button class="button2" id="buttonSave"
												onClick="saveUpdate('jygl.do?method=showTjb&save_xysh=ͨ��&doType=save','');">
												ͨ&nbsp;&nbsp;��
											</button>
											<button class="button2" id="buttonSave"
												onClick="saveUpdate('jygl.do?method=showTjb&save_xysh=��ͨ��&doType=save','');">
												��ͨ��
											</button>
										</logic:equal>
										<button class="button2" id="buttonSave"
											onClick="window.close();">
											��&nbsp;&nbsp;��
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
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
</html>
