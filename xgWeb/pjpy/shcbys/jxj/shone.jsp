<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<body>
	<html:form action="/pjpyshcbyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!--审核失败信息提示 -->
	<input type="hidden" name="xxsh" id="xxsh" value="<bean:write name="rs"  property="xxsh"/>"/>
	<input type="hidden" name="xysh" id="xysh" value="<bean:write name="rs"  property="xysh"/>"/>
	<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>	
	<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyshcbys" key="pjpy_shcbys_jxjsq" />
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						奖学金审核
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					学期：
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					本学期绩点:
				</td>
				<td align="left">
					<bean:write name="rs" property="jd"/>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					系别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>	
				<td align="right">
					绩点专业排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="mc1"/>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					职务：
				</td>
				<td align="left">
					<bean:write name="rs" property="drzw"/>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
			<td align="right">
					奖学金:
				</td>
				<td align="left">
					<bean:write name="rs" property="jxjmc"/>
				</td>
				<input type="hidden" id="jxjdm" name="jxjdm" value="${rs.jxjdm }"/>
			</tr>
			<tr>
			<td></td><td></td>
				<td align="right">
					
					<logic:equal value="xy" name="userType">
						<logic:equal value="true" name="isFdy">辅导员</logic:equal>
						<logic:notEqual value="true" name="isFdy"><bean:message key="lable.xsgzyxpzxy" /></logic:notEqual>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">学校</logic:notEqual>审核:
				</td>
				<td align="left">
					<html:select property="yesNo" styleId="yesNo">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="width:22px">
					<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>各科成绩</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2" style="display:none">
						<table width="90%" border="1" align="center" class="tbstyle">
							<tr>
								<td align="center">
									学年
								</td>
								<td align="center">
									学期
								</td>
								<td align="center">
									课程名称
								</td>
								<td align="center">
									成绩
								</td>
							</tr>
							<logic:notEmpty name="rss">
								<logic:iterate name="rss" id="s">
									<tr style="cursor:hand;">
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="rss">
								<tr>
									<td align="center" colspan="4">
										未找到任何记录！
									</td>
								</tr>
							</logic:empty>
						</table>
					</div>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					个人申请理由:
				</td>
				<td align="left" colspan="3" >
					<html:textarea name="rs" property="sqly" readonly="true" rows="5" styleId="sqly" 
					style="width:95%"></html:textarea>
				</td>
			</tr>
			<logic:equal value="xy" name="userType">
				<logic:equal value="true" name="isFdy">
					<tr style="height:23px">
						<td align="right">
							辅导员意见:
						</td>
						<td align="left" colspan="3" >
							<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
						</td>
						</tr>
				</logic:equal>
				<logic:notEqual value="true" name="isFdy">
					<tr style="height:23px">
						<td align="right">
							<bean:message key="lable.xb" />意见:
						</td>
						<td align="left" colspan="3" >
							<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
						</td>
					</tr>
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<tr style="height:23px">
				<td align="right">
					学生处意见:
				</td>
				<td align="left" colspan="3" >
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="savejxjinfo()"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert(''+document.getElementById('failInfo').value);
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			
		</logic:present>
		<script>
			function savejxjinfo() {
				var userType = document.getElementById('userType').value;
				var xxsh = document.getElementById('xxsh').value;
				var xysh = document.getElementById('xysh').value;
				var isFdy = document.getElementById('isFdy').value;
				if (userType == 'xy') {
					if (isFdy=='true') {
						if (xysh=='通过' || xxsh=='通过') {
							alert('经相关部门审核且通过,不能再操作!');
							return;
						} else {
							BatAlert.showTips('正在操作，请等待...');
							saveinfo('pjpy_shcbys_jxjshoneres.do','');
						}
					} else {
						if (xxsh=='通过') {
							alert('经相关部门审核且通过,不能再操作!');
							return;
						} else {
							BatAlert.showTips('正在操作，请等待...');
							saveinfo('pjpy_shcbys_jxjshoneres.do','');
						}
					}
				} else {
					BatAlert.showTips('正在操作，请等待...');
					saveinfo('pjpy_shcbys_jxjshoneres.do','');
				}
			}
		</script>
	</html:form>
</body>
