<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhShszCx.js"></script>
		<style>
			#xmList tr{ height:40px; }	
			#xmList td{ width:100px; }	
		</style>
	</head>
	<body>
		<html:form action="/xszz_xmwh_shsz" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm" />
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>--%>
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：<font color="red">${xmmc}</font>
					
					<span id="spztTip" style="display:none;">
					<br/>
					<font color="red">当前项目已有学生申请，不允许修改</font>
					</span>
				</p>
				<p>
					已勾选项目为可调整的项目
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			
				<table width="100%" border="0" class="dateline">
					<tbody id="xmList">
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="saveBtn" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

