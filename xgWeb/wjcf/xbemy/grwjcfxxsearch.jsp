<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script>
			function cfqr(obj) {
				if (confirm('确定要对该处分信息进行确认吗,确认后不能进行撤消操作，请仔细核对！')) {
					var pk = obj.parentNode.parentNode.getElementsByTagName("td")[0].getElementsByTagName("input")[0].value;
					refreshForm("grwjcfxxsearch.do?act=save&pk="+pk);
				}
			}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 处分申报管理 - 学生处分查询</a>
			</p>
		</div>

		<html:form action="/grwjcfxxsearch.do" method="post">
			<button type="button" style="display:none" id="search_go" onclick="refreshForm('grwjcfxxsearch.do')" ></button>
			<div class="tab">
			
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left">
								学号： ${userName }
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								姓名： ${userNameReal }
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								历史违纪次数: ${num }
							</td>
						</tr>
					</thead>
				</table>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span>违纪处分信息&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue"> 提示：双击一行可以查看详细信息；</font>
						</logic:notEmpty>
						 </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="left" style="cursor:hand">
								<!-- 广州大学单独处理表头 -->
								<logic:equal value="11078" name="xxdm">
									<logic:iterate id="tit" name="topTr" offset="1" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td onclick="tableSort(this)" nowrap>
										操作
									</td>
								</logic:equal>
								<!-- 其它学校 -->
								<logic:notEqual value="11078" name="xxdm">
									<logic:iterate id="tit" name="topTr" offset="1" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
<%--									<td>--%>
<%--										学生确认--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										确认时间--%>
<%--									</td>--%>
<%--									<td onclick="tableSort(this)" nowrap>--%>
<%--										操作--%>
<%--									</td>--%>
								</logic:notEqual>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr align="left" onclick="rowOnClick(this);"
									style="cursor:hand"
									ondblclick="showTopWin('grwjcfxxview.do?pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<!-- 广州大学数据输出单独 处理 -->
									<logic:equal value="11078" name="xxdm">
										<logic:iterate id="v" name="s" offset="2" length="7">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</logic:equal>
									<!-- 其它大学数据输出单独 处理 -->
									<logic:notEqual value="11078" name="xxdm">
										<logic:iterate id="v" name="s" offset="2" length="6">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</logic:notEqual>
									<!-- 其它学校要显示下载文件的按钮 广州大学不显示 -->
									<logic:notEqual value="11078" name="xxdm">
										<logic:iterate id="v" name="s" offset="9" length="1">
											<td nowrap>
												<logic:equal value="无" name="v">
													<logic:iterate id="v" name="s" offset="9" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</logic:equal>
												<logic:notEqual value="无" name="v">
													<a
														href="downloadfilewj.do?len=&wjsclj=<logic:iterate id="v" name="s" offset="8" length="1"><bean:write name="v" /></logic:iterate>"
														target="_blank"><logic:iterate id="v" name="s"
															offset="9" length="1">
															<bean:write name="v" />
														</logic:iterate> </a>
												</logic:notEqual>
											</td>
										</logic:iterate>
<%--										<td>--%>
<%--											<logic:iterate id="v" name="s" offset="9" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<logic:iterate id="v" name="s" offset="10" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<button type="button" id="qr" class="button2"--%>
<%--												onclick="rowOnClick(this);cfqr(this)"--%>
<%--												<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>>--%>
<%--												确认处分--%>
<%--											</button>--%>
<%--										</td>--%>
									</logic:notEqual>
									<logic:equal value="11078" name="xxdm">
										<td>
											<button type="button" id="qr" class="button2"
												onclick="rowOnClick(this);cfqr(this)"
												<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v" /></logic:iterate>>
												处分知情
											</button>
										</td>
									</logic:equal>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
			</div>
			
			<logic:notEqual value="10653" name="xxdm">
				<logic:notEmpty name="rsData">
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 
					<logic:equal value="10653" name="xxdm">
					已撤消(解除)的处分信息&nbsp;&nbsp; 
					</logic:equal>
					<logic:notEqual value="10653" name="xxdm">
					解除留校察看信息&nbsp;&nbsp; 
					</logic:notEqual>
					<logic:empty name="rsData">
					
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
						<logic:notEmpty name="rsData">
						<font color="blue"> 提示：双击一行可以查看详细信息；</font>
						</logic:notEmpty>
						</span>
				</h3>

				<div class="con_overlfow">
					<table summary="" class="dateline " align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="left">
									学年
								</td>
								<td align="left">
									年度
								</td>
								<td align="left">
									处分类别
								</td>
								<td align="left">
									处分原因
								</td>
								<td align="left">
									处分时间
								</td>
								<td align="left">
									处分文号
								</td>
								<td align="left">
									解除时间
								</td>
								<td align="left">
									解除文号
								</td>
								<td align="center">
									解除结果
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsData" id="es">
								<tr align="left" onclick="rowOnClick(this);"
									style="cursor:hand"
									ondblclick="showTopWin('wjcf_zjcm_stulxckxx.do?pkValue='+curr_row.getElementsByTagName('input')[0].value,650,550)">
									<td>
										<logic:iterate id="v" name="es" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="es" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="es" offset="2">
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
			</div>
			</div>
			</logic:notEqual>

			<script type="text/javascript">
					function chkPriseOne2(url, w, h) {
						if (w == null) {
							w = 700;
						}
						if (h == null) {
							h = 500;
						}	
						if (curr_row == null) {
							alert("请选择要操作的行！");
							return false;
						} else {		
							var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url+=val;
							url+="&cfwh=";
							url+=curr_row.cells[8].innerText;
							url+="&cfsj=";
							url+=curr_row.cells[9].innerText;	
							showTopWin(url, w, h);
						}
					}
				</script>
			</div>
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>

