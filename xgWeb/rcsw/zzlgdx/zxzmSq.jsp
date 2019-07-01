<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function print(){
			var xh = $('xh').value;
			var url = '/xgxt/zzlgdx_rcsw.do?method=zxzmPrint&xh='+xh;
			 window.open(url);
		}
		function checkSjshzt(){
			var sjshzt = document.getElementById('sjshzt').value;
			if(sjshzt=='未审核'){
				return true;
			}else{
				alert('正在审核中，不可操作！');
				return false;
			}
		}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 在校证明 - 在校证明申请</a>
			</p>
		</div>


		<html:form action="zzlgdx_rcsw.do?method=zxzmEdit" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" id="url" name="url"
				value="/zzlgdx_rcsw.do?method=zxzmSq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>在校证明申请</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<logic:equal value="view" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</logic:equal>
								<logic:equal value="sh" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</logic:equal>
								<logic:equal value="update" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</logic:equal>
								<logic:notEqual name="doType" value="view">
									<logic:notEqual value="update" name="doType">
										<logic:notEqual value="sh" name="doType">
											<html:text name='rs' property="xh" styleId="xh"
												readonly="true"
												onkeypress="autoFillStuInfo(event.keyCode,this)" />
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												选择
											</button>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<input type="text" readonly="readonly" id="xm" name="xm"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<input type="text" id="xb" readonly="readonly" name="xb"
									value="<bean:write name="rs" property="xb"/>" />
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
									value="<bean:write name="rs" property="zzmmmc"/>" />
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								<input type="text" id="mzmc" readonly="readonly" name="mzmc"
									value="<bean:write name="rs" property="mzmc"/>" />
							</td>
							<th>
								身份证号
							</th>
							<td>
								<input type="text" id="sfzh" name="sfzh" readonly="readonly"
									value="<bean:write name="rs" property="sfzh"/>" />
							</td>
						</tr>
						<tr>
							<th>
								出生日期
							</th>
							<td>
								<input type="text" id="csrq" readonly="readonly" name="csrq"
									value="<bean:write name="rs" property="csrq"/>" />
							</td>
							<th>
								入学时间
							</th>
							<td>
								<input type="text" id="rxrq" name="rxrq" readonly="readonly"
									value="<bean:write name="rs" property="rxrq"/>" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<input type="text" id="nj" readonly="readonly" name="nj"
									value="<bean:write name="rs" property="nj"/>" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td>
								<input type="text" id="xymc" name="xymc" readonly="readonly"
									value="<bean:write name="rs" property="xymc"/>" />
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								<input type="text" id="zymc" readonly="readonly" name="zymc"
									value="<bean:write name="rs" property="zymc"/>" />
							</td>
							<th>
								班级名称
							</th>
							<td>
								<input type="text" id="bjmc" name="bjmc" readonly="readonly"
									value="<bean:write name="rs" property="bjmc"/>" />
							</td>
						</tr>
						<tr>
							<th>
								学制
							</th>
							<td>
								<input type="text" id="xz" readonly="readonly" name="xz"
									value="<bean:write name="rs" property="xz"/>" />
							</td>
							<th>
								录入时间
							</th>
							<td>
								<input type="text" id="lrsj" name="lrsj" readonly="readonly"
									value="<bean:write name="rs" property="lrsj"/>" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td>
								<html:select property="xmmc" name="rs">
									<html:options collection="xmmcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<!-- 武汉商业 -->
							<logic:equal value="11654" name="xxdm" scope="session">
								<input type="hidden" id="shlx" name="shlx" value="三级审核"/>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</logic:equal>
							<logic:notEqual value="11654" name="xxdm" scope="session">
								<th>
									<font color="red">*</font>类型
								</th>
								<td>
									<html:select property="shlx" name="rs">
										<html:option value="">----请选择----</html:option>
										<html:options collection="shlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th>
								在校表现及文化
								<br />
								程度鉴定情况
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zxbx" rows='10'
									style="word-break:break-all;width:100%" />
							</td>
						</tr>
						<customTag:customTable rsname="rs"
							nodeslist="rcswZzlgdxActionForm" doType="updata" scope="request" />
						
						<!-- 武汉商业 -->
						<logic:equal value="11654" name="xxdm" scope="session">
							<!--审核  -->
							<logic:equal value="sh" name="doType">
								<logic:equal value="true" name="isFdy" scope="session">
									<tr>
										<th>
											辅导员意见
										</th>
										<td colspan="3">
											<html:textarea property="fdyyj" name="rs" style="width:100%"
												rows="10" onblur="chLeng(this,'500')"></html:textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual value="true" name="isFdy" scope="session">
									<logic:equal value="xy" name="userType" scope="session">
										<tr>
											<th>
												<bean:message key="lable.xsgzyxpzxy" />
												意见
											</th>
											<td colspan="3">
												<html:textarea property="xyyj" name="rs" style="width:100%"
													rows="10" onblur="chLeng(this,'500')"></html:textarea>
											</td>
										</tr>
									</logic:equal>
									<logic:equal value="xx" name="userType" scope="session">
										<tr>
											<th>
												学校意见
											</th>
											<td colspan="3">
												<html:textarea property="xxyj" name="rs" style="width:100%"
													rows="10" onblur="chLeng(this,'500')"></html:textarea>
											</td>
										</tr>
									</logic:equal>
									<logic:equal value="admin" name="userType" scope="session">
										<tr>
											<th>
												学校意见
											</th>
											<td colspan="3">
												<html:textarea property="xxyj" name="rs" style="width:100%"
													rows="10" onblur="chLeng(this,'500')"></html:textarea>
											</td>
										</tr>
									</logic:equal>
								</logic:notEqual>
							</logic:equal>
							<!-- 查看 -->
							<logic:equal value="view" name="doType">
								<tr>
									<th>
										辅导员意见
									</th>
									<td colspan="3">
										<html:textarea property="fdyyj" name="rs" style="width:100%"
											rows="10" readonly="true"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										意见
									</th>
									<td colspan="3">
										<html:textarea property="xyyj" name="rs" style="width:100%"
											rows="10" readonly="true"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										学校意见
									</th>
									<td colspan="3">
										<html:textarea property="xxyj" name="rs" style="width:100%"
											rows="10" readonly="true"></html:textarea>
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<!-- 非武汉商业 -->
						<logic:notEqual value="11654" name="xxdm" scope="session">
							<logic:equal value="sh" name="doType">
								<logic:equal value="xy" name="userType" scope="session">
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											意见
										</th>
										<td colspan="3">
											<html:textarea property="xyyj" name="rs" style="width:100%"
												rows="10" onblur="chLeng(this,'500')"></html:textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="xx" name="userType" scope="session">
									<tr>
										<th>
											学校意见
										</th>
										<td colspan="3">
											<html:textarea property="xxyj" name="rs" style="width:100%"
												rows="10" onblur="chLeng(this,'500')"></html:textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="admin" name="userType" scope="session">
									<tr>
										<th>
											学校意见
										</th>
										<td colspan="3">
											<html:textarea property="xxyj" name="rs" style="width:100%"
												rows="10" onblur="chLeng(this,'500')"></html:textarea>
										</td>
									</tr>
								</logic:equal>
							</logic:equal>
							<logic:equal value="view" name="doType">
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
										意见
									</th>
									<td colspan="3">
										<html:textarea property="xyyj" name="rs" style="width:100%"
											rows="10" readonly="true"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										学校意见
									</th>
									<td colspan="3">
										<html:textarea property="xxyj" name="rs" style="width:100%"
											rows="10" readonly="true"></html:textarea>
									</td>
								</tr>
							</logic:equal>
						</logic:notEqual>
						
						
						</tbody>
						
						<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEqual value="view" name="doType">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
								</logic:notEqual>
								<div class="btn">
									<logic:equal value="sh" name="doType">
										<!-- 武汉商业对上级审核进行验证处理 -->
										<logic:equal value="11654" name="xxdm" scope="session">
											<logic:equal value="true" name="isFdy" scope="session">
												<input type="hidden" id="sjshzt" name="sjshzt" value="${rs.xysh }"/>
											</logic:equal>
											<logic:notEqual value="true" name="isFdy" scope="session">
												<logic:equal value="xy" name="userType" scope="session">
													<input type="hidden" id="sjshzt" name="sjshzt" value="${rs.xxsh }"/>
												</logic:equal>
												<logic:notEqual value="xy" name="userType"  scope="session">
													<input type="hidden" id="sjshzt" name="sjshzt" value="未审核"/>
												</logic:notEqual>
											</logic:notEqual>
											<button type="button"
												onclick="if(checkSjshzt()){saveUpdate('/xgxt/zzlgdx_rcsw.do?method=zxzmShow&doType=modify&shzt=已通过','');}">
												审核通过
											</button>
											<button type="button"
												onclick="if(checkSjshzt()){saveUpdate('/xgxt/zzlgdx_rcsw.do?method=zxzmShow&doType=modify&shzt=不通过','');}">
												审核未通过
											</button>
										</logic:equal>
										<logic:notEqual value="11654" name="xxdm" scope="session">
											<button type="button"
												onclick="saveUpdate('/xgxt/zzlgdx_rcsw.do?method=zxzmShow&doType=modify&shzt=已通过','');">
												审核通过
											</button>
											<button type="button"
												onclick="saveUpdate('/xgxt/zzlgdx_rcsw.do?method=zxzmShow&doType=modify&shzt=不通过','');">
												审核未通过
											</button>
										</logic:notEqual>
									</logic:equal>
									<logic:equal value="view" name="doType">
										<button type="button" onclick="window.close();return false;">
											关闭
										</button>
									</logic:equal>
									<logic:equal value="update" name="doType">
										<button type="button" id="buttonSave"
											onclick="saveUpdate('/xgxt/zzlgdx_rcsw.do?method=zxzmShow&doType=modi','xh-xmmc-shlx-rsj');">
											修改
										</button>
									</logic:equal>
									<logic:notEqual value="update" name="doType">
										<logic:notEqual value="view" name="doType">
											<logic:notEqual value="sh" name="doType">
												<button type="button" id="buttonSave"
													onclick="saveUpdate('/xgxt/zzlgdx_rcsw.do?method=zxzmSq&doType=save','xh-xmmc-shlx-rsj');">
													确定
												</button>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("保存成功！");
	         	if(window.dialogArguments){
					 window.close();
					 window.dialogArguments.document.getElementById('search_go').click();
				}
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("申请失败，该学生在校证明已申请！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
