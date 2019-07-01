<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyzjjdwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	<input type="hidden" name="oldwydj" id="oldwydj" value="<bean:write name="rs" property="wydj"/>"/>
	<input type="hidden" name="oldjsjdj" id="oldjsjdj" value="<bean:write name="rs" property="jsjdj"/>"/>
	<input type="hidden" name="oldbjpddj" id="oldbjpddj" value="<bean:write name="rs" property="bjpddj"/>"/>
	<input type="hidden" name="oldrychdm" id="oldrychdm" value="<bean:write name="rs" property="rychdm"/>"/>
	<input type="hidden" name="oldbz" id="oldbz" value="<bean:write name="rs" property="bz"/>"/>	
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_rychsq" />
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个修改
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="30%">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
				</td>
				<td align="right" width="16%">
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
					综合测评成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="zhszcpzf" />
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
					综合测评名次：
				</td>
				<td align="left">
					<bean:write name="rs" property="zhszpm" />
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
					体育成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="tycj" />
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
					学年内德育平均成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="dypjcj" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					英语过级情况：
				</td>
				<td align="left">
					<html:text property="wydj" styleId="wydj" styleClass="inputtext"></html:text>
				</td>
				<td align="right">
					学年内智育平均成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="zypjcj"/>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					计算机过级情况：
				</td>
				<td align="left">
					<html:text  property="jsjdj" styleId="jsjdj" styleClass="inputtext"></html:text>
				</td>
				<td align="right">
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级评定等级：
				</td>
				<td align="left">
					<html:select property="bjpddj" styleId="bjpddj" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="pdList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>荣誉称号：
				</td>
				<td align="left">
					<html:select property="rychdm" styleId="rychdm" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding:0;">
					<%@ include file="/pjpy/zjjd/yhkh.jsp"%>
				</td>
			</tr>
			<tr >
				<td align="right">
					备注：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" 
					style="width: 98%" styleClass="inputtext" rows="5"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="if (chkrychdata()) {alert('您未作任何修改！');return;} else saveinfo('rychmodi.do','xh-rychdm');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 数据保存后的提示对话框 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
