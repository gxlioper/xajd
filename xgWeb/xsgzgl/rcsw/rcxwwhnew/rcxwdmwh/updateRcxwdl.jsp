<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		function saveForm(){
			  var xxdm = jQuery("#xxdm").val();	  
			  var rcxwlbdlmc=jQuery("#rcxwlbdlmc").val();
			  if(jQuery.trim(rcxwlbdlmc)==""){
				  if(xxdm == '10704'){
					  showAlert("�������ۺϲ����������ƣ�");
				  }else{
					  showAlert("��������Ϊ�������ƣ�");
				  }
					return false;
			  }
			  var rcxwlbdm=jQuery("#rcxwlbdm").val();
			  if(jQuery.trim(rcxwlbdm)==""){
				  if(xxdm = '10704'){
					  showAlert("��ѡ�������ۺϲ������");
				  }else{
					  showAlert("��ѡ��������Ϊ���");
				  }
					return false;
			  }
			  var splc=jQuery("#splc").val();
			  if(jQuery.trim(splc)==""){
				  showAlert("��ѡ��������̣�");
					return false;
			  }
		     var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwdl&type=update";
		      ajaxSubFormWithFun("rcxwdmwhForm",url,function(data){
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
		jQuery(function(){
			setTimeout("titleLoad('splc')",500);
			setTimeout("titleLoad('rcxwlbdm')",500);
			if('${update_n}' == 'true'){
				jQuery('select').attr('disabled','disabled');
			}
		})
		function titleLoad(id){
			if(jQuery("#"+id)){
				jQuery("#"+id).children("option").each(function(){
					jQuery(this).attr("title",jQuery(this).text());
				});
			}
		}
		</script>
	</head>
	<body >
		<input type="hidden" id="xxdm" value="${xxdm}"/>
		<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
			<html:hidden property="rcxwlbdldm"/>
			<logic:equal name="update_n" value="true">
				<div class="prompt">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						<logic:equal value="10704" name="xxdm">
							�����ۺϲ���С����ۺϲ�������ֻ���޸����ơ�
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							������ΪС�����Ϊ����ֻ���޸����ơ�
						</logic:notEqual>
					</p>
					<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
				</div>
			</logic:equal>
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<%--<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>��Ϊ�������
								</th>
								<td >
									<html:text property="rcxwlbdldm" styleId="rcxwlbdldm" maxlength="20" styleClass="text_nor" />
								</td>
							</tr>
							--%><tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="10704" name="xxdm">
											�ۺϲ�����������
										</logic:equal>
										<logic:notEqual value="10704" name="xxdm">
											��Ϊ��������
										</logic:notEqual>										
								</th>
								<td>
									<html:text property="rcxwlbdlmc" styleId="rcxwlbdlmc" maxlength="25" style="width:273px;" styleClass="text_nor" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="10704" name="xxdm">
											�����ۺϲ������
										</logic:equal>
										<logic:notEqual value="10704" name="xxdm">
											������Ϊ���
										</logic:notEqual>									
								</th>
								<td>
									<html:select property="rcxwlbdm" styleId="rcxwlbdm" disabled="false" style="width:280px;">
										<html:options collection="rcxwlbListByYhsq" property="rcxwlbdm" labelProperty="rcxwlbmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th><span> </span>
									����ѧԺ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<td>
									<html:hidden property="ssxydm" style="width:180px" styleId="ssxydm"/>
										${ssxymc}
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>
									�������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<td>
									<html:select property="splc" styleId="splc" disabled="false" style="width:280px;">
										<html:options collection="shlcList" property="splc" labelProperty="lcxx" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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

