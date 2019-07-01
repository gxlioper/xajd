<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<body>
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>填写申请表</b>
						</td>
					</tr>
				</thead>
				<tbody>
					<th  width="16%">
						<font color="red">*</font>学号
					</th>
					<td align="left" width="34%">
						<logic:notEqual name="doType" value="modi">
							<logic:notEqual name="doType" value="view">
							
							<logic:equal name="userOnLine" value="teacher">
							<html:text name="rs" property="save_xh" styleId="xh" value="${rs.xh}" 
								onkeypress="autoFillStuInfo(event.keyCode,this)"
								onblur="chkIsStu(this,'view_xsjbxx','xh')"
							/>
							</logic:equal>
							
							<logic:equal name="userType" value="stu">
							<html:text name="rs" property="save_xh" readonly="true" styleId="xh" value="${rs.xh}" 
								onkeypress="autoFillStuInfo(event.keyCode,this)"
								onblur="chkIsStu(this,'view_xsjbxx','xh')"
							/>
							</logic:equal>
							
							<logic:notEqual value="stu" name="userType">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									 id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<logic:equal name="doType" value="modi">
							<bean:write name="rs" property="xh"/>
							<html:hidden property="save_xh" value="${xh}"/>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<bean:write name="rs" property="xh"/>
							<html:hidden property="save_xh" value="${xh}"/>
						</logic:equal>
					</td>
					<th width="16%">
						<div align="right">
							姓名
						</div>
					</th>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>
						性别
					</th>
					<td>
						${rs.xb }
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>
						专业
					</td>
					<td>
						${rs.zymc }
					</td>
					<th>
						班级
					</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th align="right">
						<font color="red">*</font>学年
					</th>
					<td>
						${xn}
					</td>
					<th align="right">
					<logic:notEmpty name="jxjList">
						<font color="red">*</font>奖学金名称
					</logic:notEmpty>
					<logic:notEmpty name="rychList">
						<font color="red">*</font>荣誉称号名称
					</logic:notEmpty>
					</th>
					<td>
					
					<logic:notEmpty name="jxjList">
					  <logic:notEqual name="doType"  value="view">
						<logic:notEqual name="doType"  value="modi">
						<html:select property="save_jxjdm" styleId="jxjdm">
							<html:options collection="jxjList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						</logic:notEqual>
					  </logic:notEqual>
						
						<logic:equal name="doType" value="modi">
							<bean:write name="rs" property="jxjmc"/>
							<html:hidden property="save_jxjdm" value="${rs.jxjdm }" styleId="jxjdm"/>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<bean:write name="rs" property="jxjmc"/>
							<html:hidden property="save_jxjdm" value="${rs.jxjdm }" styleId="jxjdm"/>
						</logic:equal>
					</logic:notEmpty>
				
					<logic:notEmpty name="rychList">
						<logic:notEqual name="doType"  value="view">
						<logic:notEqual name="doType"  value="modi">
						<html:select property="save_rychdm" styleId="rychdm">
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
						</html:select>
						</logic:notEqual>
						</logic:notEqual>
						
						<logic:equal name="doType" value="modi">
							<bean:write name="rs" property="rychmc"/>
							<html:hidden property="save_rychdm" value="${rs.rychdm }" styleId="rychdm"/>
						</logic:equal>
						<logic:equal name="doType" value="view">
							<bean:write name="rs" property="rychmc"/>
							<html:hidden property="save_rychdm" value="${rs.rychdm }" styleId="rychdm"/>
						</logic:equal>
					</logic:notEmpty>
					
					</td>
				</tr>
				<tr>
					<th>
						出生年月
					</th>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<th>
						民族
					</th>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<th>
						政治面貌
					</th>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					<th >
						专业人数
					</th>
					<td>
						${zyrs}人
					</td>
				</tr>
				<tr>
					<th>
						年级
					</th>
					<td>
						${rs.nj}
					</td>
					<th>
						手机号码
					</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
				</tr>
				<tr>
					<th>
						工行卡号
					</th>
					<td>
						<bean:write name="rs" property="yhkh" />
					</td>
					<logic:notEmpty name="jxjList">
					<th>
						当学年任职情况
					</th>
					<td>
						<html:text name="rs" property="save_drzw"/>
					</td>
					</logic:notEmpty>
					<logic:notEmpty name="rychList">
					<th>
						当学年任职情况
					</th>
					<td>
						<html:text name="rs" property="drzw"/>
					</td>
					</logic:notEmpty>
				</tr>
				<tr>
					<th>
						综合测评成绩排名
					</th>
					<td>
						<logic:notEmpty name="zcfpm" property="fs">
						分数：${zcfpm.fs}分
						</logic:notEmpty>
						<logic:notEmpty name="zcfpm" property="pm">
						排名：${zcfpm.pm }名
						</logic:notEmpty>
					</td>
					<th>
						当学年补考门数
					</th>
					<td>
						${bkms }门
					</td>
				</tr>
				<tr>
					<th>
						当学年综考门数
					</th>
					<td>
					   ${zkms}门
					</td>
					<th>
						申请日期
					</th>
					<td>
						${sqsj}
					</td>
				</tr>
</body>