<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		//增加楼栋信息
		function addLdxx(){
			var url="/xgxt/gyglGywh.do?method=ldxxwh&doType=add";
			showTopWin(url,600,480);
		}
		//修改楼栋信息
		function modiLdxx(){
			if (null == curr_row) {
				alert('请选择一行');
			} else {
				var lddm = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=ldxxwh&doType=update&lddm='+lddm;
				showTopWin(url,600,480);
			}
		}
		//删除楼栋信息
		function delLdxx(){
			var pkV=document.getElementsByName("checkVal");
			
			//判断是否有选中的记录
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				if(confirm("确定要删除选中的记录吗?")){
					var mklx=$("mklx").value;
					var url="/xgxt/gyglGywh.do?method=ldwhManage";
					url+="&doType=del&mklx="+mklx;
					refreshForm(url);
					hiddenMessage(true,true);
					BatAlert.showTips('正在操作，请稍等...');
				}else{
				
					return false;
				}
			}else{
				alert("没有需要保存的记录!");
				return false;
			}
		
			
		}
		
		//显示楼栋详细信息
		function showLdxxxx(){
			var pkValue=curr_row.getElementsByTagName('input')[0].value;
			var url="/xgxt/gyglGywh.do?method=ldxxxx&pkValue="+pkValue;
			refreshForm(url)
		}
		
		//显示楼栋详细信息
		function showLdxx(lddm){
			var pkValue=lddm;
			var url="/xgxt/gyglGywh.do?method=ldxxxx&pkValue="+pkValue;
			refreshForm(url)
		}
		
		//高级查询
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGywh.do?method=ldwhManage&mklx=${mklx }');
		}
		
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next==""){
				next=$("next_1").value;
			}
			
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else {
				next+="&doType=scjc";
				refreshForm(next);
			}
		}
		
		function qszdsc(){
			var pkV=document.getElementsByName("checkVal");
			
			//判断是否有选中的记录
			blog=false;
			var n=0;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
					n++
				}
			}
			if(!blog){
				alert("请选择一个需要自动生成寝室的楼栋!");
				return false;
			}
			if(n>1){
				alert("只能选择一个楼栋进行寝室自动生成!");
				return false;
			}
			
			refreshForm('/xgxt/gygl_gywh_zdsc.do?tzPath=ldwh');
		}
		
		
		</script>
	</head>
	<body onload="">

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 模块类型 -->
			<input type="hidden" id="mklx" name="mklx" value="${mklx}" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="path" value="gygl_gywh_ldwh.do" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="addLdxx();return false;" class="btn_zj">
								增加 </a>
						</li>
						<li>
							<a href="#" onclick="modiLdxx();return false;" class="btn_xg">
								修改 </a>
						</li>
						<li>
							<a href="#" onclick="delLdxx();return false;" class="btn_sc">
								删除 </a>
						</li>
						<li>
							<a href="#" class="btn_dr"
								onclick="impAndChkData();return false;">导入数据</a>
						</li>
						<li>
							<a href="#" class="btn_dc"
								onclick="setSearchTj();configureExportData();return false;">导出数据</a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a>
						</li>
						<li>
							<a href="#" class="btn_csh" onclick="qszdsc();return false;">寝室自动生成</a>
						</li>
					</ul>
				</div>

				<!-- new 版本 -->
				<logic:equal name="superSearch" value="yes">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</logic:equal>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">双击一条记录可以查看详细信息;</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />

									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="6"
										indexId="index">
										<td id="<bean:write name="tit" property="en"/>" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="showLdxxxx()">
										<td>
											<input type="checkbox" name="checkVal" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="2" length="4">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<!-- 楼栋寝室数统计 -->

										<td>
											<a href="#"
												onclick="showLdxx('<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>');return false;"><font
												color="blue"><U>
												<logic:iterate id="v" name="s" offset="6" length="1">
														<bean:write name="v" />
												</logic:iterate></U>
												
												</font>
											</a>
										</td>

									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=gyglGywhForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="/comm/delMessage.jsp"%>
		<logic:equal name="result" value="true">
			<script>
			alert("操作成功!");	
		</script>
		</logic:equal>
	</body>
</html>
