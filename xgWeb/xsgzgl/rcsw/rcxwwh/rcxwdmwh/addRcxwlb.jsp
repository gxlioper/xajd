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
            var xxdm="${xxdm}";
		function saveForm(){
			 // var rcxwlbdm=jQuery("#rcxwlbdm").val();
			 // if(rcxwlbdm==""){
				//  showAlert("��������Ϊ�����룡");
				//	return false;
			 // }
			  
			  var rcxwlbmc=jQuery("#rcxwlbmc").val();
			  if(jQuery.trim(rcxwlbmc)==""){
                  var msg = "��������Ϊ������ƣ�";
                  if("13431" == xxdm) msg = "������ӷ�������ƣ�";
                  showAlert(msg);
				  return false;
			  }
			  
			  var rcxwlbzdfz=jQuery("#rcxwlbzdfz").val();
			  var rcxwlbzgfz=jQuery("#rcxwlbzgfz").val();
			  if(jQuery.trim(rcxwlbzdfz)=="" && jQuery.trim(rcxwlbzgfz)==""){
                  var msg = "��������Ϊ����ֵ���䣡";
                  if("13431" == xxdm) msg = "������ӷ�����ֵ���䣡";
                  showAlert(msg);
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
			  
			  var rcxwfzlx=jQuery("#rcxwfzlx").val();
			  if(jQuery.trim(rcxwfzlx)==""){
				  showAlert("��ѡ���ֵ���ͣ�");
					return false;
			  }
			  
			  var rcxwlbdlmc=jQuery("#rcxwlbdldm").val();
			  if(jQuery.trim(rcxwlbdlmc)==""){
                  var msg = "��ѡ��������Ϊ���࣡";
                  if("13431" == xxdm) msg = "��ѡ�������ӷִ��࣡";
                  showAlert(msg);
					return false;
			  }

		     var url = "rcsw_rcxwwh_rcxwdmwhgl.do?method=addRcxwlb&type=save";
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
	
	
		</script>
		
		
	</head>
	<body >
		<html:form action="/rcsw_rcxwwh_rcxwdmwhgl" method="post" styleId="rcxwdmwhForm" onsubmit="return false;">
			
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>
									<logic:notEqual name="xxdm" value="13431">
										<span>��Ϊ�������</span>
									</logic:notEqual>
									<logic:equal name="xxdm" value="13431">
										<span>�ӷ��������</span>
									</logic:equal>
								</th>
								<td>
									<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxlength="100"/>
								</td>
								<th>
									<span class="red">*</span>
									<logic:notEqual name="xxdm" value="13431">
										<span>������Ϊ����</span>
									</logic:notEqual>
									<logic:equal name="xxdm" value="13431">
										<span>�����ӷִ���</span>
									</logic:equal>
								</th>
								<td>
									<html:select property="rcxwlbdldm" styleId="rcxwlbdldm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="xwdlList" property="rcxwlbdldm" labelProperty="rcxwlbdlmc" />
									</html:select>
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="red">*</span>��ֵ����
								</th>
								<td>
									<html:select property="rcxwfzlx" styleId="rcxwfzlx" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="01">�ӷ�</html:option>
										<html:option value="02">����</html:option>
									</html:select>
								</td>
								<th>
									<span class="red">*</span>��ֵ
								</th>
								<td>
									<input type="text" name="rcxwlbzdfz" id="rcxwlbzdfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px"/>&nbsp; - &nbsp;
									<input type="text" name="rcxwlbzgfz" id="rcxwlbzgfz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 56px"/>
									<br/><span class="red">��ֻ������1��ֵ�����ֵΪ�̶�ֵ</span>
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

