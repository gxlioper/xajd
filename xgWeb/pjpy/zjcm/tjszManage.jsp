<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function searchTj(){
		var lx = $("lx").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var jxjdm = $("jxjdm").value;
		
		if(xn == ""){
			alert("学年不能为空！！");
			return false;
		}
		
		if(xq == ""){
			alert("学期不能为空！！");
			return false;
		}
		
		if(jxjdm == ""){
			if("jxj" == lx){
				alert("请确定奖学金类型！！")
				return false;
			}else if("zhcpjxj" == lx){
				alert("请确定奖学金！")
				return false;
			}else{
				alert("请确定荣誉称号类型！！")
				return false;
			}
		}
		allNotEmpThenGo('/xgxt/zjcmPjpy.do?method=tjszManage')
	}
	
	function saveTj(){
		var lx = $("lx").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var jxjdm = $("jxjdm").value;
		var tjzd = $("tjzd").value;
		var tjlx = $("tjlx").value;
		var tjz = $("tjz").value;
		var tsgs = $("tsgs").value;
		var bjlx = $("bjlx").value;
		
		if(xn == ""){
			alert("学年不能为空！！");
			return false;
		}
		
		if(xq == ""){
			alert("学期不能为空！！");
			return false;
		}
		
		if(jxjdm == ""){
			if("jxj" == lx){
				alert("请确定奖学金类型！！")
				return false;
			}else{
				alert("请确定荣誉称号类型！！")
				return false;
			}
		}
		
		if(tjzd == "" || tjlx == ""){
			alert("请确定所设置条件完整！！")
			return false;
		}
		
		if(tsgs != "yw" && tjz ==""){
			alert("请确定所设置条件完整！！")
			return false;
		}
		if(tsgs == "wlk" && bjlx ==""){
			alert("请确定班级类型！！")
			return false;
		}
		
		saveUpdate('/xgxt/zjcmPjpy.do?method=tjszManage&doType=save','');
	}
	
	function chTjlx(){
		dwr.engine.setAsync(false);
		var tjzd = $("tjzd").value;
		var tj = $("tj").value;
		
		if(tjzd != ""){
			var tableName="jxjtjzdb";
			var colList =["tsgs"];
			var pk = "zdmc";
			var pkValue = tjzd;
			getPjpyInfo.getPjpyInfo(tableName, pk, pkValue,colList,function(data){
				if(data!=null){
					if(data.tsgs !="" && data.tsgs != null){
						$("tsgs").value = data.tsgs;
					}else{
						$("tsgs").value = "";
					}
				}
			});
			var szlx = $("tsgs").value;
			var objId = "tjlx"
			if("%" == szlx){
				$("tjz").style.display = "";
				$("bfh").style.display = "";
				$("bjlx").value = "";
				$("wlk").style.display = "none";
				getPjpyInfo.getSelectList("tjlx",function(data){
					if(data!=null){
						DWRUtil.removeAllOptions(objId);
						DWRUtil.addOptions(objId,data,"en","cn");
					}
				});
			}else if("wlk" == szlx){
				$("tjz").style.display = "";
				$("bfh").style.display = "none";
				$("wlk").style.display = "";
				getPjpyInfo.getSelectList("tjlx",function(data){
					if(data!=null){
						DWRUtil.removeAllOptions(objId);
						DWRUtil.addOptions(objId,data,"en","cn");
					}
				});
			}else if("yw" == szlx){
				$("bfh").style.display = "none";
				$("bjlx").value = "";
				$("wlk").style.display = "none";
				$("tjz").style.display = "none";
				getPjpyInfo.getSelectList("ywlx",function(data){
					if(data!=null){
						DWRUtil.removeAllOptions(objId);
						DWRUtil.addOptions(objId,data,"en","cn");
					}
				});	
			}else{
				$("bfh").style.display = "none";
				$("tjz").style.display = "";
				$("bjlx").value = "";
				$("wlk").style.display = "none";			
				getPjpyInfo.getSelectList("tjlx",function(data){
					if(data!=null){
						DWRUtil.removeAllOptions(objId);
						DWRUtil.addOptions(objId,data,"en","cn");
					}
				});
			}
			
			for(var i = 0;i < $(objId).options.length; i++){
				if($(objId).options[i].value == tj){
					$(objId).options[i].selected = true;
					return true;
				}
			}
		dwr.engine.setAsync(true);
		}
		
	}
	
	function setPxdj(){
		if ('pxdj'==$('tjzd').value) {
			$('pxdj').style.display='';
			$('tjz').style.display='none';
			$('tjz').value='';
		} else {
			$('pxdj').style.display='none';
			$('tjz').style.display='';
			$('tjz').value='';
		}
	}
	
	function changePagePj(obj,num){
	    	obj.parentNode.className = 'ha';
	    	
	    	var elementLi = ele('ul1').children;
	    	for(li in elementLi){
	    		if(li.id != obj.id){
	    			li.className = 'ha';
	    		}
	    	}
			//id = id.replace("ul", "");
			refreshForm('zjcmPjpy.do?method=tjszManage&lx=' + obj.id)
	    }
	   function loadchangePj(){
			var table = val('firstCard');
			var tab = table != '' ? table : ele('ul1').children[0].children[0].id;
			ele(tab).parentNode.className = "ha";
			document.getElementById("firstCard").value=tab;
	    }
	</script>
