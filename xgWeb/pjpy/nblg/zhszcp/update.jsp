<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/syscommon/pagehead_V4.ini"%>
<head>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>
</head>
<body>
	<html:form action="/pjpynblgzhcpwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
	<input type="hidden" name="params" id="params" value="${params }"/>
	<input type="hidden" name="act" id="act" value="${act }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>当前所在位置: </em><a>评奖评优 - 信息维护 - 综合素质测评</a>
			</p>
		</div>	
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4" >
						 ${dycjName }单个修改
					</th>
				</tr>
			</thead>
			<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
													<button type="button"
														onclick="saveinfo('pjpy_nblg_dycjmodi.do?act=save','xh-xn');"
														id="buttonSave">
														保 存
													</button>
											<button type="button" onclick="Close();return false;" id="buttonClose">
												关 闭
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
			<tr>
				<th width="16%">
					<font color="red">*</font>学号：
				</th>
				<td width="34%">
					<html:text name='rs' property="xh" styleId="xh"
						readonly="true"/>
				</td>
				<th>
					<font color="red">*</font>学年：
				</th>
				<td>
					<html:select property="xn" styleId="xn" styleClass="select">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					姓名：
				</th>
				<td>
					<bean:write name="rs" property="xm" />
				</td>
				<th>
					${dycjName }：
				</th>
				<td>
					<html:text property="dycj" styleId="dycj" onblur="ckinpdata(this)"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					年级：
				</th>
				<td>
					<bean:write name="rs" property="nj" />
				</td>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />：
				</th>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			<tr>
				<th>
					专业：
				</th>
				<td>
					<bean:write name="rs" property="zymc" />
				</td>
				<th>
					班级：
				</th>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr>
				<th>
					备注：
				</th>
				<td colspan="3">
					<html:textarea property="bz" style="width:95%" rows="5">
					</html:textarea>
				</td>
			</tr>
		</table>
		<!--<div class="buttontool" align="center">
					<logic:notEqual value="no" name="write">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nblg_dycjmodi.do?act=save','xh-xn');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" 
						style="width:80px" id="buttonClose">
						关 闭
					</button>
				</div> -->
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
</html>
