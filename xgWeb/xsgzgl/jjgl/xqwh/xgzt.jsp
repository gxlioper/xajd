<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function jjztxgSubmit(){
			if(jQuery('#ztbz').val().length > 100){
				showAlert('�޸�ԭ����������100!');
				return false;
			}
			//�ύ
			showConfirm("��ȷ��Ҫ�ύ��",{"okFun" : function(){
				var url = "jjgl_xqwhgl.do?method=changeJJztSubmit";
				ajaxSubFormWithFun("jjglXqwhForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
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
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid" value="${xqxxMap.xqid }"/>

			<div class='tab' style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>״̬��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">��ǰ״̬</th>
					    	<td  width="70%">${xqxxMap.jjztmc}</td>
					    	
						</tr>
						<tr>
							<th >�ҽ̲���</th>
					    	<td >
					    		<html:select property="jjzt" styleId="jjzt" value="${xqxxMap.jjzt}" style="width:70%">
					    			<logic:present name="xqxxMap">					    			
					    			<logic:equal value="0" name="xqxxMap" property="jjzt">
					    				<html:option value="4">�رռҽ�</html:option>
					    			</logic:equal>
					    			<logic:equal value="1" name="xqxxMap" property="jjzt">
					    				<html:option value="0">�˼ҽ�</html:option>
					    				<html:option value="3">��Э����</html:option>
					    				<html:option value="4">�رռҽ�</html:option>
					    			</logic:equal>
					    			<logic:equal value="2" name="xqxxMap" property="jjzt">
										<html:option value="0">�˼ҽ�</html:option>
										<html:option value="3">��Э����</html:option>
										<html:option value="4">�رռҽ�</html:option>
					    			</logic:equal>
					    			<logic:equal value="3" name="xqxxMap" property="jjzt">
					    				<html:option value="4">�رռҽ�</html:option>
					    			</logic:equal>
					    			</logic:present>
					    		</html:select>
					    		
					    	</td>
						</tr>
						<tr>
					    	<th width="30%">�޸�ԭ��<br/><font color="red">(��100��)</font></th>
					    	<td >
					    		<html:textarea styleId="ztbz" property="ztbz" rows="3" style="width:90%"></html:textarea>
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
								<div class="btn">
									<button type="button" id="btqd" onclick="jjztxgSubmit();">
										�ύ
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
