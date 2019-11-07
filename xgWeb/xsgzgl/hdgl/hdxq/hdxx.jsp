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


<!--鉴于iframe兼容性问题，在iframe引入该页面特地隐去头部声明，防止加载冲突  -->


<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--	<head>--%>
<%--		<script type="text/javascript">--%>
<%--			var stylePath = "<%=stylePath%>";--%>
<%--		</script>--%>
<%--		<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--		<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
<%--		<link href="assets/css/style.css" rel="stylesheet">--%>
<%--		--%>
<%--		--%>
<%--		<script type="text/javascript" src="js/function.js"></script>--%>
<%--		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>--%>
<%--		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>--%>
<%--		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>--%>
<%--		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>--%>
<%--		<script type='text/javascript' src="js/comm/message.js"></script>--%>
<%--		<script type='text/javascript' src="js/comm/watermark.js"></script>--%>
<%--		<script language="javascript" src="js/comm/commFunction.js"></script>--%>
<%--		<script type="text/javascript" src="js/json.js"></script>--%>
<%--		<link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css" media="all" />--%>
<%--		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>--%>
<%--		<script type="text/javascript" src="js/calendar/calendar.js"></script>--%>
<%--		--%>
<%--		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>--%>
<%--		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxq.js"></script>--%>
<%--		<script type="text/javascript">--%>
<%--			--%>
<%--		</script>--%>
<%--		--%>
<%--	</head>--%>
<%----%>
<%--	<body>--%>
<%--		<div class="secondclass_details container">--%>
<%--				<ul class="nav-tabs nav panel-heading notice-tabs col-sm-12 p-0">--%>
<%--					<li class="active boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="getHdInfo('${data.hdid}');return false;">最新详情</a></li>--%>
<%--					<li class="boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="plgl('${data.hdid}');return false;">评论管理</a></li>--%>
<%--					<li><a href="javascript:void(0);" data-toggle="tab" onclick="bmgl('${data.hdid}')">报名管理</a></li>--%>
<%--				</ul>--%>
<%--		</div>--%>
<%--		<div id="content">--%>
			<script type="text/javascript">
				function lableConfig(){
					var hdid = jQuery("#hdid").val();
					showDialog("活动设置", 800, 550, "hdgl_hdxx.do?method=szHdxx&hdid="+hdid);
				}

				function bmlj() {
					var hdid = jQuery("#hdid").val();
					var url = "hdgl_hdxx.do?method=getBmEwm&hdid="+hdid;
					showDialog("报名二维码链接", 360, 200, url);
				}
				function qdlj() {
					var hdid = jQuery("#hdid").val();
					var url = "http://mtg.xjtu.edu.cn/template/GetDanmuTemplate?conid="+hdid;
					window.open(url);
                }
			</script>
			<html:form action="/hdgl_hdxq" method="post" styleId="hdxxForm" onsubmit="return false;">		  
				<input type="hidden" id="hdid" name="hdid" value="${data.hdid}" />
				<input type="hidden" id="hdid" value="${data.hdid}"/>
				<input type="hidden" id="hdzt" value="${data.hdzt}"/>
				<div class="secondclass_details container" style="width:100%;">
					 <div class="tab-content col-sm-12 p-0">
		                <div class="tab-pane fade active in" id="hdxq">
	                	 	<div class="col-sm-7 num-list p-l-0 m-15-0">
                                <logic:equal name="data" property="bmsf" value="1">
		                        <label>已报名/活动票数：</label>
			                        <div class="sc_num">
			                            <span class="blue">${data.rs == null ? 0 : data.rs}</span>人
			                            /
			                            <span>${data.bmrs}</span>人
			                        </div>
                                </logic:equal>
			                        <label class="m-l-25">评论：</label>
			                        <div class="sc_num">
			                            <span>0</span>条
			                        </div>
									<label class="m-l-25"> </label>
									<%--<div class="sc_num">--%>
										<%--<a href="javascript:;" id="showEwm" onclick="showDialog('签到二维码', 150, 150, 'hdgl_hdxq.do?method=getEwm&hdid=${data.hdid}');return false;">--%>
											<%--签到二维码--%>
										<%--</a>--%>
									<%--</div>--%>
                  			 </div>
                  			 <div class="button-groups m-15-0 col-sm-5 text-right p-r-0">
								 <logic:equal name="data" property="bmsf" value="1">
								 <button class="btn btn-primary" onclick="bmlj();return false;">
									 <i class="glyphicon glyphicon-time"></i>
									 活动报名链接
								 </button>
								 </logic:equal>
								 <logic:equal name="sfqd" value="yes">
								 <button class="btn btn-primary" onclick="qdlj();return false;">
									 <i class="glyphicon glyphicon-time"></i>
									 活动签到链接
								 </button>
								 </logic:equal>
                        		<button class="btn btn-primary" onclick="lableConfig();return false;">
                            		<i class="glyphicon glyphicon-time"></i>
										活动编辑
                        		</button>
								 <logic:equal name="data" property="bmsf" value="1">
								 <button class="btn btn-primary" onclick="openBmsjDialog();return false;">
									 <i class="glyphicon glyphicon-time"></i>
										报名设置
								 </button>
								 </logic:equal>
								 <logic:equal name="data" property="hdzt" value="1">
									 <button id="btn_hdxj" class="btn btn-primary" onclick="hdxj()">
										 下架活动
									 </button>
								 </logic:equal>
								 <logic:equal name="data" property="hdzt" value="0">
									 <button id="btn_hdxj" class="btn btn-primary grey-bg-btn" disabled="disabled" onclick="hdxj()">
										 已下架
									 </button>
								 </logic:equal>
<%--                       	 		<button class="btn btn-primary">--%>
<%--                            		<i class="glyphicon glyphicon-edit"></i>--%>
<%--                          				  编辑--%>
<%--                        		</button>--%>
<%--                        		<button class="btn btn-primary">--%>
<%--                            	<i class="glyphicon glyphicon-credit-card"></i>--%>
<%--                           				 位置打卡--%>
<%--                        		</button>--%>
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
										<logic:equal name="data" property="bmsf" value="1">
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
										</logic:equal>
										<div class="form-group">
										    <label>结果认定：</label>
										    <span class="address">
										    	${data.hdjgrd}
										    </span>
										</div>

		                            </div>
		                            <div class="datails_button">
		                                <button class="btn btn-primary" onclick="fh();return false;">返回</button>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		          </div>
				</html:form>
<%--	      </div>--%>
<%--	</body>--%>
<%--</html>--%>
