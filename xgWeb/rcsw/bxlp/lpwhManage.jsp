<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
		<script type="text/javascript" >
		//�����ύɾ��
		function sumitInfo(url,doType){
			var len=jQuery("input:checkbox[name=checkVal]:checked").length;
			if(len > 0){
					url+="&doType="+doType;
					if (confirm("ȷ��Ҫɾ������ѡ������?")) {
						showTips('���������У���ȴ�......');
						refreshForm(url);
					}
			}else{
				alert("�빴ѡҪ���������");
				return false;
			}
		}

		//���´���
		function showInfo(url,doType,w,h){

			var len=jQuery("input:checkbox[name=checkVal]:checked").length;
			if(len != 1){
				alert("�빴ѡһ��Ҫ�޸ĵ�����");
				return false;
			}
			var pk = jQuery("input:checkbox[name=checkVal]:checked").val();
			url+="&doType="+doType;
			url+="&pk="+pk;
			showTopWin(url,w,h);
		}
				
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/rcsw_bxlp" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<input type="hidden" name="likeCol" value="xh!!xm!!sfzh" />
			<input type="hidden" name="queryCol" value="xb!!nj!!xydm!!zydm!!bjdm!!cxlx" />

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/rcsw_bxlp.do?method=lpwhSave','650','500')"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/rcsw_bxlp.do?method=lpwhSave','update','650','500');"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/rcsw_bxlp.do?method=lpwhManage','del');"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkDataForZdy('ty_bdsz')"
									class="btn_dr"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="ZdyDataExport('ty_bdsz_bcnr')"
									class="btn_dc"> ���� </a>
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
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/rcsw_bxlp.do?method=lpwhManage&go=go')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
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
									<html:select property="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" styleClass="inputtext"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal value="xy" name="userType">
										<input type="hidden" name="xydm" value="${userDep }"/>
									</logic:equal>
									<html:select property="xydm" style="width:200px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										style="width:200px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									<html:select property="xb">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>
								</td>
								<th>
									���֤��
								</th>
								<td>
									<html:text property="sfzh" styleId="sfzh" maxlength="18"
										styleClass="inputtext"></html:text>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="cxlx">
										<html:options collection="cxlxList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<logic:empty name="rs">
					    <h3 class="datetitle_01">
					    <span>
					    	��ѯ���&nbsp;&nbsp;
								<font color="red">δ�ҵ��κμ�¼��</font> 
					    </span>
					    </h3>
					 </logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span>
								��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
							</span>
						</h3>
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
											<td nowrap>
												<input type="checkbox" id="cb" name="cb"
													onclick="selectAll()" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="1"
												scope="request">
												<td id="${tit.en}" onclick="tableSort(this)" nowrap>
													${tit.cn}
												</td>
											</logic:iterate>
										</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)"
											ondblclick="showInfo('/xgxt/rcsw_bxlp.do?method=lpwhSave','view','650','500');"
											style="cursor:hand;">
											<td align=center>
												<input type="checkbox" id="pk" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align=center>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
							</tbody>
						</table>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=bxlpForm"></jsp:include>
						<!--��ҳ��ʾ-->
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
