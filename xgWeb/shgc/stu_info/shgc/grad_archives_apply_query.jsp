<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
	</head>
	<body onload="check_user();">
			<html:form action="/business.do" method="post">
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<input type="hidden" name="realTable" id="realTable" value="bysdasqb"/>
				<div class="tab_cur" id="jd">
					<p class="location" id="title_m">
						<em>���ĵ�ǰλ��:</em><a>
						<logic:present name="issh">
							��ǰλ��: ѧ����Ϣ - ѧ������ -  ��ҵ��ת�����			
						</logic:present>
						<logic:notPresent name="issh">
							��ǰλ��: ѧ����Ϣ - ѧ������ -  ��ҵ��ת����������ѯ						
						</logic:notPresent>
						</a>
					</p>
				</div>
				<logic:notEqual value="student" name="userOnLine" scope="session">
				 <div class="toolbox">
				 	<logic:notEqual value="student" name="userOnLine">
						<logic:equal value="yes" name="writeAble" scope="request">
						<logic:empty name="sh">
						 <div class="buttonbox">
						    <ul>
							<li> <a href="#" onclick="refreshForm('business.do?method=gradArchivesApply')" class="btn_zj"> ���� </a> </li>
						    <li> <a href="#" onclick="if(curr_row != null){refreshForm('business.do?method=gradArchivesApply&doType=view&xh='+curr_row.cells[0].innerText)}else{alert('��ѡ��Ҫ�޸ĵ��У�');return false;}" class="btn_xg"> �޸� </a> </li>
							<li> <a href="#" onclick="javascript:if(curr_row==null){alert('��ѡ����Ҫɾ���ļ�¼��');return false;} else{if(confirm('ȷ��ɾ����')) refreshForm('business.do?method=gradArchivesQuerry&doType=del&xh=' + curr_row.cells[0].innerText)}" class="btn_sc"> ɾ�� </a> </li>
						    </ul>
						 </div>
						 </logic:empty>
						 </logic:equal>
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
<!--				<div style="float:left;">-->
<!--					<div class="listbox" style="width:155px;float:left">-->
<!--						<div class="menulist">-->
<!--						��start-->
<!--						<div class="menutitle">-->
<!--						   <h4 style="height:30px;line-height:30px;padding-left:40px;">�����б�</h4>-->
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
<!--				</div>-->
<!--				<div style="float:right;width:630px;">-->
					<div class="searchtab">
						<table width="100%" border="0">
							 <tfoot>
							 	<tr>
							 		<td colspan="6" >
										<div class="btn">
					             			<input type="hidden" name="go" value="a" />
											<logic:present name="issh">
											<input type="hidden" id="hidden_sh">
											<button class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('business.do?method=gradArchivesAuding')">
												��ѯ
											</button>
											</logic:present>
											
											<logic:notPresent name="issh">
											<input type="hidden" id="hidden_cx">
											<button class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('business.do?method=gradArchivesQuerry')">
												��ѯ
											</button>
											</logic:notPresent>
											
					                         &nbsp;&nbsp;&nbsp;&nbsp;
					                         <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
					              	           	 �� ��
					                         </button>
					                    </div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th align="left">
										ѧ��
									</th>
									<td>
										<html:text property="xh" style="width:100px" maxlength="20"/>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" style="width:100px" maxlength="20"/>	
									</td>
									<td colspan="2">
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<logic:equal name="userType" value="xy" scope="session">
											<html:select property="xydm" style="width:100px" onchange="initZyList();initBj();"
												disabled="true" styleId="xy">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:equal>
										<logic:notEqual name="userType" value="xy" scope="session">
											<html:select property="xydm" style="width:100px" onchange="initZyList();initBj();" 
											styleId="xy">
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
										<html:select property="zydm" style="width:100px" styleId="zy"
											onchange="initBj();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										�༶
									</th>
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
				</logic:notEqual>

				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
					<font color="blue">��ʾ��<logic:notEmpty name="issh">˫��һ�п��Խ�����ˣ�</logic:notEmpty>������ͷ��������</font>
			    </span>
			    </h3>
				
<!--				<div style="overflow-x:auto;width:630px;">-->
				<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:empty name="issh">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</logic:empty>
							<logic:notEmpty name="issh">
								<logic:iterate id="tit" name="topTr" offset="1" length="10">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</logic:notEmpty>
						</tr>
					</thead>
					<tbody>
					<logic:empty name="rs">
						<%
						for(int i=0; i<11; i++){
						%>
						<tr>	
							<logic:notEmpty name="issh">
								<logic:iterate id="tit" name="topTr" offset="1" length="10">
								<td>
									&nbsp;
								</td>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="issh">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</logic:empty>		
					 	</tr>
		
						<%}%>
		 			</logic:empty>
					<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">								
						<logic:notEmpty name="issh">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
							    <logic:iterate id="v" name="s" offset="0" length="1">
							    <bean:write name="v"/>
							    </logic:iterate>
							     "
								ondblclick="showTopWin('business.do?method=gradArchivesAuding&doType=view&xh='+curr_row.cells[0].innerText,700,500)">
								<logic:iterate id="v" name="s" offset="1" length="10">
									<td align="left" style="cursor:hand">
										<input type="hidden" value="<bean:write name="v"/>" />
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:notEmpty>
						<logic:empty name="issh">
							<tr onclick="rowMoreClick(this,'',event)" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td align="left" style="cursor:hand">
										<input type="hidden" value="<bean:write name="v"/>" />
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:empty>						
					</logic:iterate>
					<logic:lessEqual value="${pageSize}" name="rsNum">
						<%
						int rsNum = ((List)request.getAttribute("rs")).size();
						for(int i=0; i<(11-rsNum); i++){
						%>
						<tr>
						<logic:notEmpty name="issh">
							<logic:iterate id="tit" name="topTr" offset="1" length="10">
							<td>
								&nbsp;
							</td>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="issh">
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
						</logic:empty>
					 	</tr>
						<%}%>
					</logic:lessEqual>
					</logic:notEmpty>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>	
					<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
							alert("ɾ���ɹ�");
							document.forms[0].search_go.click();
						</script>
						</logic:equal>
						<logic:equal value="false" name="result">
							<script>
							alert("ɾ��ʧ��");							
						</script>
						</logic:equal>
					</logic:notEmpty>
			</html:form>
	</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	if(ele('hidden_sh')){-->
<!--		url = "business.do?method=gradArchivesAuding";-->
<!--	}-->
<!--	if(ele('hidden_cx')){-->
<!--		url = "business.do?method=gradArchivesQuerry";-->
<!--	}-->
<!--	-->
<!--</script>-->
</html>
