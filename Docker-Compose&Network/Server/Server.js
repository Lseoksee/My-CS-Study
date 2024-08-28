const express = require("express");

const server = express();

let count = 0;
server.get("/api", (req, res) => {
    res.json({ count: count });
    count++;
});

server.listen(80, "0.0.0.0", () => {
    console.log("80 포트에서 서버 시작");
});
