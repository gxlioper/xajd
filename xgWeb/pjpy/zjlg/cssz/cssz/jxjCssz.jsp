<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script language="javascript" src="js/moveDiv.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" scope="request" /></a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_fh" onclick="showTopWin('zjlgPjpy.do?method=cpzhf',800,520)">�����黮��</a></li>
						<li><a href="#" class="btn_sz" onclick="chkPriseBatForZjlg()">��������</a></li>
						<logic:notEqual name="userType" value="xy" scope="session">	
						<li><a href="#" class="btn_qb" onclick="showTopWin('zjlgPjpy.do?method=pjfs',450,400)">���۷�ʽ</a></li>
						<li><a href="#" class="btn_csh" onclick="priseDataInitForZjlg()">��ʼ������</a></li>
						<li><a href="#" class="btn_sq" onclick="viewTempDiv('����ѧ��','tmpdiv','400','',null)">����ѧ��</a></li>
						</logic:notEqual>								
					</ul>
				</div>
				</div>
			</logic:equal>
			<input type="hidden" id="isFdy" value=""/>
			<input type="hidden" id="zyV" name="zyV"/>
			<input type="hidden" id="bjV" name="bjV"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="method" name="method" value="cssz" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			
			<div class="comp_title">
				<ul>
				<logic:notEqual value="10657" name="xxdm">
					<li id="zxszy_sjd"  class="ha"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=jxj')">
							<span>��ѧ��</span>
						</a>
					</li>
					<li id="zxszy_dd"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=rych')">
							<span>�����ƺ�</span>
						</a>
					</li>
				</logic:notEqual>
				<logic:equal value="10657" name="xxdm">
					<li id="zxszy_sjd" class="ha"><a href="#" onclick="$('go').value='';refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=jxj')"">
							<span>��ѧ��</span>
						</a>
					</li>
					<li id="zxszy_dd"><a href="#" onclick="$('go').value='';refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=rych')">
							<span>�����ƺ�</span>
						</a>
					</li>
				</logic:equal>
					<li id="xlxhhd_hdxs"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=xjbj')">
							<span>�Ƚ��༶</span>
						</a>
					</li>
				</ul>
			</div>
			
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td><html:select property="xn" style="width:150px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
							<th>��ѧ��</th>
							<td><html:select property="jxjdm" style="width:150px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
								</html:select></td>
							<th>��ʾ��ʽ</th>
							<td><html:select property="bmlb" style="width:150px"
									styleId="dispFlag" onchange="zjlgChgDispConf('dispFlag');">
									<html:option value="xydm"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									<html:option value="cpzdm">������</html:option>
									<html:option value="bjdm">�༶</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width:150px" styleId="xy" 
										onchange="initBjList();initCpzList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<input type="hidden" name="xyV" value=""/>
							</td>
							<th>������</th>
							<td><html:select property="cpzdm" style="width:150px"  styleId="cpz"
									onchange="getCpzBjList();">
									<html:option value=""></html:option>
									<html:options collection="cpzList" property="cpzdm"
										labelProperty="cpzmc" />
									</html:select>
								<input type="hidden" name="cpzV" value=""/>
								<input type="hidden" name="zyV" value=""/>
							</td>	
							<th>�༶</th>
							<td><html:select property="bjdm" style="width:150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									<input type="hidden" name="bjV" value="" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<logic:equal name="userType" value="xy" scope="session">
								<input type="hidden" name="go" id="go" value="a" />
								<button type="button" id="search_go1" onclick="zjlgPriseConfJxj('/xgxt/zjlgPjpy.do');">
									��ѯ
								</button>
								
							    <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									����
								</button>
							</logic:equal>
							<logic:notEqual name="userType" value="xy" scope="session">
								<input type="hidden" name="go" id="go" value="go" />
								<button type="button" id="search_go1" onclick="zjlgPriseConfJxj('/xgxt/zjlgPjpy.do')">
									��ѯ
								</button>
								<button type="button" id="search_go" style="display:none" onclick="allNotEmpThenGo('/xgxt/zjlgPjpy.do');">
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									����
								</button>
							</logic:notEqual>
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
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Ե���������������ͷ��������</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('��δ���������������ɽ������������ܵ���������');return false;}dgrstz()">

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
						</logic:iterate>
					</table>
					
					<!--��ҳ��ʾ-->
			     	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
					<!--��ҳ��ʾ-->
			</logic:notEmpty>
			   
			<div id="tmpdiv1"></div>
			<div  id="tmpdiv" style="display:none">
			           <table width='300' class='formlist'>
			           <thead>
			           <tr height='10'>
			           <td colspan='2'>
			           <span>����ѧ��</span>
			           </td>
			           </tr>
			           </thead>
			           <tbody>
			           <tr height='30'>
			           <th align='right'>
			                                                         ��������ѧ�����Ϊ
			           </th>
			           <td align='left'>
			          <html:select property="jxjxn" style="width:150px" 
									styleId="select_jxjxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
			           </td>
			           </tr>
			           <tbody>
			           <tfoot><tr><td colspan='2' align='right'>
			           <button type="button" onclick=refreshForm('zjlgPjpy.do?method=tzjxjxn');>ȷ��</button>
			           </td></tr></tfoot>
			           </table>
			
			
			
			</div>
			</div>
			<logic:present name="initOK">
			<logic:equal name="initOK" value = "ok">
				<script language="javascript">
					alert("��ʼ���ɹ�");
				</script>
			</logic:equal>
			<logic:equal name="initOK" value = "no">
				<script language="javascript">
					alert("��ʼ��ʧ��");
				</script>
			</logic:equal>
			</logic:present>
			<logic:present name="updateOK">
			<logic:equal name="updateOK" value = "ok">
				<script language="javascript">
					alert("�޸ĳɹ�");
				</script>
			</logic:equal>
			<logic:equal name="updateOK" value = "no">
				<script language="javascript">
					alert("�޸�ʧ��");
				</script>
			</logic:equal>
			</logic:present>
			
			<logic:present name="message">
				<input type="hidden" name ="message" id ="message" value = "<bean:write name="message"/>" />
				<script language="javascript">
					alert($('message').value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript">

function openWins(){
	if (document.getElementById('jxjdm').value=='') {
		alert('��ѡ��ѧ�����!');
		return;
	} else {
		url = 'jxjrsdataexp.do?jxjdm=';
		url += document.getElementById('jxjdm').value;
		url += '&xydm=';
		url += document.getElementById('xy').value;
		url += '&zydm=';
		url += document.getElementById('zy').value;
		url += '&bjdm=';
		url += document.getElementById('bj').value;
		url += '&bmlb=';
		url += document.getElementById('dispFalg').value;
		url += '&nj=';
		url += document.getElementById('nj').value;
		window.open(url);
	} 
}
</script>
	</body>
</html>
