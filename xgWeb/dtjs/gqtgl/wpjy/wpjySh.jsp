<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
	</script>
	</head>
	<body >
		<html:form action="/ntzyWpjy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" styleId="${userName }"/>
			<input type="hidden" name="realTable" id="realTable" value="ghxy_bjryfb" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:equal name="writeAble" value="yes">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#"  onclick="modi('ntzyWpjy.do?method=shOneWpjy&doType=view')" class="btn_sh"> 单个审核 </a> </li>
			      <li> <a href="#" onclick="dataBatch('ntzyWpjy.do?method=wpjySh&doType=save&shjg=通过')" class="btn_shtg"> 审核通过 </a> </li>
				<li> <a href="#" onclick="dataBatch('ntzyWpjy.do?method=wpjySh&doType=save&shjg=不通过')" class="btn_shbtg"> 审核不通过 </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			 <div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('ntzyWpjy.do?method=wpjySh&doType=qry')">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			 

				<table width="100%" class="tbstyle">
					<tbody>
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
							<th>申请人</th>
							<td>
									<html:text property="querylike_sqr" styleId="sqr"/>
							</td>
						</tr>
						<tr>
						   <th>经办人</th>
						   <td>
									<html:text property="querylike_jbr" styleId="jbr"/>
							</td>
							<th>部门审核</th>
							<td> 
								<html:select property="queryequals_xysh" styleId="xysh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>	
							</td>
							<th>学校审核</th>
							<td>
								<html:select property="queryequals_xxsh" styleId="xxsh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>部门</th>
							<td>
						    <logic:equal name="isBmyh" value="true">	
						    	 <html:select  property="bmdm" disabled="true" style="width:180px"
							  		styleId="bm" onchange="initZyList();initBjList();">
							     	<html:option value=""></html:option>
							     	<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
								 </html:select>
								 <html:hidden property="queryequals_bmdm" value="${userDep}"/>	 
						    </logic:equal>
						   	<logic:notEqual name="isBmyh" value="true">
							  <html:select  property="queryequals_bmdm"  style="width:180px"
							  		styleId="bm" onchange="initZyList();initBjList();">
							     	<html:option value=""></html:option>
							     	<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
								 </html:select>	 
							</logic:notEqual>	
							</td>
							<td colspan="4"/>
						</tr>
					</tbody>
				</table>
			</table>
			</div>
			
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					 <table summary="" class="datelist" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()" style="display:none">	
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<logic:notEqual name="index" value="1">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=ntzyWpjyForm"></jsp:include>
			</logic:notEmpty>
			</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
