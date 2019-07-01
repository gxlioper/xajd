<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
			function setCompTab(id,otherOne,otherTwo){
				$(id).className="ha";
				$('flg').value=id;
				$(otherOne).className="";
				$(otherTwo).className="";
				refreshForm('jywebUseCheckSession.do?method=tjManage');
			}
			
			function onloadTab(){
				var flg = $('flg').value;
				
				if ('zpxx'==flg){
					$('zpxx').className="ha";
					$('dwxg').className="";
					$('stu').className="";
				} else if('stu'==flg){
					$('stu').className="ha";
					$('dwxg').className="";
					$('zpxx').className="";
					
					setStuTj($('tjfs').value);
				}
			}
			
			
			function setStuTj(text){
				
				if ('xy' == text){
					$('zy').disabled=true;
					$('bj').disabled=true;
					$('xm').disabled=true;
					$('sfzh').disabled=true;
				} else if ('zy' == text){
					$('zy').disabled=false;
					$('bj').disabled=true;
					$('xm').disabled=true;
					$('sfzh').disabled=true;
				} else if ('bj' == text){
					$('zy').disabled=false;
					$('bj').disabled=false;
					$('xm').disabled=true;
					$('sfzh').disabled=true;
				} else {
					$('zy').disabled=false;
					$('bj').disabled=false;
					$('xm').disabled=false;
					$('sfzh').disabled=false;
				}
			
			}
			
		</script>
	</head>
	<body onload="onloadTab();" style="height:800px">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>统计分析</a>
			</p>
		</div>
	
		<html:form action="/jyweb">
			<input type="hidden" name="flg" value="${flg }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />

			<div class="compTab">
				<div class="comp_title">
					<ul>
						<li class="ha" id="dwxg">
							<a href="javascript:setCompTab('dwxg','zpxx','stu');"><span>单位信息统计</span>
							</a>
						</li>
						<li id="zpxx">
							<a href="javascript:setCompTab('zpxx','dwxg','stu');"><span>招聘信息统计</span>
							</a>
						</li>
						<li id="stu">
							<a href="javascript:setCompTab('stu','dwxg','zpxx');""><span>学生相关统计</span>
							</a>
						</li>
					</ul>
				</div>
				<div class="comp_con">
					<div class="toolbox">
						<!-- 按钮 -->
<%--						<div class="buttonbox">--%>
<%--							<ul>--%>
<%--							</ul>--%>
<%--						</div>--%>
						<!-- 过滤条件 -->
						<div class="searchtab">
							<table width="100%" border="0">
								<tfoot>
									<tr>
										<td colspan="6">
											<div class="btn">
												<button class="btn_cx" id="search_go"
													onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=tjManage&doType=query')">
													查 询
												</button>
												<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
													重 置
												</button>
											</div>
										</td>
									</tr>
								</tfoot>
								<tbody>
									<logic:equal value="dwxg" name="flg">
										<tr>
											<th>
												注册时间
											</th>
											<td colspan="3">
												<html:text property="querygreaterequal_zcsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querygreaterequal_zcsj"
													onblur="dateFormatChg(this)"
												></html:text>
												-
												<html:text property="querylessequal_zcsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querylessequal_zcsj"
													onblur="dateFormatChg(this)"
												></html:text>
											</td>
										</tr>
									</logic:equal>
									<logic:equal value="zpxx" name="flg">
										<tr>
											<th>
												发布时间
											</th>
											<td colspan="3">
												<html:text property="querygreaterequal_fbsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querygreaterequal_fbsj"
													onblur="dateFormatChg(this)"
												></html:text>
												-
												<html:text property="querylessequal_fbsj"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querylessequal_fbsj"
													onblur="dateFormatChg(this)"
												></html:text>
											</td>
										</tr>
									</logic:equal>
									<logic:notEqual value="stu" name="flg">
										<tr>
											<th>
												单位性质
											</th>
											<td>
												<html:select property="dwxz">
													<html:options collection="dwxzList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												所属行业
											</th>
											<td>
												<html:select property="hyfl">
													<html:options collection="hyflList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
										</tr>
									</logic:notEqual>

									<logic:equal value="stu" name="flg">
										<tr>
											<th>
												年级
											</th>
											<td>
												<html:select property="nj"
													onchange="initZyList();initBjList()">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
											<th>
												姓名
											</th>
											<td>
												<html:text property="xm" maxlength="20"></html:text>
											</td>
											<th>
												身份证号
											</th>
											<td>
												<html:text property="sfzh" maxlength="20"></html:text>
											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="lable.xb" />
											</th>
											<td>
												<html:select property="xydm"
													onchange="initZyList();initBjList()" styleId="xy"
													style="width:200px">
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
													styleId="zy" style="width:200px">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</td>
											<th>
												班级
											</th>
											<td>
												<html:select property="bjdm" styleId="bj"
													style="width:200px">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th>
												统计方式
											</th>
											<td>
												<html:select property="tjfs"
													onchange="setStuTj(this.value);">
													<html:option value="stu">按学生统计</html:option>
													<html:option value="xy">按<bean:message key="lable.xb" />统计</html:option>
													<html:option value="zy">按专业统计</html:option>
													<html:option value="bj">按班级统计</html:option>
												</html:select>
											</td>
											<th>
												时间段
											</th>
											<td colspan="3">
												<html:text property="querygreaterequal_tdrq"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querygreaterequal_tdrq"
													onblur="dateFormatChg(this)"></html:text>
												-
												<html:text property="querylessequal_tdrq"
													onclick="showCalendar(this.id,'y-mm-dd')"
													styleId="querylessequal_tdrq"
													onblur="dateFormatChg(this)"></html:text>
											</td>
										</tr>

									</logic:equal>

								</tbody>
							</table>
						</div>
					</div>
					
					<!-- From内容 start-->
					<!---------------------表格--有复选框的数据表单------------------->
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; </span>
						</h3>
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr>
										<logic:iterate id="tit" name="topTr" offset="0"
											scope="request">
											<td id="${tit}" >
												${tit}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr>
												<logic:iterate id="v" name="s" offset="0">
													<td>
														${v }
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
										<%
										for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
										%>
										<tr>
											<logic:iterate id="t" name="topTr" offset="0" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
										<%
										}
										%>
									</logic:notEmpty>
									<logic:empty name="rs">
										<%
											for (int i = 0; i < (Integer) request
													.getAttribute("maxNum"); i++) {
										%>
										<tr>
											<logic:iterate id="t" name="topTr" offset="0" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
										<%
										}
										%>
									</logic:empty>
								</tbody>
							</table>

							<logic:notEqual value="zpxx" name="flg">
								<jsp:include flush="true"
									page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
								<script type="text/javascript">
									$('choose').className="hide";
								</script>
							</logic:notEqual>
					</div>
				</div>
					
		</html:form>
	</body>
</html>
