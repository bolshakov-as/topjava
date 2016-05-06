/**
 * Created by bolshakov-as on 06.05.2016.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable(
        {
            "ajax":{
              "url":ajaxUrl,
              "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function(date, type, row){
                        if(type == 'display'){
                            var dateObject = new Date(date);
                            return '<span>' + dateObject.toISOString().substring(0, 10) + ' ' + dateObject.toISOString().substring(11,19)  + '</span>';
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {

                if (data.exceed) {
                    $(row).addClass('exceeded');
                }
                else{
                    $(row).addClass('normal');
                }
            },
            "initComplete": makeEditable
        });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });
    makeEditable();
});