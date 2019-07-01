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
				refreshForm("/xgxt/pjpyszyqwh.do?method=szyc_xthdBzrAdd&doType=save&tbName=szyq_yybdjzb");
				$("buttonSave").disabled=true;
				$("buttonClose").disabled=true;
			}
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课-语言表达查询</a>
			</p>
		</div>


		<html:form action="/pjpyszyqwh" method="post">
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable}" />
			<input type="hidden" id="tmp" name="tmp" value="${tmp }" />
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<!-- 批量删除信息提示 -->
			<input type="hidden" id="failInfo" name="failInfo"
				value="${failinfo }" />
			<input type="hidden" id="showSelect" name="showSelect" value="yes" />

			<div class="comp_title">
				<ul>
					<li>
						<a
							href="javascript:refreshForm('pjpyszyqwh.do?method=szyc_xthdManage&xxk=yybd');"><span>查询</span>
						</a>
					</li>
					<li class="ha">
						<a
							href="javascript:refreshForm('pjpyszyqwh.do?method=szyc_xthdBzrAdd&tbName=szyq_yybdjzb');"><span>增加</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_zj" onclick="bzrSave();return false;"
									id="btn_zj">保存</a>
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
											onclick="refreshForm('pjpyszyqwh.do?method=szyc_xthdBzrAdd&tbName=szyq_yybdjzb');">
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
									<html:select property="nj" styleId="nj" style="width:90px"
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
									<html:select property="xn" style="width:90px"
										styleClass="select" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="width:90px"
										styleClass="select" styleId="xn">
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
											readonly="true" style="width:100px" class="inputtext" />
									</logic:equal>
									<logic:notEqual name="userType" value="stu" scope="session">
										<html:text property="xh" styleId="xh" styleClass="inputtext"
											style="width:100px"></html:text>
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"
										style="width:100px"></html:text>
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
									<html:select property="zydm" onchange="initBjList()"
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
									<html:select property="bjdm" styleClass="select" styleId="bj">
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
			<div class="leftframe04">
				<div class="menulist">
					<!--层start-->
					<div class="menutitle">
						<h3>
							<span class="title">学团活动</span>
						</h3>
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:325px; overflow:auto;">
							<ul id="tsbjList">
								<li>
									<a
										href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_xthdBzrAdd&tbName=szyq_dshdjzb');"
										style="float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"
										class="Child"> 读书活动 </a>
								</li>
								<li class="Current">
									<a
										href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_xthdBzrAdd&tbName=szyq_yybdjzb');"
										style="float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"
										class="Child"> 语言表达 </a>
								</li>
								<li>
									<a
										href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_xthdBzrAdd&tbName=szyq_ivtltb');"
										style="float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"
										class="Child"> IVT论坛 </a>
								</li>
								<li>
									<a
										href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_xthdBzrAdd&tbName=szyq_xthddjb');"
										style="float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"
										class="Child"> 文体活动 </a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="rightframe04">
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <font color="blue">提示：单击表头可以排序</font>
						</span>
					</h3>
					
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
											语言表达内容
										</td>
										<td>
											日期
										</td>
										<td>
											加减分
										</td>
										<td>
											评分
										</td>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr>
											<td>
												<bean:write name="s" property="xh" />
												<input type="hidden" name="hdxh"
													value="<bean:write name="s" property="xh" />" />
											</td>
											<td>
												<bean:write name="s" property="xm" />
											</td>
											<td>
												<bean:write name="s" property="xb" />
											</td>
											<td>
												<bean:write name="s" property="bjmc" />
											</td>
											<td>
												<textarea name="yybdnr" style="width:80px" rows="3"
													onblur="chLeng(this,150)"></textarea>
											</td>
											<td>
												<input type='text' style='width:80px' name='xthdrq'
													id="xthdrq${index }" maxlength='20' readonly='true'
													onblur='dateFormatChg(this)' style='cursor:hand;'
													onclick="return showCalendar('xthdrq${index }','y-mm-dd')" />
											</td>
											<td align="center">
												<select name='jjf' style="">
													<option value='加分'>
														加分
													</option>
													<option value='减分'>
														减分
													</option>
												</select>
											</td>
											<td>
												<input type="text" name="pf"
													onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5"
													style="width:80px" />
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
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