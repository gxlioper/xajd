<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:present name="showXsgy">
	<div class="formbox" id="gygl" style="display:none">
		<logic:present name="showWmqs">
			<table width="100%" class="dateline">
				<thead>
					<tr>
						<td colspan="6">
							文明寝室记录
						</td>
					</tr>
					<tr>
						<td>
							学年
						</td>
						<td>
							学期
						</td>
						<td>
							评优时间
						</td>
						<td>
							宿舍编号
						</td>
						<td>
							评比等级
						</td>
						<td>
							备注
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
							住宿纪律记录
						</td>
					</tr>
					<tr>
						<td>
							学年
						</td>
						<td>
							学期
						</td>
						<td>
							宿舍编号
						</td>
						<td>
							寝室号
						</td>
						<td>
							纪律名称
						</td>
						<td>
							事由
						</td>
						<td>
							处理结果
						</td>
						<td>
							时间
						</td>
						<td>
							备注
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
							住宿基本信息
						</td>
					</tr>
					<tr>
						<logic:present name="gygl_xqbj">
							<logic:notEqual name="gygl_xqbj" value="0">
								<td>校区</td>
							</logic:notEqual>
						</logic:present>
						<logic:present name="gygl_yqbj">
							<logic:notEqual name="gygl_yqbj" value="0">
								<td>园区</td>
							</logic:notEqual>
						</logic:present>
						<td>
							楼栋
						</td>
						<td>
							楼层
						</td>
						<td>
							寝室号
						</td>
						<td>
							床位号
						</td>
						<td>
							寝室电话
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