<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script language="javascript" src="/xgxt/js/xgutil.js"></script>
	<script language="javascript">
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
		function rowMoreClick(objTr) {
		
		if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
		}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
		
	iRow=window.event.srcElement;
	do{
		iRow=iRow.parentElement;
	}while(iRow.tagName!='TR')

	//Ctrl��ѡ
	if(event.ctrlKey){
		var j=-1;
		for(i=0;i<Rows.length;i++){
			if(iRow==Rows[i]){
				j=i;
				break;
			}
		}
		if(j!=-1){
			for(i=j;i<Rows.length-1;i++){
				Rows[i]=Rows[i+1];
			}
			Rows.length=Rows.length-1;
			iRow.style.backgroundColor = "#FFFFFF";
		}else{
			Rows[Rows.length]=iRow;
		}
//		ShiftStartRow=iRow;
	}
	else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
		
//		ShiftStartRow=iRow;
	}
	changeColor(iRow);
}

//ѡ���б�ɫ
function changeColor(E){
	
/*	for(var i=1;i<E.parentElement.rows.length;i++){
		E.parentElement.rows(i).style.backgroundColor=cur_bgc;
	}
*/
	for(i=0;i<Rows.length;i++){
		Rows[i].style.backgroundColor=cur_bgc;	
	}
}

function selectAll(){
	var checkBoxArr = document.getElementsByName("pk");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (!checkBoxArr[i].disabled) {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function del(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function tg(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ�����޸�Ϊͨ���ļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function btg(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ�����޸�Ϊ��ͨ���ļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		url += "&xmdm="+document.getElementById('xmdm').value;
		showTopWin(url, 750, 550);
		return true;
	}
}

function add(url){
	url += "&xmdm=";
	url += $('xmdm').value;
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ�޸ĵļ�¼��");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	url += "&xmdm="
	url += $('xmdm').value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1Exp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		function tjjg(){
			var dd_html = "<div>";
			dd_html += "<center><br><table width='350' class='tbstyle'>";
			dd_html += "<thead>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "��ѡ����Ҫ�ύ��˽���İ༶";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "ѧ�꣺";		
			dd_html += "</td><td>";
			dd_html += val('xn');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "�༶��";		
			dd_html += "</td><td>";
			dd_html += "<select id='tjbjdm' name='tjbjdm'/>";
			dd_html += "</td></tr>";
			dd_html += "<tr height='30' bgcolor='EEF4F9'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "<button class='button2' onclick='commitSh()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
			dd_html += "<button class='button2' onclick='closeAdd()'>ȡ��</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table></center>";
			dd_html += "</div>";
			showDiv(dd_html, 400, 170);
			copySelect('bj','tjbjdm');
			setVal('tjbjdm',val('bj'));
		}
		
		function commitSh(){
			//�ж�ѡ��İ༶�Ƿ��Ѿ��ύ��
			var xn = val('xn');
			var zxjdm = val('xmdm');
			var bjdm = val('tjbjdm');
			if(bjdm == null || bjdm == "" || bjdm == undefined){
				alert('��ѡ����Ҫ�ύ��˽���İ༶��');
				return false;
			}
			qgzxgzkh.checkExists('xszz_com_bmshtjb','zjz||dm||bm||tjxm||tjzt',xn+zxjdm+bjdm+'bjwszxj1'+'1',function(data){
				if(data != null && data == true){
					alert('�ð༶�Ѿ��ύ��');
					return false;
				}else{//�ύ
					qgzxgzkh.checkExists('view_xszz_com_wszxj1','xn||zxjdm||bjdm||fdysh',xn+zxjdm+bjdm+'δ���',function(data){
						if(data){
							alert('��������δ���,��ʱ�����ύ�����');
							return false;
						}else{
							refreshForm("n05_xszz.do?method=wszxj1sh&go=tjsh&xn=" + xn + "&xmdm=" + zxjdm + "&tjbjdm=" + bjdm);
						}
					});
					
				}
			});
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/n05_xszz.do?method=wszxj1sh" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>
					<logic:equal name="isQuery" value="is">
					ѧ������ - ��������ѯ - <bean:write name="xmmc"/>
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					ѧ������ - ��� - <bean:write name="xmmc"/>
					</logic:notEqual>
					</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="xmdm" name="xmdm"
				value="<bean:write name="xmdm" />">
			<div class="toolbox">
			<logic:equal value="yes" name="writeAble" scope="request">
					 <div class="buttonbox">
						<ul>
						<logic:equal name="isQuery" value="is">
						<li> <a href="#" onclick="add('/xgxt/n05_xszz.do?method=wszxj1sq');" class="btn_zj"> ���� </a> </li>
					    <li> <a href="#" onclick="modi('/xgxt/n05_xszz.do?method=wszxj1sq&type=modi');" class="btn_xg"> �޸� </a> </li>
						<li> <a href="#" onclick="del('/xgxt/n05_xszz.do?method=wszxj1sh&go=del');" class="btn_sc"> ɾ�� </a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a> </li>
						<li> <a href="#" onclick="dataExport2()"  class="btn_dc"> ���� </a> </li>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<li> <a href="#" onclick="tg('/xgxt/n05_xszz.do?method=wszxj1sh&go=tg');" class="btn_shtg"> ͨ �� </a> </li>
							<li> <a href="#"  onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=btg');" class="btn_shbtg"> ��ͨ�� </a> </li>
							<logic:present name="fdyshFlag">
								<li> <a href="#"  onclick="tjjg()" class="btn_tj"> �ύ��� </a> </li>
							</logic:present>
						</logic:notEqual>
					    </ul>
					 </div>
			</logic:equal>
			<div class="searchtab">
			<table width="100%" border="0">
				<tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
								<input type="hidden" name="go" value="a" />
								<div class="bz">
									<!--����Ա�ύ��Ϣ-->
									<logic:present name="tjxx">
									<tr>
										<td colspan="2">
											<font color="red"><b><bean:write name="tjxx" property="bjmc"/>�ύ�����<bean:write name="tjxx" property="tjzt"/></b></font>
										</td>
									</tr>
									</logic:present>
									<!--end����Ա�ύ��Ϣ-->
								</div>
								<button class="btn_cx"   id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=wszxj1sh&go=go')">
									�� ѯ
								</button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 �� ��
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
						<tr>
							<th>	
								ѧ��
							</th>
							<td>	
								<html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								����
							</th>
							<td>	
								<html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								�Ա�
							</th>
							<td>	
								<html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>	
								<logic:equal name="isQuery" value="is">
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</logic:equal>
								<logic:notEqual name="isQuery" value="is">
								<html:select property="xn" 
									styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</logic:notEqual>
							</td>
							<th>	
								�꼶
							</th>	
							<td>
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>	
								���֤��
							</th>
							<td>	
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>	
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>	
								רҵ
							</th>
							<td>	
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>	
								�༶
							</th>
							<td>	
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox" id="result">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
						<font color="blue"> 
							��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			    <logic:notEmpty name="rs">
						<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<logic:equal name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1sq&type=modi')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="1" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:equal>
									<logic:notEqual name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1shOne')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="1" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center><input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> 
										value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:notEqual>
								</logic:iterate>
								</tbody>
							</table>
							<!--��ҳ��ʾ-->
						    	 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzCommonN05ActionForm"></jsp:include>
						 	 <!--��ҳ��ʾ-->
							</div>
					</logic:notEmpty>
					<div id="tmpdiv"></div>
					</div>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
		 	<logic:equal value="yes" name="result">
		 		<script>
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 </logic:notEmpty>
	</body>
</html>
