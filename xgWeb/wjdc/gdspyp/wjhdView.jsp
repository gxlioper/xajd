<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		
	</script>

	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		
		textarea{
			width:90%;
			word-break:break-all;
		}
	</style>

</head>
<body>
	<html:form action="/msxldc" method="post">
		<table class="tbstyle" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right" width="20%">学号</td>
					<td width="30%">
						${rs.xh }
					</td>
					<th align="right" width="20%">姓名</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<td align="right"><bean:message key="lable.xb" /></td>
					<td>
						${rs.xymc }
					</td>
					<td align="right">专业</td>
					<td>
						${rs.zymc }
					</td>
				</tr>
				<tr>
					<td align="right">班级</td>
					<td>
						${rs.bjmc }
					</td>
					<td align="right">年级</td>
					<td>
						${rs.nj }
					</td>
				</tr>
				<tr>
					<td align="right">总分</td>
					<td>
						${rs.zf }
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">评议内容</td>
					<td colspan="3">
						${rs.pynr }
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<div class="border_01 formbox">
			<!--带有滚动条表单 标题start-->
			<!--带有滚动条表单 标题end-->
			<div style="height: 350px;overflow: auto">
				<table width="100%" class="tbstyle tablenowrap">
					<thead>
						<tr>
							<td>
								问卷试题
							</td>
							<td>
								最符合选项
							</td>
							<td>
								最不符合选项
							</td>
							<td>
								分数
							</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="hdInfoMap" name="hdInfoList">
							<tr>
								<td width="70%">
									A. ${hdInfoMap.xxa }<br/>
									B. ${hdInfoMap.xxb }<br/>
									C. ${hdInfoMap.xxc }
								</td>
								<td width="12%">
									${hdInfoMap.zfh }
								</td>
								<td width="12%">${hdInfoMap.zbf }</td>
								<td width="6%">
									${hdInfoMap.fs }
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onclick="window.close();return false;">
				关闭
			</button>
		</div>
	</html:form>
</body>
</html>
