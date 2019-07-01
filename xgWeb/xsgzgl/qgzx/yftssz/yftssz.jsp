<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/yftssz/js/yftssz.js"></script>
		<script type="text/javascript">

			function selTea(){
				showDialog("选择管理员", 770, 520, "stglStsq.do?method=getTea")	
			}
			
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_yftssz" method="post" styleId="form">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>勤工助学月份天数设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_nd">
						<tr>
							<th width="16%">
								学年
							</th>
							<td colspan="3">
								<html:select property="xn" styleId="xn" style="width:100px" onchange="changeyf()">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr><th colspan="4">&nbsp;</th></tr>
					</tbody>
					<tbody id="tbody_yf">
						<tr>
							<th width="16%">
								9月
							</th>
							<td width="34%">
								<input type="text" id="yf-9" name="yf-9" onblur="checkDays(this); return false;" value="${arr[8]}"/>
							</td>
							<th width="16%">
								3月
							</th>
							<td width="34%">
								<input type="text" id="yf-3" name="yf-3" onblur="checkDays(this); return false;" value="${arr[2]}"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								10月
							</th>
							<td width="34%">
								<input type="text" id="yf-10" name="yf-10" onblur="checkDays(this); return false;" value="${arr[9]}"/>
							</td>
							<th width="16%">
								4月
							</th>
							<td width="34%">
								<input type="text" id="yf-4" name="yf-4" onblur="checkDays(this); return false;" value="${arr[3]}"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								11月
							</th>
							<td width="34%">
								<input type="text" id="yf-11" name="yf-11" onblur="checkDays(this); return false;" value="${arr[10]}"/>
							</td>
							<th width="16%">
								5月
							</th>
							<td width="34%">
								<input type="text" id="yf-5" name="yf-5" onblur="checkDays(this); return false;" value="${arr[4]}"/>
							</td>
							
						</tr>
						<tr>
							<th width="16%">
								12月
							</th>
							<td width="34%">
								<input type="text" id="yf-12" name="yf-12" onblur="checkDays(this); return false;" value="${arr[11]}"/>
							</td>
							<th width="16%">
								6月
							</th>
							<td width="34%">
								<input type="text" id="yf-6" name="yf-6" onblur="checkDays(this); return false;" value="${arr[5]}"/>
							</td>
						
						</tr>
						<tr>
							<th width="16%">
								1月
							</th>
							<td width="34%">
								<input type="text" id="yf-1" name="yf-1" onblur="checkDays(this); return false;"  value="${arr[0]}"/>
							</td>
							<th width="16%">
								7月
							</th>
							<td width="34%">
								<input type="text" id="yf-7" name="yf-7" onblur="checkDays(this); return false;" value="${arr[6]}"/>
							</td>
							
						</tr>
						<tr>
							<th width="16%">
								2月
							</th>
							<td width="34%">
								<input type="text" id="yf-2" name="yf-2" onblur="checkDays(this); return false;" value="${arr[1]}"/>
							</td>
							<th width="16%">
								8月
							</th>
							<td width="34%">
								<input type="text" id="yf-8" name="yf-8" onblur="checkDays(this); return false;" value="${arr[7]}"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; ">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="bcYftssz()">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

