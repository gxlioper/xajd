<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<!-- 头文件 -->
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script> 
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
</head>
<body>		
	<html:form action="/stu_info_add" method="post">
		<input type="hidden" value="${oper}" id="oper" />
		<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp"/>
		<input type="hidden" name="variable" id="variable" value="ydinfo"/>
		<input type="hidden" name="redirect" id="redirect" value="true"/>
		<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj"/>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>个人信息-学生信息维护</a>
			</p>
		</div>
		
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<td colspan="5">
						<center>学生个人信息</center>
					</td>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th><span class="red">*</span>学号</th>
				<td>
					<logic:equal value="update" name="oper">
						<html:text name="rs" styleId="xh" property="xh" readonly="true"
							style="cursor:hand" maxlength="20"/>
					</logic:equal>
					<logic:equal value="add" name="oper">
						<html:text name="rs" property="xh" styleId="xh" maxlength="20" 
						onkeyup="value=value.replace(/[^\d]/g,'') " onkeydown=""/>
					</logic:equal>
				</td>
				<th><span class="red">*</span>年级</th>
				<td>
					<html:select name="rs" property="nj" styleId="nj"
						style="width:90px"
						onchange="initZyList();initBjList();">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj"
							labelProperty="nj" />
					</html:select>
				</td>
				<td align="left" width="15%" rowspan="6">
					<img border="0" style="height:133px;width:100px;"
						src="/xgxt/pictures/${rs.xh}.jpg" id="pic"/>
				</td>					
			</tr>
			<tr>
				<th><span class="red">*</span>姓名</th>
				<td>
					<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
				</td>
				<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					<html:select name="rs" property="xydm" styleId="xy"
						style="width:180px"
						onchange="initZyList();initBjList()">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
					<input type="hidden" name="xyV" value="${xydm}"/>
				</td>
			</tr>
			<tr>
				<th>民族</th>
				<td>
					<html:select name="rs" property="mz" styleId="mz"
						style="width:150px">
						<html:options collection="mzList" property="mzdm"
							labelProperty="mzmc" />
					</html:select>
				</td>
				<th><span class="red">*</span>专业</th>
				<td>
					<html:select name="rs" property="zydm" style="width:180px"
						styleId="zy"
						onchange="initBjList();">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"
							labelProperty="zymc" />
					</html:select>
					<input type="hidden" name="zyV" value="${zydm}"/>
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
				<th><span class="red">*</span>班级</th>
				<td align="left">
					<html:select name="rs" property="bjdm" style="width:180px"
						styleId="bj">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
					</html:select>
					<input type="hidden" name="bjV" value="${bjdm}"/>
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>
					<html:radio name="rs" property="xb" value="1">男</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="xb" value="2">女</html:radio>
				</td>
				<th>爱好</th>
				<td>
					<html:text name="rs" property="ah" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<th>出生日期</th>
				<td>
					<html:text name="rs" property="csrq"
						onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" maxlength="20" readonly="true"/>
				</td>
				<th>特长</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="tc" maxlength="32"/>
				</td>
			</tr>
			<tr>
				<th>身份证号</th>
				<td align="left">
					<html:text name="rs" property="sfzh" maxlength="18" styleId="sfzh"/>
				</td>
				<th>电子邮箱</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="dzyx" maxlength="64" styleId="dzyx"/>
				</td>
			</tr>
			<tr>
				<th>手机号码</th>
				<td align="left">
					<html:text name="rs" property="sjhm" maxlength="11"
					onkeyup="value=value.replace(/[^\d]/g,'') "/>
				</td>
				<th>是否贷款</th>
				<td align="left" colspan="2">						
					<html:radio name="rs" property="sfdk" value="0">否</html:radio>
					<html:radio name="rs" property="sfdk" value="1">是</html:radio>
				</td>
			</tr>
			<tr>
				<th>联系电话</th>
				<td>
					<html:text property="lxdh" name="rs" maxlength="13"/>
				</td>
				<th>学籍状态</th>
				<td colspan="2">
					<html:select property="xjzt" name="rs">
						<html:option value=""></html:option>		
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>籍贯</th>
				<td>
					<html:text property="jg" name="rs" maxlength="10"/>
				</td>
				<th>来源地区</th>
				<td colspan="2">
					<html:text property="syd" name="rs" maxlength="25"/>
				</td>
			</tr>
			<tr>
				<th>入党时间</th>
				<td align="left">
					<html:text name="rs" property="jrgcdsj" styleId="jrgcdsj"
					 onclick="return showCalendar('jrgcdsj','y-mm-dd');" readonly="true"/>
				</td>
				<th>入团时间</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="jrgqtsj" styleId="jrgqtsj"
					onclick="return showCalendar('jrgqtsj','y-mm-dd');" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th>寝室号</th>
				<td align="left">
					<html:text property="ssbh" name="rs" styleId="ssbh" readonly="true"/>
				</td>
				<th>寝室电话</th>
				<td colspan="2">
					<html:text name="rs" property="qsdh" styleId="qsdh" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th>毕业时间</th>
				<td>
					<html:text property="byny" name="rs" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
				</td>
				<th>是否毕业生</th>
				<td colspan="2">
					<html:select property="sfbys" name="rs" style="width:120px">
						<html:option value=""></html:option>
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>家庭邮编</th>
				<td align="left">
					<html:text name="rs" property="jtyb" maxlength="20"/>
				</td>
				<th>家庭电话</th>
				<td align="left" colspan="2">
					<html:text name="rs" property="jtdh" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>家庭详细<br/>地址</th>
				<td align="left" colspan="4">
					<html:text property="jtdz" name="rs" style="width:680;height:30" maxlength="127"/>
				</td>
			</tr>		
			<tr>
				<th>家庭经济<br/>情况</th>
				<td colspan="4">
					<html:radio name="rs" property="jtjjqk" value="富裕">宽裕</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="jtjjqk" value="一般">一般</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="jtjjqk" value="困难">困难</html:radio>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio name="rs" property="jtjjqk" value="特困">特困</html:radio>
				</td>
			</tr>
			
			<tr>
				<th>个人及家庭<br/>情况简介<br/>(100字)</th>
				<td align="left" colspan="4">
					<html:textarea name="rs" property="jtqkjj"
						style="width:680px;height:80px">
					</html:textarea>
				</td>
			</tr>
			<tr>
				<th>曾获得奖励<br/>(市级以上)</th>
				<td colspan="4">
					<html:textarea name="rs" property="jlcfjl"
						style="width:680px;height:80px">
					</html:textarea>
				</td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="4">
					<html:textarea name="rs" property="bz"
						style="width:680px;height:80px">
					</html:textarea>
				</td>
			</tr>
			<thead>
			<tr>			
				<td colspan="5" style="cursor:hand" 
					onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
					哈尔滨市主要社会关系
				</td>			
			</tr>
			</thead>
			<tr id="jt3" style="display:none">
				<td colspan="5">
					<div class="formbox">
					<table class="dateline">
						<tr>
							<td>
								姓名
							</td>
							<td>
								与本人关系
							</td>
							<td>
								工作单位
							</td>
							<td>
								职务
							</td>
							<td>
								单位电话
							</td>
							<td>
								手机号码
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="shgxxm1" maxlength="10"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgx1" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgzdw1" maxlength="127"/>
							</td>
							<td>
								<html:text name="rs" property="shgxzw1" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxdwdh1" maxlength="20"/>
							</td>
							<td>
								<html:text name="rs" property="shgxsjhm1" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td>
								<html:text name="rs" property="shgxxm2" maxlength="10"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgx2" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxgzdw2" maxlength="127"/>
							</td>
							<td>
								<html:text name="rs" property="shgxzw2" maxlength="32"/>
							</td>
							<td>
								<html:text name="rs" property="shgxdwdh2" maxlength="20"/>
							</td>
							<td>
								<html:text name="rs" property="shgxsjhm2" maxlength="20"/>
							</td>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			</tbody>

			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		             <logic:notPresent name="details">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button style="height:20px;width:80px" 
								id="buttonSave"
								onclick="stuinfoSave('stu_info_add.do?method=stuInfoSave&oper=');">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>
					<button style="height:20px;width:80px"
							onclick="Close();return false;">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}						
				</script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>

	</html:form>
</body>
</html>
