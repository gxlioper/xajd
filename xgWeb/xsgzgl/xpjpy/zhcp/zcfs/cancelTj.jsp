<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	
	<script type="text/javascript">

		//����
		function saveQxtj(){
			if("" == jQuery.trim(jQuery("#qxyy").val())){
				showAlert("ȡ��ԭ����Ϊ�գ�");
				return  false;
			}
			var url = "";
			if("${szyf}"=='1'){
				url = "xpj_zcfs.do?method=cancelTjofYf&doType=update";
			}else{
			    url = "xpj_zcfs.do?method=cancelTj&doType=update";
			}
			ajaxSubFormWithFun("zcfsForm",url,function(data){
				showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
	</script>		
		
	<body>
		<html:form action="/xpj_zcfs" method="post" styleId="zcfsForm" onsubmit="return false;">
		<html:hidden property="id" styleId="id" />
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="1">
								<div class="btn">
									<button type="button" onclick="saveQxtj();">
										ȷ��
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										ȡ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="75%">
								<center><span>��˵��ȡ���ύԭ��</span><font color="red">&lt;��50��&gt;</font></center>
							</th>
						</tr>
						<tr>
							<td >
								<textarea name='qxyy' property='qxyy' id="qxyy" style="word-break:break-all;width:99%;height:155px;"
										onblur="checkLen(this,50);"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
