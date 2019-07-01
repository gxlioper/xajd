<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/dkbc/tjsz/js/tjszTjzDiv.js"></script>
		<style>
			#tbody tr{ height:40px; }
		</style>
	</head>
	<body>
		<html:form action="/dkbc_tjsz" method="post" styleId="form1">
			<input type="hidden" id="xnVal" name="xnVal" value="${xnVal}"/>
				<table width="100%" border="0" class="dateline">
					<tbody id="tbody">
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">							
							<div class="bz" align="left" id="qxDiv" >
								<label><input type="checkbox"  name="grid_selectAll">全选</label>
							</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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

