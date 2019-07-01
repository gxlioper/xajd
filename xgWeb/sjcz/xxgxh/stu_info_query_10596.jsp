<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<%@ include file="/syscommon/pagehead_xg.ini"%>	
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxtjDWR.js'></script>			
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>		
	<script language="javascript" src="js/webPrint.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>	
	<script>
		function jtxxDr(){
			$('realTable').value='xsfzxxb';
			impAndChkData();
			$('realTable').value='xsxxb';
			return false;
		}
	
	
		function ljsdaUpdate(url,w,h){	
			var pk="";	
			if(curr_row == null ){
					alert("��ѡ��һ�м�¼��\n����һ�м���!");
					return false;
			} 	
					
			pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
			url+=pk;
			//�ж��Ƿ���У��
			url+="&sfzxk="+$("sfzxk").value;
			if(w==""||w==null){
				w = 800;
			}
			if(h==""||h==null){
				h = 600;
			}
			showTopWin(url,w,h);		
		}
		
		function addInfo(){
			if(curr_row == null){
				alert("�˲�����Ҫ��ѡ�е��У�����Ҫ��ӱ�ע���У�");
				return false;
			}
			showTopWin("addStuInfo.do?xh=" + curr_row.cells[1].innerText,400,300,false);
		}
	
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	if(!document.getElementsByName("pk")[i].disabled){
	      		document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
	      	}
	      }
	    }
	   
	       
		function batch(url,oper){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
		    	}
			}
			document.forms[0].delPk.value = RowsStr;
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫ������ѡ��¼��")){
				return false;
			}
			if(oper=="del"){
				refreshForm(url);
			}else{
				url += "&pk=";
				url += RowsStr;
				showTopWin(url,400,300);
			}
		}
	
		function batchOper(url){
			var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("pk").length; i++){
			    	if(document.getElementsByName("pk")[i].checked){
			    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			    	}
				}
				document.forms[0].delPk.value = RowsStr;
				
				if (RowsStr=="!!"){
					alert("��ѡ��Ҫ���������ļ�¼��");
					return false;
				}
				
				if (!confirm("ȷ��Ҫ������ѡ��¼��")){
					return false;
				}
				
				url += "&pk=";
				url += RowsStr;
				refreshForm(url);	
		}
	
		function check_user(){
		
			var user=document.all['userType'].value;
			
			if($("xydm")){
				if("xy"==user){
					document.getElementById('xydm').disabled=true;
				}else if("xx"==user){
					document.getElementById('xydm').disabled=false;
				}
			}
		}
		
		function xy_dataExport1(){
			var url='/xgxt/expData.do?tableName=view_xsjbxx';
			var xydm=document.getElementById('xydm').value;
			url=url+'&xydm='+xydm;
			dataExport1(url);
		}
		
		function showTr(){
			var xxdm = document.getElementById("xxdm").value;
			var length = 12;
			for(var i=0; i<length; i++){
				if(ele('temDiv'+(i+1))){
					ele('temDiv'+(i+1)).style.display=(ele('temDiv'+(i+1)).style.display =='none')?'':'none';
				}
			}
		}		
		
		function getValue(){
			if(document.getElementById("jgshen").value!=""){
				var shenV = document.getElementById("jgshen").options[document.getElementById("jgshen").selectedIndex].text;
				document.getElementById("jgz").value='%' + shenV + '%';
			}
			if(document.getElementById("jgshi").value!=""){
				var shiV = document.getElementById("jgshi").options[document.getElementById("jgshi").selectedIndex].text;
				document.getElementById("jgz").value += '%' + shiV + '%';
			}
			if(document.getElementById("jgxian").value!=""){
				var xianV = document.getElementById("jgxian").options[document.getElementById("jgxian").selectedIndex].text;
				document.getElementById("jgz").value += '%' + xianV + '%';
			}
		}
		
		function writeCondition(){			
			var xxdm = document.getElementById("xxdm").value;
			ele = 'nj-xh-xm-xy-zy-bj-xz-xjztm-sfzx-sfyby-byny-pycc-xb-sfzh-ssbh-csrq-sjhm-jtcyxm-mz-zzmm-jg-syd-ksh-jgshen-jgshi-jgxian-sdyshen-sydshi-sydxian-sfgat-sfssmz-sfzxk';
			var strs = ele.split('-');						
			var tmp = "";
			for(var i=0; i<strs.length; i++){
				if(document.getElementById(strs[i]) && document.getElementById(strs[i]).value!= undefined){
					if(document.getElementById(strs[i]).value != ''){						
						if(strs[i] == 'xy'){
							tmp += "@xydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'zy'){
							tmp += "@zydm!!" + document.getElementById(strs[i]).value;
						}else if(strs[i] == 'bj'){
							tmp += "@bjdm!!" + document.getElementById(strs[i]).value;
						}else{
							tmp += "@" + strs[i]+ "!!" + document.getElementById(strs[i]).value;
						}
					}
				}
			}
			
			document.getElementById('condition').value = tmp;
		}
		
		
		function zjjd_stuTable(type){
		    var bjdm = "";
		    if($("bjdm")){
		       bjdm = $("bjdm").value;
		    }
		    if(bjdm==""){
		       alert("��ѡ��Ҫͳ�ư༶��");
		       return false;
		    }
		   	var url = "/xgxt/stu_info_add.do?method=zjjd_zjjd_stuTable&tableType="+type;
		   	document.forms[0].action = url;
		   	document.forms[0].target = "_blank";
		   	document.forms[0].submit();
		  	 document.forms[0].target = "_self";
		}
	   
	   //��ְԺ��ӡ�ɼ����浥
	   function hzy_cjbgdprint(){
	      	var RowsStr="";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
			 if(document.getElementsByName("pk")[i].checked){
			    RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			 }
		}
		document.forms[0].delPk.value = RowsStr;				
		if (RowsStr==""){
			alert("��ѡ��Ҫ��ӡ�ļ�¼��");
			return false;
		}
		if(RowsStr.split("!!").length-1>2){
			alert("һ��ֻ�ܴ�ӡ������¼��");
			return false;
		}
		var url = "/xgxt/cjbgdPrint.do?method=hzy_cjbgdprint&pkValue="+$("delPk").value;
		showOpenWindow(url,800,600);		
	   }
	   
	   //��ӡ�ɼ���
	   function printCjd(){
	   	//�ж��Ƿ�ѡ��������
	   	var RowsStr="";		
		for (i=0; i<document.getElementsByName("pk").length; i++){
			 if(document.getElementsByName("pk")[i].checked){
			    RowsStr+=document.getElementsByName("pk")[i].value+"!!";
			 }
		}
		if (RowsStr==""){
			alert("��ѡ��Ҫ��ӡ�ļ�¼��");
			return false;
		}
		var url = "xsxxgl.do?method=czxxCjdPrint&pkV=" + RowsStr;
		showOpenWindow(url,800,600);
	   }
	   
	   function init(){
	   	if(val('ctypeV') != null && val('ctypeV') != ''){
	   		ele('ctype').checked=true;
	   		showTr();
	   	}
	   }
	   
	   function printXjqrd(){
	   
	   		var checkbox = jQuery('input[type=checkbox][name=pk]:checked');
	   		
	   		if (checkbox.length > 0){
	   		
	   			var url = "moralCard.do?method=xjqrdPrint";
			   	document.forms[0].action = url;
			   	document.forms[0].target = "_blank";
			   	document.forms[0].submit();
		  	 	document.forms[0].target = "_self";
	   		
	   		} else {
	   			alert('��ѡ����Ҫ��ӡѧ��ȷ�ϵ���ѧ��!');
	   			return false;
	   		}
	   }
	   
	   //��ӡѧ����Ϣ������
	   function printXsxxk(){
	   		
	   		var len=jQuery("[name=pk]:checked").length;
	   		if(len!=1){
	   			alertInfo("�빴ѡһ��ѧ����Ϣ���ٽ��иò�����");
	   			return false;
		  	}else{
		   		var xh=jQuery("[name=pk]:checked").eq(0).val();
		  		var url = "xsxx_gdjs_xsxxk.do?method=printXsxxExcel&xh="+xh;
			   	document.forms[0].action = url;
			   	document.forms[0].target = "_blank";
			   	document.forms[0].submit();
		  	 	document.forms[0].target = "_self";
		  	}
	   }
	   
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo&isChecked=false');
		}
	</script>

