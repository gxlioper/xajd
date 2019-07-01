<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rwdj/js/rwdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form"
			action="/rwdj.do?method=add&type=save">
			<html:hidden property="xh" />
			<html:hidden property="rwdjid" />
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="" border="0" class="formlist">
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
								<span>入伍信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								入伍途径
							</th>
							<td colspan="3">
								${data.rwtjmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								入伍时间
							</th>
							<td>
								${data.rwsj}
							</td>
							<th width="20%">
								学业情况
							</th>
							<td>
								${data.xyqk}
							</td>
						</tr>
						<tr>
							<th width="20%">
								有无签入伍协议
							</th>
							<td>
								${data.ywqrwxy}
							</td>
							<th width="20%">
								不及格门数
							</th>
							<td>
								${data.bjgms}
							</td>
						</tr>
						<tr>
							<th width="20%">
								婚姻状况
							</th>
							<td>
								${data.hyzk}
							</td>
							<th width="20%">
								从业类别
							</th>
							<td>
								${data.cylb}
							</td>
						</tr>
						<tr>
							<th width="20%">
								户籍类别
							</th>
							<td>
								${data.hjlb}
							</td>
							<th width="20%">
								部队联系方式
							</th>
							<td>
								${data.bdlxfs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								父亲姓名
							</th>
							<td>
								${data.fqxm}
							</td>
							<th width="20%">
								父亲手机
							</th>
							<td>
								${data.fqsj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								母亲姓名
							</th>
							<td>
								${data.mqxm}
							</td>
							<th width="20%">
								母亲手机
							</th>
							<td>
								${data.mqsj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								其他联系人
							</th>
							<td>
								${data.qtlxr}
							</td>
							<th width="20%">
								其他联系人方式
							</th>
							<td>
								${data.qtlxrfs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								左眼视力
							</th>
							<td>
								${data.zysl}
							</td>
							<th width="20%">
								右眼视力
							</th>
							<td>
								${data.yysl}
							</td>
						</tr>
						<tr>
							<th width="20%">
								服役部队
							</th>
							<td>
								${data.fybd}
							</td>
							<th width="20%">
								部队地址
							</th>
							<td>
								${data.bddz}
							</td>
						</tr>
						<tr>
							<th width="20%">
								优秀士兵
							</th>
							<td>
								${data.yxsb}
							</td>
							<th width="20%">
								复原时间
							</th>
							<td>
								${data.fysj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								嘉奖
							</th>
							<td colspan="3">	
									${data.jj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								立功
							</th>
							<td colspan="3">
								${data.lg}
							</td>
						</tr>
						<tr>
							<th width="20%">
								毕业时间
							</th>
							<td>
								${data.bysj}
							</td>
							<th width="20%">
								专接本时间
							</th>
							<td>
								${data.zjbsj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								专接本后就读学院
							</th>
							<td>
								${data.zjbhjdxy}
							</td>
							<th width="20%">
								专接本后专业
							</th>
							<td>
								${data.zjbhzy}
							</td>
						</tr>
						<tr>
							<th width="20%">
								专接本后学号
							</th>
							<td>
								${data.zjbhxh}
							</td>
							<th width="20%">
								专接本后毕业时间
							</th>
							<td>
								${data.zjbhbysj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								银行卡号
							</th>
							<td>
								${data.bjyhkh}
							</td>
							<th width="20%">
								银行卡名称
							</th>
							<td>
								${data.yhkmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								银行卡地址
							</th>
							<td>
								${data.yhkdz}
							</td>
							<th width="20%">
								入伍后学费补偿
							</th>
							<td>
								${data.rwhxfbc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								退役后学费资助
							</th>
							<td>
								${data.tyhxfzz}
							</td>
							<th width="20%">
								就业后单位
							</th>
							<td>
								${data.jyhdw}
							</td>
						</tr>
						<tr>
							<th width="20%">
								公务员
							</th>
							<td>
								${data.gwy}
							</td>
							<th width="20%">
								事业编
							</th>
							<td>
								${data.syb}
							</td>
						</tr>
						<tr>
							<th width="20%">
								国企
							</th>
							<td>
								${data.gq}
							</td>
							<th width="20%">
								非公经济
							</th>
							<td>
								${data.fgjj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								备注
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
					</tbody>
					</div>
					<div>
						<table width="100%" border="0" class="formlist"
							style="position: fixed; _position: absolute; bottom: 0;">
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
											<button type="button" onclick="iFClose();" id="buttonClose">
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
