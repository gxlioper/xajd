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
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
	</script>
	</head>
	<body >
		
		<html:form action="/mjxyRych" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		  	 <input type="hidden" name="realTable" id="realTable" value="xsrychb" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
			   	<logic:notEqual name="writeAbled" value="no">
				<li> <a href="#" onclick="showTopWin('mjxyRych.do?method=rychSq',700,500)" class="btn_zj"> ���� </a> </li>
			    <li> <a href="#" onclick="modi('mjxyRych.do?method=rychXx&doType=modi')" class="btn_xg"> �޸� </a> </li>
				<li> <a href="#" onclick="dataBatch('mjxyRych.do?method=resultRych&doType=del')" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
				</logic:notEqual>
				<li> <a href="#" onclick="wjcfDataExport('mjxyRych.do?method=expDate')" class="btn_dc"> ���� </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			            <div class="btn">
			              <input type="hidden" name="go" value="go" />
							<button  id="search_go" 
									onclick="refreshForm('/xgxt/mjxyRych.do?method=resultRych&doType=qry')"
									>
								��ѯ
							</button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
								<html:select  property="queryequals_xn"  styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>	
							<th>
								ѧ��
							</th>
							<td>
								<html:select  property="queryequals_xq"  styleId="xq">
									<html:option value=""/>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>	
							<th>
								ѧ��
							</th>
							<td>
								<logic:equal name="userOnLine" value="student">
									<html:text property="xh" disabled="true" styleId="xh" style="width:90px"/>
									<html:hidden property="querylike_xh" value="${userName}"/>
								</logic:equal>
								<logic:notEqual name="userOnLine" value="student">
									<html:text property="querylike_xh" styleId="xh"  style="width:90px"/>
								</logic:notEqual>
							</td>
							<th>	
								����
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"  style="width:90px"/>
							</td>
							</tr>
							<tr>
							<th >
							<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<logic:equal name="userType"value="xy">
									<html:select property="xydm" disabled="true" style="width:140px"
									onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual name="userType"value="xy" >
									<html:select property="queryequals_xydm" style="width:150px"
									onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="queryequals_zydm" styleId="zy" style="width:140px"
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
								<html:select property="queryequals_bjdm" styleId="bj" style="width:140px">
									<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
									</html:select>	
							</td>
							<th>
								�����ƺ�
							</th>
							<td>
								<html:select property="queryequals_rychdm" styleId="rychdm" style="width:110px">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="dm"
										labelProperty="mc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<th>	
								<bean:message key="lable.xsgzyxpzxy" />���
							</th>
							<td>
								<html:select property="queryequals_xysh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
							<th>
								ѧУ���
							</th>
							<td>
								<html:select property="queryequals_xxsh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								
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
			 		 	<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
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
								ondblclick="modi('mjxyRych.do?method=rychXx&doType=view')" style="cursor:hand">
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
				</div>
				<TABLE width="99%" id="Table" class="tbstyle">
				<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=mjxyRychForm"></jsp:include>
			</logic:notEmpty>
			
		</html:form>
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
  </body>
</html>
