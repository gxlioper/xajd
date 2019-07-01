<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="dwjl" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<!--杭州职业技术学院-->
				<logic:equal value="12872" name="xxdm">							
				<td colspan="10">
					对外交流及交流奖学金记录
				</td>
				</logic:equal>
				
				<!--非杭州职业技术学院-->
				<logic:notEqual value="12872" name="xxdm">
				<td colspan="11">
					对外交流及交流奖学金记录
				</td>
				</logic:notEqual>	
				
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
					交流项目
				</td>
				<td>
					申请时间
				</td>
				<!--杭州职业技术学院-->
				<logic:equal value="12872" name="xxdm">							
					<td>
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>
				</logic:equal>
				<!--非杭州职业技术学院-->
				<logic:notEqual value="12872" name="xxdm">
					<td>
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>
					<td>
						学校审核
					</td>
				</logic:notEqual>						
				<td>
					学校终审
				</td>
				<td>
					奖学金
				</td>
			</tr>
		</thead>
		<tbody id="dwjljjxj">

		</tbody>
	</table>
</div>
