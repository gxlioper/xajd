<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script>
	function saves() {
	var type = document.getElementById('userType').value;
	var xxsh = document.getElementById('xxsh').value;
	if ('xy'==type) {
		if ('通过'==xxsh) {
			alert('经相关部门审核且通过,不能再操作!');
			return;
		}
	}
	refreshForm('pjpy_hxxy_rychshone.do?act=save');document.getElementById('btn_save').disabled=true;
		}
</script>
<body onload="checkWinType();">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 审核 - 荣誉称号审核
       		</div>
    	</div>
    		<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<input type="hidden" id="xxsh" name="xxsh" value="${rs.xxsh }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个审核
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
						<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</td>
							<td align="right">
								年度：
							</td>
							<td align="left">
								<html:text name='rs' property="nd" readonly="true"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								学年：
							</td>
							<td align="left">
								<html:text name='rs' property="xn" readonly="true"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								性别：
							</td>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<td align="right">
								<font color="red">*</font>申请荣誉称号：
							</td>
							<td align="left">
								<bean:write name='rs' property="rychmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								年级：
							</td>
							<td align="left">
								<bean:write name='rs' property="nj" />
							</td>
							<td align="right">
									政治面貌：
								</td>
								<td align="left">
									<bean:write name='rs' property="zzmmmc" />
								</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right">
								民族：
							</td>
							<td align="left">
								<bean:write name='rs' property="mzmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
								外语水平：
							</td>
							<td align="left">
								<bean:write name='rs' property="wydj" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								班级：
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
									出生年月：
								</td>
								<td>
									<bean:write name='rs' property="csrq" />
								</td>
						</tr>
						
							<tr>
								<td align="right">
									电话：
								</td>
								<td align="left">
									<bean:write name='rs' property="lxdh" />
								</td>
								<td align="right">
									入学时间：
								</td>
								<td>
									<bean:write name='rs' property="rxrq" />
								</td>
							</tr>
						<tr style="height:22px">
							<td align="right">
								主要事迹：
								<br/>
							<span class="style1">(限制在800字内)</span>
							</td>
							<td colspan="3" align="left">
								${rs.zysj }
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								担任社会工作情况：
								<br/>
							<span class="style1">(限制在800字内)</span>
							</td>
							<td colspan="3" align="left">
								${rs.drshgzqk }
							</td>
						</tr>
				<tr>
						<td align="right">
							审核：
						</td>
						<td align="left">
							<html:select property="yesNo" styleId="yesNo" value="${rs.sh }">
								<html:option value=""></html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
								<html:option value="未审核">未审核</html:option>
							</html:select>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
				</tr>
				<logic:equal value="xy" name="userType">
				<tr> 
					<td colspan="" align="right">
						辅导员意见：
					</td>
					<td colspan="3" align="left">
						<html:textarea  property="fdyyj" value="${rs.fdyyj }" styleClass="inputtext"
						rows="4" styleId="fdyyj" style="width:98%"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						系推荐意见：
					</td>
					<td colspan="3" align="left">
						<html:textarea  property="yj" value="${rs.xyyj }" styleClass="inputtext"
						rows="4" styleId="yj" style="width:98%"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<tr> 
					<td colspan="" align="right">
						辅导员意见：
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="fdyyj" styleClass="inputtext"
						rows="4" styleId="fdyyj" style="width:98%" disabled="true"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						系推荐意见：
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="xyyj" styleClass="inputtext"
						rows="4" styleId="xyyj" style="width:98%" disabled="true"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见：
					</td>
					<td colspan="3" align="left">
						<html:textarea property="xxyj" styleClass="inputtext"
						rows="4" styleId="xxyj" style="width:98%" value="${rs.xxyj }" ></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
					<button class="button2" id="btn_save"
						onclick="saves()"
						style="width:80px"  >
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>