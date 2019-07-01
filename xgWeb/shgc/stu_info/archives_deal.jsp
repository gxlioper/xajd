<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<%@ include file="/syscommon/pagehead_xg.ini"%>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type="text/javascript">
		var pkVals = "!@!";
		var num = 0;
		var xh = "";
		function checkOpertion(){
				pkVals = "!@!";	
				num = 0;
				xh = "";			
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVals +=pks[i].value+"!@!"; 
						xh += pks[i].value;
					}
				}
				if(num == 0){
					alert("�빴ѡ��Ҫ�����ļ�¼��");
					return  false;
				}
				return true;
		}
		function updateZdxx(val){
			
				xh= curr_row.getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/archives_deal.do?doType=update&pkStr='+xh,600,500);
			
		}
		function deleteZdxx(){
			if(checkOpertion()){
				if(confirm("ȷ��ɾ����ѡ��¼��")){
					$('pkStr').value= pkVals;
					refreshForm('/xgxt/archives_deal.do?doType=delete');
				}
			}
		}
		function chec1(){
      		for(i=0;i<document.getElementsByName("pkV").length;i++){
      			document.getElementsByName("pkV")[i].checked=document.getElementsByName("all")[0].checked;
      		}
    	}
    	function viewzdxx(){
    		var array = curr_row.getElementsByTagName('input');
    		var zdlb = $('zdlb').value;
    		var xh = '';
    		if(array.length >1){
    			xh = array[1].value;
    		}else{
    			xh = array[0].value;
    		}
    		showTopWin('/xgxt/archives_deal.do?doType=view&xh='+xh+'&zdlb='+zdlb,600,500);
    	}
    	
    	function printInfo(val){
    		if(val == 'dajdd'){
    			if($('zdlb').value==''){
    				alert('ת�������Ϊ�գ�');
    				return false;
    			}
    		}
    		if(checkOpertion()){
    			$('pkStr').value= pkVals;
    			document.forms[0].action = "/xgxt/archives_print.do?doType="+val;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
    		}		
    	}
    	function viewdiv(){
    		if(checkOpertion()){
    			viewTempDiv('','div',340,180);
    		}
    	}
    	function summitdiv(){
    		if($('wh').value==''){
    			alert('�ĺŲ���Ϊ�գ�');
    			return  false;
    		}
    		if($('djr').value==''){
    			alert('�Ǽ��˲���Ϊ�գ�');
    			return  false;
    		}
    		
    		$('pkStr').value= pkVals;    		
    		document.forms[0].action = "/xgxt/archives_print.do?doType=jyjdd";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			hiddenOneDiv(true,true);		
    	}
    	function init2(obj1,obj2) {
    		document.getElementById(obj2).style.pixelTop = 
			document.getElementById(obj1).style.pixelTop-260;
		}
		
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/archives_deal.do?doType=query');
		}
	</script>
</head>
<body>
	<html:form action="/archives_deal" method="post">
		<input type="hidden" id="pkStr" name="pkStr" value=""/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		
		 <!-- �߼���ѯ ���� -->
	     <input type="hidden" name="userName" id="userName" value="${userName }"/>
	     <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
	 	 <input type="hidden" id="path" name="searchModel.path" value="archives_deal.jsp"/>
  
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<li> <a href="#" onclick="updateZdxx('no');return false;" class="btn_xg">�� ��</a> </li>
					<li> <a href="#" onclick="deleteZdxx();return false;" class="btn_sc">ɾ ��</a> </li>
					<li> <a href="#" onclick="printInfo('dajdd');return false;" class="btn_dy">�����ĵݵ�</a> </li>
					<li> <a href="#" onclick="printInfo('dajdxf');return false;" class="btn_dy">�����ĵ��ŷ�</a> </li>
					<li> <a href="#" onclick="if(checkOpertion()){viewTempDiv('��д�ĵ���Ϣ','div',340,180);};return false;" class="btn_dy">��Ҫ�ĵݵ�</a> </li>
			    </ul>
			  </div>
		</div>
		
			  <!--��ѯ����-->
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
				     <logic:notEqual value="student" name="userOnLine" scope="session">
					<!-- From���� -->
