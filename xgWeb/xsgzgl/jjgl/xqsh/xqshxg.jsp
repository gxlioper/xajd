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
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_xqshgl" method="post" styleId="jjglXqshForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<div class='tab' style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
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
					    	<th>��Ů����</th>
					    	<td colspan="3">${xqModelMap.znxm }</td>
					    </tr>
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
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��ǰ���״̬
							</th>
							<td >
								<span style="color:red;font-size: 13px">
									${xqModelMap.shztmc}
								</span>
							</td>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="1" id="shjgSpan">
								<html:select property="shzt" styleId="shzt" value="${xqModelMap.shzt}">
									<html:option value="0">δ���</html:option>
									<html:option value="1">ͨ��</html:option>
									<html:option value="2">��ͨ��</html:option>
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
								<div class="btn">
									<button type="button" id="btqd" onclick="saveAudit();">
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
