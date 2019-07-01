<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khsq/khsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhsq" method="post" styleId="GzkhKhsqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>填写</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>用人单位</th>
							<td>
							
								<html:select property="yrdw" styleId="yrdw" onclick="checkXh();" onchange="getGwxx();" style="width:200px">
										<html:options collection="yrdwList" property="bmdm" labelProperty="bmmc" />
									</html:select>
							</td>
							<th><font color="red">*</font>工时</th>
							<td>
								<html:text property="gs" styleId="gs" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"
                                ></html:text>（小时）
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>岗位</th>
							<td>
								<html:select  property="gwdm" styleId="gwdm" onclick="checkXh();" style="width:200px">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th>工作日期</th>
							<td colspan="3">
								<input name="gzrq" id="gzrq" value="${rq}" readonly onfocus="showCalendar('gzrq','yyyy-MM-dd');"/>
							</td>
							<%--<th><font color="red">*</font>工作地点</th>
							<td>
								<html:text property="gzdd" styleId="gzdd" maxlength="100" ></html:text>
								
							</td>--%>
						</tr>
						<tr>
							<th><font color="red">*</font>工作时间段</th>
							<td colspan="3">
								<html:text  property="gzkssj" styleId="gzkssj" onfocus="showCalendar('gzkssj','HH:mm');" />
								-
								<html:text  property="gzjssj" styleId="gzjssj" onfocus="showCalendar('gzjssj','HH:mm');" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 40px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								<span style="font-size: larger; color: orchid">温情提示：</span>
								<br>
								<span>1.学生每天完成工作后必须当天在系统中维护工作的时长，若未及时维护，请联系部门管理员老师代维护。</span>
								<br>
								<span>2.每天工作时长<=8小时，每月工作时长<=40小时，请合理安排工作时间。</span>
							</div>
						</td>
					</tr>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:equal value="stu" name="userType">
									<button type="button" onclick="saveKhsq('save');">
										保存草稿
									</button>
									</logic:equal>
									<button type="button" onclick="saveKhsq('submit');">
										提交申请
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