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
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_xssq_ajax.do?method=searchXssqXmxx";
			var ie = "ie";
			var xmlx = jQuery("#xmlx").val();//��Ŀ����
			if(xmlx == ""){
				xmlx = " ";
			}
			var xmxz = jQuery("#xmxz").val();//��Ŀ����
			if(xmxz == ""){
				xmxz = " ";
			}
			var xmmc = escape(jQuery("#xmmc").val());//��Ŀ����
			if(xmmc == ""){
				xmmc = " ";
			}
			
			var otherValue = [ie,xmlx,xmxz,xmmc];
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//��ʾѧ������
		function showWdpjXssq(xmdm,opera){
			
			if(checkPjtj(xmdm)){
				
				var url = "general_pjpy.do?method=wdpjXssqDetail&xmdm="+xmdm;
				url+="&opera="+opera;
				showTopWin(url,"800","600");
			}
		
		}
		
		
		function checkPjtj(xmdm){
			//�ж�ѧ��ѧ��
			var xh=${userName};
			
			var parameter={}
			
			parameter["array_xh"]=xh;
			
			parameter["str_xmdm"]=xmdm;
			
			//����URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_pjtj_ajax.do?method=checkPjtj";
			
			var flag=false;
			
			//------------�����ж� begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!=""){
						alertInfo(result);
						flag=false;
					}else{
						flag=true;
					}
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  flag;
		}
		
		jQuery(function(){
			onShow();
		})
		
		</script>
	</head>
	<body >

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

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1.������չʾ����������ѧ������������Ϣ�� </span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								�����ҵ����� </a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->

				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									��Ŀ����
								</th>
								<td>
									<select id="xmlx" style="width:150px">
										<option value=""></option>
										<option value="01">
											��ѧ��
										</option>
										<option value="02">
											�����ƺ�
										</option>
									</select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<select id="xmxz" style="width:150px">
										<option value=""></option>
										<logic:iterate name="xmxzList" id="xmxz">
											<option value="${xmxz.dm }">
												${xmxz.mc }
											</option>
										</logic:iterate>
									</select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<input type="text" id="xmmc" style="width:150px" />
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>

				<div style="display:none">
					<!-- �������� -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
					<!-- �������� end-->
				</div>
			</div>
			<h3 class="datetitle_01">
				<span> ��ѯ���&nbsp;&nbsp; </span>
			</h3>
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
				<div id="div_rs"
					style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>

				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
