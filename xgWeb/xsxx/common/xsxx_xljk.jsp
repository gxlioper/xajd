<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:notEqual value="stu" name="userType" scope="session">				
	<div class="formbox" id="xljk" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="9">
						心理测试结果记录
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
						测试项目
					</td>
					<td>
						测试结果
					</td>
					<td>
						测试时间
					</td>
					<td>
						发送标记
					</td>
				</tr>
			</thead>
			<tbody id="xlcs">
			</tbody>
		</table>
		<br></br>

		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="6">
						特殊学生记录
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
						需要特别关心类别
					</td>
				</tr>
			</thead>
			<tbody id="tsxs">
			</tbody>
		</table>
	</div>
</logic:notEqual>