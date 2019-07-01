<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<!-- 内容 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="6">
							<span>快捷方式选择（注：设置完成后请刷新页面才可见到所设置的效果）</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr style="height: 23px">
						<logic:iterate id="pic" name="rsList" indexId="index">
							<logic:notEmpty name="pic" property="picpath">
								<logic:notEqual name="size" value="${index+1 }">
									<td align="left" style="width: 20%">
										<img src="<%=stylePath%>${pic.picpath }"/>
									</td>
									<td align="left" style="width: 20%">
										${pic.mkms }
									</td>
									<td align="left" style="width: 10%">
										<logic:notEmpty name="pic" property="gnmk">
											<logic:equal name="pic" property="iskjfs" value="yes">
												<input type="checkbox" id="checkVal"  
													name="pic"  value="${pic.picpath }" checked="checked"/>
											</logic:equal>
											<logic:notEqual name="pic" property="iskjfs" value="yes">
												<input type="checkbox" id="checkVal"  
													name="pic"  value="${pic.picpath }"/>
											</logic:notEqual>
										</logic:notEmpty>
										<logic:empty name="pic" property="gnmk">
											<input type="checkbox" id="checkVal"  
												name="pic" disabled="disabled"/>
										</logic:empty>
									</td>
									<%if((index.intValue()+1)%2==0){%>
										<% out.print("</tr>"); %>
									<%}%> 
								</logic:notEqual>
								<logic:equal name="size" value="${index+1 }">
									<%if((index.intValue()+1)%2==0){%>
									<td align="left" style="width: 20%">
										<img src="<%=stylePath%>${pic.picpath }"/>
									</td>
									<td align="left" style="width: 20%">
										${pic.mkms }
									</td>
									<td align="left" style="width: 10%">
										<logic:notEmpty name="pic" property="gnmk">
											<logic:equal name="pic" property="iskjfs" value="yes">
												<input type="checkbox" id="checkVal"  
													name="pic"  value="${pic.picpath }" checked="checked"/>
											</logic:equal>
											<logic:notEqual name="pic" property="iskjfs" value="yes">
												<input type="checkbox" id="checkVal"  
													name="pic"  value="${pic.picpath }"/>
											</logic:notEqual>
										</logic:notEmpty>
										<logic:empty name="pic" property="gnmk">
											<input type="checkbox" id="checkVal"  
												name="pic" disabled="disabled"/>
										</logic:empty>
									</td>
									<%}%> 	
									<%if((index.intValue()+1)%2==1){%>
									<td align="left" style="width: 20%">
										<img src="<%=stylePath%>${pic.picpath }"/>
									</td>
									<td align="left" style="width: 20%">
										${pic.mkms }
									</td>
									<td align="left" style="width: 10%">
										<logic:notEmpty name="pic" property="gnmk">
											<logic:equal name="pic" property="iskjfs" value="yes">
												<input type="checkbox" id="checkVal"  
													name="pic"  value="${pic.picpath }" checked="checked"/>
											</logic:equal>
											<logic:notEqual name="pic" property="iskjfs" value="yes">
												<input type="checkbox" id="checkVal"  
													name="pic"  value="${pic.picpath }"/>
											</logic:notEqual>
										</logic:notEmpty>
										<logic:empty name="pic" property="gnmk">
											<input type="checkbox" id="checkVal"  
												name="pic" disabled="disabled"/>
										</logic:empty>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<%}%> 	
								</logic:equal>
							</logic:notEmpty>
						</logic:iterate>
					</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="6">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button type="button" id="buttonSave" 
										onclick="saveUpdate('/xgxt/commXtwh.do?method=kjfsUpdate&doType=save','')"
										style="width: 80px">
										保	存
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button type="button" id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 内容 end-->
		</html:form>
		<!-- 提示信息 -->
		<logic:present name="message">
		<script>
			if($("message") && $("message").value != ""){
			
				alert($("message").value);
				
				$("message").value = "";
				$("doType").value = "";
				if(window.dialogArguments){
					window.close();
				}
			}
		</script>
	</logic:present>
		<!-- 提示信息 end-->
	</body>
</html>
