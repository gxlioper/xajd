<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">

		</script>
	</head>
	<body>
		<!--数据表单start-->
		<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist">
				<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>楼栋信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th align="right" width="16%">
						楼栋名称
					</th>
					<td align="left" width="34%" nowrap="nowrap">
						${rs.ldmc }
					</td>
					<th width="16%">
						楼栋性别
					</th>
					<td width="34%" id="ldxb">
						${rs.ldxb }
					</td>
				</tr>
				<tr>
					<th>楼栋层数</th>
					<td id="ldcs">
						${rs.ldcs }
					</td>
					<th>楼栋起始层</th>
					<td id="qsch">
						${rs.qsch }
					</td>
				</tr>
				<tr>
					<th>是否含0层</th>
					<td>${rs.sfhlc }</td>
					<th></th>
					<td></td>
				</tr>
				</tbody>
				
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>寝室信息</span>
						</th>
					</tr>
				</thead>
				
				<tbody>
				<tr>
					<th>层号</th>
					<td>
						${rs.ch }
					</td>
					<th>
						寝室号				
					</th>
					<td>
						${rs.qsh }
					</td>
				</tr>
				<tr>
					<th>寝室性别</th>
					<td>
						${rs.qsxb }
					</td>
					<th>床位数</th>
					<td>
						${rs.cws }
					</td>		
				</tr>
				<tr>
					<th>收费标准</th>
					<td>
						${rs.sfbz }
					</td>
					<th>寝室电话</th>
					<td>
						${rs.qsdh }
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<th>有无空调</th>
				<td>
					<div>
						${rs.ywkt }
					</div>
				</td>
				<tr>
					<th>
						备注
					</th>
					<td colspan="3" style="word-break:break-all;width:100%">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='4' value="${rs.bz}" readonly="true"/>
					</td>
				</tr>
			</tbody>
			</table>
		<!--数据表单end-->
			<h3 class="datetitle_01">
				<span>床位信息</span>
			</h3>
			<table class="formlist">
					<thead>
						<tr>
							<th>
								床位号
							</th>
							<th>
								学号
							</th>
							<th>
								姓名
							</th>
							<th>
								年级
							</th>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<th>
								专业
							</th>
							<th>
								班级
							</th>
							<th>
								床位所属年级
							</th>
							<th>
								床位所属<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<th>
								是否保留
							</th>
							
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="s" name="list">
							<tr class="alt">
								<logic:iterate id="v" name="s">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		<table class="formlist" width="97%">
			<tfoot>
					<tr align="right">
						<td colspan="5">
							<div class="btn">
								<button type="button" name="关闭" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
	</body>
</html>

