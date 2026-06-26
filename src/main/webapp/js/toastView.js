window.addEventListener("load", () => {

    //トースト表示
    const flag = sessionStorage.getItem("showCartToast");
    if (flag === "true") {
        sessionStorage.removeItem("showCartToast");
        showGlobalToast();
    }

    //スクロール復元
    const scrollY = sessionStorage.getItem("scrollY");
    if (scrollY !== null) {
        window.scrollTo(0, parseInt(scrollY));
        sessionStorage.removeItem("scrollY");
    }
});

function saveState() {
    sessionStorage.setItem("showCartToast", "true");
    sessionStorage.setItem("scrollY", window.scrollY);
}

function showGlobalToast() {
    const toast = document.getElementById("toast");
    if (!toast) return;

    toast.style.display = "block";
    toast.style.opacity = "1";
    toast.style.transform = "translate(-50%, -50%) scale(1)";

    setTimeout(() => {
        toast.style.opacity = "0";
        toast.style.transform = "translate(-50%, -50%) scale(0.9)";
        setTimeout(() => {
            toast.style.display = "none";
        }, 300);
    }, 3000);
}
``