<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/pjpy/pjpyFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">	  
	//�����۲���Ϣ
	function zccount() {
		var pjzq = $('pjzq').value;
		var id = "";
		var msg = "";
		
		//����ѡ�ֶ��Ƿ���ѡ��
		id = 'xn' == pjzq ? "xn" : ('xq' == pjzq ? "xn-xq" : ('nd' == pjzq ? "nd" : "")); 
		msg = 'xn' == pjzq ? "ѧ��" : ('xq' == pjzq ? "ѧ��-ѧ��" : ('nd' == pjzq ? "���" : "")); 

		id += "-xy";
		msg += "-"jQuery("#xbmc").val();

		if (checkListIsSelect(id, msg)) {
			if (confirm('�Զ�������ܺķ�4��5���ӣ�ȷ��Ҫ�����Զ�������?')) {
				refreshForm('pjpy_tyb_countZccj.do');
				showTips('�Զ�������,���Ժ�...');
			}
		}
	}
	function changeJb() {
		var jb = $('queryequals_jb').value;
		if (jb=='1') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=true;
			$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=true;
		} else if (jb=='2') {
		//$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=false;
			$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=true;
			$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=true;
		} else if (jb=='3') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			//$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=false;
			$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=true;
		} else if (jb=='4') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=true;
			//$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=false;
		} else {
			//$('queryequals_ejdm').value="";
			//$('queryequals_ejdm').style.disabled=true;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').style.disabled=true;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').style.disabled=true;
		}
	}
	
	function cqdzgc() {
		var url="/xgxt/pjpyCqdzgc.do?method=printCqdzgc";
		if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ���ܵļ�¼��');
				return false;
		}
		document.forms[0].action = "";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
</script>
</head>
<body onload="xyDisabled('xy');changeJb();">
	<html:form action="/pjpyTybZhszcp.do" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" name="pjzq" id="pjzq" value="${pjzq }" />
		<!-- �۲����� -->
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="pjpyzq" id="pjpyzq" value="${pjpyzq }" />
		<!-- �������� -->
		<input type="hidden" id="pt" value="pt" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
<div id="msg_div" class="hide"
						style="left:20px;top:10px;">
						<p>�Զ�������ܺķ�4��5����</p>
					</div>
		<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="zccount();" class="btn_ccg" onmouseout="$('msg_div').className = 'hide'"
									onmousemove="$('msg_div').className = 'msg_prompt'">�Զ�����</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					  </logic:equal>
							<li> <a href="#" onclick="wjcfDataExport('pjpyTybZhszcp.do?method=zhszcpzfExport');" class="btn_dc">��������</a> </li>
					  <!--��ˮѧԺ-->
					  <logic:equal value="10352" name="xxdm">
						<li> <a href="#" onclick="printZhszcphzb();" class="btn_dy">�ɼ����ܱ�</a> </li>
					  </logic:equal>
					  <logic:equal value="12609" name="xxdm">
						 <li> <a href="#" onclick="cqdzgc()" class="btn_dy">ѧ���ɼ�����</a> </li>					  
					  </logic:equal>	
					  
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="refreshForm('pjpy_tyb_zhszcp.do?act=qry');">
									��ѯ
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												����
											</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  

					  <logic:equal value="nd" name="pjzq">
						<tr>
						<th>���</th>
						<td>
							<html:select property="queryequals_nd" styleId="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th></th>
						<td>		
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  </logic:equal>
					  <logic:equal value="xn" name="pjzq">
						 <tr>
							<th>ѧ��</th>
							<td>
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th></th>
							<td>		
							</td>
							<th></th>
							<td>
								
							</td>
						  </tr>
					  </logic:equal>
					  <logic:equal value="xq" name="pjzq">
						<tr>
							<th>ѧ��</th>
							<td>
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td>
								<html:select property="queryequals_xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>		
							</td>
							<th></th>
							<td>
								
							</td>
					  </tr>
					  </logic:equal>
					  
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="queryequals_nj"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="querylike_xh" styleId="xh" maxlength="19"></html:text>		
						</td>
						<th>����</th>
						<td>
							<html:text property="querylike_xm" styleId="xm" maxlength="10"></html:text>
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" styleId="xy"
								onchange="initZyList();initBjList()" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>	
							<html:select property="queryequals_zydm" styleId="zy" style="width:160px"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>	
						</td>
						<th>�༶</th>
						<td>
							<html:select property="queryequals_bjdm" styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>		
					  <tr>
						<th>��ѯ����</th>
						<td colspan="5">
							<html:select property="queryequals_jb" styleId="queryequals_jb" onchange="changeJb();refreshForm('pjpy_tyb_zhszcp.do')">
								<html:option value="1">�۲��ܷ�</html:option>
								<html:option value="2">����</html:option>
								<html:option value="3">����</html:option>
								<html:option value="4">�ļ�</html:option>
							</html:select>
						</td>
						</tr>
						<tr>
						<th>�۲���Ŀ</th>
						<td colspan="5">
							<font color="red">(����)</font>
							<html:select property="queryequals_ejdm"
								styleId="queryequals_ejdm"
								onchange="refreshForm('pjpy_tyb_zhszcp.do');">
								<html:option value=""></html:option>
								<html:options collection="ejdmList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;==>&nbsp;
							<font color="red">(����)</font>
							<html:select property="queryequals_sjdm"
								styleId="queryequals_sjdm"
								onchange="refreshForm('pjpy_tyb_zhszcp.do');">
								<html:option value=""></html:option>
								<html:options collection="sajdmList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;==>&nbsp;
							<font color="red">(�ļ�)</font>
							<html:select property="queryequals_sidm"
								styleId="queryequals_sidm">
								<html:option value=""></html:option>
								<html:options collection="sijdmList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;
							<%--								������ʽ��--%>
							<%--								<html:select property="queryequals_pmfs" styleId="pmfs">--%>
							<%--									<html:option value=""></html:option>--%>
							<%--									<html:option value="zy">רҵ</html:option>--%>
							<%--									<html:option value="bj">�༶</html:option>--%>
							<%--								</html:select>--%>		
						</td>
					  </tr>				  
					  </tbody>
					</table>
				</div>
				</div>
			 	<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
<%--			        <td>--%>
<%--<!--						<input type="checkbox" name="cb" onclick="selectAll()"-->--%>
<%--<!--							style="height:17.5px"/>-->--%>
<%--					</td>--%>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand;"
							ondblclick="">
<%--							<td align="left">--%>
<%--								<input type="checkbox" id="cbv" name="primarykey_cbv"--%>
<%--									style="height:17.5px"--%>
<%--									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />--%>
<%--							</td>--%>
							<td>
								<input type="hidden" name="cbv" id="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>
								<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="2">
								<td align="left">
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZhszcpwhActionForm"></jsp:include>
			 	<script type="text/javascript">
						$('choose').className="hide";
				</script>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
	</html:form>
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
</html>
