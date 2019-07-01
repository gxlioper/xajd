<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//��ѯ���
		function searchRs(){
			if(checkSjTj('kssj','��ʼʱ��','jssj','����ʱ��')){
				allNotEmpThenGo('/xgxt/xtwhSysz.do?method=sydcManage');
			}
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
		<html:form action="/xtwhSysz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="del_message" id="del_message" 
				value="ɾ����ͬʱ��������ͳ�ƣ������Ҫ������ֻ��ر��䡮���á�״̬����,ȷ��ɾ����"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/xtwhSysz.do?method=sydcUpdate','800','600');return false;"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="showInfo('/xgxt/xtwhSysz.do?method=sydcUpdate','update','800','600');return false;"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/xtwhSysz.do?method=sydcManage','del');return false;"
									class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#"
									onclick="saveUpdate('/xgxt/xtwhSysz.do?method=sydcManage&doType=save','');return false;"
									class="btn_ccg">����</a>
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
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('dcnr-sfqy-kssj-jssj');return false;">
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
									��������
								</th>
								<td>
									<html:text property="dcnr" style="" maxlength="20" styleId="filemc"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>						
								</td>
								<th>
									�Ƿ�����
								</th>
								<td>
									<html:select property="sfqy" style="" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfqyList" property="en" labelProperty="cn" />
									</html:select>		
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
										onclick="return showCalendar('kssj','y-mm-dd');"
									/>
									-
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;width: 70px"
										onclick="return showCalendar('jssj','y-mm-dd');"
									/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ�����end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">
			 	<h3 class="datetitle_01">
				    <span>
				    	��ѯ���
				    	<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							 &nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</logic:notEmpty>
				    </span>
			    </h3>	
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" length="4">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="showInfo('/xgxt/xtwhSysz.do?method=sydcUpdate','view','800','600')">
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" title="<logic:iterate id="nr" name="s" offset="5" length="1">${nr }</logic:iterate>">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="2">
									<td align="left">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4" length="1">
									<td align="left">
										<logic:equal name="v" value="��">
											<input type="radio" name="dcqy" id="dcqy${index }" value="<logic:iterate id="dc" name="s" offset="0" length="1">${dc }</logic:iterate>" checked="checked"/>
										</logic:equal>
										<logic:equal name="v" value="��">
											<input type="radio" name="dcqy" id="dcqy${index }" value="<logic:iterate id="dc" name="s" offset="0" length="1">${dc }</logic:iterate>"/>
										</logic:equal>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>					
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>