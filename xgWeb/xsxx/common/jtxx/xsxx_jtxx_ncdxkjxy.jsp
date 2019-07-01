<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:equal name="userOnLine" value="teacher" scope="session">
<%-- 南昌大学科学技术学院辅导员修改学生信息--%>
	<table width="100%" class="formlist">
		<thead align="center">
			<tr>
				<th colspan="4"><span>学生家庭信息</span></th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<th><span class="red">*</span>学号</th>
			<td>
				<logic:equal value="update" name="oper">
					<html:text property="xh" readonly="true" styleId="xh"
						style="cursor:hand" />
				</logic:equal>
				<logic:equal value="add" name="oper">
					<html:text name="rs" property="xh" styleId="xh"
						onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
					<button type="button" align="left" class="button2"
						onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
						id="buttonFindStu">
						选择
					</button>
				</logic:equal>
			</td>
			<th><bean:message key="lable.xsgzyxpzxy" /></th>
			<td>
				${rs.xymc}
			</td>
		</tr>
		<tr>
			<th>年级</th>
			<td>
				${rs.nj}
			</td>
			<th>专业</th>
			<td >
				${rs.zymc}
			</td>
		</tr>
		<tr>
			<th>姓名</th>
			<td>
				${rs.xm}
			</td>
			<th>班级</th>
			<td>
				${rs.bjmc}
			</td>
		</tr>
		<tr>
			<th>家庭地址</th>
			<td>
				<html:text name="rs" property="jtszd" maxlength="25" style="width:100%"/>
			</td>
			<th>邮政编码</th>
			<td>
				<html:text name="rs" property="jtyb" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10" style="width:100%"/>
			</td>
		</tr>
		<tr>
			<th>家庭电话</th>
			<td colspan="3">
				<html:text name="rs" property="lxdh1" maxlength="25" style="width:100%"/>
			</td>
		</tr>
		<tr>
			<th>家庭经济状况</th>
			<td colspan="3">
				<html:textarea name="rs" property="jjzk"
					style="width:100%;height:45" styleId="jjzk" ></html:textarea>
			</td>
		</tr>
	  </tbody>
	</table>

	<table width="100%" class="formlist">
		<thead>
			<tr>
				<td style="cursor:hand" align="center"
					onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
					学生家庭成员信息1
				</td>
			</tr>
		</thead>
		<tbody>
		<tr id="jt1">
			<td>
				<table width="100%" class="formlist">
					<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<html:text name="rs" property="jtcy1_xm" styleId="xm" maxlength="25"/>
							</td>
							<th>与本人关系</th>
							<td>
								<html:text name="rs" property="jtcy1_gx" styleId="ch" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>出生日期</th>
							<td>
								<html:text name="rs" property="jtcy1_nl" styleId="nl"
									onclick="return showCalendar('jtcy1_nl','y-mm-dd');" readonly="true"/>
							</td>
							<th>身份证号</th>
							<td>
								<html:text name="rs" property="jtcy1_sfzh" styleId="sfzh" />
							</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>
								<html:select name="rs" property="jtcy1_mz"
									styleId="jtcy1_mz" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select name="rs" property="jtcy1_zzmm"
									styleId="jtcy1_zzmm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>职业</th>
							<td>
								<html:text name="rs" property="jtcy1_zy" styleId="jtcy1_zy" maxlength="10"/>

							</td>
							<th>职务</th>
							<td>
								<html:text name="rs" property="jtcy1_zw" styleId="jtcy1_zw" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>工作单位电话</th>
							<td>
								<html:text name="rs" property="jtcy1_lxdh2" styleId="lxdh2" maxlength="25"/>
							</td>
							<th>个人电话</th>
							<td>
								<html:text name="rs" property="jtcy1_lxdh1" styleId="lxdh1" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy1_gzdz" styleId="gzdz" maxlength="25"/>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		</tbody>

		<thead>
			<tr>
				<td style="cursor:hand" align="center"
					onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
					学生家庭成员信息2
				</td>
			</tr>
		</thead>
		
		<tr id="jt2">
			<td> 
				<table width="100%" class="formlist">
					<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<html:text name="rs" property="jtcy2_xm" styleId="jtcy2_xm" maxlength="25"/>
							</td>
							<th>与本人关系</th>
							<td>
								<html:text name="rs" property="jtcy2_gx" styleId="jtcy2_gx" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>出生日期</th>
							<td>
								<html:text name="rs" property="jtcy2_nl" styleId="jtcy2_nl"
									onclick="return showCalendar('jtcy2_nl','y-mm-dd');" readonly="true"/>
							</td>
							<th>身份证号</th>
							<td>
								<html:text name="rs" property="jtcy2_sfzh"
									styleId="jtcy2_sfzh" />
							</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>
								<html:select name="rs" property="jtcy2_mz"
									styleId="jtcy2_mz" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select name="rs" property="jtcy2_zzmm"
									styleId="jtcy2_zzmm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>职业</th>
							<td>
								<html:text name="rs" property="jtcy2_zy" styleId="jtcy2_zy" maxlength="10"/>
							</td>
							<th>职务</th>
							<td>
								<html:text name="rs" property="jtcy2_zw" styleId="jtcy2_zw" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>工作单位电话</th>
							<td>
								<html:text name="rs" property="jtcy2_lxdh2"
									styleId="jtcy2_lxdh2" maxlength="25"/>
							</td>
							<th>个人电话</th>
							<td>
								<html:text name="rs" property="jtcy2_lxdh1"
									styleId="jtcy2_lxdh1" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy2_gzdz"
									styleId="jtcy2_gzdz" maxlength="25"/>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		</tbody>

		<thead>
			<tr>
				<td style="cursor:hand" align="center"
					onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
					学生家庭成员信息3
				</td>
			</tr>
		</thead>
		<tr id="jt3" style="display:none">
			<td>
				<table width="100%" class="formlist">
					<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<html:text name="rs" property="jtcy3_xm" styleId="jtcy3_xm" maxlength="25"/>
							</td>
							<th>与本人关系</th>
							<td>
								<html:text name="rs" property="jtcy3_gx" styleId="jtcy3_gx" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>出生日期</th>
							<td>
								<html:text name="rs" property="jtcy3_nl" styleId="jtcy3_nl"
									onclick="return showCalendar('jtcy3_nl','y-mm-dd');" readonly="true"/>
							</td>
							<th>身份证号</th>
							<td>
								<html:text name="rs" property="jtcy3_sfzh"
									styleId="jtcy3_sfzh" />
							</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>
								<html:select name="rs" property="jtcy3_mz"
									styleId="jtcy3_mz" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select name="rs" property="jtcy3_zzmm"
									styleId="jtcy3_zzmm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>职业</th>
							<td>
								<html:text name="rs" property="jtcy3_zy" styleId="jtcy3_zy" maxlength="10"/>
							</td>
							<th>职务</th>
							<td>
								<html:text name="rs" property="jtcy3_zw" styleId="jtcy3_zw" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>工作单位电话</th>
							<td>
								<html:text name="rs" property="jtcy3_lxdh2"
									styleId="jtcy3_lxdh2" maxlength="25"/>
							</td>
							<th>个人电话</th>
							<td>
								<html:text name="rs" property="jtcy3_lxdh1"
									styleId="jtcy3_lxdh1" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy3_gzdz"
									styleId="jtcy3_gzdz" maxlength="25"/>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		</tbody>
	</table>
</logic:equal>