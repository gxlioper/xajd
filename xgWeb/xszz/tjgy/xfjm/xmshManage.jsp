<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/messageFunctaion.js"></script>
		<script type="text/javascript">
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('tjgy_xfjm.do?method=xmshManage');
			}
		
			function plsh(){
				tipsWindown("系统提示","id:shDiv","350","200","true","","true","id");
			}
		
			function tjsh(shjg){
				var flg = true;
				jQuery.ajaxSetup({async:false});
				//兼得 验证
				if (shjg == '通过' && 'xy' == '${userType}'){
					var xhArr = new Array();
					var xmidArr = new Array();
					
					var selChe = jQuery('input[name=primarykey_cbv]:checked');
					for (var i = 0 ;i < selChe.length ; i++){
						var xh = jQuery('input[name=h_xh]',selChe.eq(i).parents("tr:first")).val();
						
						if (jQuery.inArray(xh,xhArr) > -1){
							alertInfo('您要审核通过的减免项目中有重复学生，请确认！');
							return false;
						} else {
							xhArr[i] = xh;  
							xmidArr[i] = jQuery('input[name=h_xmid]',selChe.eq(i).parents("tr:first")).val();
						}
					}
					
					for (var i = 0 ; i < xhArr.length ; i++){
						jQuery.post('tjgy_xfjm.do?method=checkSfktg',{xh:xhArr[i],xmid:xmidArr[i]},function(data){
							if (data == 'false'){
								alertInfo('您选择的记录中学号为:'+xhArr[i]+'的学生已经有其它学费减免项目审核通过，请确认！');
								flg = false;
							}
						})
					}
				}
				
				if (flg){
					confirmInfo('您确定要批量提交审核为"'+shjg+'"吗？',function(t){
						if (t=='ok'){
							var shyj = jQuery('#shyj').val();
							refreshForm('tjgy_xfjm.do?method=plsh&shzt='+shjg+'&shyj='+shyj);
						}
					})			
				}
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>

	<body >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		
		<html:form action="/tjgy_xfjm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
								<li>
									<a href="#" class="btn_sh"
										onclick="showAuditingWindow('primarykey_cbv','tjgy_xfjm.do?method=xmdgsh',750,580);return false;"
										id="btn_qx">审核</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
					<logic:notEmpty name="rs">
							<font color="blue">双击可以查看详细;</font>
					</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" alt=""/>
									
								</td>
								<td onclick="tableSort(this)">
									减免项目
								</td>
								<td onclick="tableSort(this)">
									学号
								</td>
								<td onclick="tableSort(this)">
									姓名
								</td>
								<td onclick="tableSort(this)">
									年级 
								</td>
								<td onclick="tableSort(this)">
									<bean:message key="lable.xb" />
								</td>
								<td onclick="tableSort(this)">
									班级
								</td>
								<td onclick="tableSort(this)">
									<bean:message key="lable.xb" />审核
								</td>
								<td onclick="tableSort(this)">
									学校审核
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" 
										style="cursor:hand"
										ondblclick="showTopWin('tjgy_xfjm.do?method=xmdgsh&doType=view&pkValue=${s.xmid }${s.xh}${s.xn}','750','580');return false;"
									>
										<td>
											<input type="hidden" name="h_xh" value="${s.xh}"/>
											<input type="hidden" name="h_xmid" value="${s.xmid}"/>
											<input type="checkbox" name="primarykey_cbv" value="${s.xmid }${s.xh}${s.xn}"
												<logic:equal value="xy" name="userType"><logic:notEqual value="未审核" name="s" property="xxsh">disabled="true"</logic:notEqual></logic:equal>
											/>
										</td>
										<td>${s.xmmc }</td>
										<td>${s.xh }</td>
										<td>${s.xm }</td>
										<td>${s.nj }</td>
										<td>${s.xymc }</td>
										<td>${s.bjmc }</td>
										<td>${s.xysh }</td>
										<td>${s.xxsh }</td>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xfjmForm"></jsp:include>
			</div>
			
			
			<!-- 批量审核弹出层 -->
			<div id="shDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请填写审核信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									审核意见<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<textarea rows="5" style="width:95%" onblur="chLeng(this,500)" name="shyj" id="shyj"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" name="通过" onclick="tjsh('通过');">
											通过
										</button>
										<button type="button" name="不通过" onclick="tjsh('不通过');">
											不通过
										</button>
										<button type="button" name="取消" onclick="closeWindown();">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
