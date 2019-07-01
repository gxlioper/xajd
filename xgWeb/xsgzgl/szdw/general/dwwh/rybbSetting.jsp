<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
		
			jQuery.ajaxSetup({async: false});
		
			//�����꼶Div
			createNjLvDiv();
			
			var index = jQuery("#hidden_nj").val();
				
			var id = "";
			
			if(index == ""){
				id = "a_nj_0";
			}else{
				id = "a_nj_"+index;
			}
					
			if($(id)){
				$(id).click();
			}
	
			jQuery.ajaxSetup({async: true});
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
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p><%--
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		--%></div>
		<!-- ���� end-->
		
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_lx" value="${lx }"/>
			<input type="hidden" id="hidden_zgh" value="${zgh }"/>
			<input type="hidden" id="hidden_nj" value="${nj }"/>
	
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="goDwwh();return false;">
								����
							</a>
						</li>
						<li>
							<a href="#" class="btn_ccg" onclick="saveFpbj();return false;">
								����
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
			</div>
				
			<!-- ��Ŀ������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��ʦ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th align="right" width="15%">
							ְ����
						</th>
						<td align="left" width="35%">
							${rs.zgh }
						</td>
						<th align="right" width="15%">
							����
						</th>
						<td align="left" width="35%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th align="right" width="">
							��������
						</th>
						<td align="left" width="">
							${rs.bmmc }	
						</td>
						<th align="right" width="">
							${lxmc }������
						</th>
						<td align="left" width="">
							<span id="span_dbs">${rs.num }</span>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>${lxmc }�����༶ѡ��ע��1����������б����꼶�����л� ��2�����ź���ʾ���������ʾ�˲��Ŵ���${rs.xm }��ʦ�������༶��</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<!-- ��֯�ܹ� DIV begin -->
							<div class="main_function">
							
								<!-- �꼶 begin -->
								<div class="function_list01" id="div_nj">
									<input type="checkbox" onclick="" checked="checked"/>
				  				</div>
				  				<!-- �꼶 end -->
				  				
				  				<!--  ���� begin -->
				  				<div class="function_list02" id="div_other" 
				  					style="overflow: scroll;overflow-x:hidden; height: 500px">
								  	
								</div>
				  				<!-- ���� end  -->
				  				
				  				 <div class="function_list03"></div>
							</div>
							<!-- ��֯�ܹ� DIV end -->
						</td>
					</tr>
				</tbody>
			</table>
			
			<div id="div_fpbj_new" style="display:none"></div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>