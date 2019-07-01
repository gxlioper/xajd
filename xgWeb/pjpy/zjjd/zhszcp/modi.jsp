<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyzjjdwh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_zhszcp" />
			</div>
		</div>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
		<input type="hidden" name="oldxn" id="oldxn"
			value="<bean:write name="rs" property="xn"/>" />
		<input type="hidden" name="oldxq" id="oldxq"
			value="<bean:write name="rs" property="xq"/>" />
		<input type="hidden" name="oldnd" id="oldnd"
			value="<bean:write name="rs" property="nd"/>" />
		<input type="hidden" name="oldzyfjf" id="oldzyfjf"
			value="<bean:write name="rs" property="zyfjf"/>" />
		<input type="hidden" name="oldtycj" id="oldtycj"
			value="<bean:write name="rs" property="tycj"/>" />
		<input type="hidden" name="oldtyfjf" id="oldtyfjf"
			value="<bean:write name="rs" property="tyfjf"/>" />
		<input type="hidden" name="oldbz" id="oldbz"
			value="<bean:write name="rs" property="bz"/>" />
		<table style="width:100%" class="tbstyle">
			<thead>
				<tr>
					<td colspan="4" align="center">
						单个修改
					</td>
				</tr>
			</thead>
			<tr style="width:22px">
				<td align="right">
					<font color="red">*</font>学号：
				</td>
				<td align="left">
					<html:text name='rs' property="xh" readonly="true" styleId="xh" 
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
				</td>
				<td align="right">
					<font color="red">*</font>学年：
				</td>
				<td align="left">
					<html:select property="xn" style="width:90px" styleClass="select"
						styleId="xn" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<html:text name='rs' property="xm" styleId="xm" readonly="true" />
				</td>
				<td align="right">
					<font color="red">*</font>年度：
				</td>
				<td align="left">
					<html:select property="nd" styleId="nd" styleClass="select"
						style="width:90px" disabled="true">
						<html:options collection="xnList" property="nd" labelProperty="nd" />
					</html:select>
				</td>

			</tr>
			<tr style="width:22px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<html:text name='rs' property="xb" styleId="xb" readonly="true" />
				</td>
				<td align="right">
					<font color="red">*</font>学期：
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq" styleClass="select"
						style="width:90px"  disabled="true">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<html:text name='rs' property="nj" styleId="nj" readonly="true" />
				</td>
				<td align="right">
					智育附加分：
				</td>
				<td align="left">
					<html:text property="zyfjf" styleId="zyfjf" styleClass="inputtext"
						onblur="ckinpdata(this);countzyZf();" maxlength="5"></html:text>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					<html:text name='rs' property="xymc" styleId="xy" readonly="true" />
				</td>
				<td align="right">
					体育成绩：
				</td>
				<td align="left">
					<html:text property="tycj" styleId="tycj" styleClass="inputtext"
						onblur="ckinpdata(this);counttyZf();" maxlength="5"></html:text>
				</td>
			</tr>
			<tr style="width:22px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					<html:text name='rs' property="zymc" styleId="zy" readonly="true" />
				</td>
				<td align="right">
					体育附加分：
				</td>
				<td align="left">
					<html:text property="tyfjf" styleId="tyfjf" styleClass="inputtext"
						onblur="ckinpdata(this);counttyZf();" maxlength="5"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
					班级：
				</td>
				<td align="left">
					<html:text name='rs' property="bjmc" styleId="bj" readonly="true" />
				</td>
				<td align="right">
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
			</tr>
			<tr style="width:22px">
				<td colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main2" style="color:blue;cursor:hand"
									onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>德育评定明细</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
							<tr>
								<td align="right">
									校园表现总加分：
								</td>
								<td align="left">
									<html:text name='rs' property='xybxjf' readonly="true"></html:text>
								</td>
								<td align="right">
									公寓表现总加分：
								</td>
								<td align="left">
									<html:text name="rs" property="gybxjf" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td align="right">
									校园表现总扣分：
								</td>
								<td align="left">
									<html:text name='rs' property='xybxkf' readonly="true"></html:text>
								</td>
								<td align="right">
									公寓表现总扣分：
								</td>
								<td align="left">
									<html:text name="rs" property="gybxkf" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td align="right">
									校园表现总分：
								</td>
								<td align="left">
									<html:text name='rs' property='xybxf' readonly="true"></html:text>
								</td>
								<td align="right">
									公寓表现总分：
								</td>
								<td align="left">
									<html:text name="rs" property="gybxf" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td align="right">
									附加分：
								</td>
								<td align="left">
									<html:text name="rs" property="dyfjf" readonly="true"></html:text>
								</td>
								<td align="right">
									<input type="hidden" name="dyzf" id="dyzf"
										value="<bean:write name="rs" property="dyzf"/>">
									<input type="hidden" name="dyxj" id="dyxj"
										value="<bean:write name="rs" property="dyxj"/>">
								</td>
								<td align="left">
									&nbsp;
								</td>
							</tr>
						</table>

					</div>
				</td>
			</tr>
			<tr style="width:22px">
				<td colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main3" style="color:blue;cursor:hand"
									onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
									<div align="center" class="style1 style3">
										<strong>智育评定明细</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child3" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
							<tr>
								<td align="right">
									平均成绩：
								</td>
								<td align="left">
									<html:text name='rs' property='pjcj' readonly="true"></html:text>
								</td>
								<td align="right">
									成绩名次：
								</td>
								<td align="left">
									<html:text name="rs" property="pjcjmc" readonly="true"></html:text>
								</td>
							</tr>
							<html:text property="zyzf" styleId="zyzf" style="display:none"></html:text>
							<html:text property="zyxj" styleId="zyxj" style="display:none"></html:text>
							<html:text property="tyzf" styleId="tyzf" style="display:none"></html:text>
							<html:text property="tyxj" styleId="tyxj" style="display:none"></html:text>
							<logic:notEmpty name="cjList">
								<tr>
									<td align="center" colspan="2">
										不及格课程
									</td>
									<td align="center" colspan="2">
										成绩
									</td>
								</tr>
								<logic:iterate id="s" name="cjList">
									<tr>
										<logic:iterate id="v" name="s">
											<td align="center" colspan="2">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="" align="right">
					备注：
				</td>
				<td colspan="3" align="left">
					<html:textarea property="bz" styleClass="inputtext" rows="4"
						styleId="bz" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<button type="button" class="button2" id="btn_save"
				onclick="if (zhszcpsavechk()) {alert('您未作任何修改！');return;} else saveinfo('zjjd_zhszcpmodi.do','xh-xn-xq-nd');"
				style="width:80px">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
