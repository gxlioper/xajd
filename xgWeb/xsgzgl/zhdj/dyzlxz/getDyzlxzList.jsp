<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function() {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_dyzlxz.do?method=getDyzlList&type=query",
                colList: [
                    {label: 'key', name: 'filepath', index: 'filepath', hidden: true},
                    {label: '文件名称', name: 'filemc', index: 'filemc', width: '20%'},
                    {label: '类型', name: 'lx', index: 'lx', width: '10%'},
                    {label: '所属', name: 'ss', index: 'ss', width: '10%'},
                    {label: '下载附件', name: 'xzfj', index: 'xzfj', width: '15%', formatter: xzLink}


                ],
                sortname: "filemc",
                sortorder: "desc",
                multiselect: false,
                radioselect: true
            }
            jQuery("#dataTable").initGrid(gridSetting);
            jQuery("#search_go").bind("click", query);
            jQuery("#cxFilemc").bind("keypress", function (event) {
                if (event.keyCode == 13 || "13" == event.keyCode) {
                    query();
                    return false;
                }
            });
        });
        //党支部查看
        function xzLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='View(\""
                + rowObject['filepath'] + "\");'>" + cellValue
                + "</a>";
        }

        function View(filepath) {
            window.open( "czxxDtjsDyxx.do?method=downLoadFile&dir=" + filepath,"_blank");

        }

        /**
         *添加搜索条件
         **/
        function query() {
            var map = {};
            map["cxFilemc"] = jQuery("#cxFilemc").val();
            jQuery("#dataTable").reloadGrid(map);
        }

        function czSearchCond() {

            jQuery("#cxFilemc").val("");
        }
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dzdy_cygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
		</html:form>
		<!-- 过滤条件 -->
		<div class="searchtab">
			<table width="100%" border="0">
				<tr>
					<th width="15%">文件名称</th>
					<td>
						<input type="text" id="cxFilemc" name="cxFilemc" maxleng="20" />
					</td>
					<td>
						<div class="btn">
							<button type="button" class="btn_cx" id="search_go">
								查 询
							</button>
							<button type="button" class="btn_cz" id="btn_cz"
									onclick="czSearchCond();">
								重 置
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="main_box">

			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
