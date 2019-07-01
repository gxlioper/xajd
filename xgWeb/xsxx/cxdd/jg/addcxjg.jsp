<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/cxdd/jg/js/cxjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/cxdd_pyjg" method="post" styleId="CxddJgForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>评语信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>学年</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:130px">
									<html:option value=""></html:option>
									<html:options collection="xnlist" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><font color="red">*</font>学期</th>
							<td>
								<html:select property="xq" styleId="xq" style="width:130px">
									<html:option value=""></html:option>
									<html:options collection="xqlist" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>评价</th>
							<td colspan="3">
								<html:select property="pj" styleId="pj" style="width:130px">
									<html:option value=""></html:option>
									<html:options collection="cxdjlist" labelProperty="cxdjmc" property="cxdjdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>评语
								</br><font color="red"><限80-120字></font></th>
							<td colspan="3">
								<html:textarea property="py" styleId="py" 
								   onkeyup="" 
								   style="width:99%;" rows="4"></html:textarea>
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
									<button type="button" onclick="saveCxjg('save');">
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