</head>
	<body onload="check_user();initXnList();init();">		
			<html:form action="/stu_info_query.do?method=stuInfo" method="post">
				<div id="tmpdiv"></div>
				<div style="display:none">
					<select id='xn' name='xn' style="display:none"></select>
					<html:select property="xq" style="display:none" styleId="xq">
						<html:option value="">--��ѡ��--</html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
				</div>

				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
				<input type="hidden" id="userType" name="userType" value="${userSpecType }" />
				<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
				<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
				<input type="hidden" id="userName" name="userName" value="${userName }" />
				<input type="hidden" id="ctypeV" value="${ctype}"/>

				<logic:equal value="yes" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="xsxxb"/>
				</logic:equal>
				<logic:equal value="no" name="userOper">
					<input type="hidden" id="realTable" name="realTable" value="bks_xsjbxx"/>
				</logic:equal>
				<input type="hidden" id="tableName" name="tableName" value="view_xsxxb"/>  
				<input type="hidden" id="delPk" name="delPk" value="pk" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<input type="hidden" name="sfzxk" id="sfzxk" value="${sfzxk}" />
				<input type="hidden" name="jgz" value="" />
				<input type="hidden" name="mes" value="${mes}" />
				<input type="hidden" name="condition" value="" id='condition' />
				<input type="hidden" name="path" value="${path }" />
				
				<!-- �߼���ѯ ���� -->
				<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
				<logic:notEmpty name="sfzxk">
					<input type="hidden" id="path" name="searchModel.path" value="stu_info_query.do?method=stuInfo&sfzxk=no"/>
				</logic:notEmpty>
				<logic:empty name="sfzxk">
					<input type="hidden" id="path" name="searchModel.path" value="stu_info_query.do?method=stuInfo"/>
				</logic:empty>
				
		        <div class="tab_cur">
		          <p class="location"> <em>���ĵ�ǰλ��:</em>
			          <a>
			          <logic:notEmpty name="title">${title}</logic:notEmpty>
			          <logic:empty name="title">ѧ����Ϣ-ѧ����Ϣ-��ʷѧ����Ϣ</logic:empty>
			          </a>
		          </p>
		        </div>

				<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
					  <!--ѧ����Ϣ��ά��-->
					  <logic:notEmpty name="userOper">
					  <logic:equal name="userType" value="admin" >
						<li><a href="#" onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);return false;" class="btn_zj">�� ��</a></li>
						<li><a href="#" onclick="batch('stu_info_add.do?method=stuInfoDelete','del');return false;" class="btn_sc">ɾ ��</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">���������Ϣ</a></li>	
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a></li>						  
					  </logic:equal>
					  <logic:equal name="userType" value="xx">
						<li><a href="#" onclick="showTopWin('stu_info_add.do?method=showStuInfo&oper=add',800,600,false);return false;" class="btn_zj">�� ��</a></li>											
						<li><a href="#" onclick="batch('stu_info_add.do?method=stuInfoDelete','del');return false;" class="btn_sc">ɾ ��</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">���������Ϣ</a></li>	
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a></li>					  
					  </logic:equal>
					  <logic:notEqual name="userType" value="admin">
					  <logic:notEqual name="userType" value="xx">
					  	<li><a href="#" onclick="ljsdaUpdate('stu_info_add.do?method=showStuInfo&oper=update&xh=',800,600);return false;" class="btn_xg">�� ��</a></li>
					  	<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>	
					  </logic:notEqual>
					  </logic:notEqual>
					  </logic:notEmpty>
					  <!--endѧ����Ϣ��ά��-->
					  </logic:equal>
					  <!--end��дȨ-->
		
				    </ul>
				  </div>				  
				  <!-- end��ť -->
				</div>

				<!-- ������ʾ����ʼ -->
				<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
					
					<!-- new �汾 -->
					<logic:equal name="superSearch" value="yes">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
					</logic:equal>
					
					<!-- old �汾 -->
					<logic:equal name="superSearch" value="no">
					<!-- From���� -->
