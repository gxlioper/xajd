<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyrz/js/fdyrz_sq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				//jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj");
				//jQuery("#bjlist").load("xsxx_bjgl.do?method=bjList");
				jQuery("#bjlist").hide();
				//jQuery("#but_save").click(save);
				jQuery("#but_close").click(go_back);
				
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl.do?method=zjXxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
								<html:hidden property="splc" name="model" styleId="splc"/>
								<input type="hidden" id="xzbj_text">
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								ְ����
							</th>
							<td width="34%">
							${rs.zgh }
							</td>
							<th width="16%">
								<font color="red">*</font>ר��ְ
							</th>
							<td width="34%" >
								<select name="zjz" id="zjz">
									<option value="רְ" <logic:equal value="רְ" property="zjz" name="rs">selected="selected"</logic:equal>>רְ</option>
									<option value="��ְ" <logic:equal value="��ְ" property="zjz" name="rs">selected="selected"</logic:equal>>��ְ</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
							${rs.xm}
							</td>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%" >
								${rs.xbs }
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
							${rs.mzmc}
							</td>
							<th width="16%">
								������ò
							</th>
							<td width="34%" >
								${rs.zzmmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ϵ�绰
							</th>
							<td width="34%">
							${rs.yddh}
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%" >
								${rs.dzyx}
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td colspan="3">
							${rs.jtzz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								˼������ʱ��
							</th>
							<td width="34%">
							${rs.szgzsj}
							</td>
							<th width="16%">
								��У����ʱ��
							</th>
							<td width="34%" >
								${rs.lxgzsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
							${rs.xl}
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%" >
								${rs.sxzy}
							</td>
						</tr>
						<tr>
							<th>
								��ҵԺУ
							</th>
							<td colspan="3">
							${rs.byyx}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqly">
						<tr>
							<th width="16%">
								<font color="red">*</font>����������
							</th>
							<td width="34%" colspan="3">
								<html:text property="sqdbgs" name="model" styleId="sqdbgs" onkeyup="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��������
								</br>
								<font color="red">����500��</font>
							</th>
							<td width="34%" colspan="3">
								<textarea rows="5" style="width: 90%" id="sqly" name="sqly"></textarea>
							</td>
						</tr>
						
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button"  onclick="save('save')">
										����ݸ�
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button"  onclick="save('submit')">
										�ύ����
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										�ر�
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

