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
			 // var rcxwlbxldm=jQuery("#rcxwlbxldm").val();
			 // if(rcxwlbxldm==""){
				//  showAlert("��������Ϊ�����룡");
				//	return false;
			 // }
			  var xxdm = jQuery("#xxdm").val();
			  
			  var rcxwlbxlmc=jQuery("#rcxwlbxlmc").val();
			  if(jQuery.trim(rcxwlbxlmc)==""){
				  if(xxdm == '10704'){
					  showAlert("�������ۺϲ���С�����ƣ�");
				  }else{
					  showAlert("��������ΪС�����ƣ�");
				  }
					return false;
			  }
			  var rcxwlbdm=jQuery("#rcxwlbdm").val();
			  if(jQuery.trim(rcxwlbdm)==""){
				  if(xxdm == '10704'){
					  showAlert("��ѡ���ۺϲ������");
				  }else{
					  showAlert("��ѡ��������Ϊ���");
				  }			  
					return false;
			  }
			  var rcxwlbdldm=jQuery("#rcxwlbdldm").val();
			  if(jQuery.trim(rcxwlbdldm)==""){
				  if(xxdm == '10704'){
					  showAlert("��ѡ���ۺϲ������࣡");
				  }else{
					  showAlert("��ѡ��������Ϊ���࣡");
				  }				  
					return false;
			  }
			  var rcxwfzlx=jQuery("#rcxwfzlx").val();
			  if(jQuery.trim(rcxwfzlx)==""){
				  showAlert("��ѡ���ֵ���ͣ�");
					return false;
			  }
			  
			  var rcxwlbzdfz=jQuery("#rcxwlbzdfz").val();
			  var rcxwlbzgfz=jQuery("#rcxwlbzgfz").val();
			  if(jQuery.trim(rcxwlbzdfz)=="" && jQuery.trim(rcxwlbzgfz)==""){
				  showAlert("�������ֵ���䣡");
					return false;
			  }
			  if(jQuery.trim(rcxwlbzdfz)!="" && jQuery.trim(rcxwlbzgfz)!=""){
				  if(parseFloat(rcxwlbzdfz)>parseFloat(rcxwlbzgfz)){
					  showAlert("��ͷ�ֵ���ܴ�����߷�ֵ��");
						return false;
			      }
				  if(parseFloat(rcxwlbzdfz)==parseFloat(rcxwlbzgfz)){
					  showAlert("��ֵ������ͬ��");
						return false;
			      }
			  }
			  
		     var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwxl&type=save";
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
		  function changeRcxwlbdldm(obj){
			  jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=queryRcxwlbdlListByLbdm",{rcxwlbdm:obj.value},function(data){
				  var html = '';
				  if(data && data.length > 0){
					  for(var i = 0; i < data.length; i++){
						html += '<option value='+data[i].rcxwlbdldm+'>'+data[i].rcxwlbdlmc+'</option>';
						if(0 == i) changeSsxy(data[0].rcxwlbdldm);
					  }
				  }else{
                      jQuery("#ssxyTd").html("");
				  }
				jQuery("#rcxwlbdldm").html(html);
			  },'json');
		  }
        function changeSsxy(value){
            jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=getSsxy",{rcxwlbdldm:value},function(data){
                jQuery("#ssxyTd").html(data);
            });
        }
		  jQuery(function(){
			  jQuery("#rcxwlbdm").change();
		  });
		</script>
	</head>
	<body >
		<input type="hidden" id="xxdm" value="${xxdm}" />
		<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
			
				<div class="open_win01">
					<table align="center" class="formlist">
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ���С������
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										��ΪС������
									</logic:notEqual>
								</th>
								<td colspan="3">
									<input type="text" id="rcxwlbxlmc" name="rcxwlbxlmc" maxlength="100" style="width: 99%;"/>
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
									<select name="rcxwlbdm" id="rcxwlbdm" style="width: 150px;" onchange="changeRcxwlbdldm(this);">
										<logic:iterate id="rcxwlbByYhsq" name="rcxwlbListByYhsq" >
											<option value="${rcxwlbByYhsq.rcxwlbdm }" title="${rcxwlbByYhsq.rcxwlbmc }">${rcxwlbByYhsq.rcxwlbmc }</option>
										</logic:iterate>
									</select>
								</td>
								<th>
									<span class="red">*</span>��ֵ����
								</th>
								<td>
									<html:select property="rcxwfzlx" styleId="rcxwfzlx" style="width:150px;">
										<html:option value="01">�ӷ�</html:option>
										<html:option value="02">����</html:option>
									</html:select>
								</td>

							</tr>
							
							<tr>
								<th>
									<span class="red">*</span>
									<logic:equal value="10704" name="xxdm">
										�����ۺϲ�������
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										������Ϊ����
									</logic:notEqual>
								</th>
								<td >
									<select name="rcxwlbdldm" id="rcxwlbdldm" style="width: 150px;" onchange="changeSsxy(this.value);">
									</select>
								</td>
								<th>
									����ѧԺ
								</th>
								<td id="ssxyTd">

								</td>

							</tr>
							<tr>
								<th>
									<span class="red">*</span>��ֵ
								</th>
								<td colspan="3">
									<input type="text" name="rcxwlbzdfz" id="rcxwlbzdfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px"/>&nbsp; - &nbsp;
									<input type="text" name="rcxwlbzgfz" id="rcxwlbzgfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px"/>
									<span class="red">��ֻ������1��ֵ�����ֵΪ�̶�ֵ</span>
								</td>
							</tr>
							<tr>
								<th>����˵��<br/><span class="red">����500�֣�</span></th>
								<td colspan="3">
									<textarea name="rcxwlbbz" id="rcxwlbbz" rows="4" cols="3" style="width:99%" onblur="checkLen(this,500);"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveForm();return false;">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="iFClose();return false;">
											ȡ ��
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

