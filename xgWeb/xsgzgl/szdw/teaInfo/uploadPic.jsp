<%@ page language="java" contentType="text/html; charset=GBK"%>

<div class="open_win01">
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>�ϴ���Ƭ</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<input type="file" id="teaPic" name="teaPic" style="width:90%"
						   onchange='uploadTeaPic();closeWindown();'
					/>
				</td>
			</tr>
		</tbody>
		<%--<tfoot>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button" name="�� ��" onclick="uploadTeaPic()">
							��&nbsp;&nbsp;��
						</button>
						<button type="button" name="ȡ��" onclick="closeWindown();return false;">
							ȡ&nbsp;&nbsp;��
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	--%></table>
</div>
