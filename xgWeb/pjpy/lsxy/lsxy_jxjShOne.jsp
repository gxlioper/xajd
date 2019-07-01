<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="js/xgutil.js"></script>
<script>
	function print(){
		var url = 'pjpyLsxy.do?method=printJxjsqb';
		url += "&xh=" + val("xh");
		url += "&xn=" + val("xn");
		showOpenWindow(url,800,600)
	}
	
	function save(){		
		refreshForm('pjpyLsxy.do?method=jxjShOne&act=save');
	}
</script>
<body>
	<html:form action="/pjpyLsxy" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${rs.pkValue}"/>
	<input type="hidden" name="xysh" id="xysh" value="${rs.xysh}"/>
	<input type="hidden" name="xxsh" id="xxsh" value="${rs.xxsh}"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置: ${title } - 单个审核
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						奖学金申请信息
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					${rs.xh}
					<input type="hidden" name="save_xh" id="xh" value="${rs.xh}">
				</td>
				<td align="right">
					学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="save_xn" id="xn" value="${rs.xn}">
					<input type="hidden" name="save_xq" id="xq" value="${rs.xq}">
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
					奖学金：
				</td>
				<td align="left">
					${rs.jxjmc}
					<input type="hidden" name="save_jxjdm" id="jxjdm" value="${rs.jxjdm}">
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
					综合测评成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="zf" />
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
					综合测评班级排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="zfpm" />
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
					同年级同专业综合测评排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="zyzfpm" />
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
					学习成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="cj" />
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
					学习成绩班级排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="cjpm" />
				</td>			
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级人数：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjrs" />
				</td>
				<td align="right">
					同年级同专业学习成绩排名：
				</td>
				<td align="left">
					<bean:write name="rs" property="zycjpm" />
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
					体质健康平均成绩：
				</td>
				<td align="left">
					<bean:write name="rs" property="tzjkcj" />
				</td>				
			</tr>
			<tr style="height:23px">
				<td align="right">
					籍 贯：
				</td>
				<td align="left">
					<bean:write name="rs" property="jg" />
				</td>
				<td align="right">
					是否有违纪受处分：
				</td>
				<td align="left">
					<bean:write name="rs" property="sfwj" />
				</td>				
			</tr>
			<tr style="height:23px">
				<td align="right">
					政治面貌：
				</td>
				<td align="left" colspan="3">
					<bean:write name="rs" property="zzmmmc" />
				</td>								
			</tr>
			<tr style="height:23px">				
				<td align="right">
					获奖情况：
				</td>
				<td align="left" colspan="3">
					${rs.jfqk}
				</td>
			</tr>			
			<tr >
				<td align="right">
					备注：
				</td>
				<td align="left" colspan="3">
					${rs.bz}
				</td>
			</tr>
			<!--学院用户-->
			<logic:equal value="xy" name="userType">
			<tr >
				<td align="right">
					<bean:message key="lable.xb" />审核：
				</td>
				<td align="left" colspan="3">
					<html:select property="save_xysh" name="rs">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>				
			</tr>
			<tr>
				<td align="right">
					<bean:message key="lable.xb" />审核意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="save_xyshyj" rows="5" style="width: 98%" name="rs" onblur="chLeng(this,'1000')"></html:textarea>
				</td>
			</tr>
			<tr >
				<td align="right">
					学校审核：
				</td>
				<td align="left" colspan="3">
					${rs.xxsh}
				</td>				
			</tr>
			<tr>
				<td align="right">
					学校审核意见：
				</td>
				<td align="left" colspan="3">
					${rs.xxshyj}
				</td>
			</tr>
			</logic:equal>
			<!--学院用户-->

			<!--非学院用户-->
			<logic:notEqual value="xy" name="userType">
			<tr >
				<td align="right">
					<bean:message key="lable.xb" />审核：
				</td>
				<td align="left" colspan="3">
					${rs.xysh}
				</td>				
			</tr>
			<tr>
				<td align="right">
					<bean:message key="lable.xb" />审核意见：
				</td>
				<td align="left" colspan="3">
					${rs.xyshyj}
				</td>
			</tr>
			<tr >
				<td align="right">
					学校审核：
				</td>
				<td align="left" colspan="3">
					<html:select property="save_xxsh" name="rs">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>				
			</tr>
			<tr>
				<td align="right">
					学校审核意见：
				</td>
				<td align="left" colspan="3">
					<html:textarea property="save_xxshyj" rows="5" style="width: 98%" name="rs" onblur="chLeng(this,'1000')"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
			<!--end非学院用户-->
		</table>
		<div class="buttontool" align="center">
			<button class="button2" id="btn_save" 
				onclick="save();return false;"
				style="width:80px">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" id="btn_dy" 
				onclick="print()"
				style="width:80px">
				打印申请表
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
				id="buttonClose">
				关 闭
			</button>
		</div>
		<!-- 保存后提示页面 -->	
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('操作成功！');
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<input id="msg" name="" value="${message}" type="hidden"/>
				<script>
					alert(document.getElementById("msg").value);
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
