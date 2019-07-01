<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function submitYhsz(spgwStr){
				refreshForm("/xgxt/splcNew.do?method=splcYhsz&spgw="+spgwStr+"&lcid="+$('lcid').value);
				//var url = "/xgxt/splcNew.do?method=splcYhsz&spgw="+spgwStr+"&lcid="+$('lcid').value
				//showDialog('',700,400,url);
				//return false;
			}

			
			function reflush(){
<%--				if (parent.window){--%>
	    			refershParent();
<%--	    		}else{--%>
<%--	    			dialogArgumentsQueryChick();--%>
<%--			    }--%>
			}
		</script>
	</head>
	<body>
		<html:form action="/splcNew" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						1.通用岗位：可以多个审批流同时使用，调整<font color="red">用户授予</font>，其他审批流当中使用当前通用岗位的人员也会随之发生变化。
					<br />
						2.自定义岗位：只会存在当前审核流当中，调整<font color="red">用户授予</font>，不会对其他审核流当中的审核人员产生影响。
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<input type="hidden" name="lcid" id="lcid" value="<bean:write name = "rs" property="id" />" /> 
			<div class="tab" id="dgncz">
				<table width="100%" border="0" class="formlist">
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="" onclick="reflush();Close()" id="btn_bc">
										完成
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>流程基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:16%">
								流程名称
							</th>
							<td colspan="3" id="p_ld" style="width:84%">
								<bean:write name = "rs" property="mc" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								所属模块
							</th>
							<td  id="p_ld"  style="width:34%" >
								<html:select name = "rs" property="djlx" styleId="djlx" style="width:190px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="ssmkList" property="mkdm" 
										labelProperty="mkmc" />
								</html:select>
							</td>
							<th style="width:16%">
								流程分几步
							</th>
							<td id="p_ld" style="width:84%">
							
								<html:select property="maxSize" styleId="maxSize" style="width:150px" disabled="true">
									<html:option value="1">一</html:option>
									<html:option value="2">二</html:option>
									<html:option value="3">三</html:option>
									<html:option value="4">四</html:option>
									<html:option value="5">五</html:option>
									<html:option value="6">六</html:option>
									<html:option value="7">七</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								流程说明
							</th>
							<td colspan="3" id="p_ld" style="width:84%">
								<html:textarea cols="2"  name="rs" property="ms" style="width: 95%" readonly="readonly" />
							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									流程维护
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="rsTable" style="width:100%;">
									<table  style="width:100%;" border="0">
									<thead>
										<tr align="center" style="cursor:hand;height: 20px">
											<td>
												流程序号
											</td>
											<td>
												岗位名称
											</td>
											<td>
												已有用户数
											</td>
											<td>
												岗位类型
											</td>
											<td>
												数据范围限定
											</td>
											<td>
												用户授予
											</td>
										</tr>
									</thead>
										<logic:iterate name="lcgwrs" id="s" indexId="f">
											
											<tr style="height: 20px">	
												<td style="width:16%">
													${f+1}
												</td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:iterate id="e" name="s" offset="2" length="1">
												</logic:iterate>
												<logic:iterate id="w" name="s" offset="4" length="1">
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td>
													<logic:equal name="e" value="1">
															通用岗位
													</logic:equal>
													<logic:notEqual name="e" value="1">
															自定义岗位
													</logic:notEqual>
												</td>
												<td>
													<logic:equal name="w" value="bzr">
															班主任库
													</logic:equal>
													<logic:equal name="w" value="fdy">
															辅导员库
													</logic:equal>
													<logic:equal name="w" value="bzrfdy">
															班主任+辅导员库
													</logic:equal>
												</td>
													<td>
<%--													<logic:equal name="e" value="1">--%>
<%--															固定岗位--%>
<%--													</logic:equal>--%>
<%--													<logic:notEqual name="e" value="1">--%>
													<button type="button" onclick="submitYhsz('<bean:write name="v" />')">用户授予</button>
<%--													</logic:notEqual>--%>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- 提示信息 -->
			<logic:notEmpty name="result">
			<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
			</logic:equal>
			</logic:notEmpty>
			<!-- 请等待 -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- 请等待 end-->
		</html:form>
	</body>
</html>
