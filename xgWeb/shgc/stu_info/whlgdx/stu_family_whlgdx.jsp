<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
</head>
	<body>
		<html:form action="/modi_stu_famil" method="post">
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" name="url" id="url" value="/sjcz/stu_family_modify.jsp"/>
			<input type="hidden" name="variable" id="variable" value="ydinfo"/>
			<input type="hidden" name="redirect" id="redirect" value="true"/>
			<input type="hidden" name="page" id="page" value="stuinfo"/>
			<input type="hidden" name="notnull" id="notnull" value="xh"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 学生家庭信息 - 信息维护</a>
				</p>
			</div>

			<logic:equal name="userOnLine" value="student" scope="session">
				<br/>
				<br/>
				<br/>
				<center><p>本页面只有<bean:message key="lable.xsgzyxpzxy" />用户可以访问！</p></center>
			</logic:equal>

			<logic:notEqual name="userType" value="xy" scope="session">
				<br/>
				<br/>
				<br/>
				<center><p>本页面只有<bean:message key="lable.xsgzyxpzxy" />用户可以访问！</p></center>
			</logic:notEqual>
			
			<logic:equal name="userType" value="xy" scope="session">
			<div class="tab">
			<table width="100%" class="formlist">	
				<thead align="center">
					<tr>
						<th align="center">
							<span>学生家庭信息</span>
						</th>
					</tr>
				</thead>
				<tbody>			
				<tr>
					<th>
						<span class="red">*</span>学号：
					</td>
					<td>
						<logic:equal value="update" name="oper">
							<html:text property="xh" readonly="true" styleId="xh"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh"
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button 
								onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
								id="buttonFindStu">
								选择
							</button>
						</logic:equal>
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<th>年级</th>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<th>专业</th>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						<bean:write name="rs" property="xm"/>
					</td>				
					<th>班级</th>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<th>家庭地址</th>
					<td>
						<html:text name="rs" property="jtszd" maxlength="25"
							style="width:100%" />
					</td>
					<th>邮政编码</th>
					<td>
						<html:text name="rs" property="jtyb"
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>家庭电话</th>
					<td colspan="3">
						<html:text name="rs" property="lxdh1" maxlength="25"
							style="width:100%" />
					</td>
				</tr>
			</tbody>
			</table>

			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th style="cursor:hand"
							onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
							学生家庭成员信息1
						</th>
					</tr>
				</thead>
				<tbody>
				<tr id="jt1">
					<td>
						<table width="100%" class="formlist">
							<thead>
								<th colspan="4">家庭成员1信息</th>
							</thead>
							<tbody>
							<tr>
								<th>姓名</th>
								<td>
									<html:text name="rs" property="jtcy1_xm" styleId="jtcy1_xm" />
								</td>
								<th>与本人关系</th>
								<td>
									<html:text name="rs" property="jtcy1_gx" styleId="jtcy1_gx" />
								</td>
							</tr>
							<tr>	
								<th>单位</th>
								<td>
									<html:text name="rs" property="jtcy1_gzdz" styleId="jtcy1_gzdz" />
								</td>
								<th>联系电话</th>
								<td>
									<html:text name="rs" property="jtcy1_lxdh1" styleId="jtcy1_lxdh1" />
								</td>
							</tr>								
							<tr>
								<th>地址</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy1_jtdz" styleId="jtcy1_jtdz" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<th style="cursor:hand"
							onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
							学生家庭成员信息2
						</th>
					</tr>
				</thead>
				<tbody>
				<tr id="jt2">
					<td>
						<table width="100%" class="formlist">
							<thead>
								<th colspan="4">家庭成员2信息</th>
							</thead>
							<tbody>
							<tr>
								<th>姓名</th>
								<td>
									<html:text name="rs" property="jtcy2_xm" styleId="jtcy2_xm" />
								</td>
								<th>与本人关系</th>
								<td>
									<html:text name="rs" property="jtcy2_gx" styleId="jtcy2_gx" />
								</td>
							</tr>
							<tr>	
								<th>单位</th>
								<td>
									<html:text name="rs" property="jtcy2_gzdz" styleId="jtcy2_gzdz" />
								</td>
								<th>联系电话</th>
								<td>
									<html:text name="rs" property="jtcy2_lxdh1" styleId="jtcy2_lxdh1" />
								</td>
							</tr>								
							<tr>
								<th>地址</th>
								<td colspan="3">
									<html:text name="rs" property="jtcy2_jtdz" styleId="jtcy2_jtdz" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr>
					<th style="cursor:hand" align="center"
						onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
						学生家庭成员信息3
					</th>
				</tr>
			</thead>
			<tbody>
			<tr id="jt3" style="display:none">
				<td>
					<table width="100%" class="formlist">
						<thead>
							<th colspan="4">家庭成员3信息</th>
						</thead>
						<tbody>
						<tr>
							<th>姓名</th>
							<td>
								<html:text name="rs" property="jtcy3_xm" styleId="jtcy3_xm" />
							</td>
							<th>与本人关系</th>
							<td>
								<html:text name="rs" property="jtcy3_gx" styleId="jtcy3_gx" />
							</td>
						</tr>
						<tr>	
							<th>单位</th>
							<td>
								<html:text name="rs" property="jtcy3_gzdz" styleId="jtcy3_gzdz" />
							</td>
							<th>联系电话</th>
							<td>
								<html:text name="rs" property="jtcy3_lxdh1" styleId="jtcy3_lxdh1" />
							</td>
						</tr>								
						<tr>
							<th>地址</th>
							<td colspan="3">
								<html:text name="rs" property="jtcy3_jtdz" styleId="jtcy3_jtdz" />
							</td>
						</tr>
					</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<logic:notEqual value="student" name="user">
			<logic:equal value="yes" name="writeAble">
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">		
		            <button style="width:80px"
						onclick="stuinfoSave('modi_stu_famil.do?doType=modify&oper=','family')">
						保 存
					</button>
					<button style="width:80px"
						onclick="Close();return false;">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</logic:equal>
			</logic:notEqual>
			</table>
			</div>
						
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					</script>
				</logic:equal>
			</logic:notEmpty>

		</html:form>
	</body>
</html>
