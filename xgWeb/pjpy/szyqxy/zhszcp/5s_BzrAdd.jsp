<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
		<script type="text/javascript">
		function bzrSave(){
			var xn=$("xn").value;
			var xq=$("xq").value;
			if(xn == "" || xq == ""){
				alert("学年或者学期不能为空，请确认！！");
				return false;
			}
			if (confirm("确认所录入的数据？")) {
				showTips('处理数据中，请等待......');
				refreshForm("/xgxt/pjpyszyqwh.do?method=szyc_5sBzrAdd&doType=save");
				$("buttonSave").disabled=true;
				$("buttonClose").disabled=true;
			}
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课-5s分维护</a>
			</p>
		</div>
		<html:form action="/pjpyszyqwh" method="post">
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable}" />
			<input type="hidden" id="tmp" name="tmp" value="${tmp }" />
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" id="isFdy" name="isFdy"
				value="<bean:write name="isFdy" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<!-- 批量删除信息提示 -->
			<input type="hidden" id="failInfo" name="failInfo"
				value="${failinfo}" />
			<input type="hidden" id="userName" name="userName"
				value="${userName}" />
			<input type="hidden" id="showSelect" name="showSelect" value="yes" />


			<div class="comp_title">
		      <ul>
		        <li><a href="javascript:refreshForm('szyc_5sManage.do');"><span>查询</span></a></li>
		        <li class="ha"><a href="javascript:refreshForm('pjpyszyqwh.do?method=szyc_5sBzrAdd');"><span>增加</span></a></li>
		      </ul>
		    </div>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_ccg" onclick="bzrSave();return false;"
									id="btn_ccg">保 存</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('pjpyszyqwh.do?method=szyc_5sBzrAdd&go=go');">
											查 询
										</button>
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
									<html:select property="nj" styleId="nj" style=""
										onchange="initZyList();initBjList()" styleClass="select">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="" styleClass="select"
										styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="" styleClass="select"
										styleId="xn" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									学号
								</th>
								<td>
									<logic:equal name="userType" value="stu" scope="session">
										<input type="text" name="xh"
											value="<bean:write name="userName" scope="session"/>"
											readonly="true" />
									</logic:equal>
									<logic:notEqual name="userType" value="stu" scope="session">
										<html:text property="xh" styleId="xh"></html:text>
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleClass="select"
										style="" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" style=""
										styleClass="select" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="" styleClass="select"
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

			<div class="formbox">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>
									项目
								</td>
								<td>
									加减分
								</td>
								<td>
									分数
								</td>
								<td>
									原 因
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsList" id="s">
								<tr>
									<td align="center" rowspan="5">
										<bean:write name="s" property="xh" />
									</td>
									<td align="center" rowspan="5">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center" rowspan="5">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center" rowspan="5">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										个人素质分
										<input type="hidden" name="fzxm" value="grszf" />
										<input type="hidden" name="fivexh"
											value="<bean:write name="s" property="xh" />" />
									</td>
									<td>
										<select name='jjf' >
											<option value='加分'>
												加分
											</option>
											<option value='减分'>
												减分
											</option>
										</select>
									</td>
									<td>
										<input type="text" name="fz"
											onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5"
											style="width:80px" />
									</td>
									<td>
										<html:select property="yy" style="" styleClass="select"
											styleId="yy">
											<html:options collection="yyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="center">
										教室与宿舍5S分
										<input type="hidden" name="fzxm" value="jsssf" />
										<input type="hidden" name="fivexh"
											value="<bean:write name="s" property="xh" />" />
									</td>
									<td>
										<select name='jjf'>
											<option value='加分'>
												加分
											</option>
											<option value='减分'>
												减分
											</option>
										</select>
									</td>
									<td>
										<input type="text" name="fz"
											onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5"
											style="width:80px" />
									</td>
									<td>
										<html:select property="yy" style="" styleClass="select"
											styleId="yy">
											<html:options collection="yyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="center">
										课堂5S分
										<input type="hidden" name="fzxm" value="ktf" />
										<input type="hidden" name="fivexh"
											value="<bean:write name="s" property="xh" />" />
									</td>
									<td>
										<select name='jjf'>
											<option value='加分'>
												加分
											</option>
											<option value='减分'>
												减分
											</option>
										</select>
									</td>
									<td>
										<input type="text" name="fz"
											onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5"
											style="width:80px" />
									</td>
									<td>
										<html:select property="yy" style="" styleClass="select"
											styleId="yy">
											<html:options collection="yyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="center">
										诚信分
										<input type="hidden" name="fzxm" value="cxf" />
										<input type="hidden" name="fivexh"
											value="<bean:write name="s" property="xh" />" />
									</td>
									<td>
										<select name='jjf'>
											<option value='加分'>
												加分
											</option>
											<option value='减分'>
												减分
											</option>
										</select>
									</td>
									<td>
										<input type="text" name="fz"
											onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5"
											style="width:80px" />
									</td>
									<td>
										<html:select property="yy" style="" styleClass="select"
											styleId="yy">
											<html:options collection="yyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="center">
										其他分
										<input type="hidden" name="fzxm" value="qtf" />
										<input type="hidden" name="fivexh"
											value="<bean:write name="s" property="xh" />" />
									</td>
									<td>
										<select name='jjf'>
											<option value='加分'>
												加分
											</option>
											<option value='减分'>
												减分
											</option>
										</select>
									</td>
									<td>
										<input type="text" name="fz"
											onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5"
											style="width:80px" />
									</td>
									<td>
										<html:select property="yy" style="" styleClass="select"
											styleId="yy">
											<html:options collection="yyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<!-- 操作提示 -->
		<jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
	</body>