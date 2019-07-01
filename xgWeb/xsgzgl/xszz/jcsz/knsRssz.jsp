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
		<script type="text/javascript" src="xsgzgl/xszz/jcsz/js/knsRssz.js"></script>
		
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
		<html:form action="/xszz_jcsz" method="post" styleId="form1">
			<input type="hidden" id="knsrsszfs" value="${knsrsszfs}"/>
			<input type="hidden" name="bl"  id="bl" />
			<input type="hidden" name="fpbl"  id="fpbl" />
			<input type="hidden" name="json"  id="json" />
			<input type="hidden" name="fpfs"  id="fpfs" />
			<input type="hidden" name="zme"  id="zme" />			
			<input type="hidden" name="rskzfw" id="rskzfw" value="${rskzfw }"/>
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" name="rskznj" id="rskznj" value="${rskznj}"/>
			<input type="hidden" name="njList" id="njList" value="${njArrList}"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="xn" styleId="xn"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div id="div_help" class="prompt" >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�������Ʒ�ΧΪ��<font color="red"><span id="helpTip"></span></font>
				</p>
				<p id="zzrsscTip">
					ע�����������ɼ����������������Զ�����
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li id="fpszLi"><a href="javascript:void(0);" onclick="blsz();" class="btn_sz">��������</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">����</a></li>
				</ul>
			</div>
			
			<div class="zmeTip">
				<p id="fpmeTip"></p>
				<%--<p>
					<a href="javascript:void(0);" onclick="lnme();" >�鿴���������</a>
				</p>--%>
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
				<tr>
					<logic:equal value="1"  name="knsrsszfs">
						<td>�϶�����</td>
							<td>
								<html:select property="rddc" styleId="rddc" style="width:130px" onchange="query();fpmeTip();">
									<html:options collection="knsdcList" property="dcdm" labelProperty="dcmc" />
								</html:select>
							</td>
					</logic:equal>
					<!-- �Ƿ���������5���������Ʒ�Χ���У��� �ᵽ��� -->
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
						</html:select>
						</td>
					
					
				<logic:equal value="bj" property="rskzfw" name="xszzKnsJcszForm">
					
						<td>�꼶</td>
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
						<td>רҵ</td>
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
				<logic:equal value="njzy" property="rskzfw" name="xszzKnsJcszForm">
					
						<td>�꼶</td>
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
						<td>רҵ</td>
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
				<logic:equal value="njxy" property="rskzfw" name="xszzKnsJcszForm">
					
						<td>�꼶</td>
						<td>
						<html:select property="njq" styleId="nj" style="width:150px" >
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
				<logic:equal value="zy" property="rskzfw" name="xszzKnsJcszForm">
					
						<td><bean:message key="lable.xb" /></td>
						<td>
						<html:select property="xydm" styleId="xy" style="width:150px" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
						<td>רҵ</td>
						<td>
						<html:select property="zydm" styleId="zy" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="xy" property="rskzfw" name="xszzKnsJcszForm">
				
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
						<td colspan="5"></td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
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
		<div id="blDiv" style="display: none;">
			<div  id="njTipDiv" class="prompt"  >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ���õġ������꼶��ֻ�����������ķ�������������Ҳ��Ҫ�����꼶���뵽���������á�ҳ����С�ѧ�������꼶�������á�
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>���䷽ʽ
							</th>
							<td width="34%" >
								<label>
									<input type="radio" name="fpfs" value="bl" checked="checked" onclick="setFpfs(this);" >����������
								</label>
								<label>
									<input type="radio" name="fpfs" value="zme" onclick="setFpfs(this);" >�����������
								</label>
							</td>
						</tr>
						<tr name="njTr" style="display:none;">
							<th width="16%">
								<font color="red">*</font>�����꼶
							</th>
							<td name="njTd" width="34%" >
							</td>
						</tr>
						<tr name="blTr" >
							<th width="16%">
								<font color="red">*</font>����(%)
							</th>
							<td width="34%" >
								<input type="text" name="blView"  maxlength="5" class="text_nor" /><br/>
								<font color="red"><span name="blTip"></span></font>
							</td>
						</tr>
						<tr name="zmeTr" style="display:none;">
							<th width="16%">
								<font color="red">*</font>������
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="divLayerClose();">
										�� ��
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
