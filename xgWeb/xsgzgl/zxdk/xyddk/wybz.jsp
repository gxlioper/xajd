<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveWybz(url){
				
				if (jQuery("#bz").val() == ""){
					showAlert("请将必填项填写完整。");
					return false;
				}
				
				ajaxSubFormWithFun("wyglForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkWygl" method="post" styleId="wyglForm">
			<html:hidden property="htbh"/>
		
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:180px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>违约备注</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>合同编号</th>
							<td>
								${zxdkWyxxForm.htbh }
							</td>
						</tr>
						<tr>
							<th>违约详情</th>
							<td>
								${zxdkWyxxForm.wyxq }
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>备注
								<br/><font color="red">(限输入400字)</font>
							</th>
							<td>
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveWybz('zxdkWygl.do?method=update');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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