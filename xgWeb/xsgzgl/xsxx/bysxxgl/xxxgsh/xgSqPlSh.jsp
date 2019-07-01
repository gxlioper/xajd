<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxxgsh/js/xgsh.js"></script>
	</head>
	<body >	
	<html:form action="/xsxx_bysxx_xxxgsh" method="post" styleId="form1">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" name="dataJson" id="dataJson" value='${dataJson}'/>
			<input type="hidden" name="shjg" id="shjg" value=''/>
			<table width="100%" border="0" style="margin-bottom: 5px" class="formlist" >
					<tbody>			
						<tr>
							<th>
								<font color="red">*</font>审核意见
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=bysxxxg&id=shyj" />
								
								<textarea rows="5" style="width: 90%;margin-top: 5px;" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<div class="btn">
										<button type="button"  onclick="save_plsh(1);">
											通过
										</button>
										<button type="button"  onclick="save_plsh(2);">
											不通过
										</button>
										<button type="button" name="关 闭" onclick="iFClose();">
											关 闭
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</html:form>
		
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</body>
</html>

