<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				//��������ѡ��
				loadMkxxSelectOptions();
				//����radioѡ��
				loadMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
			});
			//��֤ѧ���������Ƿ񳬹��������
			function checkJesxSh(obj){
				var ylzd1= jQuery(obj).val();
				var jesx = jQuery("#jesx").val();
				if(parseFloat(ylzd1)>parseFloat(jesx)){
					showAlertDivLayer("���������Ŀ������ޣ�");
					jQuery(obj).val('');
				}
			}
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<html:hidden property="xmdm"/>
			<html:hidden property="xn"/>
			<html:hidden property="xq"/>
			<html:hidden property="shzt" />
			<input type="hidden" name="isopen" id="isopen" value="${isopen }" />
			<input type="hidden" name="jesx" id="jesx" value="${mkxxForm.je}"/>
		
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					
					<thead>
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
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>ѧ��</td>
												<td>ѧ��</td>
												<td>�϶�����</td>
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
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="knsInfoList">
													<tr>
														<td colspan="3" style="text-align:center;">δ�ҵ��κμ�¼��</td>
													</tr>
												</logic:empty>
											</logic:present>
											<logic:notPresent name="knsInfoList">
												<tr>
													<td colspan="3" style="text-align:center;">δ�ҵ��κμ�¼��</td>
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
							<td>${mkxxForm.xn }&nbsp; ${mkxxForm.xqmc }</td>
							<th>��Ŀ��������</th>
							<td>${xmwhForm.pdxn }&nbsp; ${xmwhForm.pdxqmc }</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>${mkxxForm.xmmc }</td>
							<th>���</th>
							<td>${mkxxForm.ylzd1 }</td>
						</tr>
						<logic:equal value="1" name="xmwhForm" property="jesfxssq">
						<tr>
							<th>������</th>
							<td>
								<html:text onkeyup="checkInputData(this);checkJesxSh(this)" maxlength="7" property="ylzd1" styleId="ylzd1" style="width:100px"></html:text>
								<font id="message"  color="blue">���޽�${mkxxForm.je }</font>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<logic:equal value="10335" name="xxdm">
									<span style="color: blue; line-height:20px;display:block;">
										����ѧԺ(԰)֪ͨҪ������ȷ�����ϴ�������Ҫ���ϴ������޸���Ҫ���������ϴ���
									</span>
									&nbsp;
								</logic:equal>
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:notEqual value="11799" name="xxdm">
								<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf|mp4',
													//����ļ���С ��λM
													maxsize: 300,
													//��Ÿ������������id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
				</table>
			</div>

			<div style="height: 50px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveSqxg('save');">
										����ݸ�
									</button>
									<button type="button" id="save_button" type="button" onclick="saveSqxg('submit');">
										�ύ����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>

