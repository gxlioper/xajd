<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="xsxx" style="display: none">
	<table align="center" class="formlist breakword">
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('tb_zxxx');">
					<span>��У��Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody id="tb_zxxx">
			<tr>
				<th width="16%">
					�꼶
				</th>
				<td width="24%">
					${rs.nj }
				</td>
				<th width="15%">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td colspan="2">
					${rs.xymc }
				</td>
			</tr>
			<tr>
				<th>
					רҵ
				</th>
				<td>
					${rs.zymc }
				</td>
				<th>
					�༶
				</th>
				<td colspan="2">
					${rs.bjmc }
				</td>
			</tr>
			<tr>
				<th>
					ѧ��
				</th>
				<td>
					${rs.xz }
				</td>
				<th>

				</th>
				<td colspan="2">

				</td>
			</tr>
		</tbody>


		<thead>
			<tr>
				<th colspan="5" style="cursor: hand" onclick="deploy('tb_qtxx');">
					<span>������Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody id="tb_qtxx" style="display: none">
			<tr>
				<th width="16%">
					�ֻ�����
				</th>
				<td width="24%">
					${rs.sjhm }
				</td>
				<th width="15%">
					QQ����
				</th>
				<td colspan="2">
					${rs.qqhm }
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td colspan="4">
					${rs.dzyx}
				</td>
			</tr>
			<tr>
				<th>
					��������
				</th>
				<td>
					${rs.yhmc }
				</td>
				<th>
					���п���
				</th>
				<td colspan="2">
					${rs.yhkh }
				</td>
			</tr>
		</tbody>

		<thead>
			<tr>
				<th colspan="5" style="cursor: hand" onclick="deploy('tb_jtxx');">
					<span>��ͥ��Ϣ</span>
				</th>
			</tr>
		</thead>
		<tbody id="tb_jtxx" style="display: none">
			<tr>
				<th width="16%">
					��ͥ�绰
				</th>
				<td width="24%">
					${rs.lxdh1 }
				</td>
				<th width="15%">
					��ͥ�ʱ�
				</th>
				<td colspan="2">
					${rs.yb }
				</td>
			</tr>
			<tr>
				<th>
					��ͥ��ַ
				</th>
				<td colspan="4">
					${rs.jtszd }
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<table width="100%">
						<!-- ��ͥ��Ա1 -->
						<tbody>
							<tr>
								<td colspan="4" align="center">
									<span>��ͥ��Ա1</span>
								</td>
							</tr>
							<tr>
								<th width="16%">
									����
								</th>
								<td width="24%">
									${rs.jtcy1_xm }
								</td>
								<th width="15%">
									�뱾�˹�ϵ
								</th>
								<td width="">
									${rs.jtcy1_gx}
								</td>
							</tr>
							<tr>
								<th width="">
									��������
								</th>
								<td width="">
									${rs.jtcy1_nl}
								</td>
								<th width="">
									���֤��
								</th>
								<td width="">
									${rs.jtcy1_sfzh }
								</td>
							</tr>
							<tr>
								<th width="">
									����
								</th>
								<td width="">
									${rs.jtcy1_mzmc }
								</td>
								<th width="">
									������ò
								</th>
								<td width="">
									${rs.jtcy1_zzmmmc }
								</td>
							</tr>
							<tr>
								<th width="">
									ְҵ
								</th>
								<td width="">
									${rs.jtcy1_zy }
								</td>
								<th width="">
									ְ��
								</th>
								<td width="">
									${rs.jtcy1_zw }
								</td>
							</tr>
							<tr>
								<th width="">
									������λ�绰
								</th>
								<td width="">
									${rs.jtcy1_lxdh1 }
								</td>
								<th width="">
									���˵绰
								</th>
								<td width="">
									${rs.jtcy1_lxdh2 }
								</td>
							</tr>
							<tr>
								<th width="">
									������ַ
								</th>
								<td width="" colspan="3">
									${rs.jtcy1_gzdz }
								</td>
							</tr>
						</tbody>
						<!-- ��ͥ��Ա1 end-->

						<!-- ��ͥ��Ա2-->
						<tbody>
							<tr>
								<td colspan="4" align="center">
									<span>��ͥ��Ա2</span>
								</td>
							</tr>
							<tr>
								<th width="16%">
									����
								</th>
								<td width="24%">
									${rs.jtcy2_xm }
								</td>
								<th width="15%">
									�뱾�˹�ϵ
								</th>
								<td width="">
									${rs.jtcy2_gx}
								</td>
							</tr>
							<tr>
								<th width="">
									��������
								</th>
								<td width="">
									${rs.jtcy2_nl}
								</td>
								<th width="">
									���֤��
								</th>
								<td width="">
									${rs.jtcy2_sfzh }
								</td>
							</tr>
							<tr>
								<th width="">
									����
								</th>
								<td width="">
									${rs.jtcy2_mzmc }
								</td>
								<th width="">
									������ò
								</th>
								<td width="">
									${rs.jtcy2_zzmmmc }
								</td>
							</tr>
							<tr>
								<th width="">
									ְҵ
								</th>
								<td width="">
									${rs.jtcy2_zy }
								</td>
								<th width="">
									ְ��
								</th>
								<td width="">
									${rs.jtcy2_zw }
								</td>
							</tr>
							<tr>
								<th width="">
									������λ�绰
								</th>
								<td width="">
									${rs.jtcy2_lxdh1 }
								</td>
								<th width="">
									���˵绰
								</th>
								<td width="">
									${rs.jtcy2_lxdh2 }
								</td>
							</tr>
							<tr>
								<th width="">
									������ַ
								</th>
								<td width="" colspan="3">
									${rs.jtcy2_gzdz }
								</td>
							</tr>
						</tbody>
						<!-- ��ͥ��Ա2 end-->
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</div>


