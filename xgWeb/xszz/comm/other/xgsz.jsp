<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>项目基本情况</span>
			</th>
		</tr>
	</thead>
	<tbody>		
					<tr style="height: 23px">
						<th align="right" width="15%">
							开关状态：
						</th>
						<td align="left" colspan="3">
							<html:radio name="rs" property="kgzt" onclick="" value="开放申请"/>开放申请
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="kgzt" onclick="" value="关闭申请"/>关闭申请			
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="kgzt" onclick="" value="项目关闭"/>项目关闭						
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('kgts')" onmouseout="hiddTsDiv('kgts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>
								<font color="blue" id="kgts" style="display : none">
								申请开放关闭涉及到学生是否可以申请，项目关闭则在申请，审核，结果查询等模块都不再出现该项目。
								</font>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							审核级别：
						</th>
						<td width="35%">
							<html:select name="rs" property="shjb" style="" styleId="shjb" onchange="showShjb()">
								<html:options collection="shjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" colspan="2">
							
						</td>
					</tr>
					<tr id="shjbTr" style="display : none">
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="shr" id="bjsh" onclick="clickBjsh()" value="班级审核"/>班级审核
							(
							<html:radio name="rs" property="lssh" styleId="bzrsh" value="班主任" onclick="clickBzrFdy()"/>班主任
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="lssh" styleId="fdysh" value="辅导员" onclick="clickBzrFdy()"/>辅导员		
							)
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="shr" id="xysh" value="<bean:message key="lable.xb" />审核"><bean:message key="lable.xsgzyxpzxy" />审核
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="shr" id="xxsh" value="学校审核">学校审核
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('shts')" onmouseout="hiddTsDiv('shts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>	
								<div id="shts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">设置项目需要几级审核。</font><br>				
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">1：若选择一级审核而未指明具体级别，则任意级别皆有权限对项目进行审核。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">2：若选择二级审核而未指明具体级别，则默认审核顺序为(<bean:message key="lable.xb" />-->学校),若仅指定<bean:message key="lable.xb" />审核，顺序为(<bean:message key="lable.xb" />-->学校),</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">若仅指定班级审核，顺序为(班级--><bean:message key="lable.xb" />或学校)，若仅指定学校审核，顺序为(班级或<bean:message key="lable.xb" />-->学校)。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">3：若选择三级审核而未指明具体级别，则默认审核顺序为(辅导员--><bean:message key="lable.xb" />-->学校)。</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							人数控制：
						</th>
						<td width="">
							<html:select name="rs" property="rskz" style="" styleId="rskz" onchange="showRsjb();showKzjb()">
								<html:options collection="rsjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th align="right">
							控制范围：
						</th>
						<td width="">
							<html:select name="rs" property="kzjb" style="" styleId="kzjb" onchange="">
								<html:options collection="kzjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr id="rsjbTr" style="display : none">
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="kzr" id="bjkz" value="班级审核"/>班级审核
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="kzr" id="xykz" value="<bean:message key="lable.xb" />审核"/><bean:message key="lable.xb" />审核
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="kzr" id="xxkz" value="学校审核"/>学校审核
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('jbts')" onmouseout="hiddTsDiv('jbts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>	
								<div id="jbts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">设置项目是否需要进行人数上限控制,若存在需要控制的情况：</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">1：请在人数设置处对人数进行具体的设置，若选择需要控制而又未设置具体人数，则学生无法申请。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">2：请选择控制的范围，默认根据<bean:message key="lable.xb" />来进行统计人数。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">3：请选择统计的条件，默认是统计<bean:message key="lable.xb" />审核为通过的学生数。</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							人数上限：
						</th>
						<td width="">
							<html:text name="rs" property="rssx"  
							styleId="rssx"
							onkeydown="return onlyNum(this,5)"
							onmousedown="return onlyNum(this,5)"
							style="width:30%;ime-mode:disabled"/>人<span id=""><font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(请录入整数)</font></span>
						</td>
						<td colspan="2">
							
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('rsts')" onmouseout="hiddTsDiv('rsts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>	
								<div id="rsts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">设置该项目是否存在人数上限（与上述人数控制概念略有不同，请注意）。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">例如：困难补助虽然需要控制每个<bean:message key="lable.xb" />的人数，但是项目本身的没有人数上限，该值为空。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">国家奖学金最多一共只能有500人，具体每个<bean:message key="lable.xb" />有多少不管怎么设置不能超过该值，则该值维护为500。</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							申请周期：
						</th>
						<td align="left" colspan="3">
							<html:radio name="rs" property="sqzq" onclick="" value="学年"/>学年
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="学期"/>学期	
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="年度"/>年度
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="无周期"/>无周期
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="sqzq" onclick="" value="仅一次"/>仅一次
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('zqts')" onmouseout="hiddTsDiv('zqts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>	
								<div id="zqts" style="display : none">		
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">设置该项目的申请是每学年一次还是每学期（年度）一次，无周期的话每天可以申请。</font><br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="blue">注意:如果学生已经申请了该项目，校方突然修改了申请周期，那么已经申请者的信息可能会有异常，请小心。</font><br>
								</div>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							评定时间：
						</th>
						<td align="left" colspan="3">
							<html:select name="rs" property="pdsj" style="" styleId="pdsj" onchange="">
								<html:options collection="pdsjList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('sjts')" onmouseout="hiddTsDiv('sjts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>		
								<font color="blue" id="sjts" style="display : none">设置申请该项目的时间为本学年还是上一学年（具体的是学年，学期还是年度，根据申请周期定）。</font>
							</span>
						</td>
					</tr>
	</tbody>
</table>
