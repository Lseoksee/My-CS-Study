async function reqeste() {
    // 해당 주소는 docker-compose.yml 에 주소를 고정해둠
    const req = await fetch("http://192.168.0.2/api");
    console.log(await req.json());
}

setInterval(() => {
    reqeste();
}, 1000);