<!--							<div style="float:left;">-->
<!--								<div class="listbox" style="width:155px;float:left">-->
<!--									<div class="menulist">-->
<!--									��start-->
<!--									<div class="menutitle">-->
<!--									    <h4 style="height:30px;line-height:30px;padding-left:40px;">�����б�</h4>-->
<!--									<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
<!--									<ul>-->
<!--									  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
<!--									</ul>-->
<!--									</div>-->
<!--									<script type="text/javascript">-->
<!--									-->
<!--									var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
<!--									//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
<!--			 						-->
<!--									</script>-->
<!--									</div>-->
<!--									��end-->
<!--									</div>-->
<!--								</div>-->
<!--							</div>-->
<!--							<div style="float:right;width:630px;">-->
							  <div class="searchtab">
								<table width="100%" border="0">
							      <tfoot>
							        <tr>
									  <th nowrap="nowrap">
										<input type="checkbox" name="ctype" value="checked" onclick="showTr()" id="moreTj"/>						
										��������
									  </th>
							          <td colspan="5">
							            <div class="btn">
										  <input type="hidden" name="go" value="a" />
							              <button type="button" class="btn_cx" title="" id="search_go" onclick="allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo&isChecked='+$('moreTj').checked);">
											�� ѯ
										  </button>
										  <button type="button" id="cz"
												onclick="czSearchCond('nj-xh-xm-xy-zy-bj-xz-xjztm-sfzx-sfyby-byny-pycc-xb-sfzh-ssbh-csrq-sjhm-jtcyxm-jtdzshen-jtdzshi-jtdzxian-qqhm-mz-zzmm-jg-jgshen-jgshi-jgxian-sfb-jfqk-hkszd-sydshen-sydshi-sydxian-ksh-syd-sfgat-sfssmz');return false;">
												�� ��
										  </button>
							            </div>
							          </td>
							        </tr>
							      </tfoot>
								  <tbody>
								  
							      <tr id="gdDiv1">
									<th width="15%">�꼶</th>
									<td width="15%">
										<%--����ְҵ����ѧԺ--%>
										<logic:equal value="12872" name="xxdm">
											<%--����У��--%>
											<logic:notEmpty name="sfzxk">
												<logic:equal value="no" name="sfzxk">
													<html:select property="nj" styleId="nj"
														onchange="initZyList();initBj('by');" style="width:80px">
														<html:option value=""></html:option>
														<html:options collection="njList" property="nj"
															labelProperty="nj" />
													</html:select>
												</logic:equal>
											</logic:notEmpty>
											<%--end����У��--%>

											<logic:empty name="sfzxk">
												<html:select property="nj" styleId="nj"
													onchange="initZyList();initBj('zx');"  style="width:80px">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</logic:empty>
										</logic:equal>
										<%--end����ְҵ����ѧԺ--%>
			
										<logic:notEqual value="12872" name="xxdm">
											<html:select property="nj" styleId="nj"
												onchange="initZyList();initBjList();"  style="width:80px">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</logic:notEqual>									
									</td>
									<th width="15%">
										ѧ��
									</th>
									<td width="15%">
										<html:text style="width:150px"
											property="xh"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='xh' />
									</td>
									<th width="15%">
										����
									</th>
									<td width="">
										<html:text style="width:150px"
											property="xm"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='xm' />
									</td>
								</tr>					
								<tr id="gdDiv2">
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<%--����ְҵ����ѧԺ--%>
										<logic:equal value="12872" name="xxdm">
											<logic:notEmpty name="sfzxk">
												<logic:equal value="no" name="sfzxk">
													<html:select property="xydm" 
														styleId="xy" onchange="initZyList();initBj('by');" style="width:150px">
														<html:option value=""></html:option>
														<html:options collection="xyList" property="xydm"
															labelProperty="xymc" />
													</html:select>
												</logic:equal>
											</logic:notEmpty>
											<logic:empty name="sfzxk">
												<html:select property="xydm"  styleId="xy"
													onchange="initZyList();initBj('zx');" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:empty>
										</logic:equal>
										<%--end����ְҵ����ѧԺ--%>
			
										<logic:notEqual value="12872" name="xxdm">
											<html:select property="xydm"  styleId="xy"
												onchange="initZyList();initBjList();" style="width:150px">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
									</td>
									<th>
										רҵ
									</th>
									<td>
									<%--����ְҵ����ѧԺ--%>
									<logic:equal value="12872" name="xxdm">
										<logic:notEmpty name="sfzxk">
											<logic:equal value="no" name="sfzxk">
												<html:select property="zydm" 
													styleId="zy" onchange="initBj('by');" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</logic:equal>
										</logic:notEmpty>
										<logic:empty name="sfzxk">
											<html:select property="zydm" styleId="zy" style="width:150px"
												onchange="initBj('zx');">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</logic:empty>
									</logic:equal>
									<%--end����ְҵ����ѧԺ--%>
			
									<logic:notEqual value="12872" name="xxdm">
										<html:select property="zydm"  styleId="zy" style="width:150px"
											onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</logic:notEqual>
									</td>
									
									<th>
										�༶
									</th>
									<td>
										<html:select property="bjdm" styleId="bj" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr id="temDiv3" style="display:none">
									<th>
										ѧ��
									</th>
									<td>							
										<html:select property="xz" styleId="xz" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xzList" property="xz" labelProperty="xz"/>
										</html:select>
									</td>
									<th>
										ѧ��״̬
									</th>
									<td>
										<html:select property="xjzt" styleId="xjztm" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
										</html:select>
									</td>
									<th>
										�Ƿ���У
									</th>
									<td>
										<%--�����������ѧ--%>
										<logic:notEqual value="10742" name="xxdm">
										<html:select property="sfzx" styleId="sfzx" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="��У">��У</html:option>
											<html:option value="����У">����У</html:option>
										</html:select>
										</logic:notEqual>
										<%--end�����������ѧ--%>
			
										<%--���������ѧ--%>
										<logic:equal value="10742" name="xxdm">
											<html:select property="sfzx" style="width:80px">
												<html:option value=""></html:option>
												<html:option value="1">��У</html:option>
												<html:option value="0">����У</html:option>
											</html:select>
										</logic:equal>
										<%--end���������ѧ--%>
									</td>				
								</tr>
								<tr id="temDiv4" style="display:none">
									<th>
										�Ƿ��ҵ
									</th>
									<td>
										<html:select property="sfyby" styleId="sfyby" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>			
									</td>
									<th>
										��ҵʱ��
									</th>
									<td>
										<html:text property="byny" styleId="byny" style="width:150px"
										onclick="return showCalendar('byny','y-mm-dd');" />
									</td>
									<th>
										�������
									</th>
									<td>
										<html:text property="pycc" 
												styleId="pycc"
												style="width:150px"
												onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
									</td>
								</tr>
								<tr id="temDiv5" style="display:none">
									<th>
										�Ա�
									</th>
									<td>
										<html:select property="xb" styleId="xb" style="width:80px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="Ů">Ů</html:option>
										</html:select>
									</td>
									<th>
										���֤��
									</th>
									<td>
										<html:text property="sfzh" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="sfzh"  onkeyup="value=value.replace(/[^\d|X|x]/g,'')" />
									</td>
									<th>
										������
									</th>
									<td>
										<html:text property="ssbh" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='ssbh' />
									</td>						
								</tr>
								
								<tr id="temDiv6" style="display:none">
									<th>
										��������
									</th>
									<td>
										<html:text property="csrq" style="width:150px"
												onclick="return showCalendar('csrq','y-mm-dd');"
												styleId="csrq" onclick="return showCalendar('csrq','y-mm-dd');"/>
									</td>
									<th>
										�ֻ�����
									</th>
									<td>
										<html:text property="sjhm" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="sjhm"  onkeyup="value=value.replace(/[^\d]/g,'')"/>
									</td>
									<th>
										��ͥ��Ա����
									</th>
									<td>
										<html:text style="width:150px"
											property="jtcyxm"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');" />
									</td>
								</tr>
								<%--��ɽʦ��ѧԺ��ʾʡ����ȫ��ͳһ���������˵���ͥ��ϸ��ַ--%>
								<logic:equal value="10649" name="xxdm">
								<tr id="temDiv7" style="display:none">
									<th>
										��ͥ��ϸ��ַ
									</th>
									<td colspan="3">
										<html:select 
										property="jtdzshen" styleId="jtdzshen"
										onchange="loadShi('jtdzshen','jtdzshi','jtdzxian')">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
										</html:select>
										<html:select  property="jtdzshi" styleId="jtdzshi"
											onchange="loadXian('jtdzshi','jtdzxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="jtdzxian" styleId="jtdzxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</td>
									<th>
										QQ����	
									</th>
									<td>
										<html:text property="qqhm"
											style="width:150px" maxlength="20"
											onkeyup="value=value.replace(/[^\d]/g,'')"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId='qqhm' />
									</td> 
								</tr>    
								</logic:equal>
								<%--end��ɽʦ��ѧԺ��ʾʡ����ȫ��ͳһ���������˵���ͥ��ϸ��ַ��ɽʦ��--%>
								<tr id="temDiv8" style="display:none">
									<th>
										����
									</th>
									<td>
										<html:select property="mzdm"  styleId="mz" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="mzList" property="mzdm"
												labelProperty="mzmc" />
										</html:select>
												
									</td>
									<th>
										������ò	
									</th>
									<td>
										<html:select property="zzmmdm"  styleId="zzmm" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="zzmmList" property="zzmmdm"
												labelProperty="zzmmmc" />
										</html:select>
									</td>
									<logic:present name="showLys">
										<th>
											��Դʡ
										</th>
										<td>
											<html:select property="jgshen" styleId="jgshen">
												<html:option value=""></html:option>
												<html:options collection="ssList" property="ssdm"
													labelProperty="ssmc" />
											</html:select>
										</td>
									</logic:present>
									<logic:notPresent name="showLys">	
										<logic:notPresent name="showSheng">	
										<th>
											����
										</th>
										<td colspan="">							
											
												<html:text property="jg" style="width:150px"
													onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
													styleId='jg' />							
										</td>		
										</logic:notPresent>		
										<logic:present name="showSheng">
										<th>
											
										</th>
										<td colspan="">			
										</td>	
										</logic:present>		
									</logic:notPresent>	
								</tr>
								<%--��ʾʡ����ȫ��ͳһ���������˵�����--%>
								<logic:present name="showSheng">
								<tr style="display:none">
									<th>����</th>
									<td colspan="5">
										<html:select  property="jgshen" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian')">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
										<html:select  property="jgshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="jgxian" styleId="jgxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</td>
								</tr>
								</logic:present>
								<%--end��ʾʡ����ȫ��ͳһ���������˵�--%>
								
								<!--���� ����ѧ�麣ѧԺ -->
								<logic:equal value="13675" name="xxdm">
								<tr id="temDiv10" style="display:none">
									<th>
										�Ƿ񱨵�
									</th>
									<td colspan="2">
										<html:select property="sfbd" styleId="sfbd" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
										</html:select>
									</td>
									<th>
										�ɷ����
									</th>
									<td colspan="2">
										<html:select property="jfqk" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="�ѽɷ�">�ѽɷ�</html:option>
										<html:option value="δ�ɷ�">δ�ɷ�</html:option>
										</html:select>
									</td>
								</tr>
								</logic:equal>
								<!--end���� ����ѧ�麣ѧԺ -->
								<%--�������ϴ�ѧ--%>
								<logic:equal value="11417" name="xxdm">
								<tr id="temDiv11" style="display:none">
									<th>
										�������ڵ�
									</th>
									<td colspan="5">
										<html:text property="hkszd" style="width:150px"
											onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/stu_info_query.do?method=stuInfo');"
											styleId="hkszd" />
									</td>
								</tr>
								</logic:equal>
								<%--end�������ϴ�ѧ--%>
								<!-- ����ְҵ����ѧԺ-->
								<logic:equal value="11355" name="xxdm">
								<%--��ʾʡ����ȫ��ͳһ���������˵�--%>
								<logic:present name="showSheng">
								<tr id="temDiv12" style="display:none">
									<th>
										��Դ��
									</th>
									<td colspan="5">
										<html:select  property="sydshen" styleId="sydshen"
											onchange="loadShi('sydshen','sydshi','sydxian')">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
										<html:select  property="sydshi" styleId="sydshi"
											onchange="loadXian('sydshi','sydxian')">
											<html:options collection="shiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
										<html:select  property="sydxian" styleId="sydxian">
											<html:options collection="xianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</td>
								</tr>
								<tr id="temDiv13" style="display:none">
									<th>
										������
									</th>
									<td colspan="2">
										<html:text property="ksh" styleId="ksh" style="width:150px"/>
									</td>
									<th>
										
									</th>
									<td colspan="2">
										
									</td>						
								</tr>
								</logic:present>
								<%--end��ʾʡ����ȫ��ͳһ���������˵�--%>
								<logic:notPresent name="showSheng">
								<tr id="temDiv12" style="display:none">
									<th>
										��Դ��
									</th>
									<td>
										<html:text property="syd" styleId="syd" style="width:150px"/>
									</td>
									<th>
										������
									</th>
									<td colspan="3">
										<html:text property="ksh" styleId="ksh" style="width:150px"/>
									</td>
								</tr>
								</logic:notPresent>		
								</logic:equal>
								<!-- end����ְҵ����ѧԺ -->	
								<%--��������ѧԺ--%>
								<%@ include file="/xsxx/fjgcxy/xsxxcx_fjgcxy.jsp"%>			
							</tbody>
							</table>
							</div>
							</logic:equal>

							<div class="formbox" >	
								<h3 class="datetitle_01">
							    <span>
							    	��ѯ���&nbsp;&nbsp;<font color="blue">˫���鿴ѧ�������Ϣ��������ͷ��������</font>
							    	
							    </span>
							    </h3>
								<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
								<thead>
							      <tr>
							        <td>
										<logic:equal value="yes" name="userOper">
