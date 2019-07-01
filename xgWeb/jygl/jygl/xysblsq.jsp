<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/jyglDAO.js"></script>
		<script type="text/javascript">
			function szNewJyxybh(){
				var sybh = window.dialogArguments.document.getElementById('sybh').value;
				
				if ($('newjyxybh')){
					if (sybh==0){
						alert("就业协议编号已分配完，请先维护协议书编号!");
						return false;
					} else {
						dwr.engine.setAsync(false);
							jyglDAO.getWsybh(function(data){
								var wsybh = data.split("@");
								$('newjyxybh').value = wsybh[0];
							});
						dwr.engine.setAsync(true);
						return true;
					} 
				}
			}
			
		</script>

	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="url" id="url"
				value="/jygl.do?method=xysblsq&flg=${flg }" />

			<logic:equal value="stu" name="userType">
				<logic:empty name="rs">
					<div class="prompt" id="div_help">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							对不起，您的就业协议书还未登记过 ！
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none'"></a>
					</div>

					<script defer="defer">
						$('saveButton').disabled = true;
					</script>
				</logic:empty>
			</logic:equal>
			<logic:present name="rs">
				<logic:equal value="1" property="xysbbshjb" name="cssz">
					<logic:equal value="退回" name="rs" property="xxsh">
						<html:hidden property="save_xxsh" value="需重审" />
					</logic:equal>
				</logic:equal>
				<logic:equal value="2" property="xysbbshjb" name="cssz">
					<logic:equal value="退回" name="rs" property="xysh">
						<html:hidden property="save_xysh" value="需重审" />
					</logic:equal>
				</logic:equal>
			</logic:present>
			
			

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notPresent name="doType">
										<button id="saveButton"
											onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&doType=save&flg=${flg }','save_xh-save_bblbdm');">
											保存
										</button>
									</logic:notPresent>
									<logic:present name="doType">
										<logic:equal value="update" name="doType">
											<button id="saveButton"
												onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&doType=modify','save_xh-save_bblbdm');">
												保存
											</button>
										</logic:equal>
										<logic:equal value="sh" name="doType">
											<logic:equal value="1" name="cssz" property="xysbbshjb">
												<button id="saveButton"
													onclick="if(szNewJyxybh()){saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=通过&doType=modify','');}">
													通过
												</button>
												<button id="saveButton"
													onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=不通过&doType=modify','');">
													不通过
												</button>
												<button id="saveButton"
													onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=退回&doType=modify','');">
													退回
												</button>
											</logic:equal>
											<logic:equal value="2" name="cssz" property="xysbbshjb">
												<logic:equal value="xy" name="userType" scope="session">

													<logic:equal value="退回" name="rs" property="xxsh">
														<button id="saveButton"
															onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=通过&save_xxsh=需重审&doType=modify','');">
															通过
														</button>
													</logic:equal>
													<logic:notEqual value="退回" name="rs" property="xxsh">
														<button id="saveButton"
															onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=通过&doType=modify','');">
															通过
														</button>
													</logic:notEqual>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=不通过&doType=modify','');">
														不通过
													</button>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xysh=退回&doType=modify','');">
														退回
													</button>
												</logic:equal>
												<logic:notEqual value="xy" name="userType" scope="session">

													<button id="saveButton"
														onclick="if(szNewJyxybh()){saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=通过&doType=modify','');}">
														通过
													</button>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=不通过&doType=modify','');">
														不通过
													</button>
													<button id="saveButton"
														onclick="saveUpdate('/xgxt/jygl.do?method=xysblsq&save_xxsh=退回&doType=modify','');">
														退回
													</button>
												</logic:notEqual>
											</logic:equal>
										</logic:equal>
									</logic:present>
									<logic:equal value="sq" name="flg">
										<button type="reset">
											重置
										</button>
									</logic:equal>
									<logic:notEqual value="sq" name="flg">
										<button onclick="window.close();return false;">
											关闭
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th style="width:16%">
								<span class="red">*</span>学号
							</th>
							<td style="width:34%">
								<html:text property="save_xh" value="${rs.xh }" readonly="true"></html:text>
								<logic:notPresent name="doType">
									<logic:notEqual value="stu" name="userType">
										<button
											onclick="showTopWin('/xgxt/jygl.do?method=xysData',800,500);"
											id="buttonFindStu" class="btn_01">
											选择
										</button>
									</logic:notEqual>
								</logic:notPresent>
							</td>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb}
							</td>
							<th>
								身份证号
							</th>
							<td>
								${rs.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								原就业协议书编号
							</th>
							<td>
								<html:text property="save_yjyxybh" readonly="true"
									value="${rs.yjyxybh }${rs.jyxybh }"></html:text>
							</td>
							<th>
								<span class="red">*</span>补办类别
							</th>
							<td>
								<html:select property="save_bblbdm" value="${rs.bblbdm }">
									<html:options collection="bblbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								申请理由
								<br />
								<font color="red"><限500字>
								</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_sqly" value="${rs.sqly }"
									style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>

						<logic:equal value="sh" name="doType">
							<logic:equal value="1" name="cssz" property="xysbbshjb">
								<tr>
									<th>
										审核意见
										<br />
										<font color="red"><限500字>
										</font>
									</th>
									<td style="word-break:break-all;" colspan="3">
										<!-- 新就业协议编号 -->
										<html:hidden property="save_newjyxybh" styleId="newjyxybh" />

										<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
											style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										审核人
									</th>
									<td>
										${userNameReal }
										<html:hidden property="save_xxshr" value="${userName }" />
									</td>
									<th>
										审核时间
									</th>
									<td>
										${now }
										<html:hidden property="save_xxshsj" value="${now }" />
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
								<logic:equal value="xy" name="userType" scope="session">
									<tr>
										<th>
											审核意见
											<br />
											<font color="red"><限500字>
											</font>
										</th>
										<td style="word-break:break-all;" colspan="3">
											<html:textarea property="save_xyshyj" value="${rs.xyshyj }"
												style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
									<tr>
										<th>
											审核人
										</th>
										<td>
											${userNameReal }
											<html:hidden property="save_xyshr" value="${userName }" />
										</td>
										<th>
											审核时间
										</th>
										<td>
											${now }
											<html:hidden property="save_xyshsj" value="${now }" />
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="session">
									<tr>
										<th>
											审核意见
											<br />
											<font color="red"><限500字>
											</font>
										</th>
										<td style="word-break:break-all;" colspan="3">
											<!-- 新就业协议编号 -->
											<html:hidden property="save_newjyxybh" styleId="newjyxybh" />

											<html:textarea property="save_xxshyj" value="${rs.xxshyj }"
												style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
									<tr>
										<th>
											审核人
										</th>
										<td>
											${userNameReal }
											<html:hidden property="save_xxshr" value="${userName }" />
										</td>
										<th>
											审核时间
										</th>
										<td>
											${now }
											<html:hidden property="save_xxshsj" value="${now }" />
										</td>
									</tr>
								</logic:notEqual>
							</logic:equal>
						</logic:equal>
						<logic:equal value="view" name="doType">
							<logic:equal value="1" name="cssz" property="xysbbshjb">
								<tr>
									<th>
										学校审核
									</th>
									<td>
										${rs.xxsh }
									</td>
									<th>新就业协议编号</th>
									<td>${rs.newjyxybh }</td>
								</tr>
								<tr>
									<th>
										审核意见
									</th>
									<td style="word-break:break-all;" colspan="3">
										${rs.xxshyj }
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="2" name="cssz" property="xysbbshjb">
								<tr>
									<th>
										<bean:message key="lable.xb" />审核
									</th>
									<td>
										${rs.xysh }
									</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>
										审核意见
									</th>
									<td style="word-break:break-all;" colspan="3">
										${rs.xyshyj }
									</td>
								</tr>
								<tr>
									<th>
										学校审核
									</th>
									<td>
										${rs.xxsh }
									</td>
									<th>新就业协议编号</th>
									<td>${rs.newjyxybh }</td>
								</tr>
								<tr>
									<th>
										审核意见
									</th>
									<td style="word-break:break-all;" colspan="3">
										${rs.xxshyj }
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
				if (window.dialogArguments) {
					window.close();
					dialogArgumentsQueryChick();
				}
			</script>
		</logic:present>
	</body>
</html>
