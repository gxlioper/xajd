<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxxgsq/js/showXgXx.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/comm/js/commQuery.js"></script>
	</head>
	<body >	
	<html:form action="/xsxx_bysxx_xxxgsq" method="post" styleId="form1">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="sqid" id="sqid" value='${sqid}'/>
			<!-- 浮动框架 -->
			<div style="height: 50px;">
				<div id="navigation" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span class="people_xx"><span id="xmView"></span> （学号： ${xh }）</span>
						<span class="wxts">温馨提醒： <span>点击下面的类别，
								可以快速定位到您所要查看的信息</span>
						</span>
					</div>
				</div>
			</div>
			<div class="demo_xxxx" style="margin-top: 20px; overflow-x:hidden;" id="content">
			</div>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="关闭" onclick="Close()" type="button"
										id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>		
			<div id="zpHidDiv" style="display: none;">			
				<jsp:include flush="true" page="/xsgzgl/xsxx/xsxxgl/zpxs.jsp"></jsp:include>
			</div>

			<div id="jtcyxxHidDiv" style="display: none;">
				<jsp:include flush="true" page="/xsgzgl/xsxx/xsxxgl/jtcyxxxs.jsp"></jsp:include>
			</div>						

			</html:form>
		
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</body>
</html>

