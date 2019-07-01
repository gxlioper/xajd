<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/gfjyqk/sq/js/gfqksq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
	</head>
	<body>
		<html:form method="post" styleId="gfjysqForm" action="/gfjyqk_sq"
			enctype="multipart/form-data">
			<html:hidden property="sqid"  styleId="sqid"/>
			<html:hidden property="shzt" styleId="shzt" />
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="splc"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								ѧ��
							</th>
							<td align="left">
								${dqxn}
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�����������
							</th>
							<td align="left" colspan="3">
								<select name="gfqkfl" id="gfqkfl" style="width:150px;" onchange="change(this)">
									<option></option>
									<option value="1" <logic:equal value="1" name="gfqkfl">selected = "selected" </logic:equal>>���۵Ǽ����</option>
									<option value="2" <logic:equal value="2" name="gfqkfl">selected = "selected" </logic:equal>>�ξ��������</option>
									<option value="3" <logic:equal value="3" name="gfqkfl">selected = "selected" </logic:equal>>���鸴ѧ���</option>
									<option value="4" <logic:equal value="4" name="gfqkfl">selected = "selected" </logic:equal>>��������</option>
									<option value="5" <logic:equal value="5" name="gfqkfl">selected = "selected" </logic:equal>>�������</option>
									<option value="6" <logic:equal value="6" name="gfqkfl">selected = "selected" </logic:equal>>��μ����</option>
								</select>
							</td>
						</tr>
						<tr id="bydj" <logic:notEqual value="1" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								<span class="red">*</span>���۵Ǽ�ʱ��
							</th>
							    <td align="left">
										<html:text property="bydjsj" styleId="bydjsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>���۵Ǽǵص�
							</th>
								<td align="left">
									<html:text styleId="bydjdd" property="bydjdd"  maxlength="50"/>
								</td>
						</tr>
						
						<tr id="cjrw" <logic:notEqual value="2" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								<span class="red">*</span>�ξ�����ʱ��
							</th>
							    <td align="left">
										<html:text property="cjrwsj" styleId="cjrwsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>������׼��λ
							</th>
								<td align="left">
									<html:text styleId="rwpzdw" property="rwpzdw"  maxlength="50"/>
								</td>
						</tr>
						<tr id="twfx"  <logic:notEqual value="3" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								<span class="red">*</span>���鸴ѧʱ��
							</th>
							    <td align="left">
										<html:text property="twfxsj" styleId="twfxsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>������׼��λ
							</th>
								<td align="left">
									<html:text styleId="twpzdw" property="twpzdw"  maxlength="50"/>
								</td>
						</tr>
						<tr id="pjpy"  <logic:notEqual value="4" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								<span class="red">*</span>��������ʱ��
							</th>
							    <td align="left">
										<html:text property="pjpysj" styleId="pjpysj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>�������ŵ�λ
							</th>
								<td align="left">
									<html:text styleId="pjpydw" property="pjpydw"  maxlength="50"/>
								</td>
						</tr>
						
						<tr id="jc"  <logic:notEqual value="5" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								<span class="red">*</span>����ʱ��
							</th>
							    <td align="left">
										<html:text property="jcsj" styleId="jcsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>���͵�λ
							</th>
								<td align="left">
									<html:text styleId="jcdw" property="jcdw"  maxlength="50"/>
								</td>
						</tr>
						<tr id="cjhd"  <logic:notEqual value="6" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								<span class="red">*</span>�μӻʱ��
							</th>
							    <td align="left">
										<html:text property="cjhdsj" styleId="cjhdsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>�μӻ�ص�
							</th>
								<td align="left">
									<html:text styleId="cjhddd" property="cjhddd"  maxlength="50"/>
								</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
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
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="saveXg('gfjyqk_sq.do?method=update&type=update');return false;"
										id="buttonSave">
										����ݸ�
									</button>
									<button type="button"
										onclick="saveXg('gfjyqk_sq.do?method=update&type=submit');return false;"
										id="buttonSave">
										�ύ����
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
