<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:notEmpty name="gwcxview">
	<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4"><span>学生基本信息</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>学号</th>
				<td>
					${userName}
				</td>
				<th>姓名</th>
				<td>
					${rs.xm}
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc}
				</td>
				<th>专业</th>
				<td >
					${rs.zymc}
				</td>
			</tr>
			<tr>
				<th>年级</th>
				<td>
					${rs.nj}
				</td>
				<th>班级</th>
				<td>
					${rs.bjmc}
				</td>
			</tr>
			<%--湖北交通职业技术学院--%>
				<logic:equal value="12752" name="xxdm">
				<tr>
					<th>档案号</th>
					<td>
						<html:text property="dah" name="rs" styleId="dah" maxlength="30"/>
					</td>
					<th>医疗保险号</th>
					<td>
						<html:text property="ylbxh" name="rs" styleId="ylbxh" maxlength="30"/>
					</td>
				</tr>
				<tr>
					<th>QQ号码</th>
					<td>
						<html:text property="qqhm" name="rs" styleId="qqhm" maxlength="30" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th></th>
					<td>
						
					</td>
				</tr>					
				</logic:equal>
				<%--end湖北交通职业技术学院--%>
			<tr>
				<th>本人联系电话</th>
				<td>
					<input type="text" style="width:200px" name="lxdh1" id="lxdh1"
						value="<bean:write name="rs" property="lxdh1"/>"
						maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') " />
				</td>
				<th>电子邮箱</th>
				<td>
					<input type="text" style="width:200px" name="email" id="dzyx"
						value="<bean:write name="rs" property="dzyx"/>" />
				</td>				
			</tr>
			<tr>
				<th>手机号码</th>
				<td>
					<input type="text" style="width:200px" name="sjhm" id="sjhm"
						value="<bean:write name="rs" property="sjhm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
				</td>
				<th></th>
				<td>
					
				</td>
			</tr>
			<tr>
				<th>银行名称</th>
				<td>
					<html:select name="rs" property="yhdm" styleId="yhdm" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="yhList" property="yhdm" labelProperty="yhmc" />
					</html:select>
				</td>
				<th>银行卡号</th>
				<td>
					<input type="text" style="width:200px" name="yhkh" id="yhkh"
						value="<bean:write name="rs" property="yhkh"/>"
						onkeyup="value=value.replace(/[^\d|-]/g,'') " maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>家庭联系电话</th>
				<td>
					<input type="text" style="width:200px" name="lxdh2" id="lxdh2"
						value="<bean:write name="rs" property="lxdh2"/>"
						onkeyup="value=value.replace(/[^\d|-]/g,'') " maxlength="13" />
				</td>
				
				<th>家庭邮编</th>
				<td>
					<input type="text" style="width:200px" name="yb" id="yb"
						value="<bean:write name="rs" property="yb"/>" maxlength="10"
						onkeyup="value=value.replace(/[^\d]/g,'') " />
				</td>
			</tr>
			<tr>
				<th>
					<!--乐山师范学院-->
					<logic:equal value="10649" name="xxdm">
						家庭通讯地址
					</logic:equal>
					<!--end乐山师范学院-->
					<!--非乐山师范学院-->
					<logic:notEqual value="10649" name="xxdm">
						家庭所在地
					</logic:notEqual>
					<!--end非乐山师范学院-->
				</th>
				<td colspan="3">
					<input type="text" style="width:400px" name="jtszd" id="jtszd"
						value="<bean:write name="rs" property="jtszd"/>"
						maxlength="120" />
				</td>
			</tr>
