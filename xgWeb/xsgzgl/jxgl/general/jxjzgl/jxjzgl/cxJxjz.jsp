<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzgl.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			loadJxjzTjb();
		}

		//���ؾ�ѵ����ͳ�Ʊ�
		function loadJxjzTjb(){
			var url="jxjzgl_cxJxjz_ajax.do?method=cxJxjzTjbAjax";
			var parameter={};
			jQuery("#div_rs").load(url,parameter,function(data){
				setTimeout("setDivHeight()","200");
			});
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxJxjzTjbAjax";
			var ie = "ie";

			var jzid=jQuery("#searchJzid").val();
			
			var otherValue = [ie,jzid];
			searchRsByAjax(url,otherValue);
		}


		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//���������Ƽ���
		function dcJzTjb(){
			var url = "jxjzgl_cxJxjz_ajax.do?method=dcJzTjb";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		//��ѯ��ѵ��������
		function cxJzjzmdByJzid(jzid){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&jzid="+jzid;
			window.location.href=url;
		}

		//��ѯ��ѵ��������
		function cxJzjzmdByJzidAndBjdm(jzid,bjdm){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&jzid="+jzid+"&bjdm="+bjdm;
			window.location.href=url;
		}
		
		//��ѯ��ѵ��������
		function cxJxjzmdByJzidAndBjdm(jzid,bjdm,xb){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&jzid="+jzid+"&bjdm="+bjdm+"&xb="+xb;
			window.location.href=url;
		}

		//��ѯ��ѵ��������
		function cxJzjzmdPage(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			//url=url+"&jzid="+jzid;
			window.location.href=url;
		}

		//��ѯ��ѵ�������� �ѱ���
		function cxJzjzmdPageYbz(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&sjjz=1";
			window.location.href=url;
		}

		//��ѯ��ѵ��������  δ����
		function cxJzjzmdPageWbz(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&sjjz=0";
			window.location.href=url;
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
				1.���ͳ�Ʊ��е������鿴������ϸ��Ϣ
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/jxjzgl_cxJxjz" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="cxJzwh();return false;" class="btn_zj">
								����ά��
							</a>
						</li>
						<li>
							<a href="#" onclick="cxJzmd();return false;" class="btn_ck">
								������ѯ
							</a>
						</li>
						<li>
							<a href="#" onclick="dcJzTjb();return false;" class="btn_dc">
								����ͳ���
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
			</div>
			
			<!-- �������� -->	
			<div style="display: none;">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �������� end-->
			
			<!--����start-->
			    <h3 class="datetitle_01">
			    <span>��ѵ���ƣ�${jxxxwhModel.jxmc} &nbsp;��ѵ������ <a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPage()">${rsTj.cxrs }</a>��&nbsp;�ѱ���������<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageYbz()">${rsTj.ybzrs }</a>��&nbsp;��δ����������<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageWbz()">${rsTj.wbzrs }</a>��</span>
			    </h3>
			<!--����end-->
			
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
				<DIV style="OVERFLOW-X: auto; OVERFLOW-Y: hidden; WIDTH: 100%; HEIGHT: 363px"
					id="div_rs">
					
				</DIV>


				<!--��ҳ��ʾ-->
				<div style="clear:both;" style="display: none; ">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
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