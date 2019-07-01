<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
<script type="text/javascript">
<!--
	function saveData() {
		var dm = document.getElementById('dm').value;
		if (dm == null || dm == '') {
			alert("带*号内容为必填项!");
			return false;
		}
		var cj = document.getElementById('cj').value;
		var wj = document.getElementById('wj').value;
		if (cj == '有') {
			alert("当前申报的获奖数据中有部分学生成绩不及格，不符合申报条件!");
			return false;
		} else if (wj == '无') {
			alert("当前申报的获奖数据中有部分学生受过处分且处分等级已超过学校设置的处分限制级别，不符合申报条件!");
			return false;
		}
		refreshForm('pjpy_gzdx_viewHjsbxx.do?operType=save');
	}
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/gzdxPjpy" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<input type="hidden" name="lb" value="${lb }"/>
		<input type="hidden" name="key" value="${key }"/>
		<input type="hidden" name="cj" value="${cj }"/>
		<input type="hidden" name="wj" value="${wj }"/>
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前位置:评奖评优 - 审核 - 申报获奖数据
       		</div>
    	</div>
			
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							申报详细信息
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<html:text property="xh" name="rs" readonly="true"></html:text>
				</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<html:text property="xn" name="rs" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>
						<td align="right">
						学业测评分：
					</td>
					<td align="left">
						${rs.xycpf }
					</td>
					
				</tr>
				<tr style="width:22px">
					<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb }
						</td>
					<td align="right">
						行为测评分：
					</td>
					<td align="left">
						${rs.xwbxf }
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>
					<td align="right">
						突出测评分：
					</td>
					<td align="left">
						${rs.tcbxf }
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							${rs.xymc }
						</td>
					<td align="right">
						综合表现分：
					</td>
					<td align="left">
						${rs.zhbxf }
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>
					<td align="right">
						综合素质测评总分：
					</td>
					<td align="left">
						${rs.zf }
					</td>
				</tr>
				<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							测评总分班级排名：
						</td>
						<td align="left">
							${rs.bjpm }
						</td>
				</tr>
				<tr>
					<td align="right">
							申报奖项：
						</td>
						<td align="left">
							<html:select property="dm" styleId="dm">
								<html:option value=""></html:option>
								<html:options collection="dmList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<td align="right">
							学校审核：
						</td>
						<td align="left">
							${rs.xxsh }
						</td>
				</tr>
				<tr>
					<td align="right">
							学校审核意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xxyj" styleId="yj" style="width:450px" rows="4" disabled="true"></html:textarea>
						</td>
				</tr>
				<tr align="left">
						<td align="center" colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';getStucjList();">
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
							<div id="child2" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center" >
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
											<td align="center">
												补考成绩
											</td>
											<td align="center">
												重修成绩
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
								<td align="center">
									处分文号
								</td>
							</tr>
							</thead>
							<!-- 这里是通过DWR进行调用的 -->
							<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			</table>
			<div class="buttontool" align="center">
<%--			<logic:notEqual value="yes" name="view">--%>
<%--					<button class="button2" id="btn_save" --%>
<%--						onclick=""--%>
<%--						style="width:80px">--%>
<%--						保 存--%>
<%--					</button>--%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--			</logic:notEqual>--%>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>