<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){
		
			var url = "xsxx_grxx.do?method=getJgcxList";

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var ie = "ie";
			
			var otherValue = [ie,stylePath];

			searchRsByAjax(url,otherValue);
		}
		
		//չʾ�����ϸҳ��
		function showJgDetail(){
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("�빴ѡ��ϣ���鿴�ļ�¼");
				return false;
			}else if(num == 1){
			
				var sqid = jQuery("input[name=checkVal]:checked")[0].value;
				
				var url = "xsxx_grxx.do?method=jgcxDetail";
					url+= "&sqid="+sqid;
					url+= "&stylePath="+stylePath;

				showTopWin(url,'800','620');
			}else{
				alertError("ֻ�ܹ�ѡһ����¼���в鿴����ȷ��");
				return false;
			}
		}
		</script>
	</head>
	<body onload="searchRs();" >
	
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
				<span id="div_help" style="display: none">
				1.����ڹ��������е�������ѡ��<font color="blue">�����</font>�Ļ��������˳������¼��δ��˺ͷ����ͨ�������м�¼��</br>
				2.���ĳ�θ�����Ϣ�޸�<font color="blue">�������</font>�Ļ�����ֱ�Ӹ���ѧ����Ϣ�⣬�޷��ڽ����ѯ�в�ѯ����
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/xsxx_grxx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showJgDetail();return false;" class="btn_yl">
								�鿴
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGrxxForm"></jsp:include>
					 <script type="text/javascript">
				     $('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->	
			
			<!-- ��λ��Ϣ������ -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��λ��Ϣ(��ӵ�ж����λ����ѡ�񱾴���˵ĸ�λ)</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									���ĸ�λ
								</th>
								<td>
									<p id="p_gwxx" style="height: 50px">
									
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shgw').value=$('hid_gwid').value;searchRs();closeWindown();">
											ȷ ��
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>