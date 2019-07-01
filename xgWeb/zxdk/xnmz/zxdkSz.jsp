<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>	
		<script type="text/javascript">
		function save(){
			if($("sqkssj") && $("sqkssj").value==""){
				alertError("申请开始时间不能为空!");
				return false;
			}
			
			if($("sqjssj") && $("sqjssj").value==""){
				alertError("申请结束时间不能为空!");
				return false;
			}
			
			if($("shkssj") && $("shkssj").value==""){
				alertError("审核开始时间不能为空!");
				return false;
			}
			
			if($("shjssj") && $("shjssj").value==""){
				alertError("审核结束时间不能为空!");
				return false;
			}
			
			if(jQuery("input[name=splc]") && !jQuery("input[name=splc]").prop("checked")){
				alertError("审批流程不能为空!");
				return false;
			}
			
			if($("sqkssj") && $("sqjssj")){
				if(!checkSjTj('sqkssj','申请开始时间','sqjssj','申请结束时间')){
					return false;
				}
			}
			if($("shkssj") && $("shjssj")){
				if(!checkSjTj('shkssj','审核开始时间','shjssj','审核结束时间')){
					return false;
				}
			}
			
			
			var url="/xgxt/zxdk_xnmz.do?method=zxdkSz&doType=save";
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					该模块用于对"助学贷款"模块进行整体的设置。包括"申请时间"、"审核时间"、"审核流程"的设置。<br/>
					如果在"审核流程"中无可选的审核流程，请先在"系统维护-审批流程管理-审批流程"模块进行审批流<br/>
					程的维护。
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">

									<button class="button2" id="btn_bc" onclick="save();return false;">
										保 存
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>申请开始时间
							</th>
							<td style="width:34%">
								<input type="text" name="sqkssj"
									class="jssj"
									onclick="return showCalendar('sqkssj','ymmdd');"
									id="sqkssj"
									value="${rs.sqkssj }" />
							</td>
							<th style="width:16%">
								<font color="red">*</font>申请结束时间
							</th>
							<td style="width:34%">
								<input type="text" name="sqjssj"
									class="jssj"
									onclick="return showCalendar('sqjssj','ymmdd');"
									id="sqjssj"
									value="${rs.sqjssj }" 
									 />
							</td>
						</tr>

						<tr>
							<th style="width:16%">
								<font color="red">*</font>审核开始时间
							</th>
							<td id="" style="width:34%">
								<input type="text" name="shkssj"
									class="jssj"
									onclick="return showCalendar('shkssj','ymmdd');"
									id="shkssj"
									value="${rs.shkssj }" />
							</td>

							<th>
								<font color="red">*</font>审核结束时间
							</th>
							<td id="" style="width:34%">
								<input type="text" name="shjssj"
									class="jssj"
									onclick="return showCalendar('shjssj','ymmdd');"
									id="shjssj"
									value="${rs.shjssj }" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>审核流程
							</th>
							<td colspan="3" style="width:84%">
								<logic:notEmpty name="shlcList">
								<logic:iterate name="shlcList" id="shlc" indexId="index">
									<logic:equal name="rs" property="splc" value="${shlc.lcid}">
										<input type="radio" name="splc" value="${shlc.lcid}" checked="checked"/>${shlc.lcmc}:${shlc.gzgw }<br/>
									</logic:equal>
									<logic:notEqual name="rs" property="splc" value="${shlc.lcid}">
										<input type="radio" name="splc" value="${shlc.lcid}"/>${shlc.lcmc}:${shlc.gzgw }<br/>
									</logic:notEqual>
								</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="shlcList">
									<font color="red">请先维护助学贷款模块的审核流程</font>
								</logic:empty>
							</td>
						</tr>
					</tbody>
				</table>
			</div>


			<logic:present name="message">
				<script type="text/javascript">
					alertInfo("${message}!");
				</script>
			</logic:present>
			<script type="text/javascript" defer="defer">
				if($("shlc_0")){
					$("shlc_0").checked=true;
				}
				
				
			</script>
		</html:form>
	</body>
</html>
