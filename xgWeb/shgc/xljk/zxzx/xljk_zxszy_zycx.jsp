<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="JavaScript">   
		  function view(){
		  	 for(i=0;i<Rows.length;i++){
		  		var xn_id=Rows[i].getElementsByTagName("input")[0].value;
		  		showTopWin('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=View&xn_id='+xn_id,800,600);
		  	}
		  }
  
		  function apply_for(){
		  		var len=Rows.length;
		   		var str="";
		   		if(Rows.length==0){
		   			alert("请选择一行！");
		   			return false;
		   		}else {
		   			//判断是否有预约
		   			if(curr_row.getElementsByTagName('input')[1].value=="是"){
		   				alert("该资源已经被申请!");
		   			}else{
						if(Rows.length==1){
							if(confirm("确定要申请该资源吗？")){
								for(i=0;i<Rows.length;i++){
			  						str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
			  					}
								showTopWin('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=student_check&zxszy_xnid='+str,800,600);
								return true;
							}else{
								return false;
							}
						}else{
							alert("只能选中一条记录进行申请!");
							return false;
						}
					}
				}
		  }
  </script>
  </head>
	<body onload="">
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 学生申请预约 - 预约申请</a>
			</p>
		</div>
		
	
		<html:form action="/xljk_zxszyAtion" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
				
				<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="apply_for();"
									class="btn_zj"> 申请预约</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=Xssq_Zycx')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									地点
								</th>
								<td>
									<html:select property="dd_dm" style="width:100px"
										styleId="dd_dm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="ddList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>
								<th>
									时间段
								</th>
								<td>
									<html:select property="sjd_dm" style="width:100px"
										styleId="sjd_dm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sjdList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>
								<th>
									日期
								</th>
								<td>
									<html:select property="rq" style="width:100px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="rqList" property="rq"
											labelProperty="rq" />
									</html:select>
								</td>
								<th>
									咨询师编号
								</th>
								<td>
									<html:text property="zxxbh" style="width:100px" readonly="true" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细信息；<logic:notEqual value="stu" name="userType" scope="session">单击表头可以排序;按ctrl可多选</logic:notEqual></font>
							</logic:notEmpty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this);" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td>
										是否预约
									</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" offset="0">
									<tr style="cursor:hand" onclick="rowMoreClick(this,'',event);" ondblclick="view()">
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="XN_ID"/>" />
											<bean:write name="s" property="RQ" />
										</td>
										<td>
											<bean:write name="s" property="SJD" />
										</td>
										<td>
											<bean:write name="s" property="DD" />
										</td>
										<td>
											<bean:write name="s" property="SFYY" />
											<input type="hidden" name="sfyyy" id="sfyyy" value="${s.SFYY }"/>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
	</body>
</html>
