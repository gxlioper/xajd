<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script type="text/javascript" src="js/pjpy/pjpyFunction.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/pjpyTybXysz.do?method=jxjjesz" method="post">
			<input type="hidden" name="message" id="message" value="${message}" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<logic:equal value="false" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType" scope="session">
					<input type="hidden" name="queryequals_xydm" value="${userDep}" />
				</logic:equal>
			</logic:equal>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
							<li> <a href="#" onclick="jxjjesz()" class="btn_ccg">����</a> </li>
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
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/pjpyTybXysz.do?method=jxjjesz&act=qry')">
									��ѯ
								</button>
								<button type="button" class="button2"
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
							<html:select property="queryequals_nd" styleId="nd" style="width:160px">
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
							<html:select property="queryequals_xn" styleId="xn" style="width:160px" onchange="refreshForm('pjpyTybXysz.do?method=jxjjesz&act=qry')">
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
							<html:select property="queryequals_xn" styleId="xn" style="width:160px">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>	
							<html:select property="queryequals_xq" styleId="xq" style="width:160px">
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
							<html:select property="queryequals_nj"
								onchange="initZyList();initBjList();" styleId="nj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" 
									onchange="initZyList();initBjList();" styleId="xy"
									style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
						</td>
						<th>��ѧ����</th>
						<td>
							<html:text property="querygreaterequal_jxjje1" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>-
							<html:text property="querylessequal_jxjje2" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
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
							<font color="blue">��ʾ��������ͷ��������
							</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
						<td id="${tit.en}" onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;">
							<td style="display:none">
							<input type="hidden" id="cbv" name="primarykey_cbv"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>
							<logic:iterate id="v" name="s" offset="1" length="3">
								<td>
									
									<bean:write name="v" filter="false" />
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="4">
								<td>
									<input type="text" name="key_jxjzje" value="${v}" onkeyup="value=value.replace(/[^\d]/g,'') "/>
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyXyszActionForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
				</logic:notEmpty>
				</div>
		</html:form>
		
		<logic:present name="result">
			<script>
				alert(''+$('message').value);
			</script>
		</logic:present>
	</body>
</html>
