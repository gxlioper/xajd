<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	    function czmxquerygo(){
	    var userid =document.getElementById("userid").value;
	    var dowhat =document.getElementById("dowhat").value;
	    var whichtable =document.getElementById("whichtable").value;
	    var xjsj = document.getElementById("xjsj").value;
	    
	    if(userid==""&&dowhat==""&&whichtable==""&&xjsj==""){
	      if(confirm("你未选择任何条件，查询将花费较长时间，你愿意等待吗？")){
	        document.forms[0].action = "/xgxt/userczmxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
		 	return true;
	      }else{
	      return false;
	      }
	    }
		 	document.forms[0].action = "/xgxt/userczmxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
        }
        
		function czmxdelete(doType){
		var url = "/xgxt/userczmxdelete.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		function czmxdeleteall(doType){
		var url = "/xgxt/userczmxdeleteall.do?doType2=delall&doType=query&act=go";
			
		if (doType == "delall") {
			if (confirm("确定要清空当前数据吗？")) {
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		}
      }
		
		 function  jyglbyqxDataExport(){
	       document.forms[0].action = "/xgxt/czmxDataExport.do?tableName=jygl_userczmxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 模块维护 - 用户操作明细</a>
			</p>
		</div>

		<html:form action="/openCzmxWeb" method="post">
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="czmxdelete('del');" class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="#" onclick="czmxdeleteall('delall')" class="btn_hsz">
								清空 </a>
						</li>
						<li>
							<a href="#" onclick="jyglbyqxDataExport()" class="btn_dc"> 导出
							</a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="czmxquerygo()">
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
									用户名
								</th>
								<td>
									<html:text name="rs1" property="userid" style="width:130px" />
								</td>
								<th>
									操作
								</th>
								<td>
									<html:select name="rs1" property="dowhat" style="width:80px">
										<html:option value=""></html:option>
										<html:option value="增加">增加</html:option>
										<html:option value="删除">删除</html:option>
										<html:option value="修改">修改</html:option>
									</html:select>
								</td>
								<th>
									表名
								</th>
								<td>
									<html:select name="rs1" property="whichtable"
										style="width:150px">
										<html:option value=""></html:option>
										<html:option value="学生基本信息表">学生基本信息表</html:option>
										<html:option value="毕业去向表">毕业去向表</html:option>
										<html:option value="就业协议表">就业协议表</html:option>
										<html:option value="个人简历表">个人简历表</html:option>
										<html:option value="咨询教师信息表">咨询教师信息表</html:option>
										<html:option value="咨询预约管理表">咨询预约管理表</html:option>
										<html:option value="政策文件表">政策文件表</html:option>
										<html:option value="招聘信息表">招聘信息表</html:option>
										<html:option value="企业意见反馈表">企业意见反馈表</html:option>
										<html:option value="招聘信息管理表">招聘信息管理表</html:option>
										<html:option value="就业协议维护表">就业协议维护表</html:option>
										<html:option value="就业招聘维护表">就业招聘维护表</html:option>
									</html:select>
								</td>
								<th>
									时间
								</th>
								<td>
									<html:select name="rs1" property="xjsj" style="width:75px">
										<html:option value=""></html:option>
										<html:option value="-1">当天</html:option>
										<html:option value="-2">近两天</html:option>
										<html:option value="-7">一周内</html:option>
										<html:option value="-15">半月内</html:option>
										<html:option value="-30">一月内</html:option>
										<html:option value="-90">三月内</html:option>
										<html:option value="-180">半年内</html:option>
										<html:option value="-365">一年内</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>  </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
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
										<logic:iterate id="v" name="s" offset="1">
											<td align="center">
												<bean:write name="v" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>

				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("删除成功!");
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("删除失败");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="deleteall">
					<logic:equal name="deleteall" value="ok">
						<script>
                      alert("已将当前记录清空!");
                    </script>
					</logic:equal>
					<logic:equal name="deleteall" value="no">
						<script>
                      alert("清空操作失败");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		</script>
	</body>
</html>
