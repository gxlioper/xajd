<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjjg/js/operation.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjjg">
		 <html:hidden property="qjjgid"/>
		<div style="tab;width:100%;overflow-x:hidden;overflow-y:auto;">
		<table width="100%" border="0" class="formlist">
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
								<span>�����Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							ѧ��
						</th>
						<td align="left"  width="34%">
							${data.xn}
						</td>
						<th align="right"  width="16%">
							ѧ��
						</th>
						<td align="left"  width="34%">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							�������
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							��ٿ�ʼʱ��
						</th>
						<td align="left">
							${data.kssj }
						</td>
						<th align="right">
							��ٽ���ʱ��
						</th>
						<td align="left">
							${data.jssj }
						</td>
					</tr>
					<logic:equal name="xxdm" value="12727">
						<tr>
							<th align="right" width="10%">
								��ٽڴ�
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12688">
						<tr>
							<th align="right" width="10%">
								��ٽڴ�
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="70002">
						<tr>
							<th align="right">
								У��У��
							</th>
							 <td align="left">
							 	${data.xnxw }
							 </td>
							 <th></th>
							 <td></td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								�ҳ��绰
								
							</th>
							<td colspan="3">
								${data.jzdh }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
						</logic:equal>
					<logic:equal value="10511" name="xxdm">
						<tr>
							<th>��ٿγ�</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>�γ�����</td>
											<td>�ο���ʦ����</td>
											<td>�ο���ʦ��ϵ��ʽ</td>
										</tr>
									</thead>
									<tbody id="qjkc">
									<logic:present name="qjkcList">
										<logic:iterate id="qjkc" name="qjkcList" indexId="i">
											<tr>
												<td>
												${qjkc.kcmc }
												</td>
												<td>
												${qjkc.rklsxm }
												</td>
												<td>
												${qjkc.rklslxfs }
												</td>
											</tr>
										</logic:iterate>
										<logic:empty name="qjkcList">
											<tr>
												<td colspan="3" align="center">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
						</logic:equal>
					<tr>
						<th align="right">
							�������
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
					
<%-- 						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${data.filepath}" target="_blank" style="color:blue">������ز鿴</a>
							</td>
						</tr> --%>
					<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="fjid"/>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
						</tr>
				</tbody>
			</table>
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
						<th align="right" width="16%">
							<span class="red">*</span>ʵ���������
						</th>
						<td align="left"  width="34%">
							<html:text property="sjqjts" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')" styleId="sjqjts" style="width:50%" maxlength="4" value="${data.qjts}"></html:text>&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right" width="16%">
							<span class="red">*</span>ʵ�����ʱ��
						</th>
						<logic:equal value="1" name="qjsjxsgz">
						   <td align="left"  width="34%">
								<html:text property="sjkssj" value="${data.kssj }" styleId="sjkssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'sjjssj');" style="width:43%"/>
								~
								<html:text property="sjjssj" value="${data.jssj }" styleId="sjjssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'sjkssj');" style="width:43%"/>
						   </td>
						</logic:equal>
						<logic:notEqual value="1" name="qjsjxsgz">
						   <td align="left"  width="34%">
								<html:text property="sjkssj" value="${data.kssj }" styleId="sjkssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sjjssj');" style="width:43%"/>
								~
								<html:text property="sjjssj" value="${data.jssj }" styleId="sjjssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sjkssj');" style="width:43%"/>
						   </td>
						</logic:notEqual>
					</tr>
					<tr id="fjtr">
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="xjfilepath" styleId="xjfilepath" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
	                               <script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,//��׺
												accept : 'png|gif|jpg|zip|rar|doc|docx',//����ļ���С ��λM
												maxsize: 10,//��Ÿ������������id
												elementid : 'xjfilepath'
											});
										});
								</script>
							</td>
						</tr>
					<tr>
						<th align="right"  width="16%">
							<span class="red">*</span>������Ϣ&nbsp;
							<br />
							<font color="red">(��250��)</font>
						</th>
						<td colspan="3"  width="34%">
							<html:textarea rows="5"  property="xjbz" styleId="xjbz" style="width:98%" onblur="checkLen(this,250);"></html:textarea>
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
								<button type="button"  onclick="save('qjjg.do?method=xjcl&type=save','sjqjts-sjkssj-sjjssj-xjbz','false');return false;" id="buttonSave">
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
