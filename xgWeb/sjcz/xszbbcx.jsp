<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/chgRychlist.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
		function queryOne() {
	if((curr_row == null) || (curr_row == "")){
		return false;
	}
	var xh = curr_row.getElementsByTagName("input")[2].value;
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
function rowOver(objTr) {//
	curr_row = objTr;
}

function rowOut(objTr) {//
	curr_row = null;
}
			function xz_viewMore(curr_row)
			{
				var xxdm=document.all['xxdm2'].value;
				var xg_xxdm = document.getElementById("xxdm").value;
				if(xg_xxdm=="10402"){//����ʦ��
					viewMore('modi');
				} else if("no"==xxdm)
				{	
					viewMore('view');
				} else if("10110"==xxdm)
				{
					if("zhszcp"==document.all['realTable'].value)
					{
					var xn=curr_row.cells[1].innerText;
					var nd=curr_row.cells[0].innerText;
					var xh=curr_row.cells[3].innerText;
				    var url='/xgxt/pjpy_zbdx_weihu_one.do?doType=view';
				    url=url+"&xn="+xn+"&nd="+nd+"&xh="+xh;
				    showTopWin(url,'550','500');
				} else {
				    viewMore('view');
				}
			}
			}
			function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
			function chgrychlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgRychlist.xyRychList(xydm,function(data) {
					DWRUtil.removeAllOptions('rychdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('rychdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].rychdm,labelText:data[i].rychmc}];
					DWRUtil.addOptions('rychdm',o,'id','labelText');
					}
		});
	}  		
	function chgJxjlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgJxjlist.xyJxjList(xydm,function(data) {
					DWRUtil.removeAllOptions('jxjdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].jxjdm,labelText:data[i].jxjmc}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					}
		});
		}
		function xsbbcx(){
			var cx = document.getElementById("cxbb").value;
			if(cx == 1){
				document.forms[0].action = "/xgxt/data_search.do?act=studentPaperAgain&bbcx=bbcx&go=go";
				document.forms[0].submit();
			}
			if(cx == 2){
				document.forms[0].action = "/xgxt/data_search.do?act=trainCheapAgain&bbcx=bbcx&go=go";
				document.forms[0].submit();
			}
			if(cx == 3){
				document.forms[0].action = "/xgxt/data_search.do?act=oneCardAgain&bbcx=bbcx&go=go";
				document.forms[0].submit();
			}
			if(cx == 4){
				document.forms[0].action = "/xgxt/data_search.do?act=schoolBadgeAgain&bbcx=bbcx&go=go";
				document.forms[0].submit();
			}
	}

		</script>

	</head>
	<body onload="xyDisabled('xy');removeXnXq();bzrLoad();">
		

		<html:form action="/data_search" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="title" style="display: none">
				<div class="title_img" id="title_m">
					���ĵ�ǰλ��:
					�ճ����� - ���֤�������ѯ
				</div>
			</div>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ճ����� - ���֤�������ѯ</a>
				</p>
			</div>
			
			<logic:equal value="view_xslxfszsxx" name="tableName">
				<logic:equal value="stu" name="userType">
					��ҳ��ֻ��ѧУ��<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</logic:equal>
			</logic:equal>
			<logic:notEqual value="stu" name="userType">
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
						<logic:notEqual value="10497" name="xxdm">
							<logic:equal value="12872" name="xxdm">
								<logic:notEqual value="xy" name="userType">
									<li><a href="#" class="btn_dr" onclick="impAndChkData()">��������</a></li>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="12872" name="xxdm">
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">��������</a></li>
							</logic:notEqual>
						</logic:notEqual>
							<li><a href="#" class="btn_dc" onclick="dataExport()">��������</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
				<div class="rightcontent">
						<logic:present name="showhzyjx">
							<table width="100%" class="tbstyle">
								<html:radio property="grhj" value="grhj" styleId="grhj"
									onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">���˻�</html:radio>
								<html:radio property="grhj" value="bjhj" styleId="bjhj"
									onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')">�༶��</html:radio>
								<html:radio property="grhj" value="yxhj" styleId="yxhj"
									onclick="refreshForm('/xgxt/data_search.do?act=trainPrise')"><bean:message key="lable.xsgzyxpzxy" />��</html:radio>
							</table>
						</logic:present>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<input type="hidden" id="userType" name="userType"
							value="<bean:write name="userType" scope="request"/>" />
						<input type="hidden" id="tableName" name="tableName"
							value="<bean:write name="tableName" scope="request"/>" />
						<input type="hidden" id="act" name="act"
							value="<bean:write name="act" scope="request"/>" />
						<input type="hidden" id="realTable" name="realTable"
							value="<bean:write name="realTable" scope="request"/>" />
						<input type="hidden" id="pk" name="pk"
							value="<bean:write name="pk" scope="request"/>" />
						<input type="hidden" id="xxdm" name="xxdm"
							value="<bean:write name="xxdm" scope="session"/>" />
						<input type="hidden" id="dxq" name="dxq"
							value="<bean:write name="writeAble" scope="request"/>" />
						<input type="hidden" id="isBzr" name="isBzr"
							value="<bean:write name="isBzr" scope="request"/>" />
						<input type="hidden" id="stab" name="stab" value="stab" />
						<input type="hidden" id="userName" name="userName"
							value="<bean:write name="userName" scope="session"/>" />
							<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
							<input type="hidden" name="sfxxcv" id="sfxxcv" value="123"/>
						
						<div class="searchtab">
						<table width="100%" class="" border="0">
							<logic:present name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2"
									value="<bean:write name="xxdm" scope="request"/>" />
							</logic:present>
							<logic:notPresent name="showzbdx_xx">
								<input type="hidden" id="xxdm2" name="xxdm2" value="no" />
							</logic:notPresent>
							<logic:present name="sfxfzrx">
								<input type="hidden" id="sfxfzrx" name="sfxfzrx"
									value="<bean:write name="sfxfzrx" scope="request"/>" />
							</logic:present>
							<tbody>
								<tr>
									<th>�꼶</th>
									<td><html:select property="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select></td>
									<th>ѧ��</th>
									<td>
										<logic:equal name="userOnline" value="student">
											<html:text property="xh" maxlength="20" style="width:80px" readonly="true" value="${userName}"></html:text>
										</logic:equal>
										<logic:notEqual name="userOnline" value="student">
											<html:text property="xh" maxlength="20" style="width:80px"></html:text>
										</logic:notEqual>
									</td>
									<th>����</th>
									<td><html:text property="xm" maxlength="20" style="width:80px"></html:text>
									</td>
								</tr>
								<tr>
									<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select property="xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select></td>
								<th>רҵ</th>
								<td><html:select property="zydm" onchange="initBjList()"  styleId="zy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>�༶</th>
								<td><html:select property="bjdm"  styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
								</tr>
								<tr>
									<th>ѧ��</th>
									<td><html:select property="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select></td>
									<th>���</th>
									<td><html:select property="nd">
										<html:options collection="ndList" property="nd" labelProperty="nd"/>
									</html:select></td>
									<th>��ѯ��Ŀ</th>
									<td><html:select property="cxbb" style="width:70px" styleId="nj">
											<html:option value="1">ѧ��֤</html:option>
											<html:option value="2">���Żݿ�</html:option>
											<html:option value="3">һ��ͨ</html:option>
											<html:option value="4">У��</html:option>
										</html:select>
										<html:hidden property="xh" value="${userName}" />
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<input type="hidden" name="tab" id="tab" value="qtjxj" />
								<button type="button"  id="search_go"
									onclick="xsbbcx();">
									��ѯ
								</button>
								<logic:present name="showblsz">
									<td width="10" rowspan="3" align="center" valign="middle">
										<button type="button" onclick="showTopWin('/xgxt/zhszcpblsz.do',750,250)">
											��������
										</button>
									</td>
								</logic:present>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
						    <span>
						    	��ѯ���&nbsp;&nbsp;
									<font color="red">δ�ҵ��κμ�¼��</font> 
						    </span>
						    </h3>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<h3 class="datetitle_01">
								<span>
									��ѯ���&nbsp;&nbsp;<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> Υ������)</logic:present>��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
								</span>
							</h3>

							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="center" style="cursor: hand">
										<logic:notPresent name="xsjxjb">
										<logic:notEqual value="10290" name="xxdm">
											<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											</logic:notEqual>
											<logic:equal value="10290" name="xxdm">
											<logic:iterate id="tit" name="topTr" offset="1" length="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											<logic:iterate id="tit" name="topTr" offset="4" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											<logic:iterate id="tit" name="topTr" offset="6" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											<logic:iterate id="tit" name="topTr" offset="8" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											<logic:iterate id="tit" name="topTr" offset="10" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>									
											</logic:iterate>
											</logic:equal>
										</logic:notPresent>
										<logic:present name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:present>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<logic:notPresent name="xsjxjb">
										<logic:present name="showjsxx">
											<tr align="center" onclick="rowMoreClick(this,'',event);"
												style="cursor: hand" ondblclick="ShowStuCjInfo(this)">
												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="2">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:present>
										<logic:notPresent name="showjsxx">

											<logic:equal value="12872" name="xxdm">
												<logic:equal value="xsrychb" name="realTable">
													<tr align="center" onclick="rowMoreClick(this,'',event);"
														style="cursor: hand" ondblclick="hzzyrychmodi()">
														<td>
															<logic:iterate id="v" name="s" offset="0" length="1">
																<input type="hidden" value="<bean:write name="v"/>" />
															</logic:iterate>
															<logic:iterate id="v" name="s" offset="1" length="1">
																<bean:write name="v" />
															</logic:iterate>
														</td>
														<logic:iterate id="v" name="s" offset="2">
															<td>
																<bean:write name="v" />
															</td>
														</logic:iterate>
													</tr>
												</logic:equal>
												<logic:notEqual value="xsrychb" name="realTable">
													<tr align="center" onclick="rowMoreClick(this,'',event);"
														style="cursor: hand" ondblclick="xz_viewMore(this)">
														<td>
															<logic:iterate id="v" name="s" offset="0" length="1">
																<input type="hidden" value="<bean:write name="v"/>" />
															</logic:iterate>
															<logic:iterate id="v" name="s" offset="1" length="1">
																<bean:write name="v" />
															</logic:iterate>
														</td>
														<logic:iterate id="v" name="s" offset="2">
															<td>
																<bean:write name="v" />
															</td>
														</logic:iterate>
													</tr>
												</logic:notEqual>
											</logic:equal>

											<logic:notEqual value="12872" name="xxdm">
												<logic:notEqual value="10290" name="xxdm">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
												</logic:notEqual>
												<logic:equal value="10290" name="xxdm">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2" length="2">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="5" length="1">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="8" length="1">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="10" length="1">
														<td>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
												</logic:equal>
											</logic:notEqual>
										</logic:notPresent>
									</logic:notPresent>
									<!-- ��ѧ�� -->
									<logic:present name="xsjxjb">
										<logic:equal value="10290" name="xxdm">
											<tr align="center" onclick="rowMoreClick(this,'',event);"
												onmouseover="rowOver(this);" onmouseout="rowOut();"
												style="cursor: hand"
												ondblclick="<logic:equal value="12872" name="xxdm">hzyjxjmodi()</logic:equal><logic:notEqual value="12872" name="xxdm">viewMore2('modi')</logic:notEqual>">

												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<a href="#" onclick="queryOne()"> <logic:iterate id="v"
															name="s" offset="2" length="1">
															<bean:write name="v" />
														</logic:iterate> <input type="hidden" value="<bean:write name="v"/>" /> </a>
												</td>
												<logic:iterate id="v" name="s" offset="3">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:equal>
										<logic:notEqual value="10290" name="xxdm">
											<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
												ondblclick="<logic:equal value="12872" name="xxdm">hzyjxjmodi()</logic:equal><logic:notEqual value="12872" name="xxdm">viewMore2('modi')</logic:notEqual>">

												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>

													<logic:iterate id="v" name="s" offset="2" length="1">
														<bean:write name="v" />
													</logic:iterate>

												</td>
												<logic:iterate id="v" name="s" offset="3">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:notEqual>
									</logic:present>
								</logic:iterate>
							</table>
							
							
							<logic:notEqual value="12872" name="xxdm">
								<!--��ҳ��ʾ-->
							     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
								<!--��ҳ��ʾ-->
							</logic:notEqual>
							<logic:equal value="12872" name="xxdm">
								<logic:notEqual value="xsjxjb" name="realTable">
									<logic:notEqual value="xsrychb" name="realTable">
										<!--��ҳ��ʾ-->
									     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
										<!--��ҳ��ʾ-->
									</logic:notEqual>
								</logic:notEqual>
							</logic:equal>
						</logic:notEmpty>
						</div>
						
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
								<logic:equal value="wjcfb" name="realTable">
								<logic:notEqual value="10856" name="xxdm">
									<button type="button" class="button2"
									onclick="showTopWin('wjcflxcktj.do',730,600)">
										��У�쿴����
									</button>&nbsp;
									</logic:notEqual>
									<button type="button" class="button2" onclick="wjsjzy('wjcf_shgc_lssjzy.do')"
										style="">
										��ҵ������ת��
									</button>
								</logic:equal>
								<logic:present name="showzdjs">
									<button type="button" class="button2"
										onclick="AutoAccountCj('/xgxt/AutoAccount.do')"
										style="width: 80px">
										�Զ�����
									</button>
								</logic:present>
								&nbsp;
								<logic:notPresent name="showzdjs">
									<logic:equal name="act" value="party">
										<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="if(curr_row == null){alert('��ѡ��Ҫ��ת��ѧ��!');return false;}else{showTopWin('/xgxt/dtjs_zjcm.do?method=zzgx&rdsj='+curr_row.cells[7].innerText+'&pk='+curr_row.cells[3].innerText,600,480)}"
												style="width: 90px">
												��֯��ϵ��ת
											</button>
										</logic:equal>
										<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											���µ�ѧ����
										</button>
										&nbsp;
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=zzYb&go=go',800,600);"
												style="width: 90px">
												��Աת��
											</button>
										</logic:equal>
										<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											���µ�ѧ����
										</button>
										&nbsp;
									</logic:equal>
									<%--begin�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:equal name="act" value="active">
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=fzdxAll&go=go',800,600);" style="width:90px"> 
												��չ��������
											</button>
										</logic:equal>
									</logic:equal>
							</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
