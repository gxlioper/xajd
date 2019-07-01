<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript">
		//��Ƭ������
		jQuery(function(){
			
			jQuery('#btn_dc').click(function(){
				showDialog("", 500, 200, 'xtwhZpgl.do?method=zpdc');
				//tipsWindown("ϵͳ��ʾ","id:zpdcDiv","500","200","true","","true","id");
			});
			
		})
		
		//��Ƭ����
		function zpdc(){
			var photoNameType=$("photoNameType").value;
			
			document.forms[0].action = 'xtwhZpgl.do?method=xszpdc&photoNameType='+photoNameType;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		</script>
	</head>
	<body>
		<html:form action="/xtwhZpgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showDialog('', 800, 400, 'xtwhZpgl.do?method=xszpdrManage');"
								class="btn_dr"> ������Ƭ���� </a>
						</li>
						<li>
							<a href="#"
								onclick="showDialog('',800,400,'xtwhZpgl.do?method=xszpdrManage&zpType=xszp');"
								class="btn_dr"> ѧ����Ƭ���� </a>
						</li>
						<li>
							<a href="#" id="btn_dc"
								class="btn_dc"> ���� </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xtwhZpgl.do?method=zpglManage')">
											��ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" />
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										style="width:150px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="queryequals_xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy"
											style="width:150px" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy"
										style="width:150px" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									������
								</th>
								<td>
									<html:text property="ksh" styleId="ksh" />
								</td>
								<th>
									���֤��
								</th>
								<td>
									<html:text property="sfzh" styleId="sfzh" />
								</td>
								<th>
<%--									�Ƿ�����Ƭ--%>
									������Ƭ״̬
								</th>
								<td>
									<html:select property="sfdrzp" styleId="sfdrzp"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="�ѵ���">�ѵ���</html:option>
										<html:option value="δ����">δ����</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									������Ƭ״̬
								</th>
								<td>
									<html:select property="sfdrxszp" styleId="sfdrxszp"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="�ѵ���">�ѵ���</html:option>
										<html:option value="δ����">δ����</html:option>
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
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rsArrList">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</div>
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xtwhZpglForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
					</script>
					
				</logic:notEmpty>
				<div id="zpdcDiv" style="display:none">
					
					<div class="open_win01">
						<table align="center" class="formlist">
							<div id="xxPrompt" class="prompt">
								<h3>
									<span>��ʾ��</span>
								</h3>
								<p >
									��ѧ����Ϣ����ѡ�ֶ�����Ϊ�գ�����Ƭ�ĵ���������ʽĬ��Ϊ<br/>
									������+ѧ�š��� 
								</p>
								<a class="close" title="����"
									onclick="this.parentNode.style.display='none';"></a>
							</div>
							<tbody>
								<tr>
									<th>
										������Ƭ��������ʽ
									</th>
									<td>
										<html:select property="photoNameType" styleId="photoNameType" style="width:120px">
											<html:option value="xh">ѧ��</html:option>
										 	<html:option value="sfzh">���֤��</html:option>
										 	<html:option value="ksh">������</html:option>
										</html:select>
									</td>
								</tr>
<%--								<tr>--%>
<%--									<th>--%>
<%--										��������--%>
<%--									</th>--%>
<%--									<td>--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="xy"/>ѧԺ--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="zy"/>רҵ--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="bj"/>�༶--%>
<%--										<html:radio property="bmlx" styleId="bmlx" value="wxz"/>������--%>
<%--									</td>--%>
<%--								</tr>--%>
								<tr>
									<th>
										�������
									</th>
									<td>
										<input type="radio" name="zpType" value="xs_zs" checked="checked"/>����&������Ƭ
										<input type="radio" name="zpType" value="xszp"/>������Ƭ
										<input type="radio" name="zpType" value="zszp"/>������Ƭ
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="bz">
											"
											<span class="red">*</span>"Ϊ������
										</div>
										<div class="btn">
											<button type="button" name="����"
												onclick="zpdc()">
												�� ��
											</button>
											<button type="button" name="ȡ��" onclick="closeWindown();return false;">
												ȡ ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
