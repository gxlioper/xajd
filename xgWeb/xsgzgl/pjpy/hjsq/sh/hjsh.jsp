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
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${HjshForm.id}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${HjshForm.splc}&shid=${HjshForm.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("�뽫��������д������");
				return false;
			}
			var url = "jxsh.do?method=saveDgsh";
			ajaxSubFormWithFun("HjshForm",url,function(data){
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
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jxsh" method="post" styleId="HjshForm">
		<html:hidden property="id" styleId="id"/>
		<html:hidden  property="splc" styleId="splc"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th>ѧ��</th>
							<td>${xn}</td>
							<th>ѧ��</th>
							<td>${xqmc}</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								${HjshForm.hjmc}
							</td>
							<th>������</th>
							<td>
								${HjshForm.hjsj}
							</td>
						</tr>
						<tr>
							<th>�佱��λ</th>
							<td colspan="3">
								${HjshForm.fjdw}
							</td>
						</tr>
					
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<html:hidden property="fj" styleId="fj"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = jQuery('#fj').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
								</script> 
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
			<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th >
						��˽��
					</th>
					<td id="shjgSpan">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jxsh&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
										�� ��
									</button>
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
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