<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript">
		 	function zmPrint(){
		       if(curr_row == null ){
				  alert("��ѡ��Ҫ���������ݣ�\n����һ�м�¼����");
				  return false;
			    } 
		       	var xh = (curr_row.cells[0].getElementsByTagName("input"))[0].value;
		       	url = "/xgxt/outPutstuManager.do?print=wszm&xh="+xh;
		       	showTopWin(url,'700','600');
		       //	showTopWin("/xgxt/","600","700");
		    }
		</script>
	</head>
	<body>
		<html:form action="/outPutstuManager" method="post">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" scope="request" /></a>
			</p>
		</div>
		<!-- ���� end-->
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="title" value="${title }"/>
			<input type="hidden" name="writeAble" value="${writeAble }"/>
            <input type="hidden" name="isFdy" id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
            <input type="hidden" name="userName" value="${userName }"/>
            <logic:present name="isxy">
            	<script defer="defer">
            		$('xy').disabled=true;
            	</script>
            	<input type="hidden" name="xydm" value="${userDep }"/>
            </logic:present>	    			
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="viewInfo('add','/xgxt/stuOutputInfo.do')"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="viewInfo('modi','/xgxt/stuOutputInfo.do')"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="viewInfo('del','/xgxt/outPutstuManager.do')"
									class="btn_sc">ɾ��</a>
							</li>
							<logic:present name="isNCDXKJXY">
							<li>
								<a href="#"
									onclick="zmPrint()"
									class="btn_qb">����֤��</a>
							</li>
							</logic:present>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">����</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">����</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value ='go';refreshForm('/xgxt/outPutstuManager.do')">
											��ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select  property="nj"  styleId="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>ѧ��</th>
								<td>
									<html:select  property="xn"  styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:select  property="xq"  styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									<html:select property="xydm"  styleId="xy" style="width : 150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm"  styleId="zy" style="width : 150px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>�༶</th>
								<td>
									<html:select property="bjdm"  styleId="bj" style="width : 150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									��ס����
								</th>
								<td>
									<html:select  property="wzlxdm"  styleId="wzlxdm">
										<html:option value=""></html:option>
										<html:options collection="wzlxList" property="wzlxdm" labelProperty="wzlxmc" />
									</html:select>												
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text  property="xh" style="width: 80px" styleId="xh"/>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									����	
								</th>
								<td>
									<html:text  property="xm" style="width: 80px" styleId="xm" maxlength="10"/>								
								</td>
								<th>
									��ס��ʼ����
								</th>
								<td colspan="3">
									<html:text  property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
										onclick="return showCalendar('kssj','y-mm-dd');"/>	
									--
									<html:text  property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 100px"
										onclick="return showCalendar('jssj','y-mm-dd');"/>	
								</td>
							</tr>
							<logic:present name="showxbemy">
							<!-- ������ -->
							<tr>
								<th>
									�Ա�
								</th>
								<td>
									<html:select property="xb" styleId="xb">
							    	<html:option value=""></html:option>
							    	<html:option value="��">��</html:option>
							    	<html:option value="Ů">Ů</html:option>
							    </html:select>					
								</td>
								<th>
									����
								</th>
								<td>
									<html:select property="mz"  styleId="mz">
										<html:option value=""></html:option>
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>				
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="jg" styleId="jg" style="width:80px"></html:text>
								</td>
							</tr>
							</logic:present>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
								<!-- ��ѯ���-->
						<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="viewInfo('view','/xgxt/stuOutputInfo.do')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
							  <script type="text/javascript">
						      $('choose').className="hide";
						     </script>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	

		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
</html>
