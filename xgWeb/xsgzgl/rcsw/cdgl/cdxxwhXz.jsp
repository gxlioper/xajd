<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdxxwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){
					jQuery("[name=splcid]").change(function() {// �������Ƽ���,����������̽�����ʾ
						setLckz(jQuery(this).val());
					});
				});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdxxwh" method="post" styleId="rcswCdxxwhForm">
		<html:hidden property="qxfw" styleId="qxfw"/>
		<input type="hidden" id="xxdm" value="${xxdm }" /> 
			<div style='tab;width:100%;height:395px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="17%">
								<span class="red">*</span>��������
							</th>
							<td colspan="3">
								<html:text property="cdmc" style="width:350px" styleId="cdmc" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>¥��
							</th>
							<td>
								<html:text property="ld"  styleId="ld" maxlength="10"></html:text>
							</td>
							<th>
								<span class="red">*</span>����
							</th>
							<td>
								<html:text property="fj"  styleId="fj" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>��������
							</th>
							<td>
								<html:text property="rnrs"  styleId="rnrs" maxlength="5" onkeyup="checkInputData(this);"></html:text>
							</td>
							<th>
								�շѱ�׼
							</th>
							<td>
								<html:text property="sfbz"  styleId="sfbz"  maxlength="5"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>��ϵ��
							</th>
							<td>
								<html:text property="lxr"  styleId="lxr" maxlength="25"></html:text>
							</td>
							<th>
								<span class="red">*</span>��ϵ��ʽ
							</th>
							<td>
								<html:text property="lxfs"  styleId="lxfs"  maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>���⿪��ʱ��
							</th>
							<td colspan="3">
								<html:text  style="width:60px" styleId="dwkfsjkssj" property="dwkfsjkssj" onclick="return showCalendar('dwkfsjkssj','HH:mm',true,'dwkfsjjssj');"  readonly="true"></html:text>
								��
								<html:text  style="width:60px" styleId="dwkfsjjssj" property="dwkfsjjssj" onclick="return showCalendar('dwkfsjjssj','HH:mm',false,'dwkfsjkssj');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��;
							</th>
							<td colspan="3">
								<html:text property="yt" style="width:350px"  styleId="yt" maxlength="40"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								�����豸����
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="jbsbjs" styleId="jbsbjs"
									style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								�Ҹ�����ʹ��Э��
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="xfgfsyxy" styleId="xfgfsyxy"
									style="width:95%;" rows="5"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
						<tr>
							<th align="right" width="16%">
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',

											eid : 'filepath_f'
											});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th align="right" width="16%">
								��������Э��
							</th>
							<td colspan="3">
								<html:hidden property="xysfilepath" styleId="xysfilepath" />
								<input type="file" id="xysfilepath_f" name="xysfilepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#xysfilepath_f').multiUploader({
											maxcount : 1,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'xysfilepath',
	
											eid : 'xysfilepath_f'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<span class="red">*</span>�Ƿ񿪷�����
							</th>
							<td colspan="3">
								<logic:present name="sfkfsqkgList">
									<logic:iterate id="o" name="sfkfsqkgList" >
										<label style="cursor:pointer" ><html:radio property="sfkfsq" value="${o.dm}"  styleId="sfkfsq" onclick="changeSqkg()">${o.mc}</html:radio></label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr id="splcTr">
							<th width="16%">
								<span class="red">*</span>�������
							</th>
							<td colspan="3">
								<logic:present name="shlcList">
									<html:select property="splcid" styleId="splcid" disabled="false">
										<html:option value=""></html:option>
										<html:options collection="shlcList" property="splc"
											labelProperty="lcxx" />
									</html:select>
								</logic:present>
							</td>
						</tr>

						<tr id="qxfwTr">
							<th width="16%">
								���̿���
							</th>
							<td id="qxfwTd" colspan="3"></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="save_button" type="button"
										onclick="addCdxxAction();">
										�� ��
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

