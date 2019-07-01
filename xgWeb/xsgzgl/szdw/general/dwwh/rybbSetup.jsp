<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(zgh){ 
		
			jQuery.ajaxSetup({async: false});
			
			createJzgOption(zgh);
			searchRs();
			titleNjxyzybj();
			
			jQuery.ajaxSetup({async: true});
		}
		
		//创建教职工Option
		function createJzgOption(zgh){ 
		
			var url = "szdw_dwwh.do?method=createJzgOption";			
			var parameter={
				"str_zgh":jQuery("#text_zgh").val(),
				"str_xm":escape(jQuery("#text_xm").val()),
				"str_bmdm":jQuery("#select_bmdm").val(),
				"str_dbqk":jQuery("#select_dbqk").val(),
				"str_lx":jQuery("#hidden_lx").val()
			};
			
			jQuery.ajaxSetup({async:false});
			
			jQuery.post(url,
				parameter,
				function(result){
					jQuery("#select_jzg").html("");
					jQuery("#select_jzg").append(result);
					
					if(zgh != "" && zgh != null){
						jQuery("#select_jzg").val(zgh)
					}
				}
			);
		
			jQuery.ajaxSetup({async:true});
		}
		
		//查询
		function searchRs(){
			
			var zgh = jQuery("#select_jzg").val();
			
			if(zgh == "" || zgh == null){
				alertError("请在左侧教职工列表中选择一位希望编班的老师");
				return false;
			}
			
			jQuery.ajaxSetup({async:false});
			
			var url = "szdw_dwwh.do?method=searchSetup";
			
			var parameter={
				"str_zgh":jQuery("#select_jzg").val(),
				"str_njV":jQuery("#njV").val(),
				"str_xyV":jQuery("#xy").val(),
				"str_zyV":jQuery("#zyV").val(),
				"str_bjV":jQuery("#bjV").val(),
				"str_ywjs":jQuery("#select_ywjs").val(),
				"str_brdb":jQuery("#select_brdb").val(),
				"str_lx":jQuery("#hidden_lx").val()
			};
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			// 查询操作
			searchGo(url,parameter);
		
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		// 执行查询操作
		function searchGo(url,parameter){
			
			// 分页
			var currentPage = "1";
			if($("currentPage")){
				currentPage = $("currentPage").value;
			}
			
			var editPageSize = "";
			if($("editPageSize")){
				editPageSize = $("editPageSize").value;	
			}
				
			var pagesize = "";
			if($("pagesize")){
				pagesize = $("pagesize").value;
			}
			
			parameter["currentPage"]=currentPage;
			parameter["editPageSize"]=editPageSize;
			parameter["pagesize"]=pagesize;
		
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery("#div_rs").load(url,parameter,function(){
				setTimeout("setPageInfo()",500);
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				
				jQuery("#table_rs td").each(function(){
						// 行高控制
						jQuery(this).attr("height","10");
						jQuery(this).css("text-align","center");
				});

				if($("span_note")){
					var html="";
					// 带班类型
					var hid_lx=$("hidden_lx").value;
					var hid_dbs="0";
					
					if($("hid_dbs").value!=""){
						hid_dbs=$("hid_dbs").value;
					}	
					
					if(hid_lx=="fdy"){
						if(jQuery("#xxdm").val()=='12834'){
							html+="【大队长共带班级<font color='blue'>"+hid_dbs+"</font>个,标<font color='blue'>蓝色</font>的记录为已经分配班级】"
						}else{
							html+="【辅导员共带班级<font color='blue'>"+hid_dbs+"</font>个,标<font color='blue'>蓝色</font>的记录为已经分配班级】"
						}
					}else if(hid_lx=="bzr"){
					if(jQuery("#xxdm").val()=='12834'){
							html+="【中队长共带班级<font color='blue'>"+hid_dbs+"</font>个,标<font color='blue'>蓝色</font>的记录为已经分配班级】"	
						}else{
							html+="【班主任共带班级<font color='blue'>"+hid_dbs+"</font>个,标<font color='blue'>蓝色</font>的记录为已经分配班级】"	
						}
					}
					
					$("span_note").innerHTML = "查询结果&nbsp;&nbsp;"+html;
				}
			});
		}
		
		function resetFdytj(){
			
			jQuery("input,select",jQuery("#tab_fdy")).each(function(){
				
				if(jQuery(this).attr("disabled")!=true
					&& jQuery(this).css("display")!="none"){
					jQuery(this).val("");
				}
			});
		}
		
		function resetBbtj(){
			
			jQuery("input,select",jQuery("#tab_bbxx")).each(function(){
				
				if(jQuery(this).attr("disabled")!=true
					&& jQuery(this).css("display")!="none"){
					jQuery(this).val("");
				}
			});
		}
		
		
		function titleLoad(id){
			
			if(jQuery("#"+id)){
			
				jQuery("#"+id).children("option").each(function(){
					jQuery(this).attr("title",jQuery(this).text());
				});
			}
		}
		
		function titleNjxyzybj(){
		
			titleLoad('nj');
			setTimeout("titleLoad('xy')",500);
			setTimeout("titleLoad('zy')",500);
			setTimeout("titleLoad('bj')",500);
		}
		</script>
	</head>
	<body onload="onShow('')" ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p><%--
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		--%></div>
		<!-- 标题 end-->
		
		<html:form action="/general_szdw" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_lx" value="${lx }"/>
			<input type="hidden" id="hidden_zgh" value="${zgh }"/>
			<input type="hidden" id="hidden_nj" value="${nj }"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
	
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="goDwwh();return false;">
								返回
							</a>
						</li>
						<li>
							<a href="#" class="btn_ccg" onclick="saveFpbj();return false;">
								保存编班
							</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="disfrockFpbj();return false;">
								撤销编班
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
			</div>
				
			<!-- 项目基本情况 -->
			<table class="searchtab" border="0" align="center" style="width: 100%">
				<tr>
					<td style="vertical-align: top">
						<!-- 人员过滤条件 begin -->
						
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th>
										<span>教职工过滤条件</span>
									</th>
								</tr>
							</thead>
						</table>
						
						<table id="tab_fdy">
							<tr>
								<td>
									职工号
								</td>
								<td>
									<input type="text" id="text_zgh" value="${rs.zgh }" style="width:120px"/>
								</td>
							</tr>
							<tr>
								<td>
									姓名
								</td>
								<td>
									<input type="text" id="text_xm" value="${rs.xm }" style="width:120px"/>
								</td>
							</tr>
							<tr>
								<td>
									所属部门
								</td>
								<td>
									<logic:equal name="userType" value="xy"  >
									<select style="width:120px" disabled="disabled" value="${userDep }">
										<logic:notEmpty name="bmpyList">
											<option value=""></option>
											<logic:iterate name="bmpyList" id="bm">
												<logic:equal name="bm" property="bmdm" value="${rs.bmdm}">
													<option value="${bm.bmdm }" title="${bm.bmmc }" selected="selected">${bm.bmpy }${bm.bmsx }</option>
												</logic:equal>
												<logic:notEqual name="bm" property="bmdm" value="${rs.bmdm}">
												<option value="${bm.bmdm }" title="${bm.bmmc }">${bm.bmpy }${bm.bmsx }</option>
												</logic:notEqual>				
											</logic:iterate>
										</logic:notEmpty>
									</select>
									<input type="hidden" name="select_bmdm"  id="select_bmdm" value="${userDep }" style="display: none" />
									</logic:equal>
									
									<logic:notEqual name="userType" value="xy">
									<select id="select_bmdm" style="width:120px">
										<logic:notEmpty name="bmpyList">
											<option value=""></option>
											<logic:iterate name="bmpyList" id="bm">
												<logic:equal name="bm" property="bmdm" value="${rs.bmdm}">
													<option value="${bm.bmdm }" title="${bm.bmmc }" selected="selected">${bm.bmpy }${bm.bmsx }</option>
												</logic:equal>
												<logic:notEqual name="bm" property="bmdm" value="${rs.bmdm}">
													<option value="${bm.bmdm }" title="${bm.bmmc }">${bm.bmpy }${bm.bmsx }</option>
												</logic:notEqual>				
											</logic:iterate>
										</logic:notEmpty>
									</select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td>
									带班情况
								</td>
								<td>
									<select id="select_dbqk" style="width:120px">
										<option></option>
										<option value="yes">已带班</option>
										<option value="no">未带班</option>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2">								
									<div class="btn">
										<button type="button" onclick="createJzgOption()">
											查 询
										</button>
										<button type="button" onclick="resetFdytj();createJzgOption()">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</table>
						<!-- 人员过滤条件 end -->
						
						<table>
							<tr>
								<td>
									<select id="select_jzg" size="25" style="width:100%" onchange="searchRs();">
										<option></option>
									</select>
								</td>
							</tr>
						</table>
					</td>
					<td style="vertical-align: top">
					
						<!-- 部门过滤条件 begin  -->
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th>
										<span>班级过滤条件</span>
									</th>
								</tr>
							</thead>
						</table>
									
						<table align="center" class="searchtab" id="tab_bbxx">
							<thead>
								<tr>
									<th>年级</th>
									<td>
										<html:select property="nj" style="width: 120px" 
											onchange="initZyList();initBjList();$('njV').value=this.value;$('zyV').value='';$('bjV').value='';titleNjxyzybj()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
									</td>
									<th>院系</th>
									<td>
										<logic:equal name="userType" value="xy">
											<html:select property="xydm" style="width: 120px" disabled="true" value="${userDep }"
												onchange="initZyList();initBjList();$('xyV').value=this.value;$('zyV').value='';$('bjV').value='';titleNjxyzybj()">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
											<input type="text"  property="xydm" id="xy" value="${userDep }" style="display:none"/>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<html:select property="xydm" style="width: 120px" styleId="xy" 
												onchange="initZyList();initBjList();$('xyV').value=this.value;$('zyV').value='';$('bjV').value='';">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</logic:notEqual>
									</td>
									<th>专业</th>
									<td>
										<html:select property="zydm" style="width: 120px" styleId="zy" 
											onchange="initBjList();$('zyV').value=this.value;$('bjV').value='';titleNjxyzybj()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>班级</th>
									<td>
										<html:select property="bjdm" style="width:120px" styleId="bj"
											onchange="$('bjV').value=this.value">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th>有无${lxmc }</th>
									<td>
										<select id="select_ywjs" style="width:120px">
											<option value=""></option>
											<option value="yes">有</option>
											<option value="no">无</option>
										</select>
									</td>
									<th>是否有带班</th>
									<td>
										<select id="select_brdb" style="width:120px">
											<option value=""></option>
											<option value="yes">是</option>
											<option value="no">否</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<div class="btn">
						              		<button type="button" id="search_go"  onclick="searchRs();return false;">查 询</button>
						              		&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn_cz" id="btn_cz" onclick="resetBbtj()">
												重 置
											</button>
						            	</div>
									</td>
								</tr>
							</thead>
						</table>
						<!-- 部门过滤条件  end  -->
						
						<!-- 结果集 begin -->
						<div id="div_rs" style="height:400px;width:100%;overflow-x:hidden;overflow-y:auto;">
						</div>
						<!-- 结果集 end -->			
						<!--分页显示-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"/>
						<script type="text/javascript">
								$('choose').className="hide";
						</script>
					</td>
				</tr>
			</table>
			
			<!-- 教职工信息Div begin -->
			<div id="div_jzgxx" style="display:none">
				
			</div>
			<!-- jiaozhigong信息Div end -->
					
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>