<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">	
	function tbXfxx(){
		if (confirm("ȷ��Ҫͬ��ѧ����Ϣ?")) {
			showTips('���������У���ȴ�......');
			var url = "/xgxt/njjsXszz.do?method=xfxxManage&doType=tb";
			refreshForm(url);
		}
	}
	</script>
</head>
	<body>
		<html:form action="/njjsXszz" method="post">
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
							<li> <a href="#" onclick="tbXfxx();" class="btn_sx">ͬ������</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					  </logic:equal>
					  <li> <a href="#" onclick="showExportDIV('njjsXszz.do?method=xfxxManage&doType=exp');" class="btn_dc">��������</a> </li>	
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
									onclick="allNotEmpThenGo('/xgxt/njjsXszz.do?method=xfxxManage');return false;">
									��ѯ
								</button>
								<button type="button" id="cz"
									onclick="czSearchCond('xn-nd-nj-xh-xm-xy-zy-bj-sfxm-sfqf-xfsfqf-xfsfxm');return false;">
									����
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="queryequals_nj" styleId="nj" style="width:160px" onchange="initZyList();initBjList()" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="queryequals_xn" styleId="xn" onchange="" style="width:160px">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>		
						</td>
						<th>���</th>
						<td>
							<html:select property="queryequals_nd" styleId="nd" onchange="" style="width:160px">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>		
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<!-- ѧԺ -->
							<logic:equal name="userType" value="xy">
								<html:hidden property="queryequals_xydm"/>
								<html:select property="queryequals_xydm" styleId="xy" disabled="true" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<!-- ��ѧԺ -->
							<logic:notEqual name="userType" value="xy">
								<html:select property="queryequals_xydm" styleId="xy" onchange="initZyList();initBjList();" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</logic:notEqual>
						</td>
						<th>רҵ</th>
						<td>	
							<html:select property="queryequals_zydm" styleId="zy" onchange="initBjList()" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>	
						</td>
						<th>�༶</th>
						<td>
							<html:select property="queryequals_bjdm" style="width:160px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>			
						</td>
					  </tr>	
					  <tr>
						<th>ѧ��</th>
						<td>
							<html:text property="querylike_xh" styleId="xh" style="width:160px" maxlength="20"/>
						</td>						
						<th>����</th>
						<td>
							<html:text property="querylike_xm" styleId="xm" style="width:160px" maxlength="20"/>			
						</td>
						<th>�շ���Ŀ</th>
						<td>
							<html:text property="querylike_xfsfxm" styleId="xfsfxm" style="width:160px" maxlength="50"/>		
						</td>
					  </tr>
					  <tr>
						<th>�Ƿ�Ƿ��</th>
						<td>
							<html:select property="queryequals_xfsfqf" style="width:160px" styleId="xfsfqf">
								<html:option value="">----��ѡ��----</html:option>
								<html:options collection="sfList" property="en" labelProperty="cn" />
							</html:select>	
						</td>						
						<th></th>
						<td>
										
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
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr" offset="0">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s" indexId="index">
					<tr onclick="rowOnClick(this);" style="cursor:hand" 
						ondblclick="">
						<logic:iterate id="v" name="s" offset="0" length="1">
							<td align="center">
								<bean:write name="v" />	
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="1">
							<td align="left">
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				</logic:notEmpty>
				</div>					
			</logic:empty>
		</html:form>

		<logic:notEmpty name="result">
			<logic:equal name="result" value="true">
			<script>
				if($("message") && $("message").value != ""){
					alert($("message").value);
					$("message").value = "";
					$("doType").value = "";
				}
			</script>
			</logic:equal>
		</logic:notEmpty>		
	</body>
</html>
