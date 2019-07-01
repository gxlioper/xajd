<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveSyddk(){
			var url="zgdzdx_xszz.do?method=syddkUpdate_whtl&doType=save";

			if($("xh") && $("xh").value==""){
				alertInfo("学号不能为空!");
				return false;
			}
			if($("ffrq").value!=""&&$("dqrq").value!=""){
				if($("ffrq").value>=$("dqrq").value){
					alertInfo("发放日期必须小于到期日期！");
					return false;
				}
			}
			jQuery.post('zgdzdx_xszz.do?method=checkXszz',{xh:jQuery('#xh').val()},function(data){
				if(data&&data!="0"){
					alertInfo("该助学记录已存在！");
					return false;
				}else{
					showTips("保存中,请稍候...");
					refreshForm(url);
				}
			});
		}
		
		function updateSyddk(){
			var url="zgdzdx_xszz.do?method=syddkOne_whtl&doType=save";
			if($("ffrq").value!=""&&$("dqrq").value!=""){
				if($("ffrq").value>=$("dqrq").value){
					alertInfo("发放日期必须小于到期日期！");
					return false;
				}
			}
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/zgmsxy_xszz" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zgdzdx_xszz.do?method=syddkUpdate_whtl" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='view_xsjbxx' />
			<input type="hidden" name="pkValue" id="pkValue" value='${rs.pkValue}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<!-- 隐藏域 -->
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" class="button2"  id="btn_bc" onclick="saveSyddk()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<logic:notEqual name='rs' property="sfsh" value="ysh">
										<button type="button" class="button2"   id="btn_bc" onclick="updateSyddk()">
											保 存
										</button>
										</logic:notEqual>
									</logic:equal>
									<button type="button" class="button2"  onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead >
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
										选 择
									</button>
								</logic:equal>
							</td>
							<th style="width:16%">
								学年
							</th>
							<td style="width:34%">
								<logic:equal name="doType" value="add">
									${xn}
									<html:hidden property="xn" value="${xn}"/>
								</logic:equal>
								
								<logic:equal name="doType" value="update">
									${xn}
									<html:hidden name="rs" property="xn"/>
								</logic:equal>
								<logic:equal name="doType" value="view">
									${xn}
									<html:hidden name="rs"  property="xn" />
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>
							
						</tr>
						
						<tr>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								身份证号
							</th>
							<td style="width:34%">
								${rs.sfzh}
							</td>
							<th style="width:16%">
								学制
							</th>
							<td style="width:34%">
								${rs.xz}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								入学年份
							</th>
							<td style="width:34%">
								<%=(request.getAttribute("rs")==null||((HashMap<String,String>)request.getAttribute("rs")).get("rxrq")==null
									||((HashMap<String,String>)request.getAttribute("rs")).get("rxrq").length()<5)
								?"":((HashMap<String,String>)request.getAttribute("rs")).get("rxrq").substring(0,4)%>
							</td>
							<th style="width:16%">
								毕业日期
							</th>
							<td style="width:34%">
								${rs.bysj}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								合同金额
							</th>
							<td style="width:34%">
								<html:text name="rs" property="htje" styleId="htje" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="10"></html:text>
							</td>
							<th style="width:16%">
								贷款次数
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dkcs" styleId="dkcs"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')" maxlength="2"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								省资助中心
							</th>
							<td style="width:34%">
								
								<html:text name="rs" property="szzzx" maxlength="75"/>
							</td>
							<th style="width:16%">
								县资助中心
							</th>
							<td style="width:34%">
								<html:text name="rs" property="xzzzx" maxlength="75"/>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								合同编号
							</th>
							<td style="width:34%">
								<html:text name="rs" property="htbh" maxlength="20"/>
							</td>
							<th style="width:16%">
								合同状态
							</th>
							<td style="width:34%">
								<html:text name="rs" property="htzt" maxlength="10"></html:text>
							</td>
						</tr>		
						<tr>
							<th style="width:16%">
								代理机构
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dljg" maxlength="75"/>
							</td>
							<th style="width:16%">
								代理结算网点
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dljswd" maxlength="75"></html:text>
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								个人账号
							</th>
							<td style="width:34%">
								<html:text name="rs" property="grzh" maxlength="20"/>
							</td>
							<th style="width:16%">
								贷款期限
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dkqx" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								发放日期
							</th>
							<td style="width:34%">
								<html:text name="rs" property="ffrq" styleId="ffrq" onclick="return showCalendar(this.id,'y-mm-dd');"
								 maxlength="10"/>
							</td>
							<th style="width:16%">
								到期日期
							</th>
							<td style="width:34%">
								<html:text name="rs" property="dqrq" styleId="dqrq"  onclick="return showCalendar(this.id,'y-mm-dd');"
								 maxlength="10"></html:text>
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								联系电话
							</th>
							<td style="width:34%">
								<html:text name="rs" property="lxdh" styleId="lxdh"  onkeyup="this.value=this.value.replace(/[^\d|-]/g,'')" maxlength="12"></html:text>
							</td>
							<th style="width:16%">
								备注
							</th>
							<td style="width:34%">
								<html:text name="rs" property="bz" maxlength="100"/>
							</td>
						</tr>		
					</tbody>
				</table>
			</div>
			<logic:present name="message">
			<script language="javascript">
			alertInfo('${message}',function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
				});
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
