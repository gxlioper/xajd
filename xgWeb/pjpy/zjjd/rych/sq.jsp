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
			value="/pjpy_zjjd_rychsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_rychsq" />
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
						填写申请表
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="30%">
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
					</logic:notPresent>
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
						onclick="saveinfo('rychsave.do','xh-rychdm');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败！');
				</script>
			</logic:equal>
		</logic:present>
		<logic:present name="failinfo">
				<script>
					alert('该生不符奖学金申请条件！');
				</script>
			</logic:present>
		<logic:present name="cjFlag">
			<script>
					alert('学生成绩表中无该生课程成绩,请先导入成绩！');
				</script>
		</logic:present>
	</html:form>
</body>
