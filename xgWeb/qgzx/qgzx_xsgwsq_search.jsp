<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function printYyhz(){
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			if(xn == null || xn == '' || xq == null || xq == ''){
				alert('ѧ�꣬ѧ��Ϊ��ѡ��');
				return false;
			}
			document.forms[0].action = "/xgxt/qgzxNblg.do?method=printYyhz";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//ѧ��ѧ������
		function queryOne(xh) {
			var url = "/xgxt/stu_info_details.do?xh="+xh;
			showTopWin(url, 800, 600);
		}
		
		//����
		function add(){
			var xxdm = document.getElementById('xxdm').value;
			if(xxdm == '10402'){//����ʦ��<bean:message key="lable.xsgzyxpzxy" />
				showTopWin('qgzxZzsf.do?method=addXsgwxx&type=add',700,500);
			}else{
				viewMore('add');
			}
		}
		//�޸�
		function modi(){
			var xxdm = document.getElementById('xxdm').value;
			if(xxdm == '10402'){//����ʦ��<bean:message key="lable.xsgzyxpzxy" />
				if(curr_row == null){
					alert('��ѡ��һ����Ҫ�޸ĵļ�¼��');
					return false;
				}
				var xmdm =  curr_row.cells[1].getElementsByTagName("input")[0].value;
				showTopWin('qgzxZzsf.do?method=modiXsgwxx&type=modi'+ '&xmdm='+xmdm,700,500);
			}else if(xxdm == '10628#'){//����ѧԺ
				if(curr_row == null){
					alert('��ѡ��һ����Ҫ�޸ĵļ�¼��');
					return false;
				}
				var xmdm =  curr_row.cells[1].getElementsByTagName("input")[0].value;
				showTopWin('qgzxXcxy.do?method=modiXsgwxx&type=modi'+ '&xmdm='+xmdm,700,500);
			}else{
				if (curr_row == null) {
					alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
					return false;
				}
				if(xxdm=='11654'){
					if(trim(curr_row.cells[10].innerHTML)=='ͨ��' || trim(curr_row.cells[11].innerHTML)=='ͨ��' || trim(curr_row.cells[12].innerHTML)=='ͨ��'){
						alert('���������¼������ˣ������޸ģ�');
						return false;
					}
				}
				//�㽭��ְͨҵ����ѧԺ
				if(xxdm=='12036'){
					if(trim(curr_row.cells[10].innerHTML)=='ͨ��' || trim(curr_row.cells[11].innerHTML)=='ͨ��' || trim(curr_row.cells[12].innerHTML)=='ͨ��'){
						alert('���������¼������ˣ������޸ģ�');
						return false;
					}
				}
				
				var url = "/xgxt/post_stu_apply.do?realTable=";
				var tmp = document.forms[0].realTable.value;
				var pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += tmp;
				url += "&doType=modi";
				url += "&tableName=";
				url += document.forms[0].tableName.value;
				url += "&pk=";
				url += document.forms[0].pk.value;
				url += "&from=";
				url += document.forms[0].act.value;
				url += "&pkValue=";
				url += pkValue;
				showTopWin(url,800,600);
				//viewMore('modi');
			}
		}
		//ɾ��
		function dataDel(url){
			var xxdm = document.getElementById('xxdm').value;
			var RowsStr="!!";	
			var realTable = document.getElementById('realTable').value;	
			var mes = "ȷ��Ҫ������ѡ��¼��";
			var shzt = '';
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    		//�人��ҵ
		    		if(xxdm=='11654'){
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[10].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[11].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[12].innerHTML);
		    		}
		    		//�㽭��ְͨҵ����ѧԺ
		    		if(xxdm=='12036'){
		    			shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[10].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[11].innerHTML);
			    		shzt += trim(document.getElementsByName("pkV")[i].parentNode.parentNode.childNodes[12].innerHTML);
		    		}
		    	}
			}
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			//�人��ҵ
			if(xxdm=='11654'){
				if(shzt.indexOf('ͨ��')>=0){
					alert('ѡ�������¼���в���������ˣ�����ɾ����');
					return false;
				}
			}
			//�㽭��ְͨҵ����ѧԺ
			if(xxdm=='12036'){
				if(shzt.indexOf('ͨ��')>=0){
					alert('ѡ�������¼���в���������ˣ�����ɾ����');
					return false;
				}
			}
			
			if(realTable != null && "gwxxb" == realTable){
				mes = "ɾ����λ��ɾ����λ�µ�����ѧ����Ϣ��ȷ��ɾ����";
			}
			
			if (!confirm(mes)){
				return false;
			}
			
			url += "&pkString=";
			url += RowsStr;
			url += "&page=";
			url += "page";//��ת����λ��ѯҳ��
			refreshForm(url);
		}
		
		//��ӡ����
		function printBb(url){
			
			var len = jQuery("[name=pkV]:checked").length;
			
			if(len!=1){
				alertInfo("�빴ѡһ����Ҫ��ӡ�ļ�¼!");
			}else{
				
				var pkValue=jQuery("[name=pkV]:checked").val();
				
				var ie = 'ie';
				
				url+="&pkValue="+pkValue;
				
				if(ie == "5.6"){
					confirmInfo("����IE�汾δIE6���汾���ͣ����ܵ��´�ӡ��ʽ���⣬����ʹ��IE8�������Ƿ�Ҫ�ڹ�������������ȡ�����Լ����ñ��������ӡ��",askInfo);
				}else{
					document.forms[0].action = url;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
					
				}
				
			}
		}
		
		
	</script>
