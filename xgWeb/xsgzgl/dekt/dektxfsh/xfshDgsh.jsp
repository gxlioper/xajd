<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	

	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${shid}");
	});

	function saveShzt(){
		var shzt = jQuery("#shjg").val();
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("��ѡ�����״̬��");
			return false;
		}
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("����д��������");
			return false;
		}
		var xf=jQuery("#xf").val();
		if (jQuery.trim(xf) == ""){
			showAlertDivLayer("�뽫��������д������");
			return false;
		}
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "ͨ��";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "��ͨ��";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "�˻�";
		}
		showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
			var url = "dekt_xfsh.do?method=saveSh";
			ajaxSubFormWithFun("dektxfshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
	}

	
	</script>
</head>
<body>
	<html:form action="/dekt_xfsh" method="post" styleId="dektxfshForm">
			
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden property="shid" styleId="shid"/>
			
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
		<table width="100%" border="0" class="formlist">
		<div class="prompt" id="div_help" style="display:">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				ѧ���϶���׼�������ѧ���϶���׼�ĵ���
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
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
							<th>�϶�����</th>
							<td colspan="3" >
								${model.rdnr}
							</td>
						</tr>
			      		 <tr>
							<th >
							   	���뷽ʽ
							</th>
							<td colspan="3" >
								${model.cyfsmc}
							</td>
					    </tr>
			      		 <tr>
							<th>��ʱ��</th>
							</th>
							<td colspan="3"> 
								${model.hjsj}
							</td>
			      		</tr>
			      		 <tr>
							<th>����˵��</th>
							</th>
							<td colspan="3"> 
								${model.sqsm}
							</td>
			      		</tr>
				<tr>
					<th align="right" width="15%">
						����
					</th>
					<td colspan="3">
						<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
						<html:hidden name="model" property="filepath" styleId="filepath" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
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
					<th width="20%">
						<font color="red">*&nbsp;</font> ѧ��
					</th>
					<td colspan="3">
						<html:text name="model" property="xf" styleId="xf" maxlength="5" onkeyup="checkInputNum(this)"></html:text>
					</td>
				</tr>
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
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gyglxyzssh&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
		  </table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveShzt();return false;">
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
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
