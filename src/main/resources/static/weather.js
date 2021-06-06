async function getWeatherDetails() {
    var results = document.querySelector("#results");
    var country = document.querySelector("#country").value;
    var city = document.querySelector("#city").value;

    if (!validateInputs(country, city)) {
        let info = document.getElementById('submit');
        info.value = "Enter above details!";

        setTimeout(() => {
            info.value = "Get Weather Details";
        }, 3000);

        return;
    }

    results.innerText = "Fetching...";

    var data = {
        city: city,
        country: country
    };

    const response = await fetch("/api/weather", {
        method: 'POST',
        headers: {
            'Content-Type': "application/json"
        },
        body: JSON.stringify(data)
    });

    response.json().then(resp => {
        results.innerText = resp.weatherDetails;
    });
}