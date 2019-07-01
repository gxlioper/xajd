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
			  var mc=jQuery("#khdxmc").val();
			  var dm=jQuery("#khlx").val();
			  if(jQuery.trim(mc)==""){
				  showAlert("�����뿼�˶������ƣ�");
					return false;
			  }
		     var url = "khglKhdxgl.do?method=saveKhdx&type=update";
		      ajaxSubFormWithFun("KhlxglForm",url,function(data){
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
		<html:form action="/khglKhdxgl" method="post"
			styleId="KhlxglForm" onsubmit="return false;">
			<html:hidden property="khdxid" styleId="khdxid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>���˶�������
							</th>
							<td>
								<html:text property="khdxmc" styleId="khdxmc" maxlength="20"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����
							</th>
							
							<td>
								<logic:equal value="0" name="sfqy">
								<html:select  property="khlx" styleId="khlx">
								<html:options collection="khlxList" labelProperty="mc" property="dm"/>
								</html:select>
								</logic:equal>
								<logic:equal value="1" name="sfqy">
									${KhdxglForm.khlxmc }
								</logic:equal>
							</td>
						</tr>

					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

