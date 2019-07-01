<%@ page language="java" pageEncoding="GBK"%>
<%--北京青年政治学院--%>
<logic:equal value="11626" name="xxdm">
	<tr>
		<th>原毕业<bean:message key="lable.xb" /></th>
		<td>
			<html:text property="rxqdw" name="rs" styleId="rxqdw" maxlength="127" />
		</td>
		<th>原毕业<bean:message key="lable.xb" />邮编</th>
		<td colspan="2">
			<html:text property="rxqdwyb" name="rs" styleId="rxqdwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
		</td>		
	</tr>
	<tr>
		<th>原毕业<bean:message key="lable.xb" />通信地址</th>
		<td colspan="4">
			<html:text property="rxqdwdz" name="rs" styleId="rxqdwdz" maxlength="100" style="width:600px"/>
		</td>
	</tr>	
	<tr>
		<th>高中阶段表现情况</th>
		<td colspan="4">
			<html:textarea property="gzbx" name="rs" styleId="gzbx" cols="60" rows="4" onblur="chLeng(this,500)"></html:textarea>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<div class="con_overlfow floatleft">
				<table style="width: 100%" id="rsT" class="formlist">					
					<!-- 打印时第一行不显示- -->
					<thead style="height: 23px">
						<tr>
							<th colspan="9">
								<span><font color="blue"><b>本人学历简历</b></font></span>
							</th>
						</tr>
						<tr>
							<td colspan="9">
								<p align="left">
									<!-- 查询得到的数据量显示区域 -->
									<button type="button" onclick="trAdd('flag','add')">+</button>
									<button type="button" onclick="trDel('flag')">-</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd" style="width: 20px"
										onblur="trAdd('flag','madd')">
									&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
									<input type="text" name="numDel" id="numDel" style="width: 20px"
										onblur="trDelAll('flag','numDel')">
									&nbsp;行
								</p>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								序号
							</th>										
							<th nowrap="nowrap">
								开始时间
							</th>		
							<th nowrap="nowrap">
								结束时间
							</th>	
							<th nowrap="nowrap">
								地区
							</th>
							<th nowrap="nowrap">
								部门
							</th>
							<th nowrap="nowrap">
								职位
							</th>								
							<th nowrap="nowrap">
								证明人
							</th>
							<th nowrap="nowrap">
								证明人单位
							</th>
							<th nowrap="nowrap">
								证明人职务
							</th>																																									
						</tr>
					</thead>
					<tbody id="flag">
					
					</tbody>
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<div class="con_overlfow floatleft">					
				<table style="width: 100%" id="rsT" class="formlist">
					<!-- 打印时第一行不显示- -->
					<thead style="height: 23px">
						<tr>
							<th colspan="7">
								<span><font color="blue"><b>社会关系</b></font></span>
							</th>
						</tr>
						<tr>
							<td colspan="7">
								<p align="left">
									<!-- 查询得到的数据量显示区域 -->
									<button type="button" onclick="shgxtrAdd('shgx','add')">+</button>
									<button type="button" onclick="trDel('shgx')">-</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
									<input type="text" name="shgx_numAdd" id="numAdd" style="width: 20px"
										onblur="shgxtrAdd('shgx','madd')">
									&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
									<input type="text" name="shgx_numDel" id="numDel" style="width: 20px"
										onblur="trDelAll('shgx','shgx_numDel')">
									&nbsp;行
								</p>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								序号
							</th>										
							<th nowrap="nowrap">
								姓名
							</th>		
							<th nowrap="nowrap">
								年龄
							</th>	
							<th nowrap="nowrap">
								政治面貌
							</th>
							<th nowrap="nowrap">
								现工作单位
							</th>
							<th nowrap="nowrap">
								担任职务
							</th>								
							<th nowrap="nowrap">
								联系电话
							</th>																																								
						</tr>
					</thead>
					<tbody id="shgx">
					
					</tbody>
				</table>
		    </div>
		</td>
	</tr>
</logic:equal>