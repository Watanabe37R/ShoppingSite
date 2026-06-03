/**
 * ID1とPWをフロントでチェックする機能
 */
const id = document.getElementById("id");
const mail = document.getElementById("mail");

document.getElementById("id").addEventListener("blur", function () {
    console.log("IDチェック発火");
    console.log("入力値:", this.value);
});

document.getElementById("pw").addEventListener("blur", function () {
    console.log("Mailチェック発火");
    console.log("入力値:", this.value);
});