<!--					<div style="float:left;">-->
<!--						<div class="listbox" style="width:155px;float:left">-->
<!--							<div class="menulist">-->
<!--							��start-->
<!--							<div class="menutitle">-->
<!--							     <h4 style="height:30px;line-height:30px;padding-left:40px;">�����б�</h4>-->
<!--							<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
<!--							<ul>-->
<!--							  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
<!--							</ul>-->
<!--							</div>-->
<!--							<script type="text/javascript">-->
<!--							-->
<!--							var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
<!--							//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
<!--	 						-->
<!--							</script>-->
<!--							</div>-->
<!--							��end-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--					<div style="float:right;width:630px;">-->
					  <div class="searchtab">
					    <table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					              <input type="hidden" name="go" value="a" />
								  <button class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/archives_deal.do?doType=query')">
										��ѯ
								  </button>
					            </div>
					          </td>
					        </tr>
					      </tfoot>
						  <tbody>
					      	<tr>
					      		<th>���</th>
								<td>
									<html:select property="nd"  styleId="nd" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>								
								<th>ѧ��</th>
								<td>
									<html:text property="xh" maxlength="20" style="width:100px"/>
								</td>
								<th>����</th>
								<td>
									<html:text property="xm" style="width:100px"></html:text>
								</td>
							</tr>
							<tr>
					      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<logic:equal name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:100px"
											 disabled="true"
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:100px"
											onchange="initZyList();initBj();" 
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>רҵ</th>
								<td>
									<html:select property="zydm" style="width:100px" styleId="zy"
										onchange="initBj();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>�༶</th>
								<td>
									<html:select property="bjdm" style="width:100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
					      		<th>ת�����</th>
								<td>
									<html:select property="zdlb" style="width:100px"
										styleId="zdlb">
										<html:option value=""></html:option>
										<html:option value="��ҵ">��ҵ</html:option>
										<html:option value="��ѧ">��ѧ</html:option>
										<html:option value="תѧ">תѧ</html:option>
										<html:option value="��ѧ">��ѧ</html:option>
									</html:select>
								</td>
								<th></th>
								<td>
								</td>
								<th></th>
								<td>
								</td>
							</tr>
						  </tbody>
						</table>
					</div>
			</logic:notEqual></logic:equal>

			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			    </span>
			    </h3>
<!--			 	<div style="overflow-x:auto;width:630px;">		-->
			  <div class="con_overlfow"> 
			  <table summary="" class="dateline"  width="100%" id="rsTable">
			    <thead>
			      <tr>
					<td>
<!--						<input type="checkbox"/>-->
						<input type="checkbox" name="all" value="all" onclick="chec1()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="0">
						<td id="${tit.en}"
							onclick="tableSort(this)">
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
							<td align="center">
								<input type="checkbox" name="pk" value="" disabled="disabled"></input>
							</td>
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
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;"
							ondblclick="updateZdxx('yes');">
								<td>
									<input type="checkbox" value="${s.xh}" name="pkV" id="pkV"/>
								</td>
								<td>
									${s.xh}
								</td>
								<td>
									${s.xm}
								</td>
								<td>
									${s.xymc}
								</td>
								<td>
									${s.zymc}
								</td>
								<td>
									${s.bjmc}
								</td>
								<td>
									${s.zddwmc}
								</td>
								<td>
									${s.zddwdz}
								</td>
								<td>
									${s.zdlb}
								</td>
								<td>
									${s.xxsh}
								</td>
								<td>
									${s.zcbh}
								</td>
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
							<logic:iterate id="tit" name="topTr" offset="0">
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
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ShgcForm"></jsp:include>
			<!--��ҳ��ʾ-->
<!--				<script type="text/javascript">-->
<!--					$('choose').className="hide";-->
<!--				</script>-->
		</div>
		<div style="display: none;position: absolute;z-index: 1;" id="div">
			<table class="formlist" style="width:100%;" >
			 <thead>
			    <tr>
			    	<th colspan="2">
			    		<span>��Ҫ�ĵݵ�</span>
			    	</th>
			    </tr>
			  </thead>
			  <tbody>
				<tr>
					<th>�ĺ�</th><td><input type="text" name="wh" id="wh"/></td>
				</tr>
				<tr>
					<th>�Ǽ���</th><td><input type="text" name="djr" id="djr"/></td>
				</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="2">
		          <div class="btn">
		            <button onclick="$('div').style.display='none';summitdiv();">
								�� ��
					</button>
					<button onclick="hiddenMessage(true,true);return false;">
								�� ��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
				alert("�����ɹ�");
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
				alert("����ʧ��");							
			</script>
			</logic:equal>
		</logic:notEmpty>
</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/archives_deal.do?doType=query";-->
<!--</script>-->
</html>
