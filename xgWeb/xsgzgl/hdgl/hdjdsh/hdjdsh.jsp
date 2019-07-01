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
	<script type="text/javascript" src="xsgzgl/hdgl/js/hdjdsh.js"></script>
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
			tableLoad("hdcyTable");
        })
	</script>

	<style type="text/css">
		span.xzjd {
			border: 2px solid #ee1bff;
		}
		.activity-progress .hr {
			width: 10%;
			height: 3px;
			margin-top: 25px;
		}
	</style>

</head>

<body>
	<html:form action="/hdgl_hdjdsh" method="post" styleId="hdxxForm" onsubmit="return false;">
		<html:hidden property="hdid" styleId="hdid" />
		<input type="hidden" id="bmlx" name="bmlx" value="${data.bmlx}"/>
		<input type="hidden" id="jdid" name="jdid" value="${hdjdInfo.jdid}"/>
		<input type="hidden" id="sfdfhd" name="sfdfhd" value="${sfdfhd}"/>
		<input type="hidden" id="sfsldf" name="sfsldf" value="${hdjdInfo.sfsldf}"/>
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
						<div>活动地点：${data.hddd}</div>
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
				<div class="level" id="jd_${jd.jdsx}" style="width: 8%;">
					<logic:equal value="${hdjdInfo.jdid}" name="jd" property="jdid">
						<span class="number xzjd" jdid="${jd.jdid}">${jd.jdsx}</span>
					</logic:equal>
					<logic:notEqual value="${hdjdInfo.jdid}" name="jd" property="jdid">
						<span class="number" jdid="${jd.jdid}">${jd.jdsx}</span>
					</logic:notEqual>
					<span class="font">${jd.jdmc}</span>
					<div class="font" style="color: #288de2;">
						<%--当前阶段类型是老师--%>
						<logic:equal value="2" name="jd" property="jdlx">
							<%--阶段顺序小于等于当前活动阶段顺序--%>
							<logic:lessEqual value="${currentJdsx}" name="jd" property="jdsx">
								<%--上一阶段类型为学生--%>
								<logic:equal value="1" name="jd" property="prejdlx">
									<logic:greaterThan value="0" name="jd" property="dclrs1">
										<a href="javascript:void (0)" jdid="${jd.jdid}" onclick="changeDcyjd(this)">
											<font color="red">${jd.dclrs1}</font> 条待处理
										</a>
									</logic:greaterThan>
								</logic:equal>
								<%--上一阶段类型为老师--%>
								<logic:equal value="2" name="jd" property="prejdlx">
									<logic:greaterThan value="0" name="jd" property="dclrs2">
										<a href="javascript:void (0)" jdid="${jd.jdid}" onclick="changeDcyjd(this)">
											<font color="red">${jd.dclrs2}</font> 条待处理
										</a>
									</logic:greaterThan>
								</logic:equal>
							</logic:lessEqual>
						</logic:equal>
					</div>
				</div>
				<logic:notEqual value="${data.jds - 1}" name="n">
					<div class="hr" id="hr_${jd.jdsx}"></div>
				</logic:notEqual>
		</logic:iterate>
		</div>

		<div class="apply-management-page m-t20">
			<div class="top-content" style="">

			</div>
				<div class="table-responsive">
					<table id="hdcyTable" class="table table-bordered text-center">
						<thead>
						<tr>
							<logic:equal value="0" name="data" property="bmlx">
								<th width="5%"><input type="checkbox" onchange="selectAll1(this);return false;"></th>
								<th width="5%">队伍序号</th>
								<th width="10%">队长学号</th>
								<th width="8%">队长姓名</th>
								<th width="10%">提交状态</th>
								<th width="10%">审核状态</th>
								<logic:equal value="true" name="sfdfhd">
									<th width="10%">总分(最新阶段打分数)</th>
									<th width="10%">平均分</th>
									<th width="10%">已打分专家数</th>
									<logic:notEqual value="1" name="hdjdInfo" property="sfsldf">
										<th width="10%">当前环节总队伍数</th>
									</logic:notEqual>
									<th width="10%">行号</th>
								</logic:equal>
							</logic:equal>
							<logic:equal value="1" name="data" property="bmlx">
								<th width="8%"><input type="checkbox" onchange="selectAll1(this);return false;"></th>
								<th width="10%">学号</th>
								<th width="10%">姓名</th>
								<th width="10%">提交状态</th>
								<th width="10%">审核状态</th>
								<logic:equal value="true" name="sfdfhd">
									<th width="10%">总分(最新阶段打分数)</th>
									<th width="10%">平均分</th>
									<th width="10%">已打分专家数</th>
									<logic:notEqual value="1" name="hdjdInfo" property="sfsldf">
										<th width="10%">当前环节总人数</th>
									</logic:notEqual>
									<th width="10%">行号</th>
								</logic:equal>
							</logic:equal>
						</tr>
						</thead>
						<tbody id="ryTbody">

						</tbody>
					</table>
				</div>
				<div class="text-right">
					<ul id="pageul"></ul>
				</div>
				<div class="operate-process">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-lr0 btn-wrap text-center padding-r0">
						<button class="btn btn-primary blue-bg-btn" onclick="hdcyjdsh()">审核</button>
						<button class="btn btn-primary blue-bg-btn" onclick="fhjdshList()">返回</button>
					</div>
				</div>
		</div>
	</html:form>

	<!--Bootstrap js-->
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap-paginator.min.js"></script>
</body>
</html>
