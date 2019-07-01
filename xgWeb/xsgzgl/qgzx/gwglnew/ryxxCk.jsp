<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div style="height:310px;overflow-x:hidden;overflow-y:auto;">
	<table width="100%" border="0" class="formlist">
		<thead>
			<tr>
				<th colspan="4">
					<span>学生信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="tbody_ryxx">
		</tbody>
		<thead>
			<tr>
				<th colspan="4">
					<span>在岗信息</span>
				</th>
			</tr>
		</thead>
	</table>
	<table width="100%" border="0" class="formlist">
		<thead id="thead_zggwxx">
			<tr>
				<td width='10px'>行号</td>
				<td width='15%'>学年</td>
				<td width='20%'>用人部门</td>
				<td width='25%'>岗位名称</td>
				<td width='20%'>岗位性质</td>
				<td width='20%'>上岗日期</td>
			</tr>
		</thead>
		<tbody id="tbody_zggwxx">
		</tbody>
	</table>
</div>
<table border="0" class="formlist">
	<tfoot>
		<tr align="right">
			<td colspan="6">
				<div class="btn">
					<button type="button" onclick="iFClose();return false;">
						关 闭
					</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>

