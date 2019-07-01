<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rwgl/rwtw/js/rwxfPlbc.js"></script>
	</head>
	<body>
		<html:form action="/rwgl_rwxfbcgl" method="post" styleId="rwxfbcglForm">
		<input type="hidden" id="guid" name="guid" value="${mkxxForm.guid }"/>
		<input type="hidden" id="xh" name="xh" value="${mkxxForm.xh }"/>
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>入伍学费补偿信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:155px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>学费补偿金额</th>
							<td>
								<html:text property="xfbcje" styleId="xfbcje" maxlength="8" onkeyup="checkInputNum(this);"/>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>学费补偿时间</th>
							<td>
								<html:text property="xfbcsj" styleId="xfbcsj" maxlength="10" onclick="return showCalendar('xfbcsj','y-mm-dd');" readonly="true"/>
							</td>
							<th></th>
							<td>
							</td>
					    </tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">(限制在500字内)</font>
							</th>
							<td colspan="4">
								<html:textarea property='bz' style="width:95%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			     		 </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="savePlbc();">
										提交
									</button>
									<button type="button" onclick="iFClose();">
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
