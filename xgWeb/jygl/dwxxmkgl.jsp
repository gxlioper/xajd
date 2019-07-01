<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
			<script language="javascript">
		function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}	
		
	    function byqxquerygo(){
		 	document.forms[0].action = "dwxxmkwh.do?act=query";
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
		var url = "dwxxmkwh.do?doType=del&act=query&pkValue=";
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
		 var url ="dwxxInput.do?pkValue=";
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue+"&act=go";
		 showTopWin(url, 600,400);
	
		}
		
		function addxsjyxx(){
		   var url ="dwxxInput.do";
		   showTopWin(url, 750, 500);	 
		}
		
		
		
		function xsjyxxupdate(doType){
		var url ="dwxxInput.do?pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue+"&dwupdate=dwupdate&act=go";
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
	       document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName=dwxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
	</head>


	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 模块维护 - 单位信息模块的维护</a>
			</p>
		</div>


		
		<html:form action="/dwxxmkwh" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
            <input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
            <input type="hidden" id="querry" name="querry"
				value="<bean:write name='querry' scope="request" />" />
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual value="12104" name="xxdm" scope="session">
						<li>
							<a href="#"
								onclick="addxsjyxx();"
								class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a href="#"
								onclick="xsjyxxupdate('update');"
								class="btn_xg"> 修改 </a>
						</li>
						</logic:notEqual>
							<li>
								<a href="#"
									onclick="stuinfodelete('del');"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#"
									onclick="delall('dwxxmkwh.do?doType2=delall&act=query');"
									class="btn_sc"> 批量删除 </a>
							</li>
							<li>
								<a href="#" onclick="delallinfo('dwxxmkwh.do?doType2=delallinfo&act=query');" class="btn_hsz"> 全部清空 </a>
							</li>
							<logic:equal name="who" value="xx">
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a>
							</li>
							<li>
								<a href="#"
									onclick="jyglDataExport()"
									class="btn_dc"> 导出 </a>
							</li>
							</logic:equal>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="byqxquerygo()">
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
									单位名称
								</th>
								<td>
									<html:text property="dwmc"/>
								</td>
								<th>
									主管部门
								</th>
								<td>
									<html:select name="rs1" property="zgbm" styleId="zgbm"
										style="width:175px">
										<html:option value=""></html:option>
										<html:options collection="sydzgbmList" property="zgbm"
											labelProperty="zgbm" />
									</html:select>
								</td>
								<th>
									所属行业
								</th>
								<td>
									<html:select name="rs1" property="hyfl" styleId="sshy" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl" labelProperty="hyfl" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									所在地区
								</th>
								<td colspan="3">
									<html:select property="szdqsh" onchange="loadShi()"
										styleId="jgshen">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									<html:select property="szdqsi" styleId="jgshi">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
								</td>
								<th>
									单位性质
								</th>
								<td>
									<html:select name="rs1" property="dwxz" styleId="dwxz">
										<html:option value=""></html:option>
										<html:options collection="dwxxdm" property="dwxz"/>
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
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')"/>
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
								<input type="hidden" name="pk"
									value="<bean:write name="s" property="dwid" />" />
								<input type="checkbox" name="pk"
									value="<bean:write name="s" property="dwid"/>" />
								</td>
								<td>
									<bean:write name="s" property="dwmc" />
								</td>
								<td>
									<bean:write name="s" property="zgbm" />
								</td>
								<td>
									<bean:write name="s" property="clrq" />
								</td>
								<td>
									<bean:write name="s" property="lxbm" />
								</td>
								<td>
									<bean:write name="s" property="lxr" />
								</td>
								<td>
									<bean:write name="s" property="lxdh" />
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

			<button onclick="refreshtheweb()" id="search_go" style="display: none" ></button>
				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("删除成功!");
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("删除失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delall">
					<logic:equal name="delall" value="ok">
						<script>
                      alert("批量删除成功!");
                    </script>
					</logic:equal>
					<logic:equal name="delall" value="no">
						<script>
                      alert("批量删除失败！");
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
	</body>
</html>
