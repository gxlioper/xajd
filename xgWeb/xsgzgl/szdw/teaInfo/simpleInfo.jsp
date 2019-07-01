<%@ page language="java" contentType="text/html; charset=GBK"%>

<div class="open_win01">
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>增加教师</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="30%">
					<span class="red">*</span>职工号
				</th>
				<td>
					<div class="pos" style="z-index:2">
						<input type="text" name="zgh" id="zgh"
						onblur="checkZgh()" maxlength="10"
						onkeyup="value=value.replace(/[\u4e00-\u9fa5]/g,'');value=value.replace(/[+&%#]/g,'');"
						onFocus="showMessage('zghMessage','hide');"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<span class="red">*</span>姓名
				</th>
				<td>
					<div class="pos" style="z-index:1">
						<input type="text" name="xm" id="xm" maxlength="25"
						onFocus="showMessage('xmMessage','hide');" />
						<div id="xmMessage" class="hide">
							<div class="prompcon">
								<p>
									姓名不能为空！
								</p>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<div class="bz">
						"<span class="red">*</span>"为必填项
					</div>
					<div class="btn">
						<button type="button" name="提 交" onclick="addTea()">
							保&nbsp;&nbsp;存
						</button>
						<button type="button" name="取消" onclick="closeWindown();return false;">
							取&nbsp;&nbsp;消
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
