<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script>
		function viewChk(){
		     var url = "/xgxt/pjpyszyqwh.do?method=szyc_xthdViewAndChk&xxk=dshd";
		     url+="&xh="+(curr_row.cells[1].getElementsByTagName("input"))[0].value;
		     url+="&xn="+$("xn").value;
		     url+="&xq="+$("xq").value;
		     showTopWin(url,"700","800");   
		} 
		function selectAllss(){
			var checkBoxArr = document.getElementsByName("pk");
			var selall = document.getElementById('cbv');
			if(selall.checked==true){
				for(var i=0;i<checkBoxArr.length;i++){
						checkBoxArr[i].checked = true;
				}
			} else {
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = false;
				}
			}
			}
		
		function dataExportXthd() {
			var url ="/xgxt/pjpyszyqwh.do?method=szyc_xthdBbhz&xxk=dshd";
			var xymc ="";
		    if($("xydm").value!=""){ 
		      xymc=document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
		    }
		    var zymc ="";
		    if($("zydm").value!=""){ 
		      zymc = document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
		    }
		    var bjmc = "";
		    if($("bjdm").value!=""){ 
		      bjmc = document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
		    }
		    var xqmc = "";
		    if($("xq").value!=""){
		       xqmc = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
		    }
		    url+="&xqmc="+xqmc;
		    url+="&xy="+xymc;
		    url+="&zy="+zymc;
		    url+="&bj="+bjmc;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
	</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-�������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" name="pkstring" id="pkstring" value="" />
		<input type="hidden" id="userType" name="userType" value="${userType}"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
    	<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />

		<div class="comp_title">
	      <ul>
	        <li class="ha"><a href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_XthdChk&xxk=dshd');"><span>����</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_XthdChk&xxk=yybd');"><span>���Ա��</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_XthdChk&xxk=ivlt');"><span>IVT��̳</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('pjpyszyqwh.do?method=szyc_XthdChk&xxk=wthd');"><span>����</span></a></li>
	      </ul>
	    </div>
	    
	    <div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" onclick="sh5s('pjpyszyqwh.do?method=szyc_XthdChk&doType=save','ͨ��');return false;">���ͨ��</a></li>
					<li><a href="#" class="btn_shbtg" onclick="sh5s('pjpyszyqwh.do?method=szyc_XthdChk&doType=save','��ͨ��');return false;">��˲�ͨ��</a></li>
					<li><a href="#" class="btn_sc" onclick="del5s('pjpyszyqwh.do?method=szyc_dshdDel&xxk=dshd&doType=del');return false">ɾ��</a></li>
					<li><a href="#" class="btn_tj" onclick="dataExportXthd();return false;">����</a></li>
				</ul>
			</div>
		</div>
		
		<div class="searchtab">
			<table width="100%">
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_XthdChk&xxk=dshd&go=go');this.disabled=true;">
							��ѯ
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							����
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
				<thead>
					<tr>
						<th>
							�꼶
						</th>
						<td>
							<html:select property="nj" styleId="nj" styleClass="select"
								onchange="initZyList();initBjList()" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td><html:select property="xn"  styleClass="select"
								styleId="xn">
								<html:option value=""></html:option>
								<html:options collection="xnList2" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td><html:select property="xq" styleClass="select"
								style="padding-left:80px" styleId="xq">		
								<html:option value=""></html:option>						
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select></td>
					</tr>
					<tr>
						<th>ѧ��</th>
						<td><html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px"></html:text>
						</td>
						<th>����</th>
						<td><html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>															
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<html:select property="xydm" onchange="initZyList();initBjList()"
								style="width:180px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" onchange="initBjList()"
								style="width:180px" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" 
								style="width:180px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</thead>
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
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>		
						<table width="99%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
								   <td>
									<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>						
									<logic:iterate id="tit" name="topTr" offset="0" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;"
									ondblclick="viewChk()">
									<td align="center">
										<input type = "hidden" id="pk" name="pk" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										<input type="checkbox" id="checkVal" name="checkVal"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>										
									<td align=center>
									    <logic:iterate id="v" name="s" offset="3" length="1">
									    <input type="hidden" value="<bean:write name="v" />" />
									    </logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
										  <bean:write name="v" />
										</logic:iterate>
									</td>																
									<logic:iterate id="v" name="s" offset="2">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzyqxyZhszcpActionForm"></jsp:include>
				</logic:notEmpty>
				</div>
				<div id="tmpdiv"></div>
			<script type="text/javascript" src="js/bottomButton.js"></script>
	</html:form>
</body>
</html>
