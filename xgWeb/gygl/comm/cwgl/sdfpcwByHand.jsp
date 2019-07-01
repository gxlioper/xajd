<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:directive.page import="xgxt.action.Base" />
		<jsp:directive.page import="java.util.HashMap" />
		<jsp:directive.page import="java.util.List" />
		<jsp:directive.page import="java.util.ArrayList" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglCwgl.js'></script>
		<script language="javascript" defer="defer">
		
		//��ʾ¥����������ϸ��Ϣ 
		
		function showTbody(objid,objTbody,lddm,cs){
			
			var obj=$(objid);
			if(obj.className=="up"){
				obj.className="down";
				obj.parentNode.parentNode.className="";
				document.getElementById(objTbody).style.display="";
				
			}else if(obj.className=="down"){
				obj.className="up";
				obj.parentNode.parentNode.className="cur-tr";
				document.getElementById(objTbody).style.display="none";
			}
		}
		
		//��ʾ¥����ϸ��Ϣ
		function showLcxx(lddm){
				var userStatus=$("userStatus").value;
				var userDep=$("userDep").value;
				var fpdx=$("fpdx").value;
				gyglCwgl.getKxssTjList(lddm,fpdx,userStatus,userDep,function(data){
					for(i=0;i<data.length;i++){
						var qss=data[i].qss;
						var wrzcw=data[i].wrzcw;
						var cs=data[i].cs;
						if(qss=="" || qss==null){
							qss="0";
						}
						
						if(wrzcw=="" || wrzcw==null){
							wrzcw="0";
						}
						$("lc_"+lddm+"_"+cs+"_qss").innerHTML="<em class='chamber'>����������������:"+qss+"</em>";
						$("lc_"+lddm+"_"+cs+"_wfps").innerHTML="<em class='chamber'>δ���䴲λ������:"+wrzcw+"</em>";
	
					}					 		 
				})
		}
		
		function showLoad(lddm,cs){
			var objid="a_"+lddm+"_"+cs;
			var objTbody="lc_"+lddm+"_"+cs;
			if(!$(lddm+"_"+cs+"_cs")){
				$("lc_"+lddm+"_"+cs+"_div").style.display="";
				
				setTimeout("showTbody('"+objid+"','"+objTbody+"','"+lddm+"','"+cs+"')","5000");
				setTimeout("showQsxx('"+lddm+"','"+cs+"')","5000");
				
				
			}else{
				
				showTbody(objid,objTbody,lddm,cs);
				showQsxx(lddm,cs);
			}
			
			
			
		}
		
		//��ʾ������ϸ��Ϣ
		function showQsxx(lddm,cs){
		
			var userStatus=$("userStatus").value;
			var userDep=$("userDep").value;
			var fpdx=$("fpdx").value;
		
			$("lc_"+lddm+"_"+cs+"_div").style.display="none";
			//�жϸö����Ƿ����,�������˵��
			//�ö����Ѿ�����,�Ͳ���Ҫ�ٴι���
			if(!$(lddm+"_"+cs+"_cs")){
				
				var qscwxxArr=new Array();
				
				dwr.engine.setAsync(false);
				
				var xsArr=new Array();
				//���ҷ����б�
				gyglCwgl.getQsxxList(lddm,fpdx,userDep,userStatus,cs,xsArr,function(data){
					var lddmbf="";
					var csbf="";
					var qshbf="";
					var n=0;
					
					for(i=0;i<data.length;i++){
						var xxHtml="";
						//¥������
						var lddm=data[i].lddm;
						//����
						var cs=data[i].cs;
						//���Һ�
						var qsh=data[i].qsh;
						//¥������
						var cws=data[i].cws;
						//����
						var fpbm=data[i].fpbm;
						//���Һ�
						var rzrs=data[i].rzrs;
						//���Һ�
						var fpdx=data[i].fpdx;
						//cwxxArr
						var cwxxArr=data[i].cwxxArr;
						for(j=0;j<cwxxArr.length;j++){
							
							//���䲿��
							var cwh=cwxxArr[j].cwh;
							//�������
							var cws=cwxxArr[j].cws;
							//��λ��
							var xh=cwxxArr[j].xh;
							//��λ��
							var zrs=cwxxArr[j].zrs;
							
							var xm=cwxxArr[j].xm;
							
							var xb=cwxxArr[j].xb;
							
							var kfhz=cwxxArr[j].kfhz;
							var xydm=cwxxArr[j].xydm;
							var xymc=cwxxArr[j].xymc;
							var zydm=cwxxArr[j].zydm;
							var zymc=cwxxArr[j].zymc;
							var bjdm=cwxxArr[j].bjdm;
							var bjmc=cwxxArr[j].bjmc;
							var nj=cwxxArr[j].nj;
							
							if(j==0){
								xxHtml+="�Ա�����:"+xb+"</br>���Ż�ס:"+kfhz+"<br/>";
								id="qsxx_"+lddm+"_"+cs+"_"+qsh;
								if($(id)){
									var bmdmHtml=fpbm;
									if(fpbm.length>5){
										bmdmHtml=fpbm.substring(0,15)+"...";
									}
									
									$(id).innerHTML=qsh+"("+bmdmHtml+")";
									$(id).title=fpbm;
								}
							}
							var cwid="cwid_"+lddm+"_"+cs+"_"+qsh+"_"+cwh;
							var xmcwid="cwid_"+lddm+"_"+cs+"_"+qsh+"_"+cwh+"_xmxs";
							xxHtml+="<span class='bed_num' id='"+xmcwid+"'>"+cwh+"�Ŵ�:"+xm+"</span>";
							
							//����̨��ȡ���ֶ�ƴ�ӳ��ַ���
							var qscwxxArr=lddm+"!!split!!"+cs+"!!split!!"+qsh+"!!split!!"+fpbm+"!!split!!"+fpdx;
							qscwxxArr+="!!split!!"+cwh+"!!split!!"+cws+"!!split!!"+xh+"!!split!!"+zrs;
							qscwxxArr+="!!split!!"+xm+"!!split!!"+xb+"!!split!!"+kfhz+"!!split!!"+xydm;
							qscwxxArr+="!!split!!"+xymc+"!!split!!"+zydm+"!!split!!"+zymc+"!!split!!"+bjdm;
							qscwxxArr+="!!split!!"+bjmc+"!!split!!"+nj;
							
							if("δ����"==fpbm ){
								xxHtml+="<span  id='"+cwid+"_cz'><a class='cancel_02' onclick=\"return false;\">��ס</a></span>";
							}else if("���λ"==xm){
								xxHtml+="<span  id='"+cwid+"_cz'><a class='cancel_02' onclick=\"return false;\">��ס</a></span>";
							}else if(xm=="δ����"){
								xxHtml+="<span  id='"+cwid+"_cz'><a class='intake'  href='#' onclick=\"xsrzcw('"+qscwxxArr+"','"+cwid+"','"+xmcwid+"');return false;\">��ס</a></span>";
							}else {
								xxHtml+="<span id='"+cwid+"_cz'><a class='cancel_02' onclick=\"return false;\">��ס</a></span>";
							}
							//ѧ��(����ס����Ϣ ����)
							xxHtml+="<input type='hidden' name='xhArr' id='"+cwid+"_xh' value='"+xh+"'/>";
							//��λ��(����ס����Ϣ)
							xxHtml+="<input type='hidden' name='cwhArr' id='"+cwid+"_cwh' value='"+cwh+"'/>";
							//��λ��(����ס����Ϣ)
							xxHtml+="<input type='hidden' name='lddmArr' id='"+cwid+"_lddm' value='"+lddm+"'/>";
							//��λ��(����ס����Ϣ)
							xxHtml+="<input type='hidden' name='csArr' id='"+lddm+"_"+cs+"_cs' value='"+cs+"'/>";
							//��λ��(����ס����Ϣ)
							xxHtml+="<input type='hidden' name='qshArr' id='"+cwid+"_qsh' value='"+qsh+"'/>";
							//����(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwxmArr' id='"+cwid+"_xm' value='"+xm+"'/>";
							//�Ա�(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwxbArr' id='"+cwid+"_xb' value='"+xb+"'/>";
							//���Ŵ���(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwxydmArr' id='"+cwid+"_xydm' value='"+xydm+"'/>";
							//���Ŵ���(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwxymcArr' id='"+cwid+"_xymc' value='"+xymc+"'/>";
							//���Ŵ���(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwzydmArr' id='"+cwid+"_zydm' value='"+zydm+"'/>";
							//���Ŵ���(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwzymcArr' id='"+cwid+"_zymc' value='"+zymc+"'/>";
							//�༶����(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwbjdmArr' id='"+cwid+"_bjdm' value='"+bjdm+"'/>";
							//�༶����(ȡ������ʱ��ʾ��Ҫ)
							xxHtml+="<input type='hidden' name='cwbjmcArr' id='"+cwid+"_bjmc' value='"+bjmc+"'/>";
							//�꼶
							xxHtml+="<input type='hidden' name='cwnjArr' id='"+cwid+"_nj' value='"+nj+"'/></br>";
					
						}	
						id="cwxx_"+lddm+"_"+cs+"_"+qsh;
						if($(id)){
							$(id).innerHTML=xxHtml;
						}
					}					 		 
				})
				
				dwr.engine.setAsync(true);
			}
			
		}

		function showTable(obj,objTable){
			
			if(obj.className=="table_up"){
				obj.className="table_down";
				obj.parentNode.parentNode.className="cur-tr";
				document.getElementById(objTable).style.display="none";
			}else{
				obj.className="table_up";
				obj.parentNode.parentNode.className="";
				document.getElementById(objTable).style.display="";
			}
		}
		
		function clickTr(){
			var xhArr=document.getElementsByName("xsxhArr");
			for(i=0;i<xhArr.length;i++){
				if($("tr_"+i).style.display!="none"){
					$("tr_"+i).click();
					break;
				}
			}
		}
		
		//ѧ����ס��λ
		//������Ϣ����ϸ��Ϣ��
		function xsrzcw(qscwxxArr,id,xmid){
			if($("tr_0")){
				var xh=curr_row.getElementsByTagName('input')[0].value;
				var xm=curr_row.getElementsByTagName('input')[1].value;
				var xb=curr_row.getElementsByTagName('input')[2].value;
				var xydm=curr_row.getElementsByTagName('input')[3].value;
				var xymc=curr_row.getElementsByTagName('input')[4].value;
				var bjdm=curr_row.getElementsByTagName('input')[5].value;
				var bjmc=curr_row.getElementsByTagName('input')[6].value;
				var zydm=curr_row.getElementsByTagName('input')[7].value;
				var zymc=curr_row.getElementsByTagName('input')[8].value;
				var nj=curr_row.getElementsByTagName('input')[9].value;
				
				if(xh==""){
					alert("����ѡ����Ҫ��ס��ѧ��!");
					return false;
				}
				
				//��Ҫɾ������
				
				var qscwArr=qscwxxArr.split("!!split!!");
				
				var qsfpbm=qscwArr[3];
				//���ҷ������
				var fpdx=qscwArr[4];
				//�����Ա�
				var qsxb=qscwArr[10];
				//�ɷ��ס
				var kfhz=qscwArr[11];
				//��λ��
				var cwh=qscwArr[5];
				var blog=false;
				
				if(xb==qsxb){
					if(kfhz=="����"){
						$(id+"_xh").value=xh;
						$(id+"_xmxs").innerHTML=cwh+"�Ŵ�:"+xm;
						blog=true;
					}else {
						if(fpdx=="xy" && xymc==qsfpbm){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"�Ŵ�:"+xm;
							blog=true;
						}else if(fpdx=="njxy"  && qsfpbm==nj+xymc){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"�Ŵ�:"+xm;
							blog=true;
						}else if(fpdx=="njzy"  && qsfpbm==nj+zymc){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"�Ŵ�:"+xm;
							blog=true;
						}else if(fpdx=="bj"  && qsfpbm==bjmc){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"�Ŵ�:"+xm;
							blog=true;
						}else {
							alert("�����Ҳ��ɻ�ס��ֻ����ס"+qsfpbm+"��ѧ��");
							return false;
						}
					}
				}else{
					alert("������Ϊ"+qsxb+"���ң�ֻ����ס"+qsxb+"��");
					return false;
				}
				
				$(id+"_xh").value=xh;
				$(id+"_xm").value=xm;
				$(id+"_xb").value=xb;
				$(id+"_xydm").value=xydm;
				$(id+"_xymc").value=xymc;
				$(id+"_bjdm").value=bjdm;
				$(id+"_bjmc").value=bjmc;
				$(id+"_zydm").value=zydm;
				$(id+"_zymc").value=zymc;
				$(id+"_nj").value=nj;
				
				
				var addhtml="<a class='cancel_01'  href='#' onclick=\"xsqxcw('"+qscwxxArr+"','"+id+"','"+xmid+"');return false;\">ȡ��</a>";
				$(id+"_cz").innerHTML=addhtml;
				//�ж��Ƿ�������ס����
				if(blog){
					$("fpcwxh").value=xh;
					loadFpcwrz()
					if($("tr_0")){
						$("tr_0").click();
					}
	<%--				replaceTable(qscwxxArr,xh,id);--%>
				}
				return false;
			}else {
				alert("����Ҫ���䴲λ��ѧ��!");
				return false;
			}
		}
		
		//ѧ��ȡ����λ
		function xsqxcw(qscwxxArr,id,xmid){
			//ѧ��ס����ϸ��Ϣ
			var qscwArr=qscwxxArr.split("!!split!!");
			
			$("fpcwxh").value=$(id+"_xh").value;
			loadFpcwqx();
			if($("tr_0")){
				$("tr_0").click();
			}
			var addhtml="<a class='intake' href='#' onclick=\"xsrzcw('"+qscwxxArr+"','"+id+"','"+xmid+"');return false;\">��ס</a>";
			$(id+"_cz").innerHTML=addhtml;

			$(id+"_xh").value="";
			$(id+"_xm").value="";
			$(id+"_xb").value="";
			$(id+"_xydm").value="";
			$(id+"_xymc").value="";
			$(id+"_bjdm").value="";
			$(id+"_bjmc").value="";
			$(id+"_zydm").value="";
			$(id+"_zymc").value="";
			$(id+"_nj").value="";
			$(id+"_xmxs").innerHTML=qscwArr[5]+"�Ŵ�:δ����";
				
		}
			
			
		
		function loadAttach(len){
			($("span_xh_"+len).parentElement).parentElement.attachEvent('onclick', function(){rowOnClick($("tr_"+len))});
			
			($("span_xh_"+len).parentElement).parentElement.style.cursor="hand";
		}
		
		//�ع���߲˵���ѧ����Ϣ
		function replaceTable(qsxx,xxxx,xh,id){
			//��ȡѡ�����е�����������
			//ѧ��
			var xha=curr_row.getElementsByTagName('input')[0].value;
			//����
			var xma=curr_row.getElementsByTagName('input')[1].value;
			//�Ա�
			var xba=curr_row.getElementsByTagName('input')[2].value;
			//ѧԺ����
			var xydm=curr_row.getElementsByTagName('input')[3].value;
			//ѧԺ����
			var xymc=curr_row.getElementsByTagName('input')[4].value;
			//�༶����
			var bjdm=curr_row.getElementsByTagName('input')[5].value;
			//�༶����
			var bjmc=curr_row.getElementsByTagName('input')[6].value;
			//רҵ����
			var zydm=curr_row.getElementsByTagName('input')[7].value;
			//רҵ����
			var zymc=curr_row.getElementsByTagName('input')[8].value;
			//�꼶
			var nj=curr_row.getElementsByTagName('input')[9].value;
			
			//��ȡ��߲˵���ѧ����ϸ��Ϣ�б�
			var xhArr=document.getElementsByName("xsxhArr");
			var xsxmArr=document.getElementsByName("xsxmArr");
			var xsxbArr=document.getElementsByName("xsxbArr");
			var xsxydmArr=document.getElementsByName("xsxydmArr");
			var xsxymcArr=document.getElementsByName("xsxymcArr");
			var xsbjdmArr=document.getElementsByName("xsbjdmArr");
			var xsbjmcArr=document.getElementsByName("xsbjmcArr");
			var xszydmArr=document.getElementsByName("xszydmArr");
			var xszymcArr=document.getElementsByName("xszymcArr");
			var xsnjArr=document.getElementsByName("xsnjArr");
			
			
			var blog= false;
			
			for(i=0;i<xhArr.length;i++){
				//��ͷ����ѭ���ҵ�ѡ�е���
				if(xha==xhArr[i].value){
					//�ҵ�ѡ���к�ʼ����ֵ����
					blog=true;
				}
				
				//�ж��Ƿ�ʼѭ��,(i+1)�Ƿ�С�ڶ���ĳ���
				if(blog && (i+1)<xhArr.length){
					$("xh_"+i).value=$("xh_"+(i+1)).value;
					$("xm_"+i).value=$("xm_"+(i+1)).value;
					$("xb_"+i).value=$("xb_"+(i+1)).value;
					$("xydm_"+i).value=$("xydm_"+(i+1)).value;
					$("xymc_"+i).value=$("xymc_"+(i+1)).value;
					$("zydm_"+i).value=$("zydm_"+(i+1)).value;
					$("zymc_"+i).value=$("zymc_"+(i+1)).value;
					$("bjdm_"+i).value=$("bjdm_"+(i+1)).value;
					$("bjmc_"+i).value=$("bjmc_"+(i+1)).value;
					$("nj_"+i).value=$("nj_"+(i+1)).value;
					$("rows_"+i).value=$("rows_"+(i+1)).value;
					$("span_xh_"+i).innerHTML=$("span_xh_"+(i+1)).innerHTML;
					$("span_xm_"+i).innerHTML=$("span_xm_"+(i+1)).innerHTML;
					
				}
				
				//��i��ѭ�������һ��Ԫ��ʱ�����������
				if(i==xhArr.length-1){
					xhArr[i].value="";
					xsxmArr[i].value="";
					xsxbArr[i].value="";
					xsxydmArr[i].value="";
					xsxymcArr[i].value="";
					xsbjdmArr[i].value="";
					xsbjmcArr[i].value="";
					xszydmArr[i].value="";
					xszymcArr[i].value="";
					xsnjArr[i].value="";
					rowsArr[i].value="";
					$("span_xh_"+i).innerHTML="&nbsp;";
					$("span_xm_"+i).innerHTML="&nbsp;";
				}
			}
			trDelAll();
			//��ѯ����
			searchName($("searchText").value);
		}
		
		function setValue(id,value){
			$(id).value=value;
		}
		
		function save(){
			saveUpdate('/xgxt/gyglCwgl.do?method=cwxxtj&doType=save','');
		}
		
		function searchName(text){
		
			var xmArr=document.getElementsByName("xsxmArr");
			for (var i = 0 ; i < xmArr.length ; i++){
					if (xmArr[i].value.indexOf(text)>=0){
						$("tr_"+i).style.display="";
					}else if($("searchText").value!="����������ѯ"){
						if($("span_xh_"+i).innerHTML!="&nbsp;"){
							
							$("tr_"+i).style.display="none";
						}
					}
			}
			clickTr();
			
		}
		
		function checkNr(){
			if($("searchText").value=="����������ѯ"){
				$("searchText").value="";
			}
		}
		
		//table id,���ӵ��ǵڼ���
		 function trAdd(the_tab,len){
		
		    var cellfu = getCellfu(len);
		    DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		    
		}

		function getCellfu(len){
			var cellfu = new Array();
			cellfu =[
				function(data){
					var xxHtml="";
			    	xxHtml="<span id='span_xh_"+len+"'>&nbsp;</span>";
					return xxHtml;
				},
			    function(data){
			    	var xxHtml="";
			    	xxHtml+="<span id='span_xm_"+len+"'>&nbsp;</span>";
	    			xxHtml+="<input type='hidden' id='xh_"+len+"' name='xsxhArr' value=''/>";
					xxHtml+="<input type='hidden' id='xm_"+len+"' name='xsxmArr' value=''/>";
					xxHtml+="<input type='hidden' id='xb_"+len+"' name='xsxbArr' value=''/>";
					xxHtml+="<input type='hidden' id='xydm_"+len+"' name='xsxydmArr' value=''/>";
					xxHtml+="<input type='hidden' id='xymc_"+len+"' name='xsxymcArr' value=''/>";
					xxHtml+="<input type='hidden' id='bjdm_"+len+"' name='xsbjdmArr' value=''/>";
					xxHtml+="<input type='hidden' id='bjmc_"+len+"' name='xsbjmcArr' value=''/>";
					xxHtml+="<input type='hidden' id='zydm_"+len+"' name='xszydmArr' value=''/>";
					xxHtml+="<input type='hidden' id='zymc_"+len+"' name='xszymcArr' value=''/>";
					xxHtml+="<input type='hidden' id='nj_"+len+"' name='xsnjArr' value=''/>";
					xxHtml+="<input type='hidden' id='rows_"+len+"' name='rowsArr' value=''/>";
					return xxHtml;
			    }
			  ];
			 
			  return cellfu;
		}
		
		
		function trDelAll(){
		    var tabobj = document.getElementById("xsmdtbody");
		    var length = tabobj.rows.length;
		 	if(length>20){
		   	 	tabobj.deleteRow(tabobj.rows.length-1);
		    }  
		   
		}	

		
		//����
		function fhcwxxtj(){
			if (confirm("��Ҫ����ǰһҳ�棬��ȷ���Ѿ������˷�����Ϣ��")) {
				refreshForm("gygl_cwgl_cwfp.do?go=go&doType=query");
			}
		}
		
		function loadFpcwrz(){
			jQuery("#fpcwxsxxMain").load("/xgxt/gygl/comm/cwgl/fpcwxsrz.jsp");
		}
		
		function loadFpcwqx(){
			jQuery("#fpcwxsxxMain").load("/xgxt/gygl/comm/cwgl/fpcwxsqx.jsp");
		}
	
		</script>
	</head>
	<body onload="clickTr();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��λ���� - ��λ����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglCwgl">
			<%@ include file="/comm/loading.jsp"%>
			<input type="hidden" name="numDel" id="numDel" value=""/>
			<input type="hidden" name="userStatus" id="userStatus" value="${userStatus}"/>
			<!-- ������ --> 
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="fpdx" value="${fpdx }" />
			<input type="hidden" name="fpcwxh" id="fpcwxh" value=""/>
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<div class="leftframe04" style="height:500px; overflow:auto; overflow-x:hidden ">
					<div class="formbox">
						<!--����start-->
				    <h3 class="datetitle_01">
				    <span>ѧ������</span>
				    </h3>
						<!--����start-->
						<!--��ѯend-->
					<div class="query_name">
				    	<input name="searchText" id="searchText" type="text" value="����������ѯ" onfocus="checkNr()" onkeyup="searchName(this.value)" /><button></button>
				    </div>
				    <div id="fpcwxsxxMain">
						<table summary="" class="datelist nowrap" align="left" width="90%" id="xsmd">
									<!-- ��ͷ -->
									<thead>
										<tr align="center" style="cursor:hand">
											<td id="xh"  nowrap>
												ѧ��
											</td>
											<td id="xm"  nowrap>
												����
											</td>
										</tr>
									</thead>
									<!-- ��ͷ end-->
									<!--���� -->
									<tbody id="xsmdtbody">
									<logic:notEmpty name="fpcwxsList">
										<logic:iterate name="fpcwxsList" id="s" indexId="index">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${index}" title='${s.xb}'>
												<!-- ��ʾ��Ϣ -->
												<td>
												<span id="span_xh_${index}">
													${s.xh}
												</span>
												</td>
												<td>
													<span id="span_xm_${index}">
														${s.xm}
													</span>
													<input type="hidden" id="xh_${index}" name="xsxhArr" value="${s.xh}"/>
													<input type="hidden" id="xm_${index}" name="xsxmArr" value="${s.xm}"/>
													<input type="hidden" id="xb_${index}" name="xsxbArr" value="${s.xb}"/>
													<input type="hidden" id="xydm_${index}" name="xsxydmArr" value="${s.xydm}"/>
													<input type="hidden" id="xymc_${index}" name="xsxymcArr" value="${s.xymc}"/>
													<input type="hidden" id="bjdm_${index}" name="xsbjdmArr" value="${s.bjdm}"/>
													<input type="hidden" id="bjmc_${index}" name="xsbjmcArr" value="${s.bjmc}"/>
													<input type="hidden" id="zydm_${index}" name="xszydmArr" value="${s.zydm}"/>
													<input type="hidden" id="zymc_${index}" name="xszymcArr" value="${s.zymc}"/>
													<input type="hidden" id="nj_${index}" name="xsnjArr" value="${s.nj}"/>
													<input type="hidden" id="rows_${index}" name="rowsArr" value="${index}"/>
													
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<!--���� end-->
									<!-- ������ -->
									<%
											ArrayList list = ((ArrayList) request.getAttribute("fpcwxsList"));
											int rsNum = 0;
											if (list != null) {
												rsNum = list.size();
											}
											int pageSize = 20;
											for (int i = 0; i < (pageSize - rsNum); i++) {
									%>
									<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_<%=rsNum+i%>">
										<td>
											<span id="span_xh_<%=rsNum+i%>">&nbsp;</span>
										</td>
										<td>
											<span id="span_xm_<%=rsNum+i%>">&nbsp;</span>
											<input type="hidden" id="xh_<%=rsNum+i%>" name="xsxhArr" value=""/>
											<input type="hidden" id="xm_<%=rsNum+i%>" name="xsxmArr" value=""/>
											<input type="hidden" id="xb_<%=rsNum+i%>" name="xsxbArr" value=""/>
											<input type="hidden" id="xydm_<%=rsNum+i%>" name="xsxydmArr" value=""/>
											<input type="hidden" id="xymc_<%=rsNum+i%>" name="xsxymcArr" value=""/>
											<input type="hidden" id="bjdm_<%=rsNum+i%>" name="xsbjdmArr" value=""/>
											<input type="hidden" id="bjmc_<%=rsNum+i%>" name="xsbjmcArr" value=""/>
											<input type="hidden" id="zydm_<%=rsNum+i%>" name="xszydmArr" value=""/>
											<input type="hidden" id="zymc_<%=rsNum+i%>" name="xszymcArr" value=""/>
											<input type="hidden" id="nj_<%=rsNum+i%>" name="xsnjArr" value=""/>
											<input type="hidden" id="rows_<%=rsNum+i%>" name="rowsArr" value=""/>
										</td>
									</tr>
									<%
									}
									%>
									<!-- ������ end-->
									</tbody>
								</table>
							</div>
							</div>
							</div>
							<div class="rightframe04"><!--�������Ŀ��������ʱ����rightframe04_hidden���class��-->
								<table class="formlist" style="margin-bottom:4px" >
					    			<thead>
										<tr>
											<td >
												<span>
													���չ��������Ϣ
													<font color="blue">
													�������:
													<logic:equal name="fpdx" value="xy"><bean:message key="lable.xb" /></logic:equal>
													<logic:equal name="fpdx" value="njxy">�꼶+<bean:message key="lable.xb" /></logic:equal>
													<logic:equal name="fpdx" value="njzy">�꼶+רҵ</logic:equal>
													<logic:equal name="fpdx" value="bj">�༶</logic:equal>
													</font>
												</span>	
												<div class="btn">
													<button onclick='save();return false;'>�� ��</button>
									                <button onclick='fhcwxxtj()'>�� ��</button>
												</div>
											</td>
										</tr>
									<thead>
								</table>
							    <div class="tab">
									<%
											List<HashMap<String, String>> qsxxList = (List<HashMap<String, String>>) request
											.getAttribute("qsxxList");
											List<HashMap<String, Object>> ldxxList = (List<HashMap<String, Object>>) request
											.getAttribute("ldxxxxList");
											for (int i = 0; i < ldxxList.size(); i++) {
												HashMap<String, Object> ldxxMap = (HashMap<String, Object>) ldxxList
												.get(i);
									%>
									<div class="table_updown">
										<a href="#" class="table_down"
											onclick="showLcxx('<%=ldxxMap.get("lddm")%>');showTable(this,'lddm_<%=ldxxMap.get("lddm")%>');return false">
											<%=ldxxMap.get("xqmc")==null ?"" : ldxxMap.get("xqmc")%> 
											<%=ldxxMap.get("yqmc")==null ?"" : ldxxMap.get("yqmc")%> 
											<%=ldxxMap.get("ldmc")==null ?"" : ldxxMap.get("ldmc")%>
											(<%=ldxxMap.get("xbxd")==null ?"" : ldxxMap.get("xbxd")%>)
										</a>
									</div>
									<table width="100%" border="0" class="formlist"
										id='lddm_<%=ldxxMap.get("lddm")%>' style="display:none">
										<%
													List<HashMap<String, String>> ldlcList = (ArrayList<HashMap<String, String>>) ldxxMap
													.get("ldlcxx");
													int num = 0;
													for (int j = 0; j < ldlcList.size(); j++) {
												HashMap<String, String> ldlcMap = (HashMap<String, String>) ldlcList
														.get(j);
														
										%>
										
										<thead>
											<tr>
												<th>
													<a href="#" class="up" id="a_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>"
														onclick="showLoad('<%=ldxxMap.get("lddm")%>','<%=ldlcMap.get("cs")%>');return false"><%=ldlcMap.get("cs")%>��</a>
												</th>
												<th colspan="4">
													<em class='chamber'
														id='lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_qss'>&nbsp;</em>
													<em class='chamber'
														id='lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_wfps'>&nbsp;</em>
												</th>
											</tr>
										</thead>
										<!-- ¥�� ���� -->
										<tr  id="lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_div" style="display:none">
											<td colspan="5">
												<div id="div_tsxx" align="center">
													</br></br></br></br></br></br>
													<img src="<%=stylePath%>images/ico_loading.gif"/>
													</br>
													<img src="<%=stylePath%>images/loadingli.gif"/>
													<p id="p_tsxx"><B>ס����Ϣ��ʼ���У����Ժ󡣡���</B></p>
												</div>
											</td>
										</tr>
										<tbody id='lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>'
											style="display:none">
											<%
													//¥��������
													int qss = 0;
													if (ldlcMap.get("qss") != null) {
														qss = Integer.parseInt(ldlcMap.get("qss"));
													}

													//��������
													int len = qsxxList.size();
													
													int m = 1;
													//��������Ϣ���м���
													int n = 0;
													ArrayList<HashMap<String, String>> qsxx = new ArrayList<HashMap<String, String>>();
													for (int k = 1; k <= len; k++) {
														HashMap<String, String> qsxxMap = qsxxList
														.get(k - 1);
														if (ldlcMap.get("cs").equalsIgnoreCase(
														qsxxMap.get("cs"))
														&& (ldxxMap.get("lddm").toString())
																.equalsIgnoreCase(qsxxMap
																.get("lddm"))) {
															//����ÿ��������Ϣ
															qsxx.add(qsxxMap);
															n++;
															
														}

														//��һ����4����¼ʱ
														if (n == 4) {
															//��N��0���¼���
															n = 0;
											%>
											<!-- ������еļ�¼�����Һţ� -->
											<tr>
												<%
															for (int l = 0; l < qsxx.size(); l++) {
															HashMap<String, String> qsMap = qsxx.get(l);
												%>
												<td style="width:25%">
													<span class="num"
														id="qsxx_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_<%=qsMap.get("qsh")%>">

													</span>
												</td>
												<%
												}
												%>
											</tr>
											<!-- ������еļ�¼�����Һţ� end-->

											<!-- ������еļ�¼��������ϸ��Ϣ�� -->
											<tr>
												<%
															for (int l = 0; l < qsxx.size(); l++) {
															HashMap<String, String> qsMap = qsxx.get(l);
															
												%>
												<td  style="width:25%">
													<p
														id='cwxx_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_<%=qsMap.get("qsh")%>'>
													</p>
												</td>
												<%
																num++;
																}
												%>
											</tr>
											<!-- ������еļ�¼��������ϸ��Ϣ end�� -->
											<%
															//���¹���������ϢLIST
															qsxx = new ArrayList<HashMap<String, String>>();

														}
													}

													//�����¥������������ܳ���5��˵�����ж���������Ϣδ�����
													if (qss % 4 != 0) {
											%>
											<tr>
												<%
																for (int l = 0; l < qsxx.size(); l++) {
																HashMap<String, String> qsMap = qsxx.get(l);
																
												%>
												<!-- ������еļ�¼�����Һţ� -->
												<td  style="width:25%">
													<span class="num"
														id='qsxx_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_<%=qsMap.get("qsh")%>'>

													</span>
												</td>
												<!-- ������еļ�¼�����Һţ�end -->

												<%
															}
															//������
															for (int z = 1; z <= 4 - n; z++) {
												%>
												<td></td>
												<%
												}
												%>
											</tr>

											<tr>
												<%
																for (int l = 0; l < qsxx.size(); l++) {
																HashMap<String, String> qsMap = qsxx.get(l);
												%>

												<td  style="width:25%">
													<p id='cwxx_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_<%=qsMap.get("qsh")%>'>
													</p>

												</td>
												<%
															num++;
															}
															//������
															for (int z = 1; z <= 4 - n; z++) {
												%>
												<td></td>
												<%
												}
												%>
											</tr>
											<%
											}
											%>
										</tbody>
										<%
										}
										%>
									</table>
									<%
									}
									%>
								<table width="100%"  border="0" class="formlist">
			      </table>
			    </div>
			</div>
			
		
			
			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
						alert("�ֶ�����ɹ���");
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("�ֶ�����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:present>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>
