<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:present name="showXsgy">
	<div class="formbox" id="gygl" style="display:none">
		<logic:present name="showWmqs">
			<table width="100%" class="dateline">
				<thead>
					<tr>
						<td colspan="6">
							�������Ҽ�¼
						</td>
					</tr>
					<tr>
						<td>
							ѧ��
						</td>
						<td>
							ѧ��
						</td>
						<td>
							����ʱ��
						</td>
						<td>
							������
						</td>
						<td>
							���ȵȼ�
						</td>
						<td>
							��ע
						</td>
					</tr>
				</thead>
				<tbody id="wmqs">
				</tbody>
			</table>
			<br/><br/>
		</logic:present>

		<logic:present name="showZsjl">
			<table width="100%" class="dateline">
				<thead>
					<tr>
						<td colspan="9">
							ס�޼��ɼ�¼
						</td>
					</tr>
					<tr>
						<td>
							ѧ��
						</td>
						<td>
							ѧ��
						</td>
						<td>
							������
						</td>
						<td>
							���Һ�
						</td>
						<td>
							��������
						</td>
						<td>
							����
						</td>
						<td>
							������
						</td>
						<td>
							ʱ��
						</td>
						<td>
							��ע
						</td>
					</tr>
				</thead>
				<tbody id="zsjl">
				</tbody>
			</table>
			<br/>
			<br/>
			</logic:present>
			
			<logic:present name="showZsjbxx">
			<table width="100%" class="dateline">
				<thead>
					<tr>
						<td colspan="${gygl_colspan}">
							ס�޻�����Ϣ
						</td>
					</tr>
					<tr>
						<logic:present name="gygl_xqbj">
							<logic:notEqual name="gygl_xqbj" value="0">
								<td>У��</td>
							</logic:notEqual>
						</logic:present>
						<logic:present name="gygl_yqbj">
							<logic:notEqual name="gygl_yqbj" value="0">
								<td>԰��</td>
							</logic:notEqual>
						</logic:present>
						<td>
							¥��
						</td>
						<td>
							¥��
						</td>
						<td>
							���Һ�
						</td>
						<td>
							��λ��
						</td>
						<td>
							���ҵ绰
						</td>
					</tr>
				</thead>
				<tbody id="zsjbxx">
					<tr>
						<logic:present name="gygl_xqbj">
							<logic:notEqual name="gygl_xqbj" value="0">
								<td>${xszsjbxx.xqmc }</td>
							</logic:notEqual>
						</logic:present>
						<logic:present name="gygl_yqbj">
							<logic:notEqual name="gygl_yqbj" value="0">
								<td>${xszsjbxx.yqmc }</td>
							</logic:notEqual>
						</logic:present>
						<td>${xszsjbxx.ldmc }</td>
						<td>${xszsjbxx.chmc }</td>
						<td>${xszsjbxx.qsh }</td>
						<td>${xszsjbxx.cwh }</td>
						<td>${xszsjbxx.qsdh }</td>
					</tr>
				</tbody>
			</table>
			<br />
		</logic:present>
	</div>
</logic:present>