<%--									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">--%>
<%--										ȫ��ɾ��--%>
<%--									</button>--%>
							&nbsp;
							</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="view_xslxfszsxx" name="tableName">
								<logic:equal value="yes" name="writeAble" scope="request">
									<%--�人����ѧ--%>
									<logic:equal value="10497" name="xxdm">
										<button type="button" class="button2" onclick="impAndChkData()"
											style="width: 80px">
											<!-- impAndChkData(); -->
											��������
										</button>
									</logic:equal>
									<%--end�人����ѧ--%>

<%--									<logic:notEqual value="10497" name="xxdm">--%>
<%--										<logic:equal value="12872" name="xxdm">--%>
<%--											<logic:notEqual value="xy" name="userType">--%>
<%--												<button type="button" class="button2" onclick="impAndChkData()"--%>
<%--													style="width: 80px">--%>
<%--													��������--%>
<%--												</button>--%>
<%--											</logic:notEqual>--%>
<%--										</logic:equal>--%>
<%--										<logic:notEqual value="12872" name="xxdm">--%>
<%--											<button type="button" class="button2" onclick="impAndChkData()"--%>
<%--												style="width: 80px">--%>
<%--												��������--%>
<%--											</button>--%>
<%--										</logic:notEqual>--%>
										<!-- "impAndChkData();" -->

