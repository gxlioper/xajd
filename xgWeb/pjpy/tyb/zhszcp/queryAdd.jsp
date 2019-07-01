<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
<body>
	<html:form action="/pjpyzgmswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
<%--		<input type="hidden" name="act" id="act" value="save"/>--%>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_zgms_kkjladd.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 信息维护 - 考勤信息维护
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个增加
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh" readonly="true" />
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
				</td>
				<td align="right">
					学年：
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn">
						<html:option value=""></html:option>
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
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
					学期：
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
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
					旷课时间:
				</td>
				<td align="left">
					<html:text property="kksj" styleId="kksj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('kksj','y-mm-dd');"></html:text>
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
					旷课地点:
				</td>
				<td align="left">
					<html:text property="kkdd" styleId="kkdd"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					
				</td>
				<td align="left">
					
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
					
				</td>
				<td align="left">
					
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
					
				</td>
				<td align="left">
					
				</td>
				
			</tr>
			<tr >
				<td align="right">
					旷课记录：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="kkjl" styleId="kkjl" 
					style="width: 98%" styleClass="inputtext" rows="7"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="flag">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_zgms_kkjladd.do?act=save','xh')"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
