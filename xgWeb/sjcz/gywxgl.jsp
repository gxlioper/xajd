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
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/gywx" method="post">
			<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
						value="<bean:write name="pk" scope="request"/>" />
		    <input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
						value="<bean:write name="xxdm" scope="request"/>" />			
			<input type="hidden" id="dxq" name="dxq"
						value="<bean:write name="writeAble" scope="request"/>" />
<%--			<input type="hidden" id="isGyFdy" name="isGyFdy"--%>
<%--						value="<bean:write name="isGyFdy" scope="request"/>" />			--%>
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />												
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" 
									onclick="viewMore('add')"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="viewMore('modi')"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="viewMore('del')"
									class="btn_sc">ɾ��</a>
							</li>
							<logic:present name="isGDBYXY">
								<li>
								<a href="#"
									onclick="dataExport()"
									class="btn_dc">���޵ǼǱ�</a>
								</li>
							</logic:present>		
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
										<input type="hidden" name="go" value="go" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="submit();this.disabled=true">
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
									¥����
								</th>
								<td>
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>									
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh"  styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select  property="xn"  styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>							
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select  property="xq"  styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="bxsj" onblur="dateFormatChg(this)" style="width:80px"
										onclick="return showCalendar('bxsj','y-mm-dd');" style="cursor:hand " />				
								</td>
								<th>
									ά��ʱ��
								</th>
								<td>
									<html:text property="wxsj" onblur="dateFormatChg(this)" style="width:80px"
										onclick="return showCalendar('wxsj','y-mm-dd');" style="cursor:hand " />
								</td>
								<th>
									<logic:present name="isNBZYJSXY">
										ά������     
								   	</logic:present>
								    <logic:present name="isCSMZZYJSXY">
								    	ά�����
									</logic:present>
								</th>
								<td>
									<logic:present name="isNBZYJSXY"> 
								       <html:select property="wxnr"  styleId="wxnr">
									     <html:option value=""></html:option>
									     <html:options collection="wxnrList" property="dm" labelProperty="mc" />
								      </html:select>	
								   	</logic:present>
								    <logic:present name="isCSMZZYJSXY">
								    <html:select property="wxzt" style="width:110px"
											styleId="wxzt">
											<html:option value=""></html:option>
											<html:option value="δά��">δά��</html:option>
											<html:option value="��ά��">��ά��</html:option>
									</html:select>
									</logic:present>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">���޷������ݣ�</font>
							</logic:empty>
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
						    </logic:iterate>" ondblclick="viewMore('view')">
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
			</div>
		</html:form>
  </body>
</html>
