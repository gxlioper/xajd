<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function dateFormatChg(obj) {
			if (obj.tagName.toLowerCase() == "input") {
				obj.value = replaceChar(obj.value, "-", "").substring(0, 8);
			}
		}
		
		//���ݵ���
		function exportData(){
			var url = "qgzxcjff.do?method=expXscjffxx";				
			var elementArr = ["nd","yf","gwdm","xh","yrdwdm","fflx","ffsjks","ffsjjs","xxsh"];
			url += "&xydm=" + val("xy");
			url += "&zydm=" + val("zy");
			url += "&bjdm=" + val("bj");
			for(var i=0; i<elementArr.length; i++){
				if(ele(elementArr[i])){
					url += "&" + elementArr[i] + "=" + val(elementArr[i]);
				}
			}
			window.open(url);
		}
		
		//��������
		function batchModiXscjff(){
			var RowsStr="!!";	
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}				
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			showTopWin("qgzxcjff.do?method=batchModiXscjff&pkV=" + RowsStr);
		}
		
		//dwr��ȡtitle��
		function getTitleName(){
			if($("path")){
				var path=$("path").value;
				cqkjFunc.getTitles(path,function(data){
					document.getElementById('titleName').innerHTML=data;
				});
			}
		}
		//ͳ��Excel��ص���������
		function setPath(){
			$("path").value="/qgzx_gbmyg_execl.do";
		}
		
		function exportTj(){
			document.forms[0].action = '/xgxt/qgzxGbmyg.do?method=gbmygbtj&doType=exp';
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		//���ʷ��ű�Excel
		function exportGzff(){
			document.forms[0].action = '/xgxt/qgzxGbmyg.do?method=gbmygbtj&doType=gzff';
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		// --------------2012.3.14 qlj ��ʱ��λ��𷢷�--------------------
		// -------------�ڹ���ѧ ѧ����ʱ��λ��𷢷���Ϣͳ�� begin---------------
		function lsgwTj(){
			document.forms[0].action = '/xgxt/qgzxGbmyg.do?method=lsgwTj';
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		// -------------�ڹ���ѧ ѧ����ʱ��λ��𷢷���Ϣͳ�� end---------------
	</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/work_pay_search_t" method="post">
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="path" id="path" value="${path}"/>
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="work_pay" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" name="userType" id="userType" value="${userType}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" name="userName" id="userName" value="${userName}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ��𷢷� - ��𷢷Ų�ѯ</a>
				</p>
			</div>
			<div class="toolbox">	
			<!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<!--�㽭��ýѧԺ-->
					<logic:equal value="11647" name="xxdm">
						<li> <a href="#" onclick="showTopWin('qgzxcjff.do?method=addXscjff',600,400);return false;" class="btn_zj">����</a> </li>
						<li> <a href="#" onclick="batchModiXscjff();return false;" class="btn_fz">�����</a> </li>
					</logic:equal>
					<!--end�㽭��ýѧԺ-->
					<li> <a href="#" onclick="deleteCjff();return false;" class="btn_sc">ɾ ��</a> </li>
					<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a> </li>
					<li> <a href="#" onclick="exportData();return false;" class="btn_dc">��������</a> </li>
					<!-- �ǹ㶫����ְҵ����ѧԺ -->
					<logic:notEqual value="12741" name="xxdm">
					<li> <a href="#" onclick="setPath();exportTj();return false;" class="btn_dc">ͳ��</a> </li>
					<li> <a href="#" onclick="setPath();exportGzff();return false;" class="btn_dc">���ʷ��ű�</a> </li>
					<!--  2012.3.14 qlj ��ʱ��λ��𷢷� begin --> 
					<li> <a href="#" onclick="lsgwTj();return false;" class="btn_tj">��ʱ��λͳ��</a> </li>
					<!--   ��ʱ��λ��𷢷� end --> 
					</logic:notEqual>
					<!-- end�ǹ㶫����ְҵ����ѧԺ -->
			    </ul>
			  </div>
			</div>		 
			 <!--��ѯ����-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="if(checkFfsjTime()){allNotEmpThenGo('/xgxt/work_pay_search_t.do')}">
							��ѯ
						</button>
						<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										����
							 		</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
					<th>���</th>
					<td>
						<html:select property="nd" style="width:180px" styleId="nd" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:options collection="xnList" property="nd" labelProperty="nd"/>
						</html:select>
					</td>	
					<th>�·�</th>
					<td colspan="3">
						<html:select property="yf" style="width:180px"
							styleId="yf">
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf"
								labelProperty="yf" />
						</html:select>
					</td>			
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:text property="xh" style="width:180px" styleId="xh"></html:text>
					</td>	
					<th>����</th>
					<td colspan="3">
						<html:text property="xm" style="width:180px" styleId="xm"></html:text>
					</td>			
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" styleId="xy" style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
					</td>	
					<th>רҵ</th>
					<td>
						<html:select property="zydm" styleId="zy" style="width:180px"
							onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
					</td>
					<th>�༶</th>
					<td>
						<html:select property="bjdm" styleId="bj" style="width:180px">
						<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
						</html:select>
					</td>			
				</tr>
				<tr>
					<th>���˵�λ</th>
					<td>
						<html:select property="yrdwdm" style="width:180px"
							styleId="yrdwdm" onchange="loadGwmcxx('gwdm','','nd','')">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
						</html:select>
					</td>	
					<th>��λ����</th>
					<td>
						<html:select property="gwdm" styleId="gwdm" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdm"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th>����ʱ��</th>
					<td>
						<html:text property="ffsjks" style="width:85px" styleId="ffsjks" onclick="return showCalendar('ffsjks','y-mm-dd');" readonly="true"/>-
						<html:text property="ffsjjs" style="width:85px" styleId="ffsjjs" onclick="return showCalendar('ffsjjs','y-mm-dd');" readonly="true"/>
					</td>			
				</tr>
				<%--�й����ʴ�ѧ--%>
				<logic:equal value="10491" name="xxdm">	
				<tr>
					<th>��������</th>
					<td>
						<html:select property="fflx" styleId="fflx" style="width:180px">
						<html:option value="�¹���">�¹���</html:option>
						<html:option value="��ʱ�ڹ���">��ʱ�ڹ���</html:option>
						<html:option value="��������">��������</html:option>
						</html:select>
					</td>	
					<th>ѧУ���</th>
					<td colspan="3">
						<html:select property="xxsh" style="width:180px">
							<html:option value=""></html:option>
							<html:option value="ͨ��">ͨ��</html:option>
							<html:option value="��ͨ��">��ͨ��</html:option>
							<html:option value="δ���">δ���</html:option>
						</html:select>
					</td>		
				</tr>	
				</logic:equal>	
				<%--end�й����ʴ�ѧ--%>	
			  </tbody>
			</table>				
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
				    <td>
						<input type="checkbox" id="all" name="all" onclick="chec()" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>	
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand">
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pkV" id="pkV" value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
							<td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="2">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
			   </div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			    <!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="flag">
			<logic:equal value="true" name="flag">
				<script>
					alert('�����ɹ���');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="flag">
				<script>
					alert('����ʧ�ܣ�');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present> 
	</body>
</html>
		