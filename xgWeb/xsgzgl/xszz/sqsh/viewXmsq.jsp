<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//��������ѡ��
				loadViewMkxxSelectOptions();
				//����radioѡ��
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfoForStu",{xh:xh},function(){
					})
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());
				if(("${usertype}" != 'stu' || jQuery.trim("${knsrd.sjly}") == '2' ) && ("${xxdm}" == '10718')��){
					jQuery("#t_head_jtqk").show();
				}else{
					jQuery("#t_head_jtqk").hide();
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xh" styleId="xh"/>
		
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead id = "t_head_jtqk">
						<tr>
							<th colspan="4">
								<span>��ͥ���
									<logic:notEqual value="" property="xh" name="xszzSqshForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">���չ��/����</font>	
										</a>
<%--										|--%>
<%--										<a onclick="editJtqk();" class="btn_xg"--%>
<%--										   href="javascript:void(0);">--%>
<%--										   <font color="blue">�༭��ͥ���</font>	--%>
<%--										</a>--%>
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="knsrd">
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>ѧ��</td>
												<td>ѧ��</td>
												<td>�϶�����</td>
												<td ><b>�϶�ʱ��</b></td>
												<logic:equal value="10335" name="xxdm" scope="request">
													<td ><b>�϶�״̬</b></td>
												</logic:equal>	
											</tr>
										</thead>
										<tbody>
											<logic:present name="knsInfoList">
												<logic:notEmpty name="knsInfoList">
													<logic:iterate id="k" name="knsInfoList">
														<tr>
															<td>${k.xn }</td>
															<td>${k.xqmc }</td>
															<td>${k.dcmc }</td>
															<td >
																${k.sqsj}
															</td>
															<logic:equal value="10335" name="xxdm" scope="request">
																<td >
																	${k.sfqxrd}
																</td>	
															</logic:equal>	
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="knsInfoList">
													<tr>
														<logic:equal value="10335" name="xxdm" scope="request">
															<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
														</logic:equal>
														<logic:notEqual value="10335" name="xxdm" scope="request">
															<td colspan="4" style="text-align:center;">δ�ҵ��κμ�¼��</td>
														</logic:notEqual>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="knsInfoList">
												<tr>
													<logic:equal value="10335" name="xxdm" scope="request">
														<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
													</logic:equal>
													<logic:notEqual value="10335" name="xxdm" scope="request">
														<td colspan="4" style="text-align:center;">δ�ҵ��κμ�¼��</td>
													</logic:notEqual>
												</tr>
											</logic:notPresent>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>
									������Ŀ������Ϣ
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>��������</th>
							<td>${mkxxForm.xn }&nbsp;${mkxxForm.xqmc }</td>
							<th>��Ŀ��������</th>
							<td>${mkxxForm.pdxn }&nbsp;${mkxxForm.pdxqmc }</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>${mkxxForm.xmmc }</td>
							<th>���</th>
							<td>${mkxxForm.ylzd1 }</td>
						</tr>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="fjid"/>
								<div id="fjidDiv"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#fjidDiv').multiUploader_q({
											gid : jQuery('#fjid').val(),
											uid : '2'
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					<logic:equal name="SFBJPY_Y" value="true">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								����С���Ա
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								����С�����
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgshztmc }
							</td>
						</tr>
						<tr>
							<th>
								�����ʱ��
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhsj }
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhdd }
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${mkxxForm.bjpyjgpyyj }
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<tbody id="shxx">
											<tr>
												<td colspan="4" id="shlccx">
												
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
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

