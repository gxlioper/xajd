<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<%@ include file="/syscommon/pagehead_xg.ini"%>	
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script>
	function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	if(document.getElementsByName("pk")[i].disabled == false){
	      		document.getElementsByName("pk")[i].checked=document.getElementsByName("jtxx")[0].checked;
	      	}
	      }
    }
    
    function del(url){
		var RowsStr="!!#!!";
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
	    	}
		}		
		document.forms[0].delPk.value=RowsStr;	
		
		if (RowsStr=="!!#!!"){
			alert("��ѡ��Ҫ������յļ�ͥ��Ϣ��");
			return false;
		}
		
		if (!confirm("ȷ��Ҫ�����ѡ��ͥ��Ϣ��")){
			return false;
		}
		document.forms[0].action=url;
	    document.forms[0].submit();
	}
	
	function searchRs(){
		allNotEmpThenGo('/xgxt/modi_stu_famil.do');
	}
	</script>
</head>
<body>
	<html:form action="/modi_stu_famil" method="post">
		<input type="hidden" id="delPk" name="delPk" value="delPk" />
		<input type="hidden" name="xxdm" value="${xxdm}" />
		<input type="hidden" id="realTable" value="xsfzxxb" />
		<input type="hidden" id="tableName" value="view_xsjtxx" />
		<input type="hidden" id="userName" name="userName" value="${userName}" />
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		
		<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		<input type="hidden" id="path" name="searchModel.path" value="${path }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>

		<div class="toolbox">	
			  <!--��ť	-->
			  <div class="buttonbox">
			    <ul>
				<logic:equal value="yes" name="writeAble">
				<logic:equal value="yes" name="userOper">					
					<li> 
						<a href="#" onclick="familyUpdate('modi_stu_famil.do?doType=showAddpage&xxdm=${xxdm}&type=update&xh=',800,600);return false;" class="btn_xg">ά����ͥ��Ϣ</a> 
					</li>
					<li> 
						<a href="#" onclick="del('modi_stu_famil.do?doType=del');return false;" class="btn_hsz">��ռ�ͥ��Ϣ</a> 
					</li>
					<li> 
						<a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a> 
					</li>
				</logic:equal>
				</logic:equal>
	
				<!--�Ƕ�����ҵ��ѧ-->
				<logic:notEqual value="10225" name="xxdm">
					<li> 
						<a href="#" onclick="showExportDIV('/xgxt/expData.do?tableName=view_xsjtxx&realTable=xsfzxxb');" class="btn_dc">��������</a> 
					</li>
				</logic:notEqual>
				<!--end�Ƕ�����ҵ��ѧ-->
				</ul>
			  </div>
			  <!-- �й��ش� �汾 -->
				<logic:notEqual name="xxdm" value="10491">
			  	<%@ include file="/comm/search/superSearchArea.jsp"%>
			  	</logic:notEqual>
			</div>

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
					<!-- From���� -->
<!--						<div class="listbox" style="width:155px;float:left">-->
<!--							<div class="menulist">-->
<!--							��start-->
<!--							<div class="menutitle">-->
<!--							    <h4 style="height:30px;line-height:30px;padding-left:40px;">�����б�</h4>-->
<!--							<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
<!--								<ul>-->
<!--								  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
<!--								</ul>-->
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
<!--					<div style="float:right;width:630px;">-->
							 <!--��ѯ����-->
							 <!-- �ش� -->
							<logic:equal name="xxdm" value="10491">
							<div class="searchtab">
							    <table width="100%" border="0">
							      <tfoot>
							        <tr>
							          <td colspan="6">
							            <div class="btn">
										  	<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx" 
													id="search_go"
													onclick="allNotEmpThenGo('/xgxt/modi_stu_famil.do');">
												�� ѯ
											</button>
											<button type="button" id="cz"
												onclick="czSearchCond('nj-xh-xm-xy-zy-bj');return false;">
												�� ��
										    </button>
							            </div>
							          </td>
							        </tr>
							      </tfoot>
								  <tbody>
							      	<tr>
							      		<th>�꼶</th>
										<td>
											<html:select property="nj" style="width:100px" onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
										<th>ѧ��</th>
										<td>
											<html:text property="xh" style="width:100px"  onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/modi_stu_famil.do');" />
										</td>
										<th>����</th>
										<td>
											<html:text property="xm" style="width:100px"  onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/modi_stu_famil.do');"/>
										</td>
									</tr>
									<tr>
							      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											<logic:equal name="userType" value="xy" scope="session">
												<html:select property="xydm" style="width:100px"
													onchange="initZyList();initBjList();"
													disabled="true" styleId="xy">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:equal>
											<logic:notEqual name="userType" value="xy" scope="session">
												<html:select property="xydm" style="width:100px"
													onchange="initZyList();initBjList();" styleId="xy">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
											</logic:notEqual>
										</td>
										<th>רҵ</th>
										<td>
											<html:select property="zydm" style="width:100px" styleId="zy"
												onchange="initBjList();">
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
								  </tbody>
								</table>				
							</div>
							</logic:equal>
							<!-- �ش� end -->
							<div class="formbox">
								<h3 class="datetitle_01">
								    <span>
								    	��ѯ���&nbsp;&nbsp;
								    	<font color="blue">��ʾ��˫���ɲ鿴��ϸ��Ϣ��������ͷ��������</font>
								    </span>
							    </h3>			
								
								  <div class="con_overlfow"> 	
								  <table summary="" class="dateline"  width="100%" id="rsTable">
								    <thead>
								      <tr>
								        <td>
<!--											<input type="checkbox"/>-->
											<input type="checkbox" name="jtxx" value="all" onclick="chec()"/>
										</td>
										<logic:iterate id="tit" name="top">
											<td id="${tit.en}"
												onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
								      </tr>
								    </thead>				
								    <tbody>
										<%
											int pageSize = (Integer) request.getAttribute("pageSize");
										%>
										<logic:empty name="rs">
											<%
											for(int i=0; i<pageSize; i++){
											%>
											<tr>
												<td align="center">
													<input type="checkbox" name="pk" value="" disabled="disabled"></input>
												</td>
												<logic:iterate id="tit" name="top">
													<td>
														&nbsp;
													</td>
												</logic:iterate>
										 	</tr>
							
											<%}%>
							 			</logic:empty>
										<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											<tr onclick="rowOnClick(this)" style="cursor:hand"
												ondblclick="familyUpdate('modi_stu_famil.do?doType=showAddpage&type=update&userOper=view&xh=',800,600)">
												<td>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<input type="checkbox" name="pk" value="${v}"/>
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<td align="left">
														<input type="hidden" value="${v}"/>
														${v}
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2">
													<td align="left">
														${v}
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
										<logic:lessEqual value="${pageSize}" name="rsNum">
												<%
												int rsNum = ((List)request.getAttribute("rs")).size();
												
												for(int i=0; i<(pageSize-rsNum); i++){
												%>
												<tr>
													<td align="center">
														<input type="checkbox" name="pk" value="" disabled="disabled"></input>
													</td>
													<logic:iterate id="tit" name="top">
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
								      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
								    <!--��ҳ��ʾ-->
<!--									<script type="text/javascript">-->
<!--										$('choose').className="hide";-->
<!--									</script>			-->
									
							</div>
						</div>
					</div>
			</div>	
	</html:form>

	<logic:notEmpty name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ���");
				Close();	
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>
	</logic:notEmpty>

<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/modi_stu_famil.do"-->
<!--</script>-->
</body>
</html>
