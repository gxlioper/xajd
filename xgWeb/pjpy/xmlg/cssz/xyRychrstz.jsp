<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">       
function chgPage(objTr) {
	document.forms[0].action = "/xgxt/pjpy_xmlg_xyJxjrstz.do?act=" + objTr.id;
	document.forms[0].submit();
}
function disBmxx() {   
	var bm = document.forms[0].bmlb;//
	var returnVal=bm.value;
<%--	for(var i=0;i<bm.length;i++){--%>
<%--		if(bm[i].checked){--%>
<%--			returnVal = bm[i].value;--%>
<%--			num = i;--%>
<%--		}--%>
<%--	}--%>
	if (returnVal=='zy') {
		document.getElementById("bj").selectedIndex = 0;	
		document.getElementById("bj").disabled = true;
		document.getElementById("zy").disabled = false;
	} else if (returnVal=='bj') {
		document.getElementById("bj").disabled = false;
		document.getElementById("zy").disabled = false;
	}	
}
function  round(num,n){
  var   dd=1;  
  var   tempnum;  
  for(i=0;i<n;i++)  
  {  
    dd*=10;  
  }  
  tempnum=num*dd;  
  tempnum=Math.round(tempnum);  
  return tempnum/dd;  
}  
function tzlimit(obj){
        if(curr_row==null){
          return false;
        }
        
        var bmrs = curr_row.cells[5].innerText.replace(" ","");
        bmrs = parseInt((bmrs=="")?"0":bmrs);
        var bl = curr_row.cells[6].innerText.replace(" ","");
        var jyvalue = curr_row.cells[7].innerText.replace(" ","");
            jyvalue =(jyvalue=="")?"0":jyvalue; 
        var tzvalue= parseInt((obj.value=="")?"0":obj.value);
        if(bl==""&&tzvalue>0){
           alert("��δ�Ը������ƺŸò������������ƺ���������,\n\n�ݲ��ܽ�������������");
           obj.value="";
           return false;
        }
        if(tzvalue>bmrs){
           alert("�����������ڲ���������");
           obj.value="";
           return false;
        } 
        if(tzvalue>round(jyvalue,0)){
           alert("�����������ܴ��ڽ����������������룩��");	
           obj.value=""; 
           return false;
        }      
<%--        var tabObj = document.getElementById("rsTable");--%>
<%--        var tableLen = document.getElementById("rsTable").rows.length-1;--%>
<%--        var jesum = "";--%>
<%--        var tzrssum = "";--%>
<%--        var pkValues = "'";--%>
<%--        for(i=1;i<=tableLen;i++){--%>
<%--           var jetem =  replaceChar(tabObj.rows[i].cells[3].innerText, " ", "");--%>
<%--               jetem = (jetem==""||jetem==null)?"0":jetem;--%>
<%--           jesum =jesum+jetem+"!!";--%>
<%--           var tzrstem =  replaceChar(tabObj.rows[i].getElementsByTagName('input')[1].value, " ", "");--%>
<%--               tzrstem = (tzrstem==""||tzrstem==null)?"0":tzrstem;--%>
<%--           tzrssum =tzrssum+tzrstem+"!!";--%>
<%--           pkValues +=replaceChar(tabObj.rows[i].getElementsByTagName('input')[0].value, " ", "")+"','";--%>
<%--           --%>
<%--        }    --%>
<%--        pkValues=pkValues.substring(0,pkValues.length-2);  --%>
<%--	    var xzfs=$("xzfs").value;--%>
<%--	    var yxzje=$("yxzje").value;--%>
<%--	    var pkValue=curr_row.getElementsByTagName("input")[0].value;--%>
<%--	    var tzfs=$("bmlb").value;--%>
<%--	    var jyvalue=curr_row.cells[8].innerText.replace(" ","");	    --%>
<%--	    var xydm=$("xydm").value;--%>
<%--	    var xn=$("xn").value;--%>
<%--	    getStuDtiaInfo.retXyRsTzXzFs(xzfs,yxzje,tzfs,jyvalue,tzvalue,xydm,xn,jesum,tzrssum,pkValues,function(data){--%>
<%--	        if(data[0]=="false"){--%>
<%--	           alert("�����������ܴ��ڽ����������������룩��");	--%>
<%--	           obj.value="";                 --%>
<%--	        }else if(data[1]=="false"){--%>
<%--	           var clin = "���༶";--%>
<%--	           if(tzfs=="zy"){--%>
<%--	              clin = "��רҵ";--%>
<%--	           }--%>
<%--	           alert("��<bean:message key="lable.xsgzyxpzxy" />��"+clin+"���������ܽ��ѳ�����<bean:message key="lable.xsgzyxpzxy" />�ܽ�����ޣ�");	  --%>
<%--	           obj.value="";         --%>
<%--	        }--%>
<%--	    });--%>
	}
function dataSearch(){
<%--	    var bm = document.forms[0].bmlb;--%>
<%--	    var returnVal="";--%>
<%--	    for(var i=0;i<bm.length;i++){--%>
<%--		  if(bm[i].checked){--%>
<%--			returnVal = bm[i].value;			--%>
<%--		  }--%>
<%--	    }--%>
<%--	    if(returnVal==""){--%>
<%--	       alert("��ѡ�������ʽ��");--%>
<%--	       return false;	       --%>
<%--	    }--%>
	    refreshForm('/xgxt/xmlgpjpy.do?method=xyRychrstz&operType=query');
}

