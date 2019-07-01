<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
	//��ѯ�����
	  function searchRs(){
		allNotEmpThenGo('/xgxt/stu_archives_now.do')
	  }
	  
	  function commit(url){
		if(curr_row == null){
			alert("��ѡ����Ҫ�����ļ�¼��");
			return false;
		}		
		var xh = curr_row.cells[1].innerText.trim();
				
		url += "&xh=";
		url += xh;
		showTopWin(url,550,700);			
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
	  
	  	function delZxsda(){
	  		var url = 'stu_archives_now.do?doType=del';
	  	
	  		var RowsStr="!!SplitOneSplit!!";  
	  		var checkbox = jQuery('input[name=pk]:checked');
	  		var pkValue;
	  		if (checkbox.length==0){  													
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
				return false;
			} else {
				refreshForm(url);
	  		}
	  	}
	  
	  
  	</script>

	</head>
	<body onload="check_user();">
		<html:form action="/stu_archives_history">
			<input type="hidden" value="stu_archives" id="realTable" />
			<input type="hidden" value="${userType}" id="userType" />
			<input type="hidden" value="view_stu_archives" id="tableName" />
			<input type="hidden" name="tableName" value="view_stu_archives" />
			<input type="hidden" name="realTable" value="stu_archives" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<!-- �߼���ѯ ���� -->
			<input type="hidden" name="userName" id="userName"
				value="${userName }" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep }" />
			<input type="hidden" id="path" name="searchModel.path"
				value="stu_archives_now.jsp" />


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
							<th>
								�鵵����
							</th>
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
									<button class='button2'
										onclick="batch('business.do?method=datumCommit&doType=save&mk=dacx');return false;">
										ȷ��
									</button>
									&nbsp;&nbsp;
									<button class='button2' onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			





			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��Уѧ������ - ��Уѧ��������ѯ</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/stu_archives_now.do?doType=add&type=add',800,600);return false;"
									class="btn_zj">�� ��</a>
							</li>
							<li>
								<a href="#"
									onclick="familyUpdate('/xgxt/stu_archives_now.do?doType=update&xh=',800,600);return false;"
									class="btn_xg">�� ��</a>
							</li>
							<li>
								<a href="#"
									onclick="delZxsda();return false;"
									class="btn_sc">ɾ ��</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;"
									class="btn_dr">��������</a>
							</li>
							<li>
								<a href="#" class="btn_qx"
									onclick="choiceFields();return false;">��������</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc"
								onclick="configureExportData();return false;">��������</a>
						</li>
						<li>
							<a href="#"
								onclick="commit('stu_info_add.do?method=datumCommitSignle');return false;"
								class="btn_ccg">�����ύ</a>
						</li>
						<li>
							<a href="#"
								onclick="if(checkSelect()){viewTempDiv('�����ύ�鵵����','gdzlDiv',300,200);};return false;"
								class="btn_ccg">�����ύ</a>
						</li>
					</ul>
				</div>
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
						<!--				<div style="float:left;">-->
						<!--					<div class="listbox" style="width:155px;float:left">-->
						<!--						<div class="menulist">-->
						<!--						��start-->
						<!--						<div class="menutitle">-->
						<!--						    <h4 style="height:30px;line-height:30px;padding-left:40px;">�����б�</h4>-->
						<!--						<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
						<!--						<ul>-->
						<!--						  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
						<!--						</ul>-->
						<!--						</div>-->
						<!--						<script type="text/javascript">-->
						<!--						-->
						<!--						var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
						<!--						//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
						<!-- 						-->
						<!--						</script>-->
						<!--						</div>-->
						<!--						��end-->
						<!--						</div>-->
						<!--					</div>-->
						<!--				</div>	-->
						<!--				<div style="float:right;width:630px;">-->
						<!--��ѯ����-->
						<logic:notEqual value="student" name="userOnLine" scope="session">
							<div class="searchtab">
								<table width="100%" border="0">
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="btn">
													<input type="hidden" name="go" value="a" />
													<button class="btn_cx" id="search_go"
														onclick="allNotEmpThenGo('/xgxt/stu_archives_now.do')">
														��ѯ
													</button>
													<button id="cz"
														onclick="czSearchCond('nj-xh-xm-xy-zy-bj-zddwmc-jyh');return false;">
														����
													</button>
												</div>
											</td>
										</tr>
									</tfoot>
									<tbody>
										<tr>
											<th>
												�꼶
											</th>
											<td>
												<html:select property="nj" styleId="nj"
													onchange="initZyList();initBj();" style="width:100px">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
													<bean:write name="njList" />
												</html:select>
											</td>
											<th>
												ѧ��
											</th>
											<td>
												<html:text property="xh" style="width:100px" maxlength="20"></html:text>
											</td>
											<th>
												����
											</th>
											<td>
												<html:text property="xm" style="width:100px" maxlength="20"></html:text>
											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="lable.xsgzyxpzxy" />
											</th>
											<td>
												<html:select property="xydm" style="width:100px"
													styleId="xy" onchange="initZyList();initBj();">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</td>
											<th>
												רҵ
											</th>
											<td>
												<html:select property="zydm" style="width:100px"
													styleId="zy" onchange="initBj();">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</td>
											<th>
												�༶
											</th>
											<td>
												<html:select property="bjdm" style="width:100px"
													styleId="bj">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</td>
										</tr>
										<!--��������-->
										<%@ include file="/xsxx/fjgcxy/zxxsdacx_fjgcxy.jsp"%>
									</tbody>
								</table>
							</div>
						</logic:notEqual>
					</logic:equal>

					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ;������ͷ��������;</font>
							</span>
						</h3>


						<div class="con_overlfow">
							<!--  								tablenowrap-->
							<!--						 <div style="overflow-x:auto;width:630px;">-->
							<table summary="" class="dateline" width="100%" id="rsTable">
								<thead>
									<tr>
										<td>
											<input type="checkbox" disabled="true" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="${tit.en}" onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:empty name="rs">
										<%
										for (int i = 0; i < 11; i++) {
										%>
										<tr>
												<td>
													&nbsp;
												</td>
											<logic:iterate id="tit" name="topTr" offset="1">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>

										<%
										}
										%>
									</logic:empty>
									<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
												ondblclick="familyUpdate('/xgxt/stu_archives_now.do?doType=update&view=true&xh=',800,600)">
												<td>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="checkbox" name="pk" value="${v }" />
													</logic:iterate>
												</td>
												<td>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
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
										<logic:lessEqual value="${pageSize}" name="rsNum">
											<%
													int rsNum = ((List) request.getAttribute("rs")).size();
													int pageSize = (Integer) request
															.getAttribute("pageSize");
													for (int i = 0; i < (pageSize - rsNum); i++) {
											%>
											<tr>
												<td>
													<input type="checkbox" name="pkValue" disabled="true" />
												</td>
												<logic:iterate id="tit" name="topTr" offset="1">
													<td>
														&nbsp;
													</td>
												</logic:iterate>
											</tr>
											<%
											}
											%>
										</logic:lessEqual>
									</logic:notEmpty>
								</tbody>
							</table>
						</div>
						<!--��ҳ��ʾ-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ShgcForm"></jsp:include>
						<!--��ҳ��ʾ-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>

					</div>
				</div>
			</div>
			</div>
			<logic:present name="have">
				<script>								  						  
					showGdzlInfo(500,400);
				</script>
			</logic:present>

			<logic:notEmpty name="result" scope="request">
				<logic:equal value="true" name="result" scope="request">
					<script>
					alert("�����ɹ���");
					Close();					
					document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result" scope="request">
					<script>
					alert("����ʧ�ܣ�");
				</script>
				</logic:equal>
			</logic:notEmpty>
			</div>
		</html:form>
	</body>
	<script type="text/javascript" src="js/xsxx/bmTree.js"></script>
	<script>
	url = "/xgxt/stu_archives_now.do";
</script>
</html>
