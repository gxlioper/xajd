<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/lkxxgl/js/lkxx.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#xb").attr("checked","true");
		})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xgygl_lkxxdj" method="post" styleId="lkxxForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ס���˻�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th width="20%">
							<span><font color="red">*</font></span>����
						</th>
						<td width="30%">
							<html:text property="xm" styleId="xm" maxlength="10"></html:text>
						</td>
						<th><span><font color="red">*</font></span>���֤��</th>
						<td>
							<html:text property="sfzh" styleId="sfzh" onblur="checkSfzh(this)" maxlength="18"></html:text>
						</td>
					</tr>
					<tr>
						<th width="20%">
							<span><font color="red">*</font></span>�Ա�
						</th>
						<td width="30%">							
							<html:radio property="xb" styleId="xb" value="��">��</html:radio>
							<html:radio property="xb" styleId="xb" value="Ů">Ů</html:radio>
						</td>
						<th>�������ڵ�</th>
						<td>
							<html:text property="hkszd" styleId="hkszd" maxlength="40" onblur="checkLen(this, 40)" ></html:text>
						</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>ס����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>��ס¥��
							</th>
							<td width="30%">
								<html:text property="rzld" styleId="rzld" maxlength="15"></html:text>
							</td>
							<th><span><font color="red">*</font></span>��ס����</th>
							<td>
								<html:text property="rzfj" styleId="rzfj" maxlength="15"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ס��λ
							</th>
							<td width="30%">
								<html:text property="rzcw" styleId="rzcw" maxlength="8"></html:text>
							</td>
							<th>��סѺ��Ԫ��</th>
							<td>
								<html:text property="rzyj" styleId="rzyj" onkeyup="value=value.replace(/[^\d]/g,'');" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>��סʱ��
							</th>
							<td width="30%">
								<html:text property="rzsj" styleId="rzsj" onfocus="showCalendar('rzsj','y-mm-dd',true,'tssj');"></html:text>
							</td>
							<th>����ʱ��</th>
							<td>
								<html:text property="tssj" styleId="tssj" onfocus="showCalendar('tssj','y-mm-dd',false,'rzsj');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								    <button type="button" onclick="save('save');">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

