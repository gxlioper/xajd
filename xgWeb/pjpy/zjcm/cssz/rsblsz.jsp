
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"
			src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
	</head>
	<body onload="xyDisabled('xy');dispconf('fs')">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - ��������</a>
			</p>
		</div>
		<!-- ���� end-->


		<html:form action="/pjpyZjcmCssz.do?method=rsblsz" method="post">
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />


			<div class="comp_title">
				<ul>
					<li class="ha">
						<a href="javascript:refreshForm('pjpyZjcmCssz.do?method=rsblsz');"><span>��ѧ���������</span>
						</a>
					</li>
					<li>
						<a
							href="javascript:refreshForm('pjpyZjcmCssz.do?method=rychRsblsz');"><span>�����ƺű�������</span>
						</a>
					</li>
				</ul>
			</div>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showTopWin('pjpy_tyb_pjsjsz.do',450,350);"
								class="btn_sz">������������</a>
						</li>
						<li>
							<a href="#" onclick="showTopWin('viewTotStuNum.do',700,500);"
								class="btn_ck">�鿴��������</a>
						</li>
						<li>
							<a href="#" onclick="baseDataInit('jxj');" class="btn_csh">��ʼ������</a>
						</li>
						<li>
							<a href="#"
								onclick="showTopWin('pjpy_zjcm_jxjblPlsz.do',500,450);"
								class="btn_sz">�������ñ���</a>
						</li>
						<li>
							<a href="#"
								onclick="modiAndDel('pjpy_zjcm_jxjrstz.do?pkValue=','modi','620','520');"
								class="btn_sz">��������</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width:90px"
										styleClass="select" styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" styleId="xq" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" styleId="nd" disabled="true">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��ѧ�����
								</th>
								<td>
									<html:select property="jxjlb" styleId="jxjlb"
										onchange="changeJxj('pjpy_zjcm_rsblsz.do')">
										<html:options collection="lbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��ѧ��
								</th>
								<td>
									<html:select property="jxjdm" styleId="dm" style="width:150px">
										<html:options collection="dmList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��ʾ��Χ
								</th>
								<td>
									<html:select property="fs" styleId="fs"
										onchange="dispconf('fs')">
										<html:options collection="cpfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" styleClass="select"
										style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleClass="select"
										style="width:180px" styleId="xy">
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
										style="width:180px" styleClass="select" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:180px"
										styleClass="select" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th></th>
								<td></td>
								<th></th>
								<td>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('pjpy_zjcm_rsblsz.do?act=qry');">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� -->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:notEmpty name="rs">
						<div class="con_overlfow">
							<table summary="" class="dateline tablenowrap" align=""
								width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()"
												style="height: 17.5px" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" style="cursor:hand;"
											ondblclick="modiAndDel('pjpy_zjcm_jxjrstz.do?act=view&pkValue=','modi','620','520');">
											<td align=center>
												<input type="checkbox" id="cbv" name="cbv"
													style="height: 17.5px"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
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
						</div>
					</logic:notEmpty>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=pjpyZjcmActionForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
		</html:form>
		<div id="tmpdiv"></div>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
	 			alert('����ʧ�ܣ�');
	 			document.getElementById('search_go').onclick();
	 		</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
