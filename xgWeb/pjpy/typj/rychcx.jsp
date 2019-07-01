<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script type="text/javascript">
	function print() {
		var url = '/xgxt/typj.do?method=rychPrint';
		if (null != curr_row) {
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			url+='&pkValue='+pkValue;
		}
		window.open(url);
	}
	function disXhxm() {
		var userType = $('userType').value;
		if (userType=='stu') {
			$('xh').readOnly=true;
			$('xy').disabled=true;
		}
	}
</script>
</head>
	<body onload="xyDisabled('xy');disXhxm()">		
		<html:form action="/typj" method="post">
			<input type="hidden" name="url" id="url" value="${url }"/>
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }"/>
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="shjb" name="shjb" value="${shjb }"/>
			
			<logic:equal value="false" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType" scope="session">
					<input type="hidden"  name="queryequals_xydm" value="${userDep }"/>
				</logic:equal>
			</logic:equal>

			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
							<li> <a href="#" onclick="showTopWin('/xgxt/typj_yrchsq.do?lx=gb',750,600);" class="btn_zj">�� ��</a> </li>
						    <li> <a href="#" onclick="var shjb = $('shjb').value;showInfo('/xgxt/typj.do?method=rychView&shjb='+shjb,'update','900','600');" class="btn_xg">�޸�</a> </li>
							<li> <a href="#" onclick="deletedata('/xgxt/typj.do?method=rychcx&doType=del');" class="btn_sc">ɾ��</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					  </logic:equal>
					  		<li> <a href="#" onclick="expData('/xgxt/typj.do?method=rychcx&doType=expData');" class="btn_dc">��������</a> </li>
							<logic:equal value="11355" name="xxdm" scope="session">
							<li> <a href="#" onclick="print();" class="btn_dy">��ӡ����</a> </li>
							</logic:equal>
							<logic:equal value="10657" name="xxdm" scope="session">
							<li> <a href="#" onclick="print();" class="btn_dy">��ӡ����</a> </li>
							</logic:equal>				
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/typj.do?method=rychcx&doType=query')">
									��ѯ
								</button>
								<button
									onclick="searchReset();return false;">
									����
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <!--2010.10.11 by lr-->
						<!--��������Ϊ���-->
						<logic:equal value="nd" name="pjzq">
						<tr>
						<th>���</th>
						<td>
							<html:select property="queryequals_nd" styleId="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th></th>
						<td>		
						</td>
						<th></th>
						<td>
							
						</td>
					  	</tr>
						</logic:equal>
						<!--end��������Ϊ���-->

						<!--��������ѧ��-->
						<logic:equal value="xn" name="pjzq">
					    <tr>
						<th>ѧ��</th>
						<td>
							<html:select property="queryequals_xn" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th></th>
						<td>		
						</td>
						<th></th>
						<td>
							
						</td>
					  	</tr>
						</logic:equal>
						<!--end��������ѧ��-->
						
						<!--��������ѧ��-->
						<logic:equal value="xq" name="pjzq">
						<tr>
						<th>ѧ��</th>
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
						<th></th>
						<td>
							
						</td>
					  	</tr>
						</logic:equal>
						<!--end��������ѧ��-->
						<!--end 2010.10.11 by lr-->
					   <tr>
						<th>�꼶</th>
						<td>
							<html:select property="queryequals_nj" onchange="initZyList();initBjList()" styleId="rxnf">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>	
							<html:text property="querylike_xh" maxlength="20" style="width:80px" styleId="xh"></html:text>	
						</td>
						<th>����</th>
						<td>
							<html:text property="querylike_xm" maxlength="20" style="width:80px"></html:text>	
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
						</td>
						<th>רҵ</th>
						<td>	
							<html:select property="queryequals_zydm" onchange="initBjList()"  styleId="zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>	
						</td>
						<th>�༶</th>
						<td>
							<html:select property="queryequals_bjdm"  styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>�����ƺ�</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm">
								<html:options collection="rychList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th>���״̬</th>
						<td>	
							<html:select property="queryequals_shzt"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>	
						</td>
						<th>����Ա���</th>
						<td>
							<html:select property="queryequals_fdysh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>							
						</td>
					  </tr>					
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" />���</th>
						<td>
							<html:select property="queryequals_xysh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th>ѧУ���</th>
						<td>	
							<html:select property="queryequals_xxsh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>  
						</td>
						<th></th>
						<td>						
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="var shjb = $('shjb').value;showInfo('/xgxt/typj.do?method=rychView&shjb='+shjb,'view','900','600')"
							style="cursor:hand;">
							<td align=center>
								<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
						   		<input type="hidden" value="<bean:write name="v" />"/>
						    </td>
							<logic:iterate id="v" name="s" offset="1" length="6">
								<td align=center>
									<bean:write name="v"/>
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="7">
								<td>
									<bean:write name="v" filter="false"/>
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<script>
				alert(''+$('message').value);
				document.getElementById('search_go').click();
			</script>
	</logic:present>
	</body>
</html>
