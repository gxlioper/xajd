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


<!--����iframe���������⣬��iframe�����ҳ���ص���ȥͷ����������ֹ���س�ͻ  -->


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
<%--					<li class="active boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="getHdInfo('${data.hdid}');return false;">��������</a></li>--%>
<%--					<li class="boder-right"><a href="javascript:void(0);" data-toggle="tab" onclick="plgl('${data.hdid}');return false;">���۹���</a></li>--%>
<%--					<li><a href="javascript:void(0);" data-toggle="tab" onclick="bmgl('${data.hdid}')">��������</a></li>--%>
<%--				</ul>--%>
<%--		</div>--%>
<%--		<div id="content">--%>
			<script type="text/javascript">
				function lableConfig(){
					var hdid = jQuery("#hdid").val();
					showDialog("�����", 800, 550, "hdgl_hdxx.do?method=szHdxx&hdid="+hdid);
				}

				function bmlj() {
					var hdid = jQuery("#hdid").val();
					var url = "hdgl_hdxx.do?method=getBmEwm&hdid="+hdid;
					showDialog("������ά������", 360, 200, url);
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
		                        <label>�ѱ���/�Ʊ����</label>
			                        <div class="sc_num">
			                            <span class="blue">${data.rs == null ? 0 : data.rs}</span>��
			                            /
			                            <span>${data.bmrs}</span>��
			                        </div>
			                        <label class="m-l-25">���ۣ�</label>
			                        <div class="sc_num">
			                            <span>0</span>��
			                        </div>
									<label class="m-l-25"> </label>
									<%--<div class="sc_num">--%>
										<%--<a href="javascript:;" id="showEwm" onclick="showDialog('ǩ����ά��', 150, 150, 'hdgl_hdxq.do?method=getEwm&hdid=${data.hdid}');return false;">--%>
											<%--ǩ����ά��--%>
										<%--</a>--%>
									<%--</div>--%>
                  			 </div>
                  			 <div class="button-groups m-15-0 col-sm-5 text-right p-r-0">
								 <logic:equal name="data" property="bmsf" value="1">
								 <button class="btn btn-primary" onclick="bmlj();return false;">
									 <i class="glyphicon glyphicon-time"></i>
									 ���������
								 </button>
								 </logic:equal>
                        		<button class="btn btn-primary" onclick="lableConfig();return false;">
                            		<i class="glyphicon glyphicon-time"></i>
										��༭
                        		</button>
								 <logic:equal name="data" property="bmsf" value="1">
								 <button class="btn btn-primary" onclick="openBmsjDialog();return false;">
									 <i class="glyphicon glyphicon-time"></i>
										��������
								 </button>
								 </logic:equal>
								 <logic:equal name="data" property="hdzt" value="1">
									 <button id="btn_hdxj" class="btn btn-primary" onclick="hdxj()">
										 �¼ܻ
									 </button>
								 </logic:equal>
								 <logic:equal name="data" property="hdzt" value="0">
									 <button id="btn_hdxj" class="btn btn-primary grey-bg-btn" disabled="disabled" onclick="hdxj()">
										 ���¼�
									 </button>
								 </logic:equal>
<%--                       	 		<button class="btn btn-primary">--%>
<%--                            		<i class="glyphicon glyphicon-edit"></i>--%>
<%--                          				  �༭--%>
<%--                        		</button>--%>
<%--                        		<button class="btn btn-primary">--%>
<%--                            	<i class="glyphicon glyphicon-credit-card"></i>--%>
<%--                           				 λ�ô�--%>
<%--                        		</button>--%>
                    		</div>
		                    <div class="details-left col-sm-6 p-half p-l-0">
		                        <div class="panel panel-default margin_t15 p-10 index_list">
		                            <div class="details-con">
		                                <h4 class="col-sm-7">�����</h4>
		
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
											<label>���ǩ��</label>
											<span class="time">
		                                    	${data.hdbqmc}
		                                    </span>
										</div>
										<div class="form-group">
											<label>������ǩ��</label>
											<span class="time">
													${data.nlbqmc}
											</span>
										</div>
		                                <div class="form-group">
		                                    <label>ʱ�䣺</label>
		                                    <span class="time">
		                                    	${data.hdkssj} - ${data.hdjssj}
		                                    </span>
		                                </div>
		                                <div class="form-group">
		                                    <label>�ص㣺</label>
		                                    <span class="address">
		                                    	${data.hddd}
		                                    </span>
		                                </div>
										<div class="form-group">
											<label>���췽��</label>
											<span class="address">
													${data.zbf}
											</span>
										</div>
										<div class="form-group">
											<label>����ʣ�</label>
											<span class="address">
													${data.hdxs}
											</span>
										</div>
		                                <div class="form-group">
		                                    <label>���ͣ�</label>
		                                    <span class="address">
		                                    	${data.hdlxmc}
		                                    </span>
		                                </div>
										<div class="form-group">
											<label>�����</label>
											<span class="address">
													${data.jzjb}
											</span>
										</div>
		                                <div class="form-group">
		                                    <label>����ʱ�䣺</label>
		                                    <span class="address">
		                                    	${data.fbsj}
		                                    </span>
		                                </div>
										<logic:iterate id="jdxx" name="jdxxList">
											<div class="form-group">
												<label>${jdxx.jdlx=='1' ? 'ѧ��':'��ʦ'}�׶Σ�</label>
												<span class="address">
														${jdxx.jdkssj}��${jdxx.jdjssj}
												</span>
											</div>
										</logic:iterate>
										<logic:equal name="data" property="bmsf" value="1">
		                                <div class="form-group">
		                                    <label>��������</label>
											<logic:equal name="data" property="bmdx" value="��У��������">
												<span class="address">
													${data.bmdx}
												</span>
											</logic:equal>
											<logic:notEqual name="data" property="bmdx" value="��У��������">
												<span class="address">
													${data.bmmc}
												</span>
											</logic:notEqual>
		                                </div>
		                                <div class="form-group">
		                                    <label>�������ͣ�</label>
		                                    <span class="address">${data.bmlx=='0'?'���':'����'}</span>
		                                </div>
										</logic:equal>
										<div class="form-group">
										    <label>����϶���</label>
										    <span class="address">
										    	${data.hdjgrd}
										    </span>
										</div>

		                            </div>
		                            <div class="datails_button">
		                                <button class="btn btn-primary" onclick="fh();return false;">����</button>
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
