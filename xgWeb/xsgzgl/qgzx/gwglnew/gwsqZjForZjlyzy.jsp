<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsqForZjlyzy.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script type="text/javascript">
		
		
		
			jQuery(document).ready(function(){
				changeYxssz();
				var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();//是否设置酬金上线 12309默认no
				//基础配置 设置的薪酬上限
				var gwzgcjsx=jQuery("#gwzgcjsx").val();//酬金上线金额
				var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();//用人单位申请岗位时可否更改岗位酬金上限
				//岗位设置酬金上限
				var gwcjsx=jQuery("#gwcjsx").val();
				//如果此岗位未设置
				if(gwcjsx==""){
					jQuery("#gwcjsx").val(gwzgcjsx);
					jQuery("#gwcjsxh").text(gwzgcjsx);
				}
				if("no"==sfsdgwcjsx){
					jQuery("#gwcjsxTr").hide();
				}else{
					jQuery("#gwcjsxTr").show();
					if("no"==sfkgggwcjsx){
						jQuery("#gwcjsx").hide();
						jQuery("#gwcjsxh").show();
						jQuery("#sxcolor").hide();
					}else{
						jQuery("#gwcjsx").show();
						jQuery("#gwcjsxh").hide();
						jQuery("#sxcolor").show();
					}
				}
			});
			function selTea(){
				showDialog("选择管理员", 770, 520, "stglStsq.do?method=getTea")	
			}
		</script>
	</head>
	<body style="width:100%">
		<!-- 浙江旅游职业学院个性化页面    xxdm=12867 -->
		<html:form action="/qgzx_gwglnew" method="post" styleId="form">
		<input type="hidden"  id="xxdm" value="${xxdm}"/>
		<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
		<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
		<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
		<input type="hidden"  id="qgzq" name="qgzq" value="${rs.qgzq}" />
		<input type="hidden" id="userDep" value="${bmdm}" />
		<input type="hidden" id="sfxy" value="${sfxy}" /><%--
		<input type="hidden" name="gwshrxm" id="gwshrxm" value="${zxsInfo.xm}"/>
		--%><div style='tab；width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>岗位申请人信息</span>
							</th>
						</tr>
						</thead>
						<tbody id="tbody_jbxx" name="tbody_jbxx">
						<tr>
							<th width="16%">
								部门
							</th>
							
							<td width="34%" >
								<input type="hidden" id="yrbm" name="yrbm" value="${rs.yrbm }"/>
								${rs.yrdwmc }
							</td>
							<th width="16%">
								<font color="red">*</font>联系人
							</th>
							<td width="34%">
								<input type="text" id="lxr" name="lxr" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								申报时间
							</th>
							<td width="34%" >
								<input type="hidden" id="sqsj" name="sqsj" value="${sqsj }"/>
								${sqsj }
							</td>
							<th width="16%">
								<font color="red">*</font>联系电话
							</th>
							<td width="34%">
								<input type="text" id="lxPhone" name="lxPhone" onblur="checkPhone(this);"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								需求岗位数
							</th>
							<td width="30%">
								<input type="text" id="xqgws" maxlength="50" style="width:200px" onclick="checkXqrs(this);return false;"
									onblur="changeGwxx();"/>
							</td>
						</tr>
				</table>
					
				 <table id="gwsqxx" width="100%" border="0" class="formlist">				 
					 <thead>
							<tr>
								<th colspan="5">
									<span>岗位申请信息
										<a onclick="zjGwxx('jiahao');" href="javascript:void(0);">
											<img src="images/knsrd/jiahao.gif" />
										</a>
									</span>
								</th>
							</tr>
					</thead>
					<logic:present name="jlList">						
							<logic:iterate name="jlList" id="jl" indexId="s">
								<tbody id="t_${s}" name='xgTbody'>
									<input type="hidden" value="${gw.id}" />
									<tr>
										<th width="16%">
											<font color="red">*</font>岗位名称
										</th>
							
										<td width="34%">
								
											<html:text property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
										</td>
										<th width="16%">
											需求人数
										</th>
										<td width="34%">
											<html:text property="xqrs" styleId="xqrs" maxlength="50" style="width:200px"></html:text>
										</td>
									</tr>
									<tr>
										<th><font color="red">*</font>岗位类型</th>
										<td>
											<html:select property="gwxzdm" styleId="gwxzdm">
												<html:option value=""></html:option>
												<html:option value="固定岗">固定岗</html:option>
												<html:option value="实习岗">实习岗</html:option>
											</html:select>
										</td>
										<th width="16%">
											<font color="red">*</font>困难生数
										</th>
										<td width="34%">
											<html:text property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)" value="0"></html:text>
										</td>
										<td rowspan="5" style="text-align: center"><a href='javascript:void(0)' onclick='scXg("t_${s}")'>删除</a></td>
									</tr>
									<tr id="zxsInfo">
										<th><span class="red">*</span>岗位审核人</th>
										<td>
											<input type="text" id="gwshr" class="gwshr" name="gwshr" style="width:120px;" readonly="readonly"/>
											<button type="button" onclick="showDialog('教师选择',680,480,'qgzx_gwglnew.do?method=showFdysDg');return false;" class="btn_01" id="buttonFindStu">
												选择
											</button>
										</td>
										<th><span class="red">*</span>岗位审核人姓名</th>
										<td width="30%"  class="xm">
											<input type="text" id="gwshrxm" class="gwshrxm" name="gwshrxm" style="width:120px;" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<th><span class="red">*</span>岗位要求</th>
										<td colspan="3" >
											<html:textarea  property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
												rows='6' />
										</td>
									</tr>
									<tr>
										<th align="right" >
											<font color="red">*</font>工作内容<br/><font color="red">(限500字)</font>
										</th>
										<td colspan="3" >
											<html:textarea property="gznr" style="width:97%" rows="5" onblur="chLengs(this,500);" styleId="gznr" />
										</td>
									</tr>
								</tbody>
							</logic:iterate>					
					</logic:present>								
				 </table>
			</div>
			<div style='height: 50px'>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
										<button type="button" onclick="zjBcGwsqForZjlyzy('submit')">
											提交
										</button>
										<button type="button" onclick="Close();return false;">
											关 闭
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
					
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

