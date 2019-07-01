<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function modi(url){
			
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function view(url){
			
			showTopWin(url + '?pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);

		}
		</script>
	</head>
	<body>

		<html:form action="/qgzxLsgwxx" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xg_zjjs_qgzx_lsgwxxb" />
			<input type="hidden" name="tableName" id="tableName"
				value="xg_view_zjjs_qgzx_lsgwxx" />
			<input type="hidden" name="viewName" id="viewName"
				value="xg_view_zjjs_qgzx_lsgwxx" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#"
								onclick="dataBatch('qgzxLsgwxx.do?doType=del')"
								class="btn_sc"> ɾ�� </a>
						</li>
						
						<li>
							<a href="#" class="btn_dr"
								onclick="impAndChkData();return false;">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc"
								onclick="dataExport();return false;">����</a>
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
											onclick="allNotEmpThenGo('qgzxLsgwxx.do')">
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
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
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
										<html:select property="queryequals_xydm" styleId="xy"
											disabled="true" value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
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
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
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
							</tr>
							
							
							<logic:equal value="12862" name="xxdm">
							<tr>
								<th>
									��λ����
								</th>
								<td>
									<html:text property="gwmc" styleId="gwmc"/>
								</td>
								<th>
									������ʱ���
								</th>
								<td>
									<html:text property="gzzsj_ks" 
										style='width:50px' maxlength="5"  styleId="gzzsj_ks" onkeyup="value=value.replace(/[^\d]/g,'') "  /> (Сʱ)
										-
									<html:text property="gzzsj_js"
										style='width:50px' maxlength="5" styleId="gzzsj_js" onkeyup="value=value.replace(/[^\d]/g,'')" /> (Сʱ)
								</td>
								<th colspan="2"></th>
							</tr>
							</logic:equal>
							
							<logic:notEqual value="12862" name="xxdm">
							<tr>
								<th>
									��λ����
								</th>
								<td>
									<html:text property="gwmc" styleId="gwmc"/>
								</td>
								<th>
									������ʼʱ��
								</th>
								<td>
									<html:text property="gzkssj_ks"
										onclick="return showCalendar('gzkssj_ks','y-mm-dd');" style='width:80px'
										styleId="gzkssj_ks" onclick="return showCalendar('gzkssj_ks','y-mm-dd');"/>
										-
									<html:text property="gzkssj_js"
										onclick="return showCalendar('gzkssj_js','y-mm-dd');"  style='width:80px'
										styleId="gzkssj_js" onclick="return showCalendar('gzkssj_js','y-mm-dd');"/>
								</td>
								<th>
									��������ʱ��
								</th>
								<td>
									<html:text property="gzjssj_ks" 
										onclick="return showCalendar('gzjssj_ks','y-mm-dd');"  style='width:80px'
										styleId="gzjssj_ks" onclick="return showCalendar('gzjssj_ks','y-mm-dd');"/>
										-
									<html:text property="gzjssj_js" 
										onclick="return showCalendar('gzjssj_js','y-mm-dd');"  style='width:80px'
										styleId="gzjssj_js" onclick="return showCalendar('gzjssj_js','y-mm-dd');"/>
								</td>
							</tr>
							</logic:notEqual>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">��ʾ��������ͷ��������;˫��һ����¼���Բ鿴��ϸ��Ϣ��</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									&nbsp;
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="view('qgzxLsgwView.do')">
										<td>
											&nbsp;
											<input type="checkbox" name="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- ������ end-->
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>

			</div>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
