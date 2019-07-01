<%@ page language="java" contentType="text/html; charset=GBK"%>
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
				<td>
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
						<html:text property="dah" name="rs" styleId="dah" maxlength="30"  />
					</td>
					<th>医疗保险号</th>
					<td>
						<html:text property="ylbxh" name="rs" styleId="ylbxh" maxlength="30" />
					</td>
				</tr>
				<tr>
					<th>QQ号码</th>
					<td>
						<html:text property="qqhm" name="rs" styleId="qqhm" maxlength="30"/>
					</td>
					<th></th>
					<td>
						
					</td>
				</tr>					
			</logic:equal>
			<%--end湖北交通职业技术学院--%>
			<tr>
				<th>联系电话</th>
				<td>
					<input type="text" name="lxdh1" id="lxdh1"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
				</td>
				<th>手机号码</th>
				<td>
					<input type="text" name="sjhm" id="sjhm"
						value="<bean:write name="rs" property="sjhm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
				</td>
			</tr>
			<tr>
				<th>电子邮箱</th>
				<td>
					<input type="text" name="email" id="dzyx"
						value="<bean:write name="rs" property="dzyx"/>" maxlength="32"
						onblur="if(this.value!='' && !isEmail(this.value)){alert('请输入正确的电子邮箱！');document.getElementById('dzyx').value='';document.getElementById('dzyx').focus();}"/>
				</td>
				<th>家庭所在地</th>
				<td>
					<input type="text" name="jtszd" id="jtszd"
						value="<bean:write name="rs" property="jtszd"/>"
						maxlength="25" />
				</td>
			</tr>
			<!--天津交通职业学院-->
			<logic:equal value="12883" name="xxdm">
			<tr>
                <td colspan="4">
                <%@ include file="/xsxx/xxjlxxb.jsp"%>
                </td>
            </tr>
			</logic:equal>
			<!--end天津交通职业学院-->
			<%--西昌学院--%>
			<logic:equal value="10628#" name="xxdm">
				<tr>
					<th>饭卡号</th>
					<td>
						<input type="text" name="kh" id="kh" maxlength="20"
							value="<bean:write name="rs" property="kh"/>" />
					</td>
					<th>一卡通</th>
					<td>
						<input type="text" name="ykth" id="ykth"
							value="<bean:write name="rs" property="ykth"/>"
							maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>银行卡号</th>
					<td>
						<input type="text" name="yhkh" id="yhkh" maxlength="20"
							value="<bean:write name="rs" property="yhkh"/>" />
					</td>
					<th></th>
					<td>

					</td>
				</tr>
			</logic:equal>
			<%--云南艺术--%>
			<logic:equal value="10690" name="xxdm">
				<tr>
					<th>籍贯</th>
					<td colspan="3">
						<html:select name="rs" property="jgs" onchange="loadShi()"
							styleId="jgshen">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="jgshi" styleId="jgshi"
							onchange="loadXian()">
							<html:options collection="shiList" property="shidm"
								labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="jgx" styleId="jgxian">
							<html:options collection="xianList" labelProperty="xianmc"
								property="xiandm" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>家庭年收入</th>
					<td>
						<html:text name="rs" property="jtzsr"
							onkeyup="value=value.replace(/[^\d]/g,'') " styleId="jtzsr" />
						元/年
					</td>
					<th>是否需要助学贷款</th>
					<td>
						<html:radio name="rs" property="sfdk" value="是"
							styleId="sfdks">是</html:radio>
						<html:radio name="rs" property="sfdk" value="否"
							styleId="sfdkf">否</html:radio>
					</td>

				</tr>
				<tr>
					<th>校外住宿详细地址</th>
					<td colspan="4">
						<html:textarea name="rs" property="xwzsxxdz"
							styleId="xwzsxxdz" style="width:100%;height:100%"></html:textarea>
					</td>
				</tr>
			</logic:equal>
			<%--安徽建筑工业学院--%>
			<logic:equal value="10878" name="xxdm">
				<tr>
					<th>出生日期</th>
					<td>
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');"
							styleId="csrq" readonly="true" />
					</td>
					<th>身份证号</th>
					<td>
						<html:text name="rs" property="sfzh" styleId="sfzh"
							maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>政治面貌</th>
					<td>
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
					<th>民族</th>
					<td>
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>籍贯</th>
					<td>
						<html:text property="jg" name="rs" styleId="jg" maxlength="10" />
					</td>
					<th>来源地区</th>
					<td>
						<html:text property="syd" name="rs" styleId="syd"
							maxlength="10" />
					</td>
				</tr>
				<tr>
					<th>学籍状态</th>
					<td>
						<html:select name="rs" property="xjzt" style="width:150">
							<html:option value=""></html:option>
							<html:option value="有学籍">有学籍</html:option>
							<html:option value="无学籍">无学籍</html:option>
						</html:select>
					</td>
					<th>卡号</th>
					<td>
						<html:text property="kh" name="rs" styleId="kh" maxlength="10" />
					</td>
				</tr>
			</logic:equal>
			<%--浙江机电职业技术学院--%> 
			<logic:equal value="12861" name="xxdm">
				<tr>
					<th>出生日期</th>
					<td>
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');"
							styleId="csrq" readonly="true" />
					</td>
					<th>家庭电话</th>
					<td>
						<html:text name="rs" property="jtdh" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>宿舍号</th>
					<td>
						<html:text property="ssbh" name="rs" styleId="ssbh"
							disabled="true" />
					</td>
					<th>寝室电话</th>
					<td>
						<html:text name="rs" property="qsdh" styleId="qsdh"
							disabled="true" />
					</td>
				</tr>
				<tr>
					<th>血型</th>
					<td>
						<html:text name="rs" property="xx" maxlength="10" />
					</td>
					<th>健康状况</th>
					<td>
						<html:text name="rs" property="jkzk" maxlength="125" />
					</td>
				</tr>
				<tr>
					<th>QQ号码</th>
					<td>
						<html:text name="rs" property="qqhm" maxlength="13"
							onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>
					<th>政治面貌</th>
					<td>
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>职务</th>
					<td>
						<html:text name="rs" property="zw" maxlength="32" />
					</td>
					<th></th>
					<td>

					</td>					
				</tr>
				<tr>
					<th>家庭经济情况</th>
					<td colspan="3">
						<html:radio name="rs" property="jtjjqk" value="1">宽裕</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="jtjjqk" value="2">一般</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="jtjjqk" value="3">困难</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="jtjjqk" value="4">特困</html:radio>
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<html:text name="rs" property="bz"
							style="width: 95%;height: 25px" maxlength="500" />
					</td>
				</tr>
			</logic:equal>
			<%--东北林业大学--%>
			<logic:equal value="10225" name="xxdm">
				<tr>
					<th>出生日期</th>
					<td>
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');"
							styleId="csrq" readonly="true" />
					</td>
					<th>身份证号</th>
					<td>
						<html:text name="rs" property="sfzh" styleId="sfzh"
							maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>政治面貌</th>
					<td>
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
					<th>民族</th>
					<td>
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>籍贯</th>
					<td>
						<html:text name="rs" property="jg" styleId="jgdb" 
						maxlength="10" />
					</td>
					<th>来源地区</th>
					<td>
						<html:text property="syd" name="rs" styleId="syd"
							maxlength="10" />
					</td>
				</tr>
				<tr>
					<th>爱好</th>
					<td>
						<html:text name="rs" property="ah" styleId="ah"
							maxlength="120" />
					</td>
					<th>特长</th>
					<td>
						<html:text name="rs" property="tc" styleId="tc" maxlength="32" />
					</td>
				</tr>
				<tr>
					<th>入党时间</th>
					<td>
						<html:text name="rs" property="jrgcdsj" styleId="jrgcdsj"
							onclick="return showCalendar('jrgcdsj','y-mm-dd');"
							readonly="true" />
					</td>
					<th>入团时间</th>
					<td>
						<html:text name="rs" property="jrgqtsj" styleId="jrgqtsj"
							onclick="return showCalendar('jrgqtsj','y-mm-dd');"
							maxlength="true" />
					</td>
				</tr>
				<tr>
					<th>宿舍号</th>
					<td>
						<html:text property="ssbh" name="rs" styleId="ssbh" />
					</td>
					<th>寝室电话</th>
					<td>
						<html:text name="rs" property="qsdh" styleId="qsdh" />
					</td>
				</tr>
				<tr>
					<th>是否贷款</th>
					<td>
						<html:radio name="rs" property="sfdk" value="0">否</html:radio>
						<html:radio name="rs" property="sfdk" value="1">是</html:radio>
					</td>
					<th>家庭邮编</th>
					<td>
						<html:text name="rs" property="jtyb" styleId="jtyb"
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10" />
					</td>
				</tr>
				<tr>
					<th>家庭详细地址</th>
					<td colspan="3">
						<html:text property="jtdz" name="rs"
							style="width:680;height:30" styleId="jtdz" maxlength="25" />
					</td>
				</tr>
				<tr>
					<th>家庭经济情况</th>
					<td colspan="3">
						<html:radio name="rs" property="jtjjqk" value="1">宽裕</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="jtjjqk" value="2">一般</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="jtjjqk" value="3">困难</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="jtjjqk" value="4">特困</html:radio>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<td colspan="5" style="cursor:hand" align="center"
						onclick="document.getElementById('shgx').style.display=(document.getElementById('shgx').style.display==''?'none':'')">
						哈尔滨市主要社会关系
					</td>
				</tr>
			</thead>
			<tbody>
				<tr id="shgx" style="display:none">
					<td colspan="5">
						<table class="formlist">
							<thead>
							<tr>
								<th><span>姓名</span></th>
								<th><span>与本人关系</span></th>
								<th><span>工作单位</span></th>
								<th><span>职务</span></th>
								<th><span>单位电话</span></th>
								<th><span>手机号码</span></th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td>
									<html:text name="rs" property="shgxxm1" styleId="shgxxm1"
										maxlength="16" />
								</td>
								<td>
									<html:text name="rs" property="shgxgx1" styleId="shgxgx1"
										maxlength="10" />
								</td>
								<td>
									<html:text name="rs" property="shgxgzdw1"
										styleId="shgxgzdw1" maxlength="120" />
								</td>
								<td>
									<html:text name="rs" property="shgxzw1" styleId="shgxzw1"
										maxlength="32" />
								</td>
								<td>
									<html:text name="rs" property="shgxdwdh1"
										styleId="shgxdwdh1"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
								<td>
									<html:text name="rs" property="shgxsjhm1"
										styleId="shgxsjhm1"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
							</tr>
							<tr>
								<td>
									<html:text name="rs" property="shgxxm2" styleId="shgxxm2"
										maxlength="16" />
								</td>
								<td>
									<html:text name="rs" property="shgxgx2" styleId="shgxgx2"
										maxlength="10" />
								</td>
								<td>
									<html:text name="rs" property="shgxgzdw2"
										styleId="shgxgzdw2" maxlength="120" />
								</td>
								<td>
									<html:text name="rs" property="shgxzw2" styleId="shgxzw2"
										maxlength="32" />
								</td>
								<td>
									<html:text name="rs" property="shgxdwdh2"
										styleId="shgxdwdh2"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
								</td>
								<td>
									<html:text name="rs" property="shgxsjhm2"
										styleId="shgxsjhm2"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
								</td>
							</tr>
						</tbody>
						</table>
					</td>
				</tr>
				</tbody>
			</logic:equal>
			<thead>
				<tr>
					<th colspan="4" style="cursor:hand" align="center"
						onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
						<span>学生家庭成员信息1</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<%--东北林业大学--%>
			<tr id="jt1"
			<logic:equal value="10225" name="xxdm">
				style="display:none"
			</logic:equal>
			<%--end东北林业大学--%>
			>
				<td colspan="4">
					<table width="100%" class="formlist">
						<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_xm"
									id="jtcy1_xm"
									value="<bean:write name="rs" property="jtcy1_xm"/>"
									maxlength="16" />
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
							<th>身份证号码</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_sfzh"
									id="jtcy1_sfzh"
									value="<bean:write name="rs" property="jtcy1_sfzh"/>"
									maxlength="20" onblur="checkSfzh(this);"/>
							</td>
							<th>出生日期</th>
							<td>
								<input onclick="return showCalendar('jtcy1_nl','y-mm-dd');"
									type="text" style="width:200px" name="jtcy1_nl"
									id="jtcy1_nl"
									value="<bean:write name="rs" property="jtcy1_nl"/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>
								<html:select name="rs" property="jtcy1_mz"
									styleId="jtcy1_mz" style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select name="rs" property="jtcy1_zzmm"
									styleId="jtcy1_zzmm" style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<%--云南艺术--%>
							<logic:equal value="10690" name="xxdm">
								<th>工作单位</th>
							</logic:equal>
							<logic:notEqual value="10690" name="xxdm">
								<th>职业</th>
							</logic:notEqual>
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
							<th>个人电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_lxdh1"
									id="jtcy1_lxdh1"
									value="<bean:write name="rs" property="jtcy1_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>工作单位电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy1_lxdh2"
									id="jtcy1_lxdh2"
									value="<bean:write name="rs" property="jtcy1_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<%--云南艺术--%>
							<logic:equal value="10690" name="xxdm">
								<th>单位地址</th>
							</logic:equal>
							<logic:notEqual value="10690" name="xxdm">
								<th>工作地址</th>
							</logic:notEqual>
							<td colspan="3">
								<input type="text" style="width:600px" name="jtcy1_gzdz"
									id="jtcy1_gzdz"
									value="<bean:write name="rs" property="jtcy1_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<%--云南艺术--%>
						<logic:equal value="10690" name="xxdm">
							<tr>
								<th>工作单位邮编</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy1_yzbm"
										styleId="jtcy1_yzbm" maxlength="10" style="width:600px"
										onkeyup="value=value.replace(/[^\d]/g,'') " />
								</td>
							</tr>
						</logic:equal>
						<%--浙江商业职业技术学院--%>
						<logic:equal value="12865" name="xxdm">
							<tr>
								<th>在杭联系人</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy1_lxr"
										styleId="jtcy1_lxr" maxlength="10" style="width:600px" />
								</td>
							</tr>
						</logic:equal>
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
									maxlength="16" />
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
							<th>身份证号码</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_sfzh"
									id="jtcy2_sfzh"
									value="<bean:write name="rs" property="jtcy2_sfzh"/>"
									maxlength="20"  onblur="checkSfzh(this);"/>
							</td>
							<th>出生日期</th>
							<td>
								<input type="text"
									onclick="return showCalendar('jtcy2_nl','y-mm-dd');"
									style="width:200px" name="jtcy2_nl" id="jtcy2_nl"
									value="<bean:write name="rs" property="jtcy2_nl"/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>
								<html:select name="rs" property="jtcy2_mz"
									styleId="jtcy2_mz" style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select name="rs" property="jtcy2_zzmm"
									styleId="jtcy2_zzmm" style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<%--云南艺术--%>
							<logic:equal value="10690" name="xxdm">
								<th>工作单位</th>
							</logic:equal>
							<logic:notEqual value="10690" name="xxdm">
								<th>职业</th>
							</logic:notEqual>
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
							<th>个人电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_lxdh1"
									id="jtcy2_lxdh1"
									value="<bean:write name="rs" property="jtcy2_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>工作单位电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy2_lxdh2"
									id="jtcy2_lxdh2"
									value="<bean:write name="rs" property="jtcy2_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<%--云南艺术--%>
							<logic:equal value="10690" name="xxdm">
								<th>单位地址</th>
							</logic:equal>
							<logic:notEqual value="10690" name="xxdm">
								<th>工作地址</th>
							</logic:notEqual>
							<td colspan="3">
								<input type="text" style="width:600px" name="jtcy2_gzdz"
									id="jtcy2_gzdz"
									value="<bean:write name="rs" property="jtcy2_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<%--云南艺术--%>
						<logic:equal value="10690" name="xxdm">
							<tr>
								<th>工作单位邮编</th>
								<td colspan="43">
									<html:text name="rs" property="jtcy2_yzbm"
										styleId="jtcy2_yzbm" maxlength="10" style="width:600px"
										onkeyup="value=value.replace(/[^\d]/g,'') " />
								</td>
							</tr>
						</logic:equal>
						<%--浙江商业职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
						<logic:equal value="12865" name="xxdm">
							<tr>
								<th>在杭联系人</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy2_lxr"
										styleId="jtcy2_lxr" maxlength="10" style="width:600px" />
								</td>
							</tr>
						</logic:equal>
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
							<th>身份证号码</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_sfzh"
									id="jtcy3_sfzh"
									value="<bean:write name="rs" property="jtcy3_sfzh"/>"
									maxlength="20"  onblur="checkSfzh(this);"/>
							</td>
							<th>出生日期</th>
							<td>
								<input type="text"
									onclick="return showCalendar('jtcy3_nl','y-mm-dd');"
									style="width:200px" name="jtcy3_nl" id="jtcy3_nl"
									value="<bean:write name="rs" property="jtcy3_nl"/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>
								<html:select name="rs" property="jtcy3_mz"
									styleId="jtcy3_mz" style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select name="rs" property="jtcy3_zzmm"
									styleId="jtcy3_zzmm" style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<logic:equal value="10690" name="xxdm">
								<th>工作单位</th>
							</logic:equal>
							<logic:notEqual value="10690" name="xxdm">
								<th>职业</th>
							</logic:notEqual>
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
							<th>个人电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_lxdh1"
									id="jtcy3_lxdh1"
									value="<bean:write name="rs" property="jtcy3_lxdh1"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<th>工作单位电话</th>
							<td>
								<input type="text" style="width:200px" name="jtcy3_lxdh2"
									id="jtcy3_lxdh2"
									value="<bean:write name="rs" property="jtcy3_lxdh2"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
						</tr>
						<tr>
							<%--云南艺术--%>
							<logic:equal value="10690" name="xxdm">
								<th>单位地址</th>
							</logic:equal>
							<logic:notEqual value="10690" name="xxdm">
								<th>工作地址</th>
							</logic:notEqual>
							<td colspan="3">
								<input type="text" style="width:600px" name="jtcy3_gzdz"
									id="jtcy3_gzdz"
									value="<bean:write name="rs" property="jtcy3_gzdz"/>"
									maxlength="25" />
							</td>
						</tr>
						<%--云南艺术--%>
						<logic:equal value="10690" name="xxdm">
							<tr>
								<th>工作单位邮编</th>
								<td colspan="43">
									<html:text name="rs" property="jtcy3_yzbm"
										styleId="jtcy3_yzbm" maxlength="10" style="width:600px"
										onkeyup="value=value.replace(/[^\d]/g,'') " />
								</td>
							</tr>
						</logic:equal>
						<%--浙江商业职业技术学院--%>
						<logic:equal value="12865" name="xxdm">
							<tr>
								<th>在杭联系人</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy3_lxr"
										styleId="jtcy3_lxr" maxlength="10" style="width:600px" />
								</td>
							</tr>
						</logic:equal>
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
		            <logic:equal value="yes" name="writeAble">
						<%--东北林业大学--%>
						<logic:equal value="10225" name="xxdm">
							<div class="buttontool" align="center">
								<button type="button" class="button2" onclick="checkPage()">
									保 存 信 息
								</button>
							</div>
						</logic:equal>
						<logic:notEqual value="10225" name="xxdm">
							<div class="buttontool" align="center">
								<button type="button" class="button2" onclick="saveInfo()">
									保 存 信 息
								</button>
							</div>
						</logic:notEqual>
					</logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>