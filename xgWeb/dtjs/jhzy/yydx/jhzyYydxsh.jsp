<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/nbzy/nbzyJs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript">

function yydxshView(){   
    var url = "/xgxt/jhzyYydx.do?method=jhzyYydxView&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += pk;
    showTopWin(url,"800","400");
}
function checkType(){
   var userType = $("userType").value;
   if(userType=="xx"||userType=="admin"){
      if($("checkpass")){ $("checkpass").value="ѧУ���ͨ��";}
      if($("checknopass")){ $("checknopass").value="ѧУ��˲�ͨ��";}
   }else if(userType=="xy"){
      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />���ͨ��";}
      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />��˲�ͨ��";}
   }
} 
function batchCheck(str){
           var userType = $("userType").value;
           var url = "/xgxt/jhzyYydx.do?method=yydxCk&check="+str; 
		   var RowsStr="!!";		  
		   var userType = $("userType").value;		   
		   xyshDone = (str=="yes")?"ͨ��":"��ͨ��";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pk")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
		   if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
			     refreshForm(url);
		   }         		                  
  }
</script>
<body onload="xyDisabled('xy');checkType();">
	<html:form action="/jhzyYydx" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: ���Ž��� - ҵ�൳У - ���
			</div>
		</div>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="pkVStr" name="pkVStr" value="" />
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							&nbsp;&nbsp; ѧ�꣺
							<html:select property="xn" styleClass="select" disabled="true"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; ѧ�ڣ�
							<html:select property="xq" styleClass="select" disabled="true"
								styleId="xn">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp;�꼶��
							<html:select property="nj" styleId="nj"
								onchange="initZyList();initBjList()" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;���״̬��
							<html:select property="yesNo" styleId="yesNo">
							    <html:option value=""></html:option>
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td width="10" rowspan="3" align="center" valign="middle">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick=" refreshForm('jhzyYydx.do?method=jhzyYydxsh');document.getElementById('search_go').disabled=true;">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" styleId="xy">
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
							<html:select property="bjdm" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							&nbsp;&nbsp;ѧ�ţ�
							<html:text property="xh" />
							&nbsp;&nbsp;������
							<html:text property="xm" />
							&nbsp;&nbsp;���뵳У�ڽ죺
							<html:text property="qj" />��
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
									ondblclick="yydxshView()">
									<td align=center>
										<input type="checkbox" id="pk" name="pk"
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
				<%--				<logic:equal value="yes" name="writeAble" scope="request">--%>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button type="button" class="button2" onclick="batchCheck('yes')" id="checkpass">
						ͨ  ��
					</button>
					<button type="button" class="button2" onclick="batchCheck('no')" id="checknopass">
						��ͨ��
					</button>
				</div>

				<%--				</logic:equal>--%>
				<div id="tmpdiv"></div>
			</div>
		</div>
		<logic:equal name="update" value="yes">
			<script>
				alert("�����ɹ�");			    
   			</script>
		</logic:equal>
		<logic:equal name="update" value="no">
			<script>
				alert("����ʧ��");			    
   			</script>
		</logic:equal>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
