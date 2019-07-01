<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function() {
			jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=${kqsqForm.sqid}&tt="
							+ new Date().getTime());
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zwzxKqsq" method="post" styleId="ZwzxKqsqForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 465px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font>查看</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${kqsqForm.xn}
							</td>
							<th width="20%">
								<span id="ccrq_span"></span>
							</th>
							<td width="30%">
								${kqsqForm.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								学期
							</th>
							<td width="30%">
								${kqsqForm.xqmc}
							</td>
							<th width="20%">
								<span id="cclx_span"></span>
							</th>
							<td width="30%">
								${kqsqForm.cclxmc}
							</td>
						</tr>
							<tr>
								<th width="20%">
									班级
								</th>
								<td width="30%">
									${kqsqForm.bjmc}
								</td>
								<th width="20%">
									应到人数
								</th>
								<td width="30%">
									${kqsqForm.ydrs}
								</td>
							</tr>
							<tr>
								<th width="20%">
									实到人数
								</th>
								<td width="30%">
									${kqsqForm.sdrs}
								</td>
								<logic:equal name="xxdm" value="12970">
								<th width="20%">
									未到人数
								</th>
								</logic:equal>
								<logic:notEqual name="xxdm" value="12970">
									<th width="20%">
										缺勤人数
									</th>
								</logic:notEqual>
								<td width="30%">
									${kqsqForm.qqrs}
								</td>
							</tr>
						<tr>
							<th width="20%" id="jlf_th">
								纪律分
							</th>
							<td width="30%" id="jlf_td">
								${kqsqForm.jlf}
							</td>
							<th width="20%">
								<span id="jlr_span"></span>
							</th>
							<td width="30%" id="jlr_td">
								${kqsqForm.jlrxm }
							</td>
						</tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									带班辅导员
								</th>
								<td id="dbfdy" colspan="3">
										${kqsqForm.dbfdy }
								</td>
							</tr>
						</logic:equal>

						<tr>
						    <th>
								备注
							</th>
							<td colspan="3">
								${kqsqForm.bz}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								除缺勤外的违纪人数
								<br />
								<div align="center">
									(如:吃饭,睡觉等...)
								</div>
							</th>
							<td align="left">
								${kqsqForm.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
					<thead>
						<tr>
						<logic:equal name="xxdm" value="12970">
							<th colspan="4">
								<span>未到学生信息</span>
							</th>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12970">
							<th colspan="4">
								<span>缺勤学生信息</span>
							</th>
						</logic:notEqual>
						</tr>
					</thead>
				 </table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
								<logic:notEqual name="xxdm" value="12970">
									<td width='15%'><font color="red">*</font>缺勤类型</td>
									<td width='15%'>旷课节数</td>
									<td width='35%'>缺勤备注</td>
								</logic:notEqual>
							<logic:equal name="xxdm" value="12970">
								<td width='35%'>未到原因</td>
							</logic:equal>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
						<logic:iterate id="i" name="qqxsList" indexId="index01">
						<tr>
						<td name="xhArr">${i.xh}</td>
						<td>${i.xm}</td>
							<logic:notEqual value="12970" name="xxdm">
								<td>${i.qqlxmc}</td>
								<td>${i.kkjs}</td>
							</logic:notEqual>
						<td title="${i.ylzd1}">${i.ylzd1str}</td>
						</tr>
						</logic:iterate>
						<logic:empty name="qqxsList">
											<tr>
												<td colspan="7" align="center">无缺勤学生！</td>
											</tr>
										</logic:empty>
									</tbody>
						</tbody>
				</table>
				<logic:notEqual value="无需审核" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
				
				</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

