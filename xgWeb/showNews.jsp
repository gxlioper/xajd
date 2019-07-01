<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body class="breakword">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>相关新闻</a>
			</p>
		</div>

		<html:form method="post" action="/showNews">
			<logic:present name="showType">
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tbody>
								<tr>
									<td>
										<input type="radio" name="showType" checked="checked"
											onclick="$('showgyjb').style.display='none';$('showNews').style.display=''" />
										新闻
									</td>
									<td>
										<input type="radio" name="showType"
											onclick="$('showgyjb').style.display='';$('showNews').style.display='none'" />
										公寓每周简报
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</logic:present>
			<logic:present name="showNewsType">
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tbody>
								<tr>
									<th width="180px">
										类型
									</th>
									<td>
										<html:select property="newsType"
											onchange="refreshForm('/xgxt/showNews.do?tagId=N28')">
											<html:option value=""></html:option>
											<html:options collection="NewsTypeList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</logic:present>

			<div class="formbox" id="showNews">
				<h3 class="datetitle_01">
					<span> 新 闻 列 表&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">暂无新闻</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead class="tbstyle">
							<!-- 显示所属模块-->
							<logic:present name="showSsmk">
								<logic:equal name="showBJLH" value="is">
									<tr bgcolor="#D0E0EE" style="cursor:hand">
										<td onclick="tableSort(this)">
											序号
										</td>
										<td onclick="tableSort(this)" align="center">
											所属模块
										</td>
										<td onclick="tableSort(this)" align="center">
											新闻标题
										</td>
										<td onclick="tableSort(this)" align="center">
											发布时间
										</td>
										<td onclick="tableSort(this)" align="center">
											发布人
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual name="showBJLH" value="is">
									<tr bgcolor="#D0E0EE" style="cursor:hand">
										<logic:notPresent name="disXh">
											<td onclick="tableSort(this)" width="5%">
												序号
											</td>
										</logic:notPresent>
										<td onclick="tableSort(this)" align="center" width="10%">
											所属模块
										</td>
										<logic:present name="showNewsType">
											<!-- 显示新闻类型-->
											<td onclick="tableSort(this)" align="center" width="8%">
												类型
											</td>
										</logic:present>
										<td onclick="tableSort(this)" align="center">
											新闻标题
										</td>
										<td onclick="tableSort(this)" align="center">
											发布时间
										</td>
										<td onclick="tableSort(this)" align="center">
											发布人
										</td>
									</tr>
								</logic:notEqual>
							</logic:present>
							<logic:notPresent name="showSsmk">
								<%--								长沙民政--%>
								<logic:equal value="10827" name="xxdm">
									<tr bgcolor="#D0E0EE" style="cursor:hand">
										<td onclick="tableSort(this)">
											新闻标题
										</td>
										<td onclick="tableSort(this)" align="center">
											对象
										</td>
										<td onclick="tableSort(this)" align="center">
											发布日期
										</td>
										<td onclick="tableSort(this)" align="center">
											发布人
										</td>
										<td onclick="tableSort(this)" width="150px" align="center">
											部门
										</td>
									</tr>
								</logic:equal>

								<%--								如果不是长沙民政--%>
								<logic:notEqual value="10827" name="xxdm">
									<tr bgcolor="#D0E0EE" style="cursor:hand">
										<td onclick="tableSort(this)">
											新闻标题
										</td>
										<td onclick="tableSort(this)" align="center">
											对象
										</td>
										<td onclick="tableSort(this)" align="center">
											发布时间
										</td>
										<td onclick="tableSort(this)" align="center">
											发布人
										</td>
									</tr>
								</logic:notEqual>
							</logic:notPresent>
						</thead>
						<tbody>
							<logic:iterate id="list" name="rs">
								<tr onmouseover="rowOnClick(this)">
									<%--									显示所属模块--%>
									<logic:present name="showSsmk">
										<logic:equal value="is" name="showSsmk">
											<logic:notPresent name="disXh">
												<td width="40">
													<bean:write name="list" property="rownum" />
												</td>
											</logic:notPresent>
											<logic:present name="isEXIST">
												<td>
													<bean:write name="list" property="newspartmc" />
												</td>
											</logic:present>
											<logic:present name="showNewsType">
												<!-- 显示新闻类型-->
												<td>
													<bean:write name="list" property="newsType" />
												</td>
											</logic:present>
											<td>
												<!-- 2010/4/26 luojw -->
												<a
													href="newsContent.do?newsId=<bean:write name="list" property="newsid"/>"
													target="_blank"> <bean:write name="list"
														property="newstitle" /> </a>
												<logic:equal name="list" property="newmark" value="new">
													<img style="width:30px" src="images/newmark.gif" />
												</logic:equal>
											</td>
											<td width="140" align="center">
												<bean:write name="list" property="pubtime" />
											</td>
											<td width="80">
												<bean:write name="list" property="puber" />
											</td>
										</logic:equal>
									</logic:present>
									<%--									不显示所属模块--%>
									<logic:notPresent name="showSsmk">
										<td>
											<!-- 2010/4/26 luojw -->
											<a
												href="newsContent.do?newsId=<bean:write name="list" property="newsid"/>"
												target="_blank"> <bean:write name="list"
													property="newstitle" /> </a>
											<logic:equal name="list" property="newmark" value="new">
												<img style="width:30px" src="images/newmark.gif" />
											</logic:equal>
										</td>
										<logic:present name="showNewsType">
											<td>
												<bean:write name="list" property="newsType" />
											</td>
										</logic:present>
										<%--										长沙民政--%>
										<logic:equal value="10827" name="xxdm">
											<td width="90" align="center">
												<logic:equal name="list" property="towho" value="1">全体</logic:equal>
												<logic:equal name="list" property="towho" value="2">教师</logic:equal>
												<logic:equal name="list" property="towho" value="3">
													<bean:message key="lable.xsgzyxpzxy" />
												</logic:equal>
												<logic:equal name="list" property="towho" value="4">学生</logic:equal>
												<logic:equal name="list" property="towho" value="">全体</logic:equal>
											</td>
										</logic:equal>
										<%--										end长沙民政--%>

										<%--										非长沙民政--%>
										<logic:notEqual value="10827" name="xxdm">
											<td width="90" align="center">
												<logic:equal name="list" property="towho" value="1">全体</logic:equal>
												<logic:equal name="list" property="towho" value="2">教师</logic:equal>
												<logic:equal name="list" property="towho" value="3">
													<bean:message key="lable.xsgzyxpzxy" />
												</logic:equal>
												<logic:equal name="list" property="towho" value="4">学生</logic:equal>
												<logic:equal name="list" property="towho" value="">全体</logic:equal>
											</td>
										</logic:notEqual>
										<%--										end非长沙民政--%>
										<td width="140" align="center">
											<bean:write name="list" property="pubtime" />
										</td>
										<td width="80" align="center">
											<bean:write name="list" property="puber" />
										</td>
										<%--										长沙民政--%>
										<logic:equal value="10827" name="xxdm">
											<td align="center">
												<bean:write name="list" property="bmmc" />
											</td>
										</logic:equal>
									</logic:notPresent>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
			</div>








			<div class="formbox" id="showgyjb" style="display: none">
				<h3 class="datetitle_01">
					<span> 公 寓 简 报&nbsp;&nbsp; <logic:empty name="rsjb">
							<font color="red">暂无简报！</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rsjb">
					<table summary="" class="dateline" align="" width="100%">
						<thead class="tbstyle">
							<tr bgcolor="#D0E0EE" style="cursor:hand">
								<td onclick="tableSort(this)">
									简报标题
								</td>
								<td onclick="tableSort(this)" style="width: 150px">
									时间
								</td>
								<td onclick="tableSort(this)" style="width: 120px">
									发布人
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="list" name="rsjb">
								<tr onmouseover="rowOnClick(this)">
									<td>
										<a
											href="zjjjzy_Gygl.do?method=aqjbShow&documentId=<bean:write name="list" property="jbid"/>"
											target="_blank"> <bean:write name="list"
												property="jbtitle" /> </a>
									</td>
									<td>
										<bean:write name="list" property="jbpubtime" />
									</td>
									<td>
										<bean:write name="list" property="jbpubername" />
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
			</div>




			<logic:notEmpty name="rs2">
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 汇报清单&nbsp;&nbsp; <logic:notEmpty name="rs2">
								<font color="red">请您尽快上交,以下是您未交的汇报清单</font>
							</logic:notEmpty> </span>
					</h3>


					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									时间
								</td>
								<td nowrap>
									报表种类
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs2" id="s">
								<tr style="cursor:hand">
									<logic:iterate id="v" name="s" offset="0">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