function updateTzfs(){
        var bmlbV = $("bmlbV").value;
        var bm = document.forms[0].bmlb;
	    var returnVal="";
	    for(var i=0;i<bm.length;i++){
		  if(bm[i].checked){
			returnVal = bm[i].value;			
		  }
	    }
	    if(returnVal!=bmlbV){
	       if(confirm("���������ʽ����ձ�ѧ�ꡢ\n\n��<bean:message key="lable.xsgzyxpzxy" />�ĸ���ѧ���������\n\nȷ��Ҫ�����")){
	          refreshForm('/xgxt/xmlgpjpy.do?method=tzfsUpdate')
	       }else{
	          refreshForm('prise_conf_rs.do')
	       }
	    }
}
</script>
<body onload="disBmxx();pageCardOn('')">
	<html:form action="/xmlgpjpy.do?method=xyJxjrstz" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
<%--		<input type="hidden" name="operType" value="query" />--%>
		<input type="hidden" name="pt" id="pt" />
		<input type="hidden" name="tname" id="tname" value="rych" />
		<input type="hidden" name="userType" id="userType" value="${userType }" />
		<input type="hidden" name="xn" id="xn" value="${xn}"/>
		<input type="hidden" name="xydm" id="xydm" value="${xydm}"/>
		<input type="hidden" name="yxzje" id="yxzje" value="${yxzje}"/>
		<input type="hidden" name="yxzje" id="xzfs" value="${xzfs}"/>
		<input type="hidden" name="bmlb" id="bmlb" value="${bmlb}"/>
		<div class="title">
			<div class="title_img" id="title_m">
				����λ��:�������� - �������� - ��ѧ���������
			</div>
		</div>
		<div class="xxk">
			<logic:notEmpty name="pageCard">
				<logic:iterate id="card" name="pageCard">
					<ul>
						<li id="<bean:write name="card" property="en"/>l"
							class="xxk_off_l"></li>
						<li id="<bean:write name="card" property="en"/>"
							onclick="chgPage(this)" class="xxk_off_m">
							&nbsp;
							<bean:write name="card" property="cn" />
							&nbsp;
						</li>
						<li id="<bean:write name="card" property="en"/>r"
							class="xxk_off_r"></li>
					</ul>
				</logic:iterate>
			</logic:notEmpty>
		</div>
<%--		<fieldset>--%>
<%--			<legend>--%>
<%--				������ʽ--%>
<%--			</legend>--%>
<%--			<table width="100%" class="tbstyle">--%>
<%--				<thead>--%>
<%--					<tr>--%>
<%--						<td align="" nowrap="nowrap">--%>
<%--						<html:radio property="bmlb" styleId="bmlb" value="zy" onclick="disBmxx();updateTzfs();" >&nbsp;��רҵ</html:radio>--%>
<%--						<html:radio property="bmlb" styleId="bmlb" value="bj" onclick="disBmxx();updateTzfs();" >&nbsp;���༶</html:radio>--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--				</thead>--%>
<%--			</table>--%>
<%--		</fieldset>--%>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							ѧ�꣺
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" disabled="true" value="${xn }">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; �����ƺ�:
							<html:select property="dm" styleId="dm">
								<html:option value=""></html:option>
								<html:options collection="rychList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="dataSearch()">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							�꼶��
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" style="width:175px" disabled="true"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; רҵ��
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; �༶��
							<html:select property="bjdm" style="170px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				δ�ҵ��κμ�¼��
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<fieldset>
				<legend>
					��¼���� ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">��ʾ��������ͷ���Խ�������</font> 
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
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
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center" >
							<td>
								<input type="hidden" id="keys" name="keys"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									<logic:iterate id="v" name="s" offset="1" length="1">
									<bean:write name="v" />
									</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="2" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="3" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="4" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="5" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="6" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="7" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>
							<td>
							<logic:iterate id="v" name="s" offset="8" length="1">								
									<bean:write name="v" />								
							</logic:iterate>
							</td>							
							<td>
							<logic:iterate id="v" name="s" offset="9" length="1">								
							<input onfocus="rowOnClick(this.parentNode.parentNode)" name="tzrs" style="width:70px;cursor:hand;" onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this);tzlimit(this);' value="<bean:write name="v" />" >																														
							</logic:iterate>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</fieldset>
			<br><br><br>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<logic:equal value="yes" name="writeAble" scope="request">
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" style="width:100px" onclick="refreshForm('/xgxt/xmlgpjpy.do?method=xyRychrstzSave')">
							����
						</button>
					</div>
				</center>
			</logic:equal>
			<div id="tmpdiv"></div>
			<logic:equal value="yes" name="writeAble" scope="request">
				<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
			</logic:equal>
		</logic:notEmpty>

	</html:form>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').onclick();
				</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
					alert('����ʧ��!');
					document.getElementById('search_go').onclick();
				</script>
		</logic:equal>
	</logic:present>
</body>
