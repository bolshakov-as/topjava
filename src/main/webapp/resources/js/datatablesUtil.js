function makeEditable() {
    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('.enabled').click(function() {
        if (this.checked == false)
        {
            disableUser($(this).attr("id"));
        }
        else{
            enableUser($(this).attr("id"));
       }
    });

    $('#getFilterMeals').submit(function(){
        setVarFilter();
        updateTable();
        return false;
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}



function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function enableUser(id) {
    $.ajax({
        url: ajaxUrl + "enable/" + id,
        type: 'POST',
        success: function () {
            successNoty('enable user');
        }
    });
}

function disableUser(id) {
    $.ajax({
        url: ajaxUrl + "disable/" + id,
        type: 'POST',
        success: function () {
            successNoty('disable user');
        }
    });
}

function setVarFilter(){
    startDate = document.getElementById("startDate").value;
    endDate   = document.getElementById("endDate").value;
    startTime = document.getElementById("startTime").value;
    endTime = document.getElementById("endTime").value;
}

function updateTable() {

    var needFilter = startDate != "" || endDate != "" || startTime != "" || endTime != "";

    if(needFilter){
        var vurUrl = ajaxUrl + "filter";
        $.get(vurUrl,
            {
                'startDate': startDate,
                'endDate': endDate,
                'startTime': startTime,
                'endTime': endTime
            },
            function (data) {
            datatableApi.fnClearTable();
            $.each(data, function (key, item) {
                datatableApi.fnAddData(item);
            });
            datatableApi.fnDraw();
        });
    }
    else
    {
        $.get(ajaxUrl, function (data) {
            datatableApi.fnClearTable();
            $.each(data, function (key, item) {
                datatableApi.fnAddData(item);
            });
            datatableApi.fnDraw();
        });
    }
}

function save() {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
