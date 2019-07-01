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
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/xyrssz/xyyhCz.js"></script>
		
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<style>
		
		.zmeTip { line-height:20px; min-height:20px; _height:20px; padding:5px 30px 5px 5px; border:#b9b9b9 1px solid; margin-bottom:6px; position:relative; }
		.zmeTip p { padding-left:5px; margin:0 5px; float:left; }
		.zmeTip p a{color:#135dbb!important; }
		
		</style>
	</head>
	<body>	
		<html:form action="/xpjpy_xyrssz" method="post" styleId="xyrsszForm">
		    <%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="rsfpfs" styleId="rsfpfs"/>
			<html:hidden property="status" styleId="status"/>
			<input type="hidden" name="fpbl"  id="fpbl" />
			<input type="hidden" name="json"  id="json" />
			<input type="hidden" name="fpfs"  id="fpfs" />
			<input type="hidden" name="zme"  id="zme" />
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
			<input type="hidden" name="xmmc" id="xmmc" value="${xmmc}"/>
			<input type="hidden" name="xmje" id="xmje" value="${xmje}"/>
			<input type="hidden" name="rsjsfs" id="rsjsfs" value="${rsjsfs }" />			
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" name="rsfpnj" id="rsfpnj" value="${rsfpnj}"/>
			<input type="hidden" name="njList" id="njList" value="${njArrList}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox" >
				<ul>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">保存</a></li>
				</ul>
			</div> 
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<td>是否已设置</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">是</html:option>
							<html:option value="0">否</html:option>
						</html:select>
						</td>
						<td><bean:message key="lable.xb" /></td>
						<td>
						<html:select property="xydm" styleId="xy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="5">
							<h3>
								当前设置项目为：<font color="red" >${xmmc}</font>
							</h3>
						</td>
						<td align="left">
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
			<logic:notEqual name="userType" value="xy">
				<div id="pager"></div>
			</logic:notEqual>
		</div>
		</html:form>
	</body>
</html>