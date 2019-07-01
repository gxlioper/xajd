<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
</head>
	<body onload="initZje();">
		<html:form action="/cjff">
			<input type="hidden" value="<bean:write name="xxdm"/>" id="xxdm" />
			<input type="hidden" value="<bean:write name="userType"/>" id="userType" />
			<input type="hidden" value="<bean:write name="pTotal"/>" id="count" name="count" />
			<input type="hidden" value="<bean:write name="pkValue"/>" id="pkValue" name="pkValue" />
			<input type="hidden" value="<bean:write name="zgcjje"/>" id="zgcjje" name="zgcjje" />
			<input type="hidden" value="<bean:write name="zggzsj"/>" id="zgcjje" name="zggzsj" />
			<input type="hidden" value="<bean:write name="yrdwmc"/>" id="yrdwmc" name="yrdwmc" />
			<input type="hidden" value="<bean:write name="gwdm"/>" id="gwdm" name="gwdm" />
			<input type="hidden" value="" id="number" name="number"/>				
			<input type="hidden" value="${pk}" id="pk" name="pk" />
			<input type="hidden" value="" id="yrdw" />
			<input type="hidden" value="" id="gwmc" />
			<input type="hidden" value="" id="printFlag" />
			<input type="hidden" value="" id="tr_length" />
			<input type="hidden" value="" id="addRowNum" name="addRowNum"/>
			<input type="hidden" value="<bean:write name="currentPage"/>" id="currentPage" name="currentPage"/>
			<input type="hidden" value="<bean:write name="yf"/>" id="yf" name="yf"/>
			<input type="hidden" value="<bean:write name="nd"/>" id="nd" name="nd"/>
			<input type="hidden" value="<bean:write name="post" property="spbcbz"/>" id="spbcbz"  name="spbcbz"/>
			<input type="hidden" value="" id="start_length"  name="start_length"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学-酬金发放-临时工资发放</a>
				</p>
			</div>
			<div class="tab">
			<table border="0" id="rsTable" class="formlist" style="width:100%">
				<tbody>
				<tr>
					<td>
						<div align="center" id="title">
							<p>
								<font size="5"> <b>（ <bean:write name="year" /> ）年（
										<bean:write name="month" /> ）月临时工资发放表 </b> </font>
							</p>
							<p>
								&nbsp;
							</p>
						</div>
						<fieldset>
							<legend>
								<button type="button" onclick="clickAdd();">
									+
								</button>
								<button type="button" onclick="clickRemove();">
									-
								</button>
							</legend>
							<div class="con_overlfow">
							<table class="dateline" align="center" style="width:100%" id="rsT">
								<div align="right">${nowtime}</div>
								<!-- 打印时第一行不显示- -->
								<tr>
									<td colspan="11">
									</td>
								</tr>
								<tr>
									<td height="29" colspan="11">
										总人数：
										<bean:write name="pTotal" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 总金额：
										<span id="zje"> <bean:write name="zje" /> </span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										负责老师签名盖章：
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										负责老师联系电话：
									</td>
								</tr>
								<tr>
									<th>
										序号
									</th>
									<th>
										工作单位
									</th>
									<th>
										工作部门
									</th>
									<th>
										班级序号
									</th>
									<th>
										学号
									</th>
									<th>
										姓名
									</th>
									<th>
										月工作总时数
									</th>
									<th>
										金额
									</th>
									<th>
										联系电话
									</th>
									<th>
										学生签名
									</th>
									<th>
										备注
									</th>
								</tr>
								<logic:notEmpty name="rs">
									<logic:iterate id="list" name="rs" indexId="index">
										<tr>
											<logic:iterate id="v" name="list" offset="4" length="1">
												<input type="hidden" id="xh${index}"
													value="<bean:write name="v" />" name="xh${index}" />
											</logic:iterate>
											<logic:iterate id="v" name="list" offset="0" length="1">
												<td>
													${index+1}
												</td>
											</logic:iterate>

											<logic:iterate id="v" name="list" offset="1" length="5">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td>
												<logic:iterate id="v" name="list" offset="6" length="1">
													<input type="text" id="gzsj@@!!${index}" style="width:80px"
														value="<bean:write name="v" />" name="gzsj${index}"
														onchange="changeCjje(this)" 
                                                        onkeyup="alert('456789');value=value.replace(/[^\d|.]/g,'') "/>
												</logic:iterate>
											</td>
											<td>
												<logic:iterate id="v" name="list" offset="7" length="1">
													<input type="text" id="cjje${index}" style="width:40px"
														value="<bean:write name="v" />" name="cjje${index}"
														id="cjje${index}"
														onchange="checkRange(this)" 
                                                        onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="list" offset="8" length="2">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td>
												<logic:iterate id="v" name="list" offset="10" length="1">
													<input type="text" id="bz${index}" style="width:80px"
														value="<bean:write name="v" />" name="bz${index}" />
												</logic:iterate>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
								<tbody id="jj">

								</tbody>								
							</table>
							</div>
						</fieldset>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2" onclick="saveLsgz()" style="width:80px">
							保 存
						</button>
						<button type="button" class="button2" onclick="goLsgzUp();" style="width:80px" id="up">
							上一页
						</button>
						<button type="button" class="button2" onclick="goLsgzNext();" style="width:80px"  id="nextOne">
							下一页 
						</button>
						<button type="button" class="button2" onclick="printYgzbb()" style="width:80px">
							打 印
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
				  		alert('操作成功！');
				  	</script>
				</logic:equal>
			<logic:equal value="false" name="result">
				<logic:present name="msg">
					<input type="hidden" id="msg" name="msg" value="<bean:write name="msg"/>" />
					<script>
						alert(document.getElementById('msg').value);				  			
					</script>
				</logic:present>
				<logic:notPresent name="msg">
					<script>
						alert('操作失败！');
					</script>
				</logic:notPresent>
			</logic:equal>
		</logic:present>

	</html:form>
	</body>
</html>
