<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function getSqlTj(){
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var sqlTj = " and xn = '"+xn+"' ";
			if(xq){
				sqlTj += " and xq = '"+xq+"' ";
			}else{
				sqlTj += " and xq = 'on' ";
			}
            sqlTj += " and xzdm = '${xzdm}' ";
			return sqlTj;
		}
		function changeXmmc(){
			// 重置
			jQuery("#xmmc").val("");
			jQuery("#xmje").val("");
			//取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc																							
			var autoSetting = {
				dataTable:"xg_pjpy_new_pjxmb",
				dataField:"xmmc",
				dataFieldKey:"xmje",
				dataFieldKeyId:"xmje",
				sqlTj: getSqlTj,
				scrollHeight:135										
			}
			// 模糊搜索下拉【项目名称】
			jQuery("#xmmc").setAutocomplete(autoSetting);
		}
		jQuery(function(){																								
			changeXmmc();
		});
		</script>
		
	</head>
		
	<body>
		
		<html:form action="/xpj_pjjg" method="post" styleId="pjxmjgForm" onsubmit="return false;">
			<input type="hidden" id="cpnj" name="cpnj" value="${jbxx.nj}"/>
			<input type="hidden" id="cpxydm" name="cpxydm" value="${jbxx.xydm}"/>
			<input type="hidden" id="cpxymc" name="cpxymc" value="${jbxx.xymc}"/>
			<input type="hidden" id="cpzydm" name="cpzydm" value="${jbxx.zydm}"/>
			<input type="hidden" id="cpzymc" name="cpzymc" value="${jbxx.zymc}"/>
			<input type="hidden" id="cpbjdm" name="cpbjdm" value="${jbxx.bjdm}"/>
			<input type="hidden" id="cpbjmc" name="cpbjmc" value="${jbxx.bjmc}"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>评奖项目申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td
								<logic:notEqual value="1" name="pjzq"> colspan="3" </logic:notEqual>>

								<html:select property="xn" styleId="xn" style="width:130px" onchange="changeXmmc();">
									<html:options collection="xnList" labelProperty="xn"
										property="xn" />
								</html:select>
							</td>
							<logic:equal value="1" name="pjzq">
								<th>学期</th>
								<td>
									<html:select  property="xq" styleId="xq" style="width:130px" onchange="changeXmmc();">
									<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
									</html:select>
								</td>
							</logic:equal>
					    </tr>
					    <tr>
							<th><span class="red">*</span>项目类型</th>
							<td>
								<html:select  property="lxdm" styleId="lxdm" style="width:130px">
								<html:options collection="xmlxList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>

							<th>项目性质</th>
							<td>
								<%--<html:hidden property="xzdm"></html:hidden>--%>
								<logic:equal name="xzdm" value="2">
									<input type="hidden" name="xzdm" value="2"/>
								</logic:equal>
								<logic:notEqual name="xzdm" value="2">
									<input type="hidden" name="xzdm" value="1"/>
								</logic:notEqual>
								<logic:equal name="xzdm" value="2">
									表彰奖励
								</logic:equal>
								<logic:notEqual name="xzdm" value="2">
									奖学金
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>项目名称</th>
							<td>
								<html:text  property="xmmc" styleId="xmmc"  style="width:180px;" maxlength="20"  styleClass="text_nor"></html:text>
							</td>
							<th>金额</th>
							<td>
								<html:text  property="xmje" styleId="xmje"  style="width:120px;" maxlength="5"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<logic:equal value="13943" name="xxdm">
						<tr>
							<th>颁奖单位</th>
							<td colspan="3">
								<html:text  property="bjdw" styleId="bjdw"  style="width:180px;" maxlength="100"  styleClass="text_nor" ></html:text>
							</td>
						</tr>
						</logic:equal>
					    <tr>
							<th>
								附件信息
								<logic:equal value="10279" name="xxdm">								
									</br>								
									<font color="red">（新生奖和专业奖必须上传证书及材料）</font>
								</logic:equal>
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : ${xxdm=='12713'?10:3},
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//最大文件大小 单位M
											maxsize: ${xxdm=='12713'?30:10},
											//存放附件的隐藏域的id
											elementid : 'ylzd5'
											});
										
										//浙江同济科技职业技术学院，追加2条附件上传提示
										if(${xxdm=='12647'}){
											var tipsPrepend = "★ 文件请根据获奖情况命名，如：优秀毕业生、积极分子佐证材料照片。</br>"+
															  "★ 如需上传多个文件，请成功选择一个后，再次点击选择文件继续。</br>";
											jQuery('#tips').prepend(tipsPrepend);
										}

										//上海戏剧学院，增加1条附件上传提示
										if(${xxdm=='10279'}){
											var html = "</br><span style='margin-left:36px'>4.上传文件以学号命名！</span>";
											jQuery('#tips').append(html);
										}
									});
								</script>
							</td>
						</tr>
					    <tr>	
							<th>
							<span class="red">*</span>申请时间
							</th>
							<td colspan="3">
								<html:text property="sqsj" name="xpjPjjgModel" styleId="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" readonly="true" ></html:text>
							</td>
					    </tr>
					    <%--徐州医药高等专科个性化字段--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
							<th>
								曾受何奖励
								<br />(限500字)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<html:textarea property="djjl" styleId="djjl" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th><span class="red">*</span>申请理由</th>
							<td colspan="3">
								<select name="sqlyyy" id="sqlyyy">
									<option value="A">家庭遭受自然灾害</option>
									<option value="B" >家庭遭受突发意外事件</option>
									<option value="C" >家庭成员因残疾</option>
									<option value="D" >年迈而劳动能力弱情况</option>
									<option value="E" >家庭适龄就学子女较多</option>
									<option value="F" >家庭成员失业</option>
									<option value="G" >家庭欠债</option>
									<option value="H" >其他</option>
									<option value="I" >建档立卡家庭</option>
									<option value="J" >低保</option>
								</select>
							</td>
						</tr>
					    <tr>
					    <logic:notEqual value="12056" name="xxdm">
							<th>
								<logic:equal value="70002" name="xxdm">
									主要事迹
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									具体理由
								</logic:notEqual>
								<br /><font color="red">(限100字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,100);"/>
							</td>
						</logic:notEqual>
						<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由
								<br /><font color="red">(限150-250字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLenBtw(this,150,250);"/>
							</td>
						</logic:equal>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>

