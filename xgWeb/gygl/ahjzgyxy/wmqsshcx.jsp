<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body >
		
		<html:form action="/ahjg_gygl" method="post">
			<input type="hidden" name="qshV" id="qshV" />		
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>公寓管理 - 审核 - 文明寝室审核</a>
				</p>
			</div>
			<div class="toolbox">
			<div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="refreshForm('/xgxt/ahjg_gygl.do?method=wmqsSbShQer');this.disabled=true;">
			              	查 询
			              </button>
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
			
				  <tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select  property="xn" style="width:90px" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>
								学期
							</th>
							<td>
								<html:select  property="xq" style="width:90px" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>	
							<th>
								楼栋名
							</th>
							<td>
								<html:select property="lddm" style="width:120px"
										onchange="GetQshList()" styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>								
								
							</td>
						</tr>
						<tr>
						    <th>
						    	寝室号
						    </th>
						    <td>
								<html:select property="qsh" style="width:110px" styleId="qsh">
										<html:option value=""></html:option>
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
						    </td>
							<th>
								评优时间
							</th>
							<td>
								<html:text property="pysj" onblur="dateFormatChg(this)"
									onclick="return showCalendar('pysj','y-mm-dd');" style="cursor:hand " />
							</td>
							<th>
								审核状态
							</th>
							<td>
								<html:select property="yesNo" style="width:80px" styleId="yesNo">
										<html:option value=""></html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>								
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
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息，并可修改其审核状态；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			<logic:notEmpty name="rs">
					 <table summary="" class="datelist" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand;background-color:
	    					<logic:iterate id="v" name="s" offset="1" length="1">
						    	<bean:write name="v"/>
						    </logic:iterate>" ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
		</div>
		</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
  </body>   
<script type="text/javascript">
  function CheckDo(){
    var	w = 550;
	var	h = 350;	
	url = "/xgxt/ahjg_gygl.do?method=wmqsSbSh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
  } 
</script>  
</html>
