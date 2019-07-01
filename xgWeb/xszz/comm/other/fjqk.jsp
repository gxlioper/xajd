<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>项目级别<font color="red">(注：鼠标移到“提示”字样上，会显示相应的提示信息)</font></span>
			</th>
		</tr>
	</thead>
	<tbody>		
		<tr style="height: 23px">
			<th align="right" width="15%">
				是否涉及金额：
			</th>
			<td align="left" width="35%">
				<html:radio name="rs" property="sfje" styleId="bje" onclick="showXmwh()" value="不需要"/>不需要
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio name="rs" property="sfje" styleId="je" onclick="showXmwh()" value="需要"/>需要									
			</td>
			<td align="left" colspan="2" >
				<span onmousemove="showTsDiv('jets')" onmouseout="hiddTsDiv('jets')">
					<font color="blue">提示：</font>
					<font color="blue" id="jets" style="display : none">
					设置该项目是否与金额挂钩，若无金额（如家庭情况调查），请选择不需要。
					</font>
				</span>
			</td>
		</tr>
		<tr>
			<th align="right">
				是否分级：
			</th>
			<td>												
				<html:radio name="rs" property="sffj" styleId="bfj" value="不分级" onclick="showXmwh()"/>不分级
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio name="rs" property="sffj" styleId="fj" value="分级" onclick="showXmwh()"/>分级
			</td>
			<td align="left" colspan="2">
				<span onmousemove="showTsDiv('fjts')" onmouseout="hiddTsDiv('fjts')">
					<font color="blue">提示：</font>
					<font color="blue" id="fjts" style="display : none">
					设置该项目是否需要分级，若需要（如困难补助分一二三等），请选择分级。
					</font>
				</span>
			</td>
		</tr>
		<tr id="jelxTr" style="display : none">
			<th align="right">
				金额类型：
			</th>
			<td>
				<html:radio name="rs" property="jelx" styleId="qdz" value="确定值" onclick="showXmwh()"/>确定值
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:radio name="rs" property="jelx" styleId="qj" value="区间" onclick="showXmwh()"/>区间
				</td>
				<td align="left" colspan="2">
				<span onmousemove="showTsDiv('jezts')" onmouseout="hiddTsDiv('jezts')">
					<font color="blue">提示：</font>
					<font color="blue" id="jezts" style="display : none">
					设置金额的类型，若是确定值的话，审核时老师不可以再对获得金额进行修改。
						</font>
				</span>
			</td>
		</tr>
		<!-- 确定值金额 -->
		<tr id="qdjeTr" style="display : none">
			<th align="right">
				金额：
			</th>
			<td>
				<html:text property="nofjje" 
					onkeydown="return onlyNum(this,5)"
					onmousedown="return onlyNum(this,5)"
					style="width:30%;ime-mode:disabled"/>
			</td>
			<td>
				
			</td>
			<td>				
			</td>
		</tr>
		<!-- 区间金额 -->
		<tr id="qjTr" style="display : none">
			<th align="right">
				金额下限：
			</th>
			<td>
				<html:text property="nofjxx" 
					onkeydown="return onlyNum(this,5)"
					onmousedown="return onlyNum(this,5)"
					style="width:30%;ime-mode:disabled"/>
			</td>
			<th align="right" align="right" width="15%">
				金额上限：
			</th>
			<td>
				<html:text property="nofjsx"
					onkeydown="return onlyNum(this,5)"
					onmousedown="return onlyNum(this,5)"
					style="width:30%;ime-mode:disabled"/>
			</td>
		</tr>
		<!-- 无金额分级 -->
		<tr id="noje" style="display : none">
			<td colspan="4">
				<p>
					<input  value="+" onclick="trAdd('jbnr','add','jbnrAdd')" style="width: 20px"/>
					<input  value="-" onclick="trDel('jbnr','delRow1')" style="width: 20px"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="jbnrAdd" id="jbnrAdd" style="width: 20px" onblur="trAdd('jbnr','madd','jbnrAdd')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="jbnrDel" id="jbnrDel" style="width: 20px" onblur="trDelAll('jbnr','jbnrDel')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>				
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width : 5%'>
								请选择
							</td>				
							<td align="center" nowrap="nowrap" style=''>
								级别名称
							</td>
						</tr>
					</thead>		
					<tbody width="100%" class="tbstyle" id="jbnr">
					</tbody>	
				</table>			
			</td>
		</tr>
		<!-- 确定金额分级 -->
		<tr id="qdje" style="display : none">
			<td colspan="4">
				<p>
					<input  value="+" onclick="trAdd('qdnr','add','qdnrAdd')" style="width: 20px"/>
					<input  value="-" onclick="trDel('qdnr','delRow2')" style="width: 20px"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="jbnrAdd" id="qdnrAdd" style="width: 20px" onblur="trAdd('qdnr','madd','qdnrAdd')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="jbnrDel" id="qdnrDel" style="width: 20px" onblur="trDelAll('qdnr','qdnrDel')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>				
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width : 5%'>
								请选择
							</td>				
							<td align="center" nowrap="nowrap" style='width : 40%'>
								级别名称
							</td>
							<td align="center" nowrap="nowrap" style=''>
								金额
							</td>
						</tr>
					</thead>		
					<tbody style="height: 23px" id="qdnr">
					</tbody>	
				</table>			
			</td>
		</tr>
		<!-- 区间金额分级 -->
		<tr id="qjje" style="display : none">
			<td colspan="4">
				<p>
					<input  value="+" onclick="trAdd('qjnr','add','qjnrAdd')" style="width: 20px"/>
					<input  value="-" onclick="trDel('qjnr','delRow3')" style="width: 20px"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="jbnrAdd" id="qjnrAdd" style="width: 20px" onblur="trAdd('qjnr','madd','qjnrAdd')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="jbnrDel" id="qjnrDel" style="width: 20px" onblur="trDelAll('qjnr','qjnrDel')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>				
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<td align="center" nowrap="nowrap" style='width : 5%'>
								请选择
							</td>				
							<td align="center" nowrap="nowrap" style='width : 45%'>
								级别名称
							</td>
							<td align="center" nowrap="nowrap" style='25%'>
								金额下限
							</td>
							<td align="center" nowrap="nowrap" style='25%'>
								金额上限
							</td>
						</tr>
					</thead>		
					<tbody width="100%" class="tbstyle" id="qjnr">
					</tbody>	
				</table>			
			</td>
		</tr>	
	</tbody>
</table>