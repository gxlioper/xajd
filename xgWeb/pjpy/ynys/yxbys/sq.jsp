<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyynyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_yxbyssq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_yxbyssq" />
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
			<tr>
					<th colspan="4" align="center">
					<div align="center" style="font-size:18px;font:'黑体' ">
						${tit }
					</div>
					</th>
			</tr>
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
					学年：
				</td>
				<td align="left">
					${xn }
					<input type="hidden" name="xn" id="xn" 
					value="${xn }">
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
					民族：
				</td>
				<td align="left">
					<html:text property="mz" styleId="mz" styleClass="inputtext">
					</html:text>
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
					政治面貌：
				</td>
				<td align="left">
					<html:text property="zzmm" styleId="zzmm" styleClass="inputtext">
					</html:text>
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
					健康状况：
				</td>
				<td align="left">
					<html:text property="jkzk" styleId="jkzk" styleClass="inputtext">
					</html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xy" />
				</td>
				<td align="right">
					生源地：
				</td>
				<td align="left">
					<html:text property="syd" styleId="syd" styleClass="inputtext">
					</html:text>
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
					出生年月：
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
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
					入学时间：
				</td>
				<td align="left">
					<bean:write name="rs" property="rxrq" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					家庭详细地址：
				</td>
				<td align="left" colspan="3">
					<html:text property="jtdz" styleId="jtdz" style="width:95%" styleClass="inputtext">
					</html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					优秀事迹：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yxsj" styleId="yxsj" rows="7"
					 style="width:95%" styleClass="inputtext"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bjyj" styleId="bjyj" rows="4" 
					style="width:95%" styleClass="inputtext"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_yxbyssave.do','xh');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_print" onclick="window.open('ynys_yxbysprint.do')" style="width:80px">
						打印报表
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
	</html:form>
</body>
