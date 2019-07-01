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
			 <logic:equal name="writeAble" value="yes">
			 <div class="buttonbox">
			    <ul>
				  <li> <a href="#" onclick="dataBatch('ntzyWpjy.do?method=wpjyGh&doType=modi&act=gh')" class="btn_gx"> �黹 </a> </li>
			      <li> <a href="#"  onclick="dataBatch('ntzyWpjy.do?method=wpjyGh&doType=modi&act=wgh')" class="btn_qxgx"> �����黹 </a> </li>
			    </ul>
			 </div>
			 </logic:equal>

            <div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('ntzyWpjy.do?method=wpjyGh&doType=qry')">
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
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								ѧ��
							</th>
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
									<html:text property="querylike_sqr" styleId="sqr"/>
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
									<html:text property="querylike_jbr" styleId="jbr"/>
							</td>
						   <th>����</th>
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
							<td colspan="2" />
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
			 		 <logic:notEmpty name="rsNum">
			 		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			<div id="tmpdiv1">
				
			</div>
				<logic:notEmpty name="rs">
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
