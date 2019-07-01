<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:present name="showhzy">
<div class="formbox" id="xssfxx" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				收费记录
			</tr>
			<tr>
				<td>
					学号
				</td>
				<td>
					入住时间
				</td>
				<td>
					退房时间
				</td>
				<td>
					住宿费
				</td>
				<td>
					电视机收视费
				</td>
			</tr>
		</thead>
		<tbody id="zsf">
		</tbody>
	</table>
</div>
</logic:present>
<!--南国商学院-->
<logic:equal name='xxdm' value='12620'>
<div id="child11" id="jfqk" style="display:none">
	<table width="100%" align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="10">							
					<span>学生缴费情况</span>
				</th>
			</tr>
			<tr>
				<th>
					学号
				</th>
				<th>
					姓名
				</th>
				<th>
					学年
				</th>
				<th>
					应缴学费
				</th>
				<th>
					已缴学费
				</th>
				<th>
					所欠学费
				</th>
			</tr>
		</thead>
		<tbody width="100%" class="tbstyle" id="qfqkList">
		</tbody>
	</table>
</div>
</logic:equal>