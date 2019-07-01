<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script language="javascript">
		function showStu(url){
			var width=840;
			var height = 600;
			var left = (screen.width/2) - width/2;
			var top = (screen.height/2) - height/2;
			var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
			window.open(url,"msgWindow", styleStr);
		}
		
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
	      }
	    }
	    
	    function checkSelect(){
	    	var RowsStr="!!";
	    	for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}	
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ�ύ�ļ�¼��");
				return false;
			}
			return true;
	    }
	    
	    function batch(url){
			var RowsStr="!!";
			var gdzldm = document.getElementById("select_gdzldm").value;	
			var ddqkdm = "";
			if(document.getElementById("ddqkdm")){
				ddqkdm =document.getElementById("ddqkdm").value;
			}
			if(gdzldm=="" || gdzldm==null)	{
				alert("��ѡ��Ҫ�ύ������!");
				return false;
			}
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}	
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ�ύ�ļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫ����������ѡ��¼��")){
				return false;
			}
			
			url += "&operPk=" + RowsStr;
			url += "&gdzldm=" + gdzldm;
			url += "&ddqkdm=" + ddqkdm;
			refreshForm(url);
			return true;
		}
		function commit(url){
			if(curr_row == null){
				alert("��ѡ����Ҫ�����ļ�¼��");
				return false;
			}		
			var xh = curr_row.cells[1].innerText;
					
			url += "&xh=";
			url += xh;
			showTopWin(url,500,650);			
		}
		function viewSfqq(){
			var val ="";
			if($('dm')){
				val = document.getElementById("dm").value;
				if(val == '' || val == null){
					$('sfqq').value='';
					$('sfqq').disabled=true;
					$('gdzldm').disabled=false;
				}else{
					$('sfqq').disabled=false;
				}
			}
			
		}
		function isView(){
			var sfqq = "";
			if($('sfqq')){
				sfqq = document.getElementById("sfqq").value;
				if(sfqq == 'qq'){
					$('gdzldm').value='';
					$('gdzldm').disabled=true;
				}else{
					$('gdzldm').disabled=false;
				}
			}
			
		}
		function getZlb(){
			var userType = $('userType').value;
			var dm = "";
			if($('dm')){
				dm = $('dm').value;
				GetListData.getZlb(userType,dm,loadzlb);
			}
			
		}
		function loadzlb(data){
			var objId = 'gdzldm';
			var gdzldm = $('gdzldm').value;
			DWRUtil.removeAllOptions(objId);
			$(objId).options[0]=new Option("","");			
			DWRUtil.addOptions(objId,data,"gdzldm","gdzlmc");
			$(objId).value=gdzldm;
		}
		onload="check_user();"
		
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('business.do?method=datumCommit')
		}

		function print(){
			var superSearch = $('superSearch').value;
			var xy;
			var nj;

			if('yes' == superSearch){
				var xyArr = getClickXy();
				var njArr = getClickNj();

				if(!(xyArr.length==1 && njArr.length==1)){
					alert('��ѡ�񵥸�ѧԺ���꼶');
					return false;
				}
				xy = xyArr[0];
				nj = njArr[0];
			}else{ 
				xy = $('xy').value;
				nj = $('nj').value;

				if(''==xy || null == xy || ''==nj || null == nj){
					alert("��ѡ��ѧԺ���꼶");
					return false;
				}
			}
			
			var url = '/xgxt/business.do?method=getGdzl&xy='+xy+'&nj='+nj;
			window.open(url);
		}
	</script>
