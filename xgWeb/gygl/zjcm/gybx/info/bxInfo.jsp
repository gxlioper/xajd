<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>维修信息</span>
			</th>
		</tr>
	</thead>
	<tbody>
		<tr style="height: 23px">
			<th align="right" width="15%">
				<font color="red">*</font>
				是否收费项目
			</th>
			<td align="left" width="35%">
				<html:select name="rs" property="sfsf" style="" styleId="sfsf" onchange="">
					<html:option value=""></html:option>
					<html:options collection="sfList" property="en" labelProperty="cn" />
				</html:select>
			</td>
			<th align="right" width="15%">
				维修人员
			</th>
			<td align="left" width="35%">
				<html:text name="rs" property="wxry" styleId="wxry" maxlength="10"/>
			</td>
		</tr>
		<tr style="height: 23px">
			<th align="right" width="">
				材料费用
			</th>
			<td align="left" width="">
				<input type="text" name="clfy" id="clfy" readonly="readonly">
			</td>
			<th align="right" width="">
				<font color="red">*</font>
				维修总费用
			</th>
			<td align="left" width="">
				<html:text name="rs" property="wxfy" styleId="wxfy" 
					onkeydown="return onlyNum(this,10)"
					onmousedown="return onlyNum(this,10)"
					maxlength="10" style="ime-mode:disabled"/>
			</td>
		</tr>
		<tr style="height: 23px">
			<th align="right" width="">
				是否提交给维修部
			</th>
			<td align="left" width="">
				<html:select name="rs" property="sfwg" style="" styleId="sfwg" onchange="">
					<html:option value=""></html:option>
					<html:options collection="sfList" property="en" labelProperty="cn" />
				</html:select>
			</td>
			<th align="right" width="">
				提交时间
			</th>
			<td align="left" width="">
				<html:text name="rs" property="wgsj" styleId="wgsj" readonly="true"
					onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
					onclick="return showCalendar('wgsj','y-mm-dd');"/>	
				(
				<html:text name="rs" property="wgsjh" styleId="wgsjh" style="width:15px;ime-mode:disabled"
					onkeydown="return onlyNum(this,2)" onblur="checkTjsj()"
					onmousedown="return onlyNum(this,2)"
					/>点
				：
				<html:text name="rs" property="wgsjm" styleId="wgsjm" style="width:15px;ime-mode:disabled"
					onkeydown="return onlyNum(this,2)" onblur="checkTjsj()"
					onmousedown="return onlyNum(this,2)"
					/>分
				)
			</td>
		</tr>
		<tr style="height: 23px">
			<th align="right" width="">
				审核不通过原因
				<br>
				<font color="red">限录入500字</font>
			</th>
			<td align="left" colspan="3">
				<html:textarea name="rs" property="cljg" styleId="cljg" 
					onblur="chLeng(this,500)" rows="5" style="width : 500px">
				</html:textarea>
			</td>
		</tr>
	</tbody>
</table>

<p id="nrCtrl">
<input type="button" value="+" onclick="trAdd('nr','add','nrAdd','gybxcl')" style="width: 20px"/>
<input type="button" value="-" onclick="trDel('nr','delRow')" style="width: 20px"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
<input type="text" name="nrAdd" id="nrAdd" style="width: 20px" onblur="trAdd('nr','madd','nrAdd','gybxcl')">
&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
<input type="text" name="nrDel" id="nrDel" style="width: 20px" onblur="trDelAll('nr','nrDel')">
&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>	
<table class="formlist" align="center" width="100%" id="tTb">
	<tr>
		<td>
			<div class="mid_box">
				<table align="center" style="width: 100%" id="rsT" class="tbstyle">
					<thead style="height: 23px">
						<tr>
							<logic:iterate name="nrTitleList" id="nrTitle">
								<td align="center" nowrap="nowrap" style='5%'>
									${nrTitle.cn }
								</td>
							</logic:iterate>
						</tr>
					</thead>		
					<tbody width="100%" class="tbstyle" id="nr">
						</tbody>	
				</table>
			</div>
		</td>
	</tr>
</table>