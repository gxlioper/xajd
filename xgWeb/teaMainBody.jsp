<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.comm.homepage.HomePageService"/>
<jsp:directive.page import="com.zfsoft.xgxt.xtwh.ksdh.KsdhService"/>
<jsp:directive.page import="java.util.*"/>
<jsp:directive.page import="xgxt.action.Base"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/xg_v4.ini"%>
		<script type="text/javascript" src="xtwh/wdyy/js/wdyy.js"></script>
		<script type="text/javascript" src="xtwh/wdyy/js/zf_dialog_1.0.js"></script>
	    <script type="text/javascript" src="xtwh/wdyy/js/jquery-ui.min.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//下载专区
				jQuery.post("<%=request.getContextPath()%>/xtwhSysz.do?method=loadFilesInfo",{},function(data){
					var maxLen = (data.length > 5) ? 5 : data.length;
			 		for (var i = 0 ; i < maxLen; i++){
				 		var html = "";
			 			if(data[i]["filepath"]){
			 				html += "<a class='list-group-item' href='<%=request.getContextPath()%>/czxxDtjsDyxx.do?method=downLoadFile&dir="+data[i]["filepath"]+"'>"+data[i]["filemc"]+"</a>";
			 			}
			 			jQuery("#fileList").append(html);
			 		}
			 	},"json");
				//获取类型列表
				jQuery.post("xtwh_newsAjax.do?method=getNewsListType",function(data){
					var	title="";
					var maxLen = (data.length > 5) ? 5 : data.length;
					for (var i = 0 ; i < maxLen ; i++){
						var typename=data[i]["typename"];
						var typeid=data[i]["typeid"];
						//处理lable分类
						if(i==0){
							//第一个选中
							title+="<li class='active' role='presentation' id=\"tag"+i+"\">";
						}else{
							title+="<li id=\"tag"+i+"\" role='presentation' class=''>";
						}
						title+="<input type=\"hidden\" value=\""+typeid+"\">"
						title+="<a onclick=\"typeLable(this)\" data-toggle='tab'>"+typename+"</a>";
						title+="</li>";
					}
					jQuery("#tagslist01").append(title);
					var object=jQuery("#tagslist01").find("li").first().find("a");
					typeLable(object);			
				},"json");
				//默认执行执行第一个lable
				
				// ========== 校区统计 begin ===========
				var zjuxqmc_wzxb = 0;
				jQuery(".zjuxqmc_wzxb_class").each(function(){
					zjuxqmc_wzxb += parseInt(jQuery(this).text());
				});
				if(zjuxqmc_wzxb > 0){
					jQuery("#zjuxqmc_wzxb_td").show();
					jQuery(".zjuxqmc_wzxb_class").show();
				}
				// ========== 校区统计 end ===========
			});
			function typeLable(object){
				var typeid=jQuery(object).prev().val();
				
				//加载具体内容列表
				jQuery.post("xtwh_newsAjax.do?method=getNewsList",{"pages.pageSize":5,"typeid":typeid},function(data){
						var liHtml="";
						for (var i = 0 ; i < data.length ; i++){
							var newstitle=data[i]["newstitle"];
							if(newstitle.length > 24){
								newstitle = newstitle.substr(0,24)+"...";
							}
							//处理内容
							liHtml+="<a target=\"blank\" href=\"newsContent.do?newsId="+data[i]["newsid"]+"\" class='list-group-item'>";
							liHtml+="<span class='badge'>"+data[i]["pubtime"]+"</span>"+newstitle;
							if (data[i]["sfzd"] == "是"){
								liHtml += "<font color=\"red\" style=\"float:left;\">【置顶】</font>";
							}
							liHtml+="</a>";
						}
						jQuery("#content0").html(liHtml);
						jQuery("#more").attr("href","xtwh_news.do?method=newsMore&typeid="+typeid);
				},"json");
			}
			function removeAllLiClass(){
				jQuery("#tagslist01").find("li").each(function(){
					jQuery(this).removeClass();
				});
			}
		</script>
		
	</head>
	<%
		HomePageService homePageService = new HomePageService();
		homePageService.setTeaList(request);
		homePageService.setGncdList(request);
	 %>
	<body id="container">
	
<!--新增选项卡模块-->
<div class="container">
	<div class="row">
				<%@ include file="/homepage/info/chartInfo.jsp"%>
		</div>
	<div class="row">
		<div class="col-md-7 col-sm-7 padding_r0">				
			<div class="panel panel-default index_list margin_t15">
				<ul id="tagslist01" class="nav-tabs nav panel-heading notice-tabs">
					<span class="pull-left" style="padding:0px;"><img src="images/notice_icon.png" style="height:40px;padding:10px;"></span>
					<a href="xtwh_news.do?method=newsMore&typeid=-1" class="pull-right" title="更多">more>></a>
				</ul>
				<div class="tab-content">
				<div id="contag001" class="tab-pane fade active in">
					<div class="panel-body">
						<a href="#" id="more" class="pull-right" title="更多">
						</a>
						<ul class="list-group" id="content0">
							
						</ul>
					</div>
				</div>
				</div>
			</div>
		</div>
		<div class="col-md-5 col-sm-5">
			<div class="panel panel-default index_list margin_t15">
				<div class="panel-heading">
					<span class="pull-left"><img src="images/download.png">下载专区</span>
					<a href="xtwhSysz.do?method=xzzqView" class="pull-right" title="更多">more>></a>
				</div>
				<div class="panel-body">
					<ul class="list-group" id="fileList">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6 col-sm-6 padding_r0">
			<div class="panel panel-default index_list margin_t15">
				<div class="panel-heading">
					<span class="pull-left"><img src="images/search_icon.png">我的应用</span>
					<a href="javascript:void(0)" onclick="editwdyy();" class="pull-right">设置<img class="setting_icon" src="images/setting_icon.png"></a>
				</div>
				<div class="panel-body">
					<ul class="info-search" style="height: 235px; overflow: hidden;">
						<logic:present name="gncdlist">
								<logic:iterate id="gncd" name="gncdlist" indexId="i">
									<li title="${gncd.title}"><a href="${gncd.dyym}" target="_blank"><img style="width:56px;height:56px" src="images/wdyy/${gncd.gnmklj}"/>
									<p class="text-center">${gncd.gnmkmc}</p>
									</a></li>
								</logic:iterate>
						    </logic:present>
					</ul>
				</div>
			</div>

		</div>
		<%@ include file="/homepage/info/dbsxInfo.jsp"%>
	</div>
	
	</body>
</html>