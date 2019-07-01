<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/xlwjga/xlwjgasb/js/xlwjgasbComm.js"></script>
		<script language="javascript">
			function saveForm(){	
			  clearData('xswjbx', xswjbxMsg);  
			  clearData('xyclgc', xyclgcMsg);  
			  clearData('zyqk', zyqkMsg);  
			  var xh=jQuery("#xh").val();
			  if(jQuery.trim(xh)==""){
				  showAlert("��ѡ��ѧ����");
					return false;
			  }
			  var sbrlxfs=jQuery("#sbrlxfs").val();
			  if(jQuery.trim(sbrlxfs)==""){
				  showAlert("��ϵ�绰����Ϊ�գ�");
					return false;
			  }
			  var wjcd = jQuery("[name=wjcd]:checked").val();
			  if(jQuery.trim(wjcd)==""){
				  showAlert("��ѡ��Σ���̶ȣ�");
					return false;
			  }
			  var xswjbx=jQuery("#xswjbx").val();
			  if(jQuery.trim(xswjbx)==""){
				  showAlert("ѧ��Σ�����ֲ���Ϊ�գ�");
					return false;
			  }
			  var ywzyls = jQuery("[name=ywzyls]:checked").val();
			  if("1"==ywzyls){
				  var zyqk=jQuery("#zyqk").val();
				  if(jQuery.trim(zyqk)==""){
					  showAlert("סԺ�������Ϊ�գ�");
						return false;
				  }
			  }else{
				  jQuery("#zyqk").val("");
			  }
			  var xszk=jQuery("#xszk").val();
			  if(jQuery.trim(xszk)==""){
				  showAlert("ѧ��Ŀǰ״������Ϊ�գ�");
					return false;
			  }
					
		      var url = "xlzx_xlwjga_xlwjgasbgl.do?method=addXlwjgasb&type=save";
		      ajaxSubFormWithFun("xlwjgasbForm",url,function(data){
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
				initData('xswjbx', xswjbxMsg);  
				initData('xyclgc', xyclgcMsg);  
				initData('zyqk', zyqkMsg);
				jQuery('#ywzyls_td').click();
			})
		</script>
	</head>
	<body >
		<html:form action="/xlzx_xlwjga_xlwjgasbgl" method="post" styleId="xlwjgasbForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:465px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�ϱ���
							</th>
							<td>
								${userNameReal }
							</td>
							<th>
								<span class="red">*</span>��ϵ�绰
							</th>
							<td>
								<html:text property="sbrlxfs" styleId="sbrlxfs" maxlength="20" onkeyup="checkInputLxfx(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>Σ���̶�
							</th>
							<td colspan="3">
								<logic:iterate id="o" name="wjcdList" >
									<label>
										<html:radio property="wjcd" value="${o.lxdm}">${o.lxmc}</html:radio>
									</label>
								</logic:iterate>
							</td>
						</tr>
						<tr>
						    <th>
								<span class="red">*</span>ѧ��Σ������</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="xswjbx" styleId="xswjbx" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
						    <th>
								ѧԺ�������</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="xyclgc" styleId="xyclgc" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����סԺ��ʷ
							</th>
							<td id="ywzyls_td" colspan="3" onclick="changeZyqk()">
								<logic:iterate id="o" name="haveList" >
									<label>
										<html:radio property="ywzyls" value="${o.dm}">${o.mc}</html:radio>
									</label>
								</logic:iterate>
							</td>
						</tr>
						<tr id="zyqk_tr">
						    <th>
								<span class="red">*</span>סԺ���</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="zyqk" styleId="zyqk" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
						    <th>
								<span class="red">*</span>ѧ��Ŀǰ״��</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="xszk" styleId="xszk" cols="50" rows="4" style="width:100%" onblur="chLeng(this,500)"></html:textarea>
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