<!--										<input type="checkbox" style="height:17.5px"></input>-->
										<input type="checkbox" style="height:17.5px" name="xsxx" value="all" onclick="chec()"></input>
										</logic:equal>
									</td>
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
				
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
									<%--������ѧԺ--%>
									<logic:equal value="11641" name="xxdm">
										<td>
											ѧ���α� 
										</td>
									</logic:equal>
									<%--end������ѧԺ--%>
							      </tr>
							    </thead>
								<logic:empty name="rs">
									<%
									for(int i=0; i<11; i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
										<%--������ѧԺ--%>
										<logic:equal value="11641" name="xxdm">
											<td>
												&nbsp;
											</td>
										</logic:equal>
										<%--end������ѧԺ--%>
								 	</tr>

									<%}%>
							 	</logic:empty>		 

								<logic:notEmpty name="rs">	
								<tbody>
							      <logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="stuInfoWin(this)">
										<logic:equal value="yes" name="userOper">
											<td align="center">
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="checkbox" name="pk" value="${v}"></input>
												</logic:iterate>
											</td>
										</logic:equal>
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												${v}
											</logic:iterate>
											<input type="hidden" value="${v}" />
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												${v}
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td align="left">
												${v}
											</td>
										</logic:iterate>
										<%--������ѧԺ--%>
										<logic:equal value="11641" name="xxdm">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<font color="blue">
														<a onclick="turnSearch('${xskbUrl}','${v}','${time}');"> �鿴</a> 
				                                    </font>
												</logic:iterate>
											</td>
										</logic:equal>
										<%--end������ѧԺ--%>
									</tr>
								</logic:iterate>
								<logic:lessEqual value="${pageSize}" name="rsNum">
									<%
									int rsNum = ((List)request.getAttribute("rs")).size();
									int pageSize = (Integer) request.getAttribute("pageSize");
									for(int i=0; i<(pageSize-rsNum); i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="pk" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
										<%--������ѧԺ--%>
										<logic:equal value="11641" name="xxdm">
											<td>
												&nbsp;
											</td>
										</logic:equal>
										<%--end������ѧԺ--%>
								 	</tr>
									<%}%>
								</logic:lessEqual>
							    </tbody>
								</logic:notEmpty>
								</table>
								<!--��ҳ��ʾ-->
							      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							 	<!--��ҳ��ʾ-->
