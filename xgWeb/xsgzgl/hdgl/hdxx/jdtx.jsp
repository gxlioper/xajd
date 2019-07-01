<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<%@ include file="/syscommon/v4_url.ini"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
			var stylePath = "<%=stylePath%>";
		</script>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="assets/css/style.css" rel="stylesheet">
		
		
		<script type="text/javascript" src="js/function.js"></script>
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src="js/comm/watermark.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css" media="all" />
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxx.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var jd = jQuery("#dqjd").val();
				for(var i = 1;i <= Number(jd); i++){
					jQuery("#jd_"+i).removeClass("level");
					jQuery("#jd_"+i).addClass("level finshed");
					if(i != Number(jd)){
						jQuery("#hr_"+i).removeClass("hr");
						jQuery("#hr_"+i).addClass("hr finshed");
					}
				}
			})
		</script>
		
	</head>

	<body>
		<div class="container" style="width:100%;">
			<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
				<html:hidden property="jdid" styleId="jdid" />
				<html:hidden property="hdid" styleId="hdid" />
				<input type="hidden" name="guid" value="${data.guid}" />
				<input type="hidden" name="bmlx" value="${data.bmlx}" />
				<input type="hidden" name="dwid" value="${data.dwid}" />
				<input type="hidden" name="sflx" value="${data.sflx}" />
				<div class="active-item row">
					<div class="col-md-8">
						<div class="pic"><img src="${data.hb}" style="width:97px;height:127px;"/></div>
						<div class="content">
							<p class="title">
								${data.hdmc}
							</p>
						<div class="tag"><span>${data.hdlxmc}</span></div>
							<div class="detail">
								<div>活动开始时间：${data.hdkssj}</div>
								<div>活动结束时间：${data.hdjssj}</div>
								<div>活动地点：${data.hddd}${data.thjdid}</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
					</div>
				</div>
				<div class="activity-progress clearfix">
					<logic:iterate id="jd" name="jdList" indexId="n">
							<logic:equal value="1" name="jd" property="dqjdbj">
								<input type="hidden" id="dqjd" value="${jd.jdsx}" />
							</logic:equal>						
							<div class="level" id="jd_${jd.jdsx}">
								<span class="number">${jd.jdsx}</span>
								<span class="font">
									${jd.jdmc}<logic:equal value="${data.thjdid}" name="jd" property="jdid"><logic:equal value="3" name="data" property="shzt">退回</logic:equal></logic:equal>								
								</span>
							</div>
							<logic:notEqual value="${data.jds - 1}" name="n">
								<div class="hr" id="hr_${jd.jdsx}"></div>
							</logic:notEqual>
					</logic:iterate>
				</div>
				<!-- 如果有退回阶段id -->
				<logic:notEmpty name="data" property="thjdid">
				<!-- 如果学生的审核状态为退回  -->
					<logic:equal value="3" name="data" property="shzt">
						<p class="active-title">审核意见</p>
						<textarea rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE" readonly="readonly">${data.thshyj}</textarea>
					</logic:equal>
				</logic:notEmpty>
				<logic:equal value="是" name="data" property="sflx">
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<div class="form-group ">
								<label for="" class="col-sm-4 control-label">
									<span class="red">*</span>名称
								</label>
								<div class="col-sm-6">
									<input maxlength="100" name="mc" value="${data.mc}" style="border:1px solid #DEDEDE"/>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="form-group ">
								<label for="" class="col-sm-4 control-label">
									<span class="red">*</span>级别
								</label>
								<div class="col-sm-6">
									<input maxlength="25" name="jb" value="${data.jb}" style="border:1px solid #DEDEDE"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<div class="form-group ">
								<label for="" class="col-sm-4 control-label">
									<span class="red">*</span>指导单位
								</label>
								<div class="col-sm-6">
									<input maxlength="25" name="zddw" value="${data.zddw}" style="border:1px solid #DEDEDE"/>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="form-group ">
								<label for="" class="col-sm-4 control-label">
									<span class="red">*</span>指导教师
								</label>
								<div class="col-sm-6">
									<input maxlength="10" name="zdjs" value="${data.zdjs}" style="border:1px solid #DEDEDE"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="form-group ">
								<label for="" class="col-sm-2 control-label">
									<span class="red">*</span>内容介绍
								</label>
								<div class="col-sm-10">
									<textarea name="nrjs" rows="8" maxlength="2000" style="width: 100%;border:1px solid #DEDEDE">${data.nrjs}</textarea>
								</div>
							</div>
						</div>
					</div>
				</logic:equal>
				<logic:notEqual value="是" name="data" property="sflx">
					<p class="active-title">心得体会</p>
					<textarea name="xdth" rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE">${data.xdth}</textarea>
				</logic:notEqual>
				<div class="upload" style="padding-top: 10px;">
					<p class="active-title">附件上传</p>
<%--					<p class="active-title">--%>
<%--						<html:hidden property="fjid" styleId="fjid"/>--%>
						<input type="hidden" id="fjid" name="fjid" value="${data.fjid}" /> 
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
											elementid : 'fjid'
											});
									});
								</script>
<%--					<p class="active-title">--%>
<%--						<span class="">附件上传</span>--%>
<%--						<span><a href="">预览</a></span>--%>
<%--						<span><a href="">下载</a></span>--%>
					</p>
				</div>
				<div class="active-btn">
					<button type="button" class="red-bg-btn" onclick="tj()">提交</button>
					<button type="button" class="green-bg-btn" onclick="jdfh()">返回</button>
				</div>
			</html:form>
		</div>
	</body>
</html>
