<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			$("doType").value="";
			allNotEmpThenGo('gzdx_gygl_wmqs_qsgswh.do');
		}
		
		//�����������Ҹ���
		function save(){
			$("doType").value="save";
			allNotEmpThenGo('gzdx_gygl_wmqs_qsgswh.do');
		}
		
		//�����������Ұٷֱ�
		function setWmqsbfb(){
			var obj = {csdm:"gzdx_wmqsbfb"};
			jQuery.getJSON('gzdxWmqs.do?method=getWmqsbfb', obj, function(data){
				if(data != null){
					jQuery('#wmqsbfb').val(data.csz);
				}	
			});
			tipsWindown("������ʾ","id:tempDiv","300","150","true","","true","id");
		}	
		function saveSetWmqsbfb(){
			var wmqsbfb=jQuery('#wmqsbfb').val().trim();
			if(isNaN(wmqsbfb.replace("%",""))){
				alert("��������ȷ�ĸ�ʽ��");
				return false;
			}
			var obj = {csz:jQuery('#wmqsbfb').val().trim()};
			jQuery.post('gzdxWmqs.do?method=saveWmqsbfb', obj, function(data){
				if(data != null){
					alert(data);
					searchRs();
				}	
			});
		}	
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				�������ø�<bean:message key="lable.xb" />�����������ͨ��������
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gzdxWmqs.do?method=qsgswhManage">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="primarykey_checkVal" id="primarykey_checkVal" />
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>						
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_xg"
								onclick="save();return false;"
								class="btn_xg">
								����
							</a>
						</li>
						<li>
							<a href="#" id="btn_sz"
								onclick="setWmqsbfb();return false;"
								class="btn_sz">
								��������
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>		
				<!-- ��ť end-->	
				
				<!-- �������� -->
				
<%--				<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
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
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort_hc(this,1)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:empty name="rsArrList">
					  	<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px" style="display: none;">
									<input type="checkbox" id="pk_${index }"
									value="${v }"/>
									<input type="hidden"  name="xydms" value="${v }"/>
								</td>
							</logic:iterate>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="1" length="3">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="4" length="1">
								<td align="left">
									<input type="text" name="wmqsgss" value="${v }" onkeyup="checkInputData(this);" maxlength="4"/>
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<!--���� end-->
					<!-- ������ -->
					<%--<%@ include file="/comm/noRows.jsp"%>
					--%><!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
<%--				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gzdxGyglWmqsForm"></jsp:include>--%>
				<script type="text/javascript">
					//$('choose').className="hide";
				</script>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			<logic:present name="back">
				<script type="text/javascript">
					alert("${back}");				
				</script>
			</logic:present>
			
			
			
						<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">�������������Ұٷֱ�</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th id="th_rzsj">
									�������Ұٷֱ�
								</th>
								<td>
									<input type="text" name="wmqsbfb" id="wmqsbfb" maxlength="6"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="����"  onclick="saveSetWmqsbfb()">
											ȷ �� 
										</button>
										<button type="button" name="ȡ��"  onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			
			
		</html:form>
	</body>
</html>