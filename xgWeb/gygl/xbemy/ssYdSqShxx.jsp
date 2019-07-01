<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>	
	</head>
	<body onload="xyDisabled('xy');">

		<html:form action="/XsgyglDispatch.do?method=ssYdSqShXx" method="post">
		    <input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
		    <input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />
		     <input type="hidden" name="zyV" id="zyV" />				
			<input type="hidden" name="bjV" id="bjV" />	
			<input type="hidden" name="userType" id="userType"
			       value="<bean:write name="userType"/>"/>																
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - ��� - �����춯��� </a>
				</p>
			</div>
			<div class="toolbox" id="btn">
				 <!-- ��ť -->
				 <div class="buttonbox">
				    <ul>
						<li> <a href="#" onclick="spbPrint()" class="btn_dy" >��˱��ӡ </a> </li>
					    <li> <a href="#"onclick="dataExport()" class="btn_xg"> �������� </a> </li>
				    </ul>
				 </div>
				<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="go" />
			              <button class="btn_cx" id="search_go" 
			              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsgyglDispatch.do?method=ssYdSqShXx');">
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
									<html:select property="nj" style="width:90px" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:select  property="xn" style="width:90px" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:select  property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>	
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:text  property="xh" style="width:80px" styleId="xh"/>
							</td>
							<th>								
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width:150px" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" style="width:150px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
							</td>
							<th>
								�춯ʱ��
							</th>
							<td>
							<html:text property="kssj" style="width:80px" onblur="dateFormatChg(this)"onclick="return showCalendar('kssj','y-mm-dd');" />
								��
							<html:text property="jssj" style="width:80px"  onblur="dateFormatChg(this)"onclick="return showCalendar('jssj','y-mm-dd');" />							
							</td>
							<th>
								���״̬
							</th>
							<td>
								<html:select property="yesNo" styleId="yesNo">
								    <html:option value=""></html:option>
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
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
			 		 	��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������޸������״̬��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			
			<logic:notEmpty name="rs">
					  <table summary="" class="datelist" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
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
								style="cursor:hand;background-color:
	    					<logic:iterate id="v" name="s" offset="0" length="1">
						    	<bean:write name="v"/>
						    </logic:iterate>" ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
		</html:form>
  </body>
 <script type="text/javascript">
  function CheckDo(){
    var	w = 600;
	var	h = 550;	
	url = "/xgxt/XsgyglDispatch.do?method=ssYdSqSh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
  }
   function spbPrint() {
     if(curr_row!=null){
	    document.forms[0].action = "/xgxt/XsgyglDispatch.do?method=sSyDsPb&pkValue="+curr_row.cells[0].getElementsByTagName("input")[0].value;
 	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }else{
        alert("��ѡ��Ҫ��ӡ�ļ�¼��\n��������Ӧ���У�");
        return false;
     }
   }
  </script>
</html>

