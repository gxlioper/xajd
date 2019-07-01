<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/xsxx/js/fbglxsxx.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/fbglxsxx">
			<div
				style='width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>新生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="15%">
								考生号
							</th>
							<td align="left" width="35%">
								${data.ksh}
							</td>
							<th align="right" width="15%">
								学号
							</th>
							<td align="left" width="35%">
								${data.xh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								姓名
							</th>
							<td align="left">
								${data.xm}
							</td>
							<th align="right">
								性别
							</th>
							<td align="left">
								${data.xb}
							</td>
						</tr>
						<tr>
							<th align="right" >
								<bean:message key="lable.xb" />
							</th>
							<td align="left">
								${data.xy}
							</td>
							<th align="right">
								专业
							</th>
							<td align="left">
								${data.zymc}
							</td>
						</tr>
						<tr>
							<th align="right" >
								班级
							</th>
							<td colspan="3">
								${data.bjmc}
							</td>
							
						</tr>
						<tr>
							<th align="right" >
								年级
							</th>
							<td align="left">
								${data.nj}
							</td>
							<th align="right" >
								宗教信仰
							</th>
							<td >
								${data.zjmc}
							</td>
						</tr>
						<tr>
							<th align="right" >
								投档成绩
							</th>
							<td align="left">
								${data.tdcj}
							</td>
							<th align="right">
								学制
							</th>
							<td align="left">
								${data.xz}
							</td>
						</tr>
						<tr>
							<th align="right" >
								生源地
							</th>
							<td align="left">
								${data.sydmc}
							</td>
							<th align="right">
								层次
							</th>
							<td align="left">
								${data.pyccmc}
							</td>
						</tr>
						<tr>
							<th align="right" >
								是否在校
							</th>
							<td align="left">
								${data.sfzx}
							</td>
							<th align="right">
								出生日期
							</th>
							<td align="left">
								${data.csrq}
							</td>
						</tr>
						<tr>
							<th align="right" >
								民族
							</th>
							<td align="left">
								${data.mzmc}
							</td>
							<th align="right">
								政治面貌
							</th>
							<td align="left">
								${data.zzmmmc}
							</td>
						</tr>
						<tr>
							<th align="right" >
							    身份证号
							</th>
							<td align="left">
								${data.sfzh}
							</td>
							<th align="right">
								入学时间
							</th>
							<td align="left">
								${data.rxrq}
							</td>
						</tr>
						<tr>
							<th align="right" >
								籍贯
							</th>
							<td align="left">
								${data.jgmc}
							</td>
							<th align="right">
								户口所在地
							</th>
							<td align="left">
								${data.hkszdmc}
							</td>
						</tr>
						<tr>
							<th align="right" >
								联系电话
							</th>
							<td align="left">
								${data.sjhm}
							</td>
							<th align="right">
								QQ号码
							</th>
							<td align="left">
								${data.qqhm}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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
