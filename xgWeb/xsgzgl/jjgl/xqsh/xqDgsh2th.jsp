<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function saveAudit(shzt){
			if(jQuery('#ztbz').val().length > 100){
				showAlert('��������������100!');
				return false;
			}
			
			jQuery("#shzt").val(shzt);
			//�ύ���
			showConfirmDivLayer("��ȷ��Ҫ�ύ��",{"okFun" : function(){
				var url = "jjgl_xqshgl.do?method=audit";
				ajaxSubFormWithFun("jjglXqshForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
		
		
		function xqshxg(shzt,text){
			var url = "jjgl_jjzg.do?method=submitAudit&tt="+new Date();
			jQuery("#zgshForm").ajaxSubmit( {
				url : url,
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data["message"] == "����ɹ���") {
						showAlert("<font color='red'>["+text+"]</font>�����ɹ���", {}, {
							"clkFun" : function() {
								if (parent.window) {
									refershParent();
								}
							}
						});
					} else {
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					}
				}
			});
		}
	</script>
  </head>
  
  <body>
  		<html:form action="/jjgl_xqshgl" method="post" styleId="jjglXqshForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<div class='tab' style='width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">������</th>
					    	<td width="30%">${xqModelMap.sqr }</td>
					    	<th width="20%">����ʱ��</th>
					    	<td width="30%">${xqModelMap.sqsj }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ�ѧ��</th>
					    	<td>${xqModelMap.jjxkmc }</td>
					    	<th>ѧ���꼶</th>
					    	<td>${xqModelMap.jjnjmc }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ�ʱ��</th>
					    	<td>${xqModelMap.jjsj }</td>
					    	<th>�ҽ̵ص�</th>
					    	<td>${xqModelMap.jjdd }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ���ʦҪ��</th>
					    	<td colspan="3">${xqModelMap.jjlsyq }</td>
					    </tr>
					    <tr>
					    	<th>��ע</th>
					    	<td colspan="3">${xqModelMap.bz }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ů��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">����</th>
					    	<td width="30%">${znxxMap.xm }</td>
					    	<th width="20%">�Ա�</th>
					    	<td width="30%">${znxxMap.xb }</td>
					    </tr>
					    <tr>
					    	<th width="20%">��������</th>
					    	<td width="30%">${znxxMap.csrq }</td>
					    	<th width="20%">�꼶</th>
					    	<td width="30%">${znxxMap.nj }</td>
					    </tr>
					    <tr>
					    	<th width="20%">�ڶ�ѧУ</th>
					    	<td colspan="3">${znxxMap.zdxx }</td>
					    </tr>
					 </tbody>
					 <thead>
						<tr>
							<th colspan="5">
								<span>���</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">������<br/><font color="red">(��100��)</font></th>
					    	<td colspan="3">
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
									<button type="button" id="btqd" onclick="saveAudit('1');">
										ͨ��
									</button>
									<button type="button" id="btqd" onclick="saveAudit('2');">
										��ͨ��
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
