<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/bottomButton.js"></script>		
		<script type="text/javascript">
		function myxyDisabled(ele) {
		    var userT = document.getElementById("userType").value;
			if (userT == "xy"||userT =="stu") {
				var tmp = ele.split("-");
				for (i = 0; i < tmp.length; i++) {
					document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
		function hdxfhz(){
		    	     var url = "/xgxt/csmz_sztz.do?method=hdxfhz";	        
			         document.forms[0].action = url;
			         document.forms[0].target = "_blank";
			         document.forms[0].submit();
			         document.forms[0].target = "_self";
		}
		function tzcjhz(){
    	     var url = "/xgxt/csmz_sztz.do?method=tjlgTzcj&pkValue=";
    	     if(curr_row != null){
	    	     url+=curr_row.getElementsByTagName('input')[0].value;       
		         document.forms[0].action = url;
		         document.forms[0].target = "_blank";
		         document.forms[0].submit();
		         document.forms[0].target = "_self";
	         }else{
	         	alert("����ѡ��һ����¼!");
	         }
		}
		</script>		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">		
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips}</a>
				</p>
			</div>

			<div class="toolbox">
				 <!-- ��ť -->
				 <div class="buttonbox">
				    <ul>
				    <logic:equal value="yes" name="writeAble" scope="request">
					<li> <a href="#" onclick="MyMoreDo('add')" class="btn_zj"> ���� </a> </li>
				    <li> <a href="#" onclick="MyMoreDo('modi')" class="btn_xg"> �޸� </a> </li>
					<li> <a href="#" onclick="MyMoreDo('del')" class="btn_sc"> ɾ�� </a> </li>
					</logic:equal>
					<li> <a href="#" onclick="dataExport()" class="btn_dc"> �������� </a> </li>
				    <logic:equal value="12104" name="xxdm">
						 <!-- ����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ѧУ���� -->
						   <li> <a href="#" onclick="hdxfhz()" class="btn_tj"> �ڶ����û��ܱ� </a> </li>
					</logic:equal>
					<!-- ����� �л�ѧԺ ѧ����չ�ɼ����� 2011.3.24 qlj -->
					<logic:equal name="xxdm" value="13897">
						   <li> <a href="#" onclick="tzcjhz()" class="btn_tj"> ��չ�ɼ�ͳ�� </a> </li>
					</logic:equal>
				    </ul>
				 </div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					               <input type="hidden" name="go" value="a" />
					              <button class="btn_cx" id="search_go" 
					              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
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
										�꼶
									</th>
									<td>
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" style="width:85px"></html:text>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" style="width:85px"></html:text>
									</td>
								</tr>
								<tr>
									<th align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										רҵ
									</th>
									<td>
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									<th>
										�༶
									</th>
									<td>
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>	
								<logic:equal value="no" name="ptcx">
								<tr>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="genNdList(this)">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
									</th>
									<td>
									</td>
							</logic:equal>						
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
										<!-- ����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:equal name="xxdm" value="12104">
											<logic:iterate id="tit" name="topTr" offset="2" length="11">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" >
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
											<td>
											��ѧ���ܷ�
											</td>
										</logic:equal>
										<!-- ������ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:notEqual name="xxdm" value="12104">
											<logic:iterate id="tit" name="topTr" offset="2">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" >
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notEqual>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="MyMoreDo('view')">
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>		 
							<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
						    	<script type="text/javascript">
														$('choose').className="hide";
								</script>
				</logic:notEmpty>
			</div>
	</html:form>
    </body>
</html>	
