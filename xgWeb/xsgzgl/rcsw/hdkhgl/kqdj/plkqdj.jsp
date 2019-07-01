<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hdkhgl/kqdj/js/kqdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
				})
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/hdkhgl_hddj" method="post" styleId="HdkhglForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>考勤登记</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%"><font color="red">*</font>是否参加</th>
							<td width="75%">
								<html:select styleId="sfcj" onchange="kqdjOnchange();" property="sfcj" style="width:100px;">
									<html:options collection="kqdj" property="dm"  labelProperty="mc" ></html:options>
								</html:select>
							</td>
						</tr>
						<tr id="qqblock" style="display: none">
							<th width="25%" ><font color="red">*</font>缺勤原因</th>
							<td width="75%" >
								<html:textarea property="qqyy" styleId="qqyy"  
								   onkeyup="checkzsonsubmit(this);" 
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
								    <button type="button" onclick="savePlKqdj();">
										保存
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