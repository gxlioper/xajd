<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function saveSpl(){
			var url="wjcfJcsz_ssjcspl.do?method=ssjcsplBc";
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="" >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
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
				审批流被使用时,流程设置不可更改
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/wjcfJcsz_ssjcspl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!-- 维护信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>申诉审批流设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							审批流程
						</th>
						<td width="">
							<logic:present name="shlcList">
								<logic:iterate id="shlc" name="shlcList" indexId="index">
									<logic:present name="rs">
										<input type="radio" name="ssspl" id="ssspl"
										<logic:greaterEqual name="rs" property="ssjg" value="1">
											disabled="none"
										</logic:greaterEqual>
										<logic:equal name="rs" property="ssspl"  value="${shlc.splc}"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
										<!-- 禁用标签后,增加应藏域  begin -->
										<logic:greaterEqual name="rs" property="ssjg" value="1">
											<logic:equal name="rs" property="ssspl"  value="${shlc.splc}">
											<input type="hidden" name="ssspl" value="<bean:write name="shlc" property="splc"/>"/>
											</logic:equal>
										</logic:greaterEqual>
										<!-- 禁用标签后,增加应藏域  end -->
									</logic:present>
									<logic:notPresent name="rs">
										<input type="radio" name="ssspl" id="ssspl"
										<logic:equal name="index" value="0"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
									</logic:notPresent>
									<bean:write name="shlc" property="lcxx" />
									<br/>
								</logic:iterate>
							</logic:present>
							<logic:notPresent name="shlcList">
								当前未配置处分审批流程
							</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>处分解除审批流设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							审批流程
						</th>
						<td width="">
							<logic:present name="shlcList">
								<logic:iterate id="shlc" name="shlcList" indexId="index">
									<logic:present name="rs">
										<input type="radio" name="jcspl" id="jcspl"
										<logic:greaterEqual name="rs" property="sqjg" value="1">
											disabled="none"
										</logic:greaterEqual>
										<logic:equal name="rs" property="jcspl"  value="${shlc.splc}"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
										<!-- 禁用标签后,增加应藏域  begin -->
										<logic:greaterEqual name="rs" property="sqjg" value="1">
											<logic:equal name="rs" property="jcspl"  value="${shlc.splc}">
											<input type="hidden" name="jcspl" value="<bean:write name="shlc" property="splc"/>"/>
											</logic:equal>
										</logic:greaterEqual>
										<!-- 禁用标签后,增加应藏域  end-->
									</logic:present>
									<logic:notPresent name="rs">
										<input type="radio" name="jcspl" id="jcspl"
										<logic:equal name="index" value="0"> checked="checked" </logic:equal>
										value="<bean:write name="shlc" property="splc"/>" />
									</logic:notPresent>
									<bean:write name="shlc" property="lcxx" />
									<br />
								</logic:iterate>
							</logic:present>
							<logic:notPresent name="shlcList">
								当前未配置处分审批流程
							</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- 保存 -->
								<button type="button" onclick="saveSpl();return false;">
									保存
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 维护信息 end-->
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>