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
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>	
	<script>
		function xjydDel(url){
			if(curr_row==null){
				alert("��ѡ����Ҫɾ���ļ�¼!\n����һ�м���!");
				return false;
			}
			var pk="";
			var xh=curr_row.cells[1].getElementsByTagName("input")[0].value;
			var ydxh=curr_row.cells[0].getElementsByTagName("input")[0].value;
						
			if(confirm('��ȷ��ɾ����')){
				pk=curr_row.cells[0].getElementsByTagName("input")[0].value;			
				url+=xh+"&ydxh="+ydxh
				document.forms[0].action=url;
				document.forms[0].submit();					
			}
		}		
		
		function hiddenButton(){
			var xxdm = document.getElementById("xxdm").value;
			//���ս�����ҵ<bean:message key="lable.xsgzyxpzxy" />ѧ���춯��Ϣȡ���� ѧ����ֻ�ṩ��ѯ����
			if(xxdm=="10878"){
				document.getElementById('btn').style.display='none';
			}
		}
	
		function changeYdlb(){
			var ydlbmc ="";
			if(ele('ydlbdm')){
				ydlb = selText('ydlbdm');
				if(ydlbmc == "��ѧ"){
					ele('fxDiv').style.display='';			
				}else{
					ele('fxDiv').style.display='none';
					setVal('sffx','');
				}
			}
			
		}
		
		//��ѯ�����
		function searchRs(){
			if(checkXjydcxTime()){allNotEmpThenGo('/xgxt/stu_status_different.do')};
		}
	</script>
</head>
<body  onload="hiddenButton();changeYdlb();">
	<html:form action="/stu_status_different" method="post">
			<input type="hidden" value="bks_xjydxx" id="rsTable"/> 
			<input type="hidden" value="bks_xjydxx" id="realTable"/> 
			<input type="hidden" value="bks_xjydxx" id="rstTable"/> 
			<input type="hidden" value="view_xjydjbxx" id="tableName" name="tableName"/>
			<input type="hidden" value="${xxdm}" id="xxdm"/> 
			<input type="hidden" name="xyV" value=""/> 
			<input type="hidden" name="zyV" value=""/> 
			<input type="hidden" name="bjV" value=""/> 		
			
			<!-- �߼���ѯ ���� -->
			<input type="hidden" name="userName" id="userName" value="${userName }"/>
			<input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
			<input type="hidden" id="path" name="searchModel.path" value="stu_info.jsp"/>
  	
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">
						<%--���㽭����ְҵ����ѧԺ--%>
						<logic:notEqual value="12861" name="xxdm">
						<li> <a href="#" onclick="showTopWin('stu_status_different.do?doType=add',800,600);return false;" class="btn_zj">�� ��</a> </li>
						<li> <a href="#" onclick="update('stu_status_different.do?doType=update&xh=',800,600);return false;" class="btn_xg">�� ��</a> </li>
						<li> <a href="#" onclick="xjydDel('stu_status_different.do?doType=del&pkValue=');return false;" class="btn_sc">ɾ ��</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>			
					</logic:notEqual>
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						<%--end���㽭����ְҵ����ѧԺ--%>
					</logic:equal>
<%--					<li> <a href="#" onclick="wjcfDataExport('xjydgl.do?method=expXjydxx')" class="btn_dc">��������</a> </li>--%>
			    </ul>
			  </div>
			</div>
			
			  <!-- new �汾 -->
		    <logic:equal name="superSearch" value="yes">
		      <%@ include file="/comm/search/superSearchArea.jsp"%>
		     </logic:equal>
		     
		     <!-- old �汾 -->
		     <logic:equal name="superSearch" value="no">
			  <logic:notEqual value="student" name="userOnLine" scope="session">
			  <!-- ������ʾ����ʼ -->
				<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
					<!-- From���� -->