</head>
	<body onload="chTjlx();loadchangePj()">
		<html:form action="/zjcmPjpy" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="tj" name="tj" value="${tjlx }" />
			<input type="hidden" id="lx" name="lx" value="${lx }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
			<input type="hidden" id="needXq" name="needXq" value="${needXq }" />
			<input type="hidden" id="tsgs" name="tsgs" value="" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="firstCard" value="${lx}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="compTab" id="card" style="width: 100%">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<logic:notEmpty name="pages">
								<logic:iterate id="card" name="pages" scope="request">
									<li>
										<a href="#" onclick="changePagePj(this);$('go').value='';" id="${card.en}">
											<span>${card.cn}</span>
										</a>
									</li>									
								</logic:iterate>
							</logic:notEmpty>							
						</ul>				
					</div>
				</div>
				<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">						
						<li> <a href="#" onclick="sumitInfo('/xgxt/zjcmPjpy.do?method=tjszManage','del');" class="btn_sc">删除</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>						
					  </logic:equal>	
					  <li> <a href="#" onclick="dataExport();" class="btn_dc">导出数据</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>	
						  <td>
						  <!--操作开关提示-->
							<logic:notEmpty name="yhInfo">
								<font color="red">提示：${ yhInfo}</font>
							</logic:notEmpty>
							<!--end操作开关提示-->
						  </td>					  
				          <td colspan="5">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="searchTj()">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>学年</th>
						<td>
							<html:select property="xn" style="width:180px">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<!-- 学校评奖需要学期学期 -->
						<logic:equal name="needXq" value="true">
						<th>学期</th>
						<td>
							<html:select property="xq" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
						</logic:equal>

						<!-- 学校评奖不需要学期学期 -->
						<logic:equal name="needXq" value="false">
						<th></th>
						<td>	
							<html:hidden property="xq"/>	
						</td>
						</logic:equal>
						
						<logic:equal name="lx" value="jxj">
						<th>奖学金</th>
						<td>
							<logic:equal value="yes" name="all" >
								<html:select property="jxjdm" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="dm" labelProperty="mc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="yes" name="all">
								<html:select property="jxjdm" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc" />
								</html:select>
							</logic:notEqual>
						</td>
					  </logic:equal>
					
					  <logic:equal name="lx" value="zhcpjxj">
						<th>奖学金</th>
						<td>
							<logic:equal value="yes" name="all" >
								<html:select property="jxjdm" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="dm" labelProperty="mc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="yes" name="all">
								<html:select property="jxjdm" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc" />
								</html:select>
							</logic:notEqual>
						</td>
					  </logic:equal>
					  <logic:equal name="lx" value="rych">
						<th>荣誉称号</th>
						<td>
							<html:select property="jxjdm" style="width:180px">
								<html:options collection="rychList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					  </logic:equal>
					  </tr>	
					  <tr id="wlk" style="display:none">
						<th>班级类型</th>
						<td>
							<html:select property="bjlx" style="width:180px">
								<html:options collection="wlkList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th></th>
						<td>		
						</td>
						<th></th>
						<td>
							
						</td>								
					  </tr>
					  <tr>
						<th>条件</th>
						<td colspan="5"> 
							<html:select property="tjzd" style="" onchange="chTjlx();setPxdj();" >
								<html:option value="">----请选择----</html:option>
								<html:options collection="tjList" property="dm" labelProperty="mc" />
							</html:select>	
							&nbsp;&nbsp;
							<html:select property="tjlx" style="" onchange="">
								<html:options collection="tjlxList" property="en" labelProperty="cn" />
							</html:select>	
							<html:text property="tjz"
								onkeyup="value=value.replace(/[^\d|.]/g,'') "
								maxlength="5" style="width:120px;ime-mode:disabled"/>
							<span id="bfh" style="display:none">%</span>
							
							<select id="pxdj" style="display:none" onchange="$('tjz').value=this.value">
								<option></option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
							</select>
							<logic:empty name="yhInfo">
<!--							 <div class="btn">							  -->
								<button id="search_go"
									onclick="saveTj();">
									添加
								</button>
<!--				            </div>-->
							</logic:empty>	
							
						</td>
					  </tr>		  
					  </tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			       <td width="20px">
						<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<td width="20px">
								<input type="checkbox" id="checkVal" name="checkVal" 
								value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
				  </logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyTyForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
			</div>	
			</logic:empty>
				
		</html:form>

		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		
	</body>
</html>
