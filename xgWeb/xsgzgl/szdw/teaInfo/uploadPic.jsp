<%@ page language="java" contentType="text/html; charset=GBK"%>

<div class="open_win01">
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>上传照片</span>
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
						<button type="button" name="提 交" onclick="uploadTeaPic()">
							上&nbsp;&nbsp;传
						</button>
						<button type="button" name="取消" onclick="closeWindown();return false;">
							取&nbsp;&nbsp;消
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	--%></table>
</div>
