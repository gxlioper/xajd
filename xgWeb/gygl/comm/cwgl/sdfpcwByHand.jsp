<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
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
		
		//显示楼层内寝室详细信息 
		
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
		
		//显示楼层详细信息
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
						$("lc_"+lddm+"_"+cs+"_qss").innerHTML="<em class='chamber'>空闲寝室数（个）:"+qss+"</em>";
						$("lc_"+lddm+"_"+cs+"_wfps").innerHTML="<em class='chamber'>未分配床位（个）:"+wrzcw+"</em>";
	
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
		
		//显示寝室详细信息
		function showQsxx(lddm,cs){
		
			var userStatus=$("userStatus").value;
			var userDep=$("userDep").value;
			var fpdx=$("fpdx").value;
		
			$("lc_"+lddm+"_"+cs+"_div").style.display="none";
			//判断该对象是否存在,如果存在说明
			//该对象已经构键,就不需要再次构键
			if(!$(lddm+"_"+cs+"_cs")){
				
				var qscwxxArr=new Array();
				
				dwr.engine.setAsync(false);
				
				var xsArr=new Array();
				//寝室分配列表
				gyglCwgl.getQsxxList(lddm,fpdx,userDep,userStatus,cs,xsArr,function(data){
					var lddmbf="";
					var csbf="";
					var qshbf="";
					var n=0;
					
					for(i=0;i<data.length;i++){
						var xxHtml="";
						//楼栋代码
						var lddm=data[i].lddm;
						//层数
						var cs=data[i].cs;
						//寝室号
						var qsh=data[i].qsh;
						//楼栋代码
						var cws=data[i].cws;
						//层数
						var fpbm=data[i].fpbm;
						//寝室号
						var rzrs=data[i].rzrs;
						//寝室号
						var fpdx=data[i].fpdx;
						//cwxxArr
						var cwxxArr=data[i].cwxxArr;
						for(j=0;j<cwxxArr.length;j++){
							
							//分配部门
							var cwh=cwxxArr[j].cwh;
							//分配对象
							var cws=cwxxArr[j].cws;
							//床位号
							var xh=cwxxArr[j].xh;
							//床位数
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
								xxHtml+="性别限制:"+xb+"</br>部门混住:"+kfhz+"<br/>";
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
							xxHtml+="<span class='bed_num' id='"+xmcwid+"'>"+cwh+"号床:"+xm+"</span>";
							
							//将后台获取的字段拼接成字符传
							var qscwxxArr=lddm+"!!split!!"+cs+"!!split!!"+qsh+"!!split!!"+fpbm+"!!split!!"+fpdx;
							qscwxxArr+="!!split!!"+cwh+"!!split!!"+cws+"!!split!!"+xh+"!!split!!"+zrs;
							qscwxxArr+="!!split!!"+xm+"!!split!!"+xb+"!!split!!"+kfhz+"!!split!!"+xydm;
							qscwxxArr+="!!split!!"+xymc+"!!split!!"+zydm+"!!split!!"+zymc+"!!split!!"+bjdm;
							qscwxxArr+="!!split!!"+bjmc+"!!split!!"+nj;
							
							if("未分配"==fpbm ){
								xxHtml+="<span  id='"+cwid+"_cz'><a class='cancel_02' onclick=\"return false;\">入住</a></span>";
							}else if("行李床位"==xm){
								xxHtml+="<span  id='"+cwid+"_cz'><a class='cancel_02' onclick=\"return false;\">入住</a></span>";
							}else if(xm=="未分配"){
								xxHtml+="<span  id='"+cwid+"_cz'><a class='intake'  href='#' onclick=\"xsrzcw('"+qscwxxArr+"','"+cwid+"','"+xmcwid+"');return false;\">入住</a></span>";
							}else {
								xxHtml+="<span id='"+cwid+"_cz'><a class='cancel_02' onclick=\"return false;\">入住</a></span>";
							}
							//学号(保存住宿信息 主键)
							xxHtml+="<input type='hidden' name='xhArr' id='"+cwid+"_xh' value='"+xh+"'/>";
							//床位号(保存住宿信息)
							xxHtml+="<input type='hidden' name='cwhArr' id='"+cwid+"_cwh' value='"+cwh+"'/>";
							//床位号(保存住宿信息)
							xxHtml+="<input type='hidden' name='lddmArr' id='"+cwid+"_lddm' value='"+lddm+"'/>";
							//床位号(保存住宿信息)
							xxHtml+="<input type='hidden' name='csArr' id='"+lddm+"_"+cs+"_cs' value='"+cs+"'/>";
							//床位号(保存住宿信息)
							xxHtml+="<input type='hidden' name='qshArr' id='"+cwid+"_qsh' value='"+qsh+"'/>";
							//姓名(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwxmArr' id='"+cwid+"_xm' value='"+xm+"'/>";
							//性别(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwxbArr' id='"+cwid+"_xb' value='"+xb+"'/>";
							//部门代码(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwxydmArr' id='"+cwid+"_xydm' value='"+xydm+"'/>";
							//部门代码(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwxymcArr' id='"+cwid+"_xymc' value='"+xymc+"'/>";
							//部门代码(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwzydmArr' id='"+cwid+"_zydm' value='"+zydm+"'/>";
							//部门代码(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwzymcArr' id='"+cwid+"_zymc' value='"+zymc+"'/>";
							//班级代码(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwbjdmArr' id='"+cwid+"_bjdm' value='"+bjdm+"'/>";
							//班级名称(取消操作时显示需要)
							xxHtml+="<input type='hidden' name='cwbjmcArr' id='"+cwid+"_bjmc' value='"+bjmc+"'/>";
							//年级
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
		
		//学生入住床位
		//寝室信息、详细信息、
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
					alert("请先选择需要入住的学生!");
					return false;
				}
				
				//需要删除的行
				
				var qscwArr=qscwxxArr.split("!!split!!");
				
				var qsfpbm=qscwArr[3];
				//寝室分配对象
				var fpdx=qscwArr[4];
				//寝室性别
				var qsxb=qscwArr[10];
				//可否混住
				var kfhz=qscwArr[11];
				//床位号
				var cwh=qscwArr[5];
				var blog=false;
				
				if(xb==qsxb){
					if(kfhz=="可以"){
						$(id+"_xh").value=xh;
						$(id+"_xmxs").innerHTML=cwh+"号床:"+xm;
						blog=true;
					}else {
						if(fpdx=="xy" && xymc==qsfpbm){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"号床:"+xm;
							blog=true;
						}else if(fpdx=="njxy"  && qsfpbm==nj+xymc){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"号床:"+xm;
							blog=true;
						}else if(fpdx=="njzy"  && qsfpbm==nj+zymc){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"号床:"+xm;
							blog=true;
						}else if(fpdx=="bj"  && qsfpbm==bjmc){
							$(id+"_xh").value=xh;
							$(id+"_xmxs").innerHTML=cwh+"号床:"+xm;
							blog=true;
						}else {
							alert("该寝室不可混住，只能入住"+qsfpbm+"的学生");
							return false;
						}
					}
				}else{
					alert("该寝室为"+qsxb+"寝室，只可入住"+qsxb+"生");
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
				
				
				var addhtml="<a class='cancel_01'  href='#' onclick=\"xsqxcw('"+qscwxxArr+"','"+id+"','"+xmid+"');return false;\">取消</a>";
				$(id+"_cz").innerHTML=addhtml;
				//判断是否满足入住条件
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
				alert("无需要分配床位的学生!");
				return false;
			}
		}
		
		//学生取消床位
		function xsqxcw(qscwxxArr,id,xmid){
			//学生住宿详细信息
			var qscwArr=qscwxxArr.split("!!split!!");
			
			$("fpcwxh").value=$(id+"_xh").value;
			loadFpcwqx();
			if($("tr_0")){
				$("tr_0").click();
			}
			var addhtml="<a class='intake' href='#' onclick=\"xsrzcw('"+qscwxxArr+"','"+id+"','"+xmid+"');return false;\">入住</a>";
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
			$(id+"_xmxs").innerHTML=qscwArr[5]+"号床:未分配";
				
		}
			
			
		
		function loadAttach(len){
			($("span_xh_"+len).parentElement).parentElement.attachEvent('onclick', function(){rowOnClick($("tr_"+len))});
			
			($("span_xh_"+len).parentElement).parentElement.style.cursor="hand";
		}
		
		//重构左边菜单中学生信息
		function replaceTable(qsxx,xxxx,xh,id){
			//获取选中行中的隐藏域数据
			//学号
			var xha=curr_row.getElementsByTagName('input')[0].value;
			//姓名
			var xma=curr_row.getElementsByTagName('input')[1].value;
			//性别
			var xba=curr_row.getElementsByTagName('input')[2].value;
			//学院代码
			var xydm=curr_row.getElementsByTagName('input')[3].value;
			//学院名称
			var xymc=curr_row.getElementsByTagName('input')[4].value;
			//班级代码
			var bjdm=curr_row.getElementsByTagName('input')[5].value;
			//班级名称
			var bjmc=curr_row.getElementsByTagName('input')[6].value;
			//专业代码
			var zydm=curr_row.getElementsByTagName('input')[7].value;
			//专业名称
			var zymc=curr_row.getElementsByTagName('input')[8].value;
			//年级
			var nj=curr_row.getElementsByTagName('input')[9].value;
			
			//获取左边菜单中学生详细信息列表
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
				//从头往后循环找到选中的行
				if(xha==xhArr[i].value){
					//找到选中行后开始做赋值操作
					blog=true;
				}
				
				//判断是否开始循环,(i+1)是否小于对象的长度
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
				
				//当i是循环中最后一个元素时清空所有内容
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
			//查询条件
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
					}else if($("searchText").value!="输入姓名查询"){
						if($("span_xh_"+i).innerHTML!="&nbsp;"){
							
							$("tr_"+i).style.display="none";
						}
					}
			}
			clickTr();
			
		}
		
		function checkNr(){
			if($("searchText").value=="输入姓名查询"){
				$("searchText").value="";
			}
		}
		
		//table id,增加的是第几行
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

		
		//返回
		function fhcwxxtj(){
			if (confirm("将要返回前一页面，请确认已经保存了分配信息！")) {
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
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 床位管理 - 床位分配</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglCwgl">
			<%@ include file="/comm/loading.jsp"%>
			<input type="hidden" name="numDel" id="numDel" value=""/>
			<input type="hidden" name="userStatus" id="userStatus" value="${userStatus}"/>
			<!-- 隐藏域 --> 
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="fpdx" value="${fpdx }" />
			<input type="hidden" name="fpcwxh" id="fpcwxh" value=""/>
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<div class="leftframe04" style="height:500px; overflow:auto; overflow-x:hidden ">
					<div class="formbox">
						<!--标题start-->
				    <h3 class="datetitle_01">
				    <span>学生名单</span>
				    </h3>
						<!--标题start-->
						<!--查询end-->
					<div class="query_name">
				    	<input name="searchText" id="searchText" type="text" value="输入姓名查询" onfocus="checkNr()" onkeyup="searchName(this.value)" /><button></button>
				    </div>
				    <div id="fpcwxsxxMain">
						<table summary="" class="datelist nowrap" align="left" width="90%" id="xsmd">
									<!-- 表头 -->
									<thead>
										<tr align="center" style="cursor:hand">
											<td id="xh"  nowrap>
												学号
											</td>
											<td id="xm"  nowrap>
												姓名
											</td>
										</tr>
									</thead>
									<!-- 表头 end-->
									<!--内容 -->
									<tbody id="xsmdtbody">
									<logic:notEmpty name="fpcwxsList">
										<logic:iterate name="fpcwxsList" id="s" indexId="index">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${index}" title='${s.xb}'>
												<!-- 显示信息 -->
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
									<!--内容 end-->
									<!-- 补空行 -->
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
									<!-- 补空行 end-->
									</tbody>
								</table>
							</div>
							</div>
							</div>
							<div class="rightframe04"><!--当左边栏目导航隐藏时调用rightframe04_hidden这个class名-->
								<table class="formlist" style="margin-bottom:4px" >
					    			<thead>
										<tr>
											<td >
												<span>
													点击展开隐藏信息
													<font color="blue">
													分配对象:
													<logic:equal name="fpdx" value="xy"><bean:message key="lable.xb" /></logic:equal>
													<logic:equal name="fpdx" value="njxy">年级+<bean:message key="lable.xb" /></logic:equal>
													<logic:equal name="fpdx" value="njzy">年级+专业</logic:equal>
													<logic:equal name="fpdx" value="bj">班级</logic:equal>
													</font>
												</span>	
												<div class="btn">
													<button onclick='save();return false;'>保 存</button>
									                <button onclick='fhcwxxtj()'>返 回</button>
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
														onclick="showLoad('<%=ldxxMap.get("lddm")%>','<%=ldlcMap.get("cs")%>');return false"><%=ldlcMap.get("cs")%>层</a>
												</th>
												<th colspan="4">
													<em class='chamber'
														id='lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_qss'>&nbsp;</em>
													<em class='chamber'
														id='lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_wfps'>&nbsp;</em>
												</th>
											</tr>
										</thead>
										<!-- 楼层 内容 -->
										<tr  id="lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_div" style="display:none">
											<td colspan="5">
												<div id="div_tsxx" align="center">
													</br></br></br></br></br></br>
													<img src="<%=stylePath%>images/ico_loading.gif"/>
													</br>
													<img src="<%=stylePath%>images/loadingli.gif"/>
													<p id="p_tsxx"><B>住宿信息初始化中，请稍后。。。</B></p>
												</div>
											</td>
										</tr>
										<tbody id='lc_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>'
											style="display:none">
											<%
													//楼层寝室数
													int qss = 0;
													if (ldlcMap.get("qss") != null) {
														qss = Integer.parseInt(ldlcMap.get("qss"));
													}

													//总寝室数
													int len = qsxxList.size();
													
													int m = 1;
													//对寝室信息进行记数
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
															//保存每行寝室信息
															qsxx.add(qsxxMap);
															n++;
															
														}

														//当一行有4条记录时
														if (n == 4) {
															//将N清0重新记数
															n = 0;
											%>
											<!-- 打出该行的记录（寝室号） -->
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
											<!-- 打出该行的记录（寝室号） end-->

											<!-- 打出该行的记录（寝室详细信息） -->
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
											<!-- 打出该行的记录（寝室详细信息 end） -->
											<%
															//重新构建寝室信息LIST
															qsxx = new ArrayList<HashMap<String, String>>();

														}
													}

													//如果该楼层的寝室数不能除禁5（说明还有多余寝室信息未输出）
													if (qss % 4 != 0) {
											%>
											<tr>
												<%
																for (int l = 0; l < qsxx.size(); l++) {
																HashMap<String, String> qsMap = qsxx.get(l);
																
												%>
												<!-- 打出该行的记录（寝室号） -->
												<td  style="width:25%">
													<span class="num"
														id='qsxx_<%=ldxxMap.get("lddm")%>_<%=ldlcMap.get("cs")%>_<%=qsMap.get("qsh")%>'>

													</span>
												</td>
												<!-- 打出该行的记录（寝室号）end -->

												<%
															}
															//补空行
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
															//补空行
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
						alert("手动分配成功！");
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("手动分配失败！");
					</script>
				</logic:equal>
			</logic:present>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>
