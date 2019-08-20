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
			function bmCheck() {
			    debugger;
			    var hdid = jQuery("#hdid").val();
				bm(hdid);
            }
		</script>
		
	</head>

	<body>
		<div class="secondclass_details container">

		</div>
		<div id="content">

			<html:form action="/hdgl_hdxq" method="post" styleId="hdxxForm" onsubmit="return false;">		  
				<input type="hidden" id="hdid" name="hdid" value="${data.hdid}" />
				<input type="hidden" id="hdid" value="${data.hdid}"/>
				<input type="hidden" id="hdzt" value="${data.hdzt}"/>
				<div class="secondclass_details container" style="width:100%;">
					 <div class="tab-content col-sm-12 p-0">
		                <div class="tab-pane fade active in" id="hdxq">
	                	 	<div class="col-sm-7 num-list p-l-0 m-15-0">
		                        <label>已报名/活动票数：</label>
			                        <div class="sc_num">
			                            <span class="blue">${data.rs == null ? 0 : data.rs}</span>人
			                            /
			                            <span>${data.bmrs}</span>人
			                        </div>
			                        <label class="m-l-25">评论：</label>
			                        <div class="sc_num">
			                            <span>0</span>条
			                        </div>
                  			 </div>
                  			 <div class="button-groups m-15-0 col-sm-5 text-right p-r-0">
								 <logic:equal value="1" property="bmsf" name="data">
									 <logic:equal value="bm" property="bmztmc" name="data">
										 <button class="btn btn-primary" onclick="bmCheck();return false;">
											 <i class="glyphicon glyphicon-time"></i>
											 报名
										 </button>
									 </logic:equal>
								 </logic:equal>
                    		</div>
		                    <div class="details-left col-sm-6 p-half p-l-0">
		                        <div class="panel panel-default margin_t15 p-10 index_list">
		                            <div class="details-con">
		                                <h4 class="col-sm-7">活动内容</h4>
		
		                                <div class="img" style="width: 300px;height: 300px">
		                                    <img src="${data.hb}">
		                                </div>
		                                <span>
		                                    ${data.nryq}
		                                </span>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="details-right col-sm-6 p-half p-r-0">
		                        <div class="panel panel-default margin_t15 p-10 index_list">
		                            <div class="details_title">
		                                <h4>
		                                	 ${data.hdmc}
		                                </h4>
										<div class="form-group">
											<label>活动标签：</label>
											<span class="time">
													${data.hdbqmc}
											</span>
										</div>
										<div class="form-group">
											<label>能力标签：</label>
											<span class="time">
													${data.nlbqmc}
											</span>
										</div>
		                                <div class="form-group">
		                                    <label>时间：</label>
		                                    <span class="time">
		                                    	${data.hdkssj} - ${data.hdjssj}
		                                    </span>
		                                </div>
		                                <div class="form-group">
		                                    <label>地点：</label>
		                                    <span class="address">
		                                    	${data.hddd}
		                                    </span>
		                                </div>
										<div class="form-group">
											<label>主办方：</label>
											<span class="address">
													${data.zbf}
											</span>
										</div>
										<div class="form-group">
											<label>活动性质：</label>
											<span class="address">
													${data.hdxs}
											</span>
										</div>
		                                <div class="form-group">
		                                    <label>类型：</label>
		                                    <span class="address">
		                                    	${data.hdlxmc}
		                                    </span>
		                                </div>
										<div class="form-group">
											<label>活动级别：</label>
											<span class="address">
													${data.jzjb}
											</span>
										</div>
		                                <div class="form-group">
		                                    <label>发布时间：</label>
		                                    <span class="address">
		                                    	${data.fbsj}
		                                    </span>
		                                </div>
										<logic:iterate id="jdxx" name="jdxxList">
											<div class="form-group">
												<label>${jdxx.jdlx=='1' ? '学生':'老师'}阶段：</label>
												<span class="address">
														${jdxx.jdkssj}至${jdxx.jdjssj}
												</span>
											</div>
										</logic:iterate>
		                                <div class="form-group">
		                                    <label>报名对象：</label>
											<logic:equal name="data" property="bmdx" value="本校公开报名">
												<span class="address">
													${data.bmdx}
												</span>
											</logic:equal>
											<logic:notEqual name="data" property="bmdx" value="本校公开报名">
												<span class="address">
													${data.bmmc}
												</span>
											</logic:notEqual>
		                                </div>
		                                <div class="form-group">
		                                    <label>报名类型：</label>
		                                    <span class="address">${data.bmlx=='0'?'组队':'个人'}</span>
		                                </div>
										<div class="form-group">
										    <label>结果认定：</label>
										    <span class="address">
										    	${data.hdjgrd}
										    </span>
										</div>

		                            </div>
		                            <div class="datails_button">
		                                <button class="btn btn-primary" onclick="xsfh();return false;">返回</button>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		          </div>
				</html:form>
	      </div>
	</body>
</html>
