<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript">	
	function printHftj(){
		var url = "/xgxt/gzdxRcsw.do?method=hftjManage&doType=print";
			
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gzdxRcsw" method="post">
			<%@ include file="hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /> </font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">

				<div class="toolbox">
					<!-- 按钮 -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" onclick="printHftj()" class="btn_dc"> 导出 </a>
								</li>
							</ul>
						</div>
					</logic:equal>
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<input type="hidden" name="go" value="a"/>
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/gzdxRcsw.do?method=hftjManage');">
												统 计
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th>
										所在部门
									</th>
									<td>
										<logic:equal name="userType" value="xy">
											<html:hidden property="bmdm" />
											<html:select property="bmdm" style="" styleId="bmdm"
												disabled="true">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm"
													labelProperty="bmmc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<html:select property="bmdm" style="" styleId="bmdm">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm"
													labelProperty="bmmc" />
											</html:select>
										</logic:notEqual>
									</td>
									<th>
										职位
									</th>
									<td>
										<html:select property="zw" style="" styleId="zw">
											<html:options collection="zwList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										职工号
									</th>
									<td>
										<html:text property="zgh" style="" maxlength="20" />
									</td>
									<th>
										姓名
									</th>
									<td>
										<html:text property="xm" style="" maxlength="20" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
									<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand">
											<logic:iterate id="v" name="s" offset="0">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
						<!--分页显示-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
			</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
