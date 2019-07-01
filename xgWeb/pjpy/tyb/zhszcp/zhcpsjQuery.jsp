<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/qtsjFunction.js"></script>
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript">	  
	function loadchange(){
			var table = val('bm');
			var tab = table != '' ? table : ele('ul1').children[0].children[0].id;
			ele(tab).parentNode.className = "ha";
			document.getElementById("bm").value=tab;
	    }
	    
	    function changePage(obj, num){
	    	obj.parentNode.className = 'ha';
	    	
	    	var elementLi = ele('ul1').children;
	    	for(li in elementLi){
	    		if(li.id != obj.id){
	    			li.className = 'ha';
	    		}
	    	}
			//id = id.replace("ul", "");
			$('bm').value = $('bm' + num).value;
			
			if ($(obj.name+'bz')) {
	    		$('disli').value = $(obj.name+'bz').value;
	    	}
			refreshForm('pjpy_tyb_zhszcpSjwh.do?dmlb=' + val('dmlb') +'&dm=' + val('dm') + "&bm=" + $('bm').value);
	    }
	    
	    
	    //不需要维护的项目要屏蔽掉增，删，改，导入按钮
	    function disLi() {
	    	var disli = $('disli').value;
	    	if (disli == null || disli == '') {
				//如果为空则获取第一个li的值
	    		disli = '';
	    	}
	    	if (disli == '0') {
	    		$('lizj').style.display = 'none';
	    		$('lixg').style.display = 'none';
	    		$('lisc').style.display = 'none';
	    		$('lidr').style.display = 'none';
	    	} else {
	    		$('lizj').style.display = '';
	    		$('lixg').style.display = '';
	    		$('lisc').style.display = '';
	    		$('lidr').style.display = '';
	    	}
	    }