<%--									</logic:notEqual>									--%>
								
<%--									<button type="button" class="button2" onclick="dataExport()"--%>
<%--										style="width: 80px">--%>
<%--										��������--%>
<%--									</button>--%>
								</logic:equal>
								<!-- ��дȨ -->
								<logic:present name="showjsxx">
								&nbsp;
								<button type="button" class="button2"
										onclick="window.open('zhszcp_print.do?xydm=' + document.getElementById('xy').value + '&bjdm=' + document.getElementById('bj').value + '&xn=' + document.getElementById('xn').value, '', '');"
										style="width: 80px">
										��ӡ
									</button>
								</logic:present>
								<logic:present name="isCSMZ">
									<logic:equal value="yes" name="isCSMZ">
									&nbsp;
									</logic:equal>
								</logic:present>
								<logic:present name="showhzy">
									<logic:equal value="view_xsrychb" name="tableName">
										&nbsp;
									<button type="button" id="btn_print" class="button2" style=""
											onclick="hzyrychprint();">
											�����ӡ
										</button>
									</logic:equal>
								</logic:present>
								<logic:equal value="10878" name="xxdm">
									<logic:equal value="view_zhszcp" name="tableName">
										<button type="button" class="button2" onclick="impAndChkDataByahjg()"
											style="width: 110px">
											�������ݵ���
										</button>
									&nbsp;
									</logic:equal>
								</logic:equal>
							</logic:notEqual>
						</div>
					</center>
				</div>
			</logic:notEqual>

			<div id="tmpdiv"></div>
			<%--�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="dydate">
				<logic:equal name="dydate" value="no">
					<script language="javascript">
      					alert("��ѧ��δ��Ԥ����");
	  				</script>
				</logic:equal>
				<logic:equal name="dydate" value="yes">
					<script language="javascript">
      					alert("ת���ɹ�");
	  				</script>
				</logic:equal>
			</logic:present>
			<%--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />--%>
			<%--����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:present name="autoCj">
				<logic:equal name="autoCj" value="ok">
					<script language="javascript">
      						alert("�Զ�������ɣ�");
	  					</script>
				</logic:equal>
				<logic:equal name="autoCj" value="no">
					<script language="javascript">
	  						alert("�Զ�����ʧ��! ");
	  					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="NoStu">
			<script language="javascript">
	  				alert("�˲���ֻ���ѧ�����˻�!");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">

					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('��ѡ��Ҫ��ӡ�������У�');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
					function hzyprint() {
				     	if (curr_row==null || curr_row=='') 
				     	{
				     		alert('��ѡ��Ҫ��ӡ�������ݣ�����һ�м���!');
				     		return;
				     	}
				     	 else {
				     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
				     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				     	 	window.open(url);
				     	 }
				     }
				     function hzyjxjmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
				     function wjsjzy(url) {
				     	var RowsStr="!!SplitOneSplit!!";   
				     	if (Rows.length==0) {
				     		alert('��ѡ��Ҫ������������,��סCtrl�����Զ�ѡ!');
				     		return;
				     	}
				     	if (confirm('ȷ��Ҫ��ѡ�������ת����ʷ��Ϣ����?')) {
				     		for (i=0; i<Rows.length; i++){ 										//�����ַ���
    							RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
							}
							showTips('���������У���ȴ�......');
							refreshForm(url+"?pkValue="+RowsStr);
				     	}
				     	return;
				     }
		</script>
		<logic:equal value="10290" name="xxdm">
			<script language="JavaScript">
				setPageSize();
			</script>
		</logic:equal>
	</body>
</html>
