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
			function updateForm(){
				  if(!checkNull("xszbblxmc-shlc")){
						return false;
					  }

				 var url = "rcsw_xszbb_bblxwhgl.do?method=updateXszbblx&type=update";
			      ajaxSubFormWithFun("xszbblxwhForm",url,function(data){
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
		<html:form action="/rcsw_xszbb_bblxwhgl" method="post" styleId="xszbblxwhForm" onsubmit="return false;">
			 <html:hidden property="xszbblxdm"  styleId="xszbblxdm"/>
			 <logic:equal name="model" property="zjlxbs" value="1">
				<div id="div_help" class="prompt">
					<p>
						<span id="lable">ϵͳ���ü�¼<font color="red">֤������</font>�����޸�</span>
					</p>
					<a class="close" onclick="this.parentNode.style.display='none';" title="����"></a>
				</div>
			 </logic:equal>
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
										֤������ά��
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									֤������
								</th>
								<td>
									<span>${model.xszbblxdm}</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>֤������
								</th>
								<td>
									<logic:equal name="model" property="zjlxbs" value="1">
										<span>${model.xszbblxmc}</span>
										<html:hidden property="xszbblxmc" styleId="xszbblxmc"/>
									</logic:equal>
									<logic:notEqual name="model" property="zjlxbs" value="1">
										<html:text property="xszbblxmc" styleId="xszbblxmc" maxlength="25" styleClass="text_nor" />
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>�������</th>
								<td>
									<html:select property="shlc" styleId="shlc" style="width:280px;">
										<html:option value=""></html:option>
										<html:options collection="shlcList" property="splc"
											labelProperty="lcxx" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="updateForm();return false;">
											�� ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();return false;">
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

