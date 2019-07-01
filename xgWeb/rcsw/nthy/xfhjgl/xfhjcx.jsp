<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script>
			
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>

		<!-- ���� end-->
		<html:form action="/rcsw_nthy_xfhjgl.do">
		<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		    <input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			
			<input type="hidden" name="tableName" id="tableName"
			value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
			
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			 	<logic:equal value="yes" name="writeAble">
			 	<logic:equal value="teacher" name="userOnLine" scope="session">
			    <ul>
				<li> <a href="#" id="a_zj" onclick="showTopWin('rcsw_nthy_xfhjsq.do?show=cx',800,600)" class="btn_zj"> ���� </a> </li>
				<li> <a href="#" id="a_xg" onclick="modiAndDel('rcsw_nthy_xfhjxg.do?pkValue=','modi',700,500)" class="btn_xg"> �޸� </a> </li>
				<li> <a href="#" id="a_sc" onclick="deldata('rcsw_nthy_xfhjcx.do?act=del')" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" id="a_dr" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
				<li> <a href="#" id="a_dc" onclick="dataExport()" class="btn_dc"> ���� </a> </li>
				
			    </ul>
			    </logic:equal>
			    </logic:equal>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			            <div class="btn">
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('rcsw_nthy_xfhjcx.do')">
			              	�� ѯ
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
							<tr id="showTr" >
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" styleId="nj"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th >
								ѧ��
							</th>
							<td> 
								<logic:equal name="userOnLine" value="student" scope="session">
								<html:text property="xh" styleId="xh" style="width:150px" maxlength="20" readonly="true" value="${userName}"></html:text>
								</logic:equal>
	
								<logic:equal name="userOnLine" value="teacher" scope="session">
								<html:text property="xh" styleId="xh" style="width:150px" maxlength="20"></html:text>
								</logic:equal>
							</td>
							<th>
								����
							</th>
							<td >
								
								<logic:equal name="userOnLine" value="student" scope="session">
								<html:text property="xm" styleId="xm" style="width:150px" maxlength="20" readonly="true" value="${userNameReal}"></html:text>
								</logic:equal>
	
								<logic:equal name="userOnLine" value="teacher" scope="session">
								<html:text property="xm" styleId="xm" style="width:150px" maxlength="20"></html:text>
								</logic:equal>
							</td>
						</tr>		                       
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();">
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
								<html:select property="zydm" styleId="zy"  style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr id="showTr" >
							
							<th>
								����Ա���
							</th>
							<td>
								<html:select property="fdysh" styleId="fdysh"  style="width:150px">
										<html:option value=""></html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
										<html:option value="δ���">δ���</html:option>
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xb" />���
							</th>
							<td>
								<html:select property="xysh" styleId="xysh"  style="width:150px">
										<html:option value=""></html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
										<html:option value="δ���">δ���</html:option>
								</html:select>
							</td>
							<th>
								ѧУ���
							</th>
							<td>
								<html:select property="xxsh" styleId="xxsh"  style="width:150px">
										<html:option value=""></html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
										<html:option value="δ���">δ���</html:option>
								</html:select>
							</td>
							
						</tr>	
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn"  style="width:150px">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<td colspan="4"></td>
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			
			<!-- �๦�ܲ����� end -->

			<!-- ��ѯ���-->
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ��� <logic:empty name="rs">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							&nbsp;&nbsp;<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty> <%--						<font color="blue"></font>--%> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="dshxl"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						<!--���� -->
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;"  ondblclick="showTopWin('rcsw_nthy_xfhjsjsh.do?act=view&doType=dg&num=1&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,700,600)">
								<td align="center">
									<input type="checkbox" id="cbv" name="cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2" >
									<td align="left">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								
							</tr>
						</logic:iterate>
						<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- ������ end-->
						<!--���� end-->
						</tbody>
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xfhjglActionForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
