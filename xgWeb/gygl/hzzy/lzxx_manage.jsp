<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 公寓辅导员信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/lzxx_manage" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
				
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
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">导出</a>
						</li>
						</logic:equal>
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
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/lzxx_manage.do')">
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
									学年
								</th>
								<td>
									<html:select  property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>											
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select  property="xq" style="width:100px" styleId="xq">
									    <html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									校区名
								</th>
								<td>
									<html:select property="xqdm" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xiaoqquList" property="dm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									楼栋名
								</th>
								<td>
									<html:select property="lddm" style="width:120px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>		
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
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="myViewMore('view')"> 
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td >
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						 <script type="text/javascript">
					      $('choose').className="hide";
					     </script>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/lzxx_add.do?doType=";
   url += doType;
   url += "&pkValue=";
   if(doType=="add"){
      url +=pkValue;
      showTopWin(url,'650','400');
   }else if(doType=="view"){
      pkValue = curr_row.getElementsByTagName("input")[0].value;     
      url +=pkValue;
      url +="&isView=true";
      showTopWin(url,'650','400');
   }else {
      if(curr_row == null){
        alert("请选择要操作的数据！\n（单击相应的行）");
		return false;
      }
      pkValue = curr_row.getElementsByTagName("input")[0].value;     
      url +=pkValue;    
      if(doType=="modi"){                        
         if(confirm("确定要修改该数据？")){
             showTopWin(url,'650','400');
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

		
