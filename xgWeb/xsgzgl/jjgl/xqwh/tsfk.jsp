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
				showAlert('请填写反馈内容！');
				return false;
			}

			if(fkxx.length > 200){
				showAlert('字数超过200，请确认！');
				return false;
			}

			showConfirm("您确定要提交？",{"okFun" : function(){
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
								<span>投诉列表</span>
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
									    					<th width="15%">投诉：</th>
									    					<td width="55%"><bean:write name="tsxxItem" property="tsxx"/></td>
									    					<th width="15%">投诉时间：</th>
									    					<td width="15%"><bean:write name="tsxxItem" property="tssj"/></td>
									    				</tr>
									    				<tr>
									    					<th width="15%">反馈：</th>
									    					<td width="55%"><bean:write name="tsxxItem" property="fkxx"/></td>
									    					<th width="15%">反馈时间：</th>
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
									    					<th width="15%">投诉：</th>
									    					<td width="55%"><bean:write name="tsxxItem" property="tsxx"/></td>
									    					<th width="15%">投诉时间：</th>
									    					<td width="15%"><bean:write name="tsxxItem" property="tssj"/></td>
									    				</tr>
									    				<tr>
									    					<th width="15%"><span class="red">*</span>反馈：<br/><font color="red">(限200字)</font></th>
									    					<td width="85%" colspan="2">
									    						<textarea rows="3" style="width:100%" name="fkxx" id="<bean:write name="tsxxItem" property="tsid"/>"></textarea>
									    						<%--<html:textarea property="fkxx" rows="3" style="width:100%" ></html:textarea>
									    					--%></td>
									    					<td width="15%">
									    						<button type="button" id="btqd" onclick="tsfkSubmit('<bean:write name="tsxxItem" property="tsid"/>');">
																	反馈
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
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
										关 闭
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
