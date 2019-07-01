
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
						<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
			function clearxx(){
				document.getElementById('cjmc').value='';
				document.getElementById('zhpfmc').value='';
				document.getElementById('cjmc').value='';
				document.getElementById('drzw').value='';
				document.getElementById('sqly').value='';
				document.getElementById('jfqk').value='';
				document.getElementById('sjhm').value='';
				document.getElementById('btn_save').disabled=true;
			}
		</script>	
<body>
	<html:form action="/pjpyhzzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	
		
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 － 奖学金申请 － 申请表
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
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
				</td>
				<td align="right">
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
					<font color="red">*</font>奖学金：
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm">
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
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
					成绩名次：
				</td>
				<td align="left">
					<html:text property="cjmc" styleId="cjmc"></html:text>
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
					综合评分名次：
				</td>
				<td align="left">
					<html:text property="zhpfmc" styleId="zhpfmc"></html:text>
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
					平均成绩：
				</td>
				<td align="left">
					<html:text property="pjcj" styleId="pjcj"></html:text>
				</td>
				
			</tr>
			
			<tr style="height:23px">
				<td align="right">
					担任职务：
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw"></html:text>
				</td>
				<td align="right">
					手机号码：
				</td>
				<td align="left">
					<html:text property="sjhm" styleId="sjhm"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					奖罚情况：
					<br/>
							<span class="style1">(限制在800字内)</span>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="jfqk" styleId="jfqk" rows="7" style="width:98%"></html:textarea>
				</td>
				
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					申请理由：
					<br/>
							<span class="style1">(限制在800字内)</span>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly" rows="6" style="width:98%"></html:textarea>
				</td>
				
				
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('hzzyjxjmodisave.do','xh-jxjdm')"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示 -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
		
	</html:form>
</body>
