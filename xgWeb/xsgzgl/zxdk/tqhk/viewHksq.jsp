<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkTqhkForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zxdkTqhkForm.splcid}&shid=${zxdkTqhkForm.id}");
			});
		</script>
	</head>
	<body>
		<html:form action="/zxdkTqhk" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
											<td>${dkxx.xn}</td>
											<td>${dkxx.htbh}</td>
											<td>${dkxx.dkje}</td>
											<td>${dkxx.fkze}</td>
											<td>${dkxx.dkqx}</td>
											<td>${dkxx.dkzh}</td>
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
								</table>
							</logic:notEqual>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�����˺�</th>
							<td>
								${zxdkTqhkForm.hkzh }
							</td>
							<th>������</th>
							<td>
								${zxdkTqhkForm.hkje }
							</td>
						</tr>
						<tr>
							<th>������</th>
							<td colspan="3">
								${zxdkTqhkForm.hkbj }
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${zxdkTqhkForm.hkly }
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3">
								${zxdkTqhkForm.bz }
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
										var gid = jQuery('#fjid').val();
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
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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