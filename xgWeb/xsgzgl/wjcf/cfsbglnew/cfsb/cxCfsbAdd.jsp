<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsb/js/cfsb.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			//�ں�ְҵѧԺ���Ի�
			//��ѡ�˴���ԭ��󣬴��������Զ������ѡ�Ĵ���ԭ������
			jQuery(function(){
				if(${xxdm=="13915"}){
					jQuery("#cfyydm").change(function(){
						jQuery("#cfyj").val(jQuery(this).find("option:selected").text());
					});
				}
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="cfsbglForm" action="/wjcf_cfsbgl" enctype="multipart/form-data">
			<html:hidden property="fjmc" styleId="fjmc"/>
			<input id="xxdm" type="hidden" value="${xxdm}"/>
			<div
				style='width: 100%;overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>����ѧ��
						</th>
						<td align="left" width="30%">
							<html:select property="xn" styleId="xn" style="width:140px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
							</html:select>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>����ѧ��
						</th>
						<td align="left" width="30%">
							<html:select property="xq" styleId="xq" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
							</html:select>
						</td>
					</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>����ԭ��
							</th>
							<td align="left">
								<html:select property="cfyydm" styleId="cfyydm"
									style="width:140px">
									<html:option value=""></html:option>
									<html:options collection="cfyyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>�������
							</th>
							<td align="left">
								<html:select property="cflbdm" styleId="cflbdm"
									style="width:100px" onchange="showCfqxFlag(this.value);">
									<html:option value=""></html:option>
									<html:options collection="cflbList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>Υ��ʱ��
							</th>
							<td align="left" width="30%">
								<html:text property="wjsj" styleId="wjsj"
									style="cursor:hand;"
									onclick="return showCalendar('wjsj','y-mm-dd');" />
							</td>
							<th align="right">
								�ϱ���
							</th>
							<td align="left">
								${sbbxm }
							</td>
						</tr>
						<tr>
							<th align="right">
								<logic:equal name="xxdm" value="70002">
									<font class="red">*</font>
								</logic:equal>���ֽ���
								<br />
								<font color="red"><B>(��1000��)</B>
								</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea styleId="cfyj" property='cfyj' style="width:600px" rows='4' onblur="checkLen(this,1000)"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font class="red">*</font>
								ѧ��������
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',
                                            maxcount : '1',
											eid : 'filepath_f'
											});
									});
								</script>
							</td>
						</tr>
					<tr>
						<th align="right">
							����Υ�ͼ�¼��
						</th>
						<td colspan="3">
							<html:hidden property="filepath2" styleId="filepath2" />
							<input type="file" id="filepath_f2" name="filepath2" />
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery('#filepath_f2').multiUploader({
                                        //����ļ���С ��λM
                                        maxsize: 10,
                                        //��Ÿ������������id
                                        elementid : 'filepath2',
                                        maxcount : '1',
                                        eid : 'filepath_f2'
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							�д�ֽ��
						</th>
						<td colspan="3">
							<html:hidden property="filepath3" styleId="filepath3" />
							<input type="file" id="filepath_f3" name="filepath3" />
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery('#filepath_f3').multiUploader({
                                        //����ļ���С ��λM
                                        maxsize: 10,
                                        //��Ÿ������������id
                                        elementid : 'filepath3',
                                        maxcount : '1',
                                        eid : 'filepath_f3'
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>
							�������¼
						</th>
						<td colspan="3">
							<html:hidden property="filepath4" styleId="filepath4" />
							<input type="file" id="filepath_f4" name="filepath4" />
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery('#filepath_f4').multiUploader({
                                        //����ļ���С ��λM
                                        maxsize: 10,
                                        //��Ÿ������������id
                                        elementid : 'filepath4',
                                        maxcount : '1',
                                        eid : 'filepath_f4'
                                    });
                                });
							</script>
						</td>
					</tr>
						<tr>
							<th align="right">
								<logic:equal name="xxdm" value="70002">
									<font class="red">*</font>
								</logic:equal>Υ����ʵ����
								<br />
								<font color="red"><B>(��1000��)</B>
								</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property='wjssjg' styleId="wjssjg" style="width:600px" rows='5'
									onblur="checkLen(this,1000)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��ע
								<br />
								<font color="red"><B>(��1000��)</B>
								</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property='bz' styleId="bz" style="width:600px" rows='5'
									onblur="checkLen(this,1000)" />
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>���ܴ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
									<thead align="center">
										<tr align="center">
											<td>
												<b>ѧ��</b>
											</td>
											<td>
												<b>ѧ��</b>
											</td>
											<td>
												<b>�������</b>
											</td>
											<td>
												<b>����ԭ��</b>
											</td>
											<td>
												<b>����ʱ��</b>
											</td>
											<td>
												<b>�����ĺ�</b>
											</td>
										</tr>
									</thead>
									<tbody align="center">
										<logic:notEmpty name="yscfqkList">
											<logic:iterate name="yscfqkList" id="s">
												<tr style="cursor: hand">
													<td>
														${s.xn}
													</td>
													<td>
														${s.xqmc}
													</td>
													<td>
														${s.cflbmc}
													</td>
													<td>
														${s.cfyymc}
													</td>
													<td>
														${s.cfsj}
													</td>
													<td>
														${s.cfwh}
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="yscfqkList">
											<tr style="height: 22px">
												<td colspan="6" align="center">û��Υ�ͼ�¼</td>
											</tr>
										</logic:empty>
									</tbody>
								</table>
							</td>
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
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="save('wjcf_cfsbgl.do?method=cxCfsbSave&type=save')" id="buttonSave">
									����ݸ�
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="save('wjcf_cfsbgl.do?method=cxCfsbSave&type=submit')" id="buttonSave">
									�ύ����
								</button>
								&nbsp;&nbsp;
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