</head>
<body   
	<logic:equal value="13108" name="xxdm">onload="xyDisabled('xy-xy');"</logic:equal><logic:equal value="11654" name="xxdm">onload="xyDisabled('xy');"</logic:equal> >
	<html:form action="/qgzxLogic.do" method="post">
		
		<logic:equal value="11654" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
		</logic:equal>
		<logic:notEqual value="11654" name="xxdm" scope="session">
			<!-- �㽭��ְͨҵ����ѧԺ -->
			<logic:equal value="12036" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="${FdyQx}" />
			<input type="hidden" id="isBzr" name="isBzr" value="${BzrQx}" />
			</logic:equal>
			<!-- �㽭��ְͨҵ����ѧԺend -->
			<logic:notEqual value="12036" name="xxdm" scope="session">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="${FdyQx}" />
			<input type="hidden" id="isBzr" name="isBzr" value="${BzrQx}" />
			</logic:notEqual>
		</logic:notEqual>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em>
				<a>
					${title}<logic:empty name="title"> �ڹ���ѧ-����-��������ѯ</logic:empty>
				</a>
			</p>
		</div>
		<logic:equal value="view_xslxfszsxx" name="tableName">
			<logic:equal value="stu" name="userType">
				��ҳ��ֻ��ѧУ��<bean:message key="lable.xsgzyxpzxy" />�û����Է���
			</logic:equal>
		</logic:equal>

		<div class="toolbox">
		  <!-- ��ť -->
		  <div class="buttonbox">
		    <ul>
				<logic:equal value="yes" name="writeAble">
				<!--�ǹ��ݴ�ѧ-->
				<logic:notEqual value="11078" name="xxdm">
					<li> <a href="#" onclick="add()" class="btn_zj">�� ��</a> </li>
					<li> <a href="#" onclick="modi()" class="btn_xg">�� ��</a> </li>
				</logic:notEqual>
				<!--end�ǹ��ݴ�ѧ-->
				<logic:equal value="11078" name="xxdm">
					<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=modiSfyxFlag')" class="btn_sz">���Ϊ��Ч</a> </li>
				</logic:equal>
					<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=delStuPost')" class="btn_sc">ɾ ��</a> </li>
				<logic:notEqual value="no" name="xydel">
