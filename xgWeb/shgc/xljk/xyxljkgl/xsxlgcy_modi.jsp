<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>	
	</head>
	<body>
		
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - <bean:message key="lable.xsgzyxpzxy" />���������� - 
					<logic:equal value="10827" name="xxdm">
						����ίԱ
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						ѧ������۲�Ա
					</logic:notEqual>
					 - �޸�
					</a>
				</p>
			</div>
		
		
		<html:form action="/xljk_xsxlgcy" method="post">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="xsxlgcy_xnid" name="xsxlgcy_xnid"
				value="<bean:write name="xsxlgcy_xnid" scope="request"/>" />
				
				<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button  onclick="gcy_save('gcy_modi')"
										id="buttonUpdate">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" readonly="true" />
					</td>
					<th>
						����
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�Ա�
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th>
						��������
					</th>
					<td align="left">
						<html:text styleId="csrq"
							property="csny" onclick="return showCalendar('csrq','y-mm-dd');"
							readonly="true"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<th>
						�༶
					</th>
					<td align="left">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
				<tr>
					<logic:equal value="10856" name="xxdm">
						<th colspan="2">
							<font color="red">*</font>�꼶
						</th>
						<td align="left">
										<html:select property="nj" style="width:70px" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
						</td>
					</logic:equal>
					<logic:notEqual value="10856" name="xxdm">
						<th colspan="2">
							<font color="red">*</font>�ֻ�����
						</th>
						<td align="left">
							<html:text property="sjhm" styleId="zy" />
						</td>
					</logic:notEqual>
					<th>
						���ҵ绰
					</th>
					<td align="left">
						<html:text property="qsdh" />
					</td>
				</tr>
			<logic:equal value="10856" name="xxdm">
					<tr>
						<logic:equal value="10856" name="xxdm">
						<th colspan="2">
						�����Գɼ�
					</th>
					<td align="left">
						<html:text property="xlkscj" styleId="xlkscj"/>
					</td>
					<th>
							<font color="red">*</font>�ֻ�����
						</th>
						<td align="left">
							<html:text property="sjhm" styleId="zy" />
						</td>
					</logic:equal>
					</tr>
				</logic:equal>
				<logic:notEqual value="10856" name="xxdm">
					<tr>
						<th colspan="2">
							
							<logic:equal value="10827" name="xxdm">
							����ίԱ���
						</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
							�۲�Ա���
						</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="gcybh"/>
						</td>
						<th></th>
						<td></td>
					</tr>
				</logic:notEqual>
				<tr>
					<th colspan="2">
						��ע<br/><br/><font color="red"><��400�ַ�></font>
					</th>
					<td colspan="4" style="word-break:break-all;">
						<html:textarea rows="5" style="width:98%" property="bz"
							styleId="a" />
					</td>
				</tr>
				</tbody>
			</table>
			
			<div id="tmpdiv"></div>
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="update_success">
			<script>
				alert("���³ɹ�");
				window.dialogArguments.document.getElementById("search_go1").click();
				Close();
			</script>
		</logic:equal>
		<logic:equal name="message" value="update_fail">
			<script>alert("����ʧ��")</script>
		</logic:equal>
	</logic:notEmpty>
</html>
