<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
      }
    }
    
   
	
	function plsz(){
	
		var pkArr=document.getElementsByName("pk");
		
		for(var i=0;i<pkArr.length;i++){
			if(pkArr[i].checked && $("xyyrdwsh_"+i+"_yes")){
				
				 if($("yrdwshsz_1").checked){
				 	$("xyyrdwsh_"+i+"_no").checked=true;
				 }else {
				 	$("xyyrdwsh_"+i+"_yes").checked=true;
				 }
			}
			
		}
		hiddenMessage(true,true);
	}
	
	function saveBc(){
		
		var pkArr=document.getElementsByName("pkV");
		
		for(var i=0;i<pkArr.length;i++){
			if($("xyyrdwsh_"+i+"_yes")){
				 if($("xyyrdwsh_"+i+"_no").checked){
				 	
				 	$("xyyrdwsh_hid_"+i).value="否";
				 }else {
				 	$("xyyrdwsh_hid_"+i).value="是"
				 }
			}
			
		}
		refreshForm("/xgxt/post_stu_zhztkz.do?method=auditingControl&doType=save");
	}
	
	function showYrdwsh(){
		
		viewTempDiv("用人单位审核","div_yrdwsh",350,200);
	}
	
	function changeValue(obj,i){
		
		$("xyyrdwsh_hid_"+i).value=obj.value;
	}
	
	function resetSjkj(){
		if($("kssj") && $("jssj")){
			jQuery("#kssj").attr("readonly",false);
			jQuery("#jssj").attr("readonly",false);
		}
	}
	
	function setReadOnly(){
		if($("kssj") && $("jssj")){
			jQuery("#kssj").attr("readonly",true);
			jQuery("#jssj").attr("readonly",true);
		}
	}
</script>
	<body onload="">
		<center>

			<html:form action="/qgzxLogic.do" method="post">
				<input type="hidden" name="message" id="message" value="${message }"/>
				<input type="hidden" name="doType" id="doType" value="${doType }"/>
				<div class="tab_cur" id="jd">
					<p class="location">
						<em>您的当前位置:</em><a>${title }</a>
					</p>
				</div>
				
				<div class="toolbox">
					<!-- 按钮 -->
					<logic:equal value="yes" name="writeAble">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" onclick="showYrdwsh()" class="btn_sz"> 批量设置 </a>
								</li>
								<li>
									<a href="#" onclick="saveBc()" class="btn_ccg"> 保存 </a>
								</li>
							</ul>
						</div>
					</logic:equal>


					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/post_stu_zhztkz.do')">
												查 询
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn_cz" id="btn_cz" onclick="resetSjkj();searchReset();setReadOnly()">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th>
										岗位
									</th>
									<td>
										<html:text property="gwdm" styleId="gwdm" />
									</td>
									<th>
										申报时间
									</th>
									<td>
										<!-- 开始时间 -->
										<html:text property="kssj" styleId="kssj" size="18"
											onclick="return showCalendar('kssj','yyyy-MM-dd HH:mm:ss');"
											readonly="true"
											 />

										-
										<!-- 结束时间 -->
										<html:text property="jssj" styleId="jssj"
											onclick="return showCalendar('jssj','yyyy-MM-dd HH:mm:ss');"
											size="18" readonly="true"/>

									</td>
									<th>
										是否需要用人单位审核
									</th>
									<td>
										<html:select property="xyyrdwsh" styleId="xyyrdwsh">
											<html:option value=""></html:option>
											<html:option value="是">是</html:option>
											<html:option value="否">否</html:option>
										</html:select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">

						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<div align="center">
											<input type="checkbox" name="gwmc" value="all"
												onclick="chec()" />
										</div>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="single('qgzxLogic.do?method=showModify')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>" />
											<input type="hidden" name="pkV"
												value="<bean:write name="v"/>" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1" length="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
										<td align="left">
											<logic:equal name="v" value="是">
												<input type="radio" name="xyyrdwsh_${index}"
													id="xyyrdwsh_${index}_yes" value="是" checked
													onclick="changeValue(this,'${index}')" />是
												<input type="radio" name="xyyrdwsh_${index}"
													id="xyyrdwsh_${index}_no" value="否"
													onclick="changeValue(this,'${index}')" />否
											</logic:equal>
											<logic:equal name="v" value="否">
												<input type="radio" name="xyyrdwsh_${index}"
													id="xyyrdwsh_${index}_yes" value="是"
													onclick="changeValue(this,'${index}')" />是
												<input type="radio" name="xyyrdwsh_${index}"
													id="xyyrdwsh_${index}_no" value="否" checked
													onclick="changeValue(this,'${index}')" />否
											</logic:equal>
											<input type="hidden" name="xyyrdwsh_hid"
												id="xyyrdwsh_hid_${index}" value="${v}" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</logic:notEmpty>
				</div>
				<div id="div_yrdwsh" style="display:none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span> 用人单位审核</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>
										是否需要用
										<br />
										人单位审核
									</th>
									<td>
										<input type="radio" name="yrdwshsz" id="yrdwshsz_0" checked />
										是
										<input type="radio" name="yrdwshsz" id="yrdwshsz_1" />
										否
									</td>
								</tr>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="btn">
											<button type="button" onclick="plsz()">
												确 定
											</button>
											<button type="button" onclick="hiddenMessage(true,true);">
												关 闭
											</button>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<logic:notEmpty name="result">
					<logic:equal value="true" name="result">
						<script>
						alert("操作成功！");
						Close();
										
					</script>
					</logic:equal>
					<logic:equal name="result" value="false">
						<script>
						alert("操作失败!");
					</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
		<!-- 请等待 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 请等待 end-->
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

