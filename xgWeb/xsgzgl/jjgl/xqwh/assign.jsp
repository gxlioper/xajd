<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function assignSubmit(){
			if(jQuery('input[name="xh"]:checked').length > 1 || jQuery('input[name="xh"]:checked').length == 0){
				showAlert("��ѡ��һ��ѧ��,��ȷ��!");
				return false;
			}
			
			//�ύ
			showConfirmDivLayer("��ȷ��Ҫ�ύ��",{"okFun" : function(){
				var url = "jjgl_xqwhgl.do?method=assignSubmit";
				ajaxSubFormWithFun("jjglXqwhForm",url,function(data){
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
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<div class='tab' style='tab;width:100%;overflow-x:hidden;overflow-y:scroll;'>
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
					    	<td>${xqModelMap.jjxk }</td>
					    	<th>ѧ���꼶</th>
					    	<td>${xqModelMap.jjnj }</td>
					    </tr>
					    <tr>
					    	<th>�ҽ̵ص�</th>
					    	<td colspan="3">${xqModelMap.jjdd }</td>
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
								<span>��ǰ����ѧ���б�  <a style="color: blue;">[�빴ѡһλѧ��,Ȼ���ύ]</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<td colspan="4">
					    		<table width="100%">
					    			<thead>
										<tr>
											<td>
												ѧ��
											</td>
											<td>
												����
											</td>
											<td>
												ѧԺ
											</td>
											<td>
												רҵ
											</td>
											<td>
												�༶
											</td>
											<td>
												����ʱ��
											</td>
											<td>
												ѡ��
											</td>
										</tr>
									</thead>
					    			<tbody>
					    				<logic:empty name="xqXsSqList">
					    					<tr>
					    						<td colspan="8" style="text-align:center;">
					    							����������!
					    						</td>
					    					</tr>
					    				</logic:empty>
										<logic:notEmpty name="xqXsSqList">
											<logic:iterate id="xs" name="xqXsSqList">
											<tr>
					    						<td>${xs.xh}</td>
					    						<td>${xs.xm}</td>
					    						<td>${xs.xymc}</td>
					    						<td>${xs.zymc}</td>
					    						<td>${xs.bjmc}</td>
					    						<td>${xs.sqsj}</td>
					    						<td>
					    							<html:checkbox property="xh" value="${xs.xh}"></html:checkbox>
					    						</td>
					    						</tr>
					    					</logic:iterate>
										</logic:notEmpty>
					    			</tbody>
					    		</table>
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
									<button type="button" id="btqd" onclick="assignSubmit();">
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
