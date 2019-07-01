<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cflbdmwhnew/js/cflbdm.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//��ʼ������Ȩ���б�
				var splcId = jQuery("#spl").val();
				updateCffwqxList(splcId);
			});
		</script>
	</head>
	<body>
		<html:form action="/wjcf_cflbdmwh" styleId="cflbdmwhForm" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="wjcf_text" value="<bean:message key="wjcf.text" />"/>
			<div class="style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ִ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								�����������
							</th>
							<td style="width:70%" >
								<input type="text" name="cflbmc" style="width:235px" id="cflbmc" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th style="width:30%" >
								�Ƿ��д�������
							</th>
							<td style="width:70%" >
								<input type="hidden" name='sfszcfqx' id='sfszcfqx' value='' />
								<input type="radio"  name="cfqxsz" value="1" onclick="sfFlag('cfqxsz');" />��
								<input type="radio"  name="cfqxsz" value="2" onclick="sfFlag('cfqxsz');" checked="checked" />��
							</td>
						</tr>
						<tr name="showCfqx" style="display: none">
							<th style="width:30%">
								<font color="red">*</font>��������
							</th>
							<td style="width:70%">
								<input type="hidden" name='cfqx' id='cfqx' value='' />
								<input type="text" name="cfqxV" id="year" onkeyup="onyInt(this);" value="1" maxlength="2" style="width:50px;"/>��
								<input type="text" name="cfqxV" id="month" onkeyup="onyInt(this);" value="0" maxlength="2" style="width:50px;"/>��
								<input type="text" name="cfqxV" id="day" onkeyup="onyInt(this);" value="0" maxlength="2" style="width:50px;"/>��
							</td>
						</tr>
						<tr name="showCfqx" style="display: none">
							<th style="width:30%">
								�������Ƿ����ֹ
							</th>
							<td style="width:70%" >
								<input type="hidden" name='qxnsfkzz' id='qxnsfkzz' value='' style="width:100px;"/>
								<input type="radio"  name="sfkzz" value="1" onclick="sfFlag('sfkzz');" checked="checked">��
								<input type="radio"  name="sfkzz" value="2" onclick="sfFlag('sfkzz');">��
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								�����
							</th>
							<td  style="width:70%">
									
									<html:select property="spl" styleId="spl" style="width:240px">
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:30%">���ַ���Ȩ�޿���</th>
							<td style="width:70%">
								<select id="cffwqx" name="cffwqx" style="width:240px"></select> 
							</td>
						</tr>
						<tr>
							<th style="width:30%">
							<font color="red">*</font>
								�Ƿ������
							</th>
							<td  style="width:70%">
								<input type="checkbox"  id="bkss" value="no" checked="checked" onclick="sfkyss('sfkss',this);"/>��������
								<input type="checkbox"  id="xskss" value="xs" onclick="sfkyss('sfkss',this);"/>ѧ��������
								<input type="checkbox"  id="jskss" value="js" onclick="sfkyss('sfkss',this);"/>��ʦ������
								<input type="hidden" name='sfkss' id='sfkss' value='no' />
 							</td>
						</tr>
						
						<tr id="ssgzr" style="display: none">
							<logic:equal name="xxdm" value="14073">
								<th style="width:30%">
									��������ʱ��
								</th>
							</logic:equal>
							<logic:notEqual name="xxdm" value="14073">
								<th style="width:30%">
									���������ֹ��
								</th>
							</logic:notEqual>
							<td  style="width:70%">
								<input type="text" name="ssslgzr" id="ssslgzr"  onkeyup="onyInt(this);" maxlength="3" style="width:100px;"/>��
							</td>
						</tr>
						<tr>
							<th style="width:30%">
							<font color="red">*</font>
								�Ƿ������<bean:message key="wjcf.text" />
							</th>					
							<td  style="width:70%">
								<input type="checkbox"  id="bksqjc" value="no" checked="checked" onclick="sfkyss('sfksqjc',this);"/>����<logic:notEqual name="xxdm" value="14073"><bean:message key="wjcf.text" /></logic:notEqual><logic:equal name="xxdm" value="14073">����</logic:equal>
								<input type="checkbox"  id="xsksqjc" value="xs" onclick="sfkyss('sfksqjc',this);"/>ѧ����<logic:notEqual name="xxdm" value="14073"><bean:message key="wjcf.text" /></logic:notEqual><logic:equal name="xxdm" value="14073">����</logic:equal>
								<input type="checkbox"  id="jsksqjc" value="js" onclick="sfkyss('sfksqjc',this);"/>��ʦ��<logic:notEqual name="xxdm" value="14073"><bean:message key="wjcf.text" /></logic:notEqual><logic:equal name="xxdm" value="14073">����</logic:equal>
								<input type="hidden" name='sfksqjc' id='sfksqjc' value='no' />
							</td>
						</tr>
						<tr id="jcgzr" style="display: none">
							<logic:equal name="xxdm" value="14073">
								<th style="width:30%">
									<bean:message key="wjcf.text" />����ʱ��
								</th>
							</logic:equal>
							<logic:notEqual name="xxdm" value="14073">
								<th style="width:30%">
									<bean:message key="wjcf.text" />������ʼ��
								</th>
							</logic:notEqual>
							<td  style="width:70%">
								<input type="text" name="jsslqsr" id="jsslqsr" onkeyup="onyInt(this);" maxlength="3" style="width:100px;"/>��
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
									"<font color="red">*</font>"Ϊ������
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="save()">
										�� ��
									</button>
									<button class="button2" type="button" onclick="Close()">
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
