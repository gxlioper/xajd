<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/dtjs/gqtgl/wpjyCx.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
	</script>
	</head>
	<body >
		<html:form action="/ntzyWpjy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" styleId="${userName }"/>
			<input type="hidden" name="realTable" id="realTable"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox"  id="btn">
			    <ul>
			    <logic:equal name="writeAble" value="yes">
				<li> <a href="#" onclick="showTopWin('ntzyWpjy.do?method=wpjySq',700,500)" class="btn_zj"> ���� </a> </li>
			      <li> <a href="#" onclick="modi('ntzyWpjy.do?method=wpjyUp&doType=save')" class="btn_xg"> �޸� </a> </li>
				<li> <a href="#"onclick="dataBatch('ntzyWpjy.do?method=wpjyCx&doType=del')" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" onclick="daoru()" class="btn_dr"> ���� </a> </li>
				</logic:equal>
				<li> <a href="#" onclick="daochu()" class="btn_dc"> ���� </a> </li>
			    </ul>
			 </div>
			 <div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				               <input type="hidden" name="go" value="a" />
				             	<button type="button"  id="search_go"
									onclick="allNotEmpThenGo('ntzyWpjy.do?method=wpjyCx&doType=qry')">
									�� ѯ
								</button>
					            <button type="button"   id="cz"
											onclick="czSearchCond('xn-xq-sqr-jbr-bm-xysh-xxsh');">
											�� ��
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>ѧ��</th>
							<td>
								<html:select property="queryequals_xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
								������
							</th>
							<td>
								<logic:equal name="userType" value="stu">
									<html:text property="querylike_sqr" readonly="true" styleId="sqr"/>
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
									<html:text property="querylike_sqr" styleId="sqr"/>
								</logic:notEqual>
								
							</td>
						</tr>
						<tr>
						  <th>������</th>
						  <td>
									<html:text property="querylike_jbr" styleId="jbr"/>
						   </td>
						   <th>�������</th>
						   <td>
								<html:select property="queryequals_xysh" styleId="xysh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>	
							</td>
							<th>ѧУ���</th>
							<td>
								<html:select property="queryequals_xxsh" styleId="xxsh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
							 ����
							 </th>
							 <td>
						    <logic:equal name="isBmyh" value="true">	
						    	 <html:select  property="bmdm" disabled="true" style="width:180px"
							  		styleId="bm" onchange="initZyList();initBjList();">
							     	<html:option value=""></html:option>
							     	<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
								 </html:select>
								 <html:hidden property="queryequals_bmdm" value="${userDep}"/>	 
						    </logic:equal>
						    <logic:notEqual name="isBmyh" value="true">
							  <html:select  property="queryequals_bmdm"  style="width:180px"
							  		styleId="bm" onchange="initZyList();initBjList();">
							     	<html:option value=""></html:option>
							     	<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
								 </html:select>	 
							</logic:notEqual>	
						    </td>
						    <td colspan="4">
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	<font color="blue">��ʾ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<div id="tmpdiv1">
				
			</div>
			<logic:notEmpty name="rsNum">
					 <table summary="" class="datelist" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()" style="display:none">
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<logic:notEqual name="index" value="1">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzyWpjy.do?method=wpjyUp&doType=view')";
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=ntzyWpjyForm"></jsp:include>
				</logic:notEmpty>
				</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
