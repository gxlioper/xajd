<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="qtsj" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="10">
					<logic:equal value="12872" name="xxdm">
						保险理赔记录
					</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
						学生保险记录
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td>
					年度
				</td>
				<td>
					学年
				</td>
				<td>
					学期
				</td>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
				<td>
					保险公司
				</td>
				<td>
					投保险种
				</td>
				<td>
					投保时间
				</td>
				<td>
					退保时间
				</td>
				<td>
					退保标记
				</td>
			</tr>
		</thead>
		<tbody id="bxxx">
		</tbody>
	</table>
	<br></br>

	<logic:notEqual value="12872" name="xxdm">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="5">
						伙食消费记录
					</td>
				</tr>
				<tr>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						年份
					</td>
					<td>
						月份
					</td>
					<td>
						消费金额
					</td>
				</tr>
			</thead>
			<tbody id="hsxf">
			</tbody>
		</table>
	</logic:notEqual>
</div>
