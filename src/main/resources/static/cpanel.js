$(document).ready(function () {



    getNotice();
    getEvents();
    getSubscriber();



    $("#updateNotice").click(function () {


        $("#editNoticeTitle").removeClass("efw");
        $("#editNoticeDate").removeClass("efw");
        $("#editNoticeBody").removeClass("efw");

        title = $("#editNoticeTitle").val();
        date = $("#editNoticeDate").val();
        body = $("#editNoticeBody").val();

        if(title==''){
            $("#editNoticeTitle").addClass("efw");
        }else if(date==''){
            $("#editNoticeDate").addClass("efw");
        }else if(body==''){
            $("#editNoticeBody").addClass("efw");
        }else{

            $("#editNoticeModal").modal('hide');

            var  notice = {
                'id': id,
                'title': title,
                'date': date,
                'body': body
            };

            //alert(JSON.stringify(notice));

            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: "edit_notice",
                data: JSON.stringify(notice), // Note : it is important
                success: function (result) {
                    if(result){
                        getNotice();
                    }else{
                        alert("Can't update! Please try again.");
                    }
                },
                error: function (e) {
                    alert("Internal error occurred.");
                }
            });

        }
    });


    $("#updateEvent").click(function () {


        $("#editEventTitle").removeClass("efw");
        $("#editEventDate").removeClass("efw");
        $("#editEventStartTime").removeClass("efw");
        $("#editEventEndTime").removeClass("efw");
        $("#editEventLocation").removeClass("efw");
        $("#editEventType").removeClass("efw");
        $("#editEventBody").removeClass("efw");


        title = $("#editEventTitle").val();
        date = $("#editEventDate").val();
        timeFrom = $("#editEventStartTime").val();
        timeTo = $("#editEventEndTime").val();
        loction = $("#editEventLocation").val();
        type = $("#editEventType").val();
        body = $("#editEventBody").val();



        if(title==''){
            $("#editEventTitle").addClass("efw");
        }else if(date==''){
            $("#editEventDate").addClass("efw");
        }else if(timeFrom==''){
            $("#editEventStartTime").addClass("efw");
        }else if(timeTo==''){
            $("#editEventEndTime").addClass("efw");
        }else if(loction==''){
            $("#editEventLocation").addClass("efw");
        }else if(type==''){
            $("#editEventType").addClass("efw");
        }else if(body==''){
            $("#editEventBody").addClass("efw");
        }else{

            $("#editEventModal").modal('hide');


            var  event = {
                'id': id,
                'title': title,
                'date': date,
                'body': body,
                'timeFrom': timeFrom,
                'timeTo': timeTo,
                'location': loction,
                'type': type
            }

            //alert(JSON.stringify(notice));

            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: "edit_event",
                data: JSON.stringify(event), // Note : it is important
                success: function (result) {
                    if(result){
                        getEvents();
                    }else{
                        alert("Can't save! Please try again.");
                    }
                },
                error: function (e) {
                    alert("Internal error occurred.");
                }
            });

        }


    });
    


});



function getNotice() {
    
    $.post("fetch_notice", function (data, status) {

        noticeList = Object.values(data);
        //alert(Object.values(Object.values(noticeList)) );
        i=0;
        tblRow = "";
        for(notice of noticeList){

            tblRow+= "<tr>";
            tblRow+= "<td>"+(i+1)+"</td>";
            tblRow+= "<td>"+notice["title"]+"</td>";
            tblRow+= "<td>"+notice["date"]+"</td>";
            tblRow+= "<td>"+notice["body"]+"</td>";
            tblRow+= "<td style='text-align: center'><input onclick='editNotice("+i+")' data-toggle='modal' data-target='#editNoticeModal' type='button' class='button-edit' value='   Edit   ' /></td>";
            tblRow+= "<td style='text-align: center'><button  onclick='deleteNotice("+notice["id"]+")' type='button' class='button-delete'  > Delete </button></td>";
            tblRow+= "</tr>";
            i++;
        }

        $("#noticeTblBody").empty();
        $("#noticeTblBody").append(tblRow);

    });

}


function saveNotice() {

    $("#notice_title").removeClass("efw");
    $("#notice_date").removeClass("efw");
    $("#notice_body").removeClass("efw");

    noticeTitle = $("#notice_title").val();
    noticeDate = $("#notice_date").val();
    noticeBody = $("#notice_body").val();

    if (noticeTitle == '') {
        $("#notice_title").addClass("efw");
    } else if (noticeDate == '') {
        $("#notice_date").addClass("efw");
    } else if (noticeBody == '') {
        $("#notice_body").addClass("efw");
    } else {


       // alert(noticeTitle + " , " + noticeDate + ", " + noticeBody);

        $.post("save_notice",{
            noticeTitle: noticeTitle,
            noticeDate: noticeDate,
            noticeBody: noticeBody
        },function (data, status) {
            if(data){
                $("#notice_title").val('');
                $("#notice_date").val('');
                $("#notice_body").val('');
                getNotice();
            }else{
                alert("Notice can't saved, Please try again later.")
            }
        });


    }

}


function editNotice(v) {

    mdata = noticeList[v];

    id = mdata["id"];
    title = mdata["title"];
    date = mdata["date"];
    body = mdata["body"];


    $("#editNoticeTitle").removeClass("efw");
    $("#editNoticeDate").removeClass("efw");
    $("#editNoticeBody").removeClass("efw");

    $("#editNoticeTitle").val(title);
    $("#editNoticeDate").val(date);
    $("#editNoticeBody").val(body);


}




function deleteNotice(v) {

    $.post("delete_notice",{id: v},function (data, status) {
        if(data){
            getNotice();
        }else{
            alert("Can't delete notice. Please try again later.")
        }
    });
}


