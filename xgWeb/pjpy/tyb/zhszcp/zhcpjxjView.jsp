<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/pjpy/typj.js"></script>
<script type="text/javascript">
	function checkSyme() {
		var syme = window.dialogArguments.document.getElementById('syme').value;
		var userType = window.dialogArguments.document.getElementById('userType').value;
		var isFdy = window.dialogArguments.document.getElementById('isFdy').value;
		var flg = false;
		var sh = $('sh').value;
		saveUpdate('/xgxt/pjpyTybZhcpjxj.do?method=zhcpjxjView&doType=modify','save_xh-save_xn-save_jxjdm');
	}
</script>
</head>
<body onload="display();">
	<html:form action="/pjpyTybZhcpjxj.do" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
		<input type="hidden" id="jxjmc" value="${rs.jxjmc }"/>
		<input type="hidden" name="save_xq" value="${rs.xq }"/>
		<input type="hidden" name="save_nd" value="${rs.nd }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优-综合测评-综合测评奖学金</a>
			</p>
		</div>
		<div class='tab'>
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4"><span>基本信息</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th><font color="red">*</font>学号</th>
				<td>
					<html:text  property="save_xh" styleId="xh"  value="${rs.xh}" readonly="true"/>
				</td>
				<th>姓名</th>
				<td>
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>${rs.xb }</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>专业</th>
				<td>${rs.zymc }</td>
				<th>班级</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th><font color="red">*</font>学年</th>
				<td>
					<html:text property="save_xn" name="rs" readonly="true"></html:text>
				</td>
				<th><font color="red">*</font>奖学金名称</th>
				<td>
					<logic:equal value="view" name="doType">
						${rs.jxjmc }
					</logic:equal>
					<logic:notEqual value="view" name="doType">
						<html:select property="save_jxjdm" name="rs" disabled="true">
							<html:options collection="jxjList" property="dm" labelProperty="mc"/>
						</html:select>
						<html:hidden property="save_jxjdm" name="rs"/>
					</logic:notEqual>
					
				</td>
			</tr>
			</tbody>
		</table>
			
		<logic:notEqual value="11355" name="xxdm" scope="session">
			<logic:empty  name="zcpm">
				<br/>
				<p style="height:120px" align="center"><span>没有综合测评分数！</span></p>
			</logic:empty>
			<logic:notEmpty name="zcpm">
			<table width="99%" id="rsTable" class="formlist">
				<thead>
					<tr>
						<th colspan="3"><span>综合素质测评</span></th>
					</tr>
					<tr>
						<td align="center">名称</td>
						<td align="center">分数</td>
						<td align="center">排名</td>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="v" name="zcpm" offset="0" scope="request">
						<tr align="center" style="cursor:hand">
							<td>
								${v.mc }
							</td>
							<td>
								${v.fs }
							</td>
							<td>
								${v.pm }
							</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
			</logic:notEmpty>
		</logic:notEqual>		
	
		<logic:notEqual value="update" name="doType">
			<table class="formlist" width="100%">
				<thead>
					<th colspan="4"><span>审核</span></th>
				</thead>
				<tbody>
				<logic:equal value="view" name="doType">
					<logic:equal value="1" name="shjb">
						<tr>
							<th  width="300px">审核状态</th>
							<td>
							  ${rs.shzt }
							</td>
						</tr>
						<tr>
							<th>审核意见</th>
							<td>
								${rs.shyj }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="2" name="shjb">
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
							<td width="35%">
							  ${rs.xysh}
							</td>
							<th>学校审核</th>
							<td width="35%">
							  ${rs.xxsh}
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
							<td colspan="3">
								${rs.xyshyj }
							</td>
						</tr>
						<tr>
							<th>学校意见</th>
							<td colspan="3">
								${rs.xxshyj }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="3" name="shjb">
						<tr>
							<th  width="300px">辅导员审核</th>
							<td>
							  ${rs.fdysh}
							</td>
							<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
							<td>
							  ${rs.xysh}
							</td>
							<th>学校审核</th>
							<td>
							  ${rs.xxsh}
							</td>
						</tr>
						<tr>
							<th>辅导员意见</th>
							<td colspan="3">
								${rs.fdyshyj }
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
							<td colspan="3">
								${rs.xyshyj }
							</td>
						</tr>
						<tr>
							<th>学校意见</th>
							<td colspan="3">
								${rs.xxshyj }
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				<logic:equal value="sh" name="doType">
					<logic:equal value="1" name="shjb">
						<tr>
							<th>审核状态</th>
							<td>
							 	<html:select property="save_shzt" name="rs" styleId="sh">
							 		<html:options collection="shztList" property="en" labelProperty="cn"/>
							 	</html:select>
							</td>
						</tr>
						<tr>
							<th>审核意见</th>
							<td>
								<html:textarea property="save_shyj" style="width:85%" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="2" name="shjb">
						<logic:equal value="xy" name="userType" scope="session">
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
								<td>
								 	<html:select property="save_xysh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
								<td>
									<html:textarea property="save_xyshyj" style="width:85%" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
							<tr>
								<th>学校审核</th>
								<td>
								 	<html:select property="save_xxsh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th>学校意见</th>
								<td>
									<html:textarea property="save_xxshyj" style="width:85%" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:notEqual>
					</logic:equal>
					<logic:equal value="3" name="shjb">
						<logic:equal value="true" name="isFdy" scope="session">
							<tr>
								<th>辅导员审核</th>
								<td>
								 	<html:select property="save_fdysh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th>辅导员意见</th>
								<td>
									<html:textarea property="save_fdyshyj" style="width:85%" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						
	
						<logic:notEqual value="true" name="isFdy" scope="session">
								<logic:equal value="xy" name="userType" scope="session">
									<tr>
										<th><bean:message key="lable.xsgzyxpzxy" />审核</td>
										<td>
										 	<html:select property="save_xysh" name="rs" styleId="sh">
										 		<html:options collection="shztList" property="en" labelProperty="cn"/>
										 	</html:select>
										</td>
									</tr>
									<tr>
										<th><bean:message key="lable.xsgzyxpzxy" />意见</th>
										<td>
											<html:textarea property="save_xyshyj" style="width:85%" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
								</logic:equal>
						</logic:notEqual>
						<logic:notEqual value="xy" name="userType" scope="session">
							<tr>
								<th>学校审核</th>
								<td>
								 	<html:select property="save_xxsh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th>学校意见</th>
								<td>
									<html:textarea property="save_xxshyj" style="width:85%" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:notEqual>			
					</logic:equal>
				</logic:equal>
				</tbody>				
			</table>	
		</logic:notEqual>
		<table class="formlist">
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <logic:equal value="view" name="doType">
							<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
								关&nbsp;&nbsp;闭
							</button>
						</logic:equal>
						<logic:notEqual value="view" name="doType">
							<logic:equal value="update" name="doType">
								<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/pjpyTybZhcpjxj.do?method=zhcpjxjView&doType=modify','save_xh-save_xn-save_jxjdm');">
									保&nbsp;&nbsp;存
								</button>
							</logic:equal>
							<logic:equal value="sh" name="doType">
									<button type="button" class="button2" id="buttonSave" onclick="checkSyme();">
										保&nbsp;&nbsp;存
									</button>
							</logic:equal>
						</logic:notEqual>	
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					if(window.dialogArguments.document.getElementById("isPage")){
							window.dialogArguments.document.getElementById("isPage").value="yes";
					}
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>