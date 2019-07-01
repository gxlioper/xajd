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
<script type="text/javascript">	  
	//计算综测信息
	function zccount() {
		var pjzq = $('pjzq').value;
		var id = "";
		var msg = "";
		
		//检测必选字段是否有选中
		id = 'xn' == pjzq ? "xn" : ('xq' == pjzq ? "xn-xq" : ('nd' == pjzq ? "nd" : "")); 
		msg = 'xn' == pjzq ? "学年" : ('xq' == pjzq ? "学年-学期" : ('nd' == pjzq ? "年度" : "")); 

		id += "-xy";
		msg += "-"jQuery("#xbmc").val();

		if (checkListIsSelect(id, msg)) {
			if (confirm('自动计算可能耗费4～5分钟，确定要进行自动计算吗?')) {
				refreshForm('pjpy_tyb_countZccj.do');
				showTips('自动计算中,请稍候...');
			}
		}
	}
	function changeJb() {
		var jb = $('queryequals_jb').value;
		if (jb=='1') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=true;
			$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=true;
		} else if (jb=='2') {
		//$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=false;
			$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=true;
			$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=true;
		} else if (jb=='3') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			//$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=false;
			$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=true;
		} else if (jb=='4') {
			$('queryequals_ejdm').value="";
			$('queryequals_ejdm').disabled=true;
			$('queryequals_sjdm').value="";
			$('queryequals_sjdm').disabled=true;
			//$('queryequals_sidm').value="";
			$('queryequals_sidm').disabled=false;
		} else {
			//$('queryequals_ejdm').value="";
			//$('queryequals_ejdm').style.disabled=true;
			//$('queryequals_sjdm').value="";
			//$('queryequals_sjdm').style.disabled=true;
			//$('queryequals_sidm').value="";
			//$('queryequals_sidm').style.disabled=true;
		}
	}
	
	function cqdzgc() {
		var url="/xgxt/pjpyCqdzgc.do?method=printCqdzgc";
		if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要汇总的记录！');
				return false;
		}
		document.forms[0].action = "";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
</script>
</head>
<body onload="xyDisabled('xy');changeJb();">
	<html:form action="/pjpyTybZhszcp.do" method="post">
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
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
<div id="msg_div" class="hide"
						style="left:20px;top:10px;">
						<p>自动计算可能耗费4～5分钟</p>
					</div>
		<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="zccount();" class="btn_ccg" onmouseout="$('msg_div').className = 'hide'"
									onmousemove="$('msg_div').className = 'msg_prompt'">自动计算</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
					  </logic:equal>
							<li> <a href="#" onclick="wjcfDataExport('pjpyTybZhszcp.do?method=zhszcpzfExport');" class="btn_dc">导出数据</a> </li>
					  <!--丽水学院-->
					  <logic:equal value="10352" name="xxdm">
						<li> <a href="#" onclick="printZhszcphzb();" class="btn_dy">成绩汇总表</a> </li>
					  </logic:equal>
					  <logic:equal value="12609" name="xxdm">
						 <li> <a href="#" onclick="cqdzgc()" class="btn_dy">学生成绩汇总</a> </li>					  
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
									onclick="refreshForm('pjpy_tyb_zhszcp.do?act=qry');">
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
								onchange="initZyList();initBjList();">
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
						<th>查询级别</th>
						<td colspan="5">
							<html:select property="queryequals_jb" styleId="queryequals_jb" onchange="changeJb();refreshForm('pjpy_tyb_zhszcp.do')">
								<html:option value="1">综测总分</html:option>
								<html:option value="2">二级</html:option>
								<html:option value="3">三级</html:option>
								<html:option value="4">四级</html:option>
							</html:select>
						</td>
						</tr>
						<tr>
						<th>综测项目</th>
						<td colspan="5">
							<font color="red">(二级)</font>
							<html:select property="queryequals_ejdm"
								styleId="queryequals_ejdm"
								onchange="refreshForm('pjpy_tyb_zhszcp.do');">
								<html:option value=""></html:option>
								<html:options collection="ejdmList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;==>&nbsp;
							<font color="red">(三级)</font>
							<html:select property="queryequals_sjdm"
								styleId="queryequals_sjdm"
								onchange="refreshForm('pjpy_tyb_zhszcp.do');">
								<html:option value=""></html:option>
								<html:options collection="sajdmList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;==>&nbsp;
							<font color="red">(四级)</font>
							<html:select property="queryequals_sidm"
								styleId="queryequals_sidm">
								<html:option value=""></html:option>
								<html:options collection="sijdmList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;
							<%--								排名方式：--%>
							<%--								<html:select property="queryequals_pmfs" styleId="pmfs">--%>
							<%--									<html:option value=""></html:option>--%>
							<%--									<html:option value="zy">专业</html:option>--%>
							<%--									<html:option value="bj">班级</html:option>--%>
							<%--								</html:select>--%>		
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
<%--			        <td>--%>
<%--<!--						<input type="checkbox" name="cb" onclick="selectAll()"-->--%>
<%--<!--							style="height:17.5px"/>-->--%>
<%--					</td>--%>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand;"
							ondblclick="">
<%--							<td align="left">--%>
<%--								<input type="checkbox" id="cbv" name="primarykey_cbv"--%>
<%--									style="height:17.5px"--%>
<%--									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />--%>
<%--							</td>--%>
							<td>
								<input type="hidden" name="cbv" id="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZhszcpwhActionForm"></jsp:include>
			 	<script type="text/javascript">
						$('choose').className="hide";
				</script>
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
</html>
