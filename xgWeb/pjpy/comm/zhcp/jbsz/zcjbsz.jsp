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
						bl="未设置";
					}
					$("span_bl_"+i).innerHTML=bl;
					
				}
			}
			
			$("btn_bj").style.display="";
			$("btn_qd").style.display="none";
		}
		
		
		function saveZhcp(){
			//保存项目名用隐藏域
			var zcxmmcArr=document.getElementsByName("zcxmmcArr");
			var zcxmdmArr=document.getElementsByName("zcxmdmArr");
			var xmdm=document.getElementsByName("xmdm");
			var xmmc=document.getElementsByName("xmmc");
			
			//保存比例
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
			confirmInfo('综测初始化操作将会初始化综测库信息,该操作将会重置当前综测!',function(t){
				if (t=='ok'){
					showLoadPage();
					refreshForm("/xgxt/zhcpJbsz.do?method=zcjbsz&doType=init");
				}
			})
		}
		
		function copyXm(){
			confirmInfo('综测项目复制操作将复制上一综测周期的项目信息,是否继续?',function(t){
				if (t=='ok'){
					showLoadPage();
					refreshForm("/xgxt/zhcpJbsz.do?method=zcjbsz&doType=copyXm");
				}
			})
		}
		
		//显示加载页面
		function showLoadPage(){
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
		}
		
		function removeZcxm(){
			
			confirmInfo('您确定要清除当前周期的综测项目吗?',function(t){
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
				<em>您的当前位置：</em><a>评奖评优-评奖综合设置-综测基本设置
				</a>
			</p>
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
		</div>
	
		<!-- 提示信息-->
		<div class="prompt">
			<h3>
				<span>综测周期：</span>
			</h3>
			<p>
				评奖学年(${pjxn })&nbsp;&nbsp;
				评奖学期(${pjxqmc })&nbsp;&nbsp;
				评奖年度(${pjnd })&nbsp;&nbsp;
				综测周期(
					<logic:equal name="zczq" value="xn">
						学年
					</logic:equal>
					<logic:equal name="zczq" value="xq">
						学期
					</logic:equal>
				)&nbsp;&nbsp;
			</p>
		</div>
		<!-- 提示信息 end-->	
	
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>评奖基本设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('zhcp_sjcl_sjdr.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function35.png" />
							<p>综测分维护</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('zhcp_zczf_zccx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function30.png" />
							<p>综测分查询(计算)</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		
		
		
		<html:form action="/zhcpJbsz">
			<!-- 综测项目列表 -->
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
												温馨提示：
											</h3>
											<p>
												当前综测周期未设置综测项目,请进行复制项目操作或者与管理员联系。
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
										项目复制
									</button>
								</logic:empty>
								<logic:notEmpty name="xmList">
								<button type="button" id="btn_init" onclick='init()'>
									综测初始化
								</button>
								</logic:notEmpty>
								<logic:notEmpty name="xmList">
									<button type="button" id="btn_bj" onclick='editor()'>
										编 辑
									</button>
									<button type="button" id="btn_bc" style="display:none" onclick='saveZhcp()'>
										保 存
									</button>
								</logic:notEmpty>
									
									<button type="button" id="btn_bc"  onclick='removeZcxm()'>
										清除项目
									</button>
								
								</div>
							</td>
						</tr>
					<thead>
				</table>
			</div>
		</html:form>
		
		<!-- 项目复制 操作结果 -->
		<logic:equal name="copyResult" value="true">
			<logic:present name="message">
				<script>
					alertInfo("${message}!");	
				</script>
			</logic:present>
		</logic:equal>
		<logic:equal name="copyResult" value="false">
			<script>
				alertInfo("上一周期的综测项目不存在，所以无法复制综测项目!");	
			</script>
		</logic:equal>
		<!-- 项目复制 操作结果end -->
		
		<!-- 综测数据初始化 操作结果 -->
		<logic:present name="initResult">
			<logic:present name="message">
				<script>
					alertInfo("综测数据${message}!");	
				</script>
			</logic:present>
		</logic:present>
		<!-- 综测数据初始化 操作结果end -->
		
		<!-- 综测比例修改操作结果 -->
		<logic:present name="updateResult">
			<logic:present name="message">
				<script>
					alertInfo("${message}!");	
				</script>
			</logic:present>
		</logic:present>
		<!-- 综测比例修改操作结果end -->
		
		<logic:present name="removeResult">
			<script defer>
				alertInfo('${removeResult}');
			</script>
		</logic:present>
	</body>
	
	
	<!-- 等待ing -->
	<div id="divWaiting" style="display: none; z-index: 1100; left: 25%; right: 25%; position: absolute;
	     text-align: center; width: 50%; height: 50px;left: expression((this.offsetParent.clientWidth/2)-(this.clientWidth/2)+this.offsetParent.scrollLeft);
	     top: expression((this.offsetParent.clientHeight/2)-(this.clientHeight/2)+this.offsetParent.scrollTop);">
	   <img src="<%=stylePath%>images/ico_loading.gif"/>
	   <p id="p_tsxx"><B>正在进行综测信息初始化,请稍候。。。</B></p>
	</div>

	<div id="divDisable" style="display: none;width:expression(document.body.offsetWidth);height:expression(document.body.offsetHeight); z-index: 1000; position: absolute;left: 0px; top: 0px;filter:alpha(opacity=50); background-color:white"></div>
</html>
