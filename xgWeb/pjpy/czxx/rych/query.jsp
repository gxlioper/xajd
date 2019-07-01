<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript">	  
	function batchDel(){
           var url = "/xgxt/pjpy_czxx_rychQuery.do?act=del&go=go"; 
		   var RowsStr="";		  
		   for (i=0; i<document.getElementsByName("cbv").length; i++){
	    	  if(document.getElementsByName("cbv")[i].checked){
	    		 RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
	    	  }
		   }
		  // document.forms[0].delPk.value = RowsStr;
		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫɾ���ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		
		   if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
			  return false;
		   }
	       refreshForm(url);          
    }
     function print() {
	var pk = "";
	if (curr_row==null || curr_row=='') {
			if (confirm("��δѡ���κ�Ҫ��ӡ�����ݣ�Ҫ������ӡ��")) {
				
			} else {
				return false;
			}
		} else {
			pk =  curr_row.cells[0].getElementsByTagName("input")[0].value;
		}
    window.open('pjpy_czxx_print.do?table=xsrychb&pkValue=' + pk);
    }
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="pt" value="pt"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - �����ƺ����� - ��������ѯ
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;ѧ�ڣ�
								<html:select property="queryequals_xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;�꼶��
								<html:select property="queryequals_nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								<td width="10" rowspan="3" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychQuery.do?act=qry');">
									��ѯ
								</button>
							</td>
							</tr>
							<tr>
							<td>
								�����ƺţ�
								<html:select property="queryequals_rychdm" styleId="rychdm" >
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
								</html:select>
								&nbsp;
								ѧ�ţ�
								<html:text property="querylike_xh"  styleId="xh" maxlength="19"></html:text>
								&nbsp;������
								<html:text property="querylike_xm" styleId="xm" maxlength="10"></html:text>
							</td>
							</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="queryequals_xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="queryequals_zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="queryequals_bjdm" styleId="bj">
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
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼���� ${rsNum } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������;</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_czxx_rychModi.do?act=view&pkValue=','modi','700','600')">
								<td align="center">
									<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2" length="3">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="center">
										<a href="#" onclick="queryOne('<bean:write name="v" />')"><bean:write name="v" /></a>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="6">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyCzxxActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
		</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>
		<logic:equal value="yes" name="writeAble">
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2" id="btn_dc" onclick="showTopWin('pjpy_czxx_rychAdd.do',700,570)"
					style="width:80px">
					����
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="modiAndDel('pjpy_czxx_rychModi.do?pkValue=','modi','700','570')"
					style="width:80px">
					�޸�
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="batchDel()"
					style="width:80px">
					ɾ��
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="impAndChkData()"
					style="width:80px">
					����
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="dataExport()"
					style="width:80px">
					����
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dy" onclick="print()"
					style="width:80px">
					��ӡ�Ƽ���
				</button>
			</div>
			<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		</logic:equal>
	</center>
	<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</logic:present>
</body>