<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>	
<script type="text/javascript">
function checkXz(){
	var iskcxz = $("iskcxz");
	var flag = false;
	
	var checkBoxArr = document.getElementsByName("kcxz");
	var flag = false;
	
	if(iskcxz.checked){
		$("kcxzTr").style.display = "";
	}else{
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				var id = "kcxz"+i;
				$(id).checked = false;
			}
			$("kcxzTr").style.display = "none";		
		}
	}
}

function viewKcxzTj(){
	var cjlx = $("cjlx").value;
	if("cjb" == cjlx){
		if($("kcxzSp")){
			$("kcxzSp").style.display = "";
		}
	}else{
		if($("kcxzSp")){
			$("kcxzSp").style.display = "none";
		}
	}
}

function checkedKcxz(){
	if($("checked_Kcxz")){
		var checked_Kcxz = $("checked_Kcxz").value;
		if(checked_Kcxz != ""){
			if ($('iskcxz')) $("iskcxz").checked = true;
			if ($("kcxzTr")) $("kcxzTr").style.display = "";		
		}
		var kcxz = checked_Kcxz.split("!!@@!!");
		if(kcxz != null && kcxz.length>0){
			if ($("kcxz")) {
				var checkBoxArr = document.getElementsByName("kcxz");
				for(var i=0;i<kcxz.length;i++){
					for(var j=0;j<checkBoxArr.length;j++){				
						if(checkBoxArr[j].value==kcxz[i]){
							var id = "kcxz"+j;
							$(id).checked = true;
						}
					}
				}
			}
		}
	}
}
	function hhgxyTj(){
			if($("bjdm").value==""){
				alert("��ѡ��༶��");
				return false;
			}
			if($("xn").value==""){
				alert("��ѡ��ѧ�꣡");
				return false;
			}
			if($("xq").value==""){
				alert("��ѡ��ѧ�ڣ�");
				return false;
			}
			var url="/xgxt/pjpyHhgxyPjtj.do?method=pjtj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
	function qtxxTj(){
			if($("bjdm").value==""){
				alert("��ѡ��༶��");
				return false;
			}
			if($("xn").value==""){
				alert("��ѡ��ѧ�꣡");
				return false;
			}
			if($("xq").value==""){
				alert("��ѡ��ѧ�ڣ�");
				return false;
			}
			var url="/xgxt/pjpyHhgxyPjtj.do?method=pjtj&xxlx=tyxx";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
<!--

//-->
</script>

</head>
<body onload="xyDisabled('xy');viewKcxzTj();checkedKcxz()">	
		<html:form action="/pjpyahjgwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:message bundle="pjpyahjg" key="pjpy_ahjg_xscjqry"/></a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>"/>
				<input type="hidden" name="zyV" id="zyV"/>
				<input type="hidden" name="bjV" id="bjV"/>
				<input type="hidden" name="xyV" id="xyV"/>
				<input type="hidden" name="tableName" value="${tableName }" id="tableName"/>
				<input type="hidden" name="realTable" value="${realTable }" id="realTable"/>
				<input type="hidden" name="userName" id="userName" value="${userName}" />
				<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
				<input type="hidden" name="checked_Kcxz" id="checked_Kcxz" value="${checked_Kcxz }"/>
				<div class="toolbox">
				<logic:equal name="writeAble" value="yes">
				<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="isFdy" value="true"> 
						<li><a href="#" id="btn_count" class="btn_csh" onclick="jwcjtb('ahjg_cjtb.do?cjlx=')">�ɼ�ͬ��</a></li>
						</logic:notEqual>
			
						<logic:equal name="xxdm" value="10338">
						<li><a href="#" class="btn_dr" id="drbtn" onclick="dr();">��������</a></li>
						</logic:equal>
											
						<li><a href="#" class="btn_dc" onclick="cjexp()">��������</a></li>
						<logic:equal name="xxdm" value="11641">
							<li><a href="#" class="btn_tj" onclick="hhgxyTj()"> ͳ�� </a></li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="11641">
							<li><a href="#" class="btn_tj" onclick="qtxxTj()"> ͳ�� </a></li>
						</logic:notEqual>
<%--						<li><a href="#" class="btn_yl" onclick="expTab('rsTable','ѧ���ɼ��������ܱ�','')">��ӡ/Ԥ��</a></li>--%>
					</ul>
				</div>
				</logic:equal>
				</div>
		              	
				<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button class="btn_cx" id="search_go" 
			              		onclick="if (document.getElementById('cjlx').value=='') {alert('��ѡ��Ҫ��ѯ�ĳɼ����ͣ�');return;} else allNotEmpThenGo('pjpy_ahjg_xscjqry.do');">
			              		��ѯ
			              		</button>
		             		 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			����
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
								<html:select property="xn" style="width:90px" 
									styleId="xn" styleClass="select">
			
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>			
							<th>
								ѧ��
							</th>
							<td><html:select property="xq" style="width:70px" 
									styleId="xq" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select></td>
							<th>�꼶</th>
							<td>
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
							</html:select></td>
						</tr>
						<tr>
							<th align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td><html:select property="xydm" style="width:180px" styleId="xy"
								 onchange="initZyList();initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
							<th>רҵ</th>
							<td><html:select property="zydm" style="width:180px" styleId="zy"
								 onchange="initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select></td>
							<th>�༶</th>
							<td><html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							
								<!-- ���ݴ�ѧ 200edit by luojw -->
								<logic:equal name="xxdm" value="11078">
								<td>
									<span id="kcxzSp" style="display : none">
									&nbsp;&nbsp;�γ����ʹ�������
									<input type="checkbox" id="iskcxz" name="iskcxz" onclick="checkXz()"/>
									<input type="hidden" id="kcxzTj" name="kcxzTj"/>
									</span>
									</td>
								</logic:equal>
								<!-- ���ݴ�ѧ end -->
							
						</tr>
						<tr>
						<th>�ɼ�����</th>
							<td>
							<html:select property="cjlx" styleId="cjlx" style="width:120px" 
							onchange="refreshForm('pjpy_ahjg_xscjdefault.do')">
								<html:option value=""></html:option>
								<html:option value="cjb">ѧ���ɼ���</html:option>
								<html:option value="djksb">ѧ���ȼ����Ա�</html:option>
							</html:select></td>
						<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh" style="width:95px" styleClass="inputtext"></html:text></td>
								
							<th>����</th>
							<td><html:text property="xm" styleId="xm" style="width:95px" styleClass="inputtext"></html:text>
							</td>
						</tr>
						<logic:equal value="xsdjksb" name="realTable">
							<tr>
						<th>�ȼ���������</th>
							<td>
								<html:select property="djksmc" styleId="djksmc" >
									<html:option value=""></html:option>
									<html:options collection="djksmcList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
							<th></th>
							<td></td>
							</tr>
						</logic:equal>
						
						
						<!-- ���ݴ�ѧ edit by luojw -->
						<logic:equal name="xxdm" value="11078">
						<tr id = "kcxzTr" style="display : none">
							<td colspan="2">
								<logic:iterate name="kcxzList" id="kcxz" indexId="index">
									<input type="checkbox" id="kcxz${index }" name="kcxz" value="${kcxz.mc }"/>${kcxz.dm }&nbsp;&nbsp;
								</logic:iterate>
							</td>
						</tr>
						</logic:equal>
						<!-- ���ݴ�ѧ end -->
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
			   </span>
			</h3>
			</div>
			
			<logic:notEmpty name="rs">
				
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
						<thead>
							<tr align="left" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="left"
								style="cursor:hand;background-color:
    							<logic:iterate id="v" name="s" offset="1" length="1">
    							<bean:write name="v"/>
   					 			</logic:iterate>"
   					 			ondblclick="">							
								<logic:iterate id="v" name="s" offset="0">
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyAhjgActionForm"></jsp:include>
			  	<!--��ҳ��ʾ-->
	
			</logic:notEmpty>
			
	
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
     			 if ($('choose')) {$('choose').className="hide";}
				function jwcjtb(url){
					if (document.getElementById('cjlx').value=='' || 
					document.getElementById('cjlx').value==null
					|| document.getElementById('xn').value=='' || document.getElementById('xn').value==null
					|| document.getElementById('xq').value=='' || document.getElementById('xq').value==null) {
						alert('����������ѧ�꣬ѧ�ڣ��ɼ�����Ϊ��ѡ����ȷ�ϣ�');
						return;
					} else {
						if (confirm('�ɼ�ͬ������ѧ��,ѧ��,�ɼ�����Ϊ��λ��ȡ����ϵͳ�ɼ�!\n�ôβ������ķѽϳ�ʱ��,ȷ��Ҫ������?')) {
							url += document.getElementById('cjlx').value;
							url += '&xn=';
							url += document.getElementById('xn').value;
							url += '&xq=';
							url += document.getElementById('xq').value;
							refreshForm(url);
							BatAlert.showTips('���ڲ�������ȴ�...');
						} else {
							return;
						}
					}
				}
				function cjexp() {
					var xxdm = $("xxdm").value;
					var cjlx = document.getElementById('cjlx').value;
					if (cjlx=='' || cjlx==null) {
						alert('�ɼ�����Ϊ��ѡ��!');
						return;
					} else {
						if("11078" == xxdm){//���ݴ�ѧ
							var checkBoxArr = document.getElementsByName("kcxz");
							var kcxzTj = "";
							for(var i=0;i<checkBoxArr.length;i++){
								if(checkBoxArr[i].checked==true){
									var id = "kcxz"+i;
									kcxzTj += $(id).value + "!!@@!!";
								}
							}
							$("kcxzTj").value = kcxzTj;
						}
						dataExport();
					}
				}
				function dr() {
					var cjlx = document.getElementById('cjlx').value;
					if (cjlx=='' || cjlx==null) {
						alert('�ɼ�����Ϊ��ѡ��!');
						return;
					} else {
						impAndChkData();
					}
				}
				
				</script>
				<logic:present name="inserted">
					<logic:equal value="yes" name="inserted">
						<script>
							alert('�����ɹ���');
						</script>
					</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
							alert('����ʧ�ܣ�');
						</script>
					</logic:equal>
				</logic:present>
	</body>
</html>