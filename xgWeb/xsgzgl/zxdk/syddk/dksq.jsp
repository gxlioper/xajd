<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>

		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dksq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script type="text/javascript" defer="defer">
		jQuery(function(){
			/*
			var _getAfterXn = function(xn,c){
				
				var xnInfo = xn.split("-");
				var star = Number(jQuery.trim(xnInfo[0]))+c;
				var end = Number(jQuery.trim(xnInfo[1]))+c;
				
				return star + "-" + end;
			};
			
			jQuery("#zsysf").bind("change",function(){
				jQuery("#dkxxTable tr").remove();
				var xn = jQuery("#xn").val();
				var xz = jQuery("#xz").val();
				
				if (xn != "" && xz != ""){
					for (var i = 0 ; i < Number(xz); i++){
						var newXn = _getAfterXn(xn,i);
						
						var tr = jQuery("<tr></tr>");
						var td = jQuery("<td><input type='hidden' name='dkxn' value='"+newXn+"'/>"+newXn+"</td>");
						var td1 = jQuery("<td><input type='text' name='xf' maxlength='10'  onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
						var td2 = jQuery("<td><input type='text' name='zsf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
						var td3 = jQuery("<td><input type='text' name='shf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
						tr.append(td).append(td1).append(td2).append(td3);
						jQuery("#dkxxTable").append(tr);
					}
				}
				
				var zsysf = jQuery(this).val();
				var zsf =  Number(parseFloat(zsysf/xz).toFixed(1));	
				jQuery("input[name='zsf']").each(function(index,domEl){
					jQuery(domEl).val(zsf);
				});

				var xfysf = jQuery("#xfysf").val();
				if(xfysf!=null||xfysf!=""){
					var xf =  Number(parseFloat(xfysf/xz).toFixed(1));	
					jQuery("input[name='xf']").each(function(index,domEl){
						jQuery(domEl).val(xf);
					});
				}
			});

			jQuery("#xfysf").bind("change",function(){
				var xfysf = jQuery(this).val();
				var xz = jQuery("#xz").val();
				var xf =  Number(parseFloat(xfysf/xz).toFixed(1));	
				jQuery("input[name='xf']").each(function(index,domEl){
					jQuery(domEl).val(xf);
				});
			});*/
		});
		</script>
	</head>
	<body>
		<html:form action="/zxdk_sydk" method="post" styleId="zxdkSydksqshForm" onsubmit="return false">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="splcid" value="${cssz.sydkshlc}"/>
			<html:hidden property="xn" styleId="xn" value="${cssz.sydkxn }"/>
		    <input type="hidden" name="xz" id="xz" value="${jbxx.xz}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;height:460px" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%;">贷款学年</th>
							<td>
								<span>${cssz.sydkxn}</span>
							</td>
							<th style="width:20%;"><font color="red">*</font>贷款银行</th>
							<td>
								<html:select property="yhdm" styleId="yhdm">
									<html:option value="">请选择</html:option>
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
						<tr>
							<th><font color="red">*</font>住宿费应收数（元）</th>
							<td>
								<html:text property="zsysf" styleId="zsysf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th><font color="red">*</font>学费应收数（元）</th>
							<td>
								<html:text property="xfysf" styleId="xfysf" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>合同编号</th>
							<td>
								<html:text property="htbh" styleId="htbh" maxlength="40" ></html:text>
							</td>
							<th><font color="red">*</font>贷款开始时间</th>
							<td>
								<html:text property="dkkssj" styleId="dkkssj" 
										   onfocus="showCalendar('dkkssj','y-mm-dd',false);"
										   maxlength="20" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>回执检验码</th>
							<td colspan="1">
								<html:text property="hzjym" styleId="hzjym" maxlength="20" ></html:text>
							</td>
						</tr>
					<!-- 
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
									<tbody id="dkxxTable"></tbody>
								</table>
							</td>
						</tr>
					 -->
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
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdk_sydk.do?method=save');">
										保存草稿
									</button>
									<button type="button" onclick="saveDksq('zxdk_sydk.do?method=saveAndSubmit');">
										提交申请
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