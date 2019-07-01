<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qssdfp');
		}
		
		//ҳ����ת���ֶ�����ҳ��
		function showQssdfp(){
			refreshForm("gyglQsgl.do?method=qssdfpdDetail");
		}
		
		//ȡ������
		function qxfp(){
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				if (confirm("��Ҫȡ������ѡ���ҵķ����������ȷ�ϣ�")) {
					saveUpdate('/xgxt/gyglQsgl.do?method=qssdfp&doType=del','');
				}
			}else{
				alert("�빴ѡ��Ҫȡ����������ң�");
			}
		}
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next==""){
				next=$("next_1").value;
			}
			
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else {
				next+="&doType=scjc";
				refreshForm(next);
			}
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>���ҷ��� - �ֶ�����</a>
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
				�������չ����ϢΪ���ҷ����¼����ѡϣ���ص��������ҷ����¼����������ҵ�������������Թ�ѡ������ɵ���������
				���û�й�ѡ�Ļ��������ȫ�����ҽ��е���������
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ END-->
		
		<html:form action="/gyglQsgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="czxq" name="czxq" value="${czxq}"/><!-- ����У�� -->
			<input type="hidden" id="czyq" name="czyq" value="${czyq}"/><!-- ����԰�� -->
			<input type="hidden" id="xqdm" name="xqdm" value="${xqdm}"/><!-- У������ -->
			<input type="hidden" id="yqdm" name="yqdm" value="${yqdm}"/><!-- ԰������ -->
			<input type="hidden" id="lddm" name="lddm" value="${lddm}"/><!-- ¥������ -->
			<input type="hidden" id="cs" name="cs" value="${cs}"/><!-- ���� -->
			<input type="hidden" id="qsh" name="qsh" value="${qsh}"/><!-- ���Һ� -->
			<input type="hidden" id="dm" name="dm" value="${dm}"/><!-- ����԰�� -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ���� -->
						<li>
							<a href="#" id="btn_fp"
								onclick="showQssdfp();return false;"
								class="btn_sx">
								���ҵ���
							</a>
						</li>
						<!-- ȡ�� -->
						<li>
							<a href="#" id="btn_qx"
								onclick="setSearchTj();qxfp();return false;"
								class="btn_qx">
								ȡ������
							</a>
						</li>
						<!-- ���� -->
						<li>
							<a href="#" id="btn_fh"
								onclick="refreshForm('gyglQsgl.do?method=qsfptj&mklx=fh');return false;"
								class="btn_fh">
								<bean:message key="lable.btn_fh" />
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->	
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
							&nbsp;&nbsp;<font color="blue"></font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" length="8">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
							<td id="rzzt" onclick="tableSort(this)" nowrap>
								 ��ס״̬
							</td>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>_<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
									/>
								</td>
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="0" length="8">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								
								<!-- ��ס״̬ -->
								<logic:iterate id="v" name="s" offset="9" length="1">
									<td align="left">
										${v }
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
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglQsglForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/delMessage.jsp"%>
			<logic:notPresent name="delMessage">
				<logic:present name="message">
				<script>
					alert($("message").value);	
					$("message").value = "";
					$("doType").value = "";	
				</script>
				</logic:present>
			</logic:notPresent>		
		</html:form>
	</body>
</html>