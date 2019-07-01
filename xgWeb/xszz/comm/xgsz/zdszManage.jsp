<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
		
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" 
								onclick="showXxInfo();" 
								class="btn_ck">保存</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button"  class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('kssj','jssj')){allNotEmpThenGo('/xgxt/commXszz.do?method=jgcxManage')}">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('nj-xn-xq-nd-kssj-jssj-xy-zy-bj-xh-xm-xmdm-shzt1-shzt2-shzt3-xmzzjb');">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									项目
								</th>
								<td>
									<html:select property="xmdm" style="" styleId="xmdm">
										<html:options collection="xmList" property="dm" labelProperty="mc" />
									</html:select>
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
			</div>
		</html:form>
	</body>
</html>