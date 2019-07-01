<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/ybgl/ybsh/js/ybsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" defer="defer">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${YbshForm.sbid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${YbshForm.splcid}&shid=${YbshForm.shid}");
		})
	   function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "xlzxnew_ybsh.do?method=saveYbsh";
		ajaxSubFormWithFun("YbshForm",url,function(data){
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
		<html:form action="/xlzxnew_ybsh" method="post" styleId="YbshForm">
			<html:hidden property="sbid"/>
			<html:hidden property="splcid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
								<tr>
								<th width="15%">
									ѧԺ
								</th>
								<td width="35%">
									${xymc}
								</td>
								<th width="15%">
									��д��
								</th>
								<td width="35%">
									${txr}
								</td>
							</tr>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									${YbshForm.xn}
								</td>
								<th>
									�¶�
								</th>
								<td>
									${yf}
								</td>
							</tr>
							<logic:equal value="10704" name="xxdm">
								<tr>
									<th>
										�Ƿ�������
									</th>
									<td>
										${YbshForm.sfywt}
									</td>
								</tr>
							</logic:equal>
						<tr>
							<th>
								ѧԺ���������<br />
								�ش��¼�<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${YbshForm.ztqk}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="6">
								<table width="100%" >
									<thead>
										<logic:notEqual value="10704" name="xxdm">
											<tr>
												<th width='15%' style="text-align:center">ѧ��</th>
												<th width='10%' style="text-align:center">����</th>
												<th width='10%' style="text-align:center">�༶</th>
												<th width='20%' style="text-align:center">���˵��</th>
												<th width='20%' style="text-align:center">��Ԥ��ʩ</th>
												<th width='25%' style="text-align:center">��Ԥ��Ч��</th>
											</tr>
										</logic:notEqual>
										<logic:equal value="10704" name="xxdm">
											<tr>
												<th width='15%' style="text-align:center">ѧ��</th>
												<th width='10%' style="text-align:center">����</th>
												<th width='18%' style="text-align:center">�༶</th>
												<th width='10%' style="text-align:center">��ϵ��ʽ</th>
												<th width='12%' style="text-align:center">��������</th>
												<th width='10%' style="text-align:center">Σ���̶�</th>
												<th width='20%' style="text-align:center">�������</th>
											</tr>
										</logic:equal>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="wtryInfo">
										<logic:notEqual value="10704" name="xxdm">
										<tr name='deltr'>
											<td style='text-align:center'><input name='xh' type='hidden' value='xh'/><label name = 'xhname'>${i.xh}</label></td>
											<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
											<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
											<td style='text-align:center'>${i.ybwtms}</td>
											<td style='text-align:center'>${i.ybgycs}</td>
											<td style='text-align:center'>${i.ybgyhjg}</td>
										</tr>
									</logic:notEqual>
									<logic:equal value="10704" name="xxdm">
										<tr name='deltr'>
											<td style='text-align:center'><input name='xh' type='hidden' value='xh'/><label name = 'xhname'>${i.xh}</label></td>
											<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
											<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
											<td style='text-align:center'>${i.sjhm}</td>
											<td style='text-align:center'>${i.wtfsrq}</td>
											<td style='text-align:center'>${i.ybwtms}</td>
											<td style='text-align:center'>${i.ybgyhjg}</td>
										</tr>
									</logic:equal>
									</logic:iterate>
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
					<thead>
										<tr>
											<th colspan="6">
												<span>������Ϣ</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="6" id="shlccx">
											
											</td>
										</tr>
									</tbody>
										<thead>
										<tr>
											<th colspan="6">
												<span>�����Ϣ</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th >
												��˽��
											</th>
											<td id="shjgSpan" colspan="5" style="text-align:left">
												
											</td>
										</tr>
										
										<tr>
											<th width="20%">
												<font color="red">*&nbsp;</font> ������
												<br />
												<font color="red">(��200��)</font>
											</th>
											<td colspan="5" style="text-align:left">
												<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ybshx&id=shyj" />
												<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
											</td>
										</tr>
									</tbody>	
									<logic:equal value="10704" name="xxdm">
					<tbody>
						<tr>
							<th align="right">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${YbshForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
						</tbody>
						</logic:equal>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
										�� ��
								   </button>
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

