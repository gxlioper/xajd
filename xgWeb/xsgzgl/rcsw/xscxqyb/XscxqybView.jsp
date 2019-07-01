<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xscxqyb/js/xscxqyb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/rcsw_xscxqybgl" method="post" styleId="xscxqybForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>查看月报信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${xxck.xn}
							</td>
							</td>
							<th>学期</th>
							<td>
								${xxck.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								填写人
							</th>
							<td width="30%">
								${xxck.xm}
							</td>
							</td>
							<th>月份</th>
							<td>
								${xxck.yf}
							</td>
						</tr>
						<tr>
					    	<th>本月工作开展情况</th>
					    	<td colspan="3">
								${xxck.bygzkzqk}
							</td>
						</tr>
						<tr>							
							<th >学生关注热点</th>
							<td colspan="3">
								${xxck.xsgzrd}
							</td>
						</tr>
						<tr>
							<th  >学生思想动态</th>
					    	<td   colspan="3">
								${xxck.xssxdt}
						    </td>
						 </tr>
						 <tr>
						    <th  >学生诉求及工作建议</th>
					    	<td  colspan="3">
								${xxck.xstsjgzjy}
						    </td>
						 </tr>			
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
				  <div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

