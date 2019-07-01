<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>报修信息</span>
			</th>
		</tr>
	</thead>
	<tbody>	
		<tr style="height: 23px">
			<th align="right" width="15%">
				<!-- 申请模块 -->
				<logic:equal name="mklx" value="sq">
					<font color="red">*</font>
				</logic:equal>
				学号
			</th>
			<td align="left" width="35%">
				<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
				<!-- 非审核模块 -->
				<logic:notEqual name="mklx" value="sh">
					<!-- 非学生 -->
					<logic:notEqual name="userType" value="stu">
						<button onclick="sendXx();return false;"
								class="button2"
								id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
				</logic:notEqual>
			</td>
			<th align="right" width="15%">
				姓名
			</th>
			<td align="left" width="35%">
				${rs.xm }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				年级
			</th>
			<td align="left" width="">
				${rs.nj }
			</td>
			<th align="right" width="">
				<bean:message key="lable.xsgzyxpzxy" />
			</th>
			<td align="left" width="">
				${rs.xymc }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				专业
			</th>
			<td align="left" width="">
				${rs.zymc }
			</td>
			<th align="right" width="">
				班级
			</th>
			<td align="left" width="">
				${rs.bjmc }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				校区
			</th>
			<td align="left" width="">
				${rs.xqmc }
			</td>
			<th align="right" width="">
				楼栋
			</th>
			<td align="left" width="">
				${rs.ldmc }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				寝室
			</th>
			<td align="left" width="">
				${rs.qsh }
			</td>
			<th align="right" width="">
				<logic:equal name="mklx" value="sq">
					<font color="red">*</font>
				</logic:equal>
				联系电话
			</th>
			<td align="left" width="">
				<logic:equal name="mklx" value="sq">
					<html:text name="rs" property="bxlxdh" 
						styleId="lxdh" style="width:100px;ime-mode:disabled"
						onkeydown="return onlyNum(this,15)"
						onmousedown="return onlyNum(this,15)"/>
				</logic:equal>
				<logic:notEqual name="mklx" value="sq">
					${rs.bxlxdh }
				</logic:notEqual>
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				报修时间
			</th>
			<td align="left" width="">
				<logic:equal name="mklx" value="sq">
				${rs.bxsjmc }
				</logic:equal>
				<logic:notEqual name="mklx" value="sq">
				${rs.bxrq }
				</logic:notEqual>
				<html:hidden name="rs" property="bxsj" styleId="bxsj"/>
			</td>
			<th align="right" width="">
				希望维修时间
			</th>
			<td align="left" width="">
				<logic:equal name="mklx" value="sq">
					<html:text name="rs" property="xwsj" styleId="xwsj" readonly="true"
						onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
						onclick="return showCalendar('xwsj','y-mm-dd');"/>
					(
					<html:text name="rs" property="xwkssj" styleId="xwkssj" style="width:15px;ime-mode:disabled"
						onkeydown="return onlyNum(this,2)" onblur="checkXwsj()"
						onmousedown="return onlyNum(this,2)"
						/>点
					--
					<html:text name="rs" property="xwjssj" styleId="xwjssj" style="width:15px;ime-mode:disabled"
						onkeydown="return onlyNum(this,2)" onblur="checkXwsj()"
						onmousedown="return onlyNum(this,2)"
						/>点
					)
				</logic:equal>
				<logic:notEqual name="mklx" value="sq">
					${rs.xwrq }&nbsp;&nbsp;${rs.xwkssj }点--${rs.xwjssj }点
				</logic:notEqual>
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				报修内容
				<br>
				<font color="red">限录入500字</font>
			</th>
			<td align="left" colspan="3">
				<logic:equal name="mklx" value="sq">
					<html:textarea name="rs" property="bxnr" styleId="bxnr" onblur="chLeng(this,500)" rows="5" style="width : 500px"></html:textarea>
				</logic:equal>
				<logic:notEqual name="mklx" value="sq">
					<html:textarea name="rs" property="bxnr" styleId="bxnr" onblur="chLeng(this,500)" rows="5" style="width : 500px" readonly="true"></html:textarea>
				</logic:notEqual>
				
			</td>
		</tr>
	</tbody>
</table>