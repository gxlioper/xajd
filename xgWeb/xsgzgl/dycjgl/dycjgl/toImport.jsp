<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<% response.addHeader("X-XSS-Protection","0"); %>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/dycjgl/dycjgl/js/dycjgl.js"></script>
		<script type="text/javascript">
            jQuery(function(){
                if (jQuery("#message").val() != ""){
                    showAlert(jQuery("#message").val(),{},{"clkFun":function(){
                        if (parent.window){
                            var api = frameElement.api,W = api.opener;
                            jQuery(W.document).find('#search_go').click();

                            if (jQuery("#result").val() == "true"){
                                api.close();
                            }
                        }
                    }});
                }
            });
		</script>
	</head>
	<body>
		<html:form action="/dycjwh_dycjgl" method="post" styleId="DycjglForm" enctype="multipart/form-data" >
			<input type="hidden" id="message" name="message" value="${message }"/>
			<input type="hidden" id="result" name="result" value="${result }"/>
			<input type="hidden" id="bjdm" value="${bjdm}"/>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="12%">˵��</th>
							<td>
								��  �۲�ֵ�����ʹ��ϵͳ�ṩ��ģ��;<br/><br/>
								��  ���ϸ���ģ���ṩ�ĸ�ʽ��д�۲��;<br/><br/>
								�� �����۲�<u><font color="red">��������Ϊ��</font></u>;<br/><br/>
								�� ��������Ϊ<u><font color="red">���ָ�ʽ������󳤶�Ϊ10λ��</font></u>;<br/><br/>
								�� ʹ���������޸ĵ���ģ�棨<font color="red">�޸�ģ����ܵ����޷��������ķ�����׼ȷ��</font>��<br/><br/>
								
								<span class="bold">
									<a href="javascript:downloadExcl();" style="color:blue;">����ģ��</a>
								</span>
							</td>
						</tr>
						
						<tr>
							<th>����</th>
							<td>
								<input type="file" name="importFile" id="importFile" style="width:80%"/>
								<label class="btn_01" onclick="uploadDycj();">����</label>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

