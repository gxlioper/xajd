<%@ page language="java" contentType="text/html; charset=GBK"%>

<div class="open_win01">
	<table align="center" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>���ӽ�ʦ</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="30%">
					<span class="red">*</span>ְ����
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
					<span class="red">*</span>����
				</th>
				<td>
					<div class="pos" style="z-index:1">
						<input type="text" name="xm" id="xm" maxlength="25"
						onFocus="showMessage('xmMessage','hide');" />
						<div id="xmMessage" class="hide">
							<div class="prompcon">
								<p>
									��������Ϊ�գ�
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
						"<span class="red">*</span>"Ϊ������
					</div>
					<div class="btn">
						<button type="button" name="�� ��" onclick="addTea()">
							��&nbsp;&nbsp;��
						</button>
						<button type="button" name="ȡ��" onclick="closeWindown();return false;">
							ȡ&nbsp;&nbsp;��
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
