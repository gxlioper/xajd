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
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdjdsh.js"></script>

    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>

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
    <html:form action="/hdgl_hdjdsh" method="post" styleId="hdxxForm" onsubmit="return false;">
        <html:hidden property="jdid" styleId="jdid" />
        <html:hidden property="hdid" styleId="hdid" />
        <input type="hidden" id="bmlx" value="${data.bmlx}"/>
        <input type="hidden" id="dqjdmc" value="${hdjdInfo.jdmc}" />
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
                <div class="level" id="jd_${jd.jdsx}">
                    <logic:equal value="${hdjdInfo.jdid}" name="jd" property="jdid">
                        <span class="number xzjd">${jd.jdsx}</span>
                    </logic:equal>
                    <logic:notEqual value="${hdjdInfo.jdid}" name="jd" property="jdid">
                        <span class="number">${jd.jdsx}</span>
                    </logic:notEqual>
                    <span class="font">${jd.jdmc}</span>
                </div>
                <logic:notEqual value="${data.jds - 1}" name="n">
                    <div class="hr" id="hr_${jd.jdsx}"></div>
                </logic:notEqual>
            </logic:iterate>
        </div>


        <p class="active-title">活动报名</p>
        <div style="padding: 0 15px 15px 15px">
            <logic:equal name="data" property="bmlx" value="1">
                <input type="hidden" id="xh" name="xh" value="${grbmxx.xh}" />
                <span>学号：${grbmxx.xh}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span>姓名：${grbmxx.xm}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span>报名时间：${grbmxx.bmsj}</span>
            </logic:equal>
            <logic:equal name="data" property="bmlx" value="0">
                <div class="apply-management-page m-t20">
                    <div class=" table-responsive">
                        <table class="table table-bordered text-center">
                            <thead>
                            <tr><th>队伍序号</th><th>学号</th><th>姓名</th><th>队伍职务</th><th>报名时间</th></tr>
                            </thead>
                            <logic:iterate id="dw" name="dwList" indexId="n">
                                <logic:equal value="0" name="n">
                                    <input type="hidden" id="dwid" name="dwid" value="${dw.dwid}" />
                                    <input type="hidden" id="xh" name="xh" value="${dw.xh}" />
                                </logic:equal>
                                <tr>
                                    <td>${dw.dwid}</td>
                                    <td>${dw.xh}</td>
                                    <td>${dw.xm}</td>
                                    <td>${dw.dwzwmc}</td>
                                    <td>${dw.bmsj}</td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </div>
                </div>
            </logic:equal>
        </div>

        <logic:iterate id="ywcjd" name="ywcjdList" indexId="n">
            <p class="active-title">${ywcjd.jdmc}</p>
            <%--学生阶段--%>
            <logic:equal value="1" name="ywcjd" property="jdlx">
                <input type="hidden" id="dqjd" value="${jd.jdsx}" />
            </logic:equal>
            <%--教师阶段--%>
            <logic:equal value="2" name="ywcjd" property="jdlx">
                <input type="hidden" id="dqjd" value="${jd.jdsx}" />
            </logic:equal>

            <div style="border: 1px solid #DEDEDE;padding: 10px;margin-bottom: 20px">
                <logic:equal value="1" name="ywcjd" property="jdlx">
                    <logic:equal value="是" name="data" property="sflx">
                        <div class="row">
                            <div class="row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="form-group ">
                                        <label for="" class="col-sm-4 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-6">
                                                ${ywcjd.mc}
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="form-group ">
                                        <label for="" class="col-sm-4 control-label">
                                            级别
                                        </label>
                                        <div class="col-sm-6">
                                                ${ywcjd.jb}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="form-group ">
                                        <label for="" class="col-sm-4 control-label">
                                            指导单位
                                        </label>
                                        <div class="col-sm-6">
                                                ${ywcjd.zddw}
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="form-group ">
                                        <label for="" class="col-sm-4 control-label">
                                            指导教师
                                        </label>
                                        <div class="col-sm-6">
                                                ${ywcjd.zdjs}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="form-group ">
                                    <label for="" class="col-sm-2 control-label">
                                        内容介绍
                                    </label>
                                    <div class="col-sm-10">
                                        <textarea name="nrjs" rows="8" readonly="readonly" class="active-textarea" style="border:1px solid #DEDEDE">${ywcjd.nrjs}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </logic:equal>
                    <logic:notEqual value="是" name="data" property="sflx">
                        <div class="row">
                            <div class="col-sm-2 col-md-2">
                                <p>心得体会</p>
                            </div>
                            <div class="col-sm-10 col-md-10">
                                <textarea name="xdth" rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE" readonly="readonly">${ywcjd.xdth}</textarea>
                            </div>
                        </div>
                    </logic:notEqual>
						<logic:notEqual value="null" name="ywcjd" property="xsfjid">
							<div class="upload" style="padding-top: 10px;">
								<p class="active-title">附件</p>
									<logic:equal value="1" name="ywcjd" property="jdlx">
										<input type="hidden" id="fjid${n}" name="fjid" value="${ywcjd.xsfjid}" />
									</logic:equal>
									<logic:notEqual value="1" name="ywcjd" property="jdlx">
										<input type="hidden" id="fjid${n}" name="fjid" value="${ywcjd.jsfjid}" />
									</logic:notEqual>
																		
									<div id="fjidDiv${n}"></div>
									<script type="text/javascript">
										//调用附件
										jQuery(function() {
											jQuery('#fjidDiv${n}').multiUploader_q({
												gid: jQuery('#fjid${n}').val(),
												uid: 'fjuid${n}'
											});
										});
									</script>	
								</p>
							</div>
						</logic:notEqual>
                </logic:equal>
                <logic:notEqual value="1" name="ywcjd" property="jdlx">
                    <logic:equal value="1" name="ywcjd" property="sfsldf">
                        <div class="row">
                            <div class="col-sm-2 col-md-2">
                                <label>
                                    打分信息
                                </label>
                            </div>
                            <div class="col-sm-12 col-md-12">
                                <span>${ywcjd.fs}</span>
                            </div>
                        </div>
                    </logic:equal>
                    <logic:notEqual value="1" name="ywcjd" property="sfsldf">
                        <div class="row">
                            <div class="col-sm-2 col-md-2">
                                <p>审核意见</p>
                            </div>
                            <div class="col-sm-10 col-md-10">
                                <textarea name="xdth" rows="8" cols="" class="active-textarea" style="border:1px solid #DEDEDE" readonly="readonly">${ywcjd.shyj}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 col-md-2">
                                <div style="padding-top: 15px;"><p>附件上传</p></div>
                            </div>
                            <div class="col-sm-10 col-md-10">
                                <div class="upload" style="padding-top: 10px;">
                                    <input type="hidden" id="fjid${n}" name="fjid" value="${ywcjd.jdlx == 1?ywcjd.xsfjid:ywcjd.jsfjid}" />

                                    <div id="fjidDiv${n}"></div>
                                    <script type="text/javascript">
                                        //调用附件
                                        jQuery(function(){
                                            jQuery('#fjidDiv${n}').multiUploader_q({
                                                gid : jQuery('#fjid${n}').val(),
                                                uid : 'fjuid${n}'
                                            });
                                        });
                                    </script>
                                </div>
                            </div>
                        </div>
                    </logic:notEqual>
                </logic:notEqual>
            </div>
        </logic:iterate>

        <logic:equal value="专家评审" name="hdjdInfo" property="jdmc">
            <p class="active-title">分数</p>
            <div style="margin-bottom: 20px">
                <input id="psfs" type="text" value="${hdjdshInfo.pffz}" readonly="readonly"/>
            </div>
        </logic:equal>

        <logic:equal value="1" name="hdjdInfo" property="sfsljx">
            <p class="active-title">奖项</p>
            <div style="margin-bottom: 20px">
                <select id="jxid" disabled>
                    <option></option>
                    <logic:iterate id="jx" name="jxList">
                        <option value="${jx.jxdm}" ${jx.jxdm==hdjdshInfo.jxid?"selected":""}>${jx.jxmc}</option>
                    </logic:iterate>
                </select>
            </div>
        </logic:equal>

        <logic:equal value="1" name="hdjdInfo" property="sfslxf">
            <p class="active-title">学分</p>
            <div style="margin-bottom: 20px">
                ${hdjdshInfo.xf}
            </div>
        </logic:equal>

        <p class="active-title">审核意见</p>
        <div style="margin-bottom: 20px">
            <textarea id="shyj" name="shyj" rows="8" cols="" readonly="readonly" class="active-textarea" style="border:1px solid #DEDEDE">${hdjdshInfo.shyj}</textarea>
        </div>

        <div class="active-btn">
            <button type="button" class="blue-bg-btn" onclick="fhjdshcyList()">返回</button>
        </div>
    </html:form>
</div>
</body>
</html>
