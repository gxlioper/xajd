<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xshdgl/js/xshdgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("[name=hdlx][value=0]").attr("checked",true);
				changeBmmc();
			})
			
		</script>
		<style type = "text/css">
			
		</style>
	</head>
	<body>
		<html:form action="/xshdgl_xshdgl" method="post" styleId="XshdglForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>�����</th>
							<td colspan="1">
								<html:text property="hdmc" styleId="hdmc" maxlength="25" />
							</td>
							<th><font color="red">*</font>�ʱ��</th>
							<td>
								<html:text property="hdsj" styleId="hdsj" maxlength="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��ص�</th>
							<td>
								<html:text property="hddd" styleId="hddd" maxlength="25" />
							</td>
							<th><font color="red">*</font>�����</th>
							<td>
								<div>
									<html:radio property="hdlx" value="0" >У��</html:radio>
									<html:radio property="hdlx" value="1">У��</html:radio>
								</div>
							</td>
							
						</tr>
						<tr>
							<th><font color="red">*</font>���쵥λ</th>
							<td>
								<html:text property="zbdwdm" styleId="zbdwdm" maxlength="50"/>
							</td>
							<th><font color="red">*</font>Э�쵥λ</th>
							<td>
								<html:text property="xbdwdm" styleId="xbdwdm"  maxlength="50"/>
							</td>
							
							
						</tr>
						<tr>
							<th><font color="red">*</font>�а쵥λ</th>
							<td>
								<html:text property="cbdwdm" styleId="cbdwdm" maxlength="50"/>
							</td>
							<th><font color="red">*</font>�������</th>
							<td><html:text property="hdfzr" styleId="hdfzr"  maxlength="25" ></html:text></td>
						</tr>
						<tr>
							<th><font color="red">*</font>������Ա
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="cyry" styleId="cyry" style="width:99%;" rows="4" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>����</br><font color="red">(��1000��)</font></th>
							<td colspan="3">
								<html:textarea property="hdjj" styleId="hdjj" style="width:99%;" rows="4" onblur="checkLen(this,1000);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>�����ϴ�</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//��׺
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//����ļ���С ��λM
												maxsize: 10,
												//��Ÿ������������id
												elementid : 'filepath'
												});
										});
										
							</script>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveXshdgl('save');">
										��    ��
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
		</html:form>
	</body>
	
</html>