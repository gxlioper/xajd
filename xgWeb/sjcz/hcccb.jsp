<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function checkForm(url) {
				var pk = 'save_cc-save_qdz-save_zdz-save_kcsj-save_ddsj-save_pj';
				var key = pk.split("-");
				
				if(key.length > 0){
					for(var i=0;i<key.length;i++){
						if($(key[i])){
							if($(key[i]).value == ""){
								alert("带\"*\"项目不能为空，请确认");
								return false;
							}
						}
					}
				}
			
				var qdz = $('qdz').value.trim();
				var zdz = $('zdz').value.trim();
				
				if (qdz == zdz) {
					alert('起点站和终点站不能为同一车站！');
					return false;
				}
				refreshForm(url);
			}		
			
		</script>
	</head>
	<body>
		<html:form action="/rcsw_pwgl" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<logic:notEqual value="update" name="doType">
											<button type="button" class="button2"
												onclick="checkForm('/xgxt/rcsw_pwgl.do?method=ccUpdate&doType=save');"
												id="btn_bc">
												保 存
											</button>
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal value="update" name="doType">
										<button type="button" class="button2"
											onclick="checkForm('/xgxt/rcsw_pwgl.do?method=ccUpdate&doType=modify');"
											id="btn_bc">
											保 存
										</button>
									</logic:equal>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="button2" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>车次信息维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>车次
							</th>
							<td>
								<html:text property="save_cc" style="width: 120px" styleId="cc"
									value="${rs.cc }" maxlength="6" onblur="checkCc(this)" />
							</td>
							<th>
								当前状态
							</th>
							<td>
								<html:select property="save_dqzt" style="width:90px"
									styleId="dqzt" value="${rs.dqzt }">
									<html:option value=""></html:option>
									<html:option value="出售">出售</html:option>
									<html:option value="售完">售完</html:option>
								</html:select>
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>起点站
							</th>
							<td>
								<html:select property="save_qdz" style="width:120px"
									styleId="qdz" value="${rs.qdz}">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czdm"
										labelProperty="czmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>终点站
							</th>
							<td>
								<html:select property="save_zdz" style="width:120px"
									styleId="zdz" value="${rs.zdz}">
									<html:option value=""></html:option>
									<html:options collection="czList" property="czdm"
										labelProperty="czmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>开车时间
							</th>
							<td>
								<html:text property="save_kcsj" style="width: 120px" readonly="true"
									styleId="kcsj" value="${rs.kcsj}" onclick="return showCalendar(this.id,'HH:mm')" />
							</td>
							<th>
								<font color="red">*</font>到达时间
							</th>
							<td>
								<html:text property="save_ddsj" style="width: 120px" readonly="true"
									styleId="ddsj" value="${rs.ddsj}" onclick="return showCalendar(this.id,'HH:mm')"/>
							</td>
						</tr>
						<tr>
							<th>
								运行时间
							</th>
							<td>
								<html:text property="save_yxsj" style="width: 90px"
									styleId="yxsj" value="${rs.yxsj}"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" />
								(小时)
							</td>
							<th>
								<font color="red">*</font>票价
							</th>
							<td>
								<html:text property="save_pj" style="width: 90px" styleId="pj"
									value="${rs.pj}" onblur="checkMoney(this)" />
								(元)
							</td>
						</tr>
						<tr>
							<th>
								停靠站
							</th>
							<td colspan="3">
								<html:textarea property='save_tkz'
									style="word-break:break-all;width:99%" rows='4'
									value="${rs.tkz}" onblur="checkLen(this,150)" />
								<br />
								提示：将所有的停靠站以符号（，）分割！
							</td>
						</tr>
					</tbody>
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