<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			function checkSjshzt(){
				var checkVal = document.getElementsByName('checkVal');
				var sjshztArr = document.getElementsByName('sjshztArr');
				var check = 0;
				for(var i=0;i<checkVal.length;i++){
					if(checkVal[i].checked){
						if(sjshztArr[i].value!='未审核'){
							check++;
						}
					}
				}
				if(check!=0){
					alert('您选择的记录中有正在进行上级审核处理的数据，不可再次审核！');
					return false;
				}else{
					return true;
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 在校证明 - 在校证明审核</a>
			</p>
		</div>
		<html:form action="/zzlgdx_rcsw" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<!-- 武汉商业对上级审核情况做验证 -->
							<logic:equal value="11654" name="xxdm" scope="session">
								<li>
									<a href="#"
										onclick="if(checkSjshzt()){sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=已通过','sh');}"
										class="btn_shtg">通过</a>
								</li>
								<li>
									<a href="#"
										onclick="if(checkSjshzt()){sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=不通过','sh');}"
										class="btn_shbtg"> 不通过 </a>
								</li>
							</logic:equal>
							<logic:notEqual value="11654" name="xxdm" scope="session">
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=已通过','sh')"
										class="btn_shtg">通过</a>
								</li>
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&shzt=不通过','sh')"
										class="btn_shbtg"> 不通过 </a>
								</li>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zzlgdx_rcsw.do?method=zxzmSh&go=go')">
											查 询
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
									年级
								</th>
								<td>
									<html:select property="nj" onchange="initBjList()" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh"
										style="inputtext" styleClass="inputtext"></html:text>
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									身份证号
								</th>
								<td>
									<html:text property="sfzh" styleId="sfzh" maxlength="18"
										styleClass="inputtext"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"></html:text>
								</td>
								<th>
									项目名称
								</th>
								<td>
									<html:select property="xmmc" style="width:150px;">
										<html:options collection="xmmcList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:150px"
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
									<html:select property="zydm" onchange="initBjList()"
										style="width:150px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 武汉商业 -->
							<logic:equal value="11654" name="xxdm" scope="session">
								<tr>
									<th>审核状态</th>
									<td colspan="5">
										<logic:equal value="true" name="isFdy" scope="session">
											<html:select property="fdysh" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</logic:equal>
										<logic:notEqual value="true" name="isFdy" scope="session">
											<logic:equal value="xy" name="userType" scope="session">
												<html:select property="xysh" style="width:150px;">
													<html:option value=""></html:option>
													<html:options collection="shztList" property="en"
														labelProperty="cn" />
												</html:select>
											</logic:equal>
											<logic:notEqual value="xy" name="userType" scope="session">
												<html:select property="xxsh" style="width:150px;">
													<html:option value=""></html:option>
													<html:options collection="shztList" property="en"
														labelProperty="cn" />
												</html:select>
											</logic:notEqual>
										</logic:notEqual>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="11654" name="xxdm" scope="session">
								<tr>
									<logic:equal value="xy" name="userType" scope="session">
										<th>
											审核类型
										</th>
										<td>
											<html:select property="shlx" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shlxList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											审核
										</th>
										<td>
											<html:select property="xysh" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:equal>
									<logic:notEqual value="xy" name="userType" scope="session">
										<th>
											学校审核
										</th>
										<td>
											<html:select property="xxsh" style="width:150px;">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:notEqual>
								</tr>
							</logic:notEqual>
						</tbody>
					</table>
				</div>
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一条记录可以进行单个审核；单击表头可以排序</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showInfo('/xgxt/zzlgdx_rcsw.do?method=zxzmShow','sh','800','600');"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="pk" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											
											<!-- 武汉商业（上级审核状态，用于判断上级审核是否已经在审核之中，只有上级审核处于未审核状态下的记录可审核） -->
											<logic:equal value="11654" name="xxdm" scope="session">
											<logic:equal value="true" name="isFdy" scope="session">
												<logic:iterate id="v" name="s" offset="10" length="1">
													<input type="hidden" id="sjshztArr" name="shshztArr" value="${v }"/>
												</logic:iterate>
											</logic:equal>
											<logic:notEqual value="true" name="isFdy" scope="session">
												<logic:equal value="xy" name="userType" scope="session">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<input type="hidden" id="sjshztArr" name="shshztArr" value="${v }"/>
													</logic:iterate>
												</logic:equal>
												<logic:notEqual value="xy" name="userType" scope="session">
													<logic:iterate id="v" name="s" offset="10" length="1">
														<input type="hidden" id="sjshztArr" name="shshztArr" value="未审核"/>
													</logic:iterate>
												</logic:notEqual>
											</logic:notEqual>
											</logic:equal>
											
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
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=rcswZzlgdxActionForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
	         	
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("操作失败！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
