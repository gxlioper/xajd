<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>

		<script type="text/javascript">
			function saveSq(){
                ajaxSubFormWithFun("demoForm","qgzx_xsgwsh.do?method=pljg&type=submit",function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
                });
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="qgzx_xsgwsh.do?method=pljg" method="post" styleId="demoForm">
			<input type="hidden" name="jgData" value="${jgData}"/>
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>解雇信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>解雇原因
								<br />
								<font color="red">限1500字</font>
							</th>
							<td  colspan="3">
								<textarea rows="5" style="width: 100%;margin-top: 5px" onblur="checkLen(this,1500)" id="sqly" name="sqly"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
			<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<%--<button type="button" onclick="saveSq();">
										保存草稿
									</button>--%>
									<button type="button" onclick="saveSq();">
										提交审核
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

