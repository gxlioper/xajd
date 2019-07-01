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
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/xswsf/js/xswsfManage.js"></script>
		<script type="text/javascript">

		
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="xswsfForm" action="/gyglnew_xswsf_12688">
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2" style="height: 50px;">
								<font color="blue">&nbsp;&nbsp;&nbsp;提示：</font>
								<font color="black">请先提交要导出学期的检查日程！</font>
							</th>
						</tr>
					</thead>
					<tbody>
						<%--<tr>
							<th colspan="2" rowspan="2">
								<font color="blue">&nbsp;&nbsp;&nbsp;提示：</font>
								请先提交要导出学期的检查日程！
							</th>
						</tr>
						--%>
						<tr>
							<th width="40%">
								<span class="red">*</span>学年
							</th>
							<td width="60%">
								<html:select property="xn" styleId="xn" style="width:100px;">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>学期
							</th>
							<td>
								<html:select property="xq" styleId="xq" style="width:100px;">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
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
								</div>
								<div class="btn">
									<button type="button" onclick="saveXsmd();" id="buttonSave">
										生成
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
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