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
							<div class="level" id="jd_${jd.jdsx}"><span class="number">${jd.jdsx}</span><span class="font">${jd.jdmc}</span></div>
							<logic:notEqual value="${data.jds - 1}" name="n">
								<div class="hr" id="hr_${jd.jdsx}"></div>
							</logic:notEqual>
					</logic:iterate>
				</div>
				
				<p class="active-title">
					<logic:notEmpty property="shzt" name="data">
						<logic:equal value="3" property="shzt" name="data">
							<logic:equal value="3" property="preshzt" name="data">
								审核状态:${data.shztmc}
							</logic:equal>
							<logic:notEqual value="3" property="preshzt" name="data">
								审核状态:${data.preshztmc}
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="3" property="shzt" name="data">
<%--								审核状态:${data.shztmc}--%>
								<logic:notEmpty name="data" property="pjjdmc">
									${data.pjjdmc}:${data.pjshztmc}
								</logic:notEmpty>
								<logic:notEmpty name="data" property="pffz">
									<p>分数:${data.pffz}</p>
								</logic:notEmpty>
								<logic:notEmpty name="data" property="jxmc">
					            	<p>奖项:${data.jxmc}</p>
					            </logic:notEmpty>
					            <logic:notEmpty name="data" property="xf">
						        	<p>学分:${data.xf}</p>
						        </logic:notEmpty>
						        <logic:notEmpty name="data" property="pjshyj">						      
						        	<p>审核意见</p>
									<textarea name="xdth" rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE" readonly="readonly">${data.pjshyj}</textarea>
								</logic:notEmpty>
						</logic:notEqual>
					</logic:notEmpty>
					
					<logic:empty property="shzt" name="data">
								<logic:notEmpty name="data" property="pjjdmc">
									${data.pjjdmc}:${data.pjshztmc}
								</logic:notEmpty>
								<logic:notEmpty name="data" property="pffz">
									<p>分数:${data.pffz}</p>
								</logic:notEmpty>
								<logic:notEmpty name="data" property="jxmc">
					            	<p>奖项:${data.jxmc}</p>
					            </logic:notEmpty>
					            <logic:notEmpty name="data" property="xf">
						        	<p>学分:${data.xf}</p>
						        </logic:notEmpty>
						        <logic:notEmpty name="data" property="pjshyj">						      
						        	<p>审核意见</p>
									<textarea name="xdth" rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE" readonly="readonly">${data.pjshyj}</textarea>
								</logic:notEmpty>
								<logic:empty name="data" property="pjjdmc">
									审核状态：审核中
								</logic:empty>
					</logic:empty>
				</p>
				
				<logic:notEmpty name="data" property="shzt">
					<logic:notEqual value="3" name="data" property="shzt">
					<logic:empty name="data" property="pjshyj">					
						<p class="active-title">审核意见</p>
						<textarea name="xdth" rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE" readonly="readonly">${data.shyj}</textarea>
					</logic:empty>
					</logic:notEqual>
				</logic:notEmpty>
				
				<div class="active-btn">
					<button type="button" class="green-bg-btn" onclick="jdfh()">返回</button>
				</div>
			</html:form>
		</div>
	</body>	
</html>
