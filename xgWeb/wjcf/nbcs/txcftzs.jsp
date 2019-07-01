<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/wjcfnbcswh.do" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：违纪处分 - 下发拟处分通知 - 填写拟处分通知
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						填写拟处分通知
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					学号：
				</td>
				<td align="left" width="30%">
					${rs.xh }
				</td>
				<td align="right" width="20%">
					学年：
				</td>
				<td align="left" width="30%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					年度：
				</td>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					拟处分类别：
				</td>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					拟处分原因：
				</td>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					违纪时间：
				</td>
				<td align="left">
					${rs.wjsj }
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
					班级：
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					违纪事实：
				</td>
				<td align="left" colspan="3">
					<html:textarea rows="9"  style="width:500px;overflow:auto" property="bz" styleId="bz" name="rs" readonly="true"></html:textarea>
				</td>
			</tr>
		<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>阅件意见：
				</td>
				<td align="left" colspan="3">
					<font color="red">本人（代理人）是否对违纪事由及学校拟作出的违纪行为处分作出申辩？</font>
					<br/>
					<html:select property="sfsb" styleId="sfsb">
						<html:option value="申辩">申辩</html:option>
						<html:option value="不申辩">不申辩</html:option>
					</html:select>
				</td>
			</tr>
		<tr style="height:23px">
				<td align="right">
					陈述和申辩事由：
					<br/>
					<font color="red">限制在1000字以内</font>
				</td>
				<td align="left" colspan="3">
					<font color="red">（不申辩的同学无须填写此栏，需申辩的可另附材料）</font><br/>
					<html:textarea property="sbsy" styleId="sbsy" style="width:500px" rows="6" onkeyup="checkLen(this,1000)"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:notEqual value="true" name="rs" property="write">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_nbcs_txcftzs.do?operType=save','sfsb');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:notEqual>
			<logic:equal value="true" name="rs" property="write">
				<font color="red">相关部门审核中,不能再进行操作!</font><br/>
			</logic:equal>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						关 闭
					</button>
				</div>
				<!-- 保存后提示页面 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
