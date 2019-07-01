<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		function saveFdgl(type) {
			var xspjjg = jQuery("#xspjjg").val();
			if(xspjjg == "" || xspjjg == null){
				alert("评价结果不能为空");
				return false;
			}
			var url = "zzyrxmglbfdgl.do?method=addBfdglpj&type="+type;
			ajaxSubFormWithFun("bfdglForm", url, function(data) {
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
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglbfdgl" method="post" styleId="bfdglForm" onsubmit="return false;">
			<html:hidden property="fdxxid"/>
			<input type="hidden" name="fdlx" value="1"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tr>
						<th width="50%">评价结果</th>
						<td width="50%">
							<logic:empty name="rs" property="xspjjg">
								<select id="xspjjg" name="xspjjg" style="width: 100px;">
									<option value="优">优</option>
									<option value="良">良</option>
									<option value="中">中</option>
									<option value="差">差</option>
								</select>
							</logic:empty>
							<logic:notEmpty name="rs" property="xspjjg">
								${rs.xspjjg }	
							</logic:notEmpty>
						</td>
					</tr>
				 </table>			
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<logic:empty name="rs" property="xspjjg">
									<button type="button" onclick="saveFdgl('save');">
										保存
									</button>
								</logic:empty>
									<button type="button" onclick="iFClose();">
										关闭
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

