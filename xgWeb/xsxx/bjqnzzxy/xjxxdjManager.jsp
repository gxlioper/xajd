<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
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
		
		function printXsdjb(url){
			if(curr_row != null){
				wjcfDataExport(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alert('��ѡ�����ݴ�ӡ��');
				return false;
			}
		}
	</script>
</head>
	<body onload="xyDisabled('xy')">
		<html:form action="/xjxxdj.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="realTable" id="realTable" value="${realTable}" />
			<input type="hidden" name="tableName" id="tableName" value="view_${realTable}" />
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
					  		<li> <a href="#" onclick="showTopWin('xjxxdj.do?method=xjxxdjAdd',800,600);" class="btn_zj">�� ��</a> </li>
							<li> <a href="#" onclick="modi('xjxxdj.do?method=xjxxdjModi&type=modi');" class="btn_xg">�� ��</a> </li>
							<li> <a href="#" onclick="dataBatch('xjxxdj.do?method=xjxxdj&type=del');" class="btn_sc">ɾ ��</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					  </logic:equal>
						<li> <a href="#" onclick="showExportDIV('xjxxdj.do?method=xjxxdjExp',600,400);" class="btn_dc">��������</a> </li>
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
									onclick="allNotEmpThenGo('/xgxt/xjxxdj.do?method=xjxxdj')">
									��ѯ
								</button>
								<button type="button" id="cz"
									onclick="czSearchCond('nj-xh-xm-xy-zy-bj');">
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
							<html:select property="queryequals_nj" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<logic:equal value="stu" name="userType">
								<html:text property="querylike_xh" readonly="true"></html:text>						
							</logic:equal>
							<logic:notEqual value="stu" name="userType">
								<html:text property="querylike_xh" styleId="xh"></html:text>
							</logic:notEqual>							
						</td>
						<th>����</th>
						<td>							
							<html:text property="querylike_xm" styleId="xm"></html:text>		
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<logic:notEqual value="true" name="fdyQx">
							<logic:equal value="xy" name="userType">							
								<html:hidden property="queryequals_xydm" value="${userDep}"/>
							</logic:equal>
							</logic:notEqual>
							<html:select property="queryequals_xydm" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>					
						</td>
						<th>�༶</th>
						<td>
							<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
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
							<font color="red">δ�ҵ��κμ�¼��(������Ϣ���࣬��ѯ���ܽ����������ĵȴ���)</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" name="all" value="all" onclick="chec()">
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
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
						ondblclick="modi('xjxxdj.do?method=xjxxdjModi&type=view')" style="cursor:hand">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="primarykey_cbv" id="pkV" value="<bean:write name="v"/>"/>
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="<bean:write name="v"/>" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2">
							<td>
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxdjForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
				</div>
			</div>
		</html:form>

		<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert('�����ɹ���');
				document.getElementById('search_go').onclick();
			</script>			
		</logic:equal>
		<logic:notEqual value="true" name="result">
			<script>
				alert('����ʧ�ܣ�');
				document.getElementById('search_go').onclick();
			</script>
		</logic:notEqual>
		</logic:present>
	</body>
</html>
