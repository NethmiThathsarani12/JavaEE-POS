loadAllItem();

$("#btnItem").click(function () {
    let itemOb = {
        "itemCode": $("#txtItemId").val(),
        "itemName": $("#txtItemName").val(),
        "itemQty": $("#txtQty").val(),  // Corrected the order of properties
        "itemPrice": $("#txtPrice").val()  // Corrected the order of properties
    };

    $.ajax({
        url: "http://localhost:8080/backEnd/item",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(itemOb),
        success: function (res) {
            if (res.status == 200) {
                loadAllItem();
                alert(res.message);
                resetItem();
            } else {
                alert(res.data);
            }
        },
        error: function (ob, textStatus, error) {
            console.log(ob);
            console.log(textStatus);
            console.log(error);
        }
    });
});

$("#btnItemGetAll").click(function () {
    resetItem();
    loadAllItem();
});

function resetItem() {
    $("#txtItemId").val("");
    $("#txtItemName").val("");
    $("#txtPrice").val("");
    $("#txtQty").val("");
}

function loadAllItem() {
    $("#tblItem").empty();
    $.ajax({
        url: "http://localhost:8080/backEnd/item?option=GETALL",
        method: "GET",
        success: function (resp) {
            for (const item of resp.data) {
                let row = `<tr><td>${item.itemCode}</td><td>${item.itemName}</td><td>${item.itemQty}</td><td>${item.itemPrice}</td></tr>`;
                $("#tblItem").append(row);
            }
            bindClickEvent();
        }
    });
}

function bindClickEvent() {
    $("#tblItem>tr").click(function () {
        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let qtyOnHand = $(this).children().eq(2).text();  // Corrected index for qtyOnHand
        let price = $(this).children().eq(3).text();  // Corrected index for price

        $("#txtItemId").val(id);
        $("#txtItemName").val(name);
        $("#txtPrice").val(price);
        $("#txtQty").val(qtyOnHand);
    });
}
