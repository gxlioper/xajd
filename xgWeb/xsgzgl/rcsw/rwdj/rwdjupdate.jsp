<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rwdj/js/rwdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form"
			action="/rwdj.do?method=add&type=save">
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="rwdjid" />
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>����;��
							</th>
							<td colspan="3">
								<html:select  property="rwtj" styleId="rwtj" style="width:155px">
									<html:options collection="rwtjList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>����ʱ��
							</th>
							<td>
								<html:text property="rwsj" styleId="rwsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
							<th width="20%">
								<span class="red">*</span>ѧҵ���
							</th>
							<td>
								<html:text property="xyqk" styleId="xyqk" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>����ǩ����Э��
							</th>
							<td>
								<div>
									<html:radio property="ywqrwxy" value="��">��</html:radio>
									<html:radio property="ywqrwxy" value="��">��</html:radio>
								</div>
							</td>
							<th width="20%">
								<span class="red">*</span>����������
							</th>
							<td>
								<html:text property="bjgms" styleId="bjgms" maxlength="2" onblur="checkInt(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>����״��
							</th>
							<td>
								<html:select property="hyzk" styleId="hyzk">
									<option value="">---��ѡ��---</option>
									<html:option value="δ��">δ��</html:option>
									<html:option value="�ѻ�">�ѻ�</html:option>
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>��ҵ���
							</th>
							<td>
								<html:text property="cylb" styleId="cylb" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>�������
							</th>
							<td>
								<html:select property="hjlb" styleId="hjlb">
									<option value="">---��ѡ��---</option>
									<html:option value="��ͥ����">��ͥ����</html:option>
									<html:option value="���廧��">���廧��</html:option>
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>������ϵ��ʽ
							</th>
							<td>
								<html:text property="bdlxfs" styleId="bdlxfs" maxlength="20" onkeyup="checkInputLxfx(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td>
								<html:text property="fqxm" styleId="fqxm" maxlength="20"></html:text>
							</td>
							<th width="20%">
								�����ֻ�
							</th>
							<td>
								<html:text property="fqsj" styleId="fqsj" maxlength="20" onkeyup="checkInputData(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								ĸ������
							</th>
							<td>
								<html:text property="mqxm" styleId="mqxm" maxlength="20"></html:text>
							</td>
							<th width="20%">
								ĸ���ֻ�
							</th>
							<td>
								<html:text property="mqsj" styleId="mqsj" maxlength="20" onkeyup="checkInputData(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								������ϵ��
							</th>
							<td>
								<html:text property="qtlxr" styleId="qtlxr" maxlength="20"></html:text>
							</td>
							<th width="20%">
								������ϵ�˷�ʽ
							</th>
							<td>
								<html:text property="qtlxrfs" styleId="qtlxrfs" maxlength="20" onkeyup="checkInputLxfx(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td>
								<html:text property="zysl" styleId="zysl" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th width="20%">
								��������
							</th>
							<td>
								<html:text property="yysl" styleId="yysl" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>���۲���
							</th>
							<td>
								<html:text property="fybd" styleId="fybd" maxlength="20"></html:text>
							</td>
							<th width="20%">
								���ӵ�ַ
							</th>
							<td>
								<html:text property="bddz" styleId="bddz" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								����ʿ��
							</th>
							<td>
								<html:text property="yxsb" styleId="yxsb" maxlength="20"></html:text>
							</td>
							<th width="20%">
								��ԭʱ��
							</th>
							<td>
								<html:text property="fysj" styleId="fysj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								�ν�
							</th>
							<td colspan="3">
								<html:textarea property="jj" styleId="jj" style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								����
							</th>
							<td colspan="3">
								<html:textarea property="lg" styleId="lg"  style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ҵʱ��
							</th>
							<td>
								<html:text property="bysj" styleId="bysj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
							<th width="20%">
								ר�ӱ�ʱ��
							</th>
							<td>
								<html:text property="zjbsj" styleId="zjbsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');"maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 ר�ӱ���Ͷ�ѧԺ
							</th>
							<td>
								<html:text property="zjbhjdxy" styleId="zjbhjdxy" maxlength="25"></html:text>
							</td>
							<th width="20%">
								ר�ӱ���רҵ
							</th>
							<td>
								<html:text property="zjbhzy" styleId="zjbhzy" maxlength="25"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 ר�ӱ���ѧ��     
							</th>
							<td>
								<html:text property="zjbhxh" styleId="zjbhxh" maxlength="25" onkeyup="checkInputData(this);"></html:text>
							</td>
							<th width="20%">
								ר�ӱ����ҵʱ��   
							</th>
							<td>
								<html:text property="zjbhbysj" styleId="zjbhbysj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 ���п���     
							</th>
							<td>
								<html:text property="bjyhkh" styleId="bjyhkh" maxlength="20" onkeyup="checkInputData(this);"></html:text>
							</td>
							<th width="20%">
								���п�����     
							</th>
							<td>
								<html:text property="yhkmc" styleId="yhkmc" maxlength="25"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								���п���ַ     
							</th>
							<td>
								<html:text property="yhkdz" styleId="yhkdz" maxlength="50"></html:text>
							</td>
							<th width="20%">
								�����ѧ�Ѳ��� 
							</th>
							<td>
								<html:text property="rwhxfbc" styleId="rwhxfbc" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								���ۺ�ѧ������ 
							</th>
							<td>
								<html:text property="tyhxfzz" styleId="tyhxfzz" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th width="20%">
								��ҵ��λ     
							</th>
							<td>
								<html:text property="jyhdw" styleId="jyhdw" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								����Ա      
							</th>
							<td>
								<html:text property="gwy" styleId="gwy" maxlength="20"></html:text>
							</td>
							<th width="20%">
								��ҵ��          
							</th>
							<td>
								<html:text property="syb" styleId="syb" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								����     
							</th>
							<td>
								<html:text property="gq" styleId="gq" maxlength="20"></html:text>
							</td>
							<th width="20%">
								�ǹ�����        
							</th>
							<td>
								<html:text property="fgjj" styleId="fgjj" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ע
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
					</div>
					<div>
						<table width="100%" border="0" class="formlist"
							style="position: fixed; _position: absolute; bottom: 0;">
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="bz">
											"
											<span class="red">*</span>"Ϊ������
										</div>
										<div class="btn">
											<button type="button"
												onclick="save('rwdj.do?method=update&type=save','xh-rwsj-xyqk-bjgms-hyzk-cylb-hjlb-bdlxfs-fybd');return false;"
												id="buttonSave">
												�� ��
											</button>
											<button type="button" onclick="iFClose();" id="buttonClose">
												�� ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					</html:form>
	</body>
</html>
