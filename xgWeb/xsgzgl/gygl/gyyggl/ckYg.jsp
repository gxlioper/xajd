<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="hqgl/rcgl/js/lfdj.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>

	</head>
	<body style="width:100%">
		<html:form action="/gyglnew_gyygxxgl" method="post" styleId="gyygxxglForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>查看外聘人员</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								编号
							</th>
							<td width="30%">
								${yhxx.ygbh}
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${yhxx.xm}
							</td>
							</tr>
							<tr>
							<th width="20%">
								性别
							</th>
							<td  width="30%">
							${yhxx.xbmc}
							</td>
							<th width="20%">
								年龄
							</th>
							<td width="30%">
								${yhxx.nl}
							</td>
							</tr>
							<tr>
							<th width="20%">
								聘前职业状况
							</th>
							<td width="30%">
								${yhxx.pqzyzk}
							</td>
							<th width="20%">
								现岗
							</th>
							<td width="30%">
								${yhxx.zwmc}
							</td>
							</tr>
							<tr>
							<th width="20%">
								聘用日期
							</th>
							<td width="30%">
								${yhxx.pyrq}
							</td>
							<th width="20%">
								身份证号
							</th>
							<td width="30%">
								${yhxx.sfzh}
							</td>
							</tr>
							<tr>
							<th width="20%">
								联系电话
							</th>
							<td width="30%">
								${yhxx.lxdh}
							</td>
							<th width="20%">
								工资标准
							</th>
							<td width="30%">
								${yhxx.gzbz}
							</td>
							</tr>
							<tr>
								<th width="20%">
									是否在岗
								</th>
								<td width="30%" colspan="3">
									${yhxx.zgztmc }
								</td>
							</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

