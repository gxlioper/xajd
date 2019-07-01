<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
		<script language="JavaScript">
		<!--
<%--		self.moveTo(100,100)--%>
<%--		self.resizeTo("800","600")--%>
		//-->
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 住宿结果 - 空闲宿舍</a>
			</p>
		</div>

		<!-- 标题 end-->
		<html:form action="/gyglZsjg">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<html:hidden property="lddm" styleId="lddm"/>

			<!-- 查询结果-->
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>楼栋信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="czxq" value="是">
							<tr>
								<th style="width:16%">
									所属
									<bean:message key="lable.xiaoqu" />
								</th>
								<td colspan="3" style="width:84%">
									${ldMap.xqmc }
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="czyq" value="是">
							<tr>
								<th style="width:16%">
									所属
									<bean:message key="lable.yuanqu" />
								</th>
								<td colspan="3" style="width:84%">
									${ldMap.yqmc }
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.ld" />
							</th>
							<td style="width:34%">
								${ldMap.ldmc }
							</td>
							<th style="width:16%">
								<bean:message key="lable.cs" />
							</th>
							<td style="width:34%">
								${ldMap.cs }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								楼栋性别限制
							</th>
							<td style="width:34%">
								${ldMap.xbxd}
							</td>
							<th style="width:16%">

							</th>
							<td style="width:34%">

							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="formbox">
									<h4 class="datetitle_01">
										<span> 查询结果 <logic:empty name="rs">
											&nbsp;&nbsp;<font color="red">未找到任何记录！</font>
											</logic:empty> <logic:notEmpty name="rs">
											&nbsp;&nbsp;<font color="blue"></font>
											</logic:notEmpty>
										 </span>
									</h3>
									<div style='width:99%;height:300px;overflow:auto;overflow-x:hidden'>
										<table summary="" class="dateline" align="" width="98%">
											<!-- 表头 -->
											<thead>
												<tr align="center" style="cursor:hand">
													<logic:iterate id="tit" name="topTr" offset="0">
														<td id="<bean:write name="tit" property="en"/>" nowrap>
															<bean:write name="tit" property="cn" />
														</td>
													</logic:iterate>
												</tr>
											</thead>
											<!-- 表头 end-->
											<!--内容 -->
											<logic:notEmpty name="rs">
												<logic:iterate name="rs" id="s" indexId="index">
													<tr onclick="rowOnClick(this);" style="cursor:hand">
														<!-- 显示信息 -->
														<logic:iterate id="v" name="s" offset="0">
															<td align="left" nowrap="nowrap">
																${v }
															</td>
														</logic:iterate>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
											<!--内容 end-->

										</table>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button class="button2" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>


			<!-- 查询结果 end-->

		</html:form>
	</body>
</html>
