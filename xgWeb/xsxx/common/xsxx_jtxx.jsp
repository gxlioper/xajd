<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="jtxx" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="8">
					家庭成员信息
				</td>
			</tr>
			<%--安徽建筑工业学院--%>
			<logic:equal value="10878" name="xxdm">
			<tr>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
				<td>
					父亲姓名
				</td>
				<td>
					与父亲关系
				</td>
				<td>
					母亲姓名
				</td>
				<td>
					与母亲关系
				</td>
				<td>
					其它成员姓名
				</td>
				<td>
					与其它成员关系
				</td>
			</tr>
			</logic:equal>

			<%--非安徽建筑工业学院--%>
			<logic:notEqual value="10878" name="xxdm">
				<tr>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						家庭成员1姓名
					</td>
					<td>
						家庭成员1关系
					</td>
					<td>
						家庭成员2姓名
					</td>
					<td>
						家庭成员2关系
					</td>
					<td>
						家庭成员3姓名
					</td>
					<td>
						家庭成员3关系
					</td>
				</tr>
			</logic:notEqual>
		</thead>
		<tbody  id="xsjtxx">

		</tbody>
	</table>
</div>
