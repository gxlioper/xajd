<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			function savePlReply(){
				if(!!jQuery("#plnr").val()){
					var hdid = jQuery("#hdid").val();
					var url = "hdgl_hdxq.do?method=savePlReply";
					ajaxSubFormWithFun("hdxxForm", url, function(data) {
						 if(data["message"]=="����ɹ���"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
									if (parent.window){
										if(frameElement.api){
											var api = frameElement.api,W = api.opener;
											//jQuery(W.document).find('#search_go').click();
											W.plgl(hdid);
											closeDialog();
										} else {
											//jQuery(parent.window.document).find('#search_go').click();
											jQuery(parent.window).plgl(hdid);
											iFClose();
										}
									}
								}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
					});
				}else{
					showAlert("����д�ظ����ݣ�");
					return false;
				}
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
			<input type="hidden" name="hdid" value="${plxx.hdid}" id="hdid" />
			<input type="hidden" name="hfplid" value="${plxx.plid}" />
			<input type="hidden" name="hfbj" value="1" />
			<input type="hidden" name="hfrid" value="${plxx.plrid}" />
			<input type="hidden" name="hfrxm" value="${plxx.plrxm}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								�����
							</th>
							<td colspan="3">
								${plxx.hdmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<logic:empty name="plxx" property="hfrxm">
									${plxx.plrxm}����
								</logic:empty>
								<logic:notEmpty name="plxx" property="hfrxm">
									${plxx.plrxm}�ظ�:${plxx.hfrxm}
								</logic:notEmpty>								
							</th>
							<td colspan="3">
								${plxx.plnr}
							</td>
						</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								�ظ�����
								<br /><font color="red">&lt;��1000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='plnr' style="width:98%" styleId="plnr" rows='14' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
			</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
										<button type="button" onclick="savePlReply();">
											����
										</button>
										<button type="button" onclick="iFClose();">
											�ر�
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

