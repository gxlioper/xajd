<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/jtqkdc/js/jtqkdc.js"></script>
		
		<script type="text/javascript">

			jQuery(function(){
				//��������ѡ��
				loadViewJtcySelectOptions();
				//���ؼ�ͥ���������ѡ��
				loadViewMkxxSelectOptions();
				/*//���ؼ�ͥ�����radioѡ�� �����Ի�*/
				if(jQuery('#xxdm').val() == '10335'){
					loadViewMkxxRadioOptions_10335();
				}else{
					//���ؼ�ͥ�����radioѡ��
					loadViewMkxxRadioOptions();
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
		<input type = 'hidden' id = 'xh' value = '${xh}'/>
		<input type = 'hidden' id = 'xxdm' value = '${xxdm}'/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>
									ѧ��������Ϣ
								</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>��ͥͨѶ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jttxxxView.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ��Ա</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jtcyView.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>Ӱ���ͥ����״���й���ϸ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/yxjtjjqkView.jsp" %>
					<logic:notEqual value="10344" name="xxdm">
					<thead>
						<tr>
							<th colspan="4">
								<span>����</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>
							������Ϣ
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd3" styleId="fjid"/>
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
					</logic:notEqual>
				</table>
			</div>
			<div style="height: 35px;"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="closeDialog();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

			</table>
		</html:form>
	</body>
</html>

