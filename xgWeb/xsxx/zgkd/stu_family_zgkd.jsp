<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
</head>
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgkd/stu_family_zgkd.jsp"/>
			<input type="hidden" name="redirect" id="redirect" value=""/>
			<input type="hidden" name="variable" id="variable" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 信息修改 - 修改个人信息</a>
				</p>
			</div>		

			<logic:equal name="userOnLine" value="student" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					本页面只有学校用户可以访问
				</p>
			</logic:equal>		
			<logic:notEqual value="student" name="userOnLine">
			
			<div class="tab">
			<table class="formlist" id="rsTable" width="100%">
				<thead>
				<tr>
					<th colspan="4">
						<span>家庭信息修改</span>
					</th>
				</tr>
				</thead>
				<tbody>
					<tr>
						<th>学号</th>
						<td>
							<html:text property="xh" name="rs" disabled="true" styleId="xh" maxlength="20"/>	
							<html:hidden property="xh" name="rs"/>
							<button type="button" 
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								id="buttonFindStu">
								选择
							</button>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/>
						</td>
					</tr>					
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" name="rs" disabled="true" styleId="xydm">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						</td>
						<th>专业</td>
						<td>
							<html:select property="zydm" name="rs" disabled="true" styleId="zydm">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>年级</th>
						<td>
							<html:select property="nj" name="rs" disabled="true" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" name="rs" disabled="true" styleId="bjdm">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>性别</th>
						<td>
							<html:radio property="xb" value="男" name="rs" disabled="true" styleId="xbn">男</html:radio>
							<html:radio property="xb" value="女" name="rs" disabled="true"  styleId="xbv">女</html:radio>
						</td>
						<th>学制</th>
						<td>
							<html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>年
						</td>
					</tr>					
					<tr>
						<th>家庭电话</th>
						<td>
							<html:text property="lxdh1" name="rs" disabled="true" styleId="lxdh1" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th>家庭地址</th>
						<td>
							<html:text property="jtszd" name="rs" disabled="true" styleId="jtszd" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>家庭邮编</th>
						<td>
							<html:text property="yb" name="rs" disabled="true" styleId="yb" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th>家庭经济情况</th>
						<td>
							<html:text property="jjzk" name="rs" disabled="true" styleId="jjzk" maxlength="100"/>
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									<span>学生家庭成员信息1</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt1">
					<td colspan="4">
					<table class="formlist" width="100%">	
					<tbody>					
					<tr>
						<th>姓名</th>
						<td>
							<html:text property="jtcy1_xm" name="rs" disabled="true" styleId="jtcy1_xm" maxlength="25"/>
						</td>
						<th>与本人关系</th>
						<td>
							<html:text property="jtcy1_gx" name="rs" disabled="true" styleId="jtcy1_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>出生日期</th>
						<td>
							<html:text property="jtcy1_nl" name="rs" disabled="true" styleId="jtcy1_nl" readonly="" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" styleId="jtcy1_nl"/>
						</td>
						<th>身份证号</th>
						<td>
							<html:text property="jtcy1_sfzh" name="rs" disabled="true" styleId="jtcy1_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>民族</th>
						<td>
							<html:select property="jtcy1_mz" name="rs" disabled="true" styleId="jtcy1_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="jtcy1_zzmm" name="rs" disabled="true" styleId="jtcy1_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>职业</th>
						<td>
							<html:text property="jtcy1_zy" name="rs" disabled="true" styleId="jtcy1_zy" maxlength="10"/>
						</td>
						<th>职务</th>
						<td>
							<html:text property="jtcy1_zw" name="rs" disabled="true" styleId="jtcy1_zw" maxlength="10"/>
						</td>
					</tr>						
					<tr>
						<th>工作单位电话</th>
						<td>
							<html:text property="jtcy1_lxdh1" name="rs" disabled="true" styleId="jtcy1_lxdh1" maxlength="25"/>
						</td>
						<th>个人电话</th>
						<td>
							<html:text property="jtcy1_lxdh2" name="rs" disabled="true" styleId="jtcy1_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>工作地址</th>
						<td colspan="3">
							<html:text property="jtcy1_gzdz" name="rs" disabled="true" styleId="jtcy1_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
					</tbody>			
					</table>
					</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									<span>学生家庭成员信息2</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt2">
					<td colspan="4">
					<table class="formlist" width="100%">		
					<tbody>		
					<tr>
						<th>姓名</th>
						<td>
							<html:text property="jtcy2_xm" name="rs" disabled="true" styleId="jtcy2_xm" maxlength="25"/>
						</td>
						<th>与本人关系</th>
						<td>
							<html:text property="jtcy2_gx" name="rs" disabled="true" styleId="jtcy2_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>出生日期</th>
						<td>
							<html:text property="jtcy2_nl" name="rs" disabled="true" styleId="jtcy2_nl" readonly="" onclick="return showCalendar('jtcy2_nl','y-mm-dd');" styleId="jtcy2_nl"/>
						</td>
						<th>身份证号</th>
						<td>
							<html:text property="jtcy2_sfzh" name="rs" disabled="true" styleId="jtcy2_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>民族</th>
						<td>
							<html:select property="jtcy2_mz" name="rs" disabled="true" styleId="jtcy2_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="jtcy2_zzmm" name="rs" disabled="true" styleId="jtcy2_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>职业</th>
						<td>
							<html:text property="jtcy2_zy" name="rs" disabled="true" styleId="jtcy2_zy" maxlength="10"/>
						</td>
						<th>职务</th>
						<td>
							<html:text property="jtcy2_zw" name="rs" disabled="true" styleId="jtcy2_zw" maxlength="10"/>
						</td>
					</tr>						
					<tr>
						<th>工作单位电话</th>
						<td>
							<html:text property="jtcy2_lxdh1" name="rs" disabled="true" styleId="jtcy2_lxdh1" maxlength="25"/>
						</td>
						<th>个人电话</th>
						<td>
							<html:text property="jtcy2_lxdh2" name="rs" disabled="true" styleId="jtcy2_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>工作地址</th>
						<td colspan="3">
							<html:text property="jtcy2_gzdz" name="rs" disabled="true" styleId="jtcy2_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
					</tbody>
					</table>
					</td>
					</tr>
					
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									<span>学生家庭成员信息3</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt3">
					<td colspan="4">
					<table class="formlist" width="100%">	
					<tbody>			
					<tr>
						<th>姓名</th>
						<td>
							<html:text property="jtcy3_xm" name="rs" disabled="true" styleId="jtcy3_xm" maxlength="25"/>
						</td>
						<th>与本人关系</th>
						<td>
							<html:text property="jtcy3_gx" name="rs" disabled="true" styleId="jtcy3_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>出生日期</th>
						<td>
							<html:text property="jtcy3_nl" name="rs" disabled="true" styleId="jtcy3_nl" readonly="" onclick="return showCalendar('jtcy3_nl','y-mm-dd');" styleId="jtcy3_nl"/>
						</td>
						<th>身份证号</th>
						<td>
							<html:text property="jtcy3_sfzh" name="rs" disabled="true" styleId="jtcy3_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>民族</th>
						<td>
							<html:select property="jtcy3_mz" name="rs" disabled="true" styleId="jtcy3_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="jtcy3_zzmm" name="rs" disabled="true" styleId="jtcy3_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>职业</th>
						<td>
							<html:text property="jtcy3_zy" name="rs" disabled="true" styleId="jtcy3_zy" maxlength="10"/>
						</td>
						<th>职务</th>
						<td>
							<html:text property="jtcy3_zw" name="rs" disabled="true" styleId="jtcy3_zw" maxlength="10"/>
						</td>			
					</tr>						
					<tr>
						<th>工作单位电话</th>
						<td>
							<html:text property="jtcy3_lxdh1" name="rs" disabled="true" styleId="jtcy3_lxdh1" maxlength="25"/>
						</td>
						<th>个人电话</th>
						<td>
							<html:text property="jtcy3_lxdh2" name="rs" disabled="true" styleId="jtcy3_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>工作地址</th>
						<td colspan="3">
							<html:text property="jtcy3_gzdz" name="rs" disabled="true" styleId="jtcy3_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
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
							onclick="refreshForm('xsxx_zgkd.do?method=saveStuFamily')">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="Close();return false;">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		</logic:notEqual>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");		
				Close();
				window.dialogArguments.document.getElementById('search_go').click();		
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败!");				
			</script>
		</logic:equal>

		</html:form>
	</body>
</html>
