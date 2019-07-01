<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyzjjdwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/jxjadd.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_jxjsq" />
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个增加
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="30%">
					<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
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
					<bean:write name="rs" property="zhszmc" />
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
					单科最低成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="dkzdcj" />
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
					体育成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="tycj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					体质健康标准等级：
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
				<td align="right">
					德育评定成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="dyzf" />
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
					<font color="red">*</font>奖学金：
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
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
					所在系意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="szxyj" styleId="szxyj"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr >
				<td align="right">
					备注：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" 
					style="width: 98%" styleClass="inputtext" rows="5"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('jxjsave.do','xh-jxjdm');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
