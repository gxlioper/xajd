<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="js/BatAlert.js"></script>
<script>
function dataAdd() {	
    var url = "/xgxt/jhzyYydx.do?method=xkcapAdd";		    	
	showTopWin(url, "700","400");			
}
function chec(){
    for(i=0;i<document.getElementsByName("pkV").length;i++){
      	 document.getElementsByName("pkV")[i].checked=document.getElementsByName("pk")[0].checked;
    }
}
 function dataDel(){
           var url = "/xgxt/jhzyYydx.do?method=xkcapDel&go=go"; 
		   var RowsStr="";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;
		   
		   if (RowsStr==""){
			   alert("��ѡ��Ҫɾ���ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		
		   if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
			  return false;
		   }
	       refreshForm(url);          
 }
 function dataViewModi(str) {
	var pkValue = "";
	var xh = "" ;
	if (curr_row == null) {
		alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
		return false;
	} else {
		var url = "/xgxt/jhzyYydx.do?method=xkcapUpdate&pkValue=";		    			    
		url+= curr_row.getElementsByTagName("input")[0].value;
		url+="&doType="+str;		
		showTopWin(url, "700","400");		
	}
}
</script>
<body onload="xyDisabled('xy')">
	<html:form action="/jhzyYydx" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã����Ž��� - ҵ�൳У - �տγ̰���
			</div>
		</div>
		<input type="hidden" id="userType" name="userType" value="${userType}" />
		<!-- ����ɾ����Ϣ��ʾ -->
		<input type="hidden" id="delPk" name="delPk" />
		<fieldset>
			<legend>
				����
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; ѧ�꣺
							<html:select property="xn" styleClass="select" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; ѧ�ڣ�
							<html:select property="xq" styleClass="select" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<input type="hidden" name="go" value="a" />
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="refreshForm('/xgxt/jhzyYydx.do?method=jhzyYydxkcap&go=go');this.disabled=true">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							ʱ��Σ�
							<html:text property="kssj" styleId="kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('kssj','y-mm-dd','aa');" />
							--
							<html:text property="jssj" styleId="jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jssj','y-mm-dd','aa');" />
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ����������;������ͷ���Խ�������;</font>
						</legend>
						<table width="99%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" id="pk" name="pk" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
									<td>
										���ظ���
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;"
									ondblclick="dataViewModi('view')">
									<td align=center>
										<input type="checkbox" id="pkV" name="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1" length="7">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td>
										<logic:iterate id="v" name="s" offset="8">
											<logic:empty name="v">
											�޸���
											</logic:empty>
											<logic:notEmpty name="v">
											<a href="zgdzdxXxwh.do?method=downLoadFile&dir=<bean:write name="v" />" target="_blank" />�������</a>
											</logic:notEmpty>
										</logic:iterate>
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" align="center" id="btn"
						style="position:absolute;width:95%;top:100px">
						<button type="button" class="button2" id="btn_del" style="width:80px"
							onclick="dataAdd()">
							����
						</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" id="btn_del" style="width:80px"
							onclick="dataViewModi('modi')">
							�޸�
						</button>
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" id="btn_del" style="width:80px"
							onclick="dataDel()">
							ɾ��
						</button>
					</div>
					<div id="tmpdiv"></div>
				</logic:equal>
			</div>
			<script type="text/javascript" src="js/bottomButton.js"></script>
		</div>
	</html:form>
</body>
