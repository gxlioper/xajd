<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${XjsqshForm.ywid}&tt="+new Date().getTime());
			});
			//�����
			function saveForm(type){
				var checkWriteStr = "sjqjts-sjkssj-sjjssj-xjbz";
				if(!checkNotNull(checkWriteStr)){
					return showAlert("�뽫��������д������");
				}
				var url = "qjgl_xjsh.do?method=saveXjsq";
				ajaxSubFormWithFun("form", url, function(data) {
					 if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjgl_xjsh">
		 <html:hidden property="qjjgid"/>
		<div style="tab;width:100%;overflow-x:hidden;overflow-y:auto;">
		<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							ѧ��
						</th>
						<td align="left"  width="34%">
							${data.xn}
						</td>
						<th align="right"  width="16%">
							ѧ��
						</th>
						<td align="left"  width="34%">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							�������
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<tr>
						<th align="right">
							��ٿ�ʼʱ��
						</th>
						<td align="left">
							${data.kssj }
						</td>
						<th align="right">
							��ٽ���ʱ��
						</th>
						<td align="left">
							${data.jssj }
						</td>
					</tr>
					<logic:equal name="xxdm" value="12727">
						<tr>
							<th align="right" width="10%">
								��ٽڴ�
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12688">
						<tr>
							<th align="right" width="10%">
								��ٽڴ�
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="70002">
						<tr>
							<th align="right">
								У��У��
							</th>
							 <td align="left">
							 	${data.xnxw }
							 </td>
							 <th></th>
							 <td></td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								�ҳ��绰
								
							</th>
							<td colspan="3">
								${data.jzdh }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
						</logic:equal>
					<tr>
						<th align="right">
							�������
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
					
<%-- 						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${data.filepath}" target="_blank" style="color:blue">������ز鿴</a>
							</td>
						</tr> --%>
					<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
							<html:hidden property="filepath" styleId="fjid" value="${data.filepath}"/>
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
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							ʵ���������
						</th>
						<td align="left"  width="34%">
							${XjsqshForm.sjqjts}��
						</td>
						<th align="right" width="16%">
							ʵ�����ʱ��
						</th>
					   <td align="left"  width="34%">
						   		${XjsqshForm.sjkssj}
								~
								${XjsqshForm.sjjssj}
					   </td>
					</tr>
					<tr id="fjtr">
							<th>
								���ٸ���
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-1" style="padding: 5px;"></div>
							<html:hidden property="xjfilepath" styleId="xjfilepath" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#xjfilepath').val();
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-1'
										});
								});
							</script>
							</td>
						</tr>
					<tr>
						<th align="right"  width="16%">
							������Ϣ&nbsp;
						</th>
						<td colspan="3"  width="34%">
							${XjsqshForm.xjbz}
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
											<td colspan="4" id="shlccx">
											
											</td>
										</tr>
									</tbody>
			</table>
		</div>
		<div style="height:50px"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
