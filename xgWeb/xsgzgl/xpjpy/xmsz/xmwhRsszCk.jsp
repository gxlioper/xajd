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
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhRsszCk.js"></script>
		
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
		<html:form action="/xpj_xmwh_rssz" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="rsfpfs" styleId="rsfpfs"/>
			<html:hidden property="status" styleId="status"/>
			<input type="hidden" name="fpbl"  id="fpbl" />
			<input type="hidden" name="json"  id="json" />
			<input type="hidden" name="fpfs"  id="fpfs" />
			<input type="hidden" name="zme"  id="zme" />			
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" name="rsfpnj" id="rsfpnj" value="${rsfpnj}"/>
			<input type="hidden" name="njList" id="njList" value="${njArrList}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<div class="searchtab">
				<table width="100%" border="0">
				<logic:equal value="bj" property="rsfpfs" name="rsszModel">
					<tr>
						<td>是否已设置</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">是</html:option>
							<html:option value="0">否</html:option>
						</html:select>
						</td>
						<td>年级</td>
						<td>
						<html:select property="njq" styleId="nj" style="width:150px" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
						</td>
						<td><bean:message key="lable.xb" /></td>
						<td>
						<html:select property="xydm" styleId="xy" style="width:150px" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
					</tr>
					<tr>
						<td>专业</td>
						<td>
						<html:select property="zydm" styleId="zy" style="width:150px" >
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</logic:equal>
				<logic:equal value="njzy" property="rsfpfs" name="rsszModel">
					<tr>
						<td>是否已设置</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">是</html:option>
							<html:option value="0">否</html:option>
						</html:select>
						</td>
						<td>年级</td>
						<td>
						<html:select property="njq" styleId="nj" style="width:150px" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
						</td>
						<td><bean:message key="lable.xb" /></td>
						<td>
						<html:select property="xydm" styleId="xy" style="width:150px"onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
					</tr>
					<tr>
						<td>专业</td>
						<td>
						<html:select property="zydm" styleId="zy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</logic:equal>
				<logic:equal value="njxy" property="rsfpfs" name="rsszModel">
					<tr>
						<td>是否已设置</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">是</html:option>
							<html:option value="0">否</html:option>
						</html:select>
						</td>
						<td>年级</td>
						<td>
						<html:select property="njq" styleId="nj" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
						</td>
						<td><bean:message key="lable.xb" /></td>
						<td>
						<html:select property="xydm" styleId="xy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="zy" property="rsfpfs" name="rsszModel">
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
						<html:select property="xydm" styleId="xy" style="width:150px" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
						<td>专业</td>
						<td>
						<html:select property="zy" styleId="zy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="xy" property="rsfpfs" name="rsszModel">
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
				</logic:equal>
					<tr>
						<td colspan="5">
							<h3>
								当前查看项目为：<font color="red" >${xmmc}</font>
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
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
