<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript"  src="js/xszz/xszzFunction.js"></script>
		<script language="javascript"  src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		function showXxInfo(){
		
			if(curr_row == null){
				alert('��ѡ��Ҫ�鿴�����ݣ�');
				return false;
			}
			
			var xh = curr_row.getElementsByTagName('input')[0].value;
			var kssj = $("kssj").value;
			var jssj = $("jssj").value;
			
			var url = "/xgxt/commXszz.do?method=zzhzUpdate";
				url+= "&xh="+xh;
				url+= "&kssj="+kssj;
				url+= "&jssj="+jssj;
				
			showTopWin(url,800,600);
		}
		</script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
<%--						<li>--%>
<%--							<a href="#" --%>
<%--								onclick="showXxInfo();" --%>
<%--								class="btn_ck">�鿴</a>--%>
<%--						</li>--%>
						<li>
							<a href="#" 
								onclick="wjcfDataExport('commXszz.do?method=zzhzManage&doType=exp');" 
								class="btn_dc">����</a>
						</li>
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
										<button type="button"  class="btn_cx" id="search_go"
											onclick="if(checkSearchTj('kssj','jssj')){allNotEmpThenGo('/xgxt/commXszz.do?method=zzhzManage')};">
											ͳ ��
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('nj-kssj-jssj-xy-zy-bj-xh-xm-xmdm-xmlb-xmmc');">
											�� ��
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
									<html:select property="nj" style="" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<!-- ѧ���û� -->
									<logic:equal name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="" readonly="true"/>
									</logic:equal>
									<!-- ѧ���û� -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="" maxlength="20"/>
									</logic:notEqual>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20"/>			
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="width: 150px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- ��ѧԺ�û� end -->
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="false">
										<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- ��ѧԺ�û� end-->
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width: 150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									��Ŀ���	
								</th>
								<td>
									<html:select property="xmlb" style="" styleId="xmlb" onchange="chXmlb()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��Ŀ
								</th>
								<td>
									<html:select property="xmdm" style="width: 150px" styleId="xmdm" onchange="">
										<html:options collection="xmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:text property="xmmc" styleId="xmmc" style="" maxlength="20" />		
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									ͳ�ƿ�ʼʱ��
								</th>
								<td>
									<html:text property="kssj" styleId="kssj" readonly="true"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');"/>	
								</td>
								<th>
									ͳ�ƽ���ʱ��
								</th>
								<td>
									<html:text property="jssj" styleId="jssj" readonly="true"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');"/>	
								</td>
								<td colspan="2">
									
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
							<logic:notEmpty name="rsArrList">
								<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ.</font>
							</logic:notEmpty>
							<logic:empty name="rsArrList">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rsArrList">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" length="8">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<tbody>
								<logic:iterate name="rsArrList" id="rs" >
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
											ondblclick="showXxInfo()">
										<logic:iterate name="rs" id="v" offset="0" length="1">
											<td align="center">
												${v }
												<input type="hidden" id="checkVal" 
													name="primarykey_checkVal"  
													value="${v }">
											</td>
										</logic:iterate>
										<logic:iterate name="rs" id="v" offset="1" length="7">
											<td align="center">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							<!--���� end-->
						</table>
					<!--��ҳ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
					<script type="text/javascript">
				      $('choose').className="hide";
				     </script>
					<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		</html:form>
		<div id="tmpdiv1"></div>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>