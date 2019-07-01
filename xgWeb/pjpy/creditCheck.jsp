<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script>
			function shOne(){
				url = "/xgxt/typj.do?method=creditCheckOne";
				xxdm = val("xxdm");
				
				if (null == curr_row){
					alert('请选择一行');
					return false
				} else if (true==curr_row.getElementsByTagName('input')[0].disabled){
					alert('上级部门审核通过,不能修改');
					return false;
				} 
				if(xxdm == "10352"){//丽水学院
					url = "/xgxt/pjpyLsxy.do?method=rychShOne";
				}
				showInfo(url,'sh','800','600');
			}
			
			function showDetail(){
				url = "";
				xxdm = val("xxdm");
				if(xxdm == "10352"){//丽水学院
					url = "/xgxt/pjpyLsxy.do?method=rychShOne&act=view";
					showInfo(url,'sh','800','600');
				}else{
					viewMore('view');
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/typj" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" name="realTable" id="realTable" value="xsrychb" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsrychb" />
			<input type="hidden" name="pk" id="pk" value="xh||xn||rychdm||xq" />
			<input type="hidden" name="act" id="act" value="modi" />


			<input type="hidden" name="syme" id="syme" value="${syme }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<!--非丽水学院-->
			<logic:notEqual value="10352" name="xxdm">
				<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }" />
			</logic:notEqual>
			<!--end非丽水学院-->
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />


			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" id="btn_sh" onclick="shOne()" class="btn_sh">
									单个审核 </a>
							</li>
							<li>
								<a href="#" id="btn_shtg"
									onclick="shformdata('/xgxt/typj.do?method=creditCheck&shjg=通过&doType=sh');"
									class="btn_shtg"> 批量通过 </a>
							</li>
							<li>
								<a href="#" id="btn_shbtg"
									onclick="shformdata('/xgxt/typj.do?method=creditCheck&shjg=不通过&doType=sh');"
									class="btn_shbtg"> 批量不通过 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学年
								</th>
								<td>
									<html:hidden property="queryequals_xn" value="${xn }" />
									<html:select property="queryequals_xn" value="${xn }"
										disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:hidden property="queryequals_nd" value="${nd }" />
									<html:select property="queryequals_nd" value="${nd }"
										disabled="true">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:80px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:80px"></html:text>
								</td>
								<th>
									荣誉称号
								</th>
								<td>
									<html:select property="queryequals_rychdm" style="width:145px"
										styleId="jxjdm">
										<html:options collection="rychList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<logic:equal value="xy" name="userType">
										<html:hidden property="queryequals_xydm"></html:hidden>
									</logic:equal>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<logic:equal value="xy" name="userType" scope="session">
									<logic:equal value="shjb" name="3">
										<th>
											辅导员审核
										</th>
										<td>
											<html:select property="queryequals_fdysh">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:equal>
									<logic:equal value="2" name="shjb">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
											审核
										</th>
										<td>
											<html:select property="queryequals_xysh">
												<html:option value=""></html:option>
												<html:options collection="shztList" property="en"
													labelProperty="cn" />
											</html:select>
										</td>
									</logic:equal>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="session">
									<th>
										学校审核
									</th>
									<td>
										<html:select property="queryequals_xxsh">
											<html:option value=""></html:option>
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>

								<td colspan="4">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/typj.do?method=creditCheck&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序;</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" name="cb" onclick="selectAll()"
											style="height:17.5px" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										name="<logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>"
										ondblclick="showDetail()"
										style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     ">
										<td align=center name="a">
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="checkbox" name="primarykey_cbv" id="cbv"
													value="<bean:write name="v"/>"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> />
												<input type="hidden" name="check"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</logic:iterate>

										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
			</div>
			<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
			<!--分页显示-->
			</logic:notEmpty>
		</html:form>
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
				document.getElementById('search_go').click();
			</script>
		</logic:present>
	</body>
</html>
