<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		function modi(){
			if($("pjyj").value==""){
				alert("评价意见不能为空!");
				return false;
			}
			
			if($("fcmy").value=="" && $("bjmy").value=="" && $("my").value=="" && $("bmy").value=="" ){
				alert("满意程度不能为空!");
				return false;
			}
			refreshForm("/xgxt/zjxyRcsw.do?method=swffOnePjManage&doType=pj");
		}
		
		function checkRadio(){
			if($("radioValue").value==""){
				$("fcmy").checked=true;
			}
		}
	</script>
	</head>
	<body onload="checkRadio()">
		
		<html:form action="/zjxyRcsw" method="post">
			<input type="hidden" name ="xxdm" id = "xxdm" value="<bean:write name="xxdm" />"/>
			<input type="hidden" name="radioValue" id="radioValue" value="${rs.save_mycd}"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}
					<logic:equal name="doType" value="modi">
					-评价
					</logic:equal>
					<logic:equal name="doType" value="view">
					-查看
					</logic:equal>
					</a>
				</p>
			</div>
					<input type="hidden" id="doType" name="doType"
						value="${doType }" />
					<input type="hidden" id="userType" name="userType" value="${userType }" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="${pkValue }" />
					<input type="hidden" name="message" id="message" value="${message }"/>
					<logic:empty name="rs">
						<input type="hidden" name="add" id="add" value="yes"/>
					</logic:empty>
						<html:hidden property="save_ffsj" value="${rs.ffsj }"/>
						<html:hidden property="save_lx" value="${rs.lx }"/>
						<html:hidden property="save_xhzgh" value="${rs.zgh}"/>
						<html:hidden property="save_xmlx" value="${rs.xmlx }"/>
						<html:hidden property="save_xn" value="${rs.xn }"/>
						<html:hidden property="save_xq" value="${rs.xq }"/>
						<html:hidden property="save_nd" value="${rs.nd }"/>
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>评价</span></th>
			        			</tr>
			   				 </thead>
			   				 <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">
						        <logic:notEqual name="doType" value="view">
						        	"<span class="red">*</span>"为必填项</div>
								</logic:notEqual>
						          <div class="btn">
						          	<logic:present name="writeAble">
						          		<logic:notEqual name="doType" value="view">
										<button type="button" name="保存" onclick="modi();" id="buttonSave">
											保 存
										</button>
										</logic:notEqual>
									</logic:present>
									<logic:notEqual value="one" name="type">
										 <button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
									</logic:notEqual>						           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
			   				<tbody>
							<tr>
								<th width="16%">
									职工号（学号）
								</th >
								<td  width="34%">
									${rs.zgh }
								</td>
								<th  width="16%">
									姓名
								</th>
								<td  width="34%">
									${rs.xm }
									
								</td>
							</tr>
							<tr>
								<th align="right">
									<bean:message key="lable.xb" />名称
								</th>
								<td >
									${rs.xymc }
								</td>
								<th align="right">
									年度
								</th>
								<td >
									${rs.nd }
								</td>
							</tr>
							<tr>
								<th align="right">
									学年
								</th>
								<td>
									${rs.xn }
								</td>
								<th align="right">
									学期
								</th>
								<td>
									${rs.xqmc }
								</td>
							</tr>
							<tr>
								<th align="right">
									项目名称
								</th>
								<td>
									${rs.xmmc }
								</td>
								<th align="right">
									发放时间
								</th>
								<td>
									${rs.ffsj }
								</td>
							</tr>
							<tr>
								<th align="right">
									<logic:notEqual name="doType" value="view">
										<font color="red">*</font>
									</logic:notEqual>满意程度
								</th>
								<td colspan="3">
									<html:radio  name="rs" property="save_mycd" styleId="fcmy" value="fcmy" />非常满意
									<html:radio  name="rs" property="save_mycd" styleId="my" value="my"/>满意
									<html:radio  name="rs" property="save_mycd" styleId="bjmy" value="bjmy"/>比较满意
									<html:radio  name="rs" property="save_mycd" styleId="bmy" value="bmy"/>不满意
								</td>
							</tr>
							<tr>
								<th>
									<logic:notEqual name="doType" value="view">
									<font color="red">*</font>
									</logic:notEqual>
									评价意见
								</th>
								<td colspan="3">
									<html:textarea  name="rs" property="save_pjyj" styleId="pjyj" 
										style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' ></html:textarea>
								</td>
							</tr>
							</tbody>
						</table>
						</div>
						<logic:present name="result">
						<script>
							alert(''+$('message').value);
							if (window.dialogArguments) {
								window.close();
								window.dialogArguments.document.getElementById('search_go').click();
							}
						</script>
						</logic:present>
		</html:form>
	</body>
</html>