<!--					<li> <a href="#" onclick="Alldel()" class="btn_sc">ȫ��ɾ��</a> </li>-->
				</logic:notEqual>
				<%--����ְҵ����ѧԺ--%>
				<logic:equal value="12872" name="xxdm">
					<logic:notEqual value="xy" name="userType">
						<li> <a href="#" onclick="impAndChkData()" class="btn_dr">��������</a> </li>
					</logic:notEqual>
				</logic:equal>
				<%--end����ְҵ����ѧԺ--%>
				<logic:notEqual value="12872" name="xxdm">
					<li> <a href="#" onclick="impAndChkData()" class="btn_dr">��������</a> </li>
				</logic:notEqual>
				<li> <a href="#" onclick="showExportDIV('/xgxt/expData.do');" class="btn_dc">��������</a> </li>
				<%--���Ϲ�ҵ��ѧ�ڹ���ѧ--%>
				<logic:equal value="11535" name="xxdm">
				<logic:equal value="xx" name="userType1">
					<li> <a href="#" onclick="getExcel()" class="btn_dc">�� �� ��</a> </li>
					<li> <a href="#" onclick="modiBzInfo()" class="btn_xg">�޸ı�ע</a> </li>
				</logic:equal>
				<logic:equal value="admin" name="userType1">
					<li> <a href="#" onclick="getExcel()" class="btn_dc">�� �� ��</a> </li>
					<li> <a href="#" onclick="modiBzInfo()" class="btn_xg">�޸ı�ע</a> </li>
					
				</logic:equal>
				</logic:equal>
				<%--end���Ϲ�ҵ��ѧ�ڹ���ѧ--%>
				</logic:equal>

				<%--�㽭��ѧ������ѧԺ--%>					
				<logic:equal name="xxdm" value="13022">
					<li> <a href="#" onclick="" class="btn_sz">��Ա���ܱ�</a> </li>
				</logic:equal>
				<logic:equal name="xxdm" value="66666">
					<li> <a href="#" onclick="printBb('qgzxGxls.do?method=printQgzxsq')" class="btn_dy">�� ӡ</a> </li>
				</logic:equal>
		    </ul>
		  </div>
		  <!--��ѯ����-->
		  <logic:notEqual value="student" name="userOnLine" scope="session">
		  <div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
					  <button type="button" class="btn_cx" id="search_go"
							onclick="allNotEmpThenGo('qgzxLogic.do?method=searchXsgwxx');">
							��ѯ
					  </button>
					  <button type="button" id="cz"
							onclick="searchReset();return false;">
							����
					  </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:select property="xn" styleId="xn" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
					<th>���</th>
					<td>
						<html:select property="nd" styleId="nd" style="width:180px">
							<html:options collection="xnList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<html:select property="xq" styleId="xq" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>			
				</tr>
		      	<tr>
					<th>�꼶</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>			      					
					<th>ѧ��</th>
					<td>
						<html:text property="xh" style="width:180px"></html:text>
					</td>
					<th>����</th>
					<td>
						<html:text property="xm" style="width:180px"></html:text>
					</td>
				</tr>
				<tr>
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<logic:equal value="xy" name="userType">
							<logic:equal value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value="" ></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" 
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()" disabled="true">
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xydm" id="xydm" value="<bean:write name="userDep1"/>"/>
								</logic:notEqual>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
						
					</td>
					<logic:notEqual value="xyhj" name="xyhjType">
					<th>רҵ</th>
					<td>
						<!-- �人��ҵ����Աʱ��������Ͻ���ݷ�Χ -->
						<logic:equal value="11654" name="xxdm" scope="session">
							<html:select property="zydm"  styleId="zy" style="width:200px"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</logic:equal>
						<logic:notEqual value="11654" name="xxdm" scope="session">
							<!-- �㽭��ְͨҵ����ѧԺ -->
							<logic:equal value="12036" name="xxdm" scope="session">
								
								<html:select property="zydm"  styleId="zy" style="width:200px"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</logic:equal>
							<!-- �㽭��ְͨҵ����ѧԺ end -->
							<logic:notEqual value="12036" name="xxdm" scope="session">
								<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
									<logic:notEqual value="true" name="isFdy">
										<html:option value=""></html:option>
								     </logic:notEqual>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</td>
					<th>�༶</th>
					<td>
						<!-- �人��ҵ����Աʱ��������Ͻ���ݷ�Χ -->
						<logic:equal value="11654" name="xxdm" scope="session">
							<html:select property="bjdm"  styleId="bj" style="width:200px">
								<logic:notEqual value="yes" name="isBzr">
								<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual value="11654" name="xxdm" scope="session">
							<!-- �㽭��ְͨҵ����ѧԺ -->
							<logic:equal value="12036" name="xxdm" scope="session">
								<html:select property="bjdm"  styleId="bj" style="width:200px">
								<logic:notEqual value="yes" name="isBzr">
								<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:equal>
							<!-- �㽭��ְͨҵ����ѧԺ end -->
							<logic:notEqual value="12036" name="xxdm" scope="session">
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<logic:notEqual value="yes" name="isBzr">
										<logic:notEqual value="true" name="isFdy">
										<html:option value=""></html:option>
										</logic:notEqual>
									</logic:notEqual>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</td>
					</logic:notEqual>
				</tr>
				<tr>
					<th>���˵�λ</th>
					<td>
						<html:select property="yrdwdm" styleId="yrdwdm" onchange="loadGwByYrdw(this.value,'gwdm')" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc" />
						</html:select>
					</td>			      					
					<th>��λ����</th>
					<td>
						<html:text property="gwdm" styleId="gwdm" style="width:180px"></html:text>
					</td>
					<!--���ݴ�ѧ-->
					<logic:equal value="11078" name="xxdm">
					<th>�Ƿ���Ч</th>
					<td>
						<html:select property="sfyx" styleId="sfyx">
						<html:option value=""></html:option>
						<html:option value="��Ч">��Ч</html:option>
						<html:option value="��Ч">��Ч</html:option>
						</html:select>
					</td>
					</logic:equal>	
					<!--end���ݴ�ѧ-->
					<logic:notEqual value="11078" name="xxdm">
					<th>��λ����</th>
					<td>
						<html:select property="gwxz" styleId="gwxz">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
						</html:select>
					</td>
					</logic:notEqual>
				</tr>
				<!--����ѧԺ-->
					<logic:equal value="10395" name="xxdm">
					<tr>
			      		<th>��λ���</th>
						<td>
							<html:select property="gwflag" style="width:180px" styleId="gwflag">
								<html:option value=""></html:option>
								<html:option value="xngw">У�ڸ�λ</html:option>
								<html:option value="xwgw">У���λ</html:option>
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
			  </tbody>
			</table>
	      </div>	
		</logic:notEqual>	
		</div>	

		<div class="formbox">
		    <h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
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
					<td id="${tit.en}"
						onclick="tableSort(this)" >
						${tit.cn}
					</td>
				</logic:iterate>
		      </tr>
		    </thead>
		    <tbody>
				<%--�й���ҵ��ѧ--%>
				<logic:equal value="10290" name="xxdm">
					<logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
						ondblclick="viewMore('view');">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" name="" value="${v}"/>
								<input type="checkbox" name="pkV" value=""/>								
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="${v}" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								${v}
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2" length="2">
							<td>
								${v}
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="4" length="1">
							<td>
								<a href="#" onclick="queryOne('${v}')">${v}</a>
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="5">
							<td>
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				</logic:equal>	
				<%--end�й���ҵ��ѧ--%>
				
				<%--���й���ҵ��ѧ--%>
				<logic:notEqual value="10290" name="xxdm">
				<!-- �㽭��ְͨҵ����ѧԺ -->
					<logic:equal value="12036" name="xxdm">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="viewMore('view');">
								<td>
									<input type="checkbox" <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate> name="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v}" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v}
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										${v}
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
				<!-- �㽭��ְͨҵ����ѧԺend -->
				<logic:notEqual value="12036" name="xxdm">
					<logic:iterate name="rs" id="s">
					<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
						ondblclick="viewMore('view');">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pkV" value="${v}"/>
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="${v}" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								${v}
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2">
							<td>
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				</logic:notEqual>
				</logic:notEqual>
				<%--end���й���ҵ��ѧ--%>
		    </tbody>			
		</table>
		</div>
		<!--��ҳ��ʾ-->
		   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
		<!--��ҳ��ʾ-->
		</logic:notEmpty>
		</div>		
		
	<div id="tmpdiv"></div>
	</html:form>
	<logic:equal name="result" value="true">
		<script language="javascript">
     				alert("�����ɹ���");
  		</script>
	</logic:equal>
	<logic:equal name="result" value="false">
		<logic:notEmpty name="mes">
			<input name="mes" id="mes" value="${mes}" />
			<script>
				alert(document.getElementById('mes').value);
			</script>
		</logic:notEmpty>
		<logic:empty name="mes">
			<script language="javascript">
  				alert("����ʧ��! ");
  		</script>
		</logic:empty>
	</logic:equal>
	<!--ҳ�������棺-->
	<script language="javascript"  defer="defer">
		var b = false;;
		if(document.getElementById('rsTable')){
			for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
		  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
		  			b = true;
		  			break;
		  		}
		  	}
		  	if (b) {
		  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
		  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
		  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
		  		    var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
		  			xhTd.innerHTML = html_content;
		  		}
		  	}
		}
   </script>
</body>
</html>
