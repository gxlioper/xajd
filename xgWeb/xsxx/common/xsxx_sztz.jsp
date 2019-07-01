<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:equal value="11032" name="xxdm">
	<div  class="formbox"  >
		<table width="100%" class="dateline" id="sztz" style="display:none">
			<thead>
				<tr>
					<td>所属科目</td>
					<td>核心能力</td>
					<td>学年</td>
					<td>学期</td>
					<td>学分</td>
					<td>是否重修</td>
				</tr>
			</thead>
			<tbody id="sztzData">
				
			</tbody>
		</table>
	</div>
</logic:equal>
<logic:notEqual value="11032" name="xxdm">
<%--江苏信息职业技术学院--%>
<logic:equal value="13108" name="xxdm">
	<div class="formbox" id="jsxxsztz" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="9">
						素质拓展认证信息
					</td>
				</tr>
				<tr>
					<td>
						学年
					</td>
					<td>
						年度
					</td>
					<td>
						学期
					</td>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						拓展活动
					</td>
					<td>
						所属项目
					</td>
					<td>
						获得学分
					</td>
				</tr>
			</thead>
			<tbody id="tzrzxx">
			</tbody>
		</table>
	</div>
</logic:equal>

<%--西南民族大学--%>
<logic:present name="isXNMZ">
	<div class="formbox" id="xnmzsztz" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="9">
						结业记录
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
						素质拓展班级
					</td>
					<td>
						结业时间
					</td>
					<td>
						结业分数
					</td>
				</tr>
			</thead>
			<tbody id="tzbjjy">
			</tbody>
		</table>
	</div>
</logic:present>

<%--宁波职业技术学院--%>
<logic:present name="isNbzyjsxy">				
	<div class="formbox">
		<table width="100%" class="dateline" id="sztz" style="display:none">
			<thead>
				<tr>
					<td colspan="10">
						活动记录
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
						项目名称
					</td>
					<td>
						项目级别
					</td>
					<td>
						科目名称
					</td>
					<td>
						所获奖项
					</td>
					<td>
						所获学分
					</td>
					<td>
						参与性质
					</td>
					<td>
						组织单位
					</td>
					<td>
						主办时间
					</td>
				</tr>
			</thead>
			<tbody id="tzhdxx">
			</tbody>
		</table>
	</div>
</logic:present>
</logic:notEqual>