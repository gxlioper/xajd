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
			
			var url = "general_pjsz_pjxm_ajax.do?method=searchPjszPjxm";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			setTimeout("setDivHeight()","1000");

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//��ʾ˳������
		function showXmszXmsy(){
			alertInfo("δ��ɣ������ڴ�");	
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
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �������� - ��Ŀ����</a>
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
				1.������Ĭ��չʾ����<font color="blue">����������</font>��������Ŀ�����������е�<font color="blue">��ѧ���������ƺ�</font>��<br/>
				2.�����������һ���µ�������Ŀ������<font color="blue">����</font>��<br/>
				3.�����������ĳ��Ŀ����Ϣ������<font color="blue">�޸�</font>����ע�⣬���ĳ��Ŀ�Ѿ���<font color="blue">ѧ������</font>���޸����ݽ��ܵ�<font color="blue">����</font>��<br/>
				4.�������ɾ��ĳ��Ŀ������<font color="blue">ɾ��</font>����ע�⣬���ĳ��Ŀ�Ѿ���<font color="blue">ѧ������</font>������Ŀ��<font color="blue">����</font>��ɾ����<br/>
				5.��ѡһ����Ŀ�����<font color="blue">��������</font>������Ϊ����Ŀ�����������ޣ���ע�⣬�������Ŀ�����������ã���<font color="blue">���ɽ������</font>��<br/>
				6.��ѡһ����Ŀ�����<font color="blue">�������</font>������Ϊ����Ŀ����<font color="blue">���ɼ��</font>����Ŀ��<br/>
				7.��ѡһ����Ŀ�����<font color="blue">ʱ������</font>������Ϊ����Ŀ����<font color="blue">������˵Ŀ�ʼ����ʱ��</font>��<br/>
				8.�����ӵ���Ŀ��ϵͳĬ�ϸ���Ŀ<font color="blue">����</font>����<font color="blue">���������</font>���������ʱ�����á�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="str_xmdm" id="xmdm"/>
			
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
						<li>
							<a href="#" onclick="showPjszPjxm();return false;" class="btn_zj">
								����
							</a>
						</li>
						<li>
							<a href="#" onclick="showPjxmUpdate();return false;" class="btn_xg">
								�޸�
							</a>
						</li>
						<li>
							<a href="#" onclick="deletePjxm();return false;" class="btn_sc">
								ɾ��
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmszPjtj('');return false;" class="btn_csh">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmszRssz('');return false;" class="btn_ck">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmszXmjd('');return false;" class="btn_sz">
								�������
							</a>
						</li>
<%--						<li>--%>
<%--							<a href="#" onclick="showXmszXmsy();return false;" class="btn_sx">--%>
<%--								��Ŀ˳������--%>
<%--							</a>--%>
<%--						</li>--%>
						<li>
							<a href="#" onclick="showXmszSjsz('');return false;" class="btn_cs">
								ʱ������
							</a>
						</li>
						<li>
							<a href="#" onclick="showXmxzDetail();return false;" class="btn_ck">
								��Ŀ���ʲ鿴
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
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--					$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ������õ����� -->
			<div id="div_xmjd" style="display:none">
				<div class="open_win01">

				</div>
			</div>
			<!-- ������õ����� end-->
			
			<!-- ʱ�����õ����� -->
			<div id="div_sjsz" style="display:none">
				<div class="open_win01">
					
				</div>
			</div>
			<!-- ʱ�����õ����� end-->
			
			<!-- ��Ŀ������ϸ������ begin -->
			<div id="div_xmxz_detail" style="display:none">
				<div class="open_win01" style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��Ŀ����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									��Ŀ���ʴ���
								</td>
								<td>
									��Ŀ��������
								</td>
							</tr>
							<logic:notEmpty name="xmxzList">
								<logic:iterate name="xmxzList" id="xmxzMap">
									<tr>
										<td>
											${xmxzMap.dm }
										</td>
										<td>
											${xmxzMap.mc }
										</td>
									</tr>
								</logic:iterate>
								<tr>
									<td colspan="2">
										����ά��������Ŀ���ʣ���ǰ��<a href="#" onclick="goPjpjDmwh();return false;"><font color="blue">����ά��</font></a>����ά��
									</td>
								</tr>
							</logic:notEmpty>
							<logic:empty name="xmxzList">
								<tr>
									<td colspan="2">
										��Ŀ����δά������ǰ��<a href="#" onclick="goPjpjDmwh();return false;"><font color="blue">����ά��</font></a>����ά��
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
			<!-- ��Ŀ������ϸ������ end -->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>