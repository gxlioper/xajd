<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		function saveForm(url){	
			if (!checkNull("pj")) {
				 return false;
			 }
				var url = "rcsw_cdgl_cdjg.do?method=pj&type=save";
				ajaxSubFormWithFun("rcswCdjgForm", url, function(data) {
					if (data["message"] == "保存成功！") {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								if (parent.window) {
									refershParent();
								}
							}
						});
					} else {
						showAlert(data["message"]);
					}
				});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/rcsw_cdgl_cdjg" method="post" styleId="rcswCdjgForm" onsubmit="return false;">
		<html:hidden property="sqid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>讲座报告评价</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>评价</th>
							<td>
								<html:select property="pj" style="width:230px" styleId="pj">
									<html:option value=""></html:option>
									<html:option value="非常满意">非常满意</html:option>
									<html:option value="满意">满意</html:option>
									<html:option value="不满意">不满意</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>评价备注<br><font color="red">限制字数（200）</font><br/></th>
							<td colspan="3">
								<html:textarea property="pjbz" styleId="pjbz" onblur="chLengs(this,200);"
								   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
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