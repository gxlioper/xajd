<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		<!--
			function sendData(){
				var zdmc = $('zdmc').value;
				var text = curr_row.getElementsByTagName('input')[0].value;
				window.dialogArguments.document.getElementById(zdmc).value=text;
				window.close();
			}
		//-->
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>用户信息查询</a>
			</p>
		</div>
	
		<html:form action="/xsh" method="post">
			<input type="hidden" id="zdmc" name="zdmc" value="${zdmc}" />
			<input type="hidden" id="userType" name="userType" value="${userType }>" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input name="go" value="go" type="hidden" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xsh.do?method=jsInfo&doType=query')">
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
									<html:text property="yhm" maxlength="20"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" maxlength="20"></html:text>
								</td>
								<th>
									组名称
								</th>
								<td>
									<html:text property="zmc" maxlength="20"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序</font>
							</logic:notEmpty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0" scope="request">
											<td id="tit"
												onclick="tableSort(this)" nowrap>
												${tit}
											</td>
										</logic:iterate>
									</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="sendData();"
										style="cursor:hand;">
										<td align=center>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
												<bean:write name="v"/>
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xshForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
		</html:form>
	</body>
</html>
