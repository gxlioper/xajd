<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	</head>
	<body>
		<html:form action="/zxdkHkjg" method="post" styleId="hkjgForm">
			<html:hidden property="id" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:present name="khkList">
						<thead>
							<tr>
								<th colspan="4">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									<logic:equal value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">ѧ��</th>
										<th style="text-align: center;">��ͬ���</th>
										<th style="text-align: center;">�����ܽ��(Ԫ)</th>
										<th style="text-align: center;">�ۼƷſ���(Ԫ)</th>
										<th style="text-align: center;">��������</th>
										<th style="text-align: center;">�����˺�</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
											<td>${dkxx.dkzh }</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
							<logic:notEqual value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">ѧ��</th>
										<th style="text-align: center;">��ͬ���</th>
										<th style="text-align: center;">�����ܽ��</th>
										<th style="text-align: center;">�ۼƷſ���</th>
										<th style="text-align: center;">��������</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
										</tr>
									</logic:iterate>
								</td>
							</tr>
						</tbody>
						</logic:notEqual>
					</logic:present>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ǰ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�����˺�</th>
							<td>
								${zxdkHkjgForm.hkzh }
							</td>
							<th>������</th>
							<td>
								${zxdkHkjgForm.hkje }
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td>
								${zxdkHkjgForm.hkbj }
							</td>
							<th>����״̬</th>
							<td>
								${zxdkHkjgForm.hkztmc }
							</td>
						</tr>
						<tr>
							<th>��ǰ��������</th>
							<td colspan="3">
								${zxdkHkjgForm.hkly }
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3">
								${zxdkHkjgForm.bz }
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						  <tr>
							<th>������Ϣ
								
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<html:hidden property="filepath" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid =  jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						  </tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkHkjg.do?method=update');">
										��    ��
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
		</html:form>
	</body>
	
</html>