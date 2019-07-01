<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/zjsy/js/zjsykq.js"></script>
		<script type="text/javascript">
		</script>
	</head>		
	<body>	
		<html:form action="/zjsy_kqgl" method="post" styleId="ZjsyKqForm" onsubmit="return false;">
		<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
							<span>考勤信息初始化</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年学期</th>
							<td>
								<html:select property="xn" styleId="xn" value="${dqxn}">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<html:select property="xq" styleId="xq" value="${dqxq}">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>	
							</td>
						</tr>
						<tr>
						  <th><span class="red">*</span>月份</th>
							<td>
								<html:select property="yf" styleId="yf" value="${dqyf}">
									<html:options collection="yfList" property="yf" labelProperty="yf" />
								</html:select>&nbsp;月
							</td>
					    </tr>
					    <tr>
					        <th><span class="red">*</span>周次</th>
							<td>
								第
								<html:text property="zc" styleId="zc" style="width:20px;" maxlength="2" styleClass="text_nor" onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
								周
							</td>
						</tr>
						  <tr>
					        <th>提示信息</th>
							<td>
								<span><font color="red">本操作会把在校的班级初始化到考勤信息</font></span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('cshKqdj','save');">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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