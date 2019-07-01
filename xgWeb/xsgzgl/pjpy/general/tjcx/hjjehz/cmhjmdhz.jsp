<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_tjcx_hjjehz_ajax.do?method=createCMHJMDHZHTML";
			var ie = "ie";
			
			var otherValue = [ie];
		
			searchRsByAjax(url,otherValue);
			
			jQuery.ajaxSetup({async:true});
		}
		
		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;

			var xn_num =  jQuery("a[name=a_name_xn]").length;
			
			if(xn_num != "1"){
				alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
				flag = false;
			}
			
<%--			else if(xmmc_num != "1"){--%>
<%--				alertError("������Ŀ����Ϊ�գ���ֻ��ѡ��һ����");--%>
<%--				flag = false;--%>
<%--			}--%>

			return flag;
		}
		
		//����ΪExcel
		function expToExcel(){
		
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				
				var url = "general_tjcx_hjjehz_ajax.do?method=expCmhjmdhz";
					url+= "&str_xn="+xn;
				
					
					
				var njdm = new Array();//�꼶
				var xydm = new Array();//ѧԺ
				var zydm = new Array();//רҵ
				var bjdm = new Array();//�༶
				var xmmcArr=new Array();//��Ŀ����
				
				// ��Ŀ��
				var n=0;
				jQuery("a[name=a_name_pjlsxm]").each(function(){
					var id = jQuery(this).attr("id");
					var xmmc = id.replace("a_id_","");
					if(xmmc !=null && xmmc!=""){
						xmmcArr[n] = xmmc;
						n++;
					}
				});
				
				if(xmmcArr != null && xmmcArr.length>0){
					url+= "&array_xmmcArr="+xmmcArr.join("!!array!!");
				}
					
				
				//�꼶
			    n=0;
				
				jQuery("a[name=a_name_nj]").each(function(){
					var id = jQuery(this).attr("id");
					var nj = id.replace("a_id_","");
					if(nj !=null && nj!=""){
						njdm[n] = nj;
						n++;
					}
				});
				
				if(njdm != null && njdm.length>0){
					url+= "&array_nj="+njdm.join("!!array!!");
				}
					
				//ѧԺ		
				n=0;
				
				jQuery("a[name=a_name_xy]").each(function(){
					var id = jQuery(this).attr("id");
					var xy = id.replace("a_id_","");
					if(xy !=null && xy!=""){
						xydm[n] = xy;
						n++;
					}
				});
				
				if(xydm != null && xydm.length>0){
					url+= "&array_xydm="+xydm.join("!!array!!");
				}
					
				//רҵ		
				n=0;

				jQuery("a[name=a_name_zy]").each(function(){
					var id = jQuery(this).attr("id");
					var zy = id.replace("a_id_","");
					if(zy !=null && zy!=""){
						zydm[n] = zy;
						n++;
					}
				});
		
				if(zydm != null && zydm.length>0){
					url+= "&array_zydm="+zydm.join("!!array!!");
				}
				
				//�༶		
				n=0;
				
				jQuery("a[name=a_name_bj]").each(function(){
					var id = jQuery(this).attr("id");
					var bj = id.replace("a_id_","");
					if(bj !=null && bj!=""){
						bjdm[n] = bj;
						n++;
					}
				});
				
				if(bjdm != null && bjdm.length>0){
					url+= "&array_bjdm="+bjdm.join("!!array!!");
				}
			
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
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
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="expToExcel();return false;" class="btn_dc">
								����
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
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>