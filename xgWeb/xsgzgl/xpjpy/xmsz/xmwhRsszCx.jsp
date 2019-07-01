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
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhRsszCx.js"></script>
		
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
		    <%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="rsfpfs" styleId="rsfpfs"/>
			<html:hidden property="status" styleId="status"/>
			<html:hidden property="czfs" styleId="czfs"/>
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
		<logic:notEqual value="10335" name="xxdm">
		<div id="div_help" class="prompt" >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					1.���������۲���ύʱ����ȷ�ϣ��������۲���ύ��ɺ��ٽ��д������á�
				</p>
				<p>
					2.�����ύ������Ϊ��ȷ���Ĳ�����Ա���������ֻ�����۲���ύ״̬���б仯��
				</p>
				<p>
					3.������������Ϊ��ǰ����������������ȷ����δȷ��������Ա����
				</p>
				<p>
					4.���������ǽ������ύ�ˡ�����Ϊ�������б�������������㡣
				</p>
				<p id="zzrsscTip">
					ע�����������ɼ����������������Զ�����
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		</logic:notEqual>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox" >
				<ul>
					<logic:notEqual name="czfs" value="xyrssz">
					  <%-- �㽭��ѧ��ʱ���ذ�ť --%>
					  <logic:notEqual name="xmdm" value="10335">
						<li id="fpszLi"><a href="javascript:void(0);" onclick="blsz();" class="btn_sz">��������</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">����</a></li>
					  </logic:notEqual>
					</logic:notEqual>
					<logic:equal name="czfs" value="xyrssz">
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">����</a></li>
					</logic:equal>
				</ul>
			</div> 
			
			<div class="zmeTip">
				<p id="fpmeTip"></p>
				<p>
					<a href="javascript:void(0);" onclick="lnme();" >�鿴���������</a>
				</p>
			</div>
			<div class="searchtab">
				<table width="100%" border="0">
				<logic:equal value="bj" property="rsfpfs" name="rsszModel">
					<tr>
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
						</html:select>
						</td>
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
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
						</html:select>
						</td>
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
				<logic:equal value="njxy" property="rsfpfs" name="rsszModel">
					<tr>
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
						</html:select>
						</td>
						<td>�꼶</td>
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
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
						</html:select>
						</td>
						<td><bean:message key="lable.xb" /></td>
						<td>
						<html:select property="xydm" styleId="xy" style="width:150px" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						</td>
						<td>רҵ</td>
						<td>
						<html:select property="zy" styleId="zydm" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						</td>
					</tr>
				</logic:equal>
				<logic:equal value="xy" property="rsfpfs" name="rsszModel">
					<tr>
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
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
				<logic:equal value="cpz" property="rsfpfs" name="rsszModel">
					<tr>
						<td>�Ƿ�������</td>
						<td>
						<html:select property="sfysz" styleId="sfysz" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="1">��</html:option>
							<html:option value="0">��</html:option>
						</html:select>
						</td>
						<td>����������</td>
						<td>
							<input type="text" id="cpzmc" name="cpzmc" maxleng="20" />
						</td>
						<td></td>
						<td></td>
					</tr>
				</logic:equal>
					<tr>
						<td colspan="5">
							<h3>
								��ǰ������ĿΪ��<font color="red" >${xmmc}</font>
							</h3>
						</td>
						<td align="left">
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
						<script type="text/javascript">
							function setSfyxgb(obj){
								var pp = jQuery(obj).val();
								jQuery("#sfyxgb").val(pp);
<%--								var rs = obj.value;--%>
<%--								var api = frameElement.api,W = api.opener;--%>
<%--								var p = jQuery(W).html();--%>
<%--								alert(p);--%>
							}
						</script>
						
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
									<button type="button" onclick="iFClose();">
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
