<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function save(){
				var url = "qjgz.do?method=saveOpenZt";
				ajaxSubFormWithFun("qjgzForm", url, function(data) {
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/qjgz" method="post" styleId="qjgzForm">
		<html:hidden property="qjgzid" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>开关状态</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>请假类型</th>
							<td>
								${qjlxmc}
							</td>
						</tr>
						<tr>
							<th>请假区间</th>
							<td>
								${qjgzForm.kssj}天~${qjgzForm.jssj}天
							</td>
						</tr>
						<tr>
							<th>审批流程</th>
							<td>
								${shlcmc}
							</td>
						</tr>
						<tr>
							<th>开关</th>
							<td>
								<html:radio property="open" value="1">开启</html:radio>
								<html:radio property="open" value="0">关闭</html:radio>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="save();">
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