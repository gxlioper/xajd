<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/pjpy/typj.js"></script>
		<script type="text/javascript">
	function checkSyme() {
		var userType = $('userType').value;
		var isFdy = $('isFdy').value;
		var syme = $('syme').value;
		var checkBoxArr = document.getElementsByName("primarykey_cbv");
		var n=0;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				var text = checkBoxArr[i].parentNode.parentNode.name.trim();
				if ('#CCCCCC'==text) {
					n++;
				}
			}
		}
		if ( 'xy'==userType && 'false'==isFdy && Number(n)>Number(syme)) {
				alert('超出名额限制!');
				return false;
		} 
		shformdata('/xgxt/guizhdx.do?method=xjbjSh&shjg=通过&doType=sh');
	}
</script>
	</head>

	<body onload="xyDisabled('xy');">
		<html:form action="/guizhdx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>评奖评优－先进班级－先进班级审核</a>
				</p>
			</div>
			<input type="hidden" id="syme" value="${syme }" />
			<input type="hidden" id="isFdy" name="isFdy"
				value="<bean:write name="isFdy" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<logic:equal value="xy" name="userType" scope="session">
				<logic:equal value="false" name="isFdy" scope="session">
					<input type="hidden" name="queryequals_fdysh" value="通过" />
					<input type="hidden" name="queryequals_xydm" value="${userDep }" />
				</logic:equal>
			</logic:equal>
			<logic:notEqual value="xy" name="userType" scope="session">
				<input type="hidden" name="queryequals_xysh" value="通过" />
			</logic:notEqual>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="checkUpdate('/xgxt/guizhdx.do?method=xjbjOne','sh','800','600');"
									class="btn_sh"> 单个审核 </a>
							</li>
							<li>
								<a href="#" onclick="checkSyme();" class="btn_shtg"> 审核通过 </a>
							</li>
							<li>
								<a href="#"
									onclick="shformdata('/xgxt/guizhdx.do?method=xjbjSh&shjg=不通过&doType=sh');"
									class="btn_shtg"> 审核不通过 </a>
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
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/guizhdx.do?method=xjbjSh&doType=query')">
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
									学年
								</th>
								<td>
									<html:hidden property="queryequals_xn" value="${xn }" />
									<html:select property="queryequals_xn" disabled="true"
										value="${xn }">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<logic:equal name="isFdy" value="true">
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
								<logic:equal value="xy" name="userType">
									<logic:notEqual name="isFdy" value="true">
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
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="xx" name="userType">
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
								</logic:equal>
								<logic:equal value="admin" name="userType">
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
								</logic:equal>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" style="width:180px"
										styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" style="width:180px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width:180px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="formbox" id="result">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs" >
							<font color="blue"> 
									提示：双击一行可以查看详细信息；单击表头可以排序</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						 <table summary="" id="rsTable" class="dateline" width="100%">
									<thead>
										<tr align="center" style="cursor:hand">
											<td nowrap>
												<input type="checkbox" name="cb" onclick="selectAll()"
													style="height:17.5px">
											</td>
											<logic:iterate id="tit" name="topTr" offset="3"
												scope="request">
												<td id="${tit.en}" onclick="tableSort(this)" nowrap>
													${tit.cn}
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)"
											name="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
											ondblclick="showInfo('/xgxt/guizhdx.do?method=xjbjOne','view','800','600');"
											style="cursor:hand;background-color:
										    <logic:iterate id="v" name="s" offset="1" length="1">
										    <bean:write name="v"/>
										    </logic:iterate>
										     ">
											<td align=center>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<input type="checkbox" id="cbv" name="primarykey_cbv"
														value="<bean:write name="v"/>"
														<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> />
													<input type="hidden" value="<bean:write name="v" />">
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
								<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
						</logic:notEmpty>
					</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
