<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<body>
	<html:form action="/wjcfxmlgwh" method="post" >
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 违纪处分 - 校办审核 - 审核
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个审核
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					学号：
				</td>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<td align="right" width="15%">
					学年：
				</td>
				<td align="left" width="35%">
					${rs.xn }
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
					处分类别：
				</td>
				<td align="left">
					${rs.cflbmc }
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
					处分原因：
				</td>
				<td align="left">
					${rs.cfyymc }
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
					处分文号：
				</td>
				<td align="left">
					<logic:equal value="xy" name="userType">
						${rs.cfwh }
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:text property="cfwh" styleId="cfwh" ></html:text>
					</logic:notEqual>
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
					处分时间：
				</td>
				<td align="left">
					<logic:equal value="xy" name="userType">
						${rs.cfsj }
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
					</logic:notEqual>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<td align="right">
					处理决定书<br/>或附件：
				</td>
				<td align="left" colspan="">
					<logic:notEmpty name="rs" property="fjsclj">
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }" target="_blank">下载</a>
					</logic:notEmpty>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					审核：
				</td>
				<td align="left" colspan="">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<td align="right">
					
				</td>
				<td align="left" colspan="">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					违纪事实：
				</td>
				<td align="left" colspan="3">
					<html:textarea rows="4" style="width:98%"
								property="bz" styleId="bz" name="rs" disabled="true"/>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
				系院意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="xyclyj" rows="4" style="width:98%" disabled="true">
							</html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
				学生处意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="xxclyj" rows="4" style="width:98%" disabled="true">
							</html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
				审核处理意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" rows="4" style="width:98%">
							</html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<logic:notEqual value="view" name="writable">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_xmlg_cfsbxxDgshByxb.do?operType=save','');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						关 闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
