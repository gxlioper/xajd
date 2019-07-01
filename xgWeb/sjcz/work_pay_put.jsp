<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>	
	<script>
		function singleWorkPay(url){
			if(curr_row==null){
				alert('请选择要批量发放的岗位!');
				return false;
			}
			var pk = document.forms[0].pk.value;
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			var gwxz = curr_row.cells[6].innerText;
			var yrdw = curr_row.cells[5].innerText;
			url += "&pk=";
			url += pk;
			url += "&pkValue=";
			url += pkValue;
			url += "&gwxz=";
			url += gwxz;
			url += "&yrdw=";
			url += yrdw;
			
			showTopWin(url,800,600);
			
		}
		
		//工资发放表打印
		function printPayReport(){
			var xxdm = document.getElementById('xxdm').value;
			var gwxzmc = "";
			if(ele("gwxz")){
				gwxzmc = selText('gwxz');
			}
			var url = "qgzxLogic.do?method=printPayReport&gwxzmc="+gwxzmc;
			if(xxdm == "13022"){//宁波理工		
				url = "qgzxNblg.do?method=printYbb";
			}
			window.open(url);
		}
		
		//填写详单
		function writeList(){
			var xxdm = document.getElementById('xxdm').value;
			var cjffsj = document.getElementById('cjffsj').value;
			if(xxdm == '10856'){//上海工程技术大学
				singleWorkPay('qgzxLogic.do?method=showSinglePage');
			}else if(xxdm == '10491'){//中国地质大学
				fillMonth('/xgxt/cjff.do?method=xzffsj&type=ygzff');
			}else{//其它
				if(cjffsj != ""){//如果在参数设置中设置了工作发放月份
					viewMoreWorkPay('add');
				}else{
					//if($('day').value<31){
					     viewMoreWorkPay('add'); 
					 //}else{
					     //alert('时间超限,请归入下月!');		
					     //return false;		  
					 //}
				 }
			}
		}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="day" name="day" value="<bean:write name="day" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="cjffsj" name="cjffsj" value="${conf.cjffsj}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em>
					<a>
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
						学生义工 - 酬金发放 - 酬金发放
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						勤工助学 - 酬金发放 - 酬金发放
				    </logic:notEqual>
					</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					<!--读写权-->
					<logic:equal value="yes" name="writeAble" scope="request">					
					<%--武汉理工大学 只能用人单位填写--%>
					<logic:equal value="10497" name="xxdm">
						<li> <a href="#" onclick="writeList();" class="btn_zj">填写详单</a> </li>
					</logic:equal>
					<%--end武汉理工大学--%>
					
					<%--非武汉理工大学--%>
					<logic:notEqual value="10497" name="xxdm">
						<li> <a href="#" onclick="writeList();" class="btn_zj">
							<%--中国地质大学--%>
							<logic:equal value="10491" name="xxdm">
								月工资
							</logic:equal>
							<%--end中国地质大学--%>
							<%--非中国地质大学--%>
							<logic:notEqual value="10491" name="xxdm">
								填写详单
							</logic:notEqual>	
							<%--end非中国地质大学--%>
						</a> </li>
					</logic:notEqual>
					<%--end非武汉理工大学--%>	
					
					<%--中国地质大学--%>
					<logic:equal value="10491" name="xxdm">
						<li> <a href="#" onclick="fillMonth('/xgxt/cjff.do?method=xzffsj&type=lsgzff');" class="btn_zj">临时工资</a> </li>	
						<li> <a href="#" onclick="rePut();" class="btn_zj">工资补发</a> </li>
					</logic:equal>
					<%--end中国地质大学--%>
					</logic:equal>
					<!--end读写权-->
					<%--广东女子职业技术学院--%>
					<logic:equal value="12742" name="xxdm">
						<li> <a href="xlsDown/qgzx_gdnzzy_gzffb.doc" class="btn_xz">工资发放表下载</a> </li>
					</logic:equal>
					<%--end广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
					
					<%--非广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
					<logic:notEqual value="12742" name="xxdm">
						<li> <a href="#" onclick="printPayReport();" class="btn_dy">工资发放表</a> </li>
					</logic:notEqual>
					<%--end非广东女子职业技术学院--%>
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
									onclick="allNotEmpThenGo('/xgxt/work_pay_put.do?act=workPayPut')">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>年度</th>
						<td>
							<html:select property="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>学年</th>
						<td>
							<html:select property="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>岗位性质</th>
						<td>
							<html:select property="gwxz">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>岗位名称</th>
						<td>
							<html:select property="gwdm">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwmc"
									labelProperty="gwmc" />
							</html:select>
						</td>
						<th></th>
						<td>
							
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
						<!--非上海工程技术大学-->
						<logic:notEqual value="10856" name="xxdm">
							<%--非浙江机电职业技术学院--%>
							<logic:notEqual value="12861" name="xxdm">
								<logic:equal value="10491" name="xxdm">
									<font color="red">${conf.sbts}</font>
								</logic:equal>
								<logic:notEqual value="10491" name="xxdm">
									<font color="red">注意：各用人单位必须在每月30日前填写酬金发放详单！</font>
								</logic:notEqual>
							</logic:notEqual>
							<%--end非浙江机电职业技术学院--%>
						</logic:notEqual>
						<!--end非上海工程技术大学-->
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
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
					<tr onclick="rowOnClick(this)" style="cursor:hand">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="<bean:write name="v"/>" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
			</div>
		</html:form>		
	</body>
</html>

