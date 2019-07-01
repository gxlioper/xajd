<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/XsGyGlLogic.do?method=sdCbXx_Modi&doType=";
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
function CbTj(url){   
    var confirmTxt = "宿舍水电超标月度汇总，将以年、月为单位进行统计";
    if(confirm(confirmTxt)){
    if(document.getElementById('nd').value==""){
		alert("年度不能为空！");
		return false;
    }
    if(document.getElementById('yf').value==""){
        alert("月份月份不能为空！");
        return false;
    }
    url+="&nd="+document.getElementById('nd').value;
    url+="&yf="+document.getElementById('yf').value;
    showTopWin(url,'600','700');
    return true;
 	}else{
 	return false;
    }
}	
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>公寓管理 - 信息维护 - 公寓水电费管理</a>
			</p>
		</div>


		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />



			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="myViewMore('add')" class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#" onclick="myViewMore('modi')" class="btn_xg"> 修改
								</a>
							</li>
							<li>
								<a href="#" onclick="myViewMore('del')" class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a>
							</li>
							<li>
								<a href="#"
									onclick="CbTj('/xgxt/XsGyGlLogic.do?method=sdCbXxTj')"
									class="btn_tj"> 月度汇总表 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<logic:notEqual value="11641" name="xxdm">
											<button class="btn_cx" id="search_go"
												onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGySdCbGl')">
												查 询
											</button>
										</logic:notEqual>
										<logic:equal name="xxdm" value="11641">
											<button class="btn_cx" id="search_go"
												onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGySdCbGl&xh='+document.getElementById('xh').value)">
												查 询
											</button>
										</logic:equal>

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
									年度
								</th>
								<td>
									<html:select property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									月份
								</th>
								<td>
									<html:select property="yf" style="width:100px" styleId="yf">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</td>
								<th>
									楼栋名称
								</th>
								<td>
									<html:select property="lddm" style="width:90px" styleId="lddm"
										onchange="GetQshList()">
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
									<input type="hidden" name="qshV" id="qshV" />
									<html:select property="qsh" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="ssList" property="qsh"
											labelProperty="qsh" />
									</html:select>
								</td>
								<logic:equal value="11641" name="xxdm" scope="session">
									<th>
										学号
									</th>
									<td>
										<input type="text" name="xh" id="xh" maxlength="20" />
									</td>
								</logic:equal>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
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
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--分页显示-->
				</logic:notEmpty>
			</div>
		</html:form>

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


