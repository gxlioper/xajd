<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/pjpy/pjpyFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/zhcpjxjDWR.js'></script>

<script type="text/javascript">	  	
	function changeJb() {
		var jb = $('queryequals_jb').value;
		if (jb=='1') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').disabled=true;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').disabled=true;
		} else if (jb=='2') {
		    //$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=false;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').disabled=true;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').disabled=true;
		} else if (jb=='3') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').disabled=false;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').disabled=true;
		} else if (jb=='4') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').disabled=true;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').disabled=false;
		} else {
			//$('queryequals_ejdm').value="";
			//$('queryequals_ejdm').style.disabled=true;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').style.disabled=true;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').style.disabled=true;
		}
	}
	
	function checkNull(){
		if((ele('xn') && val('xn') == '')){
			alert('请选择学年！');
			return false;
		}
		if((ele('xq') && val('xq')=='')){
			alert('请选择学期！');
			return false;
		}
		if((ele('nd') && val('nd') == '')){
			alert('请选择年度！');
			return false;
		}
		if((ele('select_jxjdm') && val('select_jxjdm') == '')){
			alert('请选择上报奖学金！');
			return false;
		}
		return true;
	}
</script>
</head>
<body onload="xyDisabled('xy');changeJb();">
	<html:form action="/pjpyTybZhcpjxj.do?method=zhcpjxjSb" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName" value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" name="pjzq" id="pjzq" value="${pjzq }" />
		<!-- 综测周期 -->
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="pjpyzq" id="pjpyzq" value="${pjpyzq }" />
		<!-- 评奖周期 -->
		<input type="hidden" id="pt" value="pt" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">					  		
<!--							<li> <a href="#" onclick="zhcpjxjsb()" class="btn_zj">上报</a> </li>-->
								<li> <a href="#" onclick="zhcpjxjsbCommit('zhcpjxjsb.do?act=sb');" class="btn_zj">上报</a> </li>

							<li> <a href="#" onclick="zhcpqxjxjsb('zhcpjxjsb.do?act=qxsb');" class="btn_sc">取消上报</a> </li>
					  </logic:equal>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="if(checkNull()){refreshForm('zhcpjxjsb.do?act=qry');}">
									查询
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
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
							<html:select property="queryequals_nd" styleId="nd">
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
								<html:select property="queryequals_xn" styleId="xn">
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
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>学期</th>
							<td>
								<html:select property="queryequals_xq" styleId="xq">
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
							<html:select property="queryequals_nj"
								onchange="initZyList();initBjList();" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学号</th>
						<td>
							<html:text property="querylike_xh" styleId="xh" maxlength="19"></html:text>		
						</td>
						<th>姓名</th>
						<td>
							<html:text property="querylike_xm" styleId="xm" maxlength="10"></html:text>
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" styleId="xy"
								onchange="initZyList();initBjList()" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>	
							<html:select property="queryequals_zydm" styleId="zy" style="width:160px"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>	
						</td>
						<th>班级</th>
						<td>
							<html:select property="queryequals_bjdm" styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>	
					  <tr>
						<th>查询</th>
						<td>
							<html:select property="queryequals_jb" styleId="queryequals_jb" onchange="changeJb();">
								<html:option value="1">综测总分</html:option>
								<html:option value="2">综测项目</html:option>
							</html:select>
							-
							<html:select property="queryequals_ejdm"
								styleId="queryequals_ejdm"
								onchange="refreshForm('zhcpjxjsb.do');">
								<html:option value=""></html:option>
								<html:options collection="ejdmList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>总分高于</th>
						<td><html:text property="querygreaterequal_zf" styleId="zf" maxlength="19" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>分</td>
						<th>总分排名前</th>
						<td><html:text property="querylessequal_zfpm" styleId="zfpm" maxlength="19" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>名</td>
					</tr>		  
					  </tbody>
					</table>
					<table width="100%" border="0">				      
						<tr>							
							<th>上报奖学金</th>
							<td>
								<html:select property="jxjdm" styleId="select_jxjdm" onchange="if(checkNull()){refreshForm('zhcpjxjsb.do?act=qry');}">
									<html:options collection="jxjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<td colspan="4" style="width:70%">
								固定条件:<font color="808080"><logic:empty name="tjList">无</logic:empty><logic:notEmpty name="tjList"><logic:iterate id="v" name="tjList">${v.tj};</logic:iterate></logic:notEmpty></font>
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
			        <td>
<!--						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>-->
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand;"
							ondblclick="">
							<td align="left">
								<input type="checkbox" id="cbv" name="primarykey_cbv"
									style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
							</td>
							<td>
<!--								<input type="hidden" name="cbv" id="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>-->
								<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="2">
								<td align="left">
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZhcpjxjActionForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
			</div>

<!--			<div id="jxjDiv" style="display:none">-->
<!--				<table width='300' class='formlist'>-->
<!--					<tbody>-->
<!--						<tr>-->
<!--							<th>奖学金</th>-->
<!--							<td>-->
<!--								<html:select property="jxjdm" styleId="select_jxjdm">-->
<!--									<html:options collection="jxjList" property="dm" labelProperty="mc"/>-->
<!--								</html:select>-->
<!--							</td>-->
<!--						</tr>-->
<!--					</tbody>-->
<!--					<tfoot>-->
<!--				      <tr>-->
<!--				        <td colspan="2">-->
<!--				          <div class="btn">-->
<!--				            	<button type="button" class='button2' onclick="zhcpjxjsbCommit('zhcpjxjsb.do?act=sb');">确定</button>-->
<!--								<button type="button" class='button2' onclick='hiddenMessage(true,true);'>关闭</button>		-->
<!--				          </div>-->
<!--				        </td>-->
<!--				      </tr>-->
<!--				    </tfoot>-->
<!--				</tbody>-->
<!--				</table>-->
<!--			</div>-->
	</html:form>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
					alert("操作成功!");
				</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败!");				
			</script>
		</logic:equal>
	</logic:present>
</body>
</html>