</head>
<body onload="xyDisabled('xy');viewSfqq();isView();getZlb()">
	<html:form action="/business.do" method="post">
		<input type="hidden" name="userType" value="${userType}" id="userType"/>
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}"/>
  		<input type="hidden" id="userName" name="userName" value="${userName}"/>
		<input type="hidden" name="xxdm" value="${xxdm}" id="xxdm"/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<input type="hidden" name="realTable" value="view_gdzl" />
		<input type="hidden" name="tableName" value="view_gdzl" />
		<!-- �߼���ѯ ���� -->
	    <input type="hidden" name="userName" id="userName" value="${userName }"/>
	    <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		<input type="hidden" id="path" name="searchModel.path" value="datumCommit.jsp"/>
		<input type="hidden" id="superSearch" name="superSearch" value="${superSearch}" />

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ������ - �ύ�鵵����</a>
			</p>
		</div>

		<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>					
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="commit('stu_info_add.do?method=datumCommitSignle');return false;" class="btn_ccg">�����ύ</a> </li>
						<li> <a href="#" onclick="if(checkSelect()){viewTempDiv('�����ύ�鵵����','gdzlDiv',300,200);};return false;" class="btn_ccg">�����ύ</a> </li>
					</logic:equal>
					<li> <a href="#" onclick="print();return false;" class="btn_dy">�����嵥</a> </li>
			    </ul>
			  </div>
			  <!-- new �汾 -->
					<logic:equal name="superSearch" value="yes">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
					</logic:equal>
					
					<!-- old �汾 -->
			<logic:equal name="superSearch" value="no">
			  <!--��ѯ����-->
			  <div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
						  <button class="btn_cx" id="search_go"
								onclick="allNotEmpThenGo('business.do?method=datumCommit')">
								�� ѯ
						  </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
				  <tbody>
			      	<tr>
			      		<th>�꼶</th>
						<td>
							<html:select property="nj" styleId="nj"
								onchange="initZyList();initBj();" style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
								<bean:write name="njList" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh" style="width:150px" styleId="xh"></html:text>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" style="width:150px" styleId="xm"></html:text>
						</td>
					</tr>
					<tr>
			      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								onchange="initBj();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" style="width:150px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<!--����ѧԺ-->
						<logic:equal value="11122" name="xxdm">
							<th>���ϱ����</th>
							<td>
							<html:select property="dm" style="width:150px"
								styleId="dm" onchange="viewSfqq();getZlb();">
								<html:option value=""></html:option>
								<html:options collection="zlblbList" property="dm"
									labelProperty="mc" />
							</html:select>
							</td>
							<th>�����Ƿ���ȫ</th>
							<td>
							<html:select property="sfqq" style="width:150px"
								styleId="sfqq" onchange="isView();" disabled="true">
								<html:option value=""></html:option>
								<html:option value="qq">��ȫ</html:option>
								<html:option value="bqq">����ȫ</html:option>
							</html:select>
							</td>
						</logic:equal>
						<!--end����ѧԺ-->						
						<logic:equal value="11407" name="xxdm">
							<th>�������</th>
							<td>
							<html:select property="ddqkdm" styleId="ddqkdm" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="ddqkList" property="dddm" labelProperty="ddmc"/>
							</html:select>
							</td>
						</logic:equal>
					</tr>
				</tbody>
			  </table>
			</div>	</logic:equal>			
	       </div>
		   <div class="formbox">
		    <h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ��</font>
				</logic:notEmpty>
		    </span>
		    </h3>
			   
		  <div class="con_overlfow">
		  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
		    <thead>
		      <tr>
				<td>
					<input type="checkbox" name="gwmc" value="all" onclick="chec()"/>
				</td>
				<logic:iterate id="tit" name="topTr" offset="0">
					<td id="${tit.en}" onclick="tableSort(this)">
						${tit.cn}
					</td>
				</logic:iterate>
		      </tr>
		    </thead>
		    <tbody>
	    		<logic:empty name="rs">
					<%
					for(int i=0; i<11; i++){
					%>
					<tr>
						<td align="center"><input type="checkbox" disabled="disabled"/></td>								
						<logic:iterate id="tit" name="topTr" offset="0">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
				 	</tr>
	
					<%}%>
	 			</logic:empty>
	 			
	 			<logic:notEmpty name="rs">
				<logic:iterate name="rs" id="s">
					<tr onclick="try{rowMoreClick(this,'',event);}catch(e){}" style="cursor:hand"
						ondblclick="try{commit('stu_info_add.do?method=datumCommitSignle&doType=view')}catch(e){}">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pk" value="${v}"/>
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<a href="#" onclick="showStu('/xgxt/stu_info_details.do?xh=${v}');return false;">${v}</a>
							</logic:iterate>
						</td>									
						<logic:iterate id="v" name="s" offset="1">
							<td align="left">
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				<logic:lessEqual value="${pageSize}" name="rsNum">
								<%
								int rsNum = ((List)request.getAttribute("rs")).size();
								int pageSize = (Integer) request.getAttribute("pageSize");
								for(int i=0; i<(pageSize-rsNum); i++){
								%>
								<tr>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
									
							 	</tr>
								<%}%>
							</logic:lessEqual>
		    	</logic:notEmpty>
		    </tbody>
		</table>
		</div>
		<!--��ҳ��ʾ-->
		   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
		<!--��ҳ��ʾ-->
		
		</div>
		
		<div id="gdzlDiv" style="display:none">
		<table width='350' class='formlist'>
		<thead>
		<tr>
		 <th align='center' colspan="2">
		  	��ѡ���ύ�Ĺ鵵����
		 </th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<th>�鵵����</th>
			<td>
				<html:select property="gdzldm" style="width:150px" 
					styleId="select_gdzldm">
					<html:option value=""></html:option>
					<html:options collection="zlList" property="gdzldm"
						labelProperty="gdzlmc" />
				</html:select>
			</td>
		</tr>
		</tbody>	
		<tfoot>
		<tr>
			<td colspan="2">
				<div class='btn'>
					<button class='button2' onclick="batch('business.do?method=datumCommit&doType=save')">ȷ��</button>&nbsp;&nbsp;
					<button class='button2' onclick="hiddenMessage(true,true);">�ر�</button>
				</div>
			</td>
		</tr>
		</tfoot>
		</table>
		</div>

		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
				alert("�����ɹ�!");
				Close();																						
			</script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
				alert("����ʧ��!");
			</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>

</body>
</html>

