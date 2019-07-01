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
		<html:hidden property="guid" styleId="guid" />
		<html:hidden property="xh"  styleId="xh"/>
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生卫生分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td >
								${rs.xn}
							</td>
							<th>
								学期
							</th>
							<td >
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>
								录入人
							</th>
							<td >
								${lrrxm}
							</td>
							<th>
								录入时间
							</th>
							<td >
								${rs.lrsj}
							</td>
						</tr>
						<tr>
							<th>
								检查日程
							</th>
							<td colspan="3">
								${rs.jcrcxx}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>分数<br />
								
							</th>
							<td colspan="3">
								<html:text property="fs" styleId="fs" value="${rs.fs}" maxlength="5" onblur="checkInputNum(this);changeFs(this);"/>
								<span class="red">(实际分数将为输入分数的50%)</span>
							</td>
						</tr>
						<tr>
							<th>
								备注<br />
								<span class="red">(限500字)</span>	
							</th>
							<td colspan="3">
								<html:textarea property="bz" style="width:94%;height:80px"  styleId="bz" onblur="chLengs(this,500);" ></html:textarea>
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
									<button type="button" onclick="save();" id="buttonSave">
										保 存
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