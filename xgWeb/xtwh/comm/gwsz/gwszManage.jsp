<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
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
		
		<html:form action="/xtwhGwsz" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="tabName" id="tabName" value="mdqr_xmnrb" />
		    <input type="hidden" name="viewName" id="viewName" value="view_mdqr_xmnrb" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox" >
			    <ul>
<%--			    <li> <a href="#" onclick="showTopWin('/xgxt/xtwhGwsz.do?method=gwszInsert',500,400)" class="btn_zj"> ���� </a> </li>--%>
<%--			    <li> <a href="#" onclick="modi('/xgxt/xtwhGwsz.do?method=gwszUpdate',500,400)" class="btn_xg"> �޸� </a> </li>--%>
<%--			    <li> <a href="#" onclick="dataBatch('/xgxt/xtwhGwsz.do?method=gwszDel')" class="btn_sc"> ɾ�� </a> </li>--%>
<%--				<li> <a href="#" onclick="" class="btn_dc"> ���� </a> </li>--%>
					<li><a href="#" onclick="" class="btn_sd">�û���λ��</a></li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/xtwhGwsz.do?method=gwszManage')">
			              	�� ѯ
			              </button>
			              <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
					<tbody>
						<tr>
							<th >
								��λ����
							</th>
							<td>
								<html:text property="querylike_xtgwmc" styleId="xh"/>
							</td>
							<th>
								�Ƿ�ɼ�
							</th>
							<td>
								<html:select property="queryequals_sfqy" styleId="sfqy"  style="width:150px"
											>
											<html:option value=""></html:option>
											<html:options collection="sfqyList" property="en"
											labelProperty="cn" />
									</html:select>
							</td>
							<th>
								¼����
							</th>
							<td>
								<html:text property="querylike_lrr" styleId="lrr"  style="width:150px"/>	
							</td>
						</tr>
						<tr>
							<th>
								¼��ʱ��
							</th>
							<td colspan="5">
								<html:text property="kssj" styleId="kssj" 
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
								--
								<html:text property="jssj" styleId="jssj" 
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
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
			 		 	<font color="blue">��ʾ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
			<logic:notEmpty name="rs">
				
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
										<input type="checkbox"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" id="pkV"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xtwhGwszForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
					</script>
					
			</logic:notEmpty>
		</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
  </body>
</html>
