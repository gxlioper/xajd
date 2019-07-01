<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		
		function searchRs(){
			allNotEmpThenGo('/xgxt/stu_sfzhinfo_jy.do?method=stuSfzhInfoJy');
		}

		var count=0;
		function modi(){
			count=0;
			var ck=document.getElementsByName("checkVal");
			for(var i=0;i<ck.length;i++){
				if(ck[i].checked){
					count++;
				}
			}
			if(count>0){
				tipsWindown("修改提示","id:tempDiv","350","200","true","","true","id");
			}else{
				alertInfo("请选择需要修改的记录！");
			}
		}

		function modi_submit(){
			var temp_num=0;
			var ck=document.getElementsByName("updateType");
			for(var i=0;i<ck.length;i++){
				if(ck[i].checked){
					temp_num++;
				}
			}
			if(temp_num==0){
				alertInfo("请选择需要更新的信息！");
				return false;
			}
			if(count>1000){
				alertInfo("选择的记录数不可以超过1000条！");
				return false;
			}
			confirmInfo("确定修改选中的"+count+"条记录吗？",function(ok){
					if("ok"==ok){
						allNotEmpThenGo('/xgxt/stu_sfzhinfo_jy.do?method=stuSfzhInfoJy&doType=update');
						BatAlert.showTips('正在修改，请稍等...');
					}else{
						closeWindown();
					}
				});
		}

		</script>
	</head>
	<body>

		<html:form action="/stu_sfzhinfo_jy" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					该处的的异常数据有：<br/>
					1.身份证号不正确<br/>
					2.身份证号包含的信息与系统中的籍贯、出生日期和性别不对应<br/>
					注意：
					1).籍贯格式：xxxxxx/xxxxxx/xxxxxx(代码必须是国标)<br/>
					2).出生日期格式：yyyy-MM-dd或yyyy/MM/dd或yyyyMMdd<br/>
					3).性别格式："'男'&'女'"或"'1'&'2'"(1:男;2:女)<br/>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="modi();return false;" class="btn_xg"> 修改 </a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue"></font>
					</span>
				</h3>

				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec_page()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td>
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsList" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="">
									<td>
										<input type="checkbox" name="checkVal" id="pkV" 
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										/>
									</td>
									<logic:iterate id="v" name="s" offset="0" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="4" length="1">
										<td <logic:iterate id="yczt_v" name="s" offset="10" length="1">
											<logic:equal value="是" name="yczt_v"> style="color: red"</logic:equal>
										</logic:iterate>>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="5" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="6" length="1">
										<td <logic:iterate id="yczt_v" name="s" offset="11" length="1">
											<logic:equal value="是" name="yczt_v"> style="color: red"</logic:equal>
										</logic:iterate>>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="7" length="1"><td><bean:write name="v"/></td></logic:iterate>
									<logic:iterate id="v" name="s" offset="8" length="1">
										<td <logic:iterate id="yczt_v" name="s" offset="12" length="1">
											<logic:equal value="是" name="yczt_v"> style="color: red"</logic:equal>
										</logic:iterate>>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="9" length="1"><td><bean:write name="v"/></td></logic:iterate>
									
<%--									<logic:iterate id="v" name="s" offset="0" length="10">--%>
<%--										<td nowrap>--%>
<%--											<bean:write name="v"/>--%>
<%--										</td>--%>
<%--									</logic:iterate>--%>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<!--分页显示-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<!--分页显示-->
					</div>
				</logic:notEmpty>
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">请选择需要更新的信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<input type="checkbox" name="updateType" value="xb"/>
								</th>
								<td>
									性别
								</td>
							</tr>
							<tr id="tr_tsyy">
								<th>
									<input type="checkbox" name="updateType" value="csrq"/>
								</th>
								<td>
									出生日期
								</td>
							</tr>
							<tr id="tr_bz">
								<th>
									<input type="checkbox" name="updateType" value="jg"/>
								</th>
								<td>
									籍贯
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="保存"  onclick="modi_submit()">
											确 定 
										</button>
										<button type="button" name="取消"  onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
