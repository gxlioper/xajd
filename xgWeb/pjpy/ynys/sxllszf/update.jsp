<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<body>
	<html:form action="/pjpyynyszhszcp" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/ynys_sxlxszfAdd.do" />
		<input type="hidden" id="delIds" name="delIds"
			value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_szcpwh" />
			</div>
		</div>
		<logic:present name="rs" >
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						身心能力素质素质分增加
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					学号：
				</td>
				<td align="left">
					<bean:write name="rs" property="xh" />
				</td>
				<td align="right">
					学年：
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
					性别：
				</td>
				<td align="left">
				<bean:write name="rs" property="xb" />
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
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
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
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					审核状态：
				</td>
				<logic:equal name="userType" value="stu" scope="session">
				<td align="left">
					<bean:write name="rs" property="shzt" />
				</td>
				</logic:equal>
				<logic:notEqual name="userType" value="stu" scope="session">
				<td align="left">
					<html:select name="rs" property="shzt" styleId="shzt" styleClass="select">
						<html:option value="未审核">未审核</html:option>
						<html:option value="通过">通过</html:option>
						<html:option value="不通过">不通过</html:option>
					</html:select>
				</td>
				</logic:notEqual>
				<td align="right">
				</td>
				<td align="left">
				</td>
			</tr>
		</table>
		<fieldset>
				<legend>身心能力&nbsp;&nbsp;&nbsp;
						<font color="blue"><button type="button" class="button2" onclick="add1('tTb')" style="height:18px;width:20px">+</button> 
							&nbsp;<button type="button" class="button2" onclick="decrease1('tTb')" style="height:18px;width:20px">-</button></font>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<thead style="height:23px">
						<tr>
							<td align="center">
								项目
							</td>
							<td align="center">
								得分类型
							</td>
							<td align="center">
								分数
							</td>
							<td align="center">
								身心能力素质
							</td>
							<td align="center">
								操作
							</td>
						</tr>
					</thead>
					<logic:iterate id="r" name = "rsList">
					<tr id="<bean:write name="r" property="xid"/>">
						<td align="center">
								<input type="hidden" name="xid" value="<bean:write name="r" property="xid"/>">
								<html:text name = 'r' property="dfxm" styleId="dfxm1" style="width:99px"></html:text>
						</td>
						<td align="center">
							<html:select name = 'r' property="jcxm" styleId="jcxm1" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text name = 'r' property="fs" onblur="ckinpdata(this)" styleId="fs1" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text name = 'r' property="lr" styleId="lr1" style="width:250px"></html:text>
						</td>
						<td align="center">
							<button type="button" class="button2" id="btn_del" 
							onclick="$('<bean:write name="r" property="xid"/>').style.display='none';$('delIds').value+='<bean:write name="r" property="xid"/>'+'-'"
							style="width:40px">
							删除
							</button>
						</td>
					</tr>
					</logic:iterate>
				</table>
		</fieldset>
		<div class="buttontool" align="center">
					<logic:equal name="userType" value="stu" scope="session">
					<logic:notEqual name="rs" property="shzt" value="通过">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('/xgxt/ynys_sxllfszbmodi.do','xh-xn');"
						style="width:80px">
						保 存
					</button>
					</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="userType" value="stu" scope="session">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('/xgxt/ynys_sxllfszbmodi.do','xh-xn');"
						style="width:80px">
						保 存
					</button>
					</logic:notEqual>
					
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
		`	</logic:present>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
