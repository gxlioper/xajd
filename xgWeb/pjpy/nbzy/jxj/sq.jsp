<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpynbzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_nbzy_jxjsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: 评奖评优 - 奖学金申请 - 申请
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="99%">
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
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
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
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
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
					<font color="red">*</font>申报奖学金等级:
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" >
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
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
					寝室号:
				</td>
				<td align="left">
					<html:text property="qsh" styleId="qsh" ></html:text>
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
					职业技能素养测评分:
				</td>
				<td align="left">
					${rs.zyjnsyf }
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
					可持续发展素质测评分:
				</td>
				<td align="left">
					${rs.kcxfzsyf }
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
					职业素养测评分:
				</td>
				<td align="left">
					${rs.zysyf }
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
					职业素养分名次:
				</td>
				<td align="left">
					${rs.zypm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					综合测评总分:
				</td>
				<td align="left">
					${rs.zhcpzf }
				</td>
				<td align="right">
					综合测评分名次:
				</td>
				<td align="left">
					${rs.zhpm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					申请理由:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" rows="7" styleId="sqly" style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级测评&nbsp;<br/>小组意见:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nbzy_jxjsqsave.do','xh-jxjdm-xn');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.open('pjpy_nbzy_jxjprint.do?pkValue=')" style=""
						>
						报表打印
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert(''+document.getElementById('failinfo').value);
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
