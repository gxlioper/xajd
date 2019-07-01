<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
	</head>
	<body onload="">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
	
		<html:form action="/wjdc" method="post">
			<!-- ������ -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- ������ end-->
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_zj" onclick="addWj();" id="btn_zj">����</a>
							</li>
							<li>
								<a href="#" class="btn_xg" onclick="showWj('update')" id="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="delWj()" id="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#" class="btn_dr" onclick="impAndChkData()" id="btn_dr">����</a>
							</li>
							<li>
								<a href="#" class="btn_dc" onclick="wjcfDataExport('nbcsPjpy.do?method=wjManage&doType=exp')" id="btn_dc">����</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="searchWj()">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>ѧ��</th>
								<td>
									<html:select property="queryequals_xn" style="width:100px" styleId="xn" onchange="getWjList()">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>���</th>
								<td>
									<html:select property="queryequals_nd" style="width:100px" styleId="nd" onchange="getWjList()">
										<html:options collection="ndList" property="nd" labelProperty="nd" />
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:select property="queryequals_xq" style="width:100px" styleId="xq" onchange="getWjList()">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>ģ������</th>
								<td>
									<html:hidden property="queryequals_mklx" />
									<html:select property="queryequals_mklx" style="width:100px" styleId="mklx" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="mklxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>�Ƿ���</th>
								<td>
									<html:select property="queryequals_sfkq" style="width:100px" styleId="sfkq">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>��ɺ�ɷ��޸�</th>
								<td>
									<html:select property="queryequals_kyxg" style="width:100px" styleId="kyxg">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>�𰸿���Ϊ��</th>
								<td>
									<html:select property="queryequals_dawk" style="width:100px" styleId="dawk">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>�ʾ�</th>
								<td>
									<html:select property="queryequals_id" style="width:100px" styleId="id">
										<html:options collection="wjList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>�����Ϣ</th>
								<td>
									<html:select property="queryequals_zjxx" style="width:100px" styleId="zjxx">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="zzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>����ʱ��</th>
								<td colspan="5">
									<html:text property="querygreaterequal_jlsj" styleId="querygreaterequal_jlsj"
											onblur="dateFormatChg(this)" styleClass="jssj"
											onclick="return showCalendar('querygreaterequal_jlsj','y-mm-dd');"/>	
										��
										<html:text property="querylessequal_jlsj" styleId="querylessequal_jlsj"
											onblur="dateFormatChg(this)" styleClass="jssj"
											onclick="return showCalendar('querylessequal_jlsj','y-mm-dd');"/>	
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> 
					</span>
				</h3>
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
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
							<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="showWj('view')">								
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="center">
												<input type="checkbox" id="checkVal" 
												   name="primarykey_checkVal"  
												   value="<bean:write name="v"/>"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
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
					page="/sjcz/turnpage.jsp?form=wjdcForm"></jsp:include>
				<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			</logic:empty>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>
