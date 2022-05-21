window.onload = function() {
    const score = document.getElementById("score");
    const turn = document.getElementById("turn");
    const cells = document.getElementsByClassName("cell");
    var gameId;

    function getColoring(val) {
        const colorDict = {
            0: "#ffffff",
            2: "#dadab3",
            4: "#dada9b",
            8: "#dada7d",
            16: "#dada69",
            32: "#dada5a",
            64: "#dada4b",
            128: "#dada32",
            256: "#dada19",
            512: "#dada00",
            1024: "#ffc107",
            2048: "#ff9800"
        };

        return val >= 2048 ? colorDict[2048] : colorDict[val];
    }

    function resizeToFit(cell) {
        const defaultFontSize = Number(window.getComputedStyle(document.getElementById("field")).getPropertyValue("font-size").match(/\d+/)[0]);
        const fontSizeDict = {
            0: 100,
            1: 100,
            2: 100,
            3: 70,
            4: 50,
            5: 25
        };

        cell.style.fontSize = (fontSizeDict[cell.innerText.length] * parseFloat(defaultFontSize) / 100) + 'px';
    }

    function reloadField(obj) {
        gameId = obj.gameId;

        score.innerText = obj.totalScore;
        turn.innerText = obj.turnNumber;

        Array.from(cells).forEach((cell, index) => {
            const x = Math.floor(index / 4);
            const y = index % 4;

            const val = obj.gameField[x][y];
            cell.innerText = val == 0 ? '' : val;
            cell.style.background = getColoring(val);
            resizeToFit(cell);
        });

        if (obj.gameOver == true) {
            document.getElementById("over").style.display = 'inline';
            document.onkeydown = function(e) { }
            field.addEventListener('touchstart', e => {});
            field.addEventListener('touchend', e => {});
        }
    }

    function start() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", '/games', true);
        xhr.getResponseHeader("Content-type", "application/json");

        xhr.onload = function() {
            const obj = JSON.parse(this.responseText);
            reloadField(obj);
        }

        xhr.send();
    }

    function play(direction) {
        var xhr = new XMLHttpRequest();
        xhr.open("PUT", '/games/' + gameId, true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.getResponseHeader("Content-type", "application/json");

        xhr.onload = function() {
            const obj = JSON.parse(this.responseText);
            reloadField(obj);
        }

        xhr.send(JSON.stringify(direction));
    }

    document.onkeydown = function(e) {
        switch(e.which) {
            case 37: // left
            play("LEFT");
            break;

            case 38: // up
            play("UP");
            break;

            case 39: // right
            play("RIGHT");
            break;

            case 40: // down
            play("DOWN");
            break;

            default: return; // exit this handler for other keys
        }
        e.preventDefault(); // prevent the default action (scroll / move caret)
    };

    let touchstartX = 0;
    let touchendX = 0;
    let touchstartY = 0;
    let touchendY = 0;

    const body = document.getElementsByTagName('body')[0];

    function handleGesture() {
        const dX = touchendX - touchstartX;
        const dY = touchendY - touchstartY;

        if (Math.abs(dX) > Math.abs(dY)) {
            if (dX > 0) {
                play("RIGHT");
            } else {
                play("LEFT");
            }
        } else {
            if (dY > 0) {
                play("DOWN");
            } else {
                play("UP");
            }
        }
    }

    body.addEventListener('touchstart', e => {
        touchstartX = e.changedTouches[0].screenX;
        touchstartY = e.changedTouches[0].screenY;
    });

    body.addEventListener('touchend', e => {
        touchendX = e.changedTouches[0].screenX;
        touchendY = e.changedTouches[0].screenY;
        handleGesture();
    });

    start();
}