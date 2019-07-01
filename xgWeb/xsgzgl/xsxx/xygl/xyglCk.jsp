<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xygl/js/xyglCk.js"></script>
		<script type="text/javascript" src="js/extend/zfsoft-dataExtend.js"></script>
	</head>
	<script type="text/javascript">
	</script>
	<body >
	<html:form action="/xsxx_xsxxgl" method="post">
			<!-- Òþ²ØÓò -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="jxjs" id="jxjs" value="${jxhj.js }" />
			<input type="hidden" name="jxzje" id="jxzje" value="${jxhj.zje }" />
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="zq" id="zq" value='${zq}'/>
			<input type="hidden" name="kzxxid" id="kzxxid" value='${kzxxModel.jgid}'/>
			
			<!-- ¸¡¶¯¿ò¼Ü -->
			<div>
				<div class="title_xxxx">
					<span class="people_xx"><span id="xmView"></span> £¨Ñ§ºÅ£º ${xh }£©</span>		
				</div>	
			</div>
			<div class="demo_xxxx" style="margin-top: 5px; overflow-x:hidden;" id="content">
			</div>
			
	</html:form>
		<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
								<button name="¹Ø±Õ" onclick="Close()" type="button"
									id="buttonClose">
									¹Ø ±Õ
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>			
		<div id="zpHidDiv" style="display: none;">
			<jsp:include flush="true" page="zpxs.jsp"></jsp:include>
		</div>
	</body>
</html>

