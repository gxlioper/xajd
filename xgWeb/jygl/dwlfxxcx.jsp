<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script language="javascript">
	    function byqxquerygo(){
		 	document.forms[0].action = "dwlfxx.do?doType=sect&act=query";
		 	document.forms[0].submit();
        }
        
        /*
    	全部选中
	    */    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }	
        
        /*
	批量删除
	*/
      function delall(url){
	    var RowsStr="!!#!!";
    
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请勾选需要删除的记录！");
		   return false;
    	}
	
	    if (!confirm("你确定要删除所选记录？")){
		   return false;
	    }
	    BatAlert.showTips('正在删除，请稍侯...');
	    document.forms[0].action=url;
        document.forms[0].submit();
     }   
        
           /*
	全部清空
	*/
        
        function delallinfo(url){
          if(!confirm("你确定要清空所有记录？")){
             return false;
          }
          BatAlert.showTips('正在清空列表，请稍侯...');
	      document.forms[0].action=url;
          document.forms[0].submit();
        
        
        }
        
        
		function stuinfodelete(doType){
		var url = "dwlfxx.do?doType=del&act=query&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
			    BatAlert.showTips('正在删除，请稍侯...');
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
		
		
		function viewMoreinfo(){
		 var url ="dwlfxx.do?doType=dnset&pkValue=";
	
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 600,400);
		}
		
		function addxsjyxx(){
		   var url ="stuxsjyxxinput.do";
		   showTopWin(url, 750, 500);	 
		}
		
		
		
		function xsjyxxupdate(doType){
		var url ="dwlfxx.do?doType="+doType+"&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 670, 480);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
	    function refreshtheweb()
		{
			document.forms[0].action = "stuxsjyxxquery.do";
            document.forms[0].submit();
		}
		
		
		 function  jyglDataExport(){
	       document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName=dwlfxxdjb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>单位来访信息 - 单位来访信息维护</a>
			</p>
		</div>


		<html:form action="/stuxsjyxxquery" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="realTable" name="realTable"
				value="dwlfxxdjb" />
			<input type="hidden" id="tableName" name="tableName"
				value="dwlfxxdjb" />
			<input type="hidden" id="stab" name="stab" value="stab" />

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="xsjyxxupdate('update');" class="btn_xg">
								修改 </a>
						</li>
						<li>
							<a href="#" onclick="stuinfodelete('del');" class="btn_sc">
								删除 </a>
						</li>
						<li>
							<a href="#"
								onclick="delall('dwlfxx.do?doType=delall&act=query');"
								class="btn_sc"> 批量删除 </a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>
						</li>
						<li>
							<a href="#" onclick="jyglDataExport()" class="btn_dc"> 导出 </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="byqxquerygo()">
											查 询
										</button>
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
									编号
								</th>
								<td>
									<html:text property="dwbh" style="width:80px"></html:text>
								</td>
								<th>
									单位名称
								</th>
								<td>
									<html:text property="dwmc" style="width:80px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" style="width:80px"></html:text>
								</td>
								<th>
									单位电话
								</th>
								<td>
									<html:text property="dwdh" style="width:80px"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
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
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="qbxz" value="all"
											onclick="chec('qbxz')" />
									</td>
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
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="viewMoreinfo();" align="center">
										<td>
											<input type="hidden" name="dwid" value="${s.dwid }" />
											<input type="checkbox" name="pk" value="${s.dwid }" />
										</td>
										<td>
											<bean:write name="s" property="dwbh" />
										</td>
										<td>
											<bean:write name="s" property="dwmc" />
										</td>
										<td>
											<bean:write name="s" property="xm" />
										</td>
										<td>
											<bean:write name="s" property="dwdh" />
										</td>
										<td>
											<bean:write name="s" property="bm" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
				<button onclick="refreshtheweb()" id="search_go"
					style="display: none"></button>
				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("删除成功!");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("删除失败！");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delall">
					<logic:equal name="delall" value="ok">
						<script>
                      alert("批量删除成功!");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
					<logic:equal name="delall" value="no">
						<script>
                      alert("批量删除失败！");
                      document.getElementById("search_go").click();
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delallinfo">
					<logic:equal name="delallinfo" value="ok">
						<script>
                      alert("全部内容已清空！");
                    </script>
					</logic:equal>
					<logic:equal name="delallinfo" value="no">
						<script>
                      alert("清空列表失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>

	</body>
</html>
