<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function pubNews(){
				if(frames('eWebEditor1').getHTML()){
					$('zpnr').value = frames('eWebEditor1').getHTML();
				}
			}
		</script>
		<logic:present name="message">
			<script type="text/javascript">
				alert("${message}");
	 			if(window.dialogArguments){
	 				window.close();
	 				dialogArgumentsQueryChick();
	 			}	
			</script>
		</logic:present>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>校内招聘维护</a>
			</p>
		</div>

		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="save_id" value="${rs.id }" />
			<input type="hidden" name="tempDwmc" id="tempDwmc"
				value="${tempDwmc }" />
			<input type="hidden" name="tempGwValue" id="tempGwValue"
				value="${tempGwValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>校内招聘维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:equal value="sh" name="doType">
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&save_shzt=通过&doType=save','');">
											通过
										</button>
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&save_shzt=不通过&doType=save','');">
											不通过
										</button>
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&save_shzt=退回&doType=save','');">
											退回
										</button>
										<button name="关闭" onclick="window.close();return false;">
											关闭
										</button>
									</logic:equal>
									<logic:notEqual value="sh" name="doType">
									<logic:notEqual value="show" name="doType">
										<html:hidden property="save_shzt" value="需重审"/>
										<button
											onclick="pubNews();saveUpdate('/xgxt/jyweb.do?method=ztzpUpdate&doType=save','save_zpzt-save_zpsj-save_zpdd-tempGwValue');">
											保存
										</button>
										<button onclick="refreshForm('jyweb_ztzpManage.do');">
											返回
										</button>
									</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="show" name="doType">
										<button name="关闭" onclick="window.close();return false;">
											关闭
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>招聘标题
							</th>
							<td width="86%" colspan="3">
								<html:text property="save_zpzt" maxlength="50" style="width:90%"
									value="${rs.zpzt}"></html:text>
							</td>
						</tr>
						<tr>
							<th width="14%">
								<span class="red">*</span>招聘时间
							</th>
							<td width="36%">
								<html:text property="save_zpsj" styleId="zpsj"
									value="${rs.zpsj}" onblur='dateFormatChg(this)'
									onclick="showCalendar(this.id,'y-mm-dd')"></html:text>
							</td>
							<th width="14%">
								<span class="red">*</span>招聘地点
							</th>
							<td width="36%">
								<html:text property="save_zpdd" maxlength="50"
									value="${rs.zpdd}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								发布人
							</th>
							<td>
								${rs.fbr }
								<input type="hidden" value="${jyweb_realName }" name="save_fbr" />
							</td>
							<th>
								发布时间
							</th>
							<td>
								${rs.fbsj }
							</td>
						</tr>
						<tr>
							<th>
								招聘岗位
							</th>
							<td colspan="2">
								<div id="gwxx">
									<table style="width:100%">
										<thead>
											<tr>
												<td>
													单位名称
												</td>
												<td>
													专业/岗位名称
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:iterate id="v" name="ztzpGwList">
												<tr>
													<td>
														${v.gsmc }
													</td>
													<td>
														${v.zpzwmc }
													</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</div>
							</td>
							<td>
							<logic:notEqual value="show" name="doType">
								<button
									onclick="showTopWin('/xgxt/jyweb.do?method=gwxxk',650,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>在线编辑器</span>
								</th>
							</tr>
						</thead>
						<tbody >
						<tr>
							<th>
								<span class="red">*</span>招聘内容
							</th>

							<td colspan="3">
								<input type="hidden" name="save_zpnr" id="zpnr"
									value="<bean:write name="rs" property="zpnr" filter="true"/>" />
								<input type="hidden" name="content1" id="content1"
									value="<bean:write name="rs" property="zpnr" filter="true"/>" />
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
										scrolling="no" width="96%" height="350">
								</iframe>
							</td>
						</tr>
					</tbody>
					<logic:equal value="sh" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									审核意见
									<br />
									<font color="red"><限500字>
									</font>
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" value="${rs.shyj }"
										onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${jyweb_realName }
									<html:hidden property="save_shr" value="${jyweb_userName }" />
								</td>
								<th>
									审核时间
								</th>
								<td>
									${nowTime }
									<html:hidden property="save_shsj" value="${nowTime }" />
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:equal value="show" name="doType">
						<thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									${rs.shyj }
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${rs.shr }
								</td>
								<th>
									审核时间
								</th>
								<td>
									${rs.shsj }
								</td>
							</tr>
						</tbody>
					</logic:equal>
				</table>
			</div>
		</html:form>
	</body>
</html>
