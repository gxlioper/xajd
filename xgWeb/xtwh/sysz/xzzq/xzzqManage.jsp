<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		//��ѯ���
		function searchRs(){
			allNotEmpThenGo('/xgxt/xtwhSysz.do?method=xzzqManage&writeAble='+'${writeAble}');
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
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			<logic:equal value="yes" name="writeAble">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
							<li>
								<a href="#"
									onclick="showDialog('',900,515,'/xgxt/xtwhSysz.do?method=xzzqUpdate&doType=add');return false;"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="showOpenInfoNew('/xgxt/xtwhSysz.do?method=xzzqUpdate','update',900,515);return false;"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/xtwhSysz.do?method=xzzqManage','del');return false;"
									class="btn_sc">ɾ��</a>
							</li>
						
					</ul>
				</div>
				</logic:equal>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('filelx-filemc-kssj-jssj');">
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
									�ļ�����
								</th>
								<td>
									<html:select property="filelx" style="" styleId="filelx">
										<html:options collection="filelxList" property="dm" labelProperty="mc" />
									</html:select>								
								</td>
								<th>
									�ļ���
								</th>
								<td>
									<html:text property="filemc" style="" maxlength="20" styleId="filemc"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									�ϴ�ʱ��
								</th>
								<td>
									<html:text property="kssj" styleId="kssj" style="cursor:hand;width: 70px"
										onclick="return showCalendar('kssj','yyyyMMdd',true,'jssj');"
									/>
									-
									<html:text property="jssj" styleId="jssj" style="cursor:hand;width: 70px"
										onclick="return showCalendar('jssj','yyyyMMdd',false,'kssj');"
									/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end -->
				
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
							<logic:iterate id="tit" name="topTr" offset="1">
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
								ondblclick="showOpenInfoNew('xtwhSysz.do?method=xzzqUpdate','view',900,300);">
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="6">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="7" length="1">
									<td align="left">
										<logic:notEmpty name="v">
											<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${v }" target="_blank" style="color: blue">���ظ���</a>
										</logic:notEmpty>
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