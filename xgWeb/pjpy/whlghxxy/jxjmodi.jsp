<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript">
function saveinfo(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	document.getElementById('btn_save').disabled = true;
	refreshForm(url);
}
</script>
<body>
	<html:form action="/pjpyhxxywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 奖学金申请 - 申请结果查询
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
				<td align="right">
					学号：
				</td>
				<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					
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
					民族：
				</td>
				<td align="left">
					<bean:write name="rs" property="mzmc" />
				</td>
				<td align="right">
					政治面貌：
				</td>
				<td align="left">
					<bean:write name="rs" property="zzmmmc" />
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
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
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
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					出生年月：
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
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
					外语水平：
				</td>
				<td align="left">
					<html:text property="wysp" styleId="wysp" styleClass="inputtext" maxlength="50"></html:text>
				</td>
				<td align="right">
					联系电话：
				</td>
				<td align="left">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					担任工作情况：
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw" styleClass="inputtext" maxlength="100"></html:text>
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
			<tr style="height:23px">
				<td align="right">
					平均学分绩点：
				</td>
				<td align="left">
					<bean:write name="rs" property="xf" />
				</td>
				<td align="right">
					班级排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					综合素质测评：
				</td>
				<td align="left">
					<bean:write name="rs" property="zf" />
				</td>
				<td align="right">
					班级排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="zfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					单科最低成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="zdcj" />
				</td>
				<td align="right">
					德育：
				</td>
				<td align="left">
					<bean:write name="rs" property="dcj" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					奖励加分：
				</td>
				<td align="left">
					<bean:write name="rs" property="jlf" />
				</td>
				<td align="right">
					体质健康评定：
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
			</tr>
			<tr >
				<td align="right">
					申请理由：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
		<logic:notEqual value="view" name="flag">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_hxxy_jxjmodi.do?act=save','jxjdm');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败！');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
