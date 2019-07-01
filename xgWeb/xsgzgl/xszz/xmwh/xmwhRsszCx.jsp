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
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhRsszCx.js"></script>
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
		<html:form action="/xszz_xmwh_rssz" method="post" styleId="form1">
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="pdxn" styleId="pdxn"/>
			<html:hidden property="pdxq" styleId="pdxq"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="rskzfw" styleId="rskzfw"/>
			<html:hidden property="status" styleId="status"/>
			<input type="hidden" name="bl"  id="bl" />
			<input type="hidden" name="json"  id="json" />
			<input type="hidden" name="fpfs"  id="fpfs" />
			<input type="hidden" name="zme"  id="zme" />			
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" name="rskznj" id="rskznj" value="${rskznj}"/>
			<input type="hidden" name="njList" id="njList" value="${njArrList}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" name="xslb"  id="xslb" value="${xslb}"/>
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
				</p>
				<p>
					人数控制范围为：<font color="red"><span id="helpTip"></span></font>
				</p>
				<p>
					注：最终人数由计算人数四舍五入自动生成
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		
		
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="blsz();" class="btn_sz">分配设置</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">保存</a></li>
				</ul>
			</div>
			
			<div class="zmeTip">
				<p id="fpmeTip"></p>
				<p>
					<%--<a href="javascript:void(0);" onclick="lnme();" >查看历年分配名额</a>--%>
				</p>
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
				<logic:equal value="bj" property="rskzfw" name="xmwhRsszForm">
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
						<html:select property="xydm" styleId="xy" style="width:150px"
						onchange="initZyList();">
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
				<logic:equal value="njzy" property="rskzfw" name="xmwhRsszForm">
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
				<logic:equal value="njxy" property="rskzfw" name="xmwhRsszForm">
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
						<html:select property="xydm" styleId="xy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="zy" property="rskzfw" name="xmwhRsszForm">
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
						<html:select property="zydm" styleId="zy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="xy" property="rskzfw" name="xmwhRsszForm">
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
				<logic:equal value="sy" property="rskzfw" name="xmwhRsszForm">
					<tr>
						<td>是否已设置</td>
						<td>
							<html:select property="sfysz" styleId="sfysz" style="width:80px">
								<html:option value=""></html:option>
								<html:option value="1">是</html:option>
								<html:option value="0">否</html:option>
							</html:select>
						</td>
						<td>书院</td>
						<td><%--共用一个字段--%>
							<html:select property="xydm" styleId="xy" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="syList" property="sydm" labelProperty="symc"/>
							</html:select>
						</td>
						<td></td>
						<td></td>
					</tr>
				</logic:equal>
					<tr>
						<td colspan="5"></td>
						<td>
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
		<div class="formbox">
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
		<div id="blDiv" style="display: none;">
			<div  id="njTipDiv" class="prompt"  >
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置的“控制年级”只是设置人数的分配名额，如果申请也需要控制年级，请到“条件设置”页面进行“学生所在年级”的设置。
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>分配设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>分配方式
							</th>
							<td width="34%" >
								<label>
									<input type="radio" name="fpfs" value="bl" checked="checked" onclick="setFpfs(this);" >按比例分配
								</label>
								<label>
									<input type="radio" name="fpfs" value="zme" onclick="setFpfs(this);" >按总名额分配
								</label>
							</td>
						</tr>
						<tr name="njTr" style="display:none;">
							<th width="16%">
								<font color="red">*</font>控制年级
							</th>
							<td name="njTd" width="34%" >
							</td>
						</tr>
						<tr name="blTr" >
							<th width="16%">
								<font color="red">*</font>比例(%)
							</th>
							<td width="34%" >
								<input type="text" name="blView"  maxlength="5" class="text_nor" /><br/>
								<font color="red"><span name="blTip"></span></font>
							</td>
						</tr>
						<tr name="zmeTr" style="display:none;">
							<th width="16%">
								<font color="red">*</font>总名额
							</th>
							<td width="34%" >
								<input type="text" name="zmeView"  maxlength="6" class="text_nor" /><br/>
								<font color="red"><span name="zmeTip"></span></font>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="divLayerClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
