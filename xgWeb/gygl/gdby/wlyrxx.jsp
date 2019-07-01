<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 外来人员来访登记</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<!-- 页签 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="myViewMore('add')"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="myViewMore('modi')"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="myViewMore('del')"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="impAndChkData()"
									class="btn_dr">导入</a>
							</li>		
						</logic:equal>
						<li>
							<a href="#" 
								onclick="BbTj('/xgxt/XsGyGlLogic.do?method=xsGyWlRy&done=bbTj')"
								class="btn_dc">登记报表</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGyWlRy')">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年度
								</th>
								<td>
									 <html:select  property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>												
								</td>
								<th>
									月份
								</th>
								<td>
									<html:select  property="yf" style="width:100px" styleId="yf">
									    <html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm" style="width:90px" styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
									</html:select>
								</td>
								<th>
									来访日期
								</th>
								<td>
									<html:text property="rq" styleId="rq" readonly="true" onblur="dateFormatChg(this)"
						  			  onclick="return showCalendar('rq','y-mm-dd');" style="width:100px;cursor:hand " ></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
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
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
							<tr  onclick="rowOnClick(this)" style="cursor:hand;background-color:
	                          <logic:iterate id="v" name="s" offset="0" length="1">
	                           <bean:write name="v"/>
	                            </logic:iterate>
	                             " style="cursor:hand" ondblclick="myViewMore('view')">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td >
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>						
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>		
		</html:form>

<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/XsGyGlLogic.do?method=xsGyWlRy_Modi&doType=";
   url += doType;
   url += "&pkValue=";
   if(doType=="add"){
      url +=pkValue;
      showTopWin(url,'600','400');
   }else {
      if(curr_row == null){
        alert("请选择要操作的数据！\n（单击相应的行）");
		return false;
      }
      pkValue = curr_row.getElementsByTagName("input")[0].value;  
      url +=pkValue;
      if(doType=="view"){
        showTopWin(url,'600','400');
      }
      if(doType=="modi"){                        
         if(confirm("确定要修改该数据？")){
             showTopWin(url,'600','400');
         }else{
             return false;
         }
      }
      if(doType=="del"){
         if(confirm("确定要修删除该行数据？")){
             refreshForm(url);
         }else{
             return false;
         }
      }
   }
}
function BbTj(url){
 if(document.getElementById('lddm').value==""){
		alert("楼栋名称不能为空！");
		return false;
    }
    url+="&lddm="+document.getElementById('lddm').value;
    url+="&nd="+document.getElementById('nd').value;
    url+="&yf="+document.getElementById('yf').value;
    url+="&rq="+document.getElementById('rq').value;
     showTopWin(url,'800','700');
}
</script>

<logic:equal value="ok" name="done">
<script type="text/javascript">
alert("操作成功！");
document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script type="text/javascript">
alert("操作失败！");
document.getElementById('search_go').click();   
</script>
</logic:equal>
</body>
</html>		

		
