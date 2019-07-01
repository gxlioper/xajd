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
					$('content1').value = frames('eWebEditor1').getHTML();
					$('zphnr').value = $('content1').value; 
				}
			}
		</script>
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
	 			if(window.dialogArguments){
	 				window.close();
	 				dialogArgumentsQueryChick();
	 			}	
				</script>
		</logic:present>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>招聘维护</span>
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
									<logic:present name="doType">

										<logic:equal value="sh" name="doType">
											<button
												onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&save_shzt=通过&doType=modify','');">
												通过
											</button>
											<button
												onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&save_shzt=不通过&doType=modify','');">
												不通过
											</button>
											<button
												onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&save_shzt=退回&doType=modify','');">
												退回
											</button>
										</logic:equal>

										<logic:notEqual value="sh" name="doType">
											<logic:notEqual value="view" name="doType">
												<html:hidden property="save_shzt" value="需重审"/>
												<button
													onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&doType=modify','save_zplxdm-save_zphbt-save_zphnr');">
													保存
												</button>
											</logic:notEqual>
										</logic:notEqual>

									</logic:present>
									<logic:notPresent name="doType">
										<button
											onclick="pubNews();saveUpdate('jywebUseCheckSession.do?method=zphUpdate&doType=save','save_zplxdm-save_zphbt-save_zphnr');">
											保存
										</button>
									</logic:notPresent>
									<button onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>招聘类型
							</th>
							<td colspan="3">
								<html:select property="save_zplxdm" value="${rs.zplxdm}">
									<html:options collection="zplxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>招聘标题
							</th>
							<td colspan="3">
								<html:hidden property="save_fbr" value="${jyweb_userName }" />
								<input type="hidden" name="save_fbsj" value="${nowTime }" />

								<html:text property="save_zphbt" maxlength="50"
									value="${rs.zphbt }" style="width:90%"
									onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
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
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>招聘内容
							</th>

							<td colspan="3">
								<input type="hidden" name="content1" value="${rs.zphnr }" />
								<input type="hidden" name="save_zphnr" id="zphnr"
									value="${rs.zphnr }" />
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="99%" height="350">
								</iframe>
							</td>
						</tr>
					</tbody>
					<logic:equal value="sh" name="doType" scope="request">
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
								<th width="14%">
									审核人
								</th>
								<td width="36%">
									${jyweb_realName }
									<html:hidden property="save_shr" value="${jyweb_userName }" />
								</td>
								<th width="14%">
									审核时间
								</th>
								<td>
									${nowTime }
									<html:hidden property="save_shsj" value="${nowTime }" />
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<logic:equal value="view" name="doType">
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
