<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="formbox" id="pjpy" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<%--重庆科技学院--%>
				<logic:equal value="11551" name="xxdm">
					<td colspan="11">
						奖学金记录
					</td>
				</logic:equal>
				<%--安徽建筑工业学院--%>
				<logic:equal value="10878" name="xxdm">
					<td colspan="11">
						奖学金记录
					</td>
				</logic:equal>
				<%--四川建筑职业技术学院--%>
				<logic:equal value="12764" name="xxdm">
					<td colspan="11">
						奖学金记录
					</td>
				</logic:equal>
				<%--杭州职业技术学院--%>
				<logic:equal value="12872" name="xxdm">
					<td colspan="8">
						奖学金记录
					</td>
				</logic:equal>

				<%--其他--%>
				<logic:notEqual value="11551" name="xxdm">
				<logic:notEqual value="10878" name="xxdm">
				<logic:notEqual value="12764" name="xxdm">
				<logic:notEqual value="12872" name="xxdm">
					<td colspan="9">
						奖学金记录
					</td>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
			</tr>
			<tr>
				<!-- 宁波理工不需要显示年度 -->
				<logic:notEqual value="13022" name="xxdm">							
					<td>
						年度
					</td>
				</logic:notEqual>
				<td>
					学年
				</td>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
				<td>
					班级
				</td>
				<td>
					奖学金名称
				</td>
				<td>
					奖学金金额
				</td>						
				<%--重庆科技学院--%>
				<logic:equal value="11551" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
				<%--安徽建筑工业学院--%>
				<logic:equal value="10878" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
				<%--四川建筑职业技术学院--%>
				<logic:equal value="12764" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
				
				<!--杭州职业技术学院-->
				<logic:equal value="12872" name="xxdm">
					<td>
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>							
				</logic:equal>
				<!--end杭州职业技术学院-->

				<!--非杭州职业技术学院-->
				<logic:notEqual value="12872" name="xxdm">							
					<td>
						<bean:message key="lable.xsgzyxpzxy" />审核
					</td>
					<td>
						学校审核
					</td>
				</logic:notEqual>			
			</tr>
		</thead>
		<tbody id="jxj">
		</tbody>
	</table>
	<br></br>

	<table width="100%" class="dateline">
		<thead>
			<%--重庆科技学院--%>
			<logic:equal value="11551" name="xxdm">
				<tr>
					<td colspan="9">
						荣誉称号记录
					</td>
				</tr>
			</logic:equal>
			<%--安徽建筑工业学院--%>
			<logic:equal value="10878" name="xxdm">
				<tr>
					<td colspan="9">
						荣誉称号记录
					</td>
				</tr>
			</logic:equal>
			<%--井冈山大学--%>
			<logic:equal value="10419" name="xxdm">
				<tr>
					<td colspan="9">
						荣誉称号记录
					</td>
				</tr>
			</logic:equal>
			<%--长沙民政职业技术学院--%>
			<logic:equal value="10827" name="xxdm">
				<tr>
					<td colspan="9">
						荣誉称号记录
					</td>
				</tr>
			</logic:equal>
			<!--杭州职业技术学院-->
			<logic:equal value="12872" name="xxdm">
				<tr>
					<td colspan="7">
						荣誉称号记录
					</td>
				</tr>
			</logic:equal>
			<!--杭州职业技术学院-->

			<%--其他--%>
			<logic:notEqual value="11551" name="xxdm">
			<logic:notEqual value="10878" name="xxdm">
			<logic:notEqual value="10419" name="xxdm">
			<logic:notEqual value="10827" name="xxdm">
			<logic:notEqual value="12872" name="xxdm">
				<tr>
					<td colspan="8">
						荣誉称号记录
					</td>
				</tr>
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>
			</logic:notEqual>

			<tr>
				<!-- 宁波理工不需要显示年度 -->
				<logic:notEqual value="13022" name="xxdm">							
					<td>
						年度
					</td>
				</logic:notEqual>
				<td>
					学年
				</td>
				<td>
					学号
				</td>
				<td>
					姓名
				</td>
				<td>
					班级
				</td>
				<td>
					荣誉称号名称
				</td>
				<%--重庆科技学院--%>
				<logic:equal value="11551" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
				<%--安徽建筑工业学院--%>
				<logic:equal value="10878" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
				<%--井冈山大学--%>
				<logic:equal value="10419" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
				<%--长沙民政职业技术学院--%>
				<logic:equal value="10827" name="xxdm">
					<td>
						辅导员审核
					</td>
				</logic:equal>
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
			</tr>
		</thead>
		<tbody width="100%" id="rych">
		</tbody>
	</table>
	<br/></br>

	<%--中国美院--%>
	<logic:equal value="10355" name="xxdm">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="11">
						体育达标情况记录
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
						体育达标情况
					</td>
					<td>
						备注
					</td>
				</tr>
			</thead>
			<tbody id="tydbqk">
			</tbody>
		</table>
		<br/><br/>

		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="11">
						学生考勤情况记录
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
						旷课时间
					</td>
					<td>
						旷课地点
					</td>
					<td>
						旷课记录
					</td>
				</tr>
			</thead>
			<tbody id="xskqb">

			</tbody>
		</table>
		<br/><br/>
	</logic:equal>
	<%--end中国美院--%>

	<%--非中国美院--%>
	<logic:notEqual value="10355" name="xxdm">		
	<%--非杭州职业技术学院--%>		
	<logic:notEqual value="12872" name="xxdm">					
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="11">
						纪实综合考评记录
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
						思想品德评价
					</td>
					<td>
						知识水平评价
					</td>
					<td>
						学业能力评价
					</td>
					<td>
						能力评价
					</td>
					<td>
						素质评价
					</td>
					<td>
						学分
					</td>
				</tr>
			</thead>
			<tbody id="jszhkp">
			</tbody>
		</table>
		<br/><br/>
		</logic:notEqual>

		<!-- 宁波理工不显示综合测评记录 -->
		<logic:notEqual value="13022" name="xxdm">					
			<table width="100%" class="dateline">
				<thead>
					<tr>
						<td colspan="13">
							综合素质测评记录
						</td>
					</tr>
					<logic:equal value="10822" name="xxdm">
						<%--广东白云学院>--%>
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
								班级
							</td>
							<td>
								操行成绩
							</td>
						</tr>
					</logic:equal>

					<%--浙江机电--%>
					<logic:equal value="12861" name="xxdm">								
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
								班级
							</td>
							<td>
								德育总分
							</td>
							<td>
								智育总分
							</td>
							<td>
								体育总分
							</td>
							<td>
								综合素质测评总分
							</td>
						</tr>
					</logic:equal>
					<%--杭州职业技术学院--%>
					<logic:equal value="12872" name="xxdm">								
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
								班级
							</td>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />德育考评分
							</td>
							<td>
								智育总分
							</td>
							<td>
								体育总分
							</td>
							<td>
								工作学习创新分
							</td>
							<td>
								公寓德育考评分
							</td>
							<td>
								综合素质测评总分
							</td>
						</tr>
					</logic:equal>
					<%--宁波职业技术学院--%>
					<logic:equal value="10863" name="xxdm">
						
						<tr>
							<td>
								学年
							</td>
							<td>
								职业素养测评分
							</td>
							<td>
								职业技能素养测评分
							</td>
							<td>
								可持续发展素养测评分
							</td>
							<td>
								综合测评总分
							</td>
							<td>
								职业素养排名
							</td>
							<td>
								综合素质排名
							</td>
						</tr>
					</logic:equal>
					<%--苏州工业园区职业技术学院--%>
					<logic:equal value="12809" name="xxdm">								
						<tr>
							<td>
								学年
							</td>
							<td>
								学期
							</td>
							<td>
								5S模块分
							</td>
							<td>
								社会实践分
							</td>
							<td>
								组织能力分
							</td>
							<td>
								读书活动分
							</td>
							<td>
								ivt论坛分
							</td>
							<td>
								文体活动分
							</td>
							<td>
								语言表达分
							</td>
							<td>
								学团活动总分
							</td>
							<td>
								综合素质总分
							</td>
							<td>
								综合素质排名
							</td>
						</tr>
					</logic:equal>
					<logic:notEqual value="12809" name="xxdm">
					<logic:notEqual value="10822" name="xxdm">
					<logic:notEqual value="12861" name="xxdm">
					<logic:notEqual value="12872" name="xxdm">
					<logic:notEqual value="10863" name="xxdm">
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
								班级
							</td>
							<td>
								思想政治素<br/>
								质总积分
							</td>
							<td>
								学习总积分
							</td>
							<td>
								体育素质<br/>
								总积分
							</td>
							<td>
								智能素质<br/>
								总积分
							</td>
							<td>
								综合素质<br/>
								测评总分
							</td>
							<td>
								班级排名
							</td>
							<td>
								年级排名
							</td>
						</tr>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
				</thead>
				<tbody id="zhszcp">
				</tbody>
			</table>					
		</logic:notEqual>
		<!-- end宁波理工 -->
	</logic:notEqual>
	<%--end中国美院--%>

	<%--安徽建筑工业学院--%>
	<logic:equal value="10878" name="xxdm">
		<br/><br/>
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="8">
						学习竞赛获奖情况
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
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						获奖时间
					</td>
					<td>
						学习竞赛名称
					</td>
					<td>
						学习竞赛得分
					</td>
				</tr>
			</thead>
			<tbody id="xxjshj">
			</tbody>
		</table>
	</logic:equal>
</div>
