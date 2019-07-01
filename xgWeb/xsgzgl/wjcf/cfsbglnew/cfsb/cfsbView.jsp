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
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	</head>
	<body onload="detailInit();">
		<html:form method="post" styleId="cfsbglForm" action="/wjcf_cfsbgl" enctype="multipart/form-data">
			<html:hidden property="fjmc" styleId="fjmc"/>
			<html:hidden property="cfid" styleId="cfid"/>
			<html:hidden property="cflbdm" styleId="cflbdm"/>
			<div
				style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
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
							����ѧ��
						</th>
						<td align="left" width="30%">
							<bean:write name="cfsbglForm" property="xn"/>
						</td>
						<th align="right" width="20%">
							����ѧ��
						</th>
						<td align="left" width="30%">
							<bean:write name="cfsbglForm" property="xqmc"/>
						</td>
						</tr>
		
						<tr>
							<th align="right">
								����ԭ��
							</th>
							<td align="left">
								<bean:write name="cfsbglForm" property="cfyymc"/>
							</td>
							<th align="right">
								�������
							</th>
							<td align="left">
								<bean:write name="cfsbglForm" property="cflbmc"/>&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
						    <th align="right" width="20%">
								Υ��ʱ��
							</th>
							<td align="left" width="30%">
								<bean:write name="cfsbglForm" property="wjsj"/>
							</td>
							<th align="right">
								�ϱ���
							</th>
							<td align="left" colspan="3">
								${cfsbglForm.sbbxm }
							</td>
						</tr>
						<tr>
							<th align="right">
								���ֽ���
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<bean:write name="cfsbglForm" property="cfyj"/>
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
										var gid = "${cfsbglForm.filepath}";
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
                                    var gid = "${cfsbglForm.filepath2}";
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
                                    var gid = "${cfsbglForm.filepath3}";
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
                                    var gid = "${cfsbglForm.filepath4}";
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
								<bean:write name="cfsbglForm" property="wjssjg"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								<bean:write name="cfsbglForm" property="bz"/>
							</td>
						</tr>
					</tbody>
					<%--<thead>
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
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
											</tr>
										</logic:empty>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				--%></table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
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