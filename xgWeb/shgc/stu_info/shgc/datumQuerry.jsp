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
		function chec(){
	      for(i=0;i<document.getElementsByName("pk").length;i++){
	      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
	      }
	    }
	    
	    function show(url){    	
	    	var xh = curr_row.cells[0].innerText;
	    	if($('xxdm').value=='11122'){
				url = 'stu_info_add.do?method=datumCommitSignle&doType=view';
			}
	    	url += "&xh=";
	    	url += xh;
	    	showTopWin(url);
	    }
	    
	    function print(){
			var xy = document.getElementById('xy').value;
			var nj = document.getElementById('nj').value;
			if(''==xy || null == xy || ''==nj || null == nj){
				alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" /> �� �꼶");
				return false;
			}
			var url = '/xgxt/business.do?method=getGdzl&xy='+xy+'&nj='+nj;
			window.open(url);
		}
		
		//��ѯ�����
		function searchRs(){
		 	allNotEmpThenGo('business.do?method=datumQuerry')
		}
	</script>
</head>
<body onload="check_user();">
	<html:form action="/business.do" method="post">
		<input type="hidden" value="${userType}" id="userType" />
		<input type="hidden" name="xxdm" value="${xxdm}" id="xxdm"/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		  <!-- �߼���ѯ ���� -->
	     <input type="hidden" name="userName" id="userName" value="${userName }"/>
	     <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
	  	<input type="hidden" id="path" name="searchModel.path" value="datumQuerry.jsp"/>
		
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ѧ������ - �ύ�鵵���ϲ�ѯ</a>
			</p>
		</div>
		
		<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<li> <a href="#" onclick="print()" class="btn_dc">��������</a> </li>
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
								onclick="allNotEmpThenGo('business.do?method=datumQuerry')">
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
							<html:select property="nj" style="width:80px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh" style="width:150px" maxlength="20"/>	
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" style="width:150px" maxlength="20"/>
						</td>
					</tr>
					<tr>
			      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								onchange="initBjList();">
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
					<%--��������Ժ--%>
					<logic:equal value="11407" name="xxdm">							
					<tr>
			      		<th>����</th>
						<td>
							<html:select property="ssda">
								<html:option value="">ȫ������</html:option>
								<html:option value="rxddqkdm">��ѧ����</html:option>
								<html:option value="zxddqkdm">��У����</html:option>
								<html:option value="byddqkdm">��ҵ����</html:option>
							</html:select>
						</td>
						<th>�������</th>
						<td>
							<html:select property="ddqkdm">
								<html:option value=""></html:option>
								<html:options collection="ddqkList" property="dddm" labelProperty="ddmc"/>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>	
					<%--end��������Ժ--%>
				  </tbody>
				</table>
			</div>
			</logic:equal>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
					<logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ;������ͷ��������;</font>
					</logic:notEmpty>
			    </span>
			    </h3>
				   
			  <table summary="" class="dateline" width="100%" id="rsTable">
			    <thead>
			      <tr>
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
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="show('stu_info_add.do?method=showDetailsInfoOfDatum')">
								<logic:iterate id="v" name="s" offset="0">
									<td align="left">
										<bean:write name="v" />
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
	</html:form>
	
</body>
</html>

