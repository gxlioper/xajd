<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function tsfkSubmit(tsid){
			var fkxx = jQuery.trim(jQuery("#" + tsid).val());
			if(fkxx == '' || fkxx == null){
				showAlert('����д�������ݣ�');
				return false;
			}

			if(fkxx.length > 200){
				showAlert('��������200����ȷ�ϣ�');
				return false;
			}

			showConfirm("��ȷ��Ҫ�ύ��",{"okFun" : function(){
				var url = "jjgl_xqwhgl.do?method=tsfkSubmit";

				jQuery.post(url , {tsid : tsid , fkxx : fkxx} , function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						window.location.reload();
					}});
				} , 'JSON');
				
			}});
		}
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<div class='tab' style='tab;width:100%;height:400px; hidden;overflow-y:scroll;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th >
								<span>Ͷ���б�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="tsfkxxList">
							<logic:notEmpty name="tsfkxxList">
								<logic:iterate id="tsxxItem" name="tsfkxxList">
									<logic:notEmpty name="tsxxItem" property="fksj">
										<tr>
									    	<td>
									    		<table width="100%">
									    			<tbody>
									    				<tr>
									    					<th width="15%">Ͷ�ߣ�</th>
									    					<td width="55%"><bean:write name="tsxxItem" property="tsxx"/></td>
									    					<th width="15%">Ͷ��ʱ�䣺</th>
									    					<td width="15%"><bean:write name="tsxxItem" property="tssj"/></td>
									    				</tr>
									    				<tr>
									    					<th width="15%">������</th>
									    					<td width="55%"><bean:write name="tsxxItem" property="fkxx"/></td>
									    					<th width="15%">����ʱ�䣺</th>
									    					<td width="15%"><bean:write name="tsxxItem" property="fksj"/></td>
									    				</tr>
									    			</tbody>
									    		</table>
									    	</td>
									    </tr>
									</logic:notEmpty>
									<logic:empty name="tsxxItem" property="fksj">
										<tr>
									    	<td>
									    		<table width="100%">
									    			<tbody>
									    				<tr>
									    					<th width="15%">Ͷ�ߣ�</th>
									    					<td width="55%"><bean:write name="tsxxItem" property="tsxx"/></td>
									    					<th width="15%">Ͷ��ʱ�䣺</th>
									    					<td width="15%"><bean:write name="tsxxItem" property="tssj"/></td>
									    				</tr>
									    				<tr>
									    					<th width="15%"><span class="red">*</span>������<br/><font color="red">(��200��)</font></th>
									    					<td width="85%" colspan="2">
									    						<textarea rows="3" style="width:100%" name="fkxx" id="<bean:write name="tsxxItem" property="tsid"/>"></textarea>
									    						<%--<html:textarea property="fkxx" rows="3" style="width:100%" ></html:textarea>
									    					--%></td>
									    					<td width="15%">
									    						<button type="button" id="btqd" onclick="tsfkSubmit('<bean:write name="tsxxItem" property="tsid"/>');">
																	����
																</button>
									    					</td>
									    				</tr>
									    			</tbody>
									    		</table>
									    	</td>
									    </tr>
									</logic:empty>
								</logic:iterate>
							</logic:notEmpty>
						</logic:present>
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
