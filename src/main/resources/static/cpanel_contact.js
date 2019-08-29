/**
 * Created by A.A.MAMUN on 8/17/2019.
 */
$(document).ready(function () {

    getAllIncomeMessage();
    
    $("#sendSms").click(function () {
        alert("Messaging sending.....");

        phone = "+8801916307984";
        message = "Message from Hateya H.H.U High School.";

        $.post("send_sms",
            {
                phone: phone,
                message: message
            },
            function(data, status) {
            if(data){
                alert("Message successfully sent.")
            }else{
                alert("Message  can't sent, please try again later.")
            }
        });

    });





    $("#sendEmail").click(function () {
        $("#incomeMessageModal").modal('hide');
        $("#toEmail").text("To : "+selectedEmail);
        $("#sendMessageModal").modal("show");
    });

    $("#sendEmailTo").click(function () {

        $("#sendMessageSubject").removeClass("efw");
        $("#sendMessageBody").removeClass("efw");

        if($("#sendMessageSubject").val()==''){
            $("#sendMessageSubject").addClass("efw");
        }else if($("#sendMessageBody").val()==''){
            $("#sendMessageBody").addClass("efw");
        }else{

            $("#sendMessageSubject").removeClass("efw");
            $("#sendMessageBody").removeClass("efw");


            $("#sendMessageModal").modal("hide");
            $("#sendMessageSubject").val(''),
            $("#sendMessageBody").val('');

            $.post("send_reply_email",
                {
                    email: selectedEmail,
                    subject: $("#sendMessageSubject").val(),
                    message: $("#sendMessageBody").val()

                },
                function (data, status) {


                    if(data){
                        alert("Message successfully sent.");
                    }else{
                        alert("Message can't send. Please try again later.")
                    }

                });

        }

    });

















});


function getAllIncomeMessage() {
    
    $.post("fetch_income_message", function (data, status) {

        incomeMessageList = Object.values(data);
        tblRow = "";
        i = 0;
        for(incomeMessage of incomeMessageList)
        {
            if(incomeMessage["status"]==101) {
                tblRow += "<tr onclick='selectedRow(this)' id='" + incomeMessage["id"] + "'>";
                tblRow += "<td> " + incomeMessage["name"] + " </td>";
                tblRow += "<td> " + incomeMessage["email"] + " </td>";
                tblRow += "<td> " + incomeMessage["phone"] + " </td>";
                tblRow += "<td> " + incomeMessage["date"] + " </td>";
                tblRow += "<td style='text-align: center'> <button data-toggle='modal' data-target='#incomeMessageModal' class='button-edit' style='text-align: center' onclick='viewIncomeMessage(" + i + ")' >View</button> </td>";
                tblRow += "<td style='text-align: center'> <button class='button-delete' style='text-align: center' onclick='deleteIncomeMessage(" + incomeMessage["id"] + ")' >Delete</button> </td>";
                tblRow += "</tr>";
            }else{
                tblRow += "<tr onclick='selectedRow(this)' id='" + incomeMessage["id"] + "'>";
                tblRow += "<td> <b>" + incomeMessage["name"] + "</b> </td>";
                tblRow += "<td> <b>" + incomeMessage["email"] + "</b> </td>";
                tblRow += "<td> <b>" + incomeMessage["phone"] + "</b> </td>";
                tblRow += "<td> <b>" + incomeMessage["date"] + "</b> </td>";
                tblRow += "<td style='text-align: center'> <button data-toggle='modal' data-target='#incomeMessageModal' class='button-edit' style='text-align: center' onclick='viewIncomeMessage(" + i+ ")' >View</button> </td>";
                tblRow += "<td  style='text-align: center'> <button class='button-delete' onclick='deleteIncomeMessage(" + incomeMessage["id"] + ")' >Delete</button> </td>";
                tblRow += "</tr>";
            }
            i++;
        }

        $("#incomeMessageTblBody").empty();
        $("#incomeMessageTblBody").append(tblRow);


    });

}





function viewIncomeMessage(v) {

    mdata = incomeMessageList[v];

    selectedEmail = mdata["email"];
    $("#mgsBody").text(mdata["message"]);

    if(mdata["status"]==102) {


        var incomeMessage = {
            'id': mdata["id"],
            'name': mdata["name"],
            'email': mdata["email"],
            'phone': mdata["phone"],
            'date': mdata["date"],
            'message': mdata["message"],
            'status': 101
        };

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: "update_income_message_status",
            data: JSON.stringify(incomeMessage), // Note : it is important
            success: function (result) {
                if (result) {
                    getAllIncomeMessage();
                }
            },
            error: function (e) {
                alert("Internal error occurred.");
            }
        });
    }
}


function deleteIncomeMessage(v) {

    $.post("delete_income_message",{id: v},
        function (data, status) {

        if(data){
            getAllIncomeMessage();
        }else {
            alert("Can't delete message. Please try agin later.");
        }
    });
}