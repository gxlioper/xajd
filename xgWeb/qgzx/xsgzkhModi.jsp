<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/qgzxgzkh.js"></script>
	<script>
		function save(){
			if(filedNotNull('ygzsj-ffcjje','-')){
				refreshForm('qgzxkh.do?method=saveXxsgzkh');
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ���� - ѧ��������¼ - �޸���Ϣ</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>ѧ��
							<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
						</th>
						<td>
							${rs.xh}
						</td>
						<th>��λ</th>
						<td>
							${rs.gwdm}
							<input type="hidden" name="gwdm" id="gwdm" value="${rs.gwdm}"/>
							<input type="hidden" name="gwsbsj" id="gwsbsj" value="${rs.gwsbsj}"/>
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<th>���</th>
						<td>
							${rs.nd}
							<input type="hidden" name="nd" id="rq" value="${rs.nd}"/>
						</td>						
					</tr>
					<tr>
						<th>�༶</th>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<th>�·�</th>
						<td>
							${rs.yf}
							<input type="hidden" name="yf" id="yf" value="${rs.yf}"/>
						</td>						
					</tr>	
					</tbody>
					<thead>				
					<tr>
						<th colspan="4" bgcolor="D0E0EE" onclick="ele('gzjlTable').style.display = ele('gzjlTable').style.display=='none' ? '' : 'none'">
							<span><b style="cursor:hand" title="��ʾ/����">ѧ�����¹�����¼</b></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="display:none" id="gzjlTable">
						<td colspan="4">
							<table width="100%" class="formlist">
									<thead>
										<tr>
											<th>����</thd>
											<th>������ʼʱ��</th>
											<th>��������ʱ��</th>
										    <th>�ܹ���ʱ��</th>
											<th>��������</th>
										</tr>
									</thead>
									<tbody>
										<logic:iterate name="gzjlRs" id="s">
											<tr>
												<logic:iterate id="v" name="s">
													<td>
														<font color="red"><bean:write name="v" /></font>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</tbody>								
							</table>
						</td>
					</tr>
					<tr>
						<th>���ܹ���ʱ��</th>
						<td>
							<bean:write name="rs" property="sj" />
						</td>	
						<th></th>
						<td>
							
						</td>		
					</tr>
					<tr>
						<th><span class="red">*</span>ʵ���¹���ʱ��</th>
						<td>
							<html:text property="ygzsj" name="rs" styleId="ygzsj" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>
						</td>
						<th><span class="red">*</span>���ų����</th>
						<td>
							<html:text property="ffcjje" name="rs" styleId="ffcjje" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>
						</td>						
					</tr>
					<tr>
						<th>��������</th>
						<td>
							<html:select property="gzpj" name="rs" styleId="gzpj">
								<html:option value=""></html:option>
								<html:option value="����">����</html:option>
								<html:option value="����">����</html:option>
								<html:option value="�е�">�е�</html:option>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>						
					</tr>
					<tr>
						<th>��������</th>
						<td colspan="3">
							<html:textarea property="gzbx" styleId="gzbx" name="rs" cols="70" rows="4" onblur="chLeng(this,'500')"></html:textarea>
						</td>				
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3"><html:textarea property="bz" styleId="bz" name="rs" cols="70" rows="4" onblur="chLeng(this,'300')"></html:textarea>
						</td>			
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							<logic:notEqual value="view" name="doType">
				            <button type="button"
								onclick="save();return false;"
								style="width:80px">
								�� ��
							</button>
							</logic:notEqual>
							<button type="button" onclick="window.close();return false;"
								style="width:80px">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>	
				</table>
			</div>
			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert("����ʧ�ܣ�");
						Close();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