</script>
</head>
<body onload="xyDisabled('xy');loadchange();disLi();">
	<html:form action="/pjpyTybZhszcp" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable" value="${realTable }" />
		<input type="hidden" name="userName" id="userName" value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="bm" name="bm" value="${bm }" />
		<input type="hidden" id="dm" name="dm" value="${dm }" />
		<input type="hidden" id="dmlb" name="dmlb" value="${dmlb }" />
		<input type="hidden" id="shjb" name="shjb" value="${shjb }" />
		<input type="hidden" id="queryCol" name="queryCol" value="xn!!xq!!nd!!nj!!xydm!!zydm!!bjdm!!fdysh!!xysh!!xxsh" />
		<input type="hidden" id="likeCol" name="likeCol" value="xh!!xm" />
		
		<input type="hidden" id="disli" name="disli" value="${disli }"/>
		
		<logic:equal value="xy" name="userType">
			<logic:notEqual value="true" name="isFdy">
			<logic:notEqual value="true" name="isBzr">
				<html:hidden property="xydm"/>
			</logic:notEqual>
			</logic:notEqual>
		</logic:equal>
		<input type="hidden" id="pt" value="pt"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="compTab" id="card" style="width: 100%">
			<div class="comp_title" id="div1">
				<ul id="ul1">
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request" indexId="num">
							<li>
								<a href="#" name="sfwh${num }" onclick="changePage(this,${num })" id="${card.bm}">
									<span>${card.mc}</span>
								</a>
								<input type="hidden" name="bm${num }" id="bm${num }" value="${card.bm}"/>
								<input type="hidden" name="sfwh${num }bz" id="sfwh${num }bz" value="${card.sfwh }"/>
							</li>	
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="pages">
						<p align="center">
							暂无记录!
						</p>
					</logic:empty>
				</ul>				
			</div>
		</div>

		<div class="toolbox">
		  <!-- 按钮 -->				  
		  <div class="buttonbox">
		    <ul>
			  <!--读写权-->
			  <logic:equal value="yes" name="writeAble">
				<logic:empty name="yhInfo">
					<!-- 如果是不需要进行数据维护的项目只能查询，不能进行可写操作 -->
				
					<li id="lizj" > <a href="#" onclick="showTopWin('pjpy_tyb_zhszcpAdd.do?bm='+$('bm').value+'&dmlb=' + val('dmlb')+'&dm='+val('dm'),840,650);" class="btn_zj">增 加</a> </li>
					<li id="lixg"> <a href="#" onclick="modiAndDel('pjpyTybZhszcp.do?method=zhszcpModi&shjb='+ val('shjb')+'&bm=' + val('bm') + '&dmlb=' + val('dmlb') + '&dm=' + val('dm') + '&pkValue=','modi','700','570');" class="btn_xg">修改</a> </li>
					<li id="lisc"> <a href="#" onclick="dataBatch('pjpy_tyb_zhszcpSjwh.do?dmlb='+ val('dmlb') +'&dm=' + val('dm') + '&bm=' + val('bm') + '&act=del');" class="btn_sc">删除</a> </li>
					<li id="lidr"> <a href="#" onclick="impAndChkDataForZdy('py_bdszb');" class="btn_dr">导入数据</a> </li>
					
					<!-- END -->
				</logic:empty>
			  </logic:equal>	
			  <li> <a href="#" onclick="ZdyDataExport('py_bdsz_bcnr');" class="btn_dc">导出数据</a> </li>
			</ul>					
		  </div>
		  <div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>					  
		          <td colspan="6">
		          	<div class="bz">
		          	<!--操作开关提示-->
					<logic:notEmpty name="yhInfo">
						<font color="red">提示：${ yhInfo}</font>
					</logic:notEmpty>
					<!--end操作开关提示-->
		          	</div>
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go"
							onclick="refreshForm('pjpy_tyb_zhszcpSjwh.do?act=qry');">
							查询
						</button>
						<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重置
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
			  <logic:equal value="nd" name="pjzq">
				<tr>
				<th>年度</th>
				<td>
					<html:select property="nd" styleId="nd" style="width:150px">
						<html:options collection="ndList" property="nd"
							labelProperty="nd" />
					</html:select>
				</td>
				<th></th>
				<td>		
				</td>
				<th></th>
				<td>
					
				</td>
			  </tr>
			</logic:equal>
			<logic:equal value="xn" name="pjzq">
				<tr>
				<th>学年</th>
				<td>
					<html:select property="xn" styleId="xn" style="width:150px">
						<html:options collection="xnList" property="xn"
							labelProperty="xn" />
					</html:select>
				</td>
				<th></th>
				<td>		
				</td>
				<th></th>
				<td>
					
				</td>
			  </tr>
			  </logic:equal>
			  <logic:equal value="xq" name="pjzq">
				<tr>
				<th>学年</th>
				<td>
					<html:select property="xn" styleId="xn" style="width:150px">
						<html:options collection="xnList" property="xn"
							labelProperty="xn" />
					</html:select>
				</td>
				<th>学期</th>
				<td>
					<html:select property="xq" styleId="xq" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm"
							labelProperty="xqmc" />
					</html:select>		
				</td>
				<th></th>
				<td>
					
				</td>
			  </tr>
			  </logic:equal>
			  
			  <tr>
				<th>年级</th>
				<td>
					<html:select property="nj" onchange="initZyList();initBjList();" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj"
							labelProperty="nj" />
					</html:select>
				</td>
				<th>学号</th>
				<td>	
					<html:text property="xh"  styleId="xh" maxlength="19"></html:text>	
				</td>
				<th>姓名</th>
				<td>
					<html:text property="xm" styleId="xm" maxlength="10"></html:text>		
				</td>
			  </tr>
			  <tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					<html:select property="xydm" styleId="xy"
						onchange="initZyList();initBjList()" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
				<th>专业</th>
				<td>
					<html:select property="zydm" styleId="zy"
						onchange="initBjList()" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"
							labelProperty="zymc" />
					</html:select>		
				</td>
				<th>班级</th>
				<td>
					<html:select property="bjdm" styleId="bj" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
					</html:select>		
				</td>
			  </tr>
			  <logic:equal value="3" name="shjb">
				<tr>
				<th>辅导员审核</th>
				<td>
					<html:select property="fdysh" styleId="fdysh" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
				<td>
					<html:select property="xysh" styleId="xysh" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>		
				</td>
				<th>学校审核</th>
				<td>
					<html:select property="xxsh" styleId="xxsh" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			  </tr>	
			  </logic:equal>
			  <logic:equal value="2" name="shjb">
				<tr>
				<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
				<td>
					<html:select property="xysh" styleId="xysh" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>		
				</td>
				<th>学校审核</th>
				<td>
					<html:select property="xxsh" styleId="xxsh" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<th></th>
				<td>
					
				</td>
			  </tr>	
			  </logic:equal>
			  <logic:equal value="1" name="shjb">
				<th>学校审核</th>
				<td>
					<html:select property="xxsh" styleId="xxsh" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<th></th>
				<td>
					
				</td>
				<tr>
				<th></th>
				<td>
						
				</td>
			  </tr>	
			  </logic:equal>				  
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
							<font color="blue">提示：单击表头可以排序; 双击可以查看详细信息！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			       <td>
					 <input type="checkbox" name="all" onclick="chec()" style="height:17.5px"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="3">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpyTybZhszcp.do?method=zhszcpModi&act=view&shjb='+ val('shjb')+'&bm=' + val('bm') + '&dmlb=' + val('dmlb') + '&dm=' + val('dm') + '&pkValue=','modi','700','600')">
							<td align="center">
								<input type="checkbox" id="pkV" name="primarykey_cbv" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td align="center">
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZhszcpwhActionForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>
			
	</html:form>

	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败!");				
			</script>
		</logic:equal>
	</logic:present>
</body>
