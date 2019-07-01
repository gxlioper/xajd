<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){

				onShow("zxdk_query");
				
				//��������ѡ��
				loadViewMkxxSelectOptions();
				//����radioѡ��
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					});
					
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkXyddkForm.id}&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���${zxdkXyddkForm.xn }
									<logic:notEqual value="" property="xh" name="zxdkXyddkForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">���չ��/����</font>	
										</a>
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
				</table>
			</div>
			<logic:notEqual value="10511" name="xxdm">
				<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" ></div>
			</logic:notEqual>
			<logic:equal value="10511" name="xxdm">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ��</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">��������</th>
							<td width="34%" id='dkqxtd'>
							  ${mkxxForm.dkqx}
							</td>
							<th width="16%">�������ޣ��£�</th>
							<td width="34%">
								 ${mkxxForm.jhr1}
							</td>
						</tr>
						<tr  id = "tableshow" >
							<td colspan="4"  width="100%">
								<table id="table" width="100%">
									<tr width="100%">
										<th style="text-align:center" width="15%" >ѧ��</th>
										<th style="text-align:center" >ס�޷Ѵ����</th>
										<th style="text-align:center" >ѧ�Ѵ����</th>
										<th style="text-align:center" >����Ѵ����</th>
										<th style="text-align:center" >��ס�޷�Ӧ�ɶ�</th>
										<th style="text-align:center" >��ѧ��Ӧ�ɶ�</th>
									</tr>
									<logic:iterate id="i" name="nddkList">
									<tr class ='showtr'>
										<td>
											${i.xn}
										</td>
										 <td>
										 	${i.nzsfdk}
										 </td>
										 <td>
										 	${i.nxfdk}
										 </td>
										 <td>
										 	${i.nshfdk}
										 </td>
										 <td>
										 	${i.nzsfyje}
										 </td>
										 <td>
										 	${i.nxfyje}
										  </td>
										</tr>
									</logic:iterate>
									
								</table>
							</td>
						</tr>
						<tr>
							<th width="16%">ס�޷Ѵ����ܶ�</th>
							<td width="34%">
								 ${mkxxForm.zsfdks}
							</td>
							<th width="16%">ѧ�Ѵ����ܶ�</th>
							<td width="34%">
								 ${mkxxForm.xfdks}
							</td>
						</tr>
						<tr>
							<th width="16%">����Ѵ����ܶ�</th>
							<td width="34%">
								${mkxxForm.shfdks}
							</td>
							<th width="16%">�����ܽ��</th>
							<td width="34%">
								${mkxxForm.dkje}
							</td>
						</tr>
						<tr>
							<th width="16%">��������</th>
							<td width="84%" colspan="3">
								${mkxxForm.sqly}
							</td>
						</tr>
					</tbody>
				</table>
			</logic:equal>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
					<tr>
						<th align="right" width="10%">
							������Ϣ
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
			</table>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px; margin-top: 5px;">
				<table width="100%" border="0" class="formlist">
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
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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