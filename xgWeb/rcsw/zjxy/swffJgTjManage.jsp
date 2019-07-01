<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/zjxyRcsw" method="post">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="BatAlert.showTips('查询数据中，请稍候！');allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffJgTjManage');">
												查 询
											</button>
											<input type="hidden" name="go" value="a" />
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
										学年
									</th>
									<td>
										<html:select property="xn" style="width:150px" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="width:150px" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										年度
									</th>
									<td>
										<html:select property="nd" style="width:150px" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										项目类型
									</th>
									<td>
										<html:select property="xmlx" style="width:150px"
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										项目名称
									</th>
									<td>
										<html:text property="xmmc" styleId="xmmc"/>
									</td>
									<th >
										办理开始时间
									</th>
									<td >
										 <html:text  property="kssj"   styleId="kssj" style="width:70px"
										onclick="return showCalendar('kssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="jssj" styleId="jssj"  style="width:70px"
										onclick="return showCalendar('jssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
								</tr>
								</tr>
								<tr>
									<th >
										办理结束开始
									</th>
									<td>
										 <html:text property="ffjskssj" styleId="ffjskssj" style="width:70px"
										onclick="return showCalendar('ffjskssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />--
										<html:text property="ffjsjssj" styleId="ffjsjssj"  style="width:70px"
										onclick="return showCalendar('ffjsjssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" />
									</td>
									<th>
									</th>
									<td>
									</td>
									<th>
									</th>
									<td>
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
									<font color="blue">点击结果集中蓝色带下划的数字可查看具体学生;</font>
								</logic:notEmpty>
							</span>
						</h3>

						<logic:notEmpty name="rs">
						<div class="con_overlfow" >
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand" >
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en" />"
												onclick="tableSort(this)" nowrap >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr  style="cursor:hand">
											<logic:iterate id="v" name="s" offset="0" length='7'>
												<td align="left" nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="7" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#"
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=xffrs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="8" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=yffrs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="9" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=wffrs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="10" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=ytz&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="11" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=yfs&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="12" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=ypj&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											<td align="left" nowrap>
												<logic:iterate id="v" name="s" offset="13" length="1">
													<logic:notEqual name="v" value="0">
													<a href="#" 
														onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffJgTjOne&cxxx=wpj&pk=<logic:iterate id="m" name="s" offset="14" length="1">${m}</logic:iterate>',800,600);return false;">
														<font color="blue"><U>${v }</U></font>
													</a>
													</logic:notEqual>
													<logic:equal name="v" value="0">
														<font color="red" >${v }</font>
													</logic:equal>
												</logic:iterate>
											</td>
											
										</tr>
									</logic:iterate>
									
								</tbody>
							</table>
						</div>
							<!--分页显示-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
							<!--分页显示-->
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
