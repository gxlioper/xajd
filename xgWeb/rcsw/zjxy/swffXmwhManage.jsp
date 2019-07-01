<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript">	
	function saveLqtz(){
			refreshForm('/xgxt/zjxyRcsw.do?method=swffXmwhManage&doType=lqtz');
			hiddenMessage(true,true);
			BatAlert.showTips('���ڲ��������Ե�...');
	}
	
	
	</script>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name="viewName" id="viewName"
				value="view_rcsw_swffxmwh" />
			<input type="hidden" name="tableName" id="tableName"
				value="rcsw_swffxmwhb" />
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					<!-- ��ť -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#"
										onclick="showTopWin('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate',800,600);"
										class="btn_zj"> ���� </a>
								</li>
								<li>
									<a href="#"
										onclick="showInfo('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate','update',800,600);"
										class="btn_xg"> �޸� </a>
								</li>
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/zjxyRcsw.do?method=swffXmwhManage','del');"
										class="btn_sc"> ɾ�� </a>
								</li>
								<li>
									<a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a>
								</li>
								<li>
									<a href="#"
										onclick="expData('/xgxt/zjxyRcsw.do?method=expDate')"
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
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffXmwhManage');">
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
										<html:select property="queryequals_xn" style="" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="queryequals_xq" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										���
									</th>
									<td>
										<html:select property="queryequals_nd" style="" onchange="">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:select property="queryequals_xmlx" style=""
											styleId="xmlx">
											<html:options collection="xmlxList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:text property="querylike_xmmc" style="" styleId="xmmc"/>
									</td>
									<th>
										����ʼʱ��
									</th>
									<td>
										<html:text property="querygreaterequal_ffsj"
											styleId="querygreaterequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('querygreaterequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
										--
										<html:text property="querylessequal_ffsj"
											styleId="querylessequal_ffsj" onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('querylessequal_ffsj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
									</td>

								</tr>
								<tr>
									<th>
										�������ʱ��
									</th>
									<td colspan="5">
										<html:text property="kssj"  styleId="kssj"
											onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('kssj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
										--
										<html:text property="jssj"  styleId="jssj"
											 onblur="dateFormatChg(this)"
											style="cursor:hand;width:80px"
											onclick="return showCalendar('jssj','y-mm-dd');"
											onkeypress="return onlyNum(this,8)"  />
									</td>
									
								</tr>
							</tbody>
						</table>
					</div>
					<div class="formbox">
						<logic:empty name="rs">
							<h3 class="datetitle_01">
								<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font>
								</span>
							</h3>
						</logic:empty>
						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span> ��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
								</span>
							</h3>
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr>
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="showInfo('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate','view',800,600)">
											<td>
												<input type="checkbox" id="checkVal"
													name="primarykey_checkVal"
													<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<!--��ҳ��ʾ-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<!--��ҳ��ʾ-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
			<div id="divq" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<td>
									<span color="blue"> ��Ŀ������Ŀ���ƽ�Ӱ�챣��Ľ���� </span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									�Ƿ���Ҫ��ȡ֪ͨ
									<select name="lqtz" id="select_lqtz" style="width:100px">
										<option value="��Ҫ">
											��Ҫ
										</option>
										<option value="����Ҫ">
											����Ҫ
										</option>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td align='right'>
									<button type="button" onclick='saveLqtz()'>
										ȷ��
									</button>
									<button type="button" class='button2' onclick='hiddenMessage(true,true);'>
										�ر�
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				$("doType").value="";
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
				$("doType").value="";
			</script>
		</logic:equal>
	</body>
</html>
