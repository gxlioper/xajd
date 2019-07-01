<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function workAdd(){
				if ("" == $('gsmc').value){
					alert("请先选择单位!");
				} else {
					showTopWin('jywebUseCheckSession.do?method=gwwh','800','600');
				}
			}
		
		</script>
	</head>
	<body>
		<html:form action="/jyweb.do" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="button" style="display:none" onclick="window.refresh(document.URL)" id="search_go"/>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>招聘岗位发布</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="if(window.dialogArguments){window.close();dialogArgumentsQueryChick();}">关闭</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								单位名称
							</th>
							<td width="34%">
								<html:text property="save_gsmc" value="${companyInfo.dwmc}" styleId="gsmc" readonly="true"></html:text>
								<button onclick="showTopWin('/xgxt/jyweb.do?method=moreCompany',750,550);" class="btn_01">
									选择
								</button>
							</td>
							<th width="16%">
								单位地址
							</th>
							<td width="34%">
								${companyInfo.dz }
							</td>
						</tr>
						<tr>
							<th>
								单位性质
							</th>
							<td>
								${companyInfo.dwxzmc }
							</td>
							<th>
								行业分类
							</th>
							<td>
								${companyInfo.hyflmc }
							</td>
						</tr>
						<tr>
							<th>
								传 真
							</th>
							<td>
								${companyInfo.cz }
							</td>
							<th>
								网 址
							</th>
							<td>
								${companyInfo.wz }
							</td>
						</tr>
						<tr>
							<th>
								单位简介
							</th>
							<td colspan="3" style="word-break:break-all;">
								${companyInfo.dwjj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>岗位信息</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="toolbox">
									<div class="buttonbox" align="right">
										<ul>
											<li>
												<a
													href="javascript:workAdd();"
													class="btn_zj">增加</a>
											</li>
											<li>
												<a href="#"
													onclick="showInfo('jywebUseCheckSession.do?method=workUpdate&doType=update','update','800','580')"
													class="btn_xg"> 修改 </a>
											</li>
											<li>
												<a
													href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=workAdd&doType=del','del')"
													class="btn_sc"> 删除 </a>
											</li>
										</ul>
									</div>
								</div>
							
							
								<logic:notEmpty name="rs">
									<table summary="" class="dateline" width="100%">
										<thead>
											<tr>
												<td>
												</td>
												<logic:iterate id="tit" name="topTr" offset="1" scope="request">
													<td id="${tit.en}" onclick="tableSort(this)">
														${tit.cn}
													</td>
												</logic:iterate>
											</tr>
										</thead>
										<tbody>
											<logic:iterate name="rs" id="s">
												<tr onclick="rowOnClick(this)">
													<td>
														<input type="checkbox" id="cbv" name="primarykey_cbv"
															value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
														<input type="hidden" value="${v }" />
													</td>
													<logic:iterate id="v" name="s" offset="1">
														<td>
															${v }
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
									<!--分页显示-->
									<jsp:include flush="true"
										page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
									<!--分页显示-->
								</logic:notEmpty>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<logic:present name="message">
				<script>
		 			alert("${message}");
		 		</script>
			</logic:present>
		</html:form>
	</body>
</html>
