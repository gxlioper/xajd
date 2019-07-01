<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/zxdk/gzdx/zxdkjs/zxdkjs.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript">
</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.˫��һ�п��Բ鿴��ϸ��Ϣ��</br>
				2.������ͷ��������</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/zxdk_gzdx.do" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="tableName" value="${tableName }" />
			<input type="hidden" name="realTable" value="${realTable }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failInfo" id="failInfo"
				value="${failinfo }" />


			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('zxdk_gzdx_addZxdkSjwh.do',640,510)"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="modiAndDel('zxdk_gzdx_updateZxdkSjwh.do?pkValue=','modi',640,510)"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#" onclick="deldata('zxdk_gzdx_delZxdkSjwh.do')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc"> ���� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('zxdk_gzdx_sjwh.do')">
											��ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px"
										styleClass="select" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:150px"
										styleClass="select">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:150px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										style="width:150px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									��ѧ��������
								</th>
								<td>
									<html:select property="zxdkmc" styleId="zxdkmc" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="��Դ�ش���">��Դ�ش���</html:option>
										<html:option value="������ѧ����">������ѧ����</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" styleClass="inputtext"
										style="width:130px" maxlength="15">
									</html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"
										style="width:130px" maxlength="15">
									</html:text>
								</td>
								
								<th>
									������
								</th>
								<td>
									<html:text property="dkje_ks" 
										style='width:50px' maxlength="5"  styleId="dkje_ks" onblur="checkInputNum(this);"  /> (Ԫ)
										-
									<html:text property="dkje_js"
										style='width:50px' maxlength="5" styleId="dkje_js" onblur="checkInputNum(this);" /> (Ԫ)
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox"><!-- formbox -->
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
				<div class="con_overlfow">
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody >
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;"
										ondblclick="modiAndDel('zxdk_gzdx_updateZxdkSjwh.do?operType=view&pkValue=','modi',640,510)" >
										<td nowrap="nowrap">
											<input type="checkbox" id="cbv" name="cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
												<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
												
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=zxdkgzdxForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alertInfo('�����ɹ�!');
					//document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alertInfo('����ʧ��!');
					//document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>