<!--					<div style="float:left;">-->
<!--						<div class="listbox" style="width:155px;float:left">-->
<!--							<div class="menulist">-->
<!--							��start-->
<!--							<div class="menutitle">-->
<!--							    <h4 style="height:30px;line-height:30px;padding-left:40px;">�����б�</h4>-->
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
					<!--��ѯ����-->
					  <div class="searchtab">
					    <table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					              <input type="hidden" name="go" value="a" />
								  <button class="btn_cx" id="search_go"
										onclick="if(checkXjydcxTime()){allNotEmpThenGo('/xgxt/stu_status_different.do')}">
										��ѯ
								  </button>
					            </div>
					          </td>
					        </tr>
					      </tfoot>
						  <tbody>
					      	<tr>
					      		<th>�꼶</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:100px"
										onchange="initZyList();initBj();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
										<bean:write name="njList" />
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:text property="xh" style="width:100px"></html:text>
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
										<html:select property="xydm"  value="${userDep }"
											onchange="initZyList();initBj();"
											disabled="true"
											styleId="xy"
											style="width:100px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="ydhxydm" value="${userDep }"/>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="ydhxydm" 
											onchange="initZyList();initBj();"
											styleId="xy"
											style="width:100px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>רҵ</th>
								<td>
									<html:select property="ydhzydm"  styleId="zy"
										onchange="initBj();" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>�༶</th>
								<td>
									<html:select property="ydhbjdm"  styleId="bj" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>�춯���</th>
								<td>
		                            <html:select property="ydlbdm" 
										styleId="ydlb" onchange="changeYdlb()" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="ydlbList" property="ydlbdm"
											labelProperty="ydlbmc" />
									</html:select>
		                        </td>
								<th>�춯����</th>
								<td>
		                            <html:text property="ydrqks" 
									           style="width:80px" 
									           onclick="return showCalendar('ydrqks','y-mm-dd');" 
									           onblur="dateFormatChg(this)"
									           ></html:text>
										-
									<html:text property="ydrqjs" 
									           style="width:80px" 
									           onclick="return showCalendar('ydrqjs','y-mm-dd');" 
									           onblur="dateFormatChg(this)"
									           ></html:text>
		                        </td>
								<th>�춯��ֹ����</th>
								<td>
		                            <html:text property="ydjzrqks" 
									           style="width:80px"
									           onclick="return showCalendar('ydjzrqks','y-mm-dd');" 
									           onblur="dateFormatChg(this)"
									           ></html:text>
										-
									<html:text property="ydjzrqjs" 
									           style="width:80px"
									           onclick="return showCalendar('ydjzrqjs','y-mm-dd');" 
									           onblur="dateFormatChg(this)"
									           ></html:text>
		                        </td>
							</tr>
							<tr id="fxDiv">
								<th>�Ƿ�ѧ</th>
								<td>
									<html:select property="sffx" style="width:100px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
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
			  </logic:notEqual>
			  </logic:equal>

			  <div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			    </span>
			    </h3>
				   
			  <div class="con_overlfow">
<!--			  <div style="overflow-x:auto;width:630px;">	-->
			  <table summary="" class="dateline" width="100%" id="rsTable">
			    <thead>
			      <tr>
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
						for(int i=0; i<11; i++){
						%>
						<tr>
							<logic:iterate id="tit" name="topTr" offset="1">
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
							ondblclick="update('stu_status_different.do?doType=update&userOper=view&xh=',800,600)">
							<logic:iterate id="v" name="s" offset="1">
								<td align="center" nowrap="nowrap">
									<input type="hidden" value="${v}" />
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
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			<!--��ҳ��ʾ-->
			</div>
		</div>
	</div>

			
			<logic:present name="different">
				<%--�Ϻ������춯����--%>
				<script>								  						  
			  		showNewStuStatusInfo(500,400);
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
		</html:form>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/stu_status_different.do";-->
<!--</script>		-->
</body>
</html>
