<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="xsxx" style="display: none">
	<table align="center" class="formlist breakword">
		<thead>
			<tr>
				<th colspan="5" onclick="deploy('tb_zxxx');">
					<span>在校信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="tb_zxxx">
			<tr>
				<th width="16%">
					年级
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
					专业
				</th>
				<td>
					${rs.zymc }
				</td>
				<th>
					班级
				</th>
				<td colspan="2">
					${rs.bjmc }
				</td>
			</tr>
			<tr>
				<th>
					学制
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
					<span>其他信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="tb_qtxx" style="display: none">
			<tr>
				<th width="16%">
					手机号码
				</th>
				<td width="24%">
					${rs.sjhm }
				</td>
				<th width="15%">
					QQ号码
				</th>
				<td colspan="2">
					${rs.qqhm }
				</td>
			</tr>
			<tr>
				<th>
					电子邮箱
				</th>
				<td colspan="4">
					${rs.dzyx}
				</td>
			</tr>
			<tr>
				<th>
					银行名称
				</th>
				<td>
					${rs.yhmc }
				</td>
				<th>
					银行卡号
				</th>
				<td colspan="2">
					${rs.yhkh }
				</td>
			</tr>
		</tbody>

		<thead>
			<tr>
				<th colspan="5" style="cursor: hand" onclick="deploy('tb_jtxx');">
					<span>家庭信息</span>
				</th>
			</tr>
		</thead>
		<tbody id="tb_jtxx" style="display: none">
			<tr>
				<th width="16%">
					家庭电话
				</th>
				<td width="24%">
					${rs.lxdh1 }
				</td>
				<th width="15%">
					家庭邮编
				</th>
				<td colspan="2">
					${rs.yb }
				</td>
			</tr>
			<tr>
				<th>
					家庭地址
				</th>
				<td colspan="4">
					${rs.jtszd }
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<table width="100%">
						<!-- 家庭成员1 -->
						<tbody>
							<tr>
								<td colspan="4" align="center">
									<span>家庭成员1</span>
								</td>
							</tr>
							<tr>
								<th width="16%">
									姓名
								</th>
								<td width="24%">
									${rs.jtcy1_xm }
								</td>
								<th width="15%">
									与本人关系
								</th>
								<td width="">
									${rs.jtcy1_gx}
								</td>
							</tr>
							<tr>
								<th width="">
									出生日期
								</th>
								<td width="">
									${rs.jtcy1_nl}
								</td>
								<th width="">
									身份证号
								</th>
								<td width="">
									${rs.jtcy1_sfzh }
								</td>
							</tr>
							<tr>
								<th width="">
									民族
								</th>
								<td width="">
									${rs.jtcy1_mzmc }
								</td>
								<th width="">
									政治面貌
								</th>
								<td width="">
									${rs.jtcy1_zzmmmc }
								</td>
							</tr>
							<tr>
								<th width="">
									职业
								</th>
								<td width="">
									${rs.jtcy1_zy }
								</td>
								<th width="">
									职务
								</th>
								<td width="">
									${rs.jtcy1_zw }
								</td>
							</tr>
							<tr>
								<th width="">
									工作单位电话
								</th>
								<td width="">
									${rs.jtcy1_lxdh1 }
								</td>
								<th width="">
									个人电话
								</th>
								<td width="">
									${rs.jtcy1_lxdh2 }
								</td>
							</tr>
							<tr>
								<th width="">
									工作地址
								</th>
								<td width="" colspan="3">
									${rs.jtcy1_gzdz }
								</td>
							</tr>
						</tbody>
						<!-- 家庭成员1 end-->

						<!-- 家庭成员2-->
						<tbody>
							<tr>
								<td colspan="4" align="center">
									<span>家庭成员2</span>
								</td>
							</tr>
							<tr>
								<th width="16%">
									姓名
								</th>
								<td width="24%">
									${rs.jtcy2_xm }
								</td>
								<th width="15%">
									与本人关系
								</th>
								<td width="">
									${rs.jtcy2_gx}
								</td>
							</tr>
							<tr>
								<th width="">
									出生日期
								</th>
								<td width="">
									${rs.jtcy2_nl}
								</td>
								<th width="">
									身份证号
								</th>
								<td width="">
									${rs.jtcy2_sfzh }
								</td>
							</tr>
							<tr>
								<th width="">
									民族
								</th>
								<td width="">
									${rs.jtcy2_mzmc }
								</td>
								<th width="">
									政治面貌
								</th>
								<td width="">
									${rs.jtcy2_zzmmmc }
								</td>
							</tr>
							<tr>
								<th width="">
									职业
								</th>
								<td width="">
									${rs.jtcy2_zy }
								</td>
								<th width="">
									职务
								</th>
								<td width="">
									${rs.jtcy2_zw }
								</td>
							</tr>
							<tr>
								<th width="">
									工作单位电话
								</th>
								<td width="">
									${rs.jtcy2_lxdh1 }
								</td>
								<th width="">
									个人电话
								</th>
								<td width="">
									${rs.jtcy2_lxdh2 }
								</td>
							</tr>
							<tr>
								<th width="">
									工作地址
								</th>
								<td width="" colspan="3">
									${rs.jtcy2_gzdz }
								</td>
							</tr>
						</tbody>
						<!-- 家庭成员2 end-->
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</div>


