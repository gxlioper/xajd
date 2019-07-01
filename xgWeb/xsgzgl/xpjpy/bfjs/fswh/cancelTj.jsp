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

		//保存
		function saveQxtj(){
			if("" == jQuery.trim(jQuery("#qxyy").val())){
				showAlert("取消原因不能为空！");
				return  false;
			}
			
			var url = "xpjpyBfjsFswh.do?method=cancelTj&doType=update";
			ajaxSubFormWithFun("BfjsFswhModel",url,function(data){
				showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
	</script>		
		
	<body>
		<html:form action="/xpjpyBfjsFswh" method="post" styleId="BfjsFswhModel" onsubmit="return false;">
		<html:hidden property="id" styleId="id" />
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="1">
								<div class="btn">
									<button type="button" onclick="saveQxtj();">
										确定
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
										取消
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="75%">
								<center><span>请说明取消提交原因</span><font color="red">&lt;限50字&gt;</font></center>
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
