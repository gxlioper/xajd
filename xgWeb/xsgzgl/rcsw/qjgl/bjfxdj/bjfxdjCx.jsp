<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：wujian -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "rcsw_bjfxdj_ajax.do?method=bjfxdjCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		function showView(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
				
				var url="rcsw_bjfxdj.do?method=bjfxdjCk";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,800,600);
			}else{
				
				alertInfo("请勾选一条需要查看的记录！");
				
				return false;
			}
		}
		
		function cxqjDiv(topMsg){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var sfdj = false;
				for (var i=0;i<array.length;i++) {
					if(jQuery(array[i]).parent().parent().find("td").eq(8).html()!=""){
						sfdj = true;
						break;
					}
				}
				if(sfdj==false){
					tipsWindown(topMsg,"id:tempDiv","400","300","true","","true","id");
				}else{
					alertInfo("已经返校登记,不能再进行登记！");
				}
			}else{
				alertInfo("请勾选需要登记的数据！");
				return false;
			}
		}
		
		function cxyyDivSave(){
			var sffx = jQuery("[name=sffx]:checked").val();
			var bz = jQuery("#bz").val();
			confirmInfo("该操作将会登记返校信息，是否确定继续操作？",function(tag){
				if(tag=="ok"){
					var str="";
					var array = jQuery("[name=div_pkValue]:checked");
					for (var i=0;i<array.length;i++) {
						var id = jQuery(array[i]).val();
						str +=id+"!@";
					}
					var parameter={}
					var url="rcsw_bjfxdj.do?method=bjfxdjDj";	
					parameter["str"]=str;
					parameter["sffx"]=escape(sffx);	
					parameter["bz"]=escape(bz);					
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,parameter,function(result){
						alertInfo(result,function(tag){
							if(tag=="ok"){
								onShow();
							}
						});
					});
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		
		function qxDj(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var sfdj = true;
				for (var i=0;i<array.length;i++) {
					if(jQuery(array[i]).parent().parent().find("td").eq(8).html()==""){
						sfdj = false;
						break;
					}
				}
				if(sfdj==true){
					confirmInfo("该操作将会取消返校登记信息，是否确定继续操作？",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var id = jQuery(array[i]).val();
							str +=id+"!@";
						}
						var parameter={}
						var url="rcsw_bjfxdj.do?method=bjfxdjQx";	
						parameter["str"]=str;							
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,parameter,function(result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									onShow();
								}
							});
						});
						jQuery.ajaxSetup({async:true});
					}
				});
				}else{
					alertInfo("所选记录未登记返校的无需取消！");
				}
			}else{
				alertInfo("请勾选需要取消的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		jQuery(function(){
			onShow();
		})
	</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>日常事务-请假管理-病假返校登记</a>
			</p>
		</div>


		<html:form action="/rcsw_bjfxdj" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li id="li_js">
							<a href="#" onclick="refreshForm('rcsw_qjgl_mygz_tea.do');return false;" class="btn_fh">
								返回
							</a>
						</li>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">查看详情</a>
						</li>
						<li>
							<a href="#" onclick="cxqjDiv('撤销请假');return false;" class="btn_zj">返校登记</a>
						</li>
						<li>
							<a href="#" onclick="qxDj();return false;" class="btn_sc">取消登记</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a></li>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=bjfxdjForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>病假返校登记</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th width="30%">
									<span class="red">*</span>是否返校
								</th>
								<td width="70%">
									<input type="radio" id="sffx" name="sffx" value="是" checked="checked"/>是
									<input type="radio" id="sffx" name="sffx" value="否"/>否
								</td>
							</tr>
							<tr id="tr_select_xn">
								<th width="25%">
									备注（限500字）
								</th>
								<td width="75%">
									<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLeng(this,1000);"
									rows='8'/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="cxyyDivSave();closeWindown()">
											保 存
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>