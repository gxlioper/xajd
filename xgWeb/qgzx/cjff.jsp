<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>	
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
			if(ele("queryequals_gwxz")){
				gwxzmc = selText('queryequals_gwxz');
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
					if($('day').value<31){
					     viewMoreWorkPay('add'); 
					 }else{
					     alert('时间超限,请归入下月!');		
					     return false;		  
					 }
				 }
			}
		}
		
		function viewMoreWorkPay(){
			if (curr_row == null) {
				alert("请先选择相应的岗位记录!");
				return false;
			}
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			showTopWin("qgzxcjff.do?method=txxd&pkValue="+pkValue,800,600);
		}
	</script>
</head>	
	<body>
		<html:form action="/qgzxcjff.do" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="day" name="day" value="<bean:write name="day"/>" />
			<input type="hidden" id="cjffsj" name="cjffsj" value="${conf.cjffsj}" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
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
						<%--中国地质大学--%>
						<logic:equal value="10491" name="xxdm">
							<li> <a href="#" onclick="writeList();" class="btn_zj">月工资</a> </li>
							<li> <a href="#" onclick="fillMonth('/xgxt/cjff.do?method=xzffsj&type=lsgzff')" class="btn_zj">临时工资</a> </li>
							<li> <a href="#" onclick="rePut();" class="btn_zj">工资补发</a> </li>
						</logic:equal>
						<%--end中国地质大学--%>	

						<%--非中国地质大学--%>
						<logic:notEqual value="10491" name="xxdm">
							<li> <a href="#" onclick="writeList();" class="btn_zj">填写详单</a> </li>
						</logic:notEqual>
						<%--end非中国地质大学--%>	
					</logic:equal>
					<!--end读写权-->

				
					<li> <a href="#" onclick="printPayReport();" class="btn_dy">工资发放表</a> </li>						
					

					<%--非中国地质大学--%>
					<logic:notEqual value="10491" name="xxdm">
					<li> <a href="#" onclick="printGzhzb();" class="btn_dy">工资汇总表</a> </li>
					</logic:notEqual>
					<%--end非中国地质大学--%>	
					
					<logic:equal value="12977" name="xxdm">
					<li>
						<a href="#" onclick="wjcfDataExport('qgzxcjff.do?method=gzqfb');" class="btn_dy"><bean:message key="lable.xsgzyxpzxy" />工资签收表</a> 
					</li>
					</logic:equal>
					
			    </ul>
			  </div>			 
			 <!--查询条件-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/qgzxcjff.do?method=cjff')">
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
						<html:select property="queryequals_xn" style="width:180px" styleId="xn" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>年度</th>
					<td>
						<html:select property="queryequals_nd" styleId="nd" style="width:180px" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>	
					<th>学期</th>
					<td>
						<html:select property="queryequals_xueqi" style="width:180px" styleId="xq" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>				
				</tr>						
				<tr>
					<logic:notEqual name="xxdm" value="11078">
					<th>岗位名称</th>
					<td colspan="5">
						<html:select property="queryequals_gwdm" styleId="gwdm" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdm"
								labelProperty="gwdm" />
						</html:select>
					</td>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11078">
						<th>岗位名称</th>
						<td>
							<html:select property="queryequals_gwdm" styleId="gwdm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdm"
									labelProperty="gwdm" />
							</html:select>
						</td>
			      		<th>岗位性质</th>
						<td colspan="3">
							<html:select property="queryequals_gwxz" style="width:180px" styleId="xq" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
					</logic:equal>			
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
					<%--非浙江机电职业技术学院--%>
					<logic:notEqual value="12861" name="xxdm">
						<!--中国地质大学-->
						<logic:equal value="10491" name="xxdm">
							<font color="red">${conf.sbts}</font>
						</logic:equal>
						<!--end中国地质大学-->
						<logic:notEqual value="10491" name="xxdm">
							<font color="red">注意：各用人单位必须在每月30日前填写酬金发放详单！</font>
						</logic:notEqual>
					</logic:notEqual>
					<%--end非浙江机电职业技术学院--%>
				</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
			       <logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxForm"></jsp:include>
			    <!--分页显示-->
				<script type="text/javascript">
				$('choose').className="hide";
				</script>
			</logic:notEmpty>
		</div>	
		</html:form>
	</body>
</html>

