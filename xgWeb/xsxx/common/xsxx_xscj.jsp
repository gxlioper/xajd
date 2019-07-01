<%@ page language="java" contentType="text/html; charset=GBK"%>
<!--浙江商业职业技术学院-->
<logic:present name="showZjszy">				
	<div class="formbox" id="xscj" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="5">
						不合格课程记录
					</td>
				</tr>
			</thead>
			<tr>
				<td>
					<logic:empty name="rs25">
						<br />
						<br />
						<p align="center">
							无记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs25">
						<div style="display:none">
							<logic:iterate id="in25" name="rs25" indexId="index">
								<logic:iterate id="xq" name="in25">
									<tr>
										<td bgcolor="#ffccdd" align="center"
											onclick="getBjgcjInfo(${index});document.all.kcxx${index}.style.display=(document.all.kcxx${index}.style.display =='none')?'':'none'"
											id="${index}" name="${index}" colspan="5">
											${xq.xn}学年${xq.xq}学期 &nbsp;&nbsp;&nbsp;&nbsp;共
											<font color="red">${xq.numKc}</font>门不及格,获得
											<font color="red">${xq.numXf}</font>学分
											<input type="hidden" value="${xq.xn}${xq.xq}"
												id="xq${index}">
										</td>
									</tr>

									<tr>
										<td bgcolor="#ffffff">
											<table width="100%" class="tbstyle" id="kcxx${index}"
												style="display:none">
											</table>
										</td>
									</tr>

								</logic:iterate>
							</logic:iterate>
						</div>
					</logic:notEmpty>
				</td>
			</tr>
			<logic:notEmpty name="rs25">
				<tr>
					<td colspan="5">
						<div align="right">
							在校期间截止到现在共有
							<font color="red"><bean:write name="totalKc" /> </font>门不合格
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 累计获得学分
							<font color="red"><bean:write name="totalXf" /> </font>分
						</div>
					</td>
				</tr>
			</logic:notEmpty>
		</table>
	</div>
</logic:present>

<%--中国美院--%>
<logic:equal value="10355" name="xxdm">			
	<div class="formbox" id="zgmyxscj" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="7">
						课程成绩记录
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
						课程名称
					</td>
					<td>
						课程类型
					</td>
					<td>
						补考成绩
					</td>
					<td>
						重修成绩
					</td>
					<td>
						总评成绩
					</td>
				</tr>
			</thead>
			<tbody id="xskccj">
			</tbody>
		</table>
	</div>
</logic:equal>
<%--end中国美院--%>

<%--乐山师范学院--%>
<logic:equal value="10649" name="xxdm">				
	<div class="formbox" id="lssfxscj" style="display:none">
		<table width="100%" class="dateline">
			<thead>
				<tr>
					<td colspan="7">
						课程成绩记录
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
						课程名称
					</td>
					<td>
						课程类型
					</td>
					<td>
						补考成绩
					</td>
					<td>
						重修成绩
					</td>
					<td>
						总评成绩
					</td>
				</tr>
			</thead>
			<tbody id="xskccj">
			</tbody>
		</table>
	</div>
</logic:equal>
<%--end乐山师范学院--%>


<!--非浙江商业职业技术学院-->
<logic:notPresent name="showZjszy">
<logic:notEqual value="10355" name="xxdm">	
<logic:notEqual value="10649" name="xxdm">		
<div class="formbox" id="xscj" style="display:none">
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="9">
					学生成绩
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
					课程名称
				</td>
				<td>
					课程性质
				</td>
				<td>
					学分
				</td>
				<td>
					成绩
				</td>
				<td>
					补考成绩
				</td>
				<td>
					重修成绩
				</td>
			</tr>
		</thead>
		<tbody id="xscjList">
		</tbody>
	</table>
	
	<table width="100%" class="dateline">
		<thead>
			<tr>
				<td colspan="9">
					等级考试成绩
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
					等级考试名称
				</td>
				<td>
					成绩
				</td>
			</tr>
		</thead>
		<tbody id="djksList">
		</tbody>
	</table>
</div>
</logic:notEqual>
</logic:notEqual>
</logic:notPresent>

