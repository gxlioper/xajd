<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdbljg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				var hdxs = '${hdxs}';
                if("�γ�" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                }else if("����" == hdxs){

                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("��������");
                    jQuery("#con_span").html("��������");

                    jQuery("#jzlxTr").hide();
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    jQuery("#jzlxTr").hide();
                }
                kcjbChange();
			});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdbljg" method="post" styleId="hdbljgForm" onsubmit="return false;">
		<html:hidden property="jgid" styleId="jgid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								ѧ��
							</th>
							<td width="35%">
								${hdbljgForm.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${hdbljgForm.xqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span><font color="red">*</font>�����</span>
							</th>
							<td width="35%">
								<html:text property="hdmc" styleId="hdmc" maxlength="20"/>
							</td>
							<th>
								<span><font color="red">*</font>�ʱ��</span>
							</th>
							<td>
								<html:text property="hdsj" styleId="hdsj" onfocus="showCalendar('hdsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���췽
							</th>
							<td colspan="3">
								<html:text property="zbf" styleId="zbf" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span><font color="red">*</font>����or���»</span>
							</th>
							<td width="35%">
								<html:select property="xsxxlx" styleId="xsxxlx"  style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="����">���ϻ</html:option>
									<html:option value="����">���»</html:option>
								</html:select>
							</td>
							<th width="15%">
								<span><font color="red">*</font>���ʽ</span>
							</th>
							<td width="35%">
								<html:select property="hdkclx" styleId="hdkclx"  style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="������">�����ࣨ��׶Σ�</html:option>
									<html:option value="������">�����ࣨ���׶Σ�</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span><font color="red">*</font>�����</span>
							</th>
							<td width="35%">
								<html:select property="hdxs" styleId="hdxs" onchange="changeHdxs()" style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="�">�</html:option>
									<html:option value="�γ�">�γ�</html:option>
									<html:option value="����">����</html:option>
								</html:select>
							</td>
							<th>
								<font color="red">*</font><span id="lx_span">�����</span>
							</th>
							<td>
								<html:select property="hdlx" styleId="hdlx" style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="hdlxList" labelProperty="hdlxmc" property="hdlxdm"/>
								</html:select>
							</td>
						</tr>

						<tr name="zjrxx_tr">
							<th>
								<font color="red">*</font>����������
							</th>
							<td>
								<html:text property="zjrxm" styleId="zjrxm" maxlength="10"/>
							</td>
							<th >
								<font color="red">*</font>�����˵�λ
							</th>
							<td >
								<html:text property="zjrdw" styleId="zjrdw" maxlength="20"/>
							</td>
						</tr>
						<tr name="zjrxx_tr">
							<th>
								<font color="red">*</font>������ְ��
							</th>
							<td>
								<html:select property="zjrzc" styleId="zjrzc" style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="zjrzcList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th >
								<font color="red">*</font>������ְ��
							</th>
							<td >
								<html:text property="zjrzw" styleId="zjrzw" maxlength="10"/>
							</td>
						</tr>
						<tr >
							<th>
								<font color="red">*</font>�����

							</th>
							<td colspan="3">
								<html:select property="jzjb" styleId="jzjb" style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="У���">У���</html:option>
									<html:option value="Ժ���">Ժ���</html:option>
									<html:option value="��ѡ�">��ѡ�</html:option>
								</html:select>
							</td>

						</tr>
						<tr name="zjrxx_tr">
							<th>
								�����˽���
								<br><font color="red">(��100��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="2" property="zjrjs" styleId="zjrjs"
											   style="width:95%" onblur="checkLen(this,100);"/>
							</td>

						</tr>

						<tr id="jzlxTr">
							<th>
								<font color="red">*</font>�γ̼���
							</th>
							<td>
								<html:select property="jzlx" styleId="jzlx" style="width:173px" onchange="kcjbChange()">
									<html:option value="">---&nbsp;��ѡ��γ̼���&nbsp;---</html:option>
									<html:options collection ="jzlxList" property="jzlxdm" labelProperty="jzlxmc" />
								</html:select>
							</td>
							<th id="zxkclxTh" style="display: none;">
								<font color="red">*</font>��ѡ�γ�����
							</th>
							<td id="zxkclxTd" style="display: none;">
								<html:select property="zxkclx" styleId="zxkclx" style="width:173px">
									<html:option value="">---&nbsp;��ѡ����ѡ�γ�����&nbsp;---</html:option>
									<html:options collection ="zxckclxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���ǩ
							</th>
							<td colspan="3">
								<logic:iterate name="activityLabelList" id="bq">
								<%--<html:checkbox property="hdbqs" value="${bq.dm}">${bq.mc}</html:checkbox>--%>
									<label><html:multibox property="hdbqs" value="${bq.hdbqdm}"/>${bq.hdbqmc}</label>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>
								������ǩ
							</th>
							<td colspan="3">
								<logic:iterate name="abilityLabelList" id="bq">
									<label><html:multibox property="nlbqs" value="${bq.nlbqdm}"/>${bq.nlbqmc}</label>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th width="15%">
								��ص�
							</th>
							<td width="35%">
								<html:text property="hddd" styleId="hddd" maxlength="20"/>
							</td>
							<th>�μ�����</th>
							<td>
								<html:select property="cjlx" styleId="cjlx" style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="����">����</html:option>
									<html:option value="���">���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="15%">
								���ְ��
							</th>
							<td width="35%">
								<html:select property="zdzw" styleId="zdzw" style="width:173px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="�ӳ�">�ӳ�</html:option>
									<html:option value="��Ա">��Ա</html:option>
								</html:select>
							</td>
							<th>�ְ��</th>
							<td>
								<html:text property="hdzw" styleId="hdzw" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th width="15%">
								��ý���
							</th>
							<td width="35%">
								<html:text property="hdjx" styleId="hdjx" maxlength="20"/>
							</td>
							<th>������ѧ��</th>
							<td>
								<html:text property="hdxf" styleId="hdxf" maxlength="20"
										   onblur="clearNoNum(this);return false;"/>
							</td>
						</tr>
			      		<tr>
							<th>
								<span><font color="red">*</font>����</span>
							</th>
							<td colspan="3">
								<html:hidden property="fjpath" styleId="fjpath"/>
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
											elementid : 'fjpath'
										});
									});
								</script>					
							</td>
						</tr>
						<tr>
							<th>
								<span id="con_span">����ݼ��ĵ�</span>
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:25px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="save('update');">
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

