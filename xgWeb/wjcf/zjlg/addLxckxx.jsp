<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript">
<!--
	function printB() {
		var pkValue = document.getElementById('pkValue').value;
		window.open('wjcf_zjcm_jclxcbb.do?pkValue=' + pkValue);
	}	
//-->
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - �����У�쿴 - ����ά�� - ����</a>
			</p>
		</div>


		<html:form action="/wjcfzjlgwh" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failinfo" id="failinfo"
				value="${failinfo}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/wjcf_zjlg_addLxckxx.do" />
			<input type="hidden" name="cflb" id="cflb" value="${rs.cflb }" />
			<input type="hidden" name="cfyy" id="cfyy" value="${rs.cfyy }" />
			<input type="hidden" name="cfxn" id="cfxn" value="${rs.xn }" />
			<input type="hidden" name="cfnd" id="cfnd" value="${rs.nd }" />
			<input type="hidden" name="cfsbsj" id="cfsbsj" value="${rs.sbsj }" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="30%">
								<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								<button type="button"
									onclick="showTopWin('wjcf_zjlg_lxckxxquery.do',750,600);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<th width="20%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="30%">
								<html:select property="xn" styleId="xn" style="width:90px"
									styleClass="select">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								<font color="red">*</font>���
							</th>
							<td>
								<html:select property="nd" styleId="nd" style="width:90px">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
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
								�������
							</th>
							<td align="left">
								${rs.cflbmc }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								${rs.nj }
							</td>
							<th>
								����ԭ��
							</th>
							<td align="left">
								${rs.cfyymc }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td align="left">
								${rs.xymc }
							</td>
							<th>
								����ѧ��<br/>
								�������
							</th>
							<td align="left">
								${rs.xn }
								<br/>
								${rs.nd }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								${rs.zymc }
							</td>
							<th>
								�����ĺ�
							</th>
							<td align="left">
								${rs.cfwh }
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left" colspan="">
								${rs.bjmc }
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								${rs.cfsj }
							</td>
						</tr>
						<tr>
							<th>
								������У�쿴ʱ��
							</th>
							<td align="left" colspan="">
								${rs.lxcksj }
							</td>
							<th>

							</th>
							<td align="left">

							</td>
						</tr>
						<tr>
							<th>
								${message }
							</th>
							<td align="left" colspan="3">
								<html:textarea property="xsbx" styleId="xsbx" rows="7"
									style="width:95%"></html:textarea>
							</td>
						</tr>

						<logic:equal value="11647" name="xxdm">
							<tr>
								<th>
									������Ҫ��ǰ
									<br />
									�����У�쿴��
									<br />
									����Ҫ��ǰ��
									<br />
									����ע�����ɣ�
								</th>
								<td align="left" colspan="3">
									<html:textarea property="tqjcly" styleId="tqjcly" rows="5"
										style="width:95%"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btn_save"
										onclick="saveinfo('wjcf_zjlg_addLxckxx.do?operType=save','xh-xn-nd');">
										����
									</button>
									<logic:equal value="11647" name="xxdm">
										<button type="button" id="btn_close" onclick="printB()">
											��ӡ
										</button>
									</logic:equal>
									<button type="button" id="btn_close" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
				<!-- �������ʾҳ�� -->
				<logic:present name="inserted">
					<logic:equal value="yes" name="inserted">
						<script>
					alert("�����ɹ�!");
					Close();
					if (window.dialogArguments) {
						window.dialogArguments.document.getElementById('search_go').click();	
					}
				</script>
					</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
					alert("����ʧ��,ԭ����������ݿ����Ѵ�����ͬ��¼!");
					Close();
					if (window.dialogArguments) {
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
					</logic:equal>
					<logic:equal value="exists" name="inserted">
						<script>
					alert("���ݿ����Ѵ�����ͬ��¼����ȷ�ϣ�");
					Close();
					if (window.dialogArguments) {
						window.dialogArguments.document.getElementById('search_go').click();					
					}
				</script>
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
	</html>