<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dkxx.js"></script>
		<script type="text/javascript">
			function saveDksq(url){
				
				var xn = jQuery("#xn").val();
				var dkje = jQuery("#dkje").val();
				var dkqx = jQuery("#dkqx").val();
				var sqly = jQuery("#sqly").val();
				var htbh = jQuery("#htbh").val();
				var xfyss = jQuery("#xfyss").val();
				var zsfyss = jQuery("#zsfyss").val();
				
				if (xfyss==""||zsfyss==""||xn == "" || dkje == "" || dkqx == "" || sqly == "" || htbh == ""){
					showAlertDivLayer("请将必填项填写完整。");
					return false;
				}
				var dkzs=jQuery("#dkje").val();
				if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
					showAlertDivLayer("贷款金额超过"+jQuery("#dkzesx").val()+"元!");
					jQuery("#dkje").focus();
					return false;
				}
				ajaxSubFormWithFun("xyddkForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
			var _getAfterXn = function(xn,c){
				
				var xnInfo = xn.split("-");
				var star = Number(jQuery.trim(xnInfo[0]))+c;
				var end = Number(jQuery.trim(xnInfo[1]))+c;
				
				return star + "-" + end;
			};
			
			var xnChange = function(){
				
				jQuery("#dkxxTable tr").remove();
				
				var xn = jQuery("#xn").val();
				var xz = jQuery("#xz").val();
				if (xn != "" && xz != ""){
					for (var i = 0 ; i < Number(xz); i++){
						var newXn = _getAfterXn(xn,i);
						
						var tr = jQuery("<tr></tr>");
						var td = jQuery("<td><input type='hidden' name='dkxn' value='"+newXn+"'/>"+newXn+"</td>");
						var td1 = jQuery("<td><input type='text' name='xf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
						var td2 = jQuery("<td><input type='text' name='zsf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
						var td3 = jQuery("<td><input type='text' name='shf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
						tr.append(td).append(td1).append(td2).append(td3);
						jQuery("#dkxxTable").append(tr);
					}
				}
			};
			
			jQuery(function(){
				jQuery("#xn").bind("change",xnChange);
				
				//如果贷款明细学年学费为空，则按学制初始化一次
				if(${empty dkxxList}){
					xnChange();
				}
			});
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkSyddk" method="post" styleId="xyddkForm">
			<input type="hidden" name="xz" id="xz" value="${jbxx.xz }"/>
			<html:hidden property="id" />
			<html:hidden property="xn"  />
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%;"><font color="red">*</font>贷款学年</th>
							<td>
								<html:select property="xn" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th style="width:20%;"><font color="red">*</font>贷款银行</th>
							<td>
								<html:select property="dkyh" styleId="dkyh">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm" labelProperty="yhmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>贷款金额（元）</th>
							<td>
								<html:text property="dkje" styleId="dkje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th><font color="red">*</font>贷款期限（月）</th>
							<td>
								<html:text property="dkqx" styleId="dkqx" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10264">
							<tr>
								<th><font color="red">*</font>住宿费应收数（元）</th>
								<td>
									<html:text property="xfyss" styleId="xfyss" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>
								<th><font color="red">*</font>学费应收数（元）</th>
								<td>
									<html:text property="zsfyss" styleId="zsfyss" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th><font color="red">*</font>合同编号</th>
							<td>
								<html:text property="htbh" styleId="htbh" maxlength="40" ></html:text>
							</td>
							<th>贷款开始时间</th>
							<td>
								<html:text property="dkkssj" styleId="dkkssj" 
										   onfocus="showCalendar('dkkssj','y-mm-dd',false);"
										   maxlength="20" readonly="true"></html:text>
							</td>
						</tr>
						<logic:equal name="xxdm" value="12389">
							<tr>
								<th>贷款次数</th>
								<td>
									<html:text property="dkcs" styleId="dkcs" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>
								<th>到期日期</th>
								<td>
									<html:text property="dqrq" styleId="dqrq" maxlength="20" onfocus="showCalendar('dqrq','y-mm-dd',false,'dkkssj');" readonly="true"></html:text>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>回执检验码</th>
							<td colspan="3">
								<html:text property="hzjym" styleId="hzjym" maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<font color="red">注意：请按学年用款情况如实填写，无则留空。</font>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align:center;" >
									<thead>
										<tr>
											<td align="center">学年</td>
											<td align="center">学费（元）</td>
											<td align="center">住宿费（元）</td>
											<td align="center">生活费（元）</td>
										</tr>
									</thead>
									<tbody id="dkxxTable">
										<logic:present name="dkxxList">
											<logic:iterate id="d" name="dkxxList">
												<tr>
													<td>
														${d.xn }
														<input type="hidden" name="dkxn" value="${d.xn }"/>
													</td>
													<td>
														<input value="${d.xf }" name="xf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td>
														<input value="${d.zsf }" name="zsf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td>
														<input value="${d.shf }" name="shf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>申请理由
								<br/><font color="red">(限输入500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onblur="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkSyddk.do?method=update');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>