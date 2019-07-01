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
<%--	<head>--%>
<%--				<%@ include file="/syscommon/head.ini"%>--%>
<%--		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>--%>
<%--		<script type="text/javascript" src="js/calendar/calendar.js"></script>		--%>
<%--		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>--%>
<%--		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxq.js"></script>--%>
		
<%--	</head>--%>

<%--	<body>--%>
		<script type="text/javascript">
			jQuery(function(){
				searchPl('1');
			})
			
			function savePl(){
				if(!!jQuery("#plnr").val()){
					var url = "hdgl_hdxx.do?method=savePl";
					ajaxSubFormWithFun("hdxqForm", url, function(data) {
						 if(data["message"]=="保存成功！"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
									plgl();
								}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
					});
				}else{
					showAlert("请填写评论内容！");
					return false;
				}				
			}
		</script>
		<html:form action="/hdgl_hdxq" method="post" styleId="hdxqForm" onsubmit="return false;">
		<html:hidden property="hdid"/>
		<div class="tab-content col-sm-12 p-0">
                 <div id="pjgl">
                    <div class="col-sm-7 num-list p-l-0 m-15-0" id="pls">
<%--                        <label>收藏：</label>--%>
<%--                        <div class="sc_num">--%>
<%--                            <span>1</span>--%>
<%--                        </div>--%>
<%--                        <label class="m-l-25">评论：</label>--%>
<%--                        <div class="sc_num">--%>
<%--                            <span>3</span>--%>
<%--                        </div>--%>
<%--                        <label class="m-l-25">阅读数：</label>--%>
<%--                        <div class="sc_num">--%>
<%--                            <span>1233</span>--%>
<%--                        </div>--%>
                    </div>
<%--                    <div class="button-groups m-15-0 col-sm-5 text-right p-r-0">--%>
<%--                        <button class="btn btn-primary">--%>
<%--                            <i class="glyphicon glyphicon-remove-circle"></i>--%>
<%--                            	关闭评论--%>
<%--                        </button>--%>
<%--                    </div>--%>
                    <div class="table-responsive col-sm-12 p-0 table-primary">
                        <table class="table">
                            <thead>
                                <tr>
                                    <td>评论内容</td>
                                    <td>赞数</td>
                                    <td>发布时间</td>
                                    <td>操作</td>
                                </tr>
                            </thead>
                            <tbody id="plContent">
                                
                            </tbody>
                        </table>
                       
                         <div id="replay_box" class="col-sm-12">
                            <h5>快速发表评论</h5>
                            <textarea class="form-control" rows="5" name="plnr" id="plnr"></textarea>
                            <div class="button-groups">
                                <button class="btn btn-success" onclick="savePl();return false;">
<%--                                    <i class="glyphicon glyphicon-send"></i>--%>
                                    	发送
                                </button>
                                <button class="btn btn-primary" onclick="fh();return false;">返回</button>
                            </div>
                        </div>
                    </div>
                </div>
	</html:form>
<%--	</body>--%>
</html>
