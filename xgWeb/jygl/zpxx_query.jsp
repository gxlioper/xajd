<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/zpxxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
	</script>
		<script language="javascript">
		function zpxxdel(doType2){
		var url = "/xgxt/zpxxdel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
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
		
		
		function viewMoreinfo(doType){
		var url ="/xgxt/zpxxViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 700, 650);
		 }
		}
		
		
		
		function zpxxupdate(doType){
		var url ="/xgxt/zpxxupdate.do?doType=update&rowid=";
		var rowid ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 rowid = curr_row.getElementsByTagName("input")[0].value;
		         url += rowid;
		         showTopWin(url, 680, 635);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function sendgrjl(gsmc,zpzw) {	
		var url="/xgxt/opensendgrjlweb.do?doType=view&gsmc=";
		url=url+gsmc+"&zpzw="+zpzw;	
		showTopWin(url, 680, 660);
        }
		
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 就业招聘 - 招聘信息查询</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="who" value="teacher">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="zpxxupdate('update');" class="btn_xg">
									修改 </a>
							</li>
							<li>
								<a href="#" onclick="zpxxdel('del');" class="btn_sc"> 删除 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="querygo()">
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
									公司名称
								</th>
								<td>
									<html:text name="rs1" property="gsmc" style="width:175px" />
								</td>
								<th>
									招聘职位
								</th>
								<td>
									<html:text name="rs1" property="zpzw" />
								</td>
								<th>
									工作地点
								</th>
								<td>
									<html:text name="rs1" property="gzdd" style="width:215px" />
								</td>
							</tr>
							<tr>
								<th>
									行业分类
								</th>
								<td>
									<html:select name="rs1" property="hyfl" styleId="hyfl"
										style="width:220px">
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl"
											labelProperty="hyfl" />
									</html:select>
								</td>
								<th>
									转正薪水
								</th>
								<td>
									<html:select name="rs1" property="zzxs" style="width:106px">
										<html:option value=""></html:option>
										<html:option value="面议">面议</html:option>
										<html:option value="1000以下">1000以下</html:option>
										<html:option value="1000-1500">1000-1500</html:option>
										<html:option value="1500-2500">1500-2500</html:option>
										<html:option value="2500-3500">2500-3500</html:option>
										<html:option value="3500-5000">3500-5000</html:option>
										<html:option value="5000-7000">5000-7000</html:option>
										<html:option value="7000-10000">7000-10000</html:option>
										<html:option value="10000以上">10000以上</html:option>
									</html:select>
								</td>
								<th>
									学历要求
								</th>
								<td>
									<html:select name="rs1" property="xlyq" style="width:70px">
										<html:option value=""></html:option>
										<html:option value="专科">专科</html:option>
										<html:option value="本科">本科</html:option>
										<html:option value="硕士">硕士</html:option>
										<html:option value="博士">博士</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									发布时间
								</td>
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
								<td></td>
								<td></td>
								<td></td>
								<td></td>
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
							<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td align="center">
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" ondblclick="viewMoreinfo('view')">
									
									<logic:iterate id="v" name="s" offset="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<button class="button2"
											onclick="sendgrjl(this.parentNode.parentNode.cells[1].innerText,this.parentNode.parentNode.cells[2].innerText)"
											style="width:60px">
											投简历
										</button>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					</logic:notEmpty>
				</div>
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                       alert("删除成功！");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
