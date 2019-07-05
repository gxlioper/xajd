<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdblsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
                var hdxs = jQuery("#hdxs").val();
                if("课程" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                }else if("讲座" == hdxs){

                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("具体类型");
                    jQuery("#con_span").html("讲座介绍");

                    jQuery("#jzlxTr").hide();
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    jQuery("#jzlxTr").hide();
                    var hdlx = jQuery("#hdlx").val();
                    if ("活动"==hdxs && "4"==hdlx){
                        jQuery("#zysc").show();
                    }else {
                        jQuery("#zysc").hide();
                    }
                }
                kcjbChange();
			});
            function selectHd(){
                //var xh = jQuery("#xh").val();
                var goto = encodeURIComponent('${path}');
                showDialog("选择活动",800,500,"hdgl_hdblsq.do?method=getHdxxList&goto="+goto);
            }
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdblsq" method="post" styleId="hdblsqshForm" onsubmit="return false;">
		<html:hidden property="sqid" styleId="sqid"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>活动补录申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								学年
							</th>
							<td width="35%">
								${hdblsqshForm.xn}
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span><font color="red">*</font>活动名称</span>
							</th>
							<td width="35%">
								<html:text property="hdmc" styleId="hdmc" maxlength="20"/>
								<button class="btn_01" type="button" onclick="selectHd();">选择</button>
							</td>
							<th>
								<span><font color="red">*</font>活动时间</span>
							</th>
							<td>
								<html:text property="hdsj" styleId="hdsj" onfocus="showCalendar('hdsj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>主办方
							</th>
							<td colspan="3">
								<html:text property="zbf" styleId="zbf" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span><font color="red">*</font>线上or线下活动</span>
							</th>
							<td width="35%">
								<html:select property="xsxxlx" styleId="xsxxlx"  style="width:173px">
									<html:option value="">--请选择--</html:option>
									<html:option value="线上">线上活动</html:option>
									<html:option value="线下">线下活动</html:option>
								</html:select>
							</td>
							<th width="15%">
								<span><font color="red">*</font>活动形式</span>
							</th>
							<td width="35%">
								<html:select property="hdkclx" styleId="hdkclx"  style="width:173px">
									<html:option value="">--请选择--</html:option>
									<html:option value="考核类">考核类（多阶段）</html:option>
									<html:option value="参与类">参与类（单阶段）</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span><font color="red">*</font>活动性质</span>
							</th>
							<td width="35%">
								<html:select property="hdxs" styleId="hdxs" onchange="changeHdxs()" style="width:173px">
									<html:option value="">--请选择--</html:option>
									<html:option value="活动">活动</html:option>
									<html:option value="课程">课程</html:option>
									<html:option value="讲座">讲座</html:option>
								</html:select>
							</td>
							<th>
								<font color="red">*</font><span id="lx_span">活动类型</span>
							</th>
							<td>
								<html:select property="hdlx" styleId="hdlx" style="width:173px" onchange="changeHdlx()">
									<html:option value="">--请选择--</html:option>
									<html:options collection="hdlxList" labelProperty="hdlxmc" property="hdlxdm"/>
								</html:select>
							</td>
						</tr>

						<tr name="zjrxx_tr">
							<th>
								<font color="red">*</font>主讲人姓名
							</th>
							<td>
								<html:text property="zjrxm" styleId="zjrxm" maxlength="10"/>
							</td>
							<th >
								<font color="red">*</font>主讲人单位
							</th>
							<td >
								<html:text property="zjrdw" styleId="zjrdw" maxlength="20"/>
							</td>
						</tr>
						<tr name="zjrxx_tr">
							<th>
								<font color="red">*</font>主讲人职称
							</th>
							<td>
								<html:select property="zjrzc" styleId="zjrzc" style="width:173px">
									<html:option value="">--请选择--</html:option>
									<html:options collection="zjrzcList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th >
								<font color="red">*</font>主讲人职务
							</th>
							<td >
								<html:text property="zjrzw" styleId="zjrzw" maxlength="10"/>
							</td>
						</tr>
						<tr >
							<th>
								<font color="red">*</font>活动级别

							</th>
							<td colspan="3">
								<html:select property="jzjb" styleId="jzjb" style="width:173px">
									<html:option value="">--请选择--</html:option>
									<html:option value="校级活动">校级活动</html:option>
									<html:option value="院级活动">院级活动</html:option>
									<html:option value="自选活动">自选活动</html:option>
								</html:select>
							</td>

						</tr>
						<tr name="zjrxx_tr">
							<th>
								主讲人介绍
								<br><font color="red">(限100字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="2" property="zjrjs" styleId="zjrjs"
											   style="width:95%" onblur="checkLen(this,100);"/>
							</td>
						</tr>


						<tr id="jzlxTr">
							<th>
								<font color="red">*</font>课程级别
							</th>
							<td>
								<html:select property="jzlx" styleId="jzlx" style="width:173px" onchange="kcjbChange()">
									<html:option value="">---&nbsp;请选择课程级别&nbsp;---</html:option>
									<html:options collection ="jzlxList" property="jzlxdm" labelProperty="jzlxmc" />
								</html:select>
							</td>
							<th id="zxkclxTh" style="display: none;">
								<font color="red">*</font>自选课程类型
							</th>
							<td id="zxkclxTd" style="display: none;">
								<html:select property="zxkclx" styleId="zxkclx" style="width:173px">
									<html:option value="">---&nbsp;请选择自选课程类型&nbsp;---</html:option>
									<html:options collection ="zxckclxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								活动标签
							</th>
							<td colspan="3">
								<logic:iterate name="activityLabelList" id="bq">
									<%--<html:checkbox property="hdbqs" value="${bq.dm}">${bq.mc}</html:checkbox>--%>
									<label><html:multibox property="hdbqs" value="${bq.hdbqdm}"/>${bq.hdbqmc}</label>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>
								能力标签

							</th>
							<td colspan="3">
								<logic:iterate name="abilityLabelList" id="bq">
									<label><html:multibox property="nlbqs" value="${bq.nlbqdm}"/>${bq.nlbqmc}</label>
								</logic:iterate>
								<br><font color="red">（选择参加该活动能提升的综合能力，最多三项）</font>
							</td>
						</tr>
						<tr>
							<th width="15%">
								活动地点
							</th>
							<td width="35%">
								<html:text property="hddd" styleId="hddd" maxlength="20" style="width:173px"></html:text>
							</td>
							<th>参加类型</th>
							<td>
								<html:select property="cjlx" styleId="cjlx" style="width:50%">
									<html:option value="">--请选择--</html:option>
									<html:option value="个人">个人</html:option>
									<html:option value="组队">组队</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="15%">
								组队职务
							</th>
							<td width="35%">
								<html:select property="zdzw" styleId="zdzw" style="width:173px">
									<html:option value="">--请选择--</html:option>
									<html:option value="队长">队长</html:option>
									<html:option value="成员">成员</html:option>
								</html:select>
							</td>
							<th>活动职务</th>
							<td>
								<html:text property="hdzw" styleId="hdzw" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th width="15%">
								获得奖项
							</th>
							<td width="35%">
								<html:text property="hdjx" styleId="hdjx" maxlength="20"/>
							</td>
							<th>申请获得学分</th>
							<td>
								<html:text property="hdxf" styleId="hdxf" maxlength="20" onblur="clearNoNum(this);return false;"/>
							</td>
						</tr>
						<tr id="zysc">
							<th width="15%">
								志愿时长
							</th>
							<td colspan="3">
								<html:text property="zyxss" styleId="zyxss" maxlength="50"/>
							</td>
						</tr>
			      		<tr>
							<th>
								<span><font color="red">*</font>附件</span>
							</th>
							<td colspan="3">
								<html:hidden property="fjpath" styleId="fjpath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'fjpath'
										});
									});
								</script>						
							</td>
						</tr>
						<tr>
							<th>
								<span id="con_span">活动内容及心得</span>
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:25px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveHdblsq('update');">
										保存草稿
									</button>
									<button type="button" onclick="saveHdblsq('updatesubmit');">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

