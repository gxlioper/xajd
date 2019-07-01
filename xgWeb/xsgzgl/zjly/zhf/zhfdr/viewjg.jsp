<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/zhfdr/js/zhfdr.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/comm/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/zjly_zhfdr" method="post" styleId="ZhfDrForm">
			<html:hidden property="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>综合素质分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>模块</th>
							<td>
								${jg.xmmkmc}
							</td>
							<th><font color="red">*</font>计分项目</th>
							<td>
								${jg.jfxmmc}
							</td>
						</tr>
						<tr>
							<th>考核要点</th>
							<td>
								${jg.khyd}
							</td>
							<th>得分</th>
							<td>
								${jg.fs}
							</td>
						</tr>
						<tr>
							<th>事项说明</th>
							<td >
								${jg.sxsm}
							</td>
								<th>参与获得时间</th>
							<td>
								${jg.cysj}
							</td>
						</tr>
						<tr>
							<th>
								录入人
							</th>
							<td>
									${jg.lrrname}
							</td>
							<th>
								    录入时间
							</th>
							<td>
								${jg.lrsj}
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
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="savejg('update');">
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