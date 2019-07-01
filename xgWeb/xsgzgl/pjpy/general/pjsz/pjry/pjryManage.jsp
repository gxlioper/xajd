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
			
			var url = "general_pjsz_pjry_ajax.do?method=searchPjszPjry";
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
					$("btn_xg").disabled=true;
					$("btn_ccg").disabled=true;
					$("btn_sc").disabled=true;
				}else{
					$("btn_xg").disabled=false;
					$("btn_ccg").disabled=false;
					$("btn_sc").disabled=false;
				}
			}
		}
		
		//�����༶����
		function disfrockBjtz(){
		
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			var flag=true;
			
			// �ж��Ƿ�ѡ�˼�¼
			if(num == 0){
				alertError("����<font color='blue'>��ѡ</font>��Ҫȡ�������༶��ѧ����¼��");
				return false;
			}else{
			
				// �жϹ�ѡ��¼���Ƿ���ڡ�δ�������ļ�¼
				jQuery("input[name=primarykey_checkVal]:checked").each(function(i){
					var dhbj = jQuery(this).parents("tr").children("td").eq(5).html();
					if(dhbj=="δ����"){
					
						alertInfo("��ѡ��¼�д���<font color='blue'>δ����</font>�༶��ѧ������ȷ�ϣ�");
						flag=false;
					}
				})
			
				
				
				if(flag){
					// ���������༶����
					confirmInfo("�ò�������<font color='blue'>�����ѹ�ѡѧ���༶������Ϣ</font>���Ƿ�ȷ��Ҫ����ִ�иò�����",function(tag){
						
						if(tag=="ok"){
							var xh=new Array();
					
							jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").each(function(i){
								xh[i]=jQuery(this).val();
							});
							
							var url = "general_pjsz_pjry_ajax.do?method=disfrockBjtz";
							var parameter = {
						 		"xh":xh.join("!!@@!!")
							};
							jQuery.post(url,
								parameter,
								function(result){
									$("divWaiting").style.display="none";
									$("divDisable").style.display="none";
									alertInfo(result);
					
									searchRs();
									closeWindown();
									
								}
							);
						}
					
					});
				}
			}
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
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - ������Ա������</a>
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
				1.�������չʾ���Ǳ����������ڵ�<font color="blue">��Ա����</font>��<br/>
				2.�����ѧ�����ڵİ༶<font color="blue">����</font>����������ʵ�ʴ��ڵİ༶���������е�����<br/>
				3.�����<font color="blue">��ѡ</font>����ؼ�¼�����<font color="blue">�����༶</font>��ť��ѡ��ϣ�������İ༶��<br />
				4.���ĳѧ��û���ʸ���뱾����������ִ��<font color="blue">�����Ƿ����</font><br />
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="bjdm_check"/>
			<input type="hidden" id="operation" name="operation" value="${operation }"/>
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
						
						<logic:equal name="userType" value="admin">
							<li>
								<a href="#" onclick="showBjtzDiv();return false;" disabled="true" id="btn_xg" class="btn_xg">
									�����༶
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){showSfcpDiv();}return false;" disabled="true" id="btn_ccg" class="btn_ccg">
									�����Ƿ����
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){disfrockBjtz();}return false;" disabled="true" id="btn_sc" class="btn_sc">
									ȡ�������༶
								</a>
							</li>
						</logic:equal>
						
						<logic:notEqual name="userType" value="admin">
							<li>
								<a href="#" onclick="showBjtzDiv();return false;" disabled="true" id="btn_xg" class="btn_xg">
									�����༶
								</a>
							</li>
							<li>
								<a href="#" onclick="if(checkItsDis(this)){showSfcpDiv();}return false;" disabled="true" id="btn_ccg" class="btn_ccg">
									�����Ƿ����
								</a>
							</li>
							<li style="display: none">
								<a href="#" onclick="if(checkItsDis(this)){showSfcpDiv();}return false;" disabled="true" id="btn_ccg" class="btn_ccg">
									�����Ƿ����
								</a>
							</li>
							<li style="">
								<a href="#" onclick="if(checkItsDis(this)){disfrockBjtz();}return false;" disabled="true" id="btn_sc" class="btn_sc">
									ȡ�������༶
								</a>
							</li>
						</logic:notEqual>
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
			
			<!-- �༶���������� -->
			<div id="div_bjtz" >
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�����༶</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									�꼶
								</th>
								<td width="">
									<html:select property="nj" style="width: 200px" onchange="initZyList();initBjList();" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="">
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td width="">
									<html:select property="xydm" style="width: 200px" styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="">
									רҵ
								</th>
								<td width="">
									<html:select property="zydm" style="width: 200px" styleId="zy" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th width="">
									<font color="red">*</font>�༶
								</th>
								<td width="">
									<html:select property="bjdm" style="width:200px" styleId="bj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveBjtz()">
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
			<!-- �༶���������� end-->

			<!-- �������õ����� -->
			<div id="div_cpsz" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ɷ��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									�����ʸ�
								</th>
								<td width="">
									<input type="radio" name="sfcp" id="sfcp_yes" 
										value="yes" onclick="setCheckedValue(this)"/>���ʸ����
									<input type="radio" name="sfcp" id="sfcp_no"
										value="no" onclick="setCheckedValue(this)" 
										checked="checked"/>���ʸ����
									<input type="hidden" id="sfcp_check" value="no"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveCpsz()">
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
			<!-- �������õ����� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>