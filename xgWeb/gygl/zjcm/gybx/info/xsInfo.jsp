<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>������Ϣ</span>
			</th>
		</tr>
	</thead>
	<tbody>	
		<tr style="height: 23px">
			<th align="right" width="15%">
				<!-- ����ģ�� -->
				<logic:equal name="mklx" value="sq">
					<font color="red">*</font>
				</logic:equal>
				ѧ��
			</th>
			<td align="left" width="35%">
				<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
				<!-- �����ģ�� -->
				<logic:notEqual name="mklx" value="sh">
					<!-- ��ѧ�� -->
					<logic:notEqual name="userType" value="stu">
						<button onclick="sendXx();return false;"
								class="button2"
								id="buttonFindStu">
							ѡ��
						</button>
					</logic:notEqual>
				</logic:notEqual>
			</td>
			<th align="right" width="15%">
				����
			</th>
			<td align="left" width="35%">
				${rs.xm }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				�꼶
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
				רҵ
			</th>
			<td align="left" width="">
				${rs.zymc }
			</td>
			<th align="right" width="">
				�༶
			</th>
			<td align="left" width="">
				${rs.bjmc }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				У��
			</th>
			<td align="left" width="">
				${rs.xqmc }
			</td>
			<th align="right" width="">
				¥��
			</th>
			<td align="left" width="">
				${rs.ldmc }
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				����
			</th>
			<td align="left" width="">
				${rs.qsh }
			</td>
			<th align="right" width="">
				<logic:equal name="mklx" value="sq">
					<font color="red">*</font>
				</logic:equal>
				��ϵ�绰
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
				����ʱ��
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
				ϣ��ά��ʱ��
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
						/>��
					--
					<html:text name="rs" property="xwjssj" styleId="xwjssj" style="width:15px;ime-mode:disabled"
						onkeydown="return onlyNum(this,2)" onblur="checkXwsj()"
						onmousedown="return onlyNum(this,2)"
						/>��
					)
				</logic:equal>
				<logic:notEqual name="mklx" value="sq">
					${rs.xwrq }&nbsp;&nbsp;${rs.xwkssj }��--${rs.xwjssj }��
				</logic:notEqual>
			</td>
				</tr>
				<tr style="height: 23px">
			<th align="right" width="">
				��������
				<br>
				<font color="red">��¼��500��</font>
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