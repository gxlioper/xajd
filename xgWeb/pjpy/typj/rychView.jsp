<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">
	function checkSyme() {
		var syme = window.dialogArguments.document.getElementById('syme').value;
		var userType = window.dialogArguments.document.getElementById('userType').value;
		var shjb = $('shjb').value;
		var isFdy = window.dialogArguments.document.getElementById('isFdy').value;
		var sh = $('sh').value;
		var flg = false;
		
	   if (!((syme<0 || syme==0) && 'xy' == userType)) {
			flg = true;
		}
		
		if (!flg && '通过'==sh && '10657'==$('xxdm').value) {
			alert('超出人数限制!');
			return false;
		} 
		saveUpdate('/xgxt/typj.do?method=rychView&doType=modify','save_xh-save_xn-save_rychdm');
	}
</script>
</head>
<body>
	<html:form action="/typj" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
		<input type="hidden" name="save_nd" value="${rs.nd}"/>
		<input type="hidden" name="save_xq" value="${rs.xq}"/>
		<input type="hidden" name="save_sqsj" value="${rs.sqsj}"/>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr><th colspan="4"><span>基本信息</span></th></tr>
			</thead>
			<tbody>
			<tr>
				<th width="24%"><font color="red">*</font>学号</th>
				<td width="26%">
					<html:text  property="save_xh" styleId="xh"  value="${rs.xh}" readonly="true"/>
				</td>
				<th width="24%">姓名</th>
				<td width="26%">
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
				<th><font color="red">*</font>荣誉称号名称</th>
				<td>
					<logic:equal value="view" name="doType">
						${rs.rychmc }
					</logic:equal>
					<logic:notEqual value="view" name="doType">
						<html:hidden property="save_rychdm" name="rs"/>
						<html:select property="save_rychdm" name="rs" disabled="true">
							<html:options collection="rychList" property="dm" labelProperty="mc"/>
						</html:select>
					</logic:notEqual>
				</td>
			</tr>
			</tbody>
		</table>
			<table width="99%" id="rsTable" class="formlist">
				<logic:empty  name="zcpm">
				<thead>
					<tr><th colspan="3"><span>综合素质测评</span></th></tr>
				</thead>
				<body>
					<tr><td colspan="3">没有记录！</td></tr>
				</body>
				</logic:empty>
				<logic:notEmpty name="zcpm">
				<thead>
						<tr><th  colspan="6"><span>综合素质测评<font color="blue">(根据参数设置模块中设置的评奖周期获取综测数据)</font></span></th></tr>
						<tr>
							<td align="center">学年</td>
							<td align="center">学期</td>
							<td align="center">年度</td>
							<td align="center">名称</td>
							<td align="center">分数</td>
							<td align="center">排名</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="v" name="zcpm" offset="0" scope="request">
							<tr align="center" style="cursor:hand">
								<td>
									${v.xn }
								</td>
								<td>
									${v.xqmc }
								</td>
								<td>
									${v.nd }
								</td>
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
			</logic:notEmpty>
			</table>
			<!-- 贵州大学 -->
			<logic:equal value="10657" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/guizhdx_jxjsq.jsp"></jsp:include>	
			</logic:equal>
		
			<!-- 南宁职业 -->
			<logic:equal value="11355" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/guizhdx_jxjsq.jsp"></jsp:include>
			</logic:equal>
			
			<logic:notEqual value="update" name="doType">
				<table class="formlist" width="100%">
					<thead>
						<tr><th colspan="4"><span>审核</span></th></tr>
					</thead>
					<tbody>
					<logic:equal value="view" name="doType">
						<logic:equal value="1" name="shjb">
							<tr >
								<th width="24%">审核状态</th>
								<td colspan="3">
								  ${rs.shzt }
								</td>
							</tr>
							<tr>
								<th width="24%">审核意见</th>
								<td colspan="3">
									${rs.shyj }
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="2" name="shjb">
							<tr>
								<th width="24%">审核</th>
								<td width="26%">
								  ${rs.xysh}
								</td>
								<th width="24%">学校审核</th>
								<td width="26%">
								  ${rs.xxsh}
								</td>
							</tr>
							<tr>
								<th width="24%">意见</th>
								<td colspan="3">
									${rs.xyyj }
								</td>
							</tr>
							<tr>
								<th width="24%">学校意见</th>
								<td colspan="3">
									${rs.xxyj }
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="3" name="shjb">
							<tr>
								<th width="24%">辅导员审核</th>
								<td width="26%">
								  ${rs.fdysh}
								</td>
								<th width="24%"></th>
								<td width="26%"></td>
							</tr>
							<tr>
								<th width="24%">审核</th>
								<td width="26%">
								  ${rs.xysh}
								</td >
								<th width="24%">学校审核</th>
								<td width="26%">
								  ${rs.xxsh}
								</td>
							</tr>
							<tr>
								<th>辅导员意见</th>
								<td colspan="3">
									${rs.fdyyj }
								</td>
							</tr>
							<tr>
								<th width="24%">意见</th>
								<td colspan="3">
									${rs.xyyj }
								</td>
							</tr>
							<tr>
								<th width="24%">学校意见</th>
								<td colspan="3">
									${rs.xxyj }
								</td>
							</tr>
						</logic:equal>
					</logic:equal>
					<logic:equal value="sh" name="doType">
						<logic:equal value="1" name="shjb">
							<tr>
								<th width="24%">审核状态</th>
								<td colspan="3">
								 	<html:select property="save_shzt" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th width="24%">审核意见</th>
								<td colspan="3">
									<html:textarea property="save_shyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="2" name="shjb">
							<logic:equal value="xy" name="userType" scope="session">
								<tr>
									<th width="24%">审核</th>
									<td colspan="3">
									 	<html:select property="save_xysh" name="rs" styleId="sh">
									 		<html:options collection="shztList" property="en" labelProperty="cn"/>
									 	</html:select>
									</td>
								</tr>
								<tr>
									<th width="24%">意见</th>
									<td colspan="3">
										<html:textarea property="save_xyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType" scope="session">
								<tr>
									<th width="24%">学校审核</th>
									<td colspan="3">
									 	<html:select property="save_xxsh" name="rs" styleId="sh">
									 		<html:options collection="shztList" property="en" labelProperty="cn"/>
									 	</html:select>
									</td>
								</tr>
								<tr>
									<th width="24%">学校意见</th>
									<td colspan="3">
										<html:textarea property="save_xxyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:equal>
						<logic:equal value="3" name="shjb">
							<logic:equal value="true" name="isFdy" scope="session">
								<tr>
									<th width="24%">辅导员审核</th>
									<td colspan="3">
									 	<html:select property="save_fdysh" name="rs" styleId="sh">
									 		<html:options collection="shztList" property="en" labelProperty="cn"/>
									 	</html:select>
									</td>
								</tr>
								<tr>
									<th width="24%">辅导员意见</th>
									<td colspan="3">
										<html:textarea property="save_fdyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy" scope="session">
							<logic:equal value="xy" name="userType" scope="session">
								<tr>
									<th width="24%">审核</th>
									<td colspan="3">
									 	<html:select property="save_xysh" name="rs" styleId="sh">
									 		<html:options collection="shztList" property="en" labelProperty="cn"/>
									 	</html:select>
									</td>
								</tr>
								<tr>
									<th width="24%">意见</th>
									<td colspan="3">
										<html:textarea property="save_xyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							</logic:notEqual>
							<logic:notEqual value="xy" name="userType" scope="session">
								<tr>
									<th width="24%">学校审核</th>
									<td colspan="3">
									 	<html:select property="save_xxsh" name="rs" styleId="sh">
									 		<html:options collection="shztList" property="en" labelProperty="cn"/>
									 	</html:select>
									</td>
								</tr>
								<tr>
									<th width="24%">学校意见</th>
									<td colspan="3"><html:textarea property="save_xxyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:equal>
					</logic:equal>
					</tbody>
				</table>	
			
			</logic:notEqual>
			<table class="formlist" width="100%">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <logic:equal value="view" name="doType">
							<button class="button2" id="buttonSave" onclick="window.close();return false;">
								关&nbsp;&nbsp;闭
							</button>
						</logic:equal>
						<logic:notEqual value="view" name="doType">
							<logic:equal value="update" name="doType">
									<button class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/typj.do?method=rychView&doType=modify','save_xh-save_xn-save_rychdm');">
										保&nbsp;&nbsp;存
									</button>
							</logic:equal>
							<logic:equal value="sh" name="doType">
									<button class="button2" id="buttonSave" onclick="checkSyme();">
										保&nbsp;&nbsp;存
									</button>
							</logic:equal>
						</logic:notEqual>
	
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
