<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/chgRychlist.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
	var Rows=new Array();	//����ѡ�е��ж���
	var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
	var cur_bgc = "#ffdead";//ѡ���б������ַ�����
	
	function queryOne() {
		if((curr_row == null) || (curr_row == "")){
			return false;
		}
		var xh = curr_row.getElementsByTagName("input")[2].value;
		var url = "/xgxt/stu_info_details.do?xh="+xh;
		showTopWin(url, 800, 600);
	}
		
	function rowOver(objTr) {//
		curr_row = objTr;
	}

	function rowOut(objTr) {//
		curr_row = null;
	}
	function xz_viewMore(curr_row) {
		var xxdm=document.all['xxdm2'].value;
		var xg_xxdm = document.getElementById("xxdm").value;
		if(xg_xxdm=="10402"){//����ʦ��
			viewMore('modi');
		} else if("no"==xxdm){	
			viewMore('view');
		} else if("10110"==xxdm){
			if("zhszcp"==document.all['realTable'].value){
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

	function getRqVal(name){
		var rq=document.getElementById(name).value;
		if (rq!=""){
			rqs=rq.split("-");
			rq="";
			for (var i=0;i<rqs.length;i++){
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
	</script>
	</head>
	<body onload="xyDisabled('xy');removeXnXq();bzrLoad();">
		<html:form action="/xljk_ytqk" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>
			<logic:notEqual value="stu" name="userType">
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
				<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
				
				<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">	
							<ul>
								<logic:notPresent name="showzdjs">
									<logic:equal name="act" value="party">
										<li><a href="#" class="btn_csh" onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');">���µ�ѧ����</a></li>
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<li><a href="#" class="btn_csh" onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/party_stuinfo.do');">���µ�ѧ����</a></li>
									</logic:equal>
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:notEqual value="12872" name="xxdm">
											<li><a href="#" class="btn_zj" onclick="viewMore('add')">����</a></li>
										</logic:notEqual>
									</logic:notEqual>
									<li><a href="#" class="btn_xg" onclick="
										<logic:equal value="10827" name="xxdm">
										<logic:equal value="view_xsrychb" name="tableName">updaterychxx('modi')</logic:equal>
										<logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual>
										</logic:equal>
									<logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>">�޸�</a></li>
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">ɾ��</a></li>
									</logic:notEqual>
								</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
									<li><a href="#" class="btn_qb" onclick="Alldel()">ȫ��ɾ��</a></li>
								</logic:notEqual>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>
								<logic:present name="showjsxx">
									<li><a href="#" class="btn_dyyl" onclick="window.open('zhszcp_print.do?xydm=' + document.getElementById('xy').value + '&bjdm=' + document.getElementById('bj').value + '&xn=' + document.getElementById('xn').value, '', '');">��ӡ</a></li>
								</logic:present>
								<logic:present name="showhzy">
									<logic:equal value="view_xsrychb" name="tableName">
											<li><a href="#" class="btn_dy" onclick="hzyrychprint();">�����ӡ</a></li>
									</logic:equal>
								</logic:present>					
							</ul>
						</div>
						</div>
					</logic:equal>
				<div class="searchtab">
					<table width="100%" class="">
						<logic:present name="showzbdx_xx">
							<input type="hidden" id="xxdm2" name="xxdm2" value="<bean:write name="xxdm" scope="request"/>" />
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
								<td><html:select property="nj" style="width:70px"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select></td>
									<%--										<logic:present name="zjujxjrych">--%>
								<logic:notEqual value="bjhj" name="bjhjType">
									<th>ѧ��</th>
									<td><html:text property="xh" style="width:120px"></html:text></td>
									<th>����</th>
									<td><html:text property="xm" style="width:85px"></html:text></td>
								</logic:notEqual>
									
								<th>���</th>
								<td><html:select property="dtsj" style="width:70px"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><logic:equal value="10827" name="xxdm">
										<html:select property="xydm" style="width:150px"
											styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										<html:select property="xydm" style="width:150px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual></td>
									
									<logic:notEqual value="xyhj" name="xyhjType">
										<th>רҵ</th>
										<td><html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
										<th>�༶</th>
										<td><html:select property="bjdm" style="width:180px" styleId="bj">
											<logic:notEqual value="yes" name="isBzr">
												<html:option value=""></html:option>
											</logic:notEqual>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select></td>
										<th></th>
									</logic:notEqual>
									<logic:equal value="xyhj" name="xyhjType"><th colspan="5"></th></logic:equal>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<input type="hidden" name="tab" id="tab" value="qtjxj" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/xljk_ytqk.do');">
										��ѯ
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										����
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
								<font color="blue"><logic:present name="qssj">(<bean:write
									name="qssj" />--</logic:present> <logic:present name="zzsj">
								<bean:write name="zzsj" /> Υ������)</logic:present>
									��ѯ���&nbsp;&nbsp;˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
								</span>
							</h3>
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="center" style="cursor: hand">
										<logic:notPresent name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notPresent>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
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
									<!-- ��ѧ�� -->
								</logic:iterate>
							</table>
							<logic:notEqual value="12872" name="xxdm">
								<!--��ҳ��ʾ-->
							     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
								<!--��ҳ��ʾ-->
								
								<br />
							</logic:notEqual>

					</logic:notEmpty>
					<br />
				</div>
			</logic:notEqual>

			<div id="tmpdiv"></div>
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
			<%--<script type="text/javascript">
				setPageSize();
			</script>
		--%></logic:equal>
	</body>
</html>
