<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsq/js/cfjcsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		//����
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
	</head>
	
	<body onload="showCfqxFlag('${map.cflbdm }');">
		<html:form method="post" styleId="cfjcsqForm" action="/wjcf_cfjcsq"  >
			<html:hidden property="cfid" styleId="cfid"/>
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
			<input id="xxdm" type="hidden" value="${xxdm}"/>
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
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
								ѧ��ѧ��
							</th>
							<td align="left" width="30%">						
								${map.xn }${map.xqmc }		
							</td>
							<th align="right" width="20%">
								Υ��ʱ��
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								����ԭ��
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								�������
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								���ֽ���
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								ѧ��������
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-0'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								����Υ�ͼ�¼��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-2" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-2'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�д�ֽ��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-3" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath3}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-3'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�������¼
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-4" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath4}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-4'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								Υ����ʵ����
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								�����ĺ�
							</th>
							<td align="left">
								${map.cfwh }
							</td>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								${map.cfsj }
							</td>
						</tr>
						<logic:present name="map" property="cfdqsj">
							<tr>
								<th align="right">
									���ֵ���ʱ��
								</th>
								<td align="left"  colspan="3">
									${map.cfdqsj }
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual name="xxdm" value="12865">
							<tr>
								<th align="right">
									<font color="red">*</font><bean:message key="wjcf.text" />���ֽ���
									<br />
									<font color="red"><B>(��1000��)</B>
									</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea property='sqly' styleId="sqly" style="width:600px" rows='5'
										onblur="checkLen(this,1000)" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>�༶���
									<br />
									<font color="red"><B>(��1000��)</B>
									</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea property='bjyj' styleId="bjyj" style="width:600px" rows='5'
												   onblur="checkLen(this,1000)" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>���˵��
									<br />
									<font color="red"><B>(��1000��)</B>
									</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea property='qksm' styleId="qksm" style="width:600px" rows='5'
												   onblur="checkLen(this,1000)" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								<font color="red">*</font>���ٽ�����¼��
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath"/>
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        jQuery('#filepath_f').multiUploader({
                                            maxcount : 1,
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
						<tr>
							<th>
								<font color="red">*</font>�ɼ���
							</th>
							<td colspan="3">
								<html:hidden property="filepath2" styleId="filepath2"/>
								<input type="file" id="filepath_f2" name="filepath2" />
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        jQuery('#filepath_f2').multiUploader({
                                            maxcount : 1,
                                            //��׺
                                            accept : 'png|gif|jpg|zip|rar|doc|docx',
                                            //����ļ���С ��λM
                                            maxsize: 10,
                                            //��Ÿ������������id
                                            elementid : 'filepath2',
                                            eid : 'filepath_f2'
                                        });
                                    });
								</script>
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
									<button type="button" onclick="save('wjcf_cfjcsq.do?method=cfjcsqSave&type=save')" id="buttonSave">
										����ݸ�
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="save('wjcf_cfjcsq.do?method=cfjcsqSave&type=submit')" id="buttonSave">
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