<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
<!--
	 	function delFj(){
			
				var filepath = $("fjlj").value;
				var pk = "xczt||xcdd||xcsj";
				var pkValue =  $("pk").value;
				
				if (confirm("确认要删除该附件?")) {
					var url="/xgxt/xsh.do?method=deleteFile";
					url+="&filepath="+filepath;
					url+="&url=xcxxUpdate";
					url+="&tableName=xsh_xcxx"
					url+="&pk="+pk;
					url+="&pkValue="+pkValue;
					url+="&doType=update"
					refreshForm(url);
				}
		}
//-->
</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xsh" method="post" enctype="multipart/form-data">
			<input type="hidden" name="message" value="${message }" id="message" />
			<input type="hidden" name="pk" value="${pk }" id="pk" />
			<html:hidden property="fjlj" value="${rs.fjlj }" styleId="fjlj" />


			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>宣传信息维护</span>
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
									
									<logic:notPresent name="doType">
										<button id="buttonSave"
											onclick="saveUpdate('/xgxt/xsh.do?method=xcxxUpdate&doType=save','xcdd-xcsj-xcdd-xckh');">
											保存
										</button>
									</logic:notPresent>

									<logic:present name="doType">
										<logic:notEqual value="view" name="doType">
											<button id="buttonSave"
												onclick="saveUpdate('/xgxt/xsh.do?method=xcxxUpdate&doType=modify','hdzt-hdsj-hddd');">
												保存
											</button>
										</logic:notEqual>
									</logic:present>
									<button id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>宣传主题
							</th>
							<td width="30%">
								<logic:equal value="update" name="doType">
									<html:text property="xczt" maxlength="25" value="${rs.xczt }"
										onkeyup="value=value.replace(/[+&%#]/g,'')" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="xczt" maxlength="25" value="${rs.xczt }"
										onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
								</logic:notEqual>
							</td>
							<th width="16%">
								<font color="red">*</font>宣传时间
							</th>
							<td width="30%">
								<logic:equal value="update" name="doType">
									<html:text property="xcsj" readonly="true" value="${rs.xcsj }"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="xcsj" readonly="true" styleId="xcsj"
										onclick="return showCalendar('xcsj','y-mm-dd');"
										onblur="dateFormatChg(this)" value="${rs.xcsj }"></html:text>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>宣传地点
							</th>
							<td colspan="3">
								<logic:equal value="update" name="doType">
									<html:text property="xcdd" maxlength="50" style="width:92%"
										value="${rs.xcdd }"
										onkeyup="value=value.replace(/[+&%#]/g,'')" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="xcdd" maxlength="50" style="width:92%"
										value="${rs.xcdd }"
										onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
								</logic:notEqual>


							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>宣传口号
							</th>
							<td colspan="3">
								<logic:equal value="update" name="doType">
									<html:text property="xckh" maxlength="50" style="width:92%"
										value="${rs.xckh }"
										onkeyup="value=value.replace(/[+&%#]/g,'')" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="update" name="doType">
									<html:text property="xckh" maxlength="50" style="width:92%"
										value="${rs.xckh }"
										onkeyup="value=value.replace(/[+&%#]/g,'')"></html:text>
								</logic:notEqual>

							</td>
						</tr>
						<tr>
							<th>
								附件
							</th>
							<td align=left colspan="3">
								<logic:present property="fjlj" name="rs">
									<logic:notEmpty property="fjlj" name="rs">
										<a
											href="xsh.do?method=downLoadFile&dir=<bean:write name = "rs" property='fjlj'/>"
											target="_blank" /> 
												<font color="red">下载附件</font> 
										</a>
									</logic:notEmpty>
									<logic:present property="fjlj" name="rs">
										<logic:notEmpty property="fjlj" name="rs">
											<logic:notEqual value="view" name="doType">
											<a href="#" onclick="delFj()" />点击删除</a>
											</logic:notEqual>
											<br />
										</logic:notEmpty>
									</logic:present>
								</logic:present>
								
								<logic:notEqual value="view" name="doType">
									<logic:empty property="fjlj" name="rs">
											<input type="file" name="uploadFile" style="width:60%" id="kk" contenteditable="false"/>
											&nbsp;&nbsp;
											<font color="red">(文件大小小于&lt;10M&gt;)</font>
									</logic:empty>
								</logic:notEqual>
							</td>
						</tr>
						<customTag:customTable rsname="rs" nodeslist="xshForm"
							doType="updata" scope="request" />
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" rows="8" style="width:92%"
									onblur="checkLen(this,500)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
						</tbody>
				</table>
				</div>
		</html:form>
		<logic:present name="message">
			<script language="javascript">
         		alert("${message}");
         		if (window.dialogArguments) {
					Close();
					dialogArgumentsQueryChick();
				}
         	</script>
		</logic:present>
	</body>