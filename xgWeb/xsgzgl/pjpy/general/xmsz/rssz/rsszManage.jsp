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
			
			var url = "general_xmsz_rssz_ajax.do?method=searchXmszRssz";
			var ie = "ie";
			var xmdm = jQuery("#xmdm").val();

			var arrange = jQuery("#arrange").val();
			var fashion = jQuery("#fashion").val();
			
			if(arrange == ""){
				arrange = "no";
			}
			
			var otherValue = [ie,xmdm,arrange,fashion];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - ��������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.������еĲ����Ǹ����������õ�<font color="blue">�������Ʒ�Χ</font>���ġ�<br/>
				2.�����<font color="blue">��ѡ</font>����ؼ�¼�����<font color="blue">��������</font>��ϵͳ���Թ�ѡ��¼������Ӧ�Ĳ�����<br />
				3.�����<font color="blue">δ��ѡ</font>��һ��¼�����<font color="blue">��������</font>��ϵͳ����<font color="blue">��������</font>Ϊ׼������Ӧ���Ž�����Ӧ�Ĳ�����<br />
				4.�����<font color="blue">�޸�</font>�����������е����ݣ�����Ҫִ��<font color="blue">������������</font>�����ݲŻ���Ч<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xmdm" name="str_xmdm" value="${xmdm }" />
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="#" onclick="goPjszPjxm();return false;" class="btn_fh">
								������Ŀ����
							</a>
						</li>
						<logic:equal name="checkXssq" value="true">
						<li>
							<a href="#" onclick="showSzblDiv();return false;" class="btn_sz">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="checkSaveZzrs();return false;" class="btn_ccg">
								������������
							</a>
						</li>
						</logic:equal>
						<logic:equal name="checkXssq" value="false">
						<li>
							<a href="#" onclick="return false;" disabled="true" class="btn_sz">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="return false;" disabled="true" class="btn_ccg">
								������������
							</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
					//$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ���ñ��������� -->
			<div id="div_szbl" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									<font color="red">*</font>
									����
								</th>
								<td width="">
									<input type="text" name="str_szbl" id="szbl" 
										onblur="checkInputNum(this);chMax(this,100);"
										maxlength="5" 
										style="width : 50%;ime-mode:disabled"
									/>%
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveSzbl()">
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
			<!-- ���ñ��������� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>