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
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>	
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
			
			function getXm(){
				if($("xmlbdm")){
					var xmlbdm=$("xmlbdm").value;
					mdqrDWR.getXmList(xmlbdm,function(data){
						DWRUtil.removeAllOptions($("xmdm"));		
						DWRUtil.addOptions($("xmdm"),data,"dm","mc");
					});
				}
			}
		</script>
	</head>
	<body >
		
		<html:form action="/mdqr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="tabName" id="tabName" value=mdqr_xmszb />
		    <input type="hidden" name="viewName" id="viewName" value="view_mdqr_xmszb" />
		    <input type="hidden" name="gnmk" id="gnmk" value="${gnmk}" /> 
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
			    <logic:equal name="writeAble" value="yes">
			    <li> <a href="#" onclick="showTopWin('/xgxt/mdqr.do?method=mdqrXmsz&gnmk=${gnmk }&doType=add',700,500)" class="btn_zj"> ���� </a> </li>
			    <li> <a href="#" onclick="modi('/xgxt/mdqr.do?method=mdqrXmUpdate&gnmk=${gnmk }&doType=modi',700,500)" class="btn_xg"> �޸� </a> </li>
			    <li> <a href="#" onclick="dataBatch('/xgxt/mdqr.do?method=mdqrXmCx&doType=del')" class="btn_sc"> ɾ�� </a> </li>
				</logic:equal>
				<li> <a href="#" onclick="expData('/xgxt/mdqr.do?method=expDate')" class="btn_dc"> ���� </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrXmCx&gnmk=${gnmk }&doType=query')">
			              	�� ѯ
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
							<th>
								��Ŀ���
							</th>
							<td>
								<html:select property="queryequals_xmlbdm" styleId="xmlbdm"  style="width:150px"
											onchange="getXm()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								��Ŀ
							</th>
							<td>
								<html:select property="queryequals_xmdm" styleId="xmdm"  style="width:150px">
										<html:options collection="xmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								�걨����
							</th>
							<td>
								<html:select property="queryequals_sqzq" styleId="sbzq"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="sbzqList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:select property="queryequals_mdsz" styleId="mdsz"  style="width:150px"
											onchange="getXm()">
										<html:option value=""></html:option>
										<html:options collection="mdszList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								����ȷ��
							</th>
							<td>
								<html:select property="queryequals_mdqr" styleId="mdqr"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="mdqrList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								��˼���
							</th>
							<td>
								<html:select property="queryequals_shjb" styleId="shjb"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="shjbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>		
						<tr>
							<th>
								�ɷ�����
							</th>
							<td>
								<html:select property="queryequals_kfsz" styleId="kfsb"  style="width:150px"
											>
										<html:option value=""></html:option>
										<html:options collection="kfsbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								�ɷ����
							</th>
							<td>
								<html:select property="queryequals_kfsh" styleId="kfsh"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="kfshList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								�ɷ�ȷ��
							</th>
							<td>
								<html:select property="queryequals_kfqr" styleId="kfqr"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="kfqrList" property="dm"
										labelProperty="mc" />
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
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
									
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
								ondblclick="modi('/xgxt/mdqr.do?method=mdqrXmUpdate&gnmk=${gnmk }&doType=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
											   <logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
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
						page="/sjcz/turnpage.jsp?form=mdqrForm"></jsp:include>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
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
