<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function commit(){
		var xxdm = document.getElementById('xxdm').value;
		var jfglkg = document.getElementById('jfglkg').value;
		
		if(jfglkg == "1"){//经费管理开关开
			if(parseFloat(document.forms[0].syjf.value)-parseFloat(document.getElementById('zje').innerText)<0){
				alert('经费不足，发放失败！');
				Close();
				return false;
			}
		}
		refreshForm('workPaySave.do');		
	}
	
	function defaultValue(){
		if(confirm('该操作将上报或月考核工作时间、工作酬金填写到对应的工作时间和金额中，确定继续吗？')){
			var count = val('count');
			for(var i=0; i<parseInt(count); i++){
				setVal('gzsj'+i,val('khgzsj'+i));
				setVal('cjje'+i,val('khcjje'+i));
			}
			changezje();
		}
	}
	</script>
</head>
	<body onload="checkWinType();changezje()">
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em>
					<a>
						<%--长沙民政--%>
						<logic:equal value="10827" name="xxdm">
							<span id="tipFollow"> 学生义工 - 酬金发放 - 酬金发放 - 酬金发放详单</span>
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							<span id="tipFollow"> 勤工助学 - 酬金发放 - 酬金发放 - 酬金发放详单</span>
						</logic:notEqual>
					</a>
				</p>
			</div>
			<input type="hidden" id="mes" name="mes" value="${mes}" />
			<logic:notEmpty name="workInfo">
				<input type="hidden" id="gwdm" name="gwdm" value="<bean:write name="workInfo" property="gwdm"/>" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="workInfo" property="gwsbsj"/>" />
				<input type="hidden" id="jybcbz" name="jybcbz" value="<bean:write name="workInfo" property="jybcbz"/>" />
				<input type="hidden" id="count" name="count" value="<bean:write name="count"/>" />
				<input type="hidden" id="xn" name="xn" value="<bean:write name="workInfo" property="xn"/>" />
				<input type="hidden" id="nd" name="nd" value="<bean:write name="workInfo" property="nd"/>" />
				<input type="hidden" id="xq" name="xq" value="<bean:write name="workInfo" property="xq"/>" />
				<input type="hidden" id="yf" name="yf" value="<bean:write name="workInfo" property="yf"/>" />
				<input type="hidden" id="gwxz" name="gwxz" value="<bean:write name="workInfo" property="gwxz"/>" />
				<input type="hidden" id="syjf" name="syjf" value="<bean:write name="workInfo" property="syjf"/>" />
				<input type="hidden" id="jfglkg" name="jfglkg" value="<bean:write name="workInfo" property="jfglkg"/>" />
				<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
				<div class="tab">
		  		<table width="100%" class="formlist" id="rsT">
					<thead>
						<!--非北京联合-->
						<logic:notEqual value="11417" name="xxdm">
						<%--浙江机电职业技术学院--%>
						<logic:equal value="12861" name="xxdm">
							<tr>
								<th colspan="2">年度:${cjffY}; 月份:${cjffM}</th>
							</tr>
						</logic:equal>
						<%--end浙江机电职业技术学院--%>		
						
						<%--非浙江机电职业技术学院--%>
						<logic:notEqual value="12861" name="xxdm">
							<tr>
								<th colspan="2">学年:${workInfo.xn};年度:${workInfo.nd};学期:${workInfo.xqmc};月份:${workInfo.yf}</th>
							</tr>
						</logic:notEqual>
						<%--end非浙江机电职业技术学院--%>						
						</logic:notEqual>
						<!--end非北京联合-->

						<!--北京联合-->
						<logic:equal value="11417" name="xxdm">							
							<tr>
								<th colspan="2">年度:${workInfo.nd};学期:${workInfo.xq};月份:${workInfo.yf}</th>
							</tr>
						</logic:equal>
						<!--end北京联合-->
						</thead>
						<tbody>
						<tr>
							<th>用人单位</th>
							<td>
								<bean:write name="workInfo" property="yrdwmc" />
							</td>
						</tr>
						<tr>
							<th>所属部门</th>
							<td>
								<bean:write name="workInfo" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>工作内容</th>
							<td>
								<bean:write name="workInfo" property="gznr" />
							</td>
						</tr>
						<tr>
							<th>岗位性质</th>
							<td>
								<bean:write name="workInfo" property="gwxzmc" />
							</td>
						</tr>
						<!-- 非广州大学 -->
						<logic:notEqual name="xxdm" value="11078">
						<tr>
							<th>计酬标准</th>
							<td>
								<bean:write name="workInfo" property="jybcbz" />
								<input type="hidden" id="jybcbz"
									value="<bean:write name="workInfo" property="jybcbz" />" />
								(<bean:write name="workInfo" property="jcfsmc" />)
							</td>
						</tr>
						</logic:notEqual>
						<!--经费管理开关开-->
						<logic:equal value="1" name="workInfo" property="jfglkg">
							<tr>
								<th>剩余经费</th>
								<td>
									<bean:write name="workInfo" property="syjf" />
									（单位:元）
								</td>
							</tr>
						</logic:equal>
						<!--end经费管理开关开-->
						
						<%--武汉理工大学--%>
						<logic:equal value="10497" name="xxdm">
							<tr>
								<th>发放报酬标准</th>
								<td>
									<html:text name="workInfo" property="jybcbz" styleId="ffbcbz"
										onblur="if(parseInt(this.value)>parseInt(document.getElementById('jybcbz').value)) alert('发放报酬标准超过审批标准！')" />
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
								工
								<br />
								作
								<br />
								人
								<br />
								员
								<br />
								情
								<br />
								况
							</th>
							<td>
								<table width="100%" style="height:100%" class="formlist">
									<thead>
										<tr>
											<th>学号</th>
											<th>姓名</th>
											<th>班级</th>
											<!--广州大学-->
											<logic:equal value="11078" name="xxdm">
												<th><bean:message key="lable.xsgzyxpzxy" />上报工作时间</th>
												<th><bean:message key="lable.xsgzyxpzxy" />上报酬金金额</th>
											</logic:equal>		
											<!--浙江科技-->
											<logic:equal value="11057" name="xxdm">
												<th>月考核时间</th>
												<th>月考核酬金</th>
											</logic:equal>	
											<!--end浙江科技-->									
											
											<th>工作时间(单位:<bean:write name="workInfo" property="jcfsmc" />)</th>
											<th>金额</th>

											<!--广州大学-->
											<logic:equal value="11078" name="xxdm">
												<th>工作评价</th>
											</logic:equal>
											<!--end广州大学-->

											<logic:present name="showjsxx">
												<th>签字</td>
											</logic:present>

											<!--浙江传媒学院-->
											<logic:equal value="11647" name="xxdm">
											<th>银行卡号</th>
											<th>银行名称</th>	
											</logic:equal>
											<!--end浙江传媒学院-->

											<th>备注</th>
										</tr>
									</thead>
									<logic:empty name="ffList">
										<tr>
											<!--浙江科技-->
											<logic:equal value="11057" name="xxdm">
												<th colspan="8">
													无参加该岗位的学生记录!
												</th>
											</logic:equal>	
											<!--end浙江科技-->
											<logic:notEqual value="11057" name="xxdm">
												<th colspan="6">
													无参加该岗位的学生记录!
												</th>
											</logic:notEqual>
										</tr>
									</logic:empty>
									<tbody>
									<logic:notEmpty name="ffList">
										<logic:iterate name="ffList" id="ffList" indexId="index">
											<tr>
												<td>
													<input type="text" name="xh${index}"
														value="<bean:write name="ffList" property="xh"/>"
														style="width:80px" readonly/>
												</td>
												<td>
													<bean:write name="ffList" property="xm" />
												</td>
												<td>
													<bean:write name="ffList" property="bjmc" />
												</td>
												<!--浙江科技-->
												<logic:equal value="11057" name="xxdm">
												<td>
													${ffList.ygzsj}
													<input type="hidden" id="khgzsj${index}"
																			name="khgzsj${index}"
																			value="${ffList.ygzsj}"/>
												</td>
												<td>
													${ffList.ffcjje}
													<input type="hidden" id="khcjje${index}"
																			name="khcjje${index}"
																			value="${ffList.ffcjje}"/>
												</td>
												</logic:equal>	
												<!--end浙江科技-->
												<!--广州大学-->
												<logic:equal value="11078" name="xxdm">
														<td>
														${ffList.khgzsj}
														<input type="hidden" id="khgzsj${index}"
																			name="khgzsj${index}"
																			value="${ffList.khgzsj}"
	                                                                        readonly="readonly"/>
													</td>
													<td>
														${ffList.khcjje}
														<input type="hidden" id="khcjje${index}"
																			name="khcjje${index}"
																			value="${ffList.khcjje}"
	                                                                        readonly="readonly"/>
													</td>	
												</logic:equal>
												<!--广州大学end-->
												<logic:present name="bjlh">
													<td>
														<input type="text" id="gzsj${index}" name="gzsj${index}"
															value="<bean:write name="ffList" property="gzsj"/>"
															style="width:80px"
															onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();"
															readonly />
													</td>
												</logic:present>
												<logic:notPresent name="bjlh">
													<td>
														<%--武汉理工大学--%>
														<logic:equal value="10497" name="xxdm">
															<logic:equal value="通过" name="ffList" property="xxsh">
																<input type="text" id="gzsj${index}"
																	name="gzsj${index}"
																	value="<bean:write name="ffList" property="gzsj"/>"
																	style="width:80px" 
																	readonly="readonly"
																	onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
															</logic:equal>
															<%--武汉理工大学end--%>
															<logic:notEqual value="通过" name="ffList" property="xxsh">
																<input type="text" id="gzsj${index}"
																	name="gzsj${index}"
																	value="<bean:write name="ffList" property="gzsj"/>"
																	style="width:80px"
																	maxlength="6"
																	onkeyup="value=value.replace(/[^\d|.]/g,'')"
																	onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
															</logic:notEqual>
														</logic:equal>
														<logic:notEqual value="10497" name="xxdm">
															<input type="text" id="gzsj${index}" name="gzsj${index}"
																value="<bean:write name="ffList" property="gzsj"/>"
																style="width:80px"
																maxlength="6"
																onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" 
																onkeyup="value=value.replace(/[^\d|.]/g,'')"/>
														</logic:notEqual>
													</td>
												</logic:notPresent>
												<logic:present name="bjlh">
													<td>
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="ffList" property="cjje"/>"
															style="width:60px"
															onblur="changezje();if(this.value>300&&document.forms[0].gwxz.value == '001') {alert('金额超限,请填写相应备注!');document.all(this.id.replace('cjje','bz')).focus();}"
															readonly />
													</td>
												</logic:present>
												<logic:notPresent name="bjlh">
													<%--武汉理工大学--%>
													<logic:equal value="10497" name="xxdm">
														<td>
															<input type="text" id="cjje${index}" name="cjje${index}"
																value="<bean:write name="ffList" property="cjje"/>"
																style="width:60px" 
																readonly="readonly"
																onblur="changezje();" />
														</td>
													</logic:equal>
													<%--武汉理工大学end--%>
													<logic:notEqual value="10497" name="xxdm">
														<td>
															<input type="text" id="cjje${index}" name="cjje${index}"
																value="<bean:write name="ffList" property="cjje"/>"
																style="width:60px"
																maxlength="6"
																onkeyup="value=value.replace(/[^\d|.]/g,'')"
																onblur="changezje();" />
														</td>
													</logic:notEqual>
												</logic:notPresent>
												<logic:present name="showjsxx">
													<td>
														&nbsp;
													</td>
												</logic:present>
												<!--广州大学-->
												<logic:equal value="11078" name="xxdm">
													<td>
														<input type="text" id="gzpj${index}" name="gzpj${index}"
																	value="<bean:write name="ffList" property="gzpj"/>"
																	style="width:120px"
																	maxlength="150"
																	onblur="" />
													</td>
												</logic:equal>
												<!--广州大学-->

												<!--浙江传媒学院-->
												<logic:equal value="11647" name="xxdm">
												<td>
													<input type="text" name="yhkh${index}"
																value="<bean:write name="ffList" property="kh"/>"
																maxlength="20"
																style="120px"
																readonly="readonly"
																onkeyup="value=value.replace(/[^\d]/g,'') "/>			
												</td>
												<td>
													<input type="text" name="yhmc${index}"
																value="<bean:write name="ffList" property="yhmc"/>"
																maxlength="15"
																readonly="readonly"
																style="width:120px"/>				
												</td>
												</logic:equal>
												<!--end浙江传媒学院-->
												<td>
													<%--武汉理工大学--%>
													<logic:equal value="10497" name="xxdm">
														<logic:equal value="通过" name="ffList" property="xxsh">
															<input type="text" name="bz${index}"
																value="<bean:write name="ffList" property="bz"/>"
																style="width:120px" readonly="readonly" />
														</logic:equal>
														<logic:notEqual value="通过" name="ffList" property="xxsh">
															<input type="text" name="bz${index}"
																value="<bean:write name="ffList" property="bz"/>"
																maxlength="60"
																style="width:120px" />
														</logic:notEqual>
													</logic:equal>
													<%--非武汉理工大学--%>
													<logic:notEqual value="10497" name="xxdm">
														<input type="text" name="bz${index}"
															value="<bean:write name="ffList" property="bz"/>"
															maxlength="60"
															style="120px" />
													</logic:notEqual>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<tr>
							<th>金额总计（单位:元）</th>
							<td>
								<div align="center" id="zje"></div>
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="2">
					          <div class="btn">
					            <button type="button" onclick="commit();return false;" style="width:80px"
									id="buttonSave">
									保 存
								</button>
								<!--浙江科技学院-->
								<logic:equal value="11057" name="xxdm">
									<button type="button" onclick="defaultValue()" style="width:80px"
										id="buttonDefault">
										默认
									</button>
								</logic:equal>
								<!--end浙江科技学院-->

								<!--广州大学-->
								<logic:equal value="11078" name="xxdm">
									<button type="button" onclick="defaultValue();return false;" style="width:80px"
										id="buttonDefault">
										默认
									</button>
								</logic:equal>
								<!--end广州大学-->

								<button type="button" onclick="Close();return false;" style="width:80px"
									id="buttonSave">
									关 闭
								</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>
					</table>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
					<logic:present name="mes">
						<script>
							alert(document.getElementById('mes').value);
						</script>
					</logic:present>
					<script>
					Close();
				</script>
				</logic:equal>

				<logic:equal value="false" name="result">
					<logic:present name="mes">
						<script>
						alert(document.getElementById('mes').value);
						Close();
						</script>
					</logic:present>
					<logic:notPresent name="mes">
						<script>
							alert("操作失败！");
						</script>
					</logic:notPresent>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

