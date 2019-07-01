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
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/xsqj_jscx" method="post" styleId="XsqjcxForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>请假信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<% String sjly=((HashMap<String,String>)request.getAttribute("ckxx")).get("sjly");//当前选中的楼栋代码
						String qjts = ((HashMap<String,String>)request.getAttribute("ckxx")).get("qjts");
					 %>
						<%if("new".equals(sjly)){ %>
						<tr>
							<th>请假类型</th>
							<td >
								${ckxx.qjlxmc}
							</td>
							<th>请假天数</th>
							<td>
								${ckxx.qjts}
							</td>
						</tr>
						<tr>
							<th>请假天数类型</th>
							<td>
								${ckxx.qjtslx}
							</td>
							<th>是否补假</th>
							<td >
								${ckxx.sfbj}
							</td>
						</tr>
						<tr>
							<th>寝室楼</th>
							<td>${ckxx.qsl}</td>
							<th>寝室号</th>
							<td>
								${ckxx.qsh}
							</td>
						</tr>
						<tr>
							<th>联系方式</th>
							<td>
								${ckxx.lxfs}
							</td>
							<th>家长电话</th>
							<td>
								${ckxx.jzlxfs}
							</td>
						</tr>
						<tr>
							<th>前往地点</th>
							<td colspan="3">${ckxx.qwdd}</td>
						</tr>
						<tr>
							<th>请假理由</th>
							<td colspan="3">${ckxx.qjly}</td>
						</tr>
						<tr>
						<th>请假开始时间</th>
							<td>
								${ckxx.qjkssj}
							</td>
							<th>请假结束时间</th>
							<td>
								${ckxx.qjjssj}
							</td>
						</tr>
						<tr>
							<th>请假节次</th>
							<td colspan="3">${ckxx.qjjc}</td>
						</tr>
						<tr>
						<th>是否归寝</th>
							<td>
								${ckxx.sfgq}
							</td>
							<th>不归寝说明</th>
							<td>
								${ckxx.bgqsm}
							</td>
						</tr>
						<%}else if("gj".equals(sjly)){ %>
						<tr>
							<th>请假类型</th>
							<td >
								${ckxx.qjlxmc}
							</td>
							<th>主办方负责人</th>
							<td>
								${ckxx.zbffzr}
							</td>
						</tr>
						<tr>
						<th>请假开始时间</th>
							<td>
								${ckxx.qjkssj}
							</td>
							<th>请假结束时间</th>
							<td>
								${ckxx.qjjssj}
							</td>
						</tr>
						<tr>
							<th>请假节次</th>
							<td colspan="3">${ckxx.qjjc}</td>
						</tr>
						<tr>
						<th>是否归寝</th>
							<td>
								${ckxx.sfgq}
							</td>
							<th>不归寝时间</th>
							<td>
								${ckxx.bgqsj}
							</td>
						</tr>
						<tr>
							<th>请假理由</th>
							<td colspan="3">${ckxx.qjly}</td>
						</tr>
						<tr>
							<th>请假节次</th>
							<td colspan="3">${ckxx.qjjc}</td>
						</tr>
							<tr>
							<th>备注</th>
							<td colspan="3">
								${ckxx.bz}
							</td>
						</tr>
							<%} %>
						</table>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>审批列表</span>
								</th>
							</tr>
						</thead>
						<tr align='center'>
							<td width="20%">审批级别</td>
							<td width="18%">开始时间</td>
							<td width="18%">结束时间</td>
							<td width="10%">执行人</td>
							<td width="24%">审批意见</td>
							<td width="10%">审批状态</td>
						</tr>
						<logic:iterate id="i" name="splslist">
							<tr align='center'>
								<td>${i.taskname}</td>
								<td>${i.starttime}</td>
								<td>${i.endtime}</td>
								<td>${i.exefullname}</td>
								<td>${i.opinion}</td>
								<td>${i.checkstatus}</td>
							</tr>
						</logic:iterate>
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