<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type="text/javascript">
	function showlr(url,doType,w,h){
		if(doType == "update"){
			if(curr_row == null){
				alert('��ѡ��Ҫ¼������ݣ�');
				return false;
			}
		
		var pk = curr_row.getElementsByTagName('input')[0].value;
		url+="&doType="+doType;
		url+="&pk="+pk;
		showTopWin(url,w,h);
	}
	}
	
	function initCxf(){
		var xn = $('xn').value;
		var xq = $('xq').value;
		var xqmc = DWRUtil.getText('xq');
		if('' != xn && '' != xq){
			var message = "��Ҫ��ʼ��������Ϊ\nѧ�꣺"+xn+",ѧ��:"+xqmc+"\n��ȷ��Ҫ��ô����";
			if(confirm(message)){
				refreshForm('zjjtPjpy.do?method=initCxf&xn='+xn+'&xq='+xq);
			}
		}
	}
	
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjjtPjpy" method="post">
			<%@ include file="/pjpy/hiddenValue.jsp"%>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" />
					</a>
				</p>
			</div>

			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="mklx" value="lr">
								<li>
									<a href="#" class="btn_csh" title="��ʼ��ѧ����δ¼����зּ�¼��ѧ�����Ĳ��з�ΪĬ��ֵ"
										onclick='tipsWindown("ϵͳ��ʾ","id:cshDiv","350","165","true","","true","id")'>��ʼ�����з�</a>
								</li>
								<logic:equal name="userType" value="xx">
									<li>
										<a href="#" class="btn_xg"
											onclick="showTopWin('/xgxt/zjjtPjpy.do?method=szManage','400','250')">����</a>
									</li>
								</logic:equal>
								<logic:equal name="userType" value="admin">
									<li>
										<a href="#" class="btn_xg"
											onclick="showTopWin('/xgxt/zjjtPjpy.do?method=szManage','400','250')">����</a>
									</li>
								</logic:equal>
								<logic:equal name="bzrQx" value="false">
									<li>
										<a href="#" class="btn_zj"
											onclick="showlr('/xgxt/zjjtPjpy.do?method=cxfwhUpdate&mklx='+$('mklx').value,'update','1000','600')">¼��</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:equal name="mklx" value="cx">
								<li>
									<a href="#" class="btn_sc"
										onclick="showInfo('/xgxt/zjjtPjpy.do?method=cxfwhUpdate&mklx='+$('mklx').value,'update','1000','600')">ɾ��</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()">����</a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="dataExport()">����</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</div>
			</logic:equal>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" />
					</font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<div class="searchtab">
						<table width="100%" class="" border="0">
							<tbody>
								<!-- ¼�� -->
								<logic:equal name="mklx" value="cx">
									<tr>
										<th>
											ѧ��
										</th>
										<td>
											<html:select property="xn" style="" onchange="">
												<html:options collection="xnList" property="xn"
													labelProperty="xn" />
											</html:select>
										</td>
										<th>
											ѧ��
										</th>
										<td>
											<html:select property="xq" style="" onchange="">
												<html:option value=""></html:option>
												<html:options collection="xqList" property="xqdm"
													labelProperty="xqmc" />
											</html:select>
										</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</logic:equal>
								<!-- ¼�� end-->
								<tr>
									<th>
										�꼶
									</th>
									<td>
										<html:select property="nj" style=""
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" styleId="xh" style="" maxlength="20" />
									</td>
									<th>
										����
									</th>
									<td colspan="3">
										<html:text property="xm" styleId="xm" style="" maxlength="20" />
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="xydm" style="width:170px" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										רҵ
									</th>
									<td>
										<html:select property="zydm" style="width:170px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										�༶
									</th>
									<td colspan="3">
										<html:select property="bjdm" style="width:170px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										У��
									</th>
									<td>
										<html:select property="xqdm" style="" styleId="xqdm"
											onchange="setLdList()">
											<html:options collection="xqdmList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										¥��
									</th>
									<td>
										<html:select property="lddm" style="" styleId="lddm"
											onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										��������
									</th>
									<td>
										<html:select property="cs" style="" styleId="cs"
											onchange="setQsList();">
											<html:options collection="csList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
									<th>
										���Һ�
									</th>
									<td>
										<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm"
												labelProperty="mc" />
										</html:select>
									</td>
								</tr>
								<logic:equal name="mklx" value="cx">
								<tr>
									<th>
										��ʼ����
									</th>
									<td colspan="2">
										<html:text readonly="readonly"
										property="ksrq" styleId="ksrq"
										onclick="return showCalendar('ksrq','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
									</td>
									<th>
										��������
									</th>
									<td colspan="2">
										<html:text readonly="readonly"
										property="jsrq" styleId="jsrq"
										onclick="return showCalendar('jsrq','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
									</td>
									<th>
										
									</th>
									<td>
										
									</td>
								</tr>
								</logic:equal>
							</tbody>

							<tfoot>
								<tr>
									<td colspan="8">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<!-- ¼�� -->
											<logic:equal name="mklx" value="lr">
												<button id="search_go"
													onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfwhLr');">
													��ѯ
												</button>
											</logic:equal>
											<!-- ��ѯ -->
											<logic:equal name="mklx" value="cx">
												<button id="search_go"
													onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfwhCx');">
													��ѯ
												</button>
											</logic:equal>
											<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												�� ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
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
							<div class="con_overlfow">
								<table width="100%" id="rsTable" class="dateline tablenowrap">
									<thead>
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="showInfo('/xgxt/zjjtPjpy.do?method=cxfwhUpdate&mklx='+$('mklx').value,'view','1000','600')">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<td align="center">
													<bean:write name="v" />
													<input type="checkbox" id="checkVal" name="checkVal"
														style="display : none"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</div>
							<!--��ҳ��ʾ-->
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
							<!--��ҳ��ʾ-->
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
						</logic:notEmpty>
					</div>
			</logic:empty>

			<div id="cshDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ѡ���ʼ��ѧ�ꡢѧ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>ѧ��
								</th>
								<td>
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
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
										<button name="ȷ��" onclick="initCxf()">
											ȷ ��
										</button>
										<button name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>


	<div style="height:200px"></div>

		</html:form>


		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
	</body>
</html>
