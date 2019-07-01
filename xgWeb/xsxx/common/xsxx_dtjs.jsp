<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="dtjs" style="display:none">
	<table class="dateline" width="100%">
		<thead>
			<tr>
				<td colspan="9">
					培训记录
				</td>
			</tr>
			<tr>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
				<td>
					性别
				</td>
				<td>
					年级
				</td>
				<td>
					<bean:message key="lable.xsgzyxpzxy" />名称
				</td>
				<td>
					专业名称
				</td>
				<td>
					班级名称
				</td>
				<td>
					培训次数
				</td>
			</tr>
		</thead>
		<tbody id="xspxxx">
		</tbody>
	</table>
	<br/><br/>
	<table class="dateline" width="100%">
		<thead>
			<tr>
				<td colspan="8">
					入党积极分子记录
				</td>
			</tr>
			<tr>
				<td>
					年度
				</td>
				<td>
					学年
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
					年级
				</td>
				<td>
					班级
				</td>
				<td>
					开始时间
				</td>
			</tr>
		</thead>
		<tbody id="rdjjfz">

		</tbody>
	</table>
	<br/><br/>
	<table class="dateline" width="100%">
		<thead>
			<!--三江学院-->
			<logic:equal value="11122" name="xxdm">
			<tr>
				<td colspan="10">
					预备党员记录
				</td>
			</tr>
			<tr>
				<td>
					年度
				</td>
				<td>
					学年
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
					年级
				</td>
				<td>
					班级
				</td>
				<td>
					转正情况
				</td>
				<td>
					开始时间
				</td>
				<td>
					结束时间
				</td>
			</tr>
			</logic:equal>
			<!--非三江学院-->
			<logic:notEqual value="11122" name="xxdm">
				<tr>
					<td colspan="9">
						预备党员记录
					</td>
				</tr>
				<tr>
					<td>
						年度
					</td>
					<td>
						学年
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
						年级
					</td>
					<td>
						班级
					</td>
					<td>
						开始时间
					</td>
					<td>
						结束时间
					</td>
				</tr>
			</logic:notEqual>
		</thead>
		<tbody id="ybdy">

		</tbody>
	</table>		
	<br/><br/>
	<table class="dateline" width="100%">
		<thead>
			<!--三江学院-->
			<logic:equal value="11122" name="xxdm">
			<tr>
				<td colspan="10">
					党员记录
				</td>
			</tr>								
			<tr>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
				<td>
					年级
				</td>
				<td>
					班级
				</td>
				<td>
					入党时间
				</td>
				<td>
					转正日期
				</td>
				<td>
					转正情况
				</td>
				<td>
					组织关系所在单位
				</td>
			</tr>
			</logic:equal>
			<!--三江学院-->

			<!--非三江学院-->
			<logic:notEqual value="11122" name="xxdm">
			<tr>
				<td colspan="8">
					党员记录
				</td>
			</tr>
			<tr>
				<td>
					年度
				</td>
				<td>
					学年
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
					年级
				</td>
				<td>
					班级
				</td>
				<td>
					入党时间
				</td>
			</tr>
			</logic:notEqual>
			<!--end非三江学院-->
		</thead>
		<tbody id="dy">

		</tbody>
	</table>
</div>
