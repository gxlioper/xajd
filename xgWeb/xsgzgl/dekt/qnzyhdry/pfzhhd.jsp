<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function tj(){				
				ajaxSubFormWithFun("zyhdryForm", 'zyhdry.do?method=pfbc', function(data) {
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
	</head>
	<body >
		<html:form action="/zyhdry" method="post" styleId="zyhdryForm" onsubmit="return false;">
			<html:hidden property="id" style="id"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>评价</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>评价结果
							</th>
							<td width="34%" colspan="3">
								<html:select property="pjjg" styleId="pjjg">
									<option value="满意">满意</option>
									<option value="一般">一般</option>
									<option value="不满意">不满意</option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注
							</th>
							<td width="34%" colspan="3">
								<html:textarea property="pjbz" styleId="pjbz" rows="5" onblur="checkLen(this,500);" style="width:98%;">
									
								</html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="tj();return false;">
										保存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
										关 闭
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

