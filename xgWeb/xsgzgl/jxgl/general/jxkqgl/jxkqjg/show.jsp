<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="jxkqjgForm" action="/jxkqjg">
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>考勤信息
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							军训名称
						</th>
						<td align="left">
							${kqjg.jxmc}
						</td>
						<th align="right">
							考勤类别
						</th>
						<td align="left">
								${kqjg.kqlbmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							考勤时间
						</th>
							<td align="left">
								${kqjg.kqsj}
							</td>
						<th align="right">
							考勤类型
						</th>
							<td align="left">
								${kqjg.kqlxmc}
							</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							考勤情况&nbsp;
						</th>
						<td colspan="3">
						${kqjg.kqqk}
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
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
