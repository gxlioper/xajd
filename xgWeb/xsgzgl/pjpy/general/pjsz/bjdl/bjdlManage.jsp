<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			//
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_bjdl_ajax.do?method=searchPjszBjdl";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("operation")){
				if($("operation").value=="no"){
					$("btn_sc").disabled=true;
					$("btn_ccg").disabled=true;
				}else{
					$("btn_sc").disabled=false;
					$("btn_ccg").disabled=false;
				}
			}
		}

		//�����ʾ�༶����Div
		function checkShowBjdlDiv(){

			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			// �ж��Ƿ�ѡ�˼�¼
			if(num == 0){
				alertError("����<font color='blue'>��ѡ</font>��Ҫ���õİ༶��");
				return false;
			}
			jQuery("#bjdlmc").val("");
			tipsWindown("ϵͳ��ʾ","id:div_bjdl","300","150","true","","true","id");
			
		}

		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
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
				<span>
				1.�༶����������Ҫ��Ϊ<font color="blue">��������</font>�����������������������һ�µĻ�������<font color="blue">��������</font>��<br />
				2.�����<font color="blue">��ѡ</font>����ؼ�¼�����<font color="blue">����(ȡ��)</font>��ť��ϵͳ���Թ�ѡ��¼������Ӧ�Ĳ�����<br />
				3.�����<font color="blue">δ��ѡ</font>��һ��¼�����<font color="blue">����(ȡ��)</font>��ť��ϵͳ����<font color="blue">��������</font>Ϊ׼������Ӧ�༶������Ӧ�Ĳ�����<br />
				4.���<font color="blue">�鿴</font>��ť�����Բ鿴�Ѿ�ά���õİ༶������Ϣ��������Ҫ������ǰ������ά��������ά���µİ༶���ࡣ<br />
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ҳ����Դ -->
						<logic:equal name="forward" value="jbsz">
						<li>
							<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
								��������
							</a>
						</li>
						</logic:equal>
						<!-- ҳ����Դend -->
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<!-- �ɷ�ִ�� -->
							<li>
								<a href="#" onclick="if(checkItsDis(this)){checkShowBjdlDiv();};return false;" id="btn_ccg" class="btn_ccg">
									�༶��������
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){checkDeleteBjdl();};return false;"  id="btn_sc" class="btn_sc">
									ȡ���༶����
								</a>
							</li>
							<!-- �ɷ�ִ�� end-->
						</logic:equal>
						<!-- ��дȨ end-->
						<li>
							<a href="#" onclick="showBjdlDetail();return false;" class="btn_ck">
								�༶����鿴
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- �༶�������õ����� -->
			<div id="div_bjdl" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�༶��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
<%--							<tr>--%>
<%--								<th width="30%">--%>
<%--									�༶��������--%>
<%--								</th>--%>
<%--								<td width="">--%>
<%--									<input type="radio" name="bjdlsz" id="bjdlsz_new" --%>
<%--										value="yes" --%>
<%--										onclick="setCheckedValue(this);$('tr_new').style.display='';$('tr_old').style.display='none'"--%>
<%--										checked="checked"--%>
<%--										/>�µİ༶����--%>
<%--									<br />--%>
<%--									<input type="radio" name="bjdlsz" id="bjdlsz_old"--%>
<%--										value="no" --%>
<%--										onclick="setCheckedValue(this);$('tr_new').style.display='none';$('tr_old').style.display=''" --%>
<%--										/>���еİ༶����--%>
<%--									<input type="hidden" id="bjdlsz_check" value="new"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr id="tr_new">--%>
<%--								<th width="30%">--%>
<%--									�༶��������--%>
<%--								</th>--%>
<%--								<td width="">--%>
<%--									<input type="text" id="bjdlmc" maxlength="20"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
							<tr id="tr_old" style="">
								<th width="30%">
									�༶��������
								</th>
								<td width="">
									<select id="select_bjdlmc">
										<logic:notEmpty name="bjdlList">
											<logic:iterate name="bjdlList" id="bjdlRs">
												<option value="${bjdlRs.dm }">${bjdlRs.mc }</option>
											</logic:iterate>
										</logic:notEmpty>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveBjdl();return false;">
											ȷ ��
										</button>
										
										<button type="button"  onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �༶�������õ����� end-->
			
			<!-- �༶������ϸ������ begin -->
			<div id="div_bjdl_detail" style="display:none">
				<div class="open_win01" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�༶����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									�༶�������
								</td>
								<td>
									�༶��������
								</td>
							</tr>
							<logic:notEmpty name="bjdlList">
								<logic:iterate name="bjdlList" id="bjdlMap">
									<tr>
										<td>
											${bjdlMap.dm }
										</td>
										<td>
											${bjdlMap.mc }
										</td>
									</tr>
								</logic:iterate>
								<tr>
									<td colspan="2">				
										�༶���࣬��ǰ��<font color="blue">����ά��</font>����ά��
									</td>
								</tr>
							</logic:notEmpty>
							<logic:empty name="bjdlList">
								<tr>
									<td colspan="2">
										�༶���࣬��ǰ��<font color="blue">����ά��</font>����ά��
									</td>
								</tr>
							</logic:empty>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �༶������ϸ������ end -->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>