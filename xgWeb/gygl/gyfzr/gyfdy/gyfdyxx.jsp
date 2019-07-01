<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyfdyDWR.js'></script>
		<script language="javascript">
		function getGyfdy(){
			
			var yhm = curr_row.getElementsByTagName('input')[0].value;
			var url = '/xgxt/gyglGyfdy.do?method=gyldfdy&yhm='+yhm;
			window.dialogArguments.document.forms[0].action=url;
			window.dialogArguments.document.forms[0].submit();
			window.close();
		}
		</script>
	</head>
	<body onload="">

		<html:form action="/gyglGyfdy" method="post">
			<div class="tab_cur">
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
					双击记录可以选择公寓辅导员。<br/>	
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 模块类型 -->
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/gyglGyfdy.do?method=gyfdyxx&doType=query');">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									用户名
								</th>
								<td>
									<html:text property="yhm" styleId="yhm" style="width:150px" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:150px" />
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" styleId="xb" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									部门
								</th>
								<td>
									<html:select property="bmdm" styleId="bmdm" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bmdmList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									职务
								</th>
								<td>
									<html:select property="zwdm" styleId="zwdm" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="zwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" ondblclick="getGyfdy()"
										style="cursor:hand">

										<td style="display:none">
											<input type="hidden"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>

										<logic:iterate id="v" name="s" offset="0">

											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>


									</tr>
								</logic:iterate>

							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyglGyfdyForm"></jsp:include>
			</div>
		</html:form>

	</body>
</html>
