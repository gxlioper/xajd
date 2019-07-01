<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/qgzxhzzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/hzzy_qgzxsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：勤工助学 - 申请 - 勤工助学申请
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
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
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
				<td align="right">
					<font color="red">*</font>年度：
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
					<font color="red">*</font>学年：
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
					<font color="red">*</font>学期：
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
					职务：
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw"></html:text>
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
					联系电话：
				</td>
				<td align="left">
					<html:text property="lxdh" styleId="lxdh" maxlength="15"></html:text>
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
					特长：
				</td>
				<td align="left">
					<html:text property="tc" styleId="tc"></html:text>
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
					是否贷款
				</td>
				<td align="left">
					<html:select property="sfdk" styleId="sfdk">
						<html:option value=""></html:option>
						<html:option value="0">是</html:option>
						<html:option value="1">否</html:option>
					</html:select>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					家庭住址：
				</td>
				<td align="left">
					<html:text property="jtdz" styleId="jtdz"></html:text>
				</td>
				<td align="right">
					政治面貌：
				</td>
				<td align="left">
					<html:text property="zzmm" styleId="zzmm"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					成绩名次：
				</td>
				<td align="left">
					<html:text property="cjmc" styleId="cjmc"></html:text>
				</td>
				<td align="right">
					综合评分名次：
				</td>
				<td align="left">
					<html:text property="zhpfmc" styleId="zhpfmc"></html:text>
				</td>
				
			</tr>
			<tr>
				<td align="right">
					帐号：
				</td>
				<td align="left">
					<html:text property="zh" styleId="zh" style=""></html:text>
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right">
					申请理由：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly" rows="10" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick=""
						style="width:90px">
						提 交 申 请
					</button>
					
				</div>
				<!-- 保存后提示页面 -->	
					
	</html:form>
</body>
