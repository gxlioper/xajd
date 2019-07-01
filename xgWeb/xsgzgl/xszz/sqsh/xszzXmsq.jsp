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
					});
					jQuery.ajaxSetup({async:false});
					showDialog("ѡ��������Ŀ",500,350,"xszz_sqsh.do?method=getXmsqInfo&xh="+xh,{self:true});
				}

				if ("10351" == jQuery("#xxdm").val()){
					if ("" == jQuery("textarea[name=sqly]").val()) {
						jQuery("textarea[name=sqly]").val("    ����ѧϰ̬�ȶ������ڷܿ̿࣬��λ��һ�Ƚ�ѧ�𣬲�������ĳɼ������ˡ��ݳ��ࡱ��ע�ص���������ȫ�淢չ������������ྺ��������λ񽱣���Ϊһ��ѧ���ɲ��������ɼ����������ͬѧ����ɫ������˸�����������ܺ�����ע��������ʵ�����ϣ����ڴ��£�����ѧ֪ʶ���õ����п���֮�У�����У�����⡶*******������******��2�ʡ�����⡶*********��1��������������ʵ�������Ĺ�����ƻ��������Ϊ*****��");			
					}			
				}
				
			});
			
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh?method=xszzXmsq" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
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
									������Ŀ������Ϣ&nbsp;&nbsp;
									<a onclick="showXmxz();" 
									   href="javascript:void(0);">
									   <font color="blue">ѡ��������Ŀ</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow" style="width:100%;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>��Ŀ���� </td>
												<td>��Ŀ��� </td>
												<td>��Ŀ��������</td>
											</tr>
										</thead>
										<tbody id="xmInfo" name="se">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>${currXn}</td>
							<th>����ѧ��</th>
							<td>${currXq}</td>
						</tr>
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
									<button type="button" type="button" onclick="saveXmsq('save');">
										����ݸ�
									</button>
									<button type="button" id="save_button" type="button" onclick="saveXmsq('submit');">
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

