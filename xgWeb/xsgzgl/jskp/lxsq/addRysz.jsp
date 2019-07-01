<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxsq/js/comm.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				initData();
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jskp_lxsq" method="post" styleId="LxsqForm">
			<input type="hidden" value="xg" name="ryflag" id="ryflag"/>
			<input type="hidden" name="sqid" id="sqid" value="${sqid}" />
		    <input type="hidden" name="shzt" id="shzt" value="${shzt}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th><span class="red">*</span>参与人
							</th>
							<td colspan="3">
								<textarea rows="8" style="width:99%;" name="cyry" id="cyry"></textarea>
							</td>
						</tr>
						<tr>
							<th>可参与人员</th>
							<td colspan="3">
								<span id="kcyry"></span>
								<div style="display:none" id="kcyryspan">
								
								</div>
							</td>
						</tr>
						<tr>
							<th>验证未通过人员</th>
							<td colspan="3">
								<span id="wtgry" class="red"></span>
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
									<button type="button" onclick="saveRysz();">
										保 存
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