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
	<body>
		<html:form action="xsxxCslgXsjl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="xsxx_cslg_xxjlb" />		
			<input type="hidden" name="primarykey_xh" id="primarykey_xh"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
			    <logic:equal name="writeAble" value="yes">
			    <logic:notEqual name="userOnLine" value="student">
					<li> <a href="#" onclick="showOpenWindow('xsxxCslgXsjl.do?method=xxjlWh&doType=add',700,500)" class="btn_zj"> ���� </a> </li>
			    </logic:notEqual>
			    <li> <a href="#" onclick="modi('xsxxCslgXsjl.do?method=xxjlUpdate&doType=modi')" class="btn_xg"> �޸� </a> </li>
				<logic:notEqual name="userOnLine" value="student">
					<li> <a href="#" onclick="dataBatch('xsxxCslgXsjl.do?method=xxjlCx&doType=del')" class="btn_sc"> ɾ�� </a> </li>
					<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> �������� </a> </li>
				</logic:notEqual>
				</logic:equal>
				<li> <a href="#" onclick="wjcfDataExport('xsxxCslgXsjl.do?method=expDate')"  class="btn_dc"> �������� </a> </li>
			    </ul>
			 </div>
				<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				              <button type="button" class="btn_cx" id="search_go" 
				              	onclick="allNotEmpThenGo('xsxxCslgXsjl.do?method=xxjlCx&doType=qry')" >
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
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<logic:equal name="userOnLine" value="student">
									<input type="text" value="${userName }" readonly="true"/>
									<html:hidden property="querylike_xh" styleId="xh"  value="${userName }"/>
								</logic:equal>
								<logic:notEqual name="userOnLine" value="student">
									<html:text property="querylike_xh" styleId="xh" />
								</logic:notEqual>
								
							</td>
							<th>	
								����
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"/>
							</td>
							<th >�꼶</th>
							<td>
								<html:select property="queryequals_nj" style="width:90px;padding-left:80px" styleId="nj"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select disabled="true" property="xydm" styleId="xydm" style="width:150px" value="${userDep }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select  property="queryequals_xydm" styleId="xy" style="width:150px"
									onchange="initZyList();initBjList()">
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
								<html:select  property="queryequals_zydm" styleId="zy" style="width:150px"
								onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>	
								�༶
							</th>
							<td>
								<html:select  property="queryequals_bjdm" styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
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
			 		 	<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" style="display:none" name="all" value="all" onclick="chec()"/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('xsxxCslgXsjl.do?method=xxjlOne&doType=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=xsxxCslgXsjlForm"></jsp:include>
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