<!--								<script type="text/javascript">-->
<!--									$('choose').className="hide";-->
<!--								</script>								-->
							</div>
						</div>

				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<logic:notEmpty name="mes">
							<script>
								alert(document.getElementById('mes').value);
							</script>
						</logic:notEmpty>
						<logic:empty name="mes">
							<script>
									alert("�����ɹ���");
								</script>
						</logic:empty>
						<script>
						Close();
						document.getElementById('search_go').click();						
					</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<logic:present name="mes">
							<logic:notEmpty name="mes">
								<script>
									alert(document.getElementById('mes').value);
								</script>
							</logic:notEmpty>
							<logic:empty name="mes">
								<script>
									alert("����ʧ��!");
								</script>
							</logic:empty>
						</logic:present>
						<logic:notPresent name="mes">
							<script>
								alert("����ʧ��!");
							</script>
						</logic:notPresent>
						<script>
								document.getElementById('search_go').click();
						</script>
					</logic:equal>
				</logic:notEmpty>

				<div id="tmpdiv"></div>
				<div id='bycfDiv' style="display:none">
				<table class='formlist'>
				<tbody>
				<tr>
					<th>
						��ѡ���޸ķ�ʽ��
					</th>
					<td>
						<input type='radio' name='configtype' value='2' checked="checked"/>&nbsp;�������޸�(���ڲ�ѯ����ѡ���ѧ������)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
						</br>
						<input type='radio' name='configtype' value='1'/>&nbsp;�޸�ѡ�е�����(���ڲ�ѯ����б��й�ѡ������)
					</td>
				</tr>
				</tbody>
				<tbody>		
				<thead>
					<tr>
						<th colspan='2'>
							<span>ѧ����ҵ�����Ϣ</span>
						</th>
					</tr>
				</thead>
					
				<tr>
				<th>
					ѧ��״̬��
				</th>
				<td>
					<html:select property="xjzt" styleId="select_newxjztm" style="width:180px">
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
					</html:select>
				</td>
				</tr>
				<!--�й����ʴ�ѧ-->
				<logic:equal value="10491" name="xxdm">
					
					<tr>
						<th>
							�ܷ��ҵ��
						</th>
						<td>
							<select id='select_newnfby' name='nfby'><option></option><option value='��'>��</option><option value='��'>��</option></select>
						</td>
					</tr>
					
				</logic:equal>
				<tr>
					<th>
						�Ƿ���У��
					</th>
					<td>
						<select id='select_newsfzx' name='sfzx'><option></option><option value='��У'>��У</option><option value='����У'>����У</option></select>
					</td>
				</tr>
				<tr>
						<th>
							�Ƿ��ѱ�ҵ��
						</th>
						<td>
							<select id='select_newsfyby' name='sfyby'><option></option><option value='��'>��</option><option value='��'>��</option></select>
						</td>
					</tr>	
				<tr>
					<th>
						�Ƿ�Ӧ���ҵ����
					</th>
					<td>
						<select id='select_newsfbys' name='sfbys'><option></option><option value='��'>��</option><option value='��'>��</option></select>
					</td>
				</tr>
				<tr>
					<th>
						��ҵʱ�䣺
					</th>
					<td>
						<input type='text' id='newbyny' name='byny' onclick="return showCalendar('newbyny','y-mm-dd');"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
				<tr>
					<td colspan='2'> 
						<div class='btn'>
							<span class='red'>���������ѧ����ҵ��ص���Ϣ,�����ز�����</span>
							<button type="button" onclick='setSearchTj();byxxConfig()'>ȷ��</button>
							<button type="button" onclick="hiddenMessage(true,true);">�ر�</button>
						</div>
					</td>
				</tr>
				</tfoot>
				</table>
				</div>
			</html:form>			
	</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/stu_info_query.do?method=stuInfo&isChecked="+$("moreTj").checked;-->
<!--</script>-->
</html>

