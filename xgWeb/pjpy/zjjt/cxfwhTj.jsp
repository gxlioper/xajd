<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript">	
	function selectLb(){
	
		var lbid = "lb";
		var xxid = "xx";
		
		var tableName = "zjjt_cxf_dj3"; 
		var dm = "dm"; 
		var mc = "mc";
		var msg = "";
		var pk = "ejdm";
		var pkValue = $(lbid).value;
		
		if(pkValue == ""){
			pk = "";
		}
		
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(xxid);
				DWRUtil.addOptions(xxid,data,"dm","mc");
				$(xxid).options[0].value = "";
			}
		});
	}
	
	function printCxfTj(){
	
		var url = "/xgxt/zjjtPjpy.do?method=cxfwhTj&doType=print";
			
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}

	function setTjfs(){
		var tjfs = $('tjfs1');
		if(tjfs.checked == true){
			$('xq').value = "";
			$('xq').disabled = "disabled";
		}else {
			$('xq').disabled = "";
		}
	}
	
function resetSearchTj() {

	var input = document.getElementsByTagName('input');
	var select = document.getElementsByTagName('select');
				
	for (var i = 0;i<input.length;i++) {
		if (input[i].type != 'hidden' && input[i].disabled != true 
			&& input[i].type != 'checkbox' 
			&& !input[i].readOnly
			&& input[i].id !="pageno"
			&& input[i].id !="pagesize")
			input[i].value='';
	}
	for (var i = 0;i<select.length;i++) {
		if (select[i].disabled != true && select[i].options(0).value == "")
		select[i].value='';
	}
	
	$("tjfs2").checked = true;
	$("tjfs2").onclick();
}
	</script>
	</head>
	
	
	<body onload="xyDisabled('xy');setTjfs();">
		<html:form action="/zjjtPjpy" method="post">
		<%@ include file="/pjpy/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_dc" onclick="printCxfTj()">��������</a></li>					
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<div class="searchtab">
						<table width="100%" class="" border="0">
							<tbody>
								<tr>
									<th>�꼶</th>
									<td><html:select property="nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</td>
									<th>ѧ��</th>
									<td><html:select property="xn" style="" onchange="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select></td>
									<th>ѧ��</th>
									<td><html:select property="xq" styleId="xq" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select></td>
									<th>ͳ�Ʒ�ʽ</th>
									<td>ѧ��<html:radio styleId="tjfs1" property="tjfs" value="xn" onclick="setTjfs();"/>
										ѧ��<html:radio styleId="tjfs2" property="tjfs" value="xq" onclick="setTjfs();"/>
									</td>
									</tr>
									<tr>
									<th>ѧ��</th>
									<td><html:text property="xh" styleId="xh" style="" maxlength="20"/></td>
									<th>����</th>
									<td><html:text property="xm" styleId="xm" style="" maxlength="20"/>
									</td>
									<th></th>
									<td></td>
									<th></th><td></td>
								</tr>
								<tr>
									<th><bean:message key="lable.xsgzyxpzxy" /></th>
									<td><html:select property="xydm" style="width : 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</td>
									<th>רҵ</th>
									<td><html:select property="zydm" style="width : 150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select></td>
									<th>�༶</th>
									<td><html:select property="bjdm" style="width : 150px" styleId="bj">
											<html:option value=""></html:option>
											<logic:present name="bjList">
												<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
											</logic:present>
										</html:select>
									</td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>У��</th>
									<td><html:select property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
											<html:options collection="xqdmList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									<th>¥��</th>
									<td><html:select property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									<th>��������</th>
									<td><html:select property="cs" style="" styleId="cs" onchange="setQsList();">
											<html:options collection="csList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
									<th>���Һ�</th>
									<td><html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm" labelProperty="mc" />
										</html:select>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="8">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button id="search_go" onclick="allNotEmpThenGo('/xgxt/zjjtPjpy.do?method=cxfwhTj');">
									�� ѯ
									</button>
									 <button class="btn_cz" id="btn_cz" onclick="resetSearchTj()">
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
									��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
								</span>
							</h3>
							<div class="con_overlfow">
							<table width="100%" id="rsTable" class="dateline tablenowrap">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="0">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							</div>
							<!--��ҳ��ʾ-->
						     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
							<!--��ҳ��ʾ-->
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
					</logic:notEmpty>
				</div>
			</logic:empty>
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
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
