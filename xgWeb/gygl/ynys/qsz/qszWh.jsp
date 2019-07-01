<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>	
		<script language="javascript" src="js/xgutil.js"></script>
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
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="ynysQsz" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="ynys_qszb" />
			<input type="hidden" name="rsNum" ud="rsNum" value="${rsNum}" styleId="rsNum"/>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="dataBatch('ynysQsz.do?method=qszWh&doType=delete')"
									class="btn_csh">��ʼ��</a>
							</li>
							<li>
								<a href="#"
									onclick="modi('ynysQsz.do?method=qscyXx')"
									class="btn_sz">����</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">����</a>
							</li>		
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="wjcfDataExport('ynysQsz.do?method=expDate')"
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
											onclick="allNotEmpThenGo('ynysQsz.do?method=qszWh&doType=qry')">
											��ѯ
										</button>
										<button class="btn_cx" id="cz"
											onclick="czSearchCond('xqdm-lddm-cs-qsh')">
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
									У��
								</th>
								<td>
									<html:select property="queryequals_xqdm" style="width:110px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>											
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="queryequals_lddm" style="width:110px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="queryequals_cs" style="width:110px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="queryequals_qsh" style="width:110px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
							</tr>
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
							<font color="blue">��ʾ��������ͷ��������</font>
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox"  name="all" value="all" onclick="chec()"/>
										
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" >
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
								 style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
											   <input type="hidden" name="checkVal" id="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
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
							page="/sjcz/turnpage.jsp?form=ynysQszForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
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
	</body>
</html>
