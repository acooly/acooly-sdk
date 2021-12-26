<#if ssoEnable><#include "/manage/common/ssoInclude.ftl"></#if>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_coinmarketcapQuotes_searchform" class="form-inline ac-form-search" onsubmit="return false">
                    <div class="form-group">
                        <label class="col-form-label">币种编码：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_coinCode"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">价格更新时间：</label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_lastUpdated" name="search_GTE_lastUpdated" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_lastUpdated" name="search_LTE_lastUpdated" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">当前价格（USD）：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_price"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">24小时交易量：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_volume24h"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">24小时交易量变化：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_volumeChange24h"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">价格1小时变化比：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_percentChange1h"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">价格24小时变化比：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_percentChange24h"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">价格7天变化比：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_percentChange7d"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">价格30天变化比：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_percentChange30d"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">市值：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_marketCap"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">市场占有率：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_marketCapDominance"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">稀释后市值：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_fullyDilutedMarketCap"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">创建时间：</label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">修改时间：</label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_coinmarketcapQuotes_searchform','manage_coinmarketcapQuotes_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i> 查询</button>
            </div>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_coinmarketcapQuotes_datagrid" class="easyui-datagrid" url="/manage/sdk/coinapi/coinmarketcapQuotes/listJson.html" toolbar="#manage_coinmarketcapQuotes_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" formatter="idFormatter">编号</th>
            <th field="id" sortable="true" >id</th>
			<th field="coinCode">币种编码</th>
            <th field="lastUpdatedMillis" sortable="true" sum="true">更新时间毫秒</th>
		    <th field="lastUpdated" formatter="dateTimeFormatter">价格更新时间</th>
			<th field="price">当前价格（USD）</th>
			<th field="volume24h">24小时交易量</th>
			<th field="volumeChange24h">24小时交易量变化</th>
			<th field="percentChange1h">价格1小时变化比</th>
			<th field="percentChange24h">价格24小时变化比</th>
			<th field="percentChange7d">价格7天变化比</th>
			<th field="percentChange30d">价格30天变化比</th>
			<th field="marketCap">市值</th>
			<th field="marketCapDominance">市场占有率</th>
			<th field="fullyDilutedMarketCap">稀释后市值</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
			<th field="comments" formatter="contentFormatter">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_coinmarketcapQuotes_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_coinmarketcapQuotes_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/sdk/coinapi/coinmarketcapQuotes/edit.html',id:'{0}',entity:'coinmarketcapQuotes',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/sdk/coinapi/coinmarketcapQuotes/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/sdk/coinapi/coinmarketcapQuotes/deleteJson.html','{0}','manage_coinmarketcapQuotes_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_coinmarketcapQuotes_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/sdk/coinapi/coinmarketcapQuotes/create.html',entity:'coinmarketcapQuotes',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/sdk/coinapi/coinmarketcapQuotes/deleteJson.html','manage_coinmarketcapQuotes_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_coinmarketcapQuotes_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_coinmarketcapQuotes_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/sdk/coinapi/coinmarketcapQuotes/exportXls.html','manage_coinmarketcapQuotes_searchform','币报价')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/sdk/coinapi/coinmarketcapQuotes/exportCsv.html','manage_coinmarketcapQuotes_searchform','币报价')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/sdk/coinapi/coinmarketcapQuotes/importView.html',uploader:'manage_coinmarketcapQuotes_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>
    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_coinmarketcapQuotes_searchform', 'manage_coinmarketcapQuotes_datagrid');
        });
    </script>
</div>
