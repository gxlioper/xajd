<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title } - 单个奖学金审核</a>
				</p>
		</div>
		<html:form action="/typj" method="post">
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单个奖学金审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button onclick="saveUpdate('/xgxt/typj.do?method=priseCheckOne&doType=modify','');"
											 id="buttonSave">
											保 存
										</button>
									</logic:notEqual>
									<button class="button2" onclick="window.close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th style="width:16%">
						学号
					</th>
					<td align="left" style="width:34%">
						${rs.xh }
						<html:hidden property="save_xh" name="rs"/>
					</td>
					<th style="width:16%">
						年度
					</th>
					<td align="left" style="width:34%">
						${rs.nd }
					</td>
				</tr>
				<tr>
					<th>
						姓名
					</th>
					<td align="left">
						${rs.xm }
					</td>
					<th>
						学年
					</th>
					<td align="left">
						${rs.xn }
						<html:hidden property="save_xn" name="rs"/>
						<html:hidden property="save_xq" name="rs"/>
					</td>
 				</tr>
				<tr>
					<th>
						性别
					</th>
					<td align="left">
						${rs.xb }
					</td>
					<th>
						奖学金
					</th>
					<td align="left">
						${rs.jxjmc }
						<html:hidden property="save_jxjdm" name="rs"/>
					</td>
				</tr>
				
				<tr>
					<th>
						年级
					</th>
					<td align="left">
						${rs.nj }
					</td>
					<th>
						德成绩
					</th>
					<td align="left">
						${rs.dcj }
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						${rs.xymc }
					</td>
					<th>
						德成绩排名
					</th>
					<td align="left">
						${rs.dcjpm }
					</td>
					
				</tr>
				<tr>
					<th>
						专业
					</th>
					<td align="left">
						${rs.zymc }
					</td>
					<th>
						智成绩
					</th>
					<td align="left">
						${rs.zcj }
					</td>
					
				</tr>

				<tr>
					<th>
						班级
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
					<th>
						智成绩排名
					</th>
					<td align="left">
						${rs.zcjpm }
					</td>
					
					
				</tr>
				<tr>
					<th>
						体成绩
					</th>
					<td align="left">
						${rs.tcj }
					</td>
					<th>
						体成绩排名
					</th>
					<td align="left">
						${rs.tcjpm }
					</td>
					
				</tr>
				<tr>
					<th>
						技能加分
					</th>
					<td align="left">
						${rs.jnjf }
					</td>
					<th>
						技能加分排名
					</th>
					<td align="left">
						${rs.jnjfpm }
					</td>
					
				</tr>
				<tr>
					<th>
						综合测评总分
					</th>
					<td align="left">
						${rs.kpf }
					</td>
					<th>
						综合测评总分排名
					</th>
					<td align="left">
						${rs.cpzfpm }
					</td>
					
				</tr>
				<tr>
					<th>
						综合素质测评参评排名
					</th>
					<td align="left">
						${pm }
					</td>
					<th>
						能否获得特等奖学金
					</th>
					<td align="left">
						${nfhdjxj }
					</td>
					
				</tr>
				<tr>
					<th>
						担任职务
					</th>
					<td align="left">
						${rs.drzw }
					</td>
					<th>
						审核
					</th>
					<td align="left">
						<logic:equal value="xy" name="userType" scope="session">
							<logic:equal value="true" name="isFdy" scope="session">
								<html:select property="save_fdysh" name="rs" styleId="sh">
							 		<html:options collection="shztList" property="en" labelProperty="cn"/>
							 	</html:select>
							</logic:equal>
							<logic:equal value="false" name="isFdy" scope="session">
								<html:select property="save_xysh" name="rs" styleId="sh">
							 		<html:options collection="shztList" property="en" labelProperty="cn"/>
							 	</html:select>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
							<html:select property="save_xxsh" name="rs" styleId="sh">
							 	<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<!-- 成绩信息 -->
				<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child0.style.display=(document.all.child0.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>课程成绩信息</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child0" style="display:none" class="style2">
								<logic:notEmpty name="cjList">
									<table class="tbstyle" width="85%"  >
										<thead>
										<tr>
											<td>学年</td>
											<td>课程名称</td>
											<td>课程性质</td>
											<td>成绩</td>
											<td>补考成绩</td>
										</tr>
										</thead>
										<logic:iterate id="s" name="cjList">
											<tr>
												<logic:iterate id="v" name="s" offset="2">
													<td>
														<bean:write name="v"/>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
								<logic:empty name="cjList">
									<center>没有记录！</center>
								</logic:empty>
							</div>
							</td>
						</tr>
				<!-- 违纪信息 -->	
				<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main3" style="color:blue;cursor:hand"
											onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>违纪处分信息</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child1" style="display:none" class="style2">
								<logic:notEmpty name="wjList">
									<table class="tbstyle" width="85%">
										<thead>
										<tr>
											<td>学年</td>
											<td>学期</td>
											<td>处分类别</td>
											<td>处分原因</td>
											<td>处分时间</td>
											<td>处分文号</td>
										</tr>
										</thead>
										<logic:iterate id="s" name="wjList">
											<tr>
												<logic:iterate id="v" name="s" offset="2">
													<td>
														<bean:write name="v"/>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
								<logic:empty name="wjList">
									<center>没有记录！</center>
								</logic:empty>
							</div>
							</td>
						</tr>	
				<logic:equal value="xy" name="userType" scope="session">
					<logic:equal value="true" name="isFdy" scope="session">
						<tr>
							<th>
								辅导员意见
								<br/>
								<font color="red">(限制在400字内)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property="save_fdyyj" name="rs" style="width:100%" rows="3" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="false" name="isFdy" scope="session">
						<tr>
							<th>
								辅导员意见
							</th>
							<td align="left" colspan="3">
								${rs.save_fdyyj }
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />审核意见
								<br/>
								<font color="red">(限制在400字内)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property="save_xyshyj" name="rs" style="width:100%" rows="3" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
				
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<tr>
					<th>
						辅导员意见
					</th>
					<td align="left" colspan="3">
						<bean:write name="rs" property="fdyyj" />
					</td>
				</tr>				
				<tr>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />审核意见
					</th>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<th>
						学校审核意见
						<br/>
						<font color="red">(限制在400字内)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea property="save_xxshyj" name="rs" style="width:100%" rows="3" onblur="checkLen(this,400)"></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
				</tbody>
			</table>
			</div>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
	</body>
