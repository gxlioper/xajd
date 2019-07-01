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
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${map.cfid}&tt="+new Date().getTime());
			var cflbdm ='${map.cflbdm }';
			showCfqxFlag(cflbdm);
		});

		//����
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function showCfqxFlag(cflbdm){
			//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		</script>
	</head>
	
	<body>
		<html:form method="post" styleId="cfshForm" action="/wjcf_cfsh" >
			<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
			<input type="hidden" name="cfid" id="cfid" value="${cfshForm.ywid }"/>
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
								ѧ��ѧ��
							</th>
							<td align="left" width="30%">
								${map.xn } ${map.xqmc }
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
								�ϱ���
							</th>
							<td align="left" colspan="3">
								${map.sbbxm }
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
						<logic:present name="map" property="cfwh">
							<tr>
								<th align="right">
									�����ĺ�
								</th>
								<td align="left"  >
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
								<span>����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
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
								<div class="btn">
									<div class="btn">
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</html:form>
	</body>
</html>