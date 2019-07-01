<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/xsgyglFunction.js"></script>
			<script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
			<script type='text/javascript' src='dwr/util.js'></script>
			<script language="javascript" src="js/AjaxFunction.js"></script>
			<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
			<script language="javascript" src="js/gygl/gyglFunction.js"></script>
			<script type="text/javascript">
				function disabtext(){
					var userType = document.getElementById("userType").value;
					if(userType == "xy"){
						document.getElementById("xydm").disabled = true;
					}
				}
			</script>
	</head>
	<body onload="disabtext();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>学生住宿信息 - 信息查询 - 个人信息</a>
			</p>
		</div>
		<!-- 标题 end-->
			<html:form action="/stu_LdQsInfo" method="post">
			<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="jq" id="jq" value=" "/>
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="qshV" id="qshV" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="userName" name="userName"
						value="<bean:write name="userName" scope="request"/>" />						
					<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />			     	
			<div class="toolbox">
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/stu_LdQsInfo.do');">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>											
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" maxlength="20"
										onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_LdQsInfo.do');}" />
								</td>
								<th>
									姓名
								</th>
								<td colspan="3">
									<html:text property="xm" maxlength="20"
										onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_LdQsInfo.do');}" />
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
									<html:select property="xydm" style="width:100px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>									
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:100px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td colspan="3">
									<html:select property="bjdm" style="width:100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									校区																
								</th>
								<td>
									<html:select property="xqdm" style="width:80px"
										onchange="refreshForm('/xgxt/stu_LdQsInfo.do')">
										<html:option value=""></html:option>
										<html:options collection="xiaoqquList" property="dm"
											labelProperty="xqmc" />
									</html:select>							
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm" styleId="lddm"
											onchange="getLcList()">
											<html:options collection="ldList" property="lddm"
												labelProperty="ldmc" />
										</html:select>
								</td>
								<th>
									楼层
								</th>
								<td>
									<html:select property="lc" styleId="lc"
											onchange="getQshList2()">
											<html:options collection="lcList" property="dm"
												labelProperty="mc" />
										</html:select>
								</td>
								<th>
									寝室
								</th>
								<td>
									<html:select property="qsh" styleId="qsh">
											<html:options collection="qshList" property="dm"
												labelProperty="mc" />
										</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="gyglSendInfo()">
									<logic:iterate id="v" name="s" offset="0">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
			</html:form>
		</center>
	</body>
</html>
