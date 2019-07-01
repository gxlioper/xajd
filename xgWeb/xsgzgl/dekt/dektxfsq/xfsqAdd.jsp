<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxfsq/js/xfsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/tabMerge.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				_w_table_rowspan("#xmtb",1);
				_w_table_rowspan("#xmtb",2);
				_w_table_rowspan("#xmtb",5);
			});
			/* function chLengs(obj,leng){
				if(obj.value.length > leng){
					showAlert("���������������Ϊ"+leng+",���Ѿ���������ȷ�ϣ���", function(){obj.focus();});
				}
			} */
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/dekt_xfsq">
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<input type="hidden" name="lx" id="lx" value="${lx }"/>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								�϶���׼
							</th>
							<td colspan="3">
								<div class="toolbox">
									<table id="xmtb" width="100%"  >
									<thead>
										<tr align="center" >
											<td width="15%">�϶���Ŀ</td>
											<td width="30%">�϶����ݼ���׼</td>
											<td width="15%">�ȼ�</td>
											<td width="10%">ѧ��</td>
											<td width="30%">�϶����ݼ�����</td>
										</tr>
									</thead>
									<tbody>
										<logic:iterate id="xm" name="xmlist">
											<tr align="center">
												<td>${xm.rdxm }</td>
												<td>${xm.rdnrbz} </td>
												<td>${xm.dj }</td>
												<td>${xm.xf}</td>
												<td>${xm.yjsm}</td>
											</tr>
										</logic:iterate>
									</tbody>
									</table>
								</div>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>�϶�����
							</th>
							<td colspan="3">
								<html:select property="rdxm" styleId="rdxm"   onchange="loadRdnrbz(); " style="width:150px;">
									<html:option value=""></html:option>
									<html:options collection="rdxmlist" property="rdxm" labelProperty="rdxm" />
								</html:select>
								&nbsp;&nbsp;
								<html:select property="rdnrbz" styleId="rdnrbz" onchange="loadDj();"  style="width:150px;"></html:select>
								&nbsp;&nbsp;
								<html:select property="dj" styleId="dj" onchange=""  style="width:150px;"></html:select>
							</td>
							
							
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���뷽ʽ
							</th>
							<td colspan="3">
								<html:radio property="cyfs" value="gr"  styleId="cyfs">����</html:radio>
								<html:radio property="cyfs" value="tt" styleId="cyfs">����</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��ʱ��
							</th>
							<td colspan="3">
								<html:text property="hjsj" styleId="hjsj" onclick="return showCalendar('hjsj','yyyy-MM-dd');" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����˵��
								<br><font color="red">��500������</br></font>
							</th>
							<td colspan="3" >
								<html:textarea property="sqsm" style="width:94%;height:100px"  styleId="sqsm" onblur="checkLen(this,500);" ></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath"/>
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
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="save('dekt_xfsq.do?method=xfsqSave','rdxm-sqsm-rdnrbz-dj-cyfs-hjsj');" id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
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