<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：今天中五百万 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/moveMonadism.js"></script>
		<script language="javascript" defer="defer">
		DragedTable('canmTable');
		var xsfs="";
		//调整显示顺序
		rowOnClick($("nomTable").getElementsByTagName("tr")[0]);
		xsmkChecked($("nomTable").getElementsByTagName("tr")[0]);
		function tzxssx(sftz){
			if("yes"==sftz){
				$("canMove").style.display="";
				$("noMove").style.display="none";
			}else if("no"==sftz){
				$("canMove").style.display="none";
				$("noMove").style.display="";
				
				var html="";
				html+="<table id='nomTable' width='100%'>";
				html+=$("canmTable").innerHTML;
				html=html.replaceAll("<TR>","<TR  style='cursor:hand' onclick='rowMoreClick(this);xsmkChecked(this)'>");
				html=html.replaceAll("_can","_no");
				
				html+="</table>";
				$("noMove").innerHTML=html;
			}
		}
		
		function changetXssxtz(sftz){
			$("xssxtz").value=sftz;
			tzxssx(sftz);
		}
		
		function xsmkChecked(obj){
			//显示模块
			var xsmk=curr_row.getElementsByTagName('input')[0].value;
			
			xsfs=curr_row.getElementsByTagName('input')[1].value;
			$("checkEname").value=xsmk;
			checkXxypz();
		}
		
		
		function checkXxypz(){
			var checkEname=$("checkEname").value;
			var enameArr=document.getElementsByName("enameArr");
			var xslxdmArr=document.getElementsByName("xslxdmArr");
			var xslxmcArr=document.getElementsByName("xslxmcArr");
			var picnameArr=document.getElementsByName("picnameArr");
			var bzArr=document.getElementsByName("bzArr");
			var xslxHtml="";
			xslxHtml+="<input type='hidden'  id='xslx_"+checkEname+"' value='"+xsfs+"' >";
			
			if(checkEname!="xsxx"){
			xslxHtml+="<input type='radio' name='xslx_"+checkEname+"' id='xslx_"+checkEname+"_0' value='0' onclick='changeValue(this);changeTap()' ";
			if(xsfs=="0"){
				xslxHtml+="checked";
			}
			xslxHtml+=">不显示";
			if(xsfs=="0"){
				//备注的加载
				$("span_tp").innerHTML="<img src='/xgxt/pictures/xsxx/xxypz/nothing.jpg' />";
				//备注的加载
				$("span_bz").innerHTML="不在学生详细信息页面显示该模块。";
			}
			}
			var blog=true;
			
			for(i=0;i<enameArr.length;i++){
				
				if(checkEname==enameArr[i].value){
					
					if(blog){
						
						xslxHtml+="<input type='radio' name='xslx_"+checkEname+"' id='xslx_"+checkEname+"_"+i+"' value='"+xslxdmArr[i].value+"' onclick='changeValue(this);changeTap()'";
						
						if(xsfs==xslxdmArr[i].value){
							xslxHtml+="checked";
						}
						xslxHtml+=" >"+xslxmcArr[i].value;
						//图片的加载
						$("span_tp").innerHTML="<img src='"+picnameArr[i].value+"'  />";
						//备注的加载
						$("span_bz").innerHTML=bzArr[i].value;
						blog=false;
					}else{
						xslxHtml+="<input type='radio' name='xslx_"+enameArr[i].value+"'id='xslx_"+checkEname+"_"+i+"'  value='"+xslxdmArr[i].value+"' onclick='changeValue(this);changeTap()'";
						if(xsfs==xslxdmArr[i].value){
							xslxHtml+="checked";
						}
						xslxHtml+=">"+xslxmcArr[i].value;
						//图片的加载
						$("span_tp").innerHTML="<img src='"+picnameArr[i].value+"' />";
						//备注的加载
						$("span_bz").innerHTML=bzArr[i].value;
					}
				}
			}
			
			$("span_xslx").innerHTML=xslxHtml;
			
		}
		
		function changeValue(obj){
			var checkEname=$("checkEname").value;
			$("xslx_"+checkEname).value=obj.value;
			$("sfxs_can_"+checkEname).value=obj.value;
			$("sfxs_no_"+checkEname).value=obj.value;
		}
		
		//改变选择
		function changeTap(){
			
			var checkEname=$("checkEname").value;
			var enameArr=document.getElementsByName("enameArr");
			var xslxdmArr=document.getElementsByName("xslxdmArr");
			var xslxmcArr=document.getElementsByName("xslxmcArr");
			var picnameArr=document.getElementsByName("picnameArr");
			var bzArr=document.getElementsByName("bzArr");
			
			var bool=false;
			for(i=0;i<enameArr.length;i++){
				if(checkEname==enameArr[i].value
					&& $("xslx_"+checkEname).value==xslxdmArr[i].value
					&& xslxdmArr[i].value!="0"){
					
					bool=true;
					$("span_tp").innerHTML="<img src='"+picnameArr[i].value+"'  />";
					$("span_bz").innerHTML=bzArr[i].value;
					break;
				}
			}
			
			if(!bool){
				//图片加载
				$("span_tp").innerHTML="<img src='/xgxt/pictures/xsxx/xxypz/nothing.jpg' />";
				//备注的加载
				$("span_bz").innerHTML="不在学生详细信息页面显示该模块。";
			}
		}
		
		function checkFirst(){
			if($("num_0")){
				$("num_0").click();
			}
		}
		
		function saveXxypz(){
			refreshForm('/xgxt/sjyJcsjsz.do?method=xxypzManage&doType=save');
		}
		</script>
	</head>
	<body onload="checkXxypz();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->

		<html:form action="/sjyJcsjsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="checkEname" id="checkEname" value="xsxx"/>
			<logic:iterate name="xxypzList" id="xxypz">
				<input type="hidden" name="enameArr" id="ename" value="${xxypz.ename}"/>
				<input type="hidden" name="xslxdmArr" id="xslxdm" value="${xxypz.xslxdm}"/>
				<input type="hidden" name="xslxmcArr" id="xslxmc" value="${xxypz.xslxmc}"/>
				<input type="hidden" name="picnameArr" id="picname" value="${xxypz.picname}"/>
				<input type="hidden" name="bzArr" id="bz" value="${xxypz.bz}"/>
			</logic:iterate>

			<div class="tab">
				<table class="formlist" width="100%" border="0">
					<tbody>
					<tr>
						<td>
							<table width="100%" border="0" class="formlist">
								<!-- 可移动的TABLE -->
								<tr>
									<td colspan="2">
										<input type="hidden" name="xssxtz" id="xssxtz" value="不调整" />
										调整顺序
										<input type="radio" name="tzsx" value="yes"
											onclick="changetXssxtz('yes')" />
										调整
										<input type="radio" name="tzsx" value="no" checked
											onclick="changetXssxtz('no')" />
										不调整
									</td>
								</tr>
								<tr>
									<td >
										<div id="canMove" style="display:none;height:440px" >
											<table id="canmTable" width="100%" border="0" class="formlist">
												<logic:iterate name="xsxmList" id="xsxm" indexId="index">
													<logic:equal name="index" value="0" >
													<tr >
														<td class="noMove">
															${xsxm.cname}
															<input type="hidden" name="xsxm_can" id="xsxm_can_${index}" value="${xsxm.ename}"/>
															<input type="hidden" name="sfxs_can" id="sfxs_can_${xsxm.ename}" value="${xsxm.sfxs}"/>
														</td>
													</tr>
													</logic:equal>
													<logic:notEqual name="index" value="0" >
													<tr >
														<td>
															${xsxm.cname}
															<input type="hidden" name="xsxm_can" id="xsxm_can_${index}" value="${xsxm.ename}"/>
															<input type="hidden" name="sfxs_can" id="sfxs_can_${xsxm.ename}" value="${xsxm.sfxs}"/>
														</td>
													</tr>
													</logic:notEqual>
												</logic:iterate>
											</table>
										</div>
										<!-- 不可移动的TABLE -->
										<div id="noMove" style="height:440px" >
											<table id="nomTable" width="100%" border="0" class="formlist">
												<logic:iterate name="xsxmList" id="xsxm" indexId="index">
													<tr  style='cursor:hand'  id="num_${index}"onclick='rowOnClick(this);xsmkChecked(this)'>
														<td>
															${xsxm.cname}
															<input type="hidden" name="xsxm_no" id="xsxm_no_${index}" value="${xsxm.ename}" />
															<input type="hidden" name="sfxs_no" id="sfxs_no_${xsxm.ename}" value="${xsxm.sfxs}"/>
														</td>
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
									<td style="width:79%">
										<table id="xxypz" width="100%" border="0" class="formlist">
											<tr>
												<td >
													<span id="span_xslx"></span>
												</td>
											</tr>
											<tr>
												<td style="height:300px">
												<div style='width:550px;height:298px;overflow-y:auto;overflow-x:auto'>
													<span id="span_tp"></span>
												</div>
												</td>
											</tr>
											<tr>
												<td style="height:100px">
													<span id="span_bz"></span>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="right">
								<button type="button" onclick="saveXxypz()">保存</button>
							</td>
						</tr>
					</tfoot>
				</table>
		</div>
		</html:form>
	</body>
</html>