function getEvents() {


    $.post("fetch_events",function (data, status) {

        eventList = Object.values(data);

        tblRow = "";
        i = 0;
        for(event of eventList)
        {
            tblRow+= "<tr>";
            tblRow+= "<td>"+(i+1)+"</td>";
            tblRow+= "<td>"+event["title"]+"</td>";
            tblRow+= "<td>"+event["date"]+"</td>";
            tblRow+= "<td>"+event["body"]+"</td>";
            tblRow+= "<td>"+event["timeFrom"]+"</td>";
            tblRow+= "<td>"+event["timeTo"]+"</td>";
            tblRow+= "<td>"+event["location"]+"</td>";
            tblRow+= "<td>"+event["type"]+"</td>";
            tblRow+= "<td style='text-align: center'> <input type='button' onclick='editEvent("+i+")' class='button-edit' data-toggle='modal' data-target='#editEventModal' value='   Edit   ' />  </td>";
            tblRow+= "<td style='text-align: center'> <input type='button' onclick='deleteEvent("+event["id"]+")' class='button-delete' value='Delete' />  </td>";
            tblRow+= "</tr>";


            i++;
        }

        $("#eventTblBody").empty();
        $("#eventTblBody").append(tblRow);

    });


}


function saveEvent() {

    $("#event_title").removeClass("efw");
    $("#event_date").removeClass("efw");
    $("#event_body").removeClass("efw");
    $("#event_start_time").removeClass("efw");
    $("#event_end_time").removeClass("efw");
    $("#event_location").removeClass("efw");
    $("#event_type").removeClass("efw");

    eventTitle = $("#event_title").val();
    eventDate = $("#event_date").val();
    eventBody = $("#event_body").val();
    eventStartTime = $("#event_start_time").val();
    eventEndTime = $("#event_end_time").val();
    eventLocation = $("#event_location").val();
    eventType = $("#event_type").val();



    if (eventTitle == '') {
        $("#event_title").addClass("efw");
    } else if (eventDate == '') {
        $("#event_date").addClass("efw");
    } else if (eventBody == '') {
        $("#event_body").addClass("efw");
    } else if (eventStartTime == '') {
        $("#event_start_time").addClass("efw");
    } else if (eventEndTime == '') {
        $("#event_end_time").addClass("efw");
    } else if (eventLocation == '') {
        $("#event_location").addClass("efw");
    } else if (eventType == 'Select event type') {
        $("#event_type").addClass("efw");
    } else {

        //alert("Ok " + eventType);
        var  event = {
            'title': eventTitle,
            'date': eventDate,
            'body': eventBody,
            'timeFrom': eventStartTime,
            'timeTo': eventEndTime,
            'location': eventLocation,
            'type': eventType
        }

        //alert(JSON.stringify(notice));

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: "save_event",
            data: JSON.stringify(event), // Note : it is important
            success: function (result) {
                if(result){
                    $("#event_title").val('');
                    $("#event_date").val('');
                    $("#event_body").val('');
                    $("#event_start_time").val('');
                    $("#event_end_time").val('');
                    $("#event_location").val('');
                    $("#event_type").val('Select event type');
                    getEvents();
                }else{
                    alert("Can't save! Please try again.");
                }
            },
            error: function (e) {
                alert("Internal error occurred.");
            }
        });


    }



}


function editEvent(v) {

    mdata = eventList[v];

    id = mdata["id"];
    title = mdata["title"];
    date = mdata["date"];
    body = mdata["body"];
    timeFrom = mdata["timeFrom"];
    timeTo = mdata["timeTo"];
    loction = mdata["location"];
    type = mdata["type"];


    $("#editEventTitle").removeClass("efw");
    $("#editEventDate").removeClass("efw");
    $("#editEventStartTime").removeClass("efw");
    $("#editEventEndTime").removeClass("efw");
    $("#editEventLocation").removeClass("efw");
    $("#editEventType").removeClass("efw");
    $("#editEventBody").removeClass("efw");

    $("#editEventTitle").val(title);
    $("#editEventDate").val(date);
    $("#editEventStartTime").val(timeFrom);
    $("#editEventEndTime").val(timeTo);
    $("#editEventLocation").val(loction);
    $("#editEventType").val(type);
    $("#editEventBody").val(body);

}


function deleteEvent(v) {

    $.post("delete_event",{id: v}, function (data, status) {
        if(data){
            getEvents();
        }else{
            alert("Can't delete, Please try again later.")
        }
    });
}



function getSubscriber() {


    $.post("fetch_subscriber", function (data, status) {

        subscriberList = Object.values(data);
        tblRow = "";

        for(subscriber of subscriberList){

            tblRow+= "<tr>";
            tblRow+= "<td <td style='text-align: center;'> <input type='checkbox'> </td>";
            tblRow+= "<td>"+subscriber["name"]+"</td>";
            tblRow+= "<td>"+subscriber["email"]+"</td>";
            tblRow+= "<td>"+subscriber["phone"]+"</td>";
            tblRow+= "<td>"+subscriber["address"]+"</td>";
            tblRow+= "<td style='text-align: center;'> <button onclick=\"deleteSubscriber('"+subscriber["email"]+"')\" class='button-delete'>Delete</button> </td>";
            tblRow+= "</tr>";

        }

        $("#subscribeTblBody").empty();
        $("#subscribeTblBody").append(tblRow);


    });


}

function deleteSubscriber(v) {
    // alert(v);

    $.post("delete_subscriber",{email: v}, function (data, status) {
        if(data){
            getSubscriber();
        }else{
            alert("Can't delete. Please try again later.");
        }
    });
}


