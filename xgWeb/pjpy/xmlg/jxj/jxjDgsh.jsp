<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<!-- DWR js -->
<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
<script type='text/javascript' src='/xgxt/pjpy/xmlg/pjpyxmlg.js'></script>
<script type="text/javascript">
<!--
	function savedata() {
		var sh = document.getElementById('sh').value;
		var userType = document.getElementById('userType').value;
		if (checkTextAreaLength('yj','审核意见',1000)) 
		{
			var xn = document.getElementById('xn').value;
			var pk = document.getElementById('pkValue').value;
			
			//<bean:message key="lable.xsgzyxpzxy" />审核要检测
			if (userType=='xy'&&sh=='通过') {
				document.getElementById('btn_save').disabled = true;
				//先检测一下是否超人数
				getStuDtiaInfo.checkJxjshrs(xn,pk,'jxj',function (data) {
					if (data==true) {
						saveinfo('pjpy_xmlg_jxjDgsh.do?operType=save','');
					} else {
						alert("审核失败：\n当前审核通过人数已超奖学金人数调整中设置的人数!");
						document.getElementById('btn_save').disabled = false;
						return false;
					}
				});				
			} else {//学校审核不用检测
				saveinfo('pjpy_xmlg_jxjDgsh.do?operType=save','');
			}
			
		}
	}
//-->
</script>
<body>
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pkValue" id="pkValue"
			value="${pkValue }" />
			<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pt" id="pt"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 审核 - 奖学金审核
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个审核
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="200px">
					学号：
				</td>
				<td align="left" width="200px">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right" width="200px">
					学年：
				</td>
				<td align="left" width="200px">
					${rs.xn }
					<input type="hidden" name="xn" id="xn" value="${rs.xn }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					${rs.nd }
					<input type="hidden" name="nd" id="nd" value="${rs.nd }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					奖学金类别：
				</td>
				<td align="left">
					${rs.lbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					奖学金：
				</td>
				<td align="left">
					${rs.jxjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					奖励金额：
				</td>
				<td align="left">
					${rs.je }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					外语水平：
				</td>
				<td align="left">
					${rs.wysp }
				</td>

			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					${rs.bjmc }
				</td>
				<td align="right">
					担任职务：
				</td>
				<td align="left">
					${rs.drzw }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					计算机水平：
				</td>
				<td align="left">
					${rs.jsjsp }
				</td>
				<td align="right">
					审核：
				</td>
				<td align="left">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>

			<!-- 这个地方显示成绩，综测，处分信息 -->
			<!-- 综合测评成绩 -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }学年综合测评排名信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2">
						<table width="100%" border="1" align="center" class="tbstyle">
							<thead>
							<tr>
								<td align="center">
									学期
								</td>
								<td align="center">
									德育表现分
								</td>
								<td align="center">
									智育表现分
								</td>
								<td align="center">
									文体表现分
								</td>
								<td align="center">
									学期排名
								</td>
								<td align="center">
									学年排名
								</td>
							</tr>
							</thead>
							<logic:notEmpty name="zhcpList">
								<logic:iterate id="zc" name="zhcpList">
									<tr style="cursor:hand;"	align="center" >
										<logic:iterate id="v" name="zc" >
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="zhcpList">
								<tr><td colspan="6" align="center">暂无记录</td></tr>
							</logic:empty>						
						</table>

					</div>
				</td>
			</tr>
			<!-- 学生成绩信息 -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
									onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none';getStucjList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }学年课程成绩信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child3" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									学期
								</td>
								<td align="center">
									课程性质
								</td>
								<td align="center">
									课程
								</td>
								<td align="center">
									成绩
								</td>
							</tr>
							</thead>
							<!-- 这里是通过DWR进行调用的 -->
							<tbody width="100%" class="tbstyle" id="cjxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			<!-- 处分信息 -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }学年违纪处分信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									学期
								</td>
								<td align="center">
									处分类别
								</td>
								<td align="center">
									处分原因
								</td>
								<td align="center">
									处分时间
								</td>
							</tr>
							</thead>
							<!-- 这里是通过DWR进行调用的 -->
							<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>	
				
			<tr>
				<td align="right">
					奖励情况：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="jlqk" styleId="jlqk" rows="4"
						style="width:650px" disabled="true" name="rs"></html:textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
					申请理由：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly" rows="4"
						style="width:650px" disabled="true" name="rs"></html:textarea>
				</td>
			</tr>
			<logic:notEqual value="xy" name="userType">
				<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />审核意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyshyj" styleId="xyshyj" rows="3"
						style="width:650px" disabled="true"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<td align="right">
					审核意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" rows="3"
						style="width:650px" ></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:notEqual value="no" name="writable">
			<button type="button" class="button2" id="btn_save"
				onclick="savedata()"
				style="width:80px">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>
			<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
				id="buttonClose">
				关 闭
			</button>
		</div>
		<!-- 保存后提示页面 -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
