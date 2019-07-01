<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommGygl.js'></script>
		<script language="javascript" defer="defer">
		//点击公寓结构
		function clickGyjg(lv,dm,dj){
			
			if(lv == "1"){//点击第一级
				var div2_name = "div_info2_"+dm;
				var div2_num = document.getElementsByName(div2_name).length;

				for(var i=0;i<div2_num;i++){
					var obj = document.getElementsByName(div2_name)[i];
					var id = obj.value;
					var value = id.split("_")[3];
					
					if($(id)){
						if($(id).style.display == "none"){
							$(id).style.display = "";
						}else{
							$(id).style.display = "none";
						}
					}
					
					//联动下一级
					clickGyjg("2",value,"no");
				}	
				
			}else if(lv == "2"){//点击第二级
				var div3_name = "div_info3_"+dm;
				var div3_num = document.getElementsByName(div3_name).length;

				for(var i=0;i<div3_num;i++){
					var obj = document.getElementsByName(div3_name)[i];
					var id = obj.value;
					var value = id.split("_")[3];
					
					if($(id)){
						if($(id).style.display == "none"){
							if(dj == "yes"){
								$(id).style.display = "";
							}
						}else{
							$(id).style.display = "none";
						}
					}
					
					//联动下一级
					clickGyjg("3",value,"no");
				}			
			}else if(lv == "3"){//点击第三级
				var div4_name = "div_info4_"+dm;
				var div4_num = document.getElementsByName(div4_name).length;

				for(var i=0;i<div4_num;i++){
					var obj = document.getElementsByName(div4_name)[i];
					var id = obj.value;
					
					if($(id)){
						if($(id).style.display == "none"){
							if(dj == "yes"){
								$(id).style.display = "";
							}
						}else{
							$(id).style.display = "none";
						}
					}
				}
			}else if(lv == "4"){//点击第四级

			}
		}
		
		//设置寝室信息
		function creatQsInfo(lddm,cs){
		
			dwr.engine.setAsync(false);
			
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
	
			//寝室级别
			var qsjb = $("qsjb").value;
			var div_id = "div_"+lddm+"_"+cs;
	
			if($(div_id)){
				//已存在寝室DIV
				if($(div_id).innerHTML != ""){
					if($(div_id).style.display == "none"){
						$(div_id).style.display = "";
					}else{
						$(div_id).style.display = "none";
					}
				}else{
					//分配对象
					var fpdx = $("fpdx").value;
					//联动寝室
					var gyInfo = [lddm,cs,fpdx];
					//宿舍编号
					var ssbh = new Array();
					//构造的tr数量
					var tr_num = "0";
					
					for(var i=0;i<document.getElementsByName("ssbh").length;i++){
						
						ssbh[i]=document.getElementsByName("ssbh")[i].value;
					}

					getCommGygl.getQsInfoList(gyInfo,ssbh,userStatus,userName,userDep,function(data){

						if(data !=null && data.length >0){
							
							var divHtml = "<table style=\"width: 100%\">";
							
							//每行显示列数
							var colNum = 5;
							//补空数
							var spaceSize = 0;
							//行数
							var rowNum = data.length/colNum;
							//寝室号
							var qshHtml = new Array();
							//寝室内容
							var qsnrHtml = new Array();
							
							if(data.length%colNum!=0){
								spaceSize=data.length%colNum+(5-1);
								rowNum=parseInt(data.length/colNum+1);
							}
							
							//循环行数
							for(var i=0;i<rowNum;i++){
							
								qshHtml[i]="<tr>";
								qsnrHtml[i]="<tr>";
								
								var n=0;
								
								for(var j=(i*colNum);j<data.length+spaceSize;j++){
									
									if(n!=colNum){
										//寝室号
										qshHtml[i]+="<td width=\"20%\">"
										qshHtml[i]+="<span class=\"num\">";
										if(j<data.length){
											qshHtml[i]+=data[j].qsh;
											qshHtml[i]+="("+data[j].xb+")";
										}
										qshHtml[i]+="</span>";
										qshHtml[i]+="</td>"
										
										//寝室内容
										qsnrHtml[i]+="<td width=\"20%\">"
										if(j<data.length){
											//楼栋代码
											var lddm = data[j].lddm;
											//楼栋名称
											var ldmc = data[j].ldmc;
											//层数
											var cs = data[j].cs;
											//寝室号
											var qsh = data[j].qsh;
											//入住状态
											var rzzt = data[j].rzzt;
											//分配标记
											var fpbj = data[j].fpbj;
											
											//分配部门
											var fpbm = data[j].fpbm;
											var allmc = data[j].fpbm;
											//分配年级
											var nj = "";
											//分配部门
											var bmdm = "";
												
											if(data[j].bmdm !=""&& data[j].bmdm!=null){
												bmdm = data[j].bmdm;
											}
												
											if(data[j].nj !=""&& data[j].nj!=null){
												nj = data[j].nj;
											}
												
											//保留寝室
											if(fpbj == "保留"){
												allmc="保留寝室";
												fpbm="保留寝室";
											}
											//已分配
											else if(fpbm !="" && fpbm !=null){
												fpbm=fpbm;
												allmc = allmc;
												if(fpbm.length >6){
													fpbm = fpbm.substring(0,6)+"..."
												}	
											}else{
												allmc="";
												fpbm="未分配";
											}
											
											//保存信息所需要的隐藏域
											divHtml+="<input type=\"hidden\" name=\"lddm\" value=\""+lddm+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"ldmc\" value=\""+ldmc+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"cs\" value=\""+cs+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"qsh\" value=\""+qsh+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"rzzt\" value=\""+rzzt+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"nj\" id=\"nj_"+lddm+"_"+cs+"_"+qsh+"\" value=\""+nj+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"bmdm\" id=\"bmdm_"+lddm+"_"+cs+"_"+qsh+"\" value=\""+bmdm+"\"/>";
											
											qsnrHtml[i]+="<span id=\"span_"+lddm+"_"+cs+"_"+qsh+"\" title=\""+allmc+"\">(";
											qsnrHtml[i]+=fpbm;
											qsnrHtml[i]+=")</span>";
											qsnrHtml[i]+="</br>";
												
											//床位数
											var cws = data[j].cws;
											qsnrHtml[i]+="床位数："+cws;
											qsnrHtml[i]+="</br>";
											
											//已住人数
											var yzrs = data[j].yzrs;
											qsnrHtml[i]+="已入住："+yzrs;
											qsnrHtml[i]+="</br>";
											
											qsnrHtml[i]+="入住状态："+rzzt;
											
											//保留寝室
											if(fpbj == "保留"){
												qsnrHtml[i]+="<a class=\"cant\">不可分配</a>";
											}
											//已分配
											else if(fpbm !="未分配"){
												qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"cancel\">取消分配</a>";
											}else{
												qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"distribution\">分配寝室</a>";
											}
											
										}
										qsnrHtml[i]+="</td>"
										
										n++;
									}else{
										n=0;
										break;
									}
								}	
								
								qshHtml[i]+="</tr>";
							}
							
							for(var i=0;i<rowNum;i++){
								divHtml+=qshHtml[i];
								divHtml+=qsnrHtml[i];
							}
							
							divHtml+= "</table>";
					
							$(div_id).innerHTML = divHtml;
						}else{
							$(div_id).innerHTML = "该楼层尚未维护寝室，请确认";
						}
					});
						
				}
			}
			
			dwr.engine.setAsync(true);
		}
		
		//点击分配对象生成数
		function clickBm(lv,bmdm,djlx,nj){
			
			var fpdx = $("fpdx").value;
			var ul_id = "ul_"+lv+"_"+bmdm;
			
			var flag = true;
			
			if(fpdx == "bj" && djlx == "bj"){
				flag = false;
			}else if(fpdx == "njzy" && djlx == "zy"){
				flag = false;
			}else if(fpdx == "njxy" && djlx == "xy"){
				flag = false;
			}else if(fpdx == "xy" && djlx == "xy"){
				flag = false;
			}
			
			if($(ul_id) && flag){
				if($(ul_id).innerHTML != ""){
					if($(ul_id).style.display == "none"){
						$(ul_id).style.display = "";
					}else{
						$(ul_id).style.display = "none";
					}
				}else{
					creatNewBm(lv,bmdm,djlx,nj);
					$(ul_id).style.display = "";
				}
			}
		}
		
		//生成新部门
		function creatNewBm(lv,bmdm,djlx,nj){
		
			var bmlx = "";

			if(djlx == "nj"){
				bmlx = "xy";
			}else if(djlx == "xy"){
				bmlx = "zy";
			}else if(djlx == "zy"){
				bmlx = "bj";
			}
			
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			var bmInfo=[bmlx,nj,bmdm];
			
			var ul_id = "ul_"+lv+"_"+bmdm;
			var nextLv = parseInt(lv)+1;

			dwr.engine.setAsync(false);
	
			var divHtml = "";
			
			getCommGygl.getBmList(bmInfo,userStatus,userName,userDep,function(data){
				if(data !=null && data.length >0){
					for(var i=0;i<data.length;i++){
						divHtml += "<li>";
						divHtml += "<a href=\"#\""; 
						divHtml += "onclick=\"clickBm('"+nextLv+"','"+data[i].dm+"','"+data[i].bmlx+"','"+data[i].nj+"');return false;\">";
						divHtml += data[i].mc;
						divHtml += "</a>";
						divHtml += "</li>";
						divHtml += "<ul id=\"ul_"+nextLv+"_"+data[i].dm+"\" style=\"display:none\">";											
						divHtml += "</ul>";
					}
				}
			});

			$(ul_id).innerHTML = divHtml;
			
			dwr.engine.setAsync(true);
		}
		
		//分配寝室
		function fpqs(obj,lddm,cs,qsh){

			var span_id = "span_"+lddm+"_"+cs+"_"+qsh;
			var bmdm_id = "bmdm_"+lddm+"_"+cs+"_"+qsh;
			var nj_id = "nj_"+lddm+"_"+cs+"_"+qsh;
			
			if(obj.innerHTML == "分配寝室"){

				if($("clickBmdm").value == ""){
					alert("请在左侧的部门列表中选择需要被分配的对象");
					return false;
				}
				
				if($(span_id)){
					var nj = $("clickBmnj").value;
					var bmdm = $("clickBmdm").value;
					var bmmc = $("clickBmmc").value;
					var allmc = bmmc;

					if(bmmc.length > 5){
						allmc = bmmc.substring(0,5)+"...";
					}
					
					$(span_id).innerHTML = "("+allmc+")";
					$(span_id).title = bmmc;
					
					if($(bmdm_id)){
						$(bmdm_id).value = bmdm;
					}
					
					if($(nj_id)){
						$(nj_id).value = nj;
					}
				}
			
				obj.className = "cancel";
				obj.innerHTML = "取消分配";
			}else{
			
				if($(span_id)){
					$(span_id).innerHTML = "未分配";
					$(span_id).title = "";
				}
				
				obj.className = "distribution";
				obj.innerHTML = "分配寝室";
				
				if($(bmdm_id)){
					$(bmdm_id).value = "";
				}
				
				if($(nj_id)){
					$(nj_id).value = "";
				}
			}
		}
		
		//返回手动分配
		function fhsdfp(){
			if (confirm("将要返回前一页面，请确认已经保存了分配信息！")) {
				refreshForm("gyglQsgl.do?method=qssdfp&doType=return");
			}
		}
		
		//保存手动分配
		function saveQsfp(){
			var num =  document.getElementsByName("lddm").length;
			for(var i=0;i<num;i++){
				var lddm = document.getElementsByName("lddm")[i];
				var ldmc = document.getElementsByName("ldmc")[i];
				var cs = document.getElementsByName("cs")[i];
				var qsh = document.getElementsByName("qsh")[i];
				var rzzt = document.getElementsByName("rzzt")[i];
				var bmdm = document.getElementsByName("bmdm")[i];
				
				if(bmdm.value=="" && rzzt.value!="空的"){
					alert(ldmc.value+cs.value+"层"+qsh.value+"寝室已经有学生入住，尚未分配，请确认！");
					return false;
				}
			}
			if (confirm("确定要保存当前的寝室分配吗?")) {
				saveUpdate('/xgxt/gyglQsgl.do?method=qssdfpdDetail&doType=save','');
			}
		}
	
		//显示楼栋层信息	
		function showCsInfo(obj,id){
			if(obj.className=="table_up"){
				obj.className="table_down";
				obj.parentNode.parentNode.className="cur-tr";
				$(id).style.display="none";
			}else{
				obj.className="table_up";
				obj.parentNode.parentNode.className="";
				$(id).style.display="";
			}
		}
		
		//显示楼栋寝室信息	
		function showQsInfo(obj,id,lddm,cs){
			if(obj.className=="up"){
				obj.className="down";
				obj.parentNode.parentNode.className="cur-tr";
				$(id).style.display="none";
			}else{
				obj.className="up";
				obj.parentNode.parentNode.className="";
				$(id).style.display="";
			}

			creatQsInfo(lddm,cs);
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>寝室分配 - 手动分配</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglQsgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="userStatus" value="${userStatus }"/>
			
			<logic:notEmpty name="ssbh">
				<logic:iterate name="ssbh" id="ss">
					<input type="hidden" name="primarykey_checkVal" value="${ss }"/>
				</logic:iterate>
			</logic:notEmpty>
			
			<!-- 从属关系：1（校区--园区--楼栋） -->
			<logic:equal name="csgx" value="1">
				<input type="hidden" id="qsjb" value="4"/>
			</logic:equal>
			<!-- 从属关系：2（校区--楼栋） -->
			<logic:equal name="csgx" value="2">
				<input type="hidden" id="qsjb" value="3"/>
			</logic:equal>
			<!-- 从属关系：3（园区--楼栋） -->
			<logic:equal name="csgx" value="3">
				<input type="hidden" id="qsjb" value="3"/>
			</logic:equal>
			<!-- 从属关系：4（楼栋） -->
			<logic:equal name="csgx" value="4">
				<input type="hidden" id="qsjb" value="2"/>
			</logic:equal>
			<!-- 宿舍编号 -->
			<logic:notEmpty name="ssbh">
				<logic:iterate name="ssbh" id="ss">
					<input type="hidden" name="ssbh" value="${ss }"/>
				</logic:iterate>
			</logic:notEmpty>
			<!-- 隐藏域 -->

			<!--标题start-->
		    <table class="formlist" style="margin-bottom:4px" >
    			<thead>
					<tr>
						<td >
							<span>
								点击可展开所隐藏的信息
								<font color="blue">
								注：分配对象为
								<logic:equal name="fpdx" value="xy"><bean:message key="lable.xb" /></logic:equal>
								<logic:equal name="fpdx" value="njxy">年级+<bean:message key="lable.xb" /></logic:equal>
								<logic:equal name="fpdx" value="njzy">年级+专业</logic:equal>
								<logic:equal name="fpdx" value="bj">班级</logic:equal>
								</font>
							</span>	
							<div class="btn">
								<logic:empty name="ldInfoList">
									<button  id="btn_bc" disabled="disabled">
										<bean:message key="lable.btn_bc_space" />
									</button>
								</logic:empty>
								<logic:notEmpty name="ldInfoList">
								<button onclick="saveQsfp()" id="btn_bc">
									<bean:message key="lable.btn_bc_space" />
								</button>
								</logic:notEmpty>
								<button onclick="fhsdfp()" id="btn_fh">
									<bean:message key="lable.btn_fh_space" />
								</button>
							</div>
						</td>
					</tr>
				<thead>
			</table>
		    <div class="leftframe04">
				<%@ include file="/comm/bmTree.jsp"%>	
		    </div>
			<div class="rightframe04">
				<table width="100%"><tr><td>
				<%int n=0;%>
				<logic:notEmpty name="ldInfoList">
					<logic:iterate name="ldInfoList" id="ldInfo">
						<logic:equal name="ldInfo" property="Lv" value="ld">
							<%n++;%>
							<logic:iterate name="ldInfo" property="infoList" id="ld">
								<div class="table_updown">
									<a href="#" class="table_down" onclick="showCsInfo(this,'tb_cs_${ld.lddm }');return false">
										${ld.xqmc }&nbsp;&nbsp;${ld.yqmc }&nbsp;&nbsp;${ld.ldmc }
									</a>
								</div>
								<!-- 层数 -->
								<logic:iterate name="ldInfoList" id="csInfo">
									<logic:equal name="csInfo" property="Lv" value="cs">
										<table width="100%"  border="0" class="formlist" id="tb_cs_${ld.lddm }" style="display:none">
											<logic:iterate name="csInfo" property="infoList" id="cs">
												
												<logic:equal name="cs" property="lddmKey" value="${ld.lddmKey }">
													
													<thead>
														<tr>
															<th width="20%">
																<a href="#" class="down" onclick="showQsInfo(this,'tb_qs_${ld.lddm }_${cs.dm }','${ld.lddm }','${cs.dm }');return false">
																	${cs.mc }<bean:message key="lable.cs" />
																</a>
															</th>
															<th colspan="5">
														
															</th>
														</tr>
													</thead>
													<tbody id="tb_qs_${ld.lddm }_${cs.dm }" style="display:none;">
														<tr>
															<td colspan="6">
																<div id="div_${ld.lddm }_${cs.dm }" style="width: 100%">
																	
																</div>
															</td>
														</tr>		
													</tbody>
														</tr>
													</thead>
												</logic:equal>
											</logic:iterate>
										</table>
									</logic:equal>
								</logic:iterate>
								<!-- 层数 end-->
							</logic:iterate>
						</logic:equal>
					</logic:iterate>
				</logic:notEmpty></td></tr></table>
			</div>			
			<%if(n==0){ %>
					<div class="page_prompt">
					<div class="page_promptcon">
					  <h3>温馨提示：</h3>
					  <p>没有可供分配的寝室！</p>
					</div>
					<p>&nbsp;</p>
					</div>
			<%}%>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>