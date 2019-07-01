<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetGyglDataInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>	
	</head>

	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��� - ��Ԣ(����)ά�����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/XsgyglDispatch.do?method=gywxSqShXx" method="post">
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
		    <input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />															
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" 
								onclick="toWxpqd()"
								class="btn_down">ά����ǲ��</a>
						</li>
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
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsgyglDispatch.do?method=gywxSqShXx');">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									ѧ��	
								</th>
								<td>
									<html:select property="xn" style="width:90px" styleId="xn">
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
									¥����
								</th>
								<td>
									<html:select property="lddm" style="width:120px"
										onchange="GetQshList()" styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width:110px" styleId="qsh">
										<html:option value=""></html:option>
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>										
								</td>
								<th>
									����ʱ��	
								</th>
								<td>
									<html:text property="kssj" onblur="dateFormatChg(this)"
										onclick="return showCalendar('kssj','y-mm-dd');" 
										style="cursor:hand;width:80px " />
									--
									<html:text property="jssj" onblur="dateFormatChg(this)"
										onclick="return showCalendar('jssj','y-mm-dd');" 
										style="cursor:hand;width:80px " />
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
							<!-- ������ -->
							<logic:present name="isCSMZZYJSXY">
							<tr>
								<th>
									������
								</th>
								<td>
									<html:select property="fzbm" style="width:150px"
										styleId="fzbm">
										<html:options collection="fzbmList" property="fzbmdm"
											labelProperty="fzbmmc" />
									</html:select>							
								</td>
								<th>
									
								</th>
								<td>
								
								</td>
								<th>
									
								</th>
								<td>
								</td>
							</tr>
							</logic:present>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������޸������״̬��������ͷ��������</font>
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
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
							<!-- ��ͷ end-->
							<!--���� -->
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
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>.
  </body>
 <script type="text/javascript">
  function CheckDo(){
    var	w = 600;
	var	h = 500;	
	url = "/xgxt/XsgyglDispatch.do?method=gywxSqSh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
  }
function toWxpqd(){  
    if (curr_row==null || curr_row=='') 
    {
		alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
		return false;
	}
	var pkV = curr_row.cells[0].getElementsByTagName("input")[0].value;
    var	w = 600;
	var	h = 500;
	var url = "/xgxt/XsgyglDispatch.do?method=toWxRypq";	
	url += "&pkValue=";
    url += pkV;
	GetGyglDataInfo.getInfoExist("gywxglsqb"," xxsh='ͨ��' and ssbh||bxsj=\'"+pkV+"\'",function(data){
	   if(data){
	     showOpenWindow(url, w, h,'yes','yes');
	   }else{
	     alert("��������δͨ����ˣ��ݲ��ܴ�ӡ��ǲ����");
	   }  	
	});
  }
  </script>
</html>
