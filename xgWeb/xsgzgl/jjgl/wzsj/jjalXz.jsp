<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function submitXz(sffb){
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
				var url = "jjgl_jjalgl.do?method=submit&sffb=" + sffb;
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
			<div class='tab' style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ̰�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th  width="20%"><span class="red">*</span>�ҽ̶���</th>
					    	<td colspan="1">
					    		<input type="text" name="jjdx" id="jjdx" maxlength="50" style="width: 95%"/>
					    	</td>
					    	<th  width="20%"><span class="red">*</span>�ҽ�ѧ��</th>
					    	<td colspan="1">
					    		<input type="text" name="fdxk" id="fdxk" maxlength="50" style="width: 95%"/>
					    	</td>
					    </tr>
					     <tr>
					    	<th  width="20%"><span class="red">*</span>�ҽ�ʱ��</th>
					    	<td colspan="3">
					    		<input type="text" name="jjsj" id="jjsj" maxlength="50" style="width: 95%"/>
					    	</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>�ҽ̳ɼ�</th>
					    	<td colspan="3">
					    		<textarea rows="10" style="width:95%;" name="jjms" id="jjms"></textarea>
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
									<button type="button" onclick="submitXz('1');">
										����
									</button>
									<button type="button" onclick="submitXz('0');">
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
