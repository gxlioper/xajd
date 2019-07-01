<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwmark/js/rcxwmark.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">

		</script>
		<style type = "text/css">
		
		</style>
	</head>
	<body>
		<html:form action="/rcsw_rcxwmark" method="post" styleId="RcxwmarkForm">
			<html:hidden property="id" styleId="id" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>专业奖学金、新生奖学金设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>奖项设置</th>
							<td colspan="3">
								<html:radio property="jxdm" value="1" styleId="zyjxj"/><label for="zyjxj">专业奖学金</label>
								<html:radio property="jxdm" value="2" styleId="xsjxj"/><label for="xsjxj">新生奖学金</label>
							</td>
						</tr>
						<tr>
							<th>评奖年度</th>
							<td colspan="3">
								<html:select property="pjxn" styleId="pjxn">
									<html:options collection="xnndList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>备注
								<font class="red"><限1000字></font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
								   onblur="checkLenN('备注',this,1000)"
								   style="width:99%;" rows="6">
								 </html:textarea>
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
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveSzjg('update')">
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