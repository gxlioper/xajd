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
		
		
		<script type="text/javascript" src="js/function.js"></script>
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
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
				showFbzt();
				searchRy('1');
			})
		</script>
	</head>
	<body>
	<html:form action="/zyhdry" method="post" styleId="qnzyryForm" onsubmit="return false;">
		<input type="hidden" id="rySearch" value="1"/>
		<div class="apply-management-page m-t20">
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 padding-lr0 hint" style="margin-top: -20px">
				<p style="margin-top: 10px">1�����ȡ������������Ŀ�����ܱ����룬�ɵ�����������·����</p>
			</div>
			<div class="top-content">
				<div class="container padding-l0">
					<div class="activity-info clearfix">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-l0">
							<logic:empty name="data" property="fjpath">
								<img style="width: 170px;height:140px;" src="default_dekt.jpg">
							</logic:empty>
							<logic:notEmpty name="data" property="fjpath">
								<img style="width: 170px;height:140px;" src="${data.fjpath}">
							</logic:notEmpty>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
							<h4 class="title">${data.hdmc}</h4>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-l0" style="margin-bottom:15px;">
								    <div class="col-lg-4 col-md-4 col-sm-5 col-xs-4 padding-l0">
								    	<span>
								    	${data.fzrxm}
								    	<logic:notEmpty name="data" property="hdfzrlxfs">
									    	/${data.hdfzrlxfs}
								    	</logic:notEmpty>
								    	</span>
								    </div>
								    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 apply-num"><span>�ѱ�/�޶���������${data.ybrs}/${data.xdrs}��</span></div>
								    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-4 padding-r0" style="margin-top:10px;">
										<input type="hidden" id="fbzt" value="${data.fbzt}"/> 					
										<button class="btn btn-primary cancel-btn" type="button" onclick="bgfb('0')" style="display: none" id="cancel-btn" />
											ȡ������
										</button>									
										<button class="btn btn-primary cancel-btn" type="button" onclick="bgfb('1')" style="display: none" id="confirm-btn" />
											����
										</button>
								     </div>
							</div>
							<p>��֯���ţ�${data.zzbm}</p>
							<p>
								�ʱ�䣺${data.hdkssj}
								<logic:notEmpty name="data" property="hdjssj">
									�� ${data.hdjssj}
								</logic:notEmpty>
							</p>
							<p>��ص㣺${data.hddd}</p>
						</div>
					</div>
					
					<input type="hidden" id="hdid" name="hdid" value="${data.hdid}"/>
					<div class="search-wrap m-t20 clearfix">
						<ul>
							<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12 padding-lr0 status">
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 padding-lr0"><span>���״̬</span></div>
								<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10 padding-l0">
									<input type="checkbox" name="bmzts" value="0"><span>δ���</sapn>
									<input type="checkbox" name="bmzts" value="1"><span>ͨ��</span>
									<input type="checkbox" name="bmzts" value="2"><span>��ͨ��</span>
								</div>
							</li>
							<li class="col-lg-4 col-md-4 col-sm-5 col-xs-12 padding-lr0">
								<input type="text" class="form-control" placeholder="����/ѧ��" name="mhcx">
							</li>
							<li class="col-lg-1 col-md-1 col-sm-1 col-xs-12 padding-r0 text-right">
								<button class="btn btn-primary blue-bg-btn search-btn" type="button" onclick="searchRy('1');return false;" id="search_go">����</button>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="table-responsive">
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th><input type="checkbox" onchange="selectAll(this);return false;" id="qx"></th>
								<th>ѧ��</th>
								<th>����</th>
								<th>��ϵ�绰</th>
								<th>ѧԺ</th>
								<th>����ʱ��</th>
								<th>����ʱ</th>
								<th>���</th>
							</tr>
						</thead>
						<tbody id="ryTbody">
							
						</tbody>
					</table>
				</div>
				<div class="text-right">
					<jsp:include flush="true" page="/xsgzgl/dekt/qnzyhd/turnpage.jsp?form=qnzyryForm"></jsp:include>
				</div>
				<div class="operate-process">
<%--					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 padding-lr0">--%>
<%--						<span>������</span><input type="radio" name="bmzt" value="1"><span>���ͨ��</span><input type="radio" name="bmzt" value="2"><span>��˲�ͨ��</span>--%>
<%--					</div>--%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-lr0 btn-wrap text-right padding-r0">
						<button type="button" class="btn btn-primary blue-bg-btn" onclick="bcsh('1');return false;">ͨ��</button>
						<button type="button" class="btn btn-primary cancel-btn" onclick="bcsh('2');return false;">��ͨ��</button>
						<button type="button" class="btn btn-primary green-bg-btn apply-btn" onclick="fh();return false;">����</button>
					</div>
				</div>
			</div>
		</div>
	</html:form>
	</body>
</html>

