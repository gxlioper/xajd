<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/treeFrame.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		
		function ensure(){
			var zcblArr=document.getElementsByName("zcblArr");
			var zcxmmcArr=document.getElementsByName("zcxmmcArr");
			var zcsjdmArr=document.getElementsByName("zcsjdmArr");
			
			for(i=0;i<zcxmmcArr.length;i++){
				zcxmmcArr[i].style.display="none";
				if($("span_xmmc_"+i)){
					var xmm=$("text_xmmc_"+i).value;
					$("span_xmmc_"+i).innerHTML=xmm;
					
				}
			}
			
			for(i=0;i<zcblArr.length;i++){
				$("text_bl_"+i).style.display="none";
				if($("span_bl_"+i)  &&  $("hid_sjdm_"+i).value!="" &&  $("hid_sjdm_"+i).value!=null){
					
					var bl=$("text_bl_"+i).value;
					if(bl==""){
						bl="δ����";
					}
					$("span_bl_"+i).innerHTML=bl;
					
				}
			}
			
			$("btn_bj").style.display="";
			$("btn_qd").style.display="none";
		}
		
		
		function saveZhcp(){
			//������Ŀ����������
			var zcxmmcArr=document.getElementsByName("zcxmmcArr");
			var zcxmdmArr=document.getElementsByName("zcxmdmArr");
			var xmdm=document.getElementsByName("xmdm");
			var xmmc=document.getElementsByName("xmmc");
			
			//�������
			var zcdmblArr=document.getElementsByName("zcdmblArr");
			var zcbldmArr=document.getElementsByName("zcbldmArr");
			var zcblArr=document.getElementsByName("zcblArr");
			var xmdmArr=document.getElementsByName("xmdmArr");
			var bldmArr=document.getElementsByName("bldmArr");
			var blArr=document.getElementsByName("blArr");
			
			for(i=0;i<zcxmmcArr.length;i++){
				xmdm[i].value=zcxmdmArr[i].value;
				xmmc[i].value=zcxmmcArr[i].value;
			}
			
			for(i=0;i<zcdmblArr.length;i++){
				xmdmArr[i].value=zcdmblArr[i].value;
				bldmArr[i].value=zcbldmArr[i].value;
				blArr[i].value=zcblArr[i].value;
			}
			refreshForm("/xgxt/zhcpJbsz.do?method=zcjbsz&doType=save");
		}
		
		function editor(){
			var zcblArr=document.getElementsByName("zcblArr");
			var zcxmmcArr=document.getElementsByName("zcxmmcArr");
			
			for(i=0;i<zcblArr.length;i++){
				zcblArr[i].style.display="";
				if($("span_bl_"+i) ){
					$("span_bl_"+i).innerHTML="";
				}
			}
			
			for(i=0;i<zcxmmcArr.length;i++){
				zcxmmcArr[i].style.display="";
				if($("span_xmmc_"+i)){
					$("span_xmmc_"+i).innerHTML="";
				}
			}
			
			
			$("btn_bj").style.display="none";
			$("btn_bc").style.display="";
		}
		
		function init(){
			confirmInfo('�۲��ʼ�����������ʼ���۲����Ϣ,�ò����������õ�ǰ�۲�!',function(t){
				if (t=='ok'){
					showLoadPage();
					refreshForm("/xgxt/zhcpJbsz.do?method=zcjbsz&doType=init");
				}
			})
		}
		
		function copyXm(){
			confirmInfo('�۲���Ŀ���Ʋ�����������һ�۲����ڵ���Ŀ��Ϣ,�Ƿ����?',function(t){
				if (t=='ok'){
					showLoadPage();
					refreshForm("/xgxt/zhcpJbsz.do?method=zcjbsz&doType=copyXm");
				}
			})
		}
		
		//��ʾ����ҳ��
		function showLoadPage(){
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
		}
		
		function removeZcxm(){
			
			confirmInfo('��ȷ��Ҫ�����ǰ���ڵ��۲���Ŀ��?',function(t){
				if (t=='ok'){
					refreshForm("zhcpJbsz.do?method=removePjxm");
				}
			})
		
		}
		
		</script>
	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��������-�����ۺ�����-�۲��������
				</a>
			</p>
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
		</div>
	
		<!-- ��ʾ��Ϣ-->
		<div class="prompt">
			<h3>
				<span>�۲����ڣ�</span>
			</h3>
			<p>
				����ѧ��(${pjxn })&nbsp;&nbsp;
				����ѧ��(${pjxqmc })&nbsp;&nbsp;
				�������(${pjnd })&nbsp;&nbsp;
				�۲�����(
					<logic:equal name="zczq" value="xn">
						ѧ��
					</logic:equal>
					<logic:equal name="zczq" value="xq">
						ѧ��
					</logic:equal>
				)&nbsp;&nbsp;
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->	
	
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>������������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('zhcp_sjcl_sjdr.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function35.png" />
							<p>�۲��ά��</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('zhcp_zczf_zccx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function30.png" />
							<p>�۲�ֲ�ѯ(����)</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		
		
		
		<html:form action="/zhcpJbsz">
			<!-- �۲���Ŀ�б� -->
			<logic:iterate name="xmList" id="zcxm">
				<input type="hidden" name="xmdm" value="${zcxm.xmdm}" />
				<input type="hidden" name="xmmc" value="${zcxm.xmmc}" />
				<input type="hidden" name="xmjb" value="${zcxm.xmjb}" />
				<input type="hidden" name="sjdm" value="${zcxm.sjdm}" />
			</logic:iterate>

			<logic:iterate name="zcxmxxList" id="zcxmxx">
				<input type="hidden" name="xmdmArr" value="${zcxmxx.xmdm}" />
				<input type="hidden" name="xmsjdmArr" value="${zcxmxx.sjdm}" />
				<input type="hidden" name="bldmArr" value="${zcxmxx.bldm}" />
				<input type="hidden" name="blmcArr" value="${zcxmxx.blmc}" />
				<input type="hidden" name="blArr" value="${zcxmxx.bl}" />
			</logic:iterate>

			<logic:iterate name="sjdmList" id="sjdm">

				<input type="hidden" name="sjdmArr" value="${sjdm.sjdm}" />

			</logic:iterate>
			
			<html:hidden property="upXn" />
			<html:hidden property="upXq" />
			<html:hidden property="upXqMc" />
			<html:hidden property="upNd" />
			<div id='cxjg'>
				<logic:notEmpty name="xmList">
				<div id="treeFr">
					<table >
						<tr>
							<td >
								<%@ include file="/pjpy/comm/zhcp/jbsz/treeFrame.jsp"%>

							</td>
						</tr>
					</table>
				</div>
					<br />
					<br /><br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
				</logic:notEmpty>
				<logic:empty name="xmList">
					<table align="center" width="100%">
						<tr>
							<td>
								<div align="center">
									<div class="page_prompt">
										<div class="page_promptcon">
											<h3>
												��ܰ��ʾ��
											</h3>
											<p>
												��ǰ�۲�����δ�����۲���Ŀ,����и�����Ŀ�������������Ա��ϵ��
											</p>
										</div>
										<p>
											&nbsp;
										</p>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
					<br />
				</logic:empty>

				<table class="formlist" style="margin-bottom:4px">
					<thead>
						<tr>
							<td>

								<div class="btn">
								<logic:empty name="xmList">
									<button type="button" id="btn_copy" onclick='copyXm()'>
										��Ŀ����
									</button>
								</logic:empty>
								<logic:notEmpty name="xmList">
								<button type="button" id="btn_init" onclick='init()'>
									�۲��ʼ��
								</button>
								</logic:notEmpty>
								<logic:notEmpty name="xmList">
									<button type="button" id="btn_bj" onclick='editor()'>
										�� ��
									</button>
									<button type="button" id="btn_bc" style="display:none" onclick='saveZhcp()'>
										�� ��
									</button>
								</logic:notEmpty>
									
									<button type="button" id="btn_bc"  onclick='removeZcxm()'>
										�����Ŀ
									</button>
								
								</div>
							</td>
						</tr>
					<thead>
				</table>
			</div>
		</html:form>
		
		<!-- ��Ŀ���� ������� -->
		<logic:equal name="copyResult" value="true">
			<logic:present name="message">
				<script>
					alertInfo("${message}!");	
				</script>
			</logic:present>
		</logic:equal>
		<logic:equal name="copyResult" value="false">
			<script>
				alertInfo("��һ���ڵ��۲���Ŀ�����ڣ������޷������۲���Ŀ!");	
			</script>
		</logic:equal>
		<!-- ��Ŀ���� �������end -->
		
		<!-- �۲����ݳ�ʼ�� ������� -->
		<logic:present name="initResult">
			<logic:present name="message">
				<script>
					alertInfo("�۲�����${message}!");	
				</script>
			</logic:present>
		</logic:present>
		<!-- �۲����ݳ�ʼ�� �������end -->
		
		<!-- �۲�����޸Ĳ������ -->
		<logic:present name="updateResult">
			<logic:present name="message">
				<script>
					alertInfo("${message}!");	
				</script>
			</logic:present>
		</logic:present>
		<!-- �۲�����޸Ĳ������end -->
		
		<logic:present name="removeResult">
			<script defer>
				alertInfo('${removeResult}');
			</script>
		</logic:present>
	</body>
	
	
	<!-- �ȴ�ing -->
	<div id="divWaiting" style="display: none; z-index: 1100; left: 25%; right: 25%; position: absolute;
	     text-align: center; width: 50%; height: 50px;left: expression((this.offsetParent.clientWidth/2)-(this.clientWidth/2)+this.offsetParent.scrollLeft);
	     top: expression((this.offsetParent.clientHeight/2)-(this.clientHeight/2)+this.offsetParent.scrollTop);">
	   <img src="<%=stylePath%>images/ico_loading.gif"/>
	   <p id="p_tsxx"><B>���ڽ����۲���Ϣ��ʼ��,���Ժ򡣡���</B></p>
	</div>

	<div id="divDisable" style="display: none;width:expression(document.body.offsetWidth);height:expression(document.body.offsetHeight); z-index: 1000; position: absolute;left: 0px; top: 0px;filter:alpha(opacity=50); background-color:white"></div>
</html>
