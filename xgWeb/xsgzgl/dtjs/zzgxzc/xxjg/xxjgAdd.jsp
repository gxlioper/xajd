<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/xxjg/js/xxjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#no").attr("checked","checked");
			jQuery("#xh").attr("readonly",true).attr("class","text_nor");
		})
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/dtjs_xxjg">
		<input type="hidden" id="xxdm" value="${xxdm}"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>ת�������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th><font color="red">*</font>���ڵ�֧��</th>
						<td>
							<html:select property="szdzb" styleId="szdzb" style="width:90%">
								<html:options collection="dzbList" property="dzbdm" labelProperty="dzbmc"/>
							</html:select>							
						</td>
						<th><font color="red">*</font>�Ƿ�ʡ��</th>
						<td>
							 <html:select property="sfsn" styleId="sfsn" style="width:90%">
								<html:option value="ʡ��">ʡ��</html:option>
								<html:option value="ʡ��">ʡ��</html:option>
							 </html:select>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>���ձ�����֯��ϵ�ĵ���֯</th>
						<td colspan="3">
							<html:text property="jsdzz" styleId="jsdzz" style="width:90%" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>������֯��ϵ��ȥ��λ</th>
						<td colspan="3">
							<html:text property="sqdw" styleId="sqdw"  maxlength="50" style="width:90%" />
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>���ѽ�������</th>
						<td >
							<html:text property="dfjzrq" styleId="dfjzrq" onclick="return showCalendar('dfjzrq','y-mm-dd');"  style="width:90%"/>
						</td>
						<th><font color="red">*</font>�Ƿ���Ҫ���߻���֤��</th>
						<td >
							<html:radio property="sfkjhyzm" value="��" styleId="yes"/><label for="yes">��</label>
							<html:radio property="sfkjhyzm" value="��" styleId="no" /><label for="no">��</label>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>�����ű��</th>
						<td colspan="3">
							<html:text property="jsxbh" styleId="jsxbh"  maxlength="50" style="width:35%" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="saveForAdd('xh-sfsn-szdzb-jsdzz-sqdw-dfjzrq-jsxbh');return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
