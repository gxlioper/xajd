<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">
<!--
function printFun(){
	var requestPath = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&';
	//var bjdm = document.getElementById("bjdm").value;
	requestPath += "bjdm="+'';
	//var titName = document.getElementById("titName");
	requestPath += "&titName="+'';
	//alert(requestPath);
	var pk = '';
	if (curr_row=='' || curr_row==null) {
		pk='';
	} else {
		pk = curr_row.getElementsByTagName("input")[0].value;
	}
	requestPath+='&pks='+pk;
	window.open(requestPath);
}
function plChkXjch(str) {
    var url="pjpyHzyXjchChkCx.do?method=xjchPlChk&go=go&chek="+str;
	var checkBoxArr = document.getElementsByName("cbv");
	var userType = $("userType").value;
	var flag = false;
	var xyshDone = (str=="yes")?"ͨ��":"��ͨ��";
	 var pkVArray = "'";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			 pkVArray+=checkBoxArr[i].value+"','";
		}		
	}
	if(flag){
	 if(userType=="xy"){
		      if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
			        refreshForm(url);   
		      }
     }else{
      pkVArray=pkVArray.substring(0,pkVArray.length-2);
      getSztzData.getInfoEx2("pjpy_xjbjandwmsqb"," xn||bjdm||rychdm in ("+pkVArray+") and xysh<>'ͨ��' ",function(boolean){	         
		  if(boolean){
		        alert("��ѡ��¼�У���<bean:message key="lable.xsgzyxpzxy" />���δ���\"ͨ��\"�ļ�¼��\n\n�����ύ�˲���,��ѡ��<bean:message key="lable.xsgzyxpzxy" />���\"ͨ��\"�ļ�¼���ύ��");
		  }else{		            		
		      if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
			        refreshForm(url);   
		      }
		 }
       });     
     }
	}else{
		alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
	}	
}
function xjtzbChkView(){
    if (curr_row==null || curr_row=='') {
		alert("��ѡ��Ҫ�޸ĵļ�¼��\n��������Ӧ���У�");
		return false;
    }
    var url = "/xgxt/hzyXjchChk.do?method=xjchChk&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
    var xjType = $("xjType").value;
	url += pk;
	url += "&xjType="+xjType;
    showTopWin(url,"600","300");
}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyHzyXjchChkCx" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã� �������� - ��� - ���������ƺ����
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="tableName" name="tableName"
			value="${tableName}" />
		<input type="hidden" id="realTable" name="realTable"
			value="${realTable}" />
		<%--    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- ����ɾ����Ϣ��ʾ -->--%>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							�����ƺ����ͣ�
							<html:select property="xjType" styleId="xjType"
								onchange="refreshForm('pjpyHzyXjchChkCx.do?method=xjchChkQuerry')"
								styleClass="select" style="width:90px">
								<html:option value="xjbj">�Ƚ��༶</html:option>
								<html:option value="wmbj">�����༶</html:option>
								<html:option value="xjtzb">�Ƚ���֧��</html:option>
								<html:option value="xjtzz">�Ƚ�����֧</html:option>
							</html:select>
							&nbsp;&nbsp; ѧ�꣺
							<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; ѧ�ڣ�
							<html:select property="xq" style="width:50px" 
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp; ���״̬��
							<html:select property="yesNo" style="width:100px" styleClass="select"
								styleId="yesNo">
								<html:options collection="chkList" property="cn"
									labelProperty="cn" />
							</html:select>							
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="go" value="a" />
							<button class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('pjpyHzyXjchChkCx.do?method=xjchChkQuerry&go=go');this.disabled=true;">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>							
						<td align="left" nowrap>
						�꼶��
							<html:select property="nj" styleId="nj"
								onchange="initZyList();initBjList()" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select"  styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							רҵ��
							<html:select property="zydm" onchange="initBjList()"
								 styleClass="select" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							�༶��
							<html:select property="bjdm" 
								styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<div id="result">
			<div class="searchcontent">
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼���� ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������;</font>
						</legend>
						<table width="99%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="xjtzbChkView()">
									<td align=center>
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<br />
				<br />
				<br />
<%--				<logic:equal value="yes" name="writeAble" scope="request">--%>
					<div class="buttontool" align="center" id="btn"
						style="position:absolute;width:95%;top:100px">
						<button class="button2" id="btn_modi" style="width:80px"
							onclick="plChkXjch('yes')">
							ͨ  ��
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2" id="btn_del" style="width:80px"
							onclick="plChkXjch('no')">
							��ͨ��
						</button>						
					</div>
<%--				</logic:equal>--%>
				<div id="tmpdiv"></div>
			</div>
		</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
