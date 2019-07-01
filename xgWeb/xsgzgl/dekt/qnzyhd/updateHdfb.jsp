<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
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

		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src="js/comm/watermark.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css" media="all" />
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		
		
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script language="javascript" src="comm/editor/kindeditor.js"></script>
		<script language="javascript" src="comm/editor/zh_CN.js"></script>
		<script language="javascript" src="comm/editor/editor.js"></script>
		
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/qnzyhd/js/yfbhd.js"></script>


		<script type='text/javascript'>
			jQuery(function(){
				fzrAutoComplete();
				lxfsAutoComplete();
				bindUpload();
			})
		</script>
	</head>
	<body>
		<div class="issue-activity-page m-t20" style="margin-left: -100px;">
			<div class="container">
				<html:form action="/zyhd" method="post" styleId="qnzyhdForm" onsubmit="return false;">
					<input type="hidden" id="hdid" name="hdid" value="${data.hdid}"/> 
					<input type="hidden" id="tgrs" value="${data.ytgrs}"/>
					<input type="hidden" id="oldZgh" value="${data.hdfzr}" />
					<ul>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>活动名称</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-10 col-xs-9">
									<input type="text" class="form-control" id="hdmc" name="hdmc"
										maxlength="50" value="${data.hdmc}"/>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>服务类型</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-4 col-xs-9">
									<html:select property="fwlx" styleId="fwlx" value="${data.fwlx}">
										<html:option value="">---请选择---</html:option>
										<html:options collection="fwlxList" labelProperty="mc" property="dm"/>
									</html:select>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>基本服务工时</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-3 col-xs-9">
									<input type="text" class="form-control" id="jbfwgs" name="jbfwgs"
										onblur='checkNumForPeople(this);' maxlength="5"
										style="width: 100px;" value="${data.jbfwgs}"/>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>活动地点</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-10 col-xs-9">
									<input type="text" class="form-control" id="hddd" name="hddd"
										maxlength="50" value="${data.hddd}"/>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>服务对象</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-10 col-xs-9">
									<input type="text" class="form-control" id="fwdx" name="fwdx"
										maxlength="50" value="${data.fwdx}"/>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>限定人数</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-2 col-xs-9">
									<input type="text" class="form-control" id="xdrs" name="xdrs"
										 maxlength="5" onkeyup="checkNumForPeople(this);" onblur="checkRs(this);return false;"
										style="width: 100px;" value="${data.xdrs}"/>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>报名截止时间</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-5 col-xs-9">
									<input type="text" class="form-control" id="bmjzsj" name="bmjzsj"
										onfocus="showCalendar('bmjzsj','yyyy-MM-dd HH:mm')" maxlength="5"
										style="width: 200px;" value="${data.bmjzsj}"/>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>活动时间</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-10 col-xs-9 padding-lr0">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<input type="text" class="form-control" id="hdkssj"
											name="hdkssj" value="${data.hdkssj}"
											onfocus="showCalendar('hdkssj','yyyy-MM-dd HH:mm',true,'hdjssj');">
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 padding-lr0">
										<span>~</span>
									</div>
									<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 padding-l0">
										<input type="text" class="form-control" id="hdjssj"
											name="hdjssj" value="${data.hdjssj}"
											onfocus="showCalendar('hdjssj','yyyy-MM-dd HH:mm',false,'hdkssj');">
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>负责人姓名</span>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-10 col-xs-9 padding-lr0">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 padding-r0">
										<input type='hidden' id="zgh" name="hdfzr" value="${data.hdfzr}"/>
										<input type="text" class="form-control" id="xm" value="${data.fzrxm}"/>
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<div class="col-lg-5 col-md-5 col-sm-4 col-xs-5 padding-lr0">
											<label class="red">
												*
											</label>
											<span>负责人手机号</span>
										</div>
										<div class="col-lg-7 col-md-7 col-sm-8 col-xs-7 padding-lr0">
											<input type="text" class="form-control" id="hdfzrlxfs"
												name="hdfzrlxfs" onkeyup='checkInputData(this)'
												maxlength="15" value="${data.hdfzrlxfs}"/>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 m-t10">
										<p class="grey">
											只有负责人才能管理报名人员信息
										</p>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>组织部门</span>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<input type="text" class="form-control" id="zzbm" name="zzbm" value="${data.zzbm}"/>
								</div>
							</div>
						</li>
						<li>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="col-lg-3 col-md-3 col-sm-2 col-xs-3 text-right">
									<label class="red">
										*
									</label>
									<span>开放学院</span>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-10 col-xs-12">																		
										<logic:iterate id="t" name="xyList" indexId="index">
											<span style="display: inline-table">
												<font>
													<html:multibox property="xydms" value="${t.xydm}"></html:multibox>${t.xymc}
													<%if((index+1)%5==0){%> </br> <%}%>
												</font>
											</span>
										</logic:iterate>
								</div>
							</div>
						</li>
						<li>
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 upload-posters">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 text-right padding-lr0">
									<span>上传海报</span>
								</div>
								<div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 padding-r0">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" style="">
										<img style="width:100%;height:120px;" src="${data.fjpath}">
									</div>
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12 padding-l0 upload-require">
										<p class="grey" style="width: 370px">
											1.图片为不大于 4 M的jpg、gif、png、bmp格式图片；推荐尺寸 1024 * 768。
										</p>
										<p class="grey" style="width: 370px">
											2.温馨提示：一张漂亮的海报，会起到意想不到的效果，它会让你的活动显得更加有吸引力，更有品质；这将会为您的活动带来更多的用户报名。
										</p>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<input type="file" name="file" id="upload" style="display: none">
											<input type="hidden" id="fjpath" name="fjpath" value="${data.fjpath}" />
											<input type="hidden" id="oldPath" name="oldPath" value="${data.fjpath}" />
											<input type="hidden" id="lastPath" name="lastPath"/>
												<button type="button"
													class="btn btn-primary blue-bg-btn confirm-upload-btn m-t20"
													onclick="scForUpate();return false;">
													上传图片
												</button>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div
								class="col-lg-12 col-md-12 col-sm-12 col-xs-12 fill-activity-info">
								<div
									class="col-lg-2 col-md-2 col-sm-2 col-xs-12 text-right padding-lr0">
									<label class="red">
										*
									</label>
									<span>活动详情</span>
								</div>
								<div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 padding-r0">
									<textarea name="editorid" id="editorid" rows="6" name="editorid" id="editorid" style="height:100%;">${data.hdxq}</textarea>
									<div class="text-center m-t20">
										<button type="button"
											class="btn btn-primary blue-bg-btn issue-btn"
											onclick="updateBc();">
											提交发布
										</button>
										<button type="button"
											class="btn btn-primary blue-bg-btn issue-btn"
											onclick="fh();">
											返回
										</button>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</html:form>
			</div>
		</div>
		<%--		<!--文本编辑器 -->--%>
		<%--		<script type="text/javascript">--%>
		<%--			// 创建编辑器--%>
		<%--			KindEditor.create('textarea[name="content"]');--%>
		<%--		</script>--%>
	</body>
</html>

