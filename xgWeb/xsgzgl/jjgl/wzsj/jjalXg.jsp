<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function submitXz(){
			if(jQuery.trim(jQuery('#jjdx').val()) == ''){
				showAlertDivLayer("����д�ҽ̶���");
				return false;
			}

			if (jQuery.trim(jQuery('#fdxk').val()) == ''){
				showAlertDivLayer("����д�ҽ�ѧ�ƣ�");
				return false;
			}

			if (jQuery.trim(jQuery('#jjsj').val()) == ''){
				showAlertDivLayer("����д�ҽ�ʱ�䣡");
				return false;
			}

			if (jQuery.trim(jQuery('#jjms').val()) == ''){
				showAlertDivLayer("����д�ҽ�������");
				return false;
			}
			
			if (jQuery("#jjms").val().length>500){
				showAlertDivLayer("�ҽ��������ܳ���500��");
				return false;
			}
			//�ύ���
			showConfirmDivLayer("��ȷ��Ҫ�ύ��",{"okFun" : function(){
				var url = "jjgl_jjalgl.do?method=submitXg";
				ajaxSubFormWithFun("jjglJJalForm",url,function(data){
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
		<html:form action="/jjgl_jjalgl" method="post" styleId="jjglJJalForm">
			<html:hidden property="sid"/>
			<div class='tab' style='tab;width:100%;height:350px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�޸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th  width="20%"><span class="red">*</span>�ҽ̶���</th>
					    	<td colspan="1">
					    		<html:text property="jjdx" styleId="jjdx" maxlength="50" style="width:95%" ></html:text>
					    	</td>
					    	<th  width="20%"><span class="red">*</span>�ҽ�ѧ��</th>
					    	<td colspan="1">
					    		<html:text property="fdxk" styleId="fdxk" maxlength="50" style="width:95%" ></html:text>
					    	</td>
					    </tr>
					     <tr>
					    	<th  width="20%"><span class="red">*</span>�ҽ�ʱ��</th>
					    	<td colspan="3">
					    		<html:text property="jjsj" styleId="jjsj" maxlength="50" style="width:95%" ></html:text>
					    	</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>�ҽ̳ɼ�</th>
					    	<td colspan="3">
					    		<html:textarea property="jjms" styleId="jjms" rows="10" style="width:95%"></html:textarea>
					    	</td>
					    </tr>
					     <tr>
					    	<th><span class="red">*</span>�Ƿ񷢲�</th>
					    	<td colspan="3">
					    		<html:select property="sffb" styleId="sffb">
					    			<html:option value="1">����</html:option>
					    			<html:option value="0">������</html:option>
					    		</html:select>
					    	</td>
					    </tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="submitXz();">
										����
									</button>
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
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
