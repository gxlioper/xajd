<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<script>   
var mb = 0;
function colorOn(){	
	for(i = 0;i<mbT.rows.length;i++){	
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";			
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}
function printZS(mod){

	if(mb >3 || mb <0){
		mb = 0;
		colorOn();
	}
	if (mod==3){//������ӡ
		var ul ='jxjzsprint.do?xh='
		if (curr_row == null) {
			alert("��ѡ��һ��Ҫ��ӡ�ļ�¼,����һ�м���!");
			return false;
		}
		if (curr_row==null || curr_row==''){
			var hjxn = prompt("   �������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
			var hjxq = prompt("   �������ѧ�ڣ�(��ʽ����:һ����)","");
			var hjxjdj = prompt("   �����뽱ѧ��ȼ���(��ʽ����:һ����)","");
			var hjny = prompt("   �����뷢֤���£�(���ڸ�ʽΪ:xxxx-xx)","");
		}else{
			var hjxn = prompt("   �������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
			var hjxq = prompt("   �������ѧ�ڣ�(��ʽ����:һ����)","");
			var hjxjdj = prompt("   �����뽱ѧ��ȼ���(��ʽ����:һ����)","");
			var hjny = prompt("   �����뷢֤���£�(���ڸ�ʽΪ:xxxx-xx)","");
			ul += curr_row.cells[0].getElementsByTagName("input")[0].value;
			ul += '&xm='
			ul += curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
		var ull = '&hjxn='+hjxn+'&hjxq='+hjxq+'&hjxjdj='+hjxjdj+'&hjny='+hjny;
			ul += ull;
		window.open(ul);
		return;
	}
	if (mod==4) {//��ӡ����ҳ
			var hjxn = prompt("   �������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
			var hjxq = prompt("   �������ѧ�ڣ�(��ʽ����:һ����)","");
			var hjxjdj = prompt("   �����뽱ѧ��ȼ���(��ʽ����:һ����)","");
			var hjny = prompt("   �����뷢֤���£�(���ڸ�ʽΪ:xxxx-xx)","");
			var ull = '';
			if (curr_row!=null && curr_row!=''){
				ull = curr_row.cells[0].getElementsByTagName("input")[1].value;;
				}
			ull += '&hjxn='+hjxn+'&hjxq='+hjxq+'&hjxjdj='+hjxjdj+'&hjny='+hjny;
		window.open('jxjzsprint.do?xm='+ull);
		return;
	}
	if (mod==5){//����
		var hjxn = prompt("   �������ѧ��ȣ�(���ڸ�ʽΪ:xxxx-xxxx)","");
			var hjxq = prompt("   �������ѧ�ڣ�(��ʽ����:һ����)","");
			var hjxjdj = prompt("   �����뽱ѧ��ȼ���(��ʽ����:һ����)","");
			var hjny = prompt("   �����뷢֤���£�(���ڸ�ʽΪ:xxxx-xx)","");
			var ull = '&hjxn='+hjxn+'&hjxq='+hjxq+'&hjxjdj='+hjxjdj+'&hjny='+hjny;
			if (confirm("ȷ��Ҫ���˲�����?")){
    	var xm;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xm=$("tabPri").rows[0].cells[3].innerText.trim();
			window.open("jxjzsprintmore.do?xm="+xm+ull);
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
			return false;
		 }
		 return true;
	}
     else{
	     return false;
	}   
		return;
	}
	var url = "/xgxt/print/printCotener_";
	url += mb;
	url += ".html";
	document.getElementById("nj").value = mod;
	chgRight(url,'_blank');
	document.forms[0].target = "_self";
}
</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="pjpy/zzdx/zzdxJs/zzdxjs.js"></script>
		<html:form action="/log_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ��� - ��ѧ��֤���ӡ</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!--��дȨ-->
						<logic:equal value="yes" name="writeAble">
							<div class="noprint">
								<!-- ���ݴ�ѧ����֤���ӡ -->
								<logic:present name="showzzdx">
									<li>
									<a href="#" class='btn_dy' 
										onclick="printzzdxZS('test')">��ӡ����ҳ</a>
										</li>
										<li>
									<a href="#"  class='btn_dy' 
										onclick="printzzdxZS('one')">������ӡ</a>
										</li>
										<li>
									<a href="#" class='btn_dy' 
										onclick="printzzdxZS('more')">֤������</a>
										</li>
								</logic:present>
								<logic:notPresent name="showzzdx">
								<li>
									<a href="#" class='btn_dy' 
										onclick="printZS(4)">��ӡ����ҳ</a>
										</li>
										<li>
									<a href="#" class='btn_dy' 
										onclick="printZS(3)">������ӡ</a>
										</li>
										<li>
									<a href="#" class='btn_dy' 
										onclick="printZS(5)">֤������</a>
										</li>
								</logic:notPresent>
							</div>

						</logic:equal>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td>
									<!--����������ʾ-->
									<logic:notEmpty name="yhInfo">
										<font color="red">��ʾ��${ yhInfo}</font>
									</logic:notEmpty>
									<!--end����������ʾ-->
								</td>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="" id="search_go"
											onclick="listPriseConf('/xgxt/prise_print.do')">
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
									��ѧ��
								</th>
								<td>
									<html:select property="xmdm" style="width:200px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
								</td>
								<th>
									<input type="hidden" value="a" id="nj" name="nj">
									<input type="hidden" value="<bean:write name="sj"/>" id="sj"
										name="sj">
										ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:90px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" styleId="xq" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									<input type="checkbox" style="display:none" id="chgMode">
								</td>
								
							</tr>
							<tr>
							<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:170px" styleId="xy"
										onchange="refreshForm('prise_print.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width:170px" styleId="zy"
										onchange="refreshForm('prise_print.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:170px" styleId="bjdm">
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

			<%--			<div class="noprint">--%>
			<%--				<fieldset>--%>
			<%--					<legend align="center">--%>
			<%--						��ӡ��----ģ��ѡ��--%>
			<%--					</legend>--%>
			<%--					<table class="tbstyle" align="center" width="100%" height="150px"--%>
			<%--						id="mbT">--%>
			<%--						<tr>--%>
			<%--							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=0;colorOn();"> </td> -->--%>
			<%--							<td bgcolor="#FFFFFF" width="33%" align="center"--%>
			<%--								style="cursor:hand" onclick="mb=0;colorOn();">--%>
			<%--								<font color="red">ѧУ�ṩģ��</font>--%>
			<%--								<img src="<logic:equal value="yes" name="showzzdx">print/zzdx/zzdx_zs1.jpg</logic:equal>" height="150px" border="1" />--%>
			<%--								--%>
			<%--							</td>--%>
			<%--							<td bgcolor="#FFFFFF" width="34%" align="center"--%>
			<%--								style="cursor:hand" onclick="mb=1;colorOn();">--%>
			<%--								<font color="red">ѧУ�ṩģ��</font>--%>
			<%--								<img src="<logic:equal value="yes" name="showzzdx">print/zzdx/zzdx_zf2.jpg</logic:equal>" height="150px" border="1" />--%>
			<%--							</td>--%>
			<%--							<td bgcolor="#FFFFFF" width="33%" align="center"--%>
			<%--								style="cursor:hand" onclick="mb=2;colorOn();">--%>
			<%--								<font color="red">ѧУ�ṩģ��</font>--%>
			<%--								<img src="<logic:equal value="yes" name="showzzdx">print/zzdx/zzdx_zf3.jpg</logic:equal>" height="150px" border="1" />--%>
			<%--							</td>--%>
			<%--							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=2;colorOn();"> </td> -->--%>
			<%--						</tr>--%>
			<%--						<tr>--%>
			<%--							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"--%>
			<%--								onclick="mb=0;colorOn();">--%>
			<%--								ģ��һ--%>
			<%--							</td>--%>
			<%--							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"--%>
			<%--								onclick="mb=1;colorOn();">--%>
			<%--								ģ���--%>
			<%--							</td>--%>
			<%--							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"--%>
			<%--								onclick="mb=2;colorOn();">--%>
			<%--								ģ����--%>
			<%--							</td>--%>
			<%--						</tr>--%>
			<%--					</table>--%>
			<%--				</fieldset>--%>
			<%--			</div>--%>

			<div class="formbox">
				<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 <font color="blue"></font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
				<logic:notEmpty name="rs">

						<table summary="" class="dateline" width="100%"
							id="rsTable">
							<thead>
								<tr align="left" style="cursor:hand">
								
									<logic:iterate id="tit" name="topTr">

										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody id="tabPri">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align="left"
										style="cursor:hand;">
										<td>
											<input type="hidden"
												value="<logic:iterate id="v" offset="1" length="1" name="s"><bean:write name="v" /></logic:iterate>" />
											<input type="hidden"
												value="<logic:iterate id="v" offset="2" length="1" name="s"><bean:write name="v" /></logic:iterate>" />
												<logic:iterate id="v" name="s" offset="0" length="1">
												<bean:write name="v" />
										</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					<!--��ҳ��ʾ-->

					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>


		</html:form>
	</body>
</html>
