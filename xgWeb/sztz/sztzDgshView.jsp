<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	    <script type="text/javascript">
			function setThjsr(shzt){
				
				if (shzt == '退回'){
					jQuery('#thjsrText').attr('style','display:block');
					jQuery('#thjsrZj').attr('style','visibility:');
					jQuery('#thjsr').attr('disabled',false);
				} else {
					jQuery('#thjsrText').attr('style','display:none');
					jQuery('#thjsrZj').attr('style','visibility:hidden');
					jQuery('#thjsr').attr('disabled',true);
				}
			
			}
			
			function tcsdxf(obj){
				jQuery.getJSON('sztz.do?method=tcsdxf',{jxdm:obj.value,xmid:jQuery("#xmid").val()},function(data){
					jQuery('#sdxf').empty();
					if(data != null && data.length != 0){
						jQuery('#sdxf').val(data[0].jxfs);
					}
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<html:hidden property="shlcid" value="${rs.shlcid }"/>	
			<input type="hidden" name="id" value="${rs.id }"/>
			<input type="hidden" name="pkValue" value="${rs.id }"/>
			<input type="hidden" name="pkValue" value="${rs.xmid }" id="xmid"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="saveUpdate('sztz.do?method=sztzDgshSave&sftj=是','xh-shzt-temp_xmid')">
										提 交 
									</button>
									<button onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									申请信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								${rs.xh }
			            	</td>
			            	<th width="16%">
			            		项目名称
							</th>
							<td width="34%">
								${rs.xmmc }
			            	</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								学年
							</th>
							<td>
								${rs.xn }
							</td>
						</tr>
						
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								学期
							</th>
							<td>
								${rs.xqmc }
							</td>
						</tr>
						
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								所属科目
							</th>
							<td>
								${rs.kmmc }
							</td>
						</tr>
						
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								核心能力
							</th>
							<td>
								${rs.hxnlmc }
							</td>
						</tr>
						
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								主办方
							</th>
							<td>
								${rs.zbf }
							</td>
						</tr>
						
						<tr>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								负责人
							</th>
							<td>
								${rs.fzr }
							</td>
						</tr>
						<tr>
							<th>
								举办时间
							</th>
							<td>
								${rs.jbkssj } 至 ${rs.jbjssj }
							</td>
							<th>
								基础分
							</th>
							<td>
								${rs.jcf }
							</td>
						</tr>
						<tr>
							<th>
								参与角色
							</th>
							<td>
								${rs.cyjs }
							</td>
							<th>
								是否重修
							</th>
							<td>
								${rs.sfcx }
							</td>
						</tr>
						
						<tr>
							<th>
								成果描述<br/>
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.cgms }
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
							</th>
							<td colspan="3"  style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<td colspan="4">
								<span>
									审核信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>审核人</th>
							<td>
								${userNameReal }
							</td>
							<th>审核时间</th>
							<td>
								${sysdate }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核状态
							</th>
							<td>
								<html:select property="shzt" style="width:120px" styleId="shzt" 
											 value="${rs.shzt }" onchange="setThjsr(this.value)">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th>
								<span id="thjsrText" style="display:none;">
									<font color="red">*</font>接收人
								</span>
							</th>
							<td>
								<span id="thjsrZj" style="visibility:hidden">
									<html:select property="thjsr" styleId="thjsr" disabled="true">
										<html:option value="Applicant">申请人</html:option>
										<logic:present name="xtgwList">
											<html:options collection="xtgwList" property="id" labelProperty="mc"/>
										</logic:present>
									</html:select>
								</span>
							</td>
						</tr>
						
						<tr>
							<th>所获奖项</th>
							<td>
								<html:select property="shjx" value="${rs.shjx }" onchange="tcsdxf(this);">
									<html:option value=""></html:option>
									<html:options collection="xmjxList" property="jxdm" labelProperty="jxmc"/>
								</html:select>
							</td>
							<th>所得学分</th>
							<td>
								<html:text property="sdxf" styleId="sdxf" maxlength="10" onblur="checkInputNum(this)" value="${rs.sdxf }"></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								审核意见
								<br/>
								<font color="red"><限500字></font>
							</th>
							<td colspan="3"><html:textarea property="shyj" style="width:95%" rows="5" onblur="chLeng(this,500)" value="${rs.shyj }"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alertInfo('${message}',function(){
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					});
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
