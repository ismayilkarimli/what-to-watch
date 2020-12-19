function setURL(form) {
    let searchId = document.getElementById("searchId").value;
    form.action = "/search/" + searchId;
    console.log(form.action);
}