<!--			<tr>-->
<!--				<th>本人通信地址</th>-->
<!--				<td colspan="3">-->
<!--					<input type="text" style="width:610px" name="brtxdz"-->
<!--						id="brtxdz" value="<bean:write name="rs" property="brtxdz"/>"-->
<!--						maxlength="50" />-->
<!--				</td>-->
<!--			</tr>-->
			</tbody>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
						<span>学生家庭成员信息1</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt1">
				<td colspan="4">
					<table width="100%" class="formlist">
						<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_xm"
									id="jtcy1_xm"
									value="<bean:write name="rs" property="jtcy1_xm"/>"
									maxlength="25" />
							</td>
							<th>与本人关系</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_gx"
									id="jtcy1_gx"
									value="<bean:write name="rs" property="jtcy1_gx"/>"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>出生日期</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_nl"
									id="jtcy1_nl"
									value="${rs.jtcy1_nl}"
									maxlength="20" 
									onclick="return showCalendar('jtcy1_nl','y-mm-dd');" />
							</td>
							<th>身份证号码</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_sfzh"
									id="jtcy1_sfzh"
									value="<bean:write name="rs" property="jtcy1_sfzh"/>"
									maxlength="20" />
							</td>							
						</tr>
						<%--非东北林业大学--%>
						<logic:notEqual value="10225" name="xxdm">
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
						</logic:notEqual>
						<%--end非东北林业大学--%>
						<tr>
							<th>职业</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_zy"
									id="jtcy1_zy"
									value="<bean:write name="rs" property="jtcy1_zy"/>"
									maxlength="10" />
							</td>
							<th>职务</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_zw"
									id="jtcy1_zw"
									value="<bean:write name="rs" property="jtcy1_zw"/>"
									maxlength="10" />
							</td>							
						</tr>
						<tr>
							<th>工作单位电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_lxdh2"
									id="jtcy1_lxdh2"
									value="<bean:write name="rs" property="jtcy1_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>个人电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_lxdh1"
									id="jtcy1_lxdh1"
									value="<bean:write name="rs" property="jtcy1_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<input type="text" style="width:610px" name="jtcy1_gzdz"
									id="jtcy1_gzdz"
									value="<bean:write name="rs" property="jtcy1_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<!--湖北交通职业技术学院-->
						<logic:equal value="12752" name="xxdm">
						<tr>
							<th>宗教信仰</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy1_zjxy"
									styleId="jtcy1_zjxy" maxlength="50"/>
							</td>
						</tr>
						</logic:equal>
						<!--end湖北交通职业技术学院-->
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
						<span>学生家庭成员信息2</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt2" style="display:none">
				<td colspan="4">
					<table width="100%" class="formlist">
						<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_xm"
									id="jtcy2_xm"
									value="<bean:write name="rs" property="jtcy2_xm"/>"
									maxlength="25" />
							</td>
							<th>与本人关系</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_gx"
									id="jtcy2_gx"
									value="<bean:write name="rs" property="jtcy2_gx"/>"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>出生日期</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_nl"
									id="jtcy2_nl"
									value="${rs.jtcy2_nl}"
									maxlength="20" 
									onclick="return showCalendar('jtcy2_nl','y-mm-dd');" />
							</td>
							<th>身份证号码</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_sfzh"
									id="jtcy2_sfzh"
									value="<bean:write name="rs" property="jtcy2_sfzh"/>"
									maxlength="20" />
							</td>							
						</tr>
						<%--非东北林业大学--%>
						<logic:notEqual value="10225" name="xxdm">
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
						</logic:notEqual>
						<%--end非东北林业大学--%>
						<tr>
							<th>职业</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_zy"
									id="jtcy2_zy"
									value="<bean:write name="rs" property="jtcy2_zy"/>"
									maxlength="10" />
							</td>
							<th>职务</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_zw"
									id="jtcy2_zw"
									value="<bean:write name="rs" property="jtcy2_zw"/>"
									maxlength="10" />
							</td>
							
						</tr>
						<tr>						
							<th>工作单位电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_lxdh2"
									id="jtcy2_lxdh2"
									value="<bean:write name="rs" property="jtcy2_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>个人电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_lxdh1"
									id="jtcy2_lxdh1"
									value="<bean:write name="rs" property="jtcy2_lxdh1"/>"
									maxlength="13" onkeyup="value=value.replace(/[^\d]/g,'') " />
							</td>
						</tr>
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<input type="text" style="width:610px" name="jtcy2_gzdz"
									id="jtcy2_gzdz"
									value="<bean:write name="rs" property="jtcy2_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<!--湖北交通职业技术学院-->
						<logic:equal value="12752" name="xxdm">
						<tr>
							<th>宗教信仰</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy2_zjxy"
									styleId="jtcy2_zjxy" maxlength="50"/>
							</td>
						</tr>
						</logic:equal>
						<!--end湖北交通职业技术学院-->
					</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
						<span>学生家庭成员信息3</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt3" style="display:none">
				<td colspan="4">
					<table width="100%" class="formlist">
					<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_xm"
									id="jtcy3_xm"
									value="<bean:write name="rs" property="jtcy3_xm"/>"
									maxlength="16" />
							</td>
							<th>与本人关系</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_gx"
									id="jtcy3_gx"
									value="<bean:write name="rs" property="jtcy3_gx"/>"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>出生日期</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_nl"
									id="jtcy3_nl"
									value="${rs.jtcy3_nl}"
									maxlength="20" 
									onclick="return showCalendar('jtcy3_nl','y-mm-dd');" />
							</td>
							<th>身份证号码</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_sfzh"
									id="jtcy3_sfzh"
									value="<bean:write name="rs" property="jtcy3_sfzh"/>"
									maxlength="20" />
							</td>
							
						</tr>
						<%--非东北林业大学--%>
						<logic:notEqual value="10225" name="xxdm">
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
						</logic:notEqual>
						<%--end非东北林业大学--%>
						<tr>
							<th>职业</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_zy"
									id="jtcy3_zy"
									value="<bean:write name="rs" property="jtcy3_zy"/>"
									maxlength="10" />
							</td>
							<th>职务</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_zw"
									id="jtcy3_zw"
									value="<bean:write name="rs" property="jtcy3_zw"/>"
									maxlength="10" />
							</td>							
						</tr>
						<tr>
							<th>工作单位电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_lxdh2"
									id="jtcy3_lxdh2"
									value="<bean:write name="rs" property="jtcy3_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>个人电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_lxdh1"
									id="jtcy3_lxdh1"
									value="<bean:write name="rs" property="jtcy3_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<input type="text" style="width:610px" name="jtcy3_gzdz"
									id="jtcy3_gzdz"
									value="<bean:write name="rs" property="jtcy3_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
					   <!--湖北交通职业技术学院-->
						<logic:equal value="12752" name="xxdm">
						<tr>
							<th>宗教信仰</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy3_zjxy"
									styleId="jtcy3_zjxy" maxlength="50"/>
							</td>
						</tr>
						</logic:equal>
						<!--end湖北交通职业技术学院-->
					</tbody>
					</table>
				</td>
			</tr>
		</tbody>
		<tfoot>
	      <tr>
	        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
	          <div class="btn">
	            <button type="button" class="button2"
					onclick="saveInfo()">
					保 存 信 息
				</button>
	          </div>
	        </td>
	      </tr>
	    </tfoot>
		</table>
	</logic:notEmpty>