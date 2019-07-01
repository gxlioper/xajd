<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type="text/javascript">	
		function delHkzt(){
			var url = "studentHkzt.do?method=delHkztInfo&xh=";
			if(curr_row==null){
				alert("��ѡ��Ҫɾ���ļ�¼��");
				return false;
			}else{
			if(confirm('��ȷ��Ҫɾ����ѡ�ļ�¼!')){
				url += curr_row.cells[0].innerText;
				refreshForm(url)
			}
			}
		}
		
		function expDate_hkzt(){
			document.forms[0].action = "/xgxt/studentHkzt.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//��ѯ�����
		function searchRs(){
		 allNotEmpThenGo('/xgxt/studentHkzt.do?method=hkztQuery')
		}
	</script>
</head>
<body onload="check_user();">
		<html:form action="/studentHkzt.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="tableName" value="view_hkzt" />
			<input type="hidden" name="realTable" value="hkztb" />
			
			<!-- �߼���ѯ ���� -->
		    <input type="hidden" name="userName" id="userName" value="${userName }"/>
		    <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
		  	<input type="hidden" id="path" name="searchModel.path" value="stu_hkzt_query.jsp"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - �������� - �������� - ������Ϣ��ѯ</a>
				</p>
			</div>
			<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="showTopWin('studentHkzt.do?method=addHkztInfo',800,600);return false;" class="btn_zj">�� ��</a> </li>
						<li> <a href="#" onclick="if(curr_row==null){alert('��ѡ����Ҫ�޸ĵļ�¼��')}else{showTopWin('studentHkzt.do?method=modHkztInfo&xh=' + curr_row.cells[0].innerText,800,600)};return false;" class="btn_xg">�� ��</a> </li>
						<li> <a href="#" onclick="delHkzt();return false;" class="btn_sc">ɾ ��</a> </li>
						<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a> </li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
					</logic:equal>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						<%--	<li> <a href="#" onclick="expDate_hkzt();return false;" class="btn_dc">��������</a> </li>--%>
			    </ul>
			  </div>
			  <!-- new �汾 -->
		     <logic:equal name="superSearch" value="yes">
		      <%@ include file="/comm/search/superSearchArea.jsp"%>
		     </logic:equal>
		     
		     <!-- old �汾 -->
		    <logic:equal name="superSearch" value="no">
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
								onclick="allNotEmpThenGo('/xgxt/studentHkzt.do?method=hkztQuery')">
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
							<html:select property="nj" styleId="nj" style="width:80px"
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
								<bean:write name="njList" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh" style="width:150px"></html:text>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" style="width:150px"></html:text>
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
			      		<th>����״̬</th>
						<td>
							<html:select property="hkztdm" style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="hkztList" property="hkztdm" labelProperty="hkztmc"/>
							</html:select>
						</td>
						<th>�Ƿ�ɷ�</th>
						<td>
							<html:select property="sfjf" style="width:80px">
								<html:option value=""></html:option>
								<html:option value="�ѽ�">�ѽ�</html:option>
								<html:option value="δ��">δ��</html:option>											
							</html:select>
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
			</div>
			<div class="formbox">
		    <h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ�������ͷ��������</font> 
		 		</logic:notEmpty>
		    </span>
		    </h3>
			   
		  <logic:notEmpty name="rs">
		  <div class="con_overlfow"> 
		  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
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
				<logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)" 
						ondblclick="if(curr_row==null){alert('��ѡ����Ҫ�޸ĵļ�¼��')}else{showTopWin('studentHkzt.do?method=modHkztInfo&doType=view&xh=' + curr_row.cells[0].innerText,800,600)}">									
						<logic:iterate id="v" name="s" offset="0">									
							<td>
								${v}
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
		    </tbody>
		</table>
		<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=studentHkztForm"></jsp:include>
		<!--��ҳ��ʾ-->
		</div>
		</logic:notEmpty>
		</div>
		
		<logic:present name="result">
		<logic:equal value="true" name="result">
		<script>
			alert("�����ɹ���");
			document.getElementById("search_go").click();
		</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			alert("����ʧ�ܣ�");
		</logic:equal>
		</logic:present>
	</html:form>
</body>
</html>
