async function displayPicture() {
    const resp = await fetch("/api/xkcd", {
        method: "GET",
        headers: {
            'Content-Type': "application/json"
        },
    });
    resp.json().then(img => {
        document.getElementById("picture").setAttribute("src", img.src);
        document.getElementById("next").style.display = "inline";
    });
}