function validateInputs(...values) {
    let invalid = true;
    values.forEach(val => {
        if(!val) {
            invalid = false;
            return;
        }
    });
    return invalid;
}

function invertPlaces() {
    let from = document.getElementById('from');
    let to = document.getElementById('to');

    if(from.value && to.value) {
        let temp = from.value;
        from.value = to.value;
        to.value = temp;
    }
}

async function getDirections() {
    let from = document.getElementById("from").value;
    let to = document.getElementById("to").value;
    let title = document.getElementsByTagName("h1")[0];

    if (!validateInputs(from, to)) {
        let info = document.getElementById('submit');
        info.textContent = "Enter above details!";

        setTimeout(() => {
            info.textContent = "Let's Go!";
        }, 3000);

        return;
    }

    title.textContent = "Loading.. please wait...";

    let data = {
        from: from.replaceAll(' ', '+'),
        to: to.replaceAll(' ', '+')
    }

    const resp = await fetch("/api/directions", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    resp.json().then(res => {
        title.textContent = `Directions from ${from} to ${to}`;
        document.getElementById('directions').setAttribute("src", `data:image/png;base64, ${res.src}`);
    })
}