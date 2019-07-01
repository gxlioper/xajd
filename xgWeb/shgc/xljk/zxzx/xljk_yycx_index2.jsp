<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script language="javascript" src="js/lrh_new_js.js"></script>
 	<script  language="JavaScript">   
	  function view() {
	  	 for(i=0;i<Rows.length;i++){
	  		var xn_id=Rows[i].getElementsByTagName("input")[0].value;
	  		showTopWin('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=View&xn_id='+xn_id,700,450);
	  	}
	  }
  
	  function table_search(){
	  	var len=Rows.length;
	   	var str="";
	   	if(Rows.length==0){
	   		alert("请至少选择一行！");
	   		return false;
	   	}else{
	   		if(Rows.length==1){
				for(i=0;i<Rows.length;i++){
	  				str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
	  			}
	  			showTopWin('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Search_Table&zxszy_xnid='+str,750,600);
				return true;
			}else{
				alert("只能选中一条记录进行查看!");
				return false;
			}
	   	}
	  }
  
	  function cancel_apply(){
	  	var len=Rows.length;
	   	var str="";
	   	if(Rows.length==0){
	   		alert("请至少选择一行！");
	   		return false;
	   	}else{
	   		for(i=0;i<Rows.length;i++){
	  			str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
	  		}
	  		//showTopWin('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Search_Table&zxszy_xnid='+str,650,600);
			refreshForm('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Cancle_Table&zxszy_xnid='+str);
			return true;
		}
	  }
	  
	  function xsyycxb(){
	  	document.forms[0].action = "/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=xljk_xsyycx";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	  }
  	</script>   
  	</head>
	<body onload="">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xljk_yycx" method="post">
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
								<a href="#" onclick="table_search();"
									class="btn_ck"> 查看对应申请表 </a>
							</li>
							<li>
								<a href="#"
									onclick="cancel_apply();"
									class="btn_xg">取消预约</a>
							</li>
							
							<!-- 学生预约查询表 -->
							<logic:equal name="xxdm" value="10395">
							<li>
								<a href="#"
									onclick="xsyycxb()"
									class="btn_tj">学生查询</a>
							</li>
							</logic:equal>
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
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/xljk_yycx.do?act=zycx&doType=zycx_tec')">
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
									<html:select property="dd_dm" style="width:100px" styleId="dd_dm"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="ddList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>
								<th>
									时间段
								</th>
								<td>
									<html:select property="sjd_dm" style="width:100px" styleId="sjd_dm"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="sjdList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>
								<th>
									日期
								</th>
								<td>
									<html:text style="cursor:hand; width:100px;" styleId="dateF"
										property="rq"
										onclick="return showCalendar('dateF','y-mm-dd');"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									咨询师
								</th>
								<td>
									<html:select property="zxxbh" style="width:100px" styleId="sjd_dm"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="zxxList" property="zxxbh"
										labelProperty="zxxxm"
											 />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" style="width:100px"/>
								</td>
								<th>
								</th>
								<td>
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
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;按ctrl可多选</font>
							</logic:notEmpty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
							<logic:equal value="10355" name="xxdm">
								<logic:iterate id="tit" name="topTr" offset="1" length="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this);" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:iterate id="tit" name="topTr" offset="1" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this);" nowrap>
										咨询师姓名
									</td>
								</logic:iterate>
								<logic:iterate id="tit" name="topTr" offset="5" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this);" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:notEqual value="10355" name="xxdm">
									<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this);" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:notEqual>
							</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" offset="0" >
									<tr  style="cursor:hand" 
									onclick="onfocusit()" ondblclick="view()" >
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
										<logic:equal value="10355" name="xxdm">
										<td>
											<bean:write name="s" property="ZXXXM" />
										</td>	
										</logic:equal>
										<logic:notEqual value="10355" name="xxdm">
										<td>
											<bean:write name="s" property="ZXXBH" />
										</td>	
										</logic:notEqual>
										<td>
											<bean:write name="s" property="XH" />
										</td>
										<logic:equal value="11078" name="xxdm">
											<td>
												<bean:write name="s" property="XM" />
											</td>
											<td>
												<bean:write name="s" property="XYMC" />
											</td>
										</logic:equal>	
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

