<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/comm/commFunction.js"></script>
	    
	    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
		
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "szdw_tjcx_bmqk.do?method=statBmqk";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//����˼����Աͳ��
		function expBmqkStat(){
			var url="szdw_tjcx_bmqk.do?method=export";
			document.forms[0].action=url;
			document.forms[0].target="_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}


		function showBjqkInfo(url){
			setSearchTj();
			//ģ����ѯ
			var input_mhcx = "";
			if($("input_mhcx")){
				input_mhcx = $("input_mhcx").value;
			}
			
			var mhcx_lx = "";
			if($("mhcx_lx")){
				mhcx_lx = $("mhcx_lx").value;
			}

			//�����ѯ
			var searchLx = new Array();
			var searchTj = new Array();
			var searchTjz = new Array();
			
			var n=0;
			var m=3;
			
			searchLx[0]="xy";
			searchLx[1]="zy";
			searchLx[2]="bj";
			
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}

			var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
				
			for(var j=0;j<tj_num;j++){
				var obj = $("searchTjDiv").getElementsByTagName('input')[j];
				searchTj[n]=obj.name;
				searchTjz[n]=escape(obj.value);
				n++;
			}
			
			var input_mhcx=input_mhcx;
			var mhcx_lx=mhcx_lx;
			var searchTj=searchTj.join("!!@@!!");
			var searchTjz=searchTjz.join("!!@@!!");
			var searchLx=searchLx.join("!!@@!!");

			url+='&input_mhcx='+input_mhcx;
			url+='&mhcx_lx='+mhcx_lx;
			url+='&searchTj='+searchTj;
			url+='&searchTjz='+searchTjz;
			url+='&searchLx='+searchLx;
			
			var selectNj = jQuery("a[name=a_name_nj]");
			var njArr = [];

			for (var i = 0 ; i < selectNj.length ; i++){
				njArr.push(selectNj.eq(i).attr("id").split("_")[2]);
			}

			url += "&nj="+njArr.join("!!@!!");

			///showTopWin(url,700,550);
			showDialog('�鿴��ϸ���',700,480,url);
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
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" class="btn_dc" onclick="expBmqkStat();return false;">
									����
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
				<div style="clear:both; display:none">
					<input type="hidden" id="ajax_max_record" value=""/>
					<input type="hidden" id="ajax_max_page" value=""/>
					<input type="hidden" id="ajax_max_current" value=""/>
					<input type="hidden" id="ajax_page_size" value=""/>
					<input type="hidden" id="ajax_page_maxpage" value=""/>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>