<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript">	
		function sendvalue(ssbh){
			showTopWin("/xgxt/zgdzdx_Gygl.do?method=getOneSsInfo&ssbh="+ssbh,600,600);
		}
		
		
		function printBb(){
			
			document.forms[0].action = "/xgxt/zgddGygl.do?method=rykTbtj&go=go&flg=printPage";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		</script>
	</head>

	<body onload="">

		<logic:notEqual value="printPage" name="flg">
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>公寓管理-房源库信息-图表统计</a>
				</p>
			</div>
		</logic:notEqual>

		<!-- 标题 end-->
		<html:form action="/zgddGygl" method="post">
			<%@ include file="/gygl/hiddenValue.jsp"%>

			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" />
					</FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<!-- 中国地质大学 -->
				<logic:notEqual value="printPage" name="flg">
					<logic:equal value="10491" name="xxdm">
						<div class="compTab" id="card">
							<div class="comp_title" id="div1">
								<ul>
									<li id="li-${s}">
										<a href="#"
											onclick="$('go').value='';refreshForm('ssxx_search.do?act=dormInfo')">
											<span>普通查询</span> </a>
									</li>
									<li id="li-${s}" class="ha">
										<a href="#"
											onclick="$('go').value='';refreshForm('zgddGygl.do?method=rykTbtj')">
											<span>图表统计</span> </a>
									</li>
								</ul>
							</div>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- 中国地质大学 end-->
				<!-- 信阳师范 -->
				<logic:equal value="10477" name="xxdm">
					<div class="compTab" id="card">
						<div class="comp_title" id="div1">
							<ul>
								<li id="li-${s}">
									<a href="#"
										onclick="$('go').value='';refreshForm('ssxx_search.do?act=dormInfo')">
										<span>普通查询</span> </a>
								</li>
								<li id="li-${s}" class="ha">
									<a href="#"
										onclick="$('go').value='';refreshForm('zgddGygl.do?method=rykTbtj')">
										<span>图表统计</span> </a>
								</li>
							</ul>
						</div>
					</div>
				</logic:equal>

				<logic:notEqual value="printPage" name="flg">
					<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" onclick="printBb();" class="btn_sh">打印 </a>
								</li>
							</ul>
						</div>
						<!--  信阳师范  end-->
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
														onclick="showTips('数据查询中，请等待......');allNotEmpThenGo('/xgxt/zgddGygl.do?method=rykTbtj');">
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
												校区
											</th>
											<td>
												<html:select property="xqdm" style="" styleId="xqdm"
													onchange="setLdList()">
													<html:options collection="xqdmList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												楼栋
											</th>
											<td>
												<html:select property="lddm" style="" styleId="lddm"
													onchange="setXqList();setCsList();setQsList();">
													<html:options collection="ldList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												所属层数
											</th>
											<td>
												<html:select property="cs" style="" styleId="cs"
													onchange="setQsList();">
													<html:options collection="csList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
										</tr>
										<!-- 第二行 -->
										<tr>
											<th>
												寝室号
											</th>
											<td>
												<html:select property="qsh" style="" styleId="qsh"
													onchange="">
													<html:options collection="qsList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<th>
												年级
											</th>
											<td>
												<html:select property="nj" style="" onchange="">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
											<th>
												<bean:message key="lable.xsgzyxpzxy" />
											</th>
											<td>
												<html:select property="xydm" style="" styleId="xy"
													onchange="">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</logic:notEqual>

				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> <logic:notEqual value="printPage" name="flg">	
							查询结果&nbsp;&nbsp; 
							</logic:notEqual> <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
							颜色状态：
								<font color="#000000">满的；</font>
								<font color="red">空的；</font>
								<font color="orange">闲的；</font>
								<font color="green">特殊房间(保留)；</font>
								<font color="#800080">外校生；</font>
								<font color="blue">异常数据寝室（入住学生数大于寝室床位数）。</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:equal value="printPage" name="flg">	
					<div class="btn noPrin">
			            <button onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">页面设置</button>
						<button onclick="try{WebBrowser.ExecWB(7,1)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">打印预览</button>
						<button onclick="try{WebBrowser.ExecWB(6,6)}catch(e){alert('请设置安全级别，启用ActiveX控件和插件！')}">直接打印</button>
			        </div>
			        </logic:equal>


					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="xqld">
							<table width="100%" class="table_01" style="">
								<tr>
									<td align="center" colspan="2">
										<font size="5" color="blue">${xqld.xqmc }/${xqld.ldmc }</font>
									</td>
								</tr>
								<logic:iterate id="cs" name="xqld" property="csList">
									<tr>
										<th align="center" width="10%" valign="middle">
											第${cs.ldcs.cs }层数
											<br />
											房间数：${cs.ldcs.fjs }
											<br />
											床位数：${cs.ldcs.zcw }
											<br />
											空床位数：${cs.ldcs.kcw }
										</th>
										<td>
											<table width="100%">
												<tr>
													<logic:iterate id="qs" name="cs" property="qsList"
														indexId="index">
														<td height="20px" width="10%">
															<a href="javascript:sendvalue('${qs.ssbh}')"> <font
																color="${qs.color }">${qs.qsh }</font> </a>
															<br />
															<logic:equal name="qs" property="color" value="red">
																<strong>空</strong>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																<br />
															</logic:equal>
															${qs.xymc }
															<br />
															${qs.sxnj }
															<br />
															<logic:equal name="qs" property="color" value="green">																
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																<br />
															</logic:equal>
														</td>
														<%
														if ((index.intValue() + 1) % 10 == 0) {
														%>
														<%
														out.print("</tr>");
														%>
														<%
														}
														%>
													</logic:iterate>
												</tr>
											</table>
										</td>
									</tr>
								</logic:iterate>
							</table>
						</logic:iterate>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
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

		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{
				display:none;
			}
		</style>
	</body>
</html>
