<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ְҵ��ѯ - ��ѯ��ʦ�Ǽ�</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯʦ�ǼǱ�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="�ύ" 
											id="saveButton"
											onclick="saveUpdate('/xgxt/jyzx_xszxyy_save.do?act=save','num-name-zxszg-userid-lxdh')">
										�� ��
									</button>
									<button name="����" type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
							<font color="red">*</font>��ѯʦ���
							</th>
							<td width="34%">
								<html:text name="rs" property="num" maxlength="3" 
									onkeyup="value=value.replace(/[^\d]/g,'')"
									style="width=70%" />
							</td>
							<th width="16%">
								<font color="red">*</font>��ѯʦ����
							</th>
							<td width="34%">
								<html:text name="rs" property="name" maxlength="10"
									style="width=55%" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ����
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<html:text name="rs" property="age" maxlength="3"
										  onblur="checkAge(this)"/>
									<div id="ageErrow" class="hide">
						                <p>������1-120������</p>
						            </div>
						        </div>
							</td>
							<th>
								��ѯʦ�Ա�
							</th>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="xb" value="��" checked="checked" />
								&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="xb" value="Ů" />
								&nbsp;&nbsp;Ů
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�û���
							</th>
							<td>
								<html:text name="rs" property="userid" maxlength="10"
									/>
							</td>
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
							<div class="pos" style="z-index:2">
								<html:text name="rs" property="lxdh" 
									onblur="checkPhoneV4(this)"
									maxlength="11" />
								<div id="phoneErrow" class="hide">
										<p>
											�绰��ʽ����ȷ
										</p>
								</div>	
							</div>
								
								<html:password name="rs" property="password" maxlength="12" style="display:none"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ѯʦ�ʸ�
							</th>
							<td>
								<html:text name="rs" property="zxszg"  maxlength="15"/>
							</td>
							<th>
								��������
							</th>
							<td>
							<div class="pos" style="z-index:1">
								<html:text name="rs" property="email" 
									maxlength="30"
									onblur="checkEmaile(this)"/>
								<div id="emaliErrow" class="hide">
										<p>
											�����ʽ����ȷ
										</p>
								</div>	
							 </div>
								<html:password name="rs" property="password2" maxlength="12" style="display:none"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ���
								<br/>
								<font color="red"><��300��></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxsjj" rows="5" 
									onblur="checkLen(this,300)"
									style="width:100%" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("�ύ�ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ظ��ύ������ʧ��!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

