<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdblsq.js"></script>
	<script type="text/javascript">
	
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

		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}");

	});
	function saveSh(){
        var hdxs = jQuery("#hdxs").val();
        var jzlx = jQuery("#jzlx").val();
		if ((!!jQuery("#shjg").val()) == false || (!!jQuery("#hdlx").val() == false) || (!!jQuery("#hdxf").val() == false)){
			showAlert("请将必填项填写完整！");
			return false;
		}
        var ids = "xsxxlx-hdxs-hdlx-zbf";
        if("讲座" == hdxs){
            ids += "-zjrxm-zjrdw-zjrzc-zjrzw-jzjb";
        }
        if(!check(ids)){
            showAlert("请将带<font color='red'>*</font>的项目填写完整");
            return false;
        }
        var nlbqLen = jQuery("[name='nlbqs']:checked").length;
        if(nlbqLen > 3){
            showAlert("能力标签最多只能选三个，请确认！");
            return false;
        }
        if("课程" == hdxs){
            if(jzlx == null || jzlx == ''){
                showAlert("请选择课程级别！");
                return false;
            } else {
                var zxkclx = jQuery("#zxkclx").val();
                if(zxkclx == ""){
                    showAlert("请选择自选课程类型！");
                    return false;
                }
            }
        }
		var url = "hdgl_hdblsh.do?method=sbDgsh&type=save";
		ajaxSubFormWithFun("hdblsqshForm",url,function(data){
			 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
	}	
	</script>
</head>
<body>
	<html:form action="/hdgl_hdblsh" method="post" styleId="hdblsqshForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="splc" styleId="splc"/>
		<html:hidden name="rs" property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				 </table>
		
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>审核历史</span>
					</th>
				</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>			
			</tbody>	
			<thead>
				<tr>
					<th colspan="4">
						<span>活动信息</span>
					</th>
				</tr>
			</thead>
				<tbody>
				<tr>
					<th width="15%">
						学年
					</th>
					<td width="35%">
							${rs.xn}
					</td>
					<th>学期</th>
					<td>
							${rs.xqmc}
					</td>
				</tr>
				<tr>
					<th width="15%">
						活动名称
					</th>
					<td width="35%">
							${rs.hdmc}
					</td>
					<th>活动时间</th>
					<td>
							${rs.hdsj}
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
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>获得学分
						</th>
						<td colspan="3">
							<html:text property="hdxf" styleId="hdxf" maxlength="8" onblur="clearNoNum(this);return false;"/>
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
				</tbody>
				<thead>
				<tr>
					<th colspan="4">
						<span>审核信息</span>
					</th>
				</tr>
				</thead>
				<tbody>
					<tr>
						<th >
							<font color="red">*</font>审核结果
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*&nbsp;</font> 审核意见
							<br />
							<font color="red">(限200字)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=hdblsh&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
