<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript">
		function setKgzt(value,num){
			var id = "kgzt"+num;
			$(id).value = value;
		}
		
		function saveKgzt(){
			if (confirm('ȷ������״̬��')){
				saveUpdate('/xgxt/commXszz.do?method=xmwhManage&doType=save','');
			}
		}
		
		//ǰ����������
		function goRssz(){
			var url = "xszz_xgsz_rssz.do";	
				url+="?xmdm="+$("savedXmdm").value;	
				url+="&kzjb="+$("savedKzjb").value;
											
			showWaitingDiv("30000");											
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<html:hidden property="iskns"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="savedXmdm" id="savedXmdm" value=""/>
			<input type="hidden" name="savedKzjb" id="savedKzjb" value=""/>
			<button type="button"   id="forward" onclick="goRssz()" style="display: none">��ת</button>
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<!-- �������϶��������ģ���������ӡ�ɾ�����������ܰ�ť -->
							<logic:notEqual value="true" name="knsdl">
								<li>
									<a href="#" 
										onclick="showTopWin('/xgxt/commXszz.do?method=xmwhUpdate','800','600');"
										class="btn_zj">����</a>
								</li>
							</logic:notEqual>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/commXszz.do?method=xmwhUpdate','update','800','600')"
									class="btn_xg">�޸�</a>
							</li>
							<logic:notEqual value="true" name="knsdl">
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/commXszz.do?method=xmwhManage','xmDel')"
										class="btn_sc">ɾ��</a>
								</li>
							</logic:notEqual>
							<li>
								<a href="#" 
									onclick="saveKgzt()" 
									class="btn_ccg">���濪��</a>
							</li>
							<logic:notEqual value="true" name="knsdl">
								<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
								<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<!-- ��ť end-->
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="bz">
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;��ǰѧ�꣺${xn}&nbsp;&nbsp;&nbsp;&nbsp;
											��ǰѧ�ڣ�${xq}&nbsp;&nbsp;&nbsp;&nbsp;��ǰ��ȣ�${nd }</font>
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button"  class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=xmwhManage');">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"   class="btn_cz" id="btn_cz" 
											onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�漰���
								</th>
								<td>
									<html:select property="queryequals_sfje" style="width:100px" styleId="sfje" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sfjeList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									�Ƿ�ּ�
								</th>
								<td>
									<html:select property="queryequals_sffj" style="width:100px" styleId="sffj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sffjList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									�������
								</th>
								<td>
									<html:select property="queryequals_jelx" style="width:100px" styleId="jelx" onchange="">
										<html:option value=""></html:option>
										<html:options collection="jelxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��˼���
								</th>
								<td>
									<html:select property="queryequals_shjb" style="width:100px" styleId="shjb" onchange="">
										<html:option value=""></html:option>
										<html:options collection="shjbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							
							<!-- �ڶ��� -->
							<!-- ������ -->
							<tr>
								<th>
									��������
								</th>
								<td>
									<html:select property="queryequals_rskz" style="width:100px" styleId="rskz" onchange="">
										<html:option value=""></html:option>
										<html:options collection="rsjbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="queryequals_sqzq" style="width:100px" styleId="sqzq" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sqzqList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:select property="queryequals_pdsj" style="width:100px" styleId="pdsj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="pdsjList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<td colspan="2"></td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									��Ŀ���
								</th>
								<td>
									<html:select property="queryequals_xmlb" style="width:100px" styleId="xmlb" onchange="chXmlb()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��Ŀ
								</th>
								<td>
									<html:select property="queryequals_xmdm" style="width: 100px" styleId="xmdm">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm" labelProperty="xmmc" />
									</html:select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:text property="querylike_xmmc" onkeypress="if(pressEnter(event)){return false;}" styleId="xmmc" style="width: 100px" maxlength="20"/>
									<html:text property="xh" styleId="xh" style="display: none"/>
								</td>
								<th>
									Ĭ����Ŀ
								</th>
								<td>
									<html:select property="queryequals_mrxm" style="width:100px" styleId="mrxm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ.</font>
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<div class="con_overlfow">
						 <table summary="" id="rsTable" class="dateline tablenowrap" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr>
									<td width="5%" align="center">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" 
										style="cursor:hand" 
										ondblclick="showInfo('/xgxt/commXszz.do?method=xmwhUpdate','view','800','600')">
										<!-- checkbox -->								
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align="left">
												<input type="hidden" name="zzxmdm" value="${v }"/>
												<logic:iterate id="mrxm" name="s" offset="1" length="1">
													<!-- Ĭ����Ŀ -->
													<logic:equal name="mrxm" value="��">
														<input type="checkbox" id="checkVal"  name="primarykey_checkVal" disabled="disabled"/>
													</logic:equal>
													<!-- Ĭ����Ŀ end-->
													<!-- ��Ĭ����Ŀ -->
													<logic:notEqual name="mrxm" value="��">
														<input type="checkbox" id="checkVal"  name="primarykey_checkVal"  value="<bean:write name="v"/>"/>
													</logic:notEqual>
													<!-- ��Ĭ����Ŀ end-->
												</logic:iterate>		
											</td>
										</logic:iterate>
										<!-- ��Ŀ��Ϣ -->		
										<logic:iterate id="v" name="s" offset="2" length="10">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<!-- ���� -->		
										<logic:iterate id="v" name="s" offset="12" length="1">
											<td align="left" nowrap>
												<logic:iterate name="kgztList" id="kg">
													${kg.en }
													<logic:equal name="kg" property="en" value="${v }">
														<input type="radio" value="${kg.en }" name="kg${index }" checked="checked" onclick="setKgzt(this.value,${index })"/>
														<br/>
													</logic:equal>
													<logic:notEqual name="kg" property="en" value="${v }">
														<input type="radio" value="${kg.en }" name="kg${index }" onclick="setKgzt(this.value,${index })"/>
														<br/>
													</logic:notEqual>
												</logic:iterate>
												<input type="hidden" name="zzxmkg" id="kgzt${index }" value="${v }"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>		
							<!--���� end -->
						</table>
						</div>
					<!--��ҳ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